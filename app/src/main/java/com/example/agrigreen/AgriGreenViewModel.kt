package com.example.agrigreen

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.ai.client.generativeai.GenerativeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AgriGreenViewModel : ViewModel() {

    var ImageUri by mutableStateOf<Uri?>(null)

    //login activity handling
    var emailEntered by mutableStateOf("")
    var passEntered by mutableStateOf("")
    var nameEntered by mutableStateOf("")

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authState.value = AuthState.UnAuthenticated
        }else{
            _authState.value = AuthState.Authenticated
            auth.currentUser?.uid?.let {
                getUserFromFirestore(
                    userId = it
                )
            }
        }
    }
    fun checkIfLoginFailed() : Boolean{
        return _authState.value==AuthState.UnAuthenticated
    }

    fun login(email : String,password : String){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or pass cannot be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{task->
                if(task.isSuccessful){
                    val currUserId = auth.currentUser?.uid
                    _authState.value = AuthState.Authenticated
                    if (currUserId != null) {
                        getUserFromFirestore(
                            currUserId
                        )
                    }
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"SomeThing went Wrong")
                }
            }
        loadChatHistory()
    }

    fun SignUp(email : String,password : String){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or pass cannot be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{task->
                if(task.isSuccessful){
                    val user = auth.currentUser
                    user?.let {
                        saveUserToFirestore(nameEntered,emailEntered,it.uid)
                    }
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"SomeThing went Wrong")
                }
            }
    }
    fun getUserFromFirestore(userId: String) {
        val db = FirebaseFirestore.getInstance()

        db.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val name = document.getString("name")
                    if (name != null) {
                        nameEntered = name
                    }
                } else {
                    nameEntered = ""
                }
            }
            .addOnFailureListener {
                nameEntered = ""
            }
    }
    fun saveUserToFirestore(name:String , email:String,userId : String){
        val db = FirebaseFirestore.getInstance()
        val userMap = hashMapOf(
            "name" to name,
            "email" to email
        )
        db.collection("users").document(userId)
            .set(userMap)
            .addOnSuccessListener {
                Log.d("Tag","User added succesfully")
            }
            .addOnFailureListener{ e ->
                Log.d("Tag","Error saving user : $e ")
            }
    }



    fun SignOut(){
        auth.signOut()
        _authState.value = AuthState.UnAuthenticated
        chatHistory.value = mutableListOf()
        nameEntered = ""
        emailEntered = ""
        passEntered = ""
    }

    //checking if user is already logged in
    val currentUser = FirebaseAuth.getInstance().currentUser
    fun checkLoginStatus() : Boolean{
        return if(currentUser!=null) true
        else false
    }


    //GEMINI Integration
    //using firestore to store chats of each user
    private val db = FirebaseFirestore.getInstance()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid ?: "anonymous"
    val apiKey = "AIzaSyDGqEIBMJfu9VbTHBtkXjxjKIPPK1RQhCw"
    val model = GenerativeModel(
        "gemini-2.0-flash",
        // Retrieve API key as an environmental variable defined in a Build Configuration
        // see https://github.com/google/secrets-gradle-plugin for further instructions
        apiKey
    )
    val isRequestOngoing = MutableLiveData(false)

    val chatHistory = MutableLiveData<MutableList<Pair<String,String>>>(mutableListOf())
    val QueryHistory = MutableLiveData<MutableList<String>>(mutableListOf())
    var currresponse by  mutableStateOf("")
    var currinput by mutableStateOf("")
    val chat = model.startChat()
    fun addMessageToChatHistory(query:String ,message: String) {
        val chatRef = db.collection("users").document(userId).collection("chats")
        val chatData = hashMapOf(
            "query" to query,
            "message" to message,
            "timestamp" to System.currentTimeMillis()
        )

        chatRef.add(chatData)
            .addOnSuccessListener { Log.d("Firestore", "Chat saved successfully!") }
            .addOnFailureListener { e -> Log.e("Firestore", "Error saving chat", e) }

        // Update local LiveData
        chatHistory.value = (chatHistory.value ?: mutableListOf()).apply {
            add(query to message)
        }
    }
    fun loadChatHistory() {
        db.collection("users").document(userId).collection("chats")
            .orderBy("timestamp")
            .get()
            .addOnSuccessListener { documents ->
                val chats = mutableListOf<Pair<String, String>>()
                for (doc in documents) {
                    val query = doc.getString("query") ?: ""
                    val message = doc.getString("message") ?: ""
                    chats.add(query to message)
                }
                chatHistory.value = chats
            }
            .addOnFailureListener { e -> Log.e("Firestore", "Error loading chat", e) }
    }


    // Note that sendMessage() is a suspend function and should be called from
    // a coroutine scope or another suspend function
    suspend fun getResponseFromGemini(input : String){
        try {
            isRequestOngoing.postValue(true)
            val reponsetext = chat.sendMessage(input).text
            if (reponsetext != null) {
                addMessageToChatHistory(input,reponsetext)
                currresponse = reponsetext
            }
        }finally {
            isRequestOngoing.postValue(false)
        }
    }


}


sealed class AuthState{
    object Authenticated : AuthState()
    object UnAuthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message : String) : AuthState()
}
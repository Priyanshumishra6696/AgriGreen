package com.example.agrigreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.agrigreen.firebase.AuthState
import com.example.agrigreen.firebase.FireBaseAuthentication
import com.google.ai.client.generativeai.GenerativeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgriGreenViewModel  @Inject constructor(
    private val fireBaseAuthentication: FireBaseAuthentication
): ViewModel() {

    init{
        fireBaseAuthentication.checkAuthStatus()
    }
    //login activity handling
    var emailEntered by mutableStateOf("")
    var passEntered by mutableStateOf("")
    var nameEntered by mutableStateOf("")

    val authState : LiveData<AuthState> = fireBaseAuthentication.authState
    fun PerformLogin(email:String,password:String){
        fireBaseAuthentication.loginUsingEmailPass(email,password)
    }
    fun PerformSignUp(email:String,password:String){
        fireBaseAuthentication.SignUp(email,password)
    }
    fun PerformSignOut(){
        fireBaseAuthentication.SignOut()
    }
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
            }
        }finally {
            isRequestOngoing.postValue(false)
        }
    }


}


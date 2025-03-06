package com.example.agrigreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.firebase.auth.FirebaseAuth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AgriGreenViewModel : ViewModel() {

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
        }
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
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"SomeThing went Wrong")
                }
            }

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
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"SomeThing went Wrong")
                }
            }
    }

    fun SignOut(){
        auth.signOut()
        _authState.value = AuthState.UnAuthenticated
    }

    //GEMINI Integration

    val apiKey = "AIzaSyDGqEIBMJfu9VbTHBtkXjxjKIPPK1RQhCw"
    val model = GenerativeModel(
        "gemini-2.0-flash",
        // Retrieve API key as an environmental variable defined in a Build Configuration
        // see https://github.com/google/secrets-gradle-plugin for further instructions
        apiKey
    )

//    val chatHistory = listOf(
//    )

    val chat = model.startChat()

    // Note that sendMessage() is a suspend function and should be called from
    // a coroutine scope or another suspend function
    suspend fun getResponseFromGemini(input : String): String? {
        return withContext(Dispatchers.IO) {
            chat.sendMessage(input).text
        }
    }





}


sealed class AuthState{
    object Authenticated : AuthState()
    object UnAuthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message : String) : AuthState()
}
package com.example.agrigreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AgriGreenViewModel : ViewModel() {

    //login activity handling
    var emailEntered by mutableStateOf("")
    var passEntered by mutableStateOf("")
}
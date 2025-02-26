package com.example.agrigreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.agrigreen.navigation.AgriGreenNav
import com.example.agrigreen.ui.screens.LoginScreen
import com.example.agrigreen.ui.screens.SignUpScreen
import com.example.agrigreen.ui.screens.WelcomeScreen
import com.example.agrigreen.ui.theme.AgriGreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(this)[AgriGreenViewModel::class.java]

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgriGreenTheme {
                AgriGreenNav(viewModel)
            }
        }
    }
}

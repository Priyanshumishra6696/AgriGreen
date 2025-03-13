package com.example.agrigreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.agrigreen.navigation.AgriGreenNav
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

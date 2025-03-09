package com.example.agrigreen.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agrigreen.AgriGreenViewModel
import com.example.agrigreen.ui.components.BottomNavigationBar
import com.example.agrigreen.utils.LoginSignupNavigationItems

@Composable
fun ProfileScreem(viewModel: AgriGreenViewModel,navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Button(
            onClick = {
                viewModel.SignOut()
                navController.navigate(LoginSignupNavigationItems.WelcomeScreen.route)
            }
        ) {
            Text(
                text = "Sign Out"
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar(viewModel,navController)
    }
}
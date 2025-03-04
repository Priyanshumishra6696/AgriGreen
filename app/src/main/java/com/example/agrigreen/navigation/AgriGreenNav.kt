package com.example.agrigreen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrigreen.AgriGreenViewModel
import com.example.agrigreen.ui.screens.ChatBotScreen
import com.example.agrigreen.ui.screens.GovtSchemesScreen
import com.example.agrigreen.ui.screens.HomeScreen
import com.example.agrigreen.ui.screens.LoginScreen
import com.example.agrigreen.ui.screens.ProfileScreem
import com.example.agrigreen.ui.screens.SignUpScreen
import com.example.agrigreen.ui.screens.WelcomeScreen
import com.example.agrigreen.utils.HomeBottomNavigationItems
import com.example.agrigreen.utils.LoginSignupNavigationItems

@Composable
fun AgriGreenNav(viewModel: AgriGreenViewModel){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginSignupNavigationItems.WelcomeScreen.route,
        builder = {
            composable(LoginSignupNavigationItems.WelcomeScreen.route){
                WelcomeScreen(viewModel,navController)
            }
            composable(LoginSignupNavigationItems.LoginScreen.route){
                LoginScreen(viewModel,navController)
            }
            composable(LoginSignupNavigationItems.SignUpScreen.route){
                SignUpScreen(viewModel,navController)
            }
            composable(LoginSignupNavigationItems.HomeScreen.route){
                HomeScreen(viewModel,navController)
            }
            composable(HomeBottomNavigationItems.GovtSchemesScreen.route){
                GovtSchemesScreen(viewModel,navController)
            }
            composable(HomeBottomNavigationItems.ChatBotScreen.route){
                ChatBotScreen(viewModel,navController)
            }
            composable(HomeBottomNavigationItems.ProfileScreen.route){
                ProfileScreem(viewModel,navController)
            }
        }
    )
}


package com.example.agrigreen.utils

sealed class LoginSignupNavigationItems(
    val route : String
){
    object WelcomeScreen : LoginSignupNavigationItems(
        route = "WelcomeScreen"
    )
    object LoginScreen : LoginSignupNavigationItems(
        route = "LoginScreen"
    )
    object SignUpScreen : LoginSignupNavigationItems(
        route = "SignUpScreen"
    )
    object HomeScreen : LoginSignupNavigationItems(
        route = "HomeScreen"
    )
}
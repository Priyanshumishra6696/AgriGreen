package com.example.agrigreen.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agrigreen.AgriGreenViewModel
import com.example.agrigreen.R
import com.example.agrigreen.ui.components.DarkGreenHeadingText
import com.example.agrigreen.ui.components.LoginSignUpButton
import com.example.agrigreen.utils.LoginSignupNavigationItems

@Composable
fun WelcomeScreen(viewModel: AgriGreenViewModel,navController: NavController){
    //logic and params

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp


    //ui
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ){



        //IMAGE
        Image(
            modifier = Modifier
                .size(height = screenHeightDp/2, width = screenWidthDp)
                .padding(end = 16.dp),
            painter = painterResource(id = R.drawable.welcomepageimg),
            contentDescription = null
        )




        //Spacer
        Spacer(modifier = Modifier.height(32.dp))




        //WELCOME TEXT
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            DarkGreenHeadingText("Welcome")
        }




        //Spacer
        Spacer(modifier = Modifier.height(32.dp))




        //Normal Text
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bringing Nature Closer to You:"
            )
            Text(
                text = "Transform Your Space with"
            )
            Text(
                text = "Our Finest Green Selections!"
            )
        }




        //Spacer
        Spacer(modifier = Modifier.height(32.dp))




        //Login/Sign Up Button
        LoginSignUpButton(
            onClick = {navController.navigate(LoginSignupNavigationItems.LoginScreen.route)},
            text = "Login/SignUp"
        )




        //Spacer
        Spacer(modifier = Modifier.height(24.dp))





        //Not Now Button
         Row(
             modifier = Modifier
                 .fillMaxWidth(),
             horizontalArrangement = Arrangement.Center
         ) {
             Text(
                 modifier = Modifier
                     .clickable {
                         navController.navigate(LoginSignupNavigationItems.HomeScreen.route)
                     },
                 text = "Not Now",
                 fontSize = 20.sp,
                 style = TextStyle(textDecoration = TextDecoration.Underline),
                 fontWeight = FontWeight.Bold
             )
         }
    }
}
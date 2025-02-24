package com.example.agrigreen.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrigreen.AgriGreenViewModel
import com.example.agrigreen.ui.components.DarkGreenHeadingText
import com.example.agrigreen.ui.components.InputField
import com.example.agrigreen.ui.components.LoginSignUpButton

@Composable
fun SignUpScreen(viewModel: AgriGreenViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        //Spacer
        Spacer(modifier = Modifier.height(64.dp))



        //AgriGreen Text
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            DarkGreenHeadingText("AgriGreen")
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


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            InputField(
                viewModel,
                "Enter Name",
                false,
                passBool = false,
                nameBool = true)
        }

        //Spacer
        Spacer(modifier = Modifier.height(32.dp))



        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            InputField(
                viewModel,
                "Enter Email",
                true,
                passBool = false,
                nameBool = false
            )
        }


        //Spacer
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            InputField(
                viewModel,
                "Enter Password",
                false,
                passBool = true,
                nameBool = false
                )
        }


        //Spacer
        Spacer(modifier = Modifier.height(64.dp))


        LoginSignUpButton(
            onClick = {},
            text = "SignUp"
        )

        //Spacer
        Spacer(modifier = Modifier.height(32.dp))



        //Not Now Button
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .clickable {
                        //TODO
                    },
                text = "SignUp",
                fontSize = 20.sp,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                fontWeight = FontWeight.Bold
            )
        }

        //Spacer
        Spacer(modifier = Modifier.height(32.dp))

        //Not Now Button
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .clickable {
                        //TODO
                    },
                text = "Forgot Password?",
                fontSize = 20.sp,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                fontWeight = FontWeight.Bold
            )
        }
    }
}
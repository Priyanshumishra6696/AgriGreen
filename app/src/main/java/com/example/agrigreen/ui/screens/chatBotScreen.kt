package com.example.agrigreen.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agrigreen.AgriGreenViewModel
import com.example.agrigreen.ui.components.BottomNavigationBar
import kotlinx.coroutines.launch

@Composable
fun ChatBotScreen(viewModel: AgriGreenViewModel,navController: NavController){
    var gotResponse by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Centers content vertically
        horizontalAlignment = Alignment.CenterHorizontally // Centers content horizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(), // Ensures LazyColumn takes full space
            verticalArrangement = Arrangement.Center, // Centers content vertically inside LazyColumn
            horizontalAlignment = Alignment.CenterHorizontally // Centers content horizontally
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "What Can I Do",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "For You",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        chatBotBottomSection(viewModel)
        BottomNavigationBar(viewModel, navController)
    }
}

@Composable
fun chatBotBottomSection(viewModel: AgriGreenViewModel){
    var text by remember { mutableStateOf("") }
    var gotResponse by remember { mutableStateOf("") }
    val couroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = text,
            onValueChange = {text=it}
        )
        Button(
            onClick = {
                couroutineScope.launch {
                    gotResponse = viewModel.getResponseFromGemini(input = text).toString()
                }
            }
        ) {
            Text(text = "Click it")
        }
    }

    gotResponse?.let {
        Text(
            text = gotResponse
        )
    }
}
package com.example.agrigreen.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agrigreen.AgriGreenViewModel
import com.example.agrigreen.ui.components.BottomNavigationBar
import kotlinx.coroutines.launch

@Composable
fun ChatBotScreen(viewModel: AgriGreenViewModel,navController: NavController){
    var text by remember { mutableStateOf("") }
    var gotResponse by remember { mutableStateOf("") }
    val couroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn {
            item {
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
                gotResponse?.let {
                    Text(
                        text = gotResponse
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar(viewModel,navController)
    }
}
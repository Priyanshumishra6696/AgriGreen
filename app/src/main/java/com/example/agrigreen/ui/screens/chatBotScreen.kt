package com.example.agrigreen.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agrigreen.AgriGreenViewModel
import com.example.agrigreen.ui.components.BottomNavigationBar
import com.example.agrigreen.ui.theme.ScreenWhite
import kotlinx.coroutines.launch

@Composable
fun ChatBotScreen(viewModel: AgriGreenViewModel,navController: NavController){
    val chatToBeDisplayed by viewModel.chatHistory.observeAsState()
    val isRequestOngoing by viewModel.isRequestOngoing.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                if(isRequestOngoing == true){
                    CircularProgressIndicator()
                }
            }
            if (isRequestOngoing==false){
                chatToBeDisplayed?.forEachIndexed { index, pair ->
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Spacer(
                                modifier = Modifier
                                    .weight(0.5f)
                            )
                            Row(
                                modifier = Modifier
                                    .weight(0.5f)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color.LightGray)
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(8.dp),
                                    text = pair.first,
                                    fontSize = 17.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row {
                            Text(
                                text = pair.second,
                                fontSize = 17.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
//                        Divider(
//                            color = Color.Black,
//                            thickness = 8.dp
//                        )
                    }
                }
            }
            if(chatToBeDisplayed.isNullOrEmpty()){
                item {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun chatBotBottomSection(viewModel: AgriGreenViewModel){
    var text by remember { mutableStateOf("") }
//    var gotResponse by remember { mutableStateOf("") }
    val couroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier.weight(1f),
            value = text,
            onValueChange = {text=it},
            label = { Text(text = "Enter Your Query") },
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Black,
                containerColor = ScreenWhite,
                selectionColors = TextSelectionColors(
                    handleColor = Color.Black,
                    backgroundColor = Color.White
                )
            )
        )
        Button(
            modifier = Modifier,
            onClick = {
                couroutineScope.launch {
                    viewModel.getResponseFromGemini(input = text)
                }
            },
            colors = ButtonColors(
                containerColor = ScreenWhite,
                contentColor = Color.Black,
                disabledContainerColor = ScreenWhite,
                disabledContentColor = Color.Black,
            )
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }

//    gotResponse?.let {
//        viewModel.addMessageToChatHistory(gotResponse)
//    }
}
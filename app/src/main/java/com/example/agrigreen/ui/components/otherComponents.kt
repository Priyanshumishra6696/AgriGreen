package com.example.agrigreen.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.agrigreen.AgriGreenViewModel
import com.example.agrigreen.ui.theme.ScreenWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    viewModel: AgriGreenViewModel
){
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp
    TextField(
        modifier = Modifier.width(screenWidthDp-100.dp),
        value = viewModel.emailEntered,
        onValueChange = { viewModel.emailEntered=it },
        label = { Text("Enter Email") },
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
}
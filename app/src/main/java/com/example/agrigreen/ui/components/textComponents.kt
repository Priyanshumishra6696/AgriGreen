package com.example.agrigreen.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.agrigreen.ui.theme.DarkGreen

@Composable
fun DarkGreenHeadingText(text : String){
    Text(
        text = text,
        fontSize = 48.sp,
        color = DarkGreen,
        fontWeight = FontWeight.Bold
    )
}
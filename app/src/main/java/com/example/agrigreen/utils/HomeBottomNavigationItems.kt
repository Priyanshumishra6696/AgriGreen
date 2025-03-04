package com.example.agrigreen.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Schema
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Schema
import androidx.compose.material3.OutlinedCard
import androidx.compose.ui.graphics.vector.ImageVector

sealed class HomeBottomNavigationItems(
    val route : String,
    val title : String,
    val filledIcon : ImageVector,
    val outlinedIcon : ImageVector
) {
    object HomeScreen : HomeBottomNavigationItems(
        route = "HomeScreen",
        title = "Home",
        filledIcon = Icons.Filled.Home,
        outlinedIcon = Icons.Outlined.Home
    )
    object GovtSchemesScreen : HomeBottomNavigationItems(
        route = "GovtSchemesScreen",
        title = "Schemes",
        filledIcon = Icons.Filled.Schema,
        outlinedIcon = Icons.Outlined.Schema
    )
    object ChatBotScreen : HomeBottomNavigationItems(
        route = "ChatBotScreen",
        title = "AI",
        filledIcon = Icons.Filled.ChatBubble,
        outlinedIcon = Icons.Outlined.ChatBubble
    )
    object ProfileScreen : HomeBottomNavigationItems(
        route = "ProfileScreen",
        title = "Profile",
        filledIcon = Icons.Filled.AccountCircle,
        outlinedIcon = Icons.Default.AccountCircle
    )
}
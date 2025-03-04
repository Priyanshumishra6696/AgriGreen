package com.example.agrigreen.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.agrigreen.AgriGreenViewModel
import com.example.agrigreen.utils.Constants.screens

@Composable
fun BottomNavigationBar(viewModel: AgriGreenViewModel,navController: NavController){
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        screens.forEachIndexed { index, homeBottomNavigationItems ->
            NavigationBarItem(
                label = {
                    if(currentRoute==homeBottomNavigationItems.route){
                        Text(text = homeBottomNavigationItems.title)
                    }
                },
                selected = currentRoute==homeBottomNavigationItems.route,
                icon = {
                    if(currentRoute==homeBottomNavigationItems.route){
                        Icon(imageVector = homeBottomNavigationItems.filledIcon , contentDescription = null)
                    }else{
                        Icon(imageVector = homeBottomNavigationItems.outlinedIcon , contentDescription = null)
                    }
                },
                onClick = {
                    navController.navigate(homeBottomNavigationItems.route)
                },
                colors = NavigationBarItemColors(
                    selectedIndicatorColor = Color.White,
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Black,
                    disabledIconColor = Color.Black,
                    disabledTextColor = Color.Black,
                )
            )
        }
    }
}
package com.example.agrigreen.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.ImportExport
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agrigreen.AgriGreenViewModel
import com.example.agrigreen.R
import com.example.agrigreen.ui.theme.DarkGreen
import com.example.agrigreen.ui.theme.ParrotGreen
import com.example.agrigreen.ui.theme.ScreenWhite

@Composable
fun HomeScreen(viewModel: AgriGreenViewModel,navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        LazyColumn {
            item {
                TopBar(viewModel,navController)
            }
            item {
                MiddleSection(viewModel,navController)
            }
            item {
                BottomSection(viewModel,navController)
            }
        }
    }
}

@Composable
fun TopBar(viewModel: AgriGreenViewModel,navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 32.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 32.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "AgriGreen",
            fontSize = 32.sp,
            color = DarkGreen,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {}
        ) {
            Icon(
                modifier = Modifier
                    .size(40.dp),
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null
            )
        }
    }
}

@Composable
fun MiddleSection(viewModel: AgriGreenViewModel,navController: NavController){
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp
    val temp = screenHeightDp/4
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(temp)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.5f)
                .height(temp)
                .clip(RoundedCornerShape(8.dp))
                .background(ParrotGreen)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.welcomepagelogo),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .weight(0.5f)
                .height(temp)
                .clip(RoundedCornerShape(8.dp))
                .background(ParrotGreen)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.weatherimg),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun BottomSection(viewModel: AgriGreenViewModel,navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "AgriGreen",
                color = DarkGreen,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
            )
            Icon(
                imageVector = Icons.Default.EnergySavingsLeaf,
                contentDescription = null,
                tint = ParrotGreen
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Column {
                Text(
                    text = "Supporting Farmers in",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
                Text(
                    text = "Safeguarding their crop health",
                    color = Color.Black,
                    fontSize = 20.sp,
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        //Button to take a picture
        Row (
            modifier = Modifier
                .padding(16.dp)
        ){
            ImageSelectionButton(
                viewModel = viewModel,
                navController = navController,
                text1 = "Take a Picture",
                text2 = "of your plant",
                IconImgVector = Icons.Default.CameraAlt
            )
        }
        //button to import from gallery
        Row (
            modifier = Modifier
                .padding(16.dp)
        ){
            ImageSelectionButton(
                viewModel = viewModel,
                navController = navController,
                text1 = "Import",
                text2 = "From Gallery",
                IconImgVector = Icons.Default.ImportExport
            )
        }
    }
}

@Composable
fun ImageSelectionButton(viewModel: AgriGreenViewModel,navController: NavController,text1: String,text2: String,IconImgVector: ImageVector){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(ParrotGreen)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = text1,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = text2,
                color = DarkGreen,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp),
                imageVector = IconImgVector,
                contentDescription = null,
                tint = Color.White
            )
        }

    }
}
package com.example.agrigreen.ui.screens

import android.net.Uri
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import com.example.agrigreen.AgriGreenViewModel
import com.example.agrigreen.R
import com.example.agrigreen.ui.components.BottomNavigationBar
import com.example.agrigreen.ui.theme.DarkGreen
import com.example.agrigreen.ui.theme.ParrotGreen
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import java.io.File

@Composable
fun HomeScreen(viewModel: AgriGreenViewModel,navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
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
        BottomNavigationBar(viewModel,navController)
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

//@Composable
//fun BottomSection(viewModel: AgriGreenViewModel,navController: NavController){
//    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//        // Callback is invoked after the user selects a media item or closes the
//        // photo picker.
//        if (uri != null) {
//            viewModel.ImageUri = uri
//            navController.navigate("Plant")
//            Log.d("PhotoPicker", "Selected URI: $uri")
//        } else {
//            Log.d("PhotoPicker", "No media selected")
//        }
//    }
//    val context = LocalContext.current
//    val activity = context as ComponentActivity
//    val imageUri = remember{ mutableStateOf<Uri?>(null) }
//    val file = File(context.cacheDir,"${System.currentTimeMillis()}.jpg")
//    val uri = FileProvider.getUriForFile(context,"${context.packageName}.provider",file)
//    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()){ success ->
//        if(success){
//            imageUri.value = uri
//            viewModel.ImageUri = uri
//            navController.navigate("Plant")
//
//            //send image to function
//        }
//
//    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        Spacer(modifier = Modifier.height(32.dp))
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Text(
//                text = "AgriGreen",
//                color = DarkGreen,
//                fontWeight = FontWeight.Bold,
//                fontSize = 32.sp,
//            )
//            Icon(
//                imageVector = Icons.Default.EnergySavingsLeaf,
//                contentDescription = null,
//                tint = ParrotGreen
//            )
//        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ){
//            Column {
//                Text(
//                    text = "Supporting Farmers in",
//                    color = Color.Black,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 24.sp,
//                )
//                Text(
//                    text = "Safeguarding their crop health",
//                    color = Color.Black,
//                    fontSize = 20.sp,
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(32.dp))
//        //Button to take a picture
//        Row (
//            modifier = Modifier
//                .padding(16.dp)
//        ){
//            ImageSelectionButton(
//                viewModel = viewModel,
//                navController = navController,
//                text1 = "Take a Picture",
//                text2 = "of your plant",
//                IconImgVector = Icons.Default.CameraAlt,
//                onClick = {
//                    cameraLauncher.launch(uri)
////                    CaptureImageFromCamera()
//                }
//            )
//        }
//        //button to import from gallery
//        Row (
//            modifier = Modifier
//                .padding(16.dp)
//        ){
//            ImageSelectionButton(
//                viewModel = viewModel,
//                navController = navController,
//                text1 = "Import",
//                text2 = "From Gallery",
//                IconImgVector = Icons.Default.ImportExport,
//                onClick = {
//                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//                }
//            )
//        }
//    }
//}


@Composable
fun BottomSection(viewModel: AgriGreenViewModel, navController: NavController) {
    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            viewModel.ImageUri = uri
            navController.navigate("Plant")
            Log.d("PhotoPicker", "Selected URI: $uri")
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    val context = LocalContext.current
    val activity = context as? ComponentActivity // Safe cast
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            imageUri?.let { uri ->
                viewModel.ImageUri = uri
                navController.navigate("Plant")
                Log.d("Camera", "Picture saved at: $uri")
            }
        } else {
            Log.w("Camera", "Picture capture failed or cancelled")
        }
    }

    // Function to launch the camera
    fun launchCamera() {
        val file = File.createTempFile(
            "IMG_",
            ".jpg",
            context.cacheDir
        )
        val uri: Uri = FileProvider.getUriForFile( // Declare uri as non-nullable
            context,
            "${context.packageName}.provider",
            file
        )
        imageUri = uri // Update the state
        cameraLauncher.launch(uri) // Use the local, non-nullable uri
    }

    // Permission handling
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            launchCamera()
        } else {
            // Handle permission denial (e.g., show a message)
            Log.e("Camera", "Camera permission denied")
            // You might want to show a Toast or a Snackbar here
        }
    }

    fun askCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
                launchCamera()
            }
            activity?.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) == true -> {
                // Explain why the permission is needed (optional but recommended)
                // You might want to show a dialog or a Toast here
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
            else -> {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

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
        ) {
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
        // Button to take a picture
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            ImageSelectionButton(
                viewModel = viewModel,
                navController = navController,
                text1 = "Take a Picture",
                text2 = "of your plant",
                IconImgVector = Icons.Default.CameraAlt,
                onClick = {
                    askCameraPermission() // Call the permission check
                }
            )
        }
        // button to import from gallery
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            ImageSelectionButton(
                viewModel = viewModel,
                navController = navController,
                text1 = "Import",
                text2 = "From Gallery",
                IconImgVector = Icons.Default.ImportExport,
                onClick = {
                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
            )
        }
    }
}

@Composable
fun ImageSelectionButton(viewModel: AgriGreenViewModel,
                         navController: NavController,
                         text1: String, text2: String,
                         IconImgVector: ImageVector,
                         onClick: () -> Unit,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(ParrotGreen)
            .padding(16.dp)
            .clickable {
                onClick()
            },
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
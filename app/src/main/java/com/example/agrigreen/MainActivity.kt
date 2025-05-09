package com.example.agrigreen

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.example.agrigreen.model.PlantDiseaseClassifier
import com.example.agrigreen.navigation.AgriGreenNav
import com.example.agrigreen.ui.theme.AgriGreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            val viewModel = ViewModelProvider(this)[AgriGreenViewModel::class.java]
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                AgriGreenTheme {

//                    PlantDiseaseScreen(
//                        context = this
//                    )
                    AgriGreenNav(viewModel,this)
                }
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Error during initialization", e)
        }
    }
}

@Composable
fun PlantDiseaseScreen(viewModel: AgriGreenViewModel,context: Context) {
    val classifier = remember { PlantDiseaseClassifier(context) }
    var result by remember { mutableStateOf("Click to analyze image") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DisplayImageFromUri(viewModel.ImageUri)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            result =
                viewModel.ImageUri?.let { classifier.classifyImage(it) }.toString() // Pass the image ID
        }) {
            Text("Analyze Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = result, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}


@Composable
fun DisplayImageFromUri(imageUri: Uri?) {
    if (imageUri != null) {
        AsyncImage(
            model = imageUri,
            contentDescription = "Selected Image",
            modifier = Modifier.size(200.dp) // Adjust as needed
        )
    } else {
        Text("No image selected")
    }
}



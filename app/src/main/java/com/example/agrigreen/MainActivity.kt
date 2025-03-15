package com.example.agrigreen

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.agrigreen.navigation.AgriGreenNav
import com.example.agrigreen.ui.theme.AgriGreenTheme
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(this)[AgriGreenViewModel::class.java]

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgriGreenTheme {
//                val labels = listOf( // Your class labels
//                    "Apple__Apple_scab",
//                    "Apple__Black_rot",
//                    "Apple__Cedar_apple_rust",
//                    "Apple__healthy",
//                    "Blueberry__healthy",
//                            "Cherry_(including_sour)__Powdery_mildew",
//                            "Cherry_(including_sour)__healthy",
//                            "Corn_(maize)__Cercospora_leaf_spot_Gray_leaf_Spot",
//                            "Corn_(maize)__Common_rust_",
//                            "Corn_(maize)__Northern_Leaf_Bligth",
//                            "Corn_(maize)__healthy",
//                            "Grape__Block_rot",
//                            "Grape__Esca_(Black_Measles)",
//                            "Grape___Leaf_blight_(Isariopsis_Leaf_Spot)",
//                            "Grape___healthy",
//                            "Orange___Haunglongbing_(Citrus_greening)",
//                            "Peach___Bacterial_spot",
//                            "Peach___healthy",
//                            "Pepper,_bell___Bacterial_spot",
//                            "Pepper,_bell___healthy",
//                            "Potato___Early_blight",
//                            "Potato___Late_blight",
//                            "Potato___healthy",
//                            "Raspberry___healthy",
//                            "Soybean___healthy",
//                            "Squash___Powdery_mildew",
//                            "Strawberry___Leaf_scorch",
//                            "Strawberry___healthy",
//                            "Tomato___Bacterial_spot",
//                            "Tomato___Early_blight",
//                            "Tomato___Late_blight",
//                            "Tomato___Leaf_Mold",
//                            "Tomato___Septoria_leaf_spot",
//                            "Tomato___Spider_mites Two-spotted_spider_mite",
//                            "Tomato___Target_Spot",
//                            "Tomato___Tomato_Yellow_Leaf_Curl_Virus",
//                            "Tomato___Tomato_mosaic_virus",
//                            "Tomato___healthy"
//                )
//
//                // Fix: Use `this@MainActivity` as context
//                val context = this@MainActivity
//
//                // Fix: Ensure image is in drawable and load correctly
//                val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.cornmaizecommonrust)
//
//                // Run inference
//                val (plant, disease) = runInference(context, bitmap, "model.tflite", labels)
//
//                Log.d("TAG","Plant $plant Disease : $disease")
                predictPlantDisease(context = this)
                // AgriGreenNav(viewModel) // Uncomment when UI is ready
            }
        }
    }
}
// Load image from res/drawable
fun loadImageFromResources(context: Context, resId: Int): Bitmap {
    return BitmapFactory.decodeResource(context.resources, resId)
}

// Preprocess Image
fun preprocessImage(bitmap: Bitmap, imageSize: Int): TensorImage {
    val imageProcessor = ImageProcessor.Builder()
        .add(ResizeOp(imageSize, imageSize, ResizeOp.ResizeMethod.BILINEAR))
        .build()

    val tensorImage = TensorImage(DataType.FLOAT32)
    tensorImage.load(bitmap)
    return imageProcessor.process(tensorImage)
}

// Load Model
fun loadModelFile(context: Context, modelFile: String): MappedByteBuffer {
    val fileDescriptor: AssetFileDescriptor = context.assets.openFd(modelFile)
    val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
    val fileChannel: FileChannel = inputStream.channel
    return fileChannel.map(FileChannel.MapMode.READ_ONLY, fileDescriptor.startOffset, fileDescriptor.declaredLength)
}

// Run Model Inference (Considering Model Returns Probabilities)
fun runInference(context: Context, image: Bitmap, modelFile: String, labels: List<String>): Pair<String, Float> {
    val interpreter = Interpreter(loadModelFile(context, modelFile))

    val imageSize = 224 // Adjust based on model input size
    val processedImage = preprocessImage(image, imageSize)

    val input = processedImage.buffer
    val output = Array(1) { FloatArray(labels.size) } // Output is a probability distribution

    interpreter.run(input, output)

    // Get the index of the highest probability
    val maxIndex = output[0].indices.maxByOrNull { output[0][it] } ?: -1
    val predictedLabel = labels[maxIndex]
    val confidence = output[0][maxIndex]  // Get confidence score

    return Pair(predictedLabel, confidence)
}

// Extract Plant Name and Disease from Label
fun extractPlantDisease(label: String): Pair<String, String> {
    val parts = label.split("__")
    val plantName = parts[0]
    val diseaseName = if (parts.size > 1) parts[1].replace("_", " ") else "Healthy"

    return Pair(plantName, diseaseName)
}

// Call Function in Activity
fun predictPlantDisease(context: Context) {
    val labels = listOf(
        "Apple__Apple_scab",
        "Apple__Black_rot",
        "Apple__Cedar_apple_rust",
        "Apple__healthy",
        "Blueberry__healthy",
        "Cherry_(including_sour)__Powdery_mildew",
        "Cherry_(including_sour)__healthy",
        "Corn_(maize)__Cercospora_leaf_spot_Gray_leaf_Spot",
        "Corn_(maize)__Common_rust_",
        "Corn_(maize)__Northern_Leaf_Bligth",
        "Corn_(maize)__healthy",
        "Grape__Block_rot",
        "Grape__Esca_(Black_Measles)",
        "Grape___Leaf_blight_(Isariopsis_Leaf_Spot)",
        "Grape___healthy",
        "Orange___Haunglongbing_(Citrus_greening)",
        "Peach___Bacterial_spot",
        "Peach___healthy",
        "Pepper,_bell___Bacterial_spot",
        "Pepper,_bell___healthy",
        "Potato___Early_blight",
        "Potato___Late_blight",
        "Potato___healthy",
        "Raspberry___healthy",
        "Soybean___healthy",
        "Squash___Powdery_mildew",
        "Strawberry___Leaf_scorch",
        "Strawberry___healthy",
        "Tomato___Bacterial_spot",
        "Tomato___Early_blight",
        "Tomato___Late_blight",
        "Tomato___Leaf_Mold",
        "Tomato___Septoria_leaf_spot",
        "Tomato___Spider_mites Two-spotted_spider_mite",
        "Tomato___Target_Spot",
        "Tomato___Tomato_Yellow_Leaf_Curl_Virus",
        "Tomato___Tomato_mosaic_virus",
        "Tomato___healthy"
        // Add all 38 labels here
    )

    val bitmap = loadImageFromResources(context, R.drawable.tomato)
    val (predictedLabel, confidence) = runInference(context, bitmap, "model.tflite", labels)

    val (plant, disease) = extractPlantDisease(predictedLabel)
    Log.d("TAG","Plant: $plant, Disease: $disease, Confidence: ${confidence * 100}%")

}

//fun preprocessImage(bitmap: Bitmap, imageSize: Int): TensorImage {
//    val imageProcessor = ImageProcessor.Builder()
//        .add(ResizeOp(imageSize, imageSize, ResizeOp.ResizeMethod.BILINEAR))
//        .build()
//
//    val tensorImage = TensorImage(DataType.FLOAT32)
//    tensorImage.load(bitmap)
//    return imageProcessor.process(tensorImage)
//}
//
//fun loadModelFile(context: Context, modelFile: String): MappedByteBuffer {
//    val fileDescriptor: AssetFileDescriptor = context.assets.openFd(modelFile)
//    val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
//    val fileChannel: FileChannel = inputStream.channel
//    return fileChannel.map(FileChannel.MapMode.READ_ONLY, fileDescriptor.startOffset, fileDescriptor.declaredLength)
//}
//
//fun runInference(context: Context, image: Bitmap, modelFile: String, labels: List<String>): Pair<String, String> {
//    val interpreter = Interpreter(loadModelFile(context, modelFile))
//
//    val imageSize = 224 // Adjust based on model input size
//    val processedImage = preprocessImage(image, imageSize)
//
//    // Fix: Convert TensorImage to input tensor (1D FloatArray)
//    val inputBuffer = processedImage.buffer.rewind()
//
//    val output = Array(1) { FloatArray(labels.size) } // Adjust output size
//
//    interpreter.run(inputBuffer, output)
//
//    val maxIndex = output[0].indices.maxByOrNull { output[0][it] } ?: -1
//    val predictedLabel = labels.getOrElse(maxIndex) { "Unknown" }
//
//    return extractPlantDisease(predictedLabel)
//}
//
//fun extractPlantDisease(label: String): Pair<String, String> {
//    val parts = label.split("__")
//    val plantName = parts[0]
//    val diseaseName = if (parts.size > 1) parts[1].replace("_", " ") else "Healthy"
//
//    return Pair(plantName, diseaseName)
//}
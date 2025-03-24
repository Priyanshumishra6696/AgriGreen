plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.agrigreen"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.agrigreen"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.9.0"))

    //
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")

    // Add the dependencies for any other desired Firebase products
    // https://firebase.google.com/docs/android/setup#available-libraries

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth")

    // Declare the dependency for the Cloud Firestore library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-firestore")


    //icons
    implementation(libs.androidx.material.icons.extended)

    //Gemini integration
    implementation(libs.generativeai)

    //tensorflow
//    implementation("org.tensorflow:tensorflow-lite-task-vision:2.9.0") // For image-based models
//    implementation("org.tensorflow:tensorflow-lite-task-text:2.9.0")   // For text-based models
//    implementation("org.tensorflow:tensorflow-lite-support:2.9.0")     // For general TFLite support
// TensorFlow Lite Task Library (for vision models)
    implementation("org.tensorflow:tensorflow-lite-task-vision:0.4.4") // Latest stable version

    // TensorFlow Lite Task Library (for text models)
    implementation("org.tensorflow:tensorflow-lite-task-text:0.4.4") // Latest stable version

    // TensorFlow Lite Support Library (for pre/post-processing)
    implementation("org.tensorflow:tensorflow-lite-support:0.4.4") // Latest stable version
//    implementation("org.tensorflow:tensorflow-core-platform:0.3.1")
}
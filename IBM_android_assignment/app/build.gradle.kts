plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.ibm.rides"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ibm.rides"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://random-data-api.com/\"")
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
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {
    // Core Libraries
    implementation(libs.androidx.core.ktx)                       // Android KTX core
    implementation(libs.androidx.lifecycle.runtime.ktx)          // Lifecycle-aware components
    implementation(libs.androidx.activity.compose)               // Jetpack Compose with activities

    // Compose Libraries
    implementation(platform(libs.androidx.compose.bom))          // Compose BOM for version management
    implementation(libs.androidx.ui)                             // Core UI components for Compose
    implementation(libs.androidx.ui.graphics)                    // Graphics support for Compose
    implementation(libs.androidx.ui.tooling.preview)             // UI tooling support
    implementation(libs.androidx.material3)                      // Material Design components with Compose

    // ViewBinding
    implementation("androidx.databinding:viewbinding:4.1.0")     // Enable ViewBinding for XML layouts

    // Navigation Components
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")  // Navigation for fragments
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")        // Navigation UI for easier navigation
    implementation("androidx.navigation:navigation-compose:2.6.0")       // Compose support for Navigation

    // Coroutine Support
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")  // Coroutines for background tasks

    // Retrofit for Networking (Optional, you can use another library if preferred)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")        // Retrofit for API calls
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")  // Gson converter for JSON serialization
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // Testing Libraries
    testImplementation(libs.junit)                                   // JUnit for unit tests
    androidTestImplementation(libs.androidx.junit)                   // AndroidX JUnit for UI tests
    androidTestImplementation(libs.androidx.espresso.core)           // Espresso for UI testing
    androidTestImplementation(platform(libs.androidx.compose.bom))   // Compose testing with BOM
    androidTestImplementation(libs.androidx.ui.test.junit4)          // Compose UI testing
    debugImplementation(libs.androidx.ui.tooling)                    // Tooling for Compose debug
    debugImplementation(libs.androidx.ui.test.manifest)              // Compose test manifest for debugging

    // Optional: Coil (Image Loading Library) if you're dealing with images
    implementation("io.coil-kt:coil-compose:2.1.0")                  // Coil for image loading in Jetpack Compose
    implementation(libs.androidx.ui.android)
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation(libs.androidx.runtime.livedata)
    implementation("androidx.viewpager2:viewpager2:1.1.0")

}

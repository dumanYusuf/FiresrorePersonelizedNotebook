plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.personelnotebookcleanarchitecture"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.personelnotebookcleanarchitecture"
        minSdk = 24
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // firebase boom
    androidTestImplementation(platform(libs.androidx.compose.bom))

    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // cloud firestore
    implementation("com.google.firebase:firebase-firestore")
    // firebase auth
// Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:33.3.0"))

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth")    // nav controller
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // hilt
    implementation ("com.google.dagger:hilt-android:2.52")
    kapt ("com.google.dagger:hilt-compiler:2.52")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")


    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
    // viewModel
    implementation("androidx.navigation:navigation-compose:2.7.7")

//
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")


    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")

    // coil
    implementation("io.coil-kt:coil-compose:2.0.0")

}
plugins {
     alias(libs.plugins.android.application)
     alias(libs.plugins.jetbrains.kotlin.android)
     id("kotlin-kapt")
     id("com.google.dagger.hilt.android")
     id("kotlin-parcelize")
     kotlin("plugin.serialization") version "1.8.0"
}

android {
     namespace = "dev.com.mritservices.vendingmachineproject"
     compileSdk = 34
     
     defaultConfig {
          applicationId = "dev.com.mritservices.vendingmachineproject"
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
     testImplementation(libs.junit)
     androidTestImplementation(libs.androidx.junit)
     androidTestImplementation(libs.androidx.espresso.core)
     androidTestImplementation(platform(libs.androidx.compose.bom))
     androidTestImplementation(libs.androidx.ui.test.junit4)
     debugImplementation(libs.androidx.ui.tooling)
     debugImplementation(libs.androidx.ui.test.manifest)
     
     // For date and time across all android os versions
     implementation ("com.jakewharton.threetenabp:threetenabp:1.3.0")
     implementation ("com.airbnb.android:lottie:6.0.0")
     // For pull to refresh
     implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")
     // For multiple floating options
     //  implementation ("com.nambimobile.widgets:expandable-fab:1.2.1")
     
     implementation("com.github.bumptech.glide:glide:4.12.0")
     kapt("com.github.bumptech.glide:compiler:4.12.0")
     
     // For barcode scanner
     implementation("com.journeyapps:zxing-android-embedded:4.3.0")
     implementation("com.google.zxing:core:3.5.3")
     
     
     implementation("androidx.camera:camera-core:1.3.0")
     implementation("androidx.camera:camera-camera2:1.3.0")
     implementation("androidx.camera:camera-lifecycle:1.3.0")
     implementation("androidx.camera:camera-view:1.3.0")
     implementation("androidx.camera:camera-extensions:1.3.0")
     
     
     //ViewModel
     implementation(libs.lifecycle.viewmodel.ktx)
     //ViewModel utilities for compose
     implementation(libs.lifecycle.viewmodel.compose)
     //Navigation
     implementation(libs.navigation.compose)
     implementation(libs.hilt.navigation.compose)
     
     
     // Dagger - Hilt
     implementation(libs.hilt.android)
     kapt (libs.hilt.android.compiler)
     
     implementation(libs.sdp.android)
     
     
     //retrofit
     implementation(libs.gson)
     implementation(libs.retrofit)
     implementation(libs.converter.gson)
     implementation(libs.logging.interceptor)
     
     // Kotlin Coroutines
     implementation(libs.retrofit2.kotlin.coroutines.adapter)
     implementation(libs.kotlinx.coroutines.core)
     implementation(libs.kotlinx.coroutines.android)
     
     //Kotlin extensions for 'activity' artifact & Kotlin extensions for 'fragment' artifact.
     implementation(libs.androidx.activity.ktx)
     implementation(libs.androidx.fragment.ktx)
     
     //Room Database
     implementation(libs.androidx.room.ktx)
     kapt(libs.androidx.room.compiler)
     
     // Navigation Component
     val navVersion = "2.3.5"
     implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
     implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
}
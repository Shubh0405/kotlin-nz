plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Kotlin serialization plugin for type safe routes and navigation arguments
    kotlin("plugin.serialization") version "2.0.21"
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.kotlin_nz"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.kotlin_nz"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // https://mvnrepository.com/artifact/androidx.core/core-splashscreen
    implementation("androidx.core:core-splashscreen:1.0.1")

    val nav_version = "2.9.5"

    // Jetpack Compose integration
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // JSON serialization library, works with the Kotlin serialization plugin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")


    // Hilt for dependency injection
    // https://mvnrepository.com/artifact/com.google.dagger/hilt-android
    implementation("com.google.dagger:hilt-android:2.57.2")
    kapt("com.google.dagger:hilt-compiler:2.57.2")


    // Retrofit for network calls
    // https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    // https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")

    // Coil for image loading
    // https://mvnrepository.com/artifact/io.coil-kt.coil3/coil
    implementation("io.coil-kt.coil3:coil:3.0.4")

    // DataStore (Preferences)
    // https://mvnrepository.com/artifact/androidx.datastore/datastore-preferences
    implementation("androidx.datastore:datastore-preferences:1.1.7")

    // Compose UI
    // https://mvnrepository.com/artifact/androidx.compose.foundation/foundation
    implementation("androidx.compose.foundation:foundation:1.9.1")

    // Accompanist for permissions
    // https://mvnrepository.com/artifact/com.google.accompanist/accompanist-permissions
    implementation("com.google.accompanist:accompanist-permissions:0.37.3")

    // Paging
    // https://mvnrepository.com/artifact/androidx.paging/paging-runtime
    implementation("androidx.paging:paging-runtime:3.3.6")
    // https://mvnrepository.com/artifact/androidx.paging/paging-compose
    implementation("androidx.paging:paging-compose:3.3.6")

    // ROOM DB
    // https://mvnrepository.com/artifact/androidx.room/room-runtime
    implementation("androidx.room:room-runtime:2.7.2")
    // https://mvnrepository.com/artifact/androidx.room/room-ktx
    implementation("androidx.room:room-ktx:2.7.2")
    // https://mvnrepository.com/artifact/androidx.room/room-compiler
    ksp("androidx.room:room-compiler:2.7.2")
}
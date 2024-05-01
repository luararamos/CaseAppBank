plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kapt)

}

android {
    namespace = "com.example.itautransferapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.itautransferapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        signingConfig = signingConfigs.getByName("debug")
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("androidx.activity:activity-compose:1.8.2")
    implementation (platform("androidx.compose:compose-bom:2022.10.00"))
    implementation ("androidx.compose.ui:ui")
    implementation ("androidx.compose.ui:ui-graphics")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.compose.material3:material3-android:1.2.0-rc01")

    implementation ("androidx.compose.ui:ui:1.3.1")
    implementation ("androidx.compose.material:material:1.3.1")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.3.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.3.1")
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.8.0-alpha01")
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation ("com.google.accompanist:accompanist-pager:0.12.0")
    implementation("io.coil-kt:coil-compose:2.4.0")

    // hilt
    // Navigation compose
    implementation ("androidx.navigation:navigation-compose:2.5.3")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Hilt
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt ("com.google.dagger:hilt-compiler:2.44")

    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.work:work-runtime-ktx:2.8.1")

    implementation ("androidx.compose.runtime:runtime-livedata:1.0.0-beta01")
    // Koin
    implementation("io.insert-koin:koin-android:3.4.0")
    implementation("io.insert-koin:koin-core:3.4.0")
    implementation("io.insert-koin:koin-android-compat:3.4.0")
    implementation ("io.insert-koin:koin-androidx-compose:3.4.0")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.google.code.gson:gson:2.8.6")

    // Ok Http
    implementation ("com.squareup.okhttp3:okhttp:4.2.1")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.2.1")
    implementation ("com.squareup.okhttp3:okhttp-urlconnection:4.2.1")

    // coil
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    kapt ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("com.google.accompanist:accompanist-glide:0.13.0")

    implementation ("androidx.compose.runtime:runtime-livedata:1.3.1")
    implementation ("androidx.compose.runtime:runtime-rxjava2:1.3.1")


}
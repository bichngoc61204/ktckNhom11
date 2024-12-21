plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.ktck124.lop124LTDD04.nhom11"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ktck124.lop124LTDD04.nhom11"
        minSdk = 24
        targetSdk = 34
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Các thư viện bổ sung
    implementation("com.google.code.gson:gson:2.8.8") // Gson
    implementation("androidx.cardview:cardview:1.0.0") // CardView
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Retrofit Gson Converter
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0") // OkHttp logging interceptor
    implementation ("com.google.android.material:material:1.6.0")
    // Glide cho image loading
    implementation("com.github.bumptech.glide:glide:4.15.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.0") // Glide compiler
    implementation ("com.squareup.picasso:picasso:2.71828")
}
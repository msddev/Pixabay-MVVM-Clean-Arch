plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.data"
    compileSdk = BuildVersion.CompileSdkVersion

    defaultConfig {
        minSdk = BuildVersion.MinSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "API_KEY", "\"42224337-919279ace90a5f968391bf714\"")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain"))

    // Networking
    implementation(Dependencies.Retrofit2)
    implementation(Dependencies.Retrofit2ConverterGson)
    implementation(Dependencies.Okhttp3)
    implementation(Dependencies.Okhttp3LoggingInterceptor)
    implementation(Dependencies.KotlinxSerializationJson)

    // Room
    implementation(Dependencies.RoomRuntime)
    ksp(Dependencies.RoomCompiler)
    implementation(Dependencies.RoomKtx)
    implementation(Dependencies.RoomPaging)

    // Dagger Hilt
    implementation(Dependencies.HiltAndroid)
    ksp(Dependencies.HiltCompiler)

    // Test
    testImplementation(Dependencies.Junit)
}
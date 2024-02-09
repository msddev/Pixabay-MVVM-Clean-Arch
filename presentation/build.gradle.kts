plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.pixabay"
    compileSdk = BuildVersion.CompileSdkVersion

    defaultConfig {
        applicationId = "com.example.pixabay"
        minSdk = BuildVersion.MinSdkVersion
        targetSdk = BuildVersion.TargetSdkVersion
        versionCode = AppVersion.VersionCode
        versionName = AppVersion.VersionName

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = BuildOptions.JvmTargetVersion
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildOptions.KotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))

    // Compose
    implementation(Dependencies.ActivityCompose)
    implementation(Dependencies.ComposeMaterial3)
    implementation(Dependencies.ComposeMaterial)
    implementation(Dependencies.ComposeUi)
    implementation(Dependencies.ComposeUiGraphics)
    implementation(Dependencies.ComposeUiToolingPreview)
    implementation(Dependencies.ComposeUiTooling)
    implementation(platform(Dependencies.ComposeBom))
    androidTestImplementation(platform(Dependencies.ComposeBom))
    implementation(Dependencies.ComposeRuntimeLivedata)
    implementation(Dependencies.LifecycleViewModelCompose)
    implementation(Dependencies.LifecycleRuntimeCompose)

    // Compose Navigation
    implementation(Dependencies.NavigationCompose)
    implementation(Dependencies.HiltNavigationCompose)

    // Dagger Hilt

    implementation(Dependencies.HiltAndroid)
    ksp(Dependencies.HiltCompiler)

    // Coroutine
    implementation(Dependencies.KotlinxCoroutinesAndroid)
    implementation(Dependencies.KotlinxCoroutinesCore)

    // Coil Image Loading
    implementation(Dependencies.CoilCompose)

    //Paging
    implementation(Dependencies.PagingCompose)

    // Test
    androidTestImplementation(Dependencies.EspressoCore)
    testImplementation(Dependencies.Junit)
    androidTestImplementation(Dependencies.ExtJunit)
    testImplementation(Dependencies.CoreTesting)
    testImplementation(Dependencies.Robolectric)
    debugImplementation(Dependencies.TestCore)
    testImplementation(Dependencies.KotlinxCoroutinesTest)
    testImplementation(Dependencies.MockitoKotlin)
    testImplementation(Dependencies.Mockk)
    androidTestImplementation(Dependencies.TestRules)
    androidTestImplementation(Dependencies.UiTestJunit4)
    debugImplementation(Dependencies.UiTestManifest)
    testImplementation(Dependencies.Turbine)
    testImplementation(Dependencies.Truth)
    testImplementation(Dependencies.MockWebserver)
}
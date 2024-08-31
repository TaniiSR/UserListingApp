plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("de.mannodermaus.android-junit5")
}

android {
    namespace = "com.task.userapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.task.userapp"
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
        debug {
            isMinifyEnabled = false
            isDebuggable = true
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

    implementation(libs.androidx.ui)
    implementation(libs.converter.gson)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.coroutines.android)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.lifecycle.runtime.ktx)

    testImplementation(libs.mockk)
    testImplementation(libs.junit)
    testImplementation(libs.junit5.test)
    testImplementation(libs.turbine.test)
    testImplementation(libs.truth.test)
    testImplementation(libs.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    testImplementation(libs.androidx.core.ktx.test)
    androidTestImplementation(libs.junit5.extension)
    androidTestImplementation(libs.androidx.core.test)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testRuntimeOnly(libs.junit5.engine.test)
    testRuntimeOnly(libs.vintage.test)
    testImplementation(libs.arch.testing)
    testImplementation(kotlin("test"))
}
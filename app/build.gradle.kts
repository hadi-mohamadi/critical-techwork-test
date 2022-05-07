plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.critical_techworks.news"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
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
        kotlinCompilerExtensionVersion = AndroidX.composeVersion
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


}

dependencies {
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.composeUi)
    implementation(AndroidX.composeMaterial)
    implementation(AndroidX.composeUiToolingPreview)
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.activityCompose)
    testImplementation(JUnit.junit)
    androidTestImplementation(AndroidX.testJUnit)
    androidTestImplementation(AndroidX.testEspresso)
    androidTestImplementation(AndroidX.composeUiTestJUnit)
    debugImplementation(AndroidX.composeUiTooling)
}

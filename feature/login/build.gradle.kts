plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = DefaultConfig.compileSdk

    defaultConfig {
        minSdk = DefaultConfig.minSdk
        targetSdk = DefaultConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        kotlinCompilerExtensionVersion = AndroidX.composeVersion
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(Modules.coreUi))

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.material)
    testImplementation(JUnit.junit)
    testImplementation(Mockk.mockk)
    androidTestImplementation(Mockk.androidMockk)
    androidTestImplementation(AndroidX.testJUnit)
    androidTestImplementation(AndroidX.composeUiTestJUnit)
    androidTestImplementation(AndroidX.testEspresso)
    implementation(AndroidX.composeUi)
    implementation(AndroidX.composeMaterial)
    implementation(AndroidX.composeUiToolingPreview)
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.activityCompose)
    implementation(AndroidX.composeUiTooling)

    //hilt
    implementation(Hilt.android)
    kapt(Hilt.daggerCompiler)
    kapt(Hilt.compiler)
    implementation(Hilt.lifecycleViewModel)
    implementation(Hilt.navigationCompose)

    //androidx lifecycle
    implementation(Lifecycle.viewModel)
    implementation(Lifecycle.commonJava8)
    implementation(Lifecycle.runtimeKtx)
    implementation(Lifecycle.reactiveStreamsKtx)
    androidTestImplementation(Arc.coreTesting)

    //Accompanist
    implementation(Accompanist.permissions)
    implementation(Accompanist.systemUiController)
}
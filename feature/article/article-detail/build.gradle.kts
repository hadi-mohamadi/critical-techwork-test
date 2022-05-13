plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
}

dependencies {

    implementation(project(Modules.coreNetwork))
    implementation(project(Modules.coreUi))
    implementation(project(Modules.featureArticleDomain))
    implementation(project(Modules.featureArticleUtil))

    implementation(AndroidX.appcompat)
    implementation(AndroidX.material)
    implementation(AndroidX.composeUi)
    implementation(AndroidX.composeMaterial)
    implementation(AndroidX.composeUiToolingPreview)
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.activityCompose)
    debugImplementation(AndroidX.composeUiTooling)

    //Coil for image loading
    implementation(Coil.coil)

    //Accompanist
    implementation(Accompanist.systemUiController)
}
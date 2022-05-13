plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = DefaultConfig.compileSdk

    defaultConfig {
        applicationId = DefaultConfig.applicationId
        minSdk = DefaultConfig.minSdk
        targetSdk = DefaultConfig.targetSdk
        versionCode = DefaultConfig.versionCode
        versionName = DefaultConfig.versionName

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
        kotlinCompilerExtensionVersion = AndroidX.composeVersion
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    flavorDimensions += "source"
    productFlavors {
        create("everything") {
            buildConfigField("String", "source", "\"everything\"")
            dimension = "source"
        }
        create("topHeadlines") {
            dimension = "source"
            buildConfigField("String", "source", "\"top-headlines\"")
        }
    }

    hilt {
        enableAggregatingTask = true
    }

}

dependencies {
    implementation(project(Modules.coreUi))
    implementation(project(Modules.featureLogin))
    implementation(project(Modules.featureArticleList))

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.composeUi)
    implementation(AndroidX.composeMaterial)
    implementation(AndroidX.composeUiToolingPreview)
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.activityCompose)
    implementation(AndroidX.appcompat)
    testImplementation(JUnit.junit)
    androidTestImplementation(AndroidX.testJUnit)
    androidTestImplementation(AndroidX.testEspresso)
    androidTestImplementation(AndroidX.composeUiTestJUnit)
    debugImplementation(AndroidX.composeUiTooling)

    //Accompanist
    implementation(Accompanist.permissions)
    implementation(Accompanist.systemUiController)

    //navigation component
    implementation(AndroidX.navigationCompose)
    implementation(AndroidX.navigationUiKtx)
    implementation(AndroidX.navigationRuntimeKtx)

    //hilt
    implementation(Hilt.android)
    kapt(Hilt.daggerCompiler)
    kapt(Hilt.compiler)
    implementation(Hilt.lifecycleViewModel)
    implementation(Hilt.navigationCompose)
}

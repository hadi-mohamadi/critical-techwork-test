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

        //The best practice is to put this api key in the local.properties file
        //But as you would like to be able to run the program without any issues I put it here
        buildConfigField("String", "apiKey", "\"71e4b0373c2a4b21b48365deb7cbd45c\"")
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

    implementation(project(Modules.coreNetwork))
    implementation(project(Modules.featureArticleDomain))

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appcompat)
    testImplementation(JUnit.junit)
    testImplementation(Mockk.mockk)
    androidTestImplementation(Mockk.androidMockk)
    androidTestImplementation(AndroidX.testJUnit)

    //hilt
    implementation(Hilt.android)
    kapt(Hilt.daggerCompiler)
    implementation(Hilt.lifecycleViewModel)
    kapt(Hilt.compiler)

    //retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.gson)
    implementation(Retrofit.loggingInterceptor)

    //Paging
    implementation(AndroidX.paging)

    //kotlin coroutines
    implementation(Coroutines.core)
}
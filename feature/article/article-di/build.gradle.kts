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

    implementation(project(Modules.featureArticleDomain))
    implementation(project(Modules.featureArticleRepository))

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appcompat)
    testImplementation(JUnit.junit)
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
}
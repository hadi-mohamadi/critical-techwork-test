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

    implementation(project(Modules.coreNetwork))
    implementation(project(Modules.coreUi))
    implementation(project(Modules.featureArticleDomain))
    implementation(project(Modules.featureArticleInteractors))

    testImplementation(JUnit.junit)
    testImplementation(Mockk.mockk)
    androidTestImplementation(Mockk.androidMockk)
    androidTestImplementation(AndroidX.testJUnit)
    androidTestImplementation(AndroidX.composeUiTestJUnit)
    androidTestImplementation(AndroidX.testEspresso)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.material)
    implementation(AndroidX.composeUi)
    implementation(AndroidX.composeMaterial)
    implementation(AndroidX.composeUiToolingPreview)
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.activityCompose)
    debugImplementation(AndroidX.composeUiTooling)

    //kotlin coroutines
    implementation(Coroutines.core)
    implementation(Coroutines.android)

    //Paging
    implementation(AndroidX.paging)

    //hilt
    implementation(Hilt.android)
    kapt(Hilt.daggerCompiler)
    implementation(Hilt.lifecycleViewModel)
    kapt(Hilt.compiler)
    implementation(Hilt.navigationCompose)

    //retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.gson)
    implementation(Retrofit.loggingInterceptor)

    //Coil for image loading
    implementation(Coil.coil)

    //androidx lifecycle
    implementation(Lifecycle.viewModel)
    implementation(Lifecycle.commonJava8)
    implementation(Lifecycle.runtimeKtx)
    implementation(Lifecycle.reactiveStreamsKtx)
    androidTestImplementation(Arc.coreTesting)

    //Accompanist
    implementation(Accompanist.systemUiController)
}
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("io.gitlab.arturbosch.detekt")
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization") version "1.5.0"
    id("jacoco")
}

jacoco {
    version = "0.8.8"
}

android {
    namespace = "fr.medicapp.medicapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "fr.medicapp.medicapp"
        minSdk = 24
        targetSdk = 33
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
            enableUnitTestCoverage = true
        }
        debug {
            enableUnitTestCoverage = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,LICENSE.md,LICENSE-notice.md}"
        }
    }
}

dependencies {
    implementation("androidx.room:room-common:2.6.1")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.01.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.2")

    // Material Design icons
    implementation("androidx.compose.material:material-icons-extended")

    // ML Kit
    implementation("com.google.mlkit:text-recognition:16.0.0")

    // Accompanist
    implementation("com.google.accompanist:accompanist-permissions:0.34.0")

    // Coil
    val coilVersion = "2.4.0"
    implementation("io.coil-kt:coil:$coilVersion")
    implementation("io.coil-kt:coil-compose:$coilVersion")

    // PyTorch
    implementation("org.pytorch:pytorch_android:2.1.0")

    // Mozilla
    val mozillaVersion = "123.0"
    implementation("org.mozilla.components:concept-engine:$mozillaVersion")
    implementation("org.mozilla.components:browser-engine-gecko:$mozillaVersion")

    // Maps
    implementation("org.maplibre.gl:android-sdk:10.0.2")

    // Calendar
    implementation("com.kizitonwose.calendar:compose:2.5.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

    // Room
    implementation("androidx.room:room-runtime:2.5.2")
    ksp("androidx.room:room-compiler:2.5.2")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // QRcode
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")

    //Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    androidTestImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

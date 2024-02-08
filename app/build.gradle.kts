import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("io.gitlab.arturbosch.detekt")
    id("io.objectbox")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.01.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    // Material Design icons
    implementation("androidx.compose.material:material-icons-extended")

    // ML Kit
    implementation("com.google.mlkit:text-recognition:16.0.0")

    // Accompanist
    implementation("com.google.accompanist:accompanist-permissions:0.32.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")

    // Coil
    val coilVersion = "2.4.0"
    implementation("io.coil-kt:coil:$coilVersion")
    implementation("io.coil-kt:coil-compose:$coilVersion")

    // PyTorch
    implementation("org.pytorch:pytorch_android:2.1.0")

    // Sheets Compose Dialogs
    val sheetsComposeDialogsVersion = "1.2.1"
    implementation("com.maxkeppeler.sheets-compose-dialogs:core:$sheetsComposeDialogsVersion")
    implementation("com.maxkeppeler.sheets-compose-dialogs:info:$sheetsComposeDialogsVersion")
    implementation("com.maxkeppeler.sheets-compose-dialogs:calendar:$sheetsComposeDialogsVersion")
    implementation("com.maxkeppeler.sheets-compose-dialogs:clock:$sheetsComposeDialogsVersion")
    implementation("com.maxkeppeler.sheets-compose-dialogs:duration:$sheetsComposeDialogsVersion")
    implementation("com.maxkeppeler.sheets-compose-dialogs:date-time:$sheetsComposeDialogsVersion")
    implementation("com.maxkeppeler.sheets-compose-dialogs:option:$sheetsComposeDialogsVersion")
    implementation("com.maxkeppeler.sheets-compose-dialogs:list:$sheetsComposeDialogsVersion")
    implementation("com.maxkeppeler.sheets-compose-dialogs:input:$sheetsComposeDialogsVersion")
    implementation("com.maxkeppeler.sheets-compose-dialogs:emoji:$sheetsComposeDialogsVersion")
    implementation("com.maxkeppeler.sheets-compose-dialogs:state:$sheetsComposeDialogsVersion")
    implementation("com.maxkeppeler.sheets-compose-dialogs:color:$sheetsComposeDialogsVersion")

    // PyTorch
    implementation("org.pytorch:pytorch_android:2.1.0")

    // Alarm
    implementation("com.github.ColdTea-Projects:SmplrAlarm:2.1.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:dagger-compiler:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Volley
    implementation("com.android.volley:volley:1.2.1")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
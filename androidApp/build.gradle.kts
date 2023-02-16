import Version.Android.COIL_VERSION
import Version.Android.COMPOSE_UI_VERSION
import Version.Android.LIFECYCLE_VERSION
import Version.Android.NAVIGATION_VERSION
import Version.COROUTINES_VERSION
import Version.KOIN_VERSION

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.mdgroup.nasawallpapers"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.mdgroup.nasawallpapers"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$LIFECYCLE_VERSION")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VERSION")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE_VERSION")

    // Compose
    implementation("androidx.compose.ui:ui:$COMPOSE_UI_VERSION")
    implementation("androidx.compose.ui:ui-tooling:$COMPOSE_UI_VERSION")
    implementation("androidx.compose.ui:ui-tooling-preview:$COMPOSE_UI_VERSION")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.paging:paging-compose:1.0.0-alpha18")
    implementation("com.google.accompanist:accompanist-permissions:0.29.1-alpha")

    // Calendar
    // https://github.com/mobile-development-group/ComposeCalendar
    implementation("io.github.mobile-development-group:composecalendar:2.1.2")

    // Navigation
    implementation("androidx.navigation:navigation-compose:$NAVIGATION_VERSION")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$COROUTINES_VERSION")

    // DI
    implementation("io.insert-koin:koin-android:$KOIN_VERSION")
    implementation("io.insert-koin:koin-androidx-navigation:$KOIN_VERSION")
    implementation("io.insert-koin:koin-androidx-compose:$KOIN_VERSION")

    // Coil
    // https://coil-kt.github.io/coil
    implementation("io.coil-kt:coil:$COIL_VERSION")
    implementation("io.coil-kt:coil-compose:$COIL_VERSION")
}
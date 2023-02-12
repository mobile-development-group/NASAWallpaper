import Version.COROUTINES_VERSION
import Version.KOIN_VERSION
import Version.KTOR_VERSION

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Network
                implementation("io.ktor:ktor-client-core:$KTOR_VERSION")
                implementation("io.ktor:ktor-client-content-negotiation:$KTOR_VERSION")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$KTOR_VERSION")
                implementation("io.ktor:ktor-client-logging:$KTOR_VERSION")
                implementation("io.ktor:ktor-client-auth:$KTOR_VERSION")

                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION")

                // DI
                implementation("io.insert-koin:koin-core:$KOIN_VERSION")

                // DateTime
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                // Network
                implementation("io.ktor:ktor-client-okhttp:$KTOR_VERSION")
            }
        }
        val androidUnitTest by getting

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                //Network
                implementation("io.ktor:ktor-client-ios:$KTOR_VERSION")
            }
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.mdgroup.nasawallpapers"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}
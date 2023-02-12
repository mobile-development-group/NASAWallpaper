pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://kotlin.bintray.com/kotlinx")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "NASAWallpapers"
include(":androidApp")
include(":shared")
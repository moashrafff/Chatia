rootProject.name = "Cahatia"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        maven {
            url = uri("https://androidx.dev/snapshots/builds/13508953/artifacts/repository")
        }
        mavenCentral()
        mavenLocal()
    }
}
include(":auth")

include(":core:presentation")
include(":core:domain")
include(":core:data")
include(":core:navigator")
include(":data")
include(":core:utils")
include(":feature:onBoarding")
include(":feature:login")
include(":feature:register")
include(":register")
include(":composeApp")
include(":server")
include(":shared")
include(":core:data")
include(":utils")

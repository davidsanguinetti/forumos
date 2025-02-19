pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }

}
//
//buildscript {
//    repositories {
//        google()
//    }
//    dependencies {
//        val nav_version = "2.8.6"
//        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
//    }
//}

rootProject.name = "forum os"
include(":app")

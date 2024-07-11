pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        // Các kho repository Maven khác nếu cần
    }
}



rootProject.name = "demo_app"
include(":app")
 
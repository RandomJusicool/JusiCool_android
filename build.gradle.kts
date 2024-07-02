plugins {
    id(Dependency.Gradle.APPLICATION) version Versions.GRADLE apply false
    id(Dependency.Gradle.LIBRARY) version Versions.GRADLE apply false
    id(Dependency.Gradle.KOTLIN) version Versions.KOTLIN apply false
    id(Dependency.Hilt.HILT_PLUGIN) version Versions.HILT apply false
    id(Dependency.Google.GOOGLE_SERVICES_PLUGIN) version Versions.GOOGLE_SERVICE_PLUGIN apply false
    id(Dependency.Gradle.KSP) version Versions.KSP apply false
    id("com.google.protobuf") version "0.9.4" apply false
}

buildscript {
    dependencies {
        classpath ("com.google.protobuf:protobuf-gradle-plugin:0.9.4")
    }
}

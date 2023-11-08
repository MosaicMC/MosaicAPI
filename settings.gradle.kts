pluginManagement {
    repositories {
        maven(url = "https://maven.fabricmc.net/")
        mavenCentral()
        gradlePluginPortal()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
    plugins {
        kotlin("jvm") version "1.9.0"
    }

}
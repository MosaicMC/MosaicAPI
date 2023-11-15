import java.text.SimpleDateFormat
import java.util.*

plugins {
    alias(libs.plugins.versions)
}

repositories {
    maven("https://jitpack.io")
}

dependencies {
    minecraft(
            group   = "com.mojang",
            name    = "minecraft",
            version = "minecraft_version".configKey
    )
    mappings(
            group   = "net.fabricmc",
            name    = "yarn",
            version = "${"minecraft_version".configKey}+build.${"mappings_version".configKey}"
    )
    modImplementation(
            group   = "net.fabricmc",
            name    = "fabric-loader",
            version = "loader_version".configKey
    )
    implementation(
            group   = "org.vineflower",
            name    = "vineflower",
            version = "vineflower_version".configKey
    )
    implementation(include(annotationProcessor(
            group   = "com.github.llamalad7.mixinextras",
            name    = "mixinextras-fabric",
            version = "mixin_extras_version".configKey,
    ))!!)
}

loom {
    serverOnlyMinecraftJar()
}

java {
    withSourcesJar()
    withJavadocJar()
}

val sourceCompatibility = JavaVersion.VERSION_21
val targetCompatibility = JavaVersion.VERSION_21
val archivesBaseName = "archivesBaseName".configKey
val dataFormat = SimpleDateFormat("yyyy.MM.dd.HH").format(Date())!!

version = "$dataFormat+${"mod_version".configKey}+${"minecraft_version".configKey}"

tasks.processResources {
    expand(mapOf(
        "version" to project.version,
        "mod_id" to "mod_id".configKey,
        "loader_version" to "loader_version".configKey,
        "minecraft_version" to "minecraft_version".configKey,
        "java_version" to "$sourceCompatibility",
    ))
}


tasks.jar {
    from("LICENSE") {
        rename { "${it}_$archivesBaseName"}
    }
}

tasks.compileJava {
    options.release = 21
}

intellij {
    version = "2023.2.5"
    type = "IU"
}

val String.configKey: String
    get() = project.properties[this].toString()


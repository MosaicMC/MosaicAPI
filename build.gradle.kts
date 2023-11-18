import java.text.SimpleDateFormat
import java.util.*

plugins {
    alias(libs.plugins.intellij)
    alias(libs.plugins.lombok)
    alias(libs.plugins.spotless)
    alias(libs.plugins.fabricloom)
}

repositories {
    maven("https://jitpack.io")
}

dependencies {
    minecraft(libs.bundles.minecraft)
    mappings(libs.bundles.yarn)
    modImplementation(libs.bundles.fabric)
    implementation(libs.bundles.vineflower)
    implementation(libs.bundles.mixinsextras)
    annotationProcessor(libs.bundles.mixinsextras)
    include(libs.bundles.mixinsextras)
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

version = "$dataFormat+${libs.versions.minecraft.get()}+${"mod_version".configKey}"

tasks.processResources {
    expand(mapOf(
        "version" to version,
        "mod_id" to "mod_id".configKey,
        "loader_version" to libs.versions.fabricloader.get(),
        "minecraft_version" to libs.versions.minecraft.get(),
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


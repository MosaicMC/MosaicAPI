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

version = "$dataFormat+${"mod_version".configKey}+${libs.versions.minecraft}"

tasks.processResources {
    expand(mapOf(
        "version" to project.version,
        "mod_id" to "mod_id".configKey,
        "loader_version" to libs.versions.fabricloader,
        "minecraft_version" to libs.versions.minecraft,
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


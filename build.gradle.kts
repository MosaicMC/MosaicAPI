import juuxel.vineflowerforloom.api.DecompilerBrand
import java.text.SimpleDateFormat
import java.util.*

plugins {
    id("fabric-loom") version "1.3-SNAPSHOT"
    id("maven-publish")
    id("io.github.juuxel.loom-vineflower") version "1.11.0"
    id("org.jetbrains.dokka") version "1.9.0"
    id("com.diffplug.spotless") version "6.21.0"
}

repositories {
    maven("https://jitpack.io")
    maven("https://maven.parchmentmc.org")
}

dependencies {
    minecraft(group = "com.mojang", name = "minecraft", version = "${project.properties["minecraft_version"]}")
    mappings(group = "net.fabricmc" , name = "yarn", version = "${project.properties["minecraft_version"]}+build.${project.properties["mappings_version"]}")

    modImplementation(group = "net.fabricmc", name = "fabric-loader", version = "${project.properties["loader_version"]}")
    implementation(group = "jakarta.annotation", name = "jakarta.annotation-api", version = "2.1.1")

    val mixinExtras = "com.github.llamalad7.mixinextras:mixinextras-fabric:${project.properties["mixin_extras"]}"

    include(implementation(annotationProcessor(mixinExtras)!!)!!)
}

loom {
    serverOnlyMinecraftJar()
}

vineflower {
    brand = DecompilerBrand.VINEFLOWER
}

val sourceCompatibility = JavaVersion.VERSION_21
val targetCompatibility = JavaVersion.VERSION_21
val archivesBaseName = project.properties["archivesBaseName"]
val dokkaHtmlJar = "dokkaHtmlJar"
val dokkaJavadocJar = "dokkaJavadocJar"
val dataFormat = SimpleDateFormat("yyyy.MM.dd.HH").format(Date())!!

version = "${dataFormat}+${project.properties["mod_version"]}"
group = project.properties["maven_group"].toString()


tasks.processResources {
    expand(mapOf(
        "version" to project.version,
        "mod_id" to project.properties["mod_id"],
        "loader_version" to project.properties["loader_version"],
        "fabric_kotlin_version" to project.properties["fabric_kotlin_version"],
        "kotlin_version" to project.properties["kotlin_version"],
        "minecraft_version" to project.properties["minecraft_version"],
    ))
}

tasks.build {
    dependsOn(dokkaHtmlJar)
    dependsOn(dokkaJavadocJar)
}

tasks.javadoc {
    dependsOn(dokkaJavadocJar)
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${project.properties["archivesBaseName"]}"}
    }
}

tasks.compileJava {
    options.release = 21
}

tasks.register<Jar>("dokkaHtmlJar") {
    dependsOn(tasks.dokkaHtml)
    from(tasks.dokkaHtml.flatMap { it.outputDirectory })
    archiveClassifier = "html-docs"
}

tasks.register<Jar>("dokkaJavadocJar") {
    dependsOn(tasks.dokkaJavadoc)
    from(tasks.dokkaHtml.flatMap { it.outputDirectory })
    archiveClassifier = "javadoc"
}

java {
    withSourcesJar()
    withJavadocJar()
}

spotless {
//    java {
//        palantirJavaFormat("2.38.0")
//        formatAnnotations()
//    }
}
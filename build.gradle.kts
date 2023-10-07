import java.text.SimpleDateFormat
import java.util.*

plugins {
    id("fabric-loom") version "1.4-SNAPSHOT"
    id("maven-publish")
    id("org.jetbrains.dokka") version "1.9.0"
    id("com.diffplug.spotless") version "6.21.0"
}

repositories {
    maven("https://jitpack.io")
    maven("https://maven.parchmentmc.org")
}

dependencies {
    minecraft(
            group = "com.mojang",
            name = "minecraft",
            version = fromConfig("minecraft_version")
    )
    mappings(
            group = "net.fabricmc",
            name = "yarn",
            version = "${fromConfig("minecraft_version")}+build.${fromConfig("mappings_version")}"
    )

    modImplementation(
            group = "net.fabricmc",
            name = "fabric-loader",
            version = fromConfig("loader_version")
    )
    implementation(
            group = "jakarta.annotation",
            name = "jakarta.annotation-api",
            version = fromConfig("jakarta_version")
    )
    implementation(
            group = "org.vineflower",
            name = "vineflower",
            version = fromConfig("vineflower_version")
    )
    include(implementation(annotationProcessor(
        group = "com.github.llamalad7.mixinextras",
        name = "mixinextras-fabric",
        version = fromConfig("mixin_extras_version"),
    ))!!)
}

loom {
    serverOnlyMinecraftJar()
}

val sourceCompatibility = JavaVersion.VERSION_21
val targetCompatibility = JavaVersion.VERSION_21
val archivesBaseName = fromConfig("archivesBaseName")
val dokkaHtmlJar = "dokkaHtmlJar"
val dokkaJavadocJar = "dokkaJavadocJar"
val dataFormat = SimpleDateFormat("yyyy.MM.dd.HH").format(Date())!!

version = "${dataFormat}+${fromConfig("mod_version")}"


tasks.processResources {
    expand(mapOf(
        "version" to project.version,
        "mod_id" to fromConfig("mod_id"),
        "loader_version" to fromConfig("loader_version"),
        "minecraft_version" to fromConfig("minecraft_version"),
        "java_version" to sourceCompatibility.toString(),
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

fun fromConfig(key: String): String {
    return project.properties[key] as String
}


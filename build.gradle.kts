import juuxel.vineflowerforloom.api.DecompilerBrand

plugins {
    id("fabric-loom") version "1.3-SNAPSHOT"
    id("maven-publish")
    id("org.jetbrains.kotlin.jvm")
    id("com.ncorti.ktfmt.gradle") version "0.13.0"
    id("io.github.juuxel.loom-vineflower") version "1.11.0"
    id("org.jetbrains.dokka") version "1.9.0"
}

loom {
    serverOnlyMinecraftJar()
}
vineflower {
    brand = DecompilerBrand.VINEFLOWER
}

val sourceCompatibility = JavaVersion.VERSION_17
val targetCompatibility = JavaVersion.VERSION_17
val archivesBaseName = project.properties["archivesBaseName"].toString()
val dokkaHtmlJar: String = "dokkaHtmlJar"
val dokkaJavadocJar: String = "dokkaJavadocJar"

version = project.properties["mod_version"].toString()
group = project.properties["maven_group"].toString()

repositories {
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://maven.parchmentmc.org")}
}

dependencies {
    minecraft("com.mojang:minecraft:${project.properties["minecraft_version"]}")
    @Suppress("UnstableApiUsage")
    mappings(
        loom.layered {
            officialMojangMappings()
            parchment(
                "org.parchmentmc.data:parchment-${project.properties["minecraft_version"]}:${project.properties["parchment_mappings"]}@zip"
            )
        }
    )

    modImplementation("net.fabricmc:fabric-loader:${project.properties["loader_version"]}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${project.properties["fabric_kotlin_version"]}+kotlin.${project.properties["kotlin_version"]}")
    testImplementation("net.fabricmc:fabric-loader-junit:${project.properties["loader_version"]}")

    val mixinExtras = "com.github.llamalad7.mixinextras:mixinextras-fabric:${project.properties["mixin_extras"]}"

    include(implementation(annotationProcessor(mixinExtras)!!)!!)
}

tasks.processResources {
    expand(project.properties)
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

tasks.test {
    useJUnitPlatform()
    systemProperty("fabric.side", "server")
}

tasks.compileJava {
    options.release = 17
}

tasks.compileKotlin {
    kotlinOptions.jvmTarget = "17"
    kotlinOptions.freeCompilerArgs += listOf(
        "-Xlambdas=indy",
        "-Xno-param-assertions",
        "-Xno-call-assertions",
        "-Xno-receiver-assertions",
    )
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

ktfmt {
    kotlinLangStyle()
}
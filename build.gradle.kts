import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import com.bmuschko.gradle.docker.tasks.image.DockerPushImage
import com.bmuschko.gradle.docker.tasks.image.DockerTagImage
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version = "1.3.0"
val junit_version = "5.6.0"
val hamcrest_version = "2.2"
val kaml_verison = "0.15.0"
val kxs_version = "0.14.0"
val fuel_version = "2.2.1"
val slf4j_version = "1.7.25"
val logback_version = "1.2.3"
val kotlin_logging_version = "1.7.9"
val hoplite_version = "1.2.0"

plugins {
    kotlin("jvm") version "1.3.41"
    kotlin("plugin.serialization") version "1.3.70"
    application
    id("com.github.johnrengelman.shadow") version "5.0.0"
    id("com.bmuschko.docker-remote-api") version "6.4.0"
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {

    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$kxs_version")

    fun ktor(s: String = "", v: String = ktor_version) = "io.ktor:ktor$s:$v"
    implementation(ktor())
    implementation(ktor("-serialization"))
    implementation(ktor("-server-netty"))

    fun fuel(s: String = "", v: String = fuel_version) = "com.github.kittinunf.fuel:fuel$s:$v"
    implementation(fuel())
    implementation(fuel("-rxjava"))

    fun hoplite(s: String = "", v: String = hoplite_version) = "com.sksamuel.hoplite:hoplite$s:$v"
    implementation(hoplite("-core"))
    implementation(hoplite("-yaml"))

    fun logback(s: String = "", v: String = logback_version) = "ch.qos.logback:logback$s:$v"
    implementation(logback("-classic"))
    implementation(logback("-core"))

    implementation("org.slf4j:slf4j-api:$slf4j_version")
    implementation("io.github.microutils:kotlin-logging:$kotlin_logging_version")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "org.dataart.pmsintegration.AppKt"
}

tasks {
    withType<Jar> {
        manifest {
            attributes(mapOf("Main-Class" to application.mainClassName))
        }
    }
}

fun pkey(k:String) = project.properties[k]

val dockerimagebuild by tasks.creating(DockerBuildImage::class) {
    inputDir.set(file("."))
    dockerFile.set(file("Dockerfile"))
    val pmsIntEnv:String = (project.properties["pmsIntEnv"]?:"dev") as String
    buildArgs.put("PMSINT_ENV", pmsIntEnv)
    val serverPort:String = (project.properties["serverPort"]?:"10000") as String
    buildArgs.put("SERVER__PORT", serverPort)
    images.add("${pkey("dcrname")}:${pkey("dcrtag")}")
}

val tagdockerimage by tasks.creating(DockerTagImage::class) {
    dependsOn(dockerimagebuild)
    imageId.set(dockerimagebuild.imageId)
    repository.set("${pkey("dcrregistry")}/${pkey("dcrrepo")}/${pkey("dcrname")}")
    tag.set("${pkey("dcrtag")}")
}

val pushdockerimagetogcr by tasks.creating(DockerPushImage::class) {
    dependsOn(tagdockerimage)
    val imageName = "${pkey("dcrregistry")}/${pkey("dcrrepo")}/${pkey("dcrname")}:${pkey("dcrtag")}"
    images.add(imageName)
}




   
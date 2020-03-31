import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage

val ktor_version = "1.3.0"
val junit_version = "5.6.0"
val hamcrest_version = "2.2"
val kaml_verison = "0.15.0"
val kotlinx_serialization_version = "0.14.0"
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
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$kotlinx_serialization_version")

    fun ktor(s: String = "", v: String = ktor_version) = "io.ktor:ktor$s:$v"

    compile(ktor())
    compile(ktor("-serialization"))
    compile(ktor("-server-netty"))

    implementation("com.github.kittinunf.fuel:fuel:$fuel_version")
    implementation("com.github.kittinunf.fuel:fuel-rxjava:$fuel_version")

    implementation("com.sksamuel.hoplite:hoplite-core:$hoplite_version")
    implementation("com.sksamuel.hoplite:hoplite-yaml:$hoplite_version")

    implementation("org.slf4j:slf4j-api:$slf4j_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("ch.qos.logback:logback-core:$logback_version")
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

val buildDockerImage by tasks.creating(DockerBuildImage::class) {
    inputDir.set(file("."))
    dockerFile.set(file("Dockerfile"))
    val pmsIntEnv:String = (project.properties["pmsIntEnv"]?:"dev") as String
    buildArgs.put("PMSINT_ENV", pmsIntEnv)
    val serverPort:String = (project.properties["serverPort"]?:"10000") as String
    buildArgs.put("SERVER__PORT", serverPort)
    images.add("plaguedoctor/pms-integration-backend:latest")
}




   
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version = "1.3.0"
val junit_version = "5.6.0"
val hamcrest_version = "2.2"
val kaml_verison = "0.15.0"
val kotlinx_serialization_version = "0.14.0"
val fuel_version = "2.2.1"
val slf4j_version = "1.7.25"
val logback_version = "1.2.3"
val kotlin_logging_version = "1.7.9"

plugins {
    kotlin("jvm") version "1.3.31"
    kotlin("plugin.serialization") version "1.3.70"
    application
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("com.github.kittinunf.fuel:fuel:$fuel_version")
    implementation("com.github.kittinunf.fuel:fuel-rxjava:$fuel_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$kotlinx_serialization_version")
    implementation("org.slf4j:slf4j-api:$slf4j_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("ch.qos.logback:logback-core:$logback_version")
    implementation("io.github.microutils:kotlin-logging:$kotlin_logging_version")

    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "org.dataart.pmsintegration.AppKt"
}


   
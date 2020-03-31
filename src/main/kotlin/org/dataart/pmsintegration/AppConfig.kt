package org.dataart.pmsintegration

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.SystemPropertiesPropertySource


fun loadConfig(): AppConfig {
    val env: String = System.getProperty("env", "dev")
    println("Active environment = $env")
    return ConfigLoader()
        .withPropertySource(SystemPropertiesPropertySource)
        .loadConfigOrThrow("/application-$env.yaml")
}

data class AppConfig(val server: Server, val pms: Pms)

data class Server(val port: Int)

data class AthenaHealth(val url: String)

data class Pms(val athenahealth: AthenaHealth)

package org.dataart.pmsintegration

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.EnvironmentVariablesPropertySource


fun loadConfig(args: Array<String>): AppConfig {
    val env: String = if (args.isNotEmpty()) args[0] else "dev"
    println("Active environment = $env")
    return ConfigLoader()
        .withPropertySource(EnvironmentVariablesPropertySource(useUnderscoresAsSeparator = false))
        .loadConfigOrThrow("/application-$env.yaml")
}

data class AppConfig(val server: Server, val pms: Pms)

data class Server(val port: Int)

data class AthenaHealth(val url: String)

data class Pms(val athenahealth: AthenaHealth)

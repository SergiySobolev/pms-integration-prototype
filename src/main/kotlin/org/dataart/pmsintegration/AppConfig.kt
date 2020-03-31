package org.dataart.pmsintegration

import com.sksamuel.hoplite.ConfigLoader


fun loadConfig(args: Array<String>): AppConfig {
    val env: String = if (args.isNotEmpty()) args[0] else "dev"
    println("Active environment = $env")
    val propertyFileName = "/application-$env.yaml"
    return ConfigLoader().loadConfigOrThrow(propertyFileName)
}

data class AppConfig(val server: Server, val pms: Pms)

data class Server(val port: Int)

data class AthenaHealth(val url: String)

data class Pms(val athenahealth: AthenaHealth)

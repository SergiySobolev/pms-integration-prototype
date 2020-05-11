package org.dataart.pmsintegration

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.EnvironmentVariablesPropertySource
import com.sksamuel.hoplite.SystemPropertiesPropertySource



data class AppConfig(val server: Server, val gcp: Gcp, val pms: Pms) {

    companion object {
        private val cfg: AppConfig = loadConfig()
        fun get() : AppConfig {
            return cfg
        }
    }

}

data class Server(val port: Int)

data class Gcp(val practiceinfobucketname: String)

data class AthenaHealth(val url: String)

data class Pms(val athenahealth: AthenaHealth)

private fun loadConfig(): AppConfig {
    val env: String = System.getenv("pmsint_env")?: System.getProperty("pmsint_env", "dev")
    println("Active environment = $env")
    return ConfigLoader()
        .withPropertySource(EnvironmentVariablesPropertySource(true))
        .withPropertySource(SystemPropertiesPropertySource)
        .loadConfigOrThrow("/application-$env.yaml")
}


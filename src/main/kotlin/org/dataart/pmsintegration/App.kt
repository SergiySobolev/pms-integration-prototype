package org.dataart.pmsintegration
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.serialization.DefaultJsonConfiguration
import io.ktor.serialization.serialization
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.dataart.pmsintegration.pmsclients.PmsClient
import org.dataart.pmsintegration.pmsclients.impl.AthenaHealthClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun main() {
    val config = AppConfig(10000)
    embeddedServer(Netty, port = config.port, module = Application::module).start()

}

fun Application.module() {

    val pmsClient: PmsClient = AthenaHealthClient()

    routing {
        route("/practiceinfo") {
            get("/") {
                val practicesInfo = pmsClient.getAvailablePractices()
                call.respond(practicesInfo)
            }
            get("/{practiceId}") {
                val practiceId = call.parameters["practiceId"]!!
                val practiceInfo = pmsClient.getAvailablePractice(practiceId)
                call.respond(practiceInfo)
            }
//            post("/{key}") {
//                val key = call.parameters["key"]!!
//                trie.insert(key)
//                call.respondText(status = HttpStatusCode.Created) {"Key inserted."}
//            }
//            delete("/{key}") {
//                val key = call.parameters["key"]!!
//                trie.delete(key)
//                call.respondText(status = HttpStatusCode.OK) {"Key deleted."}
//            }
        }
//        route("suggest") {
//            get("/{key}") {
//                val key = call.parameters["key"]!!
//                val suggestion = trie.getAllWordsByPrefix(key)
//                call.respondText(status = HttpStatusCode.OK) {suggestion.toString()}
//
//            }
//        }
    }

    install(ContentNegotiation) {
        serialization(
            contentType = ContentType.Application.Json,
            json = Json(
                DefaultJsonConfiguration.copy(
                    prettyPrint = true
                )
            )
        )
    }
}


@Serializable
data class AppConfig(val port: Int)

fun <R : Any> R.logger(): Lazy<Logger> {
    return lazy { LoggerFactory.getLogger(this.javaClass) }
}



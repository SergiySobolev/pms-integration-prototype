package org.dataart.pmsintegration

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpMethod.Companion.Post
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.serialization.DefaultJsonConfiguration
import io.ktor.serialization.serialization
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.dataart.pmsintegration.pmsclients.PmsClient
import org.dataart.pmsintegration.cache.AthenaHealthCache
import org.dataart.pmsintegration.data.PatientRegistrationData
import org.dataart.pmsintegration.facade.impl.AthenaHealthFacade
import org.dataart.pmsintegration.facade.PmsFacade
import org.dataart.pmsintegration.pmsclients.impl.AthenaHealthClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Instant
import java.util.Objects.nonNull

fun main() {
    val config = AppConfig(10000)
    embeddedServer(Netty, port = config.port, module = Application::module).start()
}

fun Application.module() {

    val pmsClient: PmsClient = AthenaHealthClient()
    val pmsDao = AthenaHealthCache(pmsClient.getAccessToken(), Instant.now())
    val pmsFacade: PmsFacade = AthenaHealthFacade(pmsDao, pmsClient)

    routing {
        route("/pmsint") {
            route("/patient") {
                post {
                    val patientRegistrationData = call.receive<PatientRegistrationData>()
                    val patient = pmsFacade.registerPatient(patientRegistrationData)
                    call.respond(HttpStatusCode.Created, patient)
                }

            }
            route("/practiceinfo") {
                get("/") {
                    val practicesInfo = pmsFacade.getAvailablePractices()
                    call.respond(practicesInfo)
                }
                get("/{practiceId}") {
                    val practiceId = call.parameters["practiceId"]!!
                    val practiceInfo = pmsFacade.getAvailablePractice(practiceId)
                    if (nonNull(practiceInfo)) {
                        call.respond(HttpStatusCode.OK, practiceInfo!!)
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                }

            }
            route("/{practiceId}/providers") {
                get("/") {
                    val practiceId = call.parameters["practiceId"]!!
                    val providersInfo = pmsFacade.getProvidersInfo(practiceId)
                    call.respond(providersInfo)
                }

            }
            route("/slot/{practiceId}/{providerId}") {
                get("/") {
                    val practiceId = call.parameters["practiceId"]!!
                    val providerId = call.parameters["providerId"]!!
                    val appointmentsInfo = pmsFacade.getAppointmentsInfo(practiceId, providerId)
                    call.respond(appointmentsInfo)
                }

            }
        }
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

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.AccessControlAllowHeaders)
        header(HttpHeaders.ContentType)
        header(HttpHeaders.AccessControlAllowOrigin)
        header(HttpHeaders.AccessControlAllowHeaders)
        anyHost()
    }
}


@Serializable
data class AppConfig(val port: Int)




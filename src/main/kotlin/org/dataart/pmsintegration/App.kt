package org.dataart.pmsintegration

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.serialization.DefaultJsonConfiguration
import io.ktor.serialization.serialization
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.serialization.json.Json
import org.dataart.pmsintegration.cache.AthenaHealthCache
import org.dataart.pmsintegration.data.AppointmentBookingRequest
import org.dataart.pmsintegration.data.PatientRegistrationData
import org.dataart.pmsintegration.facade.PmsFacade
import org.dataart.pmsintegration.facade.impl.AthenaHealthFacade
import org.dataart.pmsintegration.pmsclients.PmsClient
import org.dataart.pmsintegration.pmsclients.impl.AthenaHealthClient
import java.time.Instant
import java.util.Objects.nonNull

fun main(args: Array<String>) {
    val appConfig = loadConfig(args)
    embeddedServer(factory = Netty,
        port = appConfig.server.port,
        module = Application::module).start()
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
                    call.respond(Created, patient)
                }

                get("{patientid}/practice/{practiceid}/appointments") {
                    val practiceid = call.parameters["practiceid"]!!
                    val patientid = call.parameters["patientid"]!!
                    val appointmentsInfo = pmsFacade.getPatientAppointmentsInfo(patientid , practiceid)
                    call.respond(appointmentsInfo)
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
            route("/appointment") {
                put {
                    val req = call.receive<AppointmentBookingRequest>()
                    pmsFacade.bookAppointment(req)
                    call.respond(Created)
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





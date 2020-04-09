package org.dataart.pmsintegration

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import org.dataart.pmsintegration.data.AppointmentBookingRequest
import org.dataart.pmsintegration.data.PatientRegistrationData
import org.dataart.pmsintegration.facade.PmsFacade
import java.util.*


fun Application.routing(pmsFacade: PmsFacade) {
    routing {
        route("/pmsint") {
            route("/patient") {
                post {
                    val patientRegistrationData = call.receive<PatientRegistrationData>()
                    val patient = pmsFacade.registerPatient(patientRegistrationData)
                    call.respond(HttpStatusCode.Created, patient)
                }

                get("{patientid}/practice/{practiceid}/appointments") {
                    val practiceid = call.parameters["practiceid"]!!
                    val patientid = call.parameters["patientid"]!!
                    val appointmentsInfo = pmsFacade.getPatientAppointmentsInfo(patientid, practiceid)
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
                    if (Objects.nonNull(practiceInfo)) {
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
                    call.respond(HttpStatusCode.Created)
                }
            }
        }
    }
}
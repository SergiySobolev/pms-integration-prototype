package org.dataart.pmsintegration.pmsclients.impl

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.parseList
import mu.KLogging
import org.dataart.pmsintegration.data.*
import org.dataart.pmsintegration.pmsclients.PmsClient

class AthenaHealthClient : PmsClient {

    private val athenaKtorHealthClient = AthenaKtorHealthClient()

    private val json = Json(
        JsonConfiguration.Stable.copy(
            strictMode = false
        )
    )

    override fun getAvailablePractices(accessToken: String): PracticesInfo {
        val url = "https://api.athenahealth.com/preview1/1/practiceinfo"
        val authorizationHeader = "Bearer $accessToken"

        logger.info("Getting all practices info from AthenaHealth")
        val (_, response, _) = Fuel.get(url)
            .header(Headers.AUTHORIZATION, authorizationHeader)
            .response()

        return json.parse(
            PracticesInfo.serializer(),
            String(response.data)
        )
    }

    override fun getAvailablePractice(accessToken: String, practiceId: String): PracticeInfo? {
        logger.info("Getting info about practice $practiceId")
        val practicesInfo = getAvailablePractices(accessToken)
        return practicesInfo.practiceinfo.find { it.practiceid == practiceId }
    }

    override fun getPracticeDepartments(practiceId: String) : DepartmentsInfo{
        return athenaKtorHealthClient.getPracticeDepartments(practiceId)
    }

    override fun getProvidersInfo(accessToken: String, practiceId: String, limit: Int ): ProvidersInfo {
       return athenaKtorHealthClient.getProvidersInfo(practiceId, limit)
    }

    override fun getAppointmentsInfo(
        accessToken: String,
        practiceId: String,
        providerId: String,
        appointmentTypeId: Int,
        departmentId: Int
    ): AppointmentsInfo {

        val url = "https://api.athenahealth.com/preview1/$practiceId/appointments/open" +
                "?appointmenttypeid=$appointmentTypeId&departmentid=$departmentId" +
                "&ignoreschedulablepermission=false&providerid=$providerId"

        val authorizationHeader = "Bearer $accessToken"

        logger.info("Getting available slots for provider #$providerId")

        val (_, response, _) = Fuel.get(url)
            .header(Headers.AUTHORIZATION, authorizationHeader)
            .response()

        return json.parse(
            AppointmentsInfo.serializer(),
            String(response.data)
        )
    }

    @ImplicitReflectionSerializer
    override fun registerPatient(accessToken:String, patientRegistrationData: PatientRegistrationData): Patient {
        val url = "https://api.athenahealth.com/preview1/${patientRegistrationData.practiceid}/patients"
        val authorizationHeader = "Bearer $accessToken"

        logger.info("Registering patient ${patientRegistrationData.firstname} ${patientRegistrationData.lastname}")

        val parameters = listOf (
            Pair("firstname", patientRegistrationData.firstname),
            Pair("lastname", patientRegistrationData.lastname),
            Pair("dob", patientRegistrationData.dob),
            Pair("departmentid", patientRegistrationData.departmentid),
            Pair("mobilephone", patientRegistrationData.mobilephone)
        )
        val (_, response, _) = Fuel.post(url, parameters)
            .header(Headers.AUTHORIZATION, authorizationHeader)
            .response()

        val patientRegistrationResponse: List<PatientRegistrationResponse> = json.parseList(String(response.data))

        logger.info("Patient ${patientRegistrationData.firstname} ${patientRegistrationData.lastname} " +
                "successfully registered with id = ${patientRegistrationResponse[0].patientid}")

        return Patient(patientRegistrationData, patientRegistrationResponse[0])
    }

    @ImplicitReflectionSerializer
    override fun bookAppointment(accessToken: String, req: AppointmentBookingRequest) {
        val url = "https://api.athenahealth.com/preview1/${req.practiceid}/appointments/${req.appointmentid}" +
                "?patientid=${req.patientid}&appointmenttypeid=${req.appointmenttypeid}"
        val authorizationHeader = "Bearer $accessToken"

        logger.info("Booking appointment ${req.appointmentid} for patient ${req.patientid}")

        try {
            Fuel.put(url)
                .timeout(120000000)
                .header(Headers.AUTHORIZATION, authorizationHeader)
                .response()
        } catch (e: Exception) {
            logger.warn("ignore any exception because of timeout")
        }
    }

    override fun getPatientAppointmentsInfo(
        accessToken: String,
        patientid: String,
        practiceid: String
    ): AppointmentsInfo {
        val url = "https://api.athenahealth.com/preview1/$practiceid/patients/$patientid/appointments"
        val authorizationHeader = "Bearer $accessToken"

        logger.info("Getting appointments for patient $patientid")

        val (_, response, _) = Fuel.get(url)
            .header(Headers.AUTHORIZATION, authorizationHeader)
            .response()

        val appointmentsInfo = json.parse(AppointmentsInfo.serializer(), String(response.data))

        logger.info("Found ${appointmentsInfo.totalcount} appointments")

        return appointmentsInfo

    }

    override fun getAccessToken(): String {
        val url = "https://developer.athenahealth.com/io-docs/getoauth2accesstoken"
        val apiId = "784"
        val authFlow = "client_cred"
        val clientId = "k8fp5jsv974juy3sucusntpy"
        val clientSecret = "g26TewPEqJ9VfnY"

        val parameters = listOf (
            Pair("apiId", apiId),
            Pair("auth_flow", authFlow),
            Pair("client_id", clientId),
            Pair("client_secret", clientSecret)
        )
        logger.info("Authentication call to AthenaHealth")
        val (_, response, _) = Fuel.get(url, parameters).response()

        val authResponse: AuthResponse = json.parse(
            AuthResponse.serializer(),
            String(response.data)
        )
        val accessToken = authResponse.result.access_token
        logger.debug{ "Access Token:  $accessToken" }
        return accessToken
    }

    companion object : KLogging()

}



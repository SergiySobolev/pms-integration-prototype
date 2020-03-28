package org.dataart.pmsintegration.pmsclients.impl

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import mu.KLogging
import org.dataart.pmsintegration.pmsclients.PmsClient
import org.dataart.pmsintegration.pmsclients.data.AuthResponse
import org.dataart.pmsintegration.pmsclients.data.PracticeInfo
import org.dataart.pmsintegration.pmsclients.data.PracticesInfo

class AthenaHealthClient : PmsClient {

    private val json = Json(
        JsonConfiguration.Stable.copy(
            strictMode = false
        )
    )

    override fun getAvailablePractices(): PracticesInfo {
        val url = "https://api.athenahealth.com/preview1/1/practiceinfo"
        val authorizationHeader = "Bearer " + getAccessToken()

        logger.info("Getting all practices info from AthenaHealth")
        val (_, response, _) = Fuel.get(url)
            .header(Headers.AUTHORIZATION, authorizationHeader)
            .response()

        return json.parse(
            PracticesInfo.serializer(),
            String(response.data)
        )

    }

    override fun getAvailablePractice(practiceId: String): PracticeInfo {
        logger.info("Getting info about practice $practiceId")
        TODO("Not yet implemented")
    }

    override fun getPracticeDepartments() {
        TODO("Not yet implemented")
    }

    private fun getAccessToken(): String {
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
        logger.info{ "Access Token:  $accessToken" }
        return accessToken
    }

    companion object : KLogging()

}



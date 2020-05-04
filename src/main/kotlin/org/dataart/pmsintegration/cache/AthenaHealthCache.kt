package org.dataart.pmsintegration.cache

import mu.KLogging
import org.dataart.pmsintegration.pmsclients.impl.AthenaHealthClient
import java.time.Duration
import java.time.Instant

//todo rewrite with using external thread-safe storage
class AthenaHealthCache {

    companion object : KLogging(){

        private var accessToken: String = ""
        private var accessTokenRetrieveTime = Instant.now()
        private val athenaHealthClient = AthenaHealthClient()
        private val TOKEN_REFRESHMENT_RATE_MINS = 40

        private fun isAccessTokenValid(): Boolean {
            val now = Instant.now()
            return (Duration.between(accessTokenRetrieveTime, now)
                .toMinutes() < TOKEN_REFRESHMENT_RATE_MINS) && accessToken.isNotBlank()
        }

        fun getAccessToken(): String {
            if (!isAccessTokenValid()) {
                logger.warn("Access token is invalid, need to refresh ")
                val now = Instant.now()
                accessToken = athenaHealthClient.getAccessToken()
                accessTokenRetrieveTime = now
                logger.info("Access token is updated, update time is $accessTokenRetrieveTime ")
            }
            return accessToken
        }


    }

}

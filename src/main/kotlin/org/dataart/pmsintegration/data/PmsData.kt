package org.dataart.pmsintegration.data
import kotlinx.serialization.Serializable

@Serializable
data class AuthResult(val access_token: String,
                      val token_type: String,
                      val expires_in: Int,
                      val refresh_token: String,
                      val scope: String)

@Serializable
data class AuthResponse(val success: Boolean, val result: AuthResult)

@Serializable
data class PracticesInfo(val totalcount: Int, val practiceinfo: Array<PracticeInfo>)

@Serializable
data class PracticeInfo(val iscoordinatorsender: Boolean,
                        val hasclinicals: Boolean,
                        val name: String,
                        val practiceid: String,
                        val hascommunicator: Boolean,
                        val hascollector: Boolean,
                        val golivedate: String
)

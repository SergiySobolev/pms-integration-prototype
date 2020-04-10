package org.dataart.pmsintegration.pmsclients.impl
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import mu.KLogging
import org.apache.http.HttpHeaders.AUTHORIZATION
import org.dataart.pmsintegration.AppConfig
import org.dataart.pmsintegration.cache.AthenaHealthCache
import org.dataart.pmsintegration.data.DepartmentsInfo

//TODO implement PmsFacade interface in fully manner and replace AthenaHealthClient
class AthenaKtorHealthClient {

    private val appConfig = AppConfig.get()

    private val client = HttpClient(Apache) {

        engine {
            followRedirects = true
            socketTimeout = 30_000
            connectTimeout = 30_000
            connectionRequestTimeout = 60_000
        }

        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = appConfig.pms.athenahealth.url
            }
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(
                json = Json(
                    JsonConfiguration.Stable.copy(
                        strictMode = false
                    )
                )
            )
        }
    }

    fun getPracticeDepartments(practiceId: String): DepartmentsInfo {
        logger.info ("KTOR client: retrieving departments for practice #$practiceId")
        val departmentsInfo = runBlocking {
            client.get<DepartmentsInfo> {
                url {
                    encodedPath = "/$practiceId/departments?providerlist=false&showalldepartments=false"
                }
                header(AUTHORIZATION, "Bearer ${AthenaHealthCache.getAccessToken()}")
            }
        }
        logger.debug ("KTOR client: Departments info for practice #$practiceId retrieved")
        return departmentsInfo
    }

    companion object : KLogging()
}
package org.dataart.pmsintegration

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.serialization.DefaultJsonConfiguration
import io.ktor.serialization.serialization
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.serialization.json.Json
import org.dataart.pmsintegration.facade.PmsFacade
import org.dataart.pmsintegration.facade.impl.AthenaHealthFacade
import org.dataart.pmsintegration.gcpservices.datastore.PmsDatastoreService
import org.dataart.pmsintegration.gcpservices.datastore.impl.PmsDatastoreServiceImpl
import org.dataart.pmsintegration.gcpservices.storage.PmsStorageService
import org.dataart.pmsintegration.gcpservices.storage.impl.PmsStorageServiceImpl
import org.dataart.pmsintegration.pmsclients.PmsClient
import org.dataart.pmsintegration.pmsclients.impl.AthenaHealthClient

fun main() {
    val appConfig = AppConfig.get()
    embeddedServer(
        factory = Netty,
        port = appConfig.server.port,
        module = Application::module
    ).start()
}


fun Application.module() {

    val pmsClient: PmsClient = AthenaHealthClient()
    val pmsDatastoreService: PmsDatastoreService = PmsDatastoreServiceImpl()
    val pmsStorageService: PmsStorageService = PmsStorageServiceImpl()
    val pmsFacade: PmsFacade = AthenaHealthFacade(pmsClient, pmsDatastoreService, pmsStorageService)

    routing(pmsFacade)

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






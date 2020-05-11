package org.dataart.pmsintegration.gcpservices.storage.impl

import com.google.cloud.storage.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import mu.KLogging
import org.dataart.pmsintegration.AppConfig
import org.dataart.pmsintegration.data.PracticesInfo
import org.dataart.pmsintegration.gcpservices.storage.PmsStorageService
import java.time.Instant


class PmsStorageServiceImpl : PmsStorageService {

    private val storage = StorageOptions.getDefaultInstance().service

    private val json = Json(
        JsonConfiguration.Stable.copy(
            strictMode = false
        )
    )

    override fun uploadPracticeInfo(practicesInfo: PracticesInfo) {
        val bucketName = AppConfig.get().gcp.practiceinfobucketname
        val objectName = generatePracticesInfoFileName()
        logger.info("Uploading object $objectName to $bucketName GCP Storage Bucket")
        val blobId = BlobId.of(bucketName, objectName)
        val blobInfo = BlobInfo.newBuilder(blobId)
            .setStorageClass(StorageClass.STANDARD)
            .build()
        val practiceInfoBlob = storage.create(blobInfo, serialize(practicesInfo))
        logger.info("Practices info successfully uploaded")
        logger.info("Blob: $practiceInfoBlob")
    }

    private fun serialize(practicesInfo: PracticesInfo): ByteArray {
        return json.stringify(PracticesInfo.serializer(), practicesInfo).toByteArray()
    }

    private fun generatePracticesInfoFileName(): String {
        return "practices_info.json"
    }

    companion object : KLogging()
}
package org.dataart.pmsintegration.gcpservices.datastore.impl

import com.google.cloud.datastore.Entity
import com.google.cloud.datastore.Key
import com.google.cloud.datastore.KeyFactory
import mu.KLogging
import org.dataart.pmsintegration.data.PracticesInfo
import org.dataart.pmsintegration.gcpservices.datastore.ClientFactory
import org.dataart.pmsintegration.gcpservices.datastore.PmsDatastoreService

class PmsDatastoreServiceImpl : PmsDatastoreService {

    private val datastore = ClientFactory.get()

    override fun savePracticeInfo(practicesinfo: PracticesInfo) {

        val keyFactory: KeyFactory = datastore.newKeyFactory().setKind("Practice")
        for (practice in practicesinfo.practiceinfo) {
            val key: Key = keyFactory.newKey(practice.practiceid)
            val entity: Entity = Entity.newBuilder(key)
                .set("name", practice.name)
                .build()
            datastore.put(entity)
            logger.info("GCP Datastore: practice ${practice.practiceid} successfully stored")
        }

    }

    companion object : KLogging()
}
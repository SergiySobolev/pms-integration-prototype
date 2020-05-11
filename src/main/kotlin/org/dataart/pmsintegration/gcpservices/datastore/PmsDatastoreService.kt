package org.dataart.pmsintegration.gcpservices.datastore

import org.dataart.pmsintegration.data.PracticesInfo

interface PmsDatastoreService {
    fun savePracticeInfo(practicesInfo: PracticesInfo)
}
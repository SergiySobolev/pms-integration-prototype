package org.dataart.pmsintegration.gcpservices.storage

import org.dataart.pmsintegration.data.PracticesInfo

interface PmsStorageService {
    fun uploadPracticeInfo(practicesInfo: PracticesInfo)
}
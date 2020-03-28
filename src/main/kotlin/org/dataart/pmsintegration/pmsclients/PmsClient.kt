package org.dataart.pmsintegration.pmsclients

import org.dataart.pmsintegration.pmsclients.data.PracticeInfo
import org.dataart.pmsintegration.pmsclients.data.PracticesInfo

interface PmsClient {
    fun getAvailablePractices():PracticesInfo
    fun getAvailablePractice(practiceId: String): PracticeInfo
    fun getPracticeDepartments()
}
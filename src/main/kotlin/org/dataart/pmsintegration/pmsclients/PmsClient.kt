package org.dataart.pmsintegration.pmsclients

import org.dataart.pmsintegration.data.PracticeInfo
import org.dataart.pmsintegration.data.PracticesInfo


interface PmsClient {
    fun getAvailablePractices(accessToken: String):PracticesInfo
    fun getAvailablePractice(accessToken: String, practiceId: String): PracticeInfo?
    fun getPracticeDepartments(accessToken: String, practiceId: String)
    fun getAccessToken(): String
}
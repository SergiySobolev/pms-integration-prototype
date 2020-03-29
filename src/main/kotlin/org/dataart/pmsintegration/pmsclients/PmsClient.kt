package org.dataart.pmsintegration.pmsclients

import org.dataart.pmsintegration.data.AppointmentsInfo
import org.dataart.pmsintegration.data.PracticeInfo
import org.dataart.pmsintegration.data.PracticesInfo
import org.dataart.pmsintegration.data.ProvidersInfo


interface PmsClient {
    fun getAvailablePractices(accessToken: String): PracticesInfo

    fun getAvailablePractice(accessToken: String, practiceId: String): PracticeInfo?

    fun getPracticeDepartments(accessToken: String, practiceId: String)

    fun getProvidersInfo(accessToken: String, practiceId: String, limit: Int = 50): ProvidersInfo

    fun getAppointmentsInfo(
        accessToken: String,
        practiceId: String,
        providerId: String,
        appointmentTypeId: Int = 4,
        departmentId: Int = 1
    ): AppointmentsInfo

    fun getAccessToken(): String
}
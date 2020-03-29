package org.dataart.pmsintegration.facade

import org.dataart.pmsintegration.data.AppointmentsInfo
import org.dataart.pmsintegration.data.PracticeInfo
import org.dataart.pmsintegration.data.PracticesInfo
import org.dataart.pmsintegration.data.ProvidersInfo

interface PmsFacade {

    fun getAvailablePractices(): PracticesInfo

    fun getAvailablePractice(practiceId: String): PracticeInfo?

    fun getPracticeDepartments(practiceId: String)

    fun getProvidersInfo(practiceId: String, limit:Int = 50): ProvidersInfo

    fun getAppointmentsInfo(
        practiceId: String,
        providerId: String,
        appointmentTypeId: Int = 6,
        departmentId: Int = 1
    ): AppointmentsInfo
}
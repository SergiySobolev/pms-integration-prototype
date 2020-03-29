package org.dataart.pmsintegration.facade.impl

import org.dataart.pmsintegration.pmsclients.PmsClient
import org.dataart.pmsintegration.cache.AthenaHealthCache
import org.dataart.pmsintegration.data.AppointmentsInfo
import org.dataart.pmsintegration.data.PracticeInfo
import org.dataart.pmsintegration.data.PracticesInfo
import org.dataart.pmsintegration.data.ProvidersInfo
import org.dataart.pmsintegration.facade.PmsFacade

class AthenaHealthFacade(
    val athenaHealthDao: AthenaHealthCache,
    val pmsClient: PmsClient
) : PmsFacade {

    override fun getAvailablePractices(): PracticesInfo {
        return pmsClient.getAvailablePractices(athenaHealthDao.accessToken)
    }

    override fun getAvailablePractice(practiceId: String): PracticeInfo? {
        return pmsClient.getAvailablePractice(athenaHealthDao.accessToken, practiceId)
    }

    override fun getPracticeDepartments(practiceId: String) {
        return pmsClient.getPracticeDepartments(athenaHealthDao.accessToken, practiceId)
    }

    override fun getProvidersInfo(practiceId: String, limit: Int): ProvidersInfo {
        return pmsClient.getProvidersInfo(athenaHealthDao.accessToken, practiceId, limit)
    }

    override fun getAppointmentsInfo(
        practiceId: String,
        providerId: String,
        appointmentTypeId: Int,
        departmentId: Int
    ): AppointmentsInfo {
        return pmsClient.getAppointmentsInfo(
            athenaHealthDao.accessToken,
            practiceId,
            providerId,
            appointmentTypeId,
            departmentId
        )
    }


}
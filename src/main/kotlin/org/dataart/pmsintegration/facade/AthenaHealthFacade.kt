package org.dataart.pmsintegration.facade

import org.dataart.pmsintegration.pmsclients.PmsClient
import org.dataart.pmsintegration.dao.AthenaHealthDao
import org.dataart.pmsintegration.data.PracticeInfo
import org.dataart.pmsintegration.data.PracticesInfo

class AthenaHealthFacade(val athenaHealthDao: AthenaHealthDao, val pmsClient: PmsClient) : PmsFacade {

    override fun getAvailablePractices(): PracticesInfo {
        return pmsClient.getAvailablePractices(athenaHealthDao.accessToken)
    }

    override fun getAvailablePractice(practiceId: String): PracticeInfo? {
        return pmsClient.getAvailablePractice(athenaHealthDao.accessToken, practiceId)
    }

    override fun getPracticeDepartments(practiceId: String) {
        return pmsClient.getPracticeDepartments(athenaHealthDao.accessToken, practiceId)
    }
}
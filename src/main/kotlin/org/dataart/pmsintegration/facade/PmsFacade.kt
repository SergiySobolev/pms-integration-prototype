package org.dataart.pmsintegration.facade

import org.dataart.pmsintegration.data.PracticeInfo
import org.dataart.pmsintegration.data.PracticesInfo

interface PmsFacade {
    fun getAvailablePractices(): PracticesInfo
    fun getAvailablePractice(practiceId: String): PracticeInfo?
    fun getPracticeDepartments(practiceId: String)
}
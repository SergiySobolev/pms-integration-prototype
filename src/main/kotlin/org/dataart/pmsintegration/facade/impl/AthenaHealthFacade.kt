package org.dataart.pmsintegration.facade.impl

import org.dataart.pmsintegration.pmsclients.PmsClient
import org.dataart.pmsintegration.cache.AthenaHealthCache
import org.dataart.pmsintegration.data.*
import org.dataart.pmsintegration.facade.PmsFacade

class AthenaHealthFacade(
    val athenaHealthCache: AthenaHealthCache,
    val pmsClient: PmsClient
) : PmsFacade {

    override fun getAvailablePractices(): PracticesInfo {
        return pmsClient.getAvailablePractices(athenaHealthCache.getAccessToken())
    }

    override fun getAvailablePractice(practiceId: String): PracticeInfo? {
        return pmsClient.getAvailablePractice(athenaHealthCache.getAccessToken(), practiceId)
    }

    override fun getPracticeDepartments(practiceId: String) {
        return pmsClient.getPracticeDepartments(athenaHealthCache.getAccessToken(), practiceId)
    }

    override fun getProvidersInfo(practiceId: String, limit: Int): ProvidersInfo {
        return pmsClient.getProvidersInfo(athenaHealthCache.getAccessToken(), practiceId, limit)
    }

    override fun getAppointmentsInfo(
        practiceId: String,
        providerId: String,
        appointmentTypeId: Int,
        departmentId: Int
    ): AppointmentsInfo {
        return pmsClient.getAppointmentsInfo(
            athenaHealthCache.getAccessToken(),
            practiceId,
            providerId,
            appointmentTypeId,
            departmentId
        )
    }

    override fun registerPatient(patientRegistrationData: PatientRegistrationData): Patient {
        return pmsClient.registerPatient(athenaHealthCache.getAccessToken(), patientRegistrationData)
    }

    override fun bookAppointment(req: AppointmentBookingRequest) {
        pmsClient.bookAppointment(athenaHealthCache.getAccessToken(), req)
    }

    override fun getPatientAppointmentsInfo(patientid: String, practiceid: String): AppointmentsInfo {
        return pmsClient.getPatientAppointmentsInfo(athenaHealthCache.getAccessToken(), patientid, practiceid)
    }
}
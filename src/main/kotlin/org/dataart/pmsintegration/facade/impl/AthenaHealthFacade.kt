package org.dataart.pmsintegration.facade.impl

import org.dataart.pmsintegration.cache.AthenaHealthCache
import org.dataart.pmsintegration.data.*
import org.dataart.pmsintegration.facade.PmsFacade
import org.dataart.pmsintegration.gcpservices.datastore.PmsDatastoreService
import org.dataart.pmsintegration.pmsclients.PmsClient

class AthenaHealthFacade(private val pmsClient: PmsClient,
                         private val pmsDatastoreService: PmsDatastoreService) : PmsFacade {

    override fun getAvailablePractices(): PracticesInfo {
        val availablePractices = pmsClient.getAvailablePractices(AthenaHealthCache.getAccessToken())
        setInactivePractices(availablePractices)
        pmsDatastoreService.savePracticeInfo(availablePractices)
        return availablePractices
    }

    private fun setInactivePractices(availablePractices: PracticesInfo) {
        //its information from athenahealth documentation. Active practice only one.
        for (practice in availablePractices.practiceinfo) {
            if (practice.practiceid != "195900") {
                practice.isactive = false
            }
        }
    }

    override fun getAvailablePractice(practiceId: String): PracticeInfo? {
        return pmsClient.getAvailablePractice(AthenaHealthCache.getAccessToken(), practiceId)
    }

    override fun getPracticeDepartments(practiceId: String): DepartmentsInfo {
        return pmsClient.getPracticeDepartments(practiceId)
    }

    override fun getProvidersInfo(practiceId: String, limit: Int): ProvidersInfo {
        return pmsClient.getProvidersInfo(AthenaHealthCache.getAccessToken(), practiceId, limit)
    }

    override fun getAppointmentsInfo(
        practiceId: String,
        providerId: String,
        appointmentTypeId: Int,
        departmentId: Int
    ): AppointmentsInfo {
        return pmsClient.getAppointmentsInfo(
            AthenaHealthCache.getAccessToken(),
            practiceId,
            providerId,
            appointmentTypeId,
            departmentId
        )
    }

    override fun registerPatient(patientRegistrationData: PatientRegistrationData): Patient {
        return pmsClient.registerPatient(AthenaHealthCache.getAccessToken(), patientRegistrationData)
    }

    override fun bookAppointment(req: AppointmentBookingRequest) {
        pmsClient.bookAppointment(AthenaHealthCache.getAccessToken(), req)
    }

    override fun getPatientAppointmentsInfo(patientid: String, practiceid: String): AppointmentsInfo {
        return pmsClient.getPatientAppointmentsInfo(AthenaHealthCache.getAccessToken(), patientid, practiceid)
    }

    override fun getProviderInfo(practiceId: String, providerId: String): Provider? {
        return pmsClient.getProviderInfo(practiceId, providerId)
    }
}
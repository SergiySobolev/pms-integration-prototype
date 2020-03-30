package org.dataart.pmsintegration.facade

import org.dataart.pmsintegration.data.*

interface PmsFacade {

    fun getAvailablePractices(): PracticesInfo

    fun getAvailablePractice(practiceId: String): PracticeInfo?

    fun getPracticeDepartments(practiceId: String)

    fun getProvidersInfo(practiceId: String, limit:Int = 50): ProvidersInfo

    fun getAppointmentsInfo(
        practiceId: String,
        providerId: String,
        appointmentTypeId: Int = 4,
        departmentId: Int = 1
    ): AppointmentsInfo

    fun registerPatient(patientRegistrationData: PatientRegistrationData) : Patient

    fun bookAppointment(req: AppointmentBookingRequest)

    fun getPatientAppointmentsInfo(patientid: String, practiceid: String) : AppointmentsInfo
}
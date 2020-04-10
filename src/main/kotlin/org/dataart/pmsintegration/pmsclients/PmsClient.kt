package org.dataart.pmsintegration.pmsclients

import org.dataart.pmsintegration.data.*


interface PmsClient {
    fun getAvailablePractices(accessToken: String): PracticesInfo

    fun getAvailablePractice(accessToken: String, practiceId: String): PracticeInfo?

    fun getPracticeDepartments(practiceId: String): DepartmentsInfo

    fun getProvidersInfo(accessToken: String, practiceId: String, limit: Int = 50): ProvidersInfo

    fun getAppointmentsInfo(
        accessToken: String,
        practiceId: String,
        providerId: String,
        appointmentTypeId: Int = 4,
        departmentId: Int = 1
    ): AppointmentsInfo

    fun registerPatient(accessToken: String, patientRegistrationData: PatientRegistrationData) : Patient

    fun bookAppointment(accessToken: String, req: AppointmentBookingRequest)

    fun getPatientAppointmentsInfo(accessToken: String, patientid: String, practiceid: String) : AppointmentsInfo

    fun getAccessToken(): String


}
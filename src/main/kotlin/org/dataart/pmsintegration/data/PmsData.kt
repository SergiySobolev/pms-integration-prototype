package org.dataart.pmsintegration.data

import kotlinx.serialization.Serializable

@Serializable
data class AuthResult(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String,
    val scope: String
)

@Serializable
data class AuthResponse(val success: Boolean, val result: AuthResult)

@Serializable
data class PracticesInfo(val totalcount: Int, val practiceinfo: Array<PracticeInfo>)

@Serializable
data class PracticeInfo(
    val iscoordinatorsender: Boolean,
    val hasclinicals: Boolean,
    val name: String,
    val practiceid: String,
    val hascommunicator: Boolean,
    val hascollector: Boolean,
    val golivedate: String
)

@Serializable
data class Provider(
    val firstname: String,
    val acceptingnewpatients: Boolean? = true,
    val schedulingname: String,
    val providertypeid: String? = "",
    val billable: Boolean,
    val lastname: String,
    val providerid: Int,
    val ansispecialtycode: String? = "",
    val hideinportal: Boolean? = false,
    val entitytype: String? = ""
)

@Serializable
data class ProvidersInfo(
    val providers: Array<Provider>
)

@Serializable
data class Appointment(
    val date: String,
    val appointmentid: Int,
    val departmentid: Int,
    val localproviderid: Int,
    val appointmenttype: String,
    val providerid: Int,
    val starttime: String,
    val duration: Int,
    val appointmenttypeid: Int,
    val patientappointmenttypename: String
)

@Serializable
data class AppointmentsInfo(
    val totalcount: Int,
    val appointments: Array<Appointment>
)

@Serializable
data class PatientRegistrationData(
    val firstname: String,
    val lastname: String,
    val dob: String,
    val mobilephone: String,
    val practiceid: String,
    val departmentid: String
)

@Serializable
data class PatientRegistrationResponse(
    val patientid: String
)

@Serializable
data class Patient(
    val patientid: String,
    val firstname: String,
    val lastname: String,
    val dob: String,
    val mobilephone: String,
    val practiceid: String,
    val departmentid: String
) {

    constructor(
        patientRegistrationData: PatientRegistrationData,
        patientRegistrationResponse: PatientRegistrationResponse
    ) :
        this(
            patientRegistrationResponse.patientid,
            patientRegistrationData.firstname,
            patientRegistrationData.lastname,
            patientRegistrationData.dob,
            patientRegistrationData.mobilephone,
            patientRegistrationData.practiceid,
            patientRegistrationData.departmentid
        )

}
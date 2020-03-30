<template>

   <div class="container">
    
      <h3 v-if="patientid">Patient Data:</h3>
      <h3 v-else>Patient Registration:</h3>

      <div class="box">
      
        <form id="patient-register-form" @submit.prevent = "registerPatient">

          <div class="form-group" v-if="patientid">        
            <label class="label"> Patient Id </label>
            <input type="text" class="form-control" name="patientid" v-model="patientid"/>
          </div>  

          <div class="form-group">
            <label class="label"> Firstname </label>
            <input type="text" class="form-control" name="firstname" v-model="firstname"/>
          </div>  

          <div class="form-group">
            <label class="label"> Lastname </label>
            <input type="text" class="form-control" name="lastname" v-model="lastname"/>
          </div>

           <div class="form-group">
            <label class="label"> Date of Birth </label>
            <input type="text" class="form-control" name="dob" v-model="dob" />
          </div>

           <div class="form-group">
            <label class="label"> Mobile Phone </label>
            <input type="text" class="form-control" name="mobilephone" v-model="mobilephone" />
          </div>

          <div class="form-group">
            <label class="label"> Department Id </label>
            <input type="text" class="form-control" name="departmentid" v-model="departmentid" readonly/>
          </div>

          <div class="form-group">
            <label class="label"> Practice Id </label>
            <input type="text" class="form-control" name="practiceid" v-model="practiceid" readonly/>
          </div>

          <button type="submit" class="btn btn-primary" v-if="!patientid">Register Patient</button>
        </form>

      </div>

      <h3 v-if="patientid"> Patient #{{patientid}} booked appointments  </h3>
        <table class="table" v-if="patientid">
          <thead>
            <tr>
              <th scope="col">Id</th>
              <th scope="col">Appointment Type</th>
              <th scope="col">Date</th>
              <th scope="col">Start Time</th>
              <th scope="col">Duration</th>
            </tr>
          </thead>
          <tbody v-if="appointmentsinfo">
            <tr
              v-for="appointment in appointmentsinfo.appointments" 
              v-bind:key="appointment.appointmentid"> 
              <th scope="row">{{appointment.appointmentid}}</th>
              <td>{{appointment.patientappointmenttypename}}</td>
              <td>{{appointment.date}}</td>
              <td>{{appointment.starttime}}</td>
              <td>{{appointment.duration}}</td>       
            </tr>
          </tbody>
      </table>  

      <br/>
      <router-link to="/"> Back To Home </router-link>

    </div> 

</template>


<script>

  import axios from 'axios';
  
  export default { 
    name: 'PatientRegistration',
    data ()  {
      return {
        patientid: null,
        firstname : "John",
        lastname : "Doe",
        dob : "04/16/1987",
        mobilephone : "541-754-3710",
        departmentid : "1",
        practiceid : "195900",
        appointmentsinfo: null
      }
    },
    created: function () {
      if(localStorage.patientid) {
        this.patientid = localStorage.patientid
        console.log("Patient id found = " + this.patientid)
        axios
          .get("http://localhost:10000/pmsint/patient/" + this.patientid +  "/practice/195900/appointments")
          .then(res => {
            this.appointmentsinfo = res.data;
          })
      }
    },
    watch: {
      patientid(newPatientId) {
        console.log("Setting new patientid to localStorage")
        localStorage.patientid = newPatientId;
      }
    },   
    methods : {
      registerPatient: function() {
        console.log("Registering patient: " + this.firstname + " " + this.lastname);
        axios
          .post("http://localhost:10000/pmsint/patient", {
                firstname: this.firstname,
                lastname: this.lastname,
                dob: this.dob,
                mobilephone: this.mobilephone,
                departmentid: this.departmentid,
                practiceid: this.practiceid
          })
          .then(res => {              
              this.patientid = res.data.patientid;
              localStorage.patientid = this.patientid;
              console.log("Patient successfully registered with id = " + this.patientid)
          }); 
        }        
      }      
  }
</script>

<style>
  h3 {
    margin-bottom: 5%;
  }
</style>
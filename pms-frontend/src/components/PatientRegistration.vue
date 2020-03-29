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

          <button type="submit" class="btn btn-primary">Register Patient</button>
        </form>

      </div>

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
        practiceid : "195900"
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
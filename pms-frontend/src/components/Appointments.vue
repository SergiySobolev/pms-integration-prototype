<template>
  <div id="appointmentsinfo">

     <div class="container">
    
        <h3> Provider #{{providerid}} Free Appointment Slots  </h3>
        <table class="table">
          <thead>
            <tr>
              <th scope="col">Id</th>
              <th scope="col">Appointment Type</th>
              <th scope="col">Date</th>
              <th scope="col">Start Time</th>
              <th scope="col">Duration</th>
              <th scope="col">Actions</th>
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
              <td>Book </td>
             <!-- 
              <td>
                  <router-link :to="{ name: 'practicedetails', params: {practiceid: practice.practiceid} }">
                      Practice Details 
                  </router-link>
              </td> -->
            </tr>
          </tbody>
      </table>  

      <router-link :to="{ name: 'providers', params: {practiceid: practiceid} }">
                    Back to Providers
      </router-link>

      <br/>

      <router-link to="/">
                    Back to Home
      </router-link>

    </div> 

  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: 'Appointments',
    props: ['providerid', 'practiceid'],
    data() {
      return {
        appointmentsinfo: null,
        patientid: null
      };
    },    
    mounted() {
      console.log("Check for patientid...")
      if (localStorage.patientid) {
        this.patientid = localStorage.patientid;
        console.log("Patientid retrieved from localStorage " + this.patientid);
      }
    },
    created: function() {
      axios
        .get('http://localhost:10000/pmsint/slot/' + this.practiceid + "/" + this.providerid)
        .then(res => {
          this.appointmentsinfo = res.data;
        })
    }
  }
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

<template>

    <div id="nav">
      <router-link to="/practices">Practices </router-link>  <br/>
      <router-link to="/patientregistrations"> Register Patient </router-link>  
    </div>
     
</template>


<script>
  export default {
    name: 'Home', 
    data ()  {
      return {
        patientid: null,       
      }
    },  
    mounted: function () {
      if(localStorage.patientid) {
        this.patientid = localStorage.patientid
        console.log("Patient id found = " + this.patientid)
      }
    },
  }
</script>

<style>
  h3 {
    margin-bottom: 5%;
  }
</style>
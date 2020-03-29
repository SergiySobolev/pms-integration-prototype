<template>

   <div class="container">
    
      <h3>Practices:</h3>
      <table class="table">
        <thead>
          <tr>
            <th scope="col">Practice Id</th>
            <th scope="col">Practice Name</th>
            <th scope="col">Go Live Date</th>
            <th scope="col">Details</th>
            <th scope="col">Providers</th>
          </tr>
        </thead>
        <tbody v-if="practices">
          <tr
            v-for="practice in practices.practiceinfo" 
            v-bind:key="practice.practiceid"> 
            <th scope="row">{{practice.practiceid}}</th>
            <td>{{practice.name}}</td>
            <td>{{practice.golivedate}}</td>
            <td>
                <router-link :to="{ name: 'practicedetails', params: {practiceid: practice.practiceid} }">
                    Practice Details 
                </router-link>
            </td>
            <td>
               <router-link :to="{ name: 'providers', params: {practiceid: practice.practiceid} }">
                    Providers
                </router-link>
            </td>
          </tr>
        </tbody>
      </table>  

      <router-link to="/"> Back To Home </router-link>
    </div> 
</template>


<script>
  import axios from 'axios';

  export default {
    name: 'Practices',
    data() {
      return {
        practices: null,
      };
    },    
    created: function() {
      axios
        .get('http://localhost:10000/pmsint/practiceinfo')
        .then(res => {
          this.practices = res.data;
        })
    }
  }
</script>

<style>
  h3 {
    margin-bottom: 5%;
  }
</style>
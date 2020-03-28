<template>
   <div class="container">
    <h3>Practices:</h3>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Practice Id</th>
          <th scope="col">Practice Name</th>
          <th scope="col">Go Live Date</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="practice in practices.practiceinfo" v-bind:key="practice.practiceid"> 
          <th scope="row">{{practice.practiceid}}</th>
          <td>{{practice.name}}</td>
          <td>{{practice.golivedate}}</td>
        </tr>
      </tbody>
    </table> 

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
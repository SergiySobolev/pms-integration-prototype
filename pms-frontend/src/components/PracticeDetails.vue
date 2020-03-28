<template>
  <div id="practiceDetails">

     <div class="container">
    
        <h3> Practice #{{practiceid}} Details </h3>
        <table class="table">

          <tbody v-if="practice">

            <tr>
              <td>ID</td>
              <td>{{practice.practiceid}}</td>
            </tr> 

            <tr>
              <td>Name</td>
              <td>{{practice.name}}</td>
            </tr> 

            <tr>
              <td>Go Live Date</td>
              <td>{{practice.golivedate}}</td>
            </tr>

            <tr>
              <td>Is Coordinator Sender</td>
              <td>{{practice.iscoordinatorsender}}</td>
            </tr>

            <tr>
              <td>Has Communicator</td>
              <td>{{practice.hascommunicator}}</td>
            </tr>

            <tr>
              <td>Has Collector</td>
              <td>{{practice.hascollector}}</td>
            </tr>
            
          </tbody>
        </table>  

    </div> 

  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: 'PracticeDetails',
    props: ['practiceid'],
    data() {
      return {
        practice: null,
      };
    },    
    created: function() {
      axios
        .get('http://localhost:10000/pmsint/practiceinfo/' + this.practiceid)
        .then(res => {
          this.practice = res.data;
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

<template>
  <div id="Providers ">

     <div class="container">
    
        <h3> Practice #{{practiceid}} Providers  </h3>
        <table class="table">
          <thead>
            <tr>
              <th scope="col">Id</th>
              <th scope="col">First Name</th>
              <th scope="col">Last Name</th>
              <th scope="col">Speciality</th>
              <th scope="col">Type</th>
            </tr>
          </thead>
          <tbody v-if="providers">
            <tr
              v-for="provider in providers.providers" 
              v-bind:key="provider.providerid"> 
              <th scope="row">{{provider.providerid}}</th>
              <td>{{provider.firstname}}</td>
              <td>{{provider.lastname}}</td>
              <td>{{provider.ansispecialtycode}}</td>
              <td>{{provider.providertypeid}}</td>
             <!-- 
              <td>
                  <router-link :to="{ name: 'practicedetails', params: {practiceid: practice.practiceid} }">
                      Practice Details 
                  </router-link>
              </td> -->
            </tr>
          </tbody>
      </table>  

      <router-link :to="{ name: 'practicedetails', params: {practiceid: practiceid} }">
                    Back to Practice Details
      </router-link>

    </div> 

  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: 'Providers',
    props: ['practiceid'],
    data() {
      return {
        providers: null,
      };
    },    
    created: function() {
      axios
        .get('http://localhost:10000/pmsint/' + this.practiceid + "/providers")
        .then(res => {
          this.providers = res.data;
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

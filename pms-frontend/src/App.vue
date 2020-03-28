<template>
  <div id="app">
    <Practices/>    
    <router-view/>
  </div>
</template>

<script>
import Practices from './components/Practices.vue'

export default {
  name: 'App',
  components: {
    Practices
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

import Vue from 'vue'
import Router from 'vue-router'
import Practices from './components/Practices.vue'
import PracticeDetails from './components/PracticeDetails.vue'
import Home from './views/Home.vue'

Vue.use(Router)

export default new Router({
    routes: [
        {
          path: '/',
          name: 'home',
          component: Home
        },
        {
          path: '/practices',
          name: 'practices',
          component: Practices
        },
        {
          path: '/practicedetails/:practiceid',
          name: 'practicedetails',
          component: PracticeDetails,
          props: true
        }
    ]
})
import Vue from 'vue'
import Router from 'vue-router'
import Practices from './components/Practices.vue'
import PracticeDetails from './components/PracticeDetails.vue'
import PatientRegistration from './components/PatientRegistration.vue'
import Providers from './components/Providers.vue'
import Appointments from './components/Appointments.vue'
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
          path: '/patientregistrations',
          name: 'patientregistrations',
          component: PatientRegistration        
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
        },
        {
          path: '/providers/:practiceid',
          name: 'providers',
          component: Providers,
          props: true
        },
        {
          path: '/appointments/:practiceid/:providerid',
          name: 'appointments',
          component: Appointments,
          props: true
        }
    ]
})
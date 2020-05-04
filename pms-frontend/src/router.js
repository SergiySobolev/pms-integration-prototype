import Vue from 'vue'
import Router from 'vue-router'
import Practices from './components/Practices.vue'
import PracticeDetails from './components/practice/PracticeDetails.vue'
import PatientRegistration from './components/PatientRegistration.vue'
import ProviderDetails from './components/practice/ProviderDetails.vue'
import Appointments from './components/Appointments.vue'
import Home from './views/Home.vue'
import Intro from './views/Intro.vue'
import Architecture from './views/Architecture.vue'
import Pms from './views/Pms.vue'

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'intro',
            component: Intro
        },
        {
          path: '/home',
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
            path: '/practice/:practiceid/provider/:providerid',
            name: 'providerdetails',
            component: ProviderDetails,
            props: true
        },
        {
          path: '/appointments/:practiceid/:providerid',
          name: 'appointments',
          component: Appointments,
          props: true
        },
        {
            path: '/architecture',
            name: 'architecture',
            component: Architecture
        },
        {
            path: '/pms',
            name: 'pms',
            component: Pms
        },
    ]
})
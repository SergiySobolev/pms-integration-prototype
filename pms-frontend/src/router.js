import Vue from 'vue'
import Router from 'vue-router'
import PracticeDetails from './components/PracticeDetails.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/practicedetails/:practiceid',
      name: 'practicedetails',
      component: PracticeDetails,
      props: true
    }
  ]
})

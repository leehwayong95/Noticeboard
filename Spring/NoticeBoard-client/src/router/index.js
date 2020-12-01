import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
// import Home from '@/components/Home'
// import Login from '@/components/Login'
// import Register from '@/components/Register'
import Test from '@/components/Test'
import SignUp from '@/components/SignUp'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/test',
      name: 'testpage',
      component: Test
    },
    {
      path: '/signup',
      name: 'signuptpage',
      component: SignUp
    }
  ]
})

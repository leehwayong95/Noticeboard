import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
// import Home from '@/components/Home'
// import Login from '@/components/Login'
// import Register from '@/components/Register'
import Test from '@/components/Test'
import SignUp from '@/components/SignUp'
import login from '@/components/Login'
import board from '@/components/Board'

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
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/board',
      name: 'board',
      component: board
    }
  ]
})

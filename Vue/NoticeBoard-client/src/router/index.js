import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
// import Home from '@/components/Home'
// import Login from '@/components/Login'
// import Register from '@/components/Register'
import Login from '@/components/Login'
import SignUp from '@/components/SignUp'
import BoardList from '@/components/BoardList'

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
      path: '/login',
      name: 'loginPage',
      component: Login
    },
    {
      path: '/signup',
      name: 'signuppage',
      component: SignUp
    },
    {
      path: '/boardlist',
      name: 'boardlist',
      component: BoardList
    }
  ]
})

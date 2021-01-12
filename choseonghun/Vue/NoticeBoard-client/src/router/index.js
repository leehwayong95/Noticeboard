import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import SignUp from '@/components/SignUp'
import BoardList from '@/components/BoardList'
import BoardDetail from '@/components/BoardDetail'
import BoardCreate from '@/components/BoardCreate'
import BoardUpdate from '@/components/BoardUpdate'

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
    },
    {
      path: '/boarddetail',
      name: 'boraddetail',
      component: BoardDetail
    },
    {
      path: '/boardcreate',
      name: 'boardcreate',
      component: BoardCreate
    },
    {
      path: '/boarddetail/:index',
      name: 'boarddetail',
      component: BoardDetail
    },
    {
      path: '/boardupdate',
      name: 'boardupdate',
      component: BoardUpdate
    }
  ]
})

import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login.vue'
import Join from '@/components/Join.vue'
import Board from '@/components/Board'
import BoardText from '@/components/BoardText'
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
      name: 'Loginpage',
      component: Login
    },
    {
      path: '/join',
      name: 'Joinpage',
      component: Join
    },
    {
      path: '/board',
      name: 'Boardpage',
      component: Board
    },
    {
      path: '/boardtext',
      name: 'Boardpage',
      component: BoardText
    }
  ]
})

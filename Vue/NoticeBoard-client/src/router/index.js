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
import write from '@/components/WritePost'
import axios from 'axios'
Vue.use(Router)

const router = new Router({
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
      component: board,
      beforeEnter: (to, from, next) => {
        checklogin(to, from, next)
      }
    },
    {
      path: '/write',
      name: 'write',
      component: write,
      beforeEnter: (to, from, next) => {
        checklogin(to, from, next)
      }
    }
  ]
})

function checklogin (to, from, next) {
  let findflag = null
  let cookieArr = document.cookie.split(';')
  for (let i in cookieArr) {
    if (cookieArr[i].split('=')[0].trim() === 'jwt-auth-token') {
      findflag = true
      axios.defaults.headers.common['jwt-auth-token'] = cookieArr[i].split('=')[1].trim()
      axios.post('http://localhost:9000/api/info')
        .then((res) => {
          next()
        })
        .catch((error) => {
          alert('로그인을 다시 해주세요.')
          console.log(error.message)
          next('/test')
        })
      break
    }
  }
  if (findflag === null) {
    alert('로그인을 해주세요')
    next('/test')
  }
}

export default router

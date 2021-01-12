// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
<<<<<<< HEAD:00. default/Vue/NoticeBoard-client/src/main.js
import axios from 'axios'

Vue.config.productionTip = false
Vue.prototype.$axios = axios
=======
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import VueCookies from 'vue-cookies'

Vue.use(BootstrapVue)
Vue.use(VueCookies)

Vue.config.productionTip = false
// 쿠키의 만료일은 7일이다. (글로벌 세팅)
Vue.$cookies.config('7d')
>>>>>>> origin/Seonghun:choseonghun/Vue/NoticeBoard-client/src/main.js

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

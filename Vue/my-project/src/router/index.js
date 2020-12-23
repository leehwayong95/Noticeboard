/* eslint-disable */
import Vue from "vue"
import Router from "vue-router"
import HelloWorld from "@/components/HelloWorld"
import Login from "@/components/Login.vue"
import Join from "@/components/Join.vue"
import Board from "@/components/Board.vue"
import BoardText from "@/components/BoardText"
import BoardNew from "@/components/BoardNew.vue"
import Boardupdate from "@/components/Boardupdate.vue"

Vue.use(Router)

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "HelloWorld",
      component: HelloWorld
    },
    {
      path: "/login",
      name: "Login",
      component: Login
    },
    {
      path: "/join",
      name: "Join",
      component: Join
    },
    {
      path: "/board",
      name: "Board",
      component: Board
    },
    {
      path: "/boardnew",
      name: "BoardNew",
      component: BoardNew
    },
    {
      path: "/boardupdate",
      name: "Boardupdate",
      component: Boardupdate
    },
    {
      path: "/boardtext",
      name: "BoardText",
      component: BoardText
    }
  ]
})

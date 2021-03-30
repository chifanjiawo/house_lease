import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import Login from '@/views/Login'
import Index from '@/views/Index'
import Register from '@/views/Register'
import { Header } from 'element-ui'
import MyHeader from '@/components/Header'
import searchList from '@/views/SearchList'
import desc from '@/views/Housedesc'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      // 首页
      path: '/index',
      name: 'Index',
      component: Index
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    // {
    //   path: '/header',
    //   name: 'Header',
    //   component: MyHeader
    // },
    {
      path: '/search',
      name: 'Search',
      component: searchList
    },
    {
      path: '/houseorder',
      name: 'desc',
      component: desc
    }

  ]
})

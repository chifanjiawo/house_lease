import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import Login from '@/views/Login'
import Index from '@/views/Index'
import Register from '@/views/Register'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // },
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
    }

  ]
})

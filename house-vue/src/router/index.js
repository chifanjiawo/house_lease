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
import userInfo from '@/views/userInfo'
import myOrderList from '@/components/OrderLsit'
import myStar from '@/components/myStar'
import personInfo from '@/components/personInfo'
import updatePass from '@/components/Updatepass'
import myComment from '@/components/myComment'

Vue.use(Router)

const router = new Router({
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
    },
    {
      path:'/userInfo',
      name:'userInfo',
      component:userInfo,
      children:[
        {
          path:"myOrderList",
          component:myOrderList
        },{
          path:'myStar',
          component:myStar
        },{
          path:'personInfo',
          component:personInfo
        },{
          path:'updatePass',
          component:updatePass
        },{
          path:'myComment',
          component:myComment
        }
      ]
    }

  ]
})
// 路由守卫
router.beforeEach((to,from,next) => {
 
  var name  =  sessionStorage.getItem('username');

  var isLogin =false;

  if(name!=null){
    isLogin = true;
  }
  var rex = /\/userInfo*/
   

  if(rex.test(to.path)){
    isLogin ? next() : next('/login');
  }else{
    next();
  }
}
)

export default router;

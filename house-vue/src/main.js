// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import moment from 'moment'

Vue.use(VueAxios,axios);

Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.use(VueRouter);
Vue.filter('dateFmt', (input, formatString = "yyyy-MM-DD hh:mm:ss") => {

      //es5函数参数设置默认值
  
      //const lastFormatString = formatString || ''
  
     
  
      // moment(input) 把时间字符串转成时间对象
  
      // format(formatString) 把时间对象，按照指定格式，格式化成符合条件的字符串
  
      return moment(input).format(formatString)
  
  })
  
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  // components: { App },
  // template: '<App/>'
  render: h => h(App)
})

import Vue from 'vue'
import App from './App.vue'
import Global from './Global'
import router from './router'
import store from './store'
import './assets/css/style.less'
import './assets/font/iconfont.css'
require('./Mock')
import {parseTime} from './utils'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
Vue.use(mavonEditor)

Vue.prototype.global = Global
Vue.config.productionTip = false
Vue.filter('parseTime', (v) => parseTime(v,'{y}-{m}-{d}'))
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

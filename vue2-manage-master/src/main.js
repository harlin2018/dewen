import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'

import './assets/iconfont/iconfont.css'

Vue.config.productionTip = false

Vue.use(ElementUI)

import {deepClone} from '@/utils'
import rules from '@/utils/rules'

Vue.prototype.deepClone = deepClone
Vue.prototype.rule = rules

new Vue({
	el: '#app',
	router,
	store,
	template: '<App/>',
	components: { App }
})

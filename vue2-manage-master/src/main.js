import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'

import './assets/iconfont/iconfont.css'

/*图片预览 */
import preview from 'vue-photo-preview'
import 'vue-photo-preview/dist/vue-photo-preview'
import 'vue-photo-preview/dist/skin.css'
Vue.use(preview)
/*图片预览 */

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

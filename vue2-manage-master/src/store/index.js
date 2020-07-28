import Vue from 'vue'
import Vuex from 'vuex'
import {getAdminInfo} from '@/api/getData'

Vue.use(Vuex)

let userInfo=localStorage.getItem('userInfo')
let userMenu=localStorage.getItem('userMenu')
if(userInfo) userInfo=JSON.parse(userInfo)
if(userMenu) userMenu=JSON.parse(userMenu)

const state = {
    adminInfo:userInfo,
    userMenu
}

const mutations = {
	saveAdminInfo(state, adminInfo){
        state.adminInfo = adminInfo.user
        state.userMenu = adminInfo.menus
        localStorage.setItem('userInfo',JSON.stringify(adminInfo.user))
        localStorage.setItem('userMenu',JSON.stringify(adminInfo.menus))
	},
	removeAdminInfo(state){
        state.adminInfo = {}
        state.userMenu = {}
        localStorage.removeItem('userInfo')
        localStorage.removeItem('userMenu')
    }
}

const actions = {
	async getAdminData({commit}){
		try{
			// const res = await getAdminInfo()
			// if (res.status == 1) {
			// 	commit('saveAdminInfo', res.data);
			// }else{
			// 	throw new Error(res.type)
			// }
		}catch(err){
			// console.log(err.message)
		}
	}
}

export default new Vuex.Store({
	state,
	actions,
	mutations,
})

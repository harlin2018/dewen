import Vue from 'vue'
import Vuex from 'vuex'
import {getAdminInfo} from '@/api/getData'

Vue.use(Vuex)

let userInfo=localStorage.getItem('userInfo')
if(userInfo) userInfo=JSON.parse(userInfo)

const state = {
	adminInfo:userInfo
}

const mutations = {
	saveAdminInfo(state, adminInfo){
        state.adminInfo = adminInfo;
        localStorage.setItem('userInfo',JSON.stringify(adminInfo))
	},
	removeAdminInfo(state){
        state.adminInfo = {};
        localStorage.removeItem('userInfo')
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

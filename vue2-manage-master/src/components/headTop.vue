<template>
    <div class="header_container">

		<el-breadcrumb separator="/">
			<el-breadcrumb-item :to="{ path: '/tableList' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item v-for="(item, index) in $route.meta" :key="index">{{item}}</el-breadcrumb-item>
		</el-breadcrumb>
		<el-dropdown @command="handleCommand" menu-align='start' style="margin-right:20px">
			<!-- <img :src="baseImgPath + adminInfo.avatar" class="avator"> -->
            <span class="el-dropdown-link">
                {{adminInfo.userName}}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
			<el-dropdown-menu slot="dropdown">
				<el-dropdown-item command="home">首页</el-dropdown-item>
				<el-dropdown-item command="signout">退出</el-dropdown-item>
			</el-dropdown-menu>
		</el-dropdown>
    </div>
</template>

<script>
	// import {signout} from '@/api/getData'
	import {baseImgPath} from '@/config/env'
	import {mapActions, mapState} from 'vuex'

    export default {
    	data(){
    		return {
    			baseImgPath,
    		}
    	},
    	created(){
    		// if (!this.adminInfo.id) {
    		// 	this.getAdminData()
    		// }
    	},
    	computed: {
    		...mapState(['adminInfo']),
    	},
		methods: {
			...mapActions(['getAdminData']),
			handleCommand(command) {
				if (command == 'home') {
					this.$router.push('/tableList');
				}else if(command == 'signout'){
                    this.$store.commit('removeAdminInfo')
					this.$router.push('/');
				}
			},
		}
    }
</script>

<style lang="less">
@import '../style/mixin';
.header_container{
    background-color: #EFF2F7;
    height: 60px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-left: 20px;
}
.avator{
    .wh(36px, 36px);
    border-radius: 50%;
    margin-right: 37px;
}
.el-dropdown-menu__item{
    text-align: center;
}
.el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
}
.el-icon-arrow-down {
    font-size: 12px;
}
</style>

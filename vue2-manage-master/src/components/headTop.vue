<template>
    <div class="header_container">

		<el-breadcrumb separator="/">
			<el-breadcrumb-item :to="{ path: '/tableList' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item v-for="(item, index) in $route.meta" :key="index">{{item}}</el-breadcrumb-item>
		</el-breadcrumb>
        <div>
            <el-badge :value="count" :max="99" :hidden="count==0" class="item" style="margin-right:20px;">
                <span @click="cleanUnread" class="notice_btn"><i class="el-icon-bell"></i></span>
            </el-badge>
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
    </div>
</template>

<script>
// import {signout} from '@/api/getData'
import {baseImgPath} from '@/config/env'
import {mapActions, mapState} from 'vuex'

import {getUnreadNum,cleanUnreadNum} from '@/api/common'

export default {
    data(){
        return {
            count:0,
            baseImgPath,
        }
    },
    created(){
        // if (!this.adminInfo.id) {
        // 	this.getAdminData()
        // }
        this.getUnreadNum()
        setTimeout(_=>{
            this.getUnreadNum()
        },1000*60)
    },
    computed: {
        ...mapState(['adminInfo']),
    },
    methods: {
        ...mapActions(['getAdminData']),
        handleCommand(command) {
            if (command == 'home') {
                this.$router.push('/');
            }else if(command == 'signout'){
                this.$store.commit('removeAdminInfo')
                this.$router.push('/login');
            }
        },
        getUnreadNum(){
            getUnreadNum().then(res=>{
                if(res.resultCode=='0'){
                    this.count=parseInt(res.payload)
                }
            })
        },
        cleanUnread(){
            cleanUnreadNum().then(res=>{
                if(res.resultCode=='0'){
                    this.count=0
                }
            })
            this.$router.push('/notifyCenter')
        }
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
.notice_btn{
    cursor: pointer;
    // margin-right: 10px;
    .el-icon-bell{
        font-size: 22px;
    }
}
</style>

<template>
  	<div class="login_page fillcontain">
	  	<transition name="form-fade" mode="in-out">
	  		<section class="form_contianer">
		  		<div class="manage_tip">
		  			<p>宜春经济环保管家</p>
		  		</div>
		    	<el-form :model="loginForm" :rules="rules" ref="loginForm">
					<el-form-item prop="loginName">
						<el-input v-model="loginForm.loginName" placeholder="用户名"><span>dsfsf</span></el-input>
					</el-form-item>
					<el-form-item prop="hashPassword">
						<el-input type="password" placeholder="密码" v-model="loginForm.hashPassword"></el-input>
					</el-form-item>
					<el-form-item>
				    	<el-button type="primary" @click="submitForm('loginForm')" >登陆</el-button>
				    	<el-button type="primary" @click="$router.push('/register')" >注册</el-button>
				  	</el-form-item>
				</el-form>
	  		</section>
	  	</transition>
  	</div>
</template>

<script>
import {logingetAdminInfo} from '@/api/getData'
import {login} from '@/api/common'

import {mapActions, mapState} from 'vuex'

export default {
    data(){
        return {
            loginForm: {
                loginName: '',
                hashPassword: '',
            },
            rules: {
                loginName: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                ],
                hashPassword: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ],
            }
        }
    },
    mounted(){
        if (!this.adminInfo.id) {
            this.getAdminData()
        }
    },
    computed: {
        ...mapState(['adminInfo']),
    },
    methods: {
        ...mapActions(['getAdminData']),
        submitForm(formName) {
            this.$refs[formName].validate(valid=>{
                if (valid) {
                    let params={
                        loginName: this.loginForm.loginName,
                        hashPassword: this.loginForm.hashPassword
                    }
                    login(params).then(res=>{
                        if (res.resultCode == 0) {
                            this.$message({
                                type: 'success',
                                message: '登录成功'
                            })
                            this.$store.commit('saveAdminInfo',res.payload)
                            this.$router.push('tableList')
                        }else{
                            this.$message({
                                type: 'error',
                                message: res.resultMsg
                            })
                        }
                    })
                }
            })
        }
    },
    watch: {
        // adminInfo: function (newValue){
        //     if (newValue.id) {
        //         this.$message({
        //             type: 'success',
        //             message: '检测到您之前登录过，将自动登录'
        //         });
        //         this.$router.push('tableList')
        //     }
        // }
    }
}
</script>

<style lang="less" scoped>
	@import '../style/mixin';
	.login_page{
		background-color: #324057;
	}
	.manage_tip{
		position: absolute;
		width: 100%;
		top: -100px;
		left: 0;
		p{
			font-size: 34px;
			color: #fff;
		}
	}
	.form_contianer{
		.wh(320px, 210px);
		.ctp(320px, 210px);
		padding: 25px;
		border-radius: 5px;
		text-align: center;
		background-color: #fff;
		.submit_btn{
			width: 100%;
			font-size: 16px;
		}
	}
	.tip{
		font-size: 12px;
		color: red;
	}
	.form-fade-enter-active, .form-fade-leave-active {
	  	transition: all 1s;
	}
	.form-fade-enter, .form-fade-leave-active {
	  	transform: translate3d(0, -50px, 0);
	  	opacity: 0;
	}
</style>

<template>
  	<div class="login_page fillcontain">
	  	<transition name="form-fade" mode="in-out">
	  		<section class="form_contianer">
		  		<div class="manage_tip">
		  			<p>宜春经济环保管家</p>
		  		</div>
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="70px" class="demo-ruleForm">
                    <el-form-item label="用户名" prop="userName">
                        <el-input v-model.number="ruleForm.userName" autocomplete="new-userName"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="hashPassword">
                        <el-input type="password" v-model="ruleForm.hashPassword" auto-complete="new-password"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="checkPass">
                        <el-input type="password" v-model="ruleForm.checkPass"></el-input>
                    </el-form-item>
                </el-form>
                <div>
                    <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </div>
	  		</section>
	  	</transition>
  	</div>
</template>

<script>

import {register} from '@/api/getData'

export default {
    data() {
        var validatePass = (rule, value, callback)=>{
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.ruleForm.checkPass !== '') {
                    this.$refs.ruleForm.validateField('checkPass');
                }
                callback();
            }
        };
        var validatePass2 = (rule, value, callback)=>{
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm.hashPassword) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            ruleForm: {
                userName:'',
                hashPassword:'',
                loginName:'',
                checkPass:'',
            },
            rules: {
                userName:[{ required: true, message: '请输入用户名', trigger: 'change' }],
                hashPassword: [{
                    validator: validatePass,
                    trigger: 'change'
                }],
                checkPass: [{
                    validator: validatePass2,
                    trigger: 'change'
                }],
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid)=>{
                if (valid) {
                    this.ruleForm.loginName=this.ruleForm.userName
                    register(this.ruleForm).then(res=>{
                        if(res.resultCode==0){
                            this.$router.push('/')
                        }
                    })
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
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
		.wh(320px, 230px);
		.ctp(320px, 230px);
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

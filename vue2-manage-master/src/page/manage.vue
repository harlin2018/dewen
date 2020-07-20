<template>
	<div class="manage_page fillcontain">
		<el-row style="height: 100%;">
	  		<el-col :span="4"  style="min-height: 100%; background-color: #324057;">
				<el-menu :default-active="defaultActive" style="min-height: 100%;" theme="dark" router>
					<!-- <el-menu-item index="manage"><i class="el-icon-menu"></i>首页</el-menu-item> -->
					<el-menu-item index="tableList"><i class="el-icon-menu"></i>首页</el-menu-item>
					<el-menu-item index="userManager"><i class="el-icon-s-custom"></i>用户管理</el-menu-item>
					<el-menu-item index="userAuth"><i class="el-icon-s-custom"></i>权限管理</el-menu-item>
					<el-menu-item index="userRole"><i class="el-icon-s-custom"></i>角色管理</el-menu-item>
					<!-- <el-menu-item index="setting"><i class="el-icon-s-tools"></i>系统设置</el-menu-item> -->
					<!-- <el-submenu index="2">
						<template slot="title"><i class="el-icon-document"></i>数据管理</template>
						<el-menu-item index="adminSet">管理员设置</el-menu-item>
					</el-submenu>
					<el-submenu index="3">
						<template slot="title"><i class="el-icon-setting"></i>设置</template>

						<el-menu-item index="userList">用户列表</el-menu-item>
						<el-menu-item index="sendMessage">发送通知</el-menu-item>
					</el-submenu> -->
				</el-menu>
			</el-col>
			<el-col :span="20" style="height: 100%;overflow: auto;">
				<!-- <keep-alive> -->
				    <router-view></router-view>
				<!-- </keep-alive> -->
			</el-col>
		</el-row>
  	</div>
</template>

<script>
    export default {
		computed: {
			defaultActive: function(){
				return this.$route.path.replace('/', '');
			}
		},
    }
</script>


<style lang="less" scoped>
	@import '../style/mixin';
	.manage_page{

    }
    .el-menu{
        background: none;
    }
    .el-menu-item,.el-menu-item i{
        color: #fff;
    }
    .el-menu-item.is-active {
        color: #409EFF;
    }
    .el-menu-item:focus, .el-menu-item:hover{
        background: #001528;
    }
</style>

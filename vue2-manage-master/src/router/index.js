import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const login = r => require.ensure([], () => r(require('@/page/login')), 'login');
const register = r => require.ensure([], () => r(require('@/page/register')), 'register');

const manage = r => require.ensure([], () => r(require('@/page/manage')), 'manage');

const userManager = r => require.ensure([], () => r(require('@/page/userManager')), 'userManager');
const userAuth = r => require.ensure([], () => r(require('@/page/userAuth')), 'userAuth');
const userRole = r => require.ensure([], () => r(require('@/page/userRole')), 'userRole');
const tableList = r => require.ensure([], () => r(require('@/page/tableList')), 'tableList');
const tableForm = r => require.ensure([], () => r(require('@/page/tableForm/tableForm')), 'tableForm');


const routes = [
	{
		path: '/',
		component: login
	},
	{
		path: '/register',
		component: register
	},
	{
		path: '/manage',
		component: manage,
		name: '',
		children: [
            {
                path: '/tableList',
                component: tableList,
                name: ['列表'],
            },
            {
                path: '/tableForm',
                component: tableForm,
                name: ['添加'],
            },
            {
                path: '/userManager',
                component: userManager,
                name: ['用户管理'],
            },
            {
                path: '/userAuth',
                component: userAuth,
                name: ['权限管理'],
            },
            {
                path: '/userRole',
                component: userRole,
                name: ['角色管理'],
            }
        ]
	}
]

export default new Router({
	routes,
	strict: process.env.NODE_ENV !== 'production',
})

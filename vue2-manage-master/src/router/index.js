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
		redirect: '/tableList'
	},
	{
		path: '/login',
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
                name: 'tableList',
            },
            {
                path: '/tableForm',
                component: tableForm,
                name: 'tableForm',
            },
            {
                path: '/userManager',
                component: userManager,
                name: 'userManager',
            },
            {
                path: '/userAuth',
                component: userAuth,
                name: 'userAuth',
            },
            {
                path: '/userRole',
                component: userRole,
                name: 'userRole',
            }
        ]
	}
]

export default new Router({
	routes,
	strict: process.env.NODE_ENV !== 'production',
})

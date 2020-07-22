import axios from 'axios'

import {Message, MessageBox} from 'element-ui'

import {
	baseUrl
} from './env'

const modules = [{
	name: 'common',
	url: baseUrl
}]

Object.keys(modules).forEach(module => {
    module = modules[module]

    const service = axios.create({
        baseURL: module.url,
        withCredentials: true,
        timeout: 15000
    })

    modules[module.name] = service

    service.interceptors.request.use(
        config => {

            config.params = Object.assign({}, config.params)
            // 分页参数param, 查询参数data
            if (config.url.endsWith('/list')) {
                const params = {
                    pageSize: config.params.limit,
                    pageNumber: config.params.page,
                    sorts: config.params.sort,
                }
                delete config.params.limit
                delete config.params.page
                delete config.params.sort

                let data = JSON.parse(JSON.stringify(config.params))
                config.params = params

                // 过滤data中值是空的参数
                if (Object.keys(data)
                    .length > 0) {
                    for (var key in data) {
                        if (data[key] === '') {
                            delete data[key]
                        }
                    }
                }
                if (Object.keys(data).length === 0) data = null
                config.data = data
            }

            config.headers['Content-Type'] = 'application/json;charset=UTF-8'

            return config
        },
        error => {
            return Promise.reject(error)
        }
    )

    // response interceptor
    service.interceptors.response.use(
        response => {
            let res=response.data
            if(res.resultCode!='0'){
                Message({
                    message: res.resultMsg || 'Error',
                    type: 'error',
                })

            }
            return res
        },
        error => {
            Message({
                message: res.resultMsg || 'Error',
                type: 'error',
            })
            return Promise.reject(error)
        }
    )
})

function API(module) {
	return typeof module === 'string' ? modules[module] : modules['common'](module)
}

export default API

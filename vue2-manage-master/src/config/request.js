import axios from 'axios'
import { baseUrl } from './env'

const modules = [
{
  name: 'common',
  url: baseUrl
}
]

Object.keys(modules).forEach(module => {
  module = modules[module]
  // create an axios instance

  const service = axios.create({
    baseURL: module.url, // url = base url + request url
    withCredentials: true, // send cookies when cross-domain requests
    timeout: 15000 // request timeout
  })

  modules[module.name] = service

  // request interceptor
  service.interceptors.request.use(
    config => {
      // do something before request is sent
      // 替换url路径参数
      if (config.params && config.url.indexOf(':id') > -1) {
        config.url = config.url.replace(':id', config.params.id)
      } else if (config.data && config.url.indexOf(':data.id') > -1) {
        config.url = config.url.replace(':data.id', config.data.id)
      }
      // console.log(config.params)
      config.params = Object.assign({}, config.params)
      // 分页参数param, 查询参数data
      if (config.url.endsWith('/list')){
        const params = {
          pageSize: config.params.limit,
          pageNumber: config.params.page,
          sorts: config.params.sort,
        }
        delete config.params.limit
        delete config.params.page
        delete config.params.sort
        // config.data = config.params;
        let data = JSON.parse(JSON.stringify(config.params))
        config.params = params
        // 过滤data中值是空的参数
        if (Object.keys(data).length > 0) {
          for (var key in data) {
            if (data[key] === '') {
              delete data[key]
            }
          }
        }
        if (Object.keys(data).length === 0) {
          data = null
        }
        config.data = data
      }

      let contentType = 'application/json;charset=UTF-8'
      config.headers['Content-Type']=contentType
      return config
    },
    error => {
      return Promise.reject(error)
    }
  )

  // response interceptor
  service.interceptors.response.use(

    response => {
        return response.data

    },
    error => {
      return Promise.reject(error)
    }
  )
})

function API(module) {
  return typeof module === 'string' ? modules[module] : modules['common'](module)
}

export default API

import axios from 'axios'

const baseUrl = 'http://172.16.136.162:8888/project'

const modules = [
    {
        name: 'common',
        url: baseUrl
    },
]

Object.keys(modules).forEach(module => {
  module = modules[module]
  const service = axios.create({
    baseURL: module.url, // url = base url + request url
    withCredentials: true, // send cookies when cross-domain requests
    timeout: 15000 // request timeout
  })

  modules[module.name] = service

  // request interceptor
  service.interceptors.request.use(
    config => {
        if (config.params && config.url.indexOf(':id') > -1) {
            config.url = config.url.replace(':id', config.params.id)
        } else if (config.data && config.url.indexOf(':data.id') > -1) {
            config.url = config.url.replace(':data.id', config.data.id)
        }
      config.params = Object.assign({}, config.params)

      // 分页参数param, 查询参数data
      if (config.url&&config.url.endsWith('/list') && JSON.stringify(config.params)!='{}') {
        const params = {
          pageSize: config.params.limit,
          pageNumber: config.params.page,
          sorts: config.params.sort,
          searchKey: config.params.searchKey
        }
        delete config.params.limit
        delete config.params.page
        delete config.params.sort
        delete config.params.pagination
        delete config.params.searchKey
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
      config.headers['Content-Type']='application/json;charset=UTF-8'
      return config
    },
    error => {
      // do something with request error
      console.log(error) // for debug
      return Promise.reject(error)
    }
  )

  // response interceptor
  service.interceptors.response.use(
    response => {
      const res = Object.assign(response.data, { message: response.data.resultMsg })
      if (res.resultCode !== '0') {
        return Promise.reject(new Error(res.message || 'Error'))
      } else {
        res.data = res.payload
        if (res.data && res.data.content && res.data.totalElements) {
          res.data.items = res.data.content
          res.data.total = res.data.totalElements
        } else if (res.data && Array.isArray(res.data.content) && res.data.totalElements === 0) {
          res.data.items = res.data.content
          res.data.total = 0
        }
        return res
      }
    },
    error => {
        return Promise.reject(new Error(error))
    }
  )
})

function API() {
    return typeof module === 'string' ? modules[module] : modules['common'](module)
}

export default API

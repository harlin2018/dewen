import service from '@/config/request'
const request = service('common')

export function login(data) {   //登录
    return request({
        url: '/commonUser/login',
        method: 'post',
        data
    })
}


export function getCompanyList(params) {    //公司列表
    return request({
        url: '/companyInfo/list',
        method: 'post',
        params
    })
}
export function getCompanyData(id) {    //公司详情
    return request({
        url: '/companyInfo/'+id,
        method: 'get'
    })
}
export function createCompanyData(data) {    //创建公司
    return request({
        url: '/companyInfo/add',
        method: 'post',
        data
    })
}
export function updateCompanyData(data) {    //更新公司
    return request({
        url: '/companyInfo/update/'+data.id,
        method: 'put',
        data
    })
}
export function deleteCompanyData(data) {    //删除公司
    return request({
        url: '/companyInfo/delete/'+data.id,
        method: 'delete'
    })
}

export function getCateHistory() {    //分类历史列表
    return request({
        url: '/companyInfo/record',
        method: 'get'
    })
}



export function getUserList(params) {   //用户列表
    return request({
        url: '/commonUser/list',
        method: 'post',
        params
    })
}
export function approveUser(data) {   //用户审批
    return request({
        url: '/commonUser/approvalUser',
        method: 'post',
        data
    })
}

export function uploadFile(data) {  //上传
    return request({
        url: '/file/upload',
        method: 'post',
        data
    })
}

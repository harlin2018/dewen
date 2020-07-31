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


export function exportList(data) {   //导出
    return request({
        url: '/companyInfo/download/exportExcel',
        method: 'post',
        data
    })
}

export function exportDownload(param) {   //导出
    return request({
        url: '/companyInfo/download?fileName='+param,
        method: 'get'
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

export function getAuthList(params) {    //权限列表
    return request({
        url: '/commonRight/listAll',
        method: 'post',
        params
    })
}
export function createAuth(data) {    //添加权限
    return request({
        url: '/commonRight/add',
        method: 'post',
        data
    })
}
export function updateAuth(data) {    //修改权限
    return request({
        url: '/commonRight/update/'+data.id,
        method: 'put',
        data
    })
}
export function deleteAuth(data) {    //删除权限
    return request({
        url: '/commonRight/delete/'+data.id,
        method: 'delete',
        data
    })
}


export function getRoleList(params) {    //角色列表
    return request({
        url: '/commonRole/list',
        method: 'post',
        params
    })
}
export function createRole(data) {    //添加角色
    return request({
        url: '/commonRole/add',
        method: 'post',
        data
    })
}
export function updateRole(data) {    //修改角色
    return request({
        url: '/commonRole/update/'+data.id,
        method: 'put',
        data
    })
}
export function deleteRole(data) {    //删除角色
    return request({
        url: '/commonRole/delete/'+data.id,
        method: 'delete',
        data
    })
}


export function assignRole(data) {    //用户关联角色
    return request({
        url: '/commonUser/assignRole',
        method: 'post',
        data
    })
}

export function getUserRoleList(params) {    //角色列表
    return request({
        url: '/commonUserRoleRelationship/list',
        method: 'post',
        params
    })
}



export function getRoleAuthList(data) {    //角色关联权限
    return request({
        url: '/commonRight/listByRole',
        method: 'post',
        data
    })
}
export function submitAuthRole(data) {    //提交角色关联权限数据
    return request({
        url: '/commonRight/grant',
        method: 'post',
        data
    })
}


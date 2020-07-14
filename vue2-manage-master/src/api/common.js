import service from '@/utils/request'
const request = service('common')


export function login(data) {
    return request({
        url: '/commonUser/login',
        method: 'post',
        data
    })
}

export function fetchStmIssue(id) {
    return request({
        url: '/cmdb/stm/stmIssue/:id',
        method: 'get',
        params: { id }
    })
}

export function createStmIssue(data) {

  return request({
    url: '/cmdb/stm/stmIssue/add',
    method: 'post',
    data
  })
}

export function updateStmIssue(data) {
  return request({
    url: '/cmdb/stm/stmIssue/update/:data.id',
    method: 'put',
    data
  })
}

export function deleteStmIssue(data) {
  return request({
    url: '/cmdb/stm/stmIssue/delete/:data.id',
    method: 'delete',
    data
  })
}


export function getAssetsDetail(data) {
  return request({
    url: '/cmdb/stm/stmIssue/getAssetsDetail',
    method: 'post',
    data
  })
}

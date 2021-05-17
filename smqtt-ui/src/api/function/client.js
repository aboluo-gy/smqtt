import request from '@/utils/request'
import store from '@/store'
// 查询客户端列表
export function listClient(query) {
  return request({
    url: '/function/client/list',
    method: 'get',
    params: query
  })
}

// 查询客户端详细
export function getClient(id) {
  return request({
    url: '/function/client/' + id,
    method: 'get'
  })
}

// 新增客户端
export function addClient(data) {
  console.log(store.state.user.idempotentToken, '')
  return request({
    headers: {
      "Content-Type": "application/json",
      "idempotentToken": store.state.user.idempotentToken
    },
    url: '/function/client',
    method: 'post',
    data: data
  })
}

//去新增客户端
export function goAddClient() {
  return request({
    url: '/function/client/toAdd',
    method: 'get'
  })
}
// 修改客户端
export function updateClient(data) {
  return request({
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    url: '/function/client',
    method: 'put',
    params: data
  })
}

// 删除客户端
export function delClient(id) {
  return request({
    url: '/function/client/' + id,
    method: 'delete'
  })
}

//断开连接 function:client:disconnects
export function disconnectsClient(query) {
  return request({
    url: '/function/client/disconnect',
    method: 'get',
    params: query
  })
}

// 查询客户端详细
export function getClientShowTopics(clientId) {
  return request({
    url: `/function/client/showTopics/${clientId}`,
    method: 'get'
  })
}

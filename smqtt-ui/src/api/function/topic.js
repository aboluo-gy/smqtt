import request from '@/utils/request'

// 查询客户端主题列表
export function listTopic(query) {
  return request({
    url: '/function/topic/list',
    method: 'get',
    params: query
  })
}

// 查询客户端主题详细
export function getTopic(id) {
  return request({
    url: '/function/topic/' + id,
    method: 'get'
  })
}

// 新增客户端主题
export function addTopic(data) {
  return request({
    url: '/function/topic',
    method: 'post',
    data: data
  })
}

// 修改客户端主题
export function updateTopic(data) {
  return request({
    url: '/function/topic',
    method: 'put',
    data: data
  })
}

// 删除客户端主题
export function delTopic(id) {
  return request({
    url: '/function/topic/' + id,
    method: 'delete'
  })
}

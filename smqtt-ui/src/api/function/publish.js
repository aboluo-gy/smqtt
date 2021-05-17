import request from '@/utils/request'

// 查询发布消息列表
export function listPublish(query) {
  return request({
    url: '/function/publish/list',
    method: 'get',
    params: query
  })
}

// 查询发布消息详细
export function getPublish(query) {
  return request({
    url: '/function/publish/getToPayload',
    method: 'get',
    params: query
  })
}

// // 新增发布消息
// export function addPublish(data) {
//   return request({
//     url: '/function/publish',
//     method: 'post',
//     data: data
//   })
// }

// // 修改发布消息
// export function updatePublish(data) {
//   return request({
//     url: '/function/publish',
//     method: 'put',
//     data: data
//   })
// }

// 删除发布消息
export function delPublish(id) {
  return request({
    url: '/function/publish/' + id,
    method: 'delete'
  })
}

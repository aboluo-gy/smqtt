import request from '@/utils/request'

// 日志记录列表查询
export function logMqttTopiclist(query) {
  return request({
    url: '/function/logMqttTopic/list',
    method: 'get',
    params: query
  })
}
/*//查看发布内容
export function getToPayload(query) {
  return request({
    url: '/function/publish/getToPayload',
    method: 'get',
    params: query
  })
}*/
//删除日志
export function deleteLog(id) {
  return request({
    url: '/function/logMqttTopic/'+ id,
    method: 'delete'
  })
}

//文件列表
export function appFilelist(query) {
  return request({
    url: '/function/minioFile/list',
    method: 'get',
    params: query
  })
}
//删除文件
export function deleteList(id) {
  return request({
    url: '/function/minioFile/' + id,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询子设备列表
export function listDeviceInfo(query) {
  return request({
    url: '/function/deviceInfo/list',
    method: 'get',
    params: query
  })
}

// 查询子设备详细
export function getDeviceInfo(id) {
  return request({
    url: '/function/deviceInfo/' + id,
    method: 'get'
  })
}

// 新增子设备
export function addDeviceInfo(data) {
  return request({
    url: '/function/deviceInfo',
    method: 'post',
    data: data
  })
}

// 修改子设备
export function updateDeviceInfo(data) {
  return request({
    url: '/function/deviceInfo',
    method: 'put',
    data: data
  })
}

// 删除子设备
export function delDeviceInfo(id) {
  return request({
    url: '/function/deviceInfo/' + id,
    method: 'delete'
  })
}
//查看上报数据
export function getDatalistDeviceInfo(query) {
  return request({
    url: '/function/deviceInfo/getData',
    method: 'get',
    params: query
  })
}
//上报数据解析
export function getDataDetailsDeviceInfo(query) {
  return request({
    url: '/function/deviceInfo/getDataDetails',
    method: 'get',
    params: query
  })
}
// 删除上报数据
export function delDeviceInfodata(id) {
  return request({
    url: '/function/deviceInfo/data/' + id,
    method: 'delete'
  })
}
//数据下发
export function getDistributedData(query) {
  return request({
    url: '/function/deviceInfo/dataReleased',
    method: 'get',
    params: query
  })
}
//数据下发--下发
export function dataCommand(data) {
  return request({
    url: '/function/topic/dataCommand',
    method: 'post',
    data: data
  })
}
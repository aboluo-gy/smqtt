import request from '@/utils/request'

// 实时日志列表查询
export function listLogRecord(query) {
    return request({
        url: '/Physical/findPhysicalModelList',
        method: 'get',
        params: query
    })
}

// 查看服务
export function getLogRecord(query) {
    return request({
        url: '/Physical/findServices',
        method: 'get',
        params: query
    })
}

//添加实时日志数据
export function addLogRecord(data) {
    return request({
        url: '/Physical/addPhysical',
        method: 'post',
        data: data
    })
}

// 删除实时日志
export function delLogRecord(query) {
    return request({
        url: '/Physical/deleteLogRecord' + id,
        method: 'delete',
        params: query
    })
}

import request from '@/utils/request'

// 物模型列表查询
export function listPhysicalModel(query) {
    return request({
        url: '/function/physicalModel/list',
        method: 'get',
        params: query
    })
}

// 查看服务
export function getPhysicalModel(query) {
    return request({
        url: '/function/physicalModel/' + query,
        method: 'get',
        // params: query
    })
}

//快捷生成
export function addPhysicalModel(data) {
    return request({
        url: '/function/physicalModel',
        method: 'post',
        data: data
    })
}

// 删除物模型
export function delPhysicalModel(query) {
    return request({
        url: '/function/physicalModel/' + query.id,
        method: 'delete',
        // data: query
    })
}

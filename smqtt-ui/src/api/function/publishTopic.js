import request from '@/utils/request'
import store from '@/store'
// 发布命令主题跟标识查询
export function listPublishTopic(query) {
    return request({
        url: '/function/topic/addCommandShow',
        method: 'get',
        params: query
    })
}


//添加主题
export function addPublishTopic(data) {
    return request({
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        url: '/function/topic',
        method: 'post',
        params: data
    })
}


//发送主题
export function postData(data) {
    return request({
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            "idempotentToken": store.state.user.idempotentToken
        },
        url: '/function/topic/postData',
        method: 'post',
        params: data
    })
}

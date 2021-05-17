import request from '@/utils/request'
import store from '@/store'
// 快捷命令列表查询
export function listQuickcommond(query) {
    return request({
        url: '/function/appIssueOrder/list',
        method: 'get',
        params: query
    })
}
// 请求QOS标识/协议类型
export function dataTypeQuickcommond(query) {
    return request({
        url: '/system/dict/data/type/' + query,
        method: 'get'
    })
}

// 主题类型
export function getAppDictDataList(query) {
    return request({
        url: '/system/SysAppDictData/getAppDictDataList',
        method: 'get',
        params: query
    })
}
// 查看下发内容服务
export function getQuickcommond(query) {
    return request({
        url: '/function/appIssueOrder/' + query,
        method: 'get',
        // params: query
    })
}

//  一键下发页面
export function getAddQuickcommond() {
    return request({
        url: '/function/appIssueOrder/toAddQuick',
        method: 'get',
    })
}
//  获取指令下发信息
export function jumpQuickcommond(data) {
    return request({
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        url: '/function/appIssueOrder/jumpQuick',
        method: 'get',
        params: data
    })
}
//保存下发命令
export function saveQuickcommond(data) {
    console.log(store.state.user.idempotentToken, '')
    return request({
        headers: {
            "Content-Type": "application/json",
            "idempotentToken": store.state.user.idempotentToken
        },
        url: '/function/appIssueOrder',
        method: 'post',
        data: data
    })
}

// 删除快捷命令
export function delQuickcommond(query) {
    return request({
        url: '/function/appIssueOrder/' + query,
        method: 'delete',
        // params: query
    })
}

// 重新发送快捷命令
export function resendQuickcommond(data) {

    return request({
        headers: {
            "Content-Type": "application/json",
        },
        url: '/function/appIssueOrder/resend',
        method: 'post',
        params: { id: data }
    })
}
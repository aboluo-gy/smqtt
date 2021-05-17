import request from '@/utils/request'
import store from '@/store'
// 产品管理列表查询
export function listProduct(query) {
    return request({
        url: '/function/product/list',
        method: 'get',
        params: query
    })
}

// 查看服务
export function getProduct(query) {
    return request({
        url: '/function/product/' + query,
        method: 'get'
    })
}

//快捷生成产品管理数据
export function addProduct(data) {
    return request({
        headers: {
            "Content-Type": "application/json",
            "idempotentToken": store.state.user.idempotentToken
        },
        url: '/function/product',
        method: 'post',
        data: data
    })
}

// 删除产品管理
export function delProduct(query) {
    return request({
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        url: '/function/product/' + query,
        method: 'delete'
    })
}
//获取下拉字典
export function getselectProduct(query) {
    return request({
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        url: "/function/product/productFast",
        method: "get",
    })
}

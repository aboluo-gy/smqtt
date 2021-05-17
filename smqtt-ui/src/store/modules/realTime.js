// 生成store对象
const state = {
    // 接口token
    token: '',
    // 登录状态
    isLogin: false,
    // 用户信息
    userInfo: {},
    // 用户菜单
    userMenu: {},
    // 历史菜单
    historyMenu: [],
    // 登录时间
    loginTime: '',
    //实时日志的内容
    socketContent: '',
    twosocketContent: '',
    accountList: [],
    //实时日志筛选出来的数据
    arrlists: [],
    twoarrlist: []
}
// 修改全局状态方法
const mutations = {
    SocketContent(state, content) {
        state.socketContent = content;
    },
    twosetSocketContent(state, content) {
        state.twosocketContent = content;
    },
}

export default {
    namespaced: true,
    state,
    mutations
}

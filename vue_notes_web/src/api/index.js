import request from '@/utils/request'

/**
 * 获取笔记列表（可模糊查询）
 * @param params
 * @returns {AxiosPromise}
 */
export function fetchList(params) {
    return request({
        url: '/test/note/search',
        method: 'get',
        params: params
    })
}

/**
 * 根据id获取笔记详细信息
 * @param params
 * @returns {AxiosPromise}
 */
export function fetchContent(params) {
    return request({
        url: '/test/content/getNoteById',
        method: 'get',
        params: params
    })
}

/**
 * 登录
 * @param params
 * @returns {AxiosPromise}
 */
export function login(params) {
    return request({
        url: '/test/auth/login',
        method: 'post',
        params: params
    })
}

/**
 * 修改密码
 * @param params
 * @returns {AxiosPromise}
 */
export function updateUserPwd(params) {
    return request({
        url: '/test/user/updateUserPwd',
        method: 'post',
        data: params
    })
}

/**
 * 注册
 * @param params
 * @returns {AxiosPromise}
 */
export function register(params) {
    return request({
        url: '/test/auth/register',
        method: 'post',
        params: params
    })
}

/**
 * 登出
 * @param params
 * @returns {AxiosPromise}
 */
export function logout(params) {
    return request({
        url: '/test/auth/logout',
        method: 'post',
        params: params
    })
}

/**
 * 添加笔记
 * @param params
 * @returns {AxiosPromise}
 */
export function addNote(params) {
    return request({
        url: '/test/note/add',
        method: 'post',
        data: params
    })
}

/**
 * 获取分类
 * @param params
 * @returns {AxiosPromise}
 */
export function getNoteSort(params) {
    return request({
        url: '/test/noteSort/getList',
        method: 'get',
        params: params
    })
}

/**
 * 获取标签
 * @param params
 * @returns {AxiosPromise}
 */
export function getTag(params) {
    return request({
        url: '/test/tag/getList',
        method: 'get',
        params: params
    })
}

/**
 * 根据id获取笔记列表
 * @param params
 * @returns {AxiosPromise}
 */
export function getNotesById(params) {
    return request({
        url: '/test/note/getNoteByUserId',
        method: 'get',
        params: params
    })
}

/**
 * 通过token获取信息
 * @param params
 * @returns {Promise<AxiosResponse<any>>}
 */
export function authVerify (params) {
    return request.get('test/user/verify/' + params, {})
}

/**
 * 通过id获取用户信息
 * @param params
 * @returns {Promise<AxiosResponse<any>>}
 */
export function getUserById (params) {
    return request.get('test/user/getUserById/' + params, {})
}

/**
 * 通过id修改用户信息
 * @param params
 * @returns {AxiosPromise}
 */
export function editUser(params) {
    return request({
        url: 'test/user/edit',
        method: 'post',
        data: params
    })
}

/**
 * 上传头像
 * @param params
 * @returns {AxiosPromise}
 */
export function uploadAvatar(params) {
    return request({
        url: 'test/fileUpload/uploadAvatar',
        method: 'post',
        data: params
    })
}

export function fetchFocus() {
    return request({
        url: '/focus/list',
        method: 'get',
        params: {}
    })
}

export function fetchCategory() {
    return request({
        url: '/category',
        method: 'get',
        params: {}
    })
}

export function fetchFriend() {
    return request({
        url: '/friend',
        method: 'get',
        params: {}
    })
}

export function fetchSocial() {
    return request({
        url: '/social',
        method: 'get',
        params: {}
    });
}

export function fetchSiteInfo() {
    return request({
        url: '/site',
        method: 'get',
        params: {}
    })
}

export function fetchComment() {
    return request({
        url: '/comment',
        method: 'get',
        params: {}
    })
}

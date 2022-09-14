import request from '@/utils/request'

/**
 * 获取笔记列表（可模糊查询）
 * @param params
 * @returns {AxiosPromise}
 */
export function fetchList(params) {
    return request({
        url: '/note/search',
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
        url: '/content/getNoteById',
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
        url: '/auth/login',
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
        url: '/user/updateUserPwd',
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
        url: '/auth/register',
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
        url: '/auth/logout',
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
        url: '/note/add',
        method: 'post',
        data: params
    })
}

/**
 * 修改笔记
 * @param params
 * @returns {AxiosPromise}
 */
export function updateNote(params) {
    return request({
        url: '/note/update',
        method: 'post',
        data: params
    })
}

/**
 * 删除笔记
 * @param params
 * @returns {AxiosPromise}
 */
export function deleteNote(params) {
    return request({
        url: '/note/delete',
        method: 'post',
        params: params,
        handler: {ContentType: 'application/json'}
    })
}

/**
 * 获取分类
 * @param params
 * @returns {AxiosPromise}
 */
export function getNoteSort(params) {
    return request({
        url: '/noteSort/getList',
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
        url: '/tag/getList',
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
        url: '/note/getNoteByUserId',
        method: 'get',
        params: params
    })
}

/**
 * 根据用户id获取收藏的笔记
 * @param params
 * @returns {AxiosPromise}
 */
export function getCollectNoteByUserId(params) {
    return request({
        url: '/note/getCollectNoteByUserId',
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
    return request.get('/user/verify/' + params, {})
}

/**
 * 通过id获取用户信息
 * @param params
 * @returns {Promise<AxiosResponse<any>>}
 */
export function getUserById (params) {
    return request.get('/user/getUserById/' + params, {})
}

/**
 * 通过id修改用户信息
 * @param params
 * @returns {AxiosPromise}
 */
export function editUser(params) {
    return request({
        url: '/user/edit',
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
        url: '/fileUpload/uploadAvatar',
        method: 'post',
        data: params
    })
}

/**
 * 获取用户是否收藏笔记
 * @param params
 * @returns {AxiosPromise}
 */
export function getUserCollectNote(params) {
    return request({
        url: '/collect/getUserCollectNote',
        method: 'get',
        params: params
    })
}

/**
 * 用户收藏笔记
 * @param params
 * @returns {AxiosPromise}
 */
export function userCollectNote(params) {
    return request({
        url: '/collect/userCollectNote',
        method: 'get',
        params: params
    })
}

/**
 * 条件查询（分类、标签）
 * @param params
 * @returns {AxiosPromise}
 */
export function getNoteList(params) {
    return request({
        url: '/note/getNoteList',
        method: 'get',
        params: params
    })
}

/**
 * 添加留言
 * @param params
 * @returns {AxiosPromise}
 */
export function addMessage(params) {
    return request({
        url: '/message/addMessage',
        method: 'post',
        params: params
    })
}

/**
 * 获取笔记归档的月份
 * @param params
 * @returns {AxiosPromise}
 */
export function getNoteByTime (params) {
    return request({
        url: '/sort/getSortList',
        method: 'get',
        params: params
    })
}

/**
 * 通过月份获取笔记
 * @param params
 * @returns {AxiosPromise}
 */
export function getArticleByMonth (params) {
    return request({
        url: '/sort/getArticleByMonth',
        method: 'get',
        params: params
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

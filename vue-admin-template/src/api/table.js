import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/vue-admin-template/table/list',
    method: 'get',
    params
  })
}

export function getUserList(params) {
  return request({
    url: '/user/getList',
    method: 'get',
    params: params
  })
}

export function getNoteList(params) {
  return request({
    url: '/note/search',
    method: 'get',
    params: params
  })
}

export function getSortList(params) {
  return request({
    url: '/noteSort/getSortList',
    method: 'get',
    params: params
  })
}

export function getTagList(params) {
  return request({
    url: '/tag/getTagList',
    method: 'get',
    params: params
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
 * 删除用户
 * @param params
 * @returns {AxiosPromise}
 */
export function deleteUser(params) {
  return request({
    url: '/user/delete',
    method: 'post',
    params: params,
    handler: {ContentType: 'application/json'}
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
 * 删除分类
 * @param params
 * @returns {AxiosPromise}
 */
export function deleteSort(params) {
  return request({
    url: '/noteSort/delete',
    method: 'post',
    params: params,
    handler: {ContentType: 'application/json'}
  })
}

/**
 * 删除标签
 * @param params
 * @returns {AxiosPromise}
 */
export function deleteTag(params) {
  return request({
    url: '/tag/delete',
    method: 'post',
    params: params,
    handler: {ContentType: 'application/json'}
  })
}

/**
 * 添加黑名单
 * @param params
 * @returns {AxiosPromise}
 */
export function addBlacklist(params) {
  return request({
    url: '/user/addBlacklist',
    method: 'post',
    params: params,
    handler: {ContentType: 'application/json'}
  })
}

/**
 * 移除黑名单
 * @param params
 * @returns {AxiosPromise}
 */
export function deleteBlacklist(params) {
  return request({
    url: '/user/deleteBlacklist',
    method: 'post',
    params: params,
    handler: {ContentType: 'application/json'}
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
    params: params
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
    params: params
  })
}

/**
 * 添加用户
 * @param params
 * @returns {AxiosPromise}
 */
export function addUser(params) {
  return request({
    url: '/user/addition',
    method: 'post',
    params: params
  })
}

/**
 * 通过id修改用户信息
 * @param params
 * @returns {AxiosPromise}
 */
export function editUser(params) {
  return request({
    url: '/user/update',
    method: 'post',
    params: params,
  })
}

/**
 * 查询用户反馈信息列表
 * @param params
 * @returns {AxiosPromise}
 */
export function getMessageList(params) {
  return request({
    url: '/message/getMessageList',
    method: 'get',
    params: params,
  })
}

export function getPictureList(params) {
  return request({
    url: '/picture/getPictureList',
    method: 'get',
    params: params,
  })
}

export function deleteImage(params) {
  return request({
    url: '/picture/deleteImage',
    method: 'post',
    params: params,
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
 * 修改留言反馈
 * @param params
 * @returns {AxiosPromise}
 */
export function editMessage(params) {
  return request({
    url: '/message/editMessage',
    method: 'post',
    params: params,
  })
}

/**
 * 完成用户反馈的处理
 * @param params
 * @returns {AxiosPromise}
 */
export function completion(id) {
  return request({
    url: `/message/completion/${id}`,
    method: 'post',
  })
}


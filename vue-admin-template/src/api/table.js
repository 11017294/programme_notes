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
 * 添加用户
 * @param params
 * @returns {AxiosPromise}
 */
export function addUser(params) {
  return request({
    url: '/user/add',
    method: 'post',
    data: params
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



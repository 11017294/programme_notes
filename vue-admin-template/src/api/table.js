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
    url: '/noteSort/getList',
    method: 'get',
    params: params
  })
}

export function getTagList(params) {
  return request({
    url: '/tag/getList',
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

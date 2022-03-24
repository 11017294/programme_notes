import request from '@/utils/request'

/**
 * 添加分类
 * @param params
 * @returns {AxiosPromise}
 */
export function addNoteSort(params) {
  return request({
    url: '/noteSort/add',
    method: 'post',
    params: params
  })
}

/**
 * 修改分类
 * @param params
 * @returns {AxiosPromise}
 */
export function editNoteSort(params) {
  return request({
    url: '/noteSort/edit',
    method: 'post',
    params: params
  })
}

/**
 * 添加标签
 * @param params
 * @returns {AxiosPromise}
 */
export function addTag(params) {
  return request({
    url: '/tag/add',
    method: 'post',
    params: params
  })
}

/**
 * 修改标签
 * @param params
 * @returns {AxiosPromise}
 */
export function editTag(params) {
  return request({
    url: '/tag/edit',
    method: 'post',
    params: params
  })
}

/**
 * 根据id获取笔记详细信息
 * @param params
 * @returns {AxiosPromise}
 */
export function getNoteById(params) {
  return request({
    url: '/content/getNoteById',
    method: 'get',
    params: params
  })
}

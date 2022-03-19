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


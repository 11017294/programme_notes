import request from '@/utils/request'

export function getUserCount() {
  return request({
    url: '/notesSys/getUserCount',
    method: 'get'
  })
}

export function getNotesCount() {
  return request({
    url: '/notesSys/getNotesCount',
    method: 'get'
  })
}

export function getTagCount() {
  return request({
    url: '/notesSys/getTagCount',
    method: 'get'
  })
}

export function getViewingCount() {
  return request({
    url: '/notesSys/getViewingCount',
    method: 'get'
  })
}

export function getNoteContributeCount() {
  return request({
    url: '/notesSys/getNoteContributeCount',
    method: 'get'
  })
}

export function getVisitByWeek() {
  return request({
    url: '/notesSys/getVisitByWeek',
    method: 'get'
  })
}

export function getOperateLog(params) {
  return request({
    url: '/webVisit/getList',
    method: 'get',
    params: params
  })
}

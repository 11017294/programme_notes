import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '首页', icon: 'dashboard' }
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/userManagement',
    children: [
      {
        path: 'userManagement',
        name: 'UserManagement',
        component: () => import('@/views/userManagement/index'),
        meta: { title: '用户管理', icon: 'el-icon-user-solid' }
      }
    ]
  },
  {
    path: '/notesManagement',
    component: Layout,
    redirect: '/notesManagement/articleManagement',
    meta: { title: '笔记管理', icon: 'el-icon-edit' },
    children: [
      {
        path: 'articleManagement',
        component: () => import('@/views/notesManagement/index'),
        name: 'ArticleManagement',
        meta: { title: '笔记管理', icon: 'form' }
      },
      {
        path: 'sortManagement',
        component: () => import('@/views/notesManagement/sortManagement'),
        name: 'SortManagement',
        meta: { title: '分类管理', icon: 'el-icon-folder-opened' }
      },
      {
        path: 'tagManagement',
        component: () => import('@/views/notesManagement/tagManagement'),
        name: 'TagManagement',
        meta: { title: '标签管理', icon: 'el-icon-collection-tag' }
      },
      {
        path: 'takeNotes',
        component: () => import('@/views/TakeNotes/index'),
        name: 'TakeNotes',
        meta: { title: '记笔记', icon: 'el-icon-edit-outline'}
      }
    ]
  },
  {
    path: '/operateLog',
    component: Layout,
    redirect: '/operateLog',
    children: [
      {
        path: 'operateLog',
        name: 'OperateLog',
        component: () => import('@/views/operateLog/index'),
        meta: { title: '操作日志', icon: 'nested' }
      }
    ]
  },
  {
    path: '/feedback',
    component: Layout,
    children: [
      {
        path: 'feedback',
        name: 'Feedback',
        component: () => import('@/views/feedback/index'),
        meta: { title: '反馈管理', icon: 'el-icon-message' }
      }
    ]
  },
  {
    path: '/picture',
    component: Layout,
    children: [
      {
        path: 'picture',
        name: 'Picture',
        component: () => import('@/views/Picture/index'),
        meta: { title: '图片管理', icon: 'el-icon-picture' }
      }
    ]
  },
  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'http://localhost:8888',
        meta: { title: '前端', icon: 'link' }
      }
    ]
  },
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [


  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router

import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'home',
        component: () => import('../views/Home.vue'),
        meta: { title: '首页'}
    },
    {
        path: '/variable',
        name: 'variable',
        component: () => import('../views/Variable.vue'),
        meta: { title: '工具'},
    },
    {
        path: '/pigeonhole',
        name: 'pigeonhole',
        component: () => import('../views/Pigeonhole.vue'),
        meta: { title: '分类'},
    },
    {
        path: '/category/:cate',
        name: 'category',
        component: () => import('../views/Home.vue'),
        meta: { title: '分类', params: 'cate'}
    },
    {
        path: '/search',
        name: 'search',
        component: () => import('../views/Home.vue'),
        meta: { title: '搜索'}
    },
    {
        path: '/about',
        name: 'about',
        component: () => import('../views/About.vue'),
        meta: { title: '关于'}
    },
    {
        path: '/friend',
        name: 'friend',
        component: () => import('../views/Friend.vue'),
        meta: { title: '友链'}
    },
    {
        path: '/article',
        name: 'Articles',
        component: () => import('../views/Articles.vue'),
        meta: { title: '文章'}
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/Login.vue'),
        meta: { title: '登录'}
    },
    {
        path: '/takeNotes',
        name: 'takeNotes',
        component: () => import('../views/TakeNotes.vue'),
        meta: { title: '记笔记'}
    },
    {
        path: '/personalCenter',
        name: 'personalCenter',
        component: () => import('../views/PersonalCenter.vue'),
        meta: { title: '个人中心'},
        children: [
            {
                path: '/myNotes',
                name: 'myNotes',
                component: () => import('../components/PersonalCenter/notes'),
                children: [
                    {
                        path: '/noteManagement',
                        name: 'noteManagement',
                        component: () => import('../components/PersonalCenter/note-management'),
                        meta: { title: '笔记'},
                    },
                    {
                        path: '/myCollect',
                        name: 'myCollect',
                        component: () => import('../components/PersonalCenter/my-collect'),
                        meta: { title: '笔记'},
                    },
                ],
                meta: { title: '笔记'},
            },
            {
                path: '/myInstall',
                name: 'myInstall',
                component: () => import('../components/PersonalCenter/install'),
                children: [
                    {
                        path: '/edit-user-info',
                        name: 'edit-user-info',
                        component: () => import('../components/PersonalCenter/edit-user-info'),
                        meta: { title: '设置'},
                    },
                    {
                        path: '/edit-password',
                        name: 'edit-password',
                        component: () => import('../components/PersonalCenter/edit-password'),
                        meta: { title: '设置'},
                    },
                ],
                meta: { title: '设置'},
            }
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

const whiteList = ['/login', '/', '/search', '/article', '/pigeonhole']

// 全局前置导航钩子 beforeEach
// 会在路由即将改变前触发
router.beforeEach((to, from, next) => {
    let title = '编程笔记'
    if (to.meta.params){
        title = `${to.meta.title}:${to.params[to.meta.params] || ''} - ${title}`
    }else {
        title = `${to.meta.title} - ${title}`
    }
    document.title = title
    let isLogin = window.localStorage.getItem('Authorization')
    if (!isLogin) {
        if (whiteList.indexOf(to.path) !== -1) { // 在免登录白名单，直接进入
            next()
        } else {
            Vue.prototype.$message('没有访问权限或登录已过期，请重新登录！')
            next('/login')
        }
    }
/*    if (to.path !== from.path) {
        store.dispatch('setLoading', true);
    }*/
    next();
})


router.afterEach((to, from) => {
    // 最多延迟 关闭 loading
/*    setTimeout(() => {
        store.dispatch('setLoading', false);
    }, 1000)*/
})
export default router

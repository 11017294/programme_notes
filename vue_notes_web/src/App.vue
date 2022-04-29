<template>
    <div id="app" v-cloak>
        <loading></loading>
        <layout-header></layout-header>
        <layout-body></layout-body>
        <layout-footer></layout-footer>
    </div>
</template>
<script>
    import layoutHeader from '@/components/layout/layout-header'
    import layoutBody from '@/components/layout/layout-body'
    import layoutFooter from '@/components/layout/layout-footer'
    import Loading from '@/components/loading'
    import {authVerify, getUserById} from "@/api";

    export default {
        name: "app",
        components: {
            layoutHeader,
            layoutBody,
            layoutFooter,
            Loading
        },
        methods: {
            getToken() {
                let token = localStorage.getItem('Authorization');
                if(token == undefined){
                    return;
                }
                let that = this;
                authVerify(token).then(res => {     // 根据token获取用户登录信息
                    that.$store.commit("SET_IS_LOGIN", true)
                    that.$store.commit("SET_TOKEN", token)
                    that.$store.commit("SET_USERINFO", res.data.info)

                    let userUid = that.$store.state.userInfo.userUid;
                    getUserById(userUid).then(res => {  // 根据用户id获取用户个人信息
                        localStorage.setItem('userInfo', JSON.stringify(res.data.user));
                        this.$store.commit("SET_AVATAR", res.data.user.avatar)
                    }).catch(err => {
                        that.$message.error(err);
                    })
                }).catch(err => {
                    localStorage.removeItem('Authorization');
                    this.$message.error(err);
                })
            }
        },
        mounted() {
            this.getToken()
        }
    }
</script>
<style lang="less">
    #app {
        font-size: 17px;
        color: #6f6f6f;
    }
</style>

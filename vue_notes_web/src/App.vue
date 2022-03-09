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
    import {authVerify} from "@/api";

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
                console.log(111)
                authVerify(token).then(res => {
                    this.$store.commit("SET_IS_LOGIN", true)
                    this.$store.commit("SET_TOKEN", token)
                    this.$store.commit("SET_USERINFO", res.data.info)
                }).catch(err => {
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

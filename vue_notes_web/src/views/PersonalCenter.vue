<template>
    <div class="personalCenter">
        <el-container>
            <el-header>
                <el-menu :default-active="navUrl" class="el-menu-demo" mode="horizontal" @select="">
                    <el-menu-item index="myNotes" @click="menuClick('/noteManagement')">笔记</el-menu-item>
                    <el-menu-item index="myInstall" @click="menuClick('/edit-user-info')">设置</el-menu-item>
                </el-menu>
            </el-header>
            <router-view></router-view>
        </el-container>
    </div>
</template>

<script>
export default {
    name: "PersonalCenter",
    data() {
        return {
            navUrl: ''
        }
    },
    methods: {
        menuClick(path) {
            this.$router.push(path);
        }
        /*handleEdit(index, row) {
            console.log(index, row)
        },
        handleDelete(index, row) {
            console.log(index, row)
        },*/
    },
    mounted() {
        if(this.$route.name == 'noteManagement' || this.$route.name == 'myCollect'){
            this.navUrl = 'myNotes'
        } else{
            this.navUrl = 'myInstall'
        }
    }

}
</script>

<style scoped>

.personalCenter {
    width: 80%;
    margin: auto;
    margin-top: 80px;
    background: #ffffff;
}
.el-header {
    padding-left: 80px;
    line-height: 60px;
    text-align: center;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
}
</style>

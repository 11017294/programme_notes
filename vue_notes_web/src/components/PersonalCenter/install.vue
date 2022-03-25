<template>
    <el-container>
        <el-aside>
            <el-menu
                :default-active="$route.name"
                class="el-menu-vertical-demo">
<!--                @open="handleOpen"
                @close="handleClose">-->
                <el-menu-item index="edit-user-info" @click="menuClick('/edit-user-info')">
                    <i class="el-icon-setting"></i>
                    <span slot="title">修改资料</span>
                </el-menu-item>
                <el-menu-item index="edit-password" @click="menuClick('/edit-password')">
                    <i class="el-icon-unlock"></i>
                    <span slot="title">修改密码</span>
                </el-menu-item>
<!--                <el-menu-item index="3" @click="menuClick('/')">
                    <i class="el-icon-setting"></i>
                    <span slot="title">我的随记</span>
                </el-menu-item>-->
            </el-menu>
        </el-aside>
        <el-main>
            <router-view></router-view>
        </el-main>
    </el-container>
</template>

<script>
export default {
    name: "myInstall",
    data() {
        return {

        }
    },
    methods: {
        menuClick(path) {
            this.$router.push(path);
        }
    }
}
</script>

<style scoped>
.el-input{
    padding: 0 25px;
    margin: 10px 0;
}

.el-aside {
    height: 700px;
    line-height: 44px;
    text-align: center;
    background-color: #ffffff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
}
.el-main {
    margin: 1px;
    padding: 5px;
    height: 700px;
    line-height: 36px;
    background-color: #ffffff;
}

.box-bottom {
    margin-top: 20px;
}

.demo-basic--circle {
    align: 'center';
    margin-top: 30px;
    margin-left: 150px;
}
.block {
    margin-left: 25px;
    font-weight: bold;
}
.text {
    font-size: 14px;
}
.item {
    margin-bottom: 18px;
}
.clearfix:before,
.clearfix:after {
    display: table;
    content: '';
}
.clearfix:after {
    clear: both;
}

.box-card {
    width: 80%;
    border-radius: 15px;
}
</style>

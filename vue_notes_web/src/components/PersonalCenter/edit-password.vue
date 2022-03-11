<template>
    <el-card class="box-pwd">
        <div class="block">
<!--            <el-image :src="require('../../assets/pwd.jpg')" class="box-img"></el-image>-->
            <el-form ref="form" label-width="100px">
                <el-form-item label="旧密码:">
                    <el-input v-model="oldPwd" show-password></el-input>
                </el-form-item>
                <el-form-item label="新密码:">
                    <el-input v-model="newPwd" show-password></el-input>
                </el-form-item>
                <el-form-item label="重复密码:">
                    <el-input v-model="redoPwd" show-password></el-input>
                </el-form-item>
                <el-form-item align="right">
                    <el-button type="primary" @click="saveUser">保存</el-button>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
</template>

<script>

import {updateUserPwd} from "@/api";

export default {
    name: "edit-password",
    data() {
        return {
            oldPwd: '',
            newPwd: '',
            redoPwd: '',
        }
    },
    methods: {
        saveUser() {
            let oldPwd = this.oldPwd;
            let newPwd = this.newPwd;
            let redoPwd = this.redoPwd;
            if(newPwd != redoPwd){
                this.$message.error("密码不一致");
                return;
            }
            if(oldPwd == newPwd){
                this.$message.error("新密码不能和旧密码相同");
                return;
            }
            let reg= /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
            if(!reg.test(newPwd)){
                this.$message.error("密码必须是由6-20位字母+数字组合");
                return;
            }
            let params = new URLSearchParams()
            params.append("oldPwd", oldPwd)
            params.append("newPwd", newPwd)
            updateUserPwd(params)
                .then(res => {
                    this.$message.success("密码修改成功");
                    this.oldPwd = newPwd;
                    this.newPwd = '';
                    this.redoPwd = '';
                })
                .catch(err => {
                    this.$message.error(err);
            })
        },
    }
}
</script>

<style scoped>
.block {
    width: 60%;
    margin: 18% auto;
}

.box-img {
    width: 35%;
    margin: 0 auto;
}

.box-pwd{
    height: 690px;
}
</style>

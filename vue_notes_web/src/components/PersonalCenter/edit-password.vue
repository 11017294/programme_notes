<template>
    <el-card class="box-pwd">
        <div class="block">
<!--            <el-image :src="require('../../assets/pwd.jpg')" class="box-img"></el-image>-->
            <el-form ref="form" :model="password" label-width="100px">
                <el-form-item label="旧密码:">
                    <el-input v-model="oldPassword" :disabled="disabled"></el-input>
                </el-form-item>
                <el-form-item label="新密码:">
                    <el-input v-model="password" :disabled="disabled"></el-input>
                </el-form-item>
                <el-form-item label="重复密码:">
                    <el-input v-model="redoPassword" :disabled="disabled"></el-input>
                </el-form-item>
                <el-form-item align="right">
                    <el-button type="info" @click="cancel" v-show="!disabled">取消</el-button>
                    <el-button type="warning" @click="activateEdit" v-show="disabled">修改</el-button>
                    <el-button type="primary" @click="saveUser" v-show="!disabled">保存</el-button>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
</template>

<script>

export default {
    name: "edit-password",
    data() {
        return {
            oldPassword: '',
            password: '',
            redoPassword: '',
            disabled: true,
        }
    },
    methods: {
        cancel() {
            this.disabled = true;

        },
        activateEdit() {
            this.disabled = false;
        },
        saveUser() {
            if(this.password != this.redoPassword){
                this.$message.error("密码不一致");
                return;
            }
            let reg= /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
            if(!reg.test(this.password)){
                this.$message.error("密码必须是由6-20位字母+数字组合");
                return;
            }

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

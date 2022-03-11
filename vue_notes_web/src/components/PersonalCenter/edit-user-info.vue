<template>
    <el-card>
        <el-form ref="form" :model="userInfo" label-width="80px" >
            <el-row>
                <el-form-item>
                    <el-avatar shape="square" :size="100" :fit="fit" :src="userInfo.avatar"></el-avatar>
                </el-form-item>
            </el-row>
            <el-row>
                <el-col :span="10">
                    <el-form-item label="用户名:">
                        <el-input v-model="userInfo.userName" disabled></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="10">
                    <el-form-item label="昵称:">
                        <el-input v-model="userInfo.nickName" :disabled="disabled"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="10">
                    <el-form-item label="性别:">
                        <el-select v-model="userInfo.sex" style="width: 100%" :disabled="disabled">
                            <el-option label="男" :value=0></el-option>
                            <el-option label="女" :value=1></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="10">
                <el-form-item label="生日:">
                        <el-col :span="11">
                            <el-date-picker type="date" placeholder="选择日期" v-model="userInfo.birthday" style="width: 100%;" :disabled="disabled"></el-date-picker>
                        </el-col>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="10">
                    <el-form-item label="邮箱:">
                        <el-input v-model="userInfo.email" :disabled="disabled"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-form-item label="个人简介:" style="height: 240px">
                <el-input type="textarea" v-model="userInfo.summary" rows="10" resize="none" :disabled="disabled"></el-input>
            </el-form-item>
            <el-form-item align="right">
                <el-button type="info" @click="cancel" v-show="!disabled">取消</el-button>
                <el-button type="warning" @click="activateEdit" v-show="disabled">修改</el-button>
                <el-button type="primary" @click="saveUser" v-show="!disabled">保存</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script>
import {editUser} from "@/api";

export default {
    name: "edit-user-info",
    data() {
        return {
            userInfo: {
                uid: '',
                userName: '',
                sex: 0,
                avatar: '',
                email: '',
                birthday: '',
                mobile: '',
                summary: '',
                nickName: '',
            },
            disabled: true
        }
    },
    methods: {
        getUserInfo() {
            this.userInfo = JSON.parse(localStorage.getItem('userInfo'));
        },
        cancel() {
            this.$confirm('取消后信息将重置，是否取消', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.disabled = true;
                this.userInfo = JSON.parse(localStorage.getItem('userInfo'));
            })
        },
        activateEdit() {
            this.disabled = false;
        },
        saveUser() {
            let userInfo = this.userInfo;
            editUser(JSON.stringify(userInfo)).then(res => {
                localStorage.setItem('userInfo', JSON.stringify(userInfo));
                this.disabled = true;
            }).catch(err => {
                this.$message.error(err);
            })
        },
    },
    mounted() {
        this.getUserInfo();
    }

}
</script>

<style scoped>

</style>

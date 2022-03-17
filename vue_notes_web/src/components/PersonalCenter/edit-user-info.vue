<template>
    <el-card>
        <el-form ref="form" :model="userInfo" label-width="80px">
            <el-row>
                <el-form-item>
                    <el-tooltip class="item" effect="light" content="点击头像可更换" placement="left" >
                    <el-upload
                        class="avatar-uploader"
                        action="h"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload">
                        <img v-if="userInfo.avatar" :src="this.global.file_path + userInfo.avatar" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                    </el-tooltip>
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
import {editUser, uploadAvatar} from "@/api";

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
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isPNG = file.type === 'image/png';
            const isLt2M = file.size / 1024 / 1024 < 2;
            if (!isJPG && !isPNG) {
                this.$message.error('上传头像图片格式为 [JPG, PNG]');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            let param = new FormData();
            param.append("file", file)
            uploadAvatar(param)
                .then(res => {
                    let userInfo = JSON.parse(localStorage.getItem('userInfo'));
                    userInfo.setItem("avatar", res.data.fileUrl);
                    localStorage.setItem('userInfo', JSON.stringify(userInfo));
                    this.$message.success('更换成功');
                }).catch(err => {
                this.$message(err)
            })
            return isJPG && isLt2M;
        },
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
                this.$message.success("修改成功");
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

.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}
.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}
.avatar-uploader-icon {
    border: 1px dashed #d9d9d9;
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}
.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>

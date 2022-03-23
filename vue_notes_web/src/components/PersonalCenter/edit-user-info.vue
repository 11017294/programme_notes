<template>
    <el-card>
        <el-row>
            <el-col :span="4">
                <div @click="editUser">
                    <el-avatar
                        v-if="userInfo.avatar"
                        shape="square"
                        :size="178"
                        fit="fit"
                        :src="this.global.file_path + userInfo.avatar"></el-avatar>
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </div>
            </el-col>
            <el-col :span="4" >
                <h1 class="ant-userName">
                    {{ userInfo.nickName }}
                </h1>
            </el-col>
        </el-row>
        <el-row>
            <el-descriptions title="个人信息" column="1" :colon="false" :labelStyle="{'width': '100px', 'height': '16px'}">
                <template slot="extra">
                    <el-button size="small" icon="el-icon-edit" type="primary" plain @click="editUser">编辑</el-button>
                </template>
                <el-descriptions-item label="用户名" >{{ userInfo.userName }}</el-descriptions-item>
                <el-descriptions-item/>
                <el-descriptions-item label="性别">
                    <el-tag size="danger" v-if="userInfo.sex == 0">女</el-tag>
                    <el-tag size="small" v-if="userInfo.sex == 1">男</el-tag>
                </el-descriptions-item>
                <el-descriptions-item/>
                <el-descriptions-item label="邮箱">{{ userInfo.email }}</el-descriptions-item>
                <el-descriptions-item/>
                <el-descriptions-item label="手机号">{{ userInfo.mobile }}</el-descriptions-item>
                <el-descriptions-item/>
                <el-descriptions-item label="生日">{{ userInfo.birthday  | parseTime }}</el-descriptions-item>
                <el-descriptions-item/>
                <el-descriptions-item label="简介">
                    <template v-if="userInfo.summary">
                        {{ userInfo.summary }}
                    </template>
                    <template v-else>
                        这个人很懒，什么都没有留下...
                    </template>
                </el-descriptions-item>
                <el-descriptions-item/>
                <el-descriptions-item label="注册时间">{{ userInfo.createTime }}</el-descriptions-item>
            </el-descriptions>
        </el-row>

        <!-- 修改的对话框 -->
        <el-dialog title="修改信息" @closed="closeEdit" :visible.sync="dialogFormVisible">
            <el-form ref="form" :rules="rules" :model="tempUserInfo" label-width="80px">
                <el-row>
                    <el-col :span="7">
                        <el-form-item>
                            <el-tooltip class="item" effect="light" content="点击头像可更换" placement="left" >
                                <el-upload
                                    class="avatar-uploader"
                                    action="h"
                                    :show-file-list="false"
                                    :before-upload="beforeAvatarUpload">
                                    <img v-if="tempUserInfo.avatar" :src="this.global.file_path + tempUserInfo.avatar" class="avatar">
                                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                </el-upload>
                            </el-tooltip>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <h1 class="ant-userName">
                            {{ tempUserInfo.nickName }}
                        </h1>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="昵称:" prop="nickName">
                            <el-input v-model="tempUserInfo.nickName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="邮箱:" prop="email">
                            <el-input v-model="tempUserInfo.email"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="性别:" prop="sex">
                            <el-select v-model="tempUserInfo.sex" style="width: 100%">
                                <el-option label="男" :value=1></el-option>
                                <el-option label="女" :value=0></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="生日:" prop="birthday">
                            <el-col :span="24">
                                <el-date-picker
                                    type="date"
                                    value-format=" yyyy-MM-dd HH:mm:SS"
                                    placeholder="选择日期"
                                    v-model="tempUserInfo.birthday"
                                    style="width: 100%;"
                                   ></el-date-picker>
                            </el-col>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item label="个人简介:" style="height: 150px">
                    <el-input type="textarea" v-model="tempUserInfo.summary" rows="5" resize="none"></el-input>
                </el-form-item>
                <el-form-item align="right">
                    <el-button type="info" @click="closeEdit">取消</el-button>
                    <el-button type="primary" @click="saveUser">保存</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
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
                avatar: this.$store.state.avatar,
                email: '',
                birthday: '',
                mobile: '',
                summary: '',
                nickName: '',
            },
            disabled: true,
            tempUserInfo: {},
            dialogFormVisible: false, //控制弹出框
            rules: {
                nickName: [
                    {required: true, message: '昵称不能为空', trigger: 'blur'},
                    {min: 1, max: 30, message: '长度在1到30个字符'},
                ],
                sex: [
                    {required: true, message: '性别不能为空', trigger: 'blur'},
                ],
                email: [
                    {pattern: /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/, message: '请输入正确的邮箱'},
                ],
                birthday: [
                    {required: true, message: '生日不能为空', trigger: 'blur'}
                ]
            }
        }
    },
    computed: {
        avatar() {    // 用户头像
            return this.$store.state.avatar
        }
    },
    methods: {
        // 上传头像
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
                    this.tempUserInfo.avatar = res.data.fileUrl
                }).catch(err => {
                    this.$message.error(err)
            })
            return isJPG && isLt2M;
        },
        // 获取用户信息
        getUserInfo() {
            this.userInfo = JSON.parse(localStorage.getItem('userInfo'));
        },
        // 编辑用户
        editUser() {
            this.tempUserInfo = JSON.parse(JSON.stringify(this.userInfo))
            this.dialogFormVisible = true
        },
        // 提交用户信息修改
        saveUser() {
            let userInfo = this.tempUserInfo;
            editUser(userInfo).then(res => {
                localStorage.setItem('userInfo', JSON.stringify(userInfo));
                this.$store.commit("SET_AVATAR", userInfo.avatar)
                this.disabled = true;
                this.userInfo = userInfo
                this.$message.success("修改成功");
            }).catch(err => {
                this.$message.error(err);
            })
            this.dialogFormVisible = false
        },
        closeEdit() {
            this.disabled = true;
            this.dialogFormVisible = false
            this.userInfo = JSON.parse(localStorage.getItem('userInfo'));
        }
    },
    mounted() {
        this.getUserInfo();
    }

}
</script>

<style scoped>

.el-card {
    height: 690px;
    padding: 0 50px;
}

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

.ant-userName {
    margin-top: 20px;
    margin-left: 20px;
    font-size: 24px;
}
</style>

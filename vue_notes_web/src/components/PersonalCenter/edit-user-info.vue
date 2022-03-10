<template>
    <el-card>
        <el-form ref="form" :model="userInfo" label-width="80px" >
            <el-row>
                <el-avatar shape="square" :size="100" :fit="fit" :src="url"></el-avatar>
            </el-row>
            <el-row>
                <el-col :span="10">
                    <el-form-item label="用户名:">
                        <el-input v-model="userInfo.userName" disabled></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="10">
                    <el-form-item label="昵称:">
                        <el-input v-model="userInfo.nickName"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="10">
                    <el-form-item label="性别:">
                        <el-select v-model="userInfo.sex" style="width: 100%">
                            <el-option label="男" value="0"></el-option>
                            <el-option label="女" value="1"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="10">
                <el-form-item label="生日:">
                        <el-col :span="11">
                            <el-date-picker type="date" placeholder="选择日期" v-model="userInfo.birthday" style="width: 100%;"></el-date-picker>
                        </el-col>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="10">
                    <el-form-item label="邮箱:">
                        <el-input v-model="userInfo.email"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-form-item label="个人简介:" style="height: 267px">
                <el-input type="textarea" v-model="userInfo.summary" rows="12" resize="none"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="info" @click="">重置</el-button>
                <el-button type="warning" @click="">修改</el-button>
                <el-button type="primary" @click="">提交</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script>
export default {
    name: "edit-user-info",
    data() {
        return {
            userInfo: {
                userName: '',
                sex: '',
                avatar: '',
                email: '',
                birthday: '',
                mobile: '',
                summary: '',
                nickName: '',
            }
        }
    }

}
</script>

<style scoped>

</style>

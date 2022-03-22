<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container" style="margin: 10px 0 10px 0;">
      <el-input
        class="filter-item"
        @keyup.enter.native="handleFind"
        clearable
        placeholder="请输入用户名"
        style="width: 200px;"
        v-model="keyword"
      ></el-input>

      <el-button
        class="filter-item"
        @click="handleFind"
        icon="el-icon-search"
        type="primary">查找</el-button>

      <el-button
        class="filter-item"
        type="info"
        @click="resetForm">重置</el-button>

      <el-button
        class="filter-item"
        @click="handleAdd"
        type="primary"
        icon="el-icon-edit">添加用户</el-button>

    </div>

    <el-table
      v-loading="listLoading"
      :data="userData"
      element-loading-text="Loading"
    >
      <el-table-column
        align="center"
        prop="uid"
        label="ID"
        width="290">
      </el-table-column>
      <el-table-column
        align="center"
        prop="userName"
        label="用户名"
        width="150">
      </el-table-column>
      <el-table-column align="center" label="头像" width="120">
        <template slot-scope="scope">
          <img
            :src=" avatarPath + scope.row.avatar"
            style="width: 100px;height: 100px;"
          >
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        prop="nickName"
        label="昵称"
        width="180">
      </el-table-column>
      <el-table-column
        align="center" label="性别" width="70">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.sex==1" type="success">男</el-tag>
          <el-tag v-if="scope.row.sex==0" type="warning">女</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        prop="email"
        label="邮箱"
        width="180">
      </el-table-column>
      <el-table-column
        align="center"
        prop="mobile"
        label="电话"
        width="120">
      </el-table-column>
      <el-table-column
        align="center"
        prop="loginCount"
        label="登录次数"
        width="100">
      </el-table-column>
      <el-table-column
        align="center"
        prop="lastLoginTime"
        label="最后登录时间"
        width="160">
      </el-table-column>
      <el-table-column
        align="center"
        prop="lastLoginIp"
        label="最后登录IP"
        width="100">
      </el-table-column>
      <el-table-column
        align="center"
        label="状态"
        width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status==2" type="danger">黑名单</el-tag>
          <el-tag v-if="scope.row.status==1" type="success">正常</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        prop="browser"
        label="浏览器"
        width="180">
      </el-table-column>
      <el-table-column
        align="center"
        prop="os"
        label="操作系统"
        width="110">
      </el-table-column>
      <el-table-column
        align="center"
        prop="createTime"
        label="创建时间"
        width="180">
      </el-table-column>
      <el-table-column class-name="status-col" label="用户类别" width="110" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.userTag==0" type="success">普通用户</el-tag>
          <el-tag v-if="scope.row.userTag==1" type="gray">管理员</el-tag>
          <el-tag v-if="scope.row.userTag==2" type="danger">站长</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="300" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.row)">编辑</el-button>

          <el-button
            size="mini"
            type="info"
            v-show="scope.row.status != 2"
            @click="addBlacklist(scope.row)">添加至黑名单</el-button>

          <el-button
            size="mini"
            type="success"
            v-show="scope.row.status == 2"
            @click="deleteBlacklist(scope.row)">移除黑名单</el-button>

          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row)">删除</el-button>

        </template>
      </el-table-column>
    </el-table>
    <!--分页-->
    <div class="block">
      <el-pagination
        @current-change="fetchData"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        layout="total, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </div>

    <!-- 添加或修改对话框 -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item :label-width="formLabelWidth" label="用户头像">
          <div class="block">
            <el-tooltip class="item" effect="light" content="点击头像可更换" placement="right" >
              <el-upload
                class="avatar-uploader"
                action="h"
                :show-file-list="false"
                :before-upload="beforeAvatarUpload">
                <img v-if="form.avatar" :src="avatarPath + form.avatar" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
<!--            <el-image
              style="width: 100px; height: 100px"
              :src="avatarPath + form.avatar"
              fit="cover"></el-image>-->
            </el-tooltip>
          </div>
        </el-form-item>

        <el-row :gutter="24">
          <el-col :span="9">
            <el-form-item :label-width="formLabelWidth" label="用户名">
              <el-input v-model="form.userName" :disabled="disabled"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="9">
            <el-form-item :label-width="formLabelWidth" label="昵称" prop="nickName">
              <el-input v-model="form.nickName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="24">
          <el-col :span="9">
            <el-form-item :label-width="formLabelWidth" label="邮箱" prop="email">
              <el-input auto-complete="off" v-model="form.email"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item :label-width="formLabelWidth" label="性别" prop="sex">
              <el-select v-model="form.sex">
                <el-option label="男" :value=1></el-option>
                <el-option label="女" :value=0></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="24">
          <el-col :span="10">
            <el-form-item label="生日" :label-width="formLabelWidth" prop="birthday">
              <el-col :span="9">
                <el-date-picker
                  value-format=" yyyy-MM-dd HH:mm:SS"
                  type="date"
                  placeholder="选择日期"
                  v-model="form.birthday"></el-date-picker>
              </el-col>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item :label-width="formLabelWidth" label="简介">
          <el-input
            :autosize="{ minRows: 3, maxRows: 10}"
            placeholder="请输入内容"
            style="width: 70%"
            type="textarea"
            v-model="form.summary">
          </el-input>
        </el-form-item>

      </el-form>
      <div class="dialog-footer" slot="footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button @click="submitForm" type="primary">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {addBlacklist, deleteUser, getUserList, deleteBlacklist, editUser, addUser} from '@/api/table'
import {uploadAvatar} from "../../../../vue_notes_web/src/api";

export default {
  name: 'UserManagement',
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      avatarPath : this.global.file_path,
      list: null,
      userData: [],
      keyword: '',
      disabled: true,
      currentPage: 1,
      totalPages: 0,
      pageSize: 10,
      total: 0, //总数量
      listLoading: true,
      formLabelWidth: "120px",
      dialogFormVisible: false, //控制弹出框
      form: {
        uid: '',
        userName: '',
        sex: 0,
        email: '',
        birthday: '',
        mobile: '',
        summary: '',
        nickName: '',
      },
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
  created() {
    this.fetchData()
  },
  methods: {
    handleFind() {    // 按钮查询
      this.currentPage = 1
      this.fetchData();
    },
    resetForm() {       // 重置
      this.keyword = ''
      this.fetchData()
    },
    fetchData() {   // 查询用户列表
      let that = this
      this.listLoading = true
      let params = new URLSearchParams()
      params.append("pageSize", that.pageSize)
      params.append("currentPage", that.currentPage)
      if(that.keyword){
        params.append("keyword", that.keyword)
      }
      getUserList(params).then(response => {
        let data = response.data.list
        this.userData = data.records
        this.total = data.total
        this.listLoading = false
      })
    },
    handleDelete(row) {   // 删除用户
      var that = this;
      this.$confirm("此操作将把用户删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          var params = {};
          params.uid = row.uid;
          deleteUser(params).then(response => {
            that.$message.success("删除成功")
            that.fetchData();
          });
        })
        .catch(() => {
          that.$message.info("已取消删除")
        });
    },
    addBlacklist(row) {   // 添加黑名单
      var that = this;
      if(row.status == 2) {
        that.$message.warning("已经是黑名单用户")
        return
      }
      this.$confirm("此操作将用户添加为黑名单, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          var params = {};
          params.uid = row.uid;
          addBlacklist(params).then(response => {
            that.$message.success("添加黑名单成功")
            that.fetchData();
          });
        })
        .catch(() => {
          that.$message.info("已取消添加黑名单")
        });
    },
    deleteBlacklist(row) {   // 移除黑名单
      var that = this;
      this.$confirm("此操作将用户移出黑名单, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          var params = {};
          params.uid = row.uid;
          deleteBlacklist(params).then(response => {
            that.$message.success("移除黑名单成功")
            that.fetchData();
          });
        })
        .catch(() => {
          that.$message.info("已取消移除黑名单")
        });
    },
    handleAdd() {
      this.dialogFormVisible = true;
      this.form = {};
      this.isEditForm = false;
      this.disabled = false;
    },
    handleEdit(row) {
      this.title = "编辑用户";
      this.dialogFormVisible = true;
      this.isEditForm = true;
      this.form = row;
      this.disabled = true;
    },
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (!valid) {
          console.log("校验出错")
        } else {
          if(this.isEditForm) {
            editUser(this.form)
              .then(res => {
                this.$message.success('修改成功')
                this.dialogFormVisible = false
                this.fetchData()
              }).catch(err => {
                this.$message.error(err)
            })
          } else {
            addUser(this.form)
              .then(res => {
                this.$message.success('添加成功')
                this.dialogFormVisible = false
                this.fetchData()
              }).catch(err => {
                this.$message.error(err)
            })
          }
        }
      })
    },
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
      /*uploadAvatar(param)
        .then(res => {
          let userInfo = JSON.parse(localStorage.getItem('userInfo'));
          userInfo.avatar = res.data.fileUrl
          localStorage.setItem('userInfo', JSON.stringify(userInfo));
          this.$store.commit("SET_AVATAR", userInfo.avatar)
          this.$message.success('更换成功');
        }).catch(err => {
        this.$message.error(err)
      })*/
      return isJPG && isLt2M;
    }
  }
}
</script>

<style scoped>
.filter-item {
  margin: 10px;
  width: 150px
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar {
  width: 100px;
  height: 100px;
  display: block;
}
</style>

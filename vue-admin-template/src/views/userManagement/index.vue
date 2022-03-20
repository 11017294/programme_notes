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
      <el-table-column
        align="center"
        prop="nickName"
        label="昵称"
        width="180">
      </el-table-column>
      <el-table-column
        align="center" label="性别" width="80">
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
        width="130">
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
        width="180">
      </el-table-column>
      <el-table-column
        align="center"
        prop="lastLoginIp"
        label="最后登录IP"
        width="120">
      </el-table-column>
      <el-table-column
        align="center"
        prop="ipSource"
        label="ip来源"
        width="100">
      </el-table-column>
      <el-table-column
        align="center"
        prop="browser"
        label="浏览器"
        width="200">
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
      <el-table-column label="操作" width="150" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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
  </div>
</template>

<script>
import {deleteNote, deleteUser, getUserList} from '@/api/table'

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
      list: null,
      userData: [],
      keyword: "",
      currentPage: 1,
      totalPages: 0,
      pageSize: 10,
      total: 0, //总数量
      listLoading: true
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
    handleDelete(row) {
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
  }
}
</script>

<style scoped>
.filter-item {
  margin: 10px;
  width: 150px
}
</style>

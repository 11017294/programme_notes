<template>
  <div class="app-container">
    <div class="filter-container" style="margin: 10px 0 10px 0;">
      <el-input clearable class="filter-item" style="width: 200px;" v-model="queryParams.keyword" placeholder="内容"></el-input>

      <el-select
        class="filter-item"
        v-model="queryParams.status"
        clearable
        placeholder="反馈是否处理">
        <el-option value="1" label="未处理"></el-option>
        <el-option value="2" label="已处理"></el-option>
      </el-select>

      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFind">查找</el-button>
    </div>
    <el-table  v-loading="listLoading" :data="tableData"  style="width: 100%">
      <el-table-column type="selection"></el-table-column>
      <el-table-column label="序号" width="60" align="center">
        <template slot-scope="scope">
          <span >{{scope.$index + 1}}</span>
        </template>
      </el-table-column>

      <el-table-column label="ID" width="350" align="center">
        <template slot-scope="scope">
          <span style="cursor:pointer;">{{ scope.row.uid }}</span>
        </template>
      </el-table-column>

      <el-table-column label="内容" width="350" align="center">
        <template slot-scope="scope">
          <span style="cursor:pointer;">{{ scope.row.content }}</span>
        </template>
      </el-table-column>

      <el-table-column label="留言人邮箱" width="120" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.email }}</span>
        </template>
      </el-table-column>

      <el-table-column label="留言人uid" width="150" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.userUid }}</span>
        </template>
      </el-table-column>

      <el-table-column
        align="center"
        label="状态"
        width="150">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status==1" type="danger">未处理</el-tag>
          <el-tag v-if="scope.row.status==2" type="success">已处理</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="300" align="center" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            plain
            :disabled="scope.row.status==2"
            @click="Completion(scope.row)">完成处理</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--分页-->
    <div class="block">
      <el-pagination
        @current-change="fetchData"
        @size-change="handleSizeChange"
        :current-page.sync="currentPage"
        :page-sizes="[10, 20, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {completion, getMessageList} from "@/api/table";

export default {
  name: "Feedback",
  data() {
    return {
      tableData: [],
      keyword: "",
      currentPage: 1,
      pageSize: 10,
      total: 0, //总数量
      listLoading: false,
      queryParams:{
        keyword: '',
        status: '',
      }, // 搜索条件
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    handleSizeChange(val) {
      this.pageSize = val
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      let params = new URLSearchParams()
      params.append("pageSize", this.pageSize)
      params.append("currentPage", this.currentPage)
      if(this.queryParams.keyword){
        params.append("keyword", this.queryParams.keyword)
      }
      if(this.queryParams.status){
        params.append("status", this.queryParams.status)
      }
      getMessageList(params)
        .then(res => {
          let data = res.data.list
          this.tableData = data.records
          this.total = data.total
          this.pageSize = data.size
          this.currentPage = data.current
          this.listLoading = false
        })
          .catch(err => {
            this.$message.error(err)
            this.listLoading = false
        })
    },
    Completion(row) {
      this.$confirm("您确定要完成处理了吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        completion(row.uid).then(res => {
          this.$message.success("操作成功")
          this.fetchData()
        }).catch(err => {
          this.$message.error("操作失败")
        })
      })
    },
    handleFind() {
      this.currentPage = 1
      this.fetchData()
    }
  }
}
</script>

<style scoped>
.filter-item {
  margin: 10px;
  width: 150px
}
</style>

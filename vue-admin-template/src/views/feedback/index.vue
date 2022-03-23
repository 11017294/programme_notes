<template>
  <div class="app-container">
<!--    <div class="filter-container" style="margin: 10px 0 10px 0;">
      <el-input clearable class="filter-item" style="width: 200px;" v-model="keyword" placeholder="用户行为"></el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFind">查找</el-button>
    </div>-->
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
import {getMessageList} from "@/api/table";

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
    }
  }
}
</script>

<style scoped>

</style>

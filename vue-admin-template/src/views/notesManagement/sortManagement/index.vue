<template>
  <div class="app-container">
    <el-table :data="tableData"  style="width: 100%">
    <el-table-column type="selection"></el-table-column>

    <el-table-column label="序号" width="60" align="center">
      <template slot-scope="scope">
        <span >{{scope.$index + 1}}</span>
      </template>
    </el-table-column>

    <el-table-column label="分类名" width="160" align="center">
      <template slot-scope="scope">
        <span>{{ scope.row.sortName }}</span>
      </template>
    </el-table-column>

    <el-table-column label="分类介绍" width="300" align="center">
      <template slot-scope="scope">
        <span>{{ scope.row.content }}</span>
      </template>
    </el-table-column>

    <el-table-column label="排序" width="100" align="center">
      <template slot-scope="scope">
        <el-tag type="warning">{{ scope.row.sort }}</el-tag>
      </template>
    </el-table-column>

    <el-table-column label="创建时间" width="200" align="center">
      <template slot-scope="scope">
        <span >{{ scope.row.createTime }}</span>
      </template>
    </el-table-column>

    <el-table-column label="状态" width="120" align="center">
      <template slot-scope="scope">
        <template v-if="scope.row.isDelete == 0">
          <span>正常</span>
        </template>
        <template v-if="scope.row.isDelete == 1">
          <span>已删除</span>
        </template>
      </template>
    </el-table-column>

    <el-table-column label="操作" fixed="right" min-width="220">
      <template slot-scope="scope" >
        <el-button type="primary" size="small">编辑</el-button>
        <el-button type="danger" size="small">删除</el-button>
      </template>
      </el-table-column>
    </el-table>

    <!--分页-->
    <div class="block">
      <el-pagination
        @current-change="getSortList"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        layout="total, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import { getSortList } from '@/api/table'

export default {
  name: 'SortManagement',
  data() {
    return {
      tableData: [],
      keyword: '',
      currentPage: 1,
      totalPages: 0,
      pageSize: 10,
      total: 0,
    }
  },
  created() {
    this.getSortList()
  },
  methods: {
    getSortList() {
      let that = this
      this.listLoading = true
      let params = new URLSearchParams()
      params.append("pageSize", that.pageSize)
      params.append("currentPage", that.currentPage)
      getSortList(params).then(response => {
        let data = response.data.list
        this.tableData = data.records
        this.total = data.total
        this.pageSize = data.size
        this.currentPage = data.current
        this.listLoading = false
      })
    }
  }
}
</script>

<style scoped>

</style>

<template>
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
      pageSize: 15,
      total: 0,
    }
  },
  created() {
    this.getSortList()
  },
  methods: {
    getSortList() {
      let params = {}
      getSortList(params).then(response => {
        let data = response.data.list
        this.tableData = data
      })
    }
  }
}
</script>

<style scoped>

</style>

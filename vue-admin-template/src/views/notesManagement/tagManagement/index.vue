<template>
  <el-table :data="tableData"
            style="width: 100%"
            :default-sort="{prop: 'sort', order: 'descending'}">
    <el-table-column type="selection"></el-table-column>

    <el-table-column label="序号" width="60" align="center">
      <template slot-scope="scope">
        <span>{{scope.$index + 1}}</span>
      </template>
    </el-table-column>

    <el-table-column label="标签名" width="100" align="center">
      <template slot-scope="scope">
        <span>{{ scope.row.content }}</span>
      </template>
    </el-table-column>

    <el-table-column label="点击数" width="100" align="center" prop="clickCount" sortable="custom" :sort-by="['clickCount']">
      <template slot-scope="scope">
        <span>{{ scope.row.clickCount }}</span>
      </template>
    </el-table-column>

    <el-table-column label="排序" width="100" align="center" prop="sort" sortable="custom" :sort-orders="['ascending', 'descending']">
      <template slot-scope="scope">
        <el-tag type="warning">{{ scope.row.sort }}</el-tag>
      </template>
    </el-table-column>

    <el-table-column label="创建时间" width="160" align="center" prop="createTime" sortable="custom" :sort-by="['createTime']">
      <template slot-scope="scope">
        <span>{{ scope.row.createTime }}</span>
      </template>
    </el-table-column>

    <el-table-column label="更新时间" width="160" align="center" prop="updateTime" sortable="custom" :sort-by="['updateTime']">
      <template slot-scope="scope">
        <span>{{ scope.row.updateTime }}</span>
      </template>
    </el-table-column>

    <el-table-column label="状态" width="100" align="center">
      <template slot-scope="scope">
        <template v-if="scope.row.isDelete == 0">
          <span>正常</span>
        </template>
        <template v-if="scope.row.isDelete == 1">
          <span>已删除</span>
        </template>
      </template>
    </el-table-column>

    <el-table-column label="操作" fixed="right" min-width="230">
      <template slot-scope="scope">
        <el-button @click="handleEdit(scope.row)" type="primary" size="small" v-permission="'/tag/edit'">编辑</el-button>
        <el-button @click="handleDelete(scope.row)" type="danger" size="small" v-permission="'/tag/delete'">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { getTagList } from '@/api/table'

export default {
  name: 'TagManagement',
  data() {
    return {
      tableData: [],
    }
  },
  created() {
    this.getTagList()
  },
  methods: {
    getTagList() {
      let params = {}
      getTagList(params).then(response => {
        this.tableData = response.data.list
      })
    }
  }
}
</script>

<style scoped>

</style>

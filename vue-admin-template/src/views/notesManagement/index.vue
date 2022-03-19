<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="noteData"
      element-loading-text="Loading"
    >
      <el-table-column label="序号" width="80" align="center">
        <template slot-scope="scope">
          <span>{{scope.$index + 1}}</span>
        </template>
      </el-table-column>

      <el-table-column label="标题" width="350" align="center">
        <template slot-scope="scope">
          <span style="cursor:pointer;">{{ scope.row.title }}</span>
        </template>
      </el-table-column>

      <el-table-column label="作者" width="120" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.author }}</span>
        </template>
      </el-table-column>

      <el-table-column label="是否原创" width="100" align="center" prop="isOriginal" sortable="custom" :sort-by="['isOriginal']">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isOriginal==1" type="success">原创</el-tag>
          <el-tag v-if="scope.row.isOriginal==0" type="info">转载</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="分类" width="150" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.noteSortName }}</span>
        </template>
      </el-table-column>

<!--      <el-table-column label="标签" width="200" align="center" >
        <template slot-scope="scope">
          <template>
            <el-tag
              style="margin-left: 3px"
              type="warning"
              v-if="item"
              :key="index"
              v-for="(item, index) in scope.row.tagList"
            >{{item.content}}</el-tag>
          </template>
        </template>
      </el-table-column>-->

      <el-table-column label="点击数" width="100" align="center" prop="clickCount" sortable="custom" :sort-by="['clickCount']">
        <template slot-scope="scope">
          <span>{{ scope.row.clickCount }}</span>
        </template>
      </el-table-column>

      <el-table-column label="收藏数" width="100" align="center" prop="collectCount" sortable="custom">
        <template slot-scope="scope">
          <span>{{ scope.row.collectCount }}</span>
        </template>
      </el-table-column>

      <el-table-column label="开启评论" width="100" align="center" prop="openComment" sortable="custom" :sort-by="['openComment']">
        <template slot-scope="scope">
          <template>
            <span v-if="scope.row.openComment == 0">否</span>
            <span v-if="scope.row.openComment == 1">是</span>
          </template>
        </template>
      </el-table-column>

      <el-table-column label="发布状态" width="100" align="center" prop="isPublish" sortable="custom" :sort-by="['isPublish']">
        <template slot-scope="scope">
          <template>
            <span v-if="scope.row.isPublish == 0">否</span>
            <span v-if="scope.row.isPublish == 1">是</span>
          </template>
        </template>
      </el-table-column>

      <el-table-column label="创建时间" width="200" align="center" prop="createTime" sortable="custom" :sort-orders="['ascending', 'descending']">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" min-width="150" fixed="right">
        <template slot-scope="scope">
          <el-button @click="handleEdit(scope.row)" type="primary" size="small" v-permission="'/blog/edit'">编辑</el-button>
          <el-button @click="handleDelete(scope.row)" type="danger" size="small" v-permission="'/blog/delete'">删除</el-button>
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
import { getNoteList } from '@/api/table'

export default {
  name: 'ArticleManagement',
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
      noteData: [],
      keyword: "",
      currentPage: 1,
      totalPages: 0,
      pageSize: 15,
      total: 0, //总数量
      listLoading: true
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      let that = this
      this.listLoading = true
      let params = new URLSearchParams()
      params.append("pageSize", that.pageSize)
      params.append("currentPage", that.currentPage)
      if(that.keyword){
        params.append("keyword", that.keyword)
      }
      getNoteList(params).then(response => {
        let data = response.data.list
        this.noteData = data.records
        this.total = data.total
        this.pageSize = data.pageSize
        this.currentPage = data.currentPage
        this.listLoading = false
      })
    }
  }
}
</script>

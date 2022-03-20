<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container" style="margin: 10px 0 10px 0;">

      <el-form :inline="true" label-width="68px" style="margin-bottom: 8px;">
        <el-input
          clearable
          class="filter-item"
          v-model="queryParams.keyword"
          placeholder="请输入关键字"
          @keyup.enter.native="handleFind"
        ></el-input>

        <el-select
          class="filter-item"
          v-model="queryParams.sortKeyword"
          @keyup.enter.native="handleFind"
          clearable
          placeholder="请选择分类">
          <el-option
            v-for="item in noteSortData"
            :key="item.uid"
            :label="item.sortName"
            :value="item.uid"
          ></el-option>
        </el-select>

        <el-select
          class="filter-item"
          v-model="queryParams.tagKeyword"
          filterable
          clearable
          @change="$forceUpdate()"
          placeholder="请选择标签">
          <el-option
            v-for="item in tagData"
            :key="item.uid"
            :label="item.content"
            :value="item.uid"
          ></el-option>
        </el-select>

        <el-select
          class="filter-item"
          v-model="queryParams.publishKeyword"
          clearable
          placeholder="是否发布">
          <el-option value="1" label="是"></el-option>
          <el-option value="0" label="否"></el-option>
        </el-select>

        <el-select
          class="filter-item"
          v-model="queryParams.originalKeyword"
          clearable
          placeholder="是否原创">
          <el-option value="1" label="原创"></el-option>
          <el-option value="0" label="转载"></el-option>
        </el-select>

        <el-button
          class="filter-item"
          type="primary"
          icon="el-icon-search"
          @click="handleFind">查找</el-button>

        <el-button
          class="filter-item"
          type="info"
          @click="resetForm">重置</el-button>

      </el-form>
    </div>

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
          <el-button @click="handleEdit(scope.row)" type="primary" size="small">编辑</el-button>
          <el-button @click="handleDelete(scope.row)" type="danger" size="small">删除</el-button>
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
import { getNoteList, getNoteSort, getTag } from '@/api/table'

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
      currentPage: 1,
      totalPages: 0,
      pageSize: 10,
      total: 0, //总数量
      listLoading: true,
      queryParams:{
        keyword: "",
        tagKeyword: "", //标签搜索
        sortKeyword: "", //分类搜索
        publishKeyword: "", // 发布 搜索
        originalKeyword: "", // 原创 搜索
      }, // 搜索条件
      noteSortData: [],
      tagData: [],
    }
  },
  created() {
    this.fetchData()
    this.getTag()
    this.getNoteSort()
  },
  methods: {
    fetchData() {
      let that = this
      this.listLoading = true
      let params = new URLSearchParams()
      params.append("pageSize", that.pageSize)
      params.append("currentPage", that.currentPage)
      if(this.queryParams.keyword){
        params.append("keyword", this.queryParams.keyword)
      }
      if(this.queryParams.sortKeyword){
        params.append("noteSortUid", this.queryParams.sortKeyword)
      }
      if(this.queryParams.tagKeyword){
        params.append("tagUid", this.queryParams.tagKeyword)
      }
      if(this.queryParams.publishKeyword){
        params.append("isPublish", this.queryParams.publishKeyword)
      }
      if(this.queryParams.originalKeyword){
        params.append("isOriginal", this.queryParams.originalKeyword)
      }
      getNoteList(params).then(response => {
        console.log(response.data.list)
        let data = response.data.list
        this.noteData = data.records
        this.total = data.total
        this.pageSize = data.size
        this.currentPage = data.current
        this.listLoading = false
      })
    },
    handleFind: function() {
      this.currentPage = 1
      this.fetchData();
    },
    getNoteSort() {     // 获取分类
      getNoteSort().then(res => {
        this.noteSortData = res.data.list;
      }).catch(err => {
        this.$message.error(err)
      })
    },
    getTag() {      // 获取标签
      getTag().then(res => {
        this.tagData = res.data.list;
      }).catch(err => {
        this.$message.error(err)
      })
    },
    resetForm() {       // 重置
      this.queryParams = {}
      this.fetchData()
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

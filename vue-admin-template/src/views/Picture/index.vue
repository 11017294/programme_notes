<template>
  <div class="app-container">
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

      <el-table-column label="图片名" width="350" align="center">
        <template slot-scope="scope">
          <span style="cursor:pointer;">{{ scope.row.picName }}</span>
        </template>
      </el-table-column>


      <el-table-column align="center" label="图片" width="120">
        <template slot-scope="scope">
          <img
            :src=" picPath + scope.row.picName"
            style="width: 100px;height: 100px;"
          >
        </template>
      </el-table-column>

      <el-table-column label="操作" fixed="right" min-width="230">
        <template slot-scope="scope">
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
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {deleteImage, getPictureList} from "@/api/table";

export default {
  name: "Picture",
  data() {
    return {
      tableData: [],
      keyword: "",
      currentPage: 1,
      pageSize: 5,
      total: 0, //总数量
      listLoading: false,
      picPath : this.global.file_path,
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      let params = new URLSearchParams()
      params.append("pageSize", this.pageSize)
      params.append("currentPage", this.currentPage)
      getPictureList(params)
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
    handleDelete(row) {
      this.listLoading = true
      let params = new URLSearchParams()
      params.append("uid", row.uid)
      params.append("picName", row.picName)
      deleteImage(params).then(res => {
        this.listLoading = false
        this.$message.success("删除成功")
        this.fetchData()
      }).catch(err => {
        this.$message.error(err)
        this.listLoading = false
      })
    }

  }
}
</script>

<style scoped>

</style>

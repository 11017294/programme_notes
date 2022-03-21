<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container" style="margin: 10px 0 10px 0;">
      <el-input
        clearable
        @keyup.enter.native="handleFind"
        class="filter-item"
        style="width: 200px;"
        v-model="keyword"
        placeholder="请输入标签名"
      ></el-input>
      <el-button
        class="filter-item"
        type="primary"
        icon="el-icon-search"
        @click="handleFind">查找</el-button>

      <el-button
        class="filter-item"
        type="primary"
        @click="handleAdd"
        icon="el-icon-edit">添加标签</el-button>

      <el-button
        class="filter-item"
        type="info"
        @click="resetForm">重置</el-button>
    </div>

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
          <el-button @click="handleEdit(scope.row)" type="primary" size="small">编辑</el-button>
          <el-button @click="handleDelete(scope.row)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--分页-->
    <div class="block">
      <el-pagination
        @current-change="getTagList"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        layout="total, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </div>

    <!-- 添加或修改对话框 -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="form" :rules="rules" ref="form">

        <el-form-item label="标签名" :label-width="formLabelWidth" prop="content">
          <el-input v-model="form.content" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item label="排序" :label-width="formLabelWidth" prop="sort">
          <el-input v-model="form.sort" auto-complete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {deleteTag, getTagList} from '@/api/table'

export default {
  name: 'TagManagement',
  data() {
    return {
      tableData: [],
      keyword: '',
      currentPage: 1,
      totalPages: 0,
      pageSize: 10,
      total: 0, //总数量
      dialogFormVisible: false, //控制弹出框
      formLabelWidth: "120px",
      isEditForm: false,
      form: {
        uid: null,
        content: "",
        sortName: ""
      },
      rules: {
        content: [
          {required: true, message: '分类名称不能为空', trigger: 'blur'},
          {min: 1, max: 10, message: '长度在1到10个字符'},
        ],
        sort: [
          {required: true, message: '排序字段不能为空', trigger: 'blur'},
          {pattern: /^[0-9]\d*$/, message: '排序字段只能为自然数'},
        ]
      }
    }
  },
  created() {
    this.getTagList()
  },
  methods: {
    handleAdd() {
      this.title = "增加标签"
      this.dialogFormVisible = true
      this.form = {}
      this.isEditForm = false
    },
    handleFind() {
      this.currentPage = 1
      this.getTagList()
    },
    handleEdit(row) {
      this.title = "编辑标签"
      this.dialogFormVisible = true
      this.isEditForm = true
      this.form = row
    },
    getTagList() {
      let that = this
      this.listLoading = true
      let params = new URLSearchParams()
      params.append("pageSize", that.pageSize)
      params.append("currentPage", that.currentPage)
      if(this.keyword){
        params.append("keyword", this.keyword)
      }
      getTagList(params).then(response => {
        let data = response.data.list
        this.tableData = data.records
        this.total = data.total
        this.pageSize = data.size
        this.currentPage = data.current
        this.listLoading = false
      })
    },
    resetForm() {       // 重置
      this.keyword = ''
      this.getTagList()
    },
    handleDelete(row) {
      var that = this
      this.$confirm("此操作将把标签删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          var params = {}
          params.uid = row.uid
          deleteTag(params).then(response => {
            that.$message.success("删除成功")
            that.getTagList()
          });
        })
        .catch(() => {
          that.$message.info("已取消删除")
        });
    },
    submitForm() {
      this.$refs.form.validate((valid) => {
        if(!valid) {
          console.log('校验失败')
          return;
        } else {
          if (this.isEditForm) {
            /*editTag(this.form).then(response => {
              this.$message.success("修改标签成功")
              this.dialogFormVisible = false
              this.getTagList()
            }).catch(err => {
              this.$message.error(err)
            })*/
          } else {
           /* addTag(this.form).then(response => {
              this.$message.success("添加标签成功")
              this.dialogFormVisible = false
              this.getTagList()
            }).catch(err => {
              this.$message.error(err)
            })*/
          }
        }
      })
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

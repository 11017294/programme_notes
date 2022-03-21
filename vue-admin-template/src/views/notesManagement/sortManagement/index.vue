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
        placeholder="请输入分类名"
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
        icon="el-icon-edit">添加分类</el-button>

      <el-button
        class="filter-item"
        type="info"
        @click="resetForm">重置</el-button>
    </div>

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
          <el-button @click="handleEdit(scope.row)" type="primary" size="small">编辑</el-button>
          <el-button @click="handleDelete(scope.row)" type="danger" size="small">删除</el-button>
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

    <!-- 添加或修改对话框 -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="分类名" :label-width="formLabelWidth" prop="sortName">
          <el-input v-model="form.sortName" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item label="分类介绍" :label-width="formLabelWidth" prop="content">
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
import {deleteSort, getSortList} from '@/api/table'
import {addNoteSort, editNoteSort} from "@/api/note";

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
      dialogFormVisible: false, //控制弹出框
      formLabelWidth: "120px",
      isEditForm: false,
      form: {
        uid: null,
        content: "",
        sortName: ""
      },
      rules: {
        sortName: [
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
    this.getSortList()
  },
  methods: {
    handleFind: function() {
      this.currentPage = 1
      this.getSortList()
    },
    handleEdit: function(row) {
      this.title = "编辑分类"
      this.dialogFormVisible = true
      this.isEditForm = true
      this.form = row
    },
    handleAdd: function() {
      this.title = "增加分类"
      this.dialogFormVisible = true
      this.form = {}
      this.isEditForm = false
    },
    getSortList() {
      let that = this
      this.listLoading = true
      let params = new URLSearchParams()
      params.append("pageSize", that.pageSize)
      params.append("currentPage", that.currentPage)
      if(this.keyword){
        params.append("keyword", this.keyword)
      }
      getSortList(params).then(response => {
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
      this.getSortList()
    },
    handleDelete(row) {
      var that = this
      this.$confirm("此操作将把分类删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          var params = {}
          params.uid = row.uid
          deleteSort(params).then(response => {
            that.$message.success("删除成功")
            that.getSortList()
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
            editNoteSort(this.form).then(response => {
               this.$message.success("修改分类成功")
               this.dialogFormVisible = false
               this.getSortList()
             }).catch(err => {
               this.$message.error(err)
             })
          } else {
            addNoteSort(this.form).then(response => {
              this.$message.success("添加分类成功")
              this.dialogFormVisible = false
              this.getSortList()
            }).catch(err => {
              this.$message.error(err)
            })
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

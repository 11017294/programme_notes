<template>
  <div class="app-container">
    <div class="filter-container" style="margin: 10px 0 10px 0;">
      <el-input clearable class="filter-item" style="width: 200px;" v-model="keyword" placeholder="用户行为"></el-input>
      <el-date-picker
        clearable
        v-model="value5"
        type="datetimerange"
        :picker-options="pickerOptions2"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        align="right">
      </el-date-picker>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFind">查找</el-button>
    </div>
    <el-table :data="tableData"  style="width: 100%">
      <el-table-column type="selection"></el-table-column>
      <el-table-column label="序号" width="60" align="center">
        <template slot-scope="scope">
          <span >{{scope.$index + 1}}</span>
        </template>
      </el-table-column>

      <el-table-column label="IP" width="150" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.ip }}</span>
        </template>
      </el-table-column>

      <el-table-column label="IP来源" width="200" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.ipSource }}</span>
        </template>
      </el-table-column>

      <el-table-column label="平台" width="150" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.os }}</span>
        </template>
      </el-table-column>

      <el-table-column label="浏览器" width="200" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.browser }}</span>
        </template>
      </el-table-column>

      <el-table-column label="用户行为" width="150" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.behaviorContent }}</span>
        </template>
      </el-table-column>

      <el-table-column label="内容" width="350" align="center">
        <template slot-scope="scope">
<!--          <span  v-html="$xss(scope.row.content, options)"></span>-->
          <span>{{ scope.row.content }}</span>
        </template>
      </el-table-column>

      <el-table-column label="请求时间" width="160" align="center">
        <template slot-scope="scope">
          <span >{{ scope.row.createTime }}</span>
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

    </el-table>

    <!--分页-->
    <div class="block">
      <el-pagination
        @current-change="handleCurrentChange"
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
import {getOperateLog} from "@/api/notesSys";

export default {
  name: "OperateLog",
  data() {
    return {
      // xss白名单配置
      options : {
        whiteList: {
          a: ['href', 'title', 'target', 'style'],
          span: ['class', 'style']
        }
      },
      tableData: [],
      keyword: "",
      currentPage: 1,
      pageSize: 10,
      total: 0, //总数量
      formLabelWidth: "120px",
      pickerOptions2: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            }
          }
        ]
      },
      value5: ""
    };
  },
  created() {
    this.webVisitList();
  },
  methods: {
    handleSizeChange(val) {
      this.pageSize = val
      this.webVisitList()
    },
    webVisitList() {
      var params = {};
      params.keyword = this.keyword;
      params.startTime = "";
      if(this.value5) {
        params.startTime = this.value5[0] + "," + this.value5[1];
      }
      params.pageSize = this.pageSize;
      params.currentPage = this.currentPage;
      getOperateLog(params).then(response => {
        let res = response.data
        this.tableData = res.records;
        console.log(res)
        this.currentPage = res.current;
        this.pageSize = res.size;
        this.total = res.total;
      });
    },
    handleFind() {
      this.currentPage = 1
      this.webVisitList();
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.webVisitList();
    }
  }
}
</script>

<style scoped>

.filter-item {
  margin: 20px;
}
</style>

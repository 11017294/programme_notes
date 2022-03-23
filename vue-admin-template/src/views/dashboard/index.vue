<template>
  <div class="dashboard-container">
    <el-row :gutter="40" class="panel-group">
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel">
          <div class="card-panel-icon-wrapper icon-people" @click="btnClick('1')">
            <i class="el-icon-user-solid" style="font-size:45px" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              当前用户数: {{userCount}}
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel">
          <div class="card-panel-icon-wrapper icon-message" @click="btnClick('2')">
            <i class="el-icon-tickets" style="font-size:45px" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              当前博客数: {{noteCount}}
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel">
          <div class="card-panel-icon-wrapper icon-money" @click="btnClick('3')">
            <i class="el-icon-chat-line-square" style="font-size:45px" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              总标签数: {{tagCount}}
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel">
          <div class="card-panel-icon-wrapper icon-shopping" @click="btnClick('4')">
            <i class="el-icon-view" style="font-size:45px" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              今日IP数: {{ipCount}}
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
    <!--文章贡献度-->
    <el-row>
      <CalendarChart></CalendarChart>
    </el-row>

    <!--访问量统计-->
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart v-if="showLineChart" :chart-data="lineChartData"></line-chart>
    </el-row>
  </div>
</template>

<script>

import CalendarChart from '@/components/CalendarChart'
import LineChart from '@/components/LineChart'
import { getNotesCount, getUserCount, getTagCount, getViewingCount, getVisitByWeek } from '@/api/notesSys'

export default {
  name: 'Dashboard',
  data() {
    return {
      userCount: '', // 用户数
      noteCount: '', // 文章数
      tagCount: '', // 标签数
      ipCount: '', // 浏览数
      lineChartData: {},
      showLineChart: {}
    }
  },
  components: {
    CalendarChart,
    LineChart
  },
  created() {
    this.getUserCount()
    this.getTagCount()
    this.getNoteCount()
    this.getIpCount()
    this.getVisitByWeek()
  },
  methods: {
    // 获取用户数
    getUserCount() {
      getUserCount().then(response => {
        this.userCount = response.data.count
      }).catch(err => {
        this.$message.error(err)
      })
    },
    // 获取标签数
    getTagCount() {
      getNotesCount().then(response => {
        this.noteCount = response.data.count
      }).catch(err => {
        this.$message.error(err)
      })
    },
    // 获取笔记数
    getNoteCount() {
      getTagCount().then(response => {
        this.tagCount = response.data.count
      }).catch(err => {
        this.$message.error(err)
      })
    },
    // 获取ip数
    getIpCount() {
      getViewingCount().then(response => {
        this.ipCount = response.data.count
      }).catch(err => {
        this.$message.error(err)
      })
    },
    // 折线图
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    },
    // 一周内文章贡献统计图
    getVisitByWeek() {
      getVisitByWeek().then(response => {
        var visitByWeek = response.data.res
        var lineChartData = {
          date: visitByWeek.date,
          expectedData: visitByWeek.pv,
          actualData: visitByWeek.uv
        }
        this.lineChartData = lineChartData
        this.showLineChart = true
      })
    },
    // 点击跳转
    btnClick(type) {
      switch (type) {
        case "1":
        {
          this.$router.push({ path: "/user/userManagement" });
        }
          break;
        case "2":
        {
          this.$router.push({ path: "/notesManagement/articleManagement" });
        }
          break;
        case "3":
        {
          this.$router.push({ path: "/notesManagement/tagManagement" });
        }
          break;
        case "4":
        {
          this.$router.push({ path: "/operateLog/operateLog" });
        }
          break;
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}

.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, 0.05);
    border-color: rgba(0, 0, 0, 0.05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-message {
        background: #36a3f7;
      }

      .icon-money {
        background: #f4516c;
      }

      .icon-shopping {
        background: #34bfa3;
      }
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #34bfa3;
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width: 550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>

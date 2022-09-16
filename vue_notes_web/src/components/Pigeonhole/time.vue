<template>
    <el-container>
        <el-aside>
            <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
                <el-timeline>
                    <el-timeline-item
                        v-for="item in noteTimeData"
                        :key="item">
                        <span
                            @click="getSortList(item)"
                            :class="[item == selectContent ? 'sortBoxSpan sortBoxSpanSelect' : 'sortBoxSpan']"
                        >{{item}}</span>
                    </el-timeline-item>
                </el-timeline>
            </ul>
        </el-aside>
        <el-main>
            <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
                <el-card class="box-card" v-for="item in noteData">
                    <h1 class="entry-title">
                        <a href="javascript:void(0);" @click="goToInfo(item.uid)" v-html="item.title">{{item.title}}</a>
                    </h1>
                </el-card>
            </ul>
        </el-main>
    </el-container>
</template>

<script>

import {getArticleByMonth, getNoteByTime, getNoteList} from "@/api";

export default {
    name: "time",
    data() {
        return {
            selectContent: '',
            noteTimeData: [],
            oldSelect: '',
            noteData: [],
        };
    },
    methods: {
        getNoteByTime() {
            getNoteByTime()
                .then(res => {
                    this.getSortList(res.data[0])
                    this.noteTimeData = res.data
                }).catch(err => {
                    this.$message.error(err);
                })
        },
        getSortList(month) {
            this.selectContent = month;
            // 点击不同标签
            if(month != this.oldSelect){
                // 清除旧数据
                this.noteData = [];
                this.fetchList(month);
                this.oldSelect = month;
            }
        },
        fetchList(month) {
            let params = new URLSearchParams();
            params.append("monthDate", month);
            getArticleByMonth(params)
                .then(res => {
                    this.noteData = res.data
                })
                .catch(err => {
                    this.$message.error(err)
                })
        },
        goToInfo(uid) {
            this.$router.push({
                path: "/article",
                query: {uid: uid}
            });
        },
        load () {
            this.count += 2
        }
    },
    mounted() {
        this.getNoteByTime();
    }
}
</script>

<style scoped>

.sortBoxSpan {
    cursor: pointer;
}
.sortBoxSpan:hover {
    color: #409eff;
}
.sortBoxSpanSelect {
    color: #409eff;
}

.el-timeline-item a{
    cursor:pointer
}

.el-main {
    margin: 1px;
    padding: 5px;
    height: 600px;
    line-height: 36px;
    background-color: #ffffff;
}

.el-timeline-item a{
    cursor:pointer
}

.el-card {
    margin: 10px;
    padding: 10px 50px;
}

.more {
    text-align: center;
}

.entry-title {
    font-weight: 600;
}
</style>

<template>
    <el-container class="sort">
        <el-aside>
            <el-timeline>
                <el-timeline-item
                    v-for="item in sortData"
                    :key="item.uid">
                    <a @click="getSortList(item.uid)">{{item.sortName}}</a>
                </el-timeline-item>
            </el-timeline>
        </el-aside>
        <el-main>
            <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
                <el-card class="box-card" v-for="item in noteData">
                    <h1 class="entry-title">
                        <router-link :to="`/article/${item.uid}`">{{ item.title }}</router-link>
                    </h1>
                </el-card>
            </ul>
            <!--加载更多-->
            <div class="more" v-show="total&&!isEnd">
                <div class="more-btn" @click="loadMore">查看更多</div>
            </div>
            <div class="more" v-show="!total">
                <div>该分类的笔记空空如也</div>
            </div>
        </el-main>
    </el-container>
</template>

<script>

import {getNoteList, getNoteSort} from "@/api";

export default {
    name: "sort",
    data() {
        return {
            sortData: [],
            oldSortId: '',
            noteData: [],
            currentPage: 1,
            totalPages: 0,
            pageSize: 10,
            total: 0, //总数量
            searchNoteData: [], //搜索出来的文章
            isEnd: false,
            count: 0
        };
    },
    methods: {
        getNoteSort() {
            getNoteSort()
                .then(res => {
                    this.getSortList(res.data.list[0].uid)
                    this.sortData = res.data.list;
                }).catch(err => {
                this.$message.error(err);
            })
        },
        getSortList(uid) {
            // 点击不同分类
            if(uid != this.oldSortId){
                // 清除旧数据
                this.noteData = [];
                this.currentPage = 1;
                this.totalPages = 0;
                this.pageSize = 10;
                this.total = 0;
                this.searchNoteData = [];
                this.isEnd = false;
                this.fetchList(uid);
                this.oldSortId = uid;
            }
        },
        loadMore() {
            let that = this;
            let params = new URLSearchParams();
            params.append("pageSize", this.pageSize);
            params.append("currentPage", this.currentPage + 1);
            params.append("noteSortUid", that.$store.state.userInfo.userUid);
            getNoteList(params)
                .then(res => {
                    that.convertSearchData(that, res)
                })
                .catch(err => {
                    that.$message.error(err)
                })
        },
        fetchList(uid) {
            let that = this;
            let params = new URLSearchParams();
            params.append("pageSize", that.pageSize);
            params.append("currentPage", that.currentPage);
            params.append("noteSortUid", uid);
            getNoteList(params)
                .then(res => {
                    that.convertSearchData(that, res)
                })
                .catch(err => {
                    that.$message.error(err)
                })
        },
        convertSearchData(that, response) {
            if (response.code == 0) {
                let data = response.data.list;
                that.isEnd = false;
                //获取总页数
                that.totalPages = data.records.length;
                that.total = data.total;
                that.pageSize = data.size;
                that.currentPage = data.current;
                let noteData = data.records;
                that.noteData = noteData;
                // 判断搜索的笔记是否有内容
                if(response.data.total <= 0) {
                    that.isEnd = true;
                    this.noteData = []
                    return;
                }
                //全部加载完毕
                if (noteData.length < that.pageSize) {
                    that.isEnd = true;
                }
                noteData = that.searchNoteData.concat(noteData);
                that.searchNoteData = noteData;
                this.noteData = noteData;
            }
            else {
                that.isEnd = true;
            }
        },
        load () {
            this.count += 2
        }
    },
    mounted() {
        this.getNoteSort();
    }
}
</script>

<style scoped>

.el-main {
    margin: 1px;
    padding: 5px;
    height: 600px;
    line-height: 36px;
    background-color: #ffffff;
}

.sort {
    line-height: 20px;
    height: 600px;
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

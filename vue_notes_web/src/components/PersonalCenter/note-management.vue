<template>
    <!--文章列表-->
    <main class="site-main">
        <el-card class="box-card" v-for="item in noteData">
            <el-col :span="21">
                <h1 class="entry-title">
                    <a href="javascript:void(0);" @click="goToInfo(item.uid)" v-html="item.title">{{item.title}}</a>
                </h1>
                <p class="summary">{{ item.summary }}</p>
                <div class="item-float-left" v-if="item.noteSortUid">
                    分类：<a href="javascript:void(0);">{{ item.noteSortName }}</a>
                </div>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" @click="editNote(item.uid)">编辑</el-button>
            </el-col>
            <el-col :span="1">
                <el-button type="warning" @click="deleteNote(item.uid)">删除</el-button>
            </el-col>
        </el-card>

        <!--加载更多-->
        <div class="more" v-show="total&&!isEnd">
            <div class="more-btn" @click="loadMore">查看更多</div>
        </div>
        <div class="more" v-show="!total&&isEnd">
            <el-empty description="什么都没有~"/>
        </div>
    </main>
</template>

<script>
import {deleteNote, getNotesById} from "@/api";

export default {
    name: "noteManagement",
    data() {
        return {
            noteData: [],
            currentPage: 1,
            totalPages: 0,
            pageSize: 10,
            total: 0, //总数量
            searchNoteData: [], //搜索出来的文章
            isEnd: false
        }
    },
    methods: {
        goToInfo(uid) {
            this.$router.push({
                path: "/article",
                query: {uid: uid}
            });
        },
        editNote(id) {
            this.$router.push({
                path: '/takeNotes',         // 待跳转的页面URL
                query: {uid: id},              // 跳转时传入的参数
            });
        },
        deleteNote(id) {
            deleteNote({uid: id})
                .then(res => {
                    this.$message.success("删除成功")
                    this.$router.go(0)
                })
                .catch(err => {
                    this.$message.error(err)
                })
        },
        loadMore() {
            let that = this;
            var params = new URLSearchParams();
            params.append("pageSize", this.pageSize);
            params.append("currentPage", this.currentPage + 1);
            params.append("userUid", that.$store.state.userInfo.userUid);
            getNotesById(params)
                .then(res => {
                    that.convertSearchData(that, res)
                })
                .catch(err => {
                    that.$message.error(err)
                })
        },
        fetchList() {
            let that = this;
            var params = new URLSearchParams();
            params.append("pageSize", that.pageSize);
            params.append("currentPage", that.currentPage);
            params.append("userUid", that.$store.state.userInfo.userUid);
            getNotesById(params)
                .then(res => {
                    that.convertSearchData(that, res)
                })
                .catch(err => {
                    that.$message.error(err)
                })
        },
        convertSearchData(that, response) {
            if (response.code == 200) {
                let data = response.data;
                that.isEnd = false;
                //获取总页数
                that.totalPages = data.records.length;
                that.total = data.total;
                that.pageSize = data.size;
                that.currentPage = data.current;
                var noteData = data.records;
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
        }
    },
    mounted() {
        this.fetchList();
    }
}
</script>

<style scoped>

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

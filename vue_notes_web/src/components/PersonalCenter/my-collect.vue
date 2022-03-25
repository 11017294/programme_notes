<template>
    <main class="site-main">
        <template v-for="item in noteData">
            <post :post="item" :key="item.id"></post>
        </template>

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
import {getCollectNoteByUserId, getNotesById} from "@/api";
import Banner from "@/components/banner";
import Feature from "@/components/feature";
import sectionTitle from "@/components/section-title";
import Post from "@/components/post";
import SmallIco from "@/components/small-ico";
import Quote from "@/components/quote";

export default {
    name: "myCollect",
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
    components: {
        Post,
    },
    methods: {
        loadMore() {
            let that = this;
            var params = new URLSearchParams();
            params.append("pageSize", this.pageSize);
            params.append("currentPage", this.currentPage + 1);
            params.append("userUid", that.$store.state.userInfo.userUid);
            getCollectNoteByUserId(params)
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
            getCollectNoteByUserId(params)
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

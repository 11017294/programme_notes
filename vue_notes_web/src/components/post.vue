<template>
    <el-card class="box-card">
        <el-row>
            <el-col :min-span="6">
            <h1 class="entry-title">
                <a href="javascript:void(0);" @click="goToInfo(post.uid)" v-html="post.title">{{post.title}}</a>
            </h1>
            </el-col>
        </el-row>
        <el-row>
            <el-col class="box-summary">
                <p class="summary">{{ post.summary }}</p>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="1.5">
                <el-avatar :size="30" v-if="post.userAvatar" :src="this.global.file_path + post.userAvatar">
                </el-avatar>
                <el-avatar :size="30" v-else="post.userAvatar">
                    <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
                </el-avatar>
            </el-col>
            <el-col :span="2">
                    <span>{{ post.author }}</span>
            </el-col>
            <el-col :span="5">
                分类：<a href="javascript:void(0);">{{ post.noteSortName }}</a>
            </el-col>
            <el-col :span="3">
<!--                <el-tag
                    style="margin-left: 3px"
                    type="warning"
                    v-if="item"
                    :key="index"
                    v-for="(item, index) in post.tagList"
                >{{item.content}}</el-tag>-->
            </el-col>
            <el-col :span="3">
                <span><i class="iconfont iconeyes"></i> {{ post.clickCount }}</span>
            </el-col>
            <el-col :span="3">
                <div @click="collect" :class="[collectClass]">
                    <span><i class="iconfont icon-shoucang"></i> {{ post.collectCount }}</span>
                </div>
            </el-col>
            <el-col :span="5">
                <span class="iconfont">&#xe603;</span> {{ post.createTime | parseTime }}
            </el-col>
        </el-row>
    </el-card>
</template>

<script>
import '../assets/iconfont/iconfont.css'
import {getUserCollectNote, userCollectNote} from "@/api";

export default {
    name: "post",
    props: {
        post: {
            type: Object
        }
    },
    data() {
        return {
            isCollect : false,
        }
    },
    computed: {
        collectClass() {
            if(this.isCollect){
                return "inCollect";
            } else {
                return "noCollect";
            }
        }
    },
    methods: {
        collect() {
            if(this.$store.state.isLogin){
                this.isCollect = !this.isCollect;
                userCollectNote({"noteUid": this.post.uid})
                    .then(res => {
                        this.post.collectCount = res.data.collectCount;
                    })
                    .catch(err => {})
            } else{
                this.$message.warning("你未登录还不能收藏笔记")
            }
        },
        goToInfo(uid) {
            this.$router.push({
                path: "/article",
                query: {uid: uid}
            });
        }
    },
    mounted() {
        if(this.$store.state.isLogin){
            getUserCollectNote({"noteUid": this.post.uid})
                .then(res => {
                    this.isCollect = res.data != null;
                })
                .catch(err => {})
        } else {
            this.isCollect = false;
        }
    }
}
</script>

<style scoped lang="less">

.noCollect {
    cursor: pointer;
}
.inCollect {
    cursor: pointer;
    color: orange;
}

.box-card {
    margin: 10px 0;
    line-height: 1.8;
}

.entry-title {
    font-weight: 600;
}

.box-summary {
    min-height: 80px;
    padding: 10px 0;
}
.cs{

}
</style>

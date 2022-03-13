<template>
    <el-card class="box-card">
        <el-row>
            <el-col :min-span="6">
            <h1 class="entry-title">
                <router-link :to="`/article/${post.uid}`">{{ post.title }}</router-link>
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
            this.isCollect = !this.isCollect;
            userCollectNote({"noteUid": this.post.uid})
                .then(res => {
                    this.post.collectCount = res.data.collectCount;
                })
                .catch(err => {})
        }
    },
    mounted() {
        if(this.$store.state.isLogin){
            getUserCollectNote({"noteUid": this.post.uid})
                .then(res => {
                    console.log(res.data.collect)
                    if(res.data.collect){
                        this.isCollect = true;
                    } else {
                        this.isCollect = false;
                    }
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
/*
.notes-info {
    align-items: center;
    line-height: 1.8;
    margin: 15px 0px;
}

.author {
    background: url(http://101.132.194.128:8600/blog/admin/jpg/2019/9/1/auicon.jpg) no-repeat 0 0
}

.post {
    margin: 0 0 4% 0;
    position: relative;
}

.entry-footer .item-float-left {
    float: left;
    margin-right: 20px;
}

.entry-footer .item-float-right {
    float: right;
}

.post-entry {
    .feature {
        position: absolute;
        margin-top: 10px;

        img {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border-radius: 50%;
            padding: 2px;
            border: 1px solid #DADADA;
            position: relative;
            transition: all 0.2s linear;
            overflow: hidden;

            &:hover {
                transform: scale(1.1, 1.1);
                filter: contrast(130%);
            }
        }
    }

    .entry-title {
        font-size: 21px;
        font-weight: 600;
        line-height: 50px;
        margin: 0 0 0 17%;
        position: relative;
        z-index: 1;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        width: 70%;
        color: #737373;

        & a:hover {
            color: #8fd0cc;
        }
    }

    .p-time {
        position: absolute;
        right: 0;
        top: 16px;
        font-size: 12px;
        color: #989898;
        letter-spacing: 1px;
        font-family: din, 'Hiragino Sans GB', 'Microsoft Yahei', Arial, sans-serif;
        display: flex;
        align-items: center;

        .post-more {
            margin-top: 10px;
            text-align: right;
            color: #A0DAD0;

            a:hover {
                color: #737373;
            }
        }
    }

    p.summary {
        min-height: 60px;
        margin: 0 0 0 17%;
        font-size: 15px;
        color: #6f6f6f;
        letter-spacing: 1px;
        line-height: 30px;
    }

    footer.entry-footer {
        margin: 0 0 0 17%;
        list-style: none;

        .info-meta {
            margin-top: 10px;
            font-family: din, 'Hiragino Sans GB', 'Microsoft Yahei', Arial, sans-serif;
            position: absolute;
            top: 20px;
            opacity: 0;
            padding-top: 8px;
            border-top: 1px solid #ddd;
            -webkit-transform: translate3d(-150px, 0, 0);
            transform: translate3d(-150px, 0, 0);
            visibility: hidden;
            transition: .7s all ease;
            -webkit-transition: .6s all ease;
            -moz-transition: .6s all linear;
            -o-transition: .6s all ease;
            -ms-transition: .6s all ease;

            span, a {
                color: #B3B3B3;
                font-size: 12px;
            }

            i {
                margin-top: 3px;
                margin-right: 10px;
                float: left
            }
        }

        .comnum {
            float: left;
        }
    }

}

.post-entry:hover footer.entry-footer .info-meta {
    -webkit-transform: translate3d(-230px, 0, 0);
    transform: translate3d(-230px, 0, 0);
    opacity: 1;
    visibility: visible;
}

.post hr {
    width: 30%;
    height: 1px;
    margin: 0 auto;
    border: 0;
    background: #EFEFEF;
}

!*******!
@media (max-width: 1060px) {
    .info-meta {
        display: none;
    }
}

@media (max-width: 800px) {
    .post-entry {
        .feature img {
            width: 50px;
            height: 50px;
        }

        .entry-title {
            font-size: 16px;
            line-height: 30px;
            margin: 0 0 0 65px;
        }

        .p-time {
            position: relative;
            margin: -15px 0 0 65px;
        }

        p.summary {
            margin: 20px 0 0 65px;
            font-size: 14px;
            height: 30px;
            overflow: hidden;
        }

        .post-more {
            display: none;
        }
    }

    .post-list hr {
        margin-top: 20px;
    }
}*/
</style>

<template>
    <article class="post post-list">
        <div class="post-entry">
            <div class="feature">
                <router-link :to="`/article/${post.uid}`">
                    <img :src="post.photoUrl"/>
                </router-link>
            </div>
            <h1 class="entry-title">
                <!--                <router-link :to="`/article/${post.id}`"><span v-if="post.isTop" style="color:#ff6d6d;font-weight:600">[置顶] </span>{{post.title}}</router-link>-->
                <router-link :to="`/article/${post.uid}`">{{ post.title }}</router-link>
            </h1>
            <div class="p-time">
                <div class="post-more item-float-right">
                    <router-link :to="`/article/${post.uid}`">
                        <i class="iconfont iconfish-li" style="font-size: 25px;"></i>
                    </router-link>
                </div>
            </div>
            <p class="summary">{{ post.summary }}</p>
            <footer class="entry-footer">
                <div class="notes-info">
                    <div class="author item-float-left">
                        <div class="item-float-left">
                            <router-link :to="`/article/${post.uid}`">
<!--                                <img :src="post.banner"/>-->
                                <el-avatar :size="30" class="item-float-left" v-if="post.author"
                                           src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn15%2F240%2Fw640h400%2F20180413%2Fd7da-fytnfyp4017293.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1649072353&t=0c915b5df3fece3c17aac06082b455c1">
                                </el-avatar>
                                <el-avatar :size="30" class="item-float-left" v-else="post.author">
                                    <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
                                </el-avatar>
                                <div class="item-float-left">
                                    <span>{{ post.author }}</span>
                                </div>
                            </router-link>

                        </div>
                    </div>
                    <div class="item-float-left" v-if="post.noteSortUid">
                        分类：<a href="javascript:void(0);">{{ post.noteSortStr }}</a>
                    </div>
                    <div class="createTime item-float-left"><span class="iconfont">&#xe603;</span>{{ post.createTime | parseTime }}</div>
                </div>
                <div class="info-meta">
                    <div class="comnum">
                      <span>
                        <i class="iconfont iconcomment"></i>
                        <a href="https://zhebk.cn/Web/Akina.html">{{ post.commentsCount }} 条评论</a>
                      </span>
                    </div>
                    <div class="views">
                        <span><i class="iconfont iconeyes"></i>{{ post.viewsCount }} 热度</span>
                    </div>
                </div>
            </footer>
        </div>
        <hr/>
    </article>
</template>

<script>

export default {
    name: "post",
    props: {
        post: {
            type: Object
        }
    }
}
</script>

<style scoped lang="less">
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

/*******/
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
}
</style>

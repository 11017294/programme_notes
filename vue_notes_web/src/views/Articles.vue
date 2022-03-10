<template>
    <div class="articles">

        <div class="site-content animate">
            <!-- 文章目录 -->
            <!--            <div id="article-menus">
                            <menu-tree :menus="menus" child-label="child"></menu-tree>
                        </div>-->
            <main class="site-main">
                <article class="hentry">
                    <!-- 文章头部 -->
                    <header class="entry-header">
                        <!-- 标题输出 -->
                        <el-row>
                            <h1 class="entry-title">{{ noteData.title }}</h1>
                        </el-row>
                        <el-row  class="breadcrumbs">
                            <el-col :span="1.5">
                                <el-avatar :size="30" v-if="noteData.author"
                                           src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn15%2F240%2Fw640h400%2F20180413%2Fd7da-fytnfyp4017293.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1649072353&t=0c915b5df3fece3c17aac06082b455c1">
                                </el-avatar>
                                <el-avatar :size="30" v-else="post.author">
                                    <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
                                </el-avatar>
                            </el-col>
                            <el-col :span="2">
                                <span>{{ noteData.author }}</span>
                            </el-col>
                            <el-col :span="5">
                                分类：<a href="javascript:void(0);">{{ noteData.noteSortName }}</a>
                            </el-col>
                            <el-col :span="3">
                                <span><i class="iconfont iconeyes"></i> {{ noteData.clickCount }}</span>
                            </el-col>
                            <el-col :span="3">
                                <span><i class="iconfont icon-shoucang"></i> {{ noteData.collectCount }}</span>
                            </el-col>
                            <el-col :span="5">
                                <span class="iconfont">&#xe603;</span> {{ noteData.createTime | parseTime }}
                            </el-col>
                        </el-row>


                    </header>
                    <!-- 正文输出 -->
                    <mavon-editor
                        class="md"
                        :value="noteData.content"
                        :subfield="false"
                        :defaultOpen="'preview'"
                        :toolbarsFlag="false"
                        :external-link="externalLink"
                        :editable="false"
                        :scrollStyle="true"
                        :ishljs="true"
                    />
                    <!-- 文章底部 -->
                    <section-title>
                        <footer class="post-footer">

<!--                            <div class="donate" @click="showDonate=!showDonate">
                                <span>赏</span>
                                <ul class="donate_inner" :class="{'show':showDonate}">
                                    <li class="wedonate"><img src="http://cdn.fengziy.cn/gblog/wexin_pay.png">
                                        <p>微信</p></li>
                                    <li class="alidonate"><img src="http://cdn.fengziy.cn/gblog/ali_pay.jpg">
                                        <p>支付宝</p></li>
                                </ul>
                            </div>
                             文章标签-->
                            <div class="post-tags">
                                <i class="iconfont iconcategory"></i>
                                <router-link to="/category/web">Web</router-link>
                            </div>
                        </footer>
                    </section-title>
                    <!--声明-->
                    <div class="open-message">
                        <p>声明：编程笔记|版权所有，违者必究|如未注明</p>
                        <p>转载：转载请注明原文链接<a href="/"></a></p>
                    </div>
                    <!--评论-->
<!--
                    <div class="comments">
                        <comment v-for="item in comments" :key="item.comment.id" :comment="item.comment">
                            <template v-if="item.reply.length">
                                <comment v-for="reply in item.reply" :key="reply.id" :comment="reply"></comment>
                            </template>
                        </comment>
                    </div>
                    -->
                </article>
            </main>
        </div>
    </div>
</template>

<script>
import Banner from '@/components/banner'
import sectionTitle from '@/components/section-title'
import comment from '@/components/comment'
import menuTree from '@/components/menu-tree'
import {fetchComment, fetchContent} from '../api'

export default {
    name: 'articles',
    data() {
        return {
            showDonate: false,
            comments: [],
            menus: [],
            noteData: {},
            noteUid: null,
            externalLink: {     // 需要配置的内容
                markdown_css: () => '/md/markdown/github-markdown.min.css',
                hljs_js: () => '/md/highlightjs/highlight.min.js',
                hljs_css: (css) => '/md/highlightjs/styles/' + css + '.min.css',
                hljs_lang: (lang) => '/md/highlightjs/languages/' + lang + '.min.js',
                katex_css: () => '/md/katex/katex.min.css',
                katex_js: () => '/md/katex/katex.min.js',
            },
        }
    },
    components: {
        Banner,
        sectionTitle,
        comment,
        menuTree
    },
    methods: {
        getComment() {
            fetchComment().then(res => {
                this.comments = res.data || []
            }).catch(err => {
                console.log(err)
            })
        },
        fetchH(arr, left, right) {
            if (right) {
                return arr.filter(item => item.offsetTop > left && item.offsetTop < right)
            } else {
                return arr.filter(item => item.offsetTop > left)
            }
        },
        fetchContent() {
            let that = this;
            fetchContent({uid: that.noteUid}).then(res => {
                that.noteData = res.data.note
            })
        },
    },
    mounted() {
        this.fetchContent();
    },
    created() {
        this.noteUid = this.$route.params.uid;
    }
}
</script>
<style scoped lang="less">
.site-content {
    position: relative;

    .site-main {
        padding: 80px 0 0 0;
    }
}

#article-menus {
    position: sticky;
    top: 0;
    box-shadow: 0 2px 6px rgba(0, 0, 0, .1);
    border-radius: 3px;
    padding: 15px;
    width: 300px;
    transform: translateX(-120%) translateY(150px);
    font-size: 14px;
}

article.hentry {
    .entry-header {
        .entry-title {
            font-size: 23px;
            font-weight: 600;
            color: #737373;
            margin: 0.67em 0;

            &:before {
                content: "#";
                margin-right: 6px;
                color: #d82e16;
                font-size: 20px;
                font-weight: 600;
            }
        }

        hr {
            height: 1px;
            border: 0;
            background: #EFEFEF;
            margin: 15px 0;
        }

        .breadcrumbs {
            color: #D2D2D2;
            text-decoration: none;
            margin-bottom: 30px;
            line-height: 1.8;
        }
    }

    footer.post-footer {
        width: 100%;
        padding: 20px 10px;
        margin-top: 30px;
        height: 65px;
        position: relative;

        i {
            font-size: 18px;
            margin-right: 5px;
        }

        .post-share {
            float: right;
            list-style: none;
            margin-right: 20px;
        }

        .donate {
            float: left;
            line-height: 36px;
            border-radius: 100%;
            -webkit-border-radius: 100%;
            -moz-border-radius: 100%;
            border: 1px solid #2B2B2B;

            &:hover {
                border: 1px solid goldenrod;

                span {
                    color: goldenrod;
                }
            }

            span {
                color: #2B2B2B;
                padding: 10px;
                position: relative;
                cursor: pointer;
            }

            .donate_inner {
                display: none;
                margin: 0;
                list-style: none;
                position: absolute;
                left: 80px;
                top: -40px;
                background: #FFF;
                padding: 10px;
                border: 1px solid #ddd;
                box-shadow: 0 2px 6px rgba(0, 0, 0, .08);
                border-radius: 3px;

                &.show {
                    display: block;
                }

                li {
                    float: left;
                }

                img {
                    width: 100px;
                }

                p {
                    text-align: center;
                    font-size: 15px;
                    color: #D2D2D2;
                    line-height: 1rem;
                }
            }

            .donate_inner:after, .donate_inner:before {
                content: "";
                position: absolute;
                left: 0;
                bottom: 45%;
                margin-left: -8px;
                border-top: 8px solid transparent;
                border-bottom: 8px solid transparent;
                border-right: 8px solid #fff;
            }

            .donate_inner:before {
                left: -1px;
                border-right: 8px solid #ddd;
            }

        }

        .post-tags {
            margin: 7px 0 0 20px;
            float: left;
            text-transform: uppercase;

            a:hover {
                color: #ff6d6d;
            }
        }
    }

    .open-message {
        margin: 50px 0;
        position: relative;
        background: #2B2B2B;
        padding: 10px 30px;
        border-radius: 3px;
        font-size: 14px;
        color: #fff;

        &:after {
            content: "";
            border-left: 10px solid transparent;
            border-right: 10px solid transparent;
            border-bottom: 10px solid #2B2B2B;
            position: absolute;
            top: -8px;
            left: 48%;
        }

        p {
            margin: 10px 0;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }

        a {
            color: #A0DAD0;
            padding: 0 5px;
        }
    }
}
</style>

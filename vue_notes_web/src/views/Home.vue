<template>
    <div class="home">
        <banner isHome="true"></banner>
        <div class="site-content animate">
            <!--通知栏-->
            <div class="notify">
                <div class="search-result" v-if="hideSlogan">
                    <span v-if="keyword">搜索结果："{{keyword}}" 相关文章</span>
                    <span v-else-if="category">分类 "{{category}}" 相关文章</span>
                </div>
            </div>

            <!--文章列表-->
            <main class="site-main" :class="{'search':hideSlogan}">
                <section-title v-if="!hideSlogan">推荐</section-title>
                <template v-for="item in noteData">
                    <post :post="item" :key="item.id"></post>
                </template>
            </main>

            <!--加载更多-->
            <div class="more" v-show="!isEnd">
                <div class="more-btn" @click="loadMore">查看更多</div>
            </div>
        </div>
    </div>
</template>

<script>
    import Banner from '@/components/banner'
    import Feature from '@/components/feature'
    import sectionTitle from '@/components/section-title'
    import Post from '@/components/post'
    import SmallIco from '@/components/small-ico'
    import Quote from '@/components/quote'
    import {authVerify, fetchFocus, fetchList} from '../api'

    export default {
        name: 'Home',
        props: ['cate', 'words'],
        data() {
            return {
               // features: [],
                noteData: [],
                keyword: "",
                currentPage: 1,
                totalPages: 0,
                pageSize: 10,
                total: 0, //总数量
                tagUid: "",
                searchNoteData: [], //搜索出来的文章
                sortUid: "",
                isEnd: false
            }
        },
        components: {
            Banner,
            Feature,
            sectionTitle,
            Post,
            SmallIco,
            Quote
        },
        watch:{
            $route(to,from) {
                this.keyword = to.params.keyword
                this.currentPage = 1
                this.pageSize = 10
                this.noteData = []
                this.searchNoteData = []
                this.fetchList()
            }
        },
        computed: {
            /*keywords() {
                return this.$route.query.keyword
            },*/
            category() {
                return this.$route.params.cate
            },
            hideSlogan() {
                return this.category || this.searchWords
            },
            notice() {
                return this.$store.getters.notice
            }
        },
        methods: {
            fetchFocus() {
                fetchFocus().then(res => {
                    this.features = res.data || []
                }).catch(err => {
                    console.log(err)
                })
            },
            fetchList() {
                let that = this;
                var params = new URLSearchParams();
                params.append("pageSize", that.pageSize);
                params.append("currentPage", that.currentPage);
                if(that.keyword){
                    params.append("keyword", that.keyword);
                }
                fetchList(params).then(res => {
                    that.convertSearchData(that, res)
                }).catch(err => {
                    console.log(err)
                })
            },
            loadMore() {
                let that = this;
                var params = new URLSearchParams();
                params.append("pageSize", this.pageSize);
                params.append("currentPage", this.currentPage + 1);
                if(that.keyword){
                    params.append("keyword", that.keyword);
                }
                fetchList(params).then(res => {
                    that.convertSearchData(that, res)
                }).catch(err => {
                    console.log(err)
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
            },
        },
        mounted() {
            this.fetchFocus();
            this.fetchList();
        }
    }
</script>
<style scoped lang="less">

    .site-content {
        .notify {
            margin: 60px 0;
            border-radius: 3px;
            & > div {
                padding: 20px;
            }
        }


        .search-result {
            padding: 15px 20px;
            text-align: center;
            font-size: 20px;
            font-weight: 400;
            border: 1px dashed #ddd;
            color: #828282;
        }
    }

    .top-feature {
        width: 100%;
        height: auto;
        margin-top: 30px;

        .feature-content {
            margin-top: 10px;
            display: flex;
            justify-content: space-between;
            position: relative;

            .feature-item {
                width: 32.9%;
            }
        }
    }

    .site-main {
        padding-top: 80px;

        &.search {
            padding-top: 0;
        }
    }

    .more{
        margin: 50px 0;
        .more-btn{
            width: 100px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            color: #ADADAD;
            border: 1px solid #ADADAD;
            border-radius: 20px;
            margin: 0 auto;
            cursor: pointer;
            &:hover{
                color: #8fd0cc;
                border: 1px dashed #8fd0cc;
            }
        }
    }

    /******/
    @media (max-width: 800px) {
        .top-feature {
            display: none;
        }

        .site-main {
            padding-top: 40px;
        }

        .site-content {
            .notify {
                margin: 30px 0 0 0;
            }

            .search-result {
                margin-bottom: 20px;
                font-size: 16px;
            }
        }
    }

    /******/
</style>

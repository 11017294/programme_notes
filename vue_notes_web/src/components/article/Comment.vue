<template>
    <div>
        <div class="comment-title"><i class="iconfont "/>评论</div>
        <!-- 评论框 -->
        <el-row>
            <el-col :span="24">
                <div class="comment-input">
                    <el-input
                        type="textarea"
                        :rows="10"
                        show-word-limit
                        maxlength="500"
                        resize="none"
                        placeholder="留下点什么吧..."
                        v-model="commentContent">
                    </el-input>
                </div>
            </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="end">
            <el-col :span="2">
                <el-button style="margin:10px 0px;" type="primary" @click="comment">评论</el-button>
            </el-col>
        </el-row>

        <div v-if="count > 0">
            <!-- 评论数量 -->
            <div class="count">{{ count }} 评论</div>
            <!-- 评论列表 -->
            <div v-for="(item, index) of commentList" :key="item.commentUserUid">
                <!-- 分割线 -->
                <el-divider/>
                <el-row>
                    <!--头像-->
                    <el-col :span="2">
                        <!--todo 路径错误-->
                        <el-avatar v-if="!item.commentUserAvatar" :size="45"
                                   :src="item.commentUserAvatar">
                        <!-- this.global.file_path -->
                        </el-avatar>
                        <el-avatar v-else :size="45">
                            <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
                        </el-avatar>
                    </el-col>

                    <el-col :span="22">
                        <!--用户昵称-->
                        <el-row>
                            <el-col :span="2" class="userName">
                                {{ item.commentUserNickName }}
                            </el-col>
                        </el-row>
                        <!--发表时间-->
                        <el-row>
                            <el-col :span="18" class="time">
                                {{ item.createTime | parseTime }}
                            </el-col>
                        </el-row>
                        <!--评论内容-->
                        <el-row class="content">
                            {{ item.content }}
                        </el-row>
                        <!-- 回复按钮 -->
                        <el-row class="replyCommentBtn">
                            <el-link icon="el-icon-chat-square"
                                     :underline="false"
                                     @click="replyComment(index, item)"
                            >回复</el-link>
                        </el-row>

                        <!-- 二级评论 -->
                        <div v-for="reply of item.childrenList" :key="reply.uid">
                            <!-- 分割线 -->
                            <el-divider/>
                            <el-row>
                                <!--头像-->
                                <el-col :span="2">
                                    <!--todo 路径错误-->
                                    <el-avatar v-if="!reply.commentUserAvatar" :size="45"
                                               :src="reply.commentUserAvatar">
                                        <!-- this.global.file_path -->
                                    </el-avatar>
                                    <el-avatar v-else :size="45">
                                        <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
                                    </el-avatar>
                                </el-col>

                                <el-col :span="22">
                                    <!--用户昵称-->
                                    <el-row>
                                        <el-col :span="2" class="userName">
                                            {{ reply.commentUserNickName }}
                                        </el-col>
                                    </el-row>
                                    <!--发表时间-->
                                    <el-row>
                                        <el-col :span="18" class="time">
                                            {{ reply.createTime | parseTime }}
                                        </el-col>
                                    </el-row>
                                    <!--评论内容-->
                                    <el-row class="content">
                                        <el-link type="primary" :underline="false">@{{reply.targetUserNickName}} </el-link>
                                        {{ reply.content }}
                                    </el-row>
                                    <!-- 回复按钮 -->
                                    <el-row class="replyCommentBtn">
                                        <el-link icon="el-icon-chat-square"
                                                 :underline="false"
                                                 @click="replyComment(index, reply)"
                                        >回复</el-link>
                                    </el-row>
                                </el-col>
                            </el-row>
                        </div>

                        <!-- 回复框 -->
                        <reply ref="reply" @reloadReply="reloadReply" />
                    </el-col>
                </el-row>
            </div>
        </div>

        <!-- 没有评论提示 -->
        <div v-else style="padding:1.25rem;text-align:center">
            来发评论吧~
        </div>
    </div>
</template>

<script>

import reply from "@/components/article/Reply";
import { comment } from "@/api";

export default {
    name: "Comment",
    components: {
        reply,
    },
    props: {
        commentList: {
            type: Array
        },
        articleUid: {
            type: String
        },
        count: {
            type: Number
        }
    },
    data: function () {
        return {
            commentContent: '',
            current: 1
        };
    },
    methods: {
        // 发表评论
        comment() {
            let that = this;
            if(!that.$store.state.isLogin){
                that.$message.warning("未登录不能进行评论");
                return false;
            }
            //空
            if (that.commentContent.trim() === "") {
                that.$message.warning("评论不能为空");
                return false;
            }

            let params = new URLSearchParams();
            that.addComment(that, params);
        },
        addComment(that, params) {
            params.append("content", that.commentContent);
            params.append("articleUid", that.articleUid);
            params.append("commentUserUid", that.$store.state.userInfo.userUid);

            comment(params).then(res => {
                this.$emit("reloadComment");
            }).catch(err => {
                that.$message.error(err)
            })
        },
        replyComment(index, item) {
            this.$refs.reply.forEach(item => {
                item.$el.style.display = "none";
            });
            //传值给回复框
            this.$refs.reply[index].commentContent = "";
            this.$refs.reply[index].targeNickName = item.commentUserNickName;
            this.$refs.reply[index].targetUserUid = item.commentUserUid;
            this.$refs.reply[index].parentUid = this.commentList[index].uid;
            this.$refs.reply[index].articleUid = this.articleUid;
            this.$refs.reply[index].index = index;
            this.$refs.reply[index].$el.style.display = "block";
        },
        reloadReply() {
            this.$emit("reloadComment");
        }
    },


};
</script>

<style scoped>

.comment-title {
    display: flex;
    align-items: center;
    font-size: 1.25rem;
    font-weight: bold;
    line-height: 40px;
    margin-bottom: 10px;
}

.comment-title i {
    font-size: 1.5rem;
    margin-right: 5px;
}

.count {
    padding: 5px;
    line-height: 1.75;
    font-size: 1.25rem;
    font-weight: bold;
}

.userName, .time {
    margin: 5px 0px;
    font-size: 12px;
}

.replyCommentBtn {
    margin: 5px 0px;
}

.content {
    margin: 10px 30px 5px 0px;
    line-height: 28px
}

</style>

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

        </div>
        <!-- 没有评论提示 -->
        <div v-else style="padding:1.25rem;text-align:center">
            来发评论吧~
        </div>
    </div>
</template>

<script>

import { comment } from "@/api";

export default {

    props: {
        commentList: {
            type: Array
        },
        articleId: {
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
            //判空
            if (that.commentContent.trim() === "") {
                that.$message.warning("评论不能为空");
                return false;
            }

            let params = new URLSearchParams();
            that.addComment(that, params);
        },
        addComment(that, params) {
            params.append("content", that.commentContent);
            params.append("articleUid", that.articleId);
            params.append("commentUserUid", that.$store.state.userInfo.userUid);

            comment(params).then(res => {

            }).catch(err => {
                that.$message.error(err)
            })
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

</style>

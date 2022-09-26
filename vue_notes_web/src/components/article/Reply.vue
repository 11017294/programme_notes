<template>
    <!-- 评论框 -->
    <div class="reply-input-wrapper" style="display: none" ref="reply">
        <div class="comment-input">
            <el-input
                type="textarea"
                :rows="10"
                show-word-limit
                maxlength="500"
                resize="none"
                :placeholder='"回复 @" + targeNickName + "："'
                v-model="commentContent">
            </el-input>
        </div>
        <el-row type="flex" class="row-bg" justify="end">
            <el-button style="margin:10px 0px;"
                       type="primary"
                       @click="replyComment">回复</el-button>
        </el-row>
    </div>
</template>

<script>
import { comment } from "@/api";
export default {
    name: "Reply",
    data: function () {
        return {
            index: 0,
            targeNickName: "",
            targetUserUid: null,
            parentUid: null,
            articleUid: null,
            commentContent: ""
        };
    },
    methods: {
        // 回复评论
        replyComment() {
            let that = this;
            if(!that.$store.state.isLogin){
                that.$message.warning("未登录不能进行评论");
                return false;
            }
            //判空
            if (that.commentContent.trim() === "") {
                that.$message.warning("评论不能为空");
                return false;
            }

            let params = new URLSearchParams();
            params.append("articleUid", that.articleUid);
            params.append("commentUserUid", that.$store.state.userInfo.userUid);
            params.append("targetUserUid", that.targetUserUid);
            params.append("parentUid", that.parentUid);
            params.append("content", that.commentContent);

            comment(params).then(res => {
                this.$emit("reloadReply");
                this.$refs.reply.style.display = "none";
            }).catch(err => {
                that.$message.error(err);
            })
        }
    }
}
</script>

<style scoped>

.reply-input-wrapper {
    margin-right: 30px;
}
</style>

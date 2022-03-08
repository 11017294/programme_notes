<template>
    <div class="notesInfo">
        <el-form ref="form" :model="noteForm" label-width="80px">
            <el-form-item label="标题">
                <el-input placeholder="请输入标题" style="width: 50%" v-model="noteForm.title"></el-input>
            </el-form-item>

            <el-form-item label="简介">
                <el-input placeholder="请输入简介" style="width: 50%" v-model="noteForm.summary"></el-input>
            </el-form-item>
            <el-row>
                <el-col :span="5">
                    <el-form-item label="分类">
                        <el-select v-model="noteForm.noteSortUid" placeholder="请选择分类">
                            <el-option
                                v-for="item in noteSortData"
                                :key="item.uid"
                                :label="item.sortName"
                                :value="item.uid"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item label="标签">
                        <el-select v-model="tagValue" multiple placeholder="请选择">
                            <el-option
                                v-for="item in tagData"
                                :key="item.uid"
                                :label="item.content"
                                :value="item.uid"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item label="是否原创">
                        <el-radio-group v-model="noteForm.isOriginal">
                            <el-radio label="1">原创</el-radio>
                            <el-radio label="0">转载</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-form-item label="内容">
                <el-input type="textarea" resize="none" :rows="25" style="width: 48%" v-model="markdown_body"></el-input>
                <el-input type="textarea" resize="none" :rows="25" style="width: 48%" v-model="noteForm.content"></el-input>
            </el-form-item>
            <el-form-item class="but">
                <el-button plain @click="returnPage">取消</el-button>
                <el-button type="primary" @click="commitNotes()">提交</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>

import {addNote, getNoteSort, getTag} from "@/api";

export default {

    name: "takeNotes",
    data() {
        return {
            noteForm: {
                title: '',
                summary: '',
                noteSortUid: '',
                tagUid: '',
                isOriginal: '1',
                content: ''
            },
            noteSortData: [],
            tagData: [],
            tagValue: [],
            markdown_body: ''
        }
    },
    methods: {
        commitNotes() {
            let that = this;
            var params = new URLSearchParams();
            this.noteForm.tagUid = that.tagValue.join(",");
            for(let key in that.noteForm){
                params.append(key, that.noteForm[key])
            }
            addNote(params).then(res => {
                that.$message.success("成功")
                that.$router.push('/')
            }).catch(err => {
                this.$message.error(err)
            })
        },
        returnPage() {
            this.$confirm('是否退出此次编辑', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                if (window.history.length <= 1) {
                    this.$router.push({ path: "/system/storageManagement" });
                    return false;
                } else {
                    this.$router.go(-1);
                }
            })
        },
        getNoteSort() {
            getNoteSort().then(res => {
                this.noteSortData = res.data.list;
            }).catch(err => {
                this.$message.error(err)
            })
        },
        getTag() {
            getTag().then(res => {
                this.tagData = res.data.list;
            }).catch(err => {
                this.$message.error(err)
            })
        }
    },
    mounted() {
        this.getNoteSort()
        this.getTag()
    }
}
</script>

<style scoped lang="less">

.notesInfo {
    margin-top: 60px;
    padding: 80px;

    .but {
        display: flex;
        justify-content: flex-end;
        margin-right: 70px;
    }
}


</style>

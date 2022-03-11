<template>
    <div class="notesInfo">
        <el-form ref="noteForm" :model="noteForm" :rules="rules" label-width="80px">
            <el-form-item label="标题" prop="title">
                <el-input placeholder="请输入标题" style="width: 50%" v-model="noteForm.title"></el-input>
            </el-form-item>

            <el-form-item label="简介" prop="summary">
                <el-input placeholder="请输入简介" style="width: 50%" v-model="noteForm.summary"></el-input>
            </el-form-item>
            <el-row>
                <el-col :span="6">
                    <el-form-item label="标签" prop="tagValue">
                        <el-select
                            v-model="noteForm.tagValue"
                            :multiple-limit=3
                            multiple
                            filterable
                            placeholder="请选择"
                            style="width:80%" >
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
                    <el-form-item label="分类" prop="noteSortUid">
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
                <el-col :span="3.5">
                    <el-form-item label="是否原创">
                        <el-radio-group v-model="noteForm.isOriginal">
                            <el-radio label="1">原创</el-radio>
                            <el-radio label="0">转载</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item class="but">
                        <el-button plain @click="returnPage">取消</el-button>
                        <el-button type="info" @click="resetForm">重置</el-button>
                        <el-button type="primary" @click="commitNote">提交</el-button>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-form-item label="内容" prop="content">
                <mavon-editor
                    v-model="noteForm.content"
                    :placeholder="'Edit ···'"
                    ref="noteForm"
                    @imgAdd="$imgAdd"
                    @imgDel="$imgDel"
                    :toolbars="toolbars"
                    :external-link="externalLink"
                    :toolbarsBackground="'#f9f9f9'"
                    style="height: calc(100vh - 50px)"
                />
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
                tagValue: [],
                content: ''
            },
            noteSortData: [],
            tagData: [],
            codeStyle: '',
            externalLink: {     // 需要配置的内容
                markdown_css: () => '/md/markdown/github-markdown.min.css',
                hljs_js: () => '/md/highlightjs/highlight.min.js',
                hljs_css: (css) => '/md/highlightjs/styles/' + css + '.min.css',
                hljs_lang: (lang) => '/md/highlightjs/languages/' + lang + '.min.js',
                katex_css: () => '/md/katex/katex.min.css',
                katex_js: () => '/md/katex/katex.min.js',
            },
            toolbars: {
                bold: true, // 粗体
                italic: true, // 斜体
                header: true, // 标题
                underline: true, // 下划线
                strikethrough: true, // 中划线
                mark: true, // 标记
                superscript: true, // 上角标
                subscript: true, // 下角标
                quote: true, // 引用
                ol: true, // 有序列表
                ul: true, // 无序列表
                link: true, // 链接
                imagelink: true, // 图片链接
                code: true, // code
                table: true, // 表格
                fullscreen: true, // 全屏编辑
                readmodel: true, // 沉浸式阅读
                htmlcode: true, // 展示html源码
                help: true, // 帮助
                /* 1.3.5 */
                undo: true, // 上一步
                redo: true, // 下一步
                trash: true, // 清空
                save: false, // 保存（触发events中的save事件）
                /* 1.4.2 */
                navigation: true, // 导航目录
                /* 2.1.8 */
                alignleft: true, // 左对齐
                aligncenter: true, // 居中
                alignright: true, // 右对齐
                /* 2.2.1 */
                subfield: true, // 单双栏模式
                preview: true // 预览
            },
            rules: {
                title: [
                    { required: true, message: '请输入标题', trigger: 'blur' },
                    { min: 4, max: 100, message: '长度在 4 到 100 个字符', trigger: 'blur' }
                ],
                summary: [
                    { required: true, message: '请输入简介', trigger: 'blur' },
                    { min: 6, max: 200, message: '长度在 6 到 300 个字符', trigger: 'blur' }
                ],
                noteSortUid: [
                    { required: true, message: '请选择分类', trigger: 'change' }
                ],
                tagValue: [
                    { required: true, type:"array", message: '请选择标签', trigger: ['blur', 'change']}
                ],
                content: [
                    { required: true, message: '内容不能为空', trigger: 'blur' },
                ],
            }
        }
    },
    methods: {
        $imgAdd (pos, $file) {
            console.log(pos, $file);
        },
        $imgDel() {

        },
        resetForm() {       // 重置
            this.$refs['noteForm'].resetFields();
            this.noteForm.isOriginal = '1'
        },
        commitNote() {     // 提交笔记
            this.$refs['noteForm'].validate((valid) => {
                if (valid) {
                    if(this.noteForm.content.length <= 20){
                        this.$message.warning("内容长度至少20个字符");
                        return;
                    }
                    let that = this;
                    let params = new URLSearchParams();
                    this.noteForm.tagUid = that.noteForm.tagValue.join(",");
                    for(let key in that.noteForm){
                        params.append(key, that.noteForm[key])
                    }
                    let userInfo = that.$store.state.userInfo;
                    params.append("author", userInfo.userName)
                    params.append("userUid", userInfo.userUid)
                    addNote(params).then(res => {
                        that.$message.success("成功")
                        that.$router.push('/')
                    }).catch(err => {
                        this.$message.error(err)
                    })
                }
                else {
                    this.$message.warning("必填项不能为空");
                    return;
                }
            });
        },
        returnPage() {      // 取消事件
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
        getNoteSort() {     // 获取分类
            getNoteSort().then(res => {
                this.noteSortData = res.data.list;
            }).catch(err => {
                this.$message.error(err)
            })
        },
        getTag() {      // 获取标签
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

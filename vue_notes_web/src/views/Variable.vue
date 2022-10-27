<template>
    <div class="variable">
        <!-- 输入框 -->
        <div class="grid-content bg-purple">
            <el-input placeholder="请输入内容"
                      v-model="value"
                      ref="inputRef"
                      maxlength="200"
                      @keyup.enter.native="search"
                      class="input-with-select">
                <el-button slot="append"
                           icon="el-icon-search"
                           @click="search">
                </el-button>
                <el-button slot="prepend"
                           icon="el-icon-delete"
                           @click="clear"
                ></el-button>
            </el-input>
        </div>
        <div class="tips">
            tips: 按 Ctrl 自动对焦搜索框，Enter 触发转换，输入内容不能为空
        </div>
        <!-- 列表 -->
        <el-card class="box-card" style="margin-top:8%" v-show="isDisplay">
            <el-table
                :data="tableData"
                style="width: 100%">
                <el-table-column
                    prop="nameMethod"
                    label="命名法">
                </el-table-column>
                <el-table-column
                    prop="variableName"
                    label="变量名">
                    <template slot-scope="scope">
                        <el-button @click="clickCopy(scope.row)"
                                   type="text">
                            {{scope.row.variableName}}
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>

    </div>
</template>

<script>
export default {
    name: "Variable",
    data() {
        return {
            value: '',
            isDisplay: false,
            tableData: [
                {
                    nameMethod: '小驼峰',
                    variableName: ''
                },{
                    nameMethod: '大驼峰',
                    variableName: ''
                },{
                    nameMethod: '小蛇形',
                    variableName: ''
                },{
                    nameMethod: '大蛇形',
                    variableName: ''
                },{
                    nameMethod: '脊柱',
                    variableName: ''
                },{
                    nameMethod: '注释',
                    variableName: ''
                }
            ]
        }
    },
    methods: {
        search() {
            this.$refs.inputRef.blur();
            // 必须输入内容
            let str = this.value
            if(!str || !str.trim()){
                this.isDisplay = false
                return
            }
            this.isDisplay = true

            // 处理字符串
            let newStr = this.formatString(str);
            // 分割字符串拿到每个单词
            let strArr = newStr.split("-")

            let hump = strArr[0]          // 驼峰
            let snakeShape = strArr[0]    // 蛇形
            let spinalColumn = strArr[0]  // 脊柱
            let annotation = strArr[0]    // 注释

            // 遍历每个单词，做对应的处理
            for(let i = 1;i < strArr.length; i++){
                // 跳过空字符串
                if(strArr[i] == '') continue
                hump += strArr[i][0].toUpperCase()+  strArr[i].slice(1)
                snakeShape += '_' + strArr[i]
                spinalColumn += '-' + strArr[i]
                annotation += ' ' + strArr[i]
            }

            // 小驼峰
            this.tableData[0].variableName = hump
            // 大驼峰
            this.tableData[1].variableName = hump[0].toUpperCase() + hump.slice(1)
            // 小蛇形
            this.tableData[2].variableName = snakeShape
            // 大蛇形
            this.tableData[3].variableName = snakeShape.toLocaleUpperCase()
            // 脊柱
            this.tableData[4].variableName = spinalColumn
            // 注释
            this.tableData[5].variableName = annotation
        },
        // 格式化字符串（使其达到可分割正确分割的格式）
        formatString(str){
            // 去掉首尾空格
            str = str.trim()
            // 将全部空格转换成-
            str = str.replaceAll(' ', '-')
            // 将全部_转换成-
            str = str.replaceAll('_', '-')
            // 将字符串全部转换成小写
            str = str.toLocaleLowerCase()
            // 按驼峰添加-作为分隔字符
            str = str.replace(/([A-Z])/g,function(s){
                return '-' + s.toLowerCase();
            });
            return str
        },
        // 点击复制到剪贴板
        clickCopy(row) {
            //Create new element
            const elm = document.createElement('textarea');

            //Fill the new element with text
            elm.value = row.variableName;

            //Append the new element
            document.body.appendChild(elm);

            //Select the content of the element
            elm.select();

            //Copy the content
            document.execCommand('copy');

            //Remove the element
            document.body.removeChild(elm);



            this.$notify.success({
                title: '复制成功',
                message: '命名法：' + row.nameMethod,
                showClose: false
            });
        },
        // 清除输入框内容
        clear(){
            this.value = ''
            this.isDisplay = false
        },
        // 按下Ctrl输入框自动获取焦点
        enterKey(event) {
            const code = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
            if (code == 17) {
                this.$refs.inputRef.focus();
            }
        },
        enterKeyupDestroyed() {
            document.removeEventListener("keyup", this.enterKey);
        },
        enterKeyup() {
            document.addEventListener("keyup", this.enterKey);
        }
    },
    mounted() {
        // 绑定enter事件
        this.enterKeyup();
    },
    destroyed() {
        // 销毁enter事件
        this.enterKeyupDestroyed();
    },
}
</script>

<style lang="less" scoped>
    .variable {
        width: 40%;
        margin: auto;
        margin-top: 12%;
        background: #ffffff;
    }
    .tips {
        margin-top: 5px;
        font-size: 14px;
        line-height: 30px;
        color: #9f9d9d;
    }
</style>

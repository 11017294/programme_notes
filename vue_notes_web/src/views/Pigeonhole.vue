<template>
    <div class="pigeonholeCenter">
        <el-container>
            <el-header>
                <el-tabs v-model="activeName" stretch>
<!--                    <el-tab-pane label="自定义" name="customModule">
                        <Custom/>
                    </el-tab-pane>-->
                    <el-tab-pane label="分类" name="sortModule">
                        <Sort/>
                    </el-tab-pane>
                    <el-tab-pane label="标签" name="tagModule">
                        <Tag/>
                    </el-tab-pane>
                    <el-tab-pane label="时间" name="timeModule">
                        <Time/>
                    </el-tab-pane>
                </el-tabs>
            </el-header>
        </el-container>
    </div>
</template>

<script>

import Custom from '@/components/Pigeonhole/custom'
import Sort from '@/components/Pigeonhole/sort'
import Tag from '@/components/Pigeonhole/tag'
import Time from '@/components/Pigeonhole/time'

export default {
    name: "pigeonhole",
    data() {
        return {
            activeName: 'sortModule'
        };
    },
    components: {
        Custom,
        Sort,
        Tag,
        Time
    }

}
</script>

<style scoped>
.pigeonholeCenter {
    width: 60%;
    margin: auto;
    margin-top: 80px;
    background: #ffffff;
}
</style>

<template>
    <el-container>
        <el-aside>
            <el-timeline>
                <el-timeline-item
                    v-for="item in sortData"
                    :key="item.uid">
                    <a>{{item.sortName}}</a>
                </el-timeline-item>
            </el-timeline>
        </el-aside>
        <el-main>
            <Post/>
        </el-main>
    </el-container>
</template>

<script>

import Post from '@/components/post'
import List from '@/components/Pigeonhole/list'
import {getNoteSort} from "@/api";

export default {
    name: "sort",
    data() {
        return {
            sortData: []
        };
    },
    components: {
        List,
        Post
    },
    methods: {
        getNoteSort() {
            getNoteSort()
                .then(res => {
                    this.sortData = res.data.list;
                }).catch(err => {
                this.$message.error(err);
            })
        }
    },
    mounted() {
        this.getNoteSort();
    }
}
</script>

<style scoped>

</style>

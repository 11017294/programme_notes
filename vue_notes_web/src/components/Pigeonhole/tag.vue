<template>
    <el-container class="tag">
        <el-aside>
            <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
                <el-timeline>
                    <el-timeline-item
                        v-for="item in tagData"
                        :key="item.uid">
                        <a>{{item.content}}</a>
                    </el-timeline-item>
                </el-timeline>
            </ul>
        </el-aside>
        <el-main>
            <Post/>
        </el-main>
    </el-container>
</template>

<script>

import Post from '@/components/post'
import List from '@/components/Pigeonhole/list'
import {getTag} from "@/api";

export default {
    name: "tag",
    data() {
        return {
            tagData: [],
            count: 0
        };
    },
    components: {
        List,
        Post
    },
    methods: {
        getTag() {
            getTag()
                .then(res => {
                    this.tagData = res.data.list;
                }).catch(err => {
                this.$message.error(err);
            })
        },
        load () {
            this.count += 2
        }
    },
    mounted() {
        this.getTag();
    }
}
</script>

<style scoped>

.tag {
    height-max:500px
}
</style>

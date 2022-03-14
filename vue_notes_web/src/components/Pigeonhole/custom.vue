<template>
    <el-container>
        <el-aside>
            <el-timeline>
                <el-timeline-item
                    v-for="activity in noteData"
                    :key="noteData.uid">
                    {{activity.content}}
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

export default {
    name: "customAside",
    data() {
        return {
            noteData: [],
        };
    },
    components: {
        List,
        Post
    }
}
</script>

<style scoped>

</style>

<template>
    <el-container>
        <el-aside>

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
    name: "time",
    data() {
        return {

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

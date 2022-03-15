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
        </el-main>
    </el-container>
</template>

<script>


export default {
    name: "customAside",
    data() {
        return {
            noteData: [],
        };
    }
}
</script>

<style scoped>

</style>

<template>
    <div class="header-search">
        <i class="iconfont iconsearch" @click.stop="click"></i>
        <input ref="searchInput" :class="{'show':show}" v-model="keyword" type="text" @click.stop="" @keyup.enter="search"/>
    </div>
</template>

<script>
    import {fetchList} from "@/api";

    export default {
        name: "header-search",
        data(){
          return{
              keyword: '',
              currentPage: 1,
              totalPages: 0,
              pageSize: 10,
              total: 0, //总数量
              show: false
          }
        },
        watch:{
            show(value) {
                if (value) {
                    document.body.addEventListener('click', this.close)
                } else {
                    document.body.removeEventListener('click', this.close)
                }
            }
        },
        methods:{
            search(){
                if(!this.keyword || !this.keyword.trim()){
                    return
                }
                this.$router.push({name:'search', params:{keyword: this.keyword}});
                this.close()
            },
            click(){
                this.show = !this.show
                if (this.show) {
                    this.$refs.searchInput && this.$refs.searchInput.focus()
                }
            },
            close(){
                this.$refs.searchInput && this.$refs.searchInput.blur()
                this.show = false
            }
        }
    }
</script>

<style scoped lang="less">
.header-search{
    display: inline-block;
    position: relative;
    i{
        font-size: 18px;
        position: relative;
        top: 3px;
    }
    input{
        border:none;
        outline:none;
        overflow: hidden;
        background: transparent;
        height: 30px;
        width: 0;
        transition: .2s all;
        &.show{
            width: 200px;
            margin-left: 10px;
        }
        &:focus{
            border-bottom: 1px solid #8fd0cc;
        }
    }
}
</style>

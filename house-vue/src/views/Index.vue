<template>
    <div>
      首页

      <el-link @click="test">
          test
      </el-link>
    </div>
</template>

<script>
    export default {
        name: "Index",
        data(){
            return {
                ss:false
            }
        },
        methods: {
            test(){
                alert("test");
            }
        }
    }
</script>

<style scoped>

</style>
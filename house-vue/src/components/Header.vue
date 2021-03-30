<template>
    <div  class="tophead">
        

         <div class="nav-top">  
            <el-link href="/login" :underline="false" style="float:right;margin-right:200px;line-height: 30px;font-size: 12px;">
                 登录/注册
            </el-link>      
        </div>
        <div style="position:relative;height:120px">
            <h1 style="width:300px;position:absolute">
                <img src="../assets/Systemlogo.png"  style="width:130px;height:80px;margin-left:140px"/>
            </h1>
             
             <el-input  type="text"   v-model="searchall" style="width:600px;margin-left:400px;margin-top:38px;" placeholder="请输入内容" >
             <el-button icon="el-icon-search" style="margin-right:-10px" slot="suffix" type="success" @click="sendMessage()" >
                 
             </el-button> 
             
             </el-input>
        </div>

    </div>
   
</template>

<script>
export default {
    name: 'topHeader',
    data(){

        return {
            searchall: ''
        }
    }
}
</script>

<style scoped>
.tophead {
    width: 100%;
    margin-top:-10px;
    height:180px

}
.nav-top{
    height:30px;
    
    border-bottom:1px solid #1234;
    /* background:#f5f5f5; */
}
el-input{
    border-radius: 4px;
}

.na{
    cursor: pointer;
    margin-left: 10px;
}
</style>
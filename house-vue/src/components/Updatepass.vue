<template>
    <div>
        <div>
            修改密码
        </div>
        <el-divider></el-divider>
        <el-form>
            <el-form-item label="旧密码">
                <el-input type="password" style="width:200px" v-model="oldPass">

                </el-input>
            </el-form-item>
            <el-form-item label="新密码">
                <el-input type="password" style="width:200px" v-model="newPass">
                    
                </el-input>
            </el-form-item>
            <el-form-item label="确认密码">
                <el-input type="password" style="width:200px" v-model="checkPass">

                </el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click="changePass" >
                    立即修改
                </el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
export default {
    name:'updatePass',
    data(){
        return{
            oldPass:'',
            newPass:'',
            checkPass:''
        }
    },
    methods:{
        changePass(){
            var name = sessionStorage.getItem('username');
            const headers = {
                 'token':localStorage.getItem(name)
            }
            
            if(name!=null){
                this.axios.put('http://localhost:8081/userdata/updatepass'+'?oldPass='+this.oldPass+'&newPass='+this.newPass,{data:''},
                {
                 headers:headers
                }
             )
             .then((res)=>{
                 if(res.data.code==200){
                     this.$message({
                         type:'success',
                         message:'修改成功'
                     })
                     this.oldPass='';
                     this.newPass='';
                     this.checkPass='';
                 }
             }).catch((error)=>{
                 this.$message({
                     type:'info',
                     message:'删除失败'
                 })
             })
            }
             
        }
    }
}
</script>
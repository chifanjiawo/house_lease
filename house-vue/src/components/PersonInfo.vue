<template>
    <div>
        <div>
        修改个人资料
        </div>

        <el-divider></el-divider>
        <div style="color:darkgrey;">
             用户ID: {{user.userId}}
         </div>
          <div style="margin-top:20px;font-size:15px;color:darkgrey; ">
                注册时间: {{user.userRegisterTime| dateFmt('yyyy-MM-DD hh:mm:ss')}}
         </div>
         <div style="margin-top:20px;font-size:15px;color:darkgrey;">
             用户状态：
             <span v-if="user.userBanStatus==0">正常</span>
             <span v-if="user.userBanStatus==1">禁用</span>
         </div>
         

        <el-form style="margin-top:15px">
           
             <el-form-item label="修改头像">

                 <el-upload
                    class="avatar-uploader"
                    ref="upload"
                    action="http://localhost:8081/file/avatar"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload"
                    :data="{'userId':user.userId}"
                    >
                    <img v-if="imageUrl" :src="imageUrl" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>


             </el-form-item>

            <el-form-item label="用户名">
                <el-input style="width:270px" v-model="user.userName"></el-input>
            </el-form-item>
            <el-form-item label="用户昵称">
                <el-input style="width:250px" v-model="user.userNickName"></el-input>
            </el-form-item>
            <el-form-item label="手机号码">
                <el-input style="width:250px" v-model="user.userTel"></el-input>
            </el-form-item>
            <el-form-item label="邮箱地址">
                <el-input style="width:250px" v-model="user.userEmail"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click="saveInfo">
                    立即保存
                </el-button>
            </el-form-item>
            
        </el-form>
    </div>
    

</template>

<script>
export default {
    name:'pseronInfo',
    data(){
        return {
            user:{},
            imageUrl: ''
        }
    },
    methods:{
         handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
            this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
            this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },
        initData(){
            let name = sessionStorage.getItem('username');
            if(name!=null){

                this.axios.get(
                    'http://localhost:8081/userdata/myinfo',
                    {
                        params:{
                            userName:name,
                        }
                    }

                ).then((res)=>{
                    this.user = res.data;
                    console.log(this.user);
                }).catch((error)=>{
                    console.log('连接出错');
                })

            }
          
        },
        saveInfo(){
            
            this.axios.put('http://localhost:8081/userdata/update',
            this.user
            ).then((res)=>{
                if(res.data.code==200){
                     this.$message({
                            type: 'success',
                            message: '修改成功!'
                    });
                    location.reload();
                }
            }).catch((error)=>{
                this.$message({
                    type:'info',
                    message:'修改失败!'
                })
            })
        }
    },
    created(){
        this.initData();
    }

}
</script>

<style scoped>
 .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>

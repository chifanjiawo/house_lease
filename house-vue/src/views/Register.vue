<template>
<div class="background">
   
  <div class="login-box">  
     <div class="Top_links">
      <a href="/index" style="color: #FFFFFF;float: left;text-decoration:none;">返回首页</a>
      <a href="/login" style="float: right;color: #FFFFFF;text-decoration:none;cursor: pointer; " >已有账号？去登录</a>
    </div>
    <el-form label-width="80px" :model="form" :rules="rules">
      
      <div style="padding-left:33%;text-align: center; width: 400px; ">
        <img src="@/assets/Systemlogo.png"  style="width:150px;height:100px;"/>
      </div>
      <br>
      <el-form-item  prop="tel" style="margin-left:-13%">
        <el-input type="text" placeholder="请输入手机号" v-model="form.userTel">
            <el-button v-show=disa style="margin-right:-10px;margin-top:-10px;float:right" @click="sendMessage()" slot="suffix" type="text" >
                发送验证码
            </el-button>
            <el-button disabled v-show=!disa   style="margin-right:-10px;margin-top:-10px;float:right" slot="suffix" type="text"  >
                已发送({{time}}s）
            </el-button>
        </el-input>
      </el-form-item>
     <el-form-item  prop="code" style="margin-left:-13%">
        <el-input type="text" placeholder="请输入验证码" v-model="form.code"/>
      </el-form-item>
      <el-form-item  prop="username" style="margin-left:-13%">
        <el-input type="text" placeholder="请输入账号名" v-model="form.username"/>
      </el-form-item>
      <el-form-item prop="password" style="margin-left:-13%">
        <el-input type="password" placeholder="请输入密码" v-model="form.password"/>
      </el-form-item>
      <el-form-item  prop="password" style="margin-left:-13%">
        <el-input type="password" placeholder="请再次密码" v-model="form.apassword"/>
      </el-form-item>
      
      <el-form-item style="margin-right:40px">
        <el-button type="success" style="width:250px;" v-on:click="submit()">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</div>
</template>
<script>
    export default{
        name: 'Register',

        data(){
            return {
                form:{
                    username: '',
                    password: '',
                    userTel: '',
                    code: '',
                    apassword: '',
                },
                rules: {
                username: [
                    {required: true, message: '账号不可为空', trigger: 'blur'}
                ],
                password: [
                    {required: true, message: '密码不可为空', trigger: 'blur'}
                ],
                userTel: [
                    {required: true, message: '手机不可为空', trigger: 'blur'}
                ],
                code: [
                    {required: true, message: '验证不可为空', trigger: 'blur'}
                ]


                },
                time: 0,
                disa: true,

            }
        },
        methods:{
            sendMessage(){
            
            if(this.form.tel==''){
            alert("请输入手机号码");
            return;
            }
             var formdata = new FormData();
            formdata.append('iphonenum',this.form.userTel);
            this.axios.post('http://localhost:8081/user/sendMessage',formdata)
            .then((res)=>{
              if(res.data.code==200){
                this.disa = false;
                this.time = 60;
                setInterval(() => {
                if (this.time > 0 && this.time <= 60) {
                    this.time--;
                } else {
                    this.time = 0;
                    this.disa = true;
                }
                }, 1000)
              } 
            })
            .catch((error)=>{
              alert('请求失败');
            })
            
        
            },
            submit(){
              if(this.form.password!=this.form.apassword){
                alert("密码不一致");
                return;
              }
              if(this.form.tel!='' && this.form.code!='' && this.form.username!='' &&this.form.password && this.form.apassword!=''){
                var formdata = new FormData();
                formdata.append('userName',this.form.username);
                formdata.append('userTel',this.form.userTel);
                formdata.append('code',this.form.code);
                formdata.append('userPassword',this.form.password);

                this.axios.post('http://localhost:8081/user/register',formdata)
                .then((res)=>{
                  if(res.data.code==200){
                    this.$router.push('/index');
                  }else{
                    alert(res.data.code);
                  }
                })
                .catch((error)=>{
                  alert('请求错误');
                })
              }
            },




        }
    }

</script>
<style>
   .login-box {
    background-color: #FFFFFF;
    border: 1px solid #DCDFE6;
    width: 370px;
    height: 500px; 
    margin: 180px auto;
    padding: 35px 35px 0px 0px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 25px #909399;
    
  }

  .login-title {
    text-align: center;
    margin: 0 auto 40px auto;
    color: #303133;
  }

  .background{
    background: url(../assets/bg.jpg);
    width:100%; 
    height:100%;  /**宽高100%是为了图片铺满屏幕 */
     z-index:-1;
    position: absolute;
    top: -0.5px;
    left: -0.5px;
    background-attachment:fixed;
    background-size: cover;
  }

  .Top_links {
    position: relative;
    width: 370px;
    top:-11%;
    font-size: 12px;
    
    left: 6%;
  }
.mask_body_item {
    width: 350px;
    font-size: 14px;
    margin: 0 auto;
    position: relative;
    padding: 13px 0;
    clear: both;
}
.change_login {
    border-bottom: 1px solid #ebebeb;
    margin-bottom: 10px;
    padding: 0;
    height: 55px;
    margin-top: 10px;
}
span{
  width: 50%;
  height: 10px;
  cursor: pointer; 
  padding: 10px ;
}

.divcss5-x5{ 
  padding-bottom:5px;
  border-bottom:1px solid #67C23A
  }

.active {
  color: #67C23A;
}
</style>
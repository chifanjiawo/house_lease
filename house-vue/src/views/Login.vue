<template>
  <div class="background">
   
  <div class="login-box">  
     <div class="Top_links">
      <a href="/index" style="color: #FFFFFF;float: left;text-decoration:none;">返回首页</a>
      <a href="/register" style="float: right;color: #FFFFFF;text-decoration:none;cursor: pointer; " >没有账号？去注册</a>
    </div>
    <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" >
      
      <div style=";text-align: center; ">
        <img src="@/assets/Systemlogo.png"  style="width:150px;height:100px;margin-left:75px"/>
      </div>
      <br>
      <div style="padding-left:10%;text-align:center">
        <span  :class="{active : isactive1, 'divcss5-x5' :isactive1}" @click="selectActive1()">账号密码登录</span>
        <span  :class="{active : isactive2,'divcss5-x5' :isactive2}"  v-on:click="selectActive2">手机免密登录</span>
      </div>
      <br>
      <el-form-item  prop="username" v-show="!isactive2" style="margin-left:-13%">
        <el-input type="text" @change="confirmif1" placeholder="请输入账号" v-model="form.username"/>
      </el-form-item>
      <el-form-item  prop="tel" v-show="isactive2" style="margin-left:-13%">
        <el-input @change="confirmif2" type="text" placeholder="请输入手机号" v-model="form.tel">
          <el-button v-show='disa' ref="sendmsgb"  style="padding-right:10px" slot="suffix" type="text" @click="sendMessage()" >
           发送验证码
          </el-button> 
          <el-button disabled v-show=!disa   style="padding-right:10px" slot="suffix" type="text"  >
           已发送（{{time}}s）
          </el-button>
        </el-input>
        
      </el-form-item>

      <el-form-item  prop="password" v-show="!isactive2" style="margin-left:-13%">
        <el-input   type="password" placeholder="请输入密码" v-model="form.password"/>
      </el-form-item>
      
      <el-form-item  prop="code" v-show="isactive2" style="margin-left:-13%">
        <el-input  type="text" placeholder="请输入验证码" v-model="form.code"/>
      </el-form-item>
      
      <el-form-item style="height:0px;">
         <el-checkbox style="float:left;top:-14px;left:-45px;height:30px" v-model="checked">记住登录</el-checkbox>
        <el-link href="/updatepass"  style="float:right;height:10px;">忘记密码</el-link>
        
      </el-form-item
      >
      <el-form-item  style="margin-right:40px">
        <el-button ref="loginb" type="success" style="width:250px;" v-on:click="onSubmit('loginForm')">登录</el-button>
      </el-form-item>
    </el-form>

  </div>
  </div>
</template>

<script>
  export default {
    name: "Login",
    data() {
      return {
        form: {
          username: '',
          password: '',
          tel: '',
          code: ''
        },
        // 表单验证，需要在 el-form-item 元素中增加 prop 属性
        rules: {
          username: [
            {required: true, message: '账号不可为空', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '密码不可为空', trigger: 'blur'}
          ],
          tel: [
            {required: true, message: '手机不可为空', trigger: 'blur'}
          ],
          code: [
            {required: true, message: '验证不可为空', trigger: 'blur'}
          ]

        },
        // 对话框显示和隐藏
        isactive1: true,
        isactive2: false,
        checked: false,
        time: 0,
        msgcode: '发送验证码',
        disa: true,
        f: true,
        failcount: 0,
      }

    },
    methods: {
      onSubmit(formName) {
        //为表单绑定验证功能
        // this.$refs[formName].validate((valid) => {
        //   if (valid) {
        //     // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
            
          // this.axios.get(
          //   'http://localhost:8081/user/test')
          // .then((response)=>{
          //   if(response.data.code==200){
          //     alert(response.data.data);
          //   }
              
          // })
          // .catch((error)=>{
          //     console.log(error)
          // })
          if(this.form.username!=''&&this.form.password!=''){
            var formdata = new FormData();
            formdata.append('userName',this.form.username);
            formdata.append('userPassword',this.form.password);
            this.axios.post(
                'http://localhost:8081/user/login',
                formdata
            )
            .then((res)=>{
              
              if(res.data.code==200){
                localStorage.setItem(this.form.userName,res.data.data);
                this.$router.push("/index");
              }else{
                alert(res.data.msg);
              }
              
            })
            .catch((error)=>{
              alert('error');
              console.log(error);
            })
          }
          if(this.form.tel!=''&&this.form.code!=''){
            var formdata = new FormData();
            formdata.append('iphonenum',this.form.tel);
            formdata.append('code',this.form.code);
            this.axios.post(
                'http://localhost:8081/user/tellogin',
                formdata
            )
            .then((res)=>{
              
              if(res.data.code==200){
                localStorage.setItem(this.form.userName,res.data.data);
                this.$router.push("/index");
              }else{
                 alert(res.data.msg);
              }
              
            })
            .catch((error)=>{
              alert('error2');
              console.log(error);
            })
          }
          

          
        },
      selectActive1(){
          this.isactive1 = true;
          this.isactive2 = false;
          this.form.username = '';
          this.form.password = '';
      },
      selectActive2(){
          this.isactive2 = true;
          this.isactive1 = false;
          
          this.form.tel = '';
          this.form.code = '';
      },
      sendMessage(){
        
        if(this.form.tel==''){
          alert("请输入手机号码");
          return;
        }
        var formdata = new FormData();
        formdata.append('iphonenum',this.form.tel);
        this.axios.post("http://localhost:8081/user/sendMessage",formdata)
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
          alert("发送失败")
        })
        


      },



    
    }
    
  }
</script>

<style  scoped>

  .login-box {
    
    background-color: #FFFFFF;
    border: 1px solid #DCDFE6;
    width: 370px;
    height: 390px; 
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
    top:-14%;
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
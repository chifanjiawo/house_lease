<template>
  <div  class="tophead">
  <div class="nav-top">  
             <span class="city" >
                 你所在的城市【<span style="color:#67C23A">{{city}}</span>】
             </span>
             <el-link class="loginFont" style="margin-right:200px" :underline="false"  href="/index">首页</el-link> 
            <el-link class="loginFont"  :underline="false">个人中心</el-link> 
            <el-link class="loginFont" style="" :underline="false" @click="logout">退出</el-link> 
                 
        </div>
  </div>
</template>

<script>
export default {
name: 'backHeader',
    data(){

        return {
            searchall: '',
            city:'',
            login:false,
        }
    },
    methods:{
        initUserInfo(){
            console.log("用户名"+sessionStorage.getItem('username'));
            let name = sessionStorage.getItem('username');
            
            if(name!=null){
                let token = localStorage.getItem(name);
                console.log(token);

                this.axios.get(
                    
                       'http://localhost:8081/user/inspectToken',

                      {  headers:{
                             'Content-Type': 'application/json',//设置请求头请求格式为JSON
                             'token': token
                        },
                        params:{
                             'userName':name
                        }
                    }
                )
                .then((res)=>{
                    if(res.data.code==200){
                        this.login = true;
                    }else{
                        thi.login = false;
                    }
                }).catch((error)=>{
                    alert(JSON.stringify(error.data));
                })

            }

        }, logout(){
                
                this.axios.get('http://localhost:8081/user/logout',{headers:{'token':localStorage.getItem(sessionStorage.getItem('username'))}})
                .then((res)=>{

                    this.$message({
                        type:'success',
                        message:'退出成功!'
                    })
                    localStorage.removeItem(sessionStorage.getItem('username'));
                    this.$router.push('/index');

                }).catch((error)=>{
                    this.$message({
                        type:'error',
                        message: '出现错误'
                    })


                })
            },
    },
    created(){
        setTimeout(() => {
            this.city = sessionStorage.getItem('city');
        }, 800);

        this.initUserInfo();
        
    }
}
</script>

<style scoped>
.tophead {
    width: 100%;
    margin-top:-10px;
    height:80px

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
.loginFont{
    float:right;
    margin-right:10px;
    line-height: 30px;
    font-size: 12px;
}
.city{
    float:left;
    margin-left:150px;
    line-height: 30px;
    font-size: 12px;
}
</style>
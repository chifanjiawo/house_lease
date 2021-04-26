<template>
    <div  class="tophead">
        

         <div class="nav-top">  
             <span class="city" >
                 你所在的城市【<span style="color:#67C23A">{{city}}</span>】
             </span>
             <div class="loginFont" v-if="login">
                <!-- <el-dropdown>
                    <span class="el-dropdown-link">
                        我的账户<i class="el-icon-arrow-down el-icon--right"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item> <router-link to="/userInfo" style="text-decoration : none;">个人中心</router-link> </el-dropdown-item>
                        <el-dropdown-item ><span @click="logout">退出</span></el-dropdown-item>
                       
                    </el-dropdown-menu>
                </el-dropdown> -->
                <el-badge :value="commentNum" :is-dot="true" :hidden="commentNum==0" class="item">
                 <el-link style="font-size:12px;margin-top:-10px" :underline="false" @click="readMessage" >消息</el-link>
                </el-badge> 
                 <el-link style="font-size:12px" :underline="false" href="/userInfo">个人中心</el-link> 
                 <el-link style="font-size:12px;margin-left:5px" :underline="false" @click="logout">退出</el-link> 
             </div>
           
            <el-link class="loginFont" v-if="!login" href="/login" :underline="false">
                 登录/注册
            </el-link>      
        </div>
        <div style="position:relative;height:120px">
            <h1 style="width:300px;position:absolute">
                <img src="../assets/Systemlogo.png"  style="width:130px;height:80px;margin-left:140px"/>
            </h1>
             
             <el-input  type="text"   v-model="keyWord" style="width:600px;margin-left:400px;margin-top:38px;" placeholder="请输入内容" >
                 

                   <!-- <el-autocomplete
                    class="inline-input"
                    v-model="keyWord"
                    :fetch-suggestions="querySearchAsync"
                    placeholder="请输入内容"
                    :trigger-on-focus="false"
                    @select="handleSelectUpdate"
                    style="width:60%"
                    >
                  </el-autocomplete> -->
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
            searchall: '',
            city:'',
            login:false,
            myIp:'',
            zone:{
                    city:'',
                    area:[]
            },
            userName:'',
            commentNum:0,
            keyWord: '',
            timeout:null,



        }
    },
    methods:{
        initUserInfo(){
            
            let name = sessionStorage.getItem('username');
            
            if(name!=null){
                this.userName = name;
                let token = localStorage.getItem(name);
                
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

                        this.axios.get(
                            'http://localhost:8081/user/getUserId',
                            {
                                params:{
                                    'userName':name
                                }
                            }
                        ).then((res)=>{
                            if(res.data.code==200){
                                sessionStorage.setItem('userId',res.data.data);
                            }
                            
                        }).catch((error)=>{
                            console.log("连接出错");
                        });
                        this.login = true;
                    }else{
                        this.login = false;
                        // this.$message({
                        //     type:'info',
                        //     message:'登录状态过期'
                        // })

                        sessionStorage.removeItem('username');
                        sessionStorage.removeItem('userId');
                    }
                }).catch((error)=>{
                    console.log(JSON.stringify(error.data));
                })

            }

        },   
        sendMessage(){
            let list ='';
            this.axios.get('http://localhost:8081/base/searchByKey',{
                params:{
                    'key':this.keyWord
                }
            })
            .then((res)=>{
                if(res.data.code == 200){
                    list = res.data.data;
                    console.log(list);
                    this.$router.push({
                       
                        name: 'Search',
                        params:{
                            resultList:list,
                            key:this.keyWord
                        }
                    })
                }else{
                    this.$message({
                        type:'error',
                        message:res.data.msg
                    })
                }
            })
            .catch((error)=>{
                console.log('出错了');
            })
        },   

        handleSelectUpdate(item){
            this.house  = item.value;
        },
        // querySearchAsync(queryString, cb) {
            

        //     clearTimeout(this.timeout);
        //     this.timeout = setTimeout(() => {
        //     cb(list);
        //     }, 1000 * Math.random());
        // },
        getIP(){
                this.axios.get(
                  '/ipapi'
                )
                .then((res)=>{
                    this.myIp = res.data;
                    
                    this.getCity();
                })
                .catch((error)=>{
                    alert(JSON.stringify(error));
                })
               
            },
            getCity(){
                 
                
                
                this.axios.get(
                    
                    '/localapi/location/v1/ip?ip='+ this.myIp +'&key=W4JBZ-GM2CG-WL5QI-IXRPZ-II4E7-NDFDH'
                ).then((res)=>{
                   this.zone.city =  res.data.result.ad_info.city;
                   sessionStorage.setItem('city',this.zone.city);
                   
                    var url = '/localapi/district/v1/search?&keyword='+this.zone.city+'&key=W4JBZ-GM2CG-WL5QI-IXRPZ-II4E7-NDFDH';
                    
                   this.axios.get(
                       url
                   )
                   .then((res)=>{

                       this.axios.get(
                           '/localapi/district/v1/getchildren?id='+ res.data.result[0][0].id + '&key=W4JBZ-GM2CG-WL5QI-IXRPZ-II4E7-NDFDH'
                       )
                       .then((res)=>{
                           this.zone.area = res.data.result[0];
                           this.Global.ip = this.myIp;
                           this.Global.zone = this.zone;
                           this.Global.zone.area.unshift({'fullname':'不限'});
                       })
                       .catch((error)=>{
                           alert('3连接出错');
                       })
                       
                   })
                   .catch((error)=>{
                       alert('1'+JSON.stringify (error));
                   })

                }).catch((error)=>{
                    alert('2'+JSON.stringify (error));
                })
            },readMessage(){
                this.axios.put ('http://localhost:8081/message/setRead/'+sessionStorage.getItem('userId')).then((res)=>{
                    this.$router.push('/userInfo/myComment');

                }).catch((error)=>{
                    console.log(error);
                })
            },
            logout(){
               
                this.axios.get('http://localhost:8081/user/logout',{headers:{'token':localStorage.getItem(this.userName)}})
                .then((res)=>{

                    this.$message({
                        type:'success',
                        message:'退出成功!'
                    })
                    localStorage.removeItem(this.userName)
                    location.reload()

                }).catch((error)=>{
                    this.$message({
                        type:'error',
                        message: '出现错误'
                    })


                })
            },
            getNum(){

                if(sessionStorage.getItem('userId')!=null){
                this.axios.get(
                    'http://localhost:8081/message/getMessage/'+sessionStorage.getItem('userId')
                ).then((res) =>{
                    if(res.data.code ==200){
                        this.commentNum = res.data.data;
                        

                    }
                }).catch((err) =>{

                })
                }
            }
    },
    created(){

        this.initUserInfo();
        this.getIP();
        
        this.city = sessionStorage.getItem('city');
        setTimeout(()=>{
            this.getNum();
        },500)
       
    },
   
}
</script>

<style scoped>
.item{
     margin-top: 8px;
      margin-right: 10px;
     
}
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
.loginFont{
    float:right;
    margin-right:200px;
    line-height: 30px;
    font-size: 12px;
}
.city{
    float:left;
    margin-left:150px;
    line-height: 30px;
    font-size: 12px;
}
 .el-dropdown-link {
    cursor: pointer;
 
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
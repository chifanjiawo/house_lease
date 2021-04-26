<template>
    <el-container>
        <backHeader></backHeader>

        <div class="main">
            <h1 style="width:300px;position:absolute">
                <img src="../assets/Systemlogo.png"  style="width:130px;height:80px;margin-left:164px;margin-top:-40px"/>
            </h1>
            <div class="leftmenu">

                <div style="font-size:18px">个人中心</div>
                <div class="line" ></div>
                <div >
                    <ul style="margin-left:-20px">
                        <li :class="{bgcolor:current1==index, active:current3==index}" @click="changeActive1(index)"  @mouseleave="cancelCurrent1" @mouseover="changeCurrent1(index)" v-for="(item,index) in centerInfo" :key="index">
                            <span >
                                {{item}}
                            </span>
                        </li>
                    </ul>
                </div>
                <div class="line"/>
                <div style="padding-top:10px;font-size:18px">用户设置</div>
                <div class="line"></div>
                <div>
                    <ul style="margin-left:-20px">
                        <li  :class="{bgcolor:current2==index,active:current4==index}" @click="changeActive2(index)" @mouseleave="cancelCurrent2"  @mouseover="changeCurrent2(index)" v-for="(item,index) in userSetting" :key="index">
                            <span>
                                {{item}}
                            </span>
                        </li>
                        
                    </ul>
                </div>
                <div v-if="userInfo.userRole=='admin'">
                    <div class="line"/>
                    <div style="padding-top:10px;font-size:18px">管理员设置</div>
                    <div class="line"></div>
                    <div>
                    <ul style="margin-left:-20px">
                        <li  :class="{bgcolor:current5==index,active:current6==index}" @click="changeActive3(index)" @mouseleave="cancelCurrent3"  @mouseover="changeCurrent3(index)" v-for="(item,index) in adminPath" :key="index">
                            <span>
                                {{item}}
                            </span>
                        </li>
                        
                    </ul>
                </div>
                </div>
            </div>

            <div class="info">
                <div style="float:left">
                    <el-avatar :size="90" :src='userInfo.userAvatar'></el-avatar>
                </div>
                <div class="userName" >
                    {{userInfo.userNickName}}
                </div>
                <div class="tel">
                   <i class="el-icon-phone">{{userInfo.userTel}}</i>
                </div>
                <div style="margin-left:100px;font-size:15px;color:darkgrey;margin-top:10px">
                    上次登录时间：{{userInfo.userLastLoginTime| dateFmt('yyyy-MM-DD hh:mm:ss')}}
                </div>
                

            </div>
            <div style="width:700px;margin-left:400px;margin-top:50px">
                    <!-- <el-divider></el-divider> -->
            </div>
            <div class="content">
                <router-view></router-view>
            </div>

        </div>


         


        <el-footer>
            <myfooter></myfooter>
        </el-footer>
    </el-container>
</template>
<script>
import backHeader from '../components/BackHeader'
import myfooter from '../components/Footer'


export default {
    
    name:'userInfo',
    components:{
         myfooter,
         backHeader,
         
       
    },
    data(){
        return{
            centerInfo:['我的发布','我的收藏','我的评论'],
            userSetting:['个人资料','密码设置'],
            current1:-1,
            current2:-1,
            current3:-1,
            current4:-1,
            current5:-1,
            current6:-1,
            toPath:['myOrderList','myStar','myComment'],
            toPath2:['personInfo','updatePass'],
            adminPath:['管理用户','管理房屋信息'],
            userInfo:'',

        }
    },
    methods:{
        changeCurrent1(id){
            this.current1 = id;
        },
        changeCurrent2(id){
            this.current2 = id;
        },
        changeCurrent3(id){
            this.current5 = id;
        },
        cancelCurrent1(){
            this.current1=-1;
        },
        cancelCurrent2(){
            this.current2=-1;
        },
        cancelCurrent3(){
            this.current5 = -1;
        },
        changeActive1(id){
            this.current3=id;
            this.current4=-1;
            this.current6=-1
            this.$router.push('/userInfo/'+this.toPath[id]);
            
        },
        changeActive2(id){
            this.current4=id;
            this.current3=-1;
            this.current6=-1;
            this.$router.push('/userInfo/'+this.toPath2[id]);
        
        },
        changeActive3(id){
            this.current4=-1;
            this.current3=-1;
            this.current6=id;
            this.$router.push('/userInfo/'+this.adminPath[id]);
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
                    this.userInfo = res.data;
                    
                }).catch((error)=>{
                    console.log('连接出错');
                })

            }
          
        }
    },
    created(){
        this.initData();
    }


}
</script>

<style scoped>
.main{
    width: 100%;
    height: 1600px;
}
.leftmenu{
    width: 150px;
    position:absolute;
    background:#fafafa;
    margin-left:130px;
    text-align: center;
    padding: 20px;
    margin-top:92px;

}
.line{
      padding-bottom:5px;
      border-bottom:1px solid #1234;
      padding-top:5px;
      
}
ul{
    list-style-type: none;
    padding-left: 0px;
}
li{
    padding: 5px;
    font-size: 16px;
    cursor: pointer;
    
    
}
.active{
    border-left: 2px solid  #67C23A;
}
span{
    cursor: pointer;
}
.bgcolor{
    color: #67C23A;
}
.info{
    margin-left: 400px;
    width: 700px;
    margin-top:-30px;
    
}
.userName{

    font-size: 20px;
    padding:10px;
    margin-left: 90px;
}
.tel{
    margin-left: 100px;
    font-size: 15px;
}
.content{
    /* background-color:  #67C23A; */
    width: 800px;
    height: 1000px;
    margin-left: 400px;
    margin-top:-20px
    
}
</style>
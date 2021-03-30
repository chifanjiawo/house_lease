<template>
    <el-container>
    <top-header></top-header>

    <div class="main">
        <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-top:20px">
        <el-breadcrumb-item :to="{ path: '/' }">租房首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/search' }">租房列表</el-breadcrumb-item>
        <el-breadcrumb-item :to="{path:'/desc'}">租房</el-breadcrumb-item>
        </el-breadcrumb>
        
        <div class="title">
            {{order.houseTitle}}
        </div>
        <div class="imgwrag">
            <img :src="order.housePictureUrl" style="width:100%;height:400px"/>
        </div>
        <div class="housebase">
            <div class="basetitle">
                {{order.houseCost}}元/月
            </div>
            <div>
                <ul >
                    <li class="descitem">
                        租赁方式: {{order.houseLeaseType}}
                    </li>
                    <li class="descitem">
                        房屋类型：1室1厅1卫 | {{order.houseArea}}平 | {{order.houseDecoration}}
                    </li>
                    <li class="descitem">
                       朝向楼层：{{order.houseOrientation}}
                    </li>
                    <li class="descitem">
                        所在小区： 开平东汇城(二期) 
                    </li>
                    <li class="descitem">
                        所属区域：开平市   三江
                    </li>
                    <li class="descitem">    
                        详细地址：开平大道 
                    </li>
                
                    
                </ul>
                <div class="userInfo">
                    <div style="padding:50px">
                     <el-avatar style="margin-top:0px" :size="100" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                     <div style="width:100%">
                         <span style="font-size:20px">{{order.userName}}</span>
                     </div>
                    <div class="line"></div>
                    <div style="margin-top:20px">
                    
                        <el-rate
                        v-model="value"
                        disabled
                        show-text>
                        </el-rate>
                    </div>
                    <div class="loginTime">
                        上次登录时间：{{order.userLastLoginTime| dateFmt('yyyy-MM-DD hh:mm:ss')}}
                    </div>
                    
                    <div style="margin-top:20px">
                        <el-button type="success" @click="getTel()" ref="TelB" style="width:200px;height:50px">{{tel}}</el-button>
                    </div>

                    </div>
                     
                </div>
            </div>
        </div>
        <!-- <div class="line"></div> -->
        <div>
            <div style="margin:10px;font-size:18px">
                配套详情
            </div>
            <div class="line"></div>
            <div class="icondiv">
                <ul class="house-disposal">
                        <li class="iconitem" v-if="order.furniture[0]==1"><i class="iconfont" > &#xe685;</i>床</li>
                        <li class="iconitem" v-if="order.furniture[0]==0"><i class="iconfont">&#xe629;</i>床</li>
                        <li class="iconitem" v-show="order.furniture[1]==1"><i class="iconfont">&#xe606;</i>衣柜</li>
                        <li class="iconitem" v-show="order.furniture[1]==0"><i class="iconfont">&#xe629;</i>衣柜</li>
                        <li class="iconitem" v-if="order.furniture[2]==1"><i class="iconfont">&#xe63d;</i>沙发</li>
                        <li class="iconitem" v-if="order.furniture[2]==0"><i class="iconfont">&#xe629;</i>沙发</li>
                        <li class="iconitem" v-if="order.furniture[3]==1"><i class="iconfont">&#xe90e;</i>宽带</li>
                        <li class="iconitem" v-if="order.furniture[3]==0"><i class="iconfont">&#xe629;</i>宽带</li>
                        <li class="iconitem" v-if="order.furniture[4]==1"><i class="iconfont">&#xe62e;</i>油烟机</li>
                        <li class="iconitem" v-if="order.furniture[4]==0"><i class="iconfont">&#xe629;</i>油烟机</li>
                        <li class="iconitem" v-if="order.furniture[5]==1"><i class="iconfont">&#xe60f;</i>洗衣机</li>
                        <li class="iconitem" v-if="order.furniture[5]==0"><i class="iconfont">&#xe629;</i>洗衣机</li>
                        <li class="iconitem" v-if="order.furniture[6]==1"><i class="iconfont">&#xec4f;</i>冰箱</li>
                        <li class="iconitem" v-if="order.furniture[6]==0"><i class="iconfont">&#xe629;</i>冰箱</li>
                        <li class="iconitem" v-if="order.furniture[7]==1"><i class="iconfont">&#xe621;</i>暖气</li>
                        <li class="iconitem" v-if="order.furniture[7]==0"><i class="iconfont">&#xe629;</i>暖气</li>
                        <li class="iconitem" v-if="order.furniture[8]==1"><i class="iconfont">&#xe622;</i>阳台</li>
                        <li class="iconitem" v-if="order.furniture[8]==0"><i class="iconfont">&#xe629;</i>阳台</li>
                         <li class="iconitem" v-if="order.furniture[9]==1"><i class="iconfont">&#xe614;</i>可做饭</li>
                         <li class="iconitem" v-if="order.furniture[9]==0"><i class="iconfont">&#xe629;</i>可做饭</li>
                        <li class="iconitem" v-if="order.furniture[10]==1"><i class="iconfont">&#xe678;</i>燃气灶</li>
                        <li class="iconitem" v-if="order.furniture[10]==0"><i class="iconfont">&#xe629;</i>燃气灶</li>
                        <li class="iconitem" v-if="order.furniture[11]==1"><i class="iconfont">&#xe679;</i>智能门锁</li>
                        <li class="iconitem" v-if="order.furniture[11]==0"><i class="iconfont">&#xe629;</i>智能门锁</li>
                        <li class="iconitem" v-if="order.furniture[12]==1"><i class="iconfont">&#xe6b5;</i>空调</li>
                        <li class="iconitem" v-if="order.furniture[12]==0"><i class="iconfont">&#xe629;</i>空调</li>
                        <li class="iconitem" v-if="order.furniture[13]==1"><i class="iconfont">&#xe603;</i>厕所</li>
                        <li class="iconitem" v-if="order.furniture[13]==0"><i class="iconfont">&#xe629;</i>厕所</li>
                        <li class="iconitem" v-if="order.furniture[14]==1"><i class="iconfont">&#xe67a;</i>电视</li>
                        <li class="iconitem" v-if="order.furniture[14]==0"><i class="iconfont">&#xe629;</i>电视</li>
                        <li class="iconitem" v-if="order.furniture[15]==1"><i class="iconfont">&#xe628;</i>热水器</li>
                        <li class="iconitem" v-if="order.furniture[15]==0"><i class="iconfont">&#xe629;</i>热水器</li>
                </ul>
            </div>

            <div style="margin:10px;font-size:18px">
                房屋详情
            </div>
            <div class="line"></div>
            <ul class="desc"> 

            <li class="a1">
                <span>房屋亮点:</span>
                <span>
                    配套齐全 精装修 南北通透 有阳台 首次出租 随时看房
                </span>
            </li>
            <li class="a1">
                <span>房屋描述:</span>
                <span>
                    {{order.houseDesc}}
                </span>
            </li>

            </ul>
        </div>


    </div>
   <el-footer>
         <myfooter></myfooter>
    </el-footer>
    </el-container>
</template>
<script>
import topHeader from '../components/Header'
import myfooter from '../components/Footer'


export default {
    name: 'desc',
    components:{
            topHeader,
            myfooter
    },
    data(){
        return {
            value:3.7,
            id:'',
            order:'',
           
            userInfo:'',
            lasttime:'',
            tel:'点击查看电话',
            test:'true',
            
            
        }
    },
    methods:{
        init(){
            this.id = this.$route.query.orderId;
            
            this.axios.get(
                'http://localhost:8081/house/get/'+this.id
                
            )
            .then((res)=>{
                this.order = res.data;
                
                
                
            })
            .catch((error)=>{
                alert('连接出错1');
            });
            
          

           
           
        },
        getUserInfo(id){

            
                
                this.axios.get(
                    'http://localhost:8081/userdata/getUser/'+id
                ).then((res)=>{


                    
                    this.userInfo = res.data.data;
                 

                }).catch((error)=>{
                    alert('连接出错2');
                })

            
        },

        getTel(){
           
            this.tel = this.order.userTel;
            alert(0);
            
            
        }
        // test(){
        //     this.id = this.$route.query.orderId;

        //     alert(this.id);
        // }
    },
    created(){
        
       this.init();
      
    }
}
</script>
<style scoped>
.main{
   margin-left:140px;
   width:1200px
}
.imgwrag{
    width:464px;
    height: 433px;
}
.housebase{
    width: 600px;
    position: absolute;
    margin-left: 500px;
    margin-top: -400px;
}
.basetitle{
    font-size: 25px;
    margin-left:38px;
    color:  #67C23A;
}
.line{
      padding-bottom:5px;
      border-bottom:1px solid #1234;
      padding-top:5px;
}
.icondiv{
    width:1400px;
    margin-left: -130px;

}
.iconitem{
    float:left;
    margin-left: 100px;
    margin-bottom:50px;
    text-align: center;
    width:60px;
    height: 70px;
    font-size: 13px;
    color:#555;
}
ul{
 list-style-type: none; 
}
.house-disposal{
    list-style-type: none;
    
    
}
.loginTime{
    margin-top:10px;
    font-size:15px;
    color:darkgrey;
}
.descitem{
    margin-bottom: 18px;
}
.title{
    margin-top:20px;
    margin-bottom: 20px;
    font-size: 30px;
}
.iconfont{
    font-family:"iconfont" !important;
    font-size:50px;font-style:normal;
    -webkit-font-smoothing: antialiased;
    -webkit-text-stroke-width: 0.2px;
    -moz-osx-font-smoothing: grayscale;}
.desc{
    float:left;
   padding-top:20px;
   
}
.a1{
    padding-bottom:20px
}
.userInfo{
    width:300px;
    height:400px;
    border:  1px solid #DCDFE6;
    background:#f5f5f5;
    /* position:absolute; */
    float: right;
    margin-top:-330px;
    margin-right:-100px;
    text-align: center;

}
@font-face {
  font-family: 'iconfont';  /* project id 2444124 */
  src: url('//at.alicdn.com/t/font_2444124_i9hv8kpkb7.eot');
  src: url('//at.alicdn.com/t/font_2444124_i9hv8kpkb7.eot?#iefix') format('embedded-opentype'),
  url('//at.alicdn.com/t/font_2444124_i9hv8kpkb7.woff2') format('woff2'),
  url('//at.alicdn.com/t/font_2444124_i9hv8kpkb7.woff') format('woff'),
  url('//at.alicdn.com/t/font_2444124_i9hv8kpkb7.ttf') format('truetype'),
  url('//at.alicdn.com/t/font_2444124_i9hv8kpkb7.svg#iconfont') format('svg');
}
</style>
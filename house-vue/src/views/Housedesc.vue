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
             <el-button v-if="!isStar" @click="inStar" style="float:right" type="success">
                <i class="el-icon-star-off"/>
            </el-button>
             <el-button v-if="isStar" style="float:right" @click="cancelStar" type="success">
                <i class="el-icon-star-on"/>
            </el-button>
        </div>
       
        <div class="imgwrag">
            <img :src="order.images[0]" style="width:100%;height:400px"/>
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
                        房屋类型：{{order.houseType}} | {{order.houseArea}}㎡ | {{order.houseDecoration}}
                    </li>
                    <li class="descitem">
                       朝向楼层：{{order.houseOrientation}}
                    </li>
                    <li class="descitem">
                        所在小区： {{order.addressZone}}
                    </li>
                    
                    <li class="descitem">    
                        详细地址：{{order.addressDetail}}
                    </li>
                </ul>
                <div class="userInfo">
                    <div style="padding:50px">
                     <el-avatar style="margin-top:0px" :size="100" :src="order.userAvatar"></el-avatar>
                     <div style="width:100%">
                         <span style="font-size:20px">{{order.userNickName}}</span>
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
                    
                    <div style="margin-top:40px;wodth:400px">
                        <el-button type="success" @click="getTel()" ref="TelB" style="width:200px;height:50px">{{tel}}</el-button>
                    </div>

                    </div>
                     
                </div>
            </div>
        </div>
        <!-- <div class="line"></div> -->
        <div style="height:1200px">
            <div style="margin:10px;font-size:18px">
                配套详情
            </div>
            <div class="line" style="margin-bottom:30px"></div>
            <div class="icondiv">
                <ul class="house-disposal">
                        <li class="iconitem" v-if="furniture[0]==1"><i class="iconfont" > &#xe685;</i>床</li>
                        <li class="iconitem ban" v-if="furniture[0]==0"><i class="iconfont">&#xe629;</i>床</li>
                        <li class="iconitem" v-if="furniture[1]==1"><i class="iconfont">&#xe606;</i>衣柜</li>
                        <li class="iconitem ban" v-if="furniture[1]==0"><i class="iconfont">&#xe629;</i>衣柜</li>
                        <li class="iconitem" v-if="furniture[2]==1"><i class="iconfont">&#xe63d;</i>沙发</li>
                        <li class="iconitem ban" v-if="furniture[2]==0"><i class="iconfont">&#xe629;</i>沙发</li>
                        <li class="iconitem" v-if="furniture[3]==1"><i class="iconfont">&#xe90e;</i>宽带</li>
                        <li class="iconitem ban" v-if="furniture[3]==0"><i class="iconfont">&#xe629;</i>宽带</li>
                        <li class="iconitem" v-if="furniture[4]==1"><i class="iconfont">&#xe62e;</i>油烟机</li>
                        <li class="iconitem ban" v-if="furniture[4]==0"><i class="iconfont">&#xe629;</i>油烟机</li>
                        <li class="iconitem" v-if="furniture[5]==1"><i class="iconfont">&#xe60f;</i>洗衣机</li>
                        <li class="iconitem ban" v-if="furniture[5]==0"><i class="iconfont">&#xe629;</i>洗衣机</li>
                        <li class="iconitem" v-if="furniture[6]==1"><i class="iconfont">&#xec4f;</i>冰箱</li>
                        <li class="iconitem ban" v-if="furniture[6]==0"><i class="iconfont">&#xe629;</i>冰箱</li>
                        <li class="iconitem" v-if="furniture[7]==1"><i class="iconfont">&#xe621;</i>暖气</li>
                        <li class="iconitem ban" v-if="furniture[7]==0"><i class="iconfont">&#xe629;</i>暖气</li>
                        <li class="iconitem" v-if="furniture[8]==1"><i class="iconfont">&#xe622;</i>阳台</li>
                        <li class="iconitem ban" v-if="furniture[8]==0"><i class="iconfont">&#xe629;</i>阳台</li>
                         <li class="iconitem" v-if="furniture[9]==1"><i class="iconfont">&#xe614;</i>可做饭</li>
                         <li class="iconitem ban" v-if="furniture[9]==0"><i class="iconfont">&#xe629;</i>可做饭</li>
                        <li class="iconitem" v-if="furniture[10]==1"><i class="iconfont">&#xe678;</i>燃气灶</li>
                        <li class="iconitem ban" v-if="furniture[10]==0"><i class="iconfont">&#xe629;</i>燃气灶</li>
                        <li class="iconitem" v-if="furniture[11]==1"><i class="iconfont">&#xe679;</i>智能门锁</li>
                        <li class="iconitem ban" v-if="furniture[11]==0"><i class="iconfont">&#xe629;</i>智能门锁</li>
                        <li class="iconitem" v-if="furniture[12]==1"><i class="iconfont">&#xe6b5;</i>空调</li>
                        <li class="iconitem ban" v-if="furniture[12]==0"><i class="iconfont">&#xe629;</i>空调</li>
                        <li class="iconitem" v-if="furniture[13]==1"><i class="iconfont">&#xe603;</i>厕所</li>
                        <li class="iconitem ban" v-if="furniture[13]==0"><i class="iconfont">&#xe629;</i>厕所</li>
                        <li class="iconitem" v-if="furniture[14]==1"><i class="iconfont">&#xe67a;</i>电视</li>
                        <li class="iconitem ban" v-if="furniture[14]==0"><i class="iconfont">&#xe629;</i>电视</li>
                        <li class="iconitem" v-if="furniture[15]==1"><i class="iconfont">&#xe628;</i>热水器</li>
                        <li class="iconitem ban" v-if="furniture[15]==0"><i class="iconfont">&#xe629;</i>热水器</li>
                </ul>
            </div>

            <div style="margin:10px;font-size:18px">
                房屋详情
            </div>
            <div class="line"></div>
            <div>

            
            <ul class="desc"> 
            <li class="a1">
                <span class="loginTime">房屋单号：</span>
                <span style="margin-left:20px;color:darkgrey;">
                    {{order.houseId}}
                </span>
            </li>

            <li class="a1">
                <span class="loginTime">发布时间：</span>
                <span style="margin-left:20px;color:darkgrey;">
                    {{order.houseReleaseTime|dateFmt('yyyy-MM-DD hh:mm:ss')}}
                </span>
            </li>
            <li class="a1">
                <span class="loginTime">付款方式：</span>
                <span style="margin-left:20px;color:darkgrey;">{{order.housePayment}}</span>
            </li>
            <li class="a1">
                <span class="loginTime">
                    最早入住时间：
                </span>
                <span style="margin-left:20px;color:darkgrey;">
                    {{order.houseEarlyTime|dateFmt('yyyy-MM-DD hh:mm:ss')}}
                </span>
            </li>
            <li class="a1">
                <span class="loginTime">租金包括：</span>
                <el-tag type="success" style="margin-left:20px" v-for="(item,index) in order.include" :key="index">
                    {{item}}
                </el-tag>
            </li>
            <li class="a1">
                <span class="loginTime">出租要求：</span>
                <el-tag type="success" style="margin-left:20px" v-for="(item,index) in order.requireP" :key="index">
                    {{item}}
                </el-tag>
            </li>
            <li class="a1">
                <span class="loginTime">房屋描述：</span>
                <span style="margin-left:20px">
                    {{order.houseDesc}}
                </span>
            </li>
            <li  >
                <div >
                    <el-image  style="width:398px;height:261px;margin-left:20px;margin-top:20px" :src="item" :preview-src-list="order.images" v-for="(item,index) in order.images" :key="index"/>
                </div>
            </li>
            
            </ul>
        </div>


        </div>
            <div>
                <div style="font-size:18px">
                    地图位置
                </div>

                <div class="line"></div>

                <div id="container" ref="map" >
                    

                </div>
            </div>
            
            <div style="margin-top:30px">
                <div>
                    <div style="font-size:18px">
                    评论区
                </div>
                <div class="line"></div>
                <div class="comment-box">
                    <div style="margin-top:20px;margin-bottom:30px;width:80%">
                          <el-input
                            type="textarea"
                            :autosize="{ minRows: 2, maxRows: 4}"
                            placeholder="请输入内容"
                            @focus="changeShow"
                            v-model="commentContent">
                          </el-input>
                          <div style="margin-top:10px">
                              <div style="float:right" v-show="showReplay">
                                <el-button type="success" @click="submitComment">
                                  回复
                                </el-button>
                                <el-button @click="cancelShow">
                                  取消
                                </el-button>

                              </div>
                              
                          </div>
                    </div>
                  
                    <ul class="comment-list"> 
                        <li class="comment-inline" v-for="(item,index) in commentData" :key="index">
                            <div class="comment" :class="{ 'replay-comment': item.commentPid!=0}"> 
                                <div style="float:left;margin-right:15px">
                                  <el-avatar  :size="50" :src="item.commentAuthorAvatar"></el-avatar>  
                                </div>

                                <div>{{item.commentAuthorName}}</div>
                                <div  class="comment-content">{{item.commentContent}}</div>
                                <div class="comment-time">{{item.commentCreateTime|dateFmt('yyyy-MM-DD hh:mm:ss')}}</div> 

                                
                                <div>
                                    <el-button style="float:right;margin-top:-40px"  type="text" @click="replayComment(item)"><i class="el-icon-chat-square"/>回复</el-button>
                                </div>
                               
                            </div>
                            <!-- <div class="replay-comment"> -->
                                 <!-- <div style="float:left;">
                                  <el-avatar  :size="50" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>  
                                </div>
                                <div class="comment-name">xiao</div>
                                <div class="comment-time">2021.3.29 22:40</div> 
                                <div class="comment-content">这是一段内容</div>
                                <div > 
                                   
                                    <el-button type="text"><i class="el-icon-chat-square"/>回复</el-button>
                                </div>
                                <el-divider></el-divider> -->
                            <!-- </div> -->
                        </li>
                        
                    </ul>
                </div>

                </div>
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
            furniture:[],
            userInfo:'',
            lasttime:'',
            tel:'点击查看电话',
            test:'true',
            location:{},
            isStar:false,
            commentContent:'',
            showReplay:false,
            commentData:'',
            commentForm:{
                // 'commentId':'',
                'commentPid':0,
                'commentHouseId':'',
                'commentAuthorId':'',
                'commentAuthorName':'',
                // 'commentAuthorAvatar':'',
                'commentContent':'',
                'commentCreateTime':'',
                'commentAuthorAvatar':''
            },
            imageData:[
              
            ],


        }
    },
    methods:{
        getStar(){
            
            var name = sessionStorage.getItem('username');

            if(name!=null){
                this.axios.get(
                    'http://localhost:8081/star/getStar',{
                        params:{
                            'houseId':this.order.houseId,
                            'userId':sessionStorage.getItem('userId')
                        }
                    }
                ).then((res)=>{
                    if(res.data.code==200){
                        this.isStar = true;
                        
                    }else{
                         console.log(res.data.msg);  
                    }
                }).catch((error)=>{
                    console.log('连接出错了');
                })
            }
        },
        replayComment(i){
            this.commentContent = '回复 '+ i.commentAuthorName +'：';
            this.commentForm.commentPid = i.commentId;

        },

        submitComment(){

            var name = sessionStorage.getItem('username');

            if(name!=null){
            this.commentForm.commentHouseId = this.$route.query.orderId;
            this.commentForm.commentAuthorId = sessionStorage.getItem('userId');
            this.commentForm.commentContent = this.commentContent;
            this.commentForm.commentAuthorName = sessionStorage.getItem('username');
            console.log(this.userInfo.userAvatar)
            this.commentForm.commentAuthorAvatar = this.userInfo.userAvatar;
            // this,this.commentForm.commentPid = 
            this.axios.post(
                'http://localhost:8081/comment/createcom',
                this.commentForm
            )
            .then((res)=>{
                if(res.data.code==200){
                    this.$message({
                        type:"success",
                        message:'发布成功！'
                    })
                    this.getComment();
                    this.commentContent = '';
                    this.commentForm.commentPid=0
                }
            }).catch((error)=>{
                console.log(error.data)
            })
            }else{

                this.$message({
                    type:'info',
                    message:'请先登录！'
                })
            }
            

        },
        init(){
            this.id = this.$route.query.orderId;
            
            this.axios.get(
                'http://localhost:8081/house/get/'+this.id
            )
            .then((res)=>{
                this.order = res.data;  
                this.furniture = this.order.furniture;
              
              
                this.getlocaltion();
                let name =  sessionStorage.getItem('username');
                if(name!=null)
                {this.getStar();}
            })
            .catch((error)=>{
                alert('连接出错1');
            });
 
        },
        inStar(){
            var name = sessionStorage.getItem('username');
            if(name==null){
                 this.$message({
                    message: '请先登录！',
                    type: 'warning'
                    });
                    return ;
            }
            this.axios.post(
                'http://localhost:8081/star/inStar'+'?userId='+sessionStorage.getItem('userId')+'&houseId='+this.order.houseId

            )
            .then((res)=>{

                if(res.data.code){
                    this.isStar = true;
                }
            })
            .catch((error)=>{
                console.log("连接出错");
            })
        },
        changeShow(){
            this.showReplay = true;
        },
        cancelShow(){
            this.showReplay = false;
            this.commentContent = '';
        },
        cancelStar(){
            this.axios.delete(
                'http://localhost:8081/star/delStar',
                {
                      params:{
                            'houseId':this.order.houseId,
                            'userId':sessionStorage.getItem('userId')
                        }
                }
            )
            .then((res)=>{
                if(res.data.code==200){

                    this.isStar = false;
                    this.$message({
                        type:'success',
                        message:'取消收藏成功'
                    })
                }else{
                    this.$message({
                        type:'error',
                        message:'取消收藏失败'
                    })
                }
            }).catch((error)=>{
                console.log("连接出错");
            })
        },
        getUserInfo(){  
                let id = sessionStorage.getItem('userId')
                this.axios.get(
                    'http://localhost:8081/userdata/getUser/'+id
                ).then((res)=>{
           
                    this.userInfo = res.data.data;
                    console.log(this.userInfo)
                }).catch((error)=>{
                    alert('连接出错2');
                })

            
        },
        getlocaltion(){
            this.axios.get(
                '/localapi/geocoder/v1/?address='+this.order.addressDetail+'&key=W4JBZ-GM2CG-WL5QI-IXRPZ-II4E7-NDFDH'
            )
            .then((res)=>{
                this.location = res.data.result.location;
               
            })
            .catch((error)=>{
                console.log('获取地址失败');
            })
        },
        getComment(){
            this.axios.get(
                'http://localhost:8081/comment/getCommentByOrder/'+this.$route.query.orderId
            )
            .then((res)=>{
                if(res.data.code==200){
                    this.commentData = res.data.data;
                    
                }
            }).catch((error)=>{
                console.log(error.data)
            })
        },
        getTel(){
            this.tel = this.order.userTel;
        },
        initMap(){
            
            
             console.log(this.location);
            var center = new TMap.LatLng(this.location.lat,this.location.lng);
            //初始化地图
            var map = new TMap.Map(this.$refs.map, {
                rotation: 20,//设置地图旋转角度
                pitch:30, //设置俯仰角度（0~45）
                zoom:12,//设置地图缩放级别
                center: center//设置地图中心点坐标
            });
             var marker = new TMap.MultiMarker({
            id: "marker-layer", //图层id
            map: map,
            styles: { //点标注的相关样式
                "marker": new TMap.MarkerStyle({
                    "width": 25,
                    "height": 35,
                    "anchor": { x: 16, y: 32 },
                    "src": "https://mapapi.qq.com/web/lbs/javascriptGL/demo/img/markerDefault.png"
                })
            },
            geometries: [{ //点标注数据数组
                "id": "demo",
                "styleId": "marker",
                "position": new TMap.LatLng(this.location.lat, this.location.lng),
                "properties": {
                    "title": "marker"
                }
            }]
        });

        }
        // test(){
        //     this.id = this.$route.query.orderId;

        //     alert(this.id);
        // }
    },
    created(){
      this.init();
      this.getComment();
    },
    mounted(){
     setTimeout(()=>{
         this.initMap();
     },1000); 
     this.getUserInfo();  
     
           
       
        
    }
}
</script>

<style scoped>
.main{
   margin-left:140px;
   width:1200px;
    
}
.comment-content{
    padding-top:10px;
    font-size: 18px;
}
.replay-comment{
    margin-left: 50px;
}
.comment-time{
    font-size: 13px;
    margin-top: 10px;
    margin-left: 60px;
    color:darkgrey;
}
.imgwrag{
    width:464px;
    height: 433px;
}
.housebase{
    width: 350px;
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
.comment-inline{
    margin-top:20px
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
.comment-list{
    margin-top: 80px;
    width:60%
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
    margin-top:-290px;
    margin-right:-350px;
    text-align: center;

}
.ban{
    color:darkgrey;
}
#container{
    width:800px;
    height:400px;
    margin-top:40px;
    margin-left:50px
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
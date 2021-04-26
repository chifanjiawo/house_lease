<template>
    <el-container >

    <topHeader></topHeader>

        <div style="margin-left:400px;padding-bottom:10px">
        <span class="na" style="color:green" @click="selectActive1()">首页</span>

        <span class="na">
            <router-link to="/search" style="text-decoration:none;color:#303133">
                租房
            </router-link>
            </span>
        <!-- <span class="na">二手市场</span>
        <span class="na" >关于我们</span> -->

        </div>
    <div class="main">
        <div class="block;">


        <div class="box">
            <div style="padding:20px">
                <div class="up">
                <span  style="color:#67C23A">热门区域</span>
                <div style="margin-top:20px">
                    
                    <el-link @click="toSearchWithAddress(index)" v-for="(item,index) in zone.area" :underline="false" :key="index" style="margin-left:10px">
                        {{item.fullname}}
                    </el-link>
                    
                </div>
                </div>
                <div class="line"/>
                <div class="down">
                 <span style="color:#67C23A" >租房预算</span>
                 <div style="margin-top:20px">
                     <el-link @click="toSearchWithCost(item.id)" style="margin-left:10px" :underline="false" v-for="item in costList" :key="item.id">
                        {{item.cost}}
                     </el-link>
                 </div>
                </div>
            </div>

        </div>

        <el-carousel trigger="click" height="400px" style="margin-left:-10px">

        <el-carousel-item v-for="item in images" :key="item.id" >
            <img :src="item.idview" style="width:100%;height:100%" >
        </el-carousel-item>

        </el-carousel>
    </div>

    <div style="margin:20px;height:600px">
        <div style="padding:20px;margin-top:25px;marin-bottom:25px;">
            <span style="font-size:30px;color:#67C23A">房屋推荐</span>
            <span style="float:right;width:150px;font-size:15px;margin-right:-30px;margin-top:10px;"><router-link to="/search" style="text-decoration : none;color:#818EA2;">查看更多的房源</router-link></span>
            <div class="line"></div>
        </div>
        <div style="margin-left:30px;margin-top:10px;width:1300px;">
            <a class="a1" @mouseover="mouseover(index)" @mouseleave="mouseleave(index)"  v-for="(item,index) in recom" :key="index" @click="todesc(item.houseId)" >
                <img :src="item.images[0]" style="width:100%;height:200px"/>
                <div style="background-color: #67C23A;color: #FFFFFF;margin-top:-5px;font-size:13px">
                    <span >{{item.houseTitle}}</span>
                    
                </div>
                <div style="padding-top:5px;font-size:13px">
                    <span ref="s1" >{{item.houseArea}}㎡</span>
                    <span ref="s3" style="margin-left:120px ">{{item.houseCost}}/月</span>
                </div>
                <div style="padding-top:5px;font-size:13px">
                    <span ref="s2">{{item.houseDecoration}}</span>
                </div>

            </a>
            <!-- <span v-for='item in recom' :key="item.id">{{item.id}}</span> -->
        </div>
        <!-- <div style="padding:20px;margin-top:25px;marin-bottom:25px;">
            <span style="font-size:30px;color:#67C23A">房屋类型</span>
             <div class="line"></div>
        </div> -->
        <!-- <div style="width:1300px">
            <ul style="list-style-type: none;" >
                <li style="float:left;margin-left:40px">
                    <div style="position:absolute;">
                            <ul style="list-style-type: none;margin-top:50px;">
                                <li>
                                    <span style="font-size:15px">个人房源</span>
                                </li>
                                <li>
                                    <span style="font-size:10px;color:#909399">海量房东直租房任你挑</span>
                                </li>
                                <li>
                                    <router-link to="/search">
                                    <el-button  style="width:100px;background-color:transparent;" round>点击进入</el-button>
                                    </router-link>
                                </li>
                            </ul>



                        </div>
                    <a>
                        <img  src="//pic7.58cdn.com.cn/nowater/fangfe/n_v2193f9f1fbdf74749ab6ff094b90edd40.png" style="display: block;">

                    </a>
                </li>

                <li style="float:left;margin-left:40px">
                    <div style="position:absolute;">
                            <ul style="list-style-type: none;margin-top:50px;">
                                <li>
                                    <span style="font-size:15px">精品二手房</span>
                                </li>
                                <li>
                                    <span style="font-size:10px;color:#909399">总有一款适合你</span>
                                </li>
                                <li>
                                    <el-button  style="width:100px;background-color:transparent;" round>点击进入</el-button>
                                </li>
                            </ul>

                        </div>
                    <a>
                         <img src="//pic6.58cdn.com.cn/nowater/fangfe/n_v28114f578f4dc4e24908ad50e6f3f5fcb.png" style="display: block;">
                    </a>
                </li>

                <li style="float:left;margin-left:40px">
                    <div style="position:absolute;">
                            <ul style="list-style-type: none;margin-top:50px;">
                                <li>
                                    <span style="font-size:15px">精选新房</span>
                                </li>
                                <li>
                                    <span style="font-size:10px;color:#909399">优惠好房随心挑</span>
                                </li>
                                <li>
                                    <el-button  style="width:100px;background-color:transparent;" round>点击进入</el-button>
                                </li>
                            </ul>



                        </div>
                    <a>
                         <img src="//pic8.58cdn.com.cn/nowater/fangfe/n_v2e47412b98a2e42898064ea75a33e235b.png" style="display: block;">
                    </a>
                </li>
            </ul>
        </div> -->
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

        name: "Index",
        components:{
            topHeader,
            myfooter
        },
        data(){
            return {
                ss:false,
                images:[
                    {id:0,idview:require('../assets/5.jpeg')},
                    {id:1,idview:require('../assets/6.jpeg')},
                    // {id:2,idview:require('../assets/7.jpeg')},

                ],
                
                costList:[
                    {id:0,cost:'不限'},
                    {id:1,cost:'500元以下'},
                    {id:2,cost:'500元-1000元'},
                    {id:3,cost:'1000元-1500元'},
                    {id:4,cost:'1500元-2000元'},
                    {id:5,cost:'2000-3000元'},
                ],
                recom:[
                
                ],
                myIp:'',
                zone:{
                    city:'',
                    area:[]
                }

            }
        },
        methods: {
            test(){
                alert("test");
            },
            toSearchWithAddress(id){
                this.$router.push({
                    path:'/search',
                    query:{
                      houseCost:0,
                      houseType:0,
                      houseLeaseType:0,
                      houseDecoration:'装修不限',
                      houseOrientation:'朝向不限',
                      houseAddress:this.zone.area[id].fullname,
                      active:id
                    }
                })
            },
            toSearchWithCost(id){
                this.$router.push({
                    path:'/search',
                    query:{
                      houseCost:id,
                      houseType:0,
                      houseLeaseType:0,
                      houseDecoration:'装修不限',
                      houseOrientation:'朝向不限',
                      houseAddress:'不限'
                    }
                })
            },

            // getIP(){
            //     this.axios.get(
            //       '/ipapi'
            //     )
            //     .then((res)=>{
            //         this.myIp = res.data;
                    
            //         this.getCity();
            //     })
            //     .catch((error)=>{
            //         alert(JSON.stringify(error));
            //     })
               
            // },
            // getCity(){
                 
                
                
            //     this.axios.get(
                    
            //         '/localapi/location/v1/ip?ip='+ this.myIp +'&key=W4JBZ-GM2CG-WL5QI-IXRPZ-II4E7-NDFDH'
            //     ).then((res)=>{
            //        this.zone.city =  res.data.result.ad_info.city;
            //        sessionStorage.setItem('city',this.zone.city);
                   
            //         var url = '/localapi/district/v1/search?&keyword='+this.zone.city+'&key=W4JBZ-GM2CG-WL5QI-IXRPZ-II4E7-NDFDH';
                    
            //        this.axios.get(
            //            url
            //        )
            //        .then((res)=>{

            //            this.axios.get(
            //                '/localapi/district/v1/getchildren?id='+ res.data.result[0][0].id + '&key=W4JBZ-GM2CG-WL5QI-IXRPZ-II4E7-NDFDH'
            //            )
            //            .then((res)=>{
            //                this.zone.area = res.data.result[0];
            //                this.Global.ip = this.myIp;
            //                this.Global.zone = this.zone;
            //                this.Global.zone.area.unshift({'fullname':'不限'});
            //            })
            //            .catch((error)=>{
            //                alert('3连接出错');
            //            })

                       
            //        })
            //        .catch((error)=>{
            //            alert('1'+JSON.stringify (error));
            //        })

            //     }).catch((error)=>{
            //         alert('2'+JSON.stringify (error));
            //     })
            // },


            mouseover(id){
                this.$refs.s1[id].style.color = " #67C23A";
                this.$refs.s2[id].style.color = " #67C23A";
                this.$refs.s3[id].style.color = " #67C23A";
            },
            mouseleave(id){
               this.$refs.s1[id].style.color = "#303133";
               this.$refs.s2[id].style.color = "#303133";
               this.$refs.s3[id].style.color = "#303133";
            },

            initData(){
                
                
                

                this.axios.get(
                    'http://localhost:8081/house/getRecom'
                ).then((res)=>{
                    this.recom = res.data.data;
                    
                     
                }).catch((error)=>{
                    alert("获取推荐房源失败");
                })
            },
            todesc(id){
                this.$router.push(
                   {
                       path: '/houseorder',
                       query:{
                           orderId: id
                       }
                       
                   }
                );
            }
        },
        created(){
             this.initData();
            //  this.getIP();
            setTimeout(() => {
                this.zone = this.Global.zone
           
            },1500)
             
        },
        mounted(){
            
            
        },
        // watch:{
        //     Global:{
        //         handler(){
        //             if(this.Global.zone.area!=undefined && this.Global.zone.area.length!=0){
        //                  this.zone = this.Global.zone
        //             }
                   
        //             console.log(this.zone)
        //             console.log("Global值改变了")
        //         },
        //         immediate: true,
        //         deep:true
        //     }
        // }
        
        
        
        
    }
</script>

<style scoped>
.background {
    background-image: url(../assets/backg.jpeg);
    background-attachment:fixed;
    background-size: cover;
    min-width: 100%;
    height: 350px;
    margin-top: 10px;
    margin-left: -10px;
}
.a1{
    height:280px;
    width:265px;
    display:inline-block;
    margin-left:50px;
    margin-top:30px;
    cursor:pointer;
}
/* .box {

    background-color: #FFFFFF;
    width: 400px;
    height: 300px;
    margin-top:10px;
    margin-left: 30%;
} */
.main{
    /* height:1500px; */
    margin-bottom: 30px;
}

span{
  width: 50%;
  height: 10px;

  padding: 10px ;
}
 .box {

    background-color: #FFFFFF;
    border: 1px solid #DCDFE6;
    width: 300px;
    height: 300px;
    position: absolute;
    z-index:1000;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    margin-left: 100px;
    margin-top:50px;

  }
 
  .up{
      padding: 15px;
      font-size: 15px;

  }
  .down{
      padding: 15px;
      font-size: 15px;

  }
  .line{
      padding-bottom:5px;
      border-bottom:1px solid #1234;
      padding-top:5px;

  }
  .recom{

      margin-left:50px;
      margin-top:25px;
      border: 1px solid #DCDFE6;
      width:300px;
      height:400px;
      position:relative;

  }
  .hs{
      color: #67C23A;
      text-decoration:underline;
  }
  .na{
    cursor: pointer;
    margin-left: 10px;
}
</style>

<template>
    <el-container>
    <topHeader></topHeader>
    
    <div class="main">
        <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-top:20px">
        <el-breadcrumb-item :to="{ path: '/' }">租房首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/search' }">租房列表</el-breadcrumb-item>
        
        </el-breadcrumb>
        <div class="line"></div>

        <div>
            <dl style="margin-left:-35px">
                <dd>
                    
                    
                    <a :class="{ bgColor : current==index }" @click="changeActive(index)" v-for="(item,index) in addressParams" :key="index" style="cursor: pointer;">
                        {{item.fullname}}
                    </a>

                </dd>
                <dd>
                    <a>租金:</a>
                    <a ref="c1" @click="getCostParam(index)"  style="cursor: pointer;" v-for="(item ,index) in costParams" :key="index">
                        {{item.cost}}
                    </a>
                    

                </dd>
                <dd>
                    <a>厅室:</a>
                   
                    <a ref="type"  @click="getHouseTypeParam(index)"  style="cursor: pointer;"  v-for="(item,index) in  roomTypeParams" :key="index">
                        {{item}}
                    </a>
                </dd>
                <dd>
                    <a>方式:</a>
                    <a ref="me"  @click="getLeaseTypePraram(index)"  style="cursor: pointer;"  v-for="(item,index) in leaseTypeParams" :key="index">
                        {{item}}
                    </a>
                    
                </dd>

                <dd>
                    <a>其他：</a>
                    <a>
                        <el-dropdown @command="handleCommand1" size="mini" split-button type="success">
                        {{searchPram.orientation}}
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item :command="{value:item}" v-for="(item,index) in orientationParams" :key="index">{{item}}</el-dropdown-item>
                            
                        </el-dropdown-menu>
                        </el-dropdown>
                    </a>
                    <a>
                        <el-dropdown @command="handleCommand2" size="mini" split-button type="success">
                        {{searchPram.decoration}}
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item :command="{value:item}" v-for="(item,index) in decorationParams" :key="index">{{item}}</el-dropdown-item>
                            
                        </el-dropdown-menu>
                        </el-dropdown>
                    </a>
                </dd>

            </dl>
        </div>
        <div class="infinite-list-wrapper" style="overflow:auto">
        <ul
        class="list"
        v-infinite-scroll="load"
        infinite-scroll-disabled="disabled" style="list-style-type: none;">
        <li v-for=" (item,index) in orderList" :key="index" class="list-item" ref="list" @mouseover="selectActive(index)" @mouseleave="cancelActive(index)" @click="toOrderDesc(item.houseId)"  style="margin-left:-30px">

            <div class="im">
                <img :src="item.images[0]" style="width:100%;height:100%"/>
            </div>
            <div class="item" >
                <h2 ref="title">{{item.houseLeaseType}} | {{item.houseTitle}}</h2>
                <p>{{item.houseType}}    {{item.houseArea}}㎡</p>
                <p>{{item.houseAddress}}   </p>
                <p>来自: {{item.userName}}</p>
            </div>
            <div style="float:right;margin-top:-130px;margin-right:10px;color: #67C23A;font-size:20px">
                {{item.houseCost}}元/月
            </div>
            <div id="line-item"/>
        </li>
        </ul>
    

        <p v-if="loading">加载中...</p>
        <p v-if="noMore">没有更多了</p>
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
    name: 'searchList',
    components:{
            topHeader,
            myfooter
    },
    data(){
        return {
            count:0,
            loading: false,
            orderList:[],
            searchPram:{
                'address':'',
                'cost':0,
                'roomType':0,
                'leaseType':0,
                'orientation':'朝向不限',
                'decoration':'装修不限'
            },
            addressParams:[],
            costParams:[
                {id:0,cost:'不限'},
                {id:1,cost:'500以下'},
                {id:2,cost:'500-1000元'},
                {id:3,cost:'1000-1500元'},
                {id:4,cost:'1500-2000元'},
                {id:5,cost:'2000-3000元'},
                {id:6,cost:'3000-4500元'},
                {id:7,cost:'4500元以上'}
            ],
            roomTypeParams:['不限','一室','两室','三室','四室','四室以上'],
            leaseTypeParams:['不限','整套出租','单间出租'],
            decorationParams:['装修不限','毛坯','简单装修','精装修','豪华装修'],
            orientationParams:['朝向不限','朝东','朝南','朝西','朝北','朝南北','朝东西','朝东南','朝西南','朝东北','朝西北'],
            current:0,

        }
    },
    // watch:{
    //     searchPram:{
    //         immediate : true,
    //         deep:true,
    //         handler(newVal,objVal){
            
    //         this.isActive();

    //       }
          
    //     }
    // },
    computed: {
      noMore () {
        return this.count >= this.orderList.length
      },
      disabled () {
        return this.loading || this.noMore
      },
      
     },
    watch:{
        $route:{
            handler(){
                const { houseCost } = this.$route.query;
                const { houseType } = this.$route.query;
                const { houseLeaseType } = this.$route.query;
                const { houseOrientation } = this.$route.query;
                const { houseDecoration }  = this.$route.query;
                const {houseAddress} = this.$route.query;
                const{active} =this.$route.query;
                if(active!=null && active!=undefined){
                    this.current = active;
                }

                if(houseCost!=undefined){
                this.axios.get(
                    'http://localhost:8081/house/get/listByFactor',{

                        params:{
                            houseCost:houseCost,
                            houseType:houseType,
                            houseLeaseType:houseLeaseType,
                            houseOrientation:houseOrientation,
                            houseDecoration:houseDecoration,
                            houseAddress:houseAddress
                        }
                    }
                )
                .then((res)=>{
                    this.orderList = res.data;
                })
                .catch((error)=>{
                    alert('连接出错');
                })
                }
                
                
                

            },
            immediate:true
        }
    },
    methods: {
    
      load () {
         this.loading = true
        setTimeout(() => {
          this.count += 1
          this.loading = false
        }, 2000)
      },
      selectActive(id){
          
          this.$refs.title[id].style.color = " #67C23A";
          this.$refs.list[id].style.background = "#f5f5f5";
      },
      cancelActive(id){
          this.$refs.title[id].style.color = "#303133";
          this.$refs.list[id].style.background = "";
      },
      initData(){
          
        
          var cost =  this.$route.query.houseCost;
          if(cost==null ||cost==undefined){
              this.axios.get(
              'http://localhost:8081/house/getRecom'
                )
                .then((res)=>{
                    if(res.data.code==200){
                        this.orderList = res.data.data;
                    }  
                }).catch((error)=>{
                    alert('连接出错');
                })
          }
          

      },
      toOrderDesc(id){
          this.$router.push(
              {
                  path:'/houseorder',
                  query:{
                           orderId: id
                       }
              }

          )
      },
      changeActive(id){ 
          this.current = id;
          this.searchPram.address = this.Global.zone.area[id].fullname;
          this.$router.push({
              path:'/search',
              query:{
                  houseCost:this.searchPram.cost,
                  houseType:this.searchPram.roomType,
                  houseLeaseType:this.searchPram.leaseType,
                  houseDecoration:this.searchPram.decoration,
                  houseOrientation:this.searchPram.orientation,
                  houseAddress:this.searchPram.address
              }
          });
      },
      isCostActive(){
          
            // if(this.$route.query.address!=null){
            //   this.searchPram.address = this.$route.query.address;
            // }else{
                
            // }
          
          if(this.$route.query.houseCost!=undefined){
              
              this.$refs.c1[this.searchPram.cost].style.color = '#303133';
              this.searchPram.cost = this.$route.query.houseCost;
              this.$refs.c1[this.searchPram.cost].style.color = '#67C23A';
              
          }else{
              
              this.$refs.c1[0].style.color = '#67C23A';
          }

      },
      isTypeActive(){

             if(this.$route.query.houseType!=null  && this.$route.query.houseType!=undefined){
                   this.$refs.type[this.searchPram.roomType].style.color = '#303133';
                   this.searchPram.roomType = this.$route.query.houseType;
                   this.$refs.type[this.searchPram.roomType].style.color = '#67C23A';
              }else{
                   this.$refs.type[0].style.color = '#67C23A';
              }
      },
      isLeaseActive(){
               if(this.$route.query.houseLeaseType!=null  && this.$route.query.houseLeaseType!=undefined){
                this.$refs.me[this.searchPram.leaseType].style.color = '#303133';
                this.searchPram.leaseType = this.$route.query.houseLeaseType;
                this.$refs.me[this.searchPram.leaseType].style.color = '#67C23A';
                }else{
                    this.$refs.me[0].style.color = '#67C23A';
                }
      },

      getCostParam(id){
          
          
          
          this.$router.push(
              {
                  path:'/search',
                  query:{
                      houseCost:id,
                      houseType:this.searchPram.roomType,
                      houseLeaseType:this.searchPram.leaseType,
                      houseDecoration:this.searchPram.decoration,
                      houseOrientation:this.searchPram.orientation,
                      houseAddress:this.searchPram.address
                  }
                  
              }
            );
          this.isCostActive();
          
      },
      getHouseTypeParam(id){

          this.$router.push(
              {
                  path:'/search',
                  query:{
                      houseCost:this.searchPram.cost,
                      houseType:id,
                      houseLeaseType:this.searchPram.leaseType,
                      houseDecoration:this.searchPram.decoration,
                      houseOrientation:this.searchPram.orientation,
                      houseAddress:this.searchPram.address
                  }
                  
              }
            );
          this.isTypeActive();
      },
      getLeaseTypePraram(id){

          this.$router.push(
              {
                  path:'/search',
                  query:{
                      houseCost:this.searchPram.cost,
                      houseType:this.searchPram.roomType,
                      houseLeaseType:id,
                      houseDecoration:this.searchPram.decoration,
                      houseOrientation:this.searchPram.orientation,
                      houseAddress:this.searchPram.address
                  }
                  
              }
            );
          this.isLeaseActive();

      },
      handleCommand1(command){
         this.searchPram.orientation = command.value;
          this.$router.push(
              {
                  path:'/search',
                  query:{
                      houseCost:this.searchPram.cost,
                      houseType:this.searchPram.roomType,
                      houseLeaseType:this.searchPram.leaseType,
                      houseDecoration:this.searchPram.decoration,
                      houseOrientation:this.searchPram.orientation,
                      houseAddress:this.searchPram.address
                  }
                  
              }
            );
          

      },
        handleCommand2(command){

          this.searchPram.decoration = command.value;
          this.$router.push(
              {
                  path:'/search',
                  query:{
                      houseCost:this.searchPram.cost,
                      houseType:this.searchPram.roomType,
                      houseLeaseType:this.searchPram.leaseType,
                      houseDecoration:this.searchPram.decoration,
                      houseOrientation:this.searchPram.orientation,
                      houseAddress:this.searchPram.address
                  }
                  
              }
           );
      },
      
    },
    created(){

        if(this.$route.params.key==null || this.$route.params.key==undefined){
            this.initData();
        }else{
             this.orderList = this.$route.params.resultList
        }
       this.addressParams = this.Global.zone.area; 
        
    },
    mounted(){
        this.isCostActive();
        this.isTypeActive();
        this.isLeaseActive();
        // alert(JSON.stringify (this.Global.zone.area));
        
    }
    

    
}
</script>

<style scoped>
.main{
    width:900px;
    
    margin-left: 15%;
    margin-right: 20%; 
}
.line{
      padding-bottom:5px;
      border-bottom:1px solid #1234;
      padding-top:5px;
      
  }
dd{
    padding: 5px;
    font-size: 15px;
}
a{
    margin-left:5px
}
/* dl{
    cursor: pointer;
} */


.list-item{
    padding-top: 20px;
   
}
li{
    /* padding-bottom:20px; */
    cursor: pointer;
}
#line-item{
     padding-bottom:10px;
     border-bottom:1px solid #1234;
     padding-top:10px;
}
.item{
    position:absolute;
    margin-left:180px;
    margin-top:-170px;
}
.im{
    width:150px;
    height:150px;
    margin-left:10px
}
.bgColor{
    color: #67C23A;
}
</style>
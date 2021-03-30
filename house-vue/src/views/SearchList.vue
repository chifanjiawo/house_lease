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
                    <a>不限</a>
                    <a>蓬江</a>
                    <a>新会</a>
                    <a>江海</a>
                    <a>开平市</a>
                </dd>
                <dd>
                    <a>租金:</a>
                    <a>不限</a>
                    <a>500元以下</a>
                    <a>500-1000元</a>
                    <a>1000-1500元</a>
                    <a>1500-2000元</a>
                    <a>2000-3000元</a>
                    <a>3000-4500元</a>
                    <a>4500元以上</a>

                </dd>
                <dd>
                    <a>厅室:</a>
                    <a>不限</a>
                    <a>一室</a>
                    <a>两室</a>
                    <a>三室</a>
                    <a>四室</a>
                    <a>四室以上</a>
                </dd>
                <dd>
                    <a>方式:</a>
                    <a>不限</a>
                    <a>整套出租</a>
                    <a>单间出租</a>
                </dd>

            </dl>
        </div>
        <div class="infinite-list-wrapper" style="overflow:auto">
        <ul
        class="list"
        v-infinite-scroll="load"
        infinite-scroll-disabled="disabled" style="list-style-type: none;">
        <li v-for="i in count" class="list-item" :key="i" ref="list" @mouseover="selectActive(i-1)" @mouseleave="cancelActive(i-1)" style="margin-left:-30px">
            <div class="im">
                <img src="../assets/3.jpg" style="width:100%;height:100%"/>
            </div>
            <div class="item" >
                <h2 ref="title">整租 | 全新公寓，装修新净租价1500可短租</h2>
                <p>1室     42㎡</p>
                <p>三江    开平东汇城(二期)</p>
                <p>来自经纪人: 开平嘉联地产 何晓娥</p>
            </div>
            <div style="float:right;margin-top:-130px;margin-right:10px;color: #67C23A;font-size:20px">
                1500元/月
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
            count:10,
            loading: false
        }
    },
    computed: {
      noMore () {
        return this.count >= 20
      },
      disabled () {
        return this.loading || this.noMore
      }
    },
    methods: {
      load () {
         this.loading = true
        setTimeout(() => {
          this.count += 2
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
      }


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
</style>
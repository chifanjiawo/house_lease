<template>
<div>
    <div>
        我所收藏的房源信息
    </div>
    <el-divider></el-divider>

    <el-table
      :data="starList"
      style="width: 100%"
      
      >
      <el-table-column
        prop="houseId"
        label="订单号"
        width="120">
      </el-table-column>
      <el-table-column
        prop="houseTitle"
        label="标题"
        width="100">
      </el-table-column>
      <el-table-column
        prop="houseAddress"
        label="地址"
        width="80">
      </el-table-column>
      <el-table-column
        prop="houseCost"
        label="价格(元/月)"
        width="100">
      </el-table-column>
      <el-table-column
        prop="houseReleaseTime"
        label="发布日期">
      </el-table-column>

      <el-table-column label="操作" width="200">
      <template slot-scope="scope">
        <el-button size="mini" @click="toOrderDesc(scope.row.houseId)"><i class="el-icon-search"></i></el-button>
          
      </template>
      </el-table-column>

    </el-table>
</div>
</template>
<script>
export default {
    name:'myStar',
    data(){
      return{
        starList:""
      }
    },
    methods:{
      initData(){
        var userId = sessionStorage.getItem('userId');

        if(userId!=null){
           this.axios.get('http://localhost:8081/star/getMyStar',
          {
            params:{
              'userId':userId
            }
          }
        )
        .then((res)=>{
          this.starList = res.data.data
        }).catch((error)=>{
          console.log('连接出错');
        })

        }
       
      },
      toOrderDesc(id){
        this.$router.push({
              
                 path: '/houseorder',
                 query:{
                        orderId: id
                 } 
                   
            })
      }
    },
    created(){
      this.initData();
    }
}
</script>
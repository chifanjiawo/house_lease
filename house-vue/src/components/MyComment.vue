<template>
<div>
     <div style="margin-bottom:10px;margin-top:-30px"> 
            <el-menu default-active="1" class="el-menu-demo" mode="horizontal" >
            <el-menu-item index="1" @click="changeMenu1">我的评论</el-menu-item>
            <el-menu-item index="2" @click="changeMenu2" >回复我的</el-menu-item>
            
            </el-menu>
        </div>
    
    <div v-if="!flag">
       <el-table
      :data="commentList"
      style="width: 100%"
      
      >
      <el-table-column
        prop="commentId"
        label="评论编号"
        width="80">
      </el-table-column>
      <el-table-column
        prop="commentPid"
        label="被评论人Id"
        width="90">
      </el-table-column>
      <el-table-column
        prop="commentContent"
        label="内容"
        width="200">
      </el-table-column>
     
      <el-table-column
        prop="commentCreateTime"
        label="发布日期">
      </el-table-column>

      <el-table-column label="操作" width="100">
      <template slot-scope="scope">
        <!-- <el-button size="mini" @click="delComment(scope.commentId)"><i class="el-icon-search"></i></el-button> -->
          <el-button
          size="mini"
          type="danger"
          @click="delComment(scope.row.commentId)"><i class="el-icon-delete"></i></el-button>
          </template>
      </el-table-column>

    </el-table>

    </div>

    <div v-if="flag">

       <el-table
      :data="replayList"
      style="width: 100%"
      >
      <el-table-column
        prop="commentId"
        label="评论编号"
        width="80">
      </el-table-column>
      <el-table-column
        prop="commentAuthorName"
        label="用户名"
        width="90">
      </el-table-column>
      <el-table-column
        prop="commentContent"
        label="内容"
        width="200">
      </el-table-column>
     
      <el-table-column
        prop="commentCreateTime"
        label="回复时间">
      </el-table-column>

      <el-table-column label="操作" width="100">
      <template slot-scope="scope">
        <!-- <el-button size="mini" @click="delComment(scope.commentId)"><i class="el-icon-search"></i></el-button> -->
          <el-button
          size="mini"
          @click="toOrder(scope.row.commentHouseId)"><i class="el-icon-search"></i></el-button>
          </template>
      </el-table-column>

    </el-table>

    </div>
   
</div>
</template>
<script>
export default {
    name:'myComment',
    data(){
      return{
        commentList:"",
        replayList:"",
        flag:false,
      }
    },
    methods:{
      initData(){
        var userId = sessionStorage.getItem('userId');

        if(userId!=null){
           this.axios.get('http://localhost:8081/comment/getUserComment/'+userId
        )
        .then((res)=>{
          this.commentList = res.data.data
        }).catch((error)=>{
          console.log('连接出错');
        })

        }
       
      },
      delComment(id){
          this.$confirm('此操作将删除该发布, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            }).then(() => {

                  this.axios.delete(
              'http://localhost:8081/comment/deleteComment/'+id
            ).then((res)=>{
              if(res.data.code==200){

                this.$message({
                  type:'success',
                  message:'删除成功!'
                })
                this.initData();
              }
            }).catch((error)=>{
              console.log("请检查网络");
            }
            
            )
            })
       

      },
      initReplayData(){
        this.axios.get('http://localhost:8081/comment/getReplay/'+sessionStorage.getItem('userId'))
        .then((res)=>{

            this.replayList = res.data.data;


        }).catch((error)=>{
           console.log(error);
        })
      },
      changeMenu1(){
        this.flag = false;
      },
      changeMenu2(){
        this.flag = true;

      },toOrder(id){
        this.$router.push({
              
                 path: '/houseorder',
                 query:{
                        orderId: id
                 }
                       
                   
            })
      }
      // toOrderDesc(id){
      //   this.$router.push({
              
      //            path: '/houseorder',
      //            query:{
      //                   orderId: id
      //            } 
                   
      //       })
      // }
    },
    created(){
      this.initData();
      this.initReplayData();
    }
}
</script>
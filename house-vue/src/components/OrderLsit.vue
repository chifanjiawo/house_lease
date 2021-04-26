<template>
    <div class="main">
        <div style="margin-bottom:10px;margin-top:-30px"> 
            <el-menu default-active="1" class="el-menu-demo" mode="horizontal" >
            <el-menu-item index="1" @click="changeMenu1">发布历史</el-menu-item>
            <el-menu-item index="2" @click="changeMenu2" >发布房源</el-menu-item>
            
            </el-menu>
        </div>
    <div v-show="flag">
    <el-table
      :data="tableData"
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
        <el-button
          size="mini" type="success"
          @click="getUpdatOrder(scope.row.houseId)"><i class="el-icon-edit"></i></el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.row.houseId)"><i class="el-icon-delete"></i></el-button>
          
      </template>
      </el-table-column>

        <el-dialog
        title="修改房屋信息"
        :visible.sync="dialogVisible"
        width="60%"
        :before-close="handleClose"
        :append-to-body="true"

        >
     
        <div>
             <el-form ref="form" :model="updateOrderForm" label-width="80px">
        <div class="title">
            基础信息
        </div>
        <el-form-item label="发布标题">
            <el-input style="width:60%" v-model="updateOrderForm.houseTitle">

            </el-input>
        </el-form-item>
        <el-form-item label="小区名称">
           <el-autocomplete
            class="inline-input"
            v-model="updateOrderForm.houseAddress"
            :fetch-suggestions="querySearchAsync"
            placeholder="请输入内容"
            :trigger-on-focus="false"
            @select="handleSelectUpdate"
            style="width:60%"
            >
            </el-autocomplete>
        </el-form-item>
        <el-form-item label="房屋户型">
            <el-input style="width:110px;" v-model="a1" ><span slot="suffix">室</span></el-input>
            <el-input style="width:110px;margin-left:20px" v-model="b1"><span slot="suffix">厅</span></el-input>
            <el-input style="width:110px;margin-left:20px" v-model="c1"><span slot="suffix">卫</span></el-input>
            <el-input style="width:110px;margin-left:20px" v-model="updateOrderForm.houseArea" ><span slot="prefix">共</span><span slot="suffix">㎡</span></el-input>
        </el-form-item>
        <el-form-item label="房屋朝向">
             
             <el-select v-model="updateOrderForm.houseOrientation" placeholder="请选择朝向">
                <el-option
                v-for="(item,index) in orientationParams"
                :key="index"
                :label="item"
                :value="item">
                </el-option>
             </el-select>
        </el-form-item>
        <div class="title">
            租金信息
        </div>
        <el-form-item label="月租金">
             <el-input style="width:150px;" v-model="updateOrderForm.houseCost"><span slot="suffix">元/月</span></el-input>
            
             <el-select v-model="updateOrderForm.housePayment" placeholder="请选择付款方式">
                <el-option
                v-for="(item,index) in payParams"
                :key="index"
                :label="item"
                :value="item">
                </el-option>
            </el-select>

        </el-form-item>
        <el-form-item label="租金包括">
            <el-checkbox-group v-model="updateOrderForm.include">
                <el-checkbox style="margin-left:0px" v-for="(item,index) in includeParams" :key="index" :label="item">
                 {{item}}
             </el-checkbox>
            </el-checkbox-group>
             

        </el-form-item>
        
        <div class="title">
            详细介绍
        </div>

        <el-form-item label="最早入住"> 
            <el-date-picker
            v-model="updateOrderForm.houseEarlyTime"
            align="right"
            type="date"
            placeholder="选择日期"
            >
            </el-date-picker>
            <el-input placeholder="宜住"  v-model="updateOrderForm.houseNum" style="margin-left:10px;width:120px">
                <span slot="suffix">人</span>
            </el-input>
        </el-form-item>
        <el-form-item label="出租类型">
            <el-radio-group v-model="updateOrderForm.houseLeaseType">
                 <el-radio label="整套出租">整套出租</el-radio>
                <el-radio label="单间出租">单间出租</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item label="装修情况"> 
            <el-radio-group v-model="updateOrderForm.houseDecoration">
                <el-radio label="毛坯">毛坯</el-radio>
                <el-radio label="简单装修">简单装修</el-radio>
                <el-radio label="精装修">精装修</el-radio>
                <el-radio label="豪华装修">豪华装修</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item label="房屋配置">
            <el-checkbox-group v-model="updateOrderForm.furnitureInCn">
                <el-checkbox v-for="(item,index) in furnitureParams"  :label="item" :key="index">
                 {{item}}
             </el-checkbox>
            </el-checkbox-group>
            
        </el-form-item>
        <el-form-item label="出租要求">
            <el-checkbox-group v-model="updateOrderForm.requireP">
            <el-checkbox v-for="(item,index) in requireParams" :key="index" :label="item"> 
                 {{item}}
             </el-checkbox>
             </el-checkbox-group>
        </el-form-item>
        <el-form-item label="房源描述">
            <el-input
            type="textarea"
            placeholder="可以介绍一下房源亮点，交通、周边环境，可以入住的时间和对租客的要求等"
            v-model="updateOrderForm.houseDesc"
            maxlength="300"
            show-word-limit
            >
            </el-input>
        </el-form-item>
        <div class="title">
            房源图片
        </div>
        <el-form-item>
            <el-upload
            class="upload-demo"
            ref="upload1"
            drag
            action="http://localhost:8081/file/upload"
            multiple
            :data="houseOrder"
            :auto-upload="false"
            >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
        </el-form-item>
        
        <el-form-item style="float:right;margin-top:-25px">
            <el-button type="success" @click="onSave">立即保存</el-button>
          
        </el-form-item>
        </el-form>
        </div>
        
        </el-dialog>


    </el-table>
    </div>
    <div v-show="!flag" style="margin-top:30px;">
        <el-form ref="form" :model="form" label-width="80px">
        <div class="title">
            基础信息
        </div>
        <el-form-item label="发布标题">
            <el-input style="width:514px" v-model="form.houseTitle">

            </el-input>
        </el-form-item>
        <el-form-item label="小区名称">
           <el-autocomplete
            class="inline-input"
            v-model="state1"
            :fetch-suggestions="querySearchAsync"
            placeholder="请输入内容"
            :trigger-on-focus="false"
            @select="handleSelect"
            style="width:514px"
            >
            </el-autocomplete>
        </el-form-item>
        <el-form-item label="房屋户型">
            <el-input style="width:110px;" v-model="a" ><span slot="suffix">室</span></el-input>
            <el-input style="width:110px;margin-left:20px" v-model="b"><span slot="suffix">厅</span></el-input>
            <el-input style="width:110px;margin-left:20px" v-model="c"><span slot="suffix">卫</span></el-input>
            <el-input style="width:110px;margin-left:20px" v-model="form.houseArea" ><span slot="prefix">共</span><span slot="suffix">㎡</span></el-input>
        </el-form-item>
        <el-form-item label="房屋朝向">
             
             <el-select v-model="form.houseOrientation" placeholder="请选择朝向">
                <el-option
                v-for="(item,index) in orientationParams"
                :key="index"
                :label="item"
                :value="item">
                </el-option>
             </el-select>
        </el-form-item>
        <div class="title">
            租金信息
        </div>
        <el-form-item label="月租金">
             <el-input style="width:150px;" v-model="form.houseCost"><span slot="suffix">元/月</span></el-input>
            
             <el-select v-model="form.housePayment" placeholder="请选择付款方式">
                <el-option
                v-for="(item,index) in payParams"
                :key="index"
                :label="item"
                :value="item">
                </el-option>
            </el-select>

        </el-form-item>
        <el-form-item label="租金包括">
            <el-checkbox-group v-model="form.include">
                <el-checkbox style="margin-left:0px" v-for="(item,index) in includeParams" :key="index" :label="item">
                 {{item}}
             </el-checkbox>
            </el-checkbox-group>
             

        </el-form-item>
        
        <div class="title">
            详细介绍
        </div>

        <el-form-item label="最早入住"> 
            <el-date-picker
            v-model="form.houseEarlyTime"
            align="right"
            type="date"
            placeholder="选择日期"
            >
            </el-date-picker>
            <el-input placeholder="宜住"  v-model="form.houseNum" style="margin-left:10px;width:120px">
                <span slot="suffix">人</span>
            </el-input>
        </el-form-item>
        <el-form-item label="出租类型">
            <el-radio-group v-model="form.houseLeaseType">
                 <el-radio label="整套出租">整套出租</el-radio>
                <el-radio label="单间出租">单间出租</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item label="装修情况"> 
            <el-radio-group v-model="form.houseDecoration">
                <el-radio label="毛坯">毛坯</el-radio>
                <el-radio label="简单装修">简单装修</el-radio>
                <el-radio label="精装修">精装修</el-radio>
                <el-radio label="豪华装修">豪华装修</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item label="房屋配置">
            <el-checkbox-group v-model="form.furniture">
                <el-checkbox v-for="(item,index) in furnitureParams"  :label="item" :key="index">
                 {{item}}
             </el-checkbox>
            </el-checkbox-group>
            
        </el-form-item>
        <el-form-item label="出租要求">
            <el-checkbox-group v-model="form.requireP">
            <el-checkbox v-for="(item,index) in requireParams" :key="index" :label="item"> 
                 {{item}}
             </el-checkbox>
             </el-checkbox-group>
        </el-form-item>
        <el-form-item label="房源描述">
            <el-input
            type="textarea"
            placeholder="可以介绍一下房源亮点，交通、周边环境，可以入住的时间和对租客的要求等"
            v-model="form.houseDesc"
            maxlength="300"
            show-word-limit
            >
            </el-input>
        </el-form-item>
        <div class="title">
            房源图片
        </div>
        <el-form-item>
            <el-upload
            class="upload-demo"
            ref="upload"
            drag
            action="http://localhost:8081/file/upload"
            multiple
            :data="houseOrder"
            :auto-upload="false"
            >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
        </el-form-item>
        
        <el-form-item style="float:right">
            <el-button type="success" @click="onSubmit">立即发布</el-button>
          
        </el-form-item>
        </el-form>
    </div>
    </div>
</template>
<script>
export default {
    name: 'myOrderList',
    data() {
        return {
          tableData: [],
          flag:true,
          form:{
              'houseCost':'',
              'houseTitle':'',
              'houseDesc':'',
              'furniture':[],
              'houseLeaseType':'',
              'houseDecoration':'',
            //   'houseReleaseTime':'',
              'houseArea':'',
              'houseOrientation':'',
              'housePayment':'',
              'houseEarlyTime':'',
              'houseAddress':'',
              'houseNum':'',
              'houseType':'',
              'requireP':[],
              'include':[],
              
              'houseUserName':''
          },
          decoration:{},
          orientationParams:['朝东','朝南','朝西','朝北','朝南北','朝东西','朝东南','朝西南','朝东北','朝西北'],
          payParams:['付1押1','付3押3','半年付','一年付'],
          includeParams:['水费','电费','燃气费','宽带费','物业费','取暖费','有线电视费','停车费'],
          
          furnitureParams:['床','衣柜','沙发','宽带','油烟机','洗衣机','冰箱','暖气','阳台','可做饭','燃气灶','智能门锁','空调','厕所','电视','热水器'],
          furniture:[],
          requireParams:['只限女生','一家人','禁止养宠物','半年起租','一年起租','租户稳定','作息正常','禁烟'],
          
          state1:'',
          
          timeout:  null,
          a:'',
          b:'',
          c:'',
          a1:'',
          b1:'',
          c1:'',

          houseOrder:{
              houseOrderId:''
          },
          dialogVisible:false,

          updateOrderForm:{
              'houseCost':'',
              'houseTitle':'',
              'houseDesc':'',
              'furniture':[],
              'houseLeaseType':'',
              'houseDecoration':'',
            //   'houseReleaseTime':'',
              'houseArea':'',
              'houseOrientation':'',
              'housePayment':'',
              'houseEarlyTime':'',
              'houseAddress':'',
              'houseNum':'',
              'houseType':'',
              'requireP':[],
              'include':[],
              
              'houseUserName':''
          },

        
        }
    },
    methods:{
        initData(){
            let name = sessionStorage.getItem('username');
            // let name = 'xiao';
            if(name!=null){

                this.axios.get(
                'http://localhost:8081/house/getMyOrder',
                {
                    params:{
                        userName:name
                    }
                }
                )
                .then((res)=>{
                    if(res.data.code==200){
                        this.tableData = res.data.data;
                    }
                })
                .catch((error)=>{
                    console('连接出错');
                })

            }
            
        },
        // handleEdit(i,m){
        //     console.log(i);
        //     console.log(m);
        // },
        toOrderDesc(id){
            this.$router.push({
              
                 path: '/houseorder',
                 query:{
                        orderId: id
                 }
                       
                   
            })
        },
        handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
              this.dialogVisible=false;
            // done();
          })
          .catch(_ => {});
      },
        handleDelete(id){
            
            this.$confirm('此操作将删除该发布, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            }).then(() => {

                this.axios.delete(
                    'http://localhost:8081/house/delete/'+id
                )
                .then((res)=>{
                    if(res.data.code==200){
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        this.initData();
                    }
                }).catch((error)=>{
                   this.$message({
                       type:'info',
                       message:'删除失败!'
                   })
                })
            
            }).catch(() => {
            this.$message({
                type: 'info',
                message: '已取消删除'
            });          
            });
        },
        changeMenu1(){
            this.flag = true;
        },changeMenu2(){
            this.flag = false;
        },
        querySearchAsync(queryString, cb) {
            var list = [];

            let url = '/localapi/place/v1/suggestion/?region=' + sessionStorage.getItem('city')+'&keyword='+queryString+'&key=W4JBZ-GM2CG-WL5QI-IXRPZ-II4E7-NDFDH';
            this.axios.get(url)
            .then((res)=>{
                for(let i of res.data.data){
                    i.value = i.title +'(' +i.address+')';
                }
                list = res.data.data
            })
            .catch((error)=>{
                console.log(error.data);
            })

            clearTimeout(this.timeout);
            this.timeout = setTimeout(() => {
            cb(list);
            }, 1000 * Math.random());
        },
        createStateFilter(queryString) {
            return (state1) => {
            return (state1.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
            };
        },
        handleSelect(item){
            this.form.houseAddress = item.value;
        },
        handleSelectUpdate(item){

            this.updateOrderForm.houseAddress = item.value;
        },
        getUpdatOrder(id){

            this.dialogVisible =true;
            this.axios.get('http://localhost:8081/house/get/'+id)
            .then((res)=>{
                this.updateOrderForm = res.data;
                this.a1 = this.updateOrderForm.houseType.charAt(this.updateOrderForm.houseType.indexOf("室")-1);
                this.b1 = this.updateOrderForm.houseType.charAt(this.updateOrderForm.houseType.indexOf("厅")-1);
                this.c1 = this.updateOrderForm.houseType.charAt(this.updateOrderForm.houseType.indexOf("卫")-1);
                
            }).catch((error)=>{
                console.error(error)
            })
        }
        ,
        onSubmit(){
            this.form.houseType = this.a + '室' +this.b+'厅'+this.c+'卫';
            this.form.houseUserName = sessionStorage.getItem('username');
            
            // let formData = new FormData();
            // for(var key in this.form){
            //     formData.append(key,this.form[key]);
            // }
            // console.log(formData.houseTitle);
            this.axios.post(
              'http://localhost:8081/house/create',
                // headers:{
                //     'Content-Type':'multipart/form-data',
                //     'wo':'test'
                // },
                this.form
            )
            .then((res)=>{
                if(res.data.code==200){
                    this.houseOrder.houseOrderId = res.data.data;
                    this.$refs.upload.submit();
                    this.$message({
                        type:'success',
                        message: '创建成功'
                    })
                   location.reload();
                }else{
                    this.$message({
                        type:'error',
                        message: '创建失败'
                    })
                }
            })
            .catch((error)=>{
                this.$message({
                        type:'error',
                        message: '创建失败'
                    })
            })


        },
        onSave(){

            this.updateOrderForm.houseType = this.a1 + '室' +this.b1+'厅'+this.c1+'卫';
            this.updateOrderForm.houseUserName = sessionStorage.getItem('username');
            this.axios.put("http://localhost:8081/house/update",this.updateOrderForm)
            .then((res)=>{
                if(res.data.code ==200){
                this.$message({
                    type:'success',
                    message:res.data.msg
                })
                }else{
                    this.$message({
                        type:'error',
                        message:res.data.msg
                    })
                }
            }).catch((error)=>{
                concole.log(error)
            })
        }
       
        

    },
    created(){
        this.initData();
    }
}
</script>
<style  scoped>
.title{
    margin-bottom: 20px;
    margin-left: 5px;
    font-size: 20px;
}
.main{
    
}

</style>


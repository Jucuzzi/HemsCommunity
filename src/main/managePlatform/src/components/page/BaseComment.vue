<template>
	 <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 评论1</el-breadcrumb-item>
                <el-breadcrumb-item>评论列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="handle-box">
            <el-button class="handle-del mr10" @click="deleteForMore()">批量删除</el-button>
            <!--<el-select v-model="select_cate" placeholder="举报类型" class="handle-select mr10">
                <el-option key="1" label="煽动" value="1"></el-option>
                <el-option key="2" label="辱骂" value="2"></el-option>
                <el-option key="3" label="色情" value="3"></el-option>
            </el-select>-->
            <el-input v-model="select_word" placeholder="筛选作者名称" class="handle-input mr10"></el-input>
            <el-button type="primary" icon="search"   @click="searchMore()">搜索</el-button>
        </div>
        <el-table :data="tableData" border style="width: 100%" ref="multipleTable" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            
            <el-table-column prop="id" label="编号" width="80">
            </el-table-column>
            <el-table-column prop="userName" label="作者" sortable width="120">
            </el-table-column>
            <el-table-column prop="commentDetail" label="评论内容" width="220">
            </el-table-column>
            <el-table-column prop="reportCount" label="举报次数" width="80">
            </el-table-column>
            <el-table-column prop="reportName" label="举报内容" width="150">
            </el-table-column>
            <el-table-column prop="fabulousCount" label="点赞次数" :formatter="formatter">
            </el-table-column>
            <el-table-column label="操作" width="180">
                <template scope="scope">
                    <!--<el-button size="small"
                            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>-->
                    <el-button size="small" type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination">
            <el-pagination
                    @current-change ="handleCurrentChange"
                    layout="prev, pager, next"
                    :pageSize=20
                    :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
	 export default {
	 data() {
            return {
                url: '../../../static/vuetable.json',
                tableData: [],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                total:0,
                select_word: '',
                idsArr:[],
                 infocontent:{
                	
                },
            }
        },
        created(){
            this.getData();
        },
   methods: { 
   	handleCurrentChange(val){
                this.cur_page = val;
                this.getData();
            },
	getData(){
                let self = this;
				ajaxRequest({
					url : "prvlg/getCommentList.do",
					data : {
						"pageNumber":self.cur_page,
						"condition":self.select_word
					},
					async : true,
					success : function(dataJson) {
						
						var showArr = new Array();
						var allArr = dataJson.root;
						var index = 0;
						var min = ((self.cur_page-1) * 20);
						var maxNUm=0;
						if ((min + 20)>allArr.length) {
							maxNUm=allArr.length;
						} else {
							maxNUm=(min + 20);
						}
						for (var showNumMin = min;showNumMin < maxNUm;showNumMin++){
							showArr[index]=allArr[showNumMin];
							index++;
						}
					
						self.tableData =  showArr;
						self.total = parseInt(dataJson.total);
					},
					error : function() {
						alert("系统繁忙");
					}
				});

           },
           
           
            handleDelete(index, row) {    
                let self = this;
                var canDelect = 0;
                var idsArrShow = self.idsArr.split(",");
                for (var num=0;num<idsArrShow.length;num++){
                		if (idsArrShow[num] == self.tableData[index].id) {
                			canDelect = 1;
                			break;
                		}
                }
                if (canDelect > 0) {
                	ajaxRequest({
					url : "prvlg/delcomment.do",
					data : {
						"commentIds":self.tableData[index].id
					},
					async : true,
					success : function(dataJson) {
						if (dataJson.result =="successed") {
							self.getData();
							self.$message.success("操作成功");
							
						}
					},
					error : function() {
						alert("系统繁忙");
					}
				});
              }  
            },
            
              handleSelectionChange: function(val) {
                this.multipleSelection = val;
                 this.idsArr = this.multipleSelection.map(item => item.id).join()
            },
            deleteForMore(){
            		if (this.idsArr.length < 1) {
            			alert("请先勾选项目");
            		} else{
            			 let self = this;
            			ajaxRequest({
						url : "prvlg/delcomment.do",
						data : {
							"commentIds":self.idsArr
						},
						async : true,
						success : function(dataJson) {
							if (dataJson.result =="successed") {
								self.getData();
								self.$message.success("操作成功");
							
							}
						},
						error : function() {
							alert("系统繁忙");
						}
					});
            		}
            },
            searchMore(){
           		this.getData();
            }
              
	}	            
 }
</script>

<style>
	.handle-box{
    margin-bottom: 20px;
}
.handle-del{
    border-color: #bfcbd9;
    color: #999;
}
.handle-select{
    width: 120px;
}
.handle-input{
    width: 300px;
    display: inline-block;
}
</style>
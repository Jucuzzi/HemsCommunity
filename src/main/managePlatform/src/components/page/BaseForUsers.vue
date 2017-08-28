<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 用户1</el-breadcrumb-item>
                <el-breadcrumb-item>用户列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="handle-box">
            <el-button class="handle-del mr10" @click="deleteForMore()">批量删除</el-button>
            <!--<el-select v-model="select_cate" placeholder="筛选角色" class="handle-select mr10">
                <el-option key="1" label="管理员" value="1"></el-option>
                <el-option key="2" label="普通用户" value="2"></el-option>
                <el-option key="3" label="一级黑名单用户" value="3"></el-option>
            </el-select>-->
            <el-input v-model="select_word" placeholder="筛选昵称" class="handle-input mr10"></el-input>
            <el-button type="primary" icon="search"  @click="searchMore()">搜索</el-button>
        </div>
        <el-table :data="tableData" border style="width: 100%" ref="multipleTable" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            
            <el-table-column prop="id" label="编号" width="120">
            </el-table-column>
            <el-table-column prop="name" label="姓名" sortable width="150">
            </el-table-column>
            <el-table-column prop="nickName" label="昵称" width="120">
            </el-table-column>
            <el-table-column prop="userType" label="角色" :formatter="formatter">
            </el-table-column>
            <el-table-column label="操作" width="180">
                <template scope="scope">
                    <el-button size="small"
                            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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
//              if(process.env.NODE_ENV === 'development'){
//                  self.url = '/ms/table/list';
//              };
//              self.$axios.post(self.url, {page:self.cur_page}).then((res) => {
//                  self.tableData = res.data.list;
//              })
				ajaxRequest({
					url : "prvlg/userlist.do",
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
//						self.tableData = dataJson.root;
//						for (var i=0;i<dataJson.root.length;i++){
//							dataJson.root[i].releaseTime = self.timeShowFormat(dataJson.root[i].releaseTime);
//						}
						self.total = parseInt(dataJson.total);
					},
					error : function() {
						alert("系统繁忙");
					}
				});

            },
            formatter(row, column) {
                return row.address;
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleEdit(index, row) {
                this.$message('编辑第'+(index+1)+'行');
                let self = this;
                alert(self.tableData[index].id);
		        self.infocontent["id"] = self.tableData[index].id;
		        self.infocontent["name"] = self.tableData[index].name;
		        self.infocontent["nickName"] = self.tableData[index].nickName;
		        self.infocontent["userType"] = "管理员";
		        this.$router.push({path:'/addNewOrEditUser',query:self.infocontent});
                
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
					url : "prvlg/delusers.do",
					data : {
						"userIds":self.tableData[index].id
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
						url : "prvlg/delusers.do",
						data : {
							"userIds":self.idsArr
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

<style scoped>
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
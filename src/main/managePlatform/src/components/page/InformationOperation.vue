<template>
    <div class="table">
        <div class="handle-box">
            <!--<el-button class="handle-del mr10">批量删除</el-button>-->
            <el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>
            <el-button @click="getData" type="primary" icon="search">搜索</el-button>
        </div>
        <el-table :data="tableData" border style="width: 100%" ref="multipleTable" @selection-change="handleSelectionChange">
            <!--<el-table-column type="selection" width="55"></el-table-column>-->
            <!--<el-table-column prop="date" label="时间" sortable width="150">-->
            <el-table-column prop="releaseTime" v-text="'saasd'" label="发布时间" sortable width="180">
            </el-table-column>
            <el-table-column prop="author" label="用户" width="120">
            </el-table-column>
            <el-table-column prop="title" label="资讯标题" :formatter="formatter">
            </el-table-column>
            <el-table-column label="操作" width="160">
                <template scope="scope">
                    <el-button size="small" :infocontent="infocontent"
                            @click="handleEdit(scope.$index, scope.row)">查看</el-button>

                   	<el-button v-if="scope.row.isDelete == '0'" size="small" type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                	<el-button v-else size="small" type="primary"
                        @click="handleDelete(scope.$index, scope.row)">恢复</el-button>
             
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination">
            <el-pagination
                    @current-change ="handleCurrentChange"
                    layout="prev, pager, next"
                    :total="total">
            </el-pagination>
        </div>
        <check-dialog ref="checkdialog" :infocontent="infocontent"></check-dialog>
    </div>
</template>

<script>
import checkDialog from './information/checkDialog.vue'
    export default {
        data() {
            return {
                url: '../../../static/vuetable.json',
                tableData: [],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                total:0,
                infocontent:{
                	
                },
            }
        },
        components:{
			checkDialog,
		},
        created(){
           
        },
        mounted(){
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
//                  self.total = 3;
//                  self.tableData = [
//	                    				{"informationId":"001","releaseTime":"20170802","nickName":"aaa","title":"111","isDelete":"1"},
//	              						{"informationId":"002","releaseTime":"20170803","nickName":"vsv","title":"dwsddf","isDelete":"0"},
//	         							{"informationId":"003","releaseTime":"20170804","nickName":"sgfds","title":"dgdsg","isDelete":"0"}
//	         
//                  				 ]
//              })
				ajaxRequest({
					url : "queryinformation",
					data : {
						"pageNumber":self.cur_page,
						"condition":self.select_word
					},
					async : true,
					success : function(dataJson) {
						self.tableData = dataJson.infoList;
						for (var i=0;i<dataJson.infoList.length;i++){
							dataJson.infoList[i].releaseTime = self.timeShowFormat(dataJson.infoList[i].releaseTime);
						}
						self.total = parseInt(dataJson.total);
					},
					error : function() {
						alert("系统繁忙");
					}
				});
            },
            timeShowFormat(time){
            	return  time.substring(0,4)+"-"+
					    time.substring(4,6)+"-"+
						time.substring(6,8)+" "+
						time.substring(8,10)+":"+
						time.substring(10,12)+":"+
						time.substring(12,14);
            },
            formatter(row, column) {
                return row.title;
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            insetInfo() {
            	
            },
            handleEdit(index, row) {
            	var self = this;
				ajaxRequest({
					url : "queryinformationById",
					data : {
						"informationId":row.informationId,
					},
					async : true,
					success : function(dataJson) {		
					
						self.infocontent["title"] = dataJson.title;
		            	self.infocontent["releaseTime"] = dataJson.releaseTime; 
		            	self.infocontent["userId"] = dataJson.userId;
		            	self.infocontent["author"] = dataJson.author;
		            	self.infocontent["source"] = dataJson.source;
		            	self.infocontent["detailContent"] = dataJson.detailContent;
		            	self.infocontent["isDelete"] = dataJson.isDelete
						self.$refs.checkdialog.showDialog();
					},
					error : function() {
						alert("系统繁忙");
					}
				});
                
            },
            handleDelete(index, row) {
               var self = this;
				ajaxRequest({
					url : "updateInformationIsDelete",
					data : {
						"informationId":row.informationId,
						"isDelete":row.isDelete == "1" ? "0" : "1",
					},
					async : true,
					success : function(dataJson) {		
						if (dataJson.result == "successed") {
							self.$message.success("操作成功");
							for (var i=0; i < self.tableData.length; i++) {
								if (self.tableData[i].informationId == dataJson.informationId) {
									self.tableData[i].isDelete = (self.tableData[i].isDelete == "1" ? "0" : "1");
									break;
								}
							}						
						} else {
							this.$message.error('操作失败');
						}
					},
					error : function() {
						alert("系统繁忙");
					}
				});
            },
            isShowDelete(index, row) {
            	if (row.name == 'aaa') {
            		return true;
            	} else {
            		return false;
            	}
            },
            handleSelectionChange: function(val) {
                this.multipleSelection = val;
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
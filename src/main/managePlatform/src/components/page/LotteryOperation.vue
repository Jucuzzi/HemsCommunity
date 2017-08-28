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

            <el-table-column prop="prizeName" label="奖品名称" width="120">
            </el-table-column>
            <el-table-column prop="exchangeNUmber" label="兑换码">
            </el-table-column>
			<el-table-column prop="exchangeIsExchange" label="兑换状态" width="120" :formatter="isExchangeFormatter">
            </el-table-column>
            <el-table-column prop="exchangeDate" v-text="" label="兑换时间" sortable width="180" :formatter="timeShowFormat">
            </el-table-column>

            <el-table-column label="操作" width="80">
                <template scope="scope">
                    <el-button size="small" :infocontent="infocontent" type="primary"
                            @click="handleEdit(scope.$index, scope.row)" v-show="scope.row.exchangeIsExchange!=1">兑换</el-button>            
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
        <check-prize-code-dialog ref="checkprizecodedialog" :prizecode="prizecode" :selectExchangeInfo="selectExchangeInfo"></check-prize-code-dialog>
    </div>
</template>

<script>
import checkPrizeCodeDialog from './Lottery/checkPrizeCodeDialog.vue'
    export default {
        data() {
            return {
                url: '../../../static/vuetable.json',
                tableData: [
//              			{"prizeName":"95折券","prizeCode":"adsandsandasdnudscnoasic","isExchange":"0","exchangeTime":"20170801082431"},
//              			{"prizeName":"85折券","prizeCode":"aadasdererefwefgtefwefff","isExchange":"1","exchangeTime":""}
//              			
                			],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                total:0,
                prizecode:"",
                selectExchangeInfo:{}
            }
        },
        components:{
			checkPrizeCodeDialog,
		},
        created(){
          	this.getData(); 
        },
        mounted(){
        },
        methods: {
            handleCurrentChange(val){
                this.cur_page = val;
                this.getData();
            },
            getData(){
                let self = this;
				ajaxRequest({
					url : "prvlg/getexchangeList.do",
					data : {
					},
					async : true,
					success : function(dataJson) {
						self.tableData = dataJson.root;
					},
					error : function() {
						alert("系统繁忙");
					}
				});
            },
            timeShowFormat(row, column){
            	if (row.exchangeDate != "") {
            		return  row.exchangeDate.substring(0,4)+"-"+
					    row.exchangeDate.substring(4,6)+"-"+
						row.exchangeDate.substring(6,8)+" "+
						row.exchangeDate.substring(8,10)+":"+
						row.exchangeDate.substring(10,12)+":"+
						row.exchangeDate.substring(12,14);
            	} else {
            		return "";
            	}
            
            },
            formatter(row, column) {
                return row.prizeCode;
            },
            isExchangeFormatter(row, column) {
                return row.exchangeIsExchange == "1" ? "已兑换":"未兑换";
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            insetInfo() {
            	
            },
            handleEdit(index, row) {
            	var self = this;
            	self.prizecode = row.exchangeNUmber;
            	self.selectExchangeInfo = row;
           		this.$refs.checkprizecodedialog.showDialog();
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
<template>
    <div class="table">
        <el-table :data="tableData" border style="width: 100%" ref="multipleTable" @selection-change="handleSelectionChange">
            <!--<el-table-column type="selection" width="55"></el-table-column>-->
            <!--<el-table-column prop="date" label="时间" sortable width="150">-->
            <el-table-column prop="prizeId" label="序号" width="50">
            </el-table-column>
            <el-table-column prop="prizeName" label="奖品名称" width="120">
            </el-table-column>
            <el-table-column prop="prizeAllCount" label="奖品总数量" >
            </el-table-column>
            <el-table-column prop="prizeBalanceCount" label="剩余奖品数量" >
            </el-table-column>
            <el-table-column label="操作" width="110">
                <template scope="scope">
                    <el-button size="small" :infocontent="infocontent" type="primary"
                            @click="handleEdit(scope.$index, scope.row)">增加奖品</el-button>            
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
        <add-prize-dialog ref="addprizedialog" :BalanceCount="BalanceCount" :selectPrizeInfo="selectPrizeInfo"></add-prize-dialog>
    </div>
</template>

<script>
import addPrizeDialog from './Lottery/addPrizeDialog.vue'
    export default {
        data() {
            return {
                url: '../../../static/vuetable.json',
                tableData: [
                			],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                total:0,
                BalanceCount:"",
                selectPrizeInfo:{},
            }
        },
        components:{
			addPrizeDialog,
		},
        created(){
         	this.getData(); 
        },
        mounted(){
        },
        methods: {
        		 getData(){
                let self = this;
				ajaxRequest({
					url : "prvlg/getPrizeList.do",
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
        	
            handleCurrentChange(val){
                this.cur_page = val;

            },
            timeShowFormat(row, column){
            	return  row.exchangeTime.substring(0,4)+"-"+
					    row.exchangeTime.substring(4,6)+"-"+
						row.exchangeTime.substring(6,8)+" "+
						row.exchangeTime.substring(8,10)+":"+
						row.exchangeTime.substring(10,12)+":"+
						row.exchangeTime.substring(12,14);
            },
            filterTag(value, row) {
                return row.tag === value;
            },

            handleEdit(index, row) {
            	this.BalanceCount = row.prizeBalanceCount;
            	this.selectPrizeInfo = row;
           		this.$refs.addprizedialog.showDialog();
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
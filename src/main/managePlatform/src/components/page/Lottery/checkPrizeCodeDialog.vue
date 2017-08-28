<template>
	<div v-show="show" class="check-prizecode-dialog">
		<div class="prizecode-outer-div">
			<div class="check-prizecode-div">
				<span class="prizecode">当前兑换码</span>
				<span class="prizecode" v-text="prizecode"></span>
				<el-input v-model="input_prizecode" placeholder="请输入兑换码" class="handle-input mr10 prizecode-input"></el-input>
			</div>
			<div class="check-prizecode-btn-div">
				<el-button size="big" type=""
                        @click="cancel()">取消</el-button>            
			    <el-button size="big" type="primary"
                        @click="confirm()">确认提交</el-button>            
	
			</div>
		</div>
	</div>
</template>

<script>


export default {
	props:['prizecode','selectExchangeInfo'],
	data(){
		return{
			show:false,
			input_prizecode:"",
		}
	},
	computed: {
		check(){
			return this.input_prizecode == this.prizecode ? true:false;
		}
	},
	updated(){
	},
	methods: {
		showDialog: function(){
			this.show = !this.show;
		},
		confirm(){
			if(this.check){
//				alert(this.selectExchangeInfo.prizeName);
				this.selectExchangeInfo.exchangeIsExchange = "1";
				
				//此处需要获取当前的日期，明日完善
				var date = new Date();
    				var seperator1 = "";
    				var seperator2 = "";
    				var month = date.getMonth() + 1;
    				var strDate = date.getDate();
    				var hour = date.getHours();
    				var minute = date.getMinutes();
    				var second = date.getSeconds();
   			 	if (month >= 1 && month <= 9) {
        				month = "0" + month;
    				}
    				if (strDate >= 0 && strDate <= 9) {
        				strDate = "0" + strDate;
    				}
    				if (hour >= 0 && hour <= 9) {
        				hour = "0" + hour;
    				}
    				if (minute >= 0 && minute <= 9) {
        				minute = "0" + minute;
    				}
    				if (second >= 0 && second <= 9) {
        				second = "0" + second;
    				}
    				var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            				+ "" + hour + seperator2 + minute
            				+ seperator2 + second;
            		this.selectExchangeInfo.exchangeDate = currentdate;
				
				 let self = this;
				ajaxRequest({
					url : "prvlg/updateexchange.do",
					data : self.selectExchangeInfo,
					async : true,
					success : function(dataJson) {
//						self.$message.success("操作成功");
						
							ajaxRequest({
								url : "prvlg/getprieinfobyid.do",
								data : {
									"prizeId":self.selectExchangeInfo.prizeId
								},
								async : true,
								success : function(dataJson) {
									var currentInfo = dataJson;
									currentInfo.prizeBalanceCount = (parseInt(currentInfo.prizeBalanceCount) - 1).toString();
									
									ajaxRequest({
										url : "prvlg/updatePrize.do",
										data : currentInfo,
										async : true,
										success : function(dataJson) {
											self.$message.success("操作成功");
											self.show = false;
											self.input_prizecode = "";
										},
										error : function() {
											alert("系统繁忙");
										}
									});
								},
								error : function() {
									alert("系统繁忙");
								}
				});
					},
					error : function() {
						alert("系统繁忙");
					}
				});
			} else {
				this.$message.error('兑换码不正确！请检查后重新提交');
			}
		},
		cancel(){
			this.show = false;
		},
	}
}
</script>


<style scoped="scoped">
.check-prizecode-dialog{
	  position: fixed;
	  top: 0;
	  left: 0;
	  width: 100%;
	  height: 100%;
	  background-color: rgba(0, 0, 0, 0.5);
	  display: flex;
	  display: -webkit-flex;
	  justify-content: center;
	  -webkit-justify-content: center;
	  align-items: center;
	  -webkit-align-items: center;
	  z-index: 100;
	  
}
.prizecode-outer-div{
	width: 400px;
	height: 280px;
	background-color: white;
	border-radius: 6px;
}	
.check-prizecode-div{
	width: 100%;
	height: 70%;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}
.check-prizecode-btn-div{
	width: 100%;
	height: 30%;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: row;
	
}
.prizecode{
	font-size: 20px;
	margin-bottom: 10px;
}
.prizecode-input{
	width: 70%;
	margin-top: 30px;
}
</style>
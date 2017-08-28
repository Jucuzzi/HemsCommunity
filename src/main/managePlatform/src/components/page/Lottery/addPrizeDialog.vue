<template>
	<div v-show="show" class="check-prizecode-dialog">
		<div class="prizecode-outer-div">
			<div class="check-prizecode-div">
				<span class="prizecode">当前剩余数量</span>
				<span class="prizecode" v-text="BalanceCount"></span>
				<el-input :maxlength="4" v-model="input_prize" placeholder="增加数量(1~9999)" class="handle-input mr10 prizecode-input">
				</el-input >			
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
	props:['BalanceCount','selectPrizeInfo'],
//	props:['selectPrizeInfo'],
	data(){
		return{
			show:false,
			oldNum:0,
			input_prize:"",
		}
	},
	computed: {
	
	},
	updated(){
	},
	methods: {
		showDialog: function(){
			//this.BalanceCount = this.selectPrizeInfo.prizeBalanceCount;
			this.show = !this.show;
		},
		confirm(){
			let reg = /^[1-9]\d*$/;
			if (!(/(^[1-9]\d*$)/.test(this.input_prize)) || this.input_prize >
				9999){
				this.$message.error('输入内容不合法');
			} else {
				
				this.selectPrizeInfo.prizeBalanceCount = (parseInt(this.selectPrizeInfo.prizeBalanceCount) + parseInt(this.input_prize)).toString();
				this.selectPrizeInfo.prizeAllCount = (parseInt(this.selectPrizeInfo.prizeAllCount) + parseInt(this.input_prize)).toString();
				  let self = this;
				ajaxRequest({
					url : "prvlg/updatePrize.do",
					data : self.selectPrizeInfo,
					async : true,
					success : function(dataJson) {
						self.$message.success("操作成功");
						self.show = false;
						self.input_prize = "";
					},
					error : function() {
						alert("系统繁忙");
					}
				});
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
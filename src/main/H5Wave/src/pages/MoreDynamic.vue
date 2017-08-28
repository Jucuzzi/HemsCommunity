<template>
	<div>
		<div class="sign-titleBar">
			<div class="leftDiv" v-on:click="gotoSign">
				<img class="leftImg" src="../assets/images/title_arrow_left_white.png" />
			</div>
			<div class="centerDiv">
				<span class="titleName">更多动态</span>
			</div>
		</div>
		<ul class="dynamic-ul-style">
			<li class="dynamic-li-tittle">
				<span class="dynamic-tittlr-text">今日</span>
			</li>
			<li class="dynamic-sign-list-root" v-for="item in todayStealList">
				<img class="dynamic-user-icon" src="../assets/logo.png"/>
				<div class="dynamic-sign-list-item-center">
					<span class="dynamic-user-name">{{item.userName}}</span>
					<span class="dynamic-get-num">收取 {{item.stealEnergy}}g</span>
				</div>
				<div class="dynamic-sign-list-item-right-outer">
					<span class="dynamic-get-points">{{item.date}}</span>
				</div>
			</li>
			<li class="dynamic-li-tittle">
				<span class="dynamic-tittlr-text">昨日</span>
			</li>
			<li class="dynamic-sign-list-root" v-for="item in yesterdayStealList">
				<img class="dynamic-user-icon" src="../assets/logo.png"/>
				<div class="dynamic-sign-list-item-center">
					<span class="dynamic-user-name">{{item.userName}}</span>
					<span class="dynamic-get-num">收取 {{item.stealEnergy}}g</span>
				</div>
				<div class="dynamic-sign-list-item-right-outer">
					<span class="dynamic-get-points">{{item.date}}</span>
				</div>
			</li>
		</ul>
	</div>
</template>

<script>

	export default {
		data(){
			return {
				"todayStealList":[],
				"yesterdayStealList":[],
			}
		},
		methods:{
			gotoSign: function() {
				this.$router.push({
					path: '/MainView'
				})
			},
			getData() {
				var self = this;
				ajaxRequest({
					url: 'getStealedList',
					data: {
						"userId":"1419",
					},
					async: true,
					success: function(dataJson) {
//						alert(JSON.stringify(dataJson));
						self.todayStealList = dataJson.result.root[0].stealUser;
						self.yesterdayStealList = dataJson.root.root[1].stealUser;
						alert(JSON.stringify(dataJson));
					},
					error: function() {
						alert("系统繁忙");
					}
				});
			}
		},
		mounted(){
			this.getData();
		}
		
	}
	
</script>

<style scoped="yes">
	.header-background{
		height: 50px;
		width: 100%;
		background-color: #00abfd;
	}
	.dynamic-sign-list-root{
		width: 100%;
		height: 60px;
		display: -webkit-flex;
		display:flex; 
		flex-direction: row;
		align-items: flex-start;
		align-items: center;
		border-bottom: 1px solid #dedede;
	}

	.dynamic-user-icon{
		width: 40px;
		height: 40px;
		margin-left: 20px;
		margin-right: 20px;
	}
	
	.dynamic-sign-list-item-center{
		display: -webkit-flex;
		display:flex; 
		flex-direction: column;
		align-items: flex-start;
		justify-content: center;
		width: calc(100% - 220px);
	}
	.dynamic-user-name{
		width: 100%;
		font-size: 17px;
		overflow: hidden;
		text-overflow:ellipsis;
		white-space:nowrap;
		text-align: start;
	}
	.dynamic-get-num{
		width: 100%;
		font-size: 13px;
		color: #aba9a8;
		margin-top: 3px;
		overflow: hidden;
		text-overflow:ellipsis;
		white-space:nowrap;
		text-align: start;
	}
	.dynamic-sign-list-item-right-outer{
		width: 140px;
		display: -webkit-flex;
		display:flex; 
		flex-direction: row;
		align-items: center;
		justify-content: center;
	}
	.dynamic-sign-list-item-right{
		display: -webkit-flex;
		display:flex; 
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	.dynamic-get-points{
		padding-left: 4px;
		padding-right: 4px;
		display: -webkit-flex;
		display: flex;
		white-space:pre;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		font-size: 15px;
	}
	.dynamic-sign-time{
		margin-top: 4px;
		font-size: 13px;
		color: grey;
	}
	
		
	.dynamic-tittlr-text{
		padding-left: 20px;
		color: #000000;
		font-size: 14px;
	}
	
	.dynamic-li-tittle{
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		align-items: center;
		height: 30px;
		width: 100%;
		border-bottom: 1px solid #dedede;
/*		border-top: 1px solid #dedede;*/
	}
	
	.dynamic-ul-style{
		border: 0px;
		margin: 0px;
		margin-top: 44px;
		padding: 0px;
	}
	
	/*标题栏*/
	
	.sign-titleBar {
		height: 44px;
		width: 100%;
		position: fixed;
		top: 0;
		background: #1cbafb;
		display: flex;
		flex-direction: row;
		z-index: 1000;
	}
	
	.leftDiv {
		width: 30%;
		height: 100%;
		text-align: left;
	}
	
	.leftImg {
		height: 24px;
		width: 24px;
		margin-left: 13px;
		margin-top: 10px;
	}
	
	.centerDiv {
		width: 40%;
		height: 100%;
		text-align: center;
	}
	
	.titleName {
		height: 100%;
		line-height: 44px;
		color: white;
		font-size: 17px;
	}
	
	.rightDiv {
		width: 30%;
		height: 100%;
		text-align: right;
	}
	
</style>
<template>
	<div class="raiders-root">
		<div class="sign-titleBar">
			<div class="leftDiv" v-on:click="gotoMainView">
				<img class="leftImg" src="../assets/images/title_arrow_left_white.png" />
			</div>
			<div class="centerDiv">
				<span class="titleName"></span>
			</div>
		</div>
		<div class="raiders-outer-div">
			<!--<div class="raiders-title-bar">
				<div @click="onBack" class="raiders-title-leftDiv"> 
					<img class="raiders-title-leftImg" src="../assets/strategy/title_arrow_left_white.png"/>		
				</div>
			</div>-->
			<div class="raiders-up-outer">
				<img class="raiders-icon" src="../assets/strategy/strategy_icon.png">	
				<span class="valley-energy">谷电能量攻略</span>
			</div>
		</div>
		<div class="raiders-rules-text">
			<span class="activity-rules">活动规则</span>
			<span class="activity-rules-text">1、账号下设备每日的峰谷电量都会产生一定比例的谷电能量;</span>
			<span class="activity-rules-text">2、通过收取其他用户的能量也可获得部分谷电能量；</span>
			<span class="activity-rules-text">3、每个月会产生最终的能量排行榜，排行榜名次越高，获得的电量越多。</span>
		</div>
		<div class="raiders-rules-text">
			<span class="activity-rules">奖励规则</span>
			<span class="activity-rules-text">1、排行榜前三名：获得价值30RMB的电量；</span>
			<span class="activity-rules-text">2、排行榜前十名 ：获得价值20RMB的电量；</span>
			<span class="activity-rules-text">3、排行榜前一百名 ：获得价值10RMB的电量；</span>
			<span class="activity-rules-text">4、排行榜前一千名 ：获得价值5RMB的电量；</span>
			<span class="activity-rules-text">5、排行榜一千名之后 ：获得价值1RMB的电量；</span>
		
		</div>
	</div>
</template>

<script>
export default{
	methods: {
 		onBack:function () {
 			this.$router.go(-1);
 		},
 		gotoMainView: function() {
				this.$router.push({
					path: '/MainView'
				})
			},
 	}
}
</script>

<style>
	.raiders-root{
		display:flex; 
		flex-direction: column;
		align-items: center;
	}
	.raiders-outer-div{
		width: 100%;
		height: 60vw;
		display:flex; 
		flex-direction: column;
		align-items: center;
		background-color: #0081b5;
	}
	.raiders-title-bar{
		height: 40px;
		width: 100%;
		position:fixed;
        top:0;
        background-color: #0081b5;
		display: flex;
		flex-direction: row;
	}
	.raiders-title-leftDiv{
		width: 20%;
		height: 40px;
		text-align: left;
	}
	.raiders-title-leftImg{
		height: 24px;
		width: 24px;
		margin-left: 13px;
		margin-top: 8px;
	}
	.raiders-up-outer{
		margin-top: 40px;
		display:flex; 
		flex-direction: column;
		align-items: center;
		justify-content: center;
		padding-bottom: 8%;
		background-color: #0081b5;
	}

	.raiders-icon{
		width: 65%;
		height: 40vw;
	}
	.valley-energy{
		font-size: 31px;
		color: white;
		font-weight:600;
	}
	.raiders-rules-text{
		width: 100%;
		margin-top: 5%;
		background-color: white;
		display:flex; 
		flex-direction: column;
		align-items: flex-start;
		justify-content: flex-start;
	}
	.activity-rules{
		margin-left: 4%;
		margin-right: 4%;
		text-align: left;
		font-size: 18px;
		font-weight: 600;
	}
	.activity-rules-text{
		font-size: 14px;
		color: gray;
		margin-left: 6%;
		margin-right: 4%;
		text-align: left;
	}
	/*标题栏*/
	
	.sign-titleBar {
		height: 44px;
		width: 100%;
		position: fixed;
		top: 0;
		/*background: /*#1cbafb*/;
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
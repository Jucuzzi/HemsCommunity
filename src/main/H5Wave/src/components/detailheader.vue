<template>
	<div class="titleBar">
		<div class="leftDiv"> 
			<img class="leftImg" @click="goback" src="../telehems/img/common/title_arrow_left_white.png"/>		
		</div>
		<div class="centerDiv"> 
			<span class="titleName">资讯详情</span>
		</div>
		<div class="rightDiv">  
			<img class="rightImg" src="../telehems/img/monthreportdetail/share_button_n01.png"/>		
		</div>
	</div>
</template>

<script>
	export default {
		methods: {
			goback:function() {
				this.$router.go(-1);
			}
		}
		
	}
	
</script>

<style>
	.titleBar{
		height: 40px;
		width: 100%;
		background-color: #00abfd;
		display: flex;
		flex-direction: row;
		align-items:center;
	}
	.leftDiv{
		width: 20%;
		height: 100%;
		text-align: left;
	}
	.leftImg{
		height: 24px;
		width: 24px;
		margin-left: 13px;
		margin-top: 8px;
	}
	.centerDiv{
		width: 60%;
		height: 100%;
		text-align: center;
	}
	.titleName{
		height: 100%; 
        line-height: 43px;  
		color: white;
		font-size: 20px;
	}
	.rightDiv{
		width: 20%;
		height: 100%;
		text-align: right;
	}
	.rightImg{
		height: 22px;
		width: 26px;
		margin-right: 15px;
		margin-top: 9px;
	}
</style>
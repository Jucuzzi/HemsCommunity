<template>
	<div id="mainDiv">
		<img src="" id="cityLevelImage" />
		
		<div id="progressBar" >
			<div class="leftDiv" v-on:click="gotoMainView">
				<img class="leftImg" src="../assets/images/title_arrow_left_white.png" />
			</div>
			<div id="progressText"></div>
			<div id="progress">
				<div id="topParallelogram"></div>
				<div id="parallelogram"></div>

			</div>
			<div id="cityCountImage">
				<img src="" id="factoryCount" />
			</div>
		</div>
		<div id="toptenz">
			<img src="../assets/images/Toptenz.png" v-on:click="click" style="width: 40%;margin-right: 15px;" />
		</div>
	</div>

</template>

<script>
	export default {
		name: 'hello3',
		data() {
			return {
				total: 100000,
				nowNum: 1000,
				level: 1,
				factoryCount: 1,
			}
		},
		methods: {
			click: function() {
				this.$router.push({
					path: '/TotalRankList'
				})
			},
			gotoMainView: function() {
				this.$router.push({
					path: '/mainView'
				})
			},
			getData: function() {
				var self = this;
				ajaxRequest({
					url: 'getCityState',
					data: {
					},
					async: true,
					success: function(dataJson) {
						//						{"result":{"closedPowerStations":"0","level":"1","oneLevelMaxEnergy":"1580000","nowEnergy":"0"}}
						self.total = Number(dataJson.result.oneLevelMaxEnergy);
						self.level = Number(dataJson.result.level);
						self.nowNum = Number(dataJson.result.nowEnergy);
						self.factoryCount = Number(dataJson.result.closedPowerStations);
						self.refresh();
					},
					error: function() {
						alert("系统繁忙");
					}
				});

			},
			refresh(){
			$('#progressText').html(this.nowNum + "/" + this.total);
			var width = this.nowNum / this.total * $('#parallelogram').width();
			$('#topParallelogram').width((1 - this.nowNum / this.total) * $('#parallelogram').width());
			if(this.nowNum / this.total < 0.5) {
				$('#parallelogram').css("background-color", "red");;
			}
			switch(this.level) {
				case 1:
					document.getElementById("cityLevelImage").src = require("../assets/images/city_level_1.png");
					break;
				default:
					break;
			}
			switch(this.factoryCount) {
				case 1:
					document.getElementById("factoryCount").src = require("../assets/images/count.png");
					break;
				default:
					break;
			}
		}

		},
		mounted() {
			this.getData();
			this.refresh();
		},
		
	}
</script>

<style scoped="yes">
	* {
		margin: 0;
		padding: 0;
		list-style-type: none;
	}
	
	html,
	body,
	div,
	span,
	a,
	p,
	img,
	dl,
	dt,
	dd,
	ol,
	ul,
	li {
		border: 0;
		margin: 0;
		padding: 0;
		font-size: 100%;
		text-decoration: none
	}
	
	img {
		color: transparent;
		font-size: 0;
		vertical-align: middle;
		-ms-interpolation-mode: bicubic;
		max-width: 100%
	}
	
	body {
		max-width: 640px;
		margin: 0 auto;
		font-family: "microsoft yahei";
		font-size: 13px;
		line-height: 1.5;
		background: #f9f1cd;
	}
	
	#progressBar {
		position: relative;
		width: 100%;
		margin-top: 20px;
		float: left;
		display: flex;
	}
	
	#cityLevelImage {
		position: absolute;
	}
	
	#cityCountImage {
		position: relative;
		width: 50px;
		margin-right: 10px;
		top: -10px;
		/*float: right;*/
	}
	
	#progressText {
		font-size: small;
		line-height: 20px;
		/*margin-left: 20px;*/
		width: auto;
		float: left;
		color: white;
		font-weight: lighter;
	}
	
	#progress {
		position: relative;
		/*display:inline-block;*/
		width: -webkit-fill-available;
		height: 20px;
		float: left;
		margin-left: 20px;
		margin-right: 20px;
	}
	
	#parallelogram {
		position: absolute;
		width: 100%;
		height: 100%;
		background: limegreen;
		-webkit-transform: skew(30deg);
		-moz-transform: skew(30deg);
		-o-transform: skew(30deg);
		transform: skew(-30deg);
		z-index: 0;
		/*display: inline;*/
	}
	
	#topParallelogram {
		position: absolute;
		width: 10%;
		height: 100%;
		background: #FFFFFF;
		-webkit-transform: skew(30deg);
		-moz-transform: skew(30deg);
		-o-transform: skew(30deg);
		transform: skew(-30deg);
		z-index: 1;
	}
	
	#toptenz {
		position: relative;
		text-align: right;
		width: 100%;
	}
	
	/*标题栏*/
	
	.sign-titleBar {
		height: 44px;
		width: 100%;
		position: fixed;
		background-color: transparent;
		top: 0;
		display: flex;
		flex-direction: row;
	}
	
	.leftDiv {
		width: 20%;
		height: 100%;
		margin-top: -15px;
		text-align: left;
		z-index: 1000;
	}
	
	.leftImg {
		height: 24px;
		width: 24px;
		margin-left: 13px;
		margin-top: 10px;
	}
	
	.centerDiv {
		width: 60%;
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
		width: 20%;
		height: 100%;
		text-align: right;
	}
</style>
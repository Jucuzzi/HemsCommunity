<template>
	<div>
		<mainHouseSelf v-bind:mainHouseData="mainHouseData"></mainHouseSelf>

		<div class="maintitle">
			<ul class="list-title">
				<li class="tableView-cell">
					<div class="title">最新动态</div>
				</li>
			</ul>
		</div>
		<div class="sperateView">
		</div>
		<!--
        	动态列表
        -->
		<div class="currentActivity">
			<ul class="list-infoView">
				<br />
				<li class="tableView-cell" v-on:click="stealEnergyJump" v-for="item in thieves">
					<div class="name">{{item.userName}}&nbsp;&nbsp;&nbsp;<span style="font-size: 20px; color: darkgrey;">收取{{item.stealEnergy}}g</span></div>
					<div class="time">{{item.date}}&nbsp;&nbsp;&nbsp;</div>
				</li>
				<br />
			</ul>
		</div>
		<div class="sperateView">
		</div>
		<div class="findMore" v-on:click="MoreDynamicJump">
			查看更多动态
		</div>
		<div class="sperateViewLarge">
		</div>

		<div class="midetitle">
			<ul>
				<li class="tableView-cell">
					<div class="title" style="margin-top: 15px;margin-bottom: 15px;">周边好友</div>
				</li>
			</ul>
		</div>
		<div class="sperateView">
		</div>
		<!--
        	周边好友列表
        -->
		<div class="list-frands">
			<ul class="list-frandsView" id="list-frandsViews">
				<li class="tableView-cell-frands" v-on:click="stealEnergyJump" v-for="item in friends">
					<div class="userInfo">
						<div class="selfTitleImage">
							<img src="../../static/img/userImg.jpg" id="theShow" />
						</div>
						<div class="selfName">
							{{item.userName}}
						</div>
						<div class="numOfElec">
							本月已收集谷电能量{{item.AllValleyEnergy}}g
						</div>
						<div class="canGet">
							<img src="../../static/img/getIt.png" id="haveEnage" />
						</div>
					</div>
					<!--<div class="sperateView" style="margin-top: 30px;">-->
					<!--</div>-->
				</li>

			</ul>
		</div>

	</div>
</template>

<script>
	import mainHouseSelf from './../components/mainHouse.vue'

	export default {
		data() {
			return {
				"friends": [], //可偷取的用户列表（周边好友）
				"thieves": [], //偷取能量的用户列表（最新动态）
				"mainHouseData": {}
			}
		},
		mounted() {
			this.getData();
		},
		components: {
			mainHouseSelf
		},

		methods: {
			MoreDynamicJump: function() {
				this.$router.push({
					path: '/MoreDynamic'
				});
			},
			stealEnergyJump: function() {
				this.$router.push({
					path: '/stealEnergy'
				});
			},
			getData: function() {
				var self = this;
				ajaxRequest({
					url: 'getValleyEnergyMain',
					data: {},
					async: true,
					success: function(dataJson) {
//												alert(JSON.stringify(dataJson));
						self.thieves = dataJson.result.thieves;
						self.friends = dataJson.result.friends;
						//						"enviromentLevel": "", //环境等级（窗外等级）
						//						"generatedEnergy": [], //浮动的能量球
						self.mainHouseData = {};
						self.mainHouseData.enviromentLevel = "";
						self.mainHouseData.generatedEnergy = "";
						self.mainHouseData.AllValleyEnergy = "";
						self.mainHouseData.isMain = "main";
						self.mainHouseData.enviromentLevel = dataJson.result.enviromentLevel;
						self.mainHouseData.generatedEnergy = dataJson.result.generatedEnergy;
						self.mainHouseData.AllValleyEnergy = dataJson.result.AllValleyEnergy;
						//						self.generatedEnergy = dataJson.result.generatedEnergy;
						//						self.AllValleyEnergy = dataJson.result.AllValleyEnergy;
						//						self.refresh();
					},
					error: function() {
						alert("系统繁忙");
					}
				});

			},
		}
	}
</script>

<style scoped="yes">
	.backgroundTitleView {
		position: absolute;
		left: 0;
		top: 0;
		width: 100%;
		height: 260px;
		background: url(../../static/img/freshHome.png) center;
		background-size: 100% 100%;
	}
	
	.strategy {
		width: 50px;
		height: 50px;
		padding-left: 10px;
	}
	
	#strategyButton {
		width: 50px;
		height: 50px;
		margin-top: 410%;
	}
	
	.redMoney {
		width: 50px;
		height: 50px;
		padding-left: 20px;
	}
	
	#redPacketButton {
		width: 50px;
		height: 50px;
		padding-left: 50px;
		margin-top: 310%;
	}
	
	.userInfo {
		height: 70px;
		width: 100%;
	}
	
	.userInfoTitle {
		text-align: right;
		width: 50%;
		padding-left: 50%;
		margin-top: -270px;
	}
	
	.userShow {
		padding-right: 63px;
		margin-top: 343px;
		position: relative;
		z-index: 4;
	}
	
	#userShowImg {
		width: 50px;
		height: 50px;
		border-radius: 30px;
	}
	
	.infoName {
		color: white;
		font: "微软雅黑";
		font-size: 15px;
		padding-right: 2px;
		margin-top: -52px;
		position: relative;
		z-index: 5;
	}
	
	.infoDetail {
		color: white;
		font: "微软雅黑";
		font-size: 15px;
		padding-right: 25px;
		margin-top: 0px;
		position: relative;
		z-index: 5;
	}
	
	.maintitle {
		/*margin-top: 260px;*/
	}
	
	.tableView-cell {
		width: 100%;
		height: 40px;
		list-style-type: none;
	}
	
	.tableView-cell-frands {
		position: relative;
		width: 100%;
		height: 70px;
		list-style-type: none;
		/*设置下边框样式*/
		border-bottom-style: solid;
		border-bottom-width: 1px;
		border-bottom-color: #D3D3D3;
	}
	
	.title {
		text-align: left;
		font-size: 24px;
		padding-top: 7px;
	}
	
	.sperateView {
		width: 100%;
		height: 1px;
		background-color: #D3D3D3;
	}
	
	.name {
		text-align: left;
		font-weight: 300;
		font-size: 22px;
		padding-top: 0px;
	}
	
	.time {
		text-align: right;
		font-weight: 300;
		margin-top: -30px;
		font-size: 20px;
		color: darkgrey;
	}
	
	.findMore {
		width: 100%;
		height: 60px;
		font-size: 24px;
		font-weight: normal;
		text-align: center;
		padding-top: 12px;
		color: black;
	}
	
	.sperateViewLarge {
		width: 100%;
		height: 20px;
		background-color: #F3F3F3;
	}
	
	.selfTitleImage {
		position: relative;
		top: 10px;
		width: 50px;
		height: 50px;
	}
	
	#theShow {
		width: 45px;
		height: 45px;
		margin-top: 0px;
	}
	
	.selfName {
		font: "微软雅黑";
		font-size: 18px;
		/*margin-left: -33%;*/
		/*margin-top: -60px;*/
		position: absolute;
		top: 10px;
		left: 70px;
	}
	
	.numOfElec {
		position: absolute;
		font: "微软雅黑";
		font-size: 16px;
		color: darkgray;
		left: 70px;
		top: 35px;
		width: 50%;
	}
	
	.canGet {
		position: absolute;
		right: 50px;
		width: 40px;
		right: 40px;
		top: 15px
		/*padding-left: 85%;*/
		/*margin-top: -50px;*/
	}
	
	#haveEnage {
		width: 40px;
		height: 40px;
	}
	
	.midetitle {
		margin-top: -10px;
	}
</style>
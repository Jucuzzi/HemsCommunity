<template>

	<body topmargin="0" leftmargin="0" rightmargin="0">
		<div class="sign-titleBar">
			<div class="leftDiv" v-on:click="gotoMainView">
				<img class="leftImg" src="../assets/images/title_arrow_left_white.png" />
			</div>
			<div class="centerDiv">
				<span class="titleName">累计贡献排行榜</span>
			</div>
		</div>
		<div class="backgroundView">
		</div>
		<div class="backgroundToBack">
		</div>
		<div class="titleImg" style="text-align: center;">
			<img src="../../static/img/userImg.png" id="theOne" />
		</div>

		<div class="firstNumber">
			<img src="../../static/img/NumberOneTip.png" id="firstTipImg" />
		</div>
		<div class="firstShowchach">
			<img src="../../static/img/crown.png" id="Showchach" />
		</div>
		<div class="firstShowYellow" style="text-align: center;">
			<img src="../../static/img/scrollYellow.png" id="ShowYellow" />
			<div class="firstUsername">路飞</div>
		</div>
		<div class="firstShowEnergy">
			<div class="firstelecImg">
				<img src="../../static/img/elecTip.png" id="firstelecTipImg" />
			</div>

			<div class="firstnumOfElec">
				{{AllValleyEnergy}}g
			</div>
		</div>

		<div class="list-wrapper list-wrapper-hook">
			<div class="sperateView">
			</div>
			<div>
				<!-- 列表 -->
				<ul class="list-infoView">
					<li class="tableView-cell-noBorder">
						<div class="userInfo">
							<div class="NumberOfList">
								<div class="showNumber">NO.{{rank}}</div>
							</div>
							<div class="selfTitleImage">
								<img src="../../static/img/userImg.png" id="theShow" />
							</div>
							<div class="selfName">
									{{userName}}
							</div>

							<div class="elecImg">
								<img src="../../static/img/elecTip.png" id="elecTipImg" />
							</div>

							<div class="numOfElec">
								{{AllValleyEnergy}}g
							</div>
						</div>
					</li>
					<div class="sperateLine">
					</div>
					<li class="tableView-cell" v-for="(item,itemIndex) in topUserList">
						<div class="userInfo">
							<div class="NumberOfList">
								<div class="showNumber">NO.{{itemIndex+2}}</div>
							</div>
							<div class="selfTitleImage">
								<img src="../../static/img/userImg.png" id="theShow" />
							</div>
							<div class="selfName">
								{{item.userName}}
							</div>

							<div class="elecImg">
								<img src="../../static/img/elecTip.png" id="elecTipImg" />
							</div>

							<div class="numOfElec">
								{{item.AllValleyEnergy}}g
							</div>
						</div>
					</li>

				</ul>
			</div>
			<!--<div class="sperateView">
					</div>-->
		</div>
	</body>
</template>

<script>
	export default {
		data() {
			return {
				"topUserList": [],
				"AllValleyEnergy": "",
				"rank": "",
				"userName": "",
				"topUser": {}
			}
		},
		mounted() {
			this.getData();
		},
		methods: {
			gotoMainView: function() {
				this.$router.push({
					path: '/CityView'
				})
			},
			getData() {
				var self = this;
				ajaxRequest({
					url: 'getTotalContributionRank',
					data: {
						"userId": "1419",
					},
					async: true,
					success: function(dataJson) {
						self.rank = dataJson.result.rank;
						self.userName = dataJson.result.userName;
						self.topUser = dataJson.result.topUser;
						self.AllValleyEnergy = dataJson.result.AllValleyEnergy;
						self.topUserList = dataJson.result.topUserList;
					},
					error: function() {
						alert("系统繁忙");
					}
				});
			}

		}
	}
</script>

<style scoped>
	.backgroundView {
		width: 100%;
		height: 300px;
		background: url(../../static/img/userImg.png) center;
		filter: blur(15px);
		background-size: 100% 140%;
		margin-top: 0px;
		/*设置图层位置*/
		position: absolute;
		z-index: 2;
	}
	
	.backgroundToBack {
		width: 100%;
		height: 300px;
		background: url(../../static/img/userImg.png) center;
		filter: blur(2px);
		background-size: 100% 140%;
		position: absolute;
		z-index: 1;
		margin-top: 0px;
	}
	
	#theOne {
		/*position: absolute;*/
		/*width: 30%;
		height: 30%;*/
		left: 0px;
		width: 150px;
		height: 150px;
		border-radius: 75px;
	}
	
	.titleImg {
		/*margin-left: 35%;*/
		width: 100%;
		height: 150px;
		top: 70px;
		/*margin-top: -500px;*/
		position: absolute;
		z-index: 3;
	}
	
	.firstNumber {
		margin-top: 0px;
		position: absolute;
		z-index: 4;
		top: 150px;
		margin-left: 0%;
	}
	
	#firstTipImg {
		width: 80px;
	}
	
	.tableView-cell {
		width: 100%;
		height: 70px;
		/*margin-left: -30px;*/
		list-style-type: none;
		/*设置下边框样式*/
		border-bottom-style: solid;
		border-bottom-width: 1px;
		border-bottom-color: #D3D3D3;
	}
	
	.tableView-cell-noBorder {
		width: 100%;
		height: 70px;
		/*margin-left: -30px;*/
		list-style-type: none;
	}
	
	.userInfo {
		position: relative;
		height: 70px;
		margin-top: 0px;
	}
	
	.NumberOfList {
		width: 15%;
		height: 70px;
	}
	
	.showNumber {
		padding-left: 10px;
		padding-top: 25px;
		font: "微软雅黑";
		font-size: 20px;
		color: darkorange;
		text-align: left;
		vertical-align: middle;
	}
	
	.firstShowchach {
		position: absolute;
		top: 40px;
		width: 100%;
		text-align: center;
		z-index: 5;
		/*margin-left: -30%;
		margin-top: -135px;*/
	}
	
	#Showchach {
		margin-right: 100px;
		width: 70px;
		height: 80px;
	}
	
	.firstShowYellow {
		position: absolute;
		width: 100%;
		z-index: 6;
		top: 180px;
	}
	
	.firstShowEnergy {
		position: absolute;
		width: 100%;
		z-index: 6;
		top: 245px;
		height: 40px;
		text-align: center;
	}
	
	
	.firstelecImg {
		width: 100%;
		height: 40px;
	}
	
	#firstelecTipImg {
		margin-right: 80px;
		width: 40px;
		height: 40px;
	}
	
	.firstnumOfElec {
		margin-top: -43px;
		margin-left: 40px;
		width: 100%;
		font: "微软雅黑";
		font-size: 30px;
		color: #FF8C00;
	}
	
	#ShowYellow {
		width: 300px;
	}
	
	.firstUsername {
		width: 100%;
		text-align: center;
		font-size: 18px;
		color: white;
		margin-top: -25px;
	}
	
	.selfTitleImage {
		position: absolute;
		width: 60px;
		height: 60px;
		left: 100px;
		top: 5px;
	}
	
	.selfName {
		position: absolute;
		font: "微软雅黑";
		font-size: 20px;
		left: 200px;
		top: 10px;
		/*margin-top: -60px;*/
	}
	
	.elecImg {
		position: absolute;
		width: 20px;
		height: 20px;
		top: 43px;
		left: 200px;
		/*padding-left: 51%;*/
		/*margin-top: 5px;*/
	}
	
	#elecTipImg {
		width: 20px;
		height: 20px;
	}
	
	.numOfElec {
		position: absolute;
		font: "微软雅黑";
		font-size: 18px;
		color: #FF8C00;
		left: 230px;
		top: 40px;
	}
	
	#theShow {
		position: absolute;
		top: 0px;
		width: 60px;
		height: 60px;
		border-radius: 50px;
	}
	
	.sperateView {
		/*margin-top: 40px;*/
		width: 110%;
		height: 3px;
		background-color: #F3F3F3;
		margin-left: 0%;
	}
	
	.sperateLine {
		/*margin-top: 40px;*/
		width: 130%;
		height: 30px;
		margin-top: 10px;
		background-color: #F3F3F3;
		margin-left: -30%;
	}
	
	.list-wrapper {
		position: relative;
		/*top: 260px;*/
		z-index: 0;
		margin-top: 300px;
		margin-left: 0px;
		width: 100%;
	}
	/*标题栏*/
	
	.sign-titleBar {
		height: 44px;
		width: 100%;
		position: fixed;
		top: 0;
		/*background: /*#1cbafb*/
		;
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
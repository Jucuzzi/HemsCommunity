<template>
	<div id="">
		<div class="detail-top-background">
			<div class="sign-titleBar">
				<div class="leftDiv" v-on:click="backToSign">
					<img class="leftImg" src="../assets/images/title_arrow_left_white.png" />
				</div>
			</div>
			<div class="detail-top-layout-left-right">
				<div class="detail-button-left-right">
					<div class="text-type">收入：{{incomePoints}}积分</div>
				</div>
				<div class="ver-line"></div>
				<div class="detail-button-left-right">
					<div class="text-type">支出：{{expendPoints}}积分</div>
				</div>
			</div>
			<div class="circle-big-bg">
				<div class="circle-bg">
					<div class="text-type-small">赫马积分</div>
					<div class="text-type-big">{{points}}</div>
				</div>
			</div>
		</div>
		<div class="demo-infinite-container">
			<ul class="ul-layout">
				<div v-for="item in pointsHistroy">
					<li class="date-layout">
						{{item.date.substring(0,4)}}年{{item.date.substring(4,6)}}月{{item.date.substring(6,8)}}日
					</li>
					<li class="sign-list-root" v-for="insideItem in item.history">
						<img class="user-icon" v-if="insideItem.changeType == 1" src="../assets/img/icon_one.png" />
						<img class="user-icon" v-if="insideItem.changeType == 2" src="../assets/img/icon_two.png" />
						<img class="user-icon" v-if="insideItem.changeType == 3" src="../assets/img/icon_one.png" />
						<div class="sign-list-item-center">
							<span class="user-name" v-if="insideItem.changeType == 1">
						签到获取
					</span>
							<span class="user-name" v-if="insideItem.changeType == 2">
						积分抽奖
					</span>
							<span class="user-name" v-if="insideItem.changeType == 3">
						能量兑换
					</span>
						</div>
						<div class="sign-list-item-right-outer">
							<div class="sign-list-item-right">
								<span class="get-points">{{insideItem.changeNum}}积分</span>
								<span class="sign-time">{{insideItem.date.substring(8,10)}}:{{insideItem.date.substring(10,12)}}</span>
							</div>
						</div>
					</li>
				</div>
			</ul>
			<mu-infinite-scroll :scroller="scroller" :loading="loading" @load="loadMore" />
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				"expendPoints": "",
				"incomePoints": "",
				"points": "",
				"pointsHistroy": [],
				"lastQueryId": "",
				num: 0,
				loading: false,
				scroller: null
			}
		},
		methods: {
			backToSign: function() {
				this.$router.push({
					path: 'sign'
				})
			},
			getData() {
				var self = this;
				ajaxRequest({
					url: 'getPointsDetail',
					data: {
						"userId": "1419",
						"recordId": self.lastQueryId,
					},
					async: true,
					success: function(dataJson) {
						self.expendPoints = dataJson.result.expendPoints;
						self.incomePoints = dataJson.result.incomePoints;
						self.points = dataJson.result.points;
						self.pointsHistroy = dataJson.result.pointsHistory;
						self.lastQueryId = dataJson.result.pointsHistory[dataJson.result.pointsHistory.length - 1].history[dataJson.result.pointsHistory[dataJson.result.pointsHistory.length - 1].history.length - 1].recordId;
						for(var i = 0; i < dataJson.result.pointsHistory.length; i++) {
							self.num += dataJson.result.pointsHistory[i].history.length;
						}
					},
					error: function() {
						alert("系统繁忙");
					}
				});
			},
			loadMore() {
				if(this.num == 10) {
					this.loading = true
					this.num = 0;
					setTimeout(() => {
						var self = this;
						ajaxRequest({
							url: 'getPointsDetail',
							data: {
								"userId": "1419",
								"recordId": self.lastQueryId,
							},
							async: true,
							success: function(dataJson) {
								for(var i = 0; i < dataJson.result.pointsHistory.length; i++) {
									self.pointsHistroy.push(dataJson.result.pointsHistory[i]);
									self.num += dataJson.result.pointsHistory[i].history.length;
								}
								self.lastQueryId = dataJson.result.pointsHistory[dataJson.result.pointsHistory.length - 1].history[dataJson.result.pointsHistory[dataJson.result.pointsHistory.length - 1].history.length - 1].recordId;
							},
							error: function() {
								alert("系统繁忙");
							}
						});
						this.loading = false
					}, 1000)
				}
			}
		},
		mounted() {
			this.getData();
			this.scroller = this.$el
			//			$(".sign-titleBar").css('background', 'none');
		}
	}
</script>

<style scoped="yes">
	.sign-list-root {
		width: 100%;
		height: 60px;
		display: flex;
		flex-direction: row;
		align-items: center;
	}
	
	.user-icon {
		width: 40px;
		height: 40px;
		margin-left: 20px;
	}
	
	.sign-list-item-center {
		width: calc(100% - 190px);
	}
	
	.sign-list-item-center {
		display: flex;
		flex-direction: column;
		align-items: flex-start;
		justify-content: center;
		margin-left: 10px;
	}
	
	.user-name {
		width: 100%;
		font-size: 17px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		text-align: start;
	}
	
	.sign-line {
		width: 100%;
		height: 4px;
		margin-top: 3px;
		border-radius: 2px;
		background: #00ABFD;
	}
	
	.sign-list-item-right-outer {
		width: 100px;
		margin-top: 4px;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: flex-end;
	}
	
	.sign-list-item-right {
		display: flex;
		flex-direction: column;
		align-items: flex-end;
		justify-content: center;
		margin-right: 10px;
	}
	
	.get-points {
		padding-left: 4px;
		padding-right: 4px;
		border-radius: 5px;
		border-width: 4px;
		border: 1px solid grey;
		display: flex;
		white-space: pre;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		font-size: 15px;
	}
	
	.sign-time {
		margin-top: 4px;
		font-size: 13px;
		color: grey;
		margin-right: 12px;
	}
	
	.ul-layout {
		margin: 0px;
		padding: 0px;
	}
	
	.date-layout {
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		align-items: center;
		font-size: 14px;
		color: #2c2c2c;
		height: 40px;
		padding-left: 20px;
		border-top: 1px solid #dedede;
		border-bottom: 1px solid #dedede;
	}
	
	.detail-top-background {
		display: -webkit-flex;
		display: flex;
		flex-direction: column-reverse;
		justify-content: flex-start;
		align-items: center;
		background-image: url('../assets/img/detail_top_background.png');
		background-size: 100% 100%;
		width: 100%;
		height: 300px;
	}
	
	.detail-top-layout-left-right {
		background-color: #000000;
		filter: alpha(opacity=70);
		-moz-opacity: 0.7;
		-khtml-opacity: 0.7;
		opacity: 0.7;
		display: -webkit-flex;
		display: flex;
		width: 100%;
		height: 23%;
		justify-content: center;
		align-items: center;
	}
	
	.ver-line {
		background-color: #ffffff;
		width: 1px;
		height: 70%;
	}
	
	.detail-button-left-right {
		display: -webkit-flex;
		display: flex;
		background-size: 100% 100%;
		width: 50%;
		height: 50px;
		justify-content: center;
		align-items: center;
	}
	
	.text-type {
		color: #FFFFFF;
		font-size: 14px;
	}
	
	.circle-big-bg {
		display: -webkit-flex;
		display: flex;
		justify-content: center;
		align-items: center;
		width: 100%;
		height: 77%;
	}
	
	.circle-bg {
		display: -webkit-flex;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		width: 140px;
		height: 140px;
		filter: alpha(opacity=70);
		-moz-opacity: 0.7;
		-khtml-opacity: 0.7;
		opacity: 0.7;
		border: 2px solid #ffffff;
		background-color: #000000;
		border-radius: 70px;
	}
	
	.text-type-small {
		color: #FFFFFF;
		font-size: 14px;
	}
	
	.text-type-big {
		margin-top: 12px;
		color: #FFFFFF;
		font-size: 34px;
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
		text-align: left;
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
<template>
	<div class="sign-root">
		<raiders-calendar ref="raiderscalendar" :signDateList="signDateList"></raiders-calendar>
		<div class="upper-div-outer">
			<div class="upper-div">
				<div class="sign-titleBar">
					<div class="leftDiv" v-on:click="gotoSign">
						<img class="leftImg" src="../assets/images/title_arrow_left_white.png" />
					</div>
					<div class="centerDiv">
						<span class="titleName">签到</span>
					</div>
				</div>
				<div class="details-div">
					<div v-on:click="gotoPointDetail" class="details-entrance">
						<img class="detail_icon" src="../assets/img/sign/detail_icon.png">
						<span class="detail_text">积分详情</span>
					</div>
				</div>
				<div class="upper-center-div">
					<div class="integral_circle">
						<span class="hems-integral">赫马积分</span>
						<span id="hems-integral-num" class="hems-integral-num">0</span>
						<span id="sign-integral" class="sign-integral">今日签到积分 0</span>
						<span id="sign-sort" class="sign-sort">排在第0位</span>
					</div>
				</div>
				<div class="upper-below-div">
					<div class="sign-btn">
						<span class="sign-day-num" id="sign-day-num" v-on:click="showCalendarDialog">连续签到110天</span>
					</div>
					<div class="luck-draw-btn">
						<span class="luck-draw" v-on:click="gotoChoujiang">点我抽奖</span>
					</div>
				</div>
			</div>
		</div>
		<div class="lower-div">
			<div class="sign-list-title">
				<div class="sign-num-div">
					<span class="sign-num" id="sign-num">今日0</span>
					<span class="punch-clock">人已打卡</span>
				</div>
				<div class="sign-rule-outer-div">
					<div class="sign-rule-div">
						<img class="rule-icon" src="../assets/img/sign/rule_icon.png">
						<span class="sign-rule" v-on:click="ruleBtnClick" data-target="#apply10" data-toggle="modal">签到规则</span>
					</div>
				</div>
			</div>
			<sign-list-item v-for="(item, index) in signList" :data="item" :index="index">
			</sign-list-item>
		</div>
		<!--下面是签到规则弹框界面-->
		<div id="apply10" class="modal fade padding1" tabindex="-1" data-replace="true" data-backdrop="static">
			<div class="modal-dialog modal-content">
				<div class="modal-body">
					<div class="colse1" data-dismiss="modal">
						<img class="colse1" src="../assets/images/关闭-4.png"/>
					</div>
					<div class="wrap">
						<div class="head">奖励规则</div>
						<div class="body">
							<ul class="ul-style">
								<li class="rule-background">
									<div class="rule-item-layout-left-right">
										<div class="rule-child-item-left">
											<img id="icon_image" src="../telehems/img/integral_one_icon.png" height="15px" width="15px" />
										</div>
										<span class="rule-child-item-middle">
											第1名
										</span>
										<div class="rule-child-item-right">
											<span class="get-points">+200 积分</span>
										</div>
									</div>
								</li>
								<li class="rule-background">
									<div class="rule-item-layout-left-right">
										<div class="rule-child-item-left">
											<img id="icon_image" src="../telehems/img/integral_two_icon.png" height="15px" width="15px" />
										</div>
										<span class="rule-child-item-middle">
											第2名
										</span>
										<div class="rule-child-item-right">
											<span class="get-points">+100 积分</span>
										</div>
									</div>
								</li>
								<li class="rule-background">
									<div class="rule-item-layout-left-right">
										<div class="rule-child-item-left">
											<img id="icon_image" src="../telehems/img/integral_three_icon.png" height="15px" width="15px" />
										</div>
										<span class="rule-child-item-middle">
											第3名
										</span>
										<div class="rule-child-item-right">
											<span class="get-points">+80 积分</span>
										</div>
									</div>
								</li>
								<li class="rule-background">
									<div class="rule-item-layout-left-right">
										<div class="rule-child-item-left">
											<img id="icon_image" src="../telehems/img/integral_circle_icon.png" height="15px" width="15px" />
										</div>
										<span class="rule-child-item-middle">
											第4~10名
										</span>
										<div class="rule-child-item-right">
											<span class="get-points">+50 积分</span>
										</div>
									</div>
								</li>
								<li class="rule-background">
									<div class="rule-item-layout-left-right">
										<div class="rule-child-item-left">
											<img id="icon_image" src="../telehems/img/integral_circle_icon.png" height="15px" width="15px" />
										</div>
										<span class="rule-child-item-middle">
											第11~100名
										</span>
										<div class="rule-child-item-right">
											<span class="get-points">+30 积分</span>
										</div>
									</div>
								</li>
								<li class="rule-background">
									<div class="rule-item-layout-left-right">
										<div class="rule-child-item-left">
											<img id="icon_image" src="../telehems/img/integral_circle_icon.png" height="15px" width="15px" />
										</div>
										<span class="rule-child-item-middle">
											第101~1000名
										</span>
										<div class="rule-child-item-right">
											<span class="get-points">+20 积分</span>
										</div>
									</div>
								</li>
								<li class="rule-background">
									<div class="rule-item-layout-left-right">
										<div class="rule-child-item-left">
											<img id="icon_image" src="../telehems/img/integral_circle_icon.png" height="15px" width="15px" />
										</div>
										<span class="rule-child-item-middle">
											第1000名以上
										</span>
										<div class="rule-child-item-right">
											<span class="get-points">+20 积分</span>
										</div>
									</div>
								</li>
								<li class="rule-background">
									<div class="rule-item-layout-left-right">
										
										<span class="rule-child-item-middle">
											连续签到7天可获得额外奖励100积分
										</span>
									</div>
								</li>
							</ul>
						</div>
						<div class="foot">
							<div class="rule-bottom">
								<p class="rule-bottom-font-left">今日打卡排在第</p>
								<p class="rule-bottom-font-orange" id="rule-bottom-font-orange">652</p>
								<p class="rule-bottom-font">位</p>
							</div>
							<div class="rule-child-item-right">
								<span class="get-points" id="new-points">+20 积分</span>
							</div>
						</div>
						<div class="rule-bottom">
							<p class="rule-bottom-font-left">已连续签到</p>
							<p class="rule-bottom-font-orange" id="rule-bottom-font-Current">5</p>
							<p class="rule-bottom-font">天</p>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</template>

<script>
	import signListItem from '@/components/sign/signListItem.vue'
	import raidersCalendar from '@/components/sign/signCalendar.vue'
	export default {
		data() {
			return {
				isShow:true,
//				signDateList:
//						{"20170701":"0",
//						 "20170702":"0",
//						 "20170703":"0",
//						 "20170704":"0",
//						 "20170705":"0",
//						 "20170706":"0",
//						 "20170707":"1",
//						 "20170708":"0",
//						 "20170709":"0",
//						 "20170720":"0",
//						 "20170727":"0"},
				signDateList:{},
				signList: []
			}
		},
		created(){
         	this.initView();
     	},
		methods: {
			initView(){
				
				var self = this;
				ajaxRequest({
					url : "/hemsCircleSignInMain",
					data : {
						"userId":"1419"
					},
					async : true,
					success : function(dataJson) {
						alert("签到成功");
						self.getNewData();
//						$('#point').html(dataJson.result.points);
//						$('#times').html(dataJson.result.prizeDrawNum);
					},
					error : function() {
						alert("系统繁忙");
						self.getNewData();
					}
				});
			
			},
			
			getNewData(){
				var self = this;
				ajaxRequest({
					url : "/hemsCircleQueryPointsMains",
					data : {
						"userId":"1419"
					},
					async : true,
					success : function(dataJson) {
						//总结分
						$('#hems-integral-num').html(dataJson.result.points);
						//今日签到积分
						$('#sign-integral').html("今日签到积分 "+dataJson.result.todaySighInPoints);
						//总积分排名
						$('#sign-sort').html("排在第"+dataJson.result.pointsRanking+"位");
						//连续签到几天
						$('#sign-day-num').html("连续签到"+dataJson.result.signInDays+"天");
						//今日签到人数 
						$('#sign-num').html("今日"+dataJson.result.allSignInNum);
						//今日打卡排名
						$('#rule-bottom-font-orange').html(dataJson.result.signInRanking);
						//打卡积分
						$('#new-points').html("+"+dataJson.result.todaySighInPoints+" 积分");
						//连续签到几天
						$('#rule-bottom-font-Current').html(dataJson.result.signInDays);
						
						self.signList = dataJson.result.signInList;

						ajaxRequest({
							url : "/hemsCircleQuerySignInStateHistory",
							data : {
								"userId":"1419"
							},
							async : true,
							success : function(dataJson) {
								var currentData = dataJson.result.root;
//								alert(JSON.stringify(dataJson));
								for(var index=0;index<currentData.length;index++){
									self.signDateList[currentData[index].date]=currentData[index].hasGift;	
								}
							},
							error : function() {
								alert("系统繁忙");
								self.getNewData();
							}
						});
//						self.signList = 	[{
//						"id": "1",
//						"name": "擦拭不能看",
//						"icon": "http://pic.baike.soso.com/p/20131105/20131105155548-374146863.jpg",
//						"time": "00:01",
//						"integral": "200"
//					},
//					{
//						"id": "2",
//						"name": "安德森",
//						"icon": "http://pic.baike.soso.com/p/20131105/20131105155548-374146863.jpg",
//						"time": "00:02",
//						"integral": "100"
//					},
//					{
//						"id": "3",
//						"name": "阿大使大赛的",
//						"icon": "http://pic.baike.soso.com/p/20131105/20131105155548-374146863.jpg",
//						"time": "00:03",
//						"integral": "50"
//					},
//					{
//						"id": "4",
//						"name": "阿大使大赛打扫打扫打sdsd扫",
//						"icon": "http://pic.baike.soso.com/p/20131105/20131105155548-374146863.jpg",
//						"time": "00:04",
//						"integral": "20"
//					},
//					{
//						"id": "5",
//						"name": "安定",
//						"icon": "http://pic.baike.soso.com/p/20131105/20131105155548-374146863.jpg",
//						"time": "00:05",
//						"integral": "10"
//					},
//					{
//						"id": "6",
//						"name": "阿达",
//						"icon": "http://pic.baike.soso.com/p/20131105/20131105155548-374146863.jpg",
//						"time": "00:06",
//						"integral": "5"
//					},
//					{
//						"id": "7",
//						"name": "阿达",
//						"icon": "http://pic.baike.soso.com/p/20131105/20131105155548-374146863.jpg",
//						"time": "00:06",
//						"integral": "5"
//					},
//					{
//						"id": "8",
//						"name": "阿达",
//						"icon": "http://pic.baike.soso.com/p/20131105/20131105155548-374146863.jpg",
//						"time": "00:06",
//						"integral": "5"
//					},
//					{
//						"id": "9",
//						"name": "阿达",
//						"icon": "http://pic.baike.soso.com/p/20131105/20131105155548-374146863.jpg",
//						"time": "00:06",
//						"integral": "5"
//					},
//				];
					},
					error : function() {
						alert("系统繁忙");
						self.getNewData();
					}
		});
			},
			
			gotoSign: function() {
				this.$router.push({
					path: '/'
				})
			},
			ruleBtnClick: function() {
//				alert("我进来了");
				$('#apply10').modal('show');
			},
			gotoChoujiang: function() {
				this.$router.push({
					path: 'LuckyDraw'
				})
			},
			gotoPointDetail: function() {
				this.$router.push({
					path: 'pointDetail'
				})
			},
			showCalendarDialog:function(){
				this.$refs.raiderscalendar.showDialog();
			}
		},
		mounted(){
			$(".sign-titleBar").css('background','#1cbafb');
		},
		
		components: {
			signListItem,
			raidersCalendar,
		}
	}
</script>

<style scoped="yes">
	.sign-root {
		width: 100%;
		height: 100%;
	}
	
	.upper-div-outer {
		width: 100%;
		height: 110vw;
		background: #1cbafb;
		background-image: url(../assets/img/sign_bg.png);
		background-size: cover;
		position: relative;
	}
	
	.upper-div {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		align-items: center;
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
	/*积分详情按钮*/
	
	.details-div {
		margin-top: 42px;
		width: 100%;
		height: 43px;
		display: flex;
		flex-direction: row;
		justify-content: flex-end;
	}
	
	.details-entrance {
		width: 110px;
		height: 43px;
		background: #61cffc;
		border-bottom-left-radius: 25px;
		border-top-left-radius: 25px;
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}
	
	.detail_icon {
		width: 23px;
		height: 23px;
	}
	
	.detail_text {
		color: white;
		font-size: 14px;
		margin-left: 2px;
	}
	
	/*弹框相关*/
	/*弹框相关*/
.padding1 .modal-body .colse1 {
		position: absolute;
		top: 15px;
		right: 15px;
		width: 26px;
		height: 26px;
	}
	
	.modal-dialog {
		background-color: white;
		
		margin: 20px;
		margin-top: 30%;
		
	}
	
	.modal-dialog .modal-body {
		padding-left: 20px;
		padding-right: 20px
	}
	
	.padding1 .modal-body .but6 {
		background-color: #FD4789;
		display: inline-block;
		width: 160px;
		margin: 20px auto 20px auto;
		padding: 6px 0px;
		font-size: 20px;
		color: #FFF;
		text-align: center;
		border: 2px #FD4789 solid;
		border-radius: 20px;
		-webkit-border-radius: 20px;
	}
	
	.padding1 .modal-body .but7 {
		background-color: #FFFFFF;
		display: inline-block;
		width: 160px;
		margin: 20px auto 20px auto;
		padding: 6px 0px;
		font-size: 20px;
		color: #FD4789;
		text-align: center;
		border: 2px #FD4789 solid;
		border-radius: 20px;
		-webkit-border-radius: 20px;
	}
	
	
	.padding1 .modal-body p span {
		color: #ff4338
	}
	
	.padding1 .modal-dialog {
		border-radius: 20px;
		-webkit-border-radius: 20px;
	}
	/*签到积分显示*/
	
	.upper-center-div {
		width: 100%;
		height: 50%;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
	}
	
	.integral_circle {
		width: 45%;
		height: 45vw;
		;
		background-image: url(../assets/img/integral_circle.png);
		background-size: cover;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	
	.hems-integral {
		color: white;
		font-size: 17px;
	}
	
	.hems-integral-num {
		color: white;
		font-size: 26px;
		padding: 2px;
	}
	
	.sign-integral {
		color: white;
		font-size: 14px;
	}
	
	.sign-sort {
		color: white;
		font-size: 14px;
	}
	/*签到按钮与抽奖按钮*/
	
	.upper-below-div {
		width: 100%;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		/*(110%-42px-43px-50%-42px)/2*/
		margin-top: calc(30% - 63px);
		;
	}
	
	.sign-btn {
		width: 35%;
		height: 42px;
		margin-right: 5%;
		background-image: url(../assets/img/sign/sign_btn.png);
		background-size: 100% 100%;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		font-size: 0;
	}
	
	.sign-day-num {
		font-size: 15px;
		color: white;
	}
	
	.luck-draw-btn {
		width: 35%;
		height: 42px;
		margin-left: 5%;
		background-image: url(../assets/img/sign/luck_draw.png);
		background-size: 100% 100%;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		font-size: 0;
	}
	
	.luck-draw {
		font-size: 15px;
		color: white;
	}
	/*签到列表*/
	
	.lower-div {
		width: 100%;
		display: flex;
		flex-direction: column;
	}
	
	.sign-list-title {
		width: 100%;
		margin-top: 10px;
		display: flex;
		flex-direction: row;
		margin-bottom: 10px;
	}
	
	.sign-num-div {
		width: 60%;
		display: flex;
		flex-direction: row;
		align-items: flex-end;
		font-size: 0px;
	}
	
	.sign-num {
		margin-left: 15px;
		font-size: 20px;
	}
	
	.punch-clock {
		margin-left: 2px;
		font-size: 14px;
		color: grey;
	}
	
	.sign-rule-outer-div {
		width: 40%;
		display: flex;
		flex-direction: row;
		justify-content: flex-end;
		align-items: center;
	}
	
	.sign-rule-div {
		width: 80px;
		height: 23px;
		margin-right: 10px;
		border-radius: 5px;
		border-width: 4px;
		border: 1px solid #808080;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
	}
	
	.rule-icon {
		width: 11px;
		height: 9px;
	}
	
	.sign-rule {
		margin-left: 2px;
		font-size: 14px;
	}
	
	/*奖励规则*/
	.wrap {
		margin: 5px;
		margin-top: 10px;
		padding-top: 0px;
		padding-bottom: 0px;
		z-index: 1002;
		height: 100%;
		border-radius: 4px;
	}
	
	.head {
		height: 30px;
		line-height: 40px;
		margin-bottom: 10px;
		color: #333;
		font-size: 13px;
	}
	
	.body {
		text-align: center;
		border-bottom: 1px solid #dedede;
	}
	
	.foot {
		display: -webkit-flex;
		display: flex;
		width: 100%;
		height: 35px;
		border: 0px;
		margin: 0px;
	}
	
	.ul-style{
		border: 0px;
		margin: 0px;
		padding: 0px;
	}
	
	.rule-background{
		display: -webkit-flex;
		display: flex;
		width: 100%;
		border: 0px;
		margin: 0px;
	}
	
	.rule-item-layout-left-right{
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		width: 100%;
		height: 35px;
		justify-content: center;
		align-items: center;
	}
	
	.rule-child-item-left{
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		width: 15%;
		height: 100%;
		justify-content: center;
		align-items: center;
		margin: 0px;
		border: 0px;
	}
	
	.rule-child-item-middle{
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		align-items: center;
		color: #8a8a8a;
		font-size: 12px;
		width:100%;
		height: 100%;
		background-size: 100% 100%;
	}
	
	.rule-child-item-right{
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		width: 20%;
		height: 100%;
		margin-right: 10px;
		background-size: 100% 100%;
		justify-content: flex-end;
		align-items: center;
	}
	
	.rule-content-font{
		color: #8a8a8a;
		font-size: 12px;
		letter-spacing: 0px;
		line-height: 14.5px;
		padding-bottom: 3px;
		padding-top: 3px;
	  	overflow: hidden;
	  	text-overflow: ellipsis;
	  	display: -webkit-box;
	  	-webkit-line-clamp: 2;
	  	-webkit-box-orient: vertical
	}
	
	.get-points{
		margin-right: 10px;
		padding-left: 4px;
		padding-right: 4px;
		padding-bottom: 2px;
		padding-top: 2px;
		border-radius: 5px;
		border-width: 4px;
		border: 1px solid #c9cace; 
		display: flex;
		white-space:pre;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		font-size: 11px;
	}
	
	.rule-bottom{
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: flex-start;
		width: 80%;
		height: 35px;
		margin-top: 0px;
	}
	
	.rule-bottom-font-left{
		color: #000000;
		font-size: 12px;
		padding-left: 22px;
	}
	
	.rule-bottom-font-orange{
		color: #FF7256;
		font-size: 12px;
	}
	
	.rule-bottom-font{
		color: #000000;
		font-size: 12px;
	}
</style>
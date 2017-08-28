<template>
	<div class="hello" id="backgroundDiv">
		<div class="sign-titleBar">
			<div class="leftDiv" v-on:click="gotoSign">
				<img class="leftImg" src="../assets/images/title_arrow_left_white.png" />
			</div>
			<div class="centerDiv">
				<span class="titleName">积分抽奖</span>
			</div>
			<div class="rightDiv">
				<span class="rightTitle" data-target="#apply4" data-toggle="modal">中奖记录</span>
			</div>
		</div>

		<div id="mainDiv">
			<img src="../assets/images/Bitmap.png" id="headImage" />
		</div>
		<div id="detailDiv">
			<div id="timesDiv">
				<img src="../assets/images/timeImage.png" id="pointImage" />
				<span id="pointTitle">赫马积分</span>
				<span id="point">0</span>

			</div>
			<div id="pointDiv">
				<img src="../assets/images/pointImage.png" id="timesImage" />
				<span id="timesTitle">可抽奖次数</span>
				<span id="times">0</span>
			</div>
		</div>
		<div id="choujiangDiv">
			<div style="margin-top: 20px; margin-bottom: 20px;">
				<choujiang> </choujiang>
			</div>
			<text-carousel :winningList="winningList"></text-carousel>
		</div>
		<div id="text">
			<ul id="introduceText">
				<li style="margin-bottom: 10px;margin-top: 10px;width: 100%;">活动规则：</li>
				<li>1、200积分抽奖一次；</li>
				<li>2、活动获得优惠券凭兑换的卡密至赫马官方淘宝店铺购 买赫马智控器享受相应优惠；
				</li>
				<li>3、有关兑换发现问题可联系客服专线：xxxxxxxx；</li>
				<li>4、凡以不正当手段（包括作弊、紊乱系统、实时网络攻 击等）进行兑换，本平台有权终止该兑换；
				</li>
				<li>5、商家在法律规定的范围内对该活动有解释权</li>
			</ul>
		</div>
		<div style="height: 30px;">
		</div>
		<!--中奖记录弹框-->
		<div id="apply4" class="modal fade padding1" tabindex="-1" data-replace="true" data-backdrop="static">
			<div class="modal-dialog modal-content" style="position: relative;">
				<div class="modal-body">
					<div class="colse1" data-dismiss="modal">
						<img src="../assets/images/关闭-4.png" />
					</div>
					<div class="wrap">
						<div class="head">中奖记录</div>
						<div class="record-body">
							<ul class="ul-style">
								<li v-for="item of ticketList" class="record-background">
									<div class="record-item-layout-left-right">
										<div class="record-child-item-top">
											<img id="icon_image" src="../telehems/img/integral_circle_icon.png" height="15px" width="15px" />
										</div>
										<span class="record-child-item-right">
										{{item.time}}
									</span>
									</div>
									<div class="record-child-item-bottom">
										<span class="record-child-item-bottom-left">
										{{item.type}}
									</span>
										<span class="record-child-item-bottom-middle">
										兑换码:
									</span>
										<span class="record-child-item-bottom-right">
										{{item.password}}
									</span>
									</div>
									
									<img v-if="item.used==1" class="overlap-icon" src="../telehems/img/overlap_icon.png" />
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import textCarousel from '@/components/textCarousel.vue'
	import choujiang from '@/components/choujiang.vue'
	export default {
		name: 'hello2',
		allNUmber:'123',
		numOfPlay:'',
		data() {
			return {
				msg: 'Welcome to Your Vue.js App',
				winningList: [],
				ticketList:[],
				isShow: true,
			}
		},
		 created(){
          this.initView();
        },
		 
		methods: {
			click: function() {
				$('#apply2').modal('show');
			},
			gotoSign: function() {
				this.$router.go(-1);
			},
			initView(){
				var self = this;
				ajaxRequest({
					url : "/hemsCircleQueryPrizeDrawMain",
					data : {
						"userId":"1419"
					},
					async : true,
					success : function(dataJson) {
						$('#point').html(dataJson.result.points);
						$('#times').html(dataJson.result.prizeDrawNum);
										self.ticketList=[{
					"time":"2017-07-08 12:23",
					"type":"9折卷",
					"password":"201601239099",
					"used":0
				},{
					"time":"2017-07-08 12:23",
					"type":"9折卷",
					"password":"201601239099",
					"used":1
				},{
					"time":"2017-07-08 12:23",
					"type":"9折卷",
					"password":"201601239099",
					"used":0
				},{
					"time":"2017-07-08 12:23",
					"type":"9折卷",
					"password":"201601239099",
					"used":1
				},{
					"time":"2017-07-08 12:23",
					"type":"9折卷",
					"password":"201601239099",
					"used":0
				},{
					"time":"2017-07-08 12:23",
					"type":"9折卷",
					"password":"201601239099",
					"used":0
				}];
				$('.modal-body').innerHTML.reload();
					},
					error : function() {
						alert("系统繁忙");
					}
				});
			}

		},
		components: {
			choujiang,
			textCarousel,
		},
		mounted() {
			canCircle = false;
			click = false;
			luck.index = -1; //当前转动到哪个位置，起点位置
			luck.count = 0; //总共有多少个位置
			luck.timer = 0; //setTimeout的ID，用clearTimeout清除
			luck.speed = 20; //初始转动速度
			luck.times = 0; //转动次数
			luck.cycle = 50; //转动基本次数：即至少需要转动多少次再进入抽奖环节
			luck.prize = -1; //中奖位置
			$(".sign-titleBar").css('background', '#1cbafb');
			luck.init('luck');
			$("#btn").click(function() {
				//按下弹起效果
				$("#btn").addClass("cjBtnDom");
				setTimeout(function() {
					$("#btn").removeClass("cjBtnDom");
				}, 200);
				if(click) {
					return false;
				} else {
					canCircle = true;
					luck.speed = 100;
					roll();
					click = true;
					return false;
				}
			});
		},
		destroyed() {
			canCircle = false;
			luck.index = -1; //当前转动到哪个位置，起点位置
			luck.count = 0; //总共有多少个位置
			luck.timer = 100; //setTimeout的ID，用clearTimeout清除
			luck.speed = 20; //初始转动速度
			luck.times = 0; //转动次数
			luck.cycle = 50; //转动基本次数：即至少需要转动多少次再进入抽奖环节
			luck.prize = -1; //中奖位置
		}
	}
	$(".close2").click(function() {
		$('#myModal').modal('hide');
	});
</script>

<style scoped>
	@import url('../telehems/css/myself.css');
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
	
	ol,
	ul {
		list-style: none
	}
	
	li {
		display: list-item
	}
	
	:focus,
	.focus {
		outline: none
	}
	
	.hello {
		background-color: "#2CBBF8";
	}
	
	#backgroundDiv {
		background-color: #2CBBF8;
		width: 100%;
		text-align: center;
	}
	
	#mainDiv {
		width: 100%;
		text-align: center;
	}
	
	#headImage {
		margin-top: 44px;
		width: 80%;
	}
	
	#detailDiv {
		position: relative;
		text-align: right;
		display: flex;
		flex-direction: row;
		vertical-align: middle;
		text-align: center;
	}
	
	#pointDiv {
		position: relative;
		width: 50%;
		height: 80px;
		align-items: flex-start;
		justify-content: flex-start;
		flex-direction: row;
	}
	
	#pointImage {
		position: absolute;
		width: 110px;
		height: 55px;
		margin-top: 10px;
		right: 10%;
	}
	
	#pointTitle {
		width: 110px;
		height: 12px;
		line-height: 12px;
		position: absolute;
		top: 30%;
		right: 10%;
		font-weight: lighter;
		font-size: small;
		color: #FFFFFF
	}
	
	#point {
		width: 110px;
		height: 12px;
		line-height: 12px;
		position: absolute;
		top: 55%;
		right: 10%;
		font-weight: lighter;
		font-size: medium;
		color: #FFFFFF
	}
	
	#timesDiv {
		position: relative;
		width: 50%;
		align-items: flex-end;
		justify-content: flex-end;
		flex-direction: row;
	}
	
	#timesTitle {
		width: 140px;
		height: 12px;
		line-height: 12px;
		position: absolute;
		top: 40%;
		left: 10%;
		font-weight: lighter;
		font-size: small;
		color: #FFFFFF
	}
	
	#times {
		width: 140px;
		height: 12px;
		line-height: 12px;
		position: absolute;
		top: 70%;
		left: 10%;
		font-weight: lighter;
		font-size: medium;
		text-align: center;
		color: #FFFFFF
	}
	
	#timesImage {
		position: absolute;
		
		width: 140px;
		height: 70px;
		top: 15%;
		left: 10%;
	}

	#text {
		margin-top: 30px;
		width: 100%;
		border-radius: 15px;
		vertical-align: middle;
		text-align: left;
	}
	
	#introduceText {
		width: 80%;
		margin: 0 auto;
		padding: 10px;
		border: 2px #FFFFFF solid;
		border-radius: 15px;
		font-weight: lighter;
		font-size: small;
		color: #FFFFFF;
		/*padding-bottom: 20px;*/
	}
	/*弹框相关*/
	
	.padding1 .modal-body .colse1 {
		/*background-color: black;*/
		background-image: url(../assets/images/关闭-4.png);
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
	
	.padding1 .modal-body p {
		width: 80%;
		margin: 0px auto;
		text-align: center;
		font-size: 19px;
		color: #898989;
		padding: 10px 0px;
	}
	
	.padding1 .modal-body p span {
		color: #ff4338
	}
	
	.padding1 .modal-dialog {
		border-radius: 20px;
		-webkit-border-radius: 20px;
	}
	
	.padding1 .modal-body {
		padding: 0px;
		position: relative
	}
	/*下面是中奖记录相关css*/
	
	.overlap-icon {
		position: absolute;
		left: 60%;
		top: 15px;
		width: 50px;
		height: 50px;
	}
	
	.record-child-item-right {
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		align-items: center;
		color: #000000;
		font-size: 14px;
		width: 85%;
		height: 100%;
		background-size: 100% 100%;
	}
	
	.record-child-item-bottom-left {
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
		font-size: 14px;
		color: #ffffff;
		width: 30%;
	}
	
	.record-child-item-bottom-middle {
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		justify-content: left;
		align-items: left;
		font-size: 14px;
		color: #000000;
		width: 30%;
	}
	
	.record-child-item-bottom-right {
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		align-items: center;
		font-size: 14px;
		color: #ffffff;
		width: 40%;
	}
	
	.record-child-item-bottom {
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
		background: rgba(28, 186, 251, 0.8);
		width: 90%;
		height: 35px;
		border-radius: 4px;
	}
	
	.record-background {
		position: relative;
		display: -webkit-flex;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		width: 100%;
		border: 0px;
		margin: 0px;
	}
	
	.record-item-layout-left-right {
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		width: 100%;
		height: 25px;
		justify-content: center;
		align-items: center;
	}
	
	.record-child-item-top {
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
	
	.record-body {
		text-align: center;
	}
	/*	/*
	.alert {
		position: fixed;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		background: rgba(0, 0, 0, 0.8);
		z-index: 1000;
		transition: all .3s ease-in-out;
	}*/
	
	.wrap {
		/*position: absolute;*/
		/*padding-top: 40px;*/
		margin: 5px;
		margin-top: 50px;
		padding-top: 20px;
		padding-bottom: 20px;
		z-index: 1002;
		/*min-width: 350px;
		min-height: 350px;*/
		/*background: #fff;*/
		height: 100%;
		/*left: 50%;*/
		/*top: 50%;*/
		/*transform: translate(-50%, -50%);*/
		border-radius: 4px;
	}
	
	.head {
		height: 30px;
		line-height: 40px;
		margin-bottom: 10px;
		color: #333;
		font-size: 17px;
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
	
	.ul-style {
		border: 0px;
		margin: 0px;
		padding: 0px;
	}
	
	.rule-background {
		display: -webkit-flex;
		display: flex;
		width: 100%;
		border: 0px;
		margin: 0px;
	}
	
	.rule-item-layout-left-right {
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		width: 100%;
		height: 35px;
		justify-content: center;
		align-items: center;
	}
	
	.rule-child-item-left {
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
	
	.rule-child-item-middle {
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		align-items: center;
		color: #8a8a8a;
		font-size: 12px;
		width: 65%;
		height: 100%;
		background-size: 100% 100%;
	}
	
	.rule-child-item-right {
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
	
	.rule-content-font {
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
	
	.get-points {
		margin-right: 10px;
		padding-left: 4px;
		padding-right: 4px;
		padding-bottom: 2px;
		padding-top: 2px;
		border-radius: 5px;
		border-width: 4px;
		border: 1px solid #c9cace;
		display: flex;
		white-space: pre;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		font-size: 11px;
	}
	
	.rule-bottom {
		display: -webkit-flex;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: flex-start;
		width: 80%;
		height: 35px;
		margin-top: 0px;
	}
	
	.rule-bottom-font-left {
		color: #000000;
		font-size: 12px;
		padding-left: 22px;
	}
	
	.rule-bottom-font-orange {
		color: #FF7256;
		font-size: 12px;
	}
	
	.rule-bottom-font {
		color: #000000;
		font-size: 12px;
	}
	/*第二部分*/
	
	h1,
	h2 {
		font-weight: normal;
	}
	
	ul {
		list-style-type: none;
		padding: 0;
	}
	
	li {
		display: inline-block;
		margin: 0 10px;
	}
	
	a {
		color: #42b983;
	}
	/*标题栏*/
	
	.sign-titleBar {
		height: 44px;
		width: 100%;
		position: fixed;
		top: 0;
		display: flex;
		flex-direction: row;
		z-index: 100;
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
	
	.rightTitle {
		height: 100%;
		line-height: 44px;
		color: white;
		font-size: 17px;
		margin-right: 13px;
	}
</style>
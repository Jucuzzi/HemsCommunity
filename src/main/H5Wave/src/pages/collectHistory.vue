<template>

	<body>

		<!-- header -->
		<!--<div class="titles">
			<p id="titleName">收集记录</p>
		</div>
		<div id="line1">      
		</div>-->
		<div class="sign-titleBar">
			<div class="leftDiv" v-on:click="gotoMainView">
				<img class="leftImg" src="../assets/images/title_arrow_left_white.png" />
			</div>
			<div class="centerDiv">
				<span class="titleName">收集记录</span>
			</div>
		</div>
		<!-- content -->
		<div class="list-wrapper list-wrapper-hook">
			<div>
				<!-- 列表 -->
				<ul class="list-content list-content-hook">
					<li class="tableView-cell-title">
						<div class="cellDataTime">
							<div class="showTimeTitle">获取时间</div>
						</div>
						<div class="cellDataInfo">
							<div class="showInfoTitle">获取能量</div>
						</div>

					</li>
					<li class="tableView-cell" v-for="item in stealRecord">
						<div class="cellDataTime">
							<div class="showTime">{{item.date}}</div>
						</div>
						<div class="cellDataInfo">
							<div class="showInfo">{{item.energy}}g</div>
						</div>

					</li>
				</ul>
				<!-- 
        1、底部提示信息 
        2、这里有一种情况,就是没有更多数据时,这里的文本应该显示"暂无更多数据"
      -->
				<!--<div class="bottom-tip">
					<span class="loading-hook">查看更多</span>
				</div>-->
			</div>
		</div>
		<!-- content end  -->

		<!-- alert -->
		<div class="alert alert-hook">刷新成功</div>

		<!-- js -->

	</body>
</template>

<script>
	export default {
		data() {
			return {
				"stealRecord": [],
			}
		},
		methods: {
			gotoMainView: function() {
				this.$router.push({
					path: '/rankList'
				})
			},
			getData() {
				var self = this;
//				alert("我进来了");
				ajaxRequest({
					url: 'getStealRecord',
					data: {
						"userId": "1419",
					},
					async: true,
					success: function(dataJson) {
//						alert(JSON.stringify(dataJson));
						self.stealRecord = dataJson.result.root;
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
	* {
		margin: 0;
		padding: 0;
		list-style: none;
		box-sizing: border-box;
	}
	
	body {
		font-family: 'SimHei';
	}
	
	.titles {
		width: 100%;
		height: 60px;
		background: url(../../static/img/titleImg.png) round;
	}
	
	#titleName {
		padding-top: 18px;
		margin-left: 5%;
		color: white;
	}
	
	.historyTitle {
		text-align: center;
	}
	
	#line1 {
		width: 100%;
		border-bottom: 1px solid #D3D3D3;
	}
	/* 字体文件 */
	/* header、footer */
	
	.header,
	.footer {
		position: fixed;
		z-index: 2;
		left: 0;
		display: flex;
		width: 100%;
	}
	
	.header {
		top: 0;
		height: 55px;
		background: #35a251;
	}
	
	.header .icon-shouyeshouye {
		position: absolute;
		left: 10px;
		flex: 0 0 40px;
		width: 40px;
		height: 55px;
		line-height: 55px;
		font-size: 33px;
		color: #fff;
	}
	
	.header .title {
		flex: 1;
		line-height: 55px;
		color: #fff;
		text-align: center;
	}
	
	.footer {
		bottom: 0;
		height: 50px;
		line-height: 50px;
		background: #1a1a1a;
	}
	
	.footer span {
		flex: 1;
		font-size: 20px;
		color: #fff;
		text-align: center;
	}
	/* 列表容器 */
	
	.list-wrapper {
		position: fixed;
		z-index: 1;
		top: 60px;
		bottom: 0px;
		left: 0;
		width: 100%;
		background: #ccc;
		overflow: hidden;
	}
	
	.list-wrapper .list-content {
		background: #fff;
	}
	
	.list-wrapper .list-item {
		display: flex;
		padding: 10px;
		width: 100%;
		border-bottom: 1px solid #ddd;
	}
	
	.list-wrapper .list-item .avatar {
		flex: 0 0 100px;
		border: 1px solid #ddd;
	}
	
	.list-wrapper .list-item .text {
		position: relative;
		flex: 1;
		padding-left: 10px;
	}
	
	.list-wrapper .list-item .text h2 {
		font-size: 16px;
		font-weight: normal;
		color: rgb(7, 17, 27);
	}
	
	.list-wrapper .list-item .text span {
		position: absolute;
		bottom: 20px;
		color: rgba(7, 17, 27, 0.7);
	}
	/* 下拉、上拉提示信息 */
	
	.top-tip {
		position: absolute;
		top: -40px;
		left: 0;
		z-index: 1;
		width: 100%;
		height: 40px;
		line-height: 40px;
		text-align: center;
		color: #555;
	}
	
	.bottom-tip {
		width: 100%;
		height: 35px;
		line-height: 35px;
		text-align: center;
		color: #777;
		background: #f2f2f2;
	}
	/* 全局提示信息 */
	
	.alert {
		display: none;
		position: fixed;
		top: 55px;
		left: 0;
		z-index: 2;
		width: 100%;
		height: 35px;
		line-height: 35px;
		text-align: center;
		color: #fff;
		font-size: 12px;
		background: rgba(7, 17, 27, 0.7);
	}
	
	.tableView-cell {
		width: 100%;
		height: 80px;
		list-style-type: none;
		/*设置下边框样式*/
		border-bottom-style: solid;
		border-bottom-width: 1px;
		border-bottom-color: #D3D3D3;
	}
	
	.tableView-cell-title {
		width: 100%;
		height: 50px;
		list-style-type: none;
		/*设置下边框样式*/
		border-bottom-style: solid;
		border-bottom-width: 1px;
		border-bottom-color: #D3D3D3;
	}
	
	.cellDataTime {
		width: 50%;
	}
	
	.showTime {
		text-align: center;
		font-size: 20px;
		height: 100%;
		padding-top: 25px;
	}
	
	.showTimeTitle {
		text-align: center;
		font-size: 20px;
		height: 100%;
		padding-top: 10px;
	}
	
	.cellDataInfo {
		margin-left: 50%;
		width: 50%;
		margin-top: -55px;
	}
	
	.showInfo {
		text-align: center;
		font-size: 20px;
		height: 100%;
		padding-top: 25px;
	}
	
	.showInfoTitle {
		text-align: center;
		font-size: 20px;
		height: 100%;
		padding-top: 25px;
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
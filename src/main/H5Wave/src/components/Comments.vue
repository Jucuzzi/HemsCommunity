<template>
	<div class="demo-infinite-container">
		<detailheader></detailheader>
		<mu-list class="list-style">
			<template v-for="item in list">
				<div>
					<!--<mu-list-item :title="item"/>-->
					<div id=mainDiv>
						<img :src="item.imgPath"></img>
						<label id="name">{{item.name}}</label>
						<label id="level">{{item.level}}</label>
						<label id="time">{{item.time}}</label>
					</div>
					<mu-content-block>
						<label v-on:click="open(item)" style="font-weight: 400;">{{item.content}}</label>
					</mu-content-block>
					<div id="count">
						<div id="leftDiv">
							<label id="thumbCount">{{item.preferenceCount + "个赞"}}</label>
						</div>
						<div id="rightDiv" v-on:click="thumbsUpOrCancel(item)">
							<img id="thumb" :src="item.isThumbedImgSrc"></img>
							<label id="isthumb" style="font-weight: 400;">{{item.isThumbed == 1?'取消':'点赞'}}</label>
						</div>
					</div>
					<mu-divider/>
				</div>
			</template>
		</mu-list>
		<mu-infinite-scroll :scroller="scroller" :loading="loading" @load="loadMore" />
		<div id="bottomDiv">
			<div style="margin: 5px; margin-left: 10px;  position:fixed;">
				<mu-text-field hintText="提示文字" v-model="inputStr" v-focus="focusStatus" /><br/>
			</div>
			<div style="float: right;margin: 5px;">
				<mu-flat-button @click="reply(inputStr)" backgroundColor="#2AB4FA" color="#FFF" label="发表" class="demo-flat-button" />
			</div>

		</div>

		<mu-dialog :open="dialog" title="请选择" @close="close">
			<mu-menu>
				<mu-menu-item :title=" menu" v-for="menu, index in menus" @click="click(index)" :key="index" />
			</mu-menu>
		</mu-dialog>

		<mu-dialog :open="selfDialog" title="请选择" @close="selfClose">
			<mu-menu>
				<mu-menu-item :title="selfMenus" v-for="selfMenus, index in selfMenus" @click="selfClick(index)" :key="index" />
			</mu-menu>
		</mu-dialog>

		<mu-dialog :open="reportedDialog" title="请选择举报理由" @close="reportedClose">
			<mu-menu>
				<mu-menu-item :title="reports" v-for="reports, index in reports" @click="reportedClick(index)" :key="index" />
			</mu-menu>
		</mu-dialog>

		<mu-dialog :open="tipDialog" title="温馨提示" @close="tipClose">
			举报成功，小赫对恶意发言的行为绝不姑息！
			<mu-flat-button slot="actions" primary @click="tipClose" label="确定" />
		</mu-dialog>

	</div>
</template>

<script>
	import detailheader from '@/components/detailheader.vue'
	export default {
		data() {
			const menus = []
			const selfMenus = []
			const list = []
			const reports = []
			const listTemp = {};

			//			listTemp.name = "Alex";
			//			listTemp.level = "铂金";
			//			listTemp.content = "楼主在此";
			//			listTemp.preferenceCount = "21个赞";
			//			listTemp.isThumbed = "1";
			//			listTemp.time = "9分钟前";
			//			listTemp.imgPath = require("../telehems/img/WechatIMG135.png");
			//			if(listTemp.isThumbed == "1") {
			//				listTemp.isThumbedImgSrc = require("../telehems/img/thumbsUp.png");
			//			} else {
			//				listTemp.isThumbedImgSrc = require("../telehems/img/cancelThumbsUp.png");
			//			}
			//			list.push(listTemp);
			//			for(let i = 0; i < 5; i++) {
			//				var listMode = {};
			//				listMode.name = "Alen";
			//				listMode.level = "大众";
			//				listMode.content = "刷个十五字好混个经验啦啦啦啦啦啦啦";
			//				listMode.preferenceCount = "9个赞";
			//				listMode.isThumbed = "1";
			//				listMode.time = "9分钟前";
			//				listMode.imgPath = require("../telehems/img/WechatIMG135.png");
			//				if(listTemp.isThumbed == "1") {
			//					listMode.isThumbedImgSrc = require("../telehems/img/thumbsUp.png");
			//				} else {
			//					listMode.isThumbedImgSrc = require("../telehems/img/cancelThumbsUp.png");
			//				}
			//				list.push(listMode);
			//			}
			menus.push("回复");
			menus.push("举报");
			menus.push("取消");
			selfMenus.push("回复");
			selfMenus.push("删除");
			selfMenus.push("取消");
			reports.push("色情");
			reports.push("反动");
			reports.push("辱骂");
			reports.push("广告");
			reports.push("其他");
			return {
				list:[],
				num: 10,
				pageNum: 1,
				loading: false,
				scroller: null,
				dialog: false,
				reportedDialog: false,
				tipDialog: false,
				selfDialog: false,
				clickname: "",
				inputStr: '',
				menus,
				reports,
				selfMenus,
				selfUserId: 1,
				selectItem: [],
				focusStatus: false,
			}
		},

		mounted() {
			this.scroller = this.$el;
			this.initRefresh();
		},
		directives: {
			focus: {
				inserted: function(el, {
					value
				}) {
					if(value) {
						el.focus();
					}
				}
			}
		},
		methods: {
			loadMore() {
				//				this.loading = true
				//				setTimeout(() => {
				//					for(let i = this.num; i < this.num + 1; i++) {
				//						var listMode = {};
				//						listMode.name = "Bob";
				//						listMode.level = "大众";
				//						listMode.content = "嘻嘻嘻我就是个骗赞的";
				//						listMode.preferenceCount = "9个赞";
				//						listMode.isThumbed = "已赞";
				//						listMode.time = "9分钟前";
				//						listMode.imgPath = require("../telehems/img/WechatIMG135.png");
				//						this.list.push(listMode);
				//					}
				//					this.num += 1;
				//					this.loading = false;
				//				}, 2000)
			},
			//			点赞
			thumbsUpOrCancel(item) {
				var self = this;
				if(item.isThumbed == 1) {
					ajaxRequest({
						url: 'cancelThumbsUp',
						data: {
							"commentId": item.commentId,
							"userId": self.selfUserId
						},
						async: true,
						success: function(dataJson) {
							var result = dataJson.result;
							
							if(result == "successed") {
								item.isThumbed = 0;
								item.preferenceCount = item.preferenceCount - 1;
								item.isThumbedImgSrc = require("../telehems/img/cancelThumbsUp.png");
							}
						},
						error: function() {
							alert("系统繁忙");
						}
					});
				} else {
					ajaxRequest({
						url: 'thumbsUp',
						data: {
							"commentId": item.commentId,
							"userId": self.selfUserId
						},
						async: true,
						success: function(dataJson) {
							var result = dataJson.result;
							if(result == "successed") {
								item.isThumbed = 1;
								item.preferenceCount = item.preferenceCount + 1;
								item.isThumbedImgSrc = require("../telehems/img/thumbsUp.png");
							}
						},
						error: function() {
							alert("系统繁忙");
						}
					});
				}
			},
			//			初始化刷新数据
			initRefresh() {
				var self = this;
				ajaxRequest({
					url: 'getComments',
					data: {
						"informationId": 1,
						"pageNum": 0,
						"userId": self.selfUserId
					},
					async: true,
					success: function(dataJson) {
						var array = dataJson.result;
						self.initRefreshList(array);
					},
					error: function() {
						alert("系统繁忙");
					}
				});
			},
			// 初始化刷新评论列表
			initRefreshList(initList) {
				this.num = initList.length;
				this.list.length = 0;
				for(var i = 0; i < initList.length; i++) {
					var listMode = {};
					listMode.commentId = initList[i].commentId;
					listMode.userId = initList[i].userId;
					listMode.name = initList[i].name;
					listMode.level = "";
					listMode.content = initList[i].commentDetail;
					listMode.preferenceCount = parseInt(initList[i].preferenceCount);
					listMode.isThumbed = initList[i].isThumbed;
					listMode.time = initList[i].commentTime;
//					listMode.imgPath = require("../telehems/img/" + initList[i].imgPath);
					if(listMode.isThumbed == "1") {
						listMode.isThumbedImgSrc = require("../telehems/img/thumbsUp.png");
					} else {
						listMode.isThumbedImgSrc = require("../telehems/img/cancelThumbsUp.png");
					}
					this.list.push(listMode);
				}
			},
			// 刷新评论列表并新增
			refreshList(initList) {
				this.num = initList.length;
				for(let i = 0; i < initList.length; i++) {
					var listMode = {};
					listMode.commentId = initList[i].commentId;
					listMode.userId = initList[i].userId;
					listMode.name = initList[i].name;
					listMode.level = "";
					listMode.content = initList[i].commentDetail;
					listMode.preferenceCount = parseInt(initList[i].preferenceCount);
					listMode.isThumbed = initList[i].isThumbed;
					listMode.time = initList[i].commentTime;
					listMode.imgPath = require("../telehems/img/" + initList[i].imgPath);
					if(listMode.isThumbed == "1") {
						listMode.isThumbedImgSrc = require("../telehems/img/thumbsUp.png");
					} else {
						listMode.isThumbedImgSrc = require("../telehems/img/cancelThumbsUp.png");
					}
					this.list.push(listMode);
				}
			},
			reply(message) {
				var self = this;
				ajaxRequest({
					url: 'addComment',
					data: {
						"commentDetail": message,
						"informationId": 1,
						"userId": self.selfUserId
					},
					async: true,
					success: function(dataJson) {
						var array = dataJson.result;
						console.log(array);
						self.initRefreshList(array);
						self.inputStr = "";
					},
					error: function() {
						alert("系统繁忙");
					}
				});
			},
			open(item) {
				console.log(item.commentId);
				this.selectItem = item;
				if(this.selfUserId == this.selectItem.userId) {
					this.selfDialog = true;
				} else {
					this.dialog = true
				}
			},
			close() {
				this.dialog = false
			},
			click(index) {
				if(index == 0) {
					this.focusStatus = true;
					this.inputStr = "@" + this.selectItem.name + " 回复：";
				} else if(index == 1) {
					this.reportedDialog = true;
				}
				this.dialog = false;
			},
			selfClose() {
				this.selfDialog = false;
			},
			selfClick(index) {
				if(index == 0) {
					this.focusStatus = true;
					this.inputStr = "@" + this.selectItem.name + " 回复：";
				} else if(index == 1) {
					this.deleteComment(this.selectItem.commentId);
				}
				this.selfDialog = false;
			},
			reportedOpen() {
				this.reportedDialog = true
			},
			reportedClose() {
				this.reportedDialog = false
			},
			//			举报
			reportedClick(index) {
				var self = this;
				self.reportedDialog = false;
				ajaxRequest({
					url: 'reported',
					data: {
						"reportType": index,
						"contenttype": 1,
						"commentId": self.selectItem.commentId,
						"userId": self.selectItem.userId
					},
					async: true,
					success: function(dataJson) {
						var result = dataJson.result;
						console.log(result);
						if(result == "successed") {
							self.tipDialog = true;
						}
					},
					error: function() {
						alert("系统繁忙");
					}
				});
			},
			tipOpen() {
				this.tipDialog = true;
			},
			tipClose() {
				this.tipDialog = false;
			},
			//			删除评论
			deleteComment(commentId) {
				var self = this;
				ajaxRequest({
					url: 'deleteComment',
					data: {
						"commentId": commentId,
					},
					async: true,
					success: function(dataJson) {
						var result = dataJson.result;
						console.log(result);
						if(result == "successed") {
							self.list = self.list.filter(t => t.commentId != self.selectItem.commentId);
						}
					},
					error: function() {
						alert("系统繁忙");
					}
				});
			},
			submitForm(formName) {
				const self = this;
				self.$refs[formName].validate((valid) => {
					if(valid) {
						alert(self.ruleForm.username);
						axios.get('http://localhost:8080/HemsCommunity/showView', {
							name: self.ruleForm.username,
							password: self.ruleForm.username
						}).then(response => {
							console.log(response)
						}).catch(error => {
							console.log(error)
						});
						localStorage.setItem('ms_username', self.ruleForm.username);
						self.$router.push('/readme');
					} else {
						console.log('error submit!!');
						return false;
					}
				});
			}
		},
		components: {
			detailheader
		}
	}
</script>

<style lang="css" scoped="yes">
	.list-style {
		margin-bottom: 32px;
	}
	
	.demo-infinite-container {
		width: 100%;
		height: 100%;
		overflow: auto;
		-webkit-overflow-scrolling: touch;
		border: 1px solid #d9d9d9;
	}
	
	div img {
		width: 40px;
		height: 40px;
		margin: 10px;
		margin-bottom: 0px;
		position: relative;
	}
	
	#thumb {
		width: 17px;
		height: 17px;
		top: 0px;
		margin: 0px;
		margin-right: 10px;
	}
	
	#isthumb {
		height: 17px;
		top: 0px;
		margin: 0px;
		margin-right: 10px;
	}
	
	#thumbCount {
		position: relative;
		display: flex;
		text-align: left;
		justify-content: flex-start;
		bottom: 0px;
		left: 10px;
		margin: 0px;
		margin-left: 10px;
		font-size: 12px;
		font-weight: 400;
		color: black;
	}
	
	#name {
		position: absolute;
		margin-left: 0px;
		margin-top: 10px;
		font-size: 15px;
		font-weight: 400;
		color: cornflowerblue;
	}
	
	#level {
		/*background-color: #FBC653;
		padding-left: 5px;
		padding-right: 5px;
		font-size: 11px;
		color: white;*/
	}
	
	#time {
		position: absolute;
		text-align: right;
		right: 10px;
		margin-right: 10px;
		margin-top: 10px;
		font-size: 12px;
		color: black;
		font-weight: 400;
	}
	
	#content {
		position: relative;
		line-break: strict;
		text-align: left;
		margin-left: 20px;
		margin-right: 20px;
		margin-top: 5px;
		font-size: 12px;
		/*line-height: 17px;*/
		color: black;
		
	}
	
	#count {
		position: relative;
		text-align: right;
		display: flex;
		flex-direction: row;
		bottom: 0px;
		right: 0px;
		margin: 0px;
		margin: 10px;
		font-size: 12px;
		color: black;
	}
	
	#leftDiv {
		position: relative;
		text-align: right;
		align-items: flex-start;
		flex-direction: row;
		display: flex;
		width: 50%;
		bottom: 0px;
		right: 10px;
		margin: 0px;
		font-size: 12px;
		color: black;
	}
	
	#rightDiv {
		position: relative;
		align-items: flex-end;
		justify-content: flex-end;
		flex-direction: row;
		text-align: right;
		display: flex;
		width: 50%;
		bottom: 0px;
		right: 0px;
		margin: 0px;
		font-size: 12px;
		color: black;
	}
	
	#bottomDiv {
		background-color: white;
		width: 100%;
		border-top: 1px solid lightgray;
		/*border-width: 2px;*/
		/*border-color: lightgray;*/
		height: 50px;
		position: fixed;
		bottom: 0px;
	}
</style>
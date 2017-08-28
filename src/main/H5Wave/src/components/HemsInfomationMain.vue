<template>

	<div style="height: 100%;">

		<div class="top-background">

			<div class="top-layout-left-right">

				<div class="button-left-right" @click="goEnergy">

					<img id="icon_image" src="../telehems/img/10.png" width=25px height="29px" />

					<div class="text-type-one" >谷电能量</div>

				</div>

			</div>

			<div class="top-layout-left-right">

				<div class="button-left-right" @click="goChoujiang">

					<img id="icon_image" src="../telehems/img/9.png" width=25px height="29px" />

					<div class="text-type-two">积分商城</div>

				</div>

			</div>

		</div>

		<div class="mui-content" style="height: 70%">
			<div id="slider" class="mui-slider" style="height: 100%">
				<mu-tabs :value="activeTab" @change="handleTabChange" style="height: 8%;">
					<mu-tab value="tab1" title="官方" />
					<mu-tab value="tab2" title="精品" />
					<mu-tab value="tab3" title="教程" />
					<mu-tab value="tab4" title="娱乐" />
					<mu-tab value="tab5" title="收藏" />
				</mu-tabs>
				<div style="height: 92%;overflow:auto;">
					<div v-if="activeTab === 'tab1'">
						<div class="demo-infinite-container">
							<mu-list>
								<template v-for="item in informationDataArr">
									<!--<mu-list-item :title="item.entityJSONObject.title" />
									<mu-divider/>-->
									<mu-paper class="demo-paper" :zDepth="1">
										<div class="div1" @click="itemJump">
											<div class="div2">
												<img id="icon_image" src="../telehems/img/temp.png" height="75px" width="75px" />
											</div>
											<div class="div3">
												<p class="title-font">{{item.entityJSONObject.title}}</p>
											</div>
											<div class="div4">
												<p class="content-font">{{item.entityJSONObject.briefIntroduction}}</p>
											</div>
											<div class="div3">
												<p class="bottom-font-one">{{item.entityJSONObject.releaseTime}}</p>
												<p class="bottom-font-two"> 18 收藏</p>
											</div>
										</div>
									</mu-paper>
								</template>
							</mu-list>
							<mu-infinite-scroll :scroller="scroller" :loading="loading" @load="loadMore" />
						</div>
					</div>
					<div v-if="activeTab === 'tab2'" class="demo-infinite-container">
						<div class="demo-infinite-container">
							<mu-list>
								<template v-for="item in informationDataArr">
									<!--<mu-list-item :title="item.entityJSONObject.title" />
									<mu-divider/>-->
									<mu-paper class="demo-paper" :zDepth="1">
										<div class="div1" @click="itemJump">
											<div class="div2">
												<img id="icon_image" src="../telehems/img/temp.png" height="75px" width="75px" />
											</div>
											<div class="div3">
												<p class="title-font">{{item.entityJSONObject.title}}</p>
											</div>
											<div class="div4">
												<p class="content-font">{{item.entityJSONObject.briefIntroduction}}</p>
											</div>
											<div class="div3">
												<p class="bottom-font-one">{{item.entityJSONObject.releaseTime}}</p>
												<p class="bottom-font-two"> 18 收藏</p>
											</div>
										</div>
									</mu-paper>
								</template>
							</mu-list>
							<mu-infinite-scroll :scroller="scroller" :loading="loading" @load="loadMore" />
						</div>
					</div>
					<div v-if="activeTab === 'tab3'">
						<div class="demo-infinite-container">
							<mu-list>
								<template v-for="item in informationDataArr">
									<!--<mu-list-item :title="item.entityJSONObject.title" />
									<mu-divider/>-->
									<mu-paper class="demo-paper" :zDepth="1">
										<div class="div1" @click="itemJump">
											<div class="div2">
												<img id="icon_image" src="../telehems/img/temp.png" height="75px" width="75px" />
											</div>
											<div class="div3">
												<p class="title-font">{{item.entityJSONObject.title}}</p>
											</div>
											<div class="div4">
												<p class="content-font">{{item.entityJSONObject.briefIntroduction}}</p>
											</div>
											<div class="div3">
												<p class="bottom-font-one">{{item.entityJSONObject.releaseTime}}</p>
												<p class="bottom-font-two"> 18 收藏</p>
											</div>
										</div>
									</mu-paper>
								</template>
							</mu-list>
							<mu-infinite-scroll :scroller="scroller" :loading="loading" @load="loadMore" />
						</div>
					</div>
					<div v-if="activeTab === 'tab4'">
						<div class="demo-infinite-container">
							<mu-list>
								<template v-for="item in informationDataArr">
									<!--<mu-list-item :title="item.entityJSONObject.title" />
									<mu-divider/>-->
									<mu-paper class="demo-paper" :zDepth="1">
										<div class="div1" @click="itemJump">
											<div class="div2">
												<img id="icon_image" src="../telehems/img/temp.png" height="75px" width="75px" />
											</div>
											<div class="div3">
												<p class="title-font">{{item.entityJSONObject.title}}</p>
											</div>
											<div class="div4">
												<p class="content-font">{{item.entityJSONObject.briefIntroduction}}</p>
											</div>
											<div class="div3">
												<p class="bottom-font-one">{{item.entityJSONObject.releaseTime}}</p>
												<p class="bottom-font-two"> 18 收藏</p>
											</div>
										</div>
									</mu-paper>
								</template>
							</mu-list>
							<mu-infinite-scroll :scroller="scroller" :loading="loading" @load="loadMore" />
						</div>
					</div>
					<div v-if="activeTab === 'tab5'">
						<div class="demo-infinite-container">
							<mu-list>
								<template v-for="item in informationDataArr">
									<!--<mu-list-item :title="item.entityJSONObject.title" />
									<mu-divider/>-->
									<mu-paper class="demo-paper" :zDepth="1">
										<div class="div1" @click="itemJump">
											<div class="div2">
												<img id="icon_image" src="../telehems/img/temp.png" height="75px" width="75px" />
											</div>
											<div class="div3">
												<p class="title-font">{{item.entityJSONObject.title}}</p>
											</div>
											<div class="div4">
												<p class="content-font">{{item.entityJSONObject.briefIntroduction}}</p>
											</div>
											<div class="div3">
												<p class="bottom-font-one">{{item.entityJSONObject.releaseTime}}</p>
												<p class="bottom-font-two"> 18 收藏</p>
											</div>
										</div>
									</mu-paper>
								</template>
							</mu-list>
							<mu-infinite-scroll :scroller="scroller" :loading="loading" @load="loadMore" />
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	</div>

</template>

<script>
	export default {
		data() {
			return {
				//高亮显示的标签
				activeTab: 'tab1',
				num: 10,
				loading: false,
				scroller: null,
				full: false,
				loadingText: "正在加载...",
				informationDataArr: [],
				count: 1,
			}
		},
		methods: {
			loadMore() {
				if(!this.full) {
					var self = this;
					this.loading = true;
					setTimeout(() => {
						ajaxRequest({
							url: 'queryinformation',
							data: {
								"pageNumber": self.count,
								"userId": self.selfUserId,
								"condition": ""
							},
							async: false,
							success: function(dataJson) {
								for(var i = 0; i < dataJson.infoList.length; i++) {
									self.informationDataArr.push(dataJson.infoList[i]);
								}
								//											alert("总共数据的长度是" + self.informationDataArr.length);
								self.count++;
								self.loading = false;
								if(dataJson.infoList.length < 10) {
									self.full = true;
								}

							},
							error: function() {
								alert("系统繁忙");
							}
						});
					}, 2000)
				} else {
					this.loadingText = "没有更多";
				}

			},
			handleTabChange(val) {
				this.activeTab = val
			},
			itemJump: function() {
				this.$router.push({
					path: '/monthreportdetail'
				});
			},
			goChoujiang: function() {
				this.$router.push({
					path: '/sign'
				});
			},
			goEnergy: function() {
				this.$router.push({
					path: '/mainView'
				});
			},
			getData() {
				var self = this;

				ajaxRequest({
					url: 'queryinformation',
					data: {
						"pageNumber": self.count,
						"userId": self.selfUserId,
						"condition": ""
					},
					async: false,
					success: function(dataJson) {
						//						alert(JSON.stringify(dataJson.infoList));
						//	                    self.informationDataArr = dataJson.infoList;
						for(var i = 0; i < dataJson.infoList.length; i++) {
							self.informationDataArr.push(dataJson.infoList[i]);
						}
						//						alert("总共数据的长度是" + self.informationDataArr.length);
						//						alert(self.count);
						//						alert(JSON.stringify(self.informationDataArr));
						self.count++;
						//						var array = dataJson.result;
						//						console.log(array);
						//						self.initRefreshList(array);
					},
					error: function() {
						alert("系统繁忙");
					}
				});
			}
		},
		mounted() {
			this.scroller = this.$el;
			var self = this;
			this.getData();
//			mui.init({
//				swipeBack: false
//			});
			(function($) {
				//				$('.mui-scroll-wrapper').scroll({
				//					indicators: true //是否显示滚动条
				//				});
				var html2 = '<ul class="mui-table-view"><li class="item-content"><div class="div1" @click="itemJump"><div class="div2"><img id="icon_image" src="' + require('../telehems/img/temp.png') + '" height="75px" width="75px"/></div><div class="div3"><p class="title-font">一种基于Agent的智能电网优化</p></div><div class="div4"><p class="content-font">智能电网集成优化控制技术是实现各环协调运行的关键，智能代理技术为解决这一问题提供了良好的方法aaaaaaaaaa</p></div><div class="div3"><p class="bottom-font-one">08-18 09:29</p><p class="bottom-font-two"> 18 收藏</p></div></div></li></ul>';
				var html3 = '<ul class="mui-table-view"><li class="item-content"><div class="div1" @click="itemJump"><div class="div2"><img id="icon_image" src="' + require('../telehems/img/temp.png') + '" height="75px" width="75px"/></div><div class="div3"><p class="title-font">一种基于Agent的智能电网优化</p></div><div class="div4"><p class="content-font">智能电网集成优化控制技术是实现各环协调运行的关键，智能代理技术为解决这一问题提供了良好的方法aaaaaaaaaa</p></div><div class="div3"><p class="bottom-font-one">08-18 09:29</p><p class="bottom-font-two"> 18 收藏</p></div></div></li></ul>';
				var html4 = '<ul class="mui-table-view"><li class="item-content"><div class="div1" @click="itemJump"><div class="div2"><img id="icon_image" src="' + require('../telehems/img/temp.png') + '" height="75px" width="75px"/></div><div class="div3"><p class="title-font">一种基于Agent的智能电网优化</p></div><div class="div4"><p class="content-font">智能电网集成优化控制技术是实现各环协调运行的关键，智能代理技术为解决这一问题提供了良好的方法aaaaaaaaaa</p></div><div class="div3"><p class="bottom-font-one">08-18 09:29</p><p class="bottom-font-two"> 18 收藏</p></div></div></li></ul>';
				var html5 = '<ul class="mui-table-view"><li class="item-content"><div class="div1" @click="itemJump"><div class="div2"><img id="icon_image" src="' + require('../telehems/img/temp.png') + '" height="75px" width="75px"/></div><div class="div3"><p class="title-font">一种基于Agent的智能电网优化</p></div><div class="div4"><p class="content-font">智能电网集成优化控制技术是实现各环协调运行的关键，智能代理技术为解决这一问题提供了良好的方法aaaaaaaaaa</p></div><div class="div3"><p class="bottom-font-one">08-18 09:29</p><p class="bottom-font-two"> 18 收藏</p></div></div></li></ul>';
				var item2 = document.getElementById('item2mobile');
				var item3 = document.getElementById('item3mobile');
				var item4 = document.getElementById('item4mobile');
				var item5 = document.getElementById('item5mobile');
				document.getElementById('slider').addEventListener('slide', function(e) {
					if(e.detail.slideNumber === 1) {
						if(item2.querySelector('.mui-loading')) {
							setTimeout(function() {
								item2.querySelector('.mui-scroll').innerHTML = html2;
							}, 500);
						}
					} else if(e.detail.slideNumber === 2) {
						if(item3.querySelector('.mui-loading')) {
							setTimeout(function() {
								item3.querySelector('.mui-scroll').innerHTML = html3;
							}, 500);
						}
					} else if(e.detail.slideNumber === 3) {
						if(item4.querySelector('.mui-loading')) {
							setTimeout(function() {
								item4.querySelector('.mui-scroll').innerHTML = html4;
							}, 500);
						}
					} else if(e.detail.slideNumber === 4) {
						if(item5.querySelector('.mui-loading')) {
							setTimeout(function() {
								item5.querySelector('.mui-scroll').innerHTML = html5;
							}, 500);
						}
					}
				});
				var sliderSegmentedControl = document.getElementById('sliderSegmentedControl');
				$('.mui-input-group').on('change', 'input', function() {
					if(this.checked) {
						sliderSegmentedControl.className = 'mui-slider-indicator mui-segmented-control mui-segmented-control-inverted mui-segmented-control-' + this.value;
						//force repaint
						sliderProgressBar.setAttribute('style', sliderProgressBar.getAttribute('style'));
					}
				});

				//阻尼系数
				var deceleration = mui.os.ios ? 0.003 : 0.0009;
				$('.mui-scroll-wrapper').scroll({
					bounce: false,
					indicators: false, //是否显示滚动条
					deceleration: deceleration
				});
				$.ready(function() {
					//循环初始化所有下拉刷新，上拉加载。
					$.each(document.querySelectorAll('.mui-slider-group .mui-scroll'), function(index, pullRefreshEl) {
						$(pullRefreshEl).pullToRefresh({
							down: {
								callback: function() {
									var self = this;
									setTimeout(function() {
										var ul = self.element.querySelector('.mui-table-view');
										ul.insertBefore(createFragment(ul, index, 1, true), ul.firstChild);
										self.endPullDownToRefresh();
									}, 1000);
								}
							},
							up: {
								callback: function() {
									//									self.getData();
									var self2 = this;
									ajaxRequest({
										url: 'queryinformation',
										data: {
											"pageNumber": self.count,
											"userId": self.selfUserId,
											"condition": ""
										},
										async: true,
										success: function(dataJson) {
											for(var i = 0; i < dataJson.infoList.length; i++) {
												self.informationDataArr.push(dataJson.infoList[i]);
											}
											//											alert("总共数据的长度是" + self.informationDataArr.length);
											self.count++;
											//向列表中一条一条地添加数据
											var length = ul.querySelectorAll('li').length;
											var fragment = document.createDocumentFragment();
											var li;
											for(var i = 0; i < dataJson.infoList.length; i++) {
												var li = document.createElement('li');
												li.className = 'item-content';
												li.innerHTML = '<div class="div1" @click="itemJump"><div class="div2"><img id="icon_image" src="' + require('../telehems/img/temp.png') + '" height="75px" width="75px"/></div><div class="div3"><p class="title-font">' + dataJson.infoList[i].title + '</p></div><div class="div4"><p class="content-font">+dataJson.infoList[i].briefIntroduction+</p></div><div class="div3"><p class="bottom-font-one">+dataJson.infoList[i].releaseTime+</p><p class="bottom-font-two"> 18 收藏</p></div></div>';
												li.addEventListener('click', function(e) {
													this.$router.push({
														path: '/monthreportdetail'
													});
												})
												fragment.appendChild(li);
											}
											var ul = self.element.querySelector('.mui-table-view');
											ul.appendChild(fragment);
											self.endPullUpToRefresh();

										},
										error: function() {
											alert("系统繁忙");
										}
									});
								}
							}
						});
					});
					var createFragment = function(ul, index, count, reverse) {
						var length = ul.querySelectorAll('li').length;
						var fragment = document.createDocumentFragment();
						var li;
						for(var i = 0; i < count; i++) {
							var li = document.createElement('li');
							li.className = 'item-content';
							li.innerHTML = '<div class="div1" @click="itemJump"><div class="div2"><img id="icon_image" src="' + require('../telehems/img/temp.png') + '" height="75px" width="75px"/></div><div class="div3"><p class="title-font">一种基于Agent的智能电网优化</p></div><div class="div4"><p class="content-font">智能电网集成优化控制技术是实现各环协调运行的关键，智能代理技术为解决这一问题提供了良好的方法aaaaaaaaaa</p></div><div class="div3"><p class="bottom-font-one">08-18 09:29</p><p class="bottom-font-two"> 18 收藏</p></div></div>';
							li.addEventListener('click', function(e) {
								this.$router.push({
									path: '/monthreportdetail'
								});
							})
							fragment.appendChild(li);
						}
						return fragment;
					};
				});

			})(mui);

			var swiper = new Swiper('.swiper-container', {
				pagination: '.swiper-pagination',
				paginationClickable: true
			});

		}
	};
</script>

<style scoped="yes">
	.mui-bar~.mui-content .mui-fullscreen {
		top: 44px;
		height: auto;
	}
	
	.mui-pull-top-tips {
		position: absolute;
		top: -20px;
		left: 50%;
		margin-left: -25px;
		width: 40px;
		height: 40px;
		border-radius: 100%;
		z-index: 1;
	}
	
	.mui-bar~.mui-pull-top-tips {
		top: 24px;
	}
	
	.mui-pull-top-wrapper {
		width: 42px;
		height: 42px;
		display: block;
		text-align: center;
		background-color: #efeff4;
		border: 1px solid #ddd;
		border-radius: 25px;
		background-clip: padding-box;
		box-shadow: 0 4px 10px #bbb;
		overflow: hidden;
	}
	
	.mui-pull-top-tips.mui-transitioning {
		-webkit-transition-duration: 200ms;
		transition-duration: 200ms;
	}
	
	.mui-pull-top-tips .mui-pull-loading {
		/*-webkit-backface-visibility: hidden;
		-webkit-transition-duration: 400ms;
		transition-duration: 400ms;*/
		margin: 0;
	}
	
	.mui-pull-top-wrapper .mui-icon,
	.mui-pull-top-wrapper .mui-spinner {
		margin-top: 7px;
	}
	
	.mui-pull-top-wrapper .mui-icon.mui-reverse {
		/*-webkit-transform: rotate(180deg) translateZ(0);*/
	}
	
	.mui-pull-bottom-tips {
		text-align: center;
		background-color: #efeff4;
		font-size: 15px;
		line-height: 40px;
		color: #777;
	}
	
	.mui-pull-top-canvas {
		overflow: hidden;
		background-color: #fafafa;
		border-radius: 40px;
		box-shadow: 0 4px 10px #bbb;
		width: 40px;
		height: 40px;
		margin: 0 auto;
	}
	
	.mui-pull-top-canvas canvas {
		width: 40px;
	}
	
	.mui-slider-indicator.mui-segmented-control {
		background-color: #efeff4;
	}
	/*上面部分为下拉组件的样式*/
	
	.grey-background {
		padding: 0px;
		margin: 0px;
		background-color: #efeff4;
	}
	
	.myself-col-xs-5 {
		width: 20%
	}
	/*列表文字标题的样式*/
	
	.title-font {
		color: #000;
		font-size: 14px;
		overflow: hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-line-clamp: 1;
		-webkit-box-orient: vertical
	}
	/*列表内容的样式*/
	
	.content-font {
		color: #8a8a8a;
		font-size: 12px;
		letter-spacing: 0px;
		line-height: 15px;
		margin-bottom: 3px;
		margin-top: 3px;
		overflow: hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-line-clamp: 2;
		-webkit-box-orient: vertical
	}
	/*列表底部元素的样式一*/
	
	.bottom-font-one {
		float: left;
		color: #8a8a8a;
		font-size: 12px;
		text-indent: 10px;
		letter-spacing: 0px;
		line-height: 14.5px;
		padding-bottom: 3px;
		padding-top: 3px;
	}
	/*列表底部元素的样式二*/
	
	.bottom-font-two {
		color: #00abfd;
		font-size: 12px;
		text-indent: 10px;
		letter-spacing: 0px;
		line-height: 14.5px;
		padding-bottom: 3px;
		padding-top: 3px;
	}
	
	.item-content {
		background-color: #efeff4;
		position: relative;
		overflow: hidden;
	}
	
	.div1 {
		/*border:1px red solid;*/
		overflow: hidden;
		zoom: 1;
		height: 85px;
		background-color: #ffffff;
		margin: 5px;
		padding: 5px;
	}
	
	.div2 {
		width: 75px;
		height: 75px;
		/*border:1px blue solid;*/
		float: left;
	}
	
	.div3 {
		height: 20px;
		/*border:1px green solid;*/
	}
	
	.div4 {
		height: 35px;
		/*border:1px green solid;*/
	}
	
	.div3,
	.div4 {
		margin-left: 85px;
	}
	
	.mui-control-content {
		background-color: #efeff4;
		min-height: 395px;
		max-height: 395px;
		height: 100%;
	}
	
	.mui-control-content .mui-loading {
		margin-top: 50px;
	}
	
	.top-background {
		display: -webkit-flex;
		display: flex;
		justify-content: center;
		background-image: url(../telehems/img/8.png);
		background-size: 100% 100%;
		width: 100%;
		height: 30%;
	}
	
	.top-layout-left-right {
		display: -webkit-flex;
		display: flex;
		width: 50%;
		justify-content: center;
		align-items: center;
	}
	
	.button-left-right {
		display: -webkit-flex;
		display: flex;
		background-image: url(../telehems/img/11.png);
		background-size: 100% 100%;
		width: 150px;
		height: 50px;
		margin-top: 55%;
		justify-content: center;
		align-items: center;
	}
	
	.text-type-one {
		color: #36ae3e;
		margin-left: 10px;
	}
	
	.text-type-two {
		color: #cd4a4a;
		margin-left: 10px;
	}
	
	.demo-infinite-container {
		/*position: absolute;*/
		/*display: absolute;*/
		width: 100%;
		/*height:50%;*/
		overflow: auto;
		-webkit-overflow-scrolling: touch;
		/*border: 2px solid #d9d9d9;*/
	}
	
	.demo-paper {
		display: inline-block;
		height: auto;
		width: 95%;
		margin-top: 1%;
		margin-left: 2.5%;
		margin-right: 2.5%;
		margin-bottom: 1%;
		/*text-align: center;*/
	}
</style>
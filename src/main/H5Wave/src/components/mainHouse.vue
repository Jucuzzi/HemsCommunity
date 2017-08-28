<template>
	<div style="height: 100%;">
		<div class="sign-titleBar">
			<div class="leftDiv" v-on:click="gotoSign">
				<img class="leftImg" src="../assets/images/title_arrow_left_white.png" />
			</div>
		</div>
		<div class="backgroundTitleView">

			<div id="windiow">

				<canvas id="myCanvas" width="300px" height="150px" style="background-color: rgba(1,1,1,0);"></canvas>

			</div>
			<div class="strategy">
				<img src="../../static/img/strategy.png" id="strategyButton" v-on:click="raiders" />
			</div>
			<div class="redMoney" data-target="#apply2" data-toggle="modal">
				<img src="../../static/img/redpacketl.png" id="redPacketButton" v-on:click="redPackage" />
			</div>
			<div class="userInfoTitle">
				<img src="../../static/img/titleInfoImg.png" id="userInfoImg" v-on:click="myEnergyJump" />
				<div class="userShow">
					<img src="../../static/img/userImg.jpg" id="userShowImg" v-on:click="myEnergyJump" />
				</div>
				<div class="infoName" v-on:click="myEnergyJump">
					谷电能量
				</div>
				<div class="infoDetail" v-on:click="myEnergyJump">
					{{mainHouseData.AllValleyEnergy}}g
				</div>
			</div>

			<!--下面是红包弹框-->
			<!--<div id="apply2" class="modal fade padding1" style="text-align: center;" tabindex="-1" data-replace="true" data-backdrop="static">-->
			<!--<div class="modal-dialog modal-content">-->
			<!--<div class="modal-body" style="position: relative;">-->
			<!--<div class="img1"><img src="../assets/images/红包.png" /></div>-->
			<!--<div style="position: absolute; width: 100%; top: 20%;">-->
			<!--<h4>恭喜您可以获取</h4>-->
			<!--<h4>以下奖励中的一个</h4></br>-->
			<!--（只能二选一哦）-->
			<!--</div>-->
			<!--<p><span>恭喜你获得95折券</span></p>-->
			<!--<div style="">-->
			<!--<div style="width: 45%;float: left;margin-left: 10px; position: relative;">-->
			<!--<img src="../assets/images/兑换钱.png" v-on:click="chosenMoney" />-->
			<!--<img src="../assets/images/select_icon.png" style="position: absolute; right: 15px ;top: 15px; width: 15%;"id="chosenMoney" hidden="yes"/>-->
			<!--</div>-->
			<!--<div style="width: 45%;float:right;margin-right: 10px; position:  relative;">-->
			<!--<img src="../assets/images/兑换积分.png" v-on:click="chosenPoint" />-->
			<!--<img src="../assets/images/select_icon.png" style="position: absolute; right: 15px ;top: 15px; width: 15%;"id="chosenPoint" hidden="yes"/>-->
			<!--</div>-->

			<!--</div>-->

			<!--卡密为：sadasdasdadasd <br /> （长按可复制，可点右上角查询中奖记录）
					<br />-->
			<!--<a href="#" class="colse1" data-dismiss="modal"></a>-->
			<!--<button type="button" class="but7" data-dismiss="modal" data-toggle="modal">收入囊中</button>-->
			<!--<button type="button" class="but6" data-dismiss="modal" data-toggle="modal">立即领取</button>-->
			<!--</div>-->
			<!--</div>-->
			<!--</div>-->

		</div>

	</div>
</template>

<script>
	export default {
		data() {
			return {
				chosen: 0,
			}
		},
		props: ['mainHouseData'],
		updated() {
			this.refresh();
		},
		mounted() {
			
		},
		methods: {
			myEnergyJump: function() {
//				alert(this.mainHouseData.isMain);
				if(this.mainHouseData.isMain == 'main') {
					this.$router.push({
						path: '/rankList'
					});
				}
			},
			gotoSign: function() {
				this.$router.push({
					path: '/'
				})
			},
			redPackage: function() {
				alert("弹个红包");
			},
			raiders: function() {
				this.$router.push({
					path: '/raiders'
				});
			},
			click: function() {
				$('#apply2').modal('show');
			},
			chosenMoney: function() {
				$('#chosenPoint').hide();
				$('#chosenMoney').show();
				this.chosen = 1;
			},
			chosenPoint: function() {
				$('#chosenPoint').show();
				$('#chosenMoney').hide();
				this.chosen = 2;
			},
			refresh() {
				var self = this;
				if(this.mainHouseData.hasRedPacket == '1') {
					document.getElementById('redPacketButton').style.visibility = "show";
				} else {
					document.getElementById('redPacketButton').style.visibility = "hidden";
				}
				this.chosen = 0;
				switch(this.chosen) {
					case 0:
						$('#chosenPoint').hide();
						$('#chosenMoney').hide();
						break;
					case 1:
						$('#chosenPoint').hide();
						$('#chosenMoney').show();
						break;
					case 2:
						$('#chosenPoint').show();
						$('#chosenMoney').hide();
						break;
					default:
						break;
				}

				//			var c=document.getElementById("myCanvas");
				//			var cxt=c.getContext("2d");
				//			cxt.fillStyle="#FF0000";
				//			cxt.fillRect(0,0,150,75);

				var canvas = document.getElementById('myCanvas');
				var ctx = canvas.getContext('2d');
				var then = 0;
				//下面这两句话一写，画布就画不出图片了！！！！！！！！！！
				//			ctx.lineWidth = .3;
				//			ctx.strokeStyle = (new Color(150)).style;

				var mousePosition = {
					x: canvas.width - 100,
					y: canvas.height - 60
				};
				var dots = {
					nb: self.mainHouseData.generatedEnergy.length,
					distance: 100,
					d_radius: 150,
					radius: 15,
					speed: 0.1,
					array: []
				};

				function colorValue(min) {
					return Math.floor(Math.random() * 255 + min);
				}

				function createColorStyle(r, g, b) {
					return 'rgba(' + r + ',' + g + ',' + b + ', a)';
				}

				function mixComponents(comp1, weight1, comp2, weight2) {
					return(comp1 * weight1 + comp2 * weight2) / (weight1 + weight2);
				}

				function averageColorStyles(dot1, dot2) {
					var color1 = dot1.color,
						color2 = dot2.color;

					var r = mixComponents(color1.r, dot1.radius, color2.r, dot2.radius),
						g = mixComponents(color1.g, dot1.radius, color2.g, dot2.radius),
						b = mixComponents(color1.b, dot1.radius, color2.b, dot2.radius);
					return createColorStyle(Math.floor(r), Math.floor(g), Math.floor(b));
				}

				function Alpha(a) {
					return 'rgba(' + 147 + ',' + 206 + ',' + 245 + ', ' + a + ')';
				}

				function AlphaColor(a) {
					this.r = 147;
					this.g = 206;
					this.b = 245;
					this.style = Alpha(a);
				}

				function Color(min) {
					min = min || 0;
					//				this.r = colorValue(min);
					//				this.g = colorValue(min);
					//				this.b = colorValue(min);
					this.r = 147;
					this.g = 206;
					this.b = 245;
					this.style = createColorStyle(this.r, this.g, this.b);
				}

				function Dot(index) {
					this.x = (canvas.width - dots.radius * 2.4) / (dots.nb - 1) * index + dots.radius * 1.2;
					this.y = (Math.random() * (canvas.height - dots.radius * 3.5)) + dots.radius * 1.2;
					// 初始时的位置
					this.firstX = this.x;
					this.firstY = this.y;
					// 图标
					var i = self.mainHouseData.generatedEnergy[index].type;
					var img1 = new Image();
					if(i == 0) {
						img1.src = require('../../static/img/air.png');
					} else if(i == 1) {
						img1.src = require('../../static/img/waterHeater.png');
					} else if(i == 2) {
						img1.src = require('../../static/img/car.png');
					} else if(i == 3) {
						img1.src = require('../../static/img/plug.png');
					}

					this.img = img1;
					// 透明度
					this.alpha = 1;
					// 透明度衰减
					this.alphaReduce = 0;
					//				this.vx = -.5 + Math.random();
					//				this.vy = -.5 + Math.random();
					this.vx = 0;
					this.vy = dots.speed;

					this.radius = dots.radius;

					this.color = new AlphaColor(this.alpha);
					this.energy = self.mainHouseData.generatedEnergy[index].ValleyEnergy;
					this.id = self.mainHouseData.generatedEnergy[index].generatedId;
				}

				//			function judgePosition(x,y){
				//				var okOrNot = true;
				//				for(var i=0; i<dots.array.length; i++) {
				//					var temp = dots.array[i];
				//					if ((x*x+y*y)-(temp.x*temp.x+temp.y*temp.y) <= (dots.radius*dots.radius*1.5)) {
				//						okOrNot = false;
				//					}
				//				}
				//				return okOrNot;
				//			}

				Dot.prototype = {
					draw: function() {
						ctx.beginPath();
						if(this.alpha > 0 && this.alphaReduce == 0.1) {
							this.alpha = this.alpha - this.alphaReduce;
						}
						this.color = new AlphaColor(this.alpha);
						ctx.fillStyle = this.color.style;
						ctx.arc(this.x, this.y, this.radius, 0, Math.PI * 2, false);
						ctx.fill();
						//					var img = new Image();
						//					img.onload = function(){
						//						ctx.drawImage(img,0,0,30,30);
						//					};
						//					img.src=this.imgSrc;
						if(this.alpha > 0) {
							ctx.drawImage(this.img, (this.x - dots.radius), (this.y - dots.radius));
						}
						ctx.font = "12px";
						//设置字体填充颜色
						ctx.fillStyle = 'rgba(0, 0, 0,' + this.alpha + ')';;
						//从坐标点(50,50)开始绘制文字
						ctx.fillText("" + this.energy + "g", (this.x - dots.radius * 0.6), (this.y + 2 * dots.radius));

					}
				};

				function createDots() {
					for(var i = 0; i < dots.nb; i++) {
//						alert("我进来了");
						dots.array.push(new Dot(i));
//						alert(JSON.stringify(dots.array));s
					}
				}
				

				function moveDots() {
					for(var i = 0; i < dots.nb; i++) {

						var dot = dots.array[i];

						if((dot.y - dots.radius) < (dot.firstY - dots.radius * 1.2) ||
							(dot.y + dots.radius) > (dot.firstY + dots.radius * 1.2)) {
							dot.vy = -dot.vy;
						}
						//					else if((dot.x-dots.radius) < 0 || (dot.x+dots.radius) > canvas.width){
						//						dot.vx = - dot.vx;
						//						dot.vy = dot.vy;
						//					}
						dot.x += dot.vx;
						dot.y += dot.vy;
					}
				}

				function connectDots() {
					for(var i = 0; i < dots.nb; i++) {
						for(var j = 0; j < dots.nb; j++) {
							var i_dot = dots.array[i];
							var j_dot = dots.array[j];

							if((i_dot.x - j_dot.x) < dots.distance && (i_dot.y - j_dot.y) < dots.distance && (i_dot.x - j_dot.x) > -dots.distance && (i_dot.y - j_dot.y) > -dots.distance) {
								if((i_dot.x - mousePosition.x) < dots.d_radius && (i_dot.y - mousePosition.y) < dots.d_radius && (i_dot.x - mousePosition.x) > -dots.d_radius && (i_dot.y - mousePosition.y) > -dots.d_radius) {
									ctx.beginPath();
									ctx.strokeStyle = averageColorStyles(i_dot, j_dot);
									ctx.moveTo(i_dot.x, i_dot.y);
									ctx.lineTo(j_dot.x, j_dot.y);
									ctx.stroke();
									ctx.closePath();
								}
							}
						}
					}
				}

				function drawDots() {
					for(var i = 0; i < dots.nb; i++) {
						var dot = dots.array[i];
						dot.draw();
					}
				}

				function animateDots() {
					var now = Date.now();
					var delta = now - then;
					var interval = 5;
					then = now　　
					if(delta > interval) {　　　　 // 这里不能简单then=now，否则还会出现上边简单做法的细微时间差问题。例如fps=10，每帧100ms，而现在每16ms（60fps）执行一次draw。16*7=112>100，需要7次才实际绘制一次。这个情况下，实际10帧需要112*10=1120ms>1000ms才绘制完成。
						　　　　
						then = now - (delta % interval);　　　　
						ctx.clearRect(0, 0, canvas.width, canvas.height);
						moveDots();
						drawDots();　　
					}
					//				ctx.clearRect(0, 0, canvas.width, canvas.height);
					//				moveDots();
					//				connectDots();
					//				drawDots();
					requestAnimationFrame(animateDots);
				}

				function getCanvasPos(canvas, e) {
					//获取鼠标在canvas上的坐标
					var rect = canvas.getBoundingClientRect();

					var tempX = e.clientX - rect.left * (canvas.width / rect.width);
					var tempY = e.clientY - rect.top * (canvas.height / rect.height);

					console.log("x:" + tempX + ",y:" + tempY);
					for(var i = 0; i < dots.nb; i++) {
						var dot = dots.array[i];
						if((Math.pow((tempY - dot.firstY), 2) + Math.pow((tempX - dot.firstX), 2)) <= Math.pow((dots.radius * 1.2), 2)) {
							dot.alphaReduce = 0.1;
						}

					}

				}

				function getCanvasPosPart(canvas, e, self) { //获取鼠标在canvas上的坐标  
					var rect = canvas.getBoundingClientRect();

					var tempX = e.clientX - rect.left * (canvas.width / rect.width);
					var tempY = e.clientY - rect.top * (canvas.height / rect.height);
					var shoeTag = 0;
					console.log("x:" + tempX + ",y:" + tempY);
					for(var i = 0; i < dots.nb; i++) {
						var dot = dots.array[i];
						if((Math.pow((tempY - dot.firstY), 2) + Math.pow((tempX - dot.firstX), 2)) <= Math.pow((dots.radius * 1.2), 2)) {
							dot.energy = dot.energy - 1;
							ajaxRequest({
								url: 'stealValleyEnergy',
								data: {
									"userId":"1419",
									"generatedId":dot.id,
								},
								async: true,
								success: function(dataJson) {
									alert(JSON.stringify(dataJson));
									self.refresh();
								},
								error: function() {
									alert("系统繁忙");
								}
							});
							shoeTag = 1;
						}

					}
					if(shoeTag < 1) {
						if(e.clientX >= 107 && e.clientX <= 247 && e.clientY >= 42 && e.clientY <= 162) {
							self.$router.push({
								path: '/CityView'
							})
						}
					}

				}

				//----------------------跟着鼠标动--------------------
				//			document.getElementById('home').addEventListener('mousemove', function(e){
				//				mousePosition.x = e.pageX;
				//				mousePosition.y = e.pageY;
				//			});
				//	
				//			document.getElementById('home').addEventListener('mouseleave', function(e){
				//				mousePosition.x = canvas.width / 2;
				//				mousePosition.y = canvas.height / 2;
				//			});
				document.getElementById('windiow').addEventListener('click', function(e) {
					getCanvasPosPart(canvas, e, self);
				});

				//----------------------跟着鼠标动--------------------

				createDots();
				requestAnimationFrame(animateDots);
			}
		}
	}
</script>

<style scoped>
	#windiow {
		position: absolute;
		left: 100px;
		top: 40px;
		width: 50%;
		height: 120px;
	}
	
	#myCanvas {}
	
	.backgroundTitleView {
		position: relative;
		left: 0;
		top: 0;
		width: 100%;
		height: 55vw;
		background: url(../../static/img/freshHome.png) center;
		background-size: 100% 100%;
	}
	
	#home {
		position: relative;
		z-index: 6;
	}
	
	.strategy {
		position: absolute;
		bottom: 10px;
		padding-left: 10px;
	}
	
	#strategyButton {
		width: 50px;
		height: 50px;
	}
	
	.redMoney {
		position: absolute;
		bottom: 10px;
		width: 50px;
		left: 70px;
		height: 50px;
	}
	
	#redPacketButton {
		width: 50px;
		height: 50px;
	}
	
	.userInfoTitle {
		position: absolute;
		text-align: right;
		width: 50%;
		right: 0px;
		height: 60px;
		bottom: 30px;
		/*margin-top: -270px;*/
	}
	
	#userInfoImg {
		position: absolute;
		right: 0;
		bottom: 0px;
		width: 120px;
		height: 60px;
		/*margin-bottom: -400px;*/
		opacity: 0.7;
		/*position: relative;*/
		z-index: 3;
	}
	
	.userShow {
		position: absolute;
		padding-right: 63px;
		right: 0px;
		top: 5px;
		/*margin-top: 343px;*/
		/*position: relative;*/
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
		padding-top: 10px;
		/*margin-top: -52px;*/
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
		color: black;
		font-size: 17px;
	}
	
	.rightDiv {
		width: 30%;
		height: 100%;
		text-align: right;
	}
	/*下面是红包弹框*/
	/*
* {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

div,
span,
img{
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



:focus,
.focus {
	outline: none
}
.padding1 .modal-body .colse1 {
	position: absolute;
	top: 15px;
	right: 15px;
	width: 26px;
	height: 26px;
}

.modal-dialog {
	background-color: #C3173F;
	margin-top: 20px;
}

.modal-dialog .modal-body {
	padding-left: 20px;
	padding-right: 20px
}

.padding1 .modal-body .but6 {
	background-color: #FDC838;
	display: inline-block;
	width: 160px;
	margin: 20px auto 20px auto;
	padding: 6px 0px;
	font-size: 20px;
	color: #FFF;
	text-align: center;
	border: 2px #FDC838 solid;
	border-radius: 8px;
	-webkit-border-radius: 8px;
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
	border-radius: 200px 200px 20px 20px;
	-webkit-border-radius: 20;
}

.padding1 .modal-body {
	padding: 0px;
	position: relative
}

.padding1 .modal-body .img1 img {
	width: 100%;
	border-radius: 4px 4px 0px 0px;
	-webkit-border-radius: 4px 4px 0px 0px;
	padding: 0px;
}*/
</style>
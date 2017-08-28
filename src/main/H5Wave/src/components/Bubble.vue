<template>
	<div id="home">
		<canvas id="myCanvas" width="300px" height="150px" style="background-color: rgba(1,1,1,0);"></canvas>
	</div>
</template>

<script>
	export default {
		data(){
			return{
				
			}
		},
		methods:{
			draw:function(){
				
			}
		},
		mounted() {
			
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
			
			var img1 = new Image();
			img1.src = require('../../static/img/air.png');
			
			var mousePosition = {
				x:  canvas.width - 100,
				y:  canvas.height - 60
			};
	
			var dots = {
				nb: 8,
				distance: 100,
				d_radius: 150,
				radius: 15,
				speed:0.1,
				array: []
			};
			
	
			function colorValue(min) {
				return Math.floor(Math.random() * 255 + min);
			}
	
			function createColorStyle(r,g,b) {
				return 'rgba(' + r + ',' + g + ',' + b + ', a)';
			}
	
			function mixComponents(comp1, weight1, comp2, weight2) {
				return (comp1 * weight1 + comp2 * weight2) / (weight1 + weight2);
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
				return 'rgba(' + 147 + ',' + 206 + ',' + 245 + ', '+ a +')';
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
	
			function Dot(index){
				this.x = (canvas.width - dots.radius * 2.4)/(dots.nb-1)*index + dots.radius * 1.2;
				this.y = (Math.random() * (canvas.height - dots.radius * 3.5)) + dots.radius * 1.2;
				// 初始时的位置
				this.firstX = this.x;
				this.firstY = this.y;
				// 图标
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
				this.energy = 12;
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
				draw: function(){
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
					if (this.alpha > 0) {
						ctx.drawImage(this.img, (this.x - dots.radius), (this.y - dots.radius));
					}
					ctx.font = "12px";
				    //设置字体填充颜色
				    ctx.fillStyle = 'rgba(0, 0, 0,'+ this.alpha +')';;
					//从坐标点(50,50)开始绘制文字
    				ctx.fillText(""+this.energy+"g", (this.x - dots.radius*0.6), (this.y + 2*dots.radius));

				}
			};
	
			function createDots(){
				for(var i = 0; i < dots.nb; i++){
					dots.array.push(new Dot(i));
				}
			}
	
			function moveDots() {
				for(var i = 0; i < dots.nb; i++){
	
					var dot = dots.array[i];
	
					if((dot.y-dots.radius) < (dot.firstY-dots.radius*1.2) 
						|| (dot.y+dots.radius) > (dot.firstY+dots.radius*1.2)){
						dot.vy = - dot.vy;
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
				for(var i = 0; i < dots.nb; i++){
					for(var j = 0; j < dots.nb; j++){
						var i_dot = dots.array[i];
						var j_dot = dots.array[j];
	
						if((i_dot.x - j_dot.x) < dots.distance && (i_dot.y - j_dot.y) < dots.distance && (i_dot.x - j_dot.x) > - dots.distance && (i_dot.y - j_dot.y) > - dots.distance){
							if((i_dot.x - mousePosition.x) < dots.d_radius && (i_dot.y - mousePosition.y) < dots.d_radius && (i_dot.x - mousePosition.x) > - dots.d_radius && (i_dot.y - mousePosition.y) > - dots.d_radius){
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
				for(var i = 0; i < dots.nb; i++){
					var dot = dots.array[i];
					dot.draw();
				}
			}
	
			function animateDots() {
				var now = Date.now();
				var delta = now - then;
				var interval = 5;
				then = now
			　　if (delta > interval) {
			　　　　// 这里不能简单then=now，否则还会出现上边简单做法的细微时间差问题。例如fps=10，每帧100ms，而现在每16ms（60fps）执行一次draw。16*7=112>100，需要7次才实际绘制一次。这个情况下，实际10帧需要112*10=1120ms>1000ms才绘制完成。
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
			
			function getCanvasPos(canvas,e) {
				//获取鼠标在canvas上的坐标
			    var rect = canvas.getBoundingClientRect();   

				var tempX = e.clientX - rect.left * (canvas.width / rect.width);
				var tempY = e.clientY - rect.top * (canvas.height / rect.height);  
  
				console.log("x:"+tempX+",y:"+tempY);
				for(var i = 0; i < dots.nb; i++){
					var dot = dots.array[i];
					if ((Math.pow((tempY-dot.firstY),2)+Math.pow((tempX-dot.firstX),2)) <= Math.pow((dots.radius*1.2),2)) {
						dot.alphaReduce = 0.1;
					}
				}
  
			}

		
			function getCanvasPosPart(canvas,e) {//获取鼠标在canvas上的坐标  
			    var rect = canvas.getBoundingClientRect();   

				var tempX = e.clientX - rect.left * (canvas.width / rect.width);
				var tempY = e.clientY - rect.top * (canvas.height / rect.height);  

				console.log("x:"+tempX+",y:"+tempY);
				for(var i = 0; i < dots.nb; i++){
					var dot = dots.array[i];
					if ((Math.pow((tempY-dot.firstY),2)+Math.pow((tempX-dot.firstX),2)) <= Math.pow((dots.radius*1.2),2)) {
						dot.energy = dot.energy - 1;

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
			document.getElementById('home').addEventListener('click', function(e){
				getCanvasPosPart(canvas,e);
			});
			
			//----------------------跟着鼠标动--------------------
	
			createDots();
			requestAnimationFrame(animateDots);
		}
	}
	
</script>

<style>
</style>
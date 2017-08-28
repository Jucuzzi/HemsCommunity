<template>
	<div class="hello">
		<h1>{{ msg }}</h1>
		<h2>Essential Links</h2>
		<canvas ></canvas>
		<!--<canvas ></canvas>-->
		<ul>
			<li>
				<a href="https://vuejs.org" target="_blank">Core Docs</a>
			</li>
			<li>
				<a href="https://forum.vuejs.org" target="_blank">Forum</a>
			</li>
			<li>
				<a href="https://gitter.im/vuejs/vue" target="_blank">Gitter Chat</a>
			</li>
			<li>
				<a href="https://twitter.com/vuejs" target="_blank">Twitter</a>
			</li>
			<br>
			<li>
				<a href="http://vuejs-templates.github.io/webpack/" target="_blank">Docs for This Template</a>
			</li>
		</ul>
		<h2>Ecosystem</h2>
		<ul>
			<li>
				<a href="http://router.vuejs.org/" target="_blank">vue-router</a>
			</li>
			<li>
				<a href="http://vuex.vuejs.org/" target="_blank">vuex</a>
			</li>
			<li>
				<a href="http://vue-loader.vuejs.org/" target="_blank">vue-loader</a>
			</li>
			<li>
				<a href="https://github.com/vuejs/awesome-vue" target="_blank">awesome-vue</a>
			</li>
		</ul>
	</div>
</template>

<!--<script src="../assets/js/Stats.js" ></script>
<script src="../assets/js/dat.gui.min.js" ></script>-->
<script>
	var stageWidth = 0;
	var stageHeight = 0;
	var stageWidth2 = 0;
	var stageHeight2 = 0;
	var totalLength2 = 0;

	var distanceX = 0;
//第一条曲线的参数设置
	var config = {
		height: 8,
		waveLength: 150,
		curveFactor: 0,
		speed: -4
	}

	var distanceX2 = 175;
//第二条曲线的参数设置
	var config2 = {
		height: 8,
		waveLength: 150,
		curveFactor: 0,
		speed: -4
	}

	var DELTA_WIDTH = 1;
	export default {
		name: 'hello',
		data() {
			return {
				msg: 'Welcome to Your Vue.js App',
			}
		},
		mounted() {
			init();
		}
	}
	function init() {
		window.onresize = onResize;
		onResize();
		render();
	}

	function onResize() {
		var canvasList = document.querySelectorAll('canvas');
		var canvas = canvasList[0];
		stageWidth = canvas.width = window.innerWidth;
		stageHeight = canvas.height = window.innerHeight;
		stageWidth2 = stageWidth / 2;
		stageHeight2 = stageHeight / 2;
		totalLength2 = Math.ceil(stageWidth2 / DELTA_WIDTH) * DELTA_WIDTH;
		
		redraw();
	}

	function render() {
		redraw();
		requestAnimationFrame(render);
	}

	function _getHeight(distanceX, x) {
		var offsetX = ((distanceX + x) / totalLength2 - 1) * (totalLength2 / config.waveLength);
		var waveFactor = Math.cos((x / totalLength2 - 1) * Math.PI / 2);
		return Math.cos(offsetX * Math.PI) * Math.pow(waveFactor, config.curveFactor) * config.height;
	}
	
	function _getHeight2(distanceX2, x) {
		var offsetX = ((distanceX2+ x) / totalLength2 - 1) * (totalLength2 / config2.waveLength);
		var waveFactor = Math.cos((x / totalLength2 - 1) * Math.PI / 2);
		return Math.cos(offsetX * Math.PI) * Math.pow(waveFactor, config2.curveFactor) * config2.height;
	}

	function redraw() {
		var x = stageWidth2 - totalLength2;
		var toX = x + totalLength2 * 2;
		var centerY = stageHeight2;
		var canvasList = document.querySelectorAll('canvas');
//		var canvas = document.querySelector('canvas');
		var canvas = canvasList[0];
		var ctx = canvas.getContext('2d');
		ctx.clearRect(0, 0, stageWidth, stageHeight);
		ctx.beginPath();
		ctx.moveTo(x, stageHeight);
		for(; x < toX; x += DELTA_WIDTH) {
			ctx.lineTo(x, centerY - _getHeight(distanceX, x));
		}
		ctx.lineTo(x - DELTA_WIDTH, stageHeight);
		ctx.lineWidth = 4;
		ctx.strokeStyle = '#81ecff';
		ctx.fillStyle = '#81ecff';
		ctx.fill();
		ctx.stroke();
		ctx.closePath();
		distanceX += config.speed;
		
		
		var x = stageWidth2 - totalLength2 ;
		var toX = x + totalLength2 * 2 ;
		var centerY = stageHeight2;
//		var ctx2 = canvas.getContext('2d');
		ctx.beginPath();	
		ctx.moveTo(x, stageHeight);
		for(; x < toX; x += DELTA_WIDTH) {
			ctx.lineTo(x, centerY - _getHeight2(distanceX2, x));
		}
		ctx.lineTo(x - DELTA_WIDTH, stageHeight);
		ctx.lineWidth = 4;
		ctx.strokeStyle = '#4CE2FD';
		ctx.fillStyle = '#4CE2FD';
		ctx.fill();
		ctx.stroke();
		ctx.closePath();
		distanceX2 += config2.speed;
	}
//	
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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
	
	html,
	body {
		font-family: 'Arial Narrow', serif;
		width: 100%;
		height: 100%;
		overflow: hidden;
		color: #fff;
		background-color: #000;
	}
	
	p {
		position: absolute;
		width: 100%;
		bottom: 40px;
		text-align: center;
	}
</style>
<template>
  <div style="text-align: center; width: 100%;">
	<div id="apply3" class="modal fade padding1" tabindex="-1" data-replace="true" data-backdrop="static">
		<div class="modal-dialog modal-content">
			<div class="modal-body">
					 <div class="img1"><img src="../assets/images/抽奖页面/没抽中.png"/></div>
                     
                    <div class="colse1" data-dismiss="modal">
						
						<img src="../assets/images/关闭-4.png"/>
					</div>
                      <button type="button" class="but8" data-dismiss="modal" data-toggle="modal" >朕知道了</button>
			</div>
		</div>
	</div>
	
	<!--100M省内流量 -->
		<div id="apply2" class="modal fade padding1" tabindex="-1" data-replace="true" data-backdrop="static">
			<div class="modal-dialog modal-content">
				<div class="modal-body">
					<div class="img1"><img src="../assets/images/100Mshengneiliuliang.png" /></div>

					<p><span>恭喜你获得95折券</span></p>
					卡密为：sadasdasdadasd <br /> （长按可复制，可点右上角查询中奖记录）
					<br />
					<div class="colse1" data-dismiss="modal">
						
						<img src="../assets/images/关闭-4.png"/>
					</div>
					<button type="button" class="but7" data-dismiss="modal" data-toggle="modal">收入囊中</button>
					<button type="button" class="but6" data-dismiss="modal" data-toggle="modal">赠送好友</button>
				</div>
			</div>
		</div>
	
	<div class="shanDeng" id="deng">
		<div id="luck" style="width: 100%;"><!-- luck -->
			<table>
				<tr>
					<td class="luck-unit luck-unit-0"><img src="../assets/images/抽奖页面/谢谢惠顾.png"></td>
					<td class="luck-unit luck-unit-1"><img src="../assets/images/抽奖页面/1元现金卷.png"></td>
					<td class="luck-unit luck-unit-2"><img src="../assets/images/抽奖页面/爱奇艺会员.png"></td>
				</tr>
				<tr>
					<td class="luck-unit luck-unit-7"><img src="../assets/images/抽奖页面/100积分.png"></td>
					<td rowspan="1" colspan="1" class="cjBtn" id="btn"></td>
					<td class="luck-unit luck-unit-3"><img src="../assets/images/抽奖页面/95折卷.png"></td>
				</tr>
				<tr>
					<td class="luck-unit luck-unit-6"><img src="../assets/images/抽奖页面/满50元减5元.png"></td>
					<td class="luck-unit luck-unit-5"><img src="../assets/images/抽奖页面/夺宝卷.png"></td>
					<td class="luck-unit luck-unit-4"><img src="../assets/images/抽奖页面/9折卷.png"></td>
				</tr>
			</table>
		</div><!-- luckEnd -->
	</div>
  </div>
</template>

<script>
export default {
  name: 'hello',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped="yes">
	
	*{margin:0;padding:0;list-style-type:none;}
html,body,div,span,a,p,img,dl,dt,dd,ol,ul,li{border:0;margin:0;padding:0;font-size:100%;text-decoration:none}
img{color:transparent;font-size:0;vertical-align:middle;-ms-interpolation-mode:bicubic;max-width:100%}
ol,ul{list-style:none}
li{display:list-item}
:focus,.focus{outline:none}
body{max-width:640px;margin:0 auto;font-family:"microsoft yahei";font-size:13px;line-height:1.5;background:#f9f1cd;}
h3,.h3{font-size:16px;}
a:link,a:visited,a:hover,a:active{text-decoration:none}
	.padding1 .modal-body .colse1 {
		position: absolute;
		top: 15px;
		right: 15px;
		width: 26px;
		height: 26px;
	}
	
	.modal-dialog {
		background-color: #f3f3f3;
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
		width: 40%;
		margin: 20px auto 20px 10px;
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
		width: 40%;
		margin: 20px auto 20px 10px;
		padding: 6px 0px;
		font-size: 20px;
		color: #FD4789;
		text-align: center;
		border: 2px #FD4789 solid;
		border-radius: 20px;
		-webkit-border-radius: 20px;
	}
	
	.padding1 .modal-body .but8 {
		background-color: #3FCA40;
		display: inline-block;
		width: 80%;
		margin: 20px auto 20px auto;
		padding: 6px 0px;
		font-size: 20px;
		color: #FFFFFF;
		text-align: center;
		border: 2px #3FCA40 solid;
		border-radius: 5px;
		-webkit-border-radius: 8px;
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
	
	.padding1 .modal-body .img1 img {
		width: 100%;
		border-radius: 4px 4px 0px 0px;
		-webkit-border-radius: 4px 4px 0px 0px;
		padding: 0px;
	}
	/*#title{
		width: 560px;
		height: 500px;
		margin: 200px auto 0 auto;
	}*/

	.shanDeng,.shanDeng2{
		width: 80%;
		height: 80%;
		background-size: 100%;
		background-repeat: no-repeat;
		margin: 0 auto 0 auto;
	}

	.shanDeng{
		background-image: url(../assets/images/shandeng1.png);
	}
	.shanDeng2{
		background-image: url(../assets/images/shandeng2.png);
	}

	#btn .cjBtnDom,#btn .cjBtn,.shanDeng .active,.shanDeng td{
		width:10px;
		height: 10%;
		background-size: 100%;	
	}

	#luck{
		width: 100%;
		padding:8%;
	}

	#btn.cjBtn{
		background-image: url(../assets/images/立即抽奖.png);
	}
	#btn.cjBtnDom{
		background-image: url(../assets/images/立即抽奖.png);
	}


	#luck .active{
		background-image: url(../assets/images/active.png);
	}
	.shanDeng td{
		text-align: center;
		background-image: url(../assets/images/proBj.png);
	}
	.shanDeng table{
		width: 100%;
		padding-left: 10px;
		padding-top: 10px;

	}
	
h1, h2 {
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
</style>

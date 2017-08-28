<template>
	<div >
	<button type="button" class="" data-target="#apply2" data-toggle="modal">确定</button>
	<!--100M省内流量 -->
		<div id="apply2" class="modal fade padding1" style="text-align: center;" tabindex="-1" data-replace="true" data-backdrop="static">
			<div class="modal-dialog modal-content">
				<div class="modal-body" style="position: relative;">
					<div class="img1"><img src="../assets/images/红包.png" /></div>
					<div style="position: absolute; width: 100%; top: 20%;">
						<h4>恭喜您可以获取</h4>
						<h4>以下奖励中的一个</h4></br>
						（只能二选一哦）
					</div>
					<!--<p><span>恭喜你获得95折券</span></p>-->
					<div style="">
						<div style="width: 45%;float: left;margin-left: 10px; position: relative;">
							<img src="../assets/images/兑换钱.png" v-on:click="chosenMoney" />
							<img src="../assets/images/select_icon.png" style="position: absolute; right: 15px ;top: 15px; width: 15%;"id="chosenMoney" hidden="yes"/>
						</div>
						<div style="width: 45%;float:right;margin-right: 10px; position:  relative;">
							<img src="../assets/images/兑换积分.png" v-on:click="chosenPoint" />
							<img src="../assets/images/select_icon.png" style="position: absolute; right: 15px ;top: 15px; width: 15%;"id="chosenPoint" hidden="yes"/>
						</div>
						
					</div>
					
					<!--卡密为：sadasdasdadasd <br /> （长按可复制，可点右上角查询中奖记录）
					<br />-->
					<a href="#" class="colse1" data-dismiss="modal"></a>
					<!--<button type="button" class="but7" data-dismiss="modal" data-toggle="modal">收入囊中</button>-->
					<button type="button" class="but6" data-dismiss="modal" data-toggle="modal">立即领取</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'hello2',
		data() {
			return {
				chosen:0,
			}
		},
		methods: {
			click: function() {
				$('#apply2').modal('show');
			},
			chosenMoney:function(){
				$('#chosenPoint').hide();
				$('#chosenMoney').show();
				this.chosen = 1;
			},
			chosenPoint:function(){
				$('#chosenPoint').show();
				$('#chosenMoney').hide();
				this.chosen = 2;
			}

		},
		mounted() {
			this.chosen = 0;
			switch (this.chosen){
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
		}
	}
	$(".close2").click(function() {
		$('#myModal').modal('hide');
	});
	
</script>

<style>
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
	}
</style>
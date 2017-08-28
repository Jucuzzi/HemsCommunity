<template>
	<div class="footerDiv">
		<div class="lineDiv"></div>
		<div class="footerContentDiv">
			<div id="heartDiv" class="heartDiv">
				<img v-on:click="clickHeart" id="heartImg" class="heartImg" alt="未选" src="../telehems/img/monthreportdetail/heart_line.png">
			</div>
			<div class="collectionDiv">
				<span id ="collectionNum" class="collectionNum"></span>
			</div>
			<div class="collectionTextDiv">
				<span class="collectionText">收藏</span>
			</div>
			<div class="discussDiv">
				<div class="roundedDiv" @click="jump">
					<div class="messageImgDiv">
						<img class="messageImg" src="../telehems/img/monthreportdetail/message.png">
						<div class="discussOuterNumDiv">
							<div class="discussNumDiv">
								<span class="discussNum">1</span>
							</div>
						</div>
					</div>
					<span class="discussText">参与讨论</span>
				</div>
				
			</div>
		</div>
	</div>
</template>
<script type="text/javascript">
 export default {
 	methods: {
 		clickHeart:function () {
		    var checkimg = document.getElementById("heartImg"); 
		    var collectionNum = document.getElementById("collectionNum"); 
		    var num = 0;
		    if (collectionNum.innerText != ""){
		    	num = parseInt(collectionNum.innerText);
		    }
	        if(checkimg.alt == "选中"){   
	            checkimg.src= require('../telehems/img/monthreportdetail/heart_line.png');
	            checkimg.alt = "未选";
	            
	            if (num - 1 > 0) {
	            	collectionNum.innerText = num - 1;
	            } else {
	            	collectionNum.innerText = "";
	            }
	        } else{    
	           checkimg.src = require('../telehems/img/monthreportdetail/heart.png');
	           checkimg.alt ="选中";  
	           collectionNum.innerText = num + 1;
	        }    
	    },
	    jump:function() {
			this.$router.push({path:'/Comments'});
		}
 	}
}
</script>

<style>
	.footerDiv{
		height: 46px;
		width: 100%;
		position:fixed;
        bottom:0;
	}
	.footerContentDiv{
		height: 100%;
		width: 100%;
		display: flex;
        flex-direction: row;
		align-items:center;
		background: #fafbfb;
	}
	.heartDiv{
		height: 100%;
		margin-left: 20px;
	}
	.heartImg{
		height: 24px;
		width: 24px;
		margin-top: 11px;
	}
	.collectionDiv{
		height: 100%;	
	}

	.lineDiv{
		width: 100%;
		height: 1px;
		background-color: #dde1e1;
	}
	.collectionNum{
		font-size: 12px;
		line-height:20px; 
		margin-left: 2px;
		color: #8f9191;
	}
	.collectionText{
		font-size: 16px;
		color: #8f9191;
		white-space:nowrap;
		margin-left: 6px;
	}
	.discussDiv{
		height: 100%;
		width: 100%;
		display: flex;
		flex-direction: row;
		align-items:center;
		justify-content: flex-end;

	}
	.roundedDiv{
		height: 30px;
		width: 120px;
		background: #00abfd;
		border-radius:15px;
		margin-right: 10px;
		display: flex;
		flex-direction: row;
		align-items:center;
	}
	.messageImgDiv{
		width: 40px;
		height: 24px;
		margin-left: 10px;
		margin-top: 5px;
		text-align: center;
		position:relative;
	}
	.messageImg{
		width: 24px;
		height: 20px;
		margin-left: -20px;
	}
	.discussOuterNumDiv{
		width: 100%;
		height: 30px;
		display: flex;
		flex-direction: row;
		align-items:center;
		justify-content: center;
	}
	.discussNumDiv{
		background: red;
		min-width: 15px;
		height: 9px;
		border-radius:7px;
		font-size: 0px;
		margin-top: -75px;
		text-align: center;
	}
	.discussNum{
		font-size: 8px;
		color: white;
		margin-left: 2px;
		margin-right: 2px;
		margin-top: 1px;
		margin-bottom: 1px;
	}
	.discussText{
		font-size: 16px;
		color: white;
	}
</style>
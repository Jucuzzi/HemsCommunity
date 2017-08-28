<template>
		<div  v-show="1" id="txtMarquee-top" class="txtMarquee-top">
			<div id="bd" class="bd">
				<ul id="winning-list-ul" class="winning-list-ul">
					<!--<li><a target="_blank">中国打破了世界软件巨头规则</a></li>-->
				</ul>
			</div>
		</div>
</template>

<script>
	
	import '@/assets/pluginLib/js/jQuery.textSlider.js'
	import '@/assets/pluginLib/js/Slider.js'
	export default{
			props:['winningList'],

		data(){
			return {
			
			}
		},
		mounted(){
		
				ajaxRequest({
					url : "/hemsCircleQueryPrizeDrawMain",
					data : {
						"userId":"1419"
					},
					async : true,
					success : function(dataJson) {
						
						var winnings= [{
								"userName": "11",
								"prizeType": "22"
						},
						{
								"userName": "11",
								"prizeType": "22"
							}
						];
						//真数据
//						var winningListData =  dataJson.result.prizeDrawHistory;
						//假数据
						var winningListData =  winnings;
//						alert("6655");
//						alert(winningListData.length);
						
			if (winningListData.length > 0) {
				var oUl=document.getElementById("winning-list-ul"); //拿到body下的ul
				for(var i=0;i<winningListData.length;i++){
				    //每次给li赋值一个新的对象
				    var oLi=document.createElement('li');////////
				    oLi.innerHTML="用户"+"<span style='color:blue;white-space:pre;'>  "+winningListData[i].userName+"  <\/span>"+
				    			  "抽中了"+"<span style='color:blue;white-space:pre;'>        "+winningListData[i].prizeType+"<\/span>";
				    oUl.appendChild(oLi);
				}
//				line：滚动几行；
//				speed：滚动速度；
//				timer：自动滚动间隔；
				 $("#bd").textSlider({line:1,speed:1000,timer:1000});
//				$('#txtMarquee-top').slide({mainCell:".bd ul",autoPlay:true,effect:"topMarquee",vis:1,interTime:50});
			}
						
					},
					error : function() {
						alert("系统繁忙");
					}
				});
			
		},
	}
	
</script>

<style>
	*{margin:0;padding:0;list-style-type:none;}
h3,.h3{font-size:16px;}
	.txtMarquee-top{ 
		width:100%;  
		overflow:hidden; 
		position:relative;  
		border:0px solid #ccc;  
		display: flex;
		align-items: center;
		justify-content: center;
	}
	.txtMarquee-top .bd{
		background:#afe5fe;
		width:80%;
		padding:0px;  
		height:30px;
		border-radius:5px;
	}
	.txtMarquee-top .winning-list-ul li{
		height:30px; 
		line-height:30px;
   }
</style>
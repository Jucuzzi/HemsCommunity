<template>
	<div class="detail-root">
		<detailheader></detailheader>
		<div class="info-outer-div">
			<span class="info-title textcenter" v-text="infocontent.title">11</span>
			<div class="info-detail-div">
				<span class="info-detail" v-text="'来源：'+infocontent.source">来源：22</span>
				<span class="info-detail" v-text="'作者：'+infocontent.author">作者：33</span>
				<span class="info-detail" id="showTime"></span>
			</div>
			
			<div id="fileImg">
				
			</div>
			
			<div id="info_content" class="info-content-div">
				
			</div>
			
		</div>
		
		<detailfooter ></detailfooter>
	</div>

</template>

<script>
import detailheader from '@/components/detailheader.vue'
import detailfooter from '@/components/detailfooter.vue'

export default {
	data(){
		return{
			infocontent:{
				detailContent:"",
				source:"",
				author:"",
				releaseTime:"",
				title:""
			}
		}
	},
	created(){
         this.handleShow();
     },
	methods:{
		 handleShow() {
		 	var self = this;
				ajaxRequest({
					url : "queryinformationById",
					data : {
						"informationId":self.$route.query.newsId,
					},
					async : true,
					success : function(dataJson) {
						self.infocontent = dataJson;
						document.getElementById('info_content').innerHTML=self.infocontent.detailContent;
						var showImg = self.infocontent.imgPath;
						document.getElementById('fileImg').innerHTML="<img id='newsImg' alt src='"+showImg+"' />";
						$('#showTime').html(
							self.infocontent.releaseTime.substring(0,4)+"-"+
				    			self.infocontent.releaseTime.substring(4,6)+"-"+
							self.infocontent.releaseTime.substring(6,8)+" "+
							self.infocontent.releaseTime.substring(8,10)+":"+
							self.infocontent.releaseTime.substring(10,12)+":"+
							self.infocontent.releaseTime.substring(12,14)
						);
					},
					error : function() {
						alert("系统繁忙");
					}
				});
                
           },
	},
 	components:{
		detailheader,
		detailfooter,
	},

}
</script>
<style>
.check-info-dialog{
	  position: fixed;
	  top: 0;
	  left: 0;
	  width: 100%;
	  height: 100%;
	  background-color: rgba(0, 0, 0, 0.5);
	  display: flex;
	  display: -webkit-flex;
	  justify-content: center;
	  -webkit-justify-content: center;
	  align-items: center;
	  -webkit-align-items: center;
	  z-index: 100;
}
.info-outer-div{
	width: 100%;
	height: 90%;
	background-color: white;
	border-radius: 6px;
	
}	
.info-close-btn{
	width: 98%;
	height: 25px;
	display: flex;
	justify-content: flex-end;
	margin-right: 2%;
	margin-top: 2%;
	
}
.textcenter{
	text-align: center;
	display:inline-block;
}
.info-title{
	width: 80%;
	margin-left: 10%;
	font-size: 27px;
}
.info-detail-div{
	display: flex;
	width: 100%;
	margin-top: 2%;
	display: flex;
	justify-content: center;
}
.info-detail{
	font-size: 17px;
	white-space: nowrap;
	margin-right: 15px;
	line-height: 17px;
}
.info-content-div{
	width: 100%;
	margin-left: 5%;
	margin-top: 3%;
}
#newsImg{
	width: 60%;
	height: 180px;
	margin-left: 20%;
}
</style>
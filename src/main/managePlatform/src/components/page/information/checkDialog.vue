<template>
	<div v-show="show" class="check-info-dialog">
		<div class="info-outer-div">
			<div class="info-close-btn" @click="showDialog">
				<img width="20px" height="20px" src="../../../assets/image/information/delete.png">
			</div>
			<span class="info-title textcenter" v-text="infoContentTemp.title">11</span>
			<div class="info-detail-div">
				<span class="info-detail" v-text="'来源：'+infoContentTemp.source">来源：22</span>
				<span class="info-detail" v-text="'作者：'+infoContentTemp.author">作者：33</span>
				<span class="info-detail" v-text="showTime">44</span>
			</div>
			<div id="info_content" class="info-content-div">	
			</div>
		</div>
	</div>
</template>

<script>


export default {
	props:['infocontent'],
	data(){
		return{
			show:false,
			infoContentTemp:{
				detailContent:"",
				resouce:"",
				author:"",
				releaseTime:"",
				title:"",
			}
		}
	},
	computed: {
		showTime(){
			
			return  this.infoContentTemp.releaseTime.substring(0,4)+"-"+
				    this.infoContentTemp.releaseTime.substring(4,6)+"-"+
					this.infoContentTemp.releaseTime.substring(6,8)+" "+
					this.infoContentTemp.releaseTime.substring(8,10)+":"+
					this.infoContentTemp.releaseTime.substring(10,12)+":"+
					this.infoContentTemp.releaseTime.substring(12,14);
		},
	},
	updated(){
//		document.getElementById('info_content').innerHTML='<p>Hello BBKadadfasfdasdfaf</p><p>	<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAACPUlEQVR4XuXbbU3EQBSF4YMDJCABB4ACwAE4wAGgAAkgARQADpCABHBALmGSppl2vu49M3Ppn6bdTTbv03abdDoHAI4B3AI4AnAP4Bm+lysADwC+AFwe/AWfL5qvATw5NZD4x0XbuwC8AThZBXtEWMdL8osAnAJ4jRxxTwix+G9pFwBZYl+Q/R4QNuMBfAQArwi78RK9BPCGkIyPAXhByIrfApgdITt+D2BWhKL4FMBsCMXxOQCzIFTF5wKMjlAdXwIwKkJTfCnAaAjN8TUAoyCoxNcC9EZQi28B6IWgGt8KwEZQj9cAYCGYxGsBWCOYxWsCWCGYxmsDaCOYx1sAaCFQ4q0AWhFo8ZYAtQjUeGuAUgR6PAMgF6FLPAsghSCfL4erZPt30EKe28uG5bJ+LG75W1uDL+vfpMUzz4AQmUKgxvcA2Lsc6PG9AOR9BBmMPYxcb/SxSOZ/gPTuxQcPKgITICeejsAC2IqXox27Dco+ypnAANiLD6/idHs/wRogJz51izQ9EywBSuK7IVgB1MR3QbAAaImnI2gDaMRTETQBNONpCFoAFvEUBA0Ay3hzhFYARrwpQgsAM94MoRagR7wJQg1Az3h1hFKAEeJVEUoARopXQ8gFGDFeBSEHYOT4ZoQUwAzxTQh7ADPFVyNsAcwYX4UQA5g5vhhhDeAhvghhCeApPhshAHiMz0IQAM/xKYQzAfjPU2ejc4dNByLCIem0Xo9A/c4dlktApszLcPWN45njwfwCwB2AT1n/AAt4DQCfmoObAAAAAElFTkSuQmCC"></p><p>取得完全的权威的 的权威的渠道</p>';
		this.infoContentTemp.detailContent = "detailContent" in this.infocontent ? this.infocontent.detailContent : "",
		this.infoContentTemp.source = "source" in this.infocontent ? this.infocontent.source : "",
		this.infoContentTemp.author = "author" in this.infocontent ? this.infocontent.author : "",
		this.infoContentTemp.releaseTime = "releaseTime" in this.infocontent ? this.infocontent.releaseTime : "",
		this.infoContentTemp.title = "title" in this.infocontent ? this.infocontent.title : "",
		document.getElementById('info_content').innerHTML=this.infoContentTemp.detailContent;
	},
	methods: {
		showDialog: function(){
			this.show = !this.show;
		},
		
	}
}
</script>


<style scoped="scoped">
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
	width: 70%;
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
	width: 90%;
	margin-left: 5%;
	margin-top: 3%;
}
</style>
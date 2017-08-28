<template>
	<div class="steal-energy-root">
		
		<div class="steal-div">
			<!--<div class="energy-title-bar">
				<div @click="onBack" class="energy-title-leftDiv"> 
					<img class="energy-title-leftImg" src="../telehems/images/common/title_arrow_left_white.png"/>		
				</div>
			</div>-->
			<mainHouseSelf msgfromfa="other"></mainHouseSelf>
		</div>
		<other-energy :stealEnergyResult="stealEnergyResult"></other-energy>
	</div>
</template>

<script>
	import mainHouseSelf from '@/components/mainHouse.vue'
	import otherEnergy from '@/components/otherEnergy.vue'
	export default {
	 	data(){
			return{
				stealEnergyResult:{
					"stolenIcon":"http://qq.5068.com/uploads/allimg/131226/13320533V-11.jpg",
					"stolen":"50",
					"winner":"0",
					"stealerIcon":"http://pic.baike.soso.com/p/20131105/20131105155548-374146863.jpg",
					"stealer":"10"
				},
			}
		},
	 	 components:{
			otherEnergy,
			mainHouseSelf
	 	 },
	 	 methods: {
	 		onBack:function () {
	 			this.$router.go(-1);
	 		}
	 	}
	}
</script>

<style>
	.energy-title-bar{
		height: 40px;
		width: 100%;
		position:fixed;
        top:0;

		display: flex;
		flex-direction: row;
	}
	.energy-title-leftDiv{
		width: 20%;
		height: 40px;
		text-align: left;
	}
	.energy-title-leftImg{
		height: 24px;
		width: 24px;
		margin-left: 13px;
		margin-top: 8px;
	}
	.steal-energy-root{
		height: 100%;
	}
	.steal-div{
		height: 260px;
	}
</style>
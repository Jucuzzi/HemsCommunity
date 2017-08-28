<template>
	<div class="otherEnergy-root">
		<span class="competition"><b>本月能量比拼</b></span>
		<div class="stolen-info-div">
			<div class="stolen-icon-div">
				<img id="stolen-rank" class="rank-icon" src="../assets/rank.png"/>
				<img class="stolen-icon" :src="this.stealEnergyResult.stolenIcon"/>
			</div>
			<span class="energy-text"><b>已收取能量</b></span>
			<span class="energy-num-text" v-text="this.stealEnergyResult.stolen">0</span>
			<span class="energy-text"><b>g</b></span>
		</div>
		<img class="vs-icon" src="../assets/vs_icon.png"/>
		<div class="stealer-info-div">
			<span class="energy-text"><b>已收取能量</b></span>
			<span class="energy-num-text" v-text="this.stealEnergyResult.stealer">0</span>
			<span class="energy-text"><b>g</b></span>
			<div class="stolen-icon-div">
				<img id="stealer-rank" class="rank-icon" src="../assets/rank.png"/>
				<img class="stolen-icon" :src="this.stealEnergyResult.stealerIcon"/>
			</div>
		</div>
	</div>
</template>

<script>
 export default {
		props:['stealEnergyResult'],
		mounted(){
			if (this.stealEnergyResult.winner == "0") {
				document.getElementById('stolen-rank').style.visibility="hidden"; 
			} else {
				document.getElementById('stealer-rank').style.visibility="hidden";
			}
		} 
 }
</script>

<style scoped>
	.otherEnergy-root{
		margin-top: 10%;
		width: 100%;
		background-color: white;
		text-align: center;
	}
	.competition{
		font-size: 17px;
		color: #33B23F;
		
	}
	.vs-icon{
		width: 85px;
		height: 85px;
	}
	.stolen-info-div{
		
		margin-left: 6%;
		height: 70px;
		display: flex;
		flex-direction: row;
		align-items: center;
	}
	.energy-text{
		font-size: 15px;
	}
	.stealer-info-div{
		
		margin-right: 6%;
		height: 70px;
		display: flex;
		flex-direction: row;
		justify-content: flex-end;
		align-items: center;
	}
	.stolen-icon-div{
		display: flex;
		flex-direction: column;
		justify-content:center;
		margin-top: -12px;
		margin-right: 4%;
		margin-left: 4%;
	}
	.rank-icon{
		height: 25px;
		width: 25px;
		margin-bottom: -13px;
		margin-left: -10px;
		z-index: 1;
	}
	.stolen-icon{
		height: 44px;
		width: 44px;
		border-radius: 4px;
		border-width: 4px;
		padding:3px;
		background-color: #fec700;
	}
	.energy-num-text{
		font-size: 28px;
		margin-left: 3px;
		margin-right: 3px;
		line-height: 30px;
	}
</style>
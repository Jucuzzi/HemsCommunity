<template>
	<div class="detail-root">
		<detailheader></detailheader>
		<text-carousel :winningList="winningList"></text-carousel>
		<p><router-link to="/sign">签到</router-link></p>
		<p><router-link to="/test">test</router-link></p>
		<p><router-link to="/raiders">攻略</router-link></p>
		<p><router-link to="/Hello2">攻略</router-link></p>
		<span @click="showCalendarDialog">日历</span>
		<raiders-calendar ref="raiderscalendar" :isShow="isShow" :signDateList="signDateList"></raiders-calendar>
		<p><router-link to="/stealEnergy">偷取他人能量</router-link></p>
		<detailfooter ></detailfooter>
	</div>

</template>

<script>
import textCarousel from '@/components/textCarousel.vue'
import detailheader from '@/components/detailheader.vue'
import detailfooter from '@/components/detailfooter.vue'
import raidersCalendar from '@/components/sign/signCalendar.vue'

export default {
	data(){
			return{
				winningList:[{"name":"11","winning":"95折券"},{"name":"22","winning":"85折券"}],
				isShow:true,
//				signDateList:[
//						{"signDay":"20170701","isPrize":"0"},
//						{"signDay":"20170702","isPrize":"0"},
//						{"signDay":"20170703","isPrize":"0"},
//						{"signDay":"20170704","isPrize":"0"},
//						{"signDay":"20170705","isPrize":"0"},
//						{"signDay":"20170706","isPrize":"0"},
//						{"signDay":"20170707","isPrize":"1"},
//						{"signDay":"20170720","isPrize":"0"},
//						{"signDay":"20170611","isPrize":"0"},
//						{"signDay":"20170612","isPrize":"0"},
//						{"signDay":"20170613","isPrize":"0"},
//						{"signDay":"20170614","isPrize":"0"},
//						{"signDay":"20170615","isPrize":"0"},
//						{"signDay":"20170616","isPrize":"0"},
//						{"signDay":"20170617","isPrize":"1"},
//						{"signDay":"20170620","isPrize":"0"}
//				],
				signDateList:
						{"20170701":"0",
						 "20170702":"0",
						 "20170703":"0",
						 "20170704":"0",
						 "20170705":"0",
						 "20170706":"0",
						 "20170707":"1",
						 "20170708":"0",
						 "20170709":"0",
						 "20170720":"0",
						 "20170727":"0"},
			}
	},
	mounted(){
		
	},
	methods:{
		showCalendarDialog:function(){
//			this.$refs.raiderscalendar.$emit('showCalendarDialog');
			this.$refs.raiderscalendar.showDialog();
		}
	},
  components:{
		textCarousel,
		detailheader,
		detailfooter,
		raidersCalendar
  }
}
</script>
<style>

</style>
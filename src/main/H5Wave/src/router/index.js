import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import LuckyDraw from '@/components/LuckyDraw'
import CityView from '@/pages/CityView'
import Hello4 from '@/components/hongbao'
import sign from '@/components/sign'
import PointDetail from '@/components/PointDetail'
import HemsInfomationMain from '@/components/HemsInfomationMain'
import monthreportdetail from '@/components/monthreportdetail'
import Comments from '@/components/Comments'
import MainView from '@/pages/MainView'

import List from '../pages/List.vue'
import Detail from '../pages/Detail.vue'
import TestValue from '../pages/TestValue.vue'
import rankList from '../pages/rankList.vue'
import TotalRankList from '../pages/TotalRankList.vue'
import collectHistory from '../pages/collectHistory.vue'
import MoreDynamic from '../pages/MoreDynamic.vue'
import raiders from '../pages/raiders.vue'
import stealEnergy from '../pages/stealEnergy.vue'

Vue.use(Router)

export default new Router({
	routes: [{
			path: '/',
			name: 'HemsInfomationMain',
			component: HemsInfomationMain
		},
		{
			path: '/monthreportdetail',
			name: 'monthreportdetail',
			component: monthreportdetail
		},
		{
			path: '/Comments',
			name: 'Comments',
			component: Comments
		},
		{
			path: '/sign',
			name: 'sign',
			component: sign
		},
		{
			path: '/LuckyDraw',
			name: 'LuckyDraw',
			component: LuckyDraw
		},
		{
			path: '/PointDetail',
			name: 'PointDetail',
			component: PointDetail
		},
		{
			path: '/hongbao',
			name: 'Hello4',
			component: Hello4
		},
		{
			path: '/CityView',
			name: 'CityView',
			component: CityView
		},
		{
			path: '/MainView',
			name: 'MainView',
			component: MainView
		},
		{
			path: '/List',
			name: 'List',
			component: List
		},
		{
			path: '/Detail',
			name: 'Detail',
			component: Detail
		},
		{
			path: '/TestValue',
			name: 'TestValue',
			component: TestValue
		},
		{
			path: '/rankList',
			name: 'rankList',
			component: rankList
		},
		{
			path: '/collectHistory',
			name: 'collectHistory',
			component: collectHistory
		},
		{
			path: '/MoreDynamic',
			name: 'MoreDynamic',
			component: MoreDynamic
		},
		{
			path: '/raiders',
			name: 'raiders',
			component: raiders
		},
		{
			path: '/stealEnergy',
			name: 'stealEnergy',
			component: stealEnergy
		},
		{
			path: '/TotalRankList',
			name: 'TotalRankList',
			component: TotalRankList
		}
	]
})
import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/readme',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            children:[
                {
                    path: '/',
                    component: resolve => require(['../components/page/Readme.vue'], resolve)
                },
                {
                    path: '/basetable',
                    component: resolve => require(['../components/page/BaseTable.vue'], resolve)
                },
                {
                    path: '/vuetable',
                    component: resolve => require(['../components/page/VueTable.vue'], resolve)     // vue-datasource组件
                },
                {
                    path: '/baseform',
                    component: resolve => require(['../components/page/BaseForm.vue'], resolve)
                },
                {
                    path: '/vueeditor',
                    component: resolve => require(['../components/page/VueEditor.vue'], resolve)    // Vue-Quill-Editor组件
                },
                {
                    path: '/markdown',
                    component: resolve => require(['../components/page/Markdown.vue'], resolve)     // Vue-Quill-Editor组件
                },
                {
                    path: '/upload',
                    component: resolve => require(['../components/page/Upload.vue'], resolve)       // Vue-Core-Image-Upload组件
                },
                {
                    path: '/basecharts',
                    component: resolve => require(['../components/page/BaseCharts.vue'], resolve)   // vue-schart组件
                },
                {
                    path: '/drag',
                    component: resolve => require(['../components/page/DragList.vue'], resolve)    // 拖拽列表组件
                },
                {
                    path: '/informationrelease',
                    component: resolve => require(['../components/page/InformationRelease.vue'], resolve)    // 资讯管理页面
                },
                {
                    path: '/informationoperation',
                    component: resolve => require(['../components/page/InformationOperation.vue'], resolve)    // 资讯管理页面
                },
                 {
                    path: '/baseforusers',
                    component: resolve => require(['../components/page/baseForUsers.vue'], resolve)    // 用户管理页面 
                },
                 {
                    path: '/addnewOreditUser',
                    component: resolve => require(['../components/page/addNewOrEditUser.vue'], resolve)    // 用户管理页面 
                },
                 {
                    path: '/BaseComment',
                    component: resolve => require(['../components/page/BaseComment.vue'], resolve)    // 评论管理页面 
                },
                {
                    path: '/lotteryoperation',
                    component: resolve => require(['../components/page/LotteryOperation.vue'], resolve)    // 奖品管理页面
                },
                {
                    path: '/lotterymanage',
                    component: resolve => require(['../components/page/LotteryManage.vue'], resolve)    // 奖品管理页面
                }
//               ,
//				{
//					path: '/RoleList',
//					component: resolve => require(['../components/page/RoleConfig/RoleList.vue'], resolve) // 资讯管理页面
//				},
//				{
//					path: '/MenuPrvlgConfig',
//					component: resolve => require(['../components/page/MenuConfig/MenuPrvlgConfig.vue'], resolve) // 资讯管理页面
//				},
//				{
//					path: '/BtnList',
//					component: resolve => require(['../components/page/BtnConfig/BtnList.vue'], resolve) // 资讯管理页面
//				}
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
    ]
})
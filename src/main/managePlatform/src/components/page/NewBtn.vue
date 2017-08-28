<template>
	<div>
		<div class="crumbs">
			<el-breadcrumb separator="/">
				<el-breadcrumb-item><i class="el-icon-date"></i> 用户管理</el-breadcrumb-item>
				<el-breadcrumb-item>新增按钮权限</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="form-box">
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item label="按钮名称">
					<el-input v-model="form.btnName"></el-input>
				</el-form-item>
				<el-form-item label="变量名">
					<el-input v-model="form.btnAlias"></el-input>
				</el-form-item>
				<el-form-item label="所在菜单">
					<!--<el-select v-model="form.menuId" placeholder="请选择">
						<el-option key="item.menuId" :value="item.menuId" :label="item.menuName" v-for="item in this.listData"></el-option>
					</el-select>-->
					<el-cascader expand-trigger="hover" :options="data" v-model="selectedOptions2" @change="handleChange">
				</el-cascader>
				</el-form-item>
				
				<el-form-item label="方法名">
					<el-input v-model="form.btnMethod"></el-input>
				</el-form-item>
				<!--<el-form-item label="日期时间">
                    <el-col :span="11">
                        <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
                    </el-col>
                    <el-col class="line" :span="2">-</el-col>
                    <el-col :span="11">
                        <el-time-picker type="fixed-time" placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
                    </el-col>
                </el-form-item>
                <el-form-item label="选择开关">
                    <el-switch on-text="" off-text="" v-model="form.delivery"></el-switch>
                </el-form-item>
                <el-form-item label="多选框">
                    <el-checkbox-group v-model="form.type">
                        <el-checkbox label="步步高" name="type"></el-checkbox>
                        <el-checkbox label="小天才" name="type"></el-checkbox>
                        <el-checkbox label="imoo" name="type"></el-checkbox>
                    </el-checkbox-group>
                </el-form-item>-->
				<!--<el-form-item label="单选框">
                    <el-radio-group v-model="form.resource">
                        <el-radio label="步步高"></el-radio>
                        <el-radio label="小天才"></el-radio>
                        <el-radio label="imoo"></el-radio>
                    </el-radio-group>
               </el-form-item>-->
				<el-form-item label="所在页面">
					<el-input type="textarea" v-model="form.btnUrl"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="onSubmit">提交</el-button>
					<el-button>取消</el-button>
				</el-form-item>
			</el-form>
		</div>

	</div>
</template>

<script>
	export default {
		data: function() {
			return {
				form: {
					btnName: '',
					btnAlias: '',
					menuId: '',
					btnMethod: '',
					btnUrl: '',
				},
				listData: [],
				cur_page: 1,
				total: 0,
				data:[],
				selectedOptions2:[]
			}
		},
		methods: {
			onSubmit() {
				this.$message.success('提交成功！');
				const self = this;
				if(self.form.menuName == '') {
					self.$message.error('菜单名称不能为空');
					return
				} else if(self.form.menuOrder == '') {
					self.$message.error('菜单顺序不能为空');
					return
				} else if(self.form.menuUrl == '') {
					self.$message.error('原url不能为空');
					return
				} else if(self.form.menuGourl == '') {
					self.$message.error('跳转url不能为空');
					return
				} else if(self.form.menuId == '') {
					self.$message.error('所属菜单不能为空');
					return
				}
				alert(JSON.stringify(self.form));
				ajaxRequest({
					url: "prvlg/saveorupdatebtn.do",
					data: {
						"btn": JSON.stringify(self.form)
					},
					async: true,
					success: function(dataJson) {
						if(dataJson.result == "successed") {
							self.$message.success("操作成功");
							self.from = {
								btnName: '',
								btnAlias: '',
								menuId: '',
								btnMethod: '',
								btnUrl: '',
							}
						}

					},
					error: function() {
						alert("系统繁忙");
					}
				});
			},
			handleChange(value){
				if (value.length == 2){
					this.form.menuId = value[1];
				} else{
					this.form.menuId = value[0];
				}
				
			},
			getData() {
				let self = this;

				ajaxRequest({
					url: "prvlg/queryconfiguremenu.do",
					data: {
						//						"pageNumber": self.cur_page,
						//						"condition": self.select_word
					},
					async: true,
					success: function(dataJson) {
						self.listData = dataJson.menuBtnList;
						var listo = [];

						for(var i = 0; i < dataJson.menuBtnList.length; i++) {
							var listi = {};
							listi["value"] = dataJson.menuBtnList[i].menuId;
							listi["label"] = dataJson.menuBtnList[i].menuName;
							var menuList = [];
							for(var j = 0; j < dataJson.menuBtnList[i].menuList.length; j++) {
								var listj = {};
								listj["value"] = dataJson.menuBtnList[i].menuList[j].menuId;
								listj["label"] = dataJson.menuBtnList[i].menuList[j].menuName;
								
//								if(dataJson.menuBtnList[i].menuList[j].operate == 1) {
//									self.checkedKey.push(dataJson.menuBtnList[i].menuList[j].menuId);
//								}
								menuList.push(listj);
							}
							if(dataJson.menuBtnList[i].operate == 1) {
								self.checkedKey.push(dataJson.menuBtnList[i].moduleId);
							}
							listi["children"] = menuList;
							listo.push(listi);
						}
						self.data = listo;
						for(var i = 0; i < dataJson.menuBtnList.length; i++) {
							//							dataJson.root[i].createDate = self.timeShowFormat(dataJson.root[i].createDate);
						}
						self.total = parseInt(dataJson.menuBtnList.length);
					},
					error: function() {
						alert("系统繁忙");
					}
				});

				if(process.env.NODE_ENV === 'development') {
					self.url = '/ms/table/list';
				};
				//              self.$axios.post(self.url, {page:self.cur_page}).then((res) => {
				//                  self.tableData = res.data.list;
				//              })
			},
		},
		mounted(){
			this.getData();
		}
	}
</script>
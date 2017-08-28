<template>
	<div>
		<div class="crumbs">
			<el-breadcrumb separator="/">
				<el-breadcrumb-item><i class="el-icon-date"></i> 用户管理</el-breadcrumb-item>
				<el-breadcrumb-item>新增菜单权限</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="form-box">
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item label="菜单名称">
					<el-input v-model="form.menuName"></el-input>
				</el-form-item>
				<el-form-item label="父菜单">
					<el-select v-model="form.parentId" placeholder="请选择">
						<el-option key="item.menuId" :value="item.menuId" :label="item.menuName" v-for="item in this.listData"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="菜单顺序">
					<el-input v-model="form.menuOrder"></el-input>
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
				<el-form-item label="原URL">
					<el-input type="textarea" v-model="form.menuUrl"></el-input>
				</el-form-item>
				<el-form-item label="跳转URL">
					<el-input type="textarea" v-model="form.menuGourl"></el-input>
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
					menuName: '',
					menuOrder: '',
					menuUrl: '',
					menuGourl: '',
					memuId: '',
					parentId: ''
				},
				listData: [],
				total: '',
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
				}
				ajaxRequest({
					url: "prvlg/saveorupdatemenu.do",
					data: {
						"menu": JSON.stringify(self.form)

					},
					async: true,
					success: function(dataJson) {
						if(dataJson.result == "successed") {
							self.$message.success("操作成功");
							self.from = {
								menuName: '',
								parentId: '',
								menuOrder: '',
								menuUrl: '',
								menuGourl: '',
								memuId: ''
							}
						}

					},
					error: function() {
						alert("系统繁忙");
					}
				});
			},
			getData() {
				let self = this;

				ajaxRequest({
					url: "prvlg/queryparentmenulist.do",
					data: {
						"pageNumber": self.cur_page,
						"condition": self.select_word
					},
					async: true,
					success: function(dataJson) {
						self.listData = dataJson.menuList;
						for(var i = 0; i < dataJson.menuList.length; i++) {
							//							dataJson.root[i].createDate = self.timeShowFormat(dataJson.root[i].createDate);
						}
						self.total = parseInt(dataJson.menuList.length);
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
			}
		},

		mounted() {
			this.getData();
		},
	}
</script>
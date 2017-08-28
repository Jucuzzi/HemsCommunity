<template>
	<div>
		<div v-show="show" class="check-info-dialog">
			<div class="info-outer-div">
				<div class="info-close-btn" @click="showDialog">
					<img width="20px" height="20px" src="../../../assets/image/information/delete.png">
				</div>
				<div class="role-outer-div">
					<div class="info-inner-div">
						<span class="info-title textcenter">新增按钮</span>
						<br />
						<br />
						<br />
						<br />
						<el-form ref="form" :model="form" label-width="80px">
							<el-form-item label="按钮名称">
								<el-input v-model="form.btnName"></el-input>
							</el-form-item>
							<el-form-item label="变量名">
								<el-input v-model="form.btnAlias"></el-input>
							</el-form-item>
							<el-form-item label="所在菜单">
								<el-cascader expand-trigger="hover" :options="data" v-model="selectedOptions2" @change="handleChange">
								</el-cascader>
							</el-form-item>

							<el-form-item label="方法名">
								<el-input v-model="form.btnMethod"></el-input>
							</el-form-item>
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
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data: function() {
			return {
				show:false,
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
				data: [],
				selectedOptions2: []
			}
		},
		methods: {
			showDialog: function() {
				this.show = !this.show;
			},
			onSubmit() {
				this.$message.success('提交成功！');
				const self = this;
				if(self.form.btnName == '') {
					self.$message.error('按钮名称不能为空');
					return
				} else if(self.form.btnAlias == '') {
					self.$message.error('按钮变量名不能为空');
					return
				} else if(self.form.menuId == '') {
					self.$message.error('按钮所属菜单');
					return
				} else if(self.form.method == '') {
					self.$message.error('按钮方法');
					return
				} else if(self.form.btnUrl == '') {
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
			handleChange(value) {
				if(value.length == 2) {
					this.form.menuId = value[1];
				} else {
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
		mounted() {
			this.getData();
		}
	}
</script>

<style scoped="scoped">
	.check-info-dialog {
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
	
	.info-outer-div {
		width: 70%;
		height: 90%;
		background-color: white;
		border-radius: 6px;
	}
	
	.info-inner-div {
		width: 80%;
		height: 100%;
		margin: 0px;
		padding: 0px;
	}
	
	.role-outer-div {
		width: 100%;
		height: 800px;
		display: flex;
		justify-content: center;
		align-items: center;
		overflow-y: auto;
	}
	
	.info-close-btn {
		width: 98%;
		height: 25px;
		display: flex;
		justify-content: flex-end;
		margin-right: 2%;
		margin-top: 2%;
	}
	
	.textcenter {
		text-align: center;
		display: inline-block;
	}
	
	.info-title {
		width: 80%;
		margin-left: 10%;
		font-size: 27px;
	}
	
	.info-detail-div {
		display: flex;
		width: 100%;
		margin-top: 2%;
		display: flex;
		justify-content: center;
	}
	
	.info-detail {
		font-size: 17px;
		white-space: nowrap;
		margin-right: 15px;
		line-height: 17px;
	}
	
	.info-content-div {
		width: 90%;
		margin-left: 5%;
		margin-top: 3%;
	}
</style>
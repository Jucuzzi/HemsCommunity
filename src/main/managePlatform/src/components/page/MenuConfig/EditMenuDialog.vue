<template>
	<div v-show="show" class="check-info-dialog">
		<div class="info-outer-div">
			<div class="info-close-btn" @click="showDialog">
				<img width="20px" height="20px" src="../../../assets/image/information/delete.png">
			</div>
			<div class="role-outer-div">
				<div class="info-inner-div">
					<span class="info-title textcenter">编辑菜单</span>
					<br />
					<br />
					<br />
					<br />
					<el-form ref="form" :model="form" label-width="80px">
						<el-form-item label="菜单名称">
							<el-input v-model="form.menuName"></el-input>
						</el-form-item>
						<el-form-item label="父菜单">
							<el-select v-model="form.parentId" placeholder="无">
								<el-option key="item.menuId" :value="item.menuId" :label="item.menuName" v-for="item in this.listData"></el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="菜单顺序">
							<el-input v-model="form.menuOrder"></el-input>
						</el-form-item>
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
		</div>
	</div>
</template>

<script>
	export default {
		props: ['MenuInfo'],
		data() {
			return {
				show: false,
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
			showDialog: function() {
				this.show = !this.show;
				this.form.menuId = this.MenuInfo.menuId;
				this.form.menuName = this.MenuInfo.menuName;
				this.form.menuOrder = this.MenuInfo.menuOrder;
				this.form.menuGourl = this.MenuInfo.menuGourl;
				this.form.menuUrl = this.MenuInfo.menuUrl;
				this.form.parentId = this.MenuInfo.parentId;
				if(this.form.parentId!=null){
				
				}
			},
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
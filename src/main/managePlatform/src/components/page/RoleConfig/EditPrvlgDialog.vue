<template>
	<div v-show="show" class="check-info-dialog">
		<div class="info-outer-div">
			<div class="info-close-btn" @click="showDialog">
				
				<img width="20px" height="20px" src="../../../assets/image/information/delete.png">
			</div>
			<div class="role-outer-div">
				<div class="info-inner-div">
					
					<br />
					<el-form ref="form" :model="form" label-width="80px">
						<el-form-item label="角色名称">
							<el-input v-model="form.roleName"></el-input>
						</el-form-item>
						<el-tree :data="data" node-key="id" :props="defaultProps" ref="tree" default-expand-all :check-strictly="true" show-checkbox @check-change="handleCheckChange">
						</el-tree>
						<br />
						<el-form-item label="日期时间">
							<el-col :span="11">
								<el-date-picker type="date" placeholder="选择日期" v-model="form.createDate" style="width: 100%;"></el-date-picker>
							</el-col>
						</el-form-item>
						<el-form-item label="描述">
							<el-input type="textarea" v-model="form.roleDesc"></el-input>
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
		props: ['originRole'],
		data() {
			return {
				show: false,
				form: {
					roleId: "",
					roleName: "",
					roleDesc: "",
					createDate: new Date()
				},
				menuBtnList: [],
				defaultProps: {
					children: 'children',
					label: 'label'
				},
				data: [],
				checkedKey: []
			}
		},
		updated() {
			
			
		},
		methods: {
			showDialog: function() {
				this.show = !this.show;
				this.data = this.originRole.data;
				this.form = this.originRole;
				this.form.createDate = new Date();
			},
			onSubmit() {
				this.$message.success('提交成功！');
				const self = this;
				if(self.form.roleName == '') {
					self.$message.error('角色名称不能为空');
					return
				} else if(self.form.createDate == '') {
					self.$message.error('创建日期不能为空');
					return
				} else if(self.form.roleDesc == '') {
					this.$message.error('描述不能为空');
					return
				}
				self.saveRolePrvlg();

			},
			
			setCheckedKey(checkedKey) {
				this.$refs.tree.setCheckedKeys([]);
				this.$refs.tree.setCheckedKeys(checkedKey);
			},
			timeShowFormat(time) {
				return time.substring(0, 4) + "-" +
					time.substring(4, 6) + "-" +
					time.substring(6, 8) + " " +
					time.substring(8, 10) + ":" +
					time.substring(10, 12) + ":" +
					time.substring(12, 14);
			},
			formatter(row, column) {
				return row.address;
			},
			filterTag(value, row) {
				return row.tag === value;
			},
			handleEdit(index, row) {
				this.$message('编辑第' + (index + 1) + '行');
			},
			handleDelete(index, row) {
				this.$message.error('删除第' + (index + 1) + '行');
			},
			handleSelectMenu: function(val) {
				this.menuBtnList = val;
				//				for(var i = 0;i< val.length;i++){
				//					this.menuBtnList.menuList
				//				}
			},
			change(val) {},
			handleCheckChange(data, checked, indeterminate) {
				console.log(data, checked, indeterminate);
			},
			handleNodeClick(data) {
				console.log(data);
			},
			loadNode(node, resolve) {
				if(node.level === 0) {
					return resolve([{
						name: 'region1'
					}, {
						name: 'region2'
					}]);
				}
				if(node.level > 3) return resolve([]);

				var hasChild;
				if(node.data.name === 'region1') {
					hasChild = true;
				} else if(node.data.name === 'region2') {
					hasChild = false;
				} else {
					hasChild = Math.random() > 0.5;
				}

				setTimeout(() => {
					var data;
					if(hasChild) {
						data = [{
							name: 'zone' + this.count++
						}, {
							name: 'zone' + this.count++
						}];
					} else {
						data = [];
					}

					resolve(data);
				}, 500);
			},
			saveRolePrvlg() {
				var menuBtnList = this.$refs.tree.getCheckedKeys();
				var moduleIds = "";
				var menuIds = "";
				var btnIds = "";
				for(var i = 0; i < menuBtnList.length; i++) {
					if(menuBtnList[i].match(/%(\S*)%/)) {
						btnIds += menuBtnList[i].match(/%(\S*)%/)[1]+",";
					} else {
						moduleIds += menuBtnList[i] + ",";
					}
					//					if(menuBtnList[i].checkbox) {
					//						moduleIds += menuBtnList[i].moduleId + ",";
					//						var menuList = menuBtnList[i].menuList;
					//						for(var j = 0; j < menuList.length; j++) {
					//							if(menuList[j].checkbox) {
					//								menuIds += menuList[j].menuId + ",";
					//								var btnList = menuList[j].btnList;
					//								for(var k = 0; k < btnList.length; k++) {
					//									if(btnList[k].checkbox) {
					//										btnIds += btnList[k].btnId + ",";
					//									}
					//								}
					//							}
					//						}
					//					}
				}
				var prvlgList = moduleIds.substring(0, moduleIds.length - 1) + "," +
					(menuIds.substring(0, menuIds.length - 1)) + "&" +
					(btnIds.substring(0, btnIds.length - 1));
				var self = this;

				var role = {};
				role.roleId = self.form.roleId;
				role.roleName = self.form.roleName;
				role.roleDesc = self.form.roleDesc;
				role.createDate = self.form.createDate;
				ajaxRequest({

					url: "prvlg/saveroleprvlg.do",
					data: {
						"roleId": self.form.roleId,
						"prvlgList": prvlgList
					},
					async: false,
					success: function(dataJson) {

						ajaxRequest({

							url: "prvlg/saveorupdaterole.do",
							data: {
								"role": JSON.stringify(role),
							},
							async: false,
							success: function(dataJson) {
								self.showDialog();
							},
							error: function() {
								swalErrorInfo("系统繁忙");
							}
						});
					},
					error: function() {
						swalErrorInfo("系统繁忙");
					}
				});
			}
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
		height: 880px;
		background-color: white;
		border-radius: 6px;
	}
	
	.info-inner-div {
		width: 80%;
		height: 100%;
		margin: 0px;
		padding:0px;
	}
	
	.info-close-btn {
		width: 98%;
		height: 25px;
		display : flex;
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
	
	.role-outer-div{
		width: 100%;height:800px;display: flex;
		justify-content:center;
        align-items:center;
        overflow-y:auto;
	}
</style>
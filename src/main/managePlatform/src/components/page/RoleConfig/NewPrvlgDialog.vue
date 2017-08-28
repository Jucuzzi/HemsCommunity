<template>
	<div v-show="show" class="check-info-dialog">
		<div class="info-outer-div">
			<div class="info-close-btn" @click="showDialog">
				<img width="20px" height="20px" src="../../../assets/image/information/delete.png">
			</div>
			<div class="role-outer-div">
				<div class="info-inner-div">
					<!--<div class="form-box">-->
						<el-form ref="form" :model="form" label-width="80px">
							<el-form-item label="角色名称">
								<el-input v-model="form.roleName"></el-input>
							</el-form-item>
							<el-tree :data="data" node-key="id" :props="defaultProps" ref="tree" default-expand-all show-checkbox @check-change="handleCheckChange">
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
					<!--</div>-->
				</div>

			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				show: false,
				url: '../../../static/vuetable.json',
				tableData: [],
				cur_page: 1,
				multipleSelection: [],
				select_cate: '',
				select_word: '',
				total: 0,
				infocontent: {

				},
				form: {
					roleName: '',
					roleId: '',
					createDate: new Date(),
					roleDesc: ''
				},
				menuBtnList: [],
				regions: [{
					'name': 'region1'
				}, {
					'name': 'region2'
				}],
				defaultProps: {
					children: 'children',
					label: 'label'
				},
				count: 1,
				data: []
			}
		},
		created() {
			this.getData();
		},
		methods: {
			showDialog: function() {
				this.show = !this.show;
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
				ajaxRequest({
					url: "prvlg/saveorupdaterole.do",
					data: {
						"role": JSON.stringify(self.form)

					},
					async: true,
					success: function(dataJson) {
						if(dataJson.result == "successed") {
							self.$message.success("操作成功");
							self.form = {
								roleName: '',
								roleId: '',
								createDate: new Date(),
								roleDesc: ''
							};
						}

					},
					error: function() {
						alert("系统繁忙");
					}
				});

			},
			handleCurrentChange(val) {
				this.cur_page = val;
				this.getData();
			},
			//			insetInfo() {
			//				this.infocontent["roleName"] = document.getElementById("info_title_input").value;
			//				this.infocontent["releaseTime"] = this.getNowFormatDate();
			//				this.infocontent["author"] = document.getElementById("info_user_input").value;
			//				this.infocontent["source"] = document.getElementById("info_resouce_input").value;
			//				this.infocontent["detailContent"] = '' + this.content;
			//				this.infocontent["userId"] = '1561';
			//				this.infocontent["type"] = '1';
			//				this.infocontent["imgPath"] = this.uploadImgBase;
			//
			//			},
			getData() {
				let self = this;

				ajaxRequest({
					url: "prvlg/loadprvlg.do",
					data: {
						"roleId": "1",
						"pageNumber": self.cur_page,
						"condition": self.select_word
					},
					async: true,
					success: function(dataJson) {
						//						 dataJson = {"menuBtnList":[{"menuId":"2","menuName":"基础表格","btnList":[{"btnName":"asdfasdf","btnId":"1"},{"btnName":"asdfasdf","btnId":"1"}]},{"menuId":"8","menuName":"图表","btnList":[{"btnName":"asdfasdf","btnId":"1"},{"btnName":"asdfasdf","btnId":"1"}]}]}
						self.tableData = dataJson.menuBtnList;
						var x = 1;
						var listo = [];

						for(var i = 0; i < dataJson.menuBtnList.length; i++) {
							var listi = {};
							listi["id"] = dataJson.menuBtnList[i].moduleId;
							listi["label"] = dataJson.menuBtnList[i].moduleName;
							x++
							var menuList = [];
							for(var j = 0; j < dataJson.menuBtnList[i].menuList.length; j++) {
								var listj = {};
								listj["id"] = dataJson.menuBtnList[i].menuList[j].menuId;
								listj["label"] = dataJson.menuBtnList[i].menuList[j].menuName;
								x++
								var btnlist = [];
								for(var k = 0; k < dataJson.menuBtnList[i].menuList[j].btnList.length; k++) {
									var listk = {};
									listk["id"] = "%" + dataJson.menuBtnList[i].menuList[j].btnList[k].btnId + "%";
									listk["label"] = dataJson.menuBtnList[i].menuList[j].btnList[k].btnName;
									x++;
									btnlist.push(listk);

								}
								listj["children"] = btnlist;
								menuList.push(listj);
							}
							listi["children"] = menuList;
							listo.push(listi);
						}
						self.data = listo;
						//						self.data = JSON.stringify(listo);
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
				alert(JSON.stringify(val));
				//				for(var i = 0;i< val.length;i++){
				//					this.menuBtnList.menuList
				//				}
			},
			change(val) {
				alert(JSON.stringify(val));
			},
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
			getCheckedNodes() {
				//      console.log(this.$refs.tree.getCheckedNodes());
				alert(JSON.stringify(this.$refs.tree.getCheckedNodes()));
				alert(JSON.stringify(this.$refs.tree.getCheckedKeys()));
			},
			saveRolePrvlg() {
				var menuBtnList = this.$refs.tree.getCheckedKeys();
				var moduleIds = "";
				var menuIds = "";
				var btnIds = "";
				for(var i = 0; i < menuBtnList.length; i++) {
					if(menuBtnList[i].match(/%(\S*)%/)) {
						btnIds += menuBtnList[i].match(/%(\S*)%/)[1];
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
				alert(JSON.stringify(prvlgList));
				//				layer.load(2, {
				//					shade: [0.1, '#fff'], //0.1透明度的白色背景
				//					time: 10 * 1000
				//				});
				ajaxRequest({

					url: "prvlg/saveroleprvlg.do",
					data: {
						"roleId": "1",
						"prvlgList": prvlgList
					},
					async: false,
					success: function(dataJson) {
						alert("成功啦!");
						//						if(dataJson.result == "successed") {
						//							router.push("roleInfoList");
						//						} else {
						//							swal({
						//									title: "提   示",
						//									text: dataJson.desc,
						//									type: "warning",
						//									showCancelButton: false,
						//									confirmButtonColor: '#DD6B55',
						//									confirmButtonText: '确定'
						//								},
						//								function() {
						//									router.push("roleInfoList");
						//								});
						//						}
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
		height: 90%;
		background-color: white;
		border-radius: 6px;
	}
	.info-inner-div {
		width: 80%;
		height: 100%;
		margin: 0px;
		padding:0px;
	}
	
	.role-outer-div{
		width: 100%;height:800px;display: flex;
		justify-content:center;
        align-items:center;
        overflow-y:auto;
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
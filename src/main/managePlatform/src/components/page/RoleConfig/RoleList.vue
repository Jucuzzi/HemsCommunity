<template>
	<div class="table">
		<div class="crumbs">
			<el-breadcrumb separator="/">
				<el-breadcrumb-item><i class="el-icon-menu"></i> 用户管理</el-breadcrumb-item>
				<el-breadcrumb-item>角色管理</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="handle-box">
			<el-button class="handle-del mr10">批量删除</el-button>
			<el-select v-model="select_cate" placeholder="筛选省份" class="handle-select mr10">
				<el-option key="1" label="广东省" value="1"></el-option>
				<el-option key="2" label="湖南省" value="2"></el-option>
			</el-select>
			<el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>
			<el-button type="primary" icon="search">搜索</el-button>
			<el-button type="primary" @click="newRole()" >新增角色</el-button>
		</div>
		<el-table :data="tableData" border style="width: 100%" ref="multipleTable" @selection-change="handleSelectionChange">
			<el-table-column type="selection" width="55"></el-table-column>
			<el-table-column prop="roleName" label="权限名" sortable width="150">
			</el-table-column>
			<el-table-column prop="roleDesc" label="权限详情" width="400">
			</el-table-column>
			<el-table-column prop="createDate" label="创建日期">
			</el-table-column>
			<el-table-column label="操作" width="180">
				<template scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		<div class="pagination">
			<el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="total">
			</el-pagination>
		</div>
		<EditPrvlgDialog ref="EditPrvlgDialog" :originRole="role"></EditPrvlgDialog>
		<NewPrvlgDialog ref="NewPrvlgDialog"></NewPrvlgDialog>
	</div>
</template>

<script>
	import EditPrvlgDialog from './EditPrvlgDialog.vue'
	import NewPrvlgDialog from './NewPrvlgDialog.vue'
	export default {
		data() {
			return {
				url: '../../../static/vuetable.json',
				tableData: [],
				cur_page: 1,
				multipleSelection: [],
				select_cate: '',
				select_word: '',
				total: 0,
				role: {},
				checkedKey: []
			}
		},
		components: {
			EditPrvlgDialog,
			NewPrvlgDialog,
		},
		created() {
			this.getData();
		},
		methods: {
			newRole(){
				this.$refs.NewPrvlgDialog.showDialog();
			},
			handleCurrentChange(val) {
				this.cur_page = val;
				this.getData();
			},
			getData() {
				let self = this;

				ajaxRequest({
					url: "prvlg/queryrolelistall.do",
					data: {
						"pageNumber": self.cur_page,
						"condition": self.select_word
					},
					async: true,
					success: function(dataJson) {
						self.tableData = dataJson.root;
						for(var i = 0; i < dataJson.root.length; i++) {
							//							dataJson.root[i].createDate = self.timeShowFormat(dataJson.root[i].createDate);
						}
						self.total = parseInt(dataJson.total);
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
				//          		this.$router.push({path: "/EditPrvlg", query: {roleId: row.roleId,roleName:row.roleName,roleDesc:row.roleDesc}}) 
				this.role.roleId = row.roleId;
				this.role.roleName = row.roleName;
				this.role.roleDesc = row.roleDesc;

				//获取要放进去的权限列表
				let self = this;
				ajaxRequest({
					url: "prvlg/loadprvlg.do",
					data: {
						"roleId": row.roleId,
					},
					async: true,
					success: function(dataJson) {
						self.checkedKey = [];
						//						 dataJson = {"menuBtnList":[{"menuId":"2","menuName":"基础表格","btnList":[{"btnName":"asdfasdf","btnId":"1"},{"btnName":"asdfasdf","btnId":"1"}]},{"menuId":"8","menuName":"图表","btnList":[{"btnName":"asdfasdf","btnId":"1"},{"btnName":"asdfasdf","btnId":"1"}]}]}
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
									if(dataJson.menuBtnList[i].menuList[j].btnList[k].operate == 1) {
										self.checkedKey.push("%" + dataJson.menuBtnList[i].menuList[j].btnList[k].btnId + "%");
									}
									x++;
									btnlist.push(listk);
								}
								if(dataJson.menuBtnList[i].menuList[j].operate == 1) {
									self.checkedKey.push(dataJson.menuBtnList[i].menuList[j].menuId);
								}
								listj["children"] = btnlist;
								menuList.push(listj);
							}
							if(dataJson.menuBtnList[i].operate == 1) {
								self.checkedKey.push(dataJson.menuBtnList[i].moduleId);
							}
							listi["children"] = menuList;
							listo.push(listi);
						}
						self.role.data = listo;
						self.role.prvlgList = self.checkedKey;
						self.$refs.EditPrvlgDialog.setCheckedKey(self.checkedKey);
						self.$refs.EditPrvlgDialog.showDialog();
						self.$message('编辑第' + (index + 1) + '行');
					},

					error: function() {
						alert("系统繁忙");
					}
				});

			},
			handleDelete(index, row) {
				var self = this;
				ajaxRequest({
					url: "prvlg/delrolelist.do",
					data: {
						"roleIds": row.roleId
					},
					async: true,
					success: function(dataJson) {
						if(dataJson.result == "successed") {
							self.$message.success("操作成功");
							self.getData();
						} else {
							this.$message.error('操作失败');
						}
					},
					error: function() {
						alert("系统繁忙");
					}
				});
			},
			handleSelectionChange: function(val) {
				this.multipleSelection = val;
			}
		}
	}
</script>

<style scoped>
	.handle-box {
		margin-bottom: 20px;
	}
	
	.handle-del {
		border-color: #bfcbd9;
		color: #999;
	}
	
	.handle-select {
		width: 120px;
	}
	
	.handle-input {
		width: 300px;
		display: inline-block;
	}
</style>
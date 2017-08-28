<template>
	<div class="table">
		<div class="crumbs">
			<el-breadcrumb separator="/">
				<el-breadcrumb-item><i class="el-icon-menu"></i> 角色管理</el-breadcrumb-item>
				<el-breadcrumb-item>按钮权限管理</el-breadcrumb-item>
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
			<el-button type="primary" @click="newMenu()">新增菜单</el-button>
		</div>
		<el-table :data="tableData" border style="width: 100%" ref="multipleTable" @selection-change="handleSelectionChange">
			<el-table-column type="selection" width="55"></el-table-column>
			<el-table-column prop="btnName" label="按钮名" sortable width="150">
			</el-table-column>
			<el-table-column prop="btnAlias" label="按钮别名" width="200">
			</el-table-column>
			<el-table-column prop="btnUrl" label="按钮跳转URL"width="200">
			</el-table-column>
			<el-table-column prop="menuName" label="所属菜单">
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
		<NewBtnDialog ref="NewBtnDialog"></NewBtnDialog>
		<EditBtnDialog ref="EditBtnDialog" :BtnInfo="selectBtn"></EditBtnDialog>
	</div>
</template>

<script>
	import NewBtnDialog from './NewBtnDialog.vue'
	import EditBtnDialog from './EditBtnDialog.vue'
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
				selectBtn:[],
			}
		},
		created() {
			this.getData();
		},
		components: {
			NewBtnDialog,
			EditBtnDialog,
		},
		methods: {
			newMenu() {
				this.$refs.NewBtnDialog.showDialog();
			},
			handleCurrentChange(val) {
				this.cur_page = val;
				this.getData();
			},
			getData() {
				let self = this;

				ajaxRequest({
					url: "prvlg/queryconfigurebtn.do",
					data: {
						"pageNumber": self.cur_page,
						"condition": self.select_word
					},
					async: true,
					success: function(dataJson) {
						for(var i = 0; i < dataJson.menuBtnList.length; i++) {
								for(var j = 0; j < dataJson.menuBtnList[i].btnList.length; j++) {
									dataJson.menuBtnList[i].btnList[j].menuName = dataJson.menuBtnList[i].menuName;
									self.tableData.push(dataJson.menuBtnList[i].btnList[j]);
								}

//							}
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
				this.selectBtn.btnId = row.btnId;
				this.selectBtn.btnName = row.btnName;
				this.selectBtn.btnAlias = row.btnAlias;
				this.selectBtn.btnUrl = row.btnUrl;
				this.selectBtn.menuId = row.menuId;
				this.selectBtn.btnMethod = row.btnMethod;
				this.$refs.EditBtnDialog.showDialog();
				this.$message('编辑第' + (index + 1) + '行');
			},
			handleDelete(index, row) {
				var self = this;
				ajaxRequest({
					url: "prvlg/delmenuorbtn.do",
					data: {
						"id": row.menuId,
						"flag": "1"
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
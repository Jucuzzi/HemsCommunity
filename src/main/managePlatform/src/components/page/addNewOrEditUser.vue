<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-date"></i> 用户信息</el-breadcrumb-item>
                <el-breadcrumb-item>新增用户</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="form-box">
            <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="用户名称">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="昵称1">
                    <el-input v-model="form.nickName"></el-input>
                </el-form-item>
                <el-form-item label="设置角色">
                    <el-select v-model="form.userType" placeholder="请选择" @change="handleSelectionChange">
                       <el-option v-for="item in options" :key="item.roleId" :label="item.roleName" :value="item.roleId"></el-option>
                    </el-select>
                </el-form-item>
  
                <!--<el-form-item label="用户头像">
                		<el-upload
  class="upload-demo"
  action="https://jsonplaceholder.typicode.com/posts/"
  :on-preview="handlePreview"
  :on-success="handleSuccess"
  
  :on-remove="handleRemove"
  :file-list="fileList2"
  list-type="picture">
  <el-button size="small" type="primary">点击上传</el-button>
  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
</el-upload>
                	</el-form-item>-->
  
                
                <el-form-item label="备注">
                    <el-input type="textarea" v-model="form.desc"></el-input>
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
        data(){
            return {
            	infocontent:{},
            	uploadImgBase:"",
            	roleIdList:"",
            	 imageUrl: '',
            	 fileList2: [{name: 'food.jpeg', url: 'blob:http://localhost:8075/16d07ec7-98cd-4a21-9a19-b766d9d33ac1'}]
            }
        },
        created(){
            this.getRoleData();
        },
        methods(){
        		form = this.$route.query;
        },
         data: function(){
            return {
                form: {
                    name:  this.$route.query.name,
                    nickName:  this.$route.query.nickName,
                    userType: this.$route.query.userType,
                    userIconPath:'123',
                    id: this.$route.query.id
                },
                options:[
                		
                ]
            }
        },
        
        methods: {
            onSubmit() {
             	const self = this;
             if (self.form.name == ''){
            		 self.$message.error('用户名称不能为空');
            		 return
            	} else if (self.form.nickName == '') {
            		 self.$message.error('用户昵称不能为空');
            		 return
            	} else if (this.form.userType == '') {
            		 this.$message.error('角色不能为空');
            		 return
            	}
               
            
            ajaxRequest({
					url : "prvlg/saveOrUpdateUser.do",
					data : {
						 name:  self.form.name,
                    		 nickName:  self.form.nickName,
                    		 userType: self.form.userType,
                    		 userIconPath:self.imageUrl,
                    		 id: self.form.id,
                    		 roleIdList:self.roleIdList
					},
					async : true,
					success : function(dataJson) {
						if (dataJson.result =="successed") {
							self.$message.success("操作成功");

						}
						
					},
					error : function() {
						alert("系统繁忙");
					}
				});
            
        },
        getRoleData(){
        			 let self = this;
        		 		ajaxRequest({
					url : "prvlg/queryrolelistall.do",
					async : true,
					success : function(dataJson) {

						self.options = dataJson.root;
					
					},
					error : function() {
						alert("系统繁忙");
					}
				});
        },
         handleSelectionChange: function(ele){
            alert(ele);
            this.roleIdList = ele;
         },
         
         handleSuccess(res, file) {
         	 console.log(file);
         	  console.log(res);
        		this.imageUrl = URL.createObjectURL(file.raw);
      	},
      	beforeUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
       handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
        alert(URL.createObjectURL(file.raw));
      }
      
    }
  }
</script>
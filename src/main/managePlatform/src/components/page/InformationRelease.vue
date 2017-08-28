<template>
	<div id="">
		<div class="info-title-div">
			<span class="info-title-text">资讯标题：</span>
            <input id="info_title_input" class="info-title-input"></input>
		</div>
		<div class="info-title-div">
			<span class="info-title-text">作<span class="kongge-span"></span>者：</span>
            <input id="info_user_input" class="info-title-input"></input>
		</div>
		<div class="info-title-div">
			<span class="info-title-text">来<span class="kongge-span"></span>源：</span>
            <input id="info_resouce_input" class="info-title-input"></input>
		</div>
		<div class="upload-img-div"> 
			<span class="info-title-text">资讯封面：</span>
			<img id="upload_img" v-show="src!=''" class="upload-img" :src="src" alt="">
	       <input value="选择图片" v-on:change="uploadImg" id="upload" name="file" accept="image/*" type="file" style=""/>
		</div>
		
		<quill-editor id="editor_input" ref="myTextEditor" v-model="content" :config="editorOption"></quill-editor>
                 <el-button class="editor-btn" type="primary" @click="preview">预览</el-button>
        <el-button class="editor-btn" type="primary" @click="submit">发布</el-button>
 		
		<check-dialog ref="checkdialog" :infocontent="infocontent"></check-dialog>
	</div>
</template>

<script>
	import { quillEditor } from 'vue-quill-editor';
	import checkDialog from './information/checkDialog.vue'
	import axios from 'axios'
	export default {
        data() {
            return {
            	content:"",
            	infocontent:{},
            	uploadImgBase:"",
            	src: '../../../static/image/information/picture_error.png',
            }
        },
        mounted(){
//      	document.getElementById("upload").change(function () { 	        		
//      		var objUrl = this.getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径 
//      		if (objUrl) { 
//      			document.getElementById('upload_img').attr("src", objUrl) ; //将图片路径存入src中，显示出图片
//      		} 
//      	});
     
        },
         components: {
            quillEditor,
            checkDialog,
        },
        methods: {
            onEditorChange({ editor, html, text }) {
                this.content = html;
            },
            submit(){
            	const self = this;
                self.insetInfo();
                if (self.infocontent.title == ''){
            		 self.$message.error('标题不能为空');
            		 return
            	} else if (self.content == '') {
            		 self.$message.error('内容不能为空');
            		 return
            	} else if (this.infocontent.author == '') {
            		 this.$message.error('作者不能为空');
            		 return
            	} else if (this.uploadImgBase == '') {
            		 this.$message.error('资讯封面不能为空');
            		 return
            	}

				ajaxRequest({
					url : "addinformation",
					data : self.infocontent,
					async : true,
					success : function(dataJson) {
						if (dataJson.result =="successed") {
							self.$message.success("操作成功");
							document.getElementById("info_title_input").value = "";
							document.getElementById("info_user_input").value = "";
							document.getElementById("info_resouce_input").value="";
						}
						
					},
					error : function() {
						alert("系统繁忙");
					}
				});
		    	
            },
            preview(){
            	this.insetInfo();
            	if (this.infocontent.title == ''){
            		 this.$message.error('标题不能为空');
            		 return
            	} else if (this.content == '') {
            		 this.$message.error('内容不能为空');
            		 return
            	} else if (this.infocontent.author == '') {
            		 this.$message.error('作者不能为空');
            		 return
            	} else if (this.uploadImgBase == '') {
            		 this.$message.error('资讯封面不能为空');
            		 return
            	}
            	this.$refs.checkdialog.showDialog();
            },
            insetInfo() {
            	this.infocontent["title"] = document.getElementById("info_title_input").value;
            	this.infocontent["releaseTime"] = this.getNowFormatDate(); 
            	this.infocontent["author"] = document.getElementById("info_user_input").value;
            	this.infocontent["source"] = document.getElementById("info_resouce_input").value;
            	this.infocontent["detailContent"] = ''+this.content;
            	this.infocontent["userId"] = '1561';
            	this.infocontent["type"] = '1';
            	this.infocontent["imgPath"] = this.uploadImgBase;
            	
            },
            getNowFormatDate(){
            	var date = new Date();
			    var seperator1 = "-";
			    var seperator2 = ":";
			    var month = date.getMonth() + 1;
			    var strDate = date.getDate();
			    var str
			    if (month >= 1 && month <= 9) {
			        month = "0" + month;
			    }
			    if (strDate >= 0 && strDate <= 9) {
			        strDate = "0" + strDate;
			    }
			    var currentdate = date.getFullYear() + month  + strDate
			            + "" + this.formatNum(date.getHours()) + this.formatNum(date.getMinutes())
			             + this.formatNum(date.getSeconds());
			    return currentdate;
            },
            uploadImg(){
            	var self = this;
            	var file = document.getElementById('upload').files[0];
            	if (window.FileReader) {    
		            var reader = new FileReader();    
		            reader.readAsDataURL(file);    
		            //监听文件读取结束后事件    
		            reader.onloadend = function (e) {    
		                self.showXY(e.target.result,file.fileName);    
		            };    
		        }    
            },
            showXY(source){    
			    var img = document.getElementById("upload_img");    
			    img.src = source;    
			    this.uploadImgBase = source;
			},
			formatNum(num){
				return num < 10 ? '0'+num : num;
			}
        },
        computed: {
            editor() {
                return this.$refs.myTextEditor.quillEditor;
            }
        }
    }
</script>

<style scoped="scoped">
	.editor-btn{
        margin-top: 15px;
    }
    .info-title-div{
    	display: flex;
    	flex-direction: row;
		align-items: center;
		margin-bottom: 10px;
    }
    .info-title-text{
    	font-size: 18px;
    }
    .info-title-input{
    	height: 25px;
    	margin-left: 10px;
    	width: 50%;
    	
    }
    .upload-img{
        width:100px;
        height: 100px;
        margin: 20px;
    }
    .upload-img-div{
    	display: flex;
    	flex-direction: row;
		align-items: center;
		margin-bottom: 10px;
    	margin-bottom: 20px;
    }
    .upload-btn{
    	width: 80px;
    }
    .kongge-span{
    	padding-left: 36px;
    }
</style>
function ajaxRequest(obj){
	var urlhead="http://localhost:8080/HemsCommunity/";
//	var urlhead="http://localhost:8080/MicroGridDemo/";
//	var urlhead="http://10.10.11.200:8080/MicroGridDemo/";
//	var urlhead="http://172.24.43.11:8080/MicroGridDemo/";
//	var urlhead="http://10.10.11.92:8090/MicroGridDemo/";

	$.ajax({
		url : urlhead+obj.url,
		type : "post",
		dataType : "json",
		data : obj.data?obj.data:{},
		async : obj.async?true:false,
		success : obj.success,
		error : obj.error?obj.error:function() {swalErrorInfo("系统繁忙");},
		xhrFields:{
			withCredentials:true
		}
	});
}

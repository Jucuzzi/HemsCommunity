function ajaxRequest(obj){
//	var urlhead="";
	var urlhead="http://localhost:8080/HemsCommunity/";
	$.ajax({
		url : urlhead+obj.url,
		type : "post",
		dataType : "json",
		data : obj.data?obj.data:{},
		async : obj.async?true:false,
		success : obj.success,
		error : obj.error?obj.error:function() {swalErrorInfo("系统繁忙");}
	});
}


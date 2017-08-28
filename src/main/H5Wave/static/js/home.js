
function getStraregy(){
	var listContent = document.querySelector('#list-frandsViews');
	alert('查看攻略');
	for (var i=2;i<30;i++){

	  var template = '<li class="tableView-cell-frands"><div class="userInfo"><div class="selfTitleImage">'+
	  				'<img src="../../static/img/userImg.jpg" id="theShow"/></div>'+
	  				'<div class="selfName">德玛西亚</div>'+
	  				'<div class="numOfElec">'+
	  				'本月已收集谷电能量140g</div>'+
          			'</div></li>'
  // 向ul容器中添加内容
  listContent.innerHTML = listContent.innerHTML + template;
}
}

function getRedPacket(){
	alert('领取红包');
}

function getEnage(){
	alert('获取好友能量');
}




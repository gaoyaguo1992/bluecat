function isNotEmpty(content){
	if(content == 'null' || content == null || content =='undefined' || content == undefined || $.trim(content) == '')
		{return false;}
	else
		{return true;}
}
var rows = 10;
//未使用设备
var howLongNotUseDay = 7;
function notUsecharges(page){
	//1.开启屏蔽层
	startShieldLayer();
	$.JsonSRpc(reqUrl+"/wx/beyondSevenDaysNotUseData",{
		start:(page-1)*rows,
		rows:rows,
		howLongNotUse:howLongNotUseDay,
		storeName :$("#storeName").val(),
		storeAddress :$("#storeAddress").val(),
		merchantId :$("#merchantId").val()
	},function(data){
		//2.结束..
		finishShieldLayer();
		if(data.result!="success"){
			message(data.message);
			return;
		}
		if(page==1){
		}
		$("#chargesNum").text(data.chargerNum);
		data = data.chargerBeyondThreeDaysNotUseInfoBOs;
		$("#more").remove();
		for(var i in data){
			$("#notUsecharges").append(
			    '<div class="devinfor">'+
			    	'<h2>设备编号：'+data[i].deviceId+'</h2>'+
		        	'<h2>充器编号：'+data[i].chargerId+'</h2>'+
		        	'<p>上次使用时间：'+data[i].lastUsedTimeFormat+'（<span>'+data[i].howLongNotUse+'天未使用</span>）</p>'+	
		        	'<p>终端名称：'+data[i].storeName+' '+data[i].onlineMerchantId+'</p>'+	        	
		        	'<p>终端位置：'+data[i].storeAddress+'</p>'+
		        	'<p>加  盟  商：'+data[i].allianceBusinessCn+' '+data[i].allianceBusinessId+'</p>'+
		        	'<p>联系电话：'+data[i].telNo+'</p>'+
	        	'</div>'
        	);
		}
		
		if(data.length==rows){
			$("#notUsecharges").append("<a id='more' href='javascript:void(0)' onclick='notUsecharges("+(page+1)+")'>点击获取更多</a>");
		}
	},null,function(obj){
		//2.结束..
		finishShieldLayer();
	});
}
/**
 * 开启屏蔽层.
 */
startShieldLayer = function() {
	$('#myModal').modal("show");
}
/**
 * 结束屏蔽层.
 */
finishShieldLayer = function() {
	$('#myModal').modal("hide");
}
//
$(function(){
	$("#search").click(function(){
		howLongNotUseDay = $("#howLongNotUse").val();
		$("#notUsecharges .devinfor").remove();
		notUsecharges(1);
	});
	notUsecharges(1);
});
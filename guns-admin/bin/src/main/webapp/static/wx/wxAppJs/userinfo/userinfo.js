
var openid;

$(function(){
	
	openid = sessionStorage.openid;
	//openid="oKeSgsyj4g2STo00usHDkziFTsN4";

	//显示我的信息start by yhr
	$.JsonSRpc(reqUrl+"userinfo/showUserInfo.htm",{
		openid:openid?openid:"-1"
	},function(data){
		if(data.responseInfo==null){
			
		}else{
			$("#userId").text(data.responseInfo.userId);
			$("#userName").text(data.responseInfo.userName);
			$("#telNo").text(data.responseInfo.telNo);
			if(data.responseInfo.shortName!=null){
				$("#shortName").text(data.responseInfo.shortName);
			}
			if(data.responseInfo.auditStatus!=null){
				$("#auditStatus").text(data.responseInfo.auditStatus);
			}
			if(data.result==="success"){
				$("#scan_userinfo-items").click(function(){
					location.href="userinfo-items.html";
				});
			}else if(data.result==="notpass"){
				$("#scan_userinfo-items").click(function(){
					message(data.message);
				});
			}else{
				$("#scan_userinfo-items").click(function(){
					message(data.message);
				});
			}
			
		}
	});
	
	//显示我的信息end  by yhr
	
});







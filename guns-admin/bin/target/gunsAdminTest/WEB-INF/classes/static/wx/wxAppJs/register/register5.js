$(function(){
	/*
	 * 根据微信号取数据，如果有显示，没有就放空
	 */
	var openid=sessionStorage.openid;
	if(""==openid||null==openid){
		
	}
	else{
		$.JsonRpc(
				"",
				new dataArea('register', 'getCustInfo',{share:openid}
				),
				function(data){
					if(data.result == "success"){	
						$("#corporation").val(data.responseInfo.corporation);
						$("#certificateNumber").val(data.responseInfo.encryptCertificateNumber);
						$("#corporationPhone").val(data.responseInfo.corporationPhone);
						$("#corporationTel").val(data.responseInfo.corporationTel);
						$("#dealerName").val(data.responseInfo.userName);
						$("#dealerCertificateNumber").val(data.responseInfo.certificateEncryptNumber);
						$("#dealerPhone").val(data.responseInfo.userTelNo);
					}else {
//						top.messageShow(data.message); 
					}
				});
		
	}
})

function goNext(){
	clickNext();
	if($(".info").length>0){
		return false;
	}
	var param = {};
	param.userName = $("#dealerName").val();
	param.dealerCertificateNumber = $("#dealerCertificateNumber").val();
	param.userTelNo = $("#dealerPhone").val();
	param.corporation = $("#corporation").val();
	param.certificateNumber = $("#certificateNumber").val();
	param.corporationPhone = $("#corporationPhone").val();
	param.corporationTel = $("#corporationTel").val();
	param.step='5';
	param.share=sessionStorage.openid;
	$.JsonRpc(
			"",
			new dataArea('register', 'registerCommit', 
					param
			),
			function(data){
				if(data.result == "success"){	
					location.href="register6.html";
//					top.messageShow(data.message);
				}else {
					alert(data.message); 
				}
			});
	
}
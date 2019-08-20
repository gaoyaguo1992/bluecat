// JavaScript Document
$(function(){
	var openid;
	openid = sessionStorage.openid;
	//openid="oKeSgsyj4g2STo00usHDkziFTsN4";
	

	//显示我的信息start by yhr
	$.JsonSRpc(reqUrl+"userinfo/showUserInfo.htm",{
		openid:openid?openid:"-1"
	},function(data){
		if(data.responseInfo==null){
			
		}else{
			$("#mycustName").text(data.responseInfo.custName);
			$("#registAddr").text(data.responseInfo.registAddr);
			$("#address").text(data.responseInfo.address);
			$("#postcode").text(data.responseInfo.postcode);
			$("#contactPhone").text(data.responseInfo.contactPhone);
			$("#fax").text(data.responseInfo.fax);
			$("#email").text(data.responseInfo.email);
			$("#recCode").text(data.responseInfo.recCode);
			
			$("#dealerName").text(data.responseInfo.dealerName);
			$("#dealerCertificateNumber").text(data.responseInfo.dealerCertificateNumber);
			$("#dealerPhone").text(data.responseInfo.dealerPhone);
			
			$("#corporation").text(data.responseInfo.corporation);
			$("#certificateNumber").text(data.responseInfo.certificateNumber);
			$("#corporationPhone").text(data.responseInfo.corporationPhone);
			$("#corporationTel").text(data.responseInfo.corporationTel);
			
			$("#accountName").text(data.responseInfo.accountName);
			$("#bankNo").text(data.responseInfo.bankNo);
			$("#accountNo").text(data.responseInfo.accountNo);
			alert(data.responseInfo.creditCode);
			if(data.responseInfo.creditCode!=null&&data.responseInfo.creditCode!=""&&data.responseInfo.creditCode!=undefined){
				$("#creditCodeLi").show();
				$("#creditCode").text(data.responseInfo.creditCode);
			}else{
				$("#businessLicenseLi").show();
				$("#faxNoLi").show();
				$("#organizationCodeLi").show();
				$("#businessLicense").text(data.responseInfo.businessLicense);
				$("#faxNo").text(data.responseInfo.faxNo);
				$("#organizationCode").text(data.responseInfo.organizationCode);
			}
		}
	});
	
	//显示我的信息end  by yhr
	
	$("#nextPage").click(function(e) {
        var turn = cardNumber();//当前是第几个卡片
		if(turn==4){//最后一张图无法点击
			return;
		}
		if(turn==3){//最后一张图没有下一页
			$("#nextPage").hide();	
		}
		if(turn==0){//第二张图显示上一页
			$("#prevPage").show();
		}
		$(".my-card").eq(turn).animate({left:"-100%"},"fast").removeClass("card-on");
		$(".my-card").eq(turn+1).show().animate({left:"0%"},"fast").addClass("card-on");
    });
	$("#prevPage").click(function(e) {
        var turn = cardNumber();//当前是第几个卡片
		if(turn==0){
			$("#prevPage").hide();
			return;
		}
		if(turn==4){//倒数第二张图显示下一页
			$("#nextPage").show();
		}
		if(turn==1){//第一张图隐藏上一页
			$("#prevPage").hide();
		}
		$(".my-card").eq(turn).animate({left:"100%"},"fast",function(){$(this).hide();}).removeClass("card-on");
		$(".my-card").eq(turn-1).show().animate({left:"0%"},"fast").addClass("card-on");
    });
	var start;//起始坐标
	var move;//移动中坐标
	var end;//结束坐标
	$(".card-on").touchstart(function(e){
		//var touch = e.touches[0];
		start =e.touches[0].pageX;
		//alert(start);
	});
	$(".card-on").touchmove(function(e){
		var turn = cardNumber();//当前是第几个卡片
		move = e.pageX-start;
		
		if(move<0){
				$(".my-card").eq(turn).css("left","-="+move);
				$(".my-card").eq(turn+1).css("left","+="+move);
		}else{
				$(".my-card").eq(turn).css("left","+="+move);
				$(".my-card").eq(turn-1).css("left","-="+move);
		}
	});
	$(".card-on").touchend(function(e){
		end = e.pageX-start;
	});
});
//判断当前显示的卡片
function cardNumber(){
	var cards = $(".my-card");
	for(var i=0;i<cards.length;i++){
		if(cards.eq(i).hasClass("card-on")){
			return i;	
		}
	}
}
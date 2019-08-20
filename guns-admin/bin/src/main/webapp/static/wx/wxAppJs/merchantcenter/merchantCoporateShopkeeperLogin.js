$(function(){
	buyDevices();
	myWallet();
	myInfo();
	myDevices();
	tradesTotal();
	notUseDevices();
	aggregatePayBinding();
	myTrade();
	merchantInfoList();
	addmerchantInfoQRCode();
	batchSetting();
});
function batchSetting(){
	$("#batchSetting").click(function(){
		$("#batchSettingFrom").submit();
	});
}

function merchantInfoList(){
	$("#merchantInfoList").click(function(){
		$("#merchantInfoListFrom").submit();
	});
}
function addmerchantInfoQRCode(){
	$("#addmerchantInfoQRCode").click(function(){
		$("#addmerchantInfoQRCodeForm").submit();
	});	
}

function buyDevices(){		
	$("#buyDevices").click(function(){
		$("#buyDevicesForm").submit();
	});
}
function myWallet(){		
	$("#myWallet").click(function(){
		$("#myWalletForm").submit();
	});
}
function myInfo(){		
	$("#myInfo").click(function(){
		$("#myInfoForm").submit();
	});
}
function myDevices(){		
	$("#myDevices").click(function(){
		$("#myDevicesForm").submit();
	});
}
function tradesTotal(){		
	$("#tradesTotal").click(function(){
		$("#tradesTotalForm").submit();
	});
}
function notUseDevices(){		
	$("#notUseDevices").click(function(){
		$("#notUseDevicesForm").submit();
	});
}


function aggregatePayBinding(){		
	$("#aggregatePayBinding").click(function(){
		$("#aggregatePayForm").submit();
	});
}

function myTrade(){		
	$("#myTrade").click(function(){
		$("#myTradeFrom").submit();
	});
}



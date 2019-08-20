$(function(){
	switchAccount();
	//我的信息
	myInfo();
	//我的设备
	myDevices();
	//7天未使用的设备
	notUseDevicesForm();
});
//选择账号
function switchAccount(){
	$("#switchAccount").click(function(){
		$("#switchAccountForm").submit();
	});
}
//我的信息
function myInfo(){		
	$("#myInfo").click(function(){
		$("#myInfoForm").submit();
	});
}
//我的设备
function myDevices(){		
	$("#myDevices").click(function(){
		$("#myDevicesForm").submit();
	});
}
//7天未使用的设备....
function notUseDevicesForm(){
	$("#notUseDevices").click(function(){
		$("#notUseDevicesForm").submit();
	})
}

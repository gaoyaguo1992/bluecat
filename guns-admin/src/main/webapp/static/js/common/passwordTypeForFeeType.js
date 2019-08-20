//根据设备类型得到密码类型... 返回：1:按时间，2:按时间+预付费， 3：预付费
function getPasswordTypeByDeviceTypeId(deviceTypeId){
	if (deviceTypeId == 11 || deviceTypeId == 21|| deviceTypeId == 31) {
		return 1;
	}else if(deviceTypeId==33){
		return 2;
	}else{
		return 3;
	}
}
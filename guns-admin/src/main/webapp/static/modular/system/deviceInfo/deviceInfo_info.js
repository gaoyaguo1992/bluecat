/**
 * 初始化device详情对话框
 */
var DeviceInfoInfoDlg = {
	deviceInfoInfoData : {},
	operateFlag : 0,
	nextDoForBind:null//弹出绑定绑定后的处理逻辑。。
// 处理多次点。
};

/**
 * 删除所有选中的充电设备id
 */
DeviceInfoInfoDlg.clearDevicesList=function(){
	  devicesList=[];
	  devicesNum=0;
	  $("#devicesNum").text("0");
}
/**
 * 设备编号类型修改..
 */
DeviceInfoInfoDlg.deviceNoType_onchange=function(){
	var deviceNoType=$("#deviceNoType").val();
	var divForDeviceNoTypeObjects=$("#divForDeviceNoTypeObjects");
	if(deviceNoType!=null&&deviceNoType!=undefined&&deviceNoType=="2"){
		divForDeviceNoTypeObjects.show();
	}else{
		divForDeviceNoTypeObjects.hide();
	}
}
/**
 * 查询有权限的设备..根据商户id
 */
DeviceInfoInfoDlg.searchDevicesByMerchantId=function(){
	var onlineMerchantId=$("#onlineMerchantId_hd").val();
	if(onlineMerchantId==null||onlineMerchantId==undefined||onlineMerchantId==""){
		Feng.error("请输入“终端商铺号”,然后再点查询,查询可批量操作的设备!");
		return false;
	}
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		DeviceInfoInfoDlg.operateFlag = 1;
		var params={onlineMerchantId:onlineMerchantId};
		devicesList=[];
		devicesNum=0;
		$('.yajinModelNum').text(devicesNum);
		$('.listNums').text(devicesNum);
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/searchDevicesByMerchantId", function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			if (data != null && data.result == "success") {
				DeviceInfoInfoDlg.operateFlag = 0;
				for(var i=0;i<data.responseInfos.length;i++){
					devicesList.push(data.responseInfos[i].id);
					devicesNum++;
				}
				//初始化设备列表..
				$('.yajinModelNum').text(devicesNum);
				$('.listNums').text(devicesNum);	
			} else {
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error(data.message);
			}
		}, function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("查询有权限设置的设备失败!" + data.responseJSON.message + "!");
		});
		ajax.set(params);
		ajax.start();
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
		// TODO: handle exception
	}
}
//根据商户id获取到商户下的分润比较..
DeviceInfoInfoDlg.searchDevicesByMerchantIdForRatio=function(){
	var onlineMerchantId=$("#onlineMerchantId_hd").val();
	if(onlineMerchantId==null||onlineMerchantId==undefined||onlineMerchantId==""){
		Feng.error("请输入“终端商铺号”,然后再点查询,查询可批量操作的设备!");
		return false;
	}
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		DeviceInfoInfoDlg.operateFlag = 1;
		var params={onlineMerchantId:onlineMerchantId};
		devicesList=[];
		devicesNum=0;
		$('.yajinModelNum').text(devicesNum);
		$('.listNums').text(devicesNum);
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/searchDevicesByMerchantIdForRatio", function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			if (data != null && data.result == "success") {
				DeviceInfoInfoDlg.operateFlag = 0;

				devicesList=data.responseInfos;
				initDevicesForRatio();
			} else {
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error(data.message);
			}
		}, function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("查询有权限设置的设备失败!" + data.responseJSON.message + "!");
		});
		ajax.set(params);
		ajax.start();
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
		// TODO: handle exception
	}
}
/**
 * 查询有权限的设备..根据开始结束设备id
 */
DeviceInfoInfoDlg.searchDevicesByStartAndEnd=function(){
	var startDeviceNo=$("#startDeviceNo").val();
	var endDeviceNo=$("#endDeviceNo").val();
	if(startDeviceNo==null||startDeviceNo==undefined||startDeviceNo==""){
		Feng.error("请输入“开始的设备编号”!");
		return false;
	}
	if(endDeviceNo==null||endDeviceNo==undefined||endDeviceNo==""){
		Feng.error("请输入“结束的设备编号”!");
		return false;
	}
	startDeviceNo=parseInt(startDeviceNo);
	if(isNaN(startDeviceNo)){
		Feng.error("开始的设备编号只能输入数值，请输入正确的开始设备编号!");
		return false;
	}
	endDeviceNo=parseInt(endDeviceNo);
	if(isNaN(endDeviceNo)){
		Feng.error("结束的设备编号只能输入数值，请输入正确的结束设备编号!");
		return false;
	}
	//
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	DeviceInfoInfoDlg.operateFlag = 1;
	try {
		var params={start:startDeviceNo,end:endDeviceNo};
		devicesList=[];
		devicesNum=0;
		$('.yajinModelNum').text(devicesNum);
		$('.listNums').text(devicesNum);
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/searchDevicesByStartAndEnd", function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			if (data != null && data.result == "success") {
				DeviceInfoInfoDlg.operateFlag = 0;
				for(var i=0;i<data.responseInfos.length;i++){
					devicesList.push(data.responseInfos[i].id);
					devicesNum++;
				}
				//初始化设备列表..
				$('.yajinModelNum').text(devicesNum);
				$('.listNums').text(devicesNum);
			} else {
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error(data.message);
			}
		}, function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("查询有权限设置的设备失败!" + data.responseJSON.message + "!");
		});
		ajax.set(params);
		ajax.start();
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
		// TODO: handle exception
	}
}

/**
 * 查询有权限的设备..根据开始结束设备id 根据分润比例显示..
 */
DeviceInfoInfoDlg.searchDevicesByStartAndEndForRatio=function(){
	var startDeviceNo=$("#startDeviceNo").val();
	var endDeviceNo=$("#endDeviceNo").val();
	if(startDeviceNo==null||startDeviceNo==undefined||startDeviceNo==""){
		Feng.error("请输入“开始的设备编号”!");
		return false;
	}
	if(endDeviceNo==null||endDeviceNo==undefined||endDeviceNo==""){
		Feng.error("请输入“结束的设备编号”!");
		return false;
	}
	startDeviceNo=parseInt(startDeviceNo);
	if(isNaN(startDeviceNo)){
		Feng.error("开始的设备编号只能输入数值，请输入正确的开始设备编号!");
		return false;
	}
	endDeviceNo=parseInt(endDeviceNo);
	if(isNaN(endDeviceNo)){
		Feng.error("结束的设备编号只能输入数值，请输入正确的结束设备编号!");
		return false;
	}
	//
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	DeviceInfoInfoDlg.operateFlag = 1;
	try {
		var params={start:startDeviceNo,end:endDeviceNo};
		devicesList=[];
		devicesNum=0;
		$('.yajinModelNum').text(devicesNum);
		$('.listNums').text(devicesNum);
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/searchDevicesByStartAndEndRatio", function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			if (data != null && data.result == "success") {
				DeviceInfoInfoDlg.operateFlag = 0;

				devicesList=data.responseInfos;
				initDevicesForRatio();
			} else {
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error(data.message);
			}
		}, function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("查询有权限设置的设备失败!" + data.responseJSON.message + "!");
		});
		ajax.set(params);
		ajax.start();
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
		// TODO: handle exception
	}
}
/**
 * 查询设备列表...
 */
DeviceInfoInfoDlg.searchDevicesBysDevicesList=function(){
	var deviceNoStr=$("#deviceNoStr").val();
	if(deviceNoStr==null||deviceNoStr==undefined||deviceNoStr==""){
		Feng.error("请输入“设备号(多个用,分隔)”!");
		return false;
	}
	//
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	DeviceInfoInfoDlg.operateFlag = 1;
	try {
		var params={deviceNoStr:deviceNoStr};
		devicesList=[];
		devicesNum=0;
		$('.yajinModelNum').text(devicesNum);
		$('.listNums').text(devicesNum);
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/searchDevicesBydDeviceNoStr", function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			if (data != null && data.result == "success") {
				DeviceInfoInfoDlg.operateFlag = 0;
				for(var i=0;i<data.responseInfos.length;i++){
					devicesList.push(data.responseInfos[i].id);
					devicesNum++;
				}
				//初始化设备列表..
				$('.yajinModelNum').text(devicesNum);
				$('.listNums').text(devicesNum);
			} else {
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error(data.message);
			}
		}, function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("查询有权限设置的设备失败!" + data.responseJSON.message + "!");
		});
		ajax.set(params);
		ajax.start();
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
		// TODO: handle exception
	}
}

/**
 * 查询设备列表.按照平台分润进行分组得到对应的设备例表.. 根据分润比例显示..
 */
DeviceInfoInfoDlg.searchDevicesBysDevicesListForRatio=function(){
	var deviceNoStr=$("#deviceNoStr").val();
	if(deviceNoStr==null||deviceNoStr==undefined||deviceNoStr==""){
		Feng.error("请输入“设备号(多个用,分隔)”!");
		return false;
	}
	//
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	DeviceInfoInfoDlg.operateFlag = 1;
	try {
		var params={deviceNoStr:deviceNoStr};
		devicesList=[];
		devicesNum=0;
		$('.yajinModelNum').text(devicesNum);
		$('.listNums').text(devicesNum);
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/searchDevicesBydDeviceNoStrRatio", function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			if (data != null && data.result == "success") {
				DeviceInfoInfoDlg.operateFlag = 0;
				
				devicesList=data.responseInfos;
				initDevicesForRatio();
			} else {
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error(data.message);
			}
		}, function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("查询有权限设置的设备失败!" + data.responseJSON.message + "!");
		});
		ajax.set(params);
		ajax.start();
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
		// TODO: handle exception
	}
}
/**
 * 清除数据
 */
DeviceInfoInfoDlg.clearData = function() {
	this.deviceInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
DeviceInfoInfoDlg.set = function(key, val) {
	this.deviceInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key)
			.val() : val;
	return this;
}

/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
DeviceInfoInfoDlg.get = function(key) {
	return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DeviceInfoInfoDlg.close = function() {
	parent.layer.close(window.parent.DeviceInfo.layerIndex);
}

/**
 * 收集数据
 */
DeviceInfoInfoDlg.collectData = function() {
	// 处理终端铺号..
	this.deviceInfoInfoData["onlineMerchantId"] = $("#onlineMerchantId_hd")
			.val();
	this.deviceInfoInfoData["onlineMerchantCn"] = $("#onlineMerchantId").val();
	// 顶级代码商
	this.deviceInfoInfoData["agents1Id"] = $("#agents1Id_hd").val();
	this.deviceInfoInfoData["agents1Cn"] = $("#agents1Id").val();
	// 一级代理商
	this.deviceInfoInfoData["agents2Id"] = $("#agents2Id_hd").val();
	this.deviceInfoInfoData["agents2Cn"] = $("#agents2Id").val();
	// 二级代理商
	this.deviceInfoInfoData["agents3Id"] = $("#agents3Id_hd").val();
	this.deviceInfoInfoData["agents3Cn"] = $("#agents3Id").val();
	// 铺货商
	this.deviceInfoInfoData["shopkeeperId"] = $("#shopkeeperId_hd").val();
	this.deviceInfoInfoData["shopkeeperCn"] = $("#shopkeeperId").val();
	// 加腽商
	this.deviceInfoInfoData["allianceBusinessId"] = $("#allianceBusinessId_hd").val();
	this.deviceInfoInfoData["allianceBusinessCn"] = $("#allianceBusinessId").val();
	this.deviceInfoInfoData["activationMode"] = $("#activationMode").val();
	
	// 平台分润
	this.deviceInfoInfoData["platformRato"] = $("#platformRato_hd").val();
	this.set('id').set('deviceTypeId').set('remark').set('deviceStatus').set(
			'feeTypeId').set('yfjAmount').set('amountMax24hour').set(
			'firstMinutes').set('firstAmount').set('amountPerHour').set(
			'fee1TypeId').set('fee1HourAmount').set('fee2TypeId').set(
			'fee2HourAmount').set('fee3TypeId').set('fee3HourAmount').set(
			'agents1Rato').set('agents2Rato').set('agents3Rato').set(
			'shopkeeperRato').set('allianceBusinessRate').set('yfjRebackType');
}
/**
 * 验证参数是否正确...
 */
DeviceInfoInfoDlg.validateData = function(isCheckMerch, isCheckFee,isCheckDeviceType) {
	// 1.认证分润
	var agents1Rate = this.deviceInfoInfoData["agents1Rato"];
	var agents2Rato = this.deviceInfoInfoData["agents2Rato"];
	var agents3Rato = this.deviceInfoInfoData["agents3Rato"];
	var shopkeeperRato = this.deviceInfoInfoData["shopkeeperRato"];
	var allianceBusinessRate = this.deviceInfoInfoData["allianceBusinessRate"];
	var platformRato = this.deviceInfoInfoData["platformRato"];
	// 1.一级代理
	if (isCheckMerch
			&& (agents1Rate != null && agents1Rate != undefined && agents1Rate != "")) {
		agents1Rate = parseFloat(agents1Rate)
		if (isNaN(agents1Rate) || agents1Rate > 100 || agents1Rate < 0) {
			Feng.error("顶级代理商分润必须输入0~100的数值,请输入正确的一级代理商分润!");
			return false;
		}
	} else {
		agents1Rate = 0;
	}
	// 2.二级代理
	if (isCheckMerch
			&& (agents2Rato != null && agents2Rato != undefined && agents2Rato != "")) {
		agents2Rato = parseFloat(agents2Rato)
		if (isNaN(agents2Rato) || agents2Rato > 100 || agents2Rato < 0) {
			Feng.error("一级代理商分润必须输入0~100的数值,请输入正确的一级代理商分润!");
			return false;
		}
	} else {
		agents2Rato = 0;
	}
	// 3.三级代理
	if (isCheckMerch
			&& (agents3Rato != null && agents3Rato != undefined && agents3Rato != "")) {
		agents3Rato = parseFloat(agents3Rato)
		if (isNaN(agents3Rato) || agents3Rato > 100 || agents3Rato < 0) {
			Feng.error("二级代理商分润必须输入0~100的数值,请输入正确的二级代理商分润!");
			return false;
		}
	} else {
		agents3Rato = 0;
	}
	// 4.铺货商
	if (isCheckMerch
			&& (shopkeeperRato != null && shopkeeperRato != undefined && shopkeeperRato != "")) {
		shopkeeperRato = parseFloat(shopkeeperRato)
		if (isNaN(shopkeeperRato) || shopkeeperRato > 100 || shopkeeperRato < 0) {
			Feng.error("铺货商分润必须输入0~100的数值,请输入正确的铺货商分润!");
			return false;
		}
	} else {
		shopkeeperRato = 0;
	}
	// 5.加盟商
	if (isCheckMerch
			&& (allianceBusinessRate != null
					&& allianceBusinessRate != undefined && allianceBusinessRate != "")) {
		allianceBusinessRate = parseFloat(allianceBusinessRate)
		if (isNaN(allianceBusinessRate) || allianceBusinessRate > 100
				|| allianceBusinessRate < 0) {
			Feng.error("加盟商分润必须输入0~100的数值,请输入正确的加盟商分润!");
			return false;
		}
	} else {
		allianceBusinessRate = 0;
	}
	platformRato = parseFloat(platformRato);
	if (isNaN(platformRato)) {
		platformRato = 0;
	}
	var rato = platformRato + agents1Rate + agents2Rato + agents3Rato
			+ shopkeeperRato + allianceBusinessRate;
	if (isCheckMerch && rato != 100) {
		Feng.error("所有商户包括平台分润加起来不等于100!");
		return false;
	}
	//
	if(!(agents1Rate==null||agents1Rate==undefined)){
		this.deviceInfoInfoData["agents1Rato"] = agents1Rate		
	}
	if(!(agents2Rato==null||agents2Rato==undefined)){
		this.deviceInfoInfoData["agents2Rato"] = agents2Rato;
	}

	if(!(agents3Rato==null||agents3Rato==undefined)){
		this.deviceInfoInfoData["agents3Rato"] = agents3Rato;
	}
	if(!(shopkeeperRato==null||shopkeeperRato==undefined)){
		this.deviceInfoInfoData["shopkeeperRato"] = shopkeeperRato;
	}
	if(!(allianceBusinessRate==null||allianceBusinessRate==undefined)){
		this.deviceInfoInfoData["allianceBusinessRate"] = allianceBusinessRate;
	}
	if(!(platformRato==null||platformRato==undefined)){
		this.deviceInfoInfoData["platformRato"] = platformRato;
	}
	// 6 处理设备类型和费用..
	if(isCheckDeviceType){
		var deviceTypeId = this.deviceInfoInfoData["deviceTypeId"];
		if (deviceTypeId == null || deviceTypeId == undefined || deviceTypeId == "") {
			Feng.error("设备类型不能为空，请选择正确的设备类型!");
			return false;
		}

		if (deviceTypeId == 11 || deviceTypeId == 31) {
			var fee1TypeId = this.deviceInfoInfoData["fee1TypeId"];
			var fee1HourAmount = this.deviceInfoInfoData["fee1HourAmount"];
			var fee2TypeId = this.deviceInfoInfoData["fee2TypeId"];
			var fee2HourAmount = this.deviceInfoInfoData["fee2HourAmount"];
			var fee3TypeId = this.deviceInfoInfoData["fee3TypeId"];
			var fee3HourAmount = this.deviceInfoInfoData["fee3HourAmount"];
			if (isCheckFee
					&& (fee1TypeId == null || fee1TypeId == undefined || fee1TypeId == "")
					&& (fee2TypeId == null || fee2TypeId == undefined || fee2TypeId == "")
					&& (fee3TypeId == null || fee3TypeId == undefined || fee3TypeId == "")) {
				Feng.error("使用费用未设置，请设置使用费用!");
				return false;
			}
			if (isCheckFee && fee1TypeId != null && fee1TypeId != undefined
					&& fee1TypeId == "" && fee2TypeId == fee1TypeId) {
				Feng.error("选项1和选项2设置了相同的费用类型，请选不同费用类型!");
				return false;
			}
			if (isCheckFee && fee1TypeId != null && fee1TypeId != undefined
					&& fee1TypeId == "" && fee3TypeId == fee1TypeId) {
				Feng.error("选项1和选项3设置了相同的费用类型，请选不同费用类型!");
				return false;
			}
			if (isCheckFee && fee2TypeId != null && fee2TypeId != undefined
					&& fee2TypeId == "" && fee2TypeId == fee3TypeId) {
				Feng.error("选项2和选项3设置了相同的费用类型，请选不同费用类型!");
				return false;
			}
			fee1HourAmount = parseInt(fee1HourAmount);
			if (isNaN(fee1HourAmount)) {
				fee1HourAmount = 0;
			}
			fee2HourAmount = parseInt(fee2HourAmount);
			if (isNaN(fee2HourAmount)) {
				fee2HourAmount = 0;
			}
			fee3HourAmount = parseInt(fee3HourAmount);
			if (isNaN(fee3HourAmount)) {
				fee3HourAmount = 0;
			}
	
			this.deviceInfoInfoData["fee1HourAmount"] = fee1HourAmount;
			this.deviceInfoInfoData["fee2HourAmount"] = fee2HourAmount;
			this.deviceInfoInfoData["fee3HourAmount"] = fee3HourAmount;
		} else {
			var feeTypeId = this.deviceInfoInfoData["feeTypeId"];
			var yfjAmount = this.deviceInfoInfoData["yfjAmount"];
			var firstMinutes = this.deviceInfoInfoData["firstMinutes"];
			var firstAmount = this.deviceInfoInfoData["firstAmount"];
			var amountPerHour = this.deviceInfoInfoData["amountPerHour"];
			var amountMax24hour = this.deviceInfoInfoData["amountMax24hour"];
			if (isCheckFee
					&& (feeTypeId == null || feeTypeId == undefined || feeTypeId == "")) {
				Feng.error("费用类型不能为空，请选择正确的费用类型!");
				return false;
			}
			yfjAmount = parseInt(yfjAmount);
			if (isNaN(yfjAmount)) {
				yfjAmount = 10;
			}
			firstMinutes = parseInt(firstMinutes);
			if (isNaN(firstMinutes)) {
				firstMinutes = 0;
			}
			firstAmount = parseInt(firstAmount);
			if (isNaN(firstAmount)) {
				firstAmount = 0;
			}
			amountPerHour = parseInt(amountPerHour);
			if (isNaN(amountPerHour)) {
				amountPerHour = 1;
			}
			amountMax24hour = parseInt(amountMax24hour);
			if (isNaN(amountMax24hour)) {
				amountMax24hour = 5;
			}
			this.deviceInfoInfoData["feeTypeId"] = feeTypeId;
			this.deviceInfoInfoData["yfjAmount"] = yfjAmount;
			this.deviceInfoInfoData["firstMinutes"] = firstMinutes;
			this.deviceInfoInfoData["amountPerHour"] = amountPerHour;
			this.deviceInfoInfoData["amountMax24hour"] = amountMax24hour;
		}
	}
	return true;
}
/**
 * 提交添加
 */
DeviceInfoInfoDlg.addSubmit = function() {
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		DeviceInfoInfoDlg.operateFlag = 1;
		// 1.清楚数据
		this.clearData();
		// 2.收集数据
		this.collectData();
		// 3.认证..
		if (!DeviceInfoInfoDlg.validateData(true, true,true)) {
			DeviceInfoInfoDlg.finishShieldLayer();
			this.operateFlag = 0;
			return false;
		}
		var tmp = parseFloat(this.deviceInfoInfoData["deviceStatus"]);
		if (tmp!="11") {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("\"新增设备的设备状态不能选择已激活或待确认,请修改设备状态!");
			return;
		}
		
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/add", function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			if (data != null && data.result == "success") {
				Feng.success(data.message);
				window.parent.DeviceInfo.table.refresh();
				DeviceInfoInfoDlg.close();
			} else {
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.success(data.message);
			}
		}, function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("添加失败!" + data.responseJSON.message + "!");
		});
		this.deviceInfoInfoData["feeTypeId"] = staticFeeTypeId;
		ajax.set(this.deviceInfoInfoData);
		ajax.start();
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
		// TODO: handle exception
	}
}

/**
 * 提交修改
 */
DeviceInfoInfoDlg.editSubmit = function() {
    var feeTypeId = $("#feeTypeId").val();
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	
	this.operateFlag = 1;
	this.clearData();
	this.collectData();
	// 判断参数是否正确...
	if (!DeviceInfoInfoDlg.validateData(true, true,true)) {
		DeviceInfoInfoDlg.finishShieldLayer();
		this.operateFlag = 0;
		return false;
	}
	//判断一下状态..
	var deviceStatus= this.deviceInfoInfoData["deviceStatus"];
  
	if(oldDeviceStatus==deviceStatus){
		//提交到数据库..
		DeviceInfoInfoDlg.editSubmitForSave();
	}else{
		var activationMode=$("#activationMode").val();
		if(activationMode=="1"&&(deviceStatus==10||deviceStatus==12)){
			DeviceInfoInfoDlg.nextDoForBind=DeviceInfoInfoDlg.editSubmitForSave;
			DeviceInfoInfoDlg.bindTerminalWindow();
		}else{
			//提交到数据库..
			DeviceInfoInfoDlg.editSubmitForSave();
		}		
	}
}
/**
 * 保存提交数据到数据库
 */
DeviceInfoInfoDlg.editSubmitForSave = function() {
	try {
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/update", function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			if (data != null && data.result == "success") {
				Feng.success(data.message);
				window.parent.DeviceInfo.table.refresh();
				DeviceInfoInfoDlg.close();
			} else {
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error(data.message);
			}
		}, function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("修改失败!" + data.responseJSON.message + "!");
		});
		this.deviceInfoInfoData["feeTypeId"] = staticFeeTypeId;
		ajax.set(this.deviceInfoInfoData);
		ajax.start();
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
		// TODO: handle exception
	}
}
/**
 * 批量生成充电器...
 */
DeviceInfoInfoDlg.batchAddSubmit = function() {
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		this.operateFlag = 1;
		this.clearData();
		this.collectData();
		// 处理批量处理的数据。
		this.deviceInfoInfoData["deviceQty"] = $("#deviceQty").val();
		this.deviceInfoInfoData["batchSetCharger"] = $("#batchSetCharger").is(
				':checked');
		this.deviceInfoInfoData["batchSetFee"] = $("#batchSetFee").is(
				':checked');
		this.deviceInfoInfoData["batchSetMerch"] = $("#batchSetMerch").is(
				':checked');
		this.deviceInfoInfoData["batchChargerQty"] = $("#batchChargerQty")
				.val();
		this.deviceInfoInfoData["batchChargerRemark"] = $("#batchChargerRemark")
				.val();
		//处理生成设备编号的...
		var passwordType=$("#deviceNoType").val();
		this.deviceInfoInfoData["passwordType"] = passwordType;
		//编号对应年
		var passwordYear=$("#deviceNoForYear").val();
		this.deviceInfoInfoData["passwordYear"] = passwordYear;
		//编号对应月
		var passwordMonth=$("#deviceNoForMonth").val();
		this.deviceInfoInfoData["passwordMonth"] = passwordMonth;
		//编号对应批次
		var passwordBatch= $("#deviceNoForBB").val();
		this.deviceInfoInfoData["passwordBatch"] = passwordBatch;
		//编号密码原子
		var passwordKey=$("#deviceNoForKey").val();
		this.deviceInfoInfoData["passwordKey"] = passwordKey;
		debugger;
		//如果设备编号是高级的，判断是否选的年月日及输入了批次和key..
		if(passwordType!=null&&passwordType!=undefined&&passwordType=="2"){
			if(passwordYear==null||passwordYear==undefined){
				DeviceInfoInfoDlg.finishShieldLayer();
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error("此类型的设备编号模式必须输入编号对应的年!");
				return;
			}
			if(passwordMonth==null||passwordMonth==undefined){
				DeviceInfoInfoDlg.finishShieldLayer();
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error("此类型的设备编号模式必须输入编号对应的月!");
				return;
			}
			if(passwordBatch==null||passwordBatch==undefined||passwordBatch==""){
				DeviceInfoInfoDlg.finishShieldLayer();
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error("此类型的设备编号模式必须输入编号对应的批次!");
				return;
			}
			var batch=parseInt(passwordBatch);
			if(isNaN(batch)||batch<1||batch>9){
				DeviceInfoInfoDlg.finishShieldLayer();
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error("编号对应的批次必须是大于0小于10的整数!");
				return;
			}
			this.deviceInfoInfoData["passwordBatch"] =batch;
			
			if(passwordKey==null||passwordKey==undefined||passwordKey==""){
				DeviceInfoInfoDlg.finishShieldLayer();
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error("此类型的设备编号模式必须输入密钥串!");
				return;
			}
		}
		
		// 判断参数是否正确...
		if (this.deviceInfoInfoData["batchSetCharger"] == null
				|| this.deviceInfoInfoData["batchSetCharger"] == undefined) {
			this.deviceInfoInfoData["batchSetCharger"] = false;
		}
		if (this.deviceInfoInfoData["batchSetFee"] == null
				|| this.deviceInfoInfoData["batchSetFee"] == undefined) {
			this.deviceInfoInfoData["batchSetFee"] = false;
		}
		if (this.deviceInfoInfoData["batchSetMerch"] == null
				|| this.deviceInfoInfoData["batchSetMerch"] == undefined) {
			this.deviceInfoInfoData["batchSetMerch"] = false;
		}
		// 1.判断参数是否正确.....
		if (!DeviceInfoInfoDlg.validateData(
				this.deviceInfoInfoData["batchSetMerch"],
				this.deviceInfoInfoData["batchSetFee"],
				this.deviceInfoInfoData["batchSetFee"])) {
			DeviceInfoInfoDlg.finishShieldLayer();
			this.operateFlag = 0;
			return false;
		}
		// 2.判断
		var tmp = parseFloat(this.deviceInfoInfoData["deviceQty"]);
		if (isNaN(tmp) || tmp <= 0) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("\"批量生成设备数量\"必须为大于0的数值， 请输入正确的\"批量生成设备数量\"!");
			return;
		}
		if (this.deviceInfoInfoData["batchSetCharger"]) {
			tmp = parseFloat(this.deviceInfoInfoData["batchChargerQty"]);
			if (isNaN(tmp) || tmp <= 0) {
				DeviceInfoInfoDlg.finishShieldLayer();
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error("\"每个设备生成充电器数量\"必须为大于0的数值， 请输入正确的\"每个设备生成充电器数量\"!");
				return;
			}
		}
		
		// 3.服务器提交
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/batchAddSubmit",
				function(data) {
					DeviceInfoInfoDlg.finishShieldLayer();
					if (data != null && data.result == "success") {
						Feng.success(data.message);
						window.parent.DeviceInfo.table.refresh();
						DeviceInfoInfoDlg.close();
					} else {
						DeviceInfoInfoDlg.operateFlag = 0;
						Feng.error(data.message);
					}
				}, function(data) {
					DeviceInfoInfoDlg.finishShieldLayer();
					DeviceInfoInfoDlg.operateFlag = 0;
					Feng.error("修改失败!" + data.responseJSON.message + "!");
				});
		this.deviceInfoInfoData["feeTypeId"] = staticFeeTypeId;
		ajax.set(this.deviceInfoInfoData);
		ajax.start();
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	}
}
/**
 * 批量绑定商户...
 */
DeviceInfoInfoDlg.batchBindMer = function() {
	debugger;
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		this.operateFlag = 1;
		this.clearData();
		this.collectData();
		// 选择设备类型
		this.deviceInfoInfoData["selectDeviceNoType"] = $("#selectDeviceNoType")
				.val();
		// 设备号（多个用,分隔)
		this.deviceInfoInfoData["deviceNoStr"] = $("#deviceNoStr").val();
		// 开始的设备编号
		this.deviceInfoInfoData["startDeviceNo"] = $("#startDeviceNo").val();
		// 结束的设备编号
		this.deviceInfoInfoData["endDeviceNo"] = $("#endDeviceNo").val();
		//
		if (this.deviceInfoInfoData["selectDeviceNoType"] == "1"
				&& (this.deviceInfoInfoData["deviceNoStr"] == null
						|| this.deviceInfoInfoData["deviceNoStr"] == undefined || this.deviceInfoInfoData["deviceNoStr"] == "")) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量设置商户的设备号，多个请用逗号分隔!");
			return;
		}
		//
		if (this.deviceInfoInfoData["selectDeviceNoType"] == "2"
				&& (this.deviceInfoInfoData["startDeviceNo"] == null
						|| this.deviceInfoInfoData["startDeviceNo"] == undefined
						|| this.deviceInfoInfoData["startDeviceNo"] == ""
						|| this.deviceInfoInfoData["endDeviceNo"] == null
						|| this.deviceInfoInfoData["endDeviceNo"] == undefined || this.deviceInfoInfoData["endDeviceNo"] == "")) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量设置商户的开始和结束设备号!");
			return;
		}
		var msg = "";
		if ((this.deviceInfoInfoData["agents1Rato"] == ""
				|| this.deviceInfoInfoData["agents1Rato"] == null || this.deviceInfoInfoData["agents1Rato"] == undefined)
				&& (this.deviceInfoInfoData["agents2Rato"] == ""
						|| this.deviceInfoInfoData["agents2Rato"] == null || this.deviceInfoInfoData["agents2Rato"] == undefined)
				&& (this.deviceInfoInfoData["agents3Rato"] == ""
						|| this.deviceInfoInfoData["agents3Rato"] == null || this.deviceInfoInfoData["agents3Rato"] == undefined)
				&& (this.deviceInfoInfoData["shopkeeperRato"] == ""
						|| this.deviceInfoInfoData["shopkeeperRato"] == null || this.deviceInfoInfoData["shopkeeperRato"] == undefined)
				&& (this.deviceInfoInfoData["allianceBusinessRate"] == ""
						|| this.deviceInfoInfoData["allianceBusinessRate"] == null || this.deviceInfoInfoData["allianceBusinessRate"] == undefined)) {
			msg = "您未输入任何商户分润，系统将保留原商户，若要清楚原分润可以输入0, 确定提交吗?"
		} else {
			var agents1Rato = parseFloat(this.deviceInfoInfoData["agents1Rato"]);
			var agents2Rato = parseFloat(this.deviceInfoInfoData["agents2Rato"]);
			var agents3Rato = parseFloat(this.deviceInfoInfoData["agents3Rato"]);
			var shopkeeperRato = parseFloat(this.deviceInfoInfoData["shopkeeperRato"]);
			var allianceBusinessRate = parseFloat(this.deviceInfoInfoData["allianceBusinessRate"]);
			if (isNaN(agents1Rato)) {
				agents1Rato = 0;
			}
			if (isNaN(agents2Rato)) {
				agents2Rato = 0;
			}
			if (isNaN(agents3Rato)) {
				agents3Rato = 0;
			}
			if (isNaN(shopkeeperRato)) {
				shopkeeperRato = 0;
			}
			if (isNaN(agents1Rato)) {
				allianceBusinessRate = 0;
			}
			if (agents1Rato < 0 || agents2Rato < 0 || agents3Rato < 0
					|| shopkeeperRato < 0 || allianceBusinessRate < 0) {
				DeviceInfoInfoDlg.finishShieldLayer();
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error("输入的分润必须是0～100的数值，请输入正确的分润!");
				return;
			}
			var platformRate = 100 - agents1Rato - agents2Rato - agents3Rato
					- shopkeeperRato - allianceBusinessRate;
			if (platformRate < 0) {
				DeviceInfoInfoDlg.finishShieldLayer();
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error("您输入的所有商户分润之和大于100，请输入正确的分润!");
				return;
			}			
			
			msg = "您已输入了商户分润，系统将更新设备所有商户分润, 确定提交吗?"
			this.deviceInfoInfoData["agents1Rato"]=isNaN(agents1Rato)?undefined:agents1Rato;
			this.deviceInfoInfoData["agents2Rato"]=isNaN(agents2Rato)?undefined:agents2Rato;
			this.deviceInfoInfoData["agents3Rato"]=isNaN(agents3Rato)?undefined:agents3Rato;
			this.deviceInfoInfoData["shopkeeperRato"]=isNaN(shopkeeperRato)?undefined:shopkeeperRato;
			this.deviceInfoInfoData["allianceBusinessRate"]=isNaN(allianceBusinessRate)?undefined:allianceBusinessRate;
		}
		Feng.confirm(msg,function(){
			//1.测试
			DeviceInfoInfoDlg.startShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 1;
			// 3.服务器提交
			// 提交信息
			var ajax = new $ax(Feng.ctxPath + "/deviceInfo/batchBindMer", function(data) {
				DeviceInfoInfoDlg.finishShieldLayer();
				if (data != null && data.result == "success") {
					DeviceInfoInfoDlg.operateFlag = 0;
					Feng.success(data.message);
					window.parent.DeviceInfo.table.refresh();
					DeviceInfoInfoDlg.close();
				} else {
					DeviceInfoInfoDlg.operateFlag = 0;
					Feng.error(data.message);
				}
			}, function(data) {
				DeviceInfoInfoDlg.finishShieldLayer();
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error("修改失败!" + data.responseJSON.message + "!");
			});
			ajax.set(DeviceInfoInfoDlg.deviceInfoInfoData);
			ajax.start();
		})
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	}
}
/**
 * 批理绑定商户 user...
 */
DeviceInfoInfoDlg.batchUnBindMerUser=function(){
	if(devicesNum==0||devicesList.length<=0){
		Feng.error("未获取有权限解除绑定的的设备， 请点“查询”获到有权限解除绑定的的设备!");
		return;
	}
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		this.operateFlag = 1;
		this.clearData();
		this.collectData();
		
		
		var chkAgents1Id=0;
		if($('#agents1Id').is(':checked')) {
			chkAgents1Id=1;
		}
		var chkAgents2Id=0;
		if($('#agents2Id').is(':checked')) {
			chkAgents2Id=1;
		}
		var chkAgents3Id=0;
		if($('#agents3Id').is(':checked')) {
			chkAgents3Id=1;
		}
		var chkShopkeeperId=0;
		if($('#shopkeeperId').is(':checked')) {
			chkShopkeeperId=1;
		}
		var chkAllianceBusinessId=0;
		if($('#allianceBusinessId').is(':checked')) {
			chkAllianceBusinessId=1;
		}
		var chkMerchantId=0;
		if($('#chkMerchantId').is(':checked')) {
			chkMerchantId=1;
		}
		
	
		this.deviceInfoInfoData["chkAgents1Id"] =chkAgents1Id;
		this.deviceInfoInfoData["chkAgents2Id"] =chkAgents2Id;
		this.deviceInfoInfoData["chkAgents3Id"] =chkAgents3Id;
		this.deviceInfoInfoData["chkShopkeeperId"] =chkShopkeeperId;
		this.deviceInfoInfoData["chkAllianceBusinessId"] =chkAllianceBusinessId;
		this.deviceInfoInfoData["chkMerchantId"] =chkMerchantId;
		var msg = "";
		if (this.deviceInfoInfoData["chkAgents1Id"] ==0
				&& this.deviceInfoInfoData["chkAgents2Id"] == 0 
				&& this.deviceInfoInfoData["chkAgents3Id"] == 0
				&& this.deviceInfoInfoData["chkShopkeeperId"] == 0
				&& this.deviceInfoInfoData["chkAllianceBusinessId"] == 0
				&& this.deviceInfoInfoData["chkMerchantId"] == 0) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			msg = "请选择要解绑的商户类型！"
			Feng.error(msg);
			return;
		}
		// 选择设备类型
		this.deviceInfoInfoData["selectDeviceNoType"] ="1";
		// 设备号（多个用,分隔)
		var deviceNoStr="";
		for(var i=0;i<devicesList.length;i++){
			if(i==0){
				deviceNoStr=devicesList[i];
			}else{
				deviceNoStr=deviceNoStr+","+devicesList[i];
			}
		}
		this.deviceInfoInfoData["deviceNoStr"] = deviceNoStr;
		
		
		msg="确定要解绑选中的商户吗?";
		Feng.confirm(msg,function(){
			// 3.服务器提交
			// 提交信息
			DeviceInfoInfoDlg.startShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 1;
			var ajax = new $ax(Feng.ctxPath + "/deviceInfo/batchUnBindMer", function(data) {
				DeviceInfoInfoDlg.finishShieldLayer();
				if (data != null && data.result == "success") {
					Feng.success(data.message);
					window.parent.DeviceInfo.table.refresh();
					DeviceInfoInfoDlg.close();
				} else {
					DeviceInfoInfoDlg.operateFlag = 0;
					Feng.error(data.message);
				}
			}, function(data) {
				DeviceInfoInfoDlg.finishShieldLayer();
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error("解绑败!" + data.responseJSON.message + "!");
			});
			ajax.set(DeviceInfoInfoDlg.deviceInfoInfoData);
			ajax.start();
		})
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	}
}
/**
 * 批量绑定商户...
 */
DeviceInfoInfoDlg.batchUnBindMer = function() {
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		this.operateFlag = 1;
		this.clearData();
		this.collectData();
		// 选择设备类型
		this.deviceInfoInfoData["selectDeviceNoType"] = $("#selectDeviceNoType")
				.val();
		// 设备号（多个用,分隔)
		this.deviceInfoInfoData["deviceNoStr"] = $("#deviceNoStr").val();
		// 开始的设备编号
		this.deviceInfoInfoData["startDeviceNo"] = $("#startDeviceNo").val();
		// 结束的设备编号
		this.deviceInfoInfoData["endDeviceNo"] = $("#endDeviceNo").val();
		var chkAgents1Id=0;
		if($('#agents1Id').is(':checked')) {
			chkAgents1Id=1;
		}
		var chkAgents2Id=0;
		if($('#agents2Id').is(':checked')) {
			chkAgents2Id=1;
		}
		var chkAgents3Id=0;
		if($('#agents3Id').is(':checked')) {
			chkAgents3Id=1;
		}
		var chkShopkeeperId=0;
		if($('#shopkeeperId').is(':checked')) {
			chkShopkeeperId=1;
		}
		var chkAllianceBusinessId=0;
		if($('#allianceBusinessId').is(':checked')) {
			chkAllianceBusinessId=1;
		}
		var chkMerchantId=0;
		if($('#chkMerchantId').is(':checked')) {
			chkMerchantId=1;
		}
		

		this.deviceInfoInfoData["chkAgents1Id"] =chkAgents1Id;
		this.deviceInfoInfoData["chkAgents2Id"] =chkAgents2Id;
		this.deviceInfoInfoData["chkAgents3Id"] =chkAgents3Id;
		this.deviceInfoInfoData["chkShopkeeperId"] =chkShopkeeperId;
		this.deviceInfoInfoData["chkAllianceBusinessId"] =chkAllianceBusinessId;
		this.deviceInfoInfoData["chkMerchantId"] =chkMerchantId;
		
		//
		if (this.deviceInfoInfoData["selectDeviceNoType"] == "1"
				&& (this.deviceInfoInfoData["deviceNoStr"] == null
						|| this.deviceInfoInfoData["deviceNoStr"] == undefined || this.deviceInfoInfoData["deviceNoStr"] == "")) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量设置商户的设备号，多个请用逗号分隔!");
			return;
		}
		//
		if (this.deviceInfoInfoData["selectDeviceNoType"] == "2"
				&& (this.deviceInfoInfoData["startDeviceNo"] == null
						|| this.deviceInfoInfoData["startDeviceNo"] == undefined
						|| this.deviceInfoInfoData["startDeviceNo"] == ""
						|| this.deviceInfoInfoData["endDeviceNo"] == null
						|| this.deviceInfoInfoData["endDeviceNo"] == undefined || this.deviceInfoInfoData["endDeviceNo"] == "")) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量设置商户的开始和结束设备号!");
			return;
		}
		var msg = "";
		if (this.deviceInfoInfoData["chkAgents1Id"] ==0
				&& this.deviceInfoInfoData["chkAgents2Id"] == 0 
				&& this.deviceInfoInfoData["chkAgents3Id"] == 0
				&& this.deviceInfoInfoData["chkShopkeeperId"] == 0
				&& this.deviceInfoInfoData["chkAllianceBusinessId"] == 0
				&& this.deviceInfoInfoData["chkMerchantId"] == 0) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			msg = "请选择要解绑的商户类型！"
			Feng.error(msg);
			return;
		}
		msg="确定要解绑选中的商户吗?";
		Feng.confirm(msg,function(){
			// 3.服务器提交
			// 提交信息
			DeviceInfoInfoDlg.startShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 1;
			var ajax = new $ax(Feng.ctxPath + "/deviceInfo/batchUnBindMer", function(data) {
				DeviceInfoInfoDlg.finishShieldLayer();
				if (data != null && data.result == "success") {
					Feng.success(data.message);
					window.parent.DeviceInfo.table.refresh();
					DeviceInfoInfoDlg.close();
				} else {
					DeviceInfoInfoDlg.operateFlag = 0;
					Feng.error(data.message);
				}
			}, function(data) {
				DeviceInfoInfoDlg.finishShieldLayer();
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error("解绑败!" + data.responseJSON.message + "!");
			});
			ajax.set(DeviceInfoInfoDlg.deviceInfoInfoData);
			ajax.start();
		})
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	}
}
/**
 * 批量绑定费用...对应非管理员
 */
DeviceInfoInfoDlg.batchBindFeeUser=function(){
	if(devicesNum==0||devicesList.length<=0){
		Feng.error("未获取有权限批量修改费用的设备， 请点“查询”获取到有权限批量修改费用!");
		return;
	}
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		this.operateFlag = 1;
		this.clearData();
		this.collectData();
		// 2.认证..
		if (!DeviceInfoInfoDlg.validateData(false, true, true)) {
			DeviceInfoInfoDlg.finishShieldLayer();
			this.operateFlag = 0;
			return false;
		}
		// 选择设备类型
		this.deviceInfoInfoData["selectDeviceNoType"] = "1";
		// 设备号（多个用,分隔)
		var deviceNoStr="";
		for(var i=0;i<devicesList.length;i++){
			if(i==0){
				deviceNoStr=devicesList[i];
			}else{
				deviceNoStr=deviceNoStr+","+devicesList[i];
			}
		}
		// 设备号（多个用,分隔)
		this.deviceInfoInfoData["deviceNoStr"] = deviceNoStr;
		//3.服务器提交
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/batchBindFee",
				function(data) {
					DeviceInfoInfoDlg.finishShieldLayer();
					if (data != null && data.result == "success") {
						Feng.success(data.message);
						window.parent.DeviceInfo.table.refresh();
						DeviceInfoInfoDlg.close();
					} else {
						DeviceInfoInfoDlg.operateFlag = 0;
						Feng.error(data.message);
					}
				}, function(data) {
					DeviceInfoInfoDlg.finishShieldLayer();
					DeviceInfoInfoDlg.operateFlag = 0;
					Feng.error("批量费用失败!" + data.responseJSON.message + "!");
				}
		);
		this.deviceInfoInfoData["feeTypeId"] = staticFeeTypeId;
		ajax.set(this.deviceInfoInfoData);
		ajax.start();
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	}
}
/**
 * 批量绑定费用...
 */
DeviceInfoInfoDlg.batchBindFee=function(){
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		//1.收集数据
		this.operateFlag = 1;
		this.clearData();
		this.collectData();
		// 2.认证..
		if (!DeviceInfoInfoDlg.validateData(false, true, true)) {
			DeviceInfoInfoDlg.finishShieldLayer();
			this.operateFlag = 0;
			return false;
		}
		// 选择设备类型
		this.deviceInfoInfoData["selectDeviceNoType"] = $("#selectDeviceNoType")
				.val();
		// 设备号（多个用,分隔)
		this.deviceInfoInfoData["deviceNoStr"] = $("#deviceNoStr").val();
		// 开始的设备编号
		this.deviceInfoInfoData["startDeviceNo"] = $("#startDeviceNo").val();
		// 结束的设备编号
		this.deviceInfoInfoData["endDeviceNo"] = $("#endDeviceNo").val();
		// 商户号...
		this.deviceInfoInfoData["onlineMerchantId"] = $("#onlineMerchantId_hd").val();
		//
		if (this.deviceInfoInfoData["selectDeviceNoType"] == "1"
				&& (this.deviceInfoInfoData["deviceNoStr"] == null
						|| this.deviceInfoInfoData["deviceNoStr"] == undefined || this.deviceInfoInfoData["deviceNoStr"] == "")) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量设置费用的设备号，多个请用逗号分隔!");
			return;
		}
		//
		if (this.deviceInfoInfoData["selectDeviceNoType"] == "2"
				&& (this.deviceInfoInfoData["startDeviceNo"] == null
						|| this.deviceInfoInfoData["startDeviceNo"] == undefined
						|| this.deviceInfoInfoData["startDeviceNo"] == ""
						|| this.deviceInfoInfoData["endDeviceNo"] == null
						|| this.deviceInfoInfoData["endDeviceNo"] == undefined || this.deviceInfoInfoData["endDeviceNo"] == "")) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量设置费用的开始和结束设备号!");
			return;
		}
		if (this.deviceInfoInfoData["selectDeviceNoType"] == "3"
			&& (this.deviceInfoInfoData["onlineMerchantId"] == null)) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量设置费用的终端商户号!");
			return;
		}
		//3.服务器提交
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/batchBindFee",
				function(data) {
					DeviceInfoInfoDlg.finishShieldLayer();
					if (data != null && data.result == "success") {
						Feng.success(data.message);
						window.parent.DeviceInfo.table.refresh();
						DeviceInfoInfoDlg.close();
					} else {
						DeviceInfoInfoDlg.operateFlag = 0;
						Feng.error(data.message);
					}
				}, function(data) {
					DeviceInfoInfoDlg.finishShieldLayer();
					DeviceInfoInfoDlg.operateFlag = 0;
					Feng.error("批量修改失败!" + data.responseJSON.message + "!");
				}
		);
		this.deviceInfoInfoData["feeTypeId"] = staticFeeTypeId;
		ajax.set(this.deviceInfoInfoData);
		ajax.start();
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	}
}
/**
 * 修改分润...
 * merFlag:当前修改的商户...
 */
DeviceInfoInfoDlg.ratioOnchange=function(platformRatio,merFlag){
	var agents1RatoSpan=$("#agents1Rato_"+platformRatio);
	var agents2RatoInput=$("#agents2Rato_"+platformRatio);
	var agents3RatoInput=$("#agents3Rato_"+platformRatio);
	var shopkeeperRatoInput=$("#shopkeeperRato_"+platformRatio);
	var allianceBusinessRateInput=$("#allianceBusinessRate_"+platformRatio);
	var item=null,tmp=null;
	for(var i=0;i<devicesList.length;i++){
		tmp=devicesList[i];
		if(tmp.platformRatio==platformRatio){
			item=tmp;
			break;
		}
	}
	if(item!=null&&item!=undefined){
		var agents1Rato=100-parseFloat(item.platformRatio);
		var agents2Rato=parseFloat(agents2RatoInput.val());
		var agents3Rato=parseFloat(agents3RatoInput.val());
		var shopkeeperRato=parseFloat(shopkeeperRatoInput.val());
		var allianceBusinessRate=parseFloat(allianceBusinessRateInput.val());
		if(isNaN(agents1Rato)){
			agents1Rato=0;
		}
		if(isNaN(agents2Rato)){
			agents2Rato=0;
		}
		if(isNaN(agents3Rato)){
			agents3Rato=0;
		}
		if(isNaN(shopkeeperRato)){
			shopkeeperRato=0;
		}
		if(isNaN(allianceBusinessRate)){
			allianceBusinessRate=0;
		}
		//
		var tmp=agents1Rato-(agents2Rato+agents3Rato+shopkeeperRato+allianceBusinessRate);
		if(tmp<0){
			Feng.error("输入的分润比例大于可分润的值(顶级代理商分润已小于0)，请输入正确的分润比例!");
			if(merFlag==2){
				agents2Rato=0;
			}else if(merFlag==3){
				agents3Rato=0;
			}else if(merFlag==4){
				shopkeeperRato=0;
			}else if(merFlag==5){
				allianceBusinessRate=0;
			}
		}
		agents1Rato=agents1Rato-(agents2Rato+agents3Rato+shopkeeperRato+allianceBusinessRate);
		item.agents1Rato=agents1Rato;
		item.agents2Rato=agents2Rato;
		item.agents3Rato=agents3Rato;
		item.shopkeeperRato=shopkeeperRato;
		item.allianceBusinessRate=allianceBusinessRate;
		
		agents1RatoSpan.html(item.agents1Rato);
		agents2RatoInput.val(item.agents2Rato);
		agents3RatoInput.val(item.agents3Rato);
		shopkeeperRatoInput.val(item.shopkeeperRato);
		allianceBusinessRateInput.val(item.allianceBusinessRate);
	}
}
/**
 * 绑定分润
 */
DeviceInfoInfoDlg.batchBindRato=function(obj){
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		//1.收集数据
		this.operateFlag = 1;
		this.clearData();
		this.collectData();
		// 2.认证..
		if (!DeviceInfoInfoDlg.validateData(true, false,false)) {
			DeviceInfoInfoDlg.finishShieldLayer();
			this.operateFlag = 0;
			return false;
		}
		// 选择设备类型
		this.deviceInfoInfoData["selectDeviceNoType"] = $("#selectDeviceNoType")
				.val();
		// 设备号（多个用,分隔)
		this.deviceInfoInfoData["deviceNoStr"] = $("#deviceNoStr").val();
		// 开始的设备编号
		this.deviceInfoInfoData["startDeviceNo"] = $("#startDeviceNo").val();
		// 结束的设备编号
		this.deviceInfoInfoData["endDeviceNo"] = $("#endDeviceNo").val();
		// 商户号...
		this.deviceInfoInfoData["onlineMerchantId"] = $("#onlineMerchantId_hd").val();
		//
		if (this.deviceInfoInfoData["selectDeviceNoType"] == "1"
				&& (this.deviceInfoInfoData["deviceNoStr"] == null
						|| this.deviceInfoInfoData["deviceNoStr"] == undefined || this.deviceInfoInfoData["deviceNoStr"] == "")) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量设置分润的设备号，多个请用逗号分隔!");
			return;
		}
		//
		if (this.deviceInfoInfoData["selectDeviceNoType"] == "2"
				&& (this.deviceInfoInfoData["startDeviceNo"] == null
						|| this.deviceInfoInfoData["startDeviceNo"] == undefined || this.deviceInfoInfoData["startDeviceNo"] == ""
						|| this.deviceInfoInfoData["endDeviceNo"] == null
						|| this.deviceInfoInfoData["endDeviceNo"] == undefined || this.deviceInfoInfoData["endDeviceNo"] == "")) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量设置分润开始和结束设备号!");
			return;
		}
		if (this.deviceInfoInfoData["selectDeviceNoType"] == "3"
			&& (this.deviceInfoInfoData["onlineMerchantId"] == null)) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("请输入终端商户号!");
			return;
		}
		//3.服务器提交
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/batchBindRato",
				function(data) {
					DeviceInfoInfoDlg.finishShieldLayer();
					if (data != null && data.result == "success") {
						Feng.success(data.message);
						window.parent.DeviceInfo.table.refresh();
						DeviceInfoInfoDlg.close();
					} else {
						DeviceInfoInfoDlg.operateFlag = 0;
						Feng.error(data.message);
					}
				}, function(data) {
					DeviceInfoInfoDlg.finishShieldLayer();
					DeviceInfoInfoDlg.operateFlag = 0;
					Feng.error("批量修改失败!" + data.responseJSON.message + "!");
				}
		);
		ajax.set(this.deviceInfoInfoData);
		ajax.start();
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	}
}
/**
 * 绑定分润..保存...
 */
DeviceInfoInfoDlg.batchBindRatoUser=function(){
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	this.operateFlag = 1;
	if(devicesList==null||devicesList==undefined){
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
		Feng.error("未获取有权限修改的设备， 请点“查询”获到有权限修改的设备!");
		return;
	}
	var saveFlag=false,item=null;
	for(var i=0;i<devicesList.length;i++){
		item=devicesList[i];
		if(item.deviceCount>0){;
		    saveFlag=true;
			break;
		}
	}
	if(!saveFlag){
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
		Feng.error("未获取要修改的设备，请点“查询”获到有权限修改的设备!");
		return;
	}
	try {
		//判断是否要绑定商户...
		//1. 提交信息 
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/batchBindRatoUser",
			function(data) {
				DeviceInfoInfoDlg.finishShieldLayer();
				if (data != null && data.result == "success") {
					Feng.success(data.message);
					window.parent.DeviceInfo.table.refresh();
					DeviceInfoInfoDlg.close();
				} else {
					DeviceInfoInfoDlg.operateFlag = 0;
					Feng.error(data.message);
				}
			}, function(data) {
				DeviceInfoInfoDlg.finishShieldLayer();
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error("批量修改分润失败!" + data.responseJSON.message + "!");
			}
		);
		var params={dataList:JSON.stringify(devicesList)};
		ajax.set(params);
		ajax.start();
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	}
}
/**
 * 绑定其它信息...
 */
DeviceInfoInfoDlg.batchBindOtherUser=function(){
	if(devicesNum==0||devicesList.length<=0){
		Feng.error("未获取有权限修改的设备， 请点“查询”获到有权限修改的设备!");
		return;
	}
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		//1.收集数据
		this.operateFlag = 1;
		this.clearData();
		this.collectData();
		// 选择设备类型
		this.deviceInfoInfoData["selectDeviceNoType"] ="1";
		// 设备号（多个用,分隔)
		var deviceNoStr="";
		for(var i=0;i<devicesList.length;i++){
			if(i==0){
				deviceNoStr=devicesList[i];
			}else{
				deviceNoStr=deviceNoStr+","+devicesList[i];
			}
		}
		this.deviceInfoInfoData["deviceNoStr"] = deviceNoStr;
	
		if((this.deviceInfoInfoData["deviceStatus"]==null||this.deviceInfoInfoData["deviceStatus"]==undefined
				||this.deviceInfoInfoData["deviceStatus"]=="0"||this.deviceInfoInfoData["deviceStatus"]=="")&&
				(this.deviceInfoInfoData["yfjRebackType"]==null||this.deviceInfoInfoData["yfjRebackType"]==undefined
						||this.deviceInfoInfoData["yfjRebackType"]=="0"||this.deviceInfoInfoData["yfjRebackType"]=="")){
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("未输入任何修改内容，请输入需要修改的字段内容!");
			return;
		}
		if(this.deviceInfoInfoData["deviceStatus"]!=null
				&&this.deviceInfoInfoData["deviceStatus"]!=undefined
				&&this.deviceInfoInfoData["deviceStatus"]=="12"){
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("不支付把设备状态批量修改为待确认状态，请选择正确的设备状态!");
			return;
		}
		if(this.deviceInfoInfoData["deviceStatus"]==null||this.deviceInfoInfoData["deviceStatus"]==undefined
				||this.deviceInfoInfoData["deviceStatus"]!="10"){
			DeviceInfoInfoDlg.BatchBindOtherForSave();
		}else{
			//判断是否要绑定商户...
			//1. 提交信息 
			var ajax = new $ax(Feng.ctxPath + "/deviceInfo/getDevicesActivationModeForBatch",
					function(data) {
						DeviceInfoInfoDlg.finishShieldLayer();
						if (data != null && data.result == "success") {
							if(data.responseInfo==null||data.responseInfo==undefined||data.responseInfo==""){
								DeviceInfoInfoDlg.BatchBindOtherForSave();
							}else{
								//弹出提示用户绑定商
								DeviceInfoInfoDlg.nextDoForBind=DeviceInfoInfoDlg.BatchBindOtherForSave;
								DeviceInfoInfoDlg.bindTerminalWindow();
							}
						} else {
							DeviceInfoInfoDlg.operateFlag = 0;
							Feng.error(data.message);
						}
					}, function(data) {
						DeviceInfoInfoDlg.finishShieldLayer();
						DeviceInfoInfoDlg.operateFlag = 0;
						Feng.error("修改失败!" + data.responseJSON.message + "!");
					}
			);
			ajax.set(this.deviceInfoInfoData);
			ajax.start();
		}
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	}
}
/**
 * 绑定其它信息。。。
 */
DeviceInfoInfoDlg.batchBindOther=function(obj){
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		//1.收集数据
		this.operateFlag = 1;
		this.clearData();
		this.collectData();
		// 选择设备类型
		this.deviceInfoInfoData["selectDeviceNoType"] = $("#selectDeviceNoType")
				.val();
		// 设备号（多个用,分隔)
		this.deviceInfoInfoData["deviceNoStr"] = $("#deviceNoStr").val();
		// 开始的设备编号
		this.deviceInfoInfoData["startDeviceNo"] = $("#startDeviceNo").val();
		// 结束的设备编号
		this.deviceInfoInfoData["endDeviceNo"] = $("#endDeviceNo").val();
		// 商户号...
		this.deviceInfoInfoData["onlineMerchantId"] = $("#onlineMerchantId_hd").val();
		//
		if (this.deviceInfoInfoData["selectDeviceNoType"] == "1"
				&& (this.deviceInfoInfoData["deviceNoStr"] == null
						|| this.deviceInfoInfoData["deviceNoStr"] == undefined || this.deviceInfoInfoData["deviceNoStr"] == "")) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量设置商户的设备号，多个请用逗号分隔!");
			return;
		}
		//
		if (this.deviceInfoInfoData["selectDeviceNoType"] == "2"
				&& (this.deviceInfoInfoData["startDeviceNo"] == null
						|| this.deviceInfoInfoData["startDeviceNo"] == undefined || this.deviceInfoInfoData["startDeviceNo"] == ""
						|| this.deviceInfoInfoData["endDeviceNo"] == null
						|| this.deviceInfoInfoData["endDeviceNo"] == undefined || this.deviceInfoInfoData["endDeviceNo"] == "")) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量设置开始和结束设备号!");
			return;
		}
		if (this.deviceInfoInfoData["selectDeviceNoType"] == "3"
			&& (this.deviceInfoInfoData["onlineMerchantId"] == null)) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("请终端商户号!");
			return;
		}
		if((this.deviceInfoInfoData["deviceStatus"]==null||this.deviceInfoInfoData["deviceStatus"]==undefined
				||this.deviceInfoInfoData["deviceStatus"]=="0"||this.deviceInfoInfoData["deviceStatus"]=="")&&
				(this.deviceInfoInfoData["yfjRebackType"]==null||this.deviceInfoInfoData["yfjRebackType"]==undefined
						||this.deviceInfoInfoData["yfjRebackType"]=="0"||this.deviceInfoInfoData["yfjRebackType"]=="")){
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("未输入任何修改内容，请输入需要修改的字段内容!");
			return;
		}
		if(this.deviceInfoInfoData["deviceStatus"]!=null
				&&this.deviceInfoInfoData["deviceStatus"]!=undefined
				&&this.deviceInfoInfoData["deviceStatus"]=="12"){
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("不支付把设备状态批量修改为待确认状态，请选择正确的设备状态!");
			return;
		}
		if(this.deviceInfoInfoData["deviceStatus"]==null||this.deviceInfoInfoData["deviceStatus"]==undefined
				||this.deviceInfoInfoData["deviceStatus"]!="10"){
			DeviceInfoInfoDlg.BatchBindOtherForSave();
		}else{
			//判断是否要绑定商户...
			//1. 提交信息 
			var ajax = new $ax(Feng.ctxPath + "/deviceInfo/getDevicesActivationModeForBatch",
					function(data) {
						DeviceInfoInfoDlg.finishShieldLayer();
						if (data != null && data.result == "success") {
							if(data.responseInfo==null||data.responseInfo==undefined||data.responseInfo==""){
								DeviceInfoInfoDlg.BatchBindOtherForSave();
							}else{
								//弹出提示用户绑定商
								DeviceInfoInfoDlg.nextDoForBind=DeviceInfoInfoDlg.BatchBindOtherForSave;
								DeviceInfoInfoDlg.bindTerminalWindow();
							}
						} else {
							DeviceInfoInfoDlg.operateFlag = 0;
							Feng.error(data.message);
						}
					}, function(data) {
						DeviceInfoInfoDlg.finishShieldLayer();
						DeviceInfoInfoDlg.operateFlag = 0;
						Feng.error("修改失败!" + data.responseJSON.message + "!");
					}
			);
			ajax.set(this.deviceInfoInfoData);
			ajax.start();
		}
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	}
}
/**
 * 保存批量修改...
 */
DeviceInfoInfoDlg.BatchBindOtherForSave=function(){
	try{
		//1. 提交信息 
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/BatchBindOther",
				function(data) {
					DeviceInfoInfoDlg.finishShieldLayer();
					if (data != null && data.result == "success") {
						Feng.success(data.message);
						window.parent.DeviceInfo.table.refresh();
						DeviceInfoInfoDlg.close();
					} else {
						DeviceInfoInfoDlg.operateFlag = 0;
						Feng.error(data.message);
					}
				}, function(data) {
					DeviceInfoInfoDlg.finishShieldLayer();
					DeviceInfoInfoDlg.operateFlag = 0;
					Feng.error("修改失败!" + data.responseJSON.message + "!");
				}
		);
		ajax.set(this.deviceInfoInfoData);
		ajax.start();
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	}
}
/**
 * 绑定终端店铺失去焦点
 */
DeviceInfoInfoDlg.onlineMerchantIdOnBlur = function(obj, merchantTypeId) {
	var id = obj.id;
	var hdId = id + "_hd"
	var param = {};
	param.merchantId = $("#" + id).val();
	param.merchantTypeId = merchantTypeId;

	if (param.merchantId == null || param.merchantId == undefined
			|| param.merchantId == "0" || param.merchantId == ""
			|| param.merchantId == " ") {
		$("#" + id).val("");
		$("#" + hdId).val("");
	} else {
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/selectMerchant",
				function(data) {
					if (data != null && data.result == "error") {
						Feng.error(data.message);
					} else {
						$("#" + id).val(
								data.responseInfo + "(" + param.merchantId
										+ ")");
						$("#" + hdId).val(param.merchantId);
					}
				}, function(data) {
					Feng.error("获取商户信息失败!" + data.responseJSON.message + "!");
				});
		ajax.set(param);
		ajax.start();
	}
}
/**
 * 绑定终端店铺得到焦点
 */
DeviceInfoInfoDlg.onlineMerchantIdOnFocus = function(obj, merchantTypeId) {
	var id = obj.id;
	var hdId = id + "_hd"
	var param = {};
	param.merchantId = $("#" + id).val();
	param.merchantTypeId = merchantTypeId;
	var merchantId = $("#" + hdId).val();
	$("#" + id).val(merchantId);
}
/**
 * 当用户修改设备类型时处理...
 */
DeviceInfoInfoDlg.deviceTypeIdOnChange = function() {
	var deviceTypeId = $("#deviceTypeId").val();
	var feeTypeId = $("#feeTypeIdHidden").val();
	var isFeeTypeByTime = true;
	if(feeTypeId != 0){
	    isFeeTypeByTime = false;
	}
	// Feng.info(deviceTypeId);
	if (deviceTypeId != null && deviceTypeId != undefined && deviceTypeId != "") {
		if (deviceTypeId == 11 || deviceTypeId == 31) {
			$("#feeSetCommon").hide();
			$("#feeSetByTime").show();
			//隐藏费用类型单选，只有33类型可以选
			$("#chooseFeeTypeByTime").hide();
		} else if(deviceTypeId == 33|| deviceTypeId == 21 ){
		    if(isFeeTypeByTime){
                $("#feeSetCommon").hide();
                $("#feeSetByTime").show();
                //显示费用类型单选，只有33类型可以选
                $("#chooseFeeTypeByTime").show();
//                 $('input:radio').eq(3).attr('checked', 'true');
                $('input:radio:last').attr('checked', 'checked');
		    }else{
		        $("#feeSetCommon").show();
                $("#feeSetByTime").hide();
                //显示费用类型单选，只有33类型可以选
                $("#chooseFeeTypeByYfj").show();
                var feeTypeId = $("#feeTypeId").val();
                staticFeeTypeId = feeTypeId;
//                 $('input:radio').eq(2).attr('checked', 'true');
                $('input:radio:first').attr('checked', 'checked');
		    }
		}else {
			$("#feeSetCommon").show();
			$("#feeSetByTime").hide();
            //隐藏费用类型单选，只有33类型可以选
            $("#chooseFeeTypeByYfj").hide();
		}
	}
}
/**
 * 当用户修改设备类型时处理...
 */
DeviceInfoInfoDlg.deviceTypeIdBatchOnChange = function() {
	var deviceTypeId = $("#deviceTypeId").val();
	if (deviceTypeId != null && deviceTypeId != undefined && deviceTypeId != "") {
		var isChecked = $("#batchSetFee").is(':checked');
		// Feng.info(deviceTypeId);
		if (isChecked) {
			if (deviceTypeId == 11  || deviceTypeId == 31) {
				$("#feeBatchSetCommon").hide();
				$("#feeBatchSetByTime").show();
				$("#chooseFeeTypeByYfj_2").hide();
			} else if(deviceTypeId == 33|| deviceTypeId == 21){
			    $("#chooseFeeTypeByYfj_1").show();
			    $("#feeBatchSetCommon").show();
                $("#feeBatchSetByTime").hide();
			}else {
				$("#feeBatchSetCommon").show();
				$("#feeBatchSetByTime").hide();
				$("#chooseFeeTypeByYfj_1").hide();
			}
		} else {
		    $("#chooseFeeTypeByYfj").hide();
			$("#feeBatchSetCommon").hide();
			$("#feeBatchSetByTime").hide();
		}
	}
}
/**
 * 批量设置充电器..
 */
DeviceInfoInfoDlg.batchSetChargerOnClick = function() {
	var isChecked = $("#batchSetCharger").is(':checked');
	if (isChecked) {
		$("#chargerSet").show();
	} else {
		$("#chargerSet").hide();
	}
}
/**
 * 批量设置费用
 */
DeviceInfoInfoDlg.batchSetFeeChkOnClick = function() {
	DeviceInfoInfoDlg.deviceTypeIdBatchOnChange();
}
/**
 * 批量设置商户..
 */
DeviceInfoInfoDlg.batchSetMerchOnClick = function() {
	var isChecked = $("#batchSetMerch").is(':checked');
	if (isChecked) {
		$("#bindMerchant").show();
	} else {
		$("#bindMerchant").hide();
	}
}

/**
 * 修改分润...
 */
DeviceInfoInfoDlg.merchantRatoOnChange = function(obj, merchantTypeId) {
	var platformRato = $("#platformRato_hd").val();// 平台分润比例
	var agents1Rato = $("#agents1Rato").val();// 一级代理分润比例
	var agents2Rato = $("#agents2Rato").val();// 二级代理分润比例
	var agents3Rato = $("#agents3Rato").val();// 三级代理分润比例
	var shopkeeperRato = $("#shopkeeperRato").val();// 铺货商分润比例
	var allianceBusinessRate = $("#allianceBusinessRate").val();// 铺货商分润比例
	agents1Rato = parseFloat(agents1Rato);
	if ((isNaN(agents1Rato))) {
		agents1Rato = 0;
	}
	agents2Rato = parseFloat(agents2Rato);
	if ((isNaN(agents2Rato))) {
		agents2Rato = 0;
	}
	agents3Rato = parseFloat(agents3Rato);
	if ((isNaN(agents3Rato))) {
		agents3Rato = 0;
	}
	shopkeeperRato = parseFloat(shopkeeperRato);
	if ((isNaN(shopkeeperRato))) {
		shopkeeperRato = 0;
	}
	allianceBusinessRate = parseFloat(allianceBusinessRate);
	if ((isNaN(allianceBusinessRate))) {
		allianceBusinessRate = 0;
	}
	if (merchantTypeId == 11) {
		platformRato = 100 - agents1Rato - agents2Rato - agents3Rato
				- shopkeeperRato - allianceBusinessRate;
		if (platformRato < 0) {
			Feng.error("分润比较之和大于100,请输入正确的分润比例!");
		} else {
			$("#platformRato_hd").val(platformRato);
			$("#platformRato").html("平台分润(" + platformRato + "%)");
		}
	} else {
		agents1Rato = 100 - platformRato - agents2Rato - agents3Rato
				- shopkeeperRato - allianceBusinessRate;
		if (agents1Rato < 0) {
			Feng.error("顶级代理的可分配的比例不足，请先调整顶组代码分润!");
		} else {
			$("#agents1Rato").val(agents1Rato);
		}
	}
}
//绑定商户...
DeviceInfoInfoDlg.batchBindMerUser= function() {
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		DeviceInfoInfoDlg.operateFlag = 1;
		this.clearData();
		this.collectData();
		if(devicesList==null||devicesList.length<=0){
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("未获取有权限绑定的设备, 请点击“查询”获取有权限绑定的设备!");
			return;
		}
		var deviceNoStr="";
		for(var i=0;i<devicesList.length;i++){
			if(i==0){
				deviceNoStr=devicesList[i];
			}else{
				deviceNoStr=deviceNoStr+","+devicesList[i];
			}
		}
		// 选择设备类型
		this.deviceInfoInfoData["selectDeviceNoType"] = "1";
		// 设备号（多个用,分隔)
		this.deviceInfoInfoData["deviceNoStr"] = deviceNoStr;
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/deviceInfo/batchBindMer", function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			if (data != null && data.result == "success") {
				Feng.success(data.message);
				window.parent.DeviceInfo.table.refresh();
				DeviceInfoInfoDlg.close();
			} else {
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error(data.message);
			}
		}, function(data) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("修改失败!" + data.responseJSON.message + "!");
		});
		ajax.set(DeviceInfoInfoDlg.deviceInfoInfoData);
		ajax.start();
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	}
}
/**
 * 批量绑定商户...
 */
DeviceInfoInfoDlg.batchBindMer = function() {
	DeviceInfoInfoDlg.startShieldLayer();
	if (DeviceInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		DeviceInfoInfoDlg.operateFlag = 1;
		this.clearData();
		this.collectData();
		// 选择设备类型
		this.deviceInfoInfoData["selectDeviceNoType"] = $("#selectDeviceNoType")
				.val();
		// 设备号（多个用,分隔)
		this.deviceInfoInfoData["deviceNoStr"] = $("#deviceNoStr").val();
		// 开始的设备编号
		this.deviceInfoInfoData["startDeviceNo"] = $("#startDeviceNo").val();
		// 结束的设备编号
		this.deviceInfoInfoData["endDeviceNo"] = $("#endDeviceNo").val();
		//
		if (this.deviceInfoInfoData["selectDeviceNoType"] == "1"
				&& (this.deviceInfoInfoData["deviceNoStr"] == null
						|| this.deviceInfoInfoData["deviceNoStr"] == undefined || this.deviceInfoInfoData["deviceNoStr"] == "")) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量设置商户的设备号，多个请用逗号分隔!");
			return;
		}
		//
		if (this.deviceInfoInfoData["selectDeviceNoType"] == "2"
				&& (this.deviceInfoInfoData["startDeviceNo"] == null
						|| this.deviceInfoInfoData["startDeviceNo"] == undefined
						|| this.deviceInfoInfoData["startDeviceNo"] == ""
						|| this.deviceInfoInfoData["endDeviceNo"] == null
						|| this.deviceInfoInfoData["endDeviceNo"] == undefined || this.deviceInfoInfoData["endDeviceNo"] == "")) {
			DeviceInfoInfoDlg.finishShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量设置商户的开始和结束设备号!");
			return;
		}
		var msg = "";
		if ((this.deviceInfoInfoData["agents1Rato"] == ""
				|| this.deviceInfoInfoData["agents1Rato"] == null || this.deviceInfoInfoData["agents1Rato"] == undefined)
				&& (this.deviceInfoInfoData["agents2Rato"] == ""
						|| this.deviceInfoInfoData["agents2Rato"] == null || this.deviceInfoInfoData["agents2Rato"] == undefined)
				&& (this.deviceInfoInfoData["agents3Rato"] == ""
						|| this.deviceInfoInfoData["agents3Rato"] == null || this.deviceInfoInfoData["agents3Rato"] == undefined)
				&& (this.deviceInfoInfoData["shopkeeperRato"] == ""
						|| this.deviceInfoInfoData["shopkeeperRato"] == null || this.deviceInfoInfoData["shopkeeperRato"] == undefined)
				&& (this.deviceInfoInfoData["allianceBusinessRate"] == ""
						|| this.deviceInfoInfoData["allianceBusinessRate"] == null || this.deviceInfoInfoData["allianceBusinessRate"] == undefined)) {
			msg = "您未输入任何商户分润，系统将保留原商户，若要清楚原分润可以输入0, 确定提交吗?"
		} else {
			var agents1Rato = parseFloat(this.deviceInfoInfoData["agents1Rato"]);
			var agents2Rato = parseFloat(this.deviceInfoInfoData["agents2Rato"]);
			var agents3Rato = parseFloat(this.deviceInfoInfoData["agents3Rato"]);
			var shopkeeperRato = parseFloat(this.deviceInfoInfoData["shopkeeperRato"]);
			var allianceBusinessRate = parseFloat(this.deviceInfoInfoData["allianceBusinessRate"]);
			if (isNaN(agents1Rato)) {
				agents1Rato = 0;
			}
			if (isNaN(agents2Rato)) {
				agents2Rato = 0;
			}
			if (isNaN(agents3Rato)) {
				agents3Rato = 0;
			}
			if (isNaN(shopkeeperRato)) {
				shopkeeperRato = 0;
			}
			if (isNaN(allianceBusinessRate)) {
				allianceBusinessRate = 0;
			}
			if (agents1Rato < 0 || agents2Rato < 0 || agents3Rato < 0
					|| shopkeeperRato < 0 || allianceBusinessRate < 0) {
				DeviceInfoInfoDlg.finishShieldLayer();
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error("输入的分润必须是0～100的数值，请输入正确的分润!");
				return;
			}
			var platformRate = 100 - agents1Rato - agents2Rato - agents3Rato
					- shopkeeperRato - allianceBusinessRate;
			if (platformRate < 0) {
				DeviceInfoInfoDlg.finishShieldLayer();
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error("您输入的所有商户分润之和大于100，请输入正确的分润!");
				return;
			}			
			msg = "您已输入了商户分润，系统将更新设备所有商户分润, 确定提交吗?"
			if(!isNaN(agents1Rato)){
				this.deviceInfoInfoData["agents1Rato"]=agents1Rato;
			}
			if(!isNaN(agents2Rato)){
				this.deviceInfoInfoData["agents2Rato"]=agents2Rato;
			}

			if(!isNaN(agents3Rato)){
				this.deviceInfoInfoData["agents3Rato"]=agents3Rato;
			}
			if(!isNaN(shopkeeperRato)){
				this.deviceInfoInfoData["shopkeeperRato"]=shopkeeperRato;
			}
			if(!isNaN(allianceBusinessRate)){
				this.deviceInfoInfoData["allianceBusinessRate"]=allianceBusinessRate;
			}
		}
		Feng.confirm(msg,function(){
			DeviceInfoInfoDlg.startShieldLayer();
			DeviceInfoInfoDlg.operateFlag = 1;
			// 3.服务器提交
			// 提交信息
			var ajax = new $ax(Feng.ctxPath + "/deviceInfo/batchBindMer", function(data) {
				DeviceInfoInfoDlg.finishShieldLayer();
				if (data != null && data.result == "success") {
					Feng.success(data.message);
					window.parent.DeviceInfo.table.refresh();
					DeviceInfoInfoDlg.close();
				} else {
					DeviceInfoInfoDlg.operateFlag = 0;
					Feng.error(data.message);
				}
			}, function(data) {
				DeviceInfoInfoDlg.finishShieldLayer();
				DeviceInfoInfoDlg.operateFlag = 0;
				Feng.error("修改失败!" + data.responseJSON.message + "!");
			});
			ajax.set(DeviceInfoInfoDlg.deviceInfoInfoData);
			ajax.start();
		})
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	} catch (e) {
		DeviceInfoInfoDlg.finishShieldLayer();
		DeviceInfoInfoDlg.operateFlag = 0;
	}
}
/**
 * 用户修改费用类型时。。
 */
DeviceInfoInfoDlg.feeTypeIdOnChange = function() {
	var feeTypeId = $("#feeTypeId").val();
	staticFeeTypeId = feeTypeId;
	if (feeTypeId == 26) {
		$("#firstMinutes_a").show();
		$("#amountPerHour_label").html("超过预先使用时长后每小时收费(元）");
	} else {
		$("#firstMinutes_a").hide();
		$("#amountPerHour_label").html("每小时使用费用(元)");
	}
}
/**
 * 开启屏蔽层.
 */
DeviceInfoInfoDlg.startShieldLayer = function() {
	$('#myModal').modal("show");
}
/**
 * 结束屏蔽层.
 */
DeviceInfoInfoDlg.finishShieldLayer = function() {
	$('#myModal').modal("hide");
}
/**
 * 打开绑定终端商户
 */
DeviceInfoInfoDlg.bindTerminalWindow = function() {
	$('#bindTerminal').modal("show");
}

/**
 * 在闭绑定终端商户.
 */
DeviceInfoInfoDlg.closeBindTermainalWindow = function() {
	$('#bindTerminal').modal("hide");
	DeviceInfoInfoDlg.nextDoForBind=null;
	
	DeviceInfoInfoDlg.finishShieldLayer();
	DeviceInfoInfoDlg.operateFlag = 0;
}
/**
 * 根据手机号得到商户。。。
 */
DeviceInfoInfoDlg.getMerchantInfoByTelNo=function(){
	var telNo=$("#terminalTelNo").val();
	if(telNo==null||telNo==undefined||telNo==""){
		$("#terminalMerchantId").val("");
		$("#spnterminalMerchantId").html("");
		$("#terminalMerchantName").val("");
		$("#terminalPersonName").val("");
		$("#terminalRemarkTitle").text("");
		return;
	}
	var ajax = new $ax(Feng.ctxPath + "/deviceInfo/getMerchantInfoByTelNo", function(data) {
		DeviceInfoInfoDlg.finishShieldLayer();
		if (data != null && data.result == "success") {
			if(data.responseInfos.length>0){
				//
				$("#terminalMerchantId").val(data.responseInfo.id);
				$("#spnterminalMerchantId").html(data.responseInfo.name+"("+data.responseInfo.id+")");
				$("#terminalMerchantName").val(data.responseInfo.name);
				$("#terminalPersonName").val(data.responseInfo.personName);
				$("#terminalMerchantAddress").val(data.responseInfo.addr);
				$("#terminalRemarkTitle").text(" (商户已存，已绑定"+data.message+"个设备)");
			}else{
				$("#terminalMerchantId").val("");
				$("#spnterminalMerchantId").html("");
				$("#terminalRemarkTitle").text("");
				
			}
		} else {
			Feng.error(data.message);
			$("#terminalMerchantId").val("");
			$("#spnterminalMerchantId").html("");
			$("#terminalMerchantName").val("");
			$("#terminalPersonName").val("");
			$("#terminalRemarkTitle").text("");
		}
	}, function(data) {
		$("#terminalMerchantId").val("");
		$("#spnterminalMerchantId").html("");
		$("#terminalMerchantName").val("");
		$("#terminalPersonName").val("");
		$("#terminalRemarkTitle").text("");
	});
	ajax.set({"merTelNo":telNo});
	ajax.start();
}
/**
 * 得到省
 */
DeviceInfoInfoDlg.onchangeProvince=function(){
	var parentId=$("#terminalProvince").val();
	$("#terminalCity").empty();
	$("#terminalCity").append("<option value=''> </option>");
	var ajax = new $ax(Feng.ctxPath + "/deviceInfo/getProvinceCityZondeOptions", function(data) {
		if (data != null && data.result == "success") {
			if(data.responseInfos.length>0){
				for(var i=0;i<data.responseInfos.length;i++){
					$("#terminalCity").append("<option value='"+data.responseInfos[i].id+"'>"+data.responseInfos[i].name+"</option>");
				}
			}
		}
	}, function(data) {
	
	});
	ajax.set({"parentId":parentId});
	ajax.start();
}
/**
 * 得到省市
 */
DeviceInfoInfoDlg.onchangeCity=function(){
	var parentId=$("#terminalCity").val();
	$("#terminalZone").empty();
	$("#terminalZone").append("<option value=''> </option>");
	var ajax = new $ax(Feng.ctxPath + "/deviceInfo/getProvinceCityZondeOptions", function(data) {
		if (data != null && data.result == "success") {
			if(data.responseInfos.length>0){
				for(var i=0;i<data.responseInfos.length;i++){
					$("#terminalZone").append("<option value='"+data.responseInfos[i].id+"'>"+data.responseInfos[i].name+"</option>");
				}
			}
		}
	}, function(data) {
	
	});
	ajax.set({"parentId":parentId});
	ajax.start();
}
/**
 * 提交绑定终端...
 */
DeviceInfoInfoDlg.bindTermainalForSave=function(){
	//1.绑定参数
	this.deviceInfoInfoData["terminalTelNo"] = $("#terminalTelNo").val();
	this.deviceInfoInfoData["terminalMerchantId"] = $("#terminalMerchantId").val();
	this.deviceInfoInfoData["terminalMerchantName"] = $("#terminalMerchantName").val();
	this.deviceInfoInfoData["terminalPersonName"] = $("#terminalPersonName").val();
	this.deviceInfoInfoData["terminalProvinceId"] = $("#terminalProvince").val();
	this.deviceInfoInfoData["terminalProvince"] = $("#terminalProvince").find("option:selected").text();
	this.deviceInfoInfoData["terminalCityId"] = $("#terminalCity").val();
	this.deviceInfoInfoData["terminalCity"] = $("#terminalCity").find("option:selected").text();
	this.deviceInfoInfoData["terminalZoneId"] = $("#terminalZone").val();
	this.deviceInfoInfoData["terminalZone"] = $("#terminalZone").find("option:selected").text();
	this.deviceInfoInfoData["terminalMerchantAddress"] = $("#terminalMerchantAddress").val();
	this.deviceInfoInfoData["terminalRemark"] = $("#terminalRemark").val();
	if(this.deviceInfoInfoData.terminalTelNo==null
			||this.deviceInfoInfoData["terminalTelNo"]==undefined
			||this.deviceInfoInfoData["terminalTelNo"]==""){
		Feng.error("请输入店铺联系人手机号！");
		return;
	}
	var regx = /^0?(13|15|17|18|14)[0-9]{9}$|^[0][0][8][5][2]([5|6|9])\d{7}$|^[0][0][8][5][3][6]\d{7}$|^[0][0][8][8][6][0][9]\d{8}$/;			
	if(!regx.test(this.deviceInfoInfoData.terminalTelNo)){
		Feng.error("您输入的店铺联系人手机号格式不正确!");
		return;
	}
	if(this.deviceInfoInfoData["terminalMerchantName"]==null
			||this.deviceInfoInfoData["terminalMerchantName"]==undefined
			||this.deviceInfoInfoData["terminalMerchantName"]==""){
		Feng.error("店铺名称不能为空，请输入店铺名称!");
		return;
	}
	if(this.deviceInfoInfoData["terminalPersonName"]==null
			||this.deviceInfoInfoData["terminalPersonName"]==undefined
			||this.deviceInfoInfoData["terminalPersonName"]==""){
		Feng.error("联系人名称不能为空，请输入联系人名称!");
		return;
	}
	if(this.deviceInfoInfoData["terminalProvince"]==null
			||this.deviceInfoInfoData["terminalProvince"]==undefined
			||this.deviceInfoInfoData["terminalProvince"]==""){
		Feng.error("请选择店铺地址省市区!");
		return;
	}
	//2. 提交后台处理程序..
	if(DeviceInfoInfoDlg.nextDoForBind!=null&&DeviceInfoInfoDlg.nextDoForBind!=undefined){
		$('#bindTerminal').modal("hide");		
		DeviceInfoInfoDlg.nextDoForBind();
	}else{
		DeviceInfoInfoDlg.closeBindTermainalWindow();		
	}
}
/**
 * 
 */
$(function() {
    $('input[type=radio][name=feeType]').change(function() {
      if (this.value == '1') {
         //alert("按预付费");
        staticFeeTypeId = $("#feeTypeId").val();
        $("#feeSetCommon").show();
        $("#feeSetByTime").hide();
        $("#chooseFeeTypeByYfj").show();
        $("input:radio[value='1']").attr('checked','true');
        $("input:radio[value='2']").attr('checked','false');
      }
      else if (this.value == '2') {
         //alert("按时间");
//        this.deviceInfoInfoData["feeTypeId"] = "";
        staticFeeTypeId = null;
        $("#feeSetCommon").hide();
        $("#feeSetByTime").show();
        $("#chooseFeeTypeByTime").show();
        $("input:radio[value='2']").attr('checked','true');
        $("input:radio[value='1']").attr('checked','false');
      }
    });

    //批量添加设备
    //预付费上的单选
    $('input[type=radio][name=batchFeeType_1]').change(function() {
        if(this.value == '1'){
             staticFeeTypeId = $("#feeTypeId").val();
            $("#feeBatchSetByTime").hide();
            $("#feeBatchSetCommon").show();
            $("#chooseFeeTypeByYfj_1").show();
            $("input:radio[value='1']").attr('checked','true');
            $("input:radio[value='2']").attr('checked','false');
        }else{
            staticFeeTypeId = null;
            $("#feeBatchSetByTime").show();
            $("#feeBatchSetCommon").hide();
            $("#chooseFeeTypeByYfj_2").show();
            $("input:radio[value='2']").attr('checked','true');
            $("input:radio[value='1']").attr('checked','false');
        }
    });
    //按时间上的单选
     $('input[type=radio][name=batchFeeType_2]').change(function() {
        if(this.value == '1'){
            staticFeeTypeId = $("#feeTypeId").val();
            $("#feeBatchSetByTime").hide();
            $("#feeBatchSetCommon").show();
            $("#chooseFeeTypeByYfj_1").show();
            $("input:radio[value='1']").attr('checked','true');
            $("input:radio[value='2']").attr('checked','false');
        }else{
            staticFeeTypeId = null;
            $("#feeBatchSetByTime").show();
            $("#feeBatchSetCommon").hide();
            $("#chooseFeeTypeByYfj_2").show();
            $("input:radio[value='2']").attr('checked','true');
            $("input:radio[value='1']").attr('checked','false');
         }
     });

});
var staticFeeTypeId;

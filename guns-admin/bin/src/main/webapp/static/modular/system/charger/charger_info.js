/**
 * 初始化device详情对话框
 */
var ChargerInfoDlg = {
    chargerInfoData : {},
	setGenerateDeviceFlag:1
};

/**
 * 清除数据
 */
ChargerInfoDlg.clearData = function() {
    this.chargerInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ChargerInfoDlg.set = function(key, val) {
    this.chargerInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ChargerInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ChargerInfoDlg.close = function() {
    parent.layer.close(window.parent.Charger.layerIndex);
}

/**
 * 收集数据
 */
ChargerInfoDlg.collectData = function() {
    this
    .set('id')
    .set('deviceId')
    .set('chargerTypeId')
    .set('pwdIndex')
    .set('pwds')
    .set('remark')
    .set('createId')
    .set('createDateTime')
    .set('updateDateTime')
    .set('lastUseTime');
}
/**
 * 检验数据
 */
ChargerInfoDlg.validateData=function(){
	return true;
}

/**
 * 提交添加
 */
ChargerInfoDlg.addSubmit = function() {
    ChargerInfoDlg.startShieldLayer();
	if (ChargerInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		ChargerInfoDlg.operateFlag = 1;
		// 1.清楚数据
		this.clearData();
		// 2.收集数据
		this.collectData();
		// 3.认证..
		if (!ChargerInfoDlg.validateData()) {
			ChargerInfoDlg.finishShieldLayer();
			this.operateFlag = 0;
			return false;
		}
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/charger/add", function(data) {
			ChargerInfoDlg.finishShieldLayer();
			if (data != null && data.result == "success") {
				Feng.success(data.message);
				window.parent.Charger.table.refresh();
				ChargerInfoDlg.close();
			} else {
				ChargerInfoDlg.operateFlag = 0;
				Feng.success(data.message);
			}
		}, function(data) {
			ChargerInfoDlg.finishShieldLayer();
			ChargerInfoDlg.operateFlag = 0;
			Feng.error("添加失败!" + data.responseJSON.message + "!");
		});
		ajax.set(this.chargerInfoData);
		ajax.start();
	} catch (e) {
		ChargerInfoDlg.finishShieldLayer();
		ChargerInfoDlg.operateFlag = 0;
		Feng.error("添加失败!");
	}
}

/**
 * 提交修改
 */
ChargerInfoDlg.editSubmit = function() {
    ChargerInfoDlg.startShieldLayer();
	if (ChargerInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		ChargerInfoDlg.operateFlag = 1;
		// 1.清楚数据
		this.clearData();
		// 2.收集数据
		this.collectData();
		// 3.认证..
		if (!ChargerInfoDlg.validateData()) {
			ChargerInfoDlg.finishShieldLayer();
			this.operateFlag = 0;
			return false;
		}
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/charger/update", function(data) {
			ChargerInfoDlg.finishShieldLayer();
			if (data != null && data.result == "success") {
				Feng.success(data.message);
				window.parent.Charger.table.refresh();
				ChargerInfoDlg.close();
			} else {
				ChargerInfoDlg.operateFlag = 0;
				Feng.success(data.message);
			}
		}, function(data) {
			ChargerInfoDlg.finishShieldLayer();
			ChargerInfoDlg.operateFlag = 0;
			Feng.error("修改失败!" + data.responseJSON.message + "!");
		});
		ajax.set(this.chargerInfoData);
		ajax.start();
	} catch (e) {
		ChargerInfoDlg.finishShieldLayer();
		ChargerInfoDlg.operateFlag = 0;
		Feng.error("修改失败!");
	}
}
/**
 * 查询输入的设备id是否存在.
 */
ChargerInfoDlg.deviceTypeIdOnChange=function(){
	var deviceId=$("#deviceId").val();
	if(deviceId!=null&&deviceId!=undefined&&deviceId!=""){
		this.chargerInfoData["deviceId"]=deviceId;
		 //提交信息
	    var ajax = new $ax(Feng.ctxPath + "/charger/selectDeviceTypeId", function(data){
	    	if (data == null ||data.result == "error") {
				Feng.success(data.message);
			}
	    },function(data){
	        Feng.error("输入设备编号在系统中不存在!" + data.responseJSON.message + "!");
	    });
	    ajax.set(this.chargerInfoData);
	    ajax.start();
	}
}
/**
 * 生成方式.
 */
ChargerInfoDlg.setGenerateOnClick=function(val){
	this.setGenerateDeviceFlag=val;
}
/**
 * 批量绑定设备号提交
 */
ChargerInfoDlg.batchBindDeviceSubmit=function(){
	ChargerInfoDlg.startShieldLayer();
	if (ChargerInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		ChargerInfoDlg.operateFlag = 1;
		// 1.清楚数据
		this.clearData();
		// 2.收集数据
		this.collectData();
		this.chargerInfoData["batchTypeForOperate"]=$("#batchTypeForOperate").val();
		this.chargerInfoData["chargerNoStr"]=$("#chargerNoStr").val();
		this.chargerInfoData["startChargerNo"]=$("#startChargerNo").val();
		this.chargerInfoData["endChargerNo"]=$("#endChargerNo").val();
		//
		if (this.chargerInfoData["batchTypeForOperate"] == "1"
				&& (this.chargerInfoData["chargerNoStr"] == null|| this.chargerInfoData["chargerNoStr"] == undefined
						 || this.chargerInfoData["chargerNoStr"] == "")) {
			ChargerInfoDlg.finishShieldLayer();
			ChargerInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量绑定充电器的编号，多个请用逗号分隔!");
			return false;
		}
		//
		if (this.chargerInfoData["batchTypeForOperate"] == "2"
				&& (this.chargerInfoData["startChargerNo"] == null
						|| this.chargerInfoData["startChargerNo"] == undefined || this.chargerInfoData["startChargerNo"] == ""
						|| this.chargerInfoData["endChargerNo"] == null
						|| this.chargerInfoData["endChargerNo"] == undefined || this.chargerInfoData["endChargerNo"] == "")) {
			ChargerInfoDlg.finishShieldLayer();
			ChargerInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量设置的开始和结束充电器编号!");
			return false;
		}
		// 3.认证..
		if (!ChargerInfoDlg.validateData()) {
			ChargerInfoDlg.finishShieldLayer();
			this.operateFlag = 0;
			return false;
		}
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/charger/batchBindDevice", function(data) {
			ChargerInfoDlg.finishShieldLayer();
			if (data != null && data.result == "success") {
				Feng.success(data.message);
				window.parent.Charger.table.refresh();
				ChargerInfoDlg.close();
			} else {
				ChargerInfoDlg.operateFlag = 0;
				Feng.success(data.message);
			}
		}, function(data) {
			ChargerInfoDlg.finishShieldLayer();
			ChargerInfoDlg.operateFlag = 0;
			Feng.error("批量添加失败!" + data.responseJSON.message + "!");
		});
		ajax.set(this.chargerInfoData);
		ajax.start();
	} catch (e) {
		ChargerInfoDlg.finishShieldLayer();
		ChargerInfoDlg.operateFlag = 0;
		Feng.error("批量添加失败!");
	}
}
/**
 * 批量增加充电器提交..
 */
ChargerInfoDlg.batchAddSubmit=function(){
	ChargerInfoDlg.startShieldLayer();
	if (ChargerInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		ChargerInfoDlg.operateFlag = 1;
		// 1.清楚数据
		this.clearData();
		// 2.收集数据
		this.collectData();
		this.chargerInfoData["chargerQty"]=$("#chargerQty").val();
		this.chargerInfoData["generateType"]=this.setGenerateDeviceFlag;
		if (this.chargerInfoData["chargerQty"] == null
			|| this.chargerInfoData["chargerQty"] == undefined || this.chargerInfoData["chargerQty"] == "") {
			ChargerInfoDlg.finishShieldLayer();
			ChargerInfoDlg.operateFlag = 0;
			Feng.error("请输入要批量生成的充电器数量!");
			return;
		}
		// 3.认证..
		if (!ChargerInfoDlg.validateData()) {
			ChargerInfoDlg.finishShieldLayer();
			this.operateFlag = 0;
			return false;
		}
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/charger/batchAddSubmit", function(data) {
			ChargerInfoDlg.finishShieldLayer();
			if (data != null && data.result == "success") {
				Feng.success(data.message);
				window.parent.Charger.table.refresh();
				ChargerInfoDlg.close();
			} else {
				ChargerInfoDlg.operateFlag = 0;
				Feng.success(data.message);
			}
		}, function(data) {
			ChargerInfoDlg.finishShieldLayer();
			ChargerInfoDlg.operateFlag = 0;
			Feng.error("批量添加失败!" + data.responseJSON.message + "!");
		});
		ajax.set(this.chargerInfoData);
		ajax.start();
	} catch (e) {
		ChargerInfoDlg.finishShieldLayer();
		ChargerInfoDlg.operateFlag = 0;
		Feng.error("批量添加失败!");
	}
}
/**
 * 开启屏蔽层.
 */
ChargerInfoDlg.startShieldLayer = function() {
	$('#myModal').modal("show");
}
/**
 * 结束屏蔽层.
 */
ChargerInfoDlg.finishShieldLayer = function() {
	$('#myModal').modal("hide");
}
$(function() {

});

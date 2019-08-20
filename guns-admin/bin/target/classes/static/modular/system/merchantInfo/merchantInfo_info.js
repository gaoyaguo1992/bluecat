/**
 * 初始化merchantInfo详情对话框
 */
var MerchantInfoInfoDlg = {
    merchantInfoInfoData : {},
	operateFlag : 0
};

/**
 * 清除数据
 */
MerchantInfoInfoDlg.clearData = function() {
    this.merchantInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MerchantInfoInfoDlg.set = function(key, val) {
    this.merchantInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MerchantInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MerchantInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.MerchantInfo.layerIndex);
}

/**
 * 收集数据
 */
MerchantInfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('addr')
    .set('personName')
    .set('telNo')
    .set('createTime')
    .set('updateTime')
    .set('merchantType')
    .set('merchantTypeCn')
    .set('parentMerchantId')
    .set('userId')
    .set('merchantLevel')
    .set('merchantLevelCn')
    .set('status')
    .set('remark')
    .set('merchantZone')
    .set('industryCategoryCode')
    .set('industryCategoryCn')
    .set('contractCycle')
    .set('settlementAccount')
    .set('hisTradeCount')
    .set('shopkeeperType')
    .set('shopkeeperTypeCn')
    .set('isOnlineService')
    .set('hisTradeAmount')
    .set('investMoney')
    .set('settlementCustNo')
    .set('clientType')
    .set('legalRepresentative')
    .set('uniformSocialCreditCode')
    .set('idNumber')
    .set('province')
    .set('city')
    .set('zone')
    .set('withdrawScale')
    .set('tradeAmountShowFlag')
    .set('shareQrCodePath')
    .set('receiver')
    .set('receiverTel')
    .set('receiverAddr')
    .set('currentRoundNum')
    .set('totalRoundNum')
    .set('idName')
    .set('advanceProfitFlag')
    .set('tecFeeAccumulate')
    .set('merchantSubType')
    .set('merchantSubTypeCn')
    .set('startShopTime')
    .set('endShopTime')
    .set('starLevel')
    .set('weekUseScale')
    .set('perConsume')
    .set('storePhoneNo')
    .set('profile')
    .set('isPhoneCheck')
    .set('withdrawWayId')
    .set('roomCount')
    .set('provinceId')
    .set('cityId')
    .set('zoneId')
    .set('isOldShelfProcess');
}
/**
 * 检验数据
 */
MerchantInfoInfoDlg.validateData = function() {
	return true;
}
/**
 * 提交添加
 */
MerchantInfoInfoDlg.addSubmit = function() {
	MerchantInfoInfoDlg.startShieldLayer();
	if (MerchantInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		MerchantInfoInfoDlg.operateFlag = 1;
	    this.clearData();
	    this.collectData();
		// 3.认证..
		if (!MerchantInfoInfoDlg.validateData()) {
			MerchantInfoInfoDlg.finishShieldLayer();
			this.operateFlag = 0;
			return false;
		}
	    //提交信息
	    var ajax = new $ax(Feng.ctxPath + "/merchantInfo/add", function(data){
	    	MerchantInfoInfoDlg.finishShieldLayer();
			if (data != null && data.result == "success") {
				Feng.success(data.message);
				window.parent.MerchantInfo.table.refresh();
				MerchantInfoInfoDlg.close();
			} else {
				MerchantInfoInfoDlg.operateFlag = 0;
				Feng.success(data.message);
			}
	    },function(data){
			MerchantInfoInfoDlg.finishShieldLayer();
			MerchantInfoInfoDlg.operateFlag = 0;
	        Feng.error("添加失败!" + data.responseJSON.message + "!");
	    });
	    ajax.set(this.merchantInfoInfoData);
	    ajax.start();
	} catch (e) {
		MerchantInfoInfoDlg.finishShieldLayer();
		MerchantInfoInfoDlg.operateFlag = 0;
        Feng.error("添加失败!");
	}
}

/**
 * 提交修改
 */
MerchantInfoInfoDlg.editSubmit = function() {
	MerchantInfoInfoDlg.startShieldLayer();
	if (MerchantInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		MerchantInfoInfoDlg.operateFlag = 1;
	    this.clearData();
	    this.collectData();
		// 3.认证..
		if (!MerchantInfoInfoDlg.validateData()) {
			MerchantInfoInfoDlg.finishShieldLayer();
			this.operateFlag = 0;
			return false;
		}
	    //提交信息
	    var ajax = new $ax(Feng.ctxPath + "/merchantInfo/update", function(data){
	    	if (data != null && data.result == "success") {
				Feng.success(data.message);
				window.parent.MerchantInfo.table.refresh();
				MerchantInfoInfoDlg.close();
			} else {
				MerchantInfoInfoDlg.operateFlag = 0;
				Feng.success(data.message);
			}
	    },function(data){
			MerchantInfoInfoDlg.finishShieldLayer();
			MerchantInfoInfoDlg.operateFlag = 0;
	        Feng.error("修改失败!" + data.responseJSON.message + "!");
	    });
	    ajax.set(this.merchantInfoInfoData);
	    ajax.start();
	} catch (e) {
		MerchantInfoInfoDlg.finishShieldLayer();
		MerchantInfoInfoDlg.operateFlag = 0;
	    Feng.error("修改失败!");
	}
}
/**
 * 处理父商户
 */
MerchantInfoInfoDlg.parentMerchantIdOnchange=function(){
	var parentMerchantId =$("#parentMerchantId").val();
	var param = {};
	param.merchantId =parentMerchantId;

	if (param.merchantId == null || param.merchantId == undefined|| param.merchantId == "0" || param.merchantId == "") {
		return;
	} else {
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/merchantInfo/getMerchantInfoById",
				function(data) {
					if (data != null && data.result == "error") {
						Feng.error(data.message);
					}
				}, function(data) {
					Feng.error("获取商户信息失败!" + data.responseJSON.message + "!");
				});
		ajax.set(param);
		ajax.start();
	}
}
/**
 * 开启屏蔽层.
 */
MerchantInfoInfoDlg.startShieldLayer = function() {
	$('#myModal').modal("show");
}
/**
 * 结束屏蔽层.
 */
MerchantInfoInfoDlg.finishShieldLayer = function() {
	$('#myModal').modal("hide");
}

$(function() {

});

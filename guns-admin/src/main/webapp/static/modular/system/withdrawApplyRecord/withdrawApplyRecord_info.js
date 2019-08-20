/**
 * 初始化商户提现订单详情对话框
 */
var WithdrawApplyRecordInfoDlg = {
    withdrawApplyRecordInfoData : {}
};

/**
 * 清除数据
 */
WithdrawApplyRecordInfoDlg.clearData = function() {
    this.withdrawApplyRecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WithdrawApplyRecordInfoDlg.set = function(key, val) {
    this.withdrawApplyRecordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WithdrawApplyRecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WithdrawApplyRecordInfoDlg.close = function() {
    parent.layer.close(window.parent.WithdrawApplyRecord.layerIndex);
}

/**
 * 收集数据
 */
WithdrawApplyRecordInfoDlg.collectData = function() {
    this
    .set('id')
    .set('applyTime')
    .set('applyMerchantId')
    .set('preTaxAmount')
    .set('aftTaxAmount')
    .set('withdrawStatus')
    .set('withdrawStatusCn')
    .set('approver')
    .set('approveTime')
    .set('approveStatus')
    .set('approveStatusCn')
    .set('approveComment')
    .set('shouldPayTax')
    .set('payer')
    .set('payTime')
    .set('invoiceNo')
    .set('manualDeduction')
    .set('numberOfMonth')
    .set('payWay')
    .set('isRealTime');
}

/**
 * 提交添加
 */
WithdrawApplyRecordInfoDlg.addSubmit = function() {
	var remark=$("#approveComment").val();
	var aftTaxAmount=$("#aftTaxAmount").val();
	var applyMerchantId=$("#applyMerchantId_hd").val();
	//参数
    var param={"remark":remark,"aftTaxAmount":aftTaxAmount,"applyMerchantId":applyMerchantId}
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/withdrawApplyRecord/add", function(data){
    	if(data==null||data.result!="success"){
    		//表示失败...
    		Feng.error("添加失败!"+data.message);
    		return;
    	}
    	
        Feng.success("添加成功!");
        window.parent.WithdrawApplyRecord.table.refresh();
        WithdrawApplyRecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(param);
    ajax.start();
}

/**
 * 提交修改
 */
WithdrawApplyRecordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/withdrawApplyRecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.WithdrawApplyRecord.table.refresh();
        WithdrawApplyRecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.withdrawApplyRecordInfoData);
    ajax.start();
}
/**
 * 查询商户信息
 */
WithdrawApplyRecordInfoDlg.merchantIdOnBlur=function(){
	debugger;
	var id="applyMerchantId";
	var hdId="applyMerchantId_hd";
	var merchantId = $("#"+id).val();
	var param={"merchantId":merchantId}
	if (merchantId == null || merchantId == undefined
			|| merchantId == "0" || merchantId == "" || merchantId == " ") {
		$("#"+id).val("");
		$("#"+hdId).val("");
	} else {
		// 提交信息
		var ajax = new $ax(Feng.ctxPath + "/withdrawApplyRecord/getMerchantInfoById",
				function(data) {
					if (data != null && data.result == "error") {
						Feng.error(data.message);
					} else {
						//MerchantInfoModel
						var item=data.responseInfo;
						$("#" + id).val(item.id + "(" + item.name+ ")");
						$("#" + hdId).val(item.id);
					}
				}, function(data) {
					Feng.error("获取商户信息失败!" + data.message + "!");
				});
		ajax.set(param);
		ajax.start();
	}
}
/**
 * 获到商户id..
 */
WithdrawApplyRecordInfoDlg.merchantIdOnFocus=function(){
	var id="applyMerchantId";
	var hdId="applyMerchantId_hd";
	var merchantId = $("#" + hdId).val();
	 $("#" + hdId).val("");
	$("#" + id).val(merchantId);
}
$(function() {

});

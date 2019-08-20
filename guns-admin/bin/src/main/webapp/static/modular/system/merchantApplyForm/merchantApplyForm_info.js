/**
 * 初始化详情对话框
 */
var MerchantApplyFormInfoDlg = {
    merchantApplyFormInfoData : {}
};

/**
 * 清除数据
 */
MerchantApplyFormInfoDlg.clearData = function() {
    this.merchantApplyFormInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MerchantApplyFormInfoDlg.set = function(key, val) {
    this.merchantApplyFormInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MerchantApplyFormInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MerchantApplyFormInfoDlg.close = function() {
    parent.layer.close(window.parent.MerchantApplyForm.layerIndex);
}

/**
 * 收集数据
 */
MerchantApplyFormInfoDlg.collectData = function() {
    this
    .set('id')
    .set('merchantName')
    .set('personName')
    .set('telNo')
    .set('province')
    .set('city')
    .set('zone')
    .set('agentsZone')
    .set('throwDevChannel')
    .set('applyType')
    .set('applyTypeCn')
    .set('doStatus')
    .set('doStatusCn')
    .set('custId')
    .set('createTime')
    .set('updateTime')
    .set('remark');
}

/**
 * 提交添加
 */
MerchantApplyFormInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/merchantApplyForm/add", function(data){
        Feng.success("添加成功!");
        window.parent.MerchantApplyForm.table.refresh();
        MerchantApplyFormInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.merchantApplyFormInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MerchantApplyFormInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/merchantApplyForm/update", function(data){
        Feng.success("修改成功!");
        window.parent.MerchantApplyForm.table.refresh();
        MerchantApplyFormInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.merchantApplyFormInfoData);
    ajax.start();
}

$(function() {

});

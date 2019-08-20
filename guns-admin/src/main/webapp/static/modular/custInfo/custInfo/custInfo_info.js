/**
 * 初始化用户信息详情对话框
 */
var CustInfoInfoDlg = {
	    id: "CustMerInfoTable",	//表格id
	    seItem: null,		//选中的条目
	    table: null,
	    layerIndex: -1,
	    operateFlag:0,
	    custInfoInfoData : {}
};
/**
 * 初始化表格的列
 */
CustInfoInfoDlg.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '商户编号', field: 'merchantId', visible: true, width:'100px', align: 'center', valign: 'middle'},
            {title: '商户名称', field: 'merchantName', visible: true, width:'100px', align: 'center', valign: 'middle'},
            {title: '商户类型', field: 'merchantTypeCn', visible: true, width:'100px', align: 'center', valign: 'middle'},
            {title: '客户编号', field: 'custNo', visible: true, width:'100px', align: 'center', valign: 'middle'},
            {title: '绑定时间', field: 'createTime', visible: true, width:'100px', align: 'center', valign: 'middle'}           
    ];
};
/**
 * 检查是否选中
 */
CustInfoInfoDlg.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	CustInfoInfoDlg.seItem = selected[0];
        return true;
    }
};
/**
 * 清除数据
 */
CustInfoInfoDlg.clearData = function() {
    this.custInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CustInfoInfoDlg.set = function(key, val) {
    this.custInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CustInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CustInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.CustInfo.layerIndex);
}

/**
 * 收集数据
 */
CustInfoInfoDlg.collectData = function() {
    this
    .set('merchantLoginTime')
    .set('merchantId')
    .set('custNo')
    .set('custName')
    .set('openId')
    .set('custType')
    .set('telNo')
    .set('telValid')
    .set('email')
    .set('addr')
    .set('postCode')
    .set('remark')
    .set('regTime')
    .set('updateTime')
    .set('latitude')
    .set('longitude')
    .set('mapPrecision')
    .set('nickName')
    .set('language')
    .set('city')
    .set('province')
    .set('country')
    .set('headImgUrl')
    .set('unionId')
    .set('wexinRemark')
    .set('groupId')
    .set('sex')
    .set('status')
    .set('lentChargerId')
    .set('yunDataId')
    .set('starLevel')
    .set('sweetHeartSum')
    .set('wxappOpenId')
    .set('custSourceType');
}

/**
 * 提交添加
 */
CustInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/custInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.CustInfo.table.refresh();
        CustInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.custInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CustInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/custInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.CustInfo.table.refresh();
        CustInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.custInfoInfoData);
    ajax.start();
}
/**
 * 添加商户到用户中...
 */
CustInfoInfoDlg.addMerchantSubmit=function(){
	CustInfoInfoDlg.startShieldLayer();
	if (CustInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		CustInfoInfoDlg.operateFlag = 1;
		var merchantId=$("#merchantId").val();
		if(merchantId==null||merchantId==undefined||merchantId==""){
			CustInfoInfoDlg.finishShieldLayer();
			CustInfoInfoDlg.operateFlag = 0;
		    Feng.error("请输入要绑定的商户号");
		    return;
		}
		var custId=$("#custId").val();
		var param={"merchantId":merchantId,"custId":custId};
		var refreshParam={"custId":custId};
		
		//提交信息
		var ajax = new $ax(Feng.ctxPath + "/custInfo/addMerchantSubmit", function(data){
			CustInfoInfoDlg.finishShieldLayer();
			CustInfoInfoDlg.operateFlag = 0;
		    if (data != null && data.result == "error") {
				Feng.error(data.message);
			} else {
				Feng.success("绑定成功!");
		        CustInfoInfoDlg.table.refresh();
		        $("#merchantId").val("");
			}
		},function(data){
			CustInfoInfoDlg.finishShieldLayer();
			CustInfoInfoDlg.operateFlag = 0;
		    Feng.error("绑定失败!" + data.responseJSON.message + "!");
		});
		ajax.set(param);
		ajax.start();
	} catch (e) {
		CustInfoInfoDlg.finishShieldLayer();
		CustInfoInfoDlg.operateFlag = 0;
		Feng.error("绑定失败!");
	}
}
/**
 * 删除绑定商户
 */
CustInfoInfoDlg.delMerchantSubmit=function(){
	CustInfoInfoDlg.startShieldLayer();
	if (CustInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		CustInfoInfoDlg.operateFlag = 1;
		if(this.check()){
			var custId=CustInfoInfoDlg.seItem.custNo;
			var merchantId=CustInfoInfoDlg.seItem.merchantId;
			var param={"merchantId":merchantId,"custId":custId};
			var refreshParam={"custId":custId};
			
			//提交信息
		    var ajax = new $ax(Feng.ctxPath + "/custInfo/delMerchantSubmit", function(data){
				CustInfoInfoDlg.finishShieldLayer();
				CustInfoInfoDlg.operateFlag = 0;
		        if (data != null && data.result == "error") {
					Feng.error(data.message);
				} else {
					Feng.success("解绑成功!");
			        CustInfoInfoDlg.table.refresh();
				}
		    },function(data){
				CustInfoInfoDlg.finishShieldLayer();
				CustInfoInfoDlg.operateFlag = 0;
		        Feng.error("解绑失败!" + data.responseJSON.message + "!");
		    });
		    ajax.set(param);
		    ajax.start();
		}else{
			CustInfoInfoDlg.finishShieldLayer();
			CustInfoInfoDlg.operateFlag = 0;
		}
	} catch (e) {
		CustInfoInfoDlg.finishShieldLayer();
		CustInfoInfoDlg.operateFlag = 0;
		Feng.error("绑定失败!");
	}
}


/**
 * 开启屏蔽层.
 */
CustInfoInfoDlg.startShieldLayer = function() {
	$('#myModal').modal("show");
}
/**
 * 结束屏蔽层.
 */
CustInfoInfoDlg.finishShieldLayer = function() {
	$('#myModal').modal("hide");
}
$(function() {

});

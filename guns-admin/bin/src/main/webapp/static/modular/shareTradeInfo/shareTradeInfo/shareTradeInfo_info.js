/**
 * 初始化交易订单详情对话框
 */
var ShareTradeInfoInfoDlg = {
	operateFlag:0,
    shareTradeInfoInfoData : {}
};

/**
 * 清除数据
 */
ShareTradeInfoInfoDlg.clearData = function() {
    this.shareTradeInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ShareTradeInfoInfoDlg.set = function(key, val) {
    this.shareTradeInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ShareTradeInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ShareTradeInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.ShareTradeInfo.layerIndex);
}

/**
 * 收集数据
 */
ShareTradeInfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('deviceNo')
    .set('chargerId')
    .set('custId')
    .set('yfjAmount')
    .set('tradeAmount')
    .set('tradeType')
    .set('tradeName')
    .set('borrowDatetime')
    .set('backDatetime')
    .set('tradeStatusName')
    .set('tradeStatus')
    .set('xCoordinate')
    .set('yCoordinate')
    .set('benefitStatus')
    .set('benefitDatetime')
    .set('platformAmount')
    .set('agents1Amount')
    .set('agents2Amount')
    .set('agents3Amount')
    .set('shopkeeperAmount')
    .set('allianceBusinessAmount')
    .set('backTradeId')
    .set('tradeAddress')
    .set('tradeZone')
    .set('tradeCity')
    .set('tradeProvince')
    .set('merchantId')
    .set('agents1Id')
    .set('agents2Id')
    .set('agents3Id')
    .set('shopkeeperId')
    .set('allianceBusinessId')
    .set('createId')
    .set('createDatetime')
    .set('updateDatetime')
    .set('settleAccountsStatus');
}

/**
 * 提交添加
 */
ShareTradeInfoInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/shareTradeInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.ShareTradeInfo.table.refresh();
        ShareTradeInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.shareTradeInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ShareTradeInfoInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/shareTradeInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.ShareTradeInfo.table.refresh();
        ShareTradeInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.shareTradeInfoInfoData);
    ajax.start();
}
/**
 * 结束订单。。。
 */
ShareTradeInfoInfoDlg.finishOrRefundOrder=function(){
	ShareTradeInfoInfoDlg.startShieldLayer();
	if (ShareTradeInfoInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		ShareTradeInfoInfoDlg.operateFlag = 1;
	    this.clearData();
	    this.collectData();

		var useSeconds=$("#useSeconds").val();
		var tradeAmoutForFinish=$("#tradeAmoutForFinish").val();
		var remarkForFinish=$("#remarkForFinish").val();
		var tradeAmoutForBack=$("#tradeAmoutForBack").val();
		var remarkForBack=$("#remarkForBack").val();
		var msg="";
		this.shareTradeInfoInfoData["tradeAmoutForFinish"]=tradeAmoutForFinish;
		this.shareTradeInfoInfoData["remarkForFinish"]=remarkForFinish;
		this.shareTradeInfoInfoData["tradeAmoutForBack"]=tradeAmoutForBack;
		this.shareTradeInfoInfoData["remarkForBack"]=remarkForBack;
		this.shareTradeInfoInfoData["useSeconds"]=useSeconds;
		
		if(this.shareTradeInfoInfoData["tradeStatus"]=="11"){
			msg="退款";
			if(this.shareTradeInfoInfoData["backTradeId"]!="" && this.shareTradeInfoInfoData["backTradeId"]!="0"
				&& this.shareTradeInfoInfoData["backTradeId"]!=null && this.shareTradeInfoInfoData["backTradeId"]!=undefined){
				ShareTradeInfoInfoDlg.finishShieldLayer();
				ShareTradeInfoInfoDlg.operateFlag = 0;
			    Feng.error("订单已退款，无需重复退款！");
			    return;
			}
			if(remarkForBack==null||remarkForBack==undefined||remarkForBack==""){
				ShareTradeInfoInfoDlg.finishShieldLayer();
				ShareTradeInfoInfoDlg.operateFlag = 0;
			    Feng.error("退款备注不能为空,请输入退款备注(原因)！");
			    return;
			}
			//表示已结束
			var tmp=parseFloat(tradeAmoutForBack);
			if(tradeAmoutForBack==null||tradeAmoutForBack==undefined||tradeAmoutForBack==""||isNaN(tmp)||tmp<=0){
				ShareTradeInfoInfoDlg.finishShieldLayer();
				ShareTradeInfoInfoDlg.operateFlag = 0;
			    Feng.error("退款金额必须大于0,请输入退款金额！");
			    return;
			}
			//表示已结束
			var tradeAmount=parseFloat(this.shareTradeInfoInfoData["tradeAmount"]);
			if(tmp>tradeAmount){
				ShareTradeInfoInfoDlg.finishShieldLayer();
				ShareTradeInfoInfoDlg.operateFlag = 0;
			    Feng.error("退款金额("+tmp+")大于订单可退金额("+tradeAmount+"),请输入正确的退款金额！");
			    return;
			}
		}else{
			//未结束
			msg="结束订单";
			//表示已结束
			if(remarkForFinish==null||remarkForFinish==undefined||remarkForFinish==""){
				ShareTradeInfoInfoDlg.finishShieldLayer();
				ShareTradeInfoInfoDlg.operateFlag = 0;
			    Feng.error("强制结束订单备注不能为空,请输入备注信息！");
			    return;
			}
			//表示已结束
			var tradeAmount=parseFloat(this.shareTradeInfoInfoData["tradeAmoutForFinish"]);
			var yfjAmount=parseFloat(this.shareTradeInfoInfoData["yfjAmount"])
			if(isNaN(tradeAmount)||tradeAmount>yfjAmount){
				ShareTradeInfoInfoDlg.finishShieldLayer();
				ShareTradeInfoInfoDlg.operateFlag = 0;
			    Feng.error("支付充电费("+tradeAmount+")必须小于等于订单预付金("+tradeAmount+"),请输入正确的支付费用！");
			    return;
			}
		}
		//提交信息
		var ajax = new $ax(Feng.ctxPath + "/shareTradeInfo/finishOrRefundOrder", function(data){
		    if (data != null && data.result == "error") {
				ShareTradeInfoInfoDlg.finishShieldLayer();
				ShareTradeInfoInfoDlg.operateFlag = 0;
				Feng.error(data.message);
			} else {
				Feng.success(msg+"成功!");
		        window.parent.ShareTradeInfo.table.refresh();
		        ShareTradeInfoInfoDlg.close();
			}
		},function(data){
			ShareTradeInfoInfoDlg.finishShieldLayer();
			ShareTradeInfoInfoDlg.operateFlag = 0;
		    Feng.error(msg+"失败!" + data.responseJSON.message + "!");
		});
		ajax.set(this.shareTradeInfoInfoData);
		ajax.start();
	} catch (e) {
		ShareTradeInfoInfoDlg.finishShieldLayer();
		ShareTradeInfoInfoDlg.operateFlag = 0;
		Feng.error("处理失败!");
	}
}

/**
 * 开启屏蔽层.
 */
ShareTradeInfoInfoDlg.startShieldLayer = function() {
	$('#myModal').modal("show");
}
/**
 * 结束屏蔽层.
 */
ShareTradeInfoInfoDlg.finishShieldLayer = function() {
	$('#myModal').modal("hide");
}
$(function() {

});

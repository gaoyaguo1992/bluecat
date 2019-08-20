/**
 * 交易订单管理初始化
 */
var ShareTradeInfo = {
    id: "ShareTradeInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ShareTradeInfo.initColumn = function () {
    return [
            {field: 'selectItem', radio: true},
            {title: '店铺名称', field: 'merchantName', visible: true,width: '170px', align: 'center', valign: 'middle'},
            {title: '交易金额', field: 'tradeAmount', visible: true, width: '90px', align: 'center', valign: 'middle'},
            {title: '交易状态', field: 'tradeStatusName', visible: true, width: '90px', align: 'center', valign: 'middle'},
            {title: '预付金', field: 'yfjAmount', visible: true,width: '90px',  align: 'center', valign: 'middle'},
            {title: '借出时间', field: 'borrowDatetime', visible: true, width: '180px', align: 'center', valign: 'middle'},
            {title: '归还时间', field: 'backDatetime', visible: true, width: '180px', align: 'center', valign: 'middle'},
            {title: '客户尼称', field: 'nickName', visible: true,width: '130px',  align: 'center', valign: 'middle'},
            {title: '交易id', field: 'id', visible: true,width: '130px',  align: 'center', valign: 'middle'},
            {title: '联系电话', field: 'merchantTelNo', visible: true, width: '130px', align: 'center', valign: 'middle'},
            {title: '客户编号', field: 'custId', visible: true,width: '130px',  align: 'center', valign: 'middle'},
            {title: '店铺商户', field: 'merchantId', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '设备号', field: 'deviceNo', visible: true, width: '130px', align: 'center', valign: 'middle'},
            {title: '充电器id', field: 'chargerId', visible: true, width: '130px', align: 'center', valign: 'middle'},
            {title: '商户地址', field: 'merchantAddr', visible: true, width: '130px', align: 'center', valign: 'middle'},
            {title: '商户联系人', field: 'merchantPersonName', visible: true, width: '130px', align: 'center', valign: 'middle'},
            {title: '交易名称', field: 'tradeName', visible: true, width: '130px', align: 'center', valign: 'middle'},
            {title: '交易类型', field: 'tradeType', visible: true, width: '130px', align: 'center', valign: 'middle',
            formatter: function(value,row,index){
          	  if(value == 25){
        		  return "交预付金充电";
        	  }
        	  if(value == 26){
        		  return "含有首付费用的预付金充电";
        	  }
        	  return value;
            }},
            {title: '结账状态', field: 'settleAccountsStatus', visible: true,width: '80px', align: 'center', valign: 'middle',
            	formatter: function(value,row,index){
            	  if(value == 0){
            		  return "实时结算";
            	  }
            	  if(value == 1){
            		  return "线下结算";
            	  }
            	  if(value == 2){
            		  return "月结算";
            	  }
            	  return value;
                }},
            {title: '退款订单', field: 'backTradeId', visible: true,width: '80px', align: 'center', valign: 'middle'},
            {title: '退款金额', field: 'refundAmount', visible: true,width: '80px', align: 'center', valign: 'middle'},
            {title: '分润状态', field: 'benefitStatus', visible: true, width: '130px', align: 'center', valign: 'middle',
            	formatter: function(value,row,index){
              	  if(value == 0){
              		  return "未分润";
              	  }
              	  if(value == 1){
              		  return "已分润";
              	  }
             }},
            {title: '分润时间', field: 'benefitDatetime', visible: true, width: '130px', align: 'center', valign: 'middle'},
            {title: '平台收入', field: 'platformAmount', visible: true,  width: '130px',align: 'center', valign: 'middle'},
            {title: '顶级代理商收入', field: 'agents1Amount', visible: true,  width: '150px',align: 'center', valign: 'middle'},
            {title: '一代理商收入', field: 'agents2Amount', visible: true,  width: '150px',align: 'center', valign: 'middle'},
            {title: '二代理商收入', field: 'agents3Amount', visible: true,  width: '150px', align: 'center', valign: 'middle'},
            {title: '铺货商收入', field: 'shopkeeperAmount', visible: true,width: '150px', align: 'center', valign: 'middle'},
            {title: '加盟商收入', field: 'allianceBusinessAmount', visible: true, width: '150px',align: 'center', valign: 'middle'},
            {title: '退款对应的交易id', field: 'backTradeId', visible: true,width: '150px', align: 'center', valign: 'middle'},
            {title: '交易地址', field: 'tradeAddress', visible: true,width: '150px', align: 'center', valign: 'middle'},
            {title: '顶级代理商编号', field: 'agents1Id', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '顶级代理商名称', field: 'agents1Name', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '顶级代理商联系电话', field: 'agents1TelNo', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '一级代理商编号', field: 'agents2Id', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '一级代理商名称', field: 'agents2Name', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '一级代理商联系电话', field: 'agents2TelNo', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '二级代理商编号', field: 'agents3Id', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '二级代理商名称', field: 'agents3Name', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '二级代理商联系电话', field: 'agents3TelNo', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '铺货商编号', field: 'shopkeeperId', visible: true, width: '130px',align: 'center', valign: 'middle'},
            {title: '铺货商名称', field: 'shopkeeperName', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '铺货商联系电话', field: 'shopkeeperTelNo', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '加盟商编号', field: 'allianceBusinessId', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '加盟商名称', field: 'allianceBussinessName', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '加盟商联系电话', field: 'allianceBussinessTelNo', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createDatetime', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updateDatetime', visible: true,width: '130px', align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true,width: '80px', align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ShareTradeInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ShareTradeInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加交易订单
 */
ShareTradeInfo.openAddShareTradeInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加交易订单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/shareTradeInfo/shareTradeInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看交易订单详情
 */
ShareTradeInfo.openShareTradeInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '交易订单详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/shareTradeInfo/shareTradeInfo_update/' + ShareTradeInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看交易订单详情
 */
ShareTradeInfo.advanceRefundOrder = function () {
    if (this.check()) {
    	if(ShareTradeInfo.seItem.tradeType>200){
    		Feng.error("此订单不持退款处理!");
    		return;
    	}
        var index = layer.open({
            type: 2,
            title: '订单退款或订单结束',
            area: ['800px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/shareTradeInfo/shareTradeInfo_AdvanceRefund/' + ShareTradeInfo.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 打开查看交易订单详情
 */
ShareTradeInfo.finishOrRefundOrder = function () {
    if (this.check()) {
    	if(ShareTradeInfo.seItem.tradeType>200){
    		Feng.error("此订单不持退款处理!");
    		return;
    	}
        var index = layer.open({
            type: 2,
            title: '订单退款或订单结束',
            area: ['800px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/shareTradeInfo/shareTradeInfo_finishOrRefund/' + ShareTradeInfo.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 删除交易订单
 */
ShareTradeInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/shareTradeInfo/delete", function (data) {
            Feng.success("删除成功!");
            ShareTradeInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("shareTradeInfoId",this.seItem.id);
        ajax.start();
    }
};
//导出交易数据
ShareTradeInfo.exportExcel = function(){

    var queryData = {};
    var condition=$("#condition").val();
    var value=$("#conditionValue").val(); 
    var condition1=$("#condition1").val();
    var value1=$("#conditionValue1").val();   
    
    var backTimeStart=$("#backTimeStart").val();
    var backTimeEnd=$("#backTimeEnd").val();
    var borrowTimeStart=$("#borrowTimeStart").val();
    var borrowTimeEnd=$("#borrowTimeEnd").val();
    var startDeviceNo = $("#startDeviceNo").val();
    var endDeviceNo = $("#endDeviceNo").val();
    var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
    if(startDeviceNo !=null && startDeviceNo != '' && !re.test(startDeviceNo)){
		Feng.info("起始设备号只能为纯数字！");
		return;
    }
    if(endDeviceNo !=null && endDeviceNo != '' &&  !re.test(endDeviceNo)){
		Feng.info("结束设备号只能为纯数字！");
		return;
    }
   
    queryData['startDeviceNo'] = startDeviceNo;
    queryData['endDeviceNo'] = endDeviceNo;
    queryData['condition'] = condition;
    queryData['conditionValue'] = value;
    queryData['condition1'] = condition1;
    queryData['conditionValue1'] = value1;
    queryData['backTimeStart'] = backTimeStart;
    queryData['backTimeEnd'] = backTimeEnd;
    queryData['borrowTimeStart'] = borrowTimeStart;
    queryData['borrowTimeEnd'] = borrowTimeEnd;
    var path = Feng.ctxPath + "/shareTradeInfo/exportExcel?condition="+condition
    +"&conditionValue="+value+"&backTimeStart="+backTimeStart
    +"&backTimeEnd="+backTimeEnd+"&borrowTimeStart="+borrowTimeStart
    +"&borrowTimeEnd="+borrowTimeEnd+"&condition1="+condition1
    +"&conditionValue1="+value1+"&startDeviceNo="+startDeviceNo+"&endDeviceNo="+endDeviceNo;
    alert("默认导出一个月的交易数据，请注意以上条件作导出筛选！");
    window.location.href = encodeURI(path);
	
};

/**
 * 查询交易订单列表
 */
ShareTradeInfo.search = function () {
    var queryData = {};
    var condition=$("#condition").val();
    var value=$("#conditionValue").val(); 
    var condition1=$("#condition1").val();
    var value1=$("#conditionValue1").val();   
    
    var backTimeStart=$("#backTimeStart").val();
    var backTimeEnd=$("#backTimeEnd").val();
    var borrowTimeStart=$("#borrowTimeStart").val();
    var borrowTimeEnd=$("#borrowTimeEnd").val();
    var startDeviceNo = $("#startDeviceNo").val();
    var endDeviceNo = $("#endDeviceNo").val();
    queryData['startDeviceNo'] = startDeviceNo;
    queryData['endDeviceNo'] = endDeviceNo;
    queryData['condition'] = condition;
    queryData['conditionValue'] = value;
    queryData['condition1'] = condition1;
    queryData['conditionValue1'] = value1;
    queryData['backTimeStart'] = backTimeStart;
    queryData['backTimeEnd'] = backTimeEnd;
    queryData['borrowTimeStart'] = borrowTimeStart;
    queryData['borrowTimeEnd'] = borrowTimeEnd;
    var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
    if(startDeviceNo !=null && startDeviceNo != '' && !re.test(startDeviceNo)){
		Feng.info("起始设备号只能为纯数字！");
		return;
    }
    if(endDeviceNo !=null && endDeviceNo != '' &&  !re.test(endDeviceNo)){
		Feng.info("结束设备号只能为纯数字！");
		return;
    }
    ShareTradeInfo.table.refresh({query: queryData});
    //查询汇总数据...
};
//处理刷新数据..
ShareTradeInfo.loadSuccess=function(data){
	if(data==null||data==undefined|| data.totalAmount==undefined||data.totalAmount==null||data.totalAmount==""){
		$("#spanTotal").html("0");
	}else{
		$("#spanTotal").html(data.totalAmount);
	}
	if(data==null||data==undefined|| data.total==undefined||data.total==null||data.total==""){
		$("#spTotal").html("0");
	}else{
		$("#spTotal").html(data.total);
	}
	if(data==null||data==undefined|| data.totalPlatFormAmount==undefined||data.totalPlatFormAmount==null||data.totalPlatFormAmount==""){
		$("#spTotalPlatformAgmount").html("0");
	}else{
		$("#spTotalPlatformAgmount").html(data.totalPlatFormAmount);
	}
	if(data==null||data==undefined|| data.totalAgents1Amount==undefined||data.totalAgents1Amount==null||data.totalAgents1Amount==""){
		$("#spTotalAgents1Amount").html("0");
	}else{
		$("#spTotalAgents1Amount").html(data.totalAgents1Amount);
	}
	if(data==null||data==undefined|| data.totalAgents2Amount==undefined||data.totalAgents2Amount==null||data.totalAgents2Amount==""){
		$("#spTotalAgents2Amount").html("0");
	}else{
		$("#spTotalAgents2Amount").html(data.totalAgents2Amount);
	}
	if(data==null||data==undefined|| data.totalAgents3Amount==undefined||data.totalAgents3Amount==null||data.totalAgents3Amount==""){
		$("#spTotalAgents3Amount").html("0");
	}else{
		$("#spTotalAgents3Amount").html(data.totalAgents3Amount);
	}
	if(data==null||data==undefined|| data.totalShopkeeperAmount==undefined||data.totalShopkeeperAmount==null||data.totalShopkeeperAmount==""){
		$("#spTotalShopkeeperAmount").html("0");
	}else{
		$("#spTotalShopkeeperAmount").html(data.totalShopkeeperAmount);
	}
	if(data==null||data==undefined|| data.totalAllianceBusinessAmount==undefined||data.totalAllianceBusinessAmount==null||data.totalAllianceBusinessAmount==""){
		$("#spTotalAllianceBusinessAmount").html("0");
	}else{
		$("#spTotalAllianceBusinessAmount").html(data.totalAllianceBusinessAmount);
	}
	if(data==null||data==undefined|| data.totalYFJAmount==undefined||data.totalYFJAmount==null||data.totalYFJAmount==""){
		$("#spTotalYFJAmount").html("0");
	}else{
		$("#spTotalYFJAmount").html(data.totalYFJAmount);
	}
}
$(function () {
    var defaultColunms = ShareTradeInfo.initColumn();
    var table = new BSTable(ShareTradeInfo.id, "/shareTradeInfo/list", defaultColunms);
    table.setPaginationType("server");
    var queryData = {};
    var borrowTimeStart=$("#borrowTimeStart").val();
    var borrowTimeEnd=$("#borrowTimeEnd").val();
    queryData['borrowTimeStart'] = borrowTimeStart;
    queryData['borrowTimeEnd'] = borrowTimeEnd;    
    table.setQueryParams(queryData);    
    ShareTradeInfo.table = table.init();   
    
    //$('#ShareTradeInfoTable').on('post-body.bs.table', function (e,data) {
     $('#ShareTradeInfoTable').on('load-success.bs.table', function (e,data) {
    	ShareTradeInfo.loadSuccess(data);
    });
});

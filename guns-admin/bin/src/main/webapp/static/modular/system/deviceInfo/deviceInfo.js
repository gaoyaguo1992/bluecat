/**
 * device管理初始化
 */
var DeviceInfo = {
    id: "DeviceInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};
/**
 * 初始化表格的列
 */
DeviceInfo.initColumn = function () {
    return [
            {field: 'selectItem', checkbox: true, visible: true}, 
            {title: '设备id', field: 'id', visible: true, width: '120px', align: 'center', valign: 'middle'},
            {title: '设备类型名', field: 'deviceTypeName' , width: '200px', visible: true, align: 'center', valign: 'middle'},
            {title: '设备状态(10:已激活,11:未激活，12:待确认)', field: 'deviceStatus',width: '160px', visible: true, align: 'center', valign: 'middle',
                formatter: function(value,row,index){
              	  if(value == 10){
              		  return "已激活";
              	  }
              	  if(value == 11){
              		  return "未激活";
              	  }
              	if(value == 12){
            		  return "待确认";
            	  }
                }},
            {title: '店铺商户id', field: 'onlineMerchantId', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '店铺商户名称', field: 'onlineMerchantCn', visible: true, width: '180px',align: 'center', valign: 'middle'},
            {title: '激活时间', field: 'onlineDatetime', visible: true,width: '120px', align: 'center', valign: 'middle' },
            {title: '费用描述', field: 'feeDescription', visible: true, width: '400px',align: 'center', valign: 'middle'},
            {title: '平台分润比例', field: 'platformRato', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '顶级代理商id', field: 'agents1Id', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '顶级代理商名', field: 'agents1Cn', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '顶级代理商分润比例', field: 'agents1Rato', visible: true, width: '160px',align: 'center', valign: 'middle'},
            {title: '一级代理商id', field: 'agents2Id', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '一级代理商名', field: 'agents2Cn', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '一级代理商分润比例', field: 'agents2Rato', visible: true, width: '160px',align: 'center', valign: 'middle'},
            {title: '二级代理商id', field: 'agents3Id', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '二级代理商名', field: 'agents3Cn', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '二级代理商分润比例', field: 'agents3Rato', visible: true, width: '160px',align: 'center', valign: 'middle'},
            {title: '铺货商id', field: 'shopkeeperId', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '铺货商名', field: 'shopkeeperCn', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '铺货商名分润比例', field: 'shopkeeperRato', visible: true, width: '160px',align: 'center', valign: 'middle'},
            {title: '加盟商id', field: 'allianceBusinessId', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '加盟商名', field: 'allianceBusinessCn', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '加盟商分润比例', field: 'allianceBusinessRate', visible: true, width: '160px',align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createDatetime', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updateDatetime', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '二维码生成图片', field: 'sqrUrl', visible: true, width: '160px',align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', width: '120px',valign: 'middle'},
            {title: '激活地址', field: 'onlineAddress', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '激活市', field: 'onlineCity', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '激活区', field: 'onlineZone', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '激活省市区', field: 'onlineProvince', visible: true, width: '120px',align: 'center', valign: 'middle'},            
            {title: '预付金额退回方式', field: 'yfjRebackType', visible: true, width: '160px',align: 'center', valign: 'middle',
                formatter: function(value,row,index){
                	if(value == 2){
                		  return "退回到账户余额";
                	  }
                	if(value == 1){
              		  return "实时原路退回";
              	  	}
             }}
    ];
};

/**
 * 检查是否选中
 */
DeviceInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DeviceInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 检查是否选中
 */
DeviceInfo.checkSingle = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一行记录！");
        return false;
    }else if(selected.length>1){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DeviceInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加device
 */
DeviceInfo.openAddDeviceInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加设备',
        area: ['900px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/deviceInfo/deviceInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看device详情
 */
DeviceInfo.openDeviceInfoDetail = function () {
    if (this.checkSingle()) {
        var index = layer.open({
            type: 2,
            title: '设备详情',
            area: ['900px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/deviceInfo/deviceInfo_update/' + DeviceInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除device
 */
DeviceInfo.delete = function () {
    if (this.checkSingle()) {
        var ajax = new $ax(Feng.ctxPath + "/deviceInfo/delete", function (data) {
            Feng.success("删除成功!");
            DeviceInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("deviceInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询device列表
 */
DeviceInfo.search = function () {
    var queryData = {};
    var condition=$("#condition").val();
    var value=$("#conditionValue").val();    
    queryData['condition'] = condition;
    queryData['conditionValue'] = value;
    var condition1=$("#condition1").val();
    var value1=$("#conditionValue1").val();    
    queryData['condition1'] = condition1;
    queryData['conditionValue1'] = value1;
    //判断...
    var startCreateDatetime=$("#startCreateDatetime").val();
    var endCreateDatetime=$("#endCreateDatetime").val();
    queryData['startCreateDatetime'] = startCreateDatetime;
    queryData['endCreateDatetime'] = endCreateDatetime;    
    
    DeviceInfo.table.refresh({query: queryData});
};

DeviceInfo.exportExcel = function(){
	var queryData = {};
    var condition=$("#condition").val();
    var value=$("#conditionValue").val();    
    queryData['condition'] = condition;
    queryData['conditionValue'] = value;
    var condition1=$("#condition1").val();
    var value1=$("#conditionValue1").val();    
    queryData['condition1'] = condition1;
    queryData['conditionValue1'] = value1;
    //判断...
    var startCreateDatetime=$("#startCreateDatetime").val();
    var endCreateDatetime=$("#endCreateDatetime").val();
    queryData['startCreateDatetime'] = startCreateDatetime;
    queryData['endCreateDatetime'] = endCreateDatetime;  
    
    var path = Feng.ctxPath + "/deviceInfo/exportExcel?condition="+condition
    +"&conditionValue="+value+"&condition1="+condition1
    +"&conditionValue1="+value1+"&startCreateDatetime="+startCreateDatetime
    +"&endCreateDatetime="+endCreateDatetime;
    alert("默认导出最近一个月的设备，请注意以上条件作导出筛选！");
    window.location.href = encodeURI(path);
}
/**
 * 批量生成设备
 */
DeviceInfo.generateDevice=function(){
	 var index = layer.open({
	        type: 2,
	        title: '批量添加设备',
	        area: ['900px', '600px'], //宽高
	        fix: false, //不固定
	        maxmin: true,
	        content: Feng.ctxPath + '/deviceInfo/deviceInfo_batch_add'
	    });
	    this.layerIndex = index;
}
/**
 * 批量批定商户
 */
DeviceInfo.batchBindMerchant=function(){
	 var index = layer.open({
	        type: 2,
	        title: '批量批定商户',
	        area: ['900px', '600px'], //宽高
	        fix: false, //不固定
	        maxmin: true,
	        content: Feng.ctxPath + '/deviceInfo/deviceInfo_batch_bindmer'
	    });
	    this.layerIndex = index;
}

/**
 * 批量解除绑定商户
 */
DeviceInfo.batchUnbind=function(){
	 var index = layer.open({
	        type: 2,
	        title: '批量解除批定商户',
	        area: ['900px', '600px'], //宽高
	        fix: false, //不固定
	        maxmin: true,
	        content: Feng.ctxPath + '/deviceInfo/deviceInfo_batch_unbindmer'
	    });
	    this.layerIndex = index;
}

/**
 * 批量绑定费用
 */
DeviceInfo.batchBindFee=function(){
	 var index = layer.open({
	        type: 2,
	        title: '批量批定费用',
	        area: ['900px', '600px'], //宽高
	        fix: false, //不固定
	        maxmin: true,
	        content: Feng.ctxPath + '/deviceInfo/deviceInfo_batch_bindfee'
	   });
	   this.layerIndex = index;
}
/**
 * 得到所有选中的设备id
 */
DeviceInfo.getAllSelectedDevices=function(){
	  var selected = $('#' + this.id).bootstrapTable('getSelections');
	  return selected;
}
/**
 * 批量绑定分润
 */
DeviceInfo.batchBindRato=function(){
	 var index = layer.open({
	        type: 2,
	        title: '批量绑定分润',
	        area: ['900px', '600px'], //宽高
	        fix: false, //不固定
	        maxmin: true,
	        content: Feng.ctxPath + '/deviceInfo/deviceInfo_batch_bindrato'
	   });
	   this.layerIndex = index;
}
/**
 * 其它批量操作
 */
DeviceInfo.batchBindOther=function(){
	 var index = layer.open({
	        type: 2,
	        title: '其它批量操作',
	        area: ['900px', '600px'], //宽高
	        fix: false, //不固定
	        maxmin: true,
	        content: Feng.ctxPath + '/deviceInfo/deviceInfo_batch_bindOther'
	   });
	   this.layerIndex = index;
}
/**
 * 
 */
$(function () {
    var defaultColunms = DeviceInfo.initColumn();
    var table = new BSTable(DeviceInfo.id, "/deviceInfo/list", defaultColunms);
    table.setPaginationType("server");
    var queryData = {};
    var condition=$("#condition").val();
    var value=$("#conditionValue").val();    
    queryData['condition'] = condition;
    queryData['conditionValue'] = value;
    var condition1=$("#condition1").val();
    var value1=$("#conditionValue1").val();    
    queryData['condition1'] = condition1;
    queryData['conditionValue1'] = value1;
    //判断...
    var startCreateDatetime=$("#startCreateDatetime").val();
    var endCreateDatetime=$("#endCreateDatetime").val();
    queryData['startCreateDatetime'] = startCreateDatetime;
    queryData['endCreateDatetime'] = endCreateDatetime;   
    table.setQueryParams(queryData); 
    
    
    DeviceInfo.table = table.init();
});
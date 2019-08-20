/**
 * merchantInfo管理初始化
 */
var MerchantInfo = {
    id: "MerchantInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MerchantInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '商户号 ', field: 'id', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '商户名称', field: 'name', visible: true,width: '120px',  align: 'center', valign: 'middle'},
        {title: '商户地址', field: 'addr', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '商户状态', field: 'status', visible: true, width: '120px', align: 'center', valign: 'middle',
        	formatter: function(value,row,index){
            	  if(value == 21){
            		  return "已注册";
            	  }
            	  if(value == 20){
            		  return "已申请";
            	  }
            	if(value == 22){
          		  return "已注销";
          	  }
              }},
        {title: '联系人', field: 'personName', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '商户余额', field: 'custAccount.availableBalance', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '联系人电话', field: 'telNo', visible: true, width: '140px', align: 'center', valign: 'middle'},
        {title: '商户类型', field: 'merchantTypeCn', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true,  width: '120px', align: 'center', valign: 'middle'},
        {title: '上级商户号', field: 'parentMerchantId', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '客户号', field: 'settlementAccount', visible: true, width: '130px', align: 'center', valign: 'middle'},        
        {title: '商户行业类型名', field: 'industryCategoryCn', visible: true,  width: '150px',align: 'center', valign: 'middle'},
        {title: '历史交易数', field: 'hisTradeCount', visible: true, width: '120px',align: 'center', valign: 'middle'},
        {title: '历史交易总金额', field: 'hisTradeAmount', visible: true, width: '150px', align: 'center', valign: 'middle'},
        {title: '投资金额', field: 'investMoney', visible: true, width: '120px',align: 'center', valign: 'middle'},
        {title: '法定代表人', field: 'legalRepresentative', visible: true, width: '120px',align: 'center', valign: 'middle'},
        {title: '统一社会信用代码', field: 'uniformSocialCreditCode', visible: true,width: '160px', align: 'center', valign: 'middle'},
        {title: '身份证号码', field: 'idNumber', visible: true, width: '120px',align: 'center', valign: 'middle'},
        {title: '交易金额是否显示', field: 'tradeAmountShowFlag',width: '130px', visible: true, align: 'center', valign: 'middle'},
        {title: '提现方式', field: 'withdrawWayId', visible: true, width: '120px',align: 'center', valign: 'middle'},
        {title: '分润方式', field: 'advanceProfitFlag', visible: true, width: '120px',align: 'center', valign: 'middle',
        	formatter: function(value,row,index){
          	  if(value == 0){
          		  return "实时分润";
          	  }
          	  if(value == 1){
          		  return "非实时分润";
          	  }
         }},
        {title: '收货人', field: 'receiver', visible: true, width: '120px',align: 'center', valign: 'middle'},
        {title: '收货人', field: 'receiver', visible: true, width: '120px',align: 'center', valign: 'middle'},
        {title: '收货人电话', field: 'receiverTel', visible: true, width: '120px',align: 'center', valign: 'middle'},
        {title: '收货人地址', field: 'receiverAddr', visible: true, width: '120px',align: 'center', valign: 'middle'},
        {title: '营业开始时间', field: 'startShopTime', visible: true, width: '120px',align: 'center', valign: 'middle'},
        {title: '营业结束时间', field: 'endShopTime', visible: true,  width: '120px',align: 'center', valign: 'middle'},
        {title: '充电器使用率', field: 'weekUseScale', visible: true,  width: '120px',align: 'center', valign: 'middle'},
        {title: '店铺对外电话', field: 'storePhoneNo', visible: true,  width: '120px',align: 'center', valign: 'middle'},
        {title: '店铺简介', field: 'profile', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '客户数', field: 'roomCount', visible: true,  width: '120px', align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MerchantInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MerchantInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加merchantInfo
 */
MerchantInfo.openAddMerchantInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加商户',
        area: ['900px', '520px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/merchantInfo/merchantInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看merchantInfo详情
 */
MerchantInfo.openMerchantInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '商户修改',
            area: ['900px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/merchantInfo/merchantInfo_update/' + MerchantInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除merchantInfo
 */
MerchantInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/merchantInfo/delete", function (data) {
            Feng.success("删除成功!");
            MerchantInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("merchantInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 导出excel
 */
MerchantInfo.exportExcel = function(){
	var condition=$("#condition").val();
    var value=$("#conditionValue").val();    
    var path = Feng.ctxPath + "/merchantInfo/exportExcel?condition="+condition
    +"&conditionValue="+value;
    alert("请注意选择条件作导出筛选！");
    window.location.href = encodeURI(path);
}

/**
 * 查询merchantInfo列表
 */
MerchantInfo.search = function () {
    var queryData = {};
    var condition=$("#condition").val();
    var value=$("#conditionValue").val(); 
    var merchantTypeId=$("#selMerchantTypeId").val();       
    queryData['condition'] = condition;
    queryData['conditionValue'] = value;
    queryData['merchantTypeId'] = merchantTypeId;
    MerchantInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MerchantInfo.initColumn();
    var table = new BSTable(MerchantInfo.id, "/merchantInfo/list", defaultColunms);
    table.setPaginationType("server");
    MerchantInfo.table = table.init();
});

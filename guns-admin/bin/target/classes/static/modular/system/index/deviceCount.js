/**
 * usageTrend7DaysTable管理初始化
 */
var UsageTrend = {
    id: "usageTrend7DaysTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UsageTrend.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '店铺商户号 ', field: 'merchantInfoModel.id', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '店铺名称', field: 'merchantInfoModel.name', visible: true,width: '120px',  align: 'center', valign: 'middle'},
        {title: '店铺地址', field: 'merchantInfoModel.addr', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '店铺联系人', field: 'merchantInfoModel.personName', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '店铺联系人电话', field: 'merchantInfoModel.telNo', visible: true, width: '140px', align: 'center', valign: 'middle'},
        {title: '商户行业类型名', field: 'merchantInfoModel.industryCategoryCn', visible: true,  width: '150px',align: 'center', valign: 'middle'},
        {title: '设备总数量', field: 'deviceQty', visible: true,  width: '150px',align: 'center', valign: 'middle'},
        {title: '七天使用率', field: 'device7daysUsageRate', visible: true,  width: '150px',align: 'center', valign: 'middle'},
        {title: '设备总使用次数', field: 'deviceUsedTotalQty', visible: true,  width: '150px',align: 'center', valign: 'middle'},
        {title: '设备七天使用次数', field: 'deviceUsed7daysQty', visible: true,  width: '150px',align: 'center', valign: 'middle'},
        {title: '总交易金额', field: 'deviceTotalAmount', visible: true,  width: '150px',align: 'center', valign: 'middle'},
        
    ];
};


$(function () {
    var defaultColunms = UsageTrend.initColumn();
    var table = new BSTable(UsageTrend.id, "/index/listCalculateMerchantData/2", defaultColunms);
    table.setPaginationType("server");
    UsageTrend.table = table.init();
});

//返回首页
UsageTrend.toIndex = function(){
	self.location=document.referrer;
}

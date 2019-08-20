/**
 * nouseByMerchantOf7DaysTable管理初始化
 */
var NoUseByMerchant = {
    id: "nouseByMerchantOf7Days",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
NoUseByMerchant.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '店铺商户号 ', field: 'merchantId', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '店铺名称', field: 'name', visible: true,width: '120px',  align: 'center', valign: 'middle'},
        {title: '店铺联系人', field: 'personName', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '店铺地址', field: 'addr', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '店铺联系人电话', field: 'telNo', visible: true, width: '140px', align: 'center', valign: 'middle'},
        {title: '商户行业类型', field: 'industryCategoryCn', visible: true,  width: '150px',align: 'center', valign: 'middle'},
        {title: '总使用次数', field: 'totalUsedCount', visible: true,  width: '150px',align: 'center', valign: 'middle'},
        {title: '最近使用时间', field: 'lastUsedTime', visible: true,  width: '150px',align: 'center', valign: 'middle'},
        
    ];
};


$(function () {
    var defaultColunms = NoUseByMerchant.initColumn();
    var table = new BSTable(NoUseByMerchant.id, "/index/nouseByMerchantOf7Days", defaultColunms);
    table.setPaginationType("server");
    NoUseByMerchant.table = table.init();
});
//返回首页
NoUseByMerchant.toIndex = function(){
	self.location=document.referrer;
}


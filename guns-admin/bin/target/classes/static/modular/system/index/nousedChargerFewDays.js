/**
 * nousedChargerFewDays管理初始化
 */
var NoUseByDevice = {
    id: "nousedChargerFewDays",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
NoUseByDevice.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '充电器编号 ', field: 'chargerId', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '绑定设备编号', field: 'deviceId', visible: true,width: '120px',  align: 'center', valign: 'middle'},
        {title: '设备状态', field: 'deviceStatusCn', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '设备类型', field: 'deviceTypeName', visible: true, width: '120px', align: 'center', valign: 'middle'},
        {title: '激活时间', field: 'onlineDateTime', visible: true, width: '140px', align: 'center', valign: 'middle'},
        {title: '最近使用时间', field: 'lastUseTime', visible: true,  width: '150px',align: 'center', valign: 'middle'},
        {title: '所属店铺', field: 'onlineMerchantCn', visible: true,  width: '150px',align: 'center', valign: 'middle'},
        
    ];
};


$(function () {
    var defaultColunms = NoUseByDevice.initColumn();
    console.log(days)
    var table = new BSTable(NoUseByDevice.id, "/index/nousedChargerFewDays/"+days, defaultColunms);
    table.setPaginationType("server");
    NoUseByDevice.table = table.init();
});
//返回首页
NoUseByDevice.toIndex = function(){
	self.location=document.referrer;
}


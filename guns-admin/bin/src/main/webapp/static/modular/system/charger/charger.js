/**
 * device管理初始化
 */
var Charger = {
    id: "ChargerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Charger.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '充电器id', field: 'id', visible: true,width: '120px', align: 'center', valign: 'middle'},
            {title: '设备id', field: 'deviceId', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '充电器类型名', field: 'chargerTypeName', visible: true, width: '160px',align: 'center', valign: 'middle'},
            {title: '密码序号', field: 'pwdIndex', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '密码', field: 'pwds', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '密码原子', field: 'refactorIdx', visible: true, width: '120px',align: 'center', valign: 'middle'},
            {title: '到昨天为止使用次数', field: 'useCountTimesYesterday', visible: true, width: '170px',align: 'center', valign: 'middle'},
            {title: '到昨天为止收入金额', field: 'totalAmountYesterday', visible: true, width: '170px',align: 'center', valign: 'middle'},
            {title: '创建者ID', field: 'createId', visible: true,  width: '120px',align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createDateTime', visible: true,  width: '120px',align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateDateTime', visible: true,  width: '120px',align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, width: '120px',align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Charger.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Charger.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加device
 */
Charger.openAddCharger = function () {
    var index = layer.open({
        type: 2,
        title: '添加device',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/charger/charger_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看device详情
 */
Charger.openChargerDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'device详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/charger/charger_update/' + Charger.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 批量绑定设备
 */
Charger.batchBindDevice=function(){
	var index = layer.open({
	       type: 2,
	       title: '批量绑定设备',
	       area: ['900px', '520px'], //宽高
	       fix: false, //不固定
	       maxmin: true,
	       content: Feng.ctxPath + '/charger/charger_batch_binddevice'
	 });
	 this.layerIndex = index;
}
/**
 * 批量生成充电器
 */
Charger.batchGenerateCharger=function(){
	 var index = layer.open({
	       type: 2,
	       title: '批量生成充电器',
	       area: ['900px', '520px'], //宽高
	       fix: false, //不固定
	       maxmin: true,
	       content: Feng.ctxPath + '/charger/charger_batch_add'
	 });
	 this.layerIndex = index;
}
/**
 * 删除device
 */
Charger.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/charger/delete", function (data) {
            Feng.success("删除成功!");
            Charger.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("chargerId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询device列表
 */
Charger.search = function () {
    var queryData = {};
    var condition=$("#condition").val();
    var value=$("#conditionValue").val();    
    queryData['condition'] = condition;
    queryData['conditionValue'] = value;
    Charger.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Charger.initColumn();
    var table = new BSTable(Charger.id, "/charger/list", defaultColunms);
    table.setPaginationType("server");
    Charger.table = table.init();
});

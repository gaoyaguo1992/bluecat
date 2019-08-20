/**
 * 管理初始化
 */
var MerchantApplyForm = {
    id: "MerchantApplyFormTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MerchantApplyForm.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '订单id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '商户名称', field: 'merchantName', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人', field: 'personName', visible: true, align: 'center', valign: 'middle'},
            {title: '联系电话', field: 'telNo', visible: true, align: 'center', valign: 'middle'},
            {title: '省', field: 'province', visible: true, align: 'center', valign: 'middle'},
            {title: '市', field: 'city', visible: true, align: 'center', valign: 'middle'},
            {title: '区', field: 'zone', visible: true, align: 'center', valign: 'middle'},
            {title: '代理商区', field: 'agentsZone', visible: true, align: 'center', valign: 'middle'},
            {title: '铺货渠道', field: 'throwDevChannel', visible: true, align: 'center', valign: 'middle'},
            {title: '申请类型', field: 'applyType', visible: true, align: 'center', valign: 'middle'},
            {title: '申请名', field: 'applyTypeCn', visible: true, align: 'center', valign: 'middle'},
            {title: '处理状态', field: 'doStatus', visible: true, align: 'center', valign: 'middle'},
            {title: '处理状态名', field: 'doStatusCn', visible: true, align: 'center', valign: 'middle'},
            {title: '客户id', field: 'custId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MerchantApplyForm.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MerchantApplyForm.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
MerchantApplyForm.openAddMerchantApplyForm = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/merchantApplyForm/merchantApplyForm_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
MerchantApplyForm.openMerchantApplyFormDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['850px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/merchantApplyForm/merchantApplyForm_update/' + MerchantApplyForm.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
MerchantApplyForm.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/merchantApplyForm/delete", function (data) {
            Feng.success("删除成功!");
            MerchantApplyForm.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("merchantApplyFormId",this.seItem.id);
        ajax.start();
    }
};
/**
 * 查询列表
 */
MerchantApplyForm.search = function () {
    var queryData = {};
    var condition=$("#condition").val();
    var value=$("#conditionValue").val();    
    queryData['condition'] = condition;
    queryData['conditionValue'] = value;
    MerchantApplyForm.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MerchantApplyForm.initColumn();
    var table = new BSTable(MerchantApplyForm.id, "/merchantApplyForm/list", defaultColunms);
    table.setPaginationType("server");

    var queryData = {};
    var condition=$("#condition").val();
    var value=$("#conditionValue").val();    
    queryData['condition'] = condition;
    queryData['conditionValue'] = value;
    table.setQueryParams(queryData);
    
    MerchantApplyForm.table = table.init();
});

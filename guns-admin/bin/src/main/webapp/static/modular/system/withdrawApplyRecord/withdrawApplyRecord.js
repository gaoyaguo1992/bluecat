/**
 * 商户提现订单管理初始化
 */
var WithdrawApplyRecord = {
    id: "WithdrawApplyRecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
WithdrawApplyRecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '提现申请id', field: 'id',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '提现申请时间', field: 'applyTime', width: '120px', visible: true, align: 'center', valign: 'middle'},
            {title: '商户Id', field: 'applyMerchantId',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '商户名称', field: 'merchantName',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '商户联系人', field: 'personName',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '商户电话', field: 'merchantTelNo',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '提现税前金额', field: 'preTaxAmount',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '提现税后金额', field: 'aftTaxAmount',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '提现状态', field: 'withdrawStatus',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '提现状态名', field: 'withdrawStatusCn',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '提现审批人', field: 'approver', width: '120px', visible: true, align: 'center', valign: 'middle'},
            {title: '审批时间', field: 'approveTime',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '审批状态', field: 'approveStatus',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '审批状态名', field: 'approveStatusCn',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '审批备注', field: 'approveComment',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '付款时间', field: 'payTime',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '发票号码', field: 'invoiceNo',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '手动扣除金额', field: 'manualDeduction', width: '120px', visible: true, align: 'center', valign: 'middle'},
            {title: '本月第几次发起提现', field: 'numberOfMonth',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '付款方式', field: 'payWay',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '是否实时分润', field: 'isRealTime',width: '120px',  visible: true, align: 'center', valign: 'middle'},
            {title: '手动扣除金额', field: 'manualDeduction', width: '120px', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
WithdrawApplyRecord.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        WithdrawApplyRecord.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加商户提现订单
 */
WithdrawApplyRecord.openAddWithdrawApplyRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加商户提现订单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/withdrawApplyRecord/withdrawApplyRecord_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看商户提现订单详情
 */
WithdrawApplyRecord.openWithdrawApplyRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '商户提现订单详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/withdrawApplyRecord/withdrawApplyRecord_update/' + WithdrawApplyRecord.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除商户提现订单
 */
WithdrawApplyRecord.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/withdrawApplyRecord/delete", function (data) {
            Feng.success("删除成功!");
            WithdrawApplyRecord.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("withdrawApplyRecordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询商户提现订单列表
 */
WithdrawApplyRecord.search = function () {
    var queryData = {};
    var condition=$("#condition").val();
    var value=$("#conditionValue").val();    
    queryData['condition'] = condition;
    queryData['conditionValue'] = value;
    WithdrawApplyRecord.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = WithdrawApplyRecord.initColumn();
    var table = new BSTable(WithdrawApplyRecord.id, "/withdrawApplyRecord/list", defaultColunms);
    table.setPaginationType("server");
    WithdrawApplyRecord.table = table.init();
});

/**
 * 用户信息管理初始化
 */
var CustInfo = {
    id: "CustInfoTable",	// 表格id
    seItem: null,		// 选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CustInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '客户编号', field: 'custNo', visible: true, width:'120px', align: 'center', valign: 'middle'},
            {title: '客户名称', field: 'custName', visible: true, width:'120px', align: 'center', valign: 'middle'},
            {title: 'OpenId', field: 'openId', visible: true, width:'120px', align: 'center', valign: 'middle'},
            {title: '客户类型', field: 'custType', visible: true,  width:'120px',align: 'center', valign: 'middle'},
            {title: '电话', field: 'telNo', visible: true,  width:'120px',align: 'center', valign: 'middle'},
            {title: '昵称', field: 'nickName', visible: true,  width:'120px',align: 'center', valign: 'middle'},
            {title: '头像', field: 'headImgUrl', visible: true,  width:'120px',align: 'center', valign: 'middle'},
            {title: 'UnionId', field: 'unionId', visible: true,  width:'120px',align: 'center', valign: 'middle'}, 
            {title: '语言', field: 'language', visible: true,  width:'120px',align: 'center', valign: 'middle'},
            {title: '性别', field: 'sex', visible: true,  width:'120px',align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true,  width:'120px',align: 'center', valign: 'middle'},
            {title: '城市', field: 'city', visible: true,  width:'120px',align: 'center', valign: 'middle'},
            {title: '邮箱', field: 'email', visible: true,  width:'120px',align: 'center', valign: 'middle'},
            {title: '地址', field: 'addr', visible: true,  width:'120px',align: 'center', valign: 'middle'},
            {title: '注册时间', field: 'regTime', visible: true,  width:'120px',align: 'center', valign: 'middle'},
            {title: '商户号', field: 'merchantId', visible: true,  width:'120px',align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true,  width:'120px',align: 'center', valign: 'middle'},
            {title: '微信小程序openId', field: 'wxappOpenId', visible: true,  width:'120px',align: 'center', valign: 'middle'},
            {title: '客户来源', field: 'custSourceType', visible: true,  width:'120px',align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CustInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CustInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户信息
 */
CustInfo.openAddCustInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户信息',
        area: ['800px', '420px'], // 宽高
        fix: false, // 不固定
        maxmin: true,
        content: Feng.ctxPath + '/custInfo/custInfo_add'
    });
    this.layerIndex = index;
    
};
/**
 * 查询客户商户信息
 */
CustInfo.openCustInfoAndMerchant = function(){
    if (this.check()) {
		 var index = layer.open({
		        type: 2,
		        title: '绑定商户',
		        area: ['800px', '520px'], // 宽高
		        fix: false, // 不固定
		        maxmin: true,
		        content: Feng.ctxPath + '/custInfo/bind_cust_merchant/'+CustInfo.seItem.custNo
		     });
		 this.layerIndex = index;
    }
}


/**
 * 打开查看用户信息详情
 */
CustInfo.openCustInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户信息详情',
            area: ['800px', '420px'], // 宽高
            fix: false, // 不固定
            maxmin: true,
            content: Feng.ctxPath + '/custInfo/custInfo_update/' + CustInfo.seItem.custNo
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户信息
 */
CustInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/custInfo/delete", function (data) {
            Feng.success("删除成功!");
            CustInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("custInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户信息列表
 */
CustInfo.search = function () {
    var queryData = {};
    var condition=$("#condition").val();
    var value=$("#conditionValue").val();    
    queryData['condition'] = condition;
    queryData['conditionValue'] = value;
    CustInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CustInfo.initColumn();
    var table = new BSTable(CustInfo.id, "/custInfo/list", defaultColunms);
    table.setPaginationType("server");
    CustInfo.table = table.init();
});

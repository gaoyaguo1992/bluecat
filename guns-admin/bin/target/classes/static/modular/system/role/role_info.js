/**
 * 角色详情对话框（可用于添加和修改对话框）
 */
var RolInfoDlg = {
    roleInfoData: {},
    deptZtree: null,
    pNameZtree: null,

    id: "RoleMerInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    operateFlag:0,
    custInfoInfoData : {},
    
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '用户名不能为空'
                }
            }
        },
        tips: {
            validators: {
                notEmpty: {
                    message: '别名不能为空'
                }
            }
        },
        pName: {
            validators: {
                notEmpty: {
                    message: '父级名称不能为空'
                }
            }
        }
    }
};

/**
 * 初始化表格的列
 */
RolInfoDlg.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '商户编号', field: 'merchantId', visible: true, width:'100px', align: 'center', valign: 'middle'},
            {title: '商户名称', field: 'merchantName', visible: true, width:'100px', align: 'center', valign: 'middle'},
            {title: '商户类型', field: 'merchantTypeCn', visible: true, width:'100px', align: 'center', valign: 'middle'},
            {title: '绑定时间', field: 'createTime', visible: true, width:'100px', align: 'center', valign: 'middle'}           
    ];
};
/**
 * 检查是否选中
 */
RolInfoDlg.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	RolInfoDlg.seItem = selected[0];
        return true;
    }
};


/**
 * 添加商户到用户中...
 */
RolInfoDlg.addMerchantSubmit=function(){
	RolInfoDlg.startShieldLayer();
	if (RolInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		RolInfoDlg.operateFlag = 1;
		var merchantId=$("#merchantId").val();
		if(merchantId==null||merchantId==undefined||merchantId==""){
			RolInfoDlg.finishShieldLayer();
			RolInfoDlg.operateFlag = 0;
		    Feng.error("请输入要绑定的商户号");
		    return;
		}
		var roleId=$("#roleId").val();
		var param={"merchantId":merchantId,"roleId":roleId};
		var refreshParam={"roleId":roleId};
		
		//提交信息
		var ajax = new $ax(Feng.ctxPath + "/role/addMerchantSubmit", function(data){
			RolInfoDlg.finishShieldLayer();
			RolInfoDlg.operateFlag = 0;
		    if (data != null && data.result == "error") {
				Feng.error(data.message);
			} else {
				Feng.success("绑定成功!");
				RolInfoDlg.table.refresh();
		        $("#merchantId").val("");
			}
		},function(data){
			RolInfoDlg.finishShieldLayer();
			RolInfoDlg.operateFlag = 0;
		    Feng.error("绑定失败!" + data.responseJSON.message + "!");
		});
		ajax.set(param);
		ajax.start();
	} catch (e) {
		RolInfoDlg.finishShieldLayer();
		RolInfoDlg.operateFlag = 0;
		Feng.error("绑定失败!");
	}
}
/**
 * 删除绑定商户
 */
RolInfoDlg.delMerchantSubmit=function(){
	RolInfoDlg.startShieldLayer();
	if (RolInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		RolInfoDlg.operateFlag = 1;
		if(this.check()){
			var roleId=RolInfoDlg.seItem.roleId;
			var merchantId=RolInfoDlg.seItem.merchantId;
			var param={"merchantId":merchantId,"roleId":roleId};
			var refreshParam={"roleId":roleId};
			
			//提交信息
		    var ajax = new $ax(Feng.ctxPath + "/role/delMerchantSubmit", function(data){
		    	RolInfoDlg.finishShieldLayer();
		    	RolInfoDlg.operateFlag = 0;
		        if (data != null && data.result == "error") {
					Feng.error(data.message);
				} else {
					Feng.success("解绑成功!");
					RolInfoDlg.table.refresh();
				}
		    },function(data){
		    	RolInfoDlg.finishShieldLayer();
		    	RolInfoDlg.operateFlag = 0;
		        Feng.error("解绑失败!" + data.responseJSON.message + "!");
		    });
		    ajax.set(param);
		    ajax.start();
		}else{
			RolInfoDlg.finishShieldLayer();
			RolInfoDlg.operateFlag = 0;
		}
	} catch (e) {
		RolInfoDlg.finishShieldLayer();
		RolInfoDlg.operateFlag = 0;
		Feng.error("绑定失败!");
	}
}


/**
 * 开启屏蔽层.
 */
RolInfoDlg.startShieldLayer = function() {
	$('#myModal').modal("show");
}
/**
 * 结束屏蔽层.
 */
RolInfoDlg.finishShieldLayer = function() {
	$('#myModal').modal("hide");
}


/**
 * 清除数据
 */
RolInfoDlg.clearData = function () {
    this.roleInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RolInfoDlg.set = function (key, value) {
    this.roleInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RolInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
RolInfoDlg.close = function () {
    parent.layer.close(window.parent.Role.layerIndex);
};

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
RolInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#deptName").attr("value", RolInfoDlg.deptZtree.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
};
RolInfoDlg.onDblClickDept = function (e, treeId, treeNode) {
    $("#deptName").attr("value", RolInfoDlg.deptZtree.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
    $("#deptContent").fadeOut("fast");
};

/**
 * 点击父级菜单input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
RolInfoDlg.onClickPName = function (e, treeId, treeNode) {
    $("#pName").attr("value", RolInfoDlg.pNameZtree.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
RolInfoDlg.showDeptSelectTree = function () {
    Feng.showInputTree("deptName", "deptContent");
};

/**
 * 显示父级菜单的树
 *
 * @returns
 */
RolInfoDlg.showPNameSelectTree = function () {
    Feng.showInputTree("pName", "pNameContent");
};

/**
 * 收集数据
 */
RolInfoDlg.collectData = function () {
    this.set('id').set('name').set('pid').set('deptid').set('tips').set('num');
};

/**
 * 验证数据是否为空
 */
RolInfoDlg.validate = function () {
    $('#roleInfoForm').data("bootstrapValidator").resetForm();
    $('#roleInfoForm').bootstrapValidator('validate');
    return $("#roleInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
RolInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/role/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Role.table.refresh();
        RolInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.roleInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
RolInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/role/edit", function (data) {
        Feng.success("修改成功!");
        window.parent.Role.table.refresh();
        RolInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.roleInfoData);
    ajax.start();
};

$(function () {
	
    Feng.initValidator("roleInfoForm", RolInfoDlg.validateFields);

    var deptTree = new $ZTree("deptTree", "/dept/tree");
    deptTree.bindOnClick(RolInfoDlg.onClickDept);
    deptTree.bindOnDblClick(RolInfoDlg.onDblClickDept)
    deptTree.init();
    RolInfoDlg.deptZtree = deptTree;

    var pNameTree = new $ZTree("pNameTree", "/role/roleTreeList");
    pNameTree.bindOnClick(RolInfoDlg.onClickPName);
    pNameTree.init();
    RolInfoDlg.pNameZtree = pNameTree;
});

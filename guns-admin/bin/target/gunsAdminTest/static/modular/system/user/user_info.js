/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var UserInfoDlg = {
	id: "roleMerInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    operateFlag:0,
    
    userInfoData: {},
    validateFields: {
        account: {
            validators: {
                notEmpty: {
                    message: '账户不能为空'
                }
            }
        },
        name: {
            validators: {
                notEmpty: {
                    message: '姓名不能为空'
                }
            }
        },
        citySel: {
            validators: {
                notEmpty: {
                    message: '部门不能为空'
                }
            }
        },
        password: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                identical: {
                    field: 'rePassword',
                    message: '两次密码不一致'
                },
            }
        },
        rePassword: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                identical: {
                    field: 'password',
                    message: '两次密码不一致'
                },
            }
        }
    }
};
/**
 * 初始化表格的列
 */
UserInfoDlg.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '商户编号', field: 'merchantId', visible: true, width:'100px', align: 'center', valign: 'middle'},
            {title: '商户名称', field: 'merchantName', visible: true, width:'100px', align: 'center', valign: 'middle'},
            {title: '商户类型', field: 'merchantTypeCn', visible: true, width:'100px', align: 'center', valign: 'middle'},
            {title: '绑定时间', field: 'createTime', visible: true, width:'100px', align: 'center', valign: 'middle'}           
    ];
};
/**
 * 清除数据
 */
UserInfoDlg.clearData = function () {
    this.userInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.set = function (key, value) {
    if(typeof value == "undefined"){
        if(typeof $("#" + key).val() =="undefined"){
            var str="";
            var ids="";
            $("input[name='"+key+"']:checkbox").each(function(){
                if(true == $(this).is(':checked')){
                    str+=$(this).val()+",";
                }
            });
            if(str){
                if(str.substr(str.length-1)== ','){
                    ids = str.substr(0,str.length-1);
                }
            }else{
                $("input[name='"+key+"']:radio").each(function(){
                    if(true == $(this).is(':checked')){
                        ids=$(this).val()
                    }
                });
            }
            this.userInfoData[key] = ids;
        }else{
            this.userInfoData[key]= $("#" + key).val();
        }
    }

    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
UserInfoDlg.close = function () {
    parent.layer.close(window.parent.MgrUser.layerIndex);
};

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
UserInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#citySel").attr("value", instance.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
UserInfoDlg.showDeptSelectTree = function () {
    var cityObj = $("#citySel");
    var cityOffset = $("#citySel").offset();
    $("#menuContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 显示用户详情部门选择的树
 *
 * @returns
 */
UserInfoDlg.showInfoDeptSelectTree = function () {
    var cityObj = $("#citySel");
    var cityPosition = $("#citySel").position();
    $("#menuContent").css({
        left: cityPosition.left + "px",
        top: cityPosition.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 隐藏部门选择的树
 */
UserInfoDlg.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 收集数据
 */
UserInfoDlg.collectData = function () {
    this.set('id').set('account').set('sex').set('password').set('avatar')
        .set('email').set('name').set('birthday').set('rePassword').set('deptid').set('phone').set('merchantType');
};

/**
 * 验证两个密码是否一致
 */
UserInfoDlg.validatePwd = function () {
    var password = this.get("password");
    var rePassword = this.get("rePassword");
    if (password == rePassword) {
        return true;
    } else {
        return false;
    }
};

/**
 * 验证数据是否为空
 */
UserInfoDlg.validate = function () {
    $('#userInfoForm').data("bootstrapValidator").resetForm();
    $('#userInfoForm').bootstrapValidator('validate');
    return $("#userInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
UserInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    if (!this.validatePwd()) {
        Feng.error("两次密码输入不一致");
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mgr/add", function (data) {
        Feng.success("添加成功!");
        window.parent.MgrUser.table.refresh();
        UserInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
UserInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mgr/edit", function (data) {
        Feng.success("修改成功!");
        if (window.parent.MgrUser != undefined) {
            window.parent.MgrUser.table.refresh();
            UserInfoDlg.close();
        }
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInfoData);
    ajax.start();
};

/**
 * 修改密码
 */
UserInfoDlg.chPwd = function () {
    var ajax = new $ax(Feng.ctxPath + "/mgr/changePwd", function (data) {
        Feng.success("修改成功!");
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set("oldPwd");
    ajax.set("newPwd");
    ajax.set("rePwd");
    ajax.start();

};


/**
 * 添加商户到用户中...
 */
UserInfoDlg.addMerchantSubmit=function(){
	UserInfoDlg.startShieldLayer();
	if (UserInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		UserInfoDlg.operateFlag = 1;
		var merchantId=$("#merchantId").val();
		if(merchantId==null||merchantId==undefined||merchantId==""){
			UserInfoDlg.finishShieldLayer();
			UserInfoDlg.operateFlag = 0;
		    Feng.error("请输入要绑定的商户号");
		    return;
		}
		var roleId=$("#roleId").val();
		var param={"merchantId":merchantId,"roleId":roleId};
		var refreshParam={"roleId":roleId};
		
		//提交信息
		var ajax = new $ax(Feng.ctxPath + "/role/addMerchantSubmit", function(data){
			UserInfoDlg.finishShieldLayer();
			UserInfoDlg.operateFlag = 0;
		    if (data != null && data.result == "error") {
				Feng.error(data.message);
			} else {
				Feng.success("绑定成功!");
				UserInfoDlg.table.refresh();
		        $("#merchantId").val("");
			}
		},function(data){
			UserInfoDlg.finishShieldLayer();
			UserInfoDlg.operateFlag = 0;
		    Feng.error("绑定失败!" + data.responseJSON.message + "!");
		});
		ajax.set(param);
		ajax.start();
	} catch (e) {
		UserInfoDlg.finishShieldLayer();
		UserInfoDlg.operateFlag = 0;
		Feng.error("绑定失败!");
	}
}
/**
 * 删除绑定商户
 */
UserInfoDlg.delMerchantSubmit=function(){
	UserInfoDlg.startShieldLayer();
	if (UserInfoDlg.operateFlag == 1) {
		return;
	}
	try {
		UserInfoDlg.operateFlag = 1;
		if(this.check()){
			var roleId=UserInfoDlg.seItem.roleId;
			var merchantId=UserInfoDlg.seItem.merchantId;
			var param={"merchantId":merchantId,"roleId":roleId};
			var refreshParam={"roleId":roleId};
			
			//提交信息
		    var ajax = new $ax(Feng.ctxPath + "/role/delMerchantSubmit", function(data){
		    	UserInfoDlg.finishShieldLayer();
		    	UserInfoDlg.operateFlag = 0;
		        if (data != null && data.result == "error") {
					Feng.error(data.message);
				} else {
					Feng.success("解绑成功!");
					UserInfoDlg.table.refresh();
				}
		    },function(data){
		    	UserInfoDlg.finishShieldLayer();
		    	UserInfoDlg.operateFlag = 0;
		        Feng.error("解绑失败!" + data.responseJSON.message + "!");
		    });
		    ajax.set(param);
		    ajax.start();
		}else{
			UserInfoDlg.finishShieldLayer();
			UserInfoDlg.operateFlag = 0;
		}
	} catch (e) {
		UserInfoDlg.finishShieldLayer();
		UserInfoDlg.operateFlag = 0;
		Feng.error("绑定失败!");
	}
}

/**
 * 开启屏蔽层.
 */
UserInfoDlg.startShieldLayer = function() {
	$('#myModal').modal("show");
}
/**
 * 结束屏蔽层.
 */
UserInfoDlg.finishShieldLayer = function() {
	$('#myModal').modal("hide");
}


function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
        UserInfoDlg.hideDeptSelectTree();
    }
}


$(function () {
    Feng.initValidator("userInfoForm", UserInfoDlg.validateFields);

    //初始化性别选项
    $("#sex").val($("#sexValue").val());
    //初始化商户类型选项
    $("#merchantType").val($("#merchantTypeValue").val());

    var ztree = new $ZTree("treeDemo", "/dept/tree");
    ztree.bindOnClick(UserInfoDlg.onClickDept);
    ztree.init();
    instance = ztree;

    // 初始化头像上传
    var avatarUp = new $WebUpload("avatar");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();

});

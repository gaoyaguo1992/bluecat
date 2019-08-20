$(function(){
	sumbitSave();
});

/**
 * 判断是否为空
 * @param content
 * @returns {Boolean}
 */
function isNotEmpty(content){
	if(content == 'null' || content == null || content =='undefined' || content == undefined || $.trim(content) == '')
		{return false;}
	else
		{return true;}
}
/**
 * 提示用户..
 * @param cont
 */
function tipText(cont){
	$("#result_prompt").text(cont);	    	
	$("#messageDialog").show();
	$("#msg_btn").click(function(){
		$("#messageDialog").hide();	
		$("#sumbitSaveBtn").removeAttr("style");	
	});
}
/*绑定设备star*/	
$("#getDeviceNoBtn").click(function(){
	wx.scanQRCode({
	    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
	    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
	    success: function (res) {
	    	//alert(res);
	    	var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
	    	$.JsonRpc(
					"",
					new dataArea('wx', 'doScanRlstForRegister', 
							{scanResult:result}
					),
					function(data){
						if(data.result=="error"){
							tipText(data.message);
						}else{
							$("#deviceNoDisplay").val(data.responseInfo);	
							$("#deviceNo").val(data.responseInfo);
							tipText("扫码成功获取绑定设备，设备号为：【"+data.responseInfo+"】");							
						}						
					}
			);
	    },
		fail:function (res) {			
		},
	    cancel:function (res) {	    	
	    }
	});
});
/*绑定设备end*/
var file = "";
function sumbitSave(){
	$("#sumbitSaveBtn").click(function(){
		$("#sumbitSaveBtn").css({"background":"#ccc","pointer-events": "none"});
		var merchantType = $("#merchantType").val();
		var name = $.trim($("#name").val());
		var industryCategoryCode = $.trim($("#industryCategoryCode").val());
		var addr = $.trim($("#addr").val());
		var personName = $.trim($("#personName").val());
		var telNo = $.trim($("#telNo").val());
		var deviceNo = $("#deviceNo").val();
		if(isNotEmpty(merchantType)){
			if(!isNotEmpty(name)){    	
				tipText("请输入终端店铺名称!")			
				return;
			}
			if(!isNotEmpty(industryCategoryCode)){    	
				tipText("请选择终端店铺所属行业!")			
				return;
			}
			
			if(!isNotEmpty(addr)){    	
				tipText("请输入您的终端店铺地址!")			
				return;
			}
			if(!isNotEmpty(personName)){    	
				tipText("请输入您终端店铺的联系人姓名!")	;		
				return;
			}
			if(!isNotEmpty(telNo)){	    	
				tipText("请输入您终端店铺的联系电话!")		
				return;
			}			
			var regx = /^0?(13|15|17|18|14)[0-9]{9}$|^[0][0][8][5][2]([5|6|9])\d{7}$|^[0][0][8][5][3][6]\d{7}$|^[0][0][8][8][6][0][9]\d{8}$/;			
			if(!regx.test(telNo)){
				tipText("您输入的终端店铺联系电话格式不正确!");
				return;
			}
		}
		var latitude = $("#latitude").val();
		var longitude = $("#longitude").val();
		$.JsonRpc(
				"",
				new dataArea('wx', 'submitMerchantInfo', 
					{
					opType:$("#opType").val(),
					superMerchantId:$("#merchantId").val(), 
					openId:$("#openId").val(),
					 custNo:$("#custNo").val(),
					 personName:$.trim($("#personName").val()),
					 startShopTime:$("#startShopHours").val(),
					 endShopTime:$("#endShopHours").val(),			         					 
			         province:$("#provinceName").val(),
			         city:$("#cityName").val(),
			         districtName:$("#districtName").val(),
					 addr:$("#addr").val(),
					 name:$.trim($("#name").val()),
					 latitude:latitude,
					 longitude:longitude,
					 merchantType:merchantType,
					 industryCategoryCode:$.trim($("#industryCategoryCode").val()),
					 telNo:$.trim($("#telNo").val()),
					 storePhoneNo:$.trim($("#storePhoneNo").val())
					 }
				),
				function(data){
					if(data.result == "success"){
							location.href=baseUrl+"/wx/registerApplyJump?openId="+$("#openId").val();
							$("#sumbitSaveBtn").removeAttr("style");
					}else{
						tipText(data.message);						
					}
					$("#msg_btn").click(function(){
						$("#sumbitSaveBtn").removeAttr("style");			
					});
				}
		);
		
	});
}


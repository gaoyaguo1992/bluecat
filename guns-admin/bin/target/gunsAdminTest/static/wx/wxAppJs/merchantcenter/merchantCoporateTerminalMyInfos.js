$(function(){
	sumbitWithdraw();
});
function isNotEmpty(content){
	if(content == 'null' || content == null || content =='undefined' || content == undefined || $.trim(content) == '')
		{return false;}
	else
		{return true;}
}
function tipText(cont){
	$("#result_prompt").text(cont);	    	
	$("#messageDialog").show();
	$("#msg_btn").click(function(){
		$("#messageDialog").hide();			
	});
}
/*获取验证码end*/

function sumbitWithdraw(){
	$("#merchantCoporateBtn").click(function(){
		var name = $("#name").val();
		if(!isNotEmpty(name)){    	
			tipText("亲,请填写商铺名称!")			
			return;
		}
		var name = $("#name").val();
		if(!isNotEmpty(name)){	    	
			tipText("亲,请输入您的店铺名称!")		
			return;
		}
		
		var addr = $("#addr").val();
		if(!isNotEmpty(addr)){    	
			tipText("亲,请输入您的店铺地址!")			
			return;
		}
		var personName = $("#personName").val();
		if(!isNotEmpty(personName)){    	
			tipText("亲,您的联系人姓名不能为空!")	;		
			return;
		}
		var telNo = $("#telNo").val();			
		if(!isNotEmpty(telNo)){	    	
			tipText("亲,您的联系电话不能为空!");		
			return;
		}			
		var regx = /^0?(13|15|17|18|14|19|16|)[0-9]{9}$|^[0][0][8][5][2]([5|6|9])\d{7}$|^[0][0][8][5][3][6]\d{7}$|^[0][0][8][8][6][0][9]\d{8}$/;			
		if(!regx.test($.trim(telNo))){
			tipText("亲,手机号格式不正确!");
			return;
		}
		$("#merchantCoporateBtn").css({"background":"#ccc","pointer-events": "none"});		
		var parameter ={openId:$("#openId").val(),
					personName:$("#personName").val(),
					addr:$("#addr").val(),
					gaodeMap:$("#gaodeMap").val(),
					name:$("#name").val(),
					id:$("#id").val(),
					comment:$("#comment").val(),
					industryCategoryCode:$("#industryCategoryCode").val(),
					telNo:$("#telNo").val()
				}		
		$.JsonRpc(
				"",
				new dataArea('wx', 'modifyMerInfo',parameter),
				function(data){
					tipText(data.message);
					if(data.result == "success"){
						$("#msg_btn").click(function(){
							location.href=reqUrl+"/wx/registerApplyJump?openId="+$("#openId").val()+"&showFlag=1";
							$("#merchantCoporateBtn").removeAttr("style");			
						});
					}
					$("#msg_btn").click(function(){
						$("#merchantCoporateBtn").removeAttr("style");			
					});
				},function(obj){
					
				}
		);		
	});
}
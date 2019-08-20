$(function(){
	
	sumbitWithdraw();
	//getCode();
	$("#msg_btn").click(function(){
		$("#messageDialog").hide();			
	});
	
})
function sumbitWithdraw(){
	$("#merchantLoginBtn").click(function(){
				
		var userName = $.trim($("#userName").val());
		if(userName==""){
			$("#result_prompt").text("亲,用户名不能为空!");	    	
			$("#messageDialog").show();		
			return;
		}
		
		var userPassword = $.trim($("#userPassword").val());
		if(userPassword==""){
			$("#result_prompt").text("亲,密码不能为空!");	    	
			$("#messageDialog").show();		
			return;
		}

		$.JsonRpc(
				"",
				new dataArea('usercenter', 'doMerchantLogin', 
						{openId:$("#openId").val(),
						userName:$("#userName").val(),
						userPassword:$("#userPassword").val()						 
						}
				),
				function(data){
					if(data.errorCode=="error"){
						$("#result_prompt").text(data.message);								
						$("#messageDialog").show();	
						$("#msg_btn").show();
					}else{
						$("#result_prompt").text(data.message);
						$("#messageDialog").show();	
						$("#msg_btn").show();
						location.href="/share/usercenter/merchantJump.htm?openId="+$("#openId").val();
					}				
		});
		
	});
}

function getCode(){
	
	
	//$("#sand").hide();//隐藏计时
	
	$("#yzm").click(function(){
		if($.trim($("#teleponeNumber").val())==""){
			$("#result_prompt").text("手机号不能为空");	    	
			$("#messageDialog").show();				
			return;
		}
		
		var regx = /^0?(13|15|17|18|14)[0-9]{9}$/;
		var val = $.trim($("#teleponeNumber").val());
		if(regx.test(val)!=true){
			$("#result_prompt").text("手机号格式不正确");	    	
			$("#messageDialog").show();						
			return;
		}
		var openId = $("#openId").val();
		$.JsonRpc(
				"",
				new dataArea('usercenter', 'getPhoneCheckCode', 
						{phoneNumber:$('#teleponeNumber').val(),openId:openId}
				),
				function(data){
			        
					if(!data.flag){
						$("#result_prompt").text(data.failureDetails);	    	
						$("#messageDialog").show();		
												
					}else{
						//if($("#yzm").text()==="获取验证码"||$("#yzm").text()==="重新获取"){
							$("#yzm").attr("disabled",true);//设置按钮不可用
							//alert($("#yzm").text()+"444");
							$("#sandNext").text("秒后重新获取");//改变按钮文字
							$("#sand").text(data.phoneCheckCodeTimeOut);//设定60秒
							$("#sand").show();//显示计时
							var r = setInterval(function(){
								var num = $("#sand").text();
								num--;
								$("#sand").text(num);
								if(num==0){
									clearInterval(r);
									$("#sand").text("");
									$("#yzm").attr("disabled",false);
									$("#sandNext").text("重新获取");	
									$("#sand").hide();//隐藏计时
								}
							},1000)
						//}						
					}
		});
		
	});	
}

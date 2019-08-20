$(function(){
	//校验手机号码
//	checkPhone();
	//获取验证码
	getCode();
	//点击下一步
	clickButton();
	
})
function clickButton(){
	$("#registerButton").click(function(){
		clickNext();
		if($(".info").length>0){
			return false;
		}
		$("div.info").remove();
		var telNo = $("#phone").val();
		var param = {};
		param.userId=$.url.param('userId');
		param.userName=$.url.param('userName');
		param.password=$.url.param('passWord');
		param.shortName=$.url.param('custName');//简称
		param.telNo=telNo;
		var openid=sessionStorage.openid;
		param.share=openid;
		
		
		
		$.JsonRpc(
				"",
				new dataArea('register', 'registerNormal', 
						{phoneCheckCode:$('#code').val()}
				),
				function(data){
					if(data.result=="error"){
						$("#code").parents("p").append("<div class='info' style='text-align:left'><font color='red'>"+data.message+"</font></div>");
					}else{
					$.JsonRpc(
							"",
							new dataArea('register', 'StaffCommit', 
									param
							),
							function(data){
								if(data.result == "success"){	
									location.href="register_finish.html?message="+data.message;
			//							top.messageShow(data.message);
								}else {
									location.href="register_finish.html?message="+data.message;
			//							top.messageShow(data.message); 
								}
							});
					}
		});
	});
}
function getCode(){
	$("#sand").hide();//隐藏计时
	$(".rel").click(function(){
		if($("#phone").val()==""){
			$("._phone").remove();
			$("#phone").parents("p").append("<div class='info _phone'  style='text-align:left'><font color='red'>手机号不能为空</font></div>");
		}
		regx = /^0?(13|15|17|18|14)[0-9]{9}$/;
		startCheck(regx,"phone","格式不正确");
		if($("._phone").length==0){
			var regx = /^0?(13|15|17|18|14)[0-9]{9}$/;
			var val = $.trim($("#phone").val());
			$("div.info").remove();
			if(regx.test(val)==true){
				$.JsonRpc(
						"",
						new dataArea('register', 'getPhoneCheckCode', 
								{phoneNumber:$('#phone').val()}
						),
						function(data){
					        if($(".rel").text()=="获取验证码"||$(".rel").text()=="重新获取"){
								//$(this).attr("disabled",true);//设置按钮不可用
								$("#sand").next().text("秒后重新获取");//改变按钮文字
								$("#sand").text(data.phoneCheckCodeTimeOut);//设定60秒
								$("#sand").show();//显示计时
								var r = setInterval(function(){
									var num = $("#sand").text();
									num--;
									$("#sand").text(num);
									if(num==0){
										clearInterval(r);
										$("#sand").text("");
										//$(this).attr("disabled",false);
										$("#sand").next().text("重新获取");	
										$("#sand").hide();//隐藏计时
									}
								},1000)
							}
							if(!data.flag){
								$("#code").parents("p").append("<div class='info' style='text-align:left'><font color='red'>"+data.failureDetails+"</font></div>");
							}
				});
			}else{
				$("#phone").parents("p").append("<div class='info' style='text-align:left'><font color='red'>手机号格式不正确</font></div>");
			}
		}
	});
		
	
}
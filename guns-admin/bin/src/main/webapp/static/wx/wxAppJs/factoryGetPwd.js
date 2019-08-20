document.onkeydown=function(event){
	var e = event || window.event || arguments.callee.caller.arguments[0];
	if(e && e.keyCode==13){
		check();
	}
}
function changeVerifyCode() {
	$("#LoginImgVerifyCode").attr("src", "")
			.attr(
					"src",
					reqUrl+'/appjsp/chargerPwdCheck/image.jsp?type=login&'
							+ new Date().getTime());
}
function check(){
	 $('#messageSpan').text('');
	  if($('#codeInput').val() === ''){
		  	$("#panel h2").css("padding","10px 20px 30px 20px");
	        $('#messageSpan').text('验证码不能为空');
	        $("#messageSpan").css("display", "block");
	        return false;
	  };
	  $("#messageSpan").css("display", "none");

	  $.JsonSRpc(reqUrl+"/factoryGetPwd/validCode.htm",{
		  code:$("#codeInput").val(),
		},function(data){
			if(data.flag == "true"){
				$('form').submit();
			}else {
				$("#panel h2").css("padding","10px 20px 30px 20px");
		        $('#messageSpan').text('验证码验证失败');
		        $("#messageSpan").css("display", "block");
		        return false;
			}
		});
	  
}
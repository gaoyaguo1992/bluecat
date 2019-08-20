$(function(){
	var msg=$.url.param('message');
	$("#message").html(msg)
	
	$("#whoUser").click(function(){
		message('普通用户是在平台完成快速注册的游客，并未完成企业认证的机构或个人。');
	});
})

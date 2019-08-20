$(function(){
	checkuserId();
})
function checkuserId(){
	$("#userId").blur(function(){
		var userId = $("#userId").val();
		if(userId != ""){
			//后加的 开始
			var reg=/^[A-Za-z0-9_]{1,20}$/;
			var userId=$('#userId').val();
			if(reg.test(userId)==false){
				$("._userId").remove();
				$("#userId").parents("p").append("<div class='info _userId' ><font color='red'>用户名只能为字母数字下划线！</font></div>");
			}else{
				$.JsonRpc(
						"",
						new dataArea('register', 'checkUserId', 
								{userId:$('#userId').val()}
						),
						function(data){
							if(data.result != "success"){			
								$("#userId").parents("p").append("<div class='info _userId' ><font color='red'>该用户名已存在</font></div>");
							}else{
								$("._userId").remove();
							}
						});
			}
			//结束
			}else{
			$("._userId").remove();
			$("#userId").parents("p").append("<div class='info _userId' ><font color='red'>用户名不能为空</font></div>");
		}
		
		
		
	});
}

function goNext(){
	clickNext();
	if($(".info").length>0){
		return false;
	}
	
	
	if($('#userId').val()!=""){
		$.JsonRpc(
				"",
				new dataArea('register', 'checkUserId', 
						{userId:$('#userId').val()}
				),
				function(data){
					if(data.result == "success"){	
						//clickNext();
						if($(".info").length>0){
							return false;
						}
						var userId=$("#userId").val();
						var userName=$("#userName").val();
						var passWord=$("#passWord").val();
						var custName=$("#custName").val();
						location.href="register_phone.html?userId="+userId+"&userName="+userName+"&passWord="+passWord+"&custName="+custName;
	//					alert("success");
					}else {
						$("._userId").remove();
						$("#userId").parents("p").append("<div class='info _userId' ><font color='red'>该用户名已存在</font></div>");
					}
				});
	}
}
//function callback(){
//	clickNext();
//	if($(".info").length>0){
//		return false;
//	}
//	var userId=$("#userId").val();
//	var userName=$("#userName").val();
//	var passWord=$("#passWord").val();
//	var custName=$("#custName").val();
//	location.href="register_phone.html?userId="+userId+"&userName="+userName+"&passWord="+passWord+"&custName="+custName;
//}
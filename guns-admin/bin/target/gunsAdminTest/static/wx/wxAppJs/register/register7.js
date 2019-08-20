$(function(){	 
	 /*
		 * 根据微信号取数据，如果有显示，没有就放空
		 */
		var openid=sessionStorage.openid;
		//var openid="oKeSgsyj4g2STo00usHDkziFTsN411";
		if(""==openid||null==openid){
		}
		else{
			$("#certNo").select2({
				minimumResultsForSearch: -1,
				placeholder: "请选择公司证件类型",
				allowClear: false,
				data:[{id:"1",text:"公司三证"},{id:"2",text:"统一社会信用代码"}]
			});
			//公司证件类型选择触发事件
			$("#certNo").on("change",function(e){
				var certNo = e.val;//选中的id
				if(certNo==='1'){
					$("#creditCode").val("");
					$("#tongyi").hide();
					$("#sanzheng").show();
					//验证营业执照注册号
					$("#businessLicense").blur(function(e) {
						var a;
						if($(this).val()==""||$(this).val()==null){
							$("div._"+$(this).attr("id")).remove();
							$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"不能为空"+"</font></div>");
						}else{
							regx = /^\d{15}$/;
							if(regx.test($(this).val())==false){
								$("div._"+$(this).attr("id")).remove();
								$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"格式不正确"+"</font></div>");
							}else{
								$("div._"+$(this).attr("id")).remove();
							}
						}
			        });
					//验证税务登记证号
					$("#faxNo").blur(function(e) {
						if($(this).val()==""||$(this).val()==null){
							$("div._"+$(this).attr("id")).remove();
							$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"不能为空"+"</font></div>");
						}else{
							regx = /^\d{15}$/;
							if(regx.test($(this).val())==false){
								$("div._"+$(this).attr("id")).remove();
								$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"格式不正确"+"</font></div>");
							}else{
								$("div._"+$(this).attr("id")).remove();
							}
						}
					});
					//验证组织机构代码证号
					$("#organizationCode").blur(function(e) {
						if($(this).val()==""||$(this).val()==null){
							$("div._"+$(this).attr("id")).remove();
							$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"不能为空"+"</font></div>");
						}else{
							regx = /^\d{8}-\d{1}$/;
							if(regx.test($(this).val())==false){
								$("div._"+$(this).attr("id")).remove();
								$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"格式不正确"+"</font></div>");
							}else{
								$("div._"+$(this).attr("id")).remove();
							}
						}
					});
				}else if(certNo==='2'){
					$("#businessLicense").val("");
					$("#faxNo").val("");
					$("#organizationCode").val("");
					$("#sanzheng").hide();
					$("#tongyi").show();
					$("#creditCode").blur(function(e) {
						if($(this).val()==""||$(this).val()==null){
							$("div._"+$(this).attr("id")).remove();
							$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"不能为空"+"</font></div>");
						}else if($(this).val().length != 18){
							$("div._"+$(this).attr("id")).remove();
							$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"输入有误，请输入18位统一社会信用代码"+"</font></div>");
						}else{
							$("div._"+$(this).attr("id")).remove();
						}
					});
				}	
			});
			$.JsonRpc(
					"",
					new dataArea('register', 'getCustInfo',{share:openid}
					),
					function(data){
						if(data.result == "success"){	
							$("#creditCode").val(data.responseInfo.creditCode);
							$("#businessLicense").val(data.responseInfo.businessLicense);
							$("#faxNo").val(data.responseInfo.faxNo);
							$("#organizationCode").val(data.responseInfo.organizationCode);
							
						}else {
//							top.messageShow(data.message); 
						}
					});
			
			
		}
})

function goNext(){
	var certNo = $("#certNo").val();
	if(certNo==='1'){
		$("#creditCode").val("");
		$("#tongyi").hide();
		$("#sanzheng").show();
		//验证营业执照注册号
		$("#businessLicense").blur(function(e) {
			var a;
			if($(this).val()==""||$(this).val()==null){
				$("div._"+$(this).attr("id")).remove();
				$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"不能为空"+"</font></div>");
			}else{
				regx = /^\d{15}$/;
				if(regx.test($(this).val())==false){
					$("div._"+$(this).attr("id")).remove();
					$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"格式不正确"+"</font></div>");
				}else{
					$("div._"+$(this).attr("id")).remove();
				}
			}
        });
		//验证税务登记证号
		$("#faxNo").blur(function(e) {
			if($(this).val()==""||$(this).val()==null){
				$("div._"+$(this).attr("id")).remove();
				$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"不能为空"+"</font></div>");
			}else{
				regx = /^\d{15}$/;
				if(regx.test($(this).val())==false){
					$("div._"+$(this).attr("id")).remove();
					$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"格式不正确"+"</font></div>");
				}else{
					$("div._"+$(this).attr("id")).remove();
				}
			}
		});
		//验证组织机构代码证号
		$("#organizationCode").blur(function(e) {
			if($(this).val()==""||$(this).val()==null){
				$("div._"+$(this).attr("id")).remove();
				$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"不能为空"+"</font></div>");
			}else{
				regx = /^\d{8}-\d{1}$/;
				if(regx.test($(this).val())==false){
					$("div._"+$(this).attr("id")).remove();
					$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"格式不正确"+"</font></div>");
				}else{
					$("div._"+$(this).attr("id")).remove();
				}
			}
		});
	}else if(certNo==='2'){
		$("#businessLicense").val("");
		$("#faxNo").val("");
		$("#organizationCode").val("");
		$("#sanzheng").hide();
		$("#tongyi").show();
		$("#creditCode").blur(function(e) {
			if($(this).val()==""||$(this).val()==null){
				$("div._"+$(this).attr("id")).remove();
				$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"不能为空"+"</font></div>");
			}else{
				$("div._"+$(this).attr("id")).remove();
			}
		});
	}else{
		alert("请选择证件类型");
		return false;
	}	
	//clickNext();
	if($(".info").length>0){
		return false;
	}
	var share=sessionStorage.openid;
	//var share = "oKeSgsyj4g2STo00usHDkziFTsN411";
	var param = {};
    if($("#certNo").val()==='1'){
    	param.businessLicense = $("#businessLicense").val();
    	param.faxNo = $("#faxNo").val();
    	param.organizationCode = $("#organizationCode").val();
    }else if($("#certNo").val()==='2'){
    	param.creditCode = $("#creditCode").val();
    }
	/*param.businessLicense = $("#businessLicense").val();
	param.faxNo = $("#faxNo").val();
	param.organizationCode = $("#organizationCode").val();*/
	param.step='7';
	param.share=sessionStorage.openid;
	//param.share="oKeSgsyj4g2STo00usHDkziFTsN411";
	$.JsonRpc(
			"",
			new dataArea('register', 'registerCommit', 
					param
			),
			function(data){
				if(data.result == "success"){
					location.href="register8.html?message="+data.message;
//					top.messageShow(data.message);
				}else {
					alert(data.message) 
				}
			});
	
}





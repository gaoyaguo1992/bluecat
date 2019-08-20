$(function(){
	 selectData();
	 /*
	 * 根据微信号取数据，如果有显示，没有就放空
	 */
	var openid=sessionStorage.openid;
	//var openid="oKeSgsyj4g2STo00usHDkziFTsN411";
	if(""==openid||null==openid){
	}
	else{
		$.JsonRpc(
				"",
				new dataArea('register', 'getCustInfo',{share:openid}
				),
				function(data){
					if(data.result == "success"){
						$("#registAddr").val(data.responseInfo.registAddr);
						$("#custName").val(data.responseInfo.custName);
						$.JsonSRpc(baseUrl + "/common/initProvinces.htm", {}, function(data) {
							for(var i=0;i<data.length;i++){
								//去掉港澳台
								if("71000000"===data[i].id||"81000000"===data[i].id||"82000000"===data[i].id){
									data.splice(i);
									i--;
								}
							}
							$("#provinceNo").select2({
								placeholder: "请选择省份",
								allowClear: true,
								data:data
							});
							//修改火狐的串行问题
							$(".select2-container").css({display:"inline-block"});
						})
						$("#provinceNo").val(data.responseInfo.province);
						$("#provinceCn").val(data.responseInfo.provinceCn);
						$.JsonSRpc(baseUrl + "/common/updateCitys.htm", {provinceNo:data.responseInfo.province}, function(data) {
							$("#cityNo").select2({
								placeholder: "请选择城市",
								allowClear: true,
								data:data
							});
							//修改火狐的串行问题
							$(".select2-container").css({display:"inline-block"});
						})
						//初始化加载企业类型
						$.JsonSRpc(baseUrl + "/register/initIndustryType.htm", {}, function(data) {
							$("#industryTypeCode").select2({
								placeholder: "请选择企业类型",
								allowClear: true,
								data:data
							});
							//修改火狐的串行问题
							$(".select2-container").css({display:"inline-block"});
						})
						$("#cityNo").val(data.responseInfo.cityNo);
						$("#cityCn").val(data.responseInfo.cityCn);
						$("#address").val(data.responseInfo.address);
						$("#postcode").val(data.responseInfo.postcode);
						$("#contactPhone").val(data.responseInfo.contactPhone);
						$("#fax").val(data.responseInfo.fax);
						$("#email").val(data.responseInfo.email);
						$("#recCode").val(data.responseInfo.recCode);
						$("#accountManager").val(data.responseInfo.accountManager);
						$("#contacts").val(data.responseInfo.contacts);
						$("#telNo").val(data.responseInfo.telNo);
						$("#industryTypeCode").val(data.responseInfo.industryTypeCode);
					}else {
//						top.messageShow(data.message); 
					}
				});
		
		
	}
	 
})
function selectData(){
	
	//初始化加载省份数据
	$.JsonSRpc(baseUrl + "/common/initProvinces.htm", {}, function(data) {
		for(var i=0;i<data.length;i++){
			//去掉港澳台
			if("71000000"===data[i].id||"81000000"===data[i].id||"82000000"===data[i].id){
				data.splice(i);
				i--;
			}
		}
		$("#provinceNo").select2({
			placeholder: "请选择省份",
			allowClear: true,
			data:data
		});
		//修改火狐的串行问题
		$(".select2-container").css({display:"inline-block"});
	})
	//定义城市下拉框，不初始化数据，数据根据选择的省份加载
	$("#cityNo").select2({
			placeholder: "请选择城市",
			allowClear: true,
			data:[]
		});
	//省份下拉框点击change事件：触发城市下拉框数据重新加载
	$("#provinceNo").on("change",function(e){
		var provinceNo = e.val;//选中的省份id
		$.JsonSRpc(baseUrl + "/common/updateCitys.htm", {provinceNo:provinceNo}, function(data) {
			$("#cityNo").select2({
				placeholder: "请选择城市",
				allowClear: true,
				data:data
			});
			//修改火狐的串行问题
			$(".select2-container").css({display:"inline-block"});
		})
		if($("#provinceNo").select2("data")!=null){
			$("#provinceCn").val($("#provinceNo").select2("data").text);
		}else{
			$("#provinceCn").val("");
		}
		$("#cityNo").val("");
		$("#cityCn").val("");
	})
	$("#cityNo").on("change",function(e){
			$("#cityCn").val($("#cityNo").select2("data").text);
	})
	//企业类型下拉框点击
	$("#industryTypeCode").on("change",function(e){
		$("#industryTypeCn").val($("#industryTypeCode").select2("data").text);
	})
	//修改火狐的串行问题
	 $(".select2-container").css({display:"inline-block"});
	
	//推荐人代码
	$("#recCode").blur(function(){
		if($("#recCode").val()!=""){
			getInviteCustName();
		}else{
			$(".yesCust").remove();
			$("._recCode").remove();
		}
	});
}
function goNext(){
	clickNext();
	if($(".info").length>0){
		return false;
	}
	var share=sessionStorage.openid;
	//var share="oKeSgsyj4g2STo00usHDkziFTsN411";
	var param = {};
	param.registAddr = $("#registAddr").val();
	param.custName = $("#custName").val();
	param.province = $("#provinceNo").val();
	param.provinceCn = $("#provinceCn").val();
	param.cityNo = $("#cityNo").val();
	param.cityCn = $("#cityCn").val();
	param.address = $("#address").val();
	param.postcode = $("#postcode").val();
	param.contactPhone = $("#contactPhone").val();
	param.fax = $("#fax").val();
	param.email = $("#email").val();
	param.recCode = $("#recCode").val();
	param.step='4';
	param.share=sessionStorage.openid;
	param.accountManager = $("#accountManager").val();
	param.contacts = $("#contacts").val();
	param.telNo = $("#telNo").val();
	param.industryTypeCode = $("#industryTypeCode").val();
	if($("#recCode").val()!=""){
		$.JsonSRpc(baseUrl + "/register/getInviteCustName.htm", {recCode:$("#recCode").val()}, function(data) {
			if(data.message!=null&&data.message!=""){
				$(".yesCust").remove();
				$("._recCode").remove();
				$("#recCode").parents("p").append("<div class='yesCust' style='text-align:left'><font color='red'>"+data.message+"</font></div>");	
				$.JsonRpc(
						"",
						new dataArea('register', 'registerCommit', 
								param
						),
						function(data){
							if(data.result == "success"){	
								location.href="register5.html";
							}else {
								$(".yesCust").remove();
								$("#recCode").parents("p").append("<div class='yesCust' style='text-align:left'><font color='red'>"+data.message+"</font></div>");
							}
						});
			}else{
				$(".yesCust").remove();
				$("._recCode").remove();
				$("#recCode").parents("p").append("<div class='info _recCode' style='text-align:left'><font color='red'>无此会员！</font></div>");	
			}
		});
	}else{
		$.JsonRpc(
				"",
				new dataArea('register', 'registerCommit', 
						param
				),
				function(data){
					if(data.result == "success"){	
						location.href="register5.html";
					}else {
						$(".yesCust").remove();
						$("#recCode").parents("p").append("<div class='yesCust' style='text-align:left'><font color='red'>"+data.message+"</font></div>");
					}
				});
	}

	
}
/**
 *根据邀请码取得企业名 
 */
function getInviteCustName(){
	$.JsonSRpc(baseUrl + "/register/getInviteCustName.htm", {recCode:$("#recCode").val()}, function(data) {
		if(data.message!=null&&data.message!=""){
			$(".yesCust").remove();
			$("._recCode").remove();
			$("#recCode").parents("p").append("<div class='yesCust' style='text-align:left'><font color='red'>"+data.message+"</font></div>");	
		}else{
			$(".yesCust").remove();
			$("._recCode").remove();
			$("#recCode").parents("p").append("<div class='info _recCode' style='text-align:left'><font color='red'>无此会员！</font></div>");	
		}
    });
}
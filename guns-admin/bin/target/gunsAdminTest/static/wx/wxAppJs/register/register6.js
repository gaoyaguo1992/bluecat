$(function(){
	 
	selectData();
 /*
 * 根据微信号取数据，如果有显示，没有就放空
 */
	var openid=sessionStorage.openid;
	if(""==openid||null==openid){
	}
	else{
		$.JsonRpc(
				"",
				new dataArea('register', 'getCustInfo',{share:openid}
				),
				function(data){
					if(data.result == "success"){	
						$("#accountName").val(data.responseInfo.accountName);
						$("#settleAccount").val(data.responseInfo.encryptAccount);
						$("#bankType").val(data.responseInfo.bankType).trigger("change");
						$("#province").val(data.responseInfo.bankProvinceNo).trigger("change");
						$.JsonSRpc(baseUrl + "/common/updateCitys.htm", {provinceNo:data.responseInfo.bankProvinceNo}, function(data) {
							$("#city").select2({
								placeholder: "请选择城市",
								allowClear: true,
								data:data
							});
							//修改火狐的串行问题
							$(".select2-container").css({display:"inline-block"});
						})
						
						$("#city").val(data.responseInfo.bankCityNo).trigger("change");
						$("#branch").val(data.responseInfo.bankNo);
						
					}else {
//							top.messageShow(data.message); 
					}
				});
		
		
	}
})
function selectData(){
	//初始加载开户银行数据
	$.JsonSRpc(baseUrl + "/common/initBankList.htm", {}, function(data) {
		$("#bankType").select2({//开户行行别
			placeholder: "请选择开户银行",
			allowClear: true,
			data:data
		});
		//修改火狐的串行问题
		$(".select2-container").css({display:"inline-block"});
    });
	//初始化加载省份数据
	$.JsonSRpc(baseUrl + "/common/initProvinces.htm", {}, function(data) {
		$("#province").select2({
			placeholder: "请选择省份",
			allowClear: true,
			data:data
		});
		//修改火狐的串行问题
		$(".select2-container").css({display:"inline-block"});
	})
	//定义城市下拉框，不初始化数据，数据根据选择的省份加载
	$("#city").select2({
			placeholder: "请选择城市",
			allowClear: true,
			data:[]
		});
	//定义支行下拉框，不初始化数据，数据根据选择的行别和城市加载
	$("#branch").select2({
		placeholder: "请选择支行",
		allowClear: true,
		data:[]
	});
	//开户行下拉框点击change事件：触发城市下拉框数据重新加载
	$("#bankType").on("change",function(e){
		//开户行修改后联动清空省份
		$.JsonSRpc(baseUrl + "/common/initProvinces.htm", {}, function(data) {
			$("#province").select2({
				placeholder: "请选择省份",
				allowClear: true,
				data:data
			});
			//开户行修改后联动清空市
			$("#city").select2({
				placeholder: "请选择城市",
				allowClear: true,
				data:[]
			});
			//开户行修改后联动清空支行
			$("#branch").select2({
				placeholder: "请选择支行",
				allowClear: true,
				data:[]
			});
			//修改火狐的串行问题
			$(".select2-container").css({display:"inline-block"});
		})
		$("#province").val("");//清空已选中的省份
		$("#city").val("");
		$("#branch").val("");
	});
	//省份下拉框点击change事件：触发城市下拉框数据重新加载
	$("#province").on("change",function(e){
		var provinceNo = e.val;//选中的省份id
		$.JsonSRpc(baseUrl + "/common/updateCitys.htm", {provinceNo:provinceNo}, function(data) {
			$("#city").select2({
				placeholder: "请选择城市",
				allowClear: true,
				data:data
			});
			//省份修改后联动清空支行
			$("#branch").select2({
				placeholder: "请选择支行",
				allowClear: true,
				data:[]
			});
			//修改火狐的串行问题
			$(".select2-container").css({display:"inline-block"});
		})
		$("#city").val("");
		$("#branch").val("");
	});
	//行别/城市下拉框点击change事件：根据城市和行别更新加载支行数据
	$("#city").on("change",function(e){
		var bankType = $("#bankType").val();//行别
		var city = $("#city").val();//城市
		$.JsonSRpc(baseUrl + "/common/updateBranch.htm", {bankType:bankType,cityNo:city}, function(data) {
			$("#branch").select2({
				placeholder: "请选择支行",
				allowClear: true,
				data:data
			});
			//修改火狐的串行问题
			$(".select2-container").css({display:"inline-block"});
		})
		$("#branch").val("");
	});
	//选择支行后 去掉提示信息
	$("#branch").on("change",function(e){
		if($("#branch").val()!=""){
			$(".ssdd").remove();
		}
	});
//修改火狐的串行问题
 $(".select2-container").css({display:"inline-block"});
}

function goNext(){
	clickNext();
	if($(".info").length>0){
		return false;
	}
	var share=sessionStorage.openid;
	var param = {};
	param.accountName = $("#accountName").val();
	param.bankType = $("#bankType").val();//银行行别
	param.bankProvinceNo = $("#province").val();
	param.bankCityNo = $("#city").val();
	param.bankNo = $("#branch").val();//支行名
	param.accountNo = $("#settleAccount").val();
	param.step='6';
	param.share=sessionStorage.openid;
	$.JsonRpc(
			"",
			new dataArea('register', 'registerCommit', 
					param
			),
			function(data){
				if(data.result == "success"){	
					location.href="register7.html";
//					top.messageShow(data.message);
				}else {
					alert(data.message) 
				}
			});
	
}





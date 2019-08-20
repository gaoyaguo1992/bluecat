$(function(){
	setDefaultAccount();
});
function setDefaultAccount(){
	$.JsonSRpc(reqUrl+"/merchantCenter/getAccountList.htm",{
		custNo:window.location.href.split("=")[1]
	},function(data){
		console.log(data);
		if(data.result!="success"){
			return;
		}
		data = data.responseInfo.custMerchants;
		for(var i in data){
			var className = '';
			if(data[i].isDefault == 1){
				className = 'switchlist active'
			}else{
				className = 'switchlist'
			}
			$("#switchlistbox").append(
				'<div class="'+className+'">'+
					'<input id="custNo'+i+'" name="custNo" type="hidden" value = "'+data[i].custNo+'"/>'+   
			    	'<input id="merchantId'+i+'" name="merchantId" type="hidden" value = "'+data[i].merchantId+'"/>'+
					'<input type="radio" name="account">'+data[i].merchantId+'('+data[i].merchantTypeCn+')'+
					'<span>[默认账号]</span>'+
					'<i class="iconfont icon-check"></i>'+
				'</div>'	
        	);
		}
		$("#switchlistbox .switchlist").each(function(index) {		
		    $(this).click(function() {
		    	$(".switchlist").removeAttr('checked');
		    	$(".switchlist").removeClass('active');
		    	$(this).addClass("active");
				$(this).find('input').attr("checked",'true');
				$.JsonRpc(
						"",
						new dataArea('merchantCenter', 'setDefaultAccount', 
								{merchantId:$("#merchantId"+index).val(),
									custNo:$("#custNo"+index).val()					 
									}
						),
						function(data){
							$("#tip").show();
							$("#tip").html(data.message);
							setTimeout(function(){
								$("#tip").hide();
							},5000)
				});		
		    });
		});		
	});
}
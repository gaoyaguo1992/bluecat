$(function(){
	adverPage();
	adboxtypebtn = $(".adboxtypebtn");
	herfDedail = $(adboxtypebtn).attr('href');
	$(adboxtypebtn).attr('href','javascript:void(0)');
	
	//.....
	adboxtypebtn1 = $("#adboxtypebtn1");
	herfDedail1 = $(adboxtypebtn1).attr('href');
	$(adboxtypebtn1).attr('href','javascript:void(0)');
});



function adverPage(){
    $("#adBox").click(function(){  
    	var adId = $("#adId").val();
    	var deviceId = $("#deviceId").val();
    	var chargerId = $("#chargerId").val();
    	var custNo = $("#custNo").val();
    	if(adId =="null"){
    		adId = "";
    	}
    	if(deviceId == "null"){
    		deviceId = "";
    	}
    	if(chargerId == "null"){
    		chargerId = "";
    	}
    	if(custNo == "null"){
    		chargerId = "";
    	}
    	$.JsonRpc(
				"",
				new dataArea('merchantAdClick', 'addMerchantAdClick', 
						{adId:adId,deviceId:deviceId,chargerId:chargerId,custNo:custNo}
				),
				function(data){    					    	
				}
		);
    });   
}
function textAd(){
	var adId = $("#adId").val();
	var deviceId = $("#deviceId").val();
	var chargerId = $("#chargerId").val();
	var custNo = $("#custNo").val();
	if(adId =="null"){
		adId = "";
	}
	if(deviceId == "null"){
		deviceId = "";
	}
	if(chargerId == "null"){
		chargerId = "";
	}
	if(custNo == "null"){
		custNo = "";
	}
	$.JsonRpc(
			"",
			new dataArea('merchantAdClick', 'addMerchantAdClick', 
					{adId:adId,deviceId:deviceId,chargerId:chargerId,custNo:custNo}
			),
			function(data){
				if(data.result == "success"){
					location.href = herfDedail;					
				}    					    	
			}
	);
}

function textAd1(){
	var adId = $("#adId").val();
	var deviceId = $("#deviceId").val();
	var chargerId = $("#chargerId").val();
	var custNo = $("#custNo").val();
	if(adId =="null"){
		adId = "";
	}
	if(deviceId == "null"){
		deviceId = "";
	}
	if(chargerId == "null"){
		chargerId = "";
	}
	if(custNo == "null"){
		custNo = "";
	}
	$.JsonRpc(
			"",
			new dataArea('merchantAdClick', 'addMerchantAdClick', 
					{adId:adId,deviceId:deviceId,chargerId:chargerId,custNo:custNo}
			),
			function(data){
				if(data.result == "success"){
					location.href = herfDedail1;					
				}    					    	
			}
	);
}
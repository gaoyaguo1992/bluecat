$(document).ready(function(){
    $("#inputMoney").focus();
    merchantWithdraw();
});
function merchantWithdraw(){
    function disableButton(id){
        $(id).css("opacity",".5");
        $(id).attr("disabled",true);
    }
    function ableButton(id){
        $(id).css("opacity","1");
        $(id).attr("disabled",false);
    }
    var canwithdrawBtn = $("#canwithdrawBtn");            
    var allMoneyTxt = Number($("#allMoney").text());

    $("#withdrawAll").click(function(){
        if (allMoneyTxt>20000) {
            $("#canwithdraw").hide();
            $("#withdrawtip").show();
            $("#withdrawtip").text("根据微信官方规定，单笔提现金额不能大于2万");
            $("#inputMoney").val(20000);
        }else{
            $("#inputMoney").val($("#allMoney").text());
        }
        ableButton(canwithdrawBtn);
    });
    $('#inputMoney').bind('input propertychange', function() {
        var rex=/^\d+$/g;
        var inputMoneyVal = Number($.trim($("#inputMoney").val()));
        if(rex.test(inputMoneyVal) && inputMoneyVal > 0 && inputMoneyVal <=20000){
            if (inputMoneyVal <= allMoneyTxt) {
                ableButton(canwithdrawBtn);
                $("#canwithdraw").show();
                $("#withdrawtip").hide();                  
            }else{
                disableButton(canwithdrawBtn);
                $("#canwithdraw").hide();
                $("#withdrawtip").show();
                $("#withdrawtip").text("输入金额超过可提现余额");
            }
        }else if(inputMoneyVal > 20000){
        	 $("#inputMoney").val(20000);
             $("#withdrawtip").show();
             $("#withdrawtip").text("根据微信官方规定，单笔提现金额不能大于2万");
             $("#canwithdraw").hide();
        	 ableButton(canwithdrawBtn);
        }else{
            $("#withdrawtip").show();
            $("#withdrawtip").text("输入小于等于20000的正整数");
            $("#canwithdraw").hide();
            disableButton(canwithdrawBtn);
        } 
    });
    $("#canwithdrawBtn").click(function(){
        var inputMoney = Number($.trim($("#inputMoney").val()));
        var merchantId = $.trim($("#merchantId").val());
        var custNo = $.trim($("#custNo").val());
        var openId = $.trim($("#openId").val());
        $("#messageDialogProcess").show();          
        $.JsonRpc(
	        "",
	        new dataArea('wx', 'insertWithdrawApplyRecord', 
	                {merchantId:merchantId,preTaxAmount:inputMoney,openId:openId,custNo:custNo}
	        ),
	        function(data){
	            $("#messageDialogProcess").hide();
	            if(data.result=="success"){
	                $("#result_prompt").text(data.message);                         
	            }else{
	                $("#result_prompt").text(data.message); 
	            }
	            $("#messageDialog").show();
	            $("#msg_btn").click(function(){
	            	location.href=baseUrl+"/wx/registerApplyJump?openId="+$("#openId").val();//跳转到商户中心
	            });
	        },null,function(obj){
	            $("#messageDialogProcess").hide();
	        }); 
    });    
}
 var Curpage = 1;
 $(function (){
	 getYsIncome();
	
 });
 // 获取数据
 function getYsIncome() {
	var rows = 10;
	$.JsonSRpc(reqUrl+"/usercenter/getWithdrawInfoDetail.htm",{
		withdrawId :$("#withdrawId").val()
	 },function(data){
		if(data.result!="success"){
			return;
		}else{ //成功
		var listData=data.responseInfo;
		$('.time').text(listData.payTime);
		$('#withdrawMoney').text(listData.preTaxAmount);
		$('#afterTaxMoney').text(listData.aftTaxAmount);
		$('#num').text(listData.id);
			}
	 });
};
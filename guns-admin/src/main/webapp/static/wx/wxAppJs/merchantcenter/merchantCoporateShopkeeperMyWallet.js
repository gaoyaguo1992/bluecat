$(function(){
	withdrawals();
	cashRecord();
});
function withdrawals(){		
	$("#withdrawals").click(function(){
		$("#withdrawalsForm").submit();
	});
}
function cashRecord(){		
	$("#cashRecord").click(function(){
		$("#cashRecordForm").submit();
	});
}







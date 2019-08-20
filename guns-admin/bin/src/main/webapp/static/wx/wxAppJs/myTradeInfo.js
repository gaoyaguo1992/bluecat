$(function () {
	$(".tab li").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
		$(".mytradebox").find(".mytrade").eq($(this).index()).show().siblings().hide();
		if($("#myTradeDetail").is(":visible")){
			$(".mytrade .devinfor").remove();
			myTradeDetail(1);
		}else if($("#myProfitDetail").is(":visible")){
			$(".mytrade .devinfor").remove();
			myProfitDetail(1);
		}
	});
	var oDate = new Date(); //实例一个时间对象；
	var year = oDate.getFullYear();   //获取系统的年；
	var mouth = oDate.getMonth();   //获取系统月份，由于月份是从0开始计算，所以要加1
	var mouthend = oDate.getMonth()+1;	
	var day = oDate.getDate()-1; // 获取系统日	
	if (mouthend == 1) {
		var yearpre = year - 1;
		$("#startTime").val(yearpre+"-"+12 +"-"+ day);
	}else{
		if (mouth >= 1 && mouth <= 9) {
			mouth = "0" + mouth;
	    }
		$("#startTime").val(year+"-"+mouth +"-"+ day);
	}
	if (mouthend >= 1 && mouthend <= 9) {
		mouthend = "0" + mouthend;
    }
	$("#endTime").val(year+"-"+mouthend +"-"+ day);
		
	var currYear = (new Date()).getFullYear();	
	var opt={};
	opt.date = {preset : 'date'};
	opt.datetime = {preset : 'datetime'};
	opt.time = {preset : 'time'};
	opt.defaultparam = {
		theme: 'android-ics light', //皮肤样式
		display: 'modal', //显示方式 
		mode: 'scroller', //日期选择模式
		dateFormat: 'yyyy-mm-dd',
		lang: 'zh',
		showNow: true,
		nowText: "今天",
		startYear: currYear - 50, //开始年份
		endYear: currYear + 10 //结束年份
	};

	$("#startTime").mobiscroll($.extend(opt['date'], opt['defaultparam']));
	$("#endTime").mobiscroll($.extend(opt['date'], opt['defaultparam']));

});
function isNotEmpty(content){
	if(content == 'null' || content == null || content =='undefined' || content == undefined || $.trim(content) == '')
		{return false;}
	else
		{return true;}
}


function profitDetailLook(obj){
	var time = $(obj).attr("data-msgtime");
	var ratioId = $(obj).attr("data-msgproductid");
	$("#startTime").val(time);
	$("#endTime").val(time);
	$("#ratioId").val(ratioId);
	$(".tab li").removeClass("active");
	$(".tab li").eq(1).addClass("active");
	$("#myTradeDetail").show();
	$("#myProfitDetail").hide();
	$("#myTradeDetail").empty();
	myTradeDetail(1);
}
var rows = 10;
//交易明细
function myTradeDetail(page){
	if($("#myTradeDetail").is(":visible")){
		$("#profitTongji").hide();
		$("#tradeTongji").show();
	}else if($("#myProfitDetail").is(":visible")){
		$("#profitTongji").show();
		$("#tradeTongji").hide();
	}
	$("#profitTongji").hide();
	$("#tradeTongji").show();
	$.JsonSRpc(reqUrl+"/usercenter/myHistoryTradeDetailShow.htm",{
		start:(page-1)*rows,
		rows:rows,
		/*ratioId: $("#ratioId").val(),*/
		merchantId:$("#merchantId").val(),
		merchantType:$("#merchantType").val(),
		beginDateTime:$("#startTime").val(),
		endDateTime :$("#endTime").val()
	},function(data){
		if(data.result!="success"){
			message(data.message);
			return;
		}
		if(page==1){
		}
		if(!isNotEmpty(data.tradeAmount)){
			data.tradeAmount = 0;
		}
		if(!isNotEmpty(data.tradeCount)){
			data.tradeCount = 0;
		}		
		$("#tradeTotalMoney").text(data.tradeAmount);
		$("#tradeTotalPens").text(data.tradeCount);
		data = data.myTradeDetailInfoBOs;
		$("#more1").remove();
		for(var i in data){
			if(data[i].tradeType == 61){
				$("#myTradeDetail").append(
					    '<div class="devinfor">'+
				        	'<h2>交易编号：'+data[i].tradeId+'</h2>'+
				        	'<p>交易时间：'+data[i].tradeTimeFromat+'</p>'+	
				        	'<p>归还时间：'+data[i].backTimeFromat+'</p>'+	        	
				        	'<p>交易金额：<span>'+data[i].tradeAmount+'</span></p>'+
				        	'<p>交易状态：<span class="status">'+data[i].tradeStatusCn+'</span></p>'+
				        	'<p>交易名称：<span class="status">'+data[i].tradeName+'</span></p>'+
				        	'<p>退款原因：<span class="status">'+data[i].operateComment+'</span></p>'+
				        	'<p>原交易编号：<span class="status">'+data[i].refTradeId+'</span></p>'+
			        	'</div>'
		        	);
			}else{
				$("#myTradeDetail").append(
					    '<div class="devinfor">'+
				        	'<h2>交易编号：'+data[i].tradeId+'</h2>'+
				        	'<p>交易时间：'+data[i].tradeTimeFromat+'</p>'+	
				        	'<p>归还时间：'+data[i].backTimeFromat+'</p>'+	        	
				        	'<p>交易金额：<span>'+data[i].tradeAmount+'</span></p>'+
				        	'<p>交易状态：<span class="status">'+data[i].tradeStatusCn+'</span></p>'+
				        	'<p>交易名称：<span class="status">'+data[i].tradeName+'</span></p>'+
			        	'</div>'
		        	);
			}
			
		}
		if(data.length==rows){
			$("#myTradeDetail").append("<a id='more1' href='javascript:void(0)' onclick='myTradeDetail("+(page+1)+")'>点击获取更多</a>");
		}
	});
}
//收益明细
function myProfitDetail(page){
	if($("#myTradeDetail").is(":visible")){
		$("#profitTongji").hide();
		$("#tradeTongji").show();
	}else if($("#myProfitDetail").is(":visible")){
		$("#profitTongji").show();
		$("#tradeTongji").hide();
	}
	$.JsonSRpc(reqUrl+"/usercenter/myIncomeDetailShow.htm",{
		start:(page-1)*rows,
		rows:rows,
		merchantId:$("#merchantId").val(),
		merchantType:$("#merchantType").val(),
		shopKeeperType:$("#shopKeeperType").val(),
		beginDateTime:$("#startTime").val(),
		endDateTime :$("#endTime").val()
	},function(data){
		if(data.result!="success"){
			message(data.message);
			return;
		}
		if(page==1){
		}
		if(!isNotEmpty(data.incomeAmount)){
			data.incomeAmount = 0;
		}
		if(!isNotEmpty(data.incomeCount)){
			data.incomeCount = 0;
		}		
		$("#profitTotalMoney").text(data.incomeAmount);
		$("#profitTotalPens").text(data.incomeCount);
		data = data.myIncomeDetailInfoBOs;
		$("#more2").remove();
		for(var i in data){
			$("#myProfitDetail").append(
			    '<div class="devinfor" data-msgtime='+data[i].incomeDateTimeFormat+' data-msgproductid='+data[i].ratioId+' onclick="profitDetailLook(this)">'+
		        	'<h2>收益时间：'+data[i].incomeDateTimeFormat+'</h2>'+
		        	'<p>收益金额：<span>￥'+data[i].incomeAmount+'</span></p>'+	
		        	'<p>可结算金额：<span>￥'+data[i].doneAmount+'</span></p>'+	        	
		        	//'<p>收益明细：'+data[i].incomeDesc+'</p>'+
		        	' <div class="shouyimingxi">收益明细</div>'+
	        	'</div>'
        	);
		}
		if(data.length==rows){
			$("#myProfitDetail").append("<a id='more2' href='javascript:void(0)' onclick='myProfitDetail("+(page+1)+")'>点击获取更多</a>");
		}
	});
}

$("#search").click(function(){
	$(".mytrade .devinfor").remove();
	if($("#myTradeDetail").is(":visible")){
		myTradeDetail(1);
	}else if($("#myProfitDetail").is(":visible")){
		myProfitDetail(1);
	}
});
TouchSlide({ slideCell:"#myTradeDetail",endFun:function(i,c){
	myTradeDetail(1);
}});
TouchSlide({ slideCell:"#myProfitDetail",endFun:function(i,c){
	myProfitDetail(1);
}});
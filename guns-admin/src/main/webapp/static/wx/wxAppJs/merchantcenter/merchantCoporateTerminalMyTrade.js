$(function () {
	timeSelect(1);
});

//时间选择
function timeSelect(timeChose) {
	var startTime='';
	var endTime= '';
	var oDate = new Date(); //实例一个时间对象；
	var year = oDate.getFullYear();   //获取系统的年；
	var mouth = oDate.getMonth();   //获取系统月份，由于月份是从0开始计算，所以要加1
	var mouthend = oDate.getMonth()+1;	
	var day = oDate.getDate(); // 获取系统日	
	if(timeChose==1){
		 var n=new Date(oDate.getTime());
		    var m =new Date(oDate.getTime()-86400000*30);
		   endTime=n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate();
		   startTime=m.getFullYear()+"-"+ (m.getMonth()+1)+"-"+ m.getDate();
		     $("#startTime").val(startTime);
		    $("#endTime").val(endTime);
	}
	// 2个时间段	
	var currYear = (new Date()).getFullYear();	
	var opt1={};
	opt1.date = {preset : 'date',
			defaultValue: startTime
	};
	opt1.defaultparam = {
		theme: 'android-ics light', //皮肤样式
		display: 'modal', //显示方式 
		mode: 'scroller', //日期选择模式
		dateFormat: 'yy-mm-dd',
		lang: 'zh',
		showNow: true,
		nowText: "今天",
		startYear: currYear - 50, //开始年份
		endYear: currYear + 10 //结束年份
	};

	var opt2={};
	opt2.date = {preset : 'date',
			defaultValue:	endTime
	};
	opt2.defaultparam = {
		theme: 'android-ics light', //皮肤样式
		display: 'modal', //显示方式 
		mode: 'scroller', //日期选择模式
		dateFormat: 'yy-mm-dd',
		lang: 'zh',
		showNow: true,
		nowText: "今天",
		startYear: currYear - 50, //开始年份
		endYear: currYear + 10 //结束年份
	};
	
	$("#startTime").mobiscroll($.extend(opt1['date'], opt1['defaultparam']));
	$("#endTime").mobiscroll($.extend(opt2['date'], opt2['defaultparam']));
};

function isNotEmpty(content){
	if(content == 'null' || content == null || content =='undefined' || content == undefined || $.trim(content) == '')
		{return false;}
	else
		{return true;}
}
//查询交易数据...
var rows = 10;
var page = 1;
function trade(page){
	startShieldLayer();
	$.JsonSRpc(reqUrl+"/wx/myTradeDatas",{
		start:(page-1)*rows,
		rows:rows,
		merchantId:$("#merchantId").val(),
		merchantType:$("#merchantType").val(),
		startTime:$("#startTime").val(),
		endTime :$("#endTime").val()
	},function(data){
		finishShieldLayer();
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
		$("#more").remove();
		for(var i in data){
			if(data[i].tradeType == 61){
				$("#myTradeDetail").append(
					    '<div class="devinfor">'+
				        	'<h2>交易编号：'+data[i].tradeId+'</h2>'+
				        	'<p>交易时间：'+data[i].tradeTimeFromat+'</p>'+	
				        	'<p>归还时间：'+data[i].backTimeFromat+'</p>'+	        	
				        	'<p>交易金额：<span>'+data[i].tradeAmount+'</span></p>'+	
				        	'<p>我的收益：<span>'+data[i].profitAmountStr+'</span></p>'+
				        	'<p>交易状态：<span class="status">'+data[i].tradeStatusCn+'</span></p>'+
				        	'<p>交易名称：<span class="status">'+data[i].tradeName+'</span></p>'+
				        	'<p>退款原因：<span class="status">'+data[i].operateComment+'</span></p>'+
				        	'<p>原交易编号：<span class="status">'+data[i].refTradeId+'</span></p>'+
				        	'<p>终端商户：<span>'+data[i].merchantCn+' '+data[i].merchantId+'</span></p>'+
				        	'<p>加盟商用：<span>'+data[i].allianceBusinessCn+' '+data[i].allianceBusinessId+'</span></p>'+
			        	'</div>'
		        	);
			}else{
				$("#myTradeDetail").append(
					    '<div class="devinfor">'+
				        	'<h2>交易编号：'+data[i].tradeId+'</h2>'+
				        	'<p>交易时间：'+data[i].tradeTimeFromat+'</p>'+	
				        	'<p>归还时间：'+data[i].backTimeFromat+'</p>'+	        	
				        	'<p>交易金额：<span>'+data[i].tradeAmount+'</span></p>'+	
				        	'<p>我的收益：<span>'+data[i].profitAmountStr+'</span></p>'+
				        	'<p>交易状态：<span class="status">'+data[i].tradeStatusCn+'</span></p>'+
				        	'<p>交易名称：<span class="status">'+data[i].tradeName+'</span></p>'+
				        	'<p>终端商户：<span>'+data[i].merchantCn+' '+data[i].merchantId+'</span></p>'+
				        	'<p>加盟商用：<span>'+data[i].allianceBusinessCn+' '+data[i].allianceBusinessId+'</span></p>'+
			        	'</div>'
		        	);
			}
		}
		if(data.length==rows){
			$("#myTradeDetail").append("<a id='more' href='javascript:void(0)' onclick='trade("+(page+1)+")'>点击获取更多</a>");
		}
	},null,function(obj){
 		finishShieldLayer();
	});
}


/**
 * 开启屏蔽层.
 */
startShieldLayer = function() {
	$('#myModal').modal("show");
}
/**
 * 结束屏蔽层.
 */
finishShieldLayer = function() {
	$('#myModal').modal("hide");
}
/**
 * 查询..
 */
$("#search").click(function(){
	$("#myTradeDetail .devinfor").remove();
	trade(page);
});
TouchSlide({ slideCell:"#myTradeDetail",endFun:function(i,c){
	trade(page);
}});
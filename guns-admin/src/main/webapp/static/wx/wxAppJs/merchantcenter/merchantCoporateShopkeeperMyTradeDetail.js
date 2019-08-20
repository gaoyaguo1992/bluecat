var Curpage = 1;
var deviceId = $("#deviceId").val() == null? '':$("#deviceId").val();

window.onload = function(){
	document.getElementById('merchantTradeList').innerHTML = "";
	trade(Curpage);
}
$(function () {	
	//	tab选择
	$(".tab-box span").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
	});
	//	时间切换
    var timeChose=1;
    timeSelect(timeChose);
	
	var obj = document.getElementById("nav").getElementsByTagName("span");
	for(i in obj){
		obj[i].index = i;
	    obj[i].onclick = function(){
	    	for(var j=0;j< obj.length; j++){
	    		obj[j].className = "";
	    	}
	    	this.className = "active";
	    	document.getElementById('merchantTradeList').innerHTML = "";
	    	if(this.index == 0){
	    		document.getElementById("status").value = "";
	    	}
	    	if(this.index == 1){
	    		document.getElementById("status").value = 11;
	    	}
	    	if(this.index == 2){
	    		document.getElementById("status").value = 10;
	    	}
	    	trade(Curpage);
	    }
	}	
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
		if (mouthend == 1) {
			var yearpre = year - 1;
			startTime =$("#startTime").val(yearpre+"-"+12 +"-"+ day);
		}else{
			startTime=$("#startTime").val(year+"-"+mouth +"-"+ day);
		}
		endTime=$("#endTime").val(year+"-"+mouthend +"-"+ day);
		$("#queryStatus").val(1);
	}else{
		    var n=new Date(oDate.getTime()-86400000*90);
		    var m =new Date(oDate.getTime()-86400000*120);
		   endTime=n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate();
		   startTime=m.getFullYear()+"-"+ (m.getMonth()+1)+"-"+ m.getDate();
		     $("#startTime").val(startTime);
		    $("#endTime").val(endTime);
		$("#queryStatus").val(2);
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
var merchantId = $("#merchantId").val();
function trade(page){	
	startShieldLayer();
	var rows = 10;
	$.JsonSRpc(reqUrl+"/wx/getOrdersInfoByPage",{
		start:(page-1)*rows,
		rows:rows,
		isParent:isParent,
		merchantId:$("#merchantId").val(),
		queryStatus:$("#queryStatus").val(),
		status:$("#status").val(),
		beginDateTime:$("#startTime").val(),
		endDateTime :$("#endTime").val(),
		deviceId:deviceId
	},function(data){
		finishShieldLayer();
		if(data.result!="success"){
			Feng.alert(data.message);
			return;
		}
		if(page==1){
		}
		if(!isNotEmpty(data.tradeTotalAmount)){
			data.tradeTotalAmount = 0;
		}
		if(!isNotEmpty(data.tradeTotalCount)){
			data.tradeTotalCount = 0;
		}		
		$("#tradeTotalAmount").text(data.tradeTotalAmount);
		$("#tradeTotalNum").text(data.tradeTotalCount);
		var custNo = $("#custId").val();
		data = data.merchantOrderRecordInfoBOs;
		var everyMerchantInfo = "";
		
		$("#more").remove();
		for(var i in data){
			//订单所属设备信息
			var deviceInfoModel = data[i].shareDeviceInfoModel;
			
			if(data[i].myOrderStatus == 10){
				$("#merchantTradeList").append(
					    '<div class="list">'+
					    	'<p class="title">'+
					    	'<span class="blue-icon"></span>'+
					    	'订单编号 '+
					    	'<span>'+data[i].orderId+'</span>'+
					    	'<span class="right" style="color:red">使用中</span>'+
					    	'</p>'+
					    	'<ul class="list-con">'+
					    		'<li>'+
					                '<span>客户编号</span>'+data[i].custNo+
					            '</li>'+
					            '<li>'+
				                	'<span>设备编号</span>'+data[i].deviceId+
				                '</li>'+
				                '<li>'+
			                		'<span>充电器编号</span>'+data[i].chargerId+
			                	'</li>'+
			                	'<li>'+
				                	'<span>交易金额</span>'+data[i].tradeAmount+
				                '</li>'+
				                '<li>'+
			                		'<span>终端商户</span>'+deviceInfoModel.onlineMerchantCn+" "+deviceInfoModel.onlineMerchantId+
			                	'</li>'+
				                '<li>'+
			                		'<span>加盟商</span>'+deviceInfoModel.allianceBusinessCn+" "+deviceInfoModel.allianceBusinessId+
			                	'</li>'+
					            '<li>'+
					                '<span>交易时间</span>'+data[i].createTimeFormat+
					            '</li>'+
					            '<li>'+
					                '<span>归还时间</span>'+data[i].backTimeFormat+
					            '</li>'+
					            '<a class="gorow" href="'+baseUrl+'/wx/getShowOrderDetail?orderId='+data[i].orderId+'&merchantId='+merchantId+'"></a>'+
				            '</ul>'+
			        	'</div>'
		        	);
			}else if(data[i].myOrderStatus == 11){
				$("#merchantTradeList").append(
					    '<div class="list">'+
					    	'<p class="title">'+
					    	'<span class="blue-icon"></span>'+
					    	'订单编号 '+
					    	'<span>'+data[i].orderId+'</span>'+
					    	'<span class="right" style="color:green">交易成功</span>'+
					    	'</p>'+
					    	'<ul class="list-con">'+
					    		'<li>'+
					                '<span>客户编号</span>'+data[i].custNo+
					            '</li>'+
					            '<li>'+
			                		'<span>设备编号</span>'+data[i].deviceId+
			                	'</li>'+
			                	'<li>'+
		                			'<span>充电器编号</span>'+data[i].chargerId+
		                		'</li>'+
		                		 '<li>'+
					                '<span>交易金额</span>'+data[i].tradeAmount+
					            '</li>'+
				                '<li>'+
			                		'<span>终端商户</span>'+deviceInfoModel.onlineMerchantCn+" "+deviceInfoModel.onlineMerchantId+
			                	'</li>'+
				                '<li>'+
			                		'<span>加盟商</span>'+deviceInfoModel.allianceBusinessCn+" "+deviceInfoModel.allianceBusinessId+
			                	'</li>'+
					            '<li>'+
					                '<span>交易时间</span>'+data[i].createTimeFormat+
					            '</li>'+
					            '<li>'+
					                '<span>归还时间</span>'+data[i].backTimeFormat+
					            '</li>'+
					            '<a class="gorow" href="'+baseUrl+'/wx/getShowOrderDetail?orderId='+data[i].orderId+'&merchantId='+merchantId+'"></a>'+
				            '</ul>'+
			        	'</div>'
		        	);
			}else{
				$("#merchantTradeList").append(
					    '<div class="list">'+
					    	'<p class="title">'+
					    	'<span class="blue-icon"></span>'+
					    	'订单编号 '+
					    	'<span>'+data[i].orderId+'</span>'+
					    	'<span class="right">'+data[i].myOrderStatusCn+'</span>'+
					    	'</p>'+
					    	'<ul class="list-con">'+
					    		'<li>'+
					                '<span>客户编号</span>'+data[i].custNo+
					            '</li>'+
					            '<li>'+
				                	'<span>交易金额</span>'+data[i].tradeAmount+
				                '</li>'+
				                '<li>'+
			                		'<span>终端商户</span>'+deviceInfoModel.onlineMerchantCn+" "+deviceInfoModel.onlineMerchantId+
			                	'</li>'+
				                '<li>'+
			                		'<span>加盟商</span>'+deviceInfoModel.allianceBusinessCn+" "+deviceInfoModel.allianceBusinessId+
			                	'</li>'+
					            '<li>'+
					                '<span>交易时间</span>'+data[i].createTimeFormat+
					            '</li>'+
					            '<li>'+
					                '<span>归还时间</span>'+data[i].backTimeFormat+
					            '</li>'+
					            '<a class="gorow" href="'+baseUrl+'/wx/getShowOrderDetail?orderId='+data[i].orderId+'&merchantId='+merchantId+'"></a>'+
				            '</ul>'+
			        	'</div>'
		        	);
			}
		}
		if(data.length==rows){
			$("#merchantTradeList").append("<a id='more' href='javascript:void(0)' onclick='trade("+(page+1)+")'>点击获取更多</a>");
		}
	},function(obj){
		finishShieldLayer();
	});
	
}

$("#search").click(function(){
	$("#tradeTotalAmount").text("0");
	$("#tradeTotalNum").text("0");
	document.getElementById('merchantTradeList').innerHTML = "";
	trade(Curpage);
});

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
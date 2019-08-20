$(function (){
	 var timeChose=1;
	 timeSelect(timeChose);
	 getYsIncome(Curpage);
	 $("#search").click(function(){
			$("#tradeTotalAmount").text("0");
			$("#tradeTotalNum").text("0");
			$('.lists').empty();
			getYsIncome(Curpage);
	 });
});
// 获取数据
function getYsIncome(page) {
  var rows = 10;
  $.JsonSRpc(reqUrl+"/wx/myIncomeDetailData",{
		start:(page-1)*rows,
		rows:rows,
		isParent:isParent,
		merchantId:$("#merchantId").val(),
		beginDateTime:$("#startTime").val(),
		endDateTime :$("#endTime").val(),
	},function(data){
		if(data.result!="success"){
			return;
		}else{ //成功
		  $("#more").remove();
		  if(!isNotEmpty(data.incomeAmount)){
				data.incomeAmount = 0;
		  }
		  if(!isNotEmpty(data.incomeCount)){
				data.incomeCount = 0;
			}	
		  $('#tradeTotalAmount').text(data.incomeAmount);
		  $('#tradeTotalNum').text(data.incomeCount);
		  var listData=data.myIncomeDetailInfoBOs;
		  $.each(listData,function(i,list) {
			  if(!(list.incomeAmount.toString().indexOf("-") != -1)) {
					list.incomeAmountStr='+'+list.incomeAmountStr
			  }
			  $('.lists').append(
					    '<li>'+
					    '<a style="display: block;height: inherit;" href="'+reqUrl+
					    '/wx/yesterdayIncome?searchTime='+list.incomeDateTimeFormat+'&isParent='+isParent+'&merchantId='+$("#merchantId").val()+'">'+
						'<span class="list-time">'+list.incomeDateTimeFormat+'</span><span class="incomeMoney">'+
						    	list.incomeAmountStr+'</span>'+
						'</a>'+
						'</li>'
			 );
		  })
		  if(listData.length==rows){
				$(".list-con").append("<a id='more' href='javascript:void(0)' onclick='getYsIncome("+(page+1)+")'>点击获取更多</a>");
			}  
		}
   });
};

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
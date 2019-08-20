$(function(){
	
	//1请求后台取七、30天未使用设备信息，未使用设备的商家
	getUnusedDeviceInfo();
	
	//2登陆用户所有设备七天使用率,用于图表显示
	//结果集格式："data":"使用率列表"，"days":"时间列表" 按时间升序
	getTotalDeviceUsageTrend();
	
	//3登陆用户所有设备七天交易金额,用于图表显示
	//结果集格式："data":"交易金额列表"，"days":"时间列表" 按时间升序
	getTotalDeviceTradeAmount();
	
	//4所有设备运营数据（借出订单，归还订单，交易总金额）
	getTotalDeviceBusinessData();
	
	//5店铺七天设备使用率排行type=1
	listCalculateMerchantData(1);
	
	//6店铺充电器数量排行 type=2
	listCalculateMerchantData(2);
})

//店铺七天设备使用率排行type=1
//店铺充电器数量排行 type=2
function listCalculateMerchantData(type) {
	var ajax = new $ax(Feng.ctxPath + "/index/listCalculateMerchantData/"+type, function (data) {
		if(type == 1){
			//第一名
			var one = data.rows[0];
			if(one == null || one == undefined){
				return;
			}
			//店铺七天设备使用率排行
			$("#usageTrendTop1Name").html(one.merchantInfoModel.name);
			$("#usageTrendTop1").html(one.device7daysUsageRate);
			//第二名
			var two = data.rows[1];
			if(two == null || two == undefined){
				return;
			}
			//店铺七天设备使用率排行
			$("#usageTrendTop2Name").html(two.merchantInfoModel.name);
			$("#usageTrendTop2").html(two.device7daysUsageRate);
			//第三名
			var three = data.rows[2];
			if(three == null || three == undefined){
				return;
			}
			//店铺七天设备使用率排行
			$("#usageTrendTop3Name").html(three.merchantInfoModel.name);
			$("#usageTrendTop3").html(three.device7daysUsageRate);
			//第四名
			var four = data.rows[3];
			if(four == null || four == undefined){
				return;
			}
			//店铺七天设备使用率排行
			$("#usageTrendTop4Name").html(four.merchantInfoModel.name);
			$("#usageTrendTop4").html(four.device7daysUsageRate);
			//第五名
			var five = data.rows[4];
			if(five == null || five == undefined){
				return;
			}
			//店铺七天设备使用率排行
			$("#usageTrendTop5Name").html(five.merchantInfoModel.name);
			$("#usageTrendTop5").html(five.device7daysUsageRate);
			
		}
	    if(type == 2){
	    	//店铺充电器数量排行
	    	//第一名
			var one = data.rows[0];
			if(one == null || one == undefined){
				return;
			}
			//店铺充电器数量排行
			$("#deviceCountTop1Name").html(one.merchantInfoModel.name);
			$("#deviceCountTop1").html(one.deviceQty);
			//第二名
			var two = data.rows[1];
			if(two == null || two == undefined){
				return;
			}
			//店铺充电器数量排行
			$("#deviceCountTop2Name").html(two.merchantInfoModel.name);
			$("#deviceCountTop2").html(two.deviceQty);
			//第三名
			var three = data.rows[2];
			if(three == null || three == undefined){
				return;
			}
			//店铺充电器数量排行
			$("#deviceCountTop3Name").html(three.merchantInfoModel.name);
			$("#deviceCountTop3").html(three.deviceQty);
			//第四名
			var four = data.rows[3];
			if(four == null || four == undefined){
				return;
			}
			//店铺充电器数量排行
			$("#deviceCountTop4Name").html(four.merchantInfoModel.name);
			$("#deviceCountTop4").html(four.deviceQty);
			//第五名
			var five = data.rows[4];
			if(five == null || five == undefined){
				return;
			}
			//店铺充电器数量排行
			$("#deviceCountTop5Name").html(five.merchantInfoModel.name);
			$("#deviceCountTop5").html(five.deviceQty);
	    }
		
	}, function (data) {
    });
	ajax.set("limit",5);
    ajax.start();
}

//1请求后台取七、30天未使用设备信息，未使用设备的商家
function getUnusedDeviceInfo() {
	var ajax = new $ax(Feng.ctxPath + "/index/unusedDeviceInfo", function (data) {
        $("#7daysNoUseMerchantCount").html("<b>"+data.noUseMerchantNumFor7+"</b>家");
        $("#7daysNoUseChargerCount").html("<b>"+data.noUseChargerNumFor7+"</b>个");
        $("#30daysNoUseChargerCount").html("<b>"+data.noUseChargerNumFor30+"</b>个");
    }, function (data) {
    });
    ajax.start();
}

//2登陆用户所有设备七天使用率,用于图表显示
//结果集格式："data":"使用率列表"，"days":"时间列表" 按时间升序
function getTotalDeviceUsageTrend() {
	var ajax = new $ax(Feng.ctxPath + "/index/totalDeviceUsageTrend", function (data) {
		console.log(data)
	deviceUsageTrendChart.setOption({
		 xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data:data.days 
		    }, series: [
		        {
		        	color:'#1ab394',
		            name:'充电器使用率',
		            type:'line',
		            stack: '总量',
		            data:data.data,
		            smooth: true
		        }
		    ]
	});
	}, function (data) {
    });
    ajax.start();
}

//3登陆用户所有设备七天交易金额,用于图表显示
//结果集格式："data":"交易金额列表"，"days":"时间列表" 按时间升序
function getTotalDeviceTradeAmount() {
	var ajax = new $ax(Feng.ctxPath + "/index/totalDeviceTradeAmount", function (d) {
        tradeAmountChart.setOption({
        	 xAxis: {
                 data: d.days
             }, series: [{
                 name: '近七天交易金额',
                 color:'#1ab394',
                 type: 'bar',
                 data: d.data
             }]
        });
    }, function (dd) {
    });
    ajax.start();
}

//4所有设备运营数据（借出订单，归还订单，交易总金额）
function getTotalDeviceBusinessData() {
	var ajax = new $ax(Feng.ctxPath + "/index/totalDeviceBusinessData", function (data) {
		var totalAmountCompareByDay = data.resultBO.totalAmountCompareByDay == null ? '0%':data.resultBO.totalAmountCompareByDay*100+"%";
		var finishOrdersCompareByDay = data.resultBO.finishOrdersCompareByDay == null ? '0%':data.resultBO.finishOrdersCompareByDay*100+"%";
		var ordersCompareByDay = data.resultBO.ordersCompareByDay == null ? '0%':data.resultBO.ordersCompareByDay*100+"%";
        $("#totalAmount").html("<b>"+data.resultBO.totalAmount+"</b>元");
        $("#totalAmountCompareByDay").html("日同比<b>"+totalAmountCompareByDay+"</b>");
        $("#historyFinishOrders").html("<b>"+data.resultBO.historyFinishOrders+"</b>笔");
        $("#finishOrdersCompareByDay").html("日同比<b>"+finishOrdersCompareByDay+"</b>");
        $("#historyOrders").html("<b>"+data.resultBO.historyOrders+"</b>笔");
        $("#ordersCompareByDay").html("日同比<b>"+ordersCompareByDay+"</b>");
    }, function (data) {
    });
    ajax.start();
}

function moreUsageTrendTopData() {
	location.href=Feng.ctxPath + "/index/moreUsageTrendTopData";
}

function moreDeviceCountTopData() {
	location.href=Feng.ctxPath + "/index/moreDeviceCountTopData";
}
//点运营数据跳转到交易查询
function redirectTradePage(){
	location.href=Feng.ctxPath + "/shareTradeInfo";
}
//点击连续七天未使用商家列表
function nouseByMerchantOf7DaysPage(){
	location.href=Feng.ctxPath + "/index/nouseByMerchantOf7DaysUI";
}
//点击连续七、三十天未使用充电器
function nousedChargerFewDays(days){
	location.href=Feng.ctxPath + "/index/nousedChargerFewDaysUI/"+days;
}


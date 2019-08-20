var Curpage = 1;
 $(function () {
	 getYsIncome(Curpage);
 })
 function getYsIncome(page) {
	 var rows = 10;
	 $.JsonSRpc(reqUrl+"/usercenter/getMerchantCapitalFlowInfo.htm",{
			start:(page-1)*rows,
			rows:rows,
			merchantId:$("#merchantId").val()
		},function(data){
			if(data.result!="success"){
				return;
			}else{ //成功
				  $("#more").remove();
			var listData=data.responseInfos;
			$.each(listData,function(i,list) {
				if(list.capitalTypeCn==='收益'){
					if(!(list.totalAmount.toString().indexOf("-") != -1)) {
						list.totalAmount='+'+list.totalAmount
					}
					$('.list-con ul').append(
						 '<li>'+	
						   '<a href="<%=path%>/appjsp/merchantcenter/walletBag/ysdayIncome.jsp?time='+list.checkDateFormat+'&merchantId=<%=merchantId%>">'+
							'<span class="icon-income">收</span>'+
				            '<span class="income-info">'+
				              '<i class="title">收益</i>'+
				              '<i class="time">'+list.checkDateFormat+'</i>'+
				            '</span>'+
				            '<span class="income-money">'+
				            	'<i class="title">'+list.totalAmount+'</i>'+
				            '</span>'+
				             '</a>'+
				           '</li>' 
							);
				}else{
					$('.list-con ul').append(
							'<li>'+	
						  '<a href="<%=path%>/appjsp/merchantcenter/walletBag/withdrawSuss.jsp?withdrawId='+list.id+'">'+
							'<span style="background:#FFA200" class="icon-income">提</span>'+
				            '<span class="income-info">'+
				              '<i class="title">提现</i>'+
				              '<i class="time">'+list.checkDateFormat+'</i>'+
				            '</span>'+
				            '<span class="income-money">'+
				              '<i class="title">'+'-'+list.totalAmount+'</i>'+
				            '</span>'+
				          '</a>'+  
				           '</li>' 
							);
				}
			});
			 if(listData.length==rows){
					$(".list-con").append("<a id='more' href='javascript:void(0)' onclick='getYsIncome("+(page+1)+")'>点击获取更多</a>");
				}  
			}		
		})		
 };
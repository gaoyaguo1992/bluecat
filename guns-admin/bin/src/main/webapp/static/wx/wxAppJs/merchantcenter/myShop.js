var rows=10;

function tradeRecord(page){//查询交易信息
	$.JsonSRpc(reqUrl+"/merchantCenter/getMyShopInfo.htm",{
		pageNum:page,
		rows:rows,
		merchantId:$("#oldMerchantId").val()  //先查出的是商户下所有的店铺
	},function(data){
		if(data.result!="success"){
			return;
		}
		if(page==1){
			
		}
		var shopInfos = data.shopInfos;
		var custNo = $("#custNo").val();
		$("#more").remove();
		if(shopInfos.length > 0){
			for(var i = 0 ; i < shopInfos.length ; i++){
				var shop = shopInfos[i];
				var merchantId = shop.merchantId;
				$("#myShop").append(
						'<a href="'+reqUrl+'/usercenter/myTradeInfo.htm?merchantId='+ merchantId +'&custNo='+custNo+'">'+
				        '<div class="assureDevl" data-index="'+ merchantId +'">'+
				          '<input name="shopId" type="hidden" value="'+ merchantId +'"/>'+
				          '<div class="assureDevltit shiyongzhong">'+shop.merchantName+'</div>'+
				          '<div class="assureDevlcont">'+
				            '<div class="assureDevlinfo">'+
				              '<span class="label">店铺ID</span>'+
				              '<span class="infor">'+shop.merchantId+'</span>'+
				            '</div>'+
				            '<div class="assureDevlinfo">'+
				              '<span class="label">交易次数</span>'+
				              '<span class="infor">'+shop.tradeCount+'</span>'+
				            '</div>'+
				            '<div class="assureDevlinfo">'+
				              '<span class="label">交易总金额</span>'+
				              '<span class="infor">￥'+ shop.tradeTotalMoney +'</span>'+
				            '</div>'+
				            '<div class="assureDevlinfo">'+
				              '<span class="label">店铺电话</span>'+
				              '<span class="infor">'+ shop.telNo +'</span>'+
				            '</div>'+
				            '<div class="assureDevlinfo">'+
				              '<span class="label">店铺地址</span>'+
				              '<span class="infor">'+shop.address+'</span>'+
				            '</div>'+          
				          '</div>'+
				        '</div>'+
				        '</a>'
	        	);
			}
		}else{
			
		}
		
		if(shopInfos.length==rows){
			$("#myShop").append("<a id='more' href='javascript:void(0)' onclick='myShop("+(page+1)+")'>点击获取更多</a>");
		}
	});
}

/*TouchSlide({ slideCell:"#myShop",endFun:function(i,c){
	tradeRecord(1);
	
}});*/

$(function(){
	TouchSlide({ slideCell:"#myShop",endFun:function(i,c){
		tradeRecord(1);
	}});
	
	$('#search').on("click",function(e){ //搜索点击事件
		var page = 1;
		var merchantId =$("#merchantId").val();
		var franchiseeId = $("#oldMerchantId").val();
		$.JsonSRpc(reqUrl+"/merchantCenter/searchMyShopInfo.htm?franchiseeId=" + franchiseeId +"&merchantId=" + merchantId,{
			pageNum:page,
			rows:rows,
			oldMerchantId:$("#oldMerchantId").val()  //先查出的是商户下所有的店铺
		},function(data){
			if(data.result!="success"){
				return;
			}
			if(page==1){
				$("#myShop").append("<p style='align:center;'>没有符合条件的信息</p>");
			}
			var shopInfos = data.shopInfos;
			var custNo = $("#custNo").val();
			$("#more").remove();
			//获取子元素删除
			var children = $("#myShop").children("a");
			
			for(var j = 0 ; j <= children.length ; j++){
				$(children[i]).remove();
			}
			if(shopInfos.length >0){
				for(var i = 0 ; i < shopInfos.length ; i++){
					var shop = shopInfos[i];
					var merchantId = shop.merchantId;
					$("#myShop").append(
							'<a href="'+reqUrl+'/usercenter/myTradeInfo.htm?merchantId='+ merchantId +'&custNo='+custNo+'">'+
					        '<div class="assureDevl" data-index="'+ merchantId +'">'+
					          '<input name="shopId" type="hidden" value="'+ merchantId +'"/>'+
					          '<div class="assureDevltit shiyongzhong">'+shop.merchantName+'</div>'+
					          '<div class="assureDevlcont">'+
					            '<div class="assureDevlinfo">'+
					              '<span class="label">店铺ID</span>'+
					              '<span class="infor">'+shop.merchantId+'</span>'+
					            '</div>'+
					            '<div class="assureDevlinfo">'+
					              '<span class="label">交易次数</span>'+
					              '<span class="infor">'+shop.tradeCount+'</span>'+
					            '</div>'+
					            '<div class="assureDevlinfo">'+
					              '<span class="label">交易总金额</span>'+
					              '<span class="infor">￥'+ shop.tradeTotalMoney +'</span>'+
					            '</div>'+
					            '<div class="assureDevlinfo">'+
					              '<span class="label">店铺电话</span>'+
					              '<span class="infor">'+ shop.telNo +'</span>'+
					            '</div>'+
					            '<div class="assureDevlinfo">'+
					              '<span class="label">店铺地址</span>'+
					              '<span class="infor">'+shop.address+'</span>'+
					            '</div>'+          
					          '</div>'+
					        '</div>'+
					        '</a>'
		        	);
				}
			}else{
				$("#myShop").append("<p style='align:center;'>没有符合条件的信息</p>");
			}
			
			if(shopInfos.length==rows){
				$("#myShop").append("<a id='more' href='javascript:void(0)' onclick='myShop("+(page+1)+")'>点击获取更多</a>");
			}
		});
	});
});

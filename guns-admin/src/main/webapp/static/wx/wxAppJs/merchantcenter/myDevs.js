// 获取微信配置
 var Curpage=1,rows=5,status='';
 var selectDeviceId=null,selectDeviceType=null;
 var custNo=$('#custNo').val();
 var openId=$('#openId').val(); // 所选择的设备Id
 var merchantId=$('#merchantId').val();
 var merchantType=$('#merchantType').val();
 var shopKeeperType="0";
  $(function(){
	  custNo=$('#custNo').val();
	  openId=$('#openId').val(); // 所选择的设备Id
	  merchantId=$('#merchantId').val();
	  merchantType=$('#merchantType').val();
	  getDeviceData();
	  // 点击跳转设置
	  // 搜索
	  $('#deviceId').bind('search',function(){
		  $('#deviceId').blur();
		  $('.dev-list').empty();
		  data={
				merchantId:merchantId,
				page:1,
				rows:rows,
				custNo:custNo,
				start:0,
				merchantType:merchantType,
				shopKeeperType:shopKeeperType,
				deviceId:$('#deviceId').val()
		  };
		  getDeviceData(data);		  
	  });
	  // 页面的一些交互
	  $('#selectOnline').click(function(){
		  if($('.shade').css('display')==='block'){
			  $('.shade').show();
			  $('.online').show();
			  $('#selectOnline').addClass('blueText');
			  /* $('#selectGrounding').removeClass('blueText'); */
		  }else if($('.shade').css('display')==='block'&& $('.online').css('display')==='block'){
			  $('.shade').hide();
			  $('.online').hide();
			  $('#selectOnline').removeClass('blueText');
		  }else{
			  $('.shade').show();
			  $('.online').show();
			  $('#selectOnline').addClass('blueText');
		  }			 
	  });
	  $('.shade').click(function(){
		  $('.shade').hide();
		  $('.online').hide();
		  $('#selectOnline').removeClass('blueText');
	  });
	  $('#selectSearch').click(function(){
		 $('.tab').hide();
		 $('.seachbox').show();
	  });
	  $('#search').click(function(){
		  $('.seachbox').hide();
			 $('.tab').show();
		  });
	  // 切换上下架
	  $('.online p').click(function(){
		  Curpage=1;
		  status=$(this).data('id');
		  let text=$(this).text();
		  $(this).children().addClass('seected').parent().siblings('p').children().removeClass('seected');
		  $('#selectOnline .text').text(text);
		  $('.dev-list').empty();
		  
		  let data={
					merchantId:merchantId,
					custNo:custNo,
					openId:openId,
					page:Curpage,
					rows:rows,
					start:0,
					merchantType:merchantType,
					shopKeeperType:shopKeeperType,
					status:status
				  };
		  getDeviceData(data);
		 
	  });
  });
  function tipText(cont){
	$("#result_prompt").text(cont);	    	
    $("#messageDialog").show();
    $("#msg_btn").click(function(){
	   $("#messageDialog").hide();	
	     });
     };
 // 使用率趋势
  function trendDetails(obj){
	var id = $(obj).data('id');
	console.log(id);
	$("#listDeviceId").val(id);
	$("#devTrendForm").submit();
   };
 /* 扫码获取设备信息 */
  function getDeviceId(data){
	  let deviceId={
			  codeContent:data,
			  merId:merchantId
	 };
	  $.ajax({
        type:"POST", 
        url:reqUrl+"wx/checkDeviceIdIsCorrectForDeviceScan", 
        //contentType:"application/json;charset=utf-8",  // 发送信息至服务器时内容编码类型。
        data:deviceId,
        dataType:'json',
        success: function (res) {
        	if(res.result==='success'){
        		if(res.responseInfo){
        			let data={
        					custNo:custNo,
        					openId:openId,
    	            		merchantId:merchantId,
    	        			page:1,
    	        			deviceId:res.responseInfo,
    	        			rows:rows,
    	        			start:0,
    						merchantType:merchantType,
    						shopKeeperType:shopKeeperType
    	            	};
    	            	$('.dev-list').empty();
    	            	getDeviceData(data);
        		}else{
        			tipText('扫码设备不存在,请重新扫码');
            		}
            	}else{
            		tipText(res.message);
            	}
            }
	  });     
 };
  // 点击跟多
  function getMoreDeviceData(){
	  Curpage++;
	  let data={
		custNo:custNo,
		openId:openId,
		merchantId:merchantId,
		page:Curpage,
		rows:rows,
		start:(Curpage-1)*rows,
		merchantType:merchantType,
		status:status,
		shopKeeperType:shopKeeperType,
	  };
	  getDeviceData(data);
  }
 /* 获取数据 */
 function getDeviceData(data) {
	 startShieldLayer();
	 let dataObj={};
	 if(data){
		 dataObj=data;
	 }else{
		 dataObj={
			custNo:custNo,
			openId:openId,
			merchantId:merchantId,
			page:Curpage,
			rows:rows,
			start:0,
			merchantType:merchantType,
			shopKeeperType:shopKeeperType
		 };
	 }
	 $.JsonSRpc(reqUrl+"/wx/myDeviceData",dataObj,function(data){
		finishShieldLayer();
		if(data.result!="success"){			
			return;
		}
		let deviceData=data;
    	$('#devNum').text(deviceData.myTotalNum.devNum);
    	$('#chargerNum').text(deviceData.myTotalNum.chargerNum);
    	$("#more").remove();
    	let deviceList=deviceData.myDeviceInfoBOs;
    	$.each(deviceList,function(i,list){
    		if(list.status==11){// 未上架
    			$('.dev-list').append(
        				'<div class="devinfor">'+
        			       '<h2><span style="color:#F2ACAC;border: 1px solid #F2ACAC; " class="isOn">'+list.statusCn+'</span>设备编号'+list.id+'</h2>'+
        			        '<p><span>设备类型</span>'+list.devDesc+' </p>'+
        			        '<p><span>充电器</span><span title="'+list.chargingStr+'">'+list.chargingStr+'</span> </p>'+
        			        '<p><span>商户名</span>'+list.merchantCn+' </p>'+
        			        '<p><span>加盟商</span>'+list.allianceBusinessCn+'-'+list.allianceBusinessId+' </p>'+
        			        '<p class="collectType"><span>收费描述</span><i class="typeStyle">'+list.deviceFeeDesc+'</i></p>'+
        					'<div class="devinforbtn">'+
        					  '<a class="tradeNum" href="'+reqUrl+'/wx/redirectTradePage?deviceId='+list.id+"&merchantId="+list.oppMerchantId+"&custNo="+custNo+"&isParent="+isParent+'">交易次数:'+list.tradeNum+'</a>'+
        					  '<a class="onlineNum" data-id='+list.id+' onclick="trendDetails(this)" href="javascript:void(0)">使用率趋势</a>'+
        					'</div>'+
        					'<a class="gorow" href="'+reqUrl+'/wx/getShowDevicesDetail?deviceId='+list.id+'&merchantId='+list.oppMerchantId+'"></a>'+
        		       '</div>'
        		);
    		}else{   
    			$('.dev-list').append(
    				'<div class="devinfor">'+
      			        '<h2><span style="color:#22ac38;border: 1px solid #22ac38;" class="isOn">'+list.statusCn+'</span>设备编号'+list.id+'</h2>'+
      			        '<p><span>设备类型</span>'+list.devDesc+' </p>'+
    			        '<p><span>充电器</span><span title="'+list.chargingStr+'">'+list.chargingStr+'</span></p>'+
    			        '<p><span>商户名</span>'+list.merchantCn+' </p>'+
    			        '<p><span>加盟商</span>'+list.allianceBusinessCn+'-'+list.allianceBusinessId+' </p>'+
      			        '<p class="collectType"><span>收费描述</span><i class="typeStyle">'+list.deviceFeeDesc+'</i></p>'+
      					'<div class="devinforbtn">'+
      					  '<a class="tradeNum" href="'+reqUrl+'/wx/redirectTradePage?deviceId='+list.id+"&merchantId="+list.oppMerchantId+"&custNo="+custNo+"&isParent="+isParent+'">交易次数:'+list.tradeNum+'</a>'+
      					  '<a class="onlineNum" data-id='+list.id+' onclick="trendDetails(this)" href="javascript:void(0)">使用率趋势</a>'+
      					'</div>'+
    					'<a class="gorow" href="'+reqUrl+'/wx/getShowDevicesDetail?deviceId='+list.id+'&merchantId='+list.oppMerchantId+'"></a>'+
      		       '</div>'
        		);
    		}
    	});
    	if(Curpage<(deviceData.myTotalNum.devNum/rows)){
    		$(".dev-list").append("<a id='more' href='javascript:void(0)' onclick='getMoreDeviceData()'>点击获取更多</a>");
        }
	 },function(obj){
 		finishShieldLayer();
	});
};

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
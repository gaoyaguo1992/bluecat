//qqhack
//(function(){
//function loaded(){
//	xx.childNodes[0].style.position="";
//	xx.childNodes[0].contentWindow.document.onclick=function(){
//		var dom=this.getElementById("launchBtn");
//		dom.click();
//	}
//}
//var old=document.getElementsByTagName;
//document.getElementsByTagName=function(name){
//	var re=old.call(document,name);
//	if(name=="head"){
//		var oldinsert=re[0].insertBefore;
//		re[0].insertBefore=function(newElement, referenceElement){
//			oldinsert.call(re[0],newElement,referenceElement);
//			if(newElement.src.indexOf("JSONP_CALLBACK")>=0){
//				loaded();
//			}
//		}
//	}else if(name=="script"){
//		console.info(re);
//		return [document.getElementById("qqscript")];
//	}
//	return re;
//}})();
//qqhack end

define(["jquery-debug", baseUrl+"/js/tool/highChars", "util", 'nsts/util/1.0.1/superslide', baseUrl+"/js/tool/setLine", baseUrl+"/js/baidumap/bd.map", "tab"],
	function(require, exports, module) {
		var $ = jQuery = require('jquery-debug');
		require('nsts/util/1.0.1/superslide');
		require(baseUrl+'/js/baidumap/bd.map');
		require('util');
		var setLine = require(baseUrl+"/js/tool/setLine");
		var Tabs = require('tab');
		var objhc = [];
		$(function() {
//			tradSector_btn_click_style();//交易板块按钮点击样式
//			tradSector_head_menu_btn("#tradSectorTwo_head");//交易板块头标题按钮样式滚动样式
//			//tradSector_head_menu_btn("#tradSectorThree_til");//交易板块头标题按钮样式滚动样式
//			tradSector_head_menu_btn("#tradSectorThree_head");//交易板块头标题按钮样式滚动样式
			getMessageE0003();
			//getPriceTrendList();
			//getAvePriceList();
			//                gethotShopList();
			//getCompanyList();
			//                getHotSaleList();
			//                getNewSaleList();
			//                getjilinSaleList();
			//                getbestSaleList();
			//                getSubstandardList();
//			getSaleTradeList();
//			getBuyTradeList();
//			getNoticeList();
			//getTodayPriceList();
			//                deepMachiningList();
			getmessagestyle();
			//                getfinishMachiningList();
			getMapList();
			getQQserviceList();
			$("#price-tend").slide({
				delayTime: 0,
				trigger: "click"
			});
			$("#scroll-data").slide({
				delayTime: 0,
				trigger: "click"
			});
			/*SuperSlide图片切换*/
			$("div.focusBox").slide({
				mainCell: ".pic",
				effect: "fold",
				autoPlay: true,
				delayTime: 600,
				trigger: "click"
			});
			$("#g-list").slide({
				delayTime: 0,
				trigger: "click"
			});
			jQuery("#chart").slide({
				delayTime: 0,
				trigger: "click"
			});
			jQuery("#data").slide({
				effect: "leftLoop"
			});
			jQuery("#com").slide({
				titCell: ".hd ul",
				mainCell: ".bd ul",
				autoPage: true,
				effect: "leftLoop",
				autoPlay: true,
				vis: 5,
				interTime: 4000
			});
			jQuery("#daily-news").slide({
				delayTime: 0,
				trigger: "click"
			});

			changeAttr();//更改主页品种地址
			
			initDate();
		});

		function initDate(){
			var partOne = "";
			var partTwo = "";
			var conThr = "";
			//加载交易公告
			$.JsonRpc(baseUrl + "/PortalInfo/PortalInfo.htm", {functionId:'00005',num:10}, function(data) {
				for(var i=0;i<data.length;i++){
					partOne += "<p><img src=\"image/main/dian.png\"><a class=\"f-thide\" href=\""+baseUrl+"/page/newspage/newsDetailShow.html?recordId=" + data[i].recordId + "\" target=\"_blank\">"+data[i].title+"</a><span class=\"date\">"+data[i].regTime.split(" ")[0]+"</span></p>";
				}
				$("#conOne").empty();
				$("#conOne").append(partOne+'<a style="position: absolute;bottom:0;right:0;margin:10px 20px;" href="page/newspage/newsListPage.html?type_no=00005&main_mark=mark1003" target="_blank" class="more" href="javascript:void(0);">更多>></a>');
			});
			//加载市场行情指数
			$.JsonRpc(baseUrl + "/PortalInfo/getVariety.htm", {}, function(data) {
				for(var i=0;i<data.length;i++){
					if(i==0){
						partTwo += "<span varId=\""+data[i].id+"\" class=\"on\">"+data[i].text+"</span>";
						//加载线图
						initTrendList(data[i].text);
						//加载挂牌信息展示
						initListInfo(data[i].id);
					}else{
						partTwo += "<span varId=\""+data[i].id+"\">"+data[i].text+"</span>";
					}
				}
				$("#conTwo").empty();
				$("#conTwo").empty();
				$("#conTwo").append(partTwo);
				$("#conThr").append(partTwo);
				tradSector_btn_click_style();//交易板块按钮点击样式
				tradSector_head_menu_btn("#tradSectorTwo_head");//交易板块头标题按钮样式滚动样式
				tradSector_head_menu_btn("#tradSectorThree_head");//标题按钮样式滚动样式
			});
			$("#tradSectorThree_til").append("<a href=\""+ebpBaseUrl+"\" target=\"_blank\">进入</a>");
		}
		function getQQserviceList() {
			var htm=[];
			for (var i = 0; i<3; i++) {
				htm.push('<a class="qq'+(i+1)+'"  target="_blank" href="tencent://message/?Menu=yes&amp;uin=938009080&amp;Service=58&amp;SigT=A7F6FEA02730C988965CA2E4E0160D05E29F9EB09589138B697221502787CDB76A82606CE61A016FF8FAF632C6B2DA51EB2C6B29261B142B3BF406BEA64E36A9D60089CE3EFECA4412FA4FE18BF081CF20649864ED6F3B83EFEBABAA13341F563F9AE3EF0FA2CC4C3644340362C974AC1A3F6743846A8604&amp;SigU=30E5D5233A443AB22EA307C01DB050918517B4BFF57BCF71374026C24D2189D720EFF27D99C29FFFB2EF410F215A8C0324326173751CDA24D8C7751ED1CE6D5CA3E3DA74EE8F1A84">\
						<img alt="客服" title="客服" src="'+baseUrl+'/image/f_online.png"></a>');
			}
			$(".call").html(htm.join(''));
		}
		//市场直播
		function getMessageE0003() {
			$.JsonRpc(baseUrl + "/PortalInfo/TradeInfo.htm?functionId=E0003&num=20", {}, function(data) {
				var newSaleList = "<div class=\"thead\"><span class=\"td_1\">品种</span><span class=\"td_2\">报价地</span><span class=\"td_3\">价格类型</span><span class=\"td_4\">价格(元/吨)</span></div>";
				newSaleList=newSaleList+"<div id=\"tt\" style=\"overflow: hidden;height:350px;\"><div id=\"t1\">"
				for (var i = 0; i < data.length; i++) {
					newSaleList += "<p><span class=\"col_1 f-thide\" title="+data[i].variertyName+">" +(data[i].variertyName==null?"":data[i].variertyName)+ "</span><span class=\"col_2 f-thide\">"+ (data[i].originPlace==null?"":data[i].originPlace) +"</span><span class=\"col_3 f-thide\">"+ (data[i].priceType==null?"":data[i].priceType) +"</span><span class=\"col_4 f-thide\" style=\"text-align:center;\">" + (data[i].price==null?"":data[i].price) + "</span></p>"
				}
				newSaleList=newSaleList+"</div><div id=\"t2\"></div></div>"
				$("#E0003").empty();
				$("#E0003").append(newSaleList);
				
	            var timer;var t= document.getElementById("tt"),t1 = document.getElementById("t1"),t2 = document.getElementById("t2");
	            if(!t1){
	                return;
	            }
	            t2.innerHTML=t1.innerHTML;
	            $(t).hover(function(){
	                clearInterval(timer);
	            },function(){
	                timer=setInterval(mar,60);
	            });
	            function mar(){
	                if(t2.offsetTop<=t.scrollTop)
	                    t.scrollTop-=t1.offsetHeight;
	                else t.scrollTop++;
	            }
	            timer=setInterval(mar,60);
			});
		}
		function getMapList() {
			$("#delivery-map").bmap({
				scrollWheelZoom: false,
				minZoom: 4,
				lng: 105.075146,
				lat: 36.783034,
				zoom: 5,
				zoomend:function(param,map){
					if(map.getZoom()>=12){
                        $("#delivery-map").bmap("showMarkers");
                        $("#delivery-map").bmap("clearPJoin",1);
                    }else if(map.getZoom()>=8){
                        $("#delivery-map").bmap("hideMarkers");
                        $("#delivery-map").bmap("clearPJoin");
                        $("#delivery-map").bmap("showPJoin",1);
                    }else{
                        $("#delivery-map").bmap("hideMarkers");
                        $("#delivery-map").bmap("showPJoin");
                        $("#delivery-map").bmap("clearPJoin",1);
                    };
                }});
//			$("#delivery-map").bmap("getBoundary","黑龙江-#ff44ff",{click:function(event){
//                if($("#delivery-map").bmap("getZoom")<8){
//                    $("#delivery-map").bmap("setCenter",event.point);
//                    $("#delivery-map").bmap("setZoom",8);
//                }
//			}});
//			$("#delivery-map").bmap("getBoundary","辽宁-#ff0000",{click:function(event){
//	                if($("#delivery-map").bmap("getZoom")<8){
//	                    $("#delivery-map").bmap("setCenter",event.point);
//	                    $("#delivery-map").bmap("setZoom",8);
//	                }
//	        }});
//	        $("#delivery-map").bmap("getBoundary","长春-#ff4400",{click:function(event){
//	                if($("#delivery-map").bmap("getZoom")<8){
//	                    $("#delivery-map").bmap("setCenter",event.point);
//	                    $("#delivery-map").bmap("setZoom",8);
//	                }
//	        }});
//	        $("#delivery-map").bmap("getBoundary","哈尔滨-#ff44ff",{click:function(event){
//	                if($("#delivery-map").bmap("getZoom")<8){
//	                    $("#delivery-map").bmap("setCenter",event.point);
//	                    $("#delivery-map").bmap("setZoom",8);
//	                }
//	        }});
			var markerData=[
			                {name:"辽宁省",lat:41.731701,lng:123.542151,child:[
			                       {name:"沈阳市",lat:41.731701,lng:123.542151,child:[
			                            {name:"沈阳一号仓",lat:41.758397,lng:123.385774},
			                            {name:"沈阳二号仓",lat:41.830678,lng:123.645636}
			                       ]},
			                       {name:"本溪市",lat:41.305646,lng:123.760619,child:[
			                            {name:"本溪仓库",lat: 41.282231,lng: 123.638737},
			                            {name:"本溪二号仓",lat:41.302178,lng:123.921595}
			                       ]},
			                       {name:"辽阳市", lat:41.309982,lng:123.174205,child:[
			                            {name:"白塔仓库", lat:41.276755,lng:123.179925},
			                            {name:"宏伟仓库", lat:41.221647,lng:123.201197}
			                       ]}
			                ]},
			                {name:"吉林省", lat:43.624964,lng:126.113748,child:[
			                           {name:"吉林市", lat:43.868582,lng:126.632897,child:[
			                                {name:"巴昌仓",lat:43.888336,lng:126.582879},
			                                {name:"丰满仓",lat:43.834257,lng:126.566782}
			                           ]},
			                           {name:"长春市",lat:43.820313,lng:125.330138,child:[
			                                {name:"宽城仓",lat:43.947974,lng:125.333588},
			                                {name:"南关仓",lat:43.867334,lng:125.355435},
			                                {name:"绿园仓", lat:43.884386,lng:125.257699}
			                           ]},
			                           {name:"四平市", lat:43.150732,lng:124.354507,child:[
			                                {name:"铁西仓", lat:43.150732,lng:124.354507}
			                           ]}
			                    ]}
			            ];
			
			function getChildLength(data,opt){
                for(var i=0;i<data.child.length;i++){
                    if(data.child[i].child){
                        getChildLength(data.child[i],opt);
                    }else{
                        opt.num++;
                    }
                }
            }
			var opt={num:0};
            for(var i=0;i<markerData.length;i++){
                opt.num=0;
                getChildLength(markerData[i],opt);
                $("#delivery-map").bmap("addPJoin",{text:markerData[i].name+"("+opt.num+")",click:function(map,point){
                    if($("#delivery-map").bmap("getZoom")<8){
                        $("#delivery-map").bmap("setCenter",point);
                        $("#delivery-map").bmap("setZoom",8);
                    }
                },lat:markerData[i].lat,lng:markerData[i].lng,width:32,height:25},0);
                for(var j=0;j<markerData[i].child.length;j++){
                    opt.num=0;
                    getChildLength(markerData[i].child[j],opt);
                    $("#delivery-map").bmap("addPJoin",{text:markerData[i].child[j].name+"("+opt.num+")",color:"#0044ff",click:function(map,point){
                        if($("#delivery-map").bmap("getZoom")<12){
                            $("#delivery-map").bmap("setCenter",point);
                            $("#delivery-map").bmap("setZoom",12);
                        }
                    },lat:markerData[i].child[j].lat,lng:markerData[i].child[j].lng,width:32,height:25},1);
                    var mks=markerData[i].child[j].child;
                    for(var k=0;k<mks.length;k++){
                         var dd={lat:mks[k].lat,lng:mks[k].lng,icon:baseUrl + "/js/baidumap/redp.png",html:mks[k].name};
                         $("#delivery-map").bmap("addMarker", dd);
                    }
                }
            }
            $("#delivery-map").bmap("hideMarkers");
            $("#delivery-map").bmap("clearPJoin",1);
//			$.JsonRpc(baseUrl + "/BMapService/getMapInfo.htm", {}, function(data) {
//				if (data.errorCode) {
//					message(data.message);
//				} else {
//					data=data.responseInfos;
//					for (var i = 0; i < data.length; i++) {
//						var temp = data[i];
//						$("#delivery-map").bmap("addMarker", {
//							lng: temp.yCoordinate,
//							lat: temp.xCoordinate,
//							icon: baseUrl + "/js/baidumap/redp.png",
//							click: function(mkdata) {
//								$("#delivery-map").bmap("showInfo", mkdata, function(name) {
//									if (name == "视频监控") {
//										if (mkdata.vidio_addr) {
//											window.open(mkdata.vidioAddr);
//										} else {
//											alert("视频地址不存在");
//										}
//									} else if (name == "申请入库") {
//										window.open(baseUrl + "/page/warehouseManage/stockApplyManage.html?fun_id=MENU4200")
//									} else if (name == "物流申请") {
//										window.open(baseUrl + "/page/logistics/logisticsApply.html")
//									}
//								});
//							},
//							clickparam: temp
//						});
//					}
//					$("#delivery-map").bmap("hideMarkers");
//					$("#delivery-map").bmap("addChild");
//				}
//			});		
		}
		var jQuery = require('jquery-debug');
		$('.all-sort-list > .item').hover(
			function() {
				var eq = $('.all-sort-list > .item').index(this), //获取当前滑过是第几个元素
					h = $('.all-sort-list').offset().top, //获取当前下拉菜单距离窗口多少像素
					s = $(window).scrollTop(), //获取游览器滚动了多少高度
					i = $(this).offset().top, //当前元素滑过距离窗口多少像素
					item = $(this).children('.item-list').height(), //下拉菜单子类内容容器的高度
					sort = $('.all-sort-list').height(); //父类分类列表容器的高度

				if (item < sort) { //如果子类的高度小于父类的高度
					if (eq == 0) {
						$(this).children('.item-list').css('top', (i - h));
					} else {
						$(this).children('.item-list').css('top', (i - h));
					}
				} else {
					if (s > h) { //判断子类的显示位置，如果滚动的高度大于所有分类列表容器的高度
						if (i - s > 0) { //则 继续判断当前滑过容器的位置 是否有一半超出窗口一半在窗口内显示的Bug,
							$(this).children('.item-list').css('top', (s - h) + 2);
						} else {
							$(this).children('.item-list').css('top', (s - h) - (-(i - s)) + 2);
						}
					} else {
						$(this).children('.item-list').css('top', 0);
					}
				}

				$(this).addClass('hover');
				$(this).children('.item-list').css('display', 'block');
			},
			function() {
				$(this).removeClass('hover');
				$(this).children('.item-list').css('display', 'none');
			});
		$('.item > .item-list > .close').click(function() {
			$(this).parent().parent().removeClass('hover');
			$(this).parent().hide();
		})
		jQuery(".focusBox").hover(
			function() {
				jQuery(this).find(".prev,.next").stop(true, true)
					.fadeTo("show", 0.2)
			},
			function() {
				jQuery(this).find(".prev,.next").fadeOut()
			});

		function getPriceTrendList() {
			$.JsonRpc(baseUrl + "/PortalInfo/getVariertyType.htm?functionId=E0002&num=5", {}, function(data) {
				var trendList = "";
				for (var i = 0; i < data.length; i++) {
					if (i == 0) {
						trendList = "<span id='" + data[i].variertyName + "' class ='on'>" + data[i].variertyName + "</span>";
					} else {
						trendList = trendList + "<span id='" + data[i].variertyName + "'>" + data[i].variertyName + "</span>";
					}
				}
				$("#pricepicture").empty();
				$("#pricepicture").append(trendList);
				if (data.length > 0) {
					initTrendList(data[0].variertyName);
				}
				$(".quotation .content .pricePic .top span").click(function(){
					$(this).addClass("on").siblings().removeClass("on");
					initTrendList($(this).attr("id"))
				});
				
//				$(".trade .content .top span").click(function(){
//					$(this).addClass("on").siblings().removeClass("on");
//				});
//				$("#tabs ul li").click(function() {
//					$("#tabs ul li").removeClass('on');
//					$(this).addClass('on');
//					initTrendList($(this).attr("id"))
//				});
			});
//			initTrendList("大豆");
		}

		function initTrendList(varietyId) {
			$("#tabShow").empty();
			$("#" + varietyId + "Chars").remove();
			$("#tabShow div").css("display", "none");
			$.JsonRpc(
					baseUrl + "/PortalInfo/TradePricePic.htm?functionId=E0002&num=10", {
						doType: "trendPriceList",
						varietyId: varietyId
					},
					function(data) {
						var priceList = "<div id='" + varietyId + "Chars' style='display:block; width: 100%; height: 250px; margin: 0 auto'></div>";
						var size = data.length;
						var categories = new Array(size);
						var series1 = new Array(size);
						for (var i = 0; i < size; i++) {
							if(i==0){
								categories[0] = data[i].dateId; //numToDate
							}else if(i==size-1){
								categories[1] = data[i].dateId;
							}
							series1[i] = parseFloat(data[i].price);
						}
						var series = new Array(1);
						var inObj = {};
						inObj.name = '趋势';
						inObj.data = series1;
						inObj.color = "#FF6600";
						inObj.type = "spline";
						series[0] = inObj;
						$("#tabShow").append(priceList);
						setLine.setLine(varietyId, categories, series, '元/吨');
					});
		};
		
		function initListInfo(varietyId){
			$.JsonRpc(
					baseUrl + "/PortalInfo/PortalListInfo.htm", {
						varietyId: varietyId,
						num:17
					},
					function(data) {
						$("#conF").empty();
						var partF = "<p class=\"thead\"><span>交易类型</span><span>价格类型</span><span>报价(元/吨)</span><span>挂单总量(吨)</span><span>交货地</span><span>挂牌时间</span></p>";
							partF += "<div id=\"pp\" style=\"overflow: hidden;height:300px;\"><div id=\"p1\">";
						var priceInfo = "";
						for(var i=0;i<data.length;i++){
							if(data[i].listPrice=="议价"){
								priceInfo = data[i].listPrice;
							}else{
								priceInfo ="￥"+data[i].listPrice;
							}
							if(data[i].transDirect=="11"){
								partF += "<p><span class=\"buy\">买入</span><span>"+data[i].deliverWareCn+"</span><span class=\"price\">"+priceInfo+"</span><span>"+data[i].saleQuantity+"</span><span>"+data[i].warehouseName+"</span><span class=\"date\">"+data[i].regTime.split(" ")[0]+"</span></p>";
							}else if(data[i].transDirect=="12"){
								partF += "<p><span class=\"sell\">卖出</span><span>"+data[i].deliverWareCn+"</span><span class=\"price\">"+priceInfo+"</span><span>"+data[i].saleQuantity+"</span><span>"+data[i].warehouseName+"</span><span class=\"date\">"+data[i].regTime.split(" ")[0]+"</span></p>";
							}
						}
						partF +="</div><div id=\"p2\"></div></div>";
						$("#conF").append(partF);
						//滚动效果
						if(data.length>12){
							var timer;var t= document.getElementById("pp"),t1 = document.getElementById("p1"),t2 = document.getElementById("p2");
							if(!t1){
								return;
							}
							t2.innerHTML=t1.innerHTML;
							$(t).hover(function(){
								clearInterval(timer);
							},function(){
								timer=setInterval(mar,60);
							});
							function mar(){
								if(t2.offsetTop<=t.scrollTop+data.length*10)
									t.scrollTop-=t1.offsetHeight;
								else t.scrollTop++;
							}
							timer=setInterval(mar,60);
						}
					});
		}
		/*
		 * zdn添加首页热门商铺加载JS
		 */
		function gethotShopList() {
				$.JsonRpc(baseUrl + "/page/index/hotshopList.htm", {
					doType: "hotshop"
				}, function(data) {
					var hotshopList = "";
					for (var i = 0; i < data.length; i++) {
						if (i == 0) {
							hotshopList = "<li><a href=\"" + data[i].value2 + "\"> <img src=\""+baseUrl+"/image/index/80_80_1.jpg\" alt=\"\" /></a><p> " + "<a href=\"" + data[i].value2 + "\">" + data[i].shop_name + "</a></p> <em>￥" + data[i].value3 + "</em>  <span>销量：" + data[i].value6 + "笔</span>" + "</li>";
						} else {
							hotshopList = hotshopList + "<li><a href=\"" + data[i].value2 + "\"> <img src=\""+baseUrl+"/image/index/80_80_1.jpg\" alt=\"\" /></a><p> " + "<a href=\"" + data[i].value2 + "\">" + data[i].shop_name + "</a></p> <em>￥" + data[i].value3 + "</em>  <span>销量：" + data[i].value6 + "笔</span>" + "</li>";
						}
					}
					$("#hot-goods ul").empty();
					$("#hot-goods ul").append(hotshopList);
					var hotshopList = "";
					for (var i = 0; i < data.length; i++) {
						if (i == 0) {
							hotshopList = "<li><a href=\"" + data[i].value2 + "\"> <img src=\""+baseUrl+"/image/index/shop1.jpg\" alt=\"\" /></a> " + "<a href=\"" + data[i].value2 + "\">" + data[i].shop_name + "</a> <span>销量：" + data[i].value6 + "笔</span>" + "</li>";
						} else {
							hotshopList = hotshopList + "<li><a href=\"" + data[i].value2 + "\"> <img src=\""+baseUrl+"/image/index/shop1.jpg\" alt=\"\" /></a> " + "<a href=\"" + data[i].value2 + "\">" + data[i].shop_name + "</a> <span>销量：" + data[i].value6 + "笔</span>" + "</li>";
						}
					}
					$("#rec ul").empty();
					$("#rec ul").append(hotshopList);

				});
			}
			/*
			 * zdn添加首页知公告
			 */
		function getNoticeList() {
			$.JsonRpc(baseUrl + "/page/index/getNoticeList.htm", {
				doType: "famousCompany"
			}, function(data) {
				var CompanyList = "";
				for (var i = 0; i < data.length; i++) {
					if (i == 0) {
						CompanyList = "<li><a href=\""+baseUrl+"/page/newspage/newsDetailShow.html?seq=" + data[i].seq + "\" target=\"_blank\">" + data[i].value2 + "</a></li>";
					} else {
						CompanyList = CompanyList + "<li><a href=\""+baseUrl+"/page/newspage/newsDetailShow.html?seq=" + data[i].seq + "\" target=\"_blank\">" + data[i].value2 + "</a></li>";
					}
				}
				$("#notice ul").empty();
				$("#notice ul").append(CompanyList);

			});
		}



		/*
		 * zdn添加首页知名企业加载JS
		 */
		function getCompanyList() {
				$.JsonRpc(baseUrl + "/page/index/famousCompanyList.htm", {
					doType: "famousCompany"
				}, function(data) {
					var CompanyList = "";
					for (var i = 0; i < data.length; i++) {
						if (i == 0) {
							CompanyList = " <li><a href=\"" + data[i].value2 + "\"><img src=\""+baseUrl+"/image/index/shop1.jpg\" alt=\"\"></a>" +
								"<div class=\"name\">" + data[i].short_name + "</div><div class=\"num\">营业额：" + data[i].turnover / 10000 + "万元</div><div class=\"star s" + data[i].cust_level + "\"></div></li>";
						} else {
							CompanyList = CompanyList + " <li><a href=\"" + data[i].value2 + "\"><img src=\""+baseUrl+"/image/index/shop1.jpg\" alt=\"\"></a>" +
								"<div class=\"name\">" + data[i].short_name + "</div><div class=\"num\">营业额：" + data[i].turnover / 10000 + "万元</div><div class=\"star s" + data[i].cust_level + "\"></div></li>";
						}
					}
					$("#member ul").empty();
					$("#member ul").append(CompanyList);

				});
			}
			/*
			 * zdn添加首页热销商品列表加载JS
			 */
		function getHotSaleList() {
			$.JsonRpc(baseUrl + "/page/index/hotSaleList.htm", {
				doType: "hotSale"
			}, function(data) {
				var HotSaleList = "";
				var j = 1;
				for (var i = 0; i < data.length; i++) {
					if (i == 0) {
						HotSaleList = "<li><span>" + j + "</span><a href=\"" + data[i].goods_id + "\">" + data[i].origin_place + data[i].level_name + data[i].variety_name + "" + "</a>" + "</li>";
					} else {
						HotSaleList = HotSaleList + "<li><span>" + j + "</span><a href=\"" + data[i].goods_id + "\">" + data[i].origin_place + data[i].level_name + data[i].variety_name + "" + "</a>" + "</li>";
					}
					j++;
				}
				$("#top7 ul").empty();
				$("#top7 ul").append(HotSaleList);

			});
		}

		/*
		 * zdn添加首页激活新品列表加载JS
		 */
		function getNewSaleList() {
			$.JsonRpc(baseUrl + "/page/index/newSaleList.htm", {
				doType: "newSale"
			}, function(data) {
				var newSaleList = "";
				for (var i = 0; i < data.length; i++) {
					if (i == 0) {
						newSaleList = "<a href=\"" + data[i].value2 + "\"> <img src=\""+baseUrl+"/image/index/01.jpg\"/></a> " + "<p class=\"name\">" + data[i].origin_place + data[i].variety_name + "</p>平均价格：<em>￥" + data[i].value3 + "</em></p>" + "<p>总成交量：" + data[i].value6 + "笔</p>";
					}
				}
				$("#newsalemain").empty();
				$("#newsalemain").append(newSaleList);
				var newSaleList = "";
				for (var i = 1; i < data.length; i++) {
					if (i == 1) {
						newSaleList = "<li><a href=\"" + data[i].value2 + "\"> <img src=\""+baseUrl+"/image/index/100_100_3.jpg\" alt=\"\"/></a> " + "<a href=\"\">" + data[i].origin_place + data[i].variety_name + "</a>" + "</li>";
					} else {
						newSaleList = newSaleList + "<li><a href=\"" + data[i].value2 + "\"> <img src=\""+baseUrl+"/image/index/100_100_3.jpg\" alt=\"\"/></a> " + "<a href=\"\">" + data[i].origin_place + data[i].variety_name + "</a>" + "</li>";
					}
				}
				$("#newsale ul").empty();
				$("#newsale ul").append(newSaleList);
			});
		}


		/*
		 * zdn添加首页热卖
		 */
		function getSaleTradeList() {
				$.JsonRpc(baseUrl + "/page/index/getSaleTradeList.htm", {
					doType: "newSale"
				}, function(data) {
					var newSaleList = "";
					for (var i = 0; i < data.length; i++) {
						newSaleList = newSaleList + "<div class=\"single\"><div class=\"bd\">" +
							"<i class=\"sell\">销售</i>" +
							"<a href=\""+baseUrl+"/page/listinfo/listInfoDetail.html?listNo=" + data[i].value2 + "\"><img src=\""+baseUrl+"/image/index/100_100_1.jpg\" alt=\"\" /></a>" +
							"<p><a href=\""+baseUrl+"/page/listinfo/listInfoDetail.html?listNo=" + data[i].value2 + "\">" + data[i].list_title + "</a></p>" +
							"<em>" + data[i].value3 + "</em><span>元/吨</span>" +
							"<dl>" +
							"<dt>品&nbsp;&nbsp;&nbsp;&nbsp;种：</dt><dd>" + data[i].variety_name + "</dd>" +
							"<dt>数&nbsp;&nbsp;&nbsp;&nbsp;量：</dt><dd>" + data[i].value6 + "吨</dd>" +
							"<dt>交收仓库：</dt><dd>" + data[i].warehouse_name + "</dd>" +
							"<dt>最后交收日：</dt><dd>" + data[i].deliver_end_date + "</dd>" +
							"</dl>" +
							"</div></div>";
					}
					$("#SaleTrade").empty();
					$("#SaleTrade").append(newSaleList);
					loop($("#SaleTrade").parent()[0]);
				});
			}
			/*
			 * zdn添加首页求购
			 */
		function getBuyTradeList() {
				$.JsonRpc(baseUrl + "/page/index/getBuyTradeList.htm", {
					doType: "newSale"
				}, function(data) {
					var newSaleList = "";
					for (var i = 0; i < data.length; i++) {
						newSaleList = newSaleList + "<div class=\"single\"><div class=\"bd\">" +
							"<i class=\"buy\">采购</i>" +
							"<a href=\""+baseUrl+"/page/listinfo/listInfoDetail.html?listNo=" + data[i].value2 + "\"><img src=\""+baseUrl+"/image/index/100_100_2.jpg\" alt=\"\" /></a>" +
							"<p><a href=\""+baseUrl+"/page/listinfo/listInfoDetail.html?listNo=" + data[i].value2 + "\">" + data[i].list_title + "</a></p>" +
							"<em>" + data[i].value3 + "</em><span>元/吨</span>" +
							"<dl>" +
							"<dt>品&nbsp;&nbsp;&nbsp;&nbsp;种：</dt><dd>" + data[i].variety_name + "</dd>" +
							"<dt>数&nbsp;&nbsp;&nbsp;&nbsp;量：</dt><dd>" + data[i].value6 + "吨</dd>" +
							"<dt>交收仓库：</dt><dd>" + data[i].warehouse_name + "</dd>" +
							"<dt>最后交收日：</dt><dd>" + data[i].deliver_end_date + "</dd>" +
							"</dl>" +
							"</div></div>";
					}
					$("#BuyTrade").empty();
					$("#BuyTrade").append(newSaleList);
					loop($("#BuyTrade").parent()[0]);
				});
			}
			/*
			 * zdn添加首页当日价格
			 */
		function getTodayPriceList() {

		}
		$.JsonRpc(baseUrl + "/PortalInfo/TradeInfo.htm?functionId=E0003&num=8", {}, function(data) {
			var newSaleList = "<table><thead><tr><td>地区</td><td>品种</td><td>等级</td><td>价格类型</td><td>价格(元/吨)</td><td>走势</td></tr></thead><tbody>";
			for (var i = 0; i < data.length; i++) {
/*				var priceType;
				if(data[i].priceType=='00'){
					priceType="车板价";
				}
				else{
					priceType="平舱价";
				}*/
				newSaleList = newSaleList + "<tr><td>" + data[i].originPlace + "</td>" +
				"<td>" + data[i].variertyName + "</td>" +
				"<td>" + data[i].variertyRank + "</td>" +
				"<td>" + data[i].priceType+ "</td>" +
				"<td>" + data[i].price + "</td>";
				if (data[i].priceTrend >= 0) {
					newSaleList = newSaleList + "<td><span class=\"red\">↑" + parseInt(data[i].priceTrend) + "</span></td></tr>";
				} else {
					newSaleList = newSaleList + "<td><span class=\"gre\">↓" + parseInt(data[i].priceTrend)+ "</span></td></tr>";
				}
			}
			newSaleList = newSaleList +"</tbody></table>"
			$("#todayprice").empty();
			$("#todayprice").append(newSaleList);
		});
		
		/*
		 * zdn添加首页平均价格
		 */
	function getAvePriceList() {

		$.JsonRpc(baseUrl + "/PortalInfo/TradeInfo.htm?functionId=E0006&num=9", {}, function(data) {
			var newSaleList = "<p class=\"lable\">最新平均价格</p><p class=\"thead\"><span class=\"s1\">种类</span><span class=\"s2\">价格(元/吨)</span><span class=\"s3\">涨跌</span></p>";
			for (var i = 0; i < data.length; i++) {
				newSaleList = newSaleList +"<p class=\"tbody\"><span class=\"s1\">" + data[i].variertyName + "</span><span class=\"s2\">" +
					 + data[i].price + "</span>";
					
				if (data[i].priceTrend >= 0) {
					newSaleList = newSaleList + "<span class=\"s3 red\">↑+" + data[i].priceTrend + "</span></p>";
				} else {
					newSaleList = newSaleList + "<span class=\"s3 gre\">↓" + data[i].priceTrend + "</span></p>";
				}
			}
			newSaleList = newSaleList +"</tbody></table>"
			$("#avePrice").empty();
			$("#avePrice").append(newSaleList);
		});
	}
		
		/*
		 * zdn添加首页所有资讯列表加载JS
		 */
		function getmessagestyle(){
//			getMessage("P0003","政策");
//			$(".trade .content .top span").click(function(){
//				$(this).addClass("on").siblings().removeClass("on");
//				getMessage($(this).attr("id"),$(this).attr("name"));
//			});
			getMessage();
			getMessageP0003();
		}
		
		/*
		 * 玉米专栏
		 */
		function getMessage() {
			$.JsonRpc(baseUrl + "/PortalInfo/ParentPortalInfo.htm?functionId=10001&num=9",  {}
			,function(data) {
				var messageList1 = "";

				for (var i = 0; i < data.length; i++) {
					if(i<9){
						if (i == 0) {
							messageList1 = "<p><span class=\"type\">[玉米]</span><span class=\"title\"><a href=\""+baseUrl+"/page/newspage/newsDetailShow.html?recordId=" + data[i].recordId + "\" target=\"_blank\" >"+data[i].title+"</a></span><span class=\"time\">"+(data[i].regTime+'').substr(0,10)+"</span></p>";
						} else {
							messageList1 = messageList1+"<p><span class=\"type\">[玉米]</span><span class=\"title\"><a href=\""+baseUrl+"/page/newspage/newsDetailShow.html?recordId=" + data[i].recordId + "\" target=\"_blank\">"+data[i].title+"</a></span><span class=\"time\">"+(data[i].regTime+'').substr(0,10)+"</span></p>";
						}
					}					
				}
				$("#news1").empty();
				$("#news1").append(messageList1+'<a style="position: absolute;bottom:0;right:0;margin:10px 20px;" href="page/newspage/newsListPage.html?type_no=10001&main_mark=mark1003" target="_blank" class="more" href="javascript:void(0);">更多>></a>');

			});
		}
		
		//行业快报
		function getMessageP0003(id,name) {
			$.JsonRpc(baseUrl + "/PortalInfo/IndexPortalInfo.htm?functionId=10001&num=9",  {}
			,function(data) {
				var messageList2 = "";
				for (var i = 0; i < data.length; i++) {
					if(i<9){
						if (i == 0) {
							messageList2 = "<p><span style=\"width:80px;\" class=\"type\">["+data[i].parentNo+"]</span><span class=\"title\"><a href=\""+baseUrl+"/page/newspage/newsDetailShow.html?recordId=" + data[i].recordId + "\" target=\"_blank\">"+data[i].title+"</a></span><span class=\"time\">"+(data[i].regTime+'').substr(0,10)+"</span></p>";
						} else {
							messageList2 = messageList2+"<p><span style=\"width:80px;\" class=\"type\">["+data[i].parentNo+"]</span><span class=\"title\"><a href=\""+baseUrl+"/page/newspage/newsDetailShow.html?recordId=" + data[i].recordId + "\" target=\"_blank\">"+data[i].title+"</a></span><span class=\"time\">"+(data[i].regTime+'').substr(0,10)+"</span></p>";
						}
					}	
					
				}
				$("#news2").empty();
				$("#news2").append(messageList2+'<a style="position: absolute;bottom:0;right:0;margin:10px 20px;" href="page/newspage/newsListPage.html?model=exclusive&type_no=10001&main_mark=mark1003" target="_blank" class="more" href="javascript:void(0);">更多>></a>');


			});
		}
		
		
		
		
		

		function numToDate(num) {
			return num.substr(2, 2) + "/" + num.substr(5, 3);
		}



		function showinfo(id, index) {
			var obj = $("div.ui-switchable-content .hide").eq(index);
			if (obj.data('hasdata')) {
				return;
			}
			obj.empty();
			obj.append('<div style="text-align:center;"><div class="ui-loading">100%</div></div>');
			$.JsonRpc(baseUrl + "/page/listinfo/newsearchListInfo.htm?showType=all&varietyType=" + id, {}, function(data) {
				obj.empty();
				var html = [];
				html.push('<table class="ui-table ui-table-inbox">');
				html.push('<thead><tr><th class=t1>交易类型</th><th class=t2>年份</th><th class=t3>等级</th><th class=t4>价格类型</th><th class=t5>报价(元/吨)</th><th class=t6>挂单总量(吨)</th>');
				html.push('<th class=t7>已成交量(吨)</th><th class=t8>交收地</th><th class=t9>操作</th></tr></thead>');
				html.push('</table>');
				html.push('<div class=tt id="tt' + index + '"><div id="t1' + index + '"><table class="ui-table ui-table-inbox">');
				for (var i = 0; i < data.listInfo.length; i++) {
					var v = data.listInfo[i];
					html.push('<tr><td class=t1>' + (v.trans_direct === '11' ? '<span style="color:green;">买入</span>' : '<span style="color:red;">卖出</span>') + '</td><td class=t2>' + v.goods_year + '</td><td class=t3>' + v.rank + '</td>');
					html.push('<td class=t4>' + v.pricetype + '</td><td class=t5><b class="num">￥' + v.dj + '</b></td><td class=t6>' + v.gdl + '</td><td class=t7>' + v.ycjl + '</td><td class=t8>' + v.deliver_ware + '</td><td class=t9><a  href=""+baseUrl+"/page/listinfo/listInfoDetail.html?listNo=' + $.trim(v.list_no) + '" target="_blank">查看</a></td>');
					html.push('</tr>');
				}
				html.push('</table></div><div id="t2' + index + '"></div></div>');
				obj.data('hasdata', true).append(html.join('\n'));
				if (data.listInfo.length <= 6) {
					return;
				}
				var timer;
				var t = document.getElementById("tt" + index),
					t1 = document.getElementById("t1" + index),
					t2 = document.getElementById("t2" + index);
				t2.innerHTML = t1.innerHTML;
				t.scrollTo = t.scrollTop + 100;
				$(t).hover(function() {
					clearInterval(timer);
				}, function() {
					timer = setInterval(mar, 60);
				});

				function mar() {
					if (t2.offsetTop <= t.scrollTop) {
						t.scrollTop -= t1.offsetHeight;
					} else {
						t.scrollTop++;
					}
				}
				timer = setInterval(mar, 60);
			})
		}

		function loop(target) {
			$(target).css({
				position: "relative",
				height: 219
			});
			$(target).addClass("jsloop");
			var w = target.offsetWidth;
			var l = $($(target).children()[0]).children().length;
			var pagenum = Math.ceil(l / 4);
			var current = 0;
			if (pagenum > 1) {
				var pn = $("<div class=pagenext></div>").appendTo($(target)).css({
					opacity: 0
				}).click(function() {
					current++;
					if (current === pagenum) {
						current = 0;
					}
					ul.children().removeClass("on");
					$(ul.children()[current]).addClass("on");
					$($(target).children()[0]).animate({
						marginLeft: -current * w + 1
					}, 200);
				});
				var pr = $("<div class=pageprev></div>").appendTo($(target)).css({
					opacity: 0
				}).click(function() {
					current--;
					if (current < 0) {
						current = pagenum - 1;
					}
					ul.children().removeClass("on");
					$(ul.children()[current]).addClass("on");
					var $c = $($(target).children()[0]);
					$c.animate({
						marginLeft: -current * w + current
					}, 200);
				});
				var ul = $("<ul class=hd></ul>").appendTo($(target));
				for (var i = 0; i < pagenum; i++) {
					$("<li></li>").attr("index", i).appendTo(ul).click(function() {
						var index = parseInt($(this).attr("index"));
						current = index;
						var $c = $($(target).children()[0]);
						ul.children().removeClass("on");
						$(this).addClass("on");
						$c.animate({
							marginLeft: -current * w + current
						}, 200);
					});
				}
				$(ul.children()[current]).addClass("on");
				var $c = $($(target).children()[0]);
				$(target).mouseenter(function() {
					pn.animate({
						opacity: 0.3
					}, 500);
					pr.animate({
						opacity: 0.3
					}, 500);
				}).mouseleave(function() {
					pn.animate({
						opacity: 0
					}, 500);
					pr.animate({
						opacity: 0
					}, 500);
				});
			}
		}
		/**
		 * 更改主页品种属性
		 */
		function changeAttr(){
			$('.item').find('a').each(function(i,o){
				var href = $(this).attr('href');
				$(this).attr('href',href);
			});
		}
		
		/**
		 * 交易板块按钮点击样式
		 */
		function tradSector_btn_click_style(){
			$(".tradSector .til span").click(function(){
				$(this).addClass("on").siblings().removeClass("on");
			});
			$("#conTwo span").click(function(){
				if($(this).attr("varId")!=$("#conTwo>span[class='on']").attr("varId")){
					initTrendList($(this).text());
				}
				$(this).addClass("on").siblings().removeClass("on");
//				initTrendList("大豆");
			});
			$("#tradSectorThree_head span").click(function(){
				if($(this).attr("varId")!=$("#conThr>span[class='on']").attr("varId")){
					initListInfo($(this).attr("varId"));
				}
				$(this).addClass("on").siblings().removeClass("on");
			});
		}
		
		/**
		 * 交易模块头标题按钮样式滚动样式
		 */
		function tradSector_head_menu_btn(tradSectorId){
			var move_menu_width=100;//本来是0,5个品种时最后一个被覆盖显示不出来
			for(var i=0;i<$(tradSectorId+" .move_menu span").length;i++){
				move_menu_width += $(tradSectorId+" .move_menu span:eq("+i+")").width() + parseInt($(tradSectorId+" .move_menu span:eq("+i+")").css("padding-left")) + parseInt($(tradSectorId+" .move_menu span:eq("+i+")").css("padding-right")) + parseInt($(tradSectorId+" .move_menu span:eq("+i+")").css("margin-left")) + parseInt($(tradSectorId+" .move_menu span:eq("+i+")").css("margin-right"));
			}
			$(tradSectorId+" .move_menu").width(move_menu_width);
			if($(tradSectorId+" .move_menu").width()>$(tradSectorId+"").width()){
				$(tradSectorId+" .move_left,"+tradSectorId+" .move_right").css("display","block");
				$(tradSectorId+" .move_menu").css("left",$(tradSectorId+" .move_left").width());
				var move_k = 0;
				var move_mark="";
				$(tradSectorId+" .move_right").click(function(){
					if((parseInt($(tradSectorId+" .move_menu").css("left")) + parseInt($(tradSectorId+" .move_menu").width()) + $(tradSectorId+" .move_right").width())>$(tradSectorId+"").width()){
						if(move_mark=="left"){
							move_k++
						}
						if((move_k+1) < $(tradSectorId+" .move_menu span").length){
							$(tradSectorId+" .move_left").attr("src","image/main/left1.png")
							$(tradSectorId+" .move_left").css("cursor","pointer");
							
							var change_width = $(tradSectorId+" .move_menu span:eq("+move_k+")").width() + parseInt($(tradSectorId+" .move_menu span:eq("+move_k+")").css("padding-left")) + parseInt($(tradSectorId+" .move_menu span:eq("+move_k+")").css("padding-right")) + parseInt($(tradSectorId+" .move_menu span:eq("+move_k+")").css("margin-left")) + parseInt($(tradSectorId+" .move_menu span:eq("+move_k+")").css("margin-right"));
							//$(tradSectorId+" .move_menu").animate({left:parseInt($(tradSectorId+" .move_menu").css("left"))-change_width},"normal");
							$(tradSectorId+" .move_menu").css("left",parseInt($(tradSectorId+" .move_menu").css("left"))-change_width);
							move_k++;
							move_mark="right";
//							var change_width1 = $(tradSectorId+" .move_menu span:eq("+move_k+")").width() + parseInt($(tradSectorId+" .move_menu span:eq("+move_k+")").css("padding-left")) + parseInt($(tradSectorId+" .move_menu span:eq("+move_k+")").css("padding-right")) + parseInt($(tradSectorId+" .move_menu span:eq("+move_k+")").css("margin-left")) + parseInt($(tradSectorId+" .move_menu span:eq("+move_k+")").css("margin-right"));
							if((parseInt($(tradSectorId+" .move_menu").css("left")) + parseInt($(tradSectorId+" .move_menu").width()) + $(tradSectorId+" .move_right").width())<=$(tradSectorId+"").width()){
								$(tradSectorId+" .move_right").attr("src","image/main/right2.png");
								$(tradSectorId+" .move_right").css("cursor","default");
							}
						}
					}
				});
				
				$(tradSectorId+" .move_left").click(function(){
					if(move_mark=="right"){
						move_k--
					}
					if(move_k>=0&&move_mark!=""){
						$(tradSectorId+" .move_right").attr("src","image/main/right1.png")
						$(tradSectorId+" .move_right").css("cursor","pointer");
						
						var change_width = $(tradSectorId+" .move_menu span:eq("+move_k+")").width() + parseInt($(tradSectorId+" .move_menu span:eq("+move_k+")").css("padding-left")) + parseInt($(tradSectorId+" .move_menu span:eq("+move_k+")").css("padding-right")) + parseInt($(tradSectorId+" .move_menu span:eq("+move_k+")").css("margin-left")) + parseInt($(tradSectorId+" .move_menu span:eq("+move_k+")").css("margin-right"));
						//$(tradSectorId+" .move_menu").animate({left:parseInt($(tradSectorId+" .move_menu").css("left"))+change_width},"normal");
						$(tradSectorId+" .move_menu").css("left",parseInt($(tradSectorId+" .move_menu").css("left"))+change_width);
						move_k--;
						move_mark="left";
						if(move_k<0){
							$(tradSectorId+" .move_left").attr("src","image/main/left2.png");
							$(tradSectorId+" .move_left").css("cursor","default");
						}
					}
				});
			}
		}
	});



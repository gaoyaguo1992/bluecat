define("nsts/page/1.0.1/page-debug", ["jquery-debug", "widget", "util", "urlencode", 'url', 
    'arale/popup/1.1.6/popup', 'arale/autocomplete/1.3.0/autocomplete','sticky','dialog'], 
                                      function(require, exports, module) {
    var $ = require("jquery-debug");
    var Widget = require("widget");
    var util = require("util");
    var url = require("url");
    var urlencode = require("urlencode");
    var Popup = require('arale/popup/1.1.6/popup');
    var AutoComplete = require('arale/autocomplete/1.3.0/autocomplete');
    var sticky = require('sticky');
    var Dialog = require('dialog');
    function Page() {
        Widget.autoRenderAll();
    }
    Page.prototype.init = function() {
        _pagefix();
        _init_topbar();
        _init_index_page();
        _init_sailmenu();
        _init_down_menu();
        _init_search();
        _init_search_type();
        //_init_index_button();
        _init_getUrlParam();
    }
    module.exports = Page;
    
    function _pagefix(){
        var url = window.location + '';
        if ($("#page").length != 0) {
            sticky.fix("#page");
        }
        if ($("#header_fd").length === 0) {
            return false;
        }
        $(window).scroll(function(){
            if($(document).scrollTop()>=180){
            	$("#header_fd").slideDown();
                sticky.fix("#header_fd");
            }else{
            	$("#header_fd").slideUp();
            }
        });
    }
//    顶部信息栏
    function _init_topbar() {
    	var sellUrl = ebpBaseUrl +"/page/listinfo/listChooseInfo.html?type=sell";
        var buyUrl = ebpBaseUrl +"/page/listinfo/listChooseInfo.html?type=buy";
        var currUrl = document.URL.split("?")[0];
//        if (typeof(currUrl) == 'undefined' || currUrl == 'http://' + location.host + '/ebp/page/login/login.html') {
        	 if (typeof(currUrl) == 'undefined' || currUrl == baseUrl+'/page/login/login.html') {
//            currUrl = 'http://' + location.host + '/ebp/page/suplinfo/suplinfo.html';
            currUrl = baseUrl+'/page/suplinfo/suplinfo.html';
        }
        currUrl = urlencode.encode(currUrl);
        //校验登录状态开始
        $.JsonRpc(baseUrl + "/userLogin/checkUser.htm", {}, function(response) {
        	var data = response.responseInfo;
        	var role = data.role;
            //var msgmap = data[1];
            var msgmap = {};//临时的
            msgmap.msgs='';//临时的
//            var trademap = data[2];
            var trademap = {};
            var status = data.status;
            if (status == true) {//已登录
                //var leftMsg = data[1].leftMsg;
                var leftMsg = {};
                leftMsg.commodityCount = '';//临时的
                leftMsg.isIssueCount = '';//临时的
                leftMsg.signListCount = '';//临时的
                leftMsg.unCompleteFormCount = '';//临时的
                $("#userinfozone").empty();
                $("#userinfozone").html('<LI class="userinfo menu-item nb">' +
                        '您好,' + data.userName + 
                        '&nbsp;</LI><li class="menu-item last" style="margin-left:2px;cursor:pointer"><a class="signout">[退出]</a></li>');
                //用户中心
         	   var userCenterHtml="";
                userCenterHtml+="                                      <div class=menu>";
                userCenterHtml+="                                              <A class=\"menu-hd\" href=\""+ebpBaseUrl+"/page/usercenter/usercenter.html?fun_id=MENU1200\" target=_blank rel=nofollow>用户中心<B></B></A> ";
                userCenterHtml+="                                              <div class=menu-bd>";
                userCenterHtml+="                                                      <div class=menu-bd-panel>";
                userCenterHtml+="                                                              <div>";
                userCenterHtml+="                                                                      <A href=\""+ebpBaseUrl+"/page/listinfo/orderproList_1.html?fun_id=MENU1210\" target=_blank rel=nofollow>执行中合同</A>";
                userCenterHtml+="                                                                      <A href=\""+ebpBaseUrl+"/page/contract/contract.html\" target=_blank rel=nofollow>合同管理</A> ";
                userCenterHtml+="                                                                      <A href=\""+ebpBaseUrl+"/page/logistics/logisticsRecord.html\" target=_blank rel=nofollow>物流明细</A> ";
                userCenterHtml+="                                                                      <A href=\""+ebpBaseUrl+"/page/order/orderFollow.html?fun_id=MENU1500\" target=_blank rel=nofollow>我的关注</A> ";
                userCenterHtml+="                                                                      <A href=\""+ebpBaseUrl+"/page/trade/scanHist.html\" target=_blank rel=nofollow>浏览历史</A> ";
                //userCenterHtml+="                                                                      <A href=\"../mycommodity/myCommodity.html?fun_id=MENU3100\" target=_top rel=nofollow>已有商品</A> ";
                userCenterHtml+="                                                              </div>";
                userCenterHtml+="                                                      </div>";
                userCenterHtml+="                                              </div>";
                userCenterHtml+="                                      </div>";
                $('#user_account').html(userCenterHtml);
                //站内信息
                var insideLetterHtml = "<div class=menu>" +
                "<A class=menu-hd href=\""+ebpBaseUrl+"/page/message/message.html\" target=_top rel=nofollow>站内信息<strong style='color:#ff3333;' id='msgs'>&nbsp;</strong><B></B></A> " +
                "<div class=menu-bd>" +
                "<div class=menu-bd-panel>" +
                "<div class='egrain-message'>" +
                "<div class=p1><A href=\""+ebpBaseUrl+"/page/message/message.html\" target=_top rel=nofollow><strong style='color:#ff3333;' id='msgs0'>0</strong>条系统信息</A></div>" +
                "<div class=p2><A href=\""+ebpBaseUrl+"/page/message/message.html\" target=_top rel=nofollow><strong style='color:#ff3333;' id='msgs1'>0</strong>条交易提醒</A></div>" +
                "</div></div></div></div>";
                $('#insideLetter').html(insideLetterHtml);
                $('#logistics').empty();
                $('#logistics').attr("class","menu-hd");
                $('#logistics').html("<A href=\""+ebpBaseUrl+"/page/couponDetails/couponDetails.html?initPath=/conpon/getTitles.htm\" target=\"_blank\" rel=\"nofollow\">优惠券历史</A>");
                for (var key in msgmap) {
                    $('#' + key).text(msgmap[key]);
                }
                for (var key in trademap) {
                    $('#' + key).text(trademap[key]);
                }
                $("#site-nav .enter .menu-bd").width($("#site-nav .enter .menu-hd").width()+32);
                //退出登录开始
                $(".signout").click(function() {
                    $.JsonRpc(baseUrl + "/userLogin/userLogout.htm", {}, function(data) {
                        if (data[0].status == true) {
//                        	   window.location = 'http://' + location.host + '/ebp/page/login/login.html';
                            window.location.href = baseUrl+'/page/login/login.html';
                        } else {
                            alert("退出出错！");
                        }
                    });
                });
                //退出登录结束
                //企业中心主页设置
                if ($('#hdmenu').length !== 0) {
                    if (parseInt(msgmap.msgs) == 0) {
                        $('#hdmenumsg').hide();
                    } else {
                        $('#hdmenumsg').text(msgmap.msgs);
                        $('#hdmenumsg').show();
                    }
                }
               //修改登录和注册按钮位置展示
               $(".btn-login").remove();
               $(".btn-reg").remove();
               var ss = [];
                ss.push("<a class=\"linkuc\" href=\""+ baseUrl+"/page/usercenter/usercenter.html?fun_id=MENU1200\">进入用户中心</a>");
                ss.push("<div class=\"pro\"><a  href=\""+ baseUrl+"/page/listinfo/orderproList_1.html?fun_id=MENU1210\"><span>执行中的合同</span><em>"+leftMsg.unCompleteFormCount+"</em></a></div>");
                ss.push("<div class=\"msg\"><a href=\""+baseUrl+"/page/message/message.html\"><span>消息</span><em>"+msgmap.msgs+"</em></a></div>");
                $(".bg").append(ss.join("\n"));
                var url = window.location + '';
                if (window.isIndexTag && data.needModPass) {
                	init_modpwd_window(1);//临时的
                }
                /*
                 * 登陆后去掉 登陆与注册按钮
                 */
                //$(".btn-top").remove();
                $("#userinfozone").append('<LI class=\"last\"><iframe width="280" scrolling="no" height="20" style="margin-top:-1px;" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=34&color=RGB(102,102,102)&icon=1&num=3"></iframe></LI>');
                
                //点击 我要买我要卖判断角色(是否交易员)
                if(role=="交易员"){
                	if($(".btn-want-sell").length!=0){
                		 $(".btn-want-sell").attr("href",ebpBaseUrl+"/page/listinfo/listChooseInfo.html?type=sell");
                 		$(".btn-want-buy").attr("href",ebpBaseUrl+"/page/listinfo/listChooseInfo.html?type=buy");
                	}
                }else{
                	if($(".btn-want-sell").length!=0){
	             	   $(".btn-want-sell,.btn-want-buy").click(function(){
	             		   alert("对不起,您不是交易员不能挂单!");
	             		   return false;
	             	   });
                	}
                }
            } else {//未登录
                $("#userinfozone").empty();
                $("#userinfozone").html('<LI class=\"last\"><A href=\"\" id=\"login\">登录</A></LI><LI class=\"last\"><A href=\"'+baseUrl+'/page/register/register.html\">注册</A> </LI>');
                $("#userinfozone").append('<LI class=\"last\"><iframe width="280" scrolling="no" height="20" style="margin-top:-1px;" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=34&color=RGB(102,102,102)&icon=1&num=3"></iframe></LI>');
                $("#login").attr('href', baseUrl+'/page/login/login.html?url=' + currUrl);
                $(".btn-want-sell").attr("href",ebpBaseUrl+"/page/listinfo/checkListinfoLogin.htm?type=sell&url="+urlencode.encode(sellUrl));
                $(".btn-want-buy").attr("href",ebpBaseUrl+"/page/listinfo/checkListinfoLogin.htm?type=buy&url="+urlencode.encode(buyUrl));
            }
            /**
             * 初始化密码修改窗口
             * flag:1表示是使用默认密码强制修改窗口,其他或undefined表示自主修改密码
             */
            function init_modpwd_window(flag){
            	var cb = new Dialog({
            		zIndex:'999999999',
            		height:'500px',
            		width:'600px',
            		closeTpl:'',
                    title: '提示',
                    content: baseUrl+'/page/main/modpwd.html?flag='+flag,
                    parentNode: document.body,
                    cancelTpl: ''
                }).show();
            }
            $('#modpwdclick').click(function(){
            	init_modpwd_window();
            });
          
        });
        //校验登录状态结束
        _baidu_share();
    }
//    原用户中心右面菜单
    function _init_sailmenu() {
        if ($('div.mysail_menu').length === 0) {
            return false;
        }
        var fun_id = $.url.param("fun_id");
        $.JsonRpc(baseUrl + "/userMenuController/getUserMenu.htm?fun_id=" + fun_id, {time: new Date().getTime()}, function(data) {
            var leftMenu = [];
            leftMenu.push("<dl>");
            $(data.menu).each(function(i, v) {
                var childrenMenu = v.childrenMenu;
                leftMenu.push("<dt class=\"mysail_jmenu\"><i class=\"ico" + v.id + "\"></i>" + v.name + "<a class=\"mysail_slide_up\" href=\"javascript:void(0);\"></a></dt>");
                leftMenu.push(" <dd>");
                leftMenu.push(" <ul>");
                $(childrenMenu).each(function(j, m) {
                    leftMenu.push("<li><i class=\"myicon\"></i><a href=\"" +(baseUrl+ m.link)+ "\" id=\"" + m.id + "\">" + m.name + "</a></li>");
                });
                leftMenu.push("  </ul>");
                leftMenu.push(" </dd>");
            });
            leftMenu.push("</dl>");
            $(".mysail_menu_module").eq(0).empty();
            $(".mysail_menu_module").eq(0).append(leftMenu.join("\n"));
            initMenuShow();
        });
    }
    function initMenuShow() {
        //判断来自哪个跳转页 选中当前菜单小类和大类
        var fun_id = $.url.param("fun_id");
        if (fun_id != 'undefined' && fun_id != null && $.trim(fun_id) != '') {//传过来功能id 选中功能id 不传默认选中第一个
            var curr_mysail_jmenu = $("#" + fun_id).parent().parent().parent().prev();
            $(".mysail_jmenu").not(curr_mysail_jmenu).each(function(i, v) {
                //默认展开第一个菜单
                if (fun_id.indexOf("SUPDEM") != -1) {
                    if (i != 0) {
                        $(this).next().hide();
                    }
                } else {
                    $(this).next().hide();
                }
                $(this).children("a").addClass("mysail_slide_down");
            });
            $("#" + fun_id).parent().addClass("mysail_menu_select");
        } else {
            //打开第一个菜单
            $(".mysail_jmenu").not(":first").each(function(i, v) {
                $(this).next().hide();
                $(this).children("a").addClass("mysail_slide_down");
            });

        }
        $(".mysail_jmenu").click(function() {
            var obj = $(this);
            if (obj.next().is(":visible")) {
                obj.next().hide();
                obj.children("a").addClass("mysail_slide_down");
            } else {
                obj.children("a").removeClass("mysail_slide_down").addClass("mysail_slide_up");
                obj.next().show();
            }
        });
        $(".mysail_jmenu").next().children().children().find("a").click(function() {
            $(this).addClass("mysail_menu_select");
            $(".mysail_jmenu").next().children().children().find("a").not($(this)).removeClass("mysail_menu_select");
        });
    }
    function _init_index_page() {
        $(".i-search .text").each(function() {
            var thisVal = $(this).val();
            //判断文本框的值是否为空，有值的情况就隐藏提示语，没有值就显示
            if (thisVal != "") {
                $(this).siblings("span").hide();
            } else {
                $(this).siblings("span").show();
            }
            //聚焦型输入框验证 
            $(this).focus(function() {
                $(this).siblings("span").hide();
            }).blur(function() {
                var val = $(this).val();
                if (val != "") {
                    $(this).siblings("span").hide();
                } else {
                    $(this).siblings("span").show();
                }
            });
        })
    }
    function _init_down_menu() {
        if (window.downMenuTag) {
        	$("#categorys,.all-sort-list").hover(function(){
            	$(".all-sort-list").css("display","block");
            },function(){
            	$(".all-sort-list,.all-sort-list").css("display","none");
            });
        }
    }
    function _init_search_type() {
        var urlSeachType = $.url.param('seachType');
        var url = window.location + '';
        if ($("#seachType").length === 0) {
            return;
        }
    }
    function _baidu_share() {
    	return false;//暂时注释掉
        var url = window.location + '';
        if (url.indexOf('page/login/login') !== -1) {
            return;
        }
        window._bd_share_config = {
            "common": {"bdSnsKey": {}, "bdText": "", "bdMini": "2", "bdMiniList": false, "bdPic": "", "bdStyle": "0", "bdSize": "16"},
            "slide": {"type": "slide", "bdImg": "0", "bdPos": "left", "bdTop": "180.5"}
        };
        with (document)
            0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src =
                    'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
    }
    //初始化搜索栏
    function _init_search() {
        seajs.use(baseUrl+'/css/alice/select/select.css');
        var auto = new AutoComplete({
            header: '<div class="{{classPrefix}}-header"></div>',
            html: '<strong style="float:left;">{{{title}}}</strong><span style="float:right;">{{num}}</span>',
            width: 328,
            trigger: '#global_search',
            //template: $('#auto-template').html(),
            dataSource: function(query, done) {
                //获取请求查询类型
                var type = $("#seachType i").attr("type");
                var that = this;
                if (type == undefined || type == 'undefined' || type == '') {
                    type = 'GD';
                }
                if($.trim(query)!=''){
                	query = query.replace("'","");
                }
                $.ajax({
                	url:baseUrl + '/globalSearchController/getSearchDataAuto.htm',
                	method:'post',
                	data:{type:type,keyword:query},
                	dataType: 'json',
                	success:function(data){
                		done(data);
                	}
                });
                return false;
            }
        }).render();
        auto.on('itemSelected', function(data, item) {
            //alert(data.label);
        }).on('indexChanged', function(current, prev) {
            //alert(this.items[current]);
        });
        //点击搜索
        $(".global_search+input").click(function() {
            doSearch($(this).prev().val());
        });
        function doSearch(keword) {
            var type = $("#seachType i").attr("type");
            type = "GD";
            if (type == 'GD') {//挂单
                var url = ebpBaseUrl + "/page/listinfo/listSearchShowInfo.html?seachType=" + type + "&type=global_search&keyword=" + keword;
                window.open(url);
            } 
        }
        //热门搜索
        if ($(".hot-s").length !== 0) {
            $.JsonRpc(baseUrl + "/PortalInfo/TradeInfo.htm?functionId=E0001&num=4", {}, function(data) {
                var hotkeys = "";
                var type = $("#seachType i").attr("type");
                //var url= baseUrl + "/page/shop/shopShowInfo.html?seachType="+type+"&type=global_search&keyword=";
                var url= baseUrl + "/page/listinfo/listSearchShowInfo.html?seachType="+type+"&type=global_search&keyword=";
                for (var i = 0; i < data.length; i++) {
                    hotkeys = hotkeys + "<a href="+url+data[i].recordId+" target='_blank'>" + data[i].searchKeywords
                            + "</a>";
                }
                hotkeys = "热门搜索：" + hotkeys;
                $(".hot-s").empty();
                $(".hot-s").append(hotkeys);
            });
        }
    }
    function _init_index_button(){
        if (window.isIndexTag) {
            $(".btn-top").show();
        }
    }
    function _init_getUrlParam(){
    	if ($("#nav-items").length === 0) {
            return;
        }
    	if(window.main_mark!=undefined&&window.main_mark!="undefined"){
    		$("#"+window.main_mark).addClass("curr").siblings().removeClass("curr");
    	}
    }
});

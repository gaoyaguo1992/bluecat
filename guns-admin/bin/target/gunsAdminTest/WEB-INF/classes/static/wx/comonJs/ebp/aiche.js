
String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
};

$.JsonSRpc = function(url, data, succFunc, error, async) {
    data = data || {};
    succFunc = succFunc || function() {
    };
    error = error || function() {
    };
    $("#mask").show();
    $.ajax({
        url: url,
        type: "POST",
        datatype: "JSON",
        timeout: 180000,
        data: data,
        async: async != false,
        success: function(obj) {
        	$("#mask").hide();
            if (typeof (obj) == "object") {
                if (obj.successful !== false) {
                    succFunc(obj);
                } else {
                    message(obj.resultHint);
                }
            } else {
                var response = $.parseJSON(obj);
                succFunc(response);
            }
        },
        complete: error
    });
}

function formatterOpt(dicCode,dicOptCode){
	var optName;
	var optReq= reqUrl+"?app=optionDicService&func=getOptionFromCache";
	$.JsonSRpc(optReq,{"dicCode":dicCode},function(data){
		optName = data[dicOptCode];
	},"error",false);
	return optName;
}

function formatDate(date){
	return date.substring(0,4) + "-" + date.substring(4,6) + "-" + date.substring(6);
}

function getDFT(v) {
    var d = new Date(parseInt(v));
    return d.getFullYear() + "-" + fmn(d.getMonth() + 1) + "-" + fmn(d.getDate()) + " " + fmn(d.getHours()) + ":" + fmn(d.getMinutes()) + ":" + fmn(d.getSeconds());
}
function getTFD(v, bool) {
    var d = new Date();
    d.setYear(v.substring(0, 4));
    d.setMonth(parseInt(v.substring(5, 7)) - 1);
    d.setDate(v.substring(8, 10));
    if (bool)
        d.setHours(24);
    else
        d.setHours(0);
    d.setMinutes(0);
    d.setSeconds(0);
    return d.getTime();
}
function fmn(num) {
    if ((num + "").length === 1) {
        return "0" + num;
    }
    return num;
}

/**
 * 金额，手机号码校验
 * @param obj
 * @param flg
 * @returns Boolean
 */
function Validate (obj,flg){
	var amt = /^([1-9][\d]{0,15}|0)(\.[\d]{1,2})?$/; //金额匹配校验
	var phone = /^([1][3578][\d]{9})$/; //手机号匹配校验
	var telephone= /^(0\d{2,3}-\d{8})$/; //固定电话匹配校验
	
	switch(flg)
	{
		case "A":
			return amt.test(obj.val())==true?true:false;
			break;
		case "P":
			return phone.test(obj.val())==true?true:(telephone.test(obj.val())==true?true:false);
			break;
	}
}

var messageList = [];
var show = false;
setInterval(function() {
    if (show) {
        return;
    }
    if (messageList.length > 0) {
        var obj = messageList.splice(0, 1)[0];
        show = true;
        createMessage(obj);
    }
}, 10);

function message(html, func, type) {
    if (html && func && type) {
        messageList.push({html: html, func: func, type: type});
    } else if (html && func && !type) {
        messageList.push({html: html, func: func});
    } else if (html && !func && !type) {
        messageList.push({html: html});
    }
}

function createMessage(v) {
	document.addEventListener("touchmove",function(event){
		if(show){
			event.preventDefault();
		}
	});
    $(document.body).append("<div id='msgMask' style='position:fixed;left:0;right:0;top:0;bottom:0;background-color:#fff;z-index:1111;filter:alpha(Opacity=0);-moz-opacity:0;opacity: 0;'></div>");
    $(document.body).append("<div id='msgContent' style='width:300px;position:absolute;background-color:#fff;color:#000;font-size:12px;border:1px solid #ccc;border-top-left-radius:10px;border-top-right-radius:10px;z-index: 1112;text-align:center;filter:alpha(Opacity=90);-moz-opacity:0.9;opacity: 0.9;'>" +
            "<div style='height:40px;border-bottom:1px solid #ccc;line-height:40px;font-size:20px;font-weight:bold;background-color:#F1F1F1;border-top-left-radius:10px;border-top-right-radius:10px;'>" + document.title + "</div>" +
            "<div style='text-align:center;padding:15px;font-size:16px;word-break:break-all'>" + v.html + "</div>" +
            "<div id='message_btn' style='width:100%;display:table;height:40px;line-height:40px;border-top:1px solid #ccc;font-size:16px;cursor:pointer;-webkit-user-select: none'>" +
            "</div>" +
            "</div>");
    if (v.type) {
        var btn = "<a href='javascript:void(0)' style='background-color:#00bb9c;display:table-cell;text-decoration:none;color:#fff'>确定</a>";
            $("#message_btn").html(btn);
            $("#message_btn").find("a:eq(0)").click(function() {
                $("#msgMask").remove();
                $("#msgContent").remove();
                v.func(true);
                show = false;
            });
            $("#message_btn").find("a:eq(1)").click(function() {
                $("#msgMask").remove();
                $("#msgContent").remove();
                v.func(false);
                show = false;
            });
    } else {
        if (v.func) {
            var btn = "<a href='javascript:void(0)' style='background-color:#00bb9c;display:table-cell;width:50%;text-decoration:none;color:#fff'>确定</a><a href='javascript:void(0)' style='background-color:#ff8355;display:table-cell;text-decoration:none;color:#fff'>取消</a>";
            $("#message_btn").html(btn);
            $("#message_btn").find("a:eq(0)").click(function() {
                $("#msgMask").remove();
                $("#msgContent").remove();
                v.func(true);
                show = false;
            });
            $("#message_btn").find("a:eq(1)").click(function() {
                $("#msgMask").remove();
                $("#msgContent").remove();
                v.func(false);
                show = false;
            });
        } else {
            var btn = "<a href='javascript:void(0)' style='background-color:#00bb9c;display:table-cell;text-decoration:none;color:#fff'>确定</a>";
            $("#message_btn").html(btn);
            $("#message_btn").find("a:eq(0)").click(function() {
                $("#msgMask").remove();
                $("#msgContent").remove();
                show = false;
            });
        }
    }
    $("#msgContent").css("left", ($(window).width() - $("#msgContent").width()) / 2);
    $("#msgContent").css("top", ($(window).height() - $("#msgContent").height()) / 2);
    $("#msgContent").css("display", "block");
}
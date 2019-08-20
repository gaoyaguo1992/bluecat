define("nsts/util/1.0.1/util-debug", ["jquery-debug"], function(require, exports, module) {
    var $ = jQuery = require("jquery-debug");
    var urlencode = require("urlencode");
    //json ajax请求
    jQuery.extend(
            {
                /**
                 * url         : 请求的链接(servlet)
                 * param       : 请求包含的参数
                 * successFunc : 响应回调函数
                 * async       : 是否使用异步方式传输(默认为true)
                 * errorFunc   : 错误处理函数
                 */
            	JsonRpc: function(url, param, successFunc, async,errorFunc)
                {
                    $.ajax({
                        url: url,
                        type: "POST",
                        dataType: "json",
                        data: param,
                        async: async != false,
                        success: function(obj) {
                            if (typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length) {
                                successFunc(obj);
                            } else {
                                successFunc(obj);
                            }
                        },
                        error : function(XMLHttpRequest,textstatus){
                        	if(errorFunc)
                        	errorFunc(XMLHttpRequest,textstatus);  
                        } 
                    });
                },
                /**
                 * 使用本方法如果ajax请求后台出错跳到统一的异常显示页面
                 * url         : 请求的链接(servlet)
                 * param       : 请求包含的参数
                 * successFunc : 响应回调函数
                 * async       : 是否使用异步方式传输(默认为true)
                 */
                JsonEpc: function(url, param, successFunc, async)
                {
                    $.ajax({
                        url: url,
                        type: "POST",
                        dataType: "json",
                        data: param,
                        async: async != false,
                        success: function(obj) {
                            if (typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length) {
                                successFunc(obj);
                            } else {
                                successFunc(obj);
                            }
                        },
                        error : function(XMLHttpRequest,textstatus){
                        	var isBizEx = XMLHttpRequest.getResponseHeader("isBizEx");
                        	if(isBizEx==='true'){
                        		window.location = baseUrl + '/page/error/bizerror.html';
                        	}else if(isBizEx==='false'){
                        		window.location = baseUrl + '/page/error/syserror.html';
                        	}
                        } 
                    });
                },
                goResultPage:function(flag,errorCode,message,url,isOpen){
               	 flag=urlencode.encode(flag);
               	 message=urlencode.encode(message);
               	 var _url;
               	 if(url==undefined){
               		 _url=baseUrl+"/page/result/resultShow.html";
               	 }else{
               		 _url=url;
               	 }
               	 if($.trim(isOpen)=="open"){
               		 window.open(baseUrl+_url+"?flag="+flag+"&message="+message);  
               	 }else{
               		 window.location=baseUrl+_url+"?flag="+flag+"&message="+message;  
               	 }
               	 }
            });
    //分页插件
    jQuery.extend(
            {
                /**
                 * jq         : 分页栏放置jq对象
                 * total       : 总记录数
                 * rows       : 每页显示多少条
                 * jumpPageFun ：点具体页或者跳页执行函数 (page,rows)
                 * currPage : 当前页 默认1 默认第几页选中
                 */
                pagination: function(jq, total,rows, jumpPageFun,currPage) {
                    if(currPage==undefined&&total>0){
                        currPage = 1;
                    }
                    var totalPage = Math.ceil(total / rows);
                    var pageHtml = $.getCurrentPagebar(totalPage, currPage);
                    jq.empty();
                    jq.append(pageHtml);
                    //若总数为0 返回
                    if(parseInt(total)<=0){
                        return false;
                    }
                    //给当前跳转位置赋值
                    jq.children().find(".ui-paging-which").children("input").val(currPage);
                    jq.children().find(".pitem").each(function(i, v) {
                        if ($(this).text() == currPage) {
                            $(this).addClass("ui-paging-current pitem");
                            return false;
                        }
                    });
                    //点击具体页面时候跳转
                    jq.children().find(".pitem").click(function() {
                        var curr_pageNum = $(this).text();
                         jq.children().find(".ui-paging-which").children("input").val(curr_pageNum);
                        $.pagination(jq, total, rows, jumpPageFun,curr_pageNum);
                        jumpPageFun($(this).text(), rows);
                    });
                    //点击上一页
                    jq.children().find(".ui-paging-prev").click(function() {
                        var curr_pageNum = 1;
                        jq.children().find(".pitem").each(function() {
                            if ($(this).hasClass("ui-paging-current")) {
                                curr_pageNum = $(this).text();
                                return false;
                            }
                        });
                        var prevPage = (parseInt(curr_pageNum) - 1) > 0 ? (parseInt(curr_pageNum) - 1) : 1;
                        $.pagination(jq, total, rows, jumpPageFun,prevPage);
                        jumpPageFun(prevPage, rows);
                    });
                    //点击下一页
                    jq.children().find(".ui-paging-next").click(function() {
                        var curr_pageNum = 1;
                        jq.children().find(".pitem").each(function() {
                            if ($(this).hasClass("ui-paging-current")) {
                                curr_pageNum = $(this).text();
                                return false;
                            }
                        });
                        var nextPage = (parseInt(curr_pageNum) + 1) >= totalPage ? parseInt(totalPage) : (parseInt(curr_pageNum) + 1);
                        $.pagination(jq, total, rows, jumpPageFun,nextPage);
                        jumpPageFun(nextPage, rows);
                    });
                    //点击跳转
                    jq.children().find(".ui-paging-info").click(function() {
                        var jump_pageNum = jq.children().find(".ui-paging-which").children("input").val();
                        //判断是否为空 和是否为数字
                        var reg = new RegExp("^[0-9]*$");
                        if (($.trim(jump_pageNum) == '') || (!reg.test(jump_pageNum))) {
                            jump_pageNum = 1;
                        }
                        //判断是否大于最后一页
                        if (parseInt(jump_pageNum) >= totalPage) {
                            jump_pageNum = totalPage;
                        }
                        $.pagination(jq, total, rows, jumpPageFun,jump_pageNum);
                        jumpPageFun(jump_pageNum, rows);
                    });
                },
                getCurrentPagebar: function(totalPage, current) {
                    if(parseInt(totalPage)<=0){
                        var ss = [];
//                        ss.push("<div class='nodata' style=\"width:100%;\">");
//                        ss.push("adsfasdf");
//                        ss.push("<div class=\"ui-tipbox-icon\">");
//                        ss.push("<i class=\"iconfont\" title=\"提示\">&#xF046;</i>");
//                        ss.push("</div>");
//                        ss.push("<div class=\"ui-tipbox-content-simple\" style=\"text-align:left;\">");
//                        ss.push("<h3 class=\"ui-tipbox-title\">暂无数据</h3>");
//                        ss.push("</div>");
//                        ss.push("<image src='"+baseUrl+"/image/nodata.gif' style='width:40%;;height:120px;'></image>");
//                        ss.push("</div>");
                        
                        return ss.join("\n");
                    }
                    var pagebar = [];
                    pagebar.push("<div class=\"ui-paging\" >");
                    pagebar.push("<a href=\"javascript:void(0);\" class=\"ui-paging-prev\">");
                    pagebar.push("<i class=\"iconfont\" title=\"左三角形\">&#xF039;</i> 上一页");
                    pagebar.push("</a>");
                    //判断页数是否大于6 
                    if (parseInt(current) <= 6) {
                        var num = (parseInt(totalPage) <= (parseInt(current) + 2)) ? parseInt(totalPage) : (parseInt(current) + 2);
                        for (var i = 1; i <= num; i++) {
                            pagebar.push("<a href=\"javascript:void(0);\" class=\"ui-paging-item pitem\">" + i + "</a>");
                        }
                        if (parseInt(totalPage) > (parseInt(current) + 2)) {
                            pagebar.push("<span class=\"ui-paging-ellipsis\">...</span>");
                        }
                    } else {
                        pagebar.push("<a href=\"javascript:void(0);\" class=\"ui-paging-item pitem\">1</a>");
                        pagebar.push("<a href=\"javascript:void(0);\" class=\"ui-paging-item pitem\">2</a>");
                        if ((parseInt(current) + 6) >= parseInt(totalPage)) {
                            pagebar.push("<span class=\"ui-paging-ellipsis\">...</span>");
                            var last_num = [(parseInt(current) - 2), (parseInt(current) - 1), (parseInt(current))];
                            for (var i = 0; i < last_num.length; i++) {
                                pagebar.push("<a href=\"javascript:void(0);\" class=\"ui-paging-item pitem\">" + last_num[i] + "</a>");
                            }
                            for (var i = 1; i <= (parseInt(totalPage) - parseInt(current)); i++) {
                                pagebar.push("<a href=\"javascript:void(0);\" class=\"ui-paging-item pitem\">" + (parseInt(current) + i) + "</a>");
                            }
                        } else {
                            pagebar.push("<span class=\"ui-paging-ellipsis\">...</span>");
                            var show_num = [(parseInt(current) - 2), (parseInt(current) - 1), parseInt(current), (parseInt(current) + 1), (parseInt(current) + 2)];
                            for (var i = 0; i < show_num.length; i++) {
                                pagebar.push("<a href=\"javascript:void(0);\" class=\"ui-paging-item pitem\">" + show_num[i] + "</a>");
                            }
                            pagebar.push("<span class=\"ui-paging-ellipsis\">...</span>");
                        }
                    }
                    pagebar.push("<a href=\"javascript:void(0);\" class=\"ui-paging-next\">");
                    pagebar.push("下一页 <i class=\"iconfont\" title=\"右三角形\">&#xF03A;</i>");
                    pagebar.push("</a>");
                    pagebar.push("<span class=\"ui-paging-info\"><span class=\"ui-paging-bold\">" + current + "/" + totalPage + "</span>页</span>");
                    pagebar.push("<span class=\"ui-paging-which\"><input name=\"some_name\" value=\"\" type=\"text\"></span>");
                    pagebar.push("<a class=\"ui-paging-info ui-paging-goto\" href=\"javascript:void(0);\">跳转</a>");
                    pagebar.push("</div>");
                    return pagebar.join("\n");
                }
            });
    function Util() {
    }
    //路径 参数 html控件的id 默认值 回调函数
    Util.prototype.initFilter = function(url, param, targetID, defaultValue,fn) {
        if(!$('#'+targetID)||$('#'+targetID)[0].tagName !== 'SELECT'){
            return;
        }
        url = baseUrl+'/htmlSelect/'+url;
        $.JsonRpc(url, param, function(data) {
            $('#'+targetID).empty();
            $("#" + targetID).append("<option value=\"\">--请选择--</option>");
            if (defaultValue != 'undefined') {
                for (var i = 0; i < data.length; i++) {
                    if ($.trim(defaultValue) == $.trim(data[i].codeId)) {
                        $("#" + targetID).append("<option value=\"" + data[i].codeId + "\" selected>" + data[i].codeValue + "</option>");
                    } else {
                        $("#" + targetID).append("<option value=\"" + data[i].codeId + "\">" + data[i].codeValue + "</option>");
                    }
                }
            } else {
                for (var i = 0; i < data.length; i++) {
                    $("#" + targetID).append("<option value=\"" + data[i].codeId + "\">" + data[i].codeValue + "</option>");
                }
            }
            if(fn){
                fn();
            }
        });
    }
    Util.prototype.formatNull=function(sourceStr,replaceString){
    	if(sourceStr === "" || sourceStr === null || sourceStr === undefined||sourceStr=='null'){
    		if(replaceString!=null&&replaceString!=undefined){
    			return replaceString;
    		}else{
    			return "";
    		}
    	}else{
    		return sourceStr;
    	}
    }
    module.exports = new Util();
});
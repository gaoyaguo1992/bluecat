
//增加遮盖层方法
jQuery.extend({
    showLoadMsg: function(wrap, msg) {
        $("<div class=\"datagrid-mask\"></div>").css({
            display: "block",
            width: '100%',
            height: '100%'
        }).appendTo(wrap);
        $("<div class=\"datagrid-mask-msg\" style=\"font-size:12px;\"></div>").html((msg) ? msg : '数据处理中，请稍等...').appendTo(wrap).css({
            display: "block",
            left: (wrap.width() - $("div.datagrid-mask-msg", wrap).outerWidth()) / 2,
            top: (wrap.height() - $("div.datagrid-mask-msg", wrap).outerHeight()) / 2
        });
    },
    hideLoadMsg: function(wrap) {
        wrap.find("div.datagrid-mask-msg").remove();
        wrap.find("div.datagrid-mask").remove();
    }
});
/**增加popWin方法
 * url路径 t标题 w宽 h高 fn点击确定回调函数
 * 使用方法:
 * $.popWin('main.html','标题');
 * 或者参数用json形势
 * $.popWin({fn:function(){},url:'main.html'}),如果用json形势，参数不分先后顺序和个数;
 */
jQuery.extend({
    popWin: function(url, t, w, h, fn) {
        var id = 'dialog' + new Date().getTime();
        $("<div id='" + id + "'></div>").appendTo("body");
        var options = {
            url: '',
            title: '管理驾驶舱系统',
            width: 550,
            height: 300,
            fn: function() {
                $('#' + id).dialog('close');
            }
        };
        if (arguments.length === 1 && typeof arguments[0] === 'object') {
            $.extend(options, arguments[0]);
        } else {
            $.extend(options, {
                url: url,
                title: t,
                width: w,
                height: h,
                fn: fn
            });
        }
        $('#' + id).dialog({
            title: options.title,
            width: options.width,
            height: options.height,
            cache: false,
            content: '<iframe src=' + options.url + ' style="width:100%;height:100%;" frameborder="0"></iframe>',
            modal: true,
            buttons: [{
                    text: '确定',
                    iconCls: 'icon-ok',
                    handler: function() {
                        options.fn();
                        $('#' + id).dialog('close');
                    }
                }, {
                    text: '取消',
                    iconCls: 'icon-cancel',
                    handler: function() {
                        $('#' + id).dialog('close');
                    }
                }],
            onClose: function() {
                $('#' + id).remove();
            }
        });
    }
});
/**
 * 统一消息弹出框
 * @param msg
 * @param title
 */
function message(msg,title){
	console.log($.fn.window);
    $.fn.window.defaults.zIndex=30002;
    title = title || "消息提示";
    if (typeof title === "string")
        $.messager.alert(title, msg, 'info');
    else if(typeof title==='function')
        $.messager.confirm("消息提示", msg, title);
}
/**
 * 
 * @param {string} msg 要显示的消息内容 如果msg里面含有%s 会被time替换 
 *                     如果不含有会在末尾加上（time秒后关闭）
 * @param {number} time 对话倒计时关闭时间 单位秒，默认为5秒
 * @param {string} title 对话框标题 不存在默认为 消息提示
 * @returns {undefined}
 * 例子：
 *   autoCloseMessage("这是%s秒后自动关闭的对话框",5,"自动关闭对话框");
 *   autoCloseMessage("这是自动关闭的对话框",5);
 *   autoCloseMessage("这是自动关闭的对话框");
 *   autoCloseMessage("这是%s秒后自动关闭的对话框");
 */
function autoCloseMessage(msg,time,title){
    time=time||5;
    if(time<=0)
        return message(msg,title);
    var id="time_"+new Date().getTime();
    function msgProgress(msg,bool){
        var result;
        if(msg.indexOf("%s")>=0)
            result=msg.replace("%s",time);
        else
            result=msg+"("+parseInt(time)+"秒后关闭)";
        if(bool)
            result="<span id='"+id+"'>"+result+"</span>";
        return result;          
    };
    title = title || "消息提示";
    var timer;
    var win=$.messager.alert(title, msgProgress(msg,true), 'info',function(){
        if(timer)clearTimeout(timer);
    });
    function timeGo(){
        time-=1;
        if(time<=0){
            win.window("destroy");
            return;
        }
        $("#"+id).html(msgProgress(msg));
        timer=setTimeout(timeGo,1000);
    };
    timer=setTimeout(timeGo,1000);
}
/**
 * 将form表单序列化成json对象
 * 如: $('form').serializeJson();
 */
$.fn.serializeJson=function(){  
    var serializeObj={};  
    var array=this.serializeArray();  
    var str=this.serialize();  
    $(array).each(function(){  
        if(serializeObj[this.name]){  
            if($.isArray(serializeObj[this.name])){  
                serializeObj[this.name].push(this.value);  
            }else{  
                serializeObj[this.name]=[serializeObj[this.name],this.value];  
            }  
        }else{  
            serializeObj[this.name]=this.value;   
        }  
    });  
    return serializeObj;  
};  
function isNotEmpty(content){
	if(content == 'null' || content == null || content =='undefined' || content == undefined || $.trim(content) == '')
		{return false;}
	else
		{return true;}
}


/** 
 * 时间戳格式化函数
 * @param  {string} format    格式 
 * @param  {int}    timestamp 要格式化的时间 默认为当前时间 
 * @return {string}           格式化的时间字符串 
 */
function dateToStrLongFormat(timestamp){  
	var date = new Date(timestamp);//直接用 new Date(时间戳) 格式转化获得当前时间
	return date.dateToStrShortFormtbyFmt("yyyy-MM-dd HH:mm:ss"); //再利用拼接正则等手段转化为yyyy-MM-dd HH:mm:ss 格式
}
/** 
 * 时间戳格式化函数 
 * @param  {string} format    格式 
 * @param  {int}    timestamp 要格式化的时间 默认为当前时间 
 * @return {string}           格式化的时间字符串 
 */
function dateToStrShortFormt(timestamp){  
	var date = new Date(timestamp);//直接用 new Date(时间戳) 格式转化获得当前时间
	return date.dateToStrShortFormtbyFmt("yyyy-MM-dd"); //再利用拼接正则等手段转化为yyyy-MM-dd HH:mm:ss 格式
}


Date.prototype.dateToStrShortFormtbyFmt = function (fmt) {
     var o = {
         "M+": this.getMonth() + 1, //月           
         "d+": this.getDate(), //日           
         "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //时           
         "H+": this.getHours(), //小时           
         "m+": this.getMinutes(), //分           
         "s+": this.getSeconds(), //秒           
         "q+": Math.floor((this.getMonth() + 3) / 3), //季          
         "S": this.getMilliseconds() //毫秒           
     };
     var week = {
         "0": "\u65e5",
         "1": "\u4e00",
         "2": "\u4e8c",
         "3": "\u4e09",
         "4": "\u56db",
         "5": "\u4e94",
         "6": "\u516d"
     };
     if (/(y+)/.test(fmt)) {
         fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
     }
     if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f" : "\u5468") : "") + week[this.getDay() + ""]);
     }
     for (var k in o) {
         if (new RegExp("(" + k + ")").test(fmt)) {
             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
         }
     }
     return fmt;
}


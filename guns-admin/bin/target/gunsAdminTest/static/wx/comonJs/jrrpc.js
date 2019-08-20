
jQuery.extend(
{
	/**
	 * @see 将Javascript数据类型转换为JSON字符串
	 * @param 待转换对象,支持Object,Array,String,Function,Number,Boolean,Regexp
	 * @return 返回JSON字符串
	 */
    toJSON : function (object) {
        var type = typeof object;
        if (object != null && 'object' == type) {
            if (Array == object.constructor)
                type = 'array';
            else if (RegExp == object.constructor)
                type = 'regexp';
        }
        
        switch(type) {
            case 'undefined':
            case 'unknown':
                return;
                break;
            case 'function':
            case 'boolean':
            case 'regexp':
                return object.toString();
                break;
            case 'number':
                return isFinite(object) ? object.toString() : 'null';
                break;
            case 'string':
                var str = object.replace(/(\\|\")/g,"\\$1").replace(/\n|\r|\t/g,
                    function(){
                        var a = arguments[0];
                        return  (a == '\n') ? '\\n': (a == '\r') ? '\\r': (a == '\t') ? '\\t': "";
                    });
                
                return '"' + Url.encode(str) + '"';
                break;
            case 'object':
                if (object === null) return 'null';
                var results = [];
                for (var property in object) {
                    
                    var value = $.toJSON(object[property]);
                    if (value !== undefined)
                        results.push($.toJSON(property) + ':' + value);
                }
                return '{' + results.join(',') + '}';
                break;
            case 'array':
                var results = [];
                for(var i = 0; i < object.length; i++)
                {
                    var value = $.toJSON(object[i]);
                    if (value !== undefined) results.push(value);
                }
                return '[' + results.join(',') + ']';
                break;
        }
    }

});

/**
 * 定义与后台传输的报文格式，此格式与DispatchServlet中的报文解析格式匹配，
 * 如果此处被修改，应修改DispatchServlet中对应的报文解析部分
 * 
 * app : 请求调用的类名
 * func: 请求调用的方法名
 * data: 请求包含的数据对象
 */
function dataArea(app,func,data)
{
    this.app = app;
    this.func = func;
    if (data){
    	this.data = data;
    } else {
        this.data = new Object();
    }
}

jQuery.extend(
{
	/**
	 * url         : 请求的链接(servlet)
	 * param       : 请求包含的参数
	 * successFunc : 响应回调函数
	 * async       : 是否使用异步方式传输(默认为true)
	 */
    JsonRpc : function(url, param, successFunc, async,errorFunc)
    {    	
        $.ajax({
        	url: baseUrl+'/'+param.app+'/'+param.func,
            type:"POST",
            dataType: "json",
            data: param.data,
            async: async != false,
            success: function(obj){
            	$.hideLoadMsg($('body'));
        		if(typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length){
        			successFunc(obj);
        		}else{
        			successFunc(obj);
        		}
            },
        	error: function(obj){
        		$.hideLoadMsg($('body'));
        		if(errorFunc!=null&&errorFunc!=undefined){
            		errorFunc(obj);        			
        		}
        	}
        });
    },
    JsonSRpc : function(url, param, successFunc, async,errorFunc)
    {
        $.ajax({
            url: url,
            type:"POST",
            dataType: "json",
            data: param,
            async: async != false,
            success: function(obj){
        		if(typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length){
        			successFunc(obj);
        		}else{
        			successFunc(obj);
        		}
            },
        	error: function(obj){
        		if(errorFunc!=null&&errorFunc!=undefined){
            		errorFunc(obj);        			
        		}
        	}
        });
    },
   
});
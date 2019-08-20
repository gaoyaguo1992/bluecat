
// 定义String对象原型函数，去除两侧空白。用法：' abc '.trim()
String.prototype.trim = function () {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

// 定义String对象原型函数，去除左侧空白。用法：' abc'.ltrim()
String.prototype.ltrim = function () {
	var whitespace = new String(" \t\n\r");
    var j=0, i = this.length;
    while (j < i && whitespace.indexOf(this.charAt(j)) != -1)
    {
        j++;
    }
    return this.substring(j, i);
};

// 定义String对象原型函数，去除右侧空白。用法：'abc '.rtrim()
String.prototype.rtrim = function () {
	var whitespace = new String(" \t\n\r");
    var i = this.length - 1;
    while (i >= 0 && whitespace.indexOf(this.charAt(i)) != -1)
    {
        i--;
    }
    return this.substring(0, i+1);
};


// 定义String对象原型函数，判断字符串结尾。用法：'abc'.endWith('c')
String.prototype.endWith=function(oString){  
	var reg = new RegExp(oString+"$");  
	return reg.test(this);     
}  

//判断是否为整数。用法：'23'.isInteger()
String.prototype.isInteger=function()
{
    var re = /\d*/i; //\d表示数字,*表示匹配多个数字
    var r = this.match(re);
    return r==this;
}

//判断是否为数字（整数或小数）。用法：'23'.isNumber()
String.prototype.isNumber=function()
{
    return !isNaN(this);
}

String.prototype.strlen=function(){
    return this.replace(/[^\x00-\xff]/g, "rr").length;
}

String.prototype.sub=function() {
    var r = /[^\x00-\xff]/g;
    if (this.replace(r, "mm").length <= n) return this;
    var m = Math.floor(n / 2);
    for (var i = m; i < this.length; i++) {
        if (this.substr(0, i).replace(r, "mm").length >= n) {
                return this.substr(0, i);
        }
    }
    return this;
}
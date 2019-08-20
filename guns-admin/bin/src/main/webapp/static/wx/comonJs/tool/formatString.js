define([ "jquery-debug" ], function(require, exports,
		module) {
	var $ = require("jquery-debug");
	exports.formatString = function(source,result) {
		var source = $.trim(source);
		if(source==""||source==null||source=='null'||source==undefined){
			return result;
		}else{
			return source;
		}
	}
});

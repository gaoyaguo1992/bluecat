define("nsts/util/1.0.1/format", [ "jquery-debug" ], function(require,
		exports, module) {
	var $ = require("jquery-debug");
	var fm = {};
	fm.formatDate = function(dateString){
		if(dateString!=""&&dateString!=null){
			dateString = dateString.substr(0,10);
			dateString = dateString.replace("-","年").replace("-","月")+"日";
		}
		
		return dateString;
	}

    fm.formatTelNo= function (TelNo){
	var begin=TelNo.substring(0,3);
	var end=TelNo.substring(9,11);
	return begin+"******"+end;
}
    module.exports = fm;
});


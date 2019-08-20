define([ "jquery-debug" ], function(require, exports,module) {
	var $ = require("jquery-debug");
	$.fn.setCheck=function (lineElements) {
		var checkAll = $(this);
		$(this).click(function(){
			var boolean =this.checked;
			$(lineElements).prop("checked",boolean);
		});
		
		$(lineElements).click(function(){
        		$(checkAll).prop("checked",isAllChecked());
			});
			
			
			function isAllChecked(){
		    	for(var i = 0;i<$(lineElements).length;i++){
		    		if(lineElements[i].checked==false){
		    			return false;
		    		}
		    	}
		    	return true;
		    }
	}
		});
		

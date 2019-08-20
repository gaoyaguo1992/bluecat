define("../js/selectUtil/select", [ "jquery-debug" ], function(require, exports,
		module) {
	var $ = require("jquery-debug");
	$.fn.setSelect=function (sqlName,id,text,defaultId,codeType,errormessage) {
		var oldItemId = $(this).attr("id");
		var newItemId = oldItemId+"-new";
		$(this).attr("id",newItemId);
		var oldItem = $("#"+newItemId);
		$.ajax({
			type:"post",
			dataType:"json",
			data:{sqlName:sqlName,codeType:codeType},
			url:baseUrl+"/filter/getSelect.htm",
			success:function(data){
				data = eval(data);
				var newItem = "<select name='"+oldItemId+"' id='"+oldItemId+"'"; 
				if(errormessage!=""&&errormessage!=null){
					newItem=newItem+"required data-errormessage-required='"+errormessage+"'>";
				}
				for(var i=0;i<data.length;i++){
					var dataRow = data[i];
					var opItem ="";
					if($.trim(defaultId)==dataRow[id]){
					    opItem = " <option value='"+dataRow[id]+"' selected='selected'>";
					}else{
						opItem = " <option value='"+dataRow[id]+"'>";
					}
				     opItem = opItem+dataRow[text]+"</option>";
					newItem = newItem+opItem;
				}
				newItem = newItem+"</select>";
				$(newItem).insertAfter(oldItem);
				$(oldItem).remove();
			}
		});
	   }
});

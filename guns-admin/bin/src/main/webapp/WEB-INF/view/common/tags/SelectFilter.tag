@/*
    选择查询条件标签的参数说明:

    name : 查询条件的名称
    id : 查询内容的input框id
@*/
<div class="input-group">
    <div class="input-group-btn">
	    <select class="form-control"  id="${id}"  style="${styleForSelect}">
	        ${tagBody!}
	    </select>
    </div>
    <input class="form-control" id="${idForValue}" 
               @if(isNotEmpty(styleForValue)){
                    style="${styleForValue}"
               @}
        >
</div>
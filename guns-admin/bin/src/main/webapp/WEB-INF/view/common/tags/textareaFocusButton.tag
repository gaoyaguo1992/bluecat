@/*
    表单中input框标签中各个参数的说明:
    id : input框id
    name : input框名称
    readonly : readonly属性
    changeEventFun : 点击事件的方法名
    onBlurFun: 失去焦点事件
    onFocusFun:得到焦点事件 
    style : 附加的css属性
    styleGroup:样式..
    styleLabel:Label样式
@*/
@var spaceCss = "";
@var btnType = "";
@if(isEmpty(space) || space == "false"){
@   spaceCss = "";
@}else{
@   spaceCss = "button-margin";
@}
@if(isEmpty(btnCss)){
@   btnType = "primary";
@}else{
@   btnType = btnCss;
@}
<div class="form-group"
      @if(isNotEmpty(styleGroup)){
           style="${styleGroup}"
      @}
     >
    <label class="col-sm-3 control-label "  style="width:20%;margin-top:30px;">${name}</label>
    <div class="col-sm-9" style="width:70%;padding-left:5px;padding-right:5px">
        <textarea class="form-control"  type="text" id="${id}" name="${id}" 
              @if(isNotEmpty(cols)){
              	 cols="${cols}"
              @}
              @if(isNotEmpty(rows)){
              	 rows="${rows}"
              @}
              @if(isNotEmpty(value)){
                   value="${tool.dateType(value)}"
              @}
              @if(isNotEmpty(value)){
                   value="${tool.dateType(value)}"
              @}
              @if(isNotEmpty(readonly)){
                   readonly="${readonly}"
              @}
              @if(isNotEmpty(changeEventFun)){
                   onchange="${changeEventFun}"
              @}
              @if(isNotEmpty(onBlurFun)){
              		onBlur="${onBlurFun}"
              @}
              @if(isNotEmpty(onFocusFun)){
              		onFocus="${onFocusFun}"
              @}
              @if(isNotEmpty(style)){
                   style="${style}"
              @}
              @if(isNotEmpty(disabled)){
                   disabled="${disabled}"
              @}
        ></textarea>
    </div>
    <div class="col-sm-9" style="width:10%;padding-left:1px;padding-right:1px;padding-top:20px;">	    
		<button type="button" class="btn btn-${btnType} ${spaceCss}"  id="${idBtn}"
		   @if(isNotEmpty(styleBtn)){
			 onclick="${clickFunBtn!}" 
		   @}		   
		   @if(isNotEmpty(styleBtn)){
		     style="${styleBtn}"
		   @}
		><i class="fa ${icon}"></i>&nbsp;${nameBtn}
		</button>
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}



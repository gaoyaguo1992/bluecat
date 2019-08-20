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
<div class="form-group"
      @if(isNotEmpty(styleGroup)){
           style="${styleGroup}"
      @}
     >
    <label class="col-sm-3 control-label"
      @if(isNotEmpty(styleLabel)){
           style="${styleLabel}"
      @}
      >${name}</label>
    <div class="col-sm-9">
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
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}



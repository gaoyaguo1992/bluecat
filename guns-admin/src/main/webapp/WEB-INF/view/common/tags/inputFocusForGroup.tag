@/*
    表单中input框标签中各个参数的说明:

    hidden : input hidden框的id
    id : input框id
    name : input框名称
    readonly : readonly属性
    changeEventFun : 点击事件的方法名
    onBlurFun: 失去焦点事件
    onFocusFun:得到焦点事件 
    style : 附加的css属性
    styleForLabel: label对应样式
    styleForInput: label对应样式
    
@*/
<div class="form-group" style="margin-bottom:0px;">
    <label class="col-sm-3 control-label"
      @if(isNotEmpty(styleForLabel)){
         style="${styleForLabel}"
      @}
    >${name}</label>
    <div class="col-sm-9"
      @if(isNotEmpty(styleForInput)){
         style="${styleForInput}"
      @}
    >
        <input class="form-control" type="text" id="${id}" name="${id}"
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
               autocomplete="off"
        >
        @if(isNotEmpty(hidden)){
            <input class="form-control" type="hidden" id="${hidden}" value="${hiddenValue!}">
        @}
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}



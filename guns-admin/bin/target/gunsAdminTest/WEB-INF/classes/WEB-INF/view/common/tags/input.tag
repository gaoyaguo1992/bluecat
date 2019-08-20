@/*
    表单中input框标签中各个参数的说明:

    hidden : input hidden框的id
    id : input框id
    idForLabel: label对应的id..
    name : input框名称
    readonly : readonly属性
    clickFun : 点击事件的方法名
    style : 附加的css属性
    changeEventFun: 输入变化事件
    styleForLabel:
    styleForInputDiv: 输入框
    styleForDiv:最外面的输入框样式
@*/
<div class="form-group"
 	@if(isNotEmpty(styleForDiv)){
       style="${styleForDiv}"
    @}
>
    <label class="col-sm-3 control-label"
        @if(isNotEmpty(idForLabel)){
             id="${idForLabel}"
        @}
        @if(isNotEmpty(styleForLabel)){
             style="${styleForLabel}"
        @}
    >${name}</label>
    <div class="col-sm-9"
       @if(isNotEmpty(styleForInputDiv)){
             style="${styleForInputDiv}"
        @}
    >
        <input class="form-control" id="${id}" name="${id}"
               @if(isNotEmpty(value)){
                    value="${tool.dateType(value)}"
               @}
               @if(isNotEmpty(type)){
                    type="${type}"
               @}else{
                    type="text"
               @}
               @if(isNotEmpty(readonly)){
                    readonly="${readonly}"
               @}
               @if(isNotEmpty(clickFun)){
                    onclick="${clickFun}"
               @}
               @if(isNotEmpty(style)){
                    style="${style}"
               @}
               @if(isNotEmpty(changeEventFun)){
                    onchange="${changeEventFun}"
               @}
               @if(isNotEmpty(disabled)){
                    disabled="${disabled}"
               @}
               autocomplete="off"
        >
        @if(isNotEmpty(hidden)){
            <input class="form-control" type="hidden" id="${hidden}" value="${hiddenValue!}">
        @}

        @if(isNotEmpty(selectFlag)){
            <div id="${selectId}" style="display: none; position: absolute; z-index: 200;">
                <ul id="${selectTreeId}" class="ztree tree-box" style="${selectStyle!}"></ul>
            </div>
        @}
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}



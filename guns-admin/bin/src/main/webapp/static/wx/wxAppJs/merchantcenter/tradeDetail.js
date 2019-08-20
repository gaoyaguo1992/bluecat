
  var obj = document.getElementById("nav").getElementsByTagName("li");
  for(i in obj){
    obj[i].index = i;
      obj[i].onclick = function(){
        for(var j=0;j< obj.length; j++){
          obj[j].className = "";
        }
        this.className = "active";
        if(this.index == 0){
          document.getElementById("giftStatus").value = "0";
        }
        if(this.index == 1){
          document.getElementById("giftStatus").value = "1";
        }
        if(this.index == 2){
          document.getElementById("giftStatus").value = "2";
        }
      }
  }
  $(function () {
    var oDate = new Date(); //实例一个时间对象；
    var year = oDate.getFullYear();   //获取系统的年；
    var mouth = oDate.getMonth();   //获取系统月份，由于月份是从0开始计算，所以要加1
    var mouthend = oDate.getMonth()+1;  
    var day = oDate.getDate(); // 获取系统日 
    if (mouthend == 1) {
      var yearpre = year - 1;
      $("#startTime").val(yearpre+"-"+12 +"-"+ day);
    }else{
      $("#startTime").val(year+"-"+mouth +"-"+ day);
    }
    $("#endTime").val(year+"-"+mouthend +"-"+ day);

    var currYear = (new Date()).getFullYear();  
    var opt={};
    opt.date = {preset : 'date'};
    opt.datetime = {preset : 'datetime'};
    opt.time = {preset : 'time'};
    opt.defaultparam = {
      theme: 'android-ics light', //皮肤样式
      display: 'modal', //显示方式 
      mode: 'scroller', //日期选择模式
      dateFormat: 'yyyy-mm-dd',
      lang: 'zh',
      showNow: true,
      nowText: "今天",
      startYear: currYear - 50, //开始年份
      endYear: currYear + 10 //结束年份
    };
    $("#startTime").mobiscroll($.extend(opt['date'], opt['defaultparam']));
    $("#endTime").mobiscroll($.extend(opt['date'], opt['defaultparam']));
  });
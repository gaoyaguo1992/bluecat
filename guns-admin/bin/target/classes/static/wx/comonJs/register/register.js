	
	$(function(){
		var url = $(".next a").attr("href");//获取当前页面下一步地址
		
		//清空提示信息
//			$(".regbg input").focus(function(e) {
//					$("div.info").remove();
//				});
//			$(".regbg textarea").focus(function(e) {
//				$("div.info").remove();
//			});
		
		//输入账号key事件将账号格式化
		$("#settleAccount").keyup(function(){
			$("#settleAccount").val(formatCardno($("#settleAccount").val()));
		})
		$(".regbg font").parent("span").siblings("input[type=text],input[type=password]").not("#userId,#recCode,#businessLicense,#faxNo,#organizationCode").blur(function(e) {
				if($(this).val()==""||$(this).val()==null){
					$("div._"+$(this).attr("id")).remove();
					$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' ><font color='red'>"+$(this).prev("span").text().slice(1,$(this).prev("span").text().length-1)+"不能为空"+"</font></div>");
				}
        });
		//判断注册地址select2
		$("#registAddr").blur(function(e) {
			var select2 = $(".select2-chosen");
			for(var i=0;i<select2.length;i++){
				var select = select2.eq(i);
				if(select2.eq(i).text()=="请选择省份"||select2.eq(i).text()=="请选择城市"||select2.eq(i).text()=="请选择开户银行"||select2.eq(i).text()=="请选择支行"){
					$(this).attr("href","javascript:void(0)");
					$(".ssdd").remove();
					select.parents("p").append("<div class='info ssdd' ><font color='red'>请选择"+select.parents("p").find("span").eq(0).text().slice(1,select.parents("p").find("span").eq(0).text().length-1)+"</font></div>");
					break;
				}else{
					select.parents("p").find(".info").remove();
				}
				
			}
			if($(".ssdd").length==0){
				if($(this).val()==""||$(this).val()==null){
					$("._registAddr").remove();
					$(this).parents("p").append("<div class='info _registAddr' ><font color='red'>"+$(this).siblings("span").text().slice(1,$(this).prev("span").text().length-1)+"不能为空"+"</font></div>");
				}
			}
		});
		$("#address").blur(function(e) {
			if($(this).val()==""||$(this).val()==null){
				$("._address").remove();
				$(this).parents("p").append("<div class='info _address' ><font color='red'>"+$(this).siblings("span").text().slice(1,$(this).prev("span").text().length-1)+"不能为空"+"</font></div>");
			}else{
				$("._address").remove();
			}
		});
		var regx ="";
		
		//验证密码格式
		$("#passWord").blur(function(){
			regx = /^\s*[\s\S]{6,14}\s*$/;
			startCheck(regx,"passWord","格式不正确");	
		});
		$("#pass2").blur(function(){
			regx = /^\s*[\s\S]{6,14}\s*$/;
			startCheck(regx,"pass2","格式不正确");	
			//验证密码是否相同
			if($("._passWord").length==0){
				if($("#passWord").val()!=$("#pass2").val()){
					$("._passWord").remove();
					$(this).parents("p").append("<div class='info _passWord' style='text-align:left'><font color='red'>两次输入的密码不一样</font></div>");	
				}else{
					$("._passWord").remove();
				}
			}
		});
		//1page
		//验证用户名
		$("#userId").blur(function(e) {
			regx = /^\s*[\s\S]{1,20}\s*$/;
			startCheck(regx,"userId","格式不正确");
        });
		//验证用户名
		$("#userName").blur(function(e) {
			regx = /^\s*[\s\S]{1,20}\s*$/;
			startCheck(regx,"userName","格式不正确");
		});
		//验证手机号
		$("#phone").blur(function(e) {
            regx = /^0?(13|15|17|18|14)[0-9]{9}$/;
			startCheck(regx,"phone","格式不正确");
        });
		//验证企业名称
		$("#custName").blur(function(e) {
			regx = /^\s*[\s\S]{0,50}\s*$/;
			startCheck(regx,"custName","格式不正确");
		});
		//验证企业电话
		$("#contactPhone").blur(function(e) {
			regx = /^(\d{3,4}-)?\d{7,8}$/;
			startCheck(regx,"contactPhone","格式不正确");
		});
		//验证邮政编码
		$("#postcode").blur(function(e) {
			regx = /^[0-9]\d{5}(?!\d)$/;
			startCheck(regx,"postcode","格式不正确");
        });
		//验证企业名称
		$("#comPhone").blur(function(e) {
			regx = /^(\d{3,4}-)?\d{7,8}$/;
			startCheck(regx,"comPhone","格式不正确");
        });
		//验证企业电话
		$("#comPhone").blur(function(e) {
			regx = /^(\d{3,4}-)?\d{7,8}$/;
			startCheck(regx,"comPhone","格式不正确");
        });
		//验证企业传真
		$("#fax").blur(function(e) {
			regx = /^(\d{3,4}-)?\d{7,8}$/;
			startCheck(regx,"fax","格式不正确");
        });
		//验证电子邮箱
		$("#email").blur(function(e) {
			regx = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			startCheck(regx,"email","格式不正确");
        });
		//验证推广人代码
		$("#proCode").blur(function(e) {
           
        });
		//手机验证码
		$("#code").blur(function(e) {
			regx =/^[0-9]{4}$/;
			startCheck(regx,"code","格式不正确");
		});
		//4Page
		//验证姓名
		$("#dealerName").blur(function(e) {
			regx = /^\s*[\s\S]{2,20}\s*$/;
			startCheck(regx,"dealerName","格式不正确");
        });
		
		//验证授权管理员姓名
		$("#manageName").blur(function(e) {
			regx = /^\s*[\s\S]{2,20}\s*$/;
			startCheck(regx,"manageName","格式不正确");
        });
		//验证授权管理员身份证
		$("#dealerCertificateNumber").blur(function(e) {
			if($("#dealerCertificateNumber").val()!=""){
				if(!isValid($(this).val())){
					$("div._"+$(this).attr("id")).remove();
					$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' style='text-align:left'><font color='red'>授权管理员身份证号格式不正确</font></div>");
				}else{
					$("div._"+$(this).attr("id")).remove();
				}
			}
        });
		//验证授权管理员手机号
		$("#dealerPhone").blur(function(e) {
			 regx = /^0?(13|15|17|18|14)[0-9]{9}$/;
			startCheck(regx,"dealerPhone","格式不正确");
        });
		//法定代表人或授权委托人姓名
		$("#corporation").blur(function(e) {
			regx = /^\s*[\s\S]{2,20}\s*$/;
			startCheck(regx,"corporation","格式不正确");
        });
		//法定代表人或授权委托人身份证号
		$("#certificateNumber").blur(function(e) {
			if($("#certificateNumber").val()!=""){
				if(!isValid($(this).val())){
					$("div._"+$(this).attr("id")).remove();
					$(this).parents("p").append("<div class='info _"+$(this).attr("id")+"' style='text-align:left'><font color='red'>法定代表人或授权委托人身份证号格式不正确</font></div>");
					
				}else{
					$("div._"+$(this).attr("id")).remove();
				}
			}
        });
		//法定代表人或授权委托人手机号
		$("#corporationPhone").blur(function(e) {
			 regx = /^0?(13|15|17|18|14)[0-9]{9}$/;
			startCheck(regx,"corporationPhone","格式不正确");
        });
		//法定代表人或授权委托人固定电话
		$("#corporationTel").blur(function(e) {
			regx = /^(\d{3,4}-)?\d{7,8}$/;
			startCheck(regx,"corporationTel","格式不正确");
        });
		//验证银行账户名称
		$("#accountName").blur(function(e) {
			regx = /^\s*[\s\S]{2,50}\s*$/;
			startCheck(regx,"accountName","格式不正确");
        });
		//验证银行帐号
		$("#settleAccount").blur(function(e) {
			regx = /^([0-9]|-){1,32}$/;
			startCheck(regx,"settleAccount","格式不正确");
        });
	})
	function clickNext(){
		flag = 0;
			$(".info").remove();
			//验证select2
			var select2 = $(".select2-chosen");
			for(var i=0;i<select2.length;i++){
				var select = select2.eq(i);
				if(select2.eq(i).text()=="请选择省份"||select2.eq(i).text()=="请选择城市"||select2.eq(i).text()=="请选择开户银行"||select2.eq(i).text()=="请选择支行"){
					$(this).attr("href","javascript:void(0)");
					select.parents("p").append("<div class='info ssdd' ><font color='red'>请选择"+select.parents("p").find("span").eq(0).text().slice(1,select.parents("p").find("span").eq(0).text().length-1)+"</font></div>");
					break;
				}else{
					select.parents("p").find(".info").remove();
				}
				
			}
//			if($(".info").length>0){
//				return;	
//			}
			
			if($("#userId").val()==""){
				$("#userId").parents("p").append("<div class='info _userId' ><font color='red'>用户名不能为空</font></div>");
			}
            $(".regbg font").parent("span").siblings("input").blur();
            $(".textarea").blur();
//			if($(".info").length>0){
//				$(this).attr("href","javascript:void(0)");	
//			}else{
//				$(this).attr("href",url);
//			}
			
        }
	//开始验证
	function startCheck(regx,id,msg){
		if($("#"+id).val()!=""){
			var index = $("#"+id).prev("span").text();
			var name = index.slice(1,index.length-1);
			var val;
			if(id=="settleAccount"){
				val = $("#settleAccount").val().replace(/\ +/g,"");
			}else{
				val = $.trim($("#"+id).val());
			}
			if(regx.test(val)==false){
				$("div._"+id).remove();
				$("#"+id).parents("p").append("<div class='info _"+id+"'  style='text-align:left'><font color='red'>"+name+msg+"</font></div>");
			}else{
				$("div._"+id).remove();
			}
		}
	}
	 //身份证验证
    function isValid(idCard){  
    	idCard = trim(idCard.replace(/ /g, ""));  
        if (idCard.length == 15) {  
            return isValidityBrithBy15IdCard(idCard);  
        } else if (idCard.length == 18) {  
            var a_idCard = idCard.split("");// 得到身份证数组  
            if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){  
                return true;  
            }else {  
                return false;  
            }  
        } else {  
            return false;  
        }
    }
    /** 
     * 身份证15位编码规则：dddddd yymmdd xx p  
     * dddddd：地区码  
     * yymmdd: 出生年月日  
     * xx: 顺序类编码，无法确定  
     * p: 性别，奇数为男，偶数为女 
     * <p /> 
     * 身份证18位编码规则：dddddd yyyymmdd xxx y  
     * dddddd：地区码  
     * yyyymmdd: 出生年月日  
     * xxx:顺序类编码，无法确定，奇数为男，偶数为女  
     * y: 校验码，该位数值可通过前17位计算获得 
     * <p /> 
     * 18位号码加权因子为(从右到左) Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2,1 ] 
     * 验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]  
     * 校验位计算公式：Y_P = mod( ∑(Ai×Wi),11 )  
     * i为身份证号码从右往左数的 2...18 位; Y_P为脚丫校验码所在校验码数组位置 
     *  
     */ 
     
    var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子  
    var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值.10代表X  
    function IdCardValidate(idCard) {  
        idCard = trim(idCard.replace(/ /g, ""));  
        if (idCard.length == 15) {  
            return isValidityBrithBy15IdCard(idCard);  
        } else if (idCard.length == 18) {  
            var a_idCard = idCard.split("");// 得到身份证数组  
            if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){  
                return true;  
            }else {  
                return false;  
            }  
        } else {  
            return false;  
        }  
    }  
    /** 
     * 验证15位数身份证号码中的生日是否是有效生日 
     * @param idCard15 15位书身份证字符串 
     * @return 
     */ 
    function isValidityBrithBy15IdCard(idCard15){  
        var year =  idCard15.substring(6,8);  
        var month = idCard15.substring(8,10);  
        var day = idCard15.substring(10,12);  
        var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));  
        // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法  
        if(temp_date.getYear()!=parseFloat(year)  
                ||temp_date.getMonth()!=parseFloat(month)-1  
                ||temp_date.getDate()!=parseFloat(day)){  
                  return false;  
          }else{  
              return true;  
          }  
    }  
	  //去掉字符串头尾空格  
	  function trim(str) {  
	      return str.replace(/(^\s*)|(\s*$)/g, "");  
	  } 
	  /** 
	   * 验证18位数身份证号码中的生日是否是有效生日 
	   * @param idCard 18位书身份证字符串 
	   * @return 
	   */ 
	 function isValidityBrithBy18IdCard(idCard18){  
	     var year =  idCard18.substring(6,10);  
	     var month = idCard18.substring(10,12);  
	     var day = idCard18.substring(12,14);  
	     var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));  
	     // 这里用getFullYear()获取年份，避免千年虫问题  
	     if(temp_date.getFullYear()!=parseFloat(year)  
	           ||temp_date.getMonth()!=parseFloat(month)-1  
	           ||temp_date.getDate()!=parseFloat(day)){  
	             return false;  
	     }else{  
	         return true;  
	     } 
	 } 
	 /** 
	  * 判断身份证号码为18位时最后的验证位是否正确 
	  * @param a_idCard 身份证号码数组 
	  * @return 
	  */ 
	 function isTrueValidateCodeBy18IdCard(a_idCard) {  
	     var sum = 0; // 声明加权求和变量  
	     if (a_idCard[17].toLowerCase() == 'x') {  
	         a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作  
	     }  
	     for ( var i = 0; i < 17; i++) {  
	         sum += Wi[i] * a_idCard[i];// 加权求和  
	     }  
	     valCodePosition = sum % 11;// 得到验证码所位置  
	     if (a_idCard[17] == ValideCode[valCodePosition]) {  
	         return true;  
	     } else {  
	         return false;  
	     }  
	 }
	 //格式化卡号  
	 function formatCardno(cardno){
	    var f_cardno = cardno.replace(/\s(?=\d)/g,'').replace(/(\d{4})(?=\d)/g,"$1 ")
	    return f_cardno;
	}



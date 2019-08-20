define("nsts/top/1.0.1/topTool", [ "jquery-debug",'url'], function(require, exports,
		module) {
	var $ = require("jquery-debug");
	var url = require("url");
	var top = {};
	top.a = function(x,y){
		l = $('body').offset().left;
		w = $('body').width();
		$('#tbox').css('right',x + 'px');
		$('#tbox').css('bottom',y + 'px');
	}
	top.b =function(){
		h = $(window).height()-200;
		t = $(document).scrollTop();
		if(t > h){
			$('#gotop').fadeIn('slow');
			$('#buy').fadeIn('slow');
			$('#sell').fadeIn('slow');
		}else{
			$('#gotop').fadeOut('slow');
		}
	}
	top.adTopBut =function(){
		$("body").append("<div id='tbox'><a id='gotop' href='javascript:void(0)'></a></div>");
		top.c();
	}
	top.c =function(){
	$(document).ready(function(e) {
		 var url = window.location + '';
		 var topflag = $('body[topflag="true"]').length
		if($('body').length>0){
			top.a(8,40);
			top.b();
			$('#gotop').click(function(){
				//$(document).scrollTop(0);
				$('html,body').animate({scrollTop: 0}, 400);
			})
			$(document).scrollTop(0);
		}
		
	});
	$(window).scroll(function(e){
		top.b();		
	});
	}
	module.exports = top;
});

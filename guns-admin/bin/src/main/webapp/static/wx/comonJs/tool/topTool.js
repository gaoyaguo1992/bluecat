define("../../js/tool/topTool", [ "jquery-debug",'url'], function(require, exports,
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
		 if (url.indexOf('page/login/login.html') == -1) {
			 $("body").append("<div id='bs1' class='bs'><a href='../listinfo/listChooseInfo.html?type=sell'><i class='ico2'></i>我要卖</a></a></div>");
				$("body").append("<div id='bs2' class='bs'><a href='../listinfo/listChooseInfo.html?type=buy'><i class='ico3'></i>我要买</a></a></div>");
		 }
		if($('body').length>0){
			top.a(8,40);
			top.b();
			$('#gotop').click(function(){
				$(document).scrollTop(0);	
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

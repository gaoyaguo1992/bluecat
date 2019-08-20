if(window.orientation == 0){
    var win_hei =  window.screen.availHeight*1206/1334;               
    var win_wid = 750*win_hei/1206;
    $("html").css({"width":win_wid,"height":win_hei}); 
}else{
     $("html").css({"width":"100%","height":"auto"});
}
window.addEventListener("orientationchange", function() {
    reset(); 
	if(window.orientation == 0){
	    var win_hei =  window.screen.availHeight*1206/1334; 
	    var win_wid = 750*win_hei/1206;
	    $("html").css({"width":win_wid,"height":win_hei});       
	}else{
	     $("html").css({"width":"100%","height":"auto"});
	} 
}, false);
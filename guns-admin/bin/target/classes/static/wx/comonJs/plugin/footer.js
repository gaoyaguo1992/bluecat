
var old=document.write;
document.write=function(str){
	var arr=document.getElementById("police");
	arr.innerHTML=str;
	document.write=old;
}

document.writeln("<div class=\"footer\">");
document.writeln("	  <div class=\"footer-top\">");
document.writeln("		<a href=\"http://www.grains.org\" target=\"_blank\">美国谷物协会</a><a href=\"http://www.usda.gov\" target=\"_blank\">美国农业部</a><a href=\"http://www.cofco.com\" target=\"_blank\">中粮集团</a><a href=\"http://www.chinagrain.gov.cn\" target=\"_blank\">国家粮食局</a><a href=\"http://www.hfgtc.com\" target=\"_blank\">合肥国家粮食交易中心</a><a href=\"http://www.zlqh.com\" target=\"_blank\">中粮期货信息网</a><a href=\"http://www.dce.com.cn\" target=\"_blank\">大连商品交易所</a>");
document.writeln("	  	<p class=\"lable\">友情链接</p>");
document.writeln("    </div>");
document.writeln("    <div class=\"footer-btm\">");
document.writeln("    	<p style=\"padding-bottom:12px;\"><a href=\""+baseUrl+"/page/aboutus/about-us.html\" target=\"_blank\">关于我们</a><b>|</b><a href=\""+baseUrl+"/page/contactus/contactus.html\" target=\"_blank\">联系我们</a><b></p>");
document.writeln("    	<p>版权所有&nbsp;© 2015&nbsp;&nbsp;未经许可不得复制、转载或摘编，违者必究！</p>");
document.writeln("    	<p>Copyright&nbsp;© 2015&nbsp;EXGRAIN.COM.&nbsp;All&nbsp;Rights&nbsp;Reserved</p>");
document.writeln("	  	<p style=\"position:absolute;right:280px;top:16px;\"><a href=\"http://www.miitbeian.gov.cn\" target=\"_blank\">粤ICP备14101436号</a></p>");
document.writeln("    	<ul>");
document.writeln("    		<li><a id=\"___szfw_logo___\" href=\"https://search.szfw.org/cert/l/CX20150526010076010223\" target=\"_blank\" width=\"112\" height=\"40\" style=\"position:absolute;right:156px;top:5px;\"><img src=\""+baseUrl+"/image/cert.jpg\" oncontextmenu=\"return false\"/></a></li>");
document.writeln("    		<li id=\"police\"><a href=\"https://cert.ebs.gov.cn/ce5a83e2-057c-4e46-a5c5-06a67a0f96dd\" target=\"_blank\"><img width=\"128\" height=\"52\" title=\"深圳市市场监督管理局企业主体身份公示\" style=\"border: currentColor; border-image: none;\" alt=\"深圳市市场监督管理局企业主体身份公示\" src=\"https://cert.ebs.gov.cn/Images/newGovIcon.gif\" border=\"0\"></a></li>");
document.writeln("    	</ul>");
document.writeln("    </div>");
document.writeln("</div>");
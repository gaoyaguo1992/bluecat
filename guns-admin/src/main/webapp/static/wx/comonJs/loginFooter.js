var old=document.write;
document.write=function(str){
	var arr=document.getElementById("police");
	arr.innerHTML=str;
	document.write=old;
}

function initURLArr(){
	var urlArr = new Array();
	var url1={
			urlName:"wwewewewe",
			url:"/test/lwwewetml?fun_id=MENU1210",
			result:",32232,"
			
	};
	
	urlArr.push(url1);
	return urlArr;
}

function getUrls(resultCode){
	var urlArr = initURLArr();
	var resultUrlArr = new Array();
	for(var i = 0;i<urlArr.length;i++){
		if(urlArr[i]["result"].indexOf(","+resultCode+",")!=-1){
			var urlObj={
					urlName:"",
					url:""
			}
			urlObj.urlName = urlArr[i]["urlName"];
			urlObj.url = urlArr[i]["url"];
			resultUrlArr.push(urlObj);
		}
	}
	return resultUrlArr;
}

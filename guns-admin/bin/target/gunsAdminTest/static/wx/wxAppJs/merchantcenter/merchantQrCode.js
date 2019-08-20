$(function(){
	//显示该商户能扫码添加的商户类型
	buildQrCode(firstQrCodeUrl);
	
});
//切换添加商户类型二维码

function changeAddMerchantQrCode(opType,opTypeCn,qrCodeUrl){
	console.log(opType,opTypeCn,qrCodeUrl);
	buildQrCode(qrCodeUrl);
	
}



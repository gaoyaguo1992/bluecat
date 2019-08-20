package com.stylefeng.guns.sharecore.modular.system.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.sharecore.common.utils.CIPEncryptionUtils;
import com.stylefeng.guns.sharecore.modular.system.model.ShareChargerModel;
import com.stylefeng.guns.sharecore.modular.system.model.ShareDeviceInfoModel;
import com.stylefeng.guns.sharecore.modular.system.service.UserCenterService;

public class ChargerPwdDecoder {
	/**
	 * 处理日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserCenterService.class);

	/**
	 * 生成充电器密码..
	 * 
	 * @param chargerId
	 * @param refactorIdx
	 * @param chargerType
	 * @return
	 * @throws Exception
	 */
	public static String decode(String chargerId, String refactorIdx, int chargerType) throws Exception {
		String chargerPwds = "";
		String deRefactorIdx = refactorIdx;
		String chargerIdSuffix = chargerId.substring(chargerId.length() - 8, chargerId.length());
		chargerPwds = GeneratePwdCalacutorWithFactors.calculate(chargerIdSuffix, deRefactorIdx,chargerType);
		return chargerPwds;
	}

	/**
	 * 生成充电器的密码...
	 * @return the chargerPwds
	 * @throws Exception
	 */
	public static String getChargerPwds(ShareChargerModel shareChargerModel) {
		Integer chargerType = shareChargerModel.getChargerTypeId();
		String refactorIdx = shareChargerModel.getRefactorIdx();
		Long id = shareChargerModel.getId();
		String chargerPwds = "";
		if (!StringUtils.isEmpty(refactorIdx) && null != id) {
			try {
				chargerPwds = ChargerPwdDecoder.decode(id.toString(), refactorIdx, chargerType);
			} catch (Exception e) {
				logger.error(String.format("充电器【%d】没有烧入密码,请联系客服,生成烧录密码时失败", id), e);
			}
		} else {
			logger.info(String.format("充电器【%d】没有烧入密码,请联系客服!", id));
		}
		return chargerPwds;
	}
	
	/**
	 * 生成充电器的密码...
	 * @return the chargerPwds
	 * @throws Exception
	 */
	public static String getChargerPwdsForYYMMBB(ShareDeviceInfoModel shareDeviceInfoModel) {
		String chargerPwds = "";
		String key=shareDeviceInfoModel.getPasswordKey();
		// 左移2位
		String tmp="",pwd="";
		for (int i = 1; i <=40; i++) {
			tmp=String.format("00%d", i);
			tmp=tmp.substring(tmp.length()-2);
			tmp=String.format("%s%d%s", key,shareDeviceInfoModel.getId(),tmp);
			pwd=getOneYYMMBBPassword(tmp);
			chargerPwds=(chargerPwds.isEmpty())?pwd:String.format("%s;%s", chargerPwds,pwd);
		}
		return chargerPwds;
	}
	/**
	 * 得到密码...
	 * @param source
	 * @return
	 */
	public static String getOneYYMMBBPassword(String source){
		//String source="RUIOO18B1P0000101";
		String src= MD5Util.encrypt(source);
		String md54Bit=src.substring(src.length()-4);
		String bit1=md54Bit.substring(0,2);
		String bit2=md54Bit.substring(md54Bit.length()-2);
		Integer int1=Integer.parseInt(bit1, 16);
		Integer int2=Integer.parseInt(bit2,16);
		Integer int3=Integer.parseInt("3",16);
		int pwd1=(int1&int3)+1;
		int tmp=int2>>6;
		int pwd2=(tmp&int3)+1;
		tmp=int2>>4;
		int pwd3=(tmp&int3)+1;
		tmp=int2>>2;
		int pwd4=(tmp&int3)+1;
		int pwd5=(int2&int3)+1;
		return String.format("%d%d%d%d%d", pwd1,pwd2,pwd3,pwd4,pwd5);
	}
	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) {
		String source="RUIOO18B1P0000101";
		String src= MD5Util.encrypt(source);
		String md54Bit=src.substring(src.length()-4);
		String bit1=md54Bit.substring(0,2);
		String bit2=md54Bit.substring(md54Bit.length()-2);
		Integer int1=Integer.parseInt(bit1, 16);
		Integer int2=Integer.parseInt(bit2,16);
		Integer int3=Integer.parseInt("3",16);
		int pwd1=(int1&int3)+1;
		int tmp=int2>>6;
		int pwd2=(tmp&int3)+1;
		tmp=int2>>4;
		int pwd3=(tmp&int3)+1;
		tmp=int2>>2;
		int pwd4=(tmp&int3)+1;
		int pwd5=(int2&int3)+1;
		System.out.print(String.format("%d%d%d%d%d", pwd1,pwd2,pwd3,pwd4,pwd5));
	}
}

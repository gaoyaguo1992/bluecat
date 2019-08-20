/**
 * 
 */
package com.stylefeng.guns.sharecore.modular.system.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareChargerPasswordTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareChargerTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareDeviceTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.constants.ShareFeeTypeEnum;
import com.stylefeng.guns.sharecore.modular.system.exception.ExgrainBizUncheckedException;

/**
 * @author alan.li
 *
 */
public class GeneratePwdCalacutorWithFactors {
	private static byte[] digitASCII = { 0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 };
	//
	private static byte[] factors = { 0x43, 0x6F, 0x64, 0x65, 0x43, 0x61, 0x6C, 0x63 };
	//
	private static byte[] factorsTable = { 0x00, 0x71, 0x02, 0x73, 0x04, 0x05, 0x06, 0x07, 0x09, 0x0a, 0x10, 0x61, 0x12,
			0x63, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x20, 0x57, 0x28, 0x59, 0x2a, 0x2b, 0x2c, 0x2c, 0x2d, 0x2f, 0x3e,
			0x41, 0x32, 0x43, 0x34, 0x3a, 0x3b, 0x3c, 0x3d, 0x3f, 0x4e, 0x31, 0x42, 0x33, 0x44, 0x4a, 0x4b, 0x4c, 0x4d,
			0x4f, 0x50, 0x21, 0x52, 0x23, 0x54, 0x5a, 0x5b, 0x5c, 0x5d, 0x5f, 0x6b, 0x11, 0x62, 0x13, 0x64, 0x6a, 0x6b,
			0x6c, 0x6d, 0x6f, 0x7b, 0x01, 0x72, 0x03, 0x74, 0x7a, 0x7b, 0x7c, 0x7e, 0x7f };
	private static byte[] idASCII = new byte[8];
	private static Logger logger = LoggerFactory.getLogger(GeneratePwdCalacutorWithFactors.class);
	private static byte[] middleResult = null;

	/**
	 * 得到密码因子值对...
	 * 
	 * @param factorsIndex
	 * @return
	 * @throws Exception
	 */
	public static String getFactorHexStr(String factorsIndex) throws Exception {
		if (!StringUtils.isEmpty(factorsIndex)) {
			String[] factorIdxs = factorsIndex.split(",");
			if (factorIdxs.length != 8) {
				throw new Exception("密码因因子长度不对！");
			}
			for (String idx : factorIdxs) {
				if (Integer.parseInt(idx) < 0 || Integer.parseInt(idx) > 79) {
					throw new Exception("密码因因子值不对！");
				}
			}

			byte[] factorsWithIdx = new byte[8];
			int count = 0;
			for (String idx : factorIdxs) {
				int idxInt = Integer.parseInt(idx);
				factorsWithIdx[count] = factorsTable[idxInt];
				count = count + 1;
			}
			return decodeBytesToHexString(factorsWithIdx);
		}
		return null;
	}

	/**
	 * 单个bit转到16进制字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String decodeByteToHexString(byte src) {
		byte[] des = new byte[2];
		des[1] = (byte) (src & 0x0f);
		des[0] = (byte) ((src & 0xf0) >> 4);
		return Integer.toHexString(des[0]) + Integer.toHexString(des[1]);
	}

	/**
	 * bytes数组对转换为16进制串..
	 * 
	 * @param data
	 * @return
	 */
	public static String decodeBytesToHexString(byte[] data) {
		String result = new String();
		for (byte dd : data) {
			result = result.concat(decodeByteToHexString(dd));
		}
		return result;
	}

	/**
	 * 计算密码
	 * 
	 * @param chargerId
	 * @param factorsIndex
	 * @return
	 * @throws Exception
	 */
	public synchronized static String calculate(String chargerId, String factorsIndex, int chargerType)
			throws Exception {
		if (chargerId.length() != 8) {
			return "";
		}
		ShareDeviceTypeEnum shareDeviceTypeEnum = ShareDeviceTypeEnum.getShareDeviceTypeEnumByChargerTypeId(chargerType);
		// 高级密码..
		Boolean isPasswordAdvance = shareDeviceTypeEnum == null
                || shareDeviceTypeEnum.getPasswordType() == ShareChargerPasswordTypeEnum.PW_11.getCode()
                || shareDeviceTypeEnum.getPasswordType() == ShareChargerPasswordTypeEnum.PW_21.getCode()
                || shareDeviceTypeEnum.getPasswordType() == ShareChargerPasswordTypeEnum.PW_30.getCode();

		if (!StringUtils.isEmpty(factorsIndex)) {
			String[] factorIdxs = factorsIndex.split(",");
			if (factorIdxs.length != 8) {
				throw new Exception("密码因因子长度不对！");
			}
			for (String idx : factorIdxs) {
				if (Integer.parseInt(idx) < 0 || Integer.parseInt(idx) > 79) {
					throw new Exception("密码因因子值不对！");
				}
			}
		}
		for (int i = 0; i < chargerId.length(); i++) {
			idASCII[i] = digitASCII[Integer.parseInt(chargerId.toCharArray()[i] + "")];
		}
		// 1. 将8byte数字与英文字母Code Calc（密码因子）逐位异或。数字英文对应表
		byte[] xor = new byte[8];
		if (StringUtils.isEmpty(factorsIndex)) {
			for (int i = 0; i < 8; i++) {
				xor[i] = (byte) (idASCII[i] ^ factors[i]);
			}
		} else {
			byte[] factorsWithIdx = new byte[8];
			String[] factorIdxs = factorsIndex.split(",");
			int count = 0;
			for (String idx : factorIdxs) {
				int idxInt = Integer.parseInt(idx);
				factorsWithIdx[count] = factorsTable[idxInt];
				count = count + 1;
			}

			for (int i = 0; i < 8; i++) {
				xor[i] = (byte) (idASCII[i] ^ factorsWithIdx[i]);
			}
		}
		List<String> pwsAll = new ArrayList<String>();
		StringBuffer passwds = new StringBuffer();
		String tmpPwd = "";
		int pwdCount = 0;
		// 第一次使用二二运算的结果
		middleResult = xor;
		while (true) {
			String passwd = doCalculate(chargerId, chargerType, shareDeviceTypeEnum, middleResult);
			if (passwd.length() < 5) {
				if (6 - passwd.length() == 1) {
					passwd = "0" + passwd;
				}
				if (6 - passwd.length() == 2) {
					passwd = "00" + passwd;
				}
				if (6 - passwd.length() == 3) {
					passwd = "000" + passwd;
				}
				if (6 - passwd.length() == 4) {
					passwd = "0000" + passwd;
				}
				if (5 - passwd.length() == 5) {
					passwd = "00000" + passwd;
				}
			}
			// 加强密码必须保存中间3位不重复.
			tmpPwd = passwd;
			if (isPasswordAdvance) {
				passwd = passwd.substring(2, 6);
				if(shareDeviceTypeEnum.getPasswordType()!=ShareChargerPasswordTypeEnum.PW_21.getCode()){
					tmpPwd =passwd; //passwd.substring(2, 5);
				}else{
					tmpPwd=passwd;
				}
			}
			if (!pwsAll.contains(tmpPwd) && !isPwdAllSame(tmpPwd)) {
				pwdCount++;
				// 1. 保存 生成密码
				if (6 - passwd.length() == 1) {
					passwd = "0" + passwd;
				}
				if (6 - passwd.length() == 2) {
					passwd = "00" + passwd;
				}
				if (6 - passwd.length() == 3) {
					passwd = "000" + passwd;
				}
				if (6 - passwd.length() == 4) {
					passwd = "0000" + passwd;
				}
				if (5 - passwd.length() == 5) {
					passwd = "00000" + passwd;
				}
				passwds.append(passwd);
				if (pwdCount >= 15) {
					break;
				} else {
					// 2. 生成后面这个;
					passwds.append(";");
				}
			}
			pwsAll.add(tmpPwd);
			if (pwdCount >= 15) {
				break;
			}
		}
		middleResult = null;
		return passwds.toString();
	}

	/**
	 * 判断是否重复
	 * 
	 * @param pwd
	 * @return
	 */
	private static boolean isPwdAllSame(String pwd) {
		char[] pwdArr = pwd.toCharArray();
		char a = pwdArr[0];
		for (int i = 1; i < pwdArr.length; i++) {
			if (!((a + "").equals(pwdArr[i] + ""))) {
				return false;

			}
		}
		return true;
	}

	/**
	 * 密码计算逻辑
	 * 
	 * @param chargerId
	 * @param xor
	 * @param chargerType
	 *            确定密码算法
	 * @return
	 */
	public static String doCalculate(String chargerId, int chargerType, ShareDeviceTypeEnum shareDeviceTypeEnum,
			byte[] xor) {
		String password = "";
		if (shareDeviceTypeEnum.getPasswordType() == ShareChargerPasswordTypeEnum.PW_20.getCode()) {
			password = doCalculate1003(chargerId, chargerType, xor);
		} else if (shareDeviceTypeEnum.getPasswordType() == ShareChargerPasswordTypeEnum.PW_21.getCode()) {
			password = doCalculate1004(chargerId, chargerType, xor);
		} else {
			password = doCalculateGeneral(chargerId, chargerType, xor);
		}
		return password;
	}

	/**
	 * 
	 * @param chargerId
	 * @param chargerType
	 * @param shareDeviceTypeEnum
	 * @param xor
	 * @return
	 */
	protected static String doCalculate1003(String chargerId, int chargerType, byte[] xor) {
		if (middleResult != null) {
			xor = middleResult;
		}
		String password = "";
		// 2. 高低4byte交换即1-2；3-4；5-6；7-8；
		byte temp = xor[0];
		xor[0] = xor[1];
		xor[1] = temp;

		temp = xor[2];
		xor[2] = xor[3];
		xor[3] = temp;

		temp = xor[4];
		xor[4] = xor[5];
		xor[5] = temp;

		temp = xor[6];
		xor[6] = xor[7];
		xor[7] = temp;

		StringBuffer binaryStrBuf = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			// logger.info(Integer.toHexString(xor[i]));
			binaryStrBuf.append(Integer.toBinaryString((xor[i] & 0xFF) + 0x100).substring(1));
		}
		// 左移2位
		byte[] leftcft = new byte[8];
		String binaryStr = binaryStrBuf.toString();
		binaryStr = binaryStr.substring(2) + binaryStr.substring(0, 2);
		// logger.info(binaryStr);
		for (int i = 0; i < 8; i++) {
			int temp1 = Integer.parseInt(binaryStr.substring(i * 8, i * 8 + 8), 2);
			leftcft[i] = (byte) temp1;
		}
		// 4. 逐字节异或即 2=2xor1；3=3xor2 ；4=4xor3
		// ；5=5xor4；6=6xor5；7=7xor6；8=8xor7；1=1xor8
		for (int i = 1; i <= 7; i++) {
			leftcft[i] = (byte) (leftcft[i] ^ leftcft[i - 1]);
		}
		leftcft[0] = (byte) (leftcft[0] ^ leftcft[7]);
		byte result[] = new byte[8];
		for (int i = 0; i <= 7; i++) {
			result[i] = leftcft[i];
		}
		// 5. 两两交换 1-5；2-6； 3-7；4-8.
		temp = result[0];
		result[0] = result[4];
		result[4] = temp;

		temp = result[1];
		result[1] = result[5];
		result[5] = temp;

		temp = result[2];
		result[2] = result[6];
		result[6] = temp;

		temp = result[3];
		result[3] = result[7];
		result[7] = temp;
		middleResult = new byte[8];
		for (int i = 0; i <= 7; i++) {
			middleResult[i] = result[i];
		}

		// 6 高低字节异或后的十六进制结果1
		result[0] = (byte) (result[0] ^ result[4]);
		result[1] = (byte) (result[1] ^ result[5]);
		result[2] = (byte) (result[2] ^ result[6]);
		result[3] = (byte) (result[3] ^ result[7]);

		// 7 高低字节异或后的十六进制结果2
		result[0] = (byte) (result[0] ^ result[2]);
		result[1] = (byte) (result[1] ^ result[3]);
		// 8. 将这三个字节变为一个长整形 按顺序最高字节00>>1>>2>>3
		byte[] pwdData = new byte[2];
		pwdData[0] = result[0];
		pwdData[1] = result[1];

		String tmp = "00" + Integer.toHexString(pwdData[0]);
		String pwdLngStr = tmp.substring(tmp.length() - 2);
		tmp = "00" + Integer.toHexString(pwdData[1]);
		pwdLngStr = pwdLngStr + tmp.substring(tmp.length() - 2);
		// 16进制
		pwdLngStr = pwdLngStr.substring(pwdLngStr.length() - 3);
		Integer iResult = Integer.parseInt(pwdLngStr, 16);
		String xorBinaryResult = Integer.toBinaryString(iResult);
		xorBinaryResult = "0000000000000000" + xorBinaryResult;
		xorBinaryResult = xorBinaryResult.substring(xorBinaryResult.length() - 12);
		// 9 二进制
		password = password + (bit2byte(xorBinaryResult.substring(0, 2)) + 1);
		password = password + (bit2byte(xorBinaryResult.substring(2, 4)) + 1);
		password = password + (bit2byte(xorBinaryResult.substring(4, 6)) + 1);
		password = password + (bit2byte(xorBinaryResult.substring(6, 8)) + 1);
		password = password + (bit2byte(xorBinaryResult.substring(8, 10)) + 1);
		password = password + (bit2byte(xorBinaryResult.substring(10, 12)) + 1);
		//
		return password;
	}

	/**
	 * 
	 * @param chargerId
	 * @param chargerType
	 * @param shareDeviceTypeEnum
	 * @param xor
	 * @return
	 */
	protected static String doCalculate1004(String chargerId, int chargerType, byte[] xor) {
		if (middleResult != null) {
			xor = middleResult;
		}
		String password = "";
		// 2. 高低4byte交换即1-2；3-4；5-6；7-8；
		byte temp = xor[0];
		xor[0] = xor[1];
		xor[1] = temp;

		temp = xor[2];
		xor[2] = xor[3];
		xor[3] = temp;

		temp = xor[4];
		xor[4] = xor[5];
		xor[5] = temp;

		temp = xor[6];
		xor[6] = xor[7];
		xor[7] = temp;

		StringBuffer binaryStrBuf = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			// logger.info(Integer.toHexString(xor[i]));
			binaryStrBuf.append(Integer.toBinaryString((xor[i] & 0xFF) + 0x100).substring(1));
		}
		// 左移2位
		byte[] leftcft = new byte[8];
		String binaryStr = binaryStrBuf.toString();
		binaryStr = binaryStr.substring(2) + binaryStr.substring(0, 2);
		// logger.info(binaryStr);
		for (int i = 0; i < 8; i++) {
			int temp1 = Integer.parseInt(binaryStr.substring(i * 8, i * 8 + 8), 2);
			leftcft[i] = (byte) temp1;
		}
		// 4. 逐字节异或即 2=2xor1；3=3xor2 ；4=4xor3
		// ；5=5xor4；6=6xor5；7=7xor6；8=8xor7；1=1xor8
		for (int i = 1; i <= 7; i++) {
			leftcft[i] = (byte) (leftcft[i] ^ leftcft[i - 1]);
		}
		leftcft[0] = (byte) (leftcft[0] ^ leftcft[7]);
		byte result[] = new byte[8];
		for (int i = 0; i <= 7; i++) {
			result[i] = leftcft[i];
		}
		// 5. 两两交换 1-5；2-6； 3-7；4-8.
		temp = result[0];
		result[0] = result[4];
		result[4] = temp;

		temp = result[1];
		result[1] = result[5];
		result[5] = temp;

		temp = result[2];
		result[2] = result[6];
		result[6] = temp;

		temp = result[3];
		result[3] = result[7];
		result[7] = temp;
		middleResult = new byte[8];
		for (int i = 0; i <= 7; i++) {
			middleResult[i] = result[i];
		}

		// 6 高低字节异或后的十六进制结果1
		result[0] = (byte) (result[0] ^ result[4]);
		result[1] = (byte) (result[1] ^ result[5]);
		result[2] = (byte) (result[2] ^ result[6]);
		result[3] = (byte) (result[3] ^ result[7]);

		// 7 高低字节异或后的十六进制结果2
		result[0] = (byte) (result[0] ^ result[2]);
		result[1] = (byte) (result[1] ^ result[3]);
		
		result[0] = (byte) (result[0] ^ result[1]);
		//8 ..得到密码
		String resultBinaryString = Integer.toBinaryString(result[0]);
		resultBinaryString = "00000000" + resultBinaryString;
		resultBinaryString = resultBinaryString.substring(resultBinaryString.length() - 8);

		password = password + (bit2byte(resultBinaryString.substring(0, 2)) + 1);
		password = password + (bit2byte(resultBinaryString.substring(2, 4)) + 1);
		password = password + (bit2byte(resultBinaryString.substring(4, 6)) + 1);
		password = password + (bit2byte(resultBinaryString.substring(6, 8)) + 1);
		return password;
	}

	/**
	 * 
	 * @param chargerId
	 * @param chargerType
	 * @param shareDeviceTypeEnum
	 * @param xor
	 * @return
	 */
	protected static String doCalculateGeneral(String chargerId, int chargerType, byte[] xor) {
		if (middleResult != null) {
			xor = middleResult;
		}
		String password = "";
		// 2. 高低4byte交换即1-2；3-4；5-6；7-8；
		byte temp = xor[0];
		xor[0] = xor[1];
		xor[1] = temp;

		temp = xor[2];
		xor[2] = xor[3];
		xor[3] = temp;

		temp = xor[4];
		xor[4] = xor[5];
		xor[5] = temp;

		temp = xor[6];
		xor[6] = xor[7];
		xor[7] = temp;

		StringBuffer binaryStrBuf = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			// logger.info(Integer.toHexString(xor[i]));
			binaryStrBuf.append(Integer.toBinaryString((xor[i] & 0xFF) + 0x100).substring(1));
		}
		// 左移2位
		byte[] leftcft = new byte[8];
		String binaryStr = binaryStrBuf.toString();
		binaryStr = binaryStr.substring(2) + binaryStr.substring(0, 2);
		// logger.info(binaryStr);
		for (int i = 0; i < 8; i++) {
			int temp1 = Integer.parseInt(binaryStr.substring(i * 8, i * 8 + 8), 2);
			leftcft[i] = (byte) temp1;
		}
		// 4. 逐字节异或即 2=2xor1；3=3xor2 ；4=4xor3
		// ；5=5xor4；6=6xor5；7=7xor6；8=8xor7；1=1xor8
		for (int i = 1; i <= 7; i++) {
			leftcft[i] = (byte) (leftcft[i] ^ leftcft[i - 1]);
		}
		leftcft[0] = (byte) (leftcft[0] ^ leftcft[7]);
		byte result[] = new byte[8];
		for (int i = 0; i <= 7; i++) {
			result[i] = leftcft[i];
		}
		// 5. 两两交换 1-5；2-6； 3-7；4-8.
		temp = result[0];
		result[0] = result[4];
		result[4] = temp;

		temp = result[1];
		result[1] = result[5];
		result[5] = temp;

		temp = result[2];
		result[2] = result[6];
		result[6] = temp;

		temp = result[3];
		result[3] = result[7];
		result[7] = temp;
		middleResult = new byte[8];
		for (int i = 0; i <= 7; i++) {
			middleResult[i] = result[i];
		}

		// 6 高低字节异或后的十六进制结果1
		result[0] = (byte) (result[0] ^ result[4]);
		result[1] = (byte) (result[1] ^ result[5]);
		result[2] = (byte) (result[2] ^ result[6]);
		result[3] = (byte) (result[3] ^ result[7]);

		// 7 高低字节异或后的十六进制结果2
		result[0] = (byte) (result[0] ^ result[1]);
		result[1] = (byte) (result[1] ^ result[2]);
		result[2] = (byte) (result[2] ^ result[3]);
		// 8. 将这三个字节变为一个长整形 按顺序最高字节00>>1>>2>>3 
		byte[] pwdData = new byte[4];
		pwdData[0] = 0x00;
		pwdData[1] = result[0];
		pwdData[2] = result[1];
		pwdData[3] = result[2];
		String tmp = "00" + Integer.toHexString(pwdData[0]);// Integer.toHexString((pwdData[0]
															// & 0xFF) +
															// 0x100).substring(1);
		String pwdLngStr = tmp.substring(tmp.length() - 2);
		tmp = "00" + Integer.toHexString(pwdData[1]);
		pwdLngStr = pwdLngStr + tmp.substring(tmp.length() - 2);
		tmp = "00" + Integer.toHexString(pwdData[2]);
		pwdLngStr = pwdLngStr + tmp.substring(tmp.length() - 2);
		tmp = "00" + Integer.toHexString(pwdData[3]);
		pwdLngStr = pwdLngStr + tmp.substring(tmp.length() - 2);
		/*
		 * pwdLngStr=pwdLngStr+tmp.substring(tmp.length()-2);
		 * pwdLngStr=pwdLngStr+Integer.toHexString((pwdData[0] & 0xFF) +
		 * 0x100).substring(1);
		 * pwdLngStr=pwdLngStr+Integer.toHexString((pwdData[0] & 0xFF) +
		 * 0x100).substring(1);
		 * pwdLngStr=pwdLngStr+Integer.toHexString((pwdData[0] & 0xFF) +
		 * 0x100).substring(1);
		 */
		Long pwdLng = Long.parseLong(pwdLngStr, 16);

		// 第一位密码
		long pwd1 = pwdLng % 5 + 1;
		long pwd2 = (pwdLng / 5) % 5 + 1;
		long pwd3 = (pwdLng / 50) % 5 + 1;
		long pwd4 = (pwdLng / 500) % 5 + 1;
		long pwd5 = (pwdLng / 5000) % 5 + 1;
		long pwd6 = (pwdLng / 50000) % 5 + 1;
		password = String.format("%s%s%s%s%s%s", pwd1, pwd2, pwd3, pwd4, pwd5, pwd6);
		/*
		 * // 剩下的字节再异或 byte xorResult =(byte) (result[0] ^ result[1]); String
		 * xorBinaryResult = Integer.toBinaryString((xorResult & 0xFF) +
		 * 0x100).substring(1);
		 * 
		 * String xorBinaryResult0 = Integer.toBinaryString((result[0] & 0xFF) +
		 * 0x100).substring(1); xorBinaryResult0 =
		 * xorBinaryResult0.substring(0,2); String xorBinaryResult1 =
		 * Integer.toBinaryString((result[1] & 0xFF) + 0x100).substring(1);
		 * xorBinaryResult1 = xorBinaryResult1.substring(6,8);
		 * 
		 * byte xorByteResult0 = bit2byte(xorBinaryResult0); byte xorByteResult1
		 * = bit2byte(xorBinaryResult1); byte fifthPwd =(byte) ( xorByteResult0
		 * ^ xorByteResult1); password = password +
		 * (bit2byte(xorBinaryResult.substring(0,2))+1); password = password +
		 * (bit2byte(xorBinaryResult.substring(2,4))+1); password = password +
		 * (bit2byte(xorBinaryResult.substring(4,6))+1); password = password +
		 * (bit2byte(xorBinaryResult.substring(6,8))+1); password = password +
		 * (fifthPwd+1);
		 */
		return password;
	}

	/**
	 * bit 转为byte...
	 * 
	 * @param bString
	 * @return
	 */
	private static byte bit2byte(String bString) {
		byte result = 0;
		for (int i = bString.length() - 1, j = 0; i >= 0; i--, j++) {
			result += (Byte.parseByte(bString.charAt(i) + "") * Math.pow(2, j));
		}
		return result;
	}

	/**
	 * 
	 * 根据充电时间和原密码，生成带有控制充电时间的
	 * 
	 * @param hours
	 * @param pwds
	 * @return
	 */
	public static String getPwdForTimesByHoursAndPwds(int hours, String pwds, Integer chargerTypeId) {	
		if (pwds.length() < 6) {
			throw new ExgrainBizUncheckedException("充电器密码不正确，无法生成带时间控制的密码!");
		}
		ShareDeviceTypeEnum shareDeviceTypeEnum = ShareDeviceTypeEnum.getShareDeviceTypeEnumByChargerTypeId(chargerTypeId);
		if (ShareChargerPasswordTypeEnum.PW_21.getCode() == shareDeviceTypeEnum.getPasswordType()) {	
			/*
			 * 18.生成密码举例，比如第 6步到第 9 步的的方法得到1组密码值为2341,需要充电2小时,则新密码产生过程如下
			 * (1).将2341前面2位和后面2位进行拆分，得到23和41
			 * (2).将2小时的时间识别码0010 转换为密码为 02 由于键盘没有0全部加1 为13 插入拆分的23和41中间，得到新密码231341.
			 */
			pwds="0000"+pwds;
			pwds=pwds.substring(pwds.length()-4);
			String perPwd = pwds.substring(0, 2);
			String lastPwd = pwds.substring(2, 4);
			String binaryHours= Integer.toBinaryString(hours);
			binaryHours="0000"+binaryHours;
			String high= binaryHours.substring(binaryHours.length()-4,binaryHours.length()-2);
			String low= binaryHours.substring(binaryHours.length()-2);
			int hPw= Integer.parseInt(high, 2)+1;
			int lPw=Integer.parseInt(low,2)+1;			
			return String.format("%s%d%d%s", perPwd,hPw,lPw,lastPwd);
		} else {
			/*
			 * 总的密码位数是6位，最高2位是控制充电时间（最大15个小时），后四位是密码，后四位的最后一位位密码和时间的校验核。 1.
			 * 服务器要将充电时间控制位进行排序，比如 我要充电7个小时，7的5进制为7/5=1余2 对应的为16禁止数为0x12 排成二进制 0001
			 * 0010 ， 取后面四个bit 密码就是1，2 但是我们没有0全都同时+1 密码变为 2，3 加上标准的密码比如：3243
			 * 最后一位为密码高低位异或再次异或密码最后一位 0x01^0x02= 0x02 0x02^0x03=0x00 密码为01 233241
			 * 这个密码可以开机7个小时，7个小时后关闭输出，但是每15分钟无负载会关闭一次
			 */
			// 1. 计算高位密码
			String midPwd = pwds.substring(2, 5);
			String lastPwd = pwds.substring(5, 6);

			int pwdH1 = hours / 5;
			int pwdH2 = hours % 5;
			// 2.计算最后一位检验码
			int lastNewPwd = Integer.parseInt(lastPwd);
			Integer pwdH1Tmp = new Integer(pwdH1);
			byte pwdH1ForByte = pwdH1Tmp.byteValue();
			Integer pwdH2Tmp = new Integer(pwdH2);
			byte pwdH2ForByte = pwdH2Tmp.byteValue();
			Integer pwdLastTmp = new Integer(lastPwd);
			byte pwdLastForByte = pwdLastTmp.byteValue();
			byte pwdH1Byte = (byte) (pwdH1ForByte ^ pwdH2ForByte);
			pwdH1Byte = (byte) (pwdH1Byte ^ pwdLastForByte);
			lastNewPwd = pwdH1Byte;
			// 3. 都加1
			pwdH1 = pwdH1 + 1;
			pwdH2 = pwdH2 + 1;
			lastNewPwd = lastNewPwd + 1;
			// 4. 返回
			String newPwds = String.format("%d%d%s%d", pwdH1, pwdH2, midPwd, lastNewPwd);
			return newPwds;
		}

	}

	/**
	 * 主函数...
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String chargerId = "12345379";
		System.out.println(chargerId.substring(0,5));
		
		for (int i = 0; i < chargerId.length(); i++) {
			idASCII[i] = digitASCII[Integer.parseInt(chargerId.toCharArray()[i] + "")];
		}
		// 1. 将8byte数字与英文字母Code Calc（密码因子）逐位异或。数字英文对应表
		byte[] xor = new byte[8];

		String refactors = "47659678";
		byte[] factorsWithIdx = new byte[8];
		for (int i = 0; i < refactors.length(); i++) {
			factorsWithIdx[i] = digitASCII[Integer.parseInt(refactors.toCharArray()[i] + "")];
		}
		
		for (int i = 0; i < 8; i++) {
			xor[i] = (byte) (idASCII[i] ^ factorsWithIdx[i]);
		}
		List<String> pwsAll = new ArrayList<String>();
		StringBuffer passwds = new StringBuffer();
		String tmpPwd = "";
		int pwdCount = 0;
		// 第一次使用二二运算的结果
		
		middleResult = xor;
		ShareDeviceTypeEnum shareDeviceTypeEnum=ShareDeviceTypeEnum.DE_33;
		Boolean isPasswordAdvance=true;
		while (true) {
			String passwd = doCalculate(chargerId, 33, shareDeviceTypeEnum, middleResult);
			System.out.println(passwd);
			if (passwd.length() < 5) {
				if (6 - passwd.length() == 1) {
					passwd = "0" + passwd;
				}
				if (6 - passwd.length() == 2) {
					passwd = "00" + passwd;
				}
				if (6 - passwd.length() == 3) {
					passwd = "000" + passwd;
				}
				if (6 - passwd.length() == 4) {
					passwd = "0000" + passwd;
				}
				if (5 - passwd.length() == 5) {
					passwd = "00000" + passwd;
				}
			}
			// 加强密码必须保存中间3位不重复.
			tmpPwd = passwd;
			if (isPasswordAdvance) {
				passwd = passwd.substring(2, 6);
				if(shareDeviceTypeEnum.getPasswordType()!=ShareChargerPasswordTypeEnum.PW_21.getCode()){
					tmpPwd =passwd; //passwd.substring(2, 5);
				}else{
					tmpPwd=passwd;
				}
			}
			if (!pwsAll.contains(tmpPwd) && !isPwdAllSame(tmpPwd)) {
				pwdCount++;
				// 1. 保存 生成密码
				if (6 - passwd.length() == 1) {
					passwd = "0" + passwd;
				}
				if (6 - passwd.length() == 2) {
					passwd = "00" + passwd;
				}
				if (6 - passwd.length() == 3) {
					passwd = "000" + passwd;
				}
				if (6 - passwd.length() == 4) {
					passwd = "0000" + passwd;
				}
				if (5 - passwd.length() == 5) {
					passwd = "00000" + passwd;
				}
				passwds.append(passwd);
				if (pwdCount >= 15) {
					break;
				} else {
					// 2. 生成后面这个;
					passwds.append(";");
				}
			}
			pwsAll.add(tmpPwd);
			if (pwdCount >= 15) {
				break;
			}
		}
		System.out.println("----------pwsAll----------------");
		int i=0;
		for (String string : pwsAll) {
			i++;
			System.out.println(String.format("pwsAll%d=%s",i,string));			
		}
	}
}

package com.stylefeng.guns.sharecore.common.utils;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.springframework.util.DigestUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * <P>
 * 客户信息平台各种敏感信息加密处理工具类
 * </P>
 * 
 * 
 */
@SuppressWarnings("restriction")
public class CIPEncryptionUtils {
		// 客户信息平台keystore文件本身的属性
		final static String KEYSTORE_FILE_NAME = "CIPKeystore.keystore";// keystore文件的名称
		final static char[] KEYSTORE_FILE_PASSWORD = "123kft".toCharArray();// keystore文件的密码

		// 存取密码及各种敏感信息的盐的条目属性
		// 1.用于登陆密码加密的盐
		final static String SECRETLEVEL_LOGINPASSWORD_KEYNAME = "CIPLoginPasswordKey";// 用于登陆密码加密的key条目的名称
		final static char[] LOGINPASSWORD_KEYPASSWORD = "kft123".toCharArray();// 用于取登陆密码加密的key条目的保护密码

		// 2.用于支付密码加密的盐
		final static String SECRETLEVEL_PAYMENTPASSWORD_KEYNAME = "CIPPaymentPasswordKey";// 用于支付密码加密的key条目的名称
		final static char[] PAYMENTPASSWORD_KEYPASSWORD = "1kft23".toCharArray();// 用于取支付密码加密的key条目的保护密码

		// 3.用于双向敏感信息的盐
		final static String SECRETLEVEL_SENSITIVEINFORMATIONBIDIRECTIONAL_KEYNAME = "CIPSensitiveInformationBidirectionalKey";// 敏感信息双向加密的key条目名称
		final static char[] SENSITIVEINFORMATIONBIDIRECTIONAL_KEYPASSWORD = "12kft3".toCharArray();// 敏感信息双向加密的key条目的保护密码

		// 4.用于单向敏感信息的盐
		final static String SECRETLEVEL_SENSITIVEINFORMATION_KEYNAME = "CIPSensitiveInformationKey";// 敏感信息单向加密的key条目名称
		final static char[] SENSITIVEINFORMATION_KEYPASSWORD = "1kf2t3".toCharArray();// 敏感信息单向加密的key条目的保护密码
		
		// 5.双向加密的16位向量
		final static String SECRETLEVEL_IVS_KEYNAME = "CIPIVSKey";// 敏感信息双向加密的向量key条目名称
		final static char[] IVS_KEYPASSWORD = "1kf2t3".toCharArray();// 敏感信息双向加密的向量key条目的保护密码

		// keystore的位置com.lycheepay.keystore
		final static  String KEYSTORE_PATH = "classpath:com/stylefeng/guns/sharecore/common/utils/" + KEYSTORE_FILE_NAME;

	/**
	 * 
	 * <p>
	 * 老系统数据是先MD5加密，再使用BASE64Encoder转码，本方法是将老数据还原成MD5加密后的原始格式，非base64格式
	 * </p>
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 * 
	 */

	public static final byte[] decodeBASE64(String password) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		ByteBuffer b = decoder.decodeBufferToByteBuffer(password);
		return b.array();
	}

	/**
	 * <p>
	 * 登陆密码加密，默认使用UTF-8编码,先MD5,再获取盐加密
	 * </p>
	 * 
	 * @param password 明文，必须UTF-8编码
	 * @return 可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @author 罗君 (15914032477)
	 */

	public static final byte[] encodeLoginPassword(String password) throws Exception {
		byte[] md5Password = DigestUtils.md5Digest(password.getBytes());
		byte[] key = EncryptionUtils.getKeyByKeyStore(KEYSTORE_PATH, KEYSTORE_FILE_PASSWORD,
				SECRETLEVEL_LOGINPASSWORD_KEYNAME, LOGINPASSWORD_KEYPASSWORD);
		return EncryptionUtils.encodeGeneralPassword(md5Password, key);
	}

	/**
	 * <p>
	 * 支付密码加密，默认使用UTF-8编码,先MD5,再获取盐加密
	 * </p>
	 * 
	 * @param password 明文，必须UTF-8编码
	 * @return 可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @author 罗君 (15914032477)
	 */

	public static final byte[] encodePaymentPassword(String password) throws Exception {
		byte[] md5Password = DigestUtils.md5Digest(password.getBytes());
		byte[] key = EncryptionUtils.getKeyByKeyStore(KEYSTORE_PATH, KEYSTORE_FILE_PASSWORD,
				SECRETLEVEL_PAYMENTPASSWORD_KEYNAME, PAYMENTPASSWORD_KEYPASSWORD);
		return EncryptionUtils.encodeStrongPassword(md5Password, key);
	}

	/**
	 * 
	 * <p>
	 * 单向敏感信息加密，用于密保问题
	 * </p>
	 * 
	 * @param sensitiveInformation 明文，必须UTF-8编码
	 * @return
	 * 
	 */

	public static final String encodeSensitiveInformationOfSafeQuestion(String sensitiveInformation) throws Exception {
		byte[] key = EncryptionUtils.getKeyByKeyStore(KEYSTORE_PATH, KEYSTORE_FILE_PASSWORD,
				SECRETLEVEL_SENSITIVEINFORMATION_KEYNAME,
				SENSITIVEINFORMATION_KEYPASSWORD);
		byte[] encryptSensitiveInformation = EncryptionUtils.encodeSensitiveInformation(sensitiveInformation, key);
		// return new String(encryptSensitiveInformation, "UTF-8");
		return Hex.encodeHexString(encryptSensitiveInformation);
	}

	/**
	 * 
	 * <p>
	 * 双向敏感信息加密，用于加密需要解密的敏感信息，如身份证信息等
	 * </p>
	 * 
	 * @param sensitiveInformation 明文，必须UTF-8编码
	 * @return
	 * 
	 */

	public static final String encryptSensitiveInformationBidirectional(String sensitiveInformation) throws Exception {
		byte[] key = EncryptionUtils.getKeyByKeyStore(KEYSTORE_PATH, KEYSTORE_FILE_PASSWORD,
				SECRETLEVEL_SENSITIVEINFORMATIONBIDIRECTIONAL_KEYNAME,
				SENSITIVEINFORMATIONBIDIRECTIONAL_KEYPASSWORD);
		byte[] ivs = EncryptionUtils.getKeyByKeyStore(KEYSTORE_PATH, KEYSTORE_FILE_PASSWORD,
				SECRETLEVEL_IVS_KEYNAME, IVS_KEYPASSWORD);
		byte[] encryptSensitiveInformation = EncryptionUtils
				.encryptSensitiveInformation(sensitiveInformation, key, ivs);
		BASE64Encoder b64e = new BASE64Encoder();
		return b64e.encode(encryptSensitiveInformation);
	}

	/**
	 * 
	 * <p>
	 * 双向敏感信息解密，用于已加盐的敏感信息解密，如身份证信息等
	 * </p>
	 * 
	 * @param sensitiveInformation 已经过加密
	 * @return
	 * 
	 */

	public static final String decryptSensitiveInformationBidirectional(String sensitiveInformation) throws Exception {
		byte[] key = EncryptionUtils.getKeyByKeyStore(KEYSTORE_PATH, KEYSTORE_FILE_PASSWORD,
				SECRETLEVEL_SENSITIVEINFORMATIONBIDIRECTIONAL_KEYNAME,
				SENSITIVEINFORMATIONBIDIRECTIONAL_KEYPASSWORD);
		byte[] ivs = EncryptionUtils.getKeyByKeyStore(KEYSTORE_PATH, KEYSTORE_FILE_PASSWORD,
				SECRETLEVEL_IVS_KEYNAME, IVS_KEYPASSWORD);
		return EncryptionUtils.decryptSensitiveInformationToString(decodeBASE64(sensitiveInformation), key, ivs);
	}
	
	public static void main(String[] args) throws Exception {
		// 用于对身份证，银行账号等敏感信息加密
		String str = Base64.decode("X8m/5oaXi4Oz+Snrb/npBFnWc30b7H9PU6dxMEO4AgM=");
		/*String sensitiveInfo = Base64.encode(CIPEncryptionUtils.decryptSensitiveInformationBidirectional("MAD_1703090001").getBytes("utf-8"));
		System.out.println(sensitiveInfo);*/
		System.out.println(CIPEncryptionUtils.decryptSensitiveInformationBidirectional("X8m/5oaXi4Oz+Snrb/npBFnWc30b7H9PU6dxMEO4AgM="));
		//System.out.println(Base64.encode(CIPEncryptionUtils.encodeLoginPassword("888888888888")));
		
	}
}

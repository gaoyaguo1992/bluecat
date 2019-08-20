package com.stylefeng.guns.sharecore.common;

import com.stylefeng.guns.sharecore.common.persistence.model.WxAppUserInfoForEncrypted;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;

/**
 * 微信小程序信息获取
 * 
 * @author Administrator
 * @Date 2017年2月16日 11:56:08
 */
public class WXAppletUserInfo {

	public static boolean initialized = false;
	

	private static final Logger logger = LoggerFactory.getLogger(WXAppletUserInfo.class);
	/**
	 * 得到用户信息
	 */
	public static WXAppletUserInfo wxAppletUserInfo=null;
	/**
	 * 得到用户实例...
	 * @return
	 */
	public static WXAppletUserInfo getInstance(){
		if(wxAppletUserInfo==null){
			wxAppletUserInfo=new WXAppletUserInfo();
		}
		return wxAppletUserInfo;
	}

	/**
	 * AES解密
	 * 
	 * @param content
	 *            密文
	 * @return
	 * @throws NoSuchProviderException
	 */
	public static byte[] decrypt(byte[] dataByte, byte[] keyByte, byte[] ivByte) {
		 try {
             // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
          int base = 16;
          if (keyByte.length % base != 0) {
              int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
              byte[] temp = new byte[groups * base];
              Arrays.fill(temp, (byte) 0);
              System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
              keyByte = temp;
          }
          // 初始化
          Security.addProvider(new BouncyCastleProvider());
          Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
          SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
          AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
          parameters.init(new IvParameterSpec(ivByte));
          cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
          byte[] resultByte = cipher.doFinal(dataByte);
          return resultByte;
      } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
      } catch (NoSuchPaddingException e) {
          e.printStackTrace();
      } catch (InvalidParameterSpecException e) {
          e.printStackTrace();
      } catch (IllegalBlockSizeException e) {
          e.printStackTrace();
      } catch (BadPaddingException e) {
          e.printStackTrace();
      } catch (InvalidKeyException e) {
          e.printStackTrace();
      } catch (InvalidAlgorithmParameterException e) {
          e.printStackTrace();
      } catch (NoSuchProviderException e) {
          e.printStackTrace();
      }
      return null;
	}

	public static void initialize() {
		if (initialized)
			return;
		Security.addProvider(new BouncyCastleProvider());
		initialized = true;
	}

	// 生成iv
	public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
		AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
		params.init(new IvParameterSpec(iv));
		return params;
	}

	/**
	 * 解密用户敏感数据获取用户信息
	 * 
	 * @param sessionKey
	 *            数据进行加密签名的密钥
	 * @param encryptedData
	 *            包括敏感数据在内的完整用户信息的加密数据
	 * @param iv
	 *            加密算法的初始向量
	 * @return
	 */
	public WxAppUserInfoForEncrypted getUserInfo(String encryptedData, String sessionKey,String iv) {
		WxAppUserInfoForEncrypted userInfo = null;
		try {
		     // 被加密的数据
	        byte[] encryptedDataByte =org.apache.axis.encoding.Base64.decode(encryptedData);
	        byte[] sessionKeyByte =org.apache.axis.encoding.Base64.decode(sessionKey);
	        byte[] ivByte =org.apache.axis.encoding.Base64.decode(iv);
			byte[] resultByte = decrypt(encryptedDataByte, sessionKeyByte,ivByte);
			if (null != resultByte && resultByte.length > 0) {
				String userInfoStr = new String(resultByte, StandardCharsets.UTF_8);
				userInfo=com.alibaba.fastjson.JSON.parseObject(userInfoStr, WxAppUserInfoForEncrypted.class);
			}
		}catch(Exception e){
			logger.error(String.format("小程序解密用户敏感数据获取用户信息--getUserInfo,encryptedData=%s,sessionKey=%s,iv=%s"
					,encryptedData,sessionKey,iv), e);
		}
		return userInfo;
	}

}
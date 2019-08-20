package com.stylefeng.guns.sharecore.common.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ClassUtils;



/**
 * <P> 用于各种加密的工具类,例如登录密码加密,支付密码加密,敏感的明文数据等 </P>
 * 
 */
public abstract class EncryptionUtils {

	public static final String ENCRYPTION_RESULT_PREFIX = "{SOOFA-SHA}";
	/** 3DES算法名称 */
	public static final String TRIPLE_DES_ALGORITHM_NAME = "DESede";
	public static final String AES_KEY_ALGORITHM_NAME = "AES";
	/**
	 * ECB工作模式适合传递密钥时,对密钥进行加密,因为密钥比较短
	 */
	public static final String AES_CIPHER_ALGORITHM_NAME = "AES/CTR/PKCS7Padding";
	public static final String HMAC_MD5_ALGORITHM_NAME = "HmacMD5";
	public static final String HMAC_SHA224_ALGORITHM_NAME = "HmacSHA224";
	public static final String HMAC_SHA512_ALGORITHM_NAME = "HmacSHA512";
	public static final String TRIPLE_DES_TRANSFORMATION_NAME = "DESede/ECB/PKCS5Padding";
	public static final String CERTIFICATE_TYPE = "X.509";
	public static final String DEFAULT_CHARSET = "UTF-8";
	/**
	 * X509证书签名算法SHA1WithRSA
	 */
	public static final String RSA_SHA1_SIGNATURE_ALGORITHM_NAME = "SHA1WithRSA";
	/**
	 * X509证书签名算法MD5withRSA
	 */
	public static final String RSA_MD5_SIGNATURE_ALGORITHM_NAME = "MD5withRSA";

	private final static IvParameterSpec DEFAULT_IVS = new IvParameterSpec(new byte[] { 11, -19, 16, 33, -68, 88, 11,
			20, 24, 35, 68, 23, 60, 24, 29, 67 });
	public static final String DEFAULT_STRING_OUTPUT_TYPE = "base64";
	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	static {
		// 在调用Java API初始化相应的密钥工厂/密钥生成器等引擎类之前,手动注册Bouncy Castle(JCE提供者,实现了Java Cryptography
		// Extension接口包)
		Security.addProvider(new BouncyCastleProvider());
		System.err.println("Java security provider[" + Security.getProperty("BC") + "] is already installed");
	}

	/**
	 * <p> 查找指定pattern的资源 </p>
	 * 
	 * @param locationPattern 可以直接加载指定的文件,例如"file:C:/context.xml",也可以从classpath下加载,例如
	 *            "classpath:/context.xml" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器.另外,还可以在文件系统中查找相对于classpath的文件,例如:
	 *            "classpathfile:src/test/datas/example.xlsx",注意使用classpathfile时,相对路径不要以'/'开头
	 * 
	 * @throws IOException
	 * @see org.springframework.core.io.support.PathMatchingResourcePatternResolver
	 * 
	 */
	public static Resource[] getResources(String locationPattern) throws IOException {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(
				ClassUtils.getDefaultClassLoader());
		if (locationPattern.startsWith("classpathfile:")) {
			String path = locationPattern.substring("classpathfile:".length());
			URL url = ClassUtils.getDefaultClassLoader().getResource("");
			locationPattern = "file:" + url.getPath() + path;
		}
		return resolver.getResources(locationPattern);
	}
	
	/**
	 * <p> 根据算法名称生成HMAC密钥 </p>
	 * 
	 * @param algorithmName
	 * @param keySize 密钥长度 可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws NoSuchAlgorithmException
	 */
	public static SecretKey initHmacKey(String algorithmName, int keySize) throws NoSuchAlgorithmException {
		KeyGenerator generator = KeyGenerator.getInstance(algorithmName);
		generator.init(keySize);
		SecretKey key = generator.generateKey();
		return key;
	}

	/**
	 * <p> 随机生成HMAC + SHA224的密钥 </p>
	 * 
	 * @param keySize 密钥长度 可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws NoSuchAlgorithmException
	 */
	public static SecretKey initHmacSHA224Key(int keySize) throws NoSuchAlgorithmException {
		return initHmacKey(HMAC_SHA224_ALGORITHM_NAME, keySize);
	}

	/**
	 * <p> 随机生成HMAC + SHA224的密钥 </p>
	 * 
	 * @param keySize 密钥长度 可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws NoSuchAlgorithmException
	 */
	public static SecretKey initHmacSHA512Key(int keySize) throws NoSuchAlgorithmException {
		return initHmacKey(HMAC_SHA512_ALGORITHM_NAME, keySize);
	}

	/**
	 * <p> 随机生成HMAC-MD5的密钥 </p>
	 * 
	 * @param keySize 密钥长度 可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws NoSuchAlgorithmException
	 */
	public static SecretKey initHmacMD5Key(int keySize) throws NoSuchAlgorithmException {
		return initHmacKey(HMAC_MD5_ALGORITHM_NAME, keySize);
	}

	/**
	 * <p> 生成用于单向加密普通密码(例如登录密码)的密钥 </p>
	 * 
	 * @param keySize 密钥长度 可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws NoSuchAlgorithmException
	 */
	public static SecretKey initGeneralPasswordKey(int keySize) throws NoSuchAlgorithmException {
		return initHmacSHA224Key(keySize);
	}

	/**
	 * <p> 生成用于单向加密高强度密码的密钥 </p>
	 * 
	 * @param keySize 密钥长度 可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws NoSuchAlgorithmException
	 */
	public static SecretKey initStrongPasswordKey(int keySize) throws NoSuchAlgorithmException {
		return initHmacSHA512Key(keySize);
	}

	/**
	 * <p> 生成用于单向加密传输信息的密钥 </p>
	 * 
	 * @param keySize 密钥长度 可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws NoSuchAlgorithmException
	 */
	public static SecretKey initSensitiveInformationKey(int keySize) throws NoSuchAlgorithmException {
		return initHmacMD5Key(keySize);
	}

	/**
	 * <p> 生成用于双向加密传输信息的密钥 </p>
	 * 
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] initSensitiveInformationBidirectionalByteKey() throws Exception {
		SecretKey key = initSensitiveInformationBidirectionalKey();
		return key.getEncoded();
	}

	/**
	 * <p> 生成用于双向加密传输信息的密钥 </p>
	 * 
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static SecretKey initSensitiveInformationBidirectionalKey() throws Exception {
		KeyGenerator generator = KeyGenerator.getInstance(AES_KEY_ALGORITHM_NAME, "BC");
		generator.init(128);// AES要求密钥长度为128/192/256
		SecretKey key = generator.generateKey();
		return key;
	}

	/**
	 * <p> 根据指定HMAC算法计算摘要 </p>
	 * 
	 * @param algorithmName
	 * @param data 必须UTF-8编码
	 * @param key 可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static byte[] encodeHmac(String algorithmName, String data, byte[] key) throws Exception {
		// 还原密钥
		SecretKey secretKey = new SecretKeySpec(key, algorithmName);
		// 实例化MAC
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		// 初始化MAC
		mac.init(secretKey);
		// 执行消息摘要
		return mac.doFinal(getUtf8Bytes(data));
	}

	/**
	 * <p> 根据指定HMAC算法计算摘要 </p>
	 * 
	 * @param algorithmName
	 * @param data 必须UTF-8编码
	 * @param key 可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static byte[] encodeHmac(String algorithmName, byte[] data, byte[] key) throws Exception {
		// 还原密钥
		SecretKey secretKey = new SecretKeySpec(key, algorithmName);
		// 实例化MAC
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		// 初始化MAC
		mac.init(secretKey);
		// 执行消息摘要
		return mac.doFinal(data);
	}

	/**
	 * <p> 便捷的普通密码(例如登录密码)加密,安全级别足够普通密码(例如登录密码)需求,进行HmacSHA224加密,注意,默认使用UTF-8编码 </p>
	 * 
	 * @param password 必须UTF-8编码
	 * @param key 密钥 可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static byte[] encodeGeneralPassword(String password, byte[] key) throws Exception {
		return encodeHmac(HMAC_SHA224_ALGORITHM_NAME, password, key);
	}

	/**
	 * <p> 便捷的高强度密码加密,安全级别类似支付密码.注意,默认使用UTF-8编码 </p>
	 * 
	 * @param password
	 * @param key
	 * 
	 * @throws Exception
	 */
	public static byte[] encodeStrongPassword(String password, byte[] key) throws Exception {
		return encodeHmac(HMAC_SHA512_ALGORITHM_NAME, password, key);
	}

	/**
	 * <p> 便捷的高强度密码加密,安全级别类似支付密码.注意,默认使用UTF-8编码 </p>
	 * 
	 * @param password
	 * @param key
	 * 
	 * @throws Exception
	 */
	public static byte[] encodeStrongPassword(byte[] password, byte[] key) throws Exception {
		return encodeHmac(HMAC_SHA512_ALGORITHM_NAME, password, key);
	}

	/**
	 * <p> 加密高强度密码,从指定的keystore中获取密钥进行加密 </p>
	 * 
	 * @param password 待加密的密码明文
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 密钥对应的密码
	 * 
	 * @throws Exception
	 */
	public static byte[] encodeStrongPassword(String password, String keyStorePath, char[] keyStorePassword,
			String alias, char[] keyPasswork) throws Exception {
		byte[] key = getKeyByKeyStore(keyStorePath, keyStorePassword, alias, keyPasswork);
		return encodeHmac(HMAC_SHA512_ALGORITHM_NAME, password, key);
	}

	/**
	 * <p> 加密高强度密码,从指定的keystore中获取密钥进行加密 </p>
	 * 
	 * @param password 待加密的密码明文
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 密钥对应的密码
	 * 
	 * @throws Exception
	 */
	public static byte[] encodeStrongPassword(byte[] password, String keyStorePath, char[] keyStorePassword,
			String alias, char[] keyPasswork) throws Exception {
		byte[] key = getKeyByKeyStore(keyStorePath, keyStorePassword, alias, keyPasswork);
		return encodeHmac(HMAC_SHA512_ALGORITHM_NAME, password, key);
	}

	/**
	 * <p> 加密普通密码(例如登录密码),从指定的keystore中获取密钥进行加密(开销在Thinkpad T420S上,平均为17毫秒) </p>
	 * 
	 * @param password 待加密的密码明文
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 密钥对应的密码
	 * 
	 * @throws Exception
	 */
	public static byte[] encodeGeneralPassword(String password, String keyStorePath, char[] keyStorePassword,
			String alias, char[] keyPasswork) throws Exception {
		byte[] key = getKeyByKeyStore(keyStorePath, keyStorePassword, alias, keyPasswork);
		return encodeHmac(HMAC_SHA224_ALGORITHM_NAME, password, key);
	}

	/**
	 * <p> 加密普通密码(例如登录密码),从指定的keystore中获取密钥进行加密 </p>
	 * 
	 * @param password 待加密的密码明文
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 密钥对应的密码
	 * 
	 * @throws Exception
	 */
	public static byte[] encodeGeneralPassword(byte[] password, String keyStorePath, char[] keyStorePassword,
			String alias, char[] keyPasswork) throws Exception {
		byte[] key = getKeyByKeyStore(keyStorePath, keyStorePassword, alias, keyPasswork);
		return encodeHmac(HMAC_SHA224_ALGORITHM_NAME, password, key);
	}

	/**
	 * <p> 便捷的普通密码(例如登录密码)加密,安全级别足够普通密码(例如登录密码)需求,进行HmacSHA224加密,注意,默认使用UTF-8编码 </p>
	 * 
	 * @param password 必须UTF-8编码
	 * @param key 密钥 可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static byte[] encodeGeneralPassword(byte[] password, byte[] key) throws Exception {
		return encodeHmac(HMAC_SHA224_ALGORITHM_NAME, password, key);
	}

	/**
	 * <p> 单向加密敏感的信息,用于在传输过程中避免明文传输,从指定的keystore中获取密钥进行加密 </p>
	 * 
	 * @param rawSensitiveInformation 待加密的明文敏感信息,UTF-8编码
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 密钥对应的密码
	 * 
	 * @throws Exception
	 */
	public static byte[] encodeSensitiveInformation(String rawSensitiveInformation, String keyStorePath,
			char[] keyStorePassword, String alias, char[] keyPasswork) throws Exception {
		byte[] key = getKeyByKeyStore(keyStorePath, keyStorePassword, alias, keyPasswork);
		return encodeHmac(HMAC_MD5_ALGORITHM_NAME, rawSensitiveInformation, key);
	}

	/**
	 * <p> 单向加密敏感的信息,用于在传输过程中避免明文传输,从指定的keystore中获取密钥进行加密 </p>
	 * 
	 * @param rawSensitiveInformation 待加密的明文敏感信息,UTF-8编码
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 密钥对应的密码
	 * 
	 * @throws Exception
	 */
	public static byte[] encodeSensitiveInformation(byte[] rawSensitiveInformation, String keyStorePath,
			char[] keyStorePassword, String alias, char[] keyPasswork) throws Exception {
		byte[] key = getKeyByKeyStore(keyStorePath, keyStorePassword, alias, keyPasswork);
		return encodeHmac(HMAC_MD5_ALGORITHM_NAME, rawSensitiveInformation, key);
	}

	/**
	 * <p> 单向加密敏感的信息,用于在传输过程中避免明文传输 </p>
	 * 
	 * @param rawMessage 明文敏感信息,UTF-8编码
	 * @param key 密钥 可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static byte[] encodeSensitiveInformation(byte[] rawSensitiveInformation, byte[] key) throws Exception {
		return encodeHmac(HMAC_MD5_ALGORITHM_NAME, rawSensitiveInformation, key);
	}

	/**
	 * <p> 单向加密敏感的信息,用于在传输过程中避免明文传输 </p>
	 * 
	 * @param rawMessage 明文敏感信息,UTF-8编码
	 * @param key 密钥 可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static byte[] encodeSensitiveInformation(String rawSensitiveInformation, byte[] key) throws Exception {
		return encodeHmac(HMAC_MD5_ALGORITHM_NAME, rawSensitiveInformation, key);
	}

	/**
	 * <p> 转换密钥 </p>
	 * 
	 * @param key
	 * @param algorithmName
	 */
	public static SecretKey toKey(byte[] key, String algorithmName) {
		// 还原密钥
		SecretKey secretKey = new SecretKeySpec(key, algorithmName);
		return secretKey;
	}

	/**
	 * <p> 双向加密敏感信息,默认使用AES算法,从指定的keystore中获取密钥进行加盐 </p>
	 * 
	 * @param data 待加密的密码明文
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 密钥对应的密码
	 * @param ivs 初始化向量,必须16个元素的byte数组,如果为NULL,将使用默认值DEFAULT_IVS
	 * 
	 * @throws Exception
	 */
	public static byte[] encryptSensitiveInformation(byte[] data, String keyStorePath, char[] keyStorePassword,
			String alias, char[] keyPasswork, byte[] ivs) throws Exception {
		byte[] key = getKeyByKeyStore(keyStorePath, keyStorePassword, alias, keyPasswork);
		return encryptSensitiveInformation(data, key, ivs);
	}

	/**
	 * <p> 双向加密敏感信息,默认使用AES算法 </p>
	 * 
	 * @param data 需要UTF-8编码
	 * @param key
	 * @param ivs 初始化向量,必须16个元素的byte数组,如果为NULL,将使用默认值DEFAULT_IVS
	 *            可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws Exception
	 */
	public static byte[] encryptSensitiveInformation(byte[] data, byte[] key, byte[] ivs) throws Exception {
		// 还原密钥
		Key k = toKey(key, AES_KEY_ALGORITHM_NAME);
		// 使用bouncy castle提供的PKCS7Padding填充方式,需要明确指定提供者"BC",因为JDK默认不提供此填充方式
		Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM_NAME, "BC");
		// 初始化,设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k, ivs == null ? DEFAULT_IVS : new IvParameterSpec(ivs));
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * <p> 双向加密敏感信息,默认使用AES算法,从指定的keystore中获取密钥进行加盐 </p>
	 * 
	 * @param data 待加密的密码明文
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 密钥对应的密码
	 * @param ivs 初始化向量,必须16个元素的byte数组,如果为NULL,将使用默认值DEFAULT_IVS
	 * 
	 * @throws Exception
	 */
	public static byte[] encryptSensitiveInformation(String data, String keyStorePath, char[] keyStorePassword,
			String alias, char[] keyPasswork, byte[] ivs) throws Exception {
		byte[] key = getKeyByKeyStore(keyStorePath, keyStorePassword, alias, keyPasswork);
		return encryptSensitiveInformation(data, key, ivs);
	}

	/**
	 * <p> 双向加密敏感信息,默认使用AES算法 </p>
	 * 
	 * @param data 需要UTF-8编码
	 * @param key 密钥
	 * @param ivs 初始化向量,必须16个元素的byte数组,如果为NULL,将使用默认值DEFAULT_IVS
	 *            可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws Exception
	 */
	public static byte[] encryptSensitiveInformation(String data, byte[] key, byte[] ivs) throws Exception {
		// 还原密钥
		Key k = toKey(key, AES_KEY_ALGORITHM_NAME);
		// 使用bouncy castle提供的PKCS7Padding填充方式,需要明确指定提供者"BC",因为JDK默认不提供此填充方式
		Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM_NAME, "BC");
		// 初始化,设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k, ivs == null ? DEFAULT_IVS : new IvParameterSpec(ivs));
		// 执行操作
		return cipher.doFinal(getUtf8Bytes(data));
	}

	private static byte[] getUtf8Bytes(String data) throws UnsupportedEncodingException {
		return data.getBytes(StandardCharsets.UTF_8);
	}

	/**
	 * <p> 双向解密敏感信息,默认使用AES算法 </p>
	 * 
	 * @param data 需要UTF-8编码
	 * @param key 密钥
	 * @param ivs 初始化向量,必须16个元素的byte数组,如果为NULL,将使用默认值DEFAULT_IVS
	 *            可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws Exception
	 */
	public static byte[] decryptSensitiveInformation(byte[] data, byte[] key, byte[] ivs) throws Exception {
		// 还原密钥
		Key k = toKey(key, AES_KEY_ALGORITHM_NAME);
		// 使用bouncy castle提供的PKCS7Padding填充方式,需要明确指定提供者"BC",因为JDK默认不提供此填充方式
		Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM_NAME, "BC");
		// 初始化,设置为加密模式
		cipher.init(Cipher.DECRYPT_MODE, k, (ivs == null ? DEFAULT_IVS : new IvParameterSpec(ivs)));
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * <p> 双向解密敏感信息,默认使用AES算法 </p>
	 * 
	 * @param data 需要UTF-8编码
	 * @param key 密钥
	 * @param ivs 初始化向量,必须16个元素的byte数组,如果为NULL,将使用默认值DEFAULT_IVS
	 *            可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws Exception
	 */
	public static String decryptSensitiveInformationToString(byte[] data, byte[] key, byte[] ivs) throws Exception {
		byte[] info = decryptSensitiveInformation(data, key, ivs);
		return newStringUtf8(info);
	}

	/**
	 * <p> 双向解密敏感信息,默认使用AES算法,从指定的keystore中获取密钥进行加盐 </p>
	 * 
	 * @param data 待解密的数据
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 密钥对应的密码
	 * @param ivs 初始化向量,必须16个元素的byte数组,如果为NULL,将使用默认值DEFAULT_IVS
	 * 
	 * @throws Exception
	 */
	public static byte[] decryptSensitiveInformation(byte[] data, String keyStorePath, char[] keyStorePassword,
			String alias, char[] keyPasswork, byte[] ivs) throws Exception {
		byte[] key = getKeyByKeyStore(keyStorePath, keyStorePassword, alias, keyPasswork);
		return decryptSensitiveInformation(data, key, ivs);
	}

	/**
	 * <p> 双向解密敏感信息,默认使用AES算法,从指定的keystore中获取密钥进行加盐 </p>
	 * 
	 * @param data 待解密的数据
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 密钥对应的密码
	 * @param ivs 初始化向量,必须16个元素的byte数组,如果为NULL,将使用默认值DEFAULT_IVS
	 * 
	 * @throws Exception
	 */
	public static String decryptSensitiveInformationToString(byte[] data, String keyStorePath, char[] keyStorePassword,
			String alias, char[] keyPasswork, byte[] ivs) throws Exception {
		byte[] info = decryptSensitiveInformation(data, keyStorePath, keyStorePassword, alias, keyPasswork, ivs);
		return newStringUtf8(info);
	}

	/**
	 * <p> 双向解密敏感信息,默认使用AES算法 </p>
	 * 
	 * @param data 需要UTF-8编码
	 * @param key 密钥
	 * @param ivs 初始化向量,必须16个元素的byte数组,如果为NULL,将使用默认值DEFAULT_IVS
	 *            可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws Exception
	 */
	public static byte[] decryptSensitiveInformation(String data, byte[] key, byte[] ivs) throws Exception {
		// 还原密钥
		Key k = toKey(key, AES_KEY_ALGORITHM_NAME);
		// 使用bouncy castle提供的PKCS7Padding填充方式,需要明确指定提供者"BC",因为JDK默认不提供此填充方式
		Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM_NAME, "BC");
		// 初始化,设置为加密模式
		cipher.init(Cipher.DECRYPT_MODE, k, (ivs == null ? DEFAULT_IVS : new IvParameterSpec(ivs)));
		// 执行操作
		return cipher.doFinal(getUtf8Bytes(data));
	}

	/**
	 * <p> 双向解密敏感信息,默认使用AES算法 </p>
	 * 
	 * @param data 需要UTF-8编码
	 * @param key 密钥
	 * @param ivs 初始化向量,必须16个元素的byte数组,如果为NULL,将使用默认值DEFAULT_IVS
	 *            可使用org.apache.commons.codec.binary.Base64.encodeBase64String方法进行查看
	 * @throws Exception
	 */
	public static String decryptSensitiveInformationToString(String data, byte[] key, byte[] ivs) throws Exception {
		byte[] info = decryptSensitiveInformation(data, key, ivs);
		return newStringUtf8(info);
	}

	/**
	 * <p> 双向解密敏感信息,默认使用AES算法,从指定的keystore中获取密钥进行加盐 </p>
	 * 
	 * @param data 待解密的数据,UTF-8编码
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 密钥对应的密码
	 * @param ivs 初始化向量,必须16个元素的byte数组,如果为NULL,将使用默认值DEFAULT_IVS
	 * 
	 * @throws Exception
	 */
	public static byte[] decryptSensitiveInformation(String data, String keyStorePath, char[] keyStorePassword,
			String alias, char[] keyPasswork, byte[] ivs) throws Exception {
		byte[] key = getKeyByKeyStore(keyStorePath, keyStorePassword, alias, keyPasswork);
		return decryptSensitiveInformation(data, key, ivs);
	}

	/**
	 * <p> 双向解密敏感信息,默认使用AES算法,从指定的keystore中获取密钥进行加盐 </p>
	 * 
	 * @param data 待解密的数据,UTF-8编码
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 密钥对应的密码
	 * @param ivs 初始化向量,必须16个元素的byte数组,如果为NULL,将使用默认值DEFAULT_IVS
	 * 
	 * @throws Exception
	 */
	public static String decryptSensitiveInformationToString(String data, String keyStorePath, char[] keyStorePassword,
			String alias, char[] keyPasswork, byte[] ivs) throws Exception {
		byte[] info = decryptSensitiveInformation(data, keyStorePath, keyStorePassword, alias, keyPasswork, ivs);
		return newStringUtf8(info);
	}

	/**
	 * <p> 加载KeyStore </p>
	 * 
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param password 访问密钥库的密码
	 * @param keystoreType keystore的类型,如果为NULL,则默认使用KeyStore.getDefaultType()
	 * 
	 * @throws Exception
	 */
	public static KeyStore loadKeyStore(String keyStorePath, char[] password, String keystoreType) throws Exception {
		KeyStore ks = KeyStore.getInstance(keystoreType == null ? KeyStore.getDefaultType() : keystoreType);
		Resource[] resources = getResources(keyStorePath);
		if (resources.length == 1) {
			InputStream is = null;
			try {
				is = resources[0].getInputStream();
				ks.load(is, password);
				return ks;
			} finally {
				if (is != null) {
					is.close();
				}
			}
		} else if (resources.length < 1) {
			throw new IllegalArgumentException("In the path [" + keyStorePath + "], found less than one KeyStore");
		} else {
			throw new IllegalArgumentException("In the path [" + keyStorePath + "], found more than one KeyStore");
		}
	}

	/**
	 * <p> 从keystore中获取密钥 </p>
	 * 
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 密钥条目对应的密码
	 * 
	 * @throws Exception
	 */
	public static byte[] getKeyByKeyStore(String keyStorePath, char[] keyStorePassword, String alias, char[] keyPasswork)
			throws Exception {
		KeyStore keyStore = loadKeyStore(keyStorePath, keyStorePassword, "jceks");// 只有jceks类型才能保存单独的密钥
		SecretKeyEntry entry = (SecretKeyEntry) keyStore.getEntry(alias, new KeyStore.PasswordProtection(keyPasswork));
		return entry.getSecretKey().getEncoded();
	}

	/**
	 * <p> 从证书文件读取证书.'.crt'和'.cer'文件都可以读取 .cer是IE导出的公钥证书（der格式） </p>
	 * 
	 * @param certificatePath 证书文件路径:可以直接加载指定的文件,例如"file:C:/kft.cer",也可以从classpath下加载,例如
	 *            "classpath:/kft.cer" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @throws Exception
	 */
	public static Certificate getCertificate(String certificatePath) throws Exception {
		Resource[] resources = getResources(certificatePath);
		if (resources.length == 1) {
			InputStream inputStream = null;
			try {
				inputStream = resources[0].getInputStream();
				// 实例化证书工厂
				CertificateFactory cf = CertificateFactory.getInstance(CERTIFICATE_TYPE);
				Certificate cert = cf.generateCertificate(inputStream);
				return cert;
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		} else if (resources.length < 1) {
			throw new IllegalArgumentException("In the path [" + certificatePath + "], found less than one certificate");
		} else {
			throw new IllegalArgumentException("In the path [" + certificatePath + "], found more than one certificate");
		}
	}

	/**
	 * <p> 从证书文件读取证书 </p>
	 * 
	 * @param certificateContent 证书文件的2进制形式
	 * 
	 * @throws Exception
	 */
	public static Certificate getCertificate(byte[] certificateContent) throws Exception {
		InputStream inputStream = new ByteArrayInputStream(certificateContent);
		try {
			// 实例化证书工厂
			CertificateFactory cf = CertificateFactory.getInstance(CERTIFICATE_TYPE);
			Certificate cert = cf.generateCertificate(inputStream);
			return cert;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	/**
	 * <p> 从KeyStore中读取证书 </p>
	 * 
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * 
	 * @throws Exception
	 */
	public static Certificate getCertificate(String keyStorePath, char[] keyStorePassword, String alias)
			throws Exception {
		return getCertificate(null, keyStorePath, keyStorePassword, alias);
	}

	/**
	 * <p> 从KeyStore中读取证书 </p>
	 * 
	 * @param keystoreType keystore的类型 ."JKS"或"PKCS12"等.默认是JKS ,如果为NULL,则默认使用
	 *            java.security.KeyStore.getDefaultType()
	 * 
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * 
	 * @throws Exception
	 */
	public static Certificate getCertificate(String keystoreType, String keyStorePath, char[] keyStorePassword,
			String alias) throws Exception {
		KeyStore ks = loadKeyStore(keyStorePath, keyStorePassword, keystoreType);
		if (alias == null) {
			List<String> aliases = Collections.list(ks.aliases());
			if (aliases.size() == 1) {
				alias = aliases.get(0);
			} else {
				throw new IllegalArgumentException(
						"[Assertion failed] - this String argument[alias] must have text; it must not be null, empty, or blank");
			}
		}
		return ks.getCertificate(alias);
	}

	/**
	 * <p> 从密钥库KeyStore中获取私钥 </p>
	 * 
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 私钥条目对应的密码
	 * 
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKeyByKeyStore(String keyStorePath, char[] keyStorePassword, String alias,
			char[] keyPassword) throws Exception {
		KeyStore ks = loadKeyStore(keyStorePath, keyStorePassword, null);
		if (alias == null) {
			List<String> aliases = Collections.list(ks.aliases());
			if (aliases.size() == 1) {
				alias = aliases.get(0);
			} else {
				throw new IllegalArgumentException(
						"[Assertion failed] - this String argument[alias] must have text; it must not be null, empty, or blank");
			}
		}
		PrivateKey key = (PrivateKey) ks.getKey(alias, keyPassword);
		return key;
	}

	/**
	 * <p> 从证书中获取公钥 </p>
	 * 
	 * @param certificatePath 证书文件路径:可以直接加载指定的文件,例如"file:C:/kft.cer",也可以从classpath下加载,例如
	 *            "classpath:/kft.cer" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * 
	 * @throws Exception
	 */
	public static PublicKey getPublicKeyByCertificate(String certificatePath) throws Exception {
		Certificate x509Certificate = getCertificate(certificatePath);
		return x509Certificate.getPublicKey();
	}

	/**
	 * <p> 从证书中获取公钥 </p>
	 * 
	 * @param certificateContent 证书的2进制形式
	 * 
	 * 
	 * @throws Exception
	 */
	public static PublicKey getPublicKeyByCertificate(byte[] certificateContent) throws Exception {
		Certificate cert = getCertificate(certificateContent);
		return cert.getPublicKey();
	}

	/**
	 * <p> 使用从KeyStore中取出的私钥,对数据进行加密 </p>
	 * 
	 * @param data 待加密的数据
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 私钥条目对应的密码
	 * 
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String keyStorePath, char[] keyStorePassword, String alias,
			char[] keyPasswork) throws Exception {
		PrivateKey privateKey = getPrivateKeyByKeyStore(keyStorePath, keyStorePassword, alias, keyPasswork);
		// 对数据进行加密
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * <p> 利用从证书中取出的公钥,对私钥加密过的数据进行解密 </p>
	 * 
	 * @param data 待解密的数据
	 * @param certificatePath 证书文件路径:可以直接加载指定的文件,例如"file:C:/kft.cer",也可以从classpath下加载,例如
	 *            "classpath:/kft.cer" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 */
	public static byte[] decryptByPublicKey(byte[] data, String certificatePath) throws Exception {
		PublicKey publicKey = getPublicKeyByCertificate(certificatePath);
		// 对数据进行解密
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * <p> 利用从证书中取出的公钥,对数据进行加密 </p>
	 * 
	 * @param data 待加密的数据
	 * @param certificatePath 证书文件路径:可以直接加载指定的文件,例如"file:C:/kft.cer",也可以从classpath下加载,例如
	 *            "classpath:/kft.cer" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 */
	public static byte[] encryptByPublicKey(byte[] data, String certificatePath) throws Exception {
		PublicKey publicKey = getPublicKeyByCertificate(certificatePath);
		// 对数据进行加密
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * <p> 使用从KeyStore中取出的私钥,对私钥加密过的数据进行解密 </p>
	 * 
	 * @param data 待解密的数据
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPasswork 私钥条目对应的密码
	 * 
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String keyStorePath, char[] keyStorePassword, String alias,
			char[] keyPasswork) throws Exception {
		PrivateKey privateKey = getPrivateKeyByKeyStore(keyStorePath, keyStorePassword, alias, keyPasswork);
		// 对数据进行解密
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * <p> 签名,利用从证书里取出的签名算法和keystore的私钥,对数据进行签名.可以由公钥验证签名 </p> <p> 注意:只支持JKS类型的keystore </p>
	 * 
	 * @param data 待签名数据
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPassword 私钥条目对应的密码
	 * 
	 * @throws Exception
	 */
	public static byte[] signByX509Certificate(byte[] data, String keyStorePath, char[] keyStorePassword, String alias,
			char[] keyPassword) throws Exception {
		KeyStore ks = loadKeyStore(keyStorePath, keyStorePassword, null);
		if (alias == null) {
			List<String> aliases = Collections.list(ks.aliases());
			if (aliases.size() == 1) {
				alias = aliases.get(0);
			} else {
				throw new IllegalArgumentException(
						"[Assertion failed] - this String argument[alias] must have text; it must not be null, empty, or blank");
			}
		}
		X509Certificate x509Certificate = (X509Certificate) ks.getCertificate(alias);
		Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
		PrivateKey privateKey = (PrivateKey) ks.getKey(alias, keyPassword);
		// 初始化签名,由私钥构建
		signature.initSign(privateKey);
		signature.update(data);
		return signature.sign();
	}

	/**
	 * <p> 签名,利用从证书里取出的签名算法和keystore的私钥,对数据进行签名.可以由公钥验证签名 </p>
	 * 
	 * @param keystoreType keystore的类型,例如:JKS或者PKCS12(.pfx)等
	 * @param data 待签名数据
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPassword 私钥条目对应的密码
	 * 
	 * @throws Exception 如果提示Key must not be null,可能是alias错误
	 */
	public static byte[] signByX509Certificate(String keystoreType, byte[] data, String keyStorePath,
			char[] keyStorePassword, String alias, char[] keyPassword) throws Exception {
		KeyStore ks = loadKeyStore(keyStorePath, keyStorePassword, keystoreType);
		if (alias == null) {
			List<String> aliases = Collections.list(ks.aliases());
			if (aliases.size() == 1) {
				alias = aliases.get(0);
			} else {
				throw new IllegalArgumentException(
						"[Assertion failed] - this String argument[alias] must have text; it must not be null, empty, or blank");
			}
		}
		X509Certificate x509Certificate = (X509Certificate) ks.getCertificate(alias);
		Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
		PrivateKey privateKey = (PrivateKey) ks.getKey(alias, keyPassword);
		// 初始化签名,由私钥构建
		signature.initSign(privateKey);
		signature.update(data);
		return signature.sign();
	}

	/**
	 * <p> 签名,利用从证书里取出的签名算法和keystore的私钥,对数据进行签名.可以由公钥验证签名 </p> <p> 需要自己制定keystore的类型,所以支持JKS和PKCS12
	 * <p>
	 * 
	 * @param keystoreType keystore的类型,例如:JKS或者PKCS12(.pfx)等
	 * @param sigAlgName 签名算法 例如:"SHA1WithRSA","MD5withRSA".通常情况下,X509都是使用第一种算法
	 * @param data 待签名数据
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPassword 私钥条目对应的密码
	 * 
	 * @throws Exception 如果提示Key must not be null,可能是alias错误
	 */
	public static byte[] signByX509Certificate(String keystoreType, String sigAlgName, byte[] data,
			String keyStorePath, char[] keyStorePassword, String alias, char[] keyPassword) throws Exception {
		KeyStore ks = loadKeyStore(keyStorePath, keyStorePassword, keystoreType);
		Signature signature = Signature.getInstance(sigAlgName);
		if (alias == null) {
			List<String> aliases = Collections.list(ks.aliases());
			if (aliases.size() == 1) {
				alias = aliases.get(0);
			} else {
				throw new IllegalArgumentException(
						"[Assertion failed] - this String argument[alias] must have text; it must not be null, empty, or blank");
			}
		}
		PrivateKey privateKey = (PrivateKey) ks.getKey(alias, keyPassword);
		// 初始化签名,由私钥构建
		signature.initSign(privateKey);
		signature.update(data);
		return signature.sign();
	}

	/**
	 * <p> 签名,利用从证书里取出的签名算法和keystore的私钥,对数据进行签名.可以由公钥验证签名 </p> <p> 注意:只支持JKS类型的keystore </p>
	 * 
	 * @param data 待签名数据
	 * @param keyStorePath
	 *            keystore文件路径:可以直接加载指定的文件,例如"file:C:/KFTCIPKeystore.keystore",也可以从classpath下加载,例如
	 *            "classpath:/KFTCIPKeystore.keystore" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * @param keyPassword 私钥条目对应的密码
	 * @param x509Certificate 证书对象
	 * 
	 * @throws Exception
	 */
	public static byte[] signByX509Certificate(byte[] data, String keyStorePath, char[] keyStorePassword, String alias,
			char[] keyPassword, X509Certificate x509Certificate) throws Exception {
		Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
		PrivateKey privateKey = getPrivateKeyByKeyStore(keyStorePath, keyStorePassword, alias, keyPassword);
		// 初始化签名,由私钥构建
		signature.initSign(privateKey);
		signature.update(data);
		return signature.sign();
	}

	/**
	 * <p> 验证签名,从keystore中获取公钥来验证签名是否正确 </p>
	 * 
	 * 
	 * @param data 传输的数据
	 * @param sign 对传输数据的签名
	 * @param keyStorePath
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * 
	 * @throws Exception
	 */
	public static boolean verifySign(byte[] data, byte[] sign, String keyStorePath, char[] keyStorePassword,
			String alias) throws Exception {
		X509Certificate certificate = (X509Certificate) getCertificate(keyStorePath, keyStorePassword, alias);
		Signature signature = Signature.getInstance(certificate.getSigAlgName());
		// 由证书初始化签名,使用了证书中的公钥
		signature.initVerify(certificate);
		signature.update(data);
		return signature.verify(sign);
	}

	/**
	 * <p> 验证签名,从keystore中获取公钥来验证签名是否正确 </p>
	 * 
	 * @param data 传输的数据
	 * @param sign 对传输数据的签名
	 * @param keystoreType keystore的类型 ."JKS"或"PKCS12"等.默认是JKS ,如果为NULL,则默认使用
	 *            java.security.KeyStore.getDefaultType()
	 * @param keyStorePath
	 * @param keyStorePassword 访问密钥库的密码
	 * @param alias 证书条目(包含公钥,私钥和数字证书)的别名.当keystore中只有一个条目时,此参数可以为null;如果有多个时,不能为null
	 * 
	 * @throws Exception
	 */
	public static boolean verifySign(byte[] data, byte[] sign, String keystoreType, String keyStorePath,
			char[] keyStorePassword, String alias) throws Exception {
		X509Certificate certificate = (X509Certificate) getCertificate(keystoreType, keyStorePath, keyStorePassword,
				alias);
		Signature signature = Signature.getInstance(certificate.getSigAlgName());
		// 由证书初始化签名,使用了证书中的公钥
		signature.initVerify(certificate);
		signature.update(data);
		return signature.verify(sign);
	}

	/**
	 * <p> 验证签名,从证书中获取公钥来验证签名是否正确 </p>
	 * 
	 * @param data 传输的数据
	 * @param sign 对传输数据的签名
	 * @param certificatePath 证书文件路径:可以直接加载指定的文件,例如"file:C:/kft.cer",也可以从classpath下加载,例如
	 *            "classpath:/kft.cer" 支持ANT语法 注意:从classpath下加载时,优先使用 thread context
	 *            ClassLoader,没有找到的情况下,使用当前类加载器
	 * 
	 * @throws Exception
	 */
	public static boolean verifySign(byte[] data, byte[] sign, String certificatePath) throws Exception {
		X509Certificate certificate = (X509Certificate) getCertificate(certificatePath);
		Signature signature = Signature.getInstance(certificate.getSigAlgName());
		// 由证书初始化签名,使用了证书中的公钥
		signature.initVerify(certificate);
		signature.update(data);
		return signature.verify(sign);
	}

	/**
	 * <p> 验证签名,从证书中获取公钥来验证签名是否正确 </p>
	 * 
	 * @param plaintext 传输的数据
	 * @param signature 对传输数据的签名
	 * @param certificateContent 证书内容的2进制形式
	 * 
	 * @throws Exception
	 */
	public static boolean verifySign(byte[] plaintext, byte[] signature, byte[] certificateContent) throws Exception {
		X509Certificate certificate = (X509Certificate) getCertificate(certificateContent);
		Signature verifier = Signature.getInstance(certificate.getSigAlgName());
		// 由证书初始化签名,使用了证书中的公钥
		verifier.initVerify(certificate);
		verifier.update(plaintext);
		return verifier.verify(signature);
	}

	/**
	 * <p> 验证签名,从证书中获取公钥来验证签名是否正确 </p>
	 * 
	 * @param plaintext 传输的数据
	 * @param signature 对传输数据的签名
	 * @param certificate 证书对象
	 * 
	 * @throws Exception
	 */
	public static boolean verifySign(byte[] plaintext, byte[] signature, Certificate certificate) throws Exception {
		X509Certificate x509certificate = (X509Certificate) certificate;
		Signature verifier = Signature.getInstance(x509certificate.getSigAlgName());
		// 由证书初始化签名,使用了证书中的公钥
		verifier.initVerify(x509certificate);
		verifier.update(plaintext);
		return verifier.verify(signature);
	}

	/**
	 * <p> 判断是否是经过base64编码 </p>
	 * 
	 * @param msg true表示是经过base编码的
	 */
	public static boolean isBase64(String msg) {
		return Base64.isBase64(msg);
	}

	/**
	 * <p> 判断是否是经过base64编码 </p>
	 * 
	 * @param msg true表示是经过base编码的
	 */
	public static boolean isBase64(byte[] msg) {
		return Base64.isBase64(msg);
	}

	/**
	 * <p> 判断是否是经过base64编码 </p>
	 * 
	 * @param msg true表示是经过base编码的
	 */
	public static boolean isBase64(byte msg) {
		return Base64.isBase64(msg);
	}

	/**
	 * <p> 解码base64编码的数据 </p>
	 * 
	 * @param base64String 被base64编码前必须是 UTF-8编码
	 * 
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeBase64(String base64String) throws UnsupportedEncodingException {
		byte[] decoded = new Base64().decode(base64String);
		return newStringUtf8(decoded);
	}

	public static String newStringUtf8(byte[] bytes) throws UnsupportedEncodingException {
		return newString(bytes, CharEncoding.UTF_8);
	}
	
	/**
	 * <p> 计算文件的MD5摘要,支持大文件 </p>
	 * 
	 * @param file 可以通过base64转成String或转成16进制String
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] getFileMD5(File file) throws IOException, NoSuchAlgorithmException {
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			FileChannel ch = in.getChannel();
			// 700000000 bytes are about 670M
			int maxSize = 700000000;
			long startPosition = 0L;
			long step = file.length() / maxSize;
			MessageDigest md5MessageDigest = null;
			md5MessageDigest = MessageDigest.getInstance("MD5");
			if (step == 0) {
				MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
				md5MessageDigest.update(byteBuffer);
				return md5MessageDigest.digest();
			}
			for (int i = 0; i < step; i++) {
				MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, startPosition, maxSize);
				md5MessageDigest.update(byteBuffer);
				startPosition += maxSize;
			}
			if (startPosition == file.length()) {
				return md5MessageDigest.digest();
			}
			MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, startPosition, file.length()
					- startPosition);
			md5MessageDigest.update(byteBuffer);
			return md5MessageDigest.digest();
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	/**
	 * <p> 计算文件的MD5摘要,支持大文件 </p>
	 * 
	 * @param file 16进制字符串
	 */
	public static String getFileMD5Hex(File file) throws IOException, NoSuchAlgorithmException {
		byte[] md5 = getFileMD5(file);
		return bufferToHex(md5);
	}

	/**
	 * <p> 计算文件的MD5摘要,支持大文件 </p>
	 * 
	 * @param file
	 * @param charsetName 对MD5结果先进行base编码,再按指定字符集转换成String
	 */
	public static String getFileMD5Base64(File file, String charsetName) throws IOException, NoSuchAlgorithmException {
		byte[] md5 = getFileMD5(file);
		byte[] encodeBase64 = Base64.encodeBase64(md5, false, true);
		String newString = newString(encodeBase64, charsetName);
		return newString;
	}

	public static String newString(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
		if (bytes == null) {
			return null;
		}
		return new String(bytes, charsetName);
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuilder sb = new StringBuilder(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], sb);
		}
		return sb.toString();
	}

	private static void appendHexPair(byte bt, StringBuilder sb) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		sb.append(c0);
		sb.append(c1);
	}

	/**
	 * <p> 获得指定字符串的MD5摘要,charset可以为null,如果为null则默认采用UTF-8编码 </p>
	 */
	public static String md5Encrypt(String msg, String charset) throws UnsupportedEncodingException,
			NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(msg.getBytes(charset == null ? "UTF-8" : charset));
		byte[] digest = md5.digest();
		return convertToHexString(digest);

	}

	/**
	 * 将字节数组转换为16进制字符串
	 * 
	 * @param digest
	 */
	private static String convertToHexString(byte[] digest) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < digest.length; i++) {
			result.append(Integer.toHexString((0x000000ff & digest[i]) | 0xffffff00).substring(6));
		}
		return result.toString();

	}
}

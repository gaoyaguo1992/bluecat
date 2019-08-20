package com.stylefeng.guns.sharecore.common;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import org.apache.http.conn.ssl.SSLContexts;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;

public class WxMpInMemoryConfigStorageExtend extends WxMpInMemoryConfigStorage implements InitializingBean {
	/**
	 * 微信支付证书文件路径
	 */
	@Value("${cer.keystore.file}")
	private String keyStoreFile;
	/**
	 * 微信支付证快路径
	 */
	@Value("${cer.keystore.password}")
	private String keystorePassword;

	@Override
	public void afterPropertiesSet() throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		// 读取本机存放的PKCS12证书文件
		FileInputStream instream = new FileInputStream(new File(keyStoreFile));
		try {
			// 指定PKCS12的密码(商户ID)
			keyStore.load(instream, keystorePassword.toCharArray());
		} finally {
			instream.close();
		}
		super.sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, keystorePassword.toCharArray()).build();
	}

}

/**
 * @CreateDate: 创建时间 2015-8-19 上午8:47:03
 * @author    : 作者 张霖
 * @see 包名.参考类名	(列出父类，引用)
 *
 * @history
 *  Sr Date 			Modified By  		Why & What is modified
 *	2015-8-19			    张霖				new
 */
package com.stylefeng.guns.sharecore.common.utils;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stylefeng.guns.sharecore.common.persistence.model.CustAccountModel;

/**
 * 
 * <P>TODO</P>
 */
public class SysUtil {
	static Logger logger = LoggerFactory.getLogger(SysUtil.class);

	private static final String SEPARATOR = "|";

	public static String getDigest(CustAccountModel cam) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(cam.getAccountId()).append(SEPARATOR);
		sb.append(nullToZero(cam.getBalance())).append(SEPARATOR);
		sb.append(nullToZero(cam.getAvailableBalance())).append(SEPARATOR);
		sb.append(nullToZero(cam.getFrozenBalance()));
		return CIPEncryptionUtils.encodeSensitiveInformationOfSafeQuestion(sb.toString());
	}

	public static String getStrDevId(Long devId,String clientId){
		if(clientId.indexOf('D')!=-1){
			return "D"+devId.toString();
		}else{
			return devId.toString();
		}
	}
	
	public static Long getLongDevId(String devId){
		if(devId.indexOf('D')!=-1){
			return Long.valueOf(devId.substring(1));
		}else{
			return Long.valueOf(devId);					
		}
	}
	
	private static BigDecimal nullToZero(BigDecimal amount) {
		return amount != null ? format(amount) : BigDecimal.ZERO;
	}
	
	public static BigDecimal format(BigDecimal value) {
		return value.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public static boolean checkDigest(CustAccountModel cam ,String dataDigest) {
		try {			
			if(cam.getBalance().compareTo(cam.getAvailableBalance().add(cam.getFrozenBalance()))!=0){
				return false;
			}
			String digest = getDigest(cam);
			/*logger.info("old ma.getDataDigest: {}", dataDigest);
			logger.info("new digest: {}", digest);*/
			
			//TODO: 以后再校验
			return true;
		} catch (Exception e) {
			logger.error("校验额摘要异常", e);
			return false;
		}
	}
	
	public static boolean isNotBalance(CustAccountModel cam) {

        return cam.getBalance().compareTo(cam.getAvailableBalance().add(cam.getFrozenBalance())) != 0;
			
	}
	
	public static final String getIpAddress(HttpServletRequest request) {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

		String ip = request.getHeader("X-Forwarded-For");
		if (logger.isInfoEnabled()) {
			logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
				}
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}
	
}

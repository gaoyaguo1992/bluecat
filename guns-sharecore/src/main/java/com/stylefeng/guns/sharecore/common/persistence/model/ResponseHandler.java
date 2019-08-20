package com.stylefeng.guns.sharecore.common.persistence.model;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.core.util.TenpayUtil;
import com.stylefeng.guns.core.util.XmlUtil;

import java.io.*;
import java.util.*;

/**
 * 应答处理类 应答处理类继承此类，重写isTenpaySign方法即可。
 * 
 * @author miklchen
 *
 */
public class ResponseHandler {

	/** 密钥 */
	private String key;

	/** 应答的参数 */
	private SortedMap parameters;

	private HttpServletRequest request;

	private HttpServletResponse response;

	private String uriEncoding;

	/**
	 * 构造函数
	 * 
	 * @param request
	 * @param response
	 */
	public ResponseHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.request = request;
		this.response = response;

		this.key = "";
		this.parameters = new TreeMap();

		this.uriEncoding = "";

		// Map m = this.request.getParameterMap();
		InputStream inputStream;
		inputStream = request.getInputStream();
		String sb = inputStream2String(inputStream);

		inputStream.close();

		Map<String, String> m = new HashMap<String, String>();
		m = XmlUtil.xmlResult(sb);

		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String k = (String) it.next();
			String v = m.get(k);
			this.setParameter(k, v);
		}

	}

	/**
	 * 构造方法，传入字符串形式xml，支付成功通知
	 * 
	 * @param xmlResult
	 */
	public ResponseHandler(String xmlResult) {
		this.key = "";
		this.parameters = new TreeMap();

		this.uriEncoding = "";
		Map<String, String> m = new HashMap<String, String>();
		m = XmlUtil.xmlResult(xmlResult);

		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String k = (String) it.next();
			String v = m.get(k);
			this.setParameter(k, v);
		}
	}

	/**
	 * 获取密钥
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置密钥
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取参数值
	 * 
	 * @param parameter
	 *            参数名称
	 * @return String
	 */
	public String getParameter(String parameter) {
		String s = (String) this.parameters.get(parameter);
		return (null == s) ? "" : s;
	}

	/**
	 * 设置参数值
	 * 
	 * @param parameter
	 *            参数名称
	 * @param parameterValue
	 *            参数值
	 */
	public void setParameter(String parameter, String parameterValue) {
		String v = "";
		if (null != parameterValue) {
			v = parameterValue.trim();
		}
		this.parameters.put(parameter, v);
	}

	/**
	 * 返回所有的参数
	 * 
	 * @return SortedMap
	 */
	public SortedMap getAllParameters() {
		return this.parameters;
	}

	/**
	 * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * 
	 * @return boolean
	 */
	public boolean isTenpaySign() {
		StringBuffer sb = new StringBuffer();
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}

		sb.append("key=" + this.getKey());

		// 算出摘要
		String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
		String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();

		String tenpaySign = this.getParameter("sign").toLowerCase();

		System.out.println(tenpaySign + "    " + sign);
		return tenpaySign.equals(sign);
	}

	/**
	 * 返回处理结果给财付通服务器。
	 * 
	 * @param msg:
	 *            Success or fail。
	 * @throws IOException
	 */
	public void sendToCFT(String msg) throws IOException {
		String strHtml = msg;
		PrintWriter out = this.getHttpServletResponse().getWriter();
		out.println(strHtml);
		out.flush();
		out.close();
	}

	/**
	 * 获取uri编码
	 * 
	 * @return String
	 */
	public String getUriEncoding() {
		return uriEncoding;
	}

	/**
	 * 设置uri编码
	 * 
	 * @param uriEncoding
	 * @throws UnsupportedEncodingException
	 */
	public void setUriEncoding(String uriEncoding) throws UnsupportedEncodingException {
		if (!"".equals(uriEncoding.trim())) {
			this.uriEncoding = uriEncoding;

			// 编码转换
			String enc = TenpayUtil.getCharacterEncoding(request, response);
			Iterator it = this.parameters.keySet().iterator();
			while (it.hasNext()) {
				String k = (String) it.next();
				String v = this.getParameter(k);
				v = new String(v.getBytes(uriEncoding.trim()), enc);
				this.setParameter(k, v);
			}
		}
	}

	protected HttpServletRequest getHttpServletRequest() {
		return this.request;
	}

	protected HttpServletResponse getHttpServletResponse() {
		return this.response;
	}

	private String inputStream2String(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}
}
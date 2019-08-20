package com.stylefeng.guns.rest.modular.model;

/**
 *
 */
public class SimpleRespMqMsg extends BaseDeviceMqMsg {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4904713438436271274L;
	private String result;
	private String errorDesc;
	private String errorCode;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String rsltDesc) {
		this.errorDesc = rsltDesc;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
		
	
}

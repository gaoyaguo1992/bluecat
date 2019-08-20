package com.stylefeng.guns.sharecore.modular.system.exception;

/**
 * <P>
 * common 模块根异常
 * </P>
 */
public class ExgrainBizUncheckedException extends ExgrainUncheckedException {
	
	/**
	 * TODO
	 */
	private static final long serialVersionUID = 44745061280882617L;
	/**
	 * 异常的唯一编号,对应国际化消息KEY
	 */
	protected String preCode = "";
	/**
	 * 
	 * @param defaultMessage
	 */
	public ExgrainBizUncheckedException(String defaultMessage) {
		super(defaultMessage);
	}
	
	/**
	 * <p> 构造 </p>
	 */
	public ExgrainBizUncheckedException(String code, String[] params, String defaultMessage) {
		super(defaultMessage);
		this.params = params;
		this.code = preCode + code;
	}

	/**
	 * <p> 构造类 </p>
	 */
	public ExgrainBizUncheckedException(String code, String[] params, String defaultMessage, Throwable cause) {
		super(defaultMessage, cause);
		this.params = params;
		this.code = preCode + code;
	}
}

package com.stylefeng.guns.sharecore.modular.system.exception;

/**
 * <P> common 模块根异常 </P>
 * 
 */
public class ExgrainBizCheckedException extends ExgrainCheckedException {

	/**
	 * TODO
	 */
	private static final long serialVersionUID = -3976393040591969115L;
	/**
	 * 异常的唯一编号,对应国际化消息KEY
	 */
	protected String preCode = "";

	// 系统异常信息
	protected String sysMessage;

	// 用户提示信息
	protected String userMessage;

	/**
	 * <p> Method for constructor </p>
	 * 
	 * @param code 异常的全局唯一编号,可以作为国际化消息的KEY.不可以为NULL
	 * @param params 国际化消息中填充占位符的值,可以为NULL
	 * @param defaultMessage 默认显示的消息,当国际化消息文件中没有找到对应的条目时会显示此默认消息值,可以为NULL
	 */
	public ExgrainBizCheckedException(String code, String sysMessage, String userMessage) {
		super(sysMessage);
		this.code = preCode + code;
		this.params = null;
		this.sysMessage = sysMessage;
		this.userMessage = userMessage;
	}

	/**
	 * <p> Method for constructor </p>
	 * 
	 * @param code 异常的全局唯一编号,可以作为国际化消息的KEY.不可以为NULL
	 * @param params 国际化消息中填充占位符的值,可以为NULL
	 * @param defaultMessage 默认显示的消息,当国际化消息文件中没有找到对应的条目时会显示此默认消息值,可以为NULL
	 */
	public ExgrainBizCheckedException(String code, String sysMessage, String userMessage, Throwable cause) {
		super(sysMessage, cause);
		this.code = preCode + code;
		this.params = null;
		this.sysMessage = sysMessage;
		this.userMessage = userMessage;
	}

	public String getSysMessage() {
		return sysMessage;
	}

	public String getUserMessage() {
		return userMessage;
	}

}

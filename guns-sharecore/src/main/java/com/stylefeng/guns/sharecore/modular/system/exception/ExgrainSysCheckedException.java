package com.stylefeng.guns.sharecore.modular.system.exception;


/**
 * <P>
 * common 模块根异常
 * </P>
 * 
 * 
 */
public class ExgrainSysCheckedException extends ExgrainCheckedException {

	/**
	 * TODO
	 */
	private static final long serialVersionUID = -3976393040591969115L;
	/**
	 * 异常的唯一编号,对应国际化消息KEY
	 */
	protected String preCode = "";

	/**
	 * <p> Method for constructor </p>
	 * 
	 * @param code 异常的全局唯一编号,可以作为国际化消息的KEY.不可以为NULL
	 * @param params 国际化消息中填充占位符的值,可以为NULL
	 * @param defaultMessage 默认显示的消息,当国际化消息文件中没有找到对应的条目时会显示此默认消息值,可以为NULL
	 */
	public ExgrainSysCheckedException(String code, String[] params, String defaultMessage) {
		super(defaultMessage);
		this.code = preCode + code;
		this.params = params;
	}

	/**
	 * <p> Method for constructor </p>
	 * 
	 * @param code 异常的全局唯一编号,可以作为国际化消息的KEY.不可以为NULL
	 * @param params 国际化消息中填充占位符的值,可以为NULL
	 * @param defaultMessage 默认显示的消息,当国际化消息文件中没有找到对应的条目时会显示此默认消息值,可以为NULL
	 * @param cause 需要包装的异常对象,不可以为NULL
	 */
	public ExgrainSysCheckedException(String code, String[] params, String defaultMessage, Throwable cause) {
		super(defaultMessage, cause);
		this.code = preCode + code;
		this.params = params;
	}

}

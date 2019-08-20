package com.stylefeng.guns.sharecore.modular.system.exception;

import java.util.Date;

import org.apache.commons.lang.LocaleUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.support.ResourceBundleMessageSource;


/**
 * <P>TODO</P>
 * 
 */
@Aspect
public class ExgrainExceptionHandlerInterceptor {
	private final static Logger logger = LoggerFactory.getLogger(ExgrainExceptionHandlerInterceptor.class);

	
	private ResourceBundleMessageSource messageSource;
	
	

	public void setMessageSource(ResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * the locale String to convert, null returns null
	 * 查看日志的管理员所需要的locale.一般情况下,为国际化消息文件命名中的locale部分,例如zh_CN,en_US 必须在properties文件中配置
	 */
	private String localeString;

	/**
	 * @param localeString the localeString to set
	 */
	public void setLocaleString(String localeString) {
		this.localeString = localeString;
	}

	/**
	 * <p> 在service层拦截所有应用层异常,进行如下处理:<br> 1.检查异常的类型是否合法,并进行统一封装<br> 2.将详细的异常信息包装到异常类中<br>
	 * 3.打印异常日志<br> 4.发布异常事件<br> 5.调用所有异常转换器,对异常进行转换<br> 6.将上述处理完成的异常重新抛出 </p>
	 * 
	 * @param e 拦截到的异常
	 * @throws Throwable
	 * 
	 */
	
	@AfterThrowing(pointcut = "execution(* com.ceg.dl.app.test.*.*(..))",throwing="e")
	public void handleAppException(JoinPoint jp, Throwable e) throws Throwable {
		// 如果是业务状态异常,不属于系统异常,不需要对其进行额外的处理
		if (!(e instanceof ExgrainBizCheckedException) && !(e instanceof ExgrainBizUncheckedException)
				&& !(e instanceof ExgrainSysCheckedException) && !(e instanceof ExgrainSysUncheckedException)) {
			throw e;
		}

		if ((e instanceof ExgrainBizCheckedException) || (e instanceof ExgrainSysCheckedException)) {
			handleCheckException(jp, (ExgrainCheckedException) e);
		} else {
			handleUncheckException(jp, (ExgrainUncheckedException) e);
		}

	}

	private void handleCheckException(JoinPoint jp, ExgrainCheckedException e) throws Throwable {
		Signature s = jp.getSignature();
		if (logger.isDebugEnabled()) {
			logger.debug("Catch a exception[{}] when invoke method[{}]",
					new Object[] { e.getClass().getName(), s.getDeclaringTypeName() + "." + s.getName() });
		}
		if (e.handled) {
			throw e;
		} else {
			e.setHandled(true);
			buildCheckedExceptionInfo(jp, e);
			throw e;
		}
	}

	private void handleUncheckException(JoinPoint jp, ExgrainUncheckedException e) {
		Signature s = jp.getSignature();
		if (logger.isDebugEnabled()) {
			logger.debug("Catch a exception[{}] when invoke method[{}]",
					new Object[] { e.getClass().getName(), s.getDeclaringTypeName() + "." + s.getName() });
		}
		if (e.handled) {
			throw e;
		} else {
			e.setHandled(true);
			buildUncheckedExceptionInfo(jp, e);
			throw e;
		}
	}

	/**
	 * <p> 从拦截点和slf4j的本地线程变量中获取发生异常时的相关信息 </p> <p>
	 * 注意:需要先行在slf4j的MDC中注入变量值,例如org.soofa.web.base.filter.MDCInsertingServletFilter </p>
	 * 
	 * 
	 */
	private void buildCheckedExceptionInfo(JoinPoint jp, ExgrainCheckedException ex) {

		Signature s = jp.getSignature();
		ex.setClassName(s.getDeclaringTypeName());

		ex.setLocale(LocaleUtils.toLocale(localeString));
		ex.setMethodName(s.getName());
		ex.setRemoteAddress(MDC.get("req.xForwardedFor"));

		ex.setI18nMessage(resolveMessage(ex.getCode(), ex.getParams()));
		// 此处在远程调用时,会导致调用方class not found异常,因为把服务提供方的参数类保存在异常中,导致调用方无法反序列化这些类
		ex.setParameters(convertArgsToString(jp.getArgs()));
		ex.setLogged(ex.isLogged());
		ex.setTime(new Date());

	}
	
	/**
	 * <p> 从拦截点和slf4j的本地线程变量中获取发生异常时的相关信息 </p> <p>
	 * 注意:需要先行在slf4j的MDC中注入变量值,例如org.soofa.web.base.filter.MDCInsertingServletFilter </p>
	 * 
	 * 
	 */
	private void buildUncheckedExceptionInfo(JoinPoint jp, ExgrainUncheckedException ex) {

		Signature s = jp.getSignature();
		ex.setClassName(s.getDeclaringTypeName());

		ex.setLocale(LocaleUtils.toLocale(localeString));
		ex.setMethodName(s.getName());
		ex.setRemoteAddress(MDC.get("req.xForwardedFor"));

		ex.setI18nMessage(resolveMessage(ex.getCode(), ex.getParams()));
		// 此处在远程调用时,会导致调用方class not found异常,因为把服务提供方的参数类保存在异常中,导致调用方无法反序列化这些类
		ex.setParameters(convertArgsToString(jp.getArgs()));
		ex.setLogged(ex.isLogged());
		ex.setTime(new Date());

	}

	/**
	 * <p> 必须把参数对象转成string,便于远程调用时进行反序列化,避免调用方因为没有引入抛异常的方法的参数类型而造成class not found异常 </p>
	 * 
	 * @param args
	 * 
	 */
	private String[] convertArgsToString(Object[] args) {
		String[] argsStrs = new String[args.length];
		for (int i = 0; i < args.length; i++) {
			if (args[i] != null) {
				argsStrs[i] = args[i].toString();
			} else {
				argsStrs[i] = "null";
			}

		}
		return argsStrs;
	}

	/**
	 * <p> 根据异常code解析并构造国际化消息 </p>
	 * 
	 * @param code
	 * @param params
	 * @return
	 * 
	 */
	private String resolveMessage(String code, String[] params) {
		// spring的ApplicationContext接口继承了MessageSource接口，所以可以直接获取资源信息
		String message = messageSource.getMessage(code, params, LocaleUtils.toLocale(localeString));
		if (logger.isDebugEnabled()) {
			logger.debug("The i18n key=[{}], message = [{}]", new Object[] { code, message });
		}
		return message;
	}

}

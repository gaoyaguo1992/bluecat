package com.stylefeng.guns.sharecore.common.persistence.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class ResultCommandMap <T extends Object> extends BaseObject {
	/**
	 * TODO
	 */
	private static final long serialVersionUID = -3629161174550222987L;
	public final static String SUCCESS = "success";
	public final static String FAILED = "error";
	public final static String WARNING = "warning";
	/**
	 * 查询数据集总行数
	 */
	private long totalRows;
	/**
	 * 提供数据集开始行
	 */
	private int rowsStartIndex;
	/**
	 * 提供数据集结束行..
	 */
	private int rowsEndIndex;
	private String errorCode;
	private String message;
	private String result = FAILED;
	// 结果对像
	private Map responseInfo;
	// 结果集
	private List<Map> responseInfos;
	private String state;

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	public void buildErrorInfo(String errorCode, String message) {
		this.message = message;
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}


	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the messsage
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param messsage the messsage to set
	 */
	public void setMessage(String message) {
		//自动销毁session
		cleaseSysSessionInfo();	
		this.message = message;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		//当做了修改时自动销毁session
		cleaseSysSessionInfo();
		this.result = result;
	}

	/**
	 * @return the responseInfo
	 */
	public Object getResponseInfo() {
		/**
		 * 如果是session只能取一次...取完就会自动清楚...
		 */
		Object obj=cleaseSysSessionInfo();
		
		return (obj!=null)?obj:responseInfo;
	}
	/**
	 * 当ResultCommand做了修改，系统被自己销毁session
	 */
	private Object cleaseSysSessionInfo() {
		Object obj=null;
		if(responseInfo!=null&&(responseInfo.getClass().equals(SysSessionInfo.class))){
			try {
				obj = BeanUtils.cloneBean(responseInfo);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			responseInfo=null;
		}
		return obj;
	}

	/**
	 * @param responseInfo the responseInfo to set
	 */
	public void setResponseInfo(Map responseInfo) {
		this.responseInfo = responseInfo;
	}

	public List<Map> getResponseInfos() {
		return responseInfos;
	}

	public void setResponseInfos(List<Map> responseInfos) {
		cleaseSysSessionInfo();	
		this.responseInfos = responseInfos;
	}
	
	public int getRowsStartIndex() {
		return rowsStartIndex;
	}

	public void setRowsStartIndex(int rowsStartIndex) {
		this.rowsStartIndex = rowsStartIndex;
	}

	public int getRowsEndIndex() {
		return rowsEndIndex;
	}

	public void setRowsEndIndex(int rowsEndIndex) {
		this.rowsEndIndex = rowsEndIndex;
	}
}

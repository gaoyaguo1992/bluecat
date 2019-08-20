package com.stylefeng.guns.sharecore.common.persistence.model;

public class ResultCommand extends BaseObject {
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
		this.result = result;
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

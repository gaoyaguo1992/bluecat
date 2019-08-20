package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.List;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;


/**
 * 
 * <P>一般分页查询返回BO</P>
 * 
 */
public class CommonPagingQueryBO extends BaseObject{
	/**
	 * TODO
	 */
	private static final long serialVersionUID = 7241440457982034073L;
	private long total;//结果集总数
	private List rows;//结果集
	public final static String SUCCESS = "success";
	public final static String FAILED = "error";
	private String message;
	private String result = FAILED;
	private String isShareMerchant = "false";
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the totals
	 */
	public long getTotal() {
		return total;
	}
	/**
	 * @param totals the totals to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}
	/**
	 * @return the rows
	 */
	public List getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List rows) {
		this.rows = rows;
	}
	public String getIsShareMerchant() {
		return isShareMerchant;
	}
	public void setIsShareMerchant(String isShareMerchant) {
		this.isShareMerchant = isShareMerchant;
	}
	
}

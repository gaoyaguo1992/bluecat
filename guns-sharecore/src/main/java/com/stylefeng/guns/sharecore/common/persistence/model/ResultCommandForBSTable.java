package com.stylefeng.guns.sharecore.common.persistence.model;

import java.math.BigDecimal;
import java.util.List;

public class ResultCommandForBSTable <T extends Object> extends BaseObject {
	/**
	 * TODO
	 */
	private static final long serialVersionUID = -3629161174550224487L;
	public final static String SUCCESS = "success";
	public final static String FAILED = "error";
	public final static String WARNING = "warning";
	/**
	 * 查询数据集总行数
	 */
	private long total;
	/**
	 * 总和
	 */
	private BigDecimal totalAmount;/**
     * 预付金和
     */
    private BigDecimal totalYFJAmount;
    /**
     * 平台
     */
    private BigDecimal totalPlatFormAmount;
    /**
     * 一级代理
     */
    private BigDecimal totalAgents1Amount;
    /**
     *  二级代理
     */
    private BigDecimal totalAgents2Amount;
    /**
     * 三级代理
     */
    private BigDecimal totalAgents3Amount;
    /**
     * 加盟商
     */
    private BigDecimal totalAllianceBusinessAmount;
    /**
     * 铺货商
     */
    private BigDecimal totalShopkeeperAmount;
	/**
	 * 提供数据集开始行
	 */
	private int rowsStartIndex;
	/**
	 * 提供数据集结束行..
	 */
	private int rowsEndIndex;
	/**
	 * 返回信息..
	 */
	private String message;
	/**
	 * 处理结果..
	 */
	private String result = FAILED;
	/**
	 * 得到数据例..
	 */
	private List<T> rows;
	
	
	
	public BigDecimal getTotalYFJAmount() {
		return totalYFJAmount;
	}
	public void setTotalYFJAmount(BigDecimal totalYFJAmount) {
		this.totalYFJAmount = totalYFJAmount;
	}
	public BigDecimal getTotalPlatFormAmount() {
		return totalPlatFormAmount;
	}
	public void setTotalPlatFormAmount(BigDecimal totalPlatFormAmount) {
		this.totalPlatFormAmount = totalPlatFormAmount;
	}
	public BigDecimal getTotalAgents1Amount() {
		return totalAgents1Amount;
	}
	public void setTotalAgents1Amount(BigDecimal totalAgents1Amount) {
		this.totalAgents1Amount = totalAgents1Amount;
	}
	public BigDecimal getTotalAgents2Amount() {
		return totalAgents2Amount;
	}
	public void setTotalAgents2Amount(BigDecimal totalAgents2Amount) {
		this.totalAgents2Amount = totalAgents2Amount;
	}
	public BigDecimal getTotalAgents3Amount() {
		return totalAgents3Amount;
	}
	public void setTotalAgents3Amount(BigDecimal totalAgents3Amount) {
		this.totalAgents3Amount = totalAgents3Amount;
	}
	public BigDecimal getTotalAllianceBusinessAmount() {
		return totalAllianceBusinessAmount;
	}
	public void setTotalAllianceBusinessAmount(BigDecimal totalAllianceBusinessAmount) {
		this.totalAllianceBusinessAmount = totalAllianceBusinessAmount;
	}
	public BigDecimal getTotalShopkeeperAmount() {
		return totalShopkeeperAmount;
	}
	public void setTotalShopkeeperAmount(BigDecimal totalShopkeeperAmount) {
		this.totalShopkeeperAmount = totalShopkeeperAmount;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
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
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}

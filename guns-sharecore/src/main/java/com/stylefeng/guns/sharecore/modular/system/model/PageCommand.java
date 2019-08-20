package com.stylefeng.guns.sharecore.modular.system.model;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;
import com.stylefeng.guns.sharecore.common.utils.CamelAndUnderline;

/**
 * <P>页面查询分页请求，记录分页相关必要元素，其中排序的sort由驼峰形式转成数据表字段形式用于排序使用</P>
 * 
 *  
 */
public class PageCommand extends BaseObject {

	private static final long serialVersionUID = -5619610889394437799L;

	/**
	 * 显示页数
	 */
	private int page;
	/**
	 * 每页显示记录数
	 */
	private int rows;
	/**
	 * 	开始
	 */
	private int start;
	/**
	 * 排序字段
	 */
	private String sort;
	/**
	 * 排序方式
	 */
	private String order;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return CamelAndUnderline.camelToUnderline(sort);
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

}

package com.stylefeng.guns.sharecore.modular.system.model;

import com.stylefeng.guns.sharecore.common.persistence.model.BaseObject;
import com.stylefeng.guns.sharecore.common.utils.CamelAndUnderline;

/**
 * 
 * <P>页面查询分页请求，记录分页相关必要元素，其中排序的sort由驼峰形式转成数据表字段形式用于排序使用</P>
 */
public class StartRowsPageBO extends BaseObject {

	private static final long serialVersionUID = -5619610889394437799L;

	private Integer start;
	
	/**
	 * 每页显示记录数
	 */
	private Integer rows;
	/**
	 * 排序字段
	 */
	private String sort;
	/**
	 * 排序方式
	 */
	private String order;

	 
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

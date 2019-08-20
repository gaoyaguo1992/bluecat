/*
 * Copyright 2014 - 2020.易谷网有限公司版权所有.
 */
package com.stylefeng.guns.sharecore.common.persistence.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.alibaba.fastjson.JSONObject;

/**
 * 实体对象
 * @author Alan.huang
 *
 */
public class BaseObject implements java.io.Serializable {

	private static final long serialVersionUID = 2820441983839220178L;
	

	@Override
	public boolean equals(final Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public Object toJson() {
		return JSONObject.toJSON(this);
	}
	public String toJsonStr() {
		return JSONObject.toJSONString(this);
	}
	

}

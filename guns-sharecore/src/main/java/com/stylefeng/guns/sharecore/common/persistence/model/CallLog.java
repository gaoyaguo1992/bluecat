package com.stylefeng.guns.sharecore.common.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author alan li
 * @since 2018-12-30
 */
@TableName("sys_call_log")
public class CallLog extends Model<CallLog> {

    private static final long serialVersionUID = 1L;

    /**
     * sessionId
     */
    @TableField("session_id")
    private String sessionId;
    /**
     * cust_no
     */
    @TableField("cust_no")
    private String custNo;
    /**
     * 访问ip
     */
    private String ip;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 参数
     */
    private String params;
    /**
     * url
     */
    private String url;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CallLog{" +
        "sessionId=" + sessionId +
        ", custNo=" + custNo +
        ", ip=" + ip +
        ", createTime=" + createTime +
        ", params=" + params +
        ", url=" + url +
        ", id=" + id +
        "}";
    }
}

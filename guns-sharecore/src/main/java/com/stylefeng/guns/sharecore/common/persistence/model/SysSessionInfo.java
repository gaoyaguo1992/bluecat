package com.stylefeng.guns.sharecore.common.persistence.model;

import java.util.Date;

public class SysSessionInfo extends BaseObject{
	 
	/**
	 * TODO
	 */
	private static final long serialVersionUID = 3913152752115400152L;

	private String id;

    private String openid;

    private String wxappopendid;

    private String zfbUserId;

    private String jscode;

    private Date createtime;

    private Date updatetime;

    private String sessionkey;

    private Long custNo;

    private String sessionId;

    private Integer status;

    private Integer sessiontype;
    /**
     * 上次调用的时间戳..
     */
    private Long timeStampForLastTime;
    /**
     * 上次调用的时间戳..原来的..
     */
    private Long timeStamp;


    public String getZfbUserId() {
        return zfbUserId;
    }

    public void setZfbUserId(String zfbUserId) {
        this.zfbUserId = zfbUserId;
    }
    
    public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Long getTimeStampForLastTime() {
		return timeStampForLastTime;
	}

	public void setTimeStampForLastTime(Long timeStampForLastTime) {
		this.timeStampForLastTime = timeStampForLastTime;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getWxappopendid() {
        return wxappopendid;
    }

    public void setWxappopendid(String wxappopendid) {
        this.wxappopendid = wxappopendid == null ? null : wxappopendid.trim();
    }

    public String getJscode() {
        return jscode;
    }

    public void setJscode(String jscode) {
        this.jscode = jscode == null ? null : jscode.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey == null ? null : sessionkey.trim();
    }

    public Long getCustNo() {
        return custNo;
    }

    public void setCustNo(Long custNo) {
        this.custNo = custNo;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSessiontype() {
		return sessiontype;
	}

	public void setSessiontype(Integer sessiontype) {
		this.sessiontype = sessiontype;
	}

    
}
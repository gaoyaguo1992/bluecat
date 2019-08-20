package  com.stylefeng.guns.sharecore.modular.system.model;

import java.util.Date;
/**
 * 
 * <P>提示消息实体类，用于交易或订单产生的消息保存</P>
 */
public class NotifyMessageModel {
    private Long id;

    private Integer type; //通知消息类型 1租借 2归还

    private String content;
    
    private String title;

    private Long receiveCustNo;

    private Integer status; //消息状态 1已读 2未读 

    private String formId;

    private Date createTime;

    private Long tradeId;
    
    private Long chuzuId;
    
    private Long zujieId;
    
    private String wxAppOpenId; //接收人openId
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getReceiveCustNo() {
        return receiveCustNo;
    }

    public void setReceiveCustNo(Long receiveCustNo) {
        this.receiveCustNo = receiveCustNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId == null ? null : formId.trim();
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

	public Long getChuzuId() {
		return chuzuId;
	}

	public void setChuzuId(Long chuzuId) {
		this.chuzuId = chuzuId;
	}

	public Long getZujieId() {
		return zujieId;
	}

	public void setZujieId(Long zujieId) {
		this.zujieId = zujieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWxAppOpenId() {
		return wxAppOpenId;
	}

	public void setWxAppOpenId(String wxAppOpenId) {
		this.wxAppOpenId = wxAppOpenId;
	}
}
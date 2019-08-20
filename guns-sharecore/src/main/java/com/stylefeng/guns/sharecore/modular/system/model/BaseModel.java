package com.stylefeng.guns.sharecore.modular.system.model;

import java.io.Serializable;
import java.util.Date;
/**
 * base model..
 * @author Alan.huang
 *
 */
public class BaseModel extends PageCommand implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7720591403491970891L;

	private Date createTime;

    private Date updateTime;
    
    private Long merchantId;
    
    private String dateBegin;
    
    private String dateEnd;
    
    private Long id;
    
    
    
    public String getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(String dateBegin) {
		this.dateBegin = dateBegin;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}




	/**
	  * 状态枚举
	  * @author  www.jeecg.org
	  *
	  */
	 public enum STATE {
		 	ENABLE(0, "可用"), DISABLE(1,"禁用");
			public int key;
			public String value;
			STATE(int key, String value) {
				this.key = key;
				this.value = value;
			}
			public static STATE get(int key) {
				STATE[] values = STATE.values();
				for (STATE object : values) {
					if (object.key == key) {
						return object;
					}
				}
				return null;
			}
		}
	 	
	 	/**
	 	 * 删除枚举
	 	 * @author  www.jeecg.org
	 	 *
	 	 */
	 	public enum DELETED {
			NO(0, "未删除"), YES(1,"已删除");
			public int key;
			public String value;
			DELETED(int key, String value) {
				this.key = key;
				this.value = value;
			}
			public static DELETED get(int key) {
				DELETED[] values = DELETED.values();
				for (DELETED object : values) {
					if (object.key == key) {
						return object;
					}
				}
				return null;
			}
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
	
}

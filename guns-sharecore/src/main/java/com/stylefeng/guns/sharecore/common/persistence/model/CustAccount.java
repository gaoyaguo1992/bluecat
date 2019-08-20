package com.stylefeng.guns.sharecore.common.persistence.model;

import java.math.BigDecimal;
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
 * @since 2019-01-01
 */
@TableName("cust_account")
public class CustAccount extends Model<CustAccount> {

    private static final long serialVersionUID = 1L;

    @TableId("account_id")
    private Long accountId;
    @TableField("cust_no")
    private Long custNo;
    private BigDecimal balance;
    @TableField("frozen_balance")
    private BigDecimal frozenBalance;
    @TableField("available_balance")
    private BigDecimal availableBalance;
    private Integer status;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    @TableField("data_digest")
    private String dataDigest;
    @TableField("jifen_balance")
    private BigDecimal jifenBalance;
    /**
     * 资金来源. 1--微信,2--支付宝 3--平台
     */
    @TableField("amt_source")
    private Integer amtSource;
    /**
     * 资金类型. 1--充值金额,2--赏金(其他账户转入金额) 3--交易积分
     */
    @TableField("account_type")
    private Integer accountType;


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCustNo() {
        return custNo;
    }

    public void setCustNo(Long custNo) {
        this.custNo = custNo;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFrozenBalance() {
        return frozenBalance;
    }

    public void setFrozenBalance(BigDecimal frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getDataDigest() {
        return dataDigest;
    }

    public void setDataDigest(String dataDigest) {
        this.dataDigest = dataDigest;
    }

    public BigDecimal getJifenBalance() {
        return jifenBalance;
    }

    public void setJifenBalance(BigDecimal jifenBalance) {
        this.jifenBalance = jifenBalance;
    }

    public Integer getAmtSource() {
        return amtSource;
    }

    public void setAmtSource(Integer amtSource) {
        this.amtSource = amtSource;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    @Override
    protected Serializable pkVal() {
        return this.accountId;
    }

    @Override
    public String toString() {
        return "CustAccount{" +
        "accountId=" + accountId +
        ", custNo=" + custNo +
        ", balance=" + balance +
        ", frozenBalance=" + frozenBalance +
        ", availableBalance=" + availableBalance +
        ", status=" + status +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", dataDigest=" + dataDigest +
        ", jifenBalance=" + jifenBalance +
        ", amtSource=" + amtSource +
        ", accountType=" + accountType +
        "}";
    }
}

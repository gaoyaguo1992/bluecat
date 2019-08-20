package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

public class ShareMerchantTradeProfitModel extends ShareMerchantTradeProfitModelKey {
    private Integer tradeType;

    private String tradeName;

    private Integer merchantType;

    private BigDecimal profitRato;

    private BigDecimal totalAmount;

    private BigDecimal amount;

    private Date borrowDatetime;

    private Date backDatetime;

    private Long terminalMerchantId;

    private Date createDatetime;

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName == null ? null : tradeName.trim();
    }

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }

    public BigDecimal getProfitRato() {
        return profitRato;
    }

    public void setProfitRato(BigDecimal profitRato) {
        this.profitRato = profitRato;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getBorrowDatetime() {
        return borrowDatetime;
    }

    public void setBorrowDatetime(Date borrowDatetime) {
        this.borrowDatetime = borrowDatetime;
    }

    public Date getBackDatetime() {
        return backDatetime;
    }

    public void setBackDatetime(Date backDatetime) {
        this.backDatetime = backDatetime;
    }

    public Long getTerminalMerchantId() {
        return terminalMerchantId;
    }

    public void setTerminalMerchantId(Long terminalMerchantId) {
        this.terminalMerchantId = terminalMerchantId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
}
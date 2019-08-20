package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("calculate_device_data")
public class CalculateDeviceDataModel {

	@TableId(value="deviceId", type= IdType.AUTO)
    private Long deviceId;

    private Long historyOrders;

    private Long beforeOrders1;

    private Long beforeOrders2;

    private Long beforeOrders3;

    private Long beforeOrders4;

    private Long beforeOrders5;

    private Long beforeOrders6;

    private Long beforeOrders7;

    private Long historyFinishOrders;

    private Long beforeFinishOrders1;

    private Long beforeFinishOrders2;

    private Long beforeFinishOrders3;

    private Long beforeFinishOrders4;

    private Long beforeFinishOrders5;

    private Long beforeFinishOrders6;

    private Long beforeFinishOrders7;

    private BigDecimal totalAmount;

    private BigDecimal beforeAmount1;

    private BigDecimal beforeAmount2;

    private BigDecimal beforeAmount3;

    private BigDecimal beforeAmount4;

    private BigDecimal beforeAmount5;

    private BigDecimal beforeAmount6;

    private BigDecimal beforeAmount7;

    private Date lastUseDate;

    private Date calculateDate;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getHistoryOrders() {
        return historyOrders;
    }

    public void setHistoryOrders(Long historyOrders) {
        this.historyOrders = historyOrders;
    }

    public Long getBeforeOrders1() {
        return beforeOrders1;
    }

    public void setBeforeOrders1(Long beforeOrders1) {
        this.beforeOrders1 = beforeOrders1;
    }

    public Long getBeforeOrders2() {
        return beforeOrders2;
    }

    public void setBeforeOrders2(Long beforeOrders2) {
        this.beforeOrders2 = beforeOrders2;
    }

    public Long getBeforeOrders3() {
        return beforeOrders3;
    }

    public void setBeforeOrders3(Long beforeOrders3) {
        this.beforeOrders3 = beforeOrders3;
    }

    public Long getBeforeOrders4() {
        return beforeOrders4;
    }

    public void setBeforeOrders4(Long beforeOrders4) {
        this.beforeOrders4 = beforeOrders4;
    }

    public Long getBeforeOrders5() {
        return beforeOrders5;
    }

    public void setBeforeOrders5(Long beforeOrders5) {
        this.beforeOrders5 = beforeOrders5;
    }

    public Long getBeforeOrders6() {
        return beforeOrders6;
    }

    public void setBeforeOrders6(Long beforeOrders6) {
        this.beforeOrders6 = beforeOrders6;
    }

    public Long getBeforeOrders7() {
        return beforeOrders7;
    }

    public void setBeforeOrders7(Long beforeOrders7) {
        this.beforeOrders7 = beforeOrders7;
    }

    public Long getHistoryFinishOrders() {
        return historyFinishOrders;
    }

    public void setHistoryFinishOrders(Long historyFinishOrders) {
        this.historyFinishOrders = historyFinishOrders;
    }

    public Long getBeforeFinishOrders1() {
        return beforeFinishOrders1;
    }

    public void setBeforeFinishOrders1(Long beforeFinishOrders1) {
        this.beforeFinishOrders1 = beforeFinishOrders1;
    }

    public Long getBeforeFinishOrders2() {
        return beforeFinishOrders2;
    }

    public void setBeforeFinishOrders2(Long beforeFinishOrders2) {
        this.beforeFinishOrders2 = beforeFinishOrders2;
    }

    public Long getBeforeFinishOrders3() {
        return beforeFinishOrders3;
    }

    public void setBeforeFinishOrders3(Long beforeFinishOrders3) {
        this.beforeFinishOrders3 = beforeFinishOrders3;
    }

    public Long getBeforeFinishOrders4() {
        return beforeFinishOrders4;
    }

    public void setBeforeFinishOrders4(Long beforeFinishOrders4) {
        this.beforeFinishOrders4 = beforeFinishOrders4;
    }

    public Long getBeforeFinishOrders5() {
        return beforeFinishOrders5;
    }

    public void setBeforeFinishOrders5(Long beforeFinishOrders5) {
        this.beforeFinishOrders5 = beforeFinishOrders5;
    }

    public Long getBeforeFinishOrders6() {
        return beforeFinishOrders6;
    }

    public void setBeforeFinishOrders6(Long beforeFinishOrders6) {
        this.beforeFinishOrders6 = beforeFinishOrders6;
    }

    public Long getBeforeFinishOrders7() {
        return beforeFinishOrders7;
    }

    public void setBeforeFinishOrders7(Long beforeFinishOrders7) {
        this.beforeFinishOrders7 = beforeFinishOrders7;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getBeforeAmount1() {
        return beforeAmount1;
    }

    public void setBeforeAmount1(BigDecimal beforeAmount1) {
        this.beforeAmount1 = beforeAmount1;
    }

    public BigDecimal getBeforeAmount2() {
        return beforeAmount2;
    }

    public void setBeforeAmount2(BigDecimal beforeAmount2) {
        this.beforeAmount2 = beforeAmount2;
    }

    public BigDecimal getBeforeAmount3() {
        return beforeAmount3;
    }

    public void setBeforeAmount3(BigDecimal beforeAmount3) {
        this.beforeAmount3 = beforeAmount3;
    }

    public BigDecimal getBeforeAmount4() {
        return beforeAmount4;
    }

    public void setBeforeAmount4(BigDecimal beforeAmount4) {
        this.beforeAmount4 = beforeAmount4;
    }

    public BigDecimal getBeforeAmount5() {
        return beforeAmount5;
    }

    public void setBeforeAmount5(BigDecimal beforeAmount5) {
        this.beforeAmount5 = beforeAmount5;
    }

    public BigDecimal getBeforeAmount6() {
        return beforeAmount6;
    }

    public void setBeforeAmount6(BigDecimal beforeAmount6) {
        this.beforeAmount6 = beforeAmount6;
    }

    public BigDecimal getBeforeAmount7() {
        return beforeAmount7;
    }

    public void setBeforeAmount7(BigDecimal beforeAmount7) {
        this.beforeAmount7 = beforeAmount7;
    }

    public Date getLastUseDate() {
        return lastUseDate;
    }

    public void setLastUseDate(Date lastUseDate) {
        this.lastUseDate = lastUseDate;
    }

    public Date getCalculateDate() {
        return calculateDate;
    }

    public void setCalculateDate(Date calculateDate) {
        this.calculateDate = calculateDate;
    }
}
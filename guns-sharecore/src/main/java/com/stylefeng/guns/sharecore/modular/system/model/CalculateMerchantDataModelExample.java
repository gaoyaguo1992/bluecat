package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalculateMerchantDataModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CalculateMerchantDataModelExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMerchantIdIsNull() {
            addCriterion("merchant_id is null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNotNull() {
            addCriterion("merchant_id is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdEqualTo(Long value) {
            addCriterion("merchant_id =", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotEqualTo(Long value) {
            addCriterion("merchant_id <>", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThan(Long value) {
            addCriterion("merchant_id >", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThanOrEqualTo(Long value) {
            addCriterion("merchant_id >=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThan(Long value) {
            addCriterion("merchant_id <", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThanOrEqualTo(Long value) {
            addCriterion("merchant_id <=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIn(List<Long> values) {
            addCriterion("merchant_id in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotIn(List<Long> values) {
            addCriterion("merchant_id not in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdBetween(Long value1, Long value2) {
            addCriterion("merchant_id between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotBetween(Long value1, Long value2) {
            addCriterion("merchant_id not between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIdIsNull() {
            addCriterion("merchant_type_id is null");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIdIsNotNull() {
            addCriterion("merchant_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIdEqualTo(Long value) {
            addCriterion("merchant_type_id =", value, "merchantTypeId");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIdNotEqualTo(Long value) {
            addCriterion("merchant_type_id <>", value, "merchantTypeId");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIdGreaterThan(Long value) {
            addCriterion("merchant_type_id >", value, "merchantTypeId");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("merchant_type_id >=", value, "merchantTypeId");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIdLessThan(Long value) {
            addCriterion("merchant_type_id <", value, "merchantTypeId");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("merchant_type_id <=", value, "merchantTypeId");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIdIn(List<Long> values) {
            addCriterion("merchant_type_id in", values, "merchantTypeId");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIdNotIn(List<Long> values) {
            addCriterion("merchant_type_id not in", values, "merchantTypeId");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIdBetween(Long value1, Long value2) {
            addCriterion("merchant_type_id between", value1, value2, "merchantTypeId");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("merchant_type_id not between", value1, value2, "merchantTypeId");
            return (Criteria) this;
        }

        public Criteria andDeviceQtyIsNull() {
            addCriterion("device_qty is null");
            return (Criteria) this;
        }

        public Criteria andDeviceQtyIsNotNull() {
            addCriterion("device_qty is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceQtyEqualTo(Long value) {
            addCriterion("device_qty =", value, "deviceQty");
            return (Criteria) this;
        }

        public Criteria andDeviceQtyNotEqualTo(Long value) {
            addCriterion("device_qty <>", value, "deviceQty");
            return (Criteria) this;
        }

        public Criteria andDeviceQtyGreaterThan(Long value) {
            addCriterion("device_qty >", value, "deviceQty");
            return (Criteria) this;
        }

        public Criteria andDeviceQtyGreaterThanOrEqualTo(Long value) {
            addCriterion("device_qty >=", value, "deviceQty");
            return (Criteria) this;
        }

        public Criteria andDeviceQtyLessThan(Long value) {
            addCriterion("device_qty <", value, "deviceQty");
            return (Criteria) this;
        }

        public Criteria andDeviceQtyLessThanOrEqualTo(Long value) {
            addCriterion("device_qty <=", value, "deviceQty");
            return (Criteria) this;
        }

        public Criteria andDeviceQtyIn(List<Long> values) {
            addCriterion("device_qty in", values, "deviceQty");
            return (Criteria) this;
        }

        public Criteria andDeviceQtyNotIn(List<Long> values) {
            addCriterion("device_qty not in", values, "deviceQty");
            return (Criteria) this;
        }

        public Criteria andDeviceQtyBetween(Long value1, Long value2) {
            addCriterion("device_qty between", value1, value2, "deviceQty");
            return (Criteria) this;
        }

        public Criteria andDeviceQtyNotBetween(Long value1, Long value2) {
            addCriterion("device_qty not between", value1, value2, "deviceQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsedTotalQtyIsNull() {
            addCriterion("device_used_total_qty is null");
            return (Criteria) this;
        }

        public Criteria andDeviceUsedTotalQtyIsNotNull() {
            addCriterion("device_used_total_qty is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceUsedTotalQtyEqualTo(Long value) {
            addCriterion("device_used_total_qty =", value, "deviceUsedTotalQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsedTotalQtyNotEqualTo(Long value) {
            addCriterion("device_used_total_qty <>", value, "deviceUsedTotalQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsedTotalQtyGreaterThan(Long value) {
            addCriterion("device_used_total_qty >", value, "deviceUsedTotalQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsedTotalQtyGreaterThanOrEqualTo(Long value) {
            addCriterion("device_used_total_qty >=", value, "deviceUsedTotalQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsedTotalQtyLessThan(Long value) {
            addCriterion("device_used_total_qty <", value, "deviceUsedTotalQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsedTotalQtyLessThanOrEqualTo(Long value) {
            addCriterion("device_used_total_qty <=", value, "deviceUsedTotalQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsedTotalQtyIn(List<Long> values) {
            addCriterion("device_used_total_qty in", values, "deviceUsedTotalQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsedTotalQtyNotIn(List<Long> values) {
            addCriterion("device_used_total_qty not in", values, "deviceUsedTotalQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsedTotalQtyBetween(Long value1, Long value2) {
            addCriterion("device_used_total_qty between", value1, value2, "deviceUsedTotalQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsedTotalQtyNotBetween(Long value1, Long value2) {
            addCriterion("device_used_total_qty not between", value1, value2, "deviceUsedTotalQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsed7daysQtyIsNull() {
            addCriterion("device_used_7days_qty is null");
            return (Criteria) this;
        }

        public Criteria andDeviceUsed7daysQtyIsNotNull() {
            addCriterion("device_used_7days_qty is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceUsed7daysQtyEqualTo(Long value) {
            addCriterion("device_used_7days_qty =", value, "deviceUsed7daysQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsed7daysQtyNotEqualTo(Long value) {
            addCriterion("device_used_7days_qty <>", value, "deviceUsed7daysQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsed7daysQtyGreaterThan(Long value) {
            addCriterion("device_used_7days_qty >", value, "deviceUsed7daysQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsed7daysQtyGreaterThanOrEqualTo(Long value) {
            addCriterion("device_used_7days_qty >=", value, "deviceUsed7daysQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsed7daysQtyLessThan(Long value) {
            addCriterion("device_used_7days_qty <", value, "deviceUsed7daysQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsed7daysQtyLessThanOrEqualTo(Long value) {
            addCriterion("device_used_7days_qty <=", value, "deviceUsed7daysQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsed7daysQtyIn(List<Long> values) {
            addCriterion("device_used_7days_qty in", values, "deviceUsed7daysQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsed7daysQtyNotIn(List<Long> values) {
            addCriterion("device_used_7days_qty not in", values, "deviceUsed7daysQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsed7daysQtyBetween(Long value1, Long value2) {
            addCriterion("device_used_7days_qty between", value1, value2, "deviceUsed7daysQty");
            return (Criteria) this;
        }

        public Criteria andDeviceUsed7daysQtyNotBetween(Long value1, Long value2) {
            addCriterion("device_used_7days_qty not between", value1, value2, "deviceUsed7daysQty");
            return (Criteria) this;
        }

        public Criteria andDevice7daysUsageRateIsNull() {
            addCriterion("device_7days_usage_rate is null");
            return (Criteria) this;
        }

        public Criteria andDevice7daysUsageRateIsNotNull() {
            addCriterion("device_7days_usage_rate is not null");
            return (Criteria) this;
        }

        public Criteria andDevice7daysUsageRateEqualTo(BigDecimal value) {
            addCriterion("device_7days_usage_rate =", value, "device7daysUsageRate");
            return (Criteria) this;
        }

        public Criteria andDevice7daysUsageRateNotEqualTo(BigDecimal value) {
            addCriterion("device_7days_usage_rate <>", value, "device7daysUsageRate");
            return (Criteria) this;
        }

        public Criteria andDevice7daysUsageRateGreaterThan(BigDecimal value) {
            addCriterion("device_7days_usage_rate >", value, "device7daysUsageRate");
            return (Criteria) this;
        }

        public Criteria andDevice7daysUsageRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("device_7days_usage_rate >=", value, "device7daysUsageRate");
            return (Criteria) this;
        }

        public Criteria andDevice7daysUsageRateLessThan(BigDecimal value) {
            addCriterion("device_7days_usage_rate <", value, "device7daysUsageRate");
            return (Criteria) this;
        }

        public Criteria andDevice7daysUsageRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("device_7days_usage_rate <=", value, "device7daysUsageRate");
            return (Criteria) this;
        }

        public Criteria andDevice7daysUsageRateIn(List<BigDecimal> values) {
            addCriterion("device_7days_usage_rate in", values, "device7daysUsageRate");
            return (Criteria) this;
        }

        public Criteria andDevice7daysUsageRateNotIn(List<BigDecimal> values) {
            addCriterion("device_7days_usage_rate not in", values, "device7daysUsageRate");
            return (Criteria) this;
        }

        public Criteria andDevice7daysUsageRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("device_7days_usage_rate between", value1, value2, "device7daysUsageRate");
            return (Criteria) this;
        }

        public Criteria andDevice7daysUsageRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("device_7days_usage_rate not between", value1, value2, "device7daysUsageRate");
            return (Criteria) this;
        }

        public Criteria andDeviceTotalAmountIsNull() {
            addCriterion("device_total_amount is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTotalAmountIsNotNull() {
            addCriterion("device_total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTotalAmountEqualTo(BigDecimal value) {
            addCriterion("device_total_amount =", value, "deviceTotalAmount");
            return (Criteria) this;
        }

        public Criteria andDeviceTotalAmountNotEqualTo(BigDecimal value) {
            addCriterion("device_total_amount <>", value, "deviceTotalAmount");
            return (Criteria) this;
        }

        public Criteria andDeviceTotalAmountGreaterThan(BigDecimal value) {
            addCriterion("device_total_amount >", value, "deviceTotalAmount");
            return (Criteria) this;
        }

        public Criteria andDeviceTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("device_total_amount >=", value, "deviceTotalAmount");
            return (Criteria) this;
        }

        public Criteria andDeviceTotalAmountLessThan(BigDecimal value) {
            addCriterion("device_total_amount <", value, "deviceTotalAmount");
            return (Criteria) this;
        }

        public Criteria andDeviceTotalAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("device_total_amount <=", value, "deviceTotalAmount");
            return (Criteria) this;
        }

        public Criteria andDeviceTotalAmountIn(List<BigDecimal> values) {
            addCriterion("device_total_amount in", values, "deviceTotalAmount");
            return (Criteria) this;
        }

        public Criteria andDeviceTotalAmountNotIn(List<BigDecimal> values) {
            addCriterion("device_total_amount not in", values, "deviceTotalAmount");
            return (Criteria) this;
        }

        public Criteria andDeviceTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("device_total_amount between", value1, value2, "deviceTotalAmount");
            return (Criteria) this;
        }

        public Criteria andDeviceTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("device_total_amount not between", value1, value2, "deviceTotalAmount");
            return (Criteria) this;
        }

        public Criteria andLastUsedDateIsNull() {
            addCriterion("last_used_date is null");
            return (Criteria) this;
        }

        public Criteria andLastUsedDateIsNotNull() {
            addCriterion("last_used_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastUsedDateEqualTo(Date value) {
            addCriterion("last_used_date =", value, "lastUsedDate");
            return (Criteria) this;
        }

        public Criteria andLastUsedDateNotEqualTo(Date value) {
            addCriterion("last_used_date <>", value, "lastUsedDate");
            return (Criteria) this;
        }

        public Criteria andLastUsedDateGreaterThan(Date value) {
            addCriterion("last_used_date >", value, "lastUsedDate");
            return (Criteria) this;
        }

        public Criteria andLastUsedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_used_date >=", value, "lastUsedDate");
            return (Criteria) this;
        }

        public Criteria andLastUsedDateLessThan(Date value) {
            addCriterion("last_used_date <", value, "lastUsedDate");
            return (Criteria) this;
        }

        public Criteria andLastUsedDateLessThanOrEqualTo(Date value) {
            addCriterion("last_used_date <=", value, "lastUsedDate");
            return (Criteria) this;
        }

        public Criteria andLastUsedDateIn(List<Date> values) {
            addCriterion("last_used_date in", values, "lastUsedDate");
            return (Criteria) this;
        }

        public Criteria andLastUsedDateNotIn(List<Date> values) {
            addCriterion("last_used_date not in", values, "lastUsedDate");
            return (Criteria) this;
        }

        public Criteria andLastUsedDateBetween(Date value1, Date value2) {
            addCriterion("last_used_date between", value1, value2, "lastUsedDate");
            return (Criteria) this;
        }

        public Criteria andLastUsedDateNotBetween(Date value1, Date value2) {
            addCriterion("last_used_date not between", value1, value2, "lastUsedDate");
            return (Criteria) this;
        }

        public Criteria andCalculateDateIsNull() {
            addCriterion("calculate_date is null");
            return (Criteria) this;
        }

        public Criteria andCalculateDateIsNotNull() {
            addCriterion("calculate_date is not null");
            return (Criteria) this;
        }

        public Criteria andCalculateDateEqualTo(Date value) {
            addCriterion("calculate_date =", value, "calculateDate");
            return (Criteria) this;
        }

        public Criteria andCalculateDateNotEqualTo(Date value) {
            addCriterion("calculate_date <>", value, "calculateDate");
            return (Criteria) this;
        }

        public Criteria andCalculateDateGreaterThan(Date value) {
            addCriterion("calculate_date >", value, "calculateDate");
            return (Criteria) this;
        }

        public Criteria andCalculateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("calculate_date >=", value, "calculateDate");
            return (Criteria) this;
        }

        public Criteria andCalculateDateLessThan(Date value) {
            addCriterion("calculate_date <", value, "calculateDate");
            return (Criteria) this;
        }

        public Criteria andCalculateDateLessThanOrEqualTo(Date value) {
            addCriterion("calculate_date <=", value, "calculateDate");
            return (Criteria) this;
        }

        public Criteria andCalculateDateIn(List<Date> values) {
            addCriterion("calculate_date in", values, "calculateDate");
            return (Criteria) this;
        }

        public Criteria andCalculateDateNotIn(List<Date> values) {
            addCriterion("calculate_date not in", values, "calculateDate");
            return (Criteria) this;
        }

        public Criteria andCalculateDateBetween(Date value1, Date value2) {
            addCriterion("calculate_date between", value1, value2, "calculateDate");
            return (Criteria) this;
        }

        public Criteria andCalculateDateNotBetween(Date value1, Date value2) {
            addCriterion("calculate_date not between", value1, value2, "calculateDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
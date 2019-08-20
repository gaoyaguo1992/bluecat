package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShareTradeInfoModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShareTradeInfoModelExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDeviceNoIsNull() {
            addCriterion("device_no is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNoIsNotNull() {
            addCriterion("device_no is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNoEqualTo(Long value) {
            addCriterion("device_no =", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotEqualTo(Long value) {
            addCriterion("device_no <>", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoGreaterThan(Long value) {
            addCriterion("device_no >", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoGreaterThanOrEqualTo(Long value) {
            addCriterion("device_no >=", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLessThan(Long value) {
            addCriterion("device_no <", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLessThanOrEqualTo(Long value) {
            addCriterion("device_no <=", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoIn(List<Long> values) {
            addCriterion("device_no in", values, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotIn(List<Long> values) {
            addCriterion("device_no not in", values, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoBetween(Long value1, Long value2) {
            addCriterion("device_no between", value1, value2, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotBetween(Long value1, Long value2) {
            addCriterion("device_no not between", value1, value2, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andChargerIdIsNull() {
            addCriterion("charger_id is null");
            return (Criteria) this;
        }

        public Criteria andChargerIdIsNotNull() {
            addCriterion("charger_id is not null");
            return (Criteria) this;
        }

        public Criteria andChargerIdEqualTo(Long value) {
            addCriterion("charger_id =", value, "chargerId");
            return (Criteria) this;
        }

        public Criteria andChargerIdNotEqualTo(Long value) {
            addCriterion("charger_id <>", value, "chargerId");
            return (Criteria) this;
        }

        public Criteria andChargerIdGreaterThan(Long value) {
            addCriterion("charger_id >", value, "chargerId");
            return (Criteria) this;
        }

        public Criteria andChargerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("charger_id >=", value, "chargerId");
            return (Criteria) this;
        }

        public Criteria andChargerIdLessThan(Long value) {
            addCriterion("charger_id <", value, "chargerId");
            return (Criteria) this;
        }

        public Criteria andChargerIdLessThanOrEqualTo(Long value) {
            addCriterion("charger_id <=", value, "chargerId");
            return (Criteria) this;
        }

        public Criteria andChargerIdIn(List<Long> values) {
            addCriterion("charger_id in", values, "chargerId");
            return (Criteria) this;
        }

        public Criteria andChargerIdNotIn(List<Long> values) {
            addCriterion("charger_id not in", values, "chargerId");
            return (Criteria) this;
        }

        public Criteria andChargerIdBetween(Long value1, Long value2) {
            addCriterion("charger_id between", value1, value2, "chargerId");
            return (Criteria) this;
        }

        public Criteria andChargerIdNotBetween(Long value1, Long value2) {
            addCriterion("charger_id not between", value1, value2, "chargerId");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNull() {
            addCriterion("cust_id is null");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNotNull() {
            addCriterion("cust_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustIdEqualTo(Long value) {
            addCriterion("cust_id =", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotEqualTo(Long value) {
            addCriterion("cust_id <>", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThan(Long value) {
            addCriterion("cust_id >", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThanOrEqualTo(Long value) {
            addCriterion("cust_id >=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThan(Long value) {
            addCriterion("cust_id <", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThanOrEqualTo(Long value) {
            addCriterion("cust_id <=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdIn(List<Long> values) {
            addCriterion("cust_id in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotIn(List<Long> values) {
            addCriterion("cust_id not in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdBetween(Long value1, Long value2) {
            addCriterion("cust_id between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotBetween(Long value1, Long value2) {
            addCriterion("cust_id not between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andYfjAmountIsNull() {
            addCriterion("yfj_amount is null");
            return (Criteria) this;
        }

        public Criteria andYfjAmountIsNotNull() {
            addCriterion("yfj_amount is not null");
            return (Criteria) this;
        }

        public Criteria andYfjAmountEqualTo(BigDecimal value) {
            addCriterion("yfj_amount =", value, "yfjAmount");
            return (Criteria) this;
        }

        public Criteria andYfjAmountNotEqualTo(BigDecimal value) {
            addCriterion("yfj_amount <>", value, "yfjAmount");
            return (Criteria) this;
        }

        public Criteria andYfjAmountGreaterThan(BigDecimal value) {
            addCriterion("yfj_amount >", value, "yfjAmount");
            return (Criteria) this;
        }

        public Criteria andYfjAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("yfj_amount >=", value, "yfjAmount");
            return (Criteria) this;
        }

        public Criteria andYfjAmountLessThan(BigDecimal value) {
            addCriterion("yfj_amount <", value, "yfjAmount");
            return (Criteria) this;
        }

        public Criteria andYfjAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("yfj_amount <=", value, "yfjAmount");
            return (Criteria) this;
        }

        public Criteria andYfjAmountIn(List<BigDecimal> values) {
            addCriterion("yfj_amount in", values, "yfjAmount");
            return (Criteria) this;
        }

        public Criteria andYfjAmountNotIn(List<BigDecimal> values) {
            addCriterion("yfj_amount not in", values, "yfjAmount");
            return (Criteria) this;
        }

        public Criteria andYfjAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("yfj_amount between", value1, value2, "yfjAmount");
            return (Criteria) this;
        }

        public Criteria andYfjAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("yfj_amount not between", value1, value2, "yfjAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountIsNull() {
            addCriterion("trade_amount is null");
            return (Criteria) this;
        }

        public Criteria andTradeAmountIsNotNull() {
            addCriterion("trade_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTradeAmountEqualTo(BigDecimal value) {
            addCriterion("trade_amount =", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountNotEqualTo(BigDecimal value) {
            addCriterion("trade_amount <>", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountGreaterThan(BigDecimal value) {
            addCriterion("trade_amount >", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_amount >=", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountLessThan(BigDecimal value) {
            addCriterion("trade_amount <", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_amount <=", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountIn(List<BigDecimal> values) {
            addCriterion("trade_amount in", values, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountNotIn(List<BigDecimal> values) {
            addCriterion("trade_amount not in", values, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_amount between", value1, value2, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_amount not between", value1, value2, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNull() {
            addCriterion("trade_type is null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNotNull() {
            addCriterion("trade_type is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeEqualTo(Integer value) {
            addCriterion("trade_type =", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotEqualTo(Integer value) {
            addCriterion("trade_type <>", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThan(Integer value) {
            addCriterion("trade_type >", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("trade_type >=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThan(Integer value) {
            addCriterion("trade_type <", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("trade_type <=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIn(List<Integer> values) {
            addCriterion("trade_type in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotIn(List<Integer> values) {
            addCriterion("trade_type not in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeBetween(Integer value1, Integer value2) {
            addCriterion("trade_type between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("trade_type not between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeNameIsNull() {
            addCriterion("trade_name is null");
            return (Criteria) this;
        }

        public Criteria andTradeNameIsNotNull() {
            addCriterion("trade_name is not null");
            return (Criteria) this;
        }

        public Criteria andTradeNameEqualTo(String value) {
            addCriterion("trade_name =", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameNotEqualTo(String value) {
            addCriterion("trade_name <>", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameGreaterThan(String value) {
            addCriterion("trade_name >", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameGreaterThanOrEqualTo(String value) {
            addCriterion("trade_name >=", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameLessThan(String value) {
            addCriterion("trade_name <", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameLessThanOrEqualTo(String value) {
            addCriterion("trade_name <=", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameLike(String value) {
            addCriterion("trade_name like", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameNotLike(String value) {
            addCriterion("trade_name not like", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameIn(List<String> values) {
            addCriterion("trade_name in", values, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameNotIn(List<String> values) {
            addCriterion("trade_name not in", values, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameBetween(String value1, String value2) {
            addCriterion("trade_name between", value1, value2, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameNotBetween(String value1, String value2) {
            addCriterion("trade_name not between", value1, value2, "tradeName");
            return (Criteria) this;
        }

        public Criteria andBorrowDatetimeIsNull() {
            addCriterion("borrow_datetime is null");
            return (Criteria) this;
        }

        public Criteria andBorrowDatetimeIsNotNull() {
            addCriterion("borrow_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowDatetimeEqualTo(Date value) {
            addCriterion("borrow_datetime =", value, "borrowDatetime");
            return (Criteria) this;
        }

        public Criteria andBorrowDatetimeNotEqualTo(Date value) {
            addCriterion("borrow_datetime <>", value, "borrowDatetime");
            return (Criteria) this;
        }

        public Criteria andBorrowDatetimeGreaterThan(Date value) {
            addCriterion("borrow_datetime >", value, "borrowDatetime");
            return (Criteria) this;
        }

        public Criteria andBorrowDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("borrow_datetime >=", value, "borrowDatetime");
            return (Criteria) this;
        }

        public Criteria andBorrowDatetimeLessThan(Date value) {
            addCriterion("borrow_datetime <", value, "borrowDatetime");
            return (Criteria) this;
        }

        public Criteria andBorrowDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("borrow_datetime <=", value, "borrowDatetime");
            return (Criteria) this;
        }

        public Criteria andBorrowDatetimeIn(List<Date> values) {
            addCriterion("borrow_datetime in", values, "borrowDatetime");
            return (Criteria) this;
        }

        public Criteria andBorrowDatetimeNotIn(List<Date> values) {
            addCriterion("borrow_datetime not in", values, "borrowDatetime");
            return (Criteria) this;
        }

        public Criteria andBorrowDatetimeBetween(Date value1, Date value2) {
            addCriterion("borrow_datetime between", value1, value2, "borrowDatetime");
            return (Criteria) this;
        }

        public Criteria andBorrowDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("borrow_datetime not between", value1, value2, "borrowDatetime");
            return (Criteria) this;
        }

        public Criteria andUseTimeSecondsIsNull() {
            addCriterion("use_time_seconds is null");
            return (Criteria) this;
        }

        public Criteria andUseTimeSecondsIsNotNull() {
            addCriterion("use_time_seconds is not null");
            return (Criteria) this;
        }

        public Criteria andUseTimeSecondsEqualTo(Long value) {
            addCriterion("use_time_seconds =", value, "useTimeSeconds");
            return (Criteria) this;
        }

        public Criteria andUseTimeSecondsNotEqualTo(Long value) {
            addCriterion("use_time_seconds <>", value, "useTimeSeconds");
            return (Criteria) this;
        }

        public Criteria andUseTimeSecondsGreaterThan(Long value) {
            addCriterion("use_time_seconds >", value, "useTimeSeconds");
            return (Criteria) this;
        }

        public Criteria andUseTimeSecondsGreaterThanOrEqualTo(Long value) {
            addCriterion("use_time_seconds >=", value, "useTimeSeconds");
            return (Criteria) this;
        }

        public Criteria andUseTimeSecondsLessThan(Long value) {
            addCriterion("use_time_seconds <", value, "useTimeSeconds");
            return (Criteria) this;
        }

        public Criteria andUseTimeSecondsLessThanOrEqualTo(Long value) {
            addCriterion("use_time_seconds <=", value, "useTimeSeconds");
            return (Criteria) this;
        }

        public Criteria andUseTimeSecondsIn(List<Long> values) {
            addCriterion("use_time_seconds in", values, "useTimeSeconds");
            return (Criteria) this;
        }

        public Criteria andUseTimeSecondsNotIn(List<Long> values) {
            addCriterion("use_time_seconds not in", values, "useTimeSeconds");
            return (Criteria) this;
        }

        public Criteria andUseTimeSecondsBetween(Long value1, Long value2) {
            addCriterion("use_time_seconds between", value1, value2, "useTimeSeconds");
            return (Criteria) this;
        }

        public Criteria andUseTimeSecondsNotBetween(Long value1, Long value2) {
            addCriterion("use_time_seconds not between", value1, value2, "useTimeSeconds");
            return (Criteria) this;
        }

        public Criteria andBackDatetimeIsNull() {
            addCriterion("back_datetime is null");
            return (Criteria) this;
        }

        public Criteria andBackDatetimeIsNotNull() {
            addCriterion("back_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andBackDatetimeEqualTo(Date value) {
            addCriterion("back_datetime =", value, "backDatetime");
            return (Criteria) this;
        }

        public Criteria andBackDatetimeNotEqualTo(Date value) {
            addCriterion("back_datetime <>", value, "backDatetime");
            return (Criteria) this;
        }

        public Criteria andBackDatetimeGreaterThan(Date value) {
            addCriterion("back_datetime >", value, "backDatetime");
            return (Criteria) this;
        }

        public Criteria andBackDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("back_datetime >=", value, "backDatetime");
            return (Criteria) this;
        }

        public Criteria andBackDatetimeLessThan(Date value) {
            addCriterion("back_datetime <", value, "backDatetime");
            return (Criteria) this;
        }

        public Criteria andBackDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("back_datetime <=", value, "backDatetime");
            return (Criteria) this;
        }

        public Criteria andBackDatetimeIn(List<Date> values) {
            addCriterion("back_datetime in", values, "backDatetime");
            return (Criteria) this;
        }

        public Criteria andBackDatetimeNotIn(List<Date> values) {
            addCriterion("back_datetime not in", values, "backDatetime");
            return (Criteria) this;
        }

        public Criteria andBackDatetimeBetween(Date value1, Date value2) {
            addCriterion("back_datetime between", value1, value2, "backDatetime");
            return (Criteria) this;
        }

        public Criteria andBackDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("back_datetime not between", value1, value2, "backDatetime");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNameIsNull() {
            addCriterion("trade_status_name is null");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNameIsNotNull() {
            addCriterion("trade_status_name is not null");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNameEqualTo(String value) {
            addCriterion("trade_status_name =", value, "tradeStatusName");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNameNotEqualTo(String value) {
            addCriterion("trade_status_name <>", value, "tradeStatusName");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNameGreaterThan(String value) {
            addCriterion("trade_status_name >", value, "tradeStatusName");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("trade_status_name >=", value, "tradeStatusName");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNameLessThan(String value) {
            addCriterion("trade_status_name <", value, "tradeStatusName");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNameLessThanOrEqualTo(String value) {
            addCriterion("trade_status_name <=", value, "tradeStatusName");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNameLike(String value) {
            addCriterion("trade_status_name like", value, "tradeStatusName");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNameNotLike(String value) {
            addCriterion("trade_status_name not like", value, "tradeStatusName");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNameIn(List<String> values) {
            addCriterion("trade_status_name in", values, "tradeStatusName");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNameNotIn(List<String> values) {
            addCriterion("trade_status_name not in", values, "tradeStatusName");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNameBetween(String value1, String value2) {
            addCriterion("trade_status_name between", value1, value2, "tradeStatusName");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNameNotBetween(String value1, String value2) {
            addCriterion("trade_status_name not between", value1, value2, "tradeStatusName");
            return (Criteria) this;
        }

        public Criteria andTradeStatusIsNull() {
            addCriterion("trade_status is null");
            return (Criteria) this;
        }

        public Criteria andTradeStatusIsNotNull() {
            addCriterion("trade_status is not null");
            return (Criteria) this;
        }

        public Criteria andTradeStatusEqualTo(Integer value) {
            addCriterion("trade_status =", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNotEqualTo(Integer value) {
            addCriterion("trade_status <>", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusGreaterThan(Integer value) {
            addCriterion("trade_status >", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("trade_status >=", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusLessThan(Integer value) {
            addCriterion("trade_status <", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusLessThanOrEqualTo(Integer value) {
            addCriterion("trade_status <=", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusIn(List<Integer> values) {
            addCriterion("trade_status in", values, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNotIn(List<Integer> values) {
            addCriterion("trade_status not in", values, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusBetween(Integer value1, Integer value2) {
            addCriterion("trade_status between", value1, value2, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("trade_status not between", value1, value2, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andXCoordinateIsNull() {
            addCriterion("x_coordinate is null");
            return (Criteria) this;
        }

        public Criteria andXCoordinateIsNotNull() {
            addCriterion("x_coordinate is not null");
            return (Criteria) this;
        }

        public Criteria andXCoordinateEqualTo(BigDecimal value) {
            addCriterion("x_coordinate =", value, "xCoordinate");
            return (Criteria) this;
        }

        public Criteria andXCoordinateNotEqualTo(BigDecimal value) {
            addCriterion("x_coordinate <>", value, "xCoordinate");
            return (Criteria) this;
        }

        public Criteria andXCoordinateGreaterThan(BigDecimal value) {
            addCriterion("x_coordinate >", value, "xCoordinate");
            return (Criteria) this;
        }

        public Criteria andXCoordinateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("x_coordinate >=", value, "xCoordinate");
            return (Criteria) this;
        }

        public Criteria andXCoordinateLessThan(BigDecimal value) {
            addCriterion("x_coordinate <", value, "xCoordinate");
            return (Criteria) this;
        }

        public Criteria andXCoordinateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("x_coordinate <=", value, "xCoordinate");
            return (Criteria) this;
        }

        public Criteria andXCoordinateIn(List<BigDecimal> values) {
            addCriterion("x_coordinate in", values, "xCoordinate");
            return (Criteria) this;
        }

        public Criteria andXCoordinateNotIn(List<BigDecimal> values) {
            addCriterion("x_coordinate not in", values, "xCoordinate");
            return (Criteria) this;
        }

        public Criteria andXCoordinateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("x_coordinate between", value1, value2, "xCoordinate");
            return (Criteria) this;
        }

        public Criteria andXCoordinateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("x_coordinate not between", value1, value2, "xCoordinate");
            return (Criteria) this;
        }

        public Criteria andYCoordinateIsNull() {
            addCriterion("y_coordinate is null");
            return (Criteria) this;
        }

        public Criteria andYCoordinateIsNotNull() {
            addCriterion("y_coordinate is not null");
            return (Criteria) this;
        }

        public Criteria andYCoordinateEqualTo(BigDecimal value) {
            addCriterion("y_coordinate =", value, "yCoordinate");
            return (Criteria) this;
        }

        public Criteria andYCoordinateNotEqualTo(BigDecimal value) {
            addCriterion("y_coordinate <>", value, "yCoordinate");
            return (Criteria) this;
        }

        public Criteria andYCoordinateGreaterThan(BigDecimal value) {
            addCriterion("y_coordinate >", value, "yCoordinate");
            return (Criteria) this;
        }

        public Criteria andYCoordinateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("y_coordinate >=", value, "yCoordinate");
            return (Criteria) this;
        }

        public Criteria andYCoordinateLessThan(BigDecimal value) {
            addCriterion("y_coordinate <", value, "yCoordinate");
            return (Criteria) this;
        }

        public Criteria andYCoordinateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("y_coordinate <=", value, "yCoordinate");
            return (Criteria) this;
        }

        public Criteria andYCoordinateIn(List<BigDecimal> values) {
            addCriterion("y_coordinate in", values, "yCoordinate");
            return (Criteria) this;
        }

        public Criteria andYCoordinateNotIn(List<BigDecimal> values) {
            addCriterion("y_coordinate not in", values, "yCoordinate");
            return (Criteria) this;
        }

        public Criteria andYCoordinateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("y_coordinate between", value1, value2, "yCoordinate");
            return (Criteria) this;
        }

        public Criteria andYCoordinateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("y_coordinate not between", value1, value2, "yCoordinate");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNameIsNull() {
            addCriterion("benefit_status_name is null");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNameIsNotNull() {
            addCriterion("benefit_status_name is not null");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNameEqualTo(String value) {
            addCriterion("benefit_status_name =", value, "benefitStatusName");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNameNotEqualTo(String value) {
            addCriterion("benefit_status_name <>", value, "benefitStatusName");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNameGreaterThan(String value) {
            addCriterion("benefit_status_name >", value, "benefitStatusName");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("benefit_status_name >=", value, "benefitStatusName");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNameLessThan(String value) {
            addCriterion("benefit_status_name <", value, "benefitStatusName");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNameLessThanOrEqualTo(String value) {
            addCriterion("benefit_status_name <=", value, "benefitStatusName");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNameLike(String value) {
            addCriterion("benefit_status_name like", value, "benefitStatusName");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNameNotLike(String value) {
            addCriterion("benefit_status_name not like", value, "benefitStatusName");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNameIn(List<String> values) {
            addCriterion("benefit_status_name in", values, "benefitStatusName");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNameNotIn(List<String> values) {
            addCriterion("benefit_status_name not in", values, "benefitStatusName");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNameBetween(String value1, String value2) {
            addCriterion("benefit_status_name between", value1, value2, "benefitStatusName");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNameNotBetween(String value1, String value2) {
            addCriterion("benefit_status_name not between", value1, value2, "benefitStatusName");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusIsNull() {
            addCriterion("benefit_status is null");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusIsNotNull() {
            addCriterion("benefit_status is not null");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusEqualTo(Integer value) {
            addCriterion("benefit_status =", value, "benefitStatus");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNotEqualTo(Integer value) {
            addCriterion("benefit_status <>", value, "benefitStatus");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusGreaterThan(Integer value) {
            addCriterion("benefit_status >", value, "benefitStatus");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("benefit_status >=", value, "benefitStatus");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusLessThan(Integer value) {
            addCriterion("benefit_status <", value, "benefitStatus");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusLessThanOrEqualTo(Integer value) {
            addCriterion("benefit_status <=", value, "benefitStatus");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusIn(List<Integer> values) {
            addCriterion("benefit_status in", values, "benefitStatus");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNotIn(List<Integer> values) {
            addCriterion("benefit_status not in", values, "benefitStatus");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusBetween(Integer value1, Integer value2) {
            addCriterion("benefit_status between", value1, value2, "benefitStatus");
            return (Criteria) this;
        }

        public Criteria andBenefitStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("benefit_status not between", value1, value2, "benefitStatus");
            return (Criteria) this;
        }

        public Criteria andBenefitDatetimeIsNull() {
            addCriterion("benefit_datetime is null");
            return (Criteria) this;
        }

        public Criteria andBenefitDatetimeIsNotNull() {
            addCriterion("benefit_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andBenefitDatetimeEqualTo(Date value) {
            addCriterion("benefit_datetime =", value, "benefitDatetime");
            return (Criteria) this;
        }

        public Criteria andBenefitDatetimeNotEqualTo(Date value) {
            addCriterion("benefit_datetime <>", value, "benefitDatetime");
            return (Criteria) this;
        }

        public Criteria andBenefitDatetimeGreaterThan(Date value) {
            addCriterion("benefit_datetime >", value, "benefitDatetime");
            return (Criteria) this;
        }

        public Criteria andBenefitDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("benefit_datetime >=", value, "benefitDatetime");
            return (Criteria) this;
        }

        public Criteria andBenefitDatetimeLessThan(Date value) {
            addCriterion("benefit_datetime <", value, "benefitDatetime");
            return (Criteria) this;
        }

        public Criteria andBenefitDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("benefit_datetime <=", value, "benefitDatetime");
            return (Criteria) this;
        }

        public Criteria andBenefitDatetimeIn(List<Date> values) {
            addCriterion("benefit_datetime in", values, "benefitDatetime");
            return (Criteria) this;
        }

        public Criteria andBenefitDatetimeNotIn(List<Date> values) {
            addCriterion("benefit_datetime not in", values, "benefitDatetime");
            return (Criteria) this;
        }

        public Criteria andBenefitDatetimeBetween(Date value1, Date value2) {
            addCriterion("benefit_datetime between", value1, value2, "benefitDatetime");
            return (Criteria) this;
        }

        public Criteria andBenefitDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("benefit_datetime not between", value1, value2, "benefitDatetime");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountIsNull() {
            addCriterion("platform_amount is null");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountIsNotNull() {
            addCriterion("platform_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountEqualTo(BigDecimal value) {
            addCriterion("platform_amount =", value, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountNotEqualTo(BigDecimal value) {
            addCriterion("platform_amount <>", value, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountGreaterThan(BigDecimal value) {
            addCriterion("platform_amount >", value, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_amount >=", value, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountLessThan(BigDecimal value) {
            addCriterion("platform_amount <", value, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_amount <=", value, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountIn(List<BigDecimal> values) {
            addCriterion("platform_amount in", values, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountNotIn(List<BigDecimal> values) {
            addCriterion("platform_amount not in", values, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_amount between", value1, value2, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_amount not between", value1, value2, "platformAmount");
            return (Criteria) this;
        }

        public Criteria andAgents1AmountIsNull() {
            addCriterion("agents1_amount is null");
            return (Criteria) this;
        }

        public Criteria andAgents1AmountIsNotNull() {
            addCriterion("agents1_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAgents1AmountEqualTo(BigDecimal value) {
            addCriterion("agents1_amount =", value, "agents1Amount");
            return (Criteria) this;
        }

        public Criteria andAgents1AmountNotEqualTo(BigDecimal value) {
            addCriterion("agents1_amount <>", value, "agents1Amount");
            return (Criteria) this;
        }

        public Criteria andAgents1AmountGreaterThan(BigDecimal value) {
            addCriterion("agents1_amount >", value, "agents1Amount");
            return (Criteria) this;
        }

        public Criteria andAgents1AmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("agents1_amount >=", value, "agents1Amount");
            return (Criteria) this;
        }

        public Criteria andAgents1AmountLessThan(BigDecimal value) {
            addCriterion("agents1_amount <", value, "agents1Amount");
            return (Criteria) this;
        }

        public Criteria andAgents1AmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("agents1_amount <=", value, "agents1Amount");
            return (Criteria) this;
        }

        public Criteria andAgents1AmountIn(List<BigDecimal> values) {
            addCriterion("agents1_amount in", values, "agents1Amount");
            return (Criteria) this;
        }

        public Criteria andAgents1AmountNotIn(List<BigDecimal> values) {
            addCriterion("agents1_amount not in", values, "agents1Amount");
            return (Criteria) this;
        }

        public Criteria andAgents1AmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agents1_amount between", value1, value2, "agents1Amount");
            return (Criteria) this;
        }

        public Criteria andAgents1AmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agents1_amount not between", value1, value2, "agents1Amount");
            return (Criteria) this;
        }

        public Criteria andAgents2AmountIsNull() {
            addCriterion("agents2_amount is null");
            return (Criteria) this;
        }

        public Criteria andAgents2AmountIsNotNull() {
            addCriterion("agents2_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAgents2AmountEqualTo(BigDecimal value) {
            addCriterion("agents2_amount =", value, "agents2Amount");
            return (Criteria) this;
        }

        public Criteria andAgents2AmountNotEqualTo(BigDecimal value) {
            addCriterion("agents2_amount <>", value, "agents2Amount");
            return (Criteria) this;
        }

        public Criteria andAgents2AmountGreaterThan(BigDecimal value) {
            addCriterion("agents2_amount >", value, "agents2Amount");
            return (Criteria) this;
        }

        public Criteria andAgents2AmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("agents2_amount >=", value, "agents2Amount");
            return (Criteria) this;
        }

        public Criteria andAgents2AmountLessThan(BigDecimal value) {
            addCriterion("agents2_amount <", value, "agents2Amount");
            return (Criteria) this;
        }

        public Criteria andAgents2AmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("agents2_amount <=", value, "agents2Amount");
            return (Criteria) this;
        }

        public Criteria andAgents2AmountIn(List<BigDecimal> values) {
            addCriterion("agents2_amount in", values, "agents2Amount");
            return (Criteria) this;
        }

        public Criteria andAgents2AmountNotIn(List<BigDecimal> values) {
            addCriterion("agents2_amount not in", values, "agents2Amount");
            return (Criteria) this;
        }

        public Criteria andAgents2AmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agents2_amount between", value1, value2, "agents2Amount");
            return (Criteria) this;
        }

        public Criteria andAgents2AmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agents2_amount not between", value1, value2, "agents2Amount");
            return (Criteria) this;
        }

        public Criteria andAgents3AmountIsNull() {
            addCriterion("agents3_amount is null");
            return (Criteria) this;
        }

        public Criteria andAgents3AmountIsNotNull() {
            addCriterion("agents3_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAgents3AmountEqualTo(BigDecimal value) {
            addCriterion("agents3_amount =", value, "agents3Amount");
            return (Criteria) this;
        }

        public Criteria andAgents3AmountNotEqualTo(BigDecimal value) {
            addCriterion("agents3_amount <>", value, "agents3Amount");
            return (Criteria) this;
        }

        public Criteria andAgents3AmountGreaterThan(BigDecimal value) {
            addCriterion("agents3_amount >", value, "agents3Amount");
            return (Criteria) this;
        }

        public Criteria andAgents3AmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("agents3_amount >=", value, "agents3Amount");
            return (Criteria) this;
        }

        public Criteria andAgents3AmountLessThan(BigDecimal value) {
            addCriterion("agents3_amount <", value, "agents3Amount");
            return (Criteria) this;
        }

        public Criteria andAgents3AmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("agents3_amount <=", value, "agents3Amount");
            return (Criteria) this;
        }

        public Criteria andAgents3AmountIn(List<BigDecimal> values) {
            addCriterion("agents3_amount in", values, "agents3Amount");
            return (Criteria) this;
        }

        public Criteria andAgents3AmountNotIn(List<BigDecimal> values) {
            addCriterion("agents3_amount not in", values, "agents3Amount");
            return (Criteria) this;
        }

        public Criteria andAgents3AmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agents3_amount between", value1, value2, "agents3Amount");
            return (Criteria) this;
        }

        public Criteria andAgents3AmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agents3_amount not between", value1, value2, "agents3Amount");
            return (Criteria) this;
        }

        public Criteria andShopkeeperAmountIsNull() {
            addCriterion("shopkeeper_amount is null");
            return (Criteria) this;
        }

        public Criteria andShopkeeperAmountIsNotNull() {
            addCriterion("shopkeeper_amount is not null");
            return (Criteria) this;
        }

        public Criteria andShopkeeperAmountEqualTo(BigDecimal value) {
            addCriterion("shopkeeper_amount =", value, "shopkeeperAmount");
            return (Criteria) this;
        }

        public Criteria andShopkeeperAmountNotEqualTo(BigDecimal value) {
            addCriterion("shopkeeper_amount <>", value, "shopkeeperAmount");
            return (Criteria) this;
        }

        public Criteria andShopkeeperAmountGreaterThan(BigDecimal value) {
            addCriterion("shopkeeper_amount >", value, "shopkeeperAmount");
            return (Criteria) this;
        }

        public Criteria andShopkeeperAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("shopkeeper_amount >=", value, "shopkeeperAmount");
            return (Criteria) this;
        }

        public Criteria andShopkeeperAmountLessThan(BigDecimal value) {
            addCriterion("shopkeeper_amount <", value, "shopkeeperAmount");
            return (Criteria) this;
        }

        public Criteria andShopkeeperAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("shopkeeper_amount <=", value, "shopkeeperAmount");
            return (Criteria) this;
        }

        public Criteria andShopkeeperAmountIn(List<BigDecimal> values) {
            addCriterion("shopkeeper_amount in", values, "shopkeeperAmount");
            return (Criteria) this;
        }

        public Criteria andShopkeeperAmountNotIn(List<BigDecimal> values) {
            addCriterion("shopkeeper_amount not in", values, "shopkeeperAmount");
            return (Criteria) this;
        }

        public Criteria andShopkeeperAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shopkeeper_amount between", value1, value2, "shopkeeperAmount");
            return (Criteria) this;
        }

        public Criteria andShopkeeperAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shopkeeper_amount not between", value1, value2, "shopkeeperAmount");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessAmountIsNull() {
            addCriterion("alliance_business_amount is null");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessAmountIsNotNull() {
            addCriterion("alliance_business_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessAmountEqualTo(BigDecimal value) {
            addCriterion("alliance_business_amount =", value, "allianceBusinessAmount");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessAmountNotEqualTo(BigDecimal value) {
            addCriterion("alliance_business_amount <>", value, "allianceBusinessAmount");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessAmountGreaterThan(BigDecimal value) {
            addCriterion("alliance_business_amount >", value, "allianceBusinessAmount");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("alliance_business_amount >=", value, "allianceBusinessAmount");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessAmountLessThan(BigDecimal value) {
            addCriterion("alliance_business_amount <", value, "allianceBusinessAmount");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("alliance_business_amount <=", value, "allianceBusinessAmount");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessAmountIn(List<BigDecimal> values) {
            addCriterion("alliance_business_amount in", values, "allianceBusinessAmount");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessAmountNotIn(List<BigDecimal> values) {
            addCriterion("alliance_business_amount not in", values, "allianceBusinessAmount");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("alliance_business_amount between", value1, value2, "allianceBusinessAmount");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("alliance_business_amount not between", value1, value2, "allianceBusinessAmount");
            return (Criteria) this;
        }

        public Criteria andBackTradeIdIsNull() {
            addCriterion("back_trade_id is null");
            return (Criteria) this;
        }

        public Criteria andBackTradeIdIsNotNull() {
            addCriterion("back_trade_id is not null");
            return (Criteria) this;
        }

        public Criteria andBackTradeIdEqualTo(Long value) {
            addCriterion("back_trade_id =", value, "backTradeId");
            return (Criteria) this;
        }

        public Criteria andBackTradeIdNotEqualTo(Long value) {
            addCriterion("back_trade_id <>", value, "backTradeId");
            return (Criteria) this;
        }

        public Criteria andBackTradeIdGreaterThan(Long value) {
            addCriterion("back_trade_id >", value, "backTradeId");
            return (Criteria) this;
        }

        public Criteria andBackTradeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("back_trade_id >=", value, "backTradeId");
            return (Criteria) this;
        }

        public Criteria andBackTradeIdLessThan(Long value) {
            addCriterion("back_trade_id <", value, "backTradeId");
            return (Criteria) this;
        }

        public Criteria andBackTradeIdLessThanOrEqualTo(Long value) {
            addCriterion("back_trade_id <=", value, "backTradeId");
            return (Criteria) this;
        }

        public Criteria andBackTradeIdIn(List<Long> values) {
            addCriterion("back_trade_id in", values, "backTradeId");
            return (Criteria) this;
        }

        public Criteria andBackTradeIdNotIn(List<Long> values) {
            addCriterion("back_trade_id not in", values, "backTradeId");
            return (Criteria) this;
        }

        public Criteria andBackTradeIdBetween(Long value1, Long value2) {
            addCriterion("back_trade_id between", value1, value2, "backTradeId");
            return (Criteria) this;
        }

        public Criteria andBackTradeIdNotBetween(Long value1, Long value2) {
            addCriterion("back_trade_id not between", value1, value2, "backTradeId");
            return (Criteria) this;
        }

        public Criteria andTradeAddressIsNull() {
            addCriterion("trade_address is null");
            return (Criteria) this;
        }

        public Criteria andTradeAddressIsNotNull() {
            addCriterion("trade_address is not null");
            return (Criteria) this;
        }

        public Criteria andTradeAddressEqualTo(String value) {
            addCriterion("trade_address =", value, "tradeAddress");
            return (Criteria) this;
        }

        public Criteria andTradeAddressNotEqualTo(String value) {
            addCriterion("trade_address <>", value, "tradeAddress");
            return (Criteria) this;
        }

        public Criteria andTradeAddressGreaterThan(String value) {
            addCriterion("trade_address >", value, "tradeAddress");
            return (Criteria) this;
        }

        public Criteria andTradeAddressGreaterThanOrEqualTo(String value) {
            addCriterion("trade_address >=", value, "tradeAddress");
            return (Criteria) this;
        }

        public Criteria andTradeAddressLessThan(String value) {
            addCriterion("trade_address <", value, "tradeAddress");
            return (Criteria) this;
        }

        public Criteria andTradeAddressLessThanOrEqualTo(String value) {
            addCriterion("trade_address <=", value, "tradeAddress");
            return (Criteria) this;
        }

        public Criteria andTradeAddressLike(String value) {
            addCriterion("trade_address like", value, "tradeAddress");
            return (Criteria) this;
        }

        public Criteria andTradeAddressNotLike(String value) {
            addCriterion("trade_address not like", value, "tradeAddress");
            return (Criteria) this;
        }

        public Criteria andTradeAddressIn(List<String> values) {
            addCriterion("trade_address in", values, "tradeAddress");
            return (Criteria) this;
        }

        public Criteria andTradeAddressNotIn(List<String> values) {
            addCriterion("trade_address not in", values, "tradeAddress");
            return (Criteria) this;
        }

        public Criteria andTradeAddressBetween(String value1, String value2) {
            addCriterion("trade_address between", value1, value2, "tradeAddress");
            return (Criteria) this;
        }

        public Criteria andTradeAddressNotBetween(String value1, String value2) {
            addCriterion("trade_address not between", value1, value2, "tradeAddress");
            return (Criteria) this;
        }

        public Criteria andTradeZoneIsNull() {
            addCriterion("trade_zone is null");
            return (Criteria) this;
        }

        public Criteria andTradeZoneIsNotNull() {
            addCriterion("trade_zone is not null");
            return (Criteria) this;
        }

        public Criteria andTradeZoneEqualTo(String value) {
            addCriterion("trade_zone =", value, "tradeZone");
            return (Criteria) this;
        }

        public Criteria andTradeZoneNotEqualTo(String value) {
            addCriterion("trade_zone <>", value, "tradeZone");
            return (Criteria) this;
        }

        public Criteria andTradeZoneGreaterThan(String value) {
            addCriterion("trade_zone >", value, "tradeZone");
            return (Criteria) this;
        }

        public Criteria andTradeZoneGreaterThanOrEqualTo(String value) {
            addCriterion("trade_zone >=", value, "tradeZone");
            return (Criteria) this;
        }

        public Criteria andTradeZoneLessThan(String value) {
            addCriterion("trade_zone <", value, "tradeZone");
            return (Criteria) this;
        }

        public Criteria andTradeZoneLessThanOrEqualTo(String value) {
            addCriterion("trade_zone <=", value, "tradeZone");
            return (Criteria) this;
        }

        public Criteria andTradeZoneLike(String value) {
            addCriterion("trade_zone like", value, "tradeZone");
            return (Criteria) this;
        }

        public Criteria andTradeZoneNotLike(String value) {
            addCriterion("trade_zone not like", value, "tradeZone");
            return (Criteria) this;
        }

        public Criteria andTradeZoneIn(List<String> values) {
            addCriterion("trade_zone in", values, "tradeZone");
            return (Criteria) this;
        }

        public Criteria andTradeZoneNotIn(List<String> values) {
            addCriterion("trade_zone not in", values, "tradeZone");
            return (Criteria) this;
        }

        public Criteria andTradeZoneBetween(String value1, String value2) {
            addCriterion("trade_zone between", value1, value2, "tradeZone");
            return (Criteria) this;
        }

        public Criteria andTradeZoneNotBetween(String value1, String value2) {
            addCriterion("trade_zone not between", value1, value2, "tradeZone");
            return (Criteria) this;
        }

        public Criteria andTradeCityIsNull() {
            addCriterion("trade_city is null");
            return (Criteria) this;
        }

        public Criteria andTradeCityIsNotNull() {
            addCriterion("trade_city is not null");
            return (Criteria) this;
        }

        public Criteria andTradeCityEqualTo(String value) {
            addCriterion("trade_city =", value, "tradeCity");
            return (Criteria) this;
        }

        public Criteria andTradeCityNotEqualTo(String value) {
            addCriterion("trade_city <>", value, "tradeCity");
            return (Criteria) this;
        }

        public Criteria andTradeCityGreaterThan(String value) {
            addCriterion("trade_city >", value, "tradeCity");
            return (Criteria) this;
        }

        public Criteria andTradeCityGreaterThanOrEqualTo(String value) {
            addCriterion("trade_city >=", value, "tradeCity");
            return (Criteria) this;
        }

        public Criteria andTradeCityLessThan(String value) {
            addCriterion("trade_city <", value, "tradeCity");
            return (Criteria) this;
        }

        public Criteria andTradeCityLessThanOrEqualTo(String value) {
            addCriterion("trade_city <=", value, "tradeCity");
            return (Criteria) this;
        }

        public Criteria andTradeCityLike(String value) {
            addCriterion("trade_city like", value, "tradeCity");
            return (Criteria) this;
        }

        public Criteria andTradeCityNotLike(String value) {
            addCriterion("trade_city not like", value, "tradeCity");
            return (Criteria) this;
        }

        public Criteria andTradeCityIn(List<String> values) {
            addCriterion("trade_city in", values, "tradeCity");
            return (Criteria) this;
        }

        public Criteria andTradeCityNotIn(List<String> values) {
            addCriterion("trade_city not in", values, "tradeCity");
            return (Criteria) this;
        }

        public Criteria andTradeCityBetween(String value1, String value2) {
            addCriterion("trade_city between", value1, value2, "tradeCity");
            return (Criteria) this;
        }

        public Criteria andTradeCityNotBetween(String value1, String value2) {
            addCriterion("trade_city not between", value1, value2, "tradeCity");
            return (Criteria) this;
        }

        public Criteria andTradeProvinceIsNull() {
            addCriterion("trade_province is null");
            return (Criteria) this;
        }

        public Criteria andTradeProvinceIsNotNull() {
            addCriterion("trade_province is not null");
            return (Criteria) this;
        }

        public Criteria andTradeProvinceEqualTo(String value) {
            addCriterion("trade_province =", value, "tradeProvince");
            return (Criteria) this;
        }

        public Criteria andTradeProvinceNotEqualTo(String value) {
            addCriterion("trade_province <>", value, "tradeProvince");
            return (Criteria) this;
        }

        public Criteria andTradeProvinceGreaterThan(String value) {
            addCriterion("trade_province >", value, "tradeProvince");
            return (Criteria) this;
        }

        public Criteria andTradeProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("trade_province >=", value, "tradeProvince");
            return (Criteria) this;
        }

        public Criteria andTradeProvinceLessThan(String value) {
            addCriterion("trade_province <", value, "tradeProvince");
            return (Criteria) this;
        }

        public Criteria andTradeProvinceLessThanOrEqualTo(String value) {
            addCriterion("trade_province <=", value, "tradeProvince");
            return (Criteria) this;
        }

        public Criteria andTradeProvinceLike(String value) {
            addCriterion("trade_province like", value, "tradeProvince");
            return (Criteria) this;
        }

        public Criteria andTradeProvinceNotLike(String value) {
            addCriterion("trade_province not like", value, "tradeProvince");
            return (Criteria) this;
        }

        public Criteria andTradeProvinceIn(List<String> values) {
            addCriterion("trade_province in", values, "tradeProvince");
            return (Criteria) this;
        }

        public Criteria andTradeProvinceNotIn(List<String> values) {
            addCriterion("trade_province not in", values, "tradeProvince");
            return (Criteria) this;
        }

        public Criteria andTradeProvinceBetween(String value1, String value2) {
            addCriterion("trade_province between", value1, value2, "tradeProvince");
            return (Criteria) this;
        }

        public Criteria andTradeProvinceNotBetween(String value1, String value2) {
            addCriterion("trade_province not between", value1, value2, "tradeProvince");
            return (Criteria) this;
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

        public Criteria andAgents1IdIsNull() {
            addCriterion("agents1_id is null");
            return (Criteria) this;
        }

        public Criteria andAgents1IdIsNotNull() {
            addCriterion("agents1_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgents1IdEqualTo(Long value) {
            addCriterion("agents1_id =", value, "agents1Id");
            return (Criteria) this;
        }

        public Criteria andAgents1IdNotEqualTo(Long value) {
            addCriterion("agents1_id <>", value, "agents1Id");
            return (Criteria) this;
        }

        public Criteria andAgents1IdGreaterThan(Long value) {
            addCriterion("agents1_id >", value, "agents1Id");
            return (Criteria) this;
        }

        public Criteria andAgents1IdGreaterThanOrEqualTo(Long value) {
            addCriterion("agents1_id >=", value, "agents1Id");
            return (Criteria) this;
        }

        public Criteria andAgents1IdLessThan(Long value) {
            addCriterion("agents1_id <", value, "agents1Id");
            return (Criteria) this;
        }

        public Criteria andAgents1IdLessThanOrEqualTo(Long value) {
            addCriterion("agents1_id <=", value, "agents1Id");
            return (Criteria) this;
        }

        public Criteria andAgents1IdIn(List<Long> values) {
            addCriterion("agents1_id in", values, "agents1Id");
            return (Criteria) this;
        }

        public Criteria andAgents1IdNotIn(List<Long> values) {
            addCriterion("agents1_id not in", values, "agents1Id");
            return (Criteria) this;
        }

        public Criteria andAgents1IdBetween(Long value1, Long value2) {
            addCriterion("agents1_id between", value1, value2, "agents1Id");
            return (Criteria) this;
        }

        public Criteria andAgents1IdNotBetween(Long value1, Long value2) {
            addCriterion("agents1_id not between", value1, value2, "agents1Id");
            return (Criteria) this;
        }

        public Criteria andAgents2IdIsNull() {
            addCriterion("agents2_id is null");
            return (Criteria) this;
        }

        public Criteria andAgents2IdIsNotNull() {
            addCriterion("agents2_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgents2IdEqualTo(Long value) {
            addCriterion("agents2_id =", value, "agents2Id");
            return (Criteria) this;
        }

        public Criteria andAgents2IdNotEqualTo(Long value) {
            addCriterion("agents2_id <>", value, "agents2Id");
            return (Criteria) this;
        }

        public Criteria andAgents2IdGreaterThan(Long value) {
            addCriterion("agents2_id >", value, "agents2Id");
            return (Criteria) this;
        }

        public Criteria andAgents2IdGreaterThanOrEqualTo(Long value) {
            addCriterion("agents2_id >=", value, "agents2Id");
            return (Criteria) this;
        }

        public Criteria andAgents2IdLessThan(Long value) {
            addCriterion("agents2_id <", value, "agents2Id");
            return (Criteria) this;
        }

        public Criteria andAgents2IdLessThanOrEqualTo(Long value) {
            addCriterion("agents2_id <=", value, "agents2Id");
            return (Criteria) this;
        }

        public Criteria andAgents2IdIn(List<Long> values) {
            addCriterion("agents2_id in", values, "agents2Id");
            return (Criteria) this;
        }

        public Criteria andAgents2IdNotIn(List<Long> values) {
            addCriterion("agents2_id not in", values, "agents2Id");
            return (Criteria) this;
        }

        public Criteria andAgents2IdBetween(Long value1, Long value2) {
            addCriterion("agents2_id between", value1, value2, "agents2Id");
            return (Criteria) this;
        }

        public Criteria andAgents2IdNotBetween(Long value1, Long value2) {
            addCriterion("agents2_id not between", value1, value2, "agents2Id");
            return (Criteria) this;
        }

        public Criteria andAgents3IdIsNull() {
            addCriterion("agents3_id is null");
            return (Criteria) this;
        }

        public Criteria andAgents3IdIsNotNull() {
            addCriterion("agents3_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgents3IdEqualTo(Long value) {
            addCriterion("agents3_id =", value, "agents3Id");
            return (Criteria) this;
        }

        public Criteria andAgents3IdNotEqualTo(Long value) {
            addCriterion("agents3_id <>", value, "agents3Id");
            return (Criteria) this;
        }

        public Criteria andAgents3IdGreaterThan(Long value) {
            addCriterion("agents3_id >", value, "agents3Id");
            return (Criteria) this;
        }

        public Criteria andAgents3IdGreaterThanOrEqualTo(Long value) {
            addCriterion("agents3_id >=", value, "agents3Id");
            return (Criteria) this;
        }

        public Criteria andAgents3IdLessThan(Long value) {
            addCriterion("agents3_id <", value, "agents3Id");
            return (Criteria) this;
        }

        public Criteria andAgents3IdLessThanOrEqualTo(Long value) {
            addCriterion("agents3_id <=", value, "agents3Id");
            return (Criteria) this;
        }

        public Criteria andAgents3IdIn(List<Long> values) {
            addCriterion("agents3_id in", values, "agents3Id");
            return (Criteria) this;
        }

        public Criteria andAgents3IdNotIn(List<Long> values) {
            addCriterion("agents3_id not in", values, "agents3Id");
            return (Criteria) this;
        }

        public Criteria andAgents3IdBetween(Long value1, Long value2) {
            addCriterion("agents3_id between", value1, value2, "agents3Id");
            return (Criteria) this;
        }

        public Criteria andAgents3IdNotBetween(Long value1, Long value2) {
            addCriterion("agents3_id not between", value1, value2, "agents3Id");
            return (Criteria) this;
        }

        public Criteria andShopkeeperIdIsNull() {
            addCriterion("shopkeeper_id is null");
            return (Criteria) this;
        }

        public Criteria andShopkeeperIdIsNotNull() {
            addCriterion("shopkeeper_id is not null");
            return (Criteria) this;
        }

        public Criteria andShopkeeperIdEqualTo(Long value) {
            addCriterion("shopkeeper_id =", value, "shopkeeperId");
            return (Criteria) this;
        }

        public Criteria andShopkeeperIdNotEqualTo(Long value) {
            addCriterion("shopkeeper_id <>", value, "shopkeeperId");
            return (Criteria) this;
        }

        public Criteria andShopkeeperIdGreaterThan(Long value) {
            addCriterion("shopkeeper_id >", value, "shopkeeperId");
            return (Criteria) this;
        }

        public Criteria andShopkeeperIdGreaterThanOrEqualTo(Long value) {
            addCriterion("shopkeeper_id >=", value, "shopkeeperId");
            return (Criteria) this;
        }

        public Criteria andShopkeeperIdLessThan(Long value) {
            addCriterion("shopkeeper_id <", value, "shopkeeperId");
            return (Criteria) this;
        }

        public Criteria andShopkeeperIdLessThanOrEqualTo(Long value) {
            addCriterion("shopkeeper_id <=", value, "shopkeeperId");
            return (Criteria) this;
        }

        public Criteria andShopkeeperIdIn(List<Long> values) {
            addCriterion("shopkeeper_id in", values, "shopkeeperId");
            return (Criteria) this;
        }

        public Criteria andShopkeeperIdNotIn(List<Long> values) {
            addCriterion("shopkeeper_id not in", values, "shopkeeperId");
            return (Criteria) this;
        }

        public Criteria andShopkeeperIdBetween(Long value1, Long value2) {
            addCriterion("shopkeeper_id between", value1, value2, "shopkeeperId");
            return (Criteria) this;
        }

        public Criteria andShopkeeperIdNotBetween(Long value1, Long value2) {
            addCriterion("shopkeeper_id not between", value1, value2, "shopkeeperId");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessIdIsNull() {
            addCriterion("alliance_business_id is null");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessIdIsNotNull() {
            addCriterion("alliance_business_id is not null");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessIdEqualTo(Long value) {
            addCriterion("alliance_business_id =", value, "allianceBusinessId");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessIdNotEqualTo(Long value) {
            addCriterion("alliance_business_id <>", value, "allianceBusinessId");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessIdGreaterThan(Long value) {
            addCriterion("alliance_business_id >", value, "allianceBusinessId");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessIdGreaterThanOrEqualTo(Long value) {
            addCriterion("alliance_business_id >=", value, "allianceBusinessId");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessIdLessThan(Long value) {
            addCriterion("alliance_business_id <", value, "allianceBusinessId");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessIdLessThanOrEqualTo(Long value) {
            addCriterion("alliance_business_id <=", value, "allianceBusinessId");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessIdIn(List<Long> values) {
            addCriterion("alliance_business_id in", values, "allianceBusinessId");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessIdNotIn(List<Long> values) {
            addCriterion("alliance_business_id not in", values, "allianceBusinessId");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessIdBetween(Long value1, Long value2) {
            addCriterion("alliance_business_id between", value1, value2, "allianceBusinessId");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessIdNotBetween(Long value1, Long value2) {
            addCriterion("alliance_business_id not between", value1, value2, "allianceBusinessId");
            return (Criteria) this;
        }

        public Criteria andCreateIdIsNull() {
            addCriterion("create_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateIdIsNotNull() {
            addCriterion("create_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateIdEqualTo(Long value) {
            addCriterion("create_id =", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotEqualTo(Long value) {
            addCriterion("create_id <>", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdGreaterThan(Long value) {
            addCriterion("create_id >", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("create_id >=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLessThan(Long value) {
            addCriterion("create_id <", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLessThanOrEqualTo(Long value) {
            addCriterion("create_id <=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdIn(List<Long> values) {
            addCriterion("create_id in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotIn(List<Long> values) {
            addCriterion("create_id not in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdBetween(Long value1, Long value2) {
            addCriterion("create_id between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotBetween(Long value1, Long value2) {
            addCriterion("create_id not between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeIsNull() {
            addCriterion("create_datetime is null");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeIsNotNull() {
            addCriterion("create_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeEqualTo(Date value) {
            addCriterion("create_datetime =", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeNotEqualTo(Date value) {
            addCriterion("create_datetime <>", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeGreaterThan(Date value) {
            addCriterion("create_datetime >", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_datetime >=", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeLessThan(Date value) {
            addCriterion("create_datetime <", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("create_datetime <=", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeIn(List<Date> values) {
            addCriterion("create_datetime in", values, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeNotIn(List<Date> values) {
            addCriterion("create_datetime not in", values, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeBetween(Date value1, Date value2) {
            addCriterion("create_datetime between", value1, value2, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("create_datetime not between", value1, value2, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeIsNull() {
            addCriterion("update_datetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeIsNotNull() {
            addCriterion("update_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeEqualTo(Date value) {
            addCriterion("update_datetime =", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeNotEqualTo(Date value) {
            addCriterion("update_datetime <>", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeGreaterThan(Date value) {
            addCriterion("update_datetime >", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_datetime >=", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeLessThan(Date value) {
            addCriterion("update_datetime <", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("update_datetime <=", value, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeIn(List<Date> values) {
            addCriterion("update_datetime in", values, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeNotIn(List<Date> values) {
            addCriterion("update_datetime not in", values, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeBetween(Date value1, Date value2) {
            addCriterion("update_datetime between", value1, value2, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("update_datetime not between", value1, value2, "updateDatetime");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsStatusIsNull() {
            addCriterion("settle_accounts_status is null");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsStatusIsNotNull() {
            addCriterion("settle_accounts_status is not null");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsStatusEqualTo(Integer value) {
            addCriterion("settle_accounts_status =", value, "settleAccountsStatus");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsStatusNotEqualTo(Integer value) {
            addCriterion("settle_accounts_status <>", value, "settleAccountsStatus");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsStatusGreaterThan(Integer value) {
            addCriterion("settle_accounts_status >", value, "settleAccountsStatus");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("settle_accounts_status >=", value, "settleAccountsStatus");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsStatusLessThan(Integer value) {
            addCriterion("settle_accounts_status <", value, "settleAccountsStatus");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsStatusLessThanOrEqualTo(Integer value) {
            addCriterion("settle_accounts_status <=", value, "settleAccountsStatus");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsStatusIn(List<Integer> values) {
            addCriterion("settle_accounts_status in", values, "settleAccountsStatus");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsStatusNotIn(List<Integer> values) {
            addCriterion("settle_accounts_status not in", values, "settleAccountsStatus");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsStatusBetween(Integer value1, Integer value2) {
            addCriterion("settle_accounts_status between", value1, value2, "settleAccountsStatus");
            return (Criteria) this;
        }

        public Criteria andSettleAccountsStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("settle_accounts_status not between", value1, value2, "settleAccountsStatus");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andTradeChannelIsNull() {
            addCriterion("trade_channel is null");
            return (Criteria) this;
        }

        public Criteria andTradeChannelIsNotNull() {
            addCriterion("trade_channel is not null");
            return (Criteria) this;
        }

        public Criteria andTradeChannelEqualTo(Integer value) {
            addCriterion("trade_channel =", value, "tradeChannel");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNotEqualTo(Integer value) {
            addCriterion("trade_channel <>", value, "tradeChannel");
            return (Criteria) this;
        }

        public Criteria andTradeChannelGreaterThan(Integer value) {
            addCriterion("trade_channel >", value, "tradeChannel");
            return (Criteria) this;
        }

        public Criteria andTradeChannelGreaterThanOrEqualTo(Integer value) {
            addCriterion("trade_channel >=", value, "tradeChannel");
            return (Criteria) this;
        }

        public Criteria andTradeChannelLessThan(Integer value) {
            addCriterion("trade_channel <", value, "tradeChannel");
            return (Criteria) this;
        }

        public Criteria andTradeChannelLessThanOrEqualTo(Integer value) {
            addCriterion("trade_channel <=", value, "tradeChannel");
            return (Criteria) this;
        }

        public Criteria andTradeChannelIn(List<Integer> values) {
            addCriterion("trade_channel in", values, "tradeChannel");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNotIn(List<Integer> values) {
            addCriterion("trade_channel not in", values, "tradeChannel");
            return (Criteria) this;
        }

        public Criteria andTradeChannelBetween(Integer value1, Integer value2) {
            addCriterion("trade_channel between", value1, value2, "tradeChannel");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNotBetween(Integer value1, Integer value2) {
            addCriterion("trade_channel not between", value1, value2, "tradeChannel");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNameIsNull() {
            addCriterion("trade_channel_name is null");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNameIsNotNull() {
            addCriterion("trade_channel_name is not null");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNameEqualTo(String value) {
            addCriterion("trade_channel_name =", value, "tradeChannelName");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNameNotEqualTo(String value) {
            addCriterion("trade_channel_name <>", value, "tradeChannelName");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNameGreaterThan(String value) {
            addCriterion("trade_channel_name >", value, "tradeChannelName");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNameGreaterThanOrEqualTo(String value) {
            addCriterion("trade_channel_name >=", value, "tradeChannelName");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNameLessThan(String value) {
            addCriterion("trade_channel_name <", value, "tradeChannelName");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNameLessThanOrEqualTo(String value) {
            addCriterion("trade_channel_name <=", value, "tradeChannelName");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNameLike(String value) {
            addCriterion("trade_channel_name like", value, "tradeChannelName");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNameNotLike(String value) {
            addCriterion("trade_channel_name not like", value, "tradeChannelName");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNameIn(List<String> values) {
            addCriterion("trade_channel_name in", values, "tradeChannelName");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNameNotIn(List<String> values) {
            addCriterion("trade_channel_name not in", values, "tradeChannelName");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNameBetween(String value1, String value2) {
            addCriterion("trade_channel_name between", value1, value2, "tradeChannelName");
            return (Criteria) this;
        }

        public Criteria andTradeChannelNameNotBetween(String value1, String value2) {
            addCriterion("trade_channel_name not between", value1, value2, "tradeChannelName");
            return (Criteria) this;
        }

        public Criteria andFinishFlagIsNull() {
            addCriterion("finish_flag is null");
            return (Criteria) this;
        }

        public Criteria andFinishFlagIsNotNull() {
            addCriterion("finish_flag is not null");
            return (Criteria) this;
        }

        public Criteria andFinishFlagEqualTo(Integer value) {
            addCriterion("finish_flag =", value, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNotEqualTo(Integer value) {
            addCriterion("finish_flag <>", value, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagGreaterThan(Integer value) {
            addCriterion("finish_flag >", value, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("finish_flag >=", value, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagLessThan(Integer value) {
            addCriterion("finish_flag <", value, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagLessThanOrEqualTo(Integer value) {
            addCriterion("finish_flag <=", value, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagIn(List<Integer> values) {
            addCriterion("finish_flag in", values, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNotIn(List<Integer> values) {
            addCriterion("finish_flag not in", values, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagBetween(Integer value1, Integer value2) {
            addCriterion("finish_flag between", value1, value2, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("finish_flag not between", value1, value2, "finishFlag");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNameIsNull() {
            addCriterion("finish_flag_name is null");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNameIsNotNull() {
            addCriterion("finish_flag_name is not null");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNameEqualTo(String value) {
            addCriterion("finish_flag_name =", value, "finishFlagName");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNameNotEqualTo(String value) {
            addCriterion("finish_flag_name <>", value, "finishFlagName");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNameGreaterThan(String value) {
            addCriterion("finish_flag_name >", value, "finishFlagName");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNameGreaterThanOrEqualTo(String value) {
            addCriterion("finish_flag_name >=", value, "finishFlagName");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNameLessThan(String value) {
            addCriterion("finish_flag_name <", value, "finishFlagName");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNameLessThanOrEqualTo(String value) {
            addCriterion("finish_flag_name <=", value, "finishFlagName");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNameLike(String value) {
            addCriterion("finish_flag_name like", value, "finishFlagName");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNameNotLike(String value) {
            addCriterion("finish_flag_name not like", value, "finishFlagName");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNameIn(List<String> values) {
            addCriterion("finish_flag_name in", values, "finishFlagName");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNameNotIn(List<String> values) {
            addCriterion("finish_flag_name not in", values, "finishFlagName");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNameBetween(String value1, String value2) {
            addCriterion("finish_flag_name between", value1, value2, "finishFlagName");
            return (Criteria) this;
        }

        public Criteria andFinishFlagNameNotBetween(String value1, String value2) {
            addCriterion("finish_flag_name not between", value1, value2, "finishFlagName");
            return (Criteria) this;
        }

        public Criteria andPlatFormRefundIsNull() {
            addCriterion("plat_form_refund is null");
            return (Criteria) this;
        }

        public Criteria andPlatFormRefundIsNotNull() {
            addCriterion("plat_form_refund is not null");
            return (Criteria) this;
        }

        public Criteria andPlatFormRefundEqualTo(Integer value) {
            addCriterion("plat_form_refund =", value, "platFormRefund");
            return (Criteria) this;
        }

        public Criteria andPlatFormRefundNotEqualTo(Integer value) {
            addCriterion("plat_form_refund <>", value, "platFormRefund");
            return (Criteria) this;
        }

        public Criteria andPlatFormRefundGreaterThan(Integer value) {
            addCriterion("plat_form_refund >", value, "platFormRefund");
            return (Criteria) this;
        }

        public Criteria andPlatFormRefundGreaterThanOrEqualTo(Integer value) {
            addCriterion("plat_form_refund >=", value, "platFormRefund");
            return (Criteria) this;
        }

        public Criteria andPlatFormRefundLessThan(Integer value) {
            addCriterion("plat_form_refund <", value, "platFormRefund");
            return (Criteria) this;
        }

        public Criteria andPlatFormRefundLessThanOrEqualTo(Integer value) {
            addCriterion("plat_form_refund <=", value, "platFormRefund");
            return (Criteria) this;
        }

        public Criteria andPlatFormRefundIn(List<Integer> values) {
            addCriterion("plat_form_refund in", values, "platFormRefund");
            return (Criteria) this;
        }

        public Criteria andPlatFormRefundNotIn(List<Integer> values) {
            addCriterion("plat_form_refund not in", values, "platFormRefund");
            return (Criteria) this;
        }

        public Criteria andPlatFormRefundBetween(Integer value1, Integer value2) {
            addCriterion("plat_form_refund between", value1, value2, "platFormRefund");
            return (Criteria) this;
        }

        public Criteria andPlatFormRefundNotBetween(Integer value1, Integer value2) {
            addCriterion("plat_form_refund not between", value1, value2, "platFormRefund");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIsNull() {
            addCriterion("refund_amount is null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIsNotNull() {
            addCriterion("refund_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountEqualTo(BigDecimal value) {
            addCriterion("refund_amount =", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotEqualTo(BigDecimal value) {
            addCriterion("refund_amount <>", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountGreaterThan(BigDecimal value) {
            addCriterion("refund_amount >", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount >=", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLessThan(BigDecimal value) {
            addCriterion("refund_amount <", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount <=", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIn(List<BigDecimal> values) {
            addCriterion("refund_amount in", values, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotIn(List<BigDecimal> values) {
            addCriterion("refund_amount not in", values, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount between", value1, value2, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount not between", value1, value2, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andCalTradeAmountIsNull() {
            addCriterion("cal_trade_amount is null");
            return (Criteria) this;
        }

        public Criteria andCalTradeAmountIsNotNull() {
            addCriterion("cal_trade_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCalTradeAmountEqualTo(BigDecimal value) {
            addCriterion("cal_trade_amount =", value, "calTradeAmount");
            return (Criteria) this;
        }

        public Criteria andCalTradeAmountNotEqualTo(BigDecimal value) {
            addCriterion("cal_trade_amount <>", value, "calTradeAmount");
            return (Criteria) this;
        }

        public Criteria andCalTradeAmountGreaterThan(BigDecimal value) {
            addCriterion("cal_trade_amount >", value, "calTradeAmount");
            return (Criteria) this;
        }

        public Criteria andCalTradeAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cal_trade_amount >=", value, "calTradeAmount");
            return (Criteria) this;
        }

        public Criteria andCalTradeAmountLessThan(BigDecimal value) {
            addCriterion("cal_trade_amount <", value, "calTradeAmount");
            return (Criteria) this;
        }

        public Criteria andCalTradeAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cal_trade_amount <=", value, "calTradeAmount");
            return (Criteria) this;
        }

        public Criteria andCalTradeAmountIn(List<BigDecimal> values) {
            addCriterion("cal_trade_amount in", values, "calTradeAmount");
            return (Criteria) this;
        }

        public Criteria andCalTradeAmountNotIn(List<BigDecimal> values) {
            addCriterion("cal_trade_amount not in", values, "calTradeAmount");
            return (Criteria) this;
        }

        public Criteria andCalTradeAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cal_trade_amount between", value1, value2, "calTradeAmount");
            return (Criteria) this;
        }

        public Criteria andCalTradeAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cal_trade_amount not between", value1, value2, "calTradeAmount");
            return (Criteria) this;
        }

        public Criteria andResourceTradeIdIsNull() {
            addCriterion("resource_trade_id is null");
            return (Criteria) this;
        }

        public Criteria andResourceTradeIdIsNotNull() {
            addCriterion("resource_trade_id is not null");
            return (Criteria) this;
        }

        public Criteria andResourceTradeIdEqualTo(Long value) {
            addCriterion("resource_trade_id =", value, "resourceTradeId");
            return (Criteria) this;
        }

        public Criteria andResourceTradeIdNotEqualTo(Long value) {
            addCriterion("resource_trade_id <>", value, "resourceTradeId");
            return (Criteria) this;
        }

        public Criteria andResourceTradeIdGreaterThan(Long value) {
            addCriterion("resource_trade_id >", value, "resourceTradeId");
            return (Criteria) this;
        }

        public Criteria andResourceTradeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("resource_trade_id >=", value, "resourceTradeId");
            return (Criteria) this;
        }

        public Criteria andResourceTradeIdLessThan(Long value) {
            addCriterion("resource_trade_id <", value, "resourceTradeId");
            return (Criteria) this;
        }

        public Criteria andResourceTradeIdLessThanOrEqualTo(Long value) {
            addCriterion("resource_trade_id <=", value, "resourceTradeId");
            return (Criteria) this;
        }

        public Criteria andResourceTradeIdIn(List<Long> values) {
            addCriterion("resource_trade_id in", values, "resourceTradeId");
            return (Criteria) this;
        }

        public Criteria andResourceTradeIdNotIn(List<Long> values) {
            addCriterion("resource_trade_id not in", values, "resourceTradeId");
            return (Criteria) this;
        }

        public Criteria andResourceTradeIdBetween(Long value1, Long value2) {
            addCriterion("resource_trade_id between", value1, value2, "resourceTradeId");
            return (Criteria) this;
        }

        public Criteria andResourceTradeIdNotBetween(Long value1, Long value2) {
            addCriterion("resource_trade_id not between", value1, value2, "resourceTradeId");
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
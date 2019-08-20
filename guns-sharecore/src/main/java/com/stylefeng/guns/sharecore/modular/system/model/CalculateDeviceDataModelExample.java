package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalculateDeviceDataModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CalculateDeviceDataModelExample() {
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

        public Criteria andDeviceIdIsNull() {
            addCriterion("device_id is null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIsNotNull() {
            addCriterion("device_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdEqualTo(Long value) {
            addCriterion("device_id =", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotEqualTo(Long value) {
            addCriterion("device_id <>", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThan(Long value) {
            addCriterion("device_id >", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("device_id >=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThan(Long value) {
            addCriterion("device_id <", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThanOrEqualTo(Long value) {
            addCriterion("device_id <=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIn(List<Long> values) {
            addCriterion("device_id in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotIn(List<Long> values) {
            addCriterion("device_id not in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdBetween(Long value1, Long value2) {
            addCriterion("device_id between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotBetween(Long value1, Long value2) {
            addCriterion("device_id not between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andHistoryOrdersIsNull() {
            addCriterion("history_orders is null");
            return (Criteria) this;
        }

        public Criteria andHistoryOrdersIsNotNull() {
            addCriterion("history_orders is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryOrdersEqualTo(Long value) {
            addCriterion("history_orders =", value, "historyOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryOrdersNotEqualTo(Long value) {
            addCriterion("history_orders <>", value, "historyOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryOrdersGreaterThan(Long value) {
            addCriterion("history_orders >", value, "historyOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryOrdersGreaterThanOrEqualTo(Long value) {
            addCriterion("history_orders >=", value, "historyOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryOrdersLessThan(Long value) {
            addCriterion("history_orders <", value, "historyOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryOrdersLessThanOrEqualTo(Long value) {
            addCriterion("history_orders <=", value, "historyOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryOrdersIn(List<Long> values) {
            addCriterion("history_orders in", values, "historyOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryOrdersNotIn(List<Long> values) {
            addCriterion("history_orders not in", values, "historyOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryOrdersBetween(Long value1, Long value2) {
            addCriterion("history_orders between", value1, value2, "historyOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryOrdersNotBetween(Long value1, Long value2) {
            addCriterion("history_orders not between", value1, value2, "historyOrders");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders1IsNull() {
            addCriterion("before_orders_1 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders1IsNotNull() {
            addCriterion("before_orders_1 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders1EqualTo(Long value) {
            addCriterion("before_orders_1 =", value, "beforeOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders1NotEqualTo(Long value) {
            addCriterion("before_orders_1 <>", value, "beforeOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders1GreaterThan(Long value) {
            addCriterion("before_orders_1 >", value, "beforeOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders1GreaterThanOrEqualTo(Long value) {
            addCriterion("before_orders_1 >=", value, "beforeOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders1LessThan(Long value) {
            addCriterion("before_orders_1 <", value, "beforeOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders1LessThanOrEqualTo(Long value) {
            addCriterion("before_orders_1 <=", value, "beforeOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders1In(List<Long> values) {
            addCriterion("before_orders_1 in", values, "beforeOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders1NotIn(List<Long> values) {
            addCriterion("before_orders_1 not in", values, "beforeOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders1Between(Long value1, Long value2) {
            addCriterion("before_orders_1 between", value1, value2, "beforeOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders1NotBetween(Long value1, Long value2) {
            addCriterion("before_orders_1 not between", value1, value2, "beforeOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders2IsNull() {
            addCriterion("before_orders_2 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders2IsNotNull() {
            addCriterion("before_orders_2 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders2EqualTo(Long value) {
            addCriterion("before_orders_2 =", value, "beforeOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders2NotEqualTo(Long value) {
            addCriterion("before_orders_2 <>", value, "beforeOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders2GreaterThan(Long value) {
            addCriterion("before_orders_2 >", value, "beforeOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders2GreaterThanOrEqualTo(Long value) {
            addCriterion("before_orders_2 >=", value, "beforeOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders2LessThan(Long value) {
            addCriterion("before_orders_2 <", value, "beforeOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders2LessThanOrEqualTo(Long value) {
            addCriterion("before_orders_2 <=", value, "beforeOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders2In(List<Long> values) {
            addCriterion("before_orders_2 in", values, "beforeOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders2NotIn(List<Long> values) {
            addCriterion("before_orders_2 not in", values, "beforeOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders2Between(Long value1, Long value2) {
            addCriterion("before_orders_2 between", value1, value2, "beforeOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders2NotBetween(Long value1, Long value2) {
            addCriterion("before_orders_2 not between", value1, value2, "beforeOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders3IsNull() {
            addCriterion("before_orders_3 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders3IsNotNull() {
            addCriterion("before_orders_3 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders3EqualTo(Long value) {
            addCriterion("before_orders_3 =", value, "beforeOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders3NotEqualTo(Long value) {
            addCriterion("before_orders_3 <>", value, "beforeOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders3GreaterThan(Long value) {
            addCriterion("before_orders_3 >", value, "beforeOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders3GreaterThanOrEqualTo(Long value) {
            addCriterion("before_orders_3 >=", value, "beforeOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders3LessThan(Long value) {
            addCriterion("before_orders_3 <", value, "beforeOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders3LessThanOrEqualTo(Long value) {
            addCriterion("before_orders_3 <=", value, "beforeOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders3In(List<Long> values) {
            addCriterion("before_orders_3 in", values, "beforeOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders3NotIn(List<Long> values) {
            addCriterion("before_orders_3 not in", values, "beforeOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders3Between(Long value1, Long value2) {
            addCriterion("before_orders_3 between", value1, value2, "beforeOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders3NotBetween(Long value1, Long value2) {
            addCriterion("before_orders_3 not between", value1, value2, "beforeOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders4IsNull() {
            addCriterion("before_orders_4 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders4IsNotNull() {
            addCriterion("before_orders_4 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders4EqualTo(Long value) {
            addCriterion("before_orders_4 =", value, "beforeOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders4NotEqualTo(Long value) {
            addCriterion("before_orders_4 <>", value, "beforeOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders4GreaterThan(Long value) {
            addCriterion("before_orders_4 >", value, "beforeOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders4GreaterThanOrEqualTo(Long value) {
            addCriterion("before_orders_4 >=", value, "beforeOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders4LessThan(Long value) {
            addCriterion("before_orders_4 <", value, "beforeOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders4LessThanOrEqualTo(Long value) {
            addCriterion("before_orders_4 <=", value, "beforeOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders4In(List<Long> values) {
            addCriterion("before_orders_4 in", values, "beforeOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders4NotIn(List<Long> values) {
            addCriterion("before_orders_4 not in", values, "beforeOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders4Between(Long value1, Long value2) {
            addCriterion("before_orders_4 between", value1, value2, "beforeOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders4NotBetween(Long value1, Long value2) {
            addCriterion("before_orders_4 not between", value1, value2, "beforeOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders5IsNull() {
            addCriterion("before_orders_5 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders5IsNotNull() {
            addCriterion("before_orders_5 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders5EqualTo(Long value) {
            addCriterion("before_orders_5 =", value, "beforeOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders5NotEqualTo(Long value) {
            addCriterion("before_orders_5 <>", value, "beforeOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders5GreaterThan(Long value) {
            addCriterion("before_orders_5 >", value, "beforeOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders5GreaterThanOrEqualTo(Long value) {
            addCriterion("before_orders_5 >=", value, "beforeOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders5LessThan(Long value) {
            addCriterion("before_orders_5 <", value, "beforeOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders5LessThanOrEqualTo(Long value) {
            addCriterion("before_orders_5 <=", value, "beforeOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders5In(List<Long> values) {
            addCriterion("before_orders_5 in", values, "beforeOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders5NotIn(List<Long> values) {
            addCriterion("before_orders_5 not in", values, "beforeOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders5Between(Long value1, Long value2) {
            addCriterion("before_orders_5 between", value1, value2, "beforeOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders5NotBetween(Long value1, Long value2) {
            addCriterion("before_orders_5 not between", value1, value2, "beforeOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders6IsNull() {
            addCriterion("before_orders_6 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders6IsNotNull() {
            addCriterion("before_orders_6 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders6EqualTo(Long value) {
            addCriterion("before_orders_6 =", value, "beforeOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders6NotEqualTo(Long value) {
            addCriterion("before_orders_6 <>", value, "beforeOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders6GreaterThan(Long value) {
            addCriterion("before_orders_6 >", value, "beforeOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders6GreaterThanOrEqualTo(Long value) {
            addCriterion("before_orders_6 >=", value, "beforeOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders6LessThan(Long value) {
            addCriterion("before_orders_6 <", value, "beforeOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders6LessThanOrEqualTo(Long value) {
            addCriterion("before_orders_6 <=", value, "beforeOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders6In(List<Long> values) {
            addCriterion("before_orders_6 in", values, "beforeOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders6NotIn(List<Long> values) {
            addCriterion("before_orders_6 not in", values, "beforeOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders6Between(Long value1, Long value2) {
            addCriterion("before_orders_6 between", value1, value2, "beforeOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders6NotBetween(Long value1, Long value2) {
            addCriterion("before_orders_6 not between", value1, value2, "beforeOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders7IsNull() {
            addCriterion("before_orders_7 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders7IsNotNull() {
            addCriterion("before_orders_7 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders7EqualTo(Long value) {
            addCriterion("before_orders_7 =", value, "beforeOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders7NotEqualTo(Long value) {
            addCriterion("before_orders_7 <>", value, "beforeOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders7GreaterThan(Long value) {
            addCriterion("before_orders_7 >", value, "beforeOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders7GreaterThanOrEqualTo(Long value) {
            addCriterion("before_orders_7 >=", value, "beforeOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders7LessThan(Long value) {
            addCriterion("before_orders_7 <", value, "beforeOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders7LessThanOrEqualTo(Long value) {
            addCriterion("before_orders_7 <=", value, "beforeOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders7In(List<Long> values) {
            addCriterion("before_orders_7 in", values, "beforeOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders7NotIn(List<Long> values) {
            addCriterion("before_orders_7 not in", values, "beforeOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders7Between(Long value1, Long value2) {
            addCriterion("before_orders_7 between", value1, value2, "beforeOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeOrders7NotBetween(Long value1, Long value2) {
            addCriterion("before_orders_7 not between", value1, value2, "beforeOrders7");
            return (Criteria) this;
        }

        public Criteria andHistoryFinishOrdersIsNull() {
            addCriterion("history_finish_orders is null");
            return (Criteria) this;
        }

        public Criteria andHistoryFinishOrdersIsNotNull() {
            addCriterion("history_finish_orders is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryFinishOrdersEqualTo(Long value) {
            addCriterion("history_finish_orders =", value, "historyFinishOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryFinishOrdersNotEqualTo(Long value) {
            addCriterion("history_finish_orders <>", value, "historyFinishOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryFinishOrdersGreaterThan(Long value) {
            addCriterion("history_finish_orders >", value, "historyFinishOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryFinishOrdersGreaterThanOrEqualTo(Long value) {
            addCriterion("history_finish_orders >=", value, "historyFinishOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryFinishOrdersLessThan(Long value) {
            addCriterion("history_finish_orders <", value, "historyFinishOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryFinishOrdersLessThanOrEqualTo(Long value) {
            addCriterion("history_finish_orders <=", value, "historyFinishOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryFinishOrdersIn(List<Long> values) {
            addCriterion("history_finish_orders in", values, "historyFinishOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryFinishOrdersNotIn(List<Long> values) {
            addCriterion("history_finish_orders not in", values, "historyFinishOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryFinishOrdersBetween(Long value1, Long value2) {
            addCriterion("history_finish_orders between", value1, value2, "historyFinishOrders");
            return (Criteria) this;
        }

        public Criteria andHistoryFinishOrdersNotBetween(Long value1, Long value2) {
            addCriterion("history_finish_orders not between", value1, value2, "historyFinishOrders");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders1IsNull() {
            addCriterion("before_finish_orders_1 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders1IsNotNull() {
            addCriterion("before_finish_orders_1 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders1EqualTo(Long value) {
            addCriterion("before_finish_orders_1 =", value, "beforeFinishOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders1NotEqualTo(Long value) {
            addCriterion("before_finish_orders_1 <>", value, "beforeFinishOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders1GreaterThan(Long value) {
            addCriterion("before_finish_orders_1 >", value, "beforeFinishOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders1GreaterThanOrEqualTo(Long value) {
            addCriterion("before_finish_orders_1 >=", value, "beforeFinishOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders1LessThan(Long value) {
            addCriterion("before_finish_orders_1 <", value, "beforeFinishOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders1LessThanOrEqualTo(Long value) {
            addCriterion("before_finish_orders_1 <=", value, "beforeFinishOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders1In(List<Long> values) {
            addCriterion("before_finish_orders_1 in", values, "beforeFinishOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders1NotIn(List<Long> values) {
            addCriterion("before_finish_orders_1 not in", values, "beforeFinishOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders1Between(Long value1, Long value2) {
            addCriterion("before_finish_orders_1 between", value1, value2, "beforeFinishOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders1NotBetween(Long value1, Long value2) {
            addCriterion("before_finish_orders_1 not between", value1, value2, "beforeFinishOrders1");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders2IsNull() {
            addCriterion("before_finish_orders_2 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders2IsNotNull() {
            addCriterion("before_finish_orders_2 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders2EqualTo(Long value) {
            addCriterion("before_finish_orders_2 =", value, "beforeFinishOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders2NotEqualTo(Long value) {
            addCriterion("before_finish_orders_2 <>", value, "beforeFinishOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders2GreaterThan(Long value) {
            addCriterion("before_finish_orders_2 >", value, "beforeFinishOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders2GreaterThanOrEqualTo(Long value) {
            addCriterion("before_finish_orders_2 >=", value, "beforeFinishOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders2LessThan(Long value) {
            addCriterion("before_finish_orders_2 <", value, "beforeFinishOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders2LessThanOrEqualTo(Long value) {
            addCriterion("before_finish_orders_2 <=", value, "beforeFinishOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders2In(List<Long> values) {
            addCriterion("before_finish_orders_2 in", values, "beforeFinishOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders2NotIn(List<Long> values) {
            addCriterion("before_finish_orders_2 not in", values, "beforeFinishOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders2Between(Long value1, Long value2) {
            addCriterion("before_finish_orders_2 between", value1, value2, "beforeFinishOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders2NotBetween(Long value1, Long value2) {
            addCriterion("before_finish_orders_2 not between", value1, value2, "beforeFinishOrders2");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders3IsNull() {
            addCriterion("before_finish_orders_3 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders3IsNotNull() {
            addCriterion("before_finish_orders_3 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders3EqualTo(Long value) {
            addCriterion("before_finish_orders_3 =", value, "beforeFinishOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders3NotEqualTo(Long value) {
            addCriterion("before_finish_orders_3 <>", value, "beforeFinishOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders3GreaterThan(Long value) {
            addCriterion("before_finish_orders_3 >", value, "beforeFinishOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders3GreaterThanOrEqualTo(Long value) {
            addCriterion("before_finish_orders_3 >=", value, "beforeFinishOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders3LessThan(Long value) {
            addCriterion("before_finish_orders_3 <", value, "beforeFinishOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders3LessThanOrEqualTo(Long value) {
            addCriterion("before_finish_orders_3 <=", value, "beforeFinishOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders3In(List<Long> values) {
            addCriterion("before_finish_orders_3 in", values, "beforeFinishOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders3NotIn(List<Long> values) {
            addCriterion("before_finish_orders_3 not in", values, "beforeFinishOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders3Between(Long value1, Long value2) {
            addCriterion("before_finish_orders_3 between", value1, value2, "beforeFinishOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders3NotBetween(Long value1, Long value2) {
            addCriterion("before_finish_orders_3 not between", value1, value2, "beforeFinishOrders3");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders4IsNull() {
            addCriterion("before_finish_orders_4 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders4IsNotNull() {
            addCriterion("before_finish_orders_4 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders4EqualTo(Long value) {
            addCriterion("before_finish_orders_4 =", value, "beforeFinishOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders4NotEqualTo(Long value) {
            addCriterion("before_finish_orders_4 <>", value, "beforeFinishOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders4GreaterThan(Long value) {
            addCriterion("before_finish_orders_4 >", value, "beforeFinishOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders4GreaterThanOrEqualTo(Long value) {
            addCriterion("before_finish_orders_4 >=", value, "beforeFinishOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders4LessThan(Long value) {
            addCriterion("before_finish_orders_4 <", value, "beforeFinishOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders4LessThanOrEqualTo(Long value) {
            addCriterion("before_finish_orders_4 <=", value, "beforeFinishOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders4In(List<Long> values) {
            addCriterion("before_finish_orders_4 in", values, "beforeFinishOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders4NotIn(List<Long> values) {
            addCriterion("before_finish_orders_4 not in", values, "beforeFinishOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders4Between(Long value1, Long value2) {
            addCriterion("before_finish_orders_4 between", value1, value2, "beforeFinishOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders4NotBetween(Long value1, Long value2) {
            addCriterion("before_finish_orders_4 not between", value1, value2, "beforeFinishOrders4");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders5IsNull() {
            addCriterion("before_finish_orders_5 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders5IsNotNull() {
            addCriterion("before_finish_orders_5 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders5EqualTo(Long value) {
            addCriterion("before_finish_orders_5 =", value, "beforeFinishOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders5NotEqualTo(Long value) {
            addCriterion("before_finish_orders_5 <>", value, "beforeFinishOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders5GreaterThan(Long value) {
            addCriterion("before_finish_orders_5 >", value, "beforeFinishOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders5GreaterThanOrEqualTo(Long value) {
            addCriterion("before_finish_orders_5 >=", value, "beforeFinishOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders5LessThan(Long value) {
            addCriterion("before_finish_orders_5 <", value, "beforeFinishOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders5LessThanOrEqualTo(Long value) {
            addCriterion("before_finish_orders_5 <=", value, "beforeFinishOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders5In(List<Long> values) {
            addCriterion("before_finish_orders_5 in", values, "beforeFinishOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders5NotIn(List<Long> values) {
            addCriterion("before_finish_orders_5 not in", values, "beforeFinishOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders5Between(Long value1, Long value2) {
            addCriterion("before_finish_orders_5 between", value1, value2, "beforeFinishOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders5NotBetween(Long value1, Long value2) {
            addCriterion("before_finish_orders_5 not between", value1, value2, "beforeFinishOrders5");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders6IsNull() {
            addCriterion("before_finish_orders_6 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders6IsNotNull() {
            addCriterion("before_finish_orders_6 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders6EqualTo(Long value) {
            addCriterion("before_finish_orders_6 =", value, "beforeFinishOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders6NotEqualTo(Long value) {
            addCriterion("before_finish_orders_6 <>", value, "beforeFinishOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders6GreaterThan(Long value) {
            addCriterion("before_finish_orders_6 >", value, "beforeFinishOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders6GreaterThanOrEqualTo(Long value) {
            addCriterion("before_finish_orders_6 >=", value, "beforeFinishOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders6LessThan(Long value) {
            addCriterion("before_finish_orders_6 <", value, "beforeFinishOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders6LessThanOrEqualTo(Long value) {
            addCriterion("before_finish_orders_6 <=", value, "beforeFinishOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders6In(List<Long> values) {
            addCriterion("before_finish_orders_6 in", values, "beforeFinishOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders6NotIn(List<Long> values) {
            addCriterion("before_finish_orders_6 not in", values, "beforeFinishOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders6Between(Long value1, Long value2) {
            addCriterion("before_finish_orders_6 between", value1, value2, "beforeFinishOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders6NotBetween(Long value1, Long value2) {
            addCriterion("before_finish_orders_6 not between", value1, value2, "beforeFinishOrders6");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders7IsNull() {
            addCriterion("before_finish_orders_7 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders7IsNotNull() {
            addCriterion("before_finish_orders_7 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders7EqualTo(Long value) {
            addCriterion("before_finish_orders_7 =", value, "beforeFinishOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders7NotEqualTo(Long value) {
            addCriterion("before_finish_orders_7 <>", value, "beforeFinishOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders7GreaterThan(Long value) {
            addCriterion("before_finish_orders_7 >", value, "beforeFinishOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders7GreaterThanOrEqualTo(Long value) {
            addCriterion("before_finish_orders_7 >=", value, "beforeFinishOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders7LessThan(Long value) {
            addCriterion("before_finish_orders_7 <", value, "beforeFinishOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders7LessThanOrEqualTo(Long value) {
            addCriterion("before_finish_orders_7 <=", value, "beforeFinishOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders7In(List<Long> values) {
            addCriterion("before_finish_orders_7 in", values, "beforeFinishOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders7NotIn(List<Long> values) {
            addCriterion("before_finish_orders_7 not in", values, "beforeFinishOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders7Between(Long value1, Long value2) {
            addCriterion("before_finish_orders_7 between", value1, value2, "beforeFinishOrders7");
            return (Criteria) this;
        }

        public Criteria andBeforeFinishOrders7NotBetween(Long value1, Long value2) {
            addCriterion("before_finish_orders_7 not between", value1, value2, "beforeFinishOrders7");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNull() {
            addCriterion("total_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNotNull() {
            addCriterion("total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountEqualTo(BigDecimal value) {
            addCriterion("total_amount =", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotEqualTo(BigDecimal value) {
            addCriterion("total_amount <>", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThan(BigDecimal value) {
            addCriterion("total_amount >", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amount >=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThan(BigDecimal value) {
            addCriterion("total_amount <", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amount <=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIn(List<BigDecimal> values) {
            addCriterion("total_amount in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotIn(List<BigDecimal> values) {
            addCriterion("total_amount not in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amount between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amount not between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount1IsNull() {
            addCriterion("before_amount_1 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount1IsNotNull() {
            addCriterion("before_amount_1 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount1EqualTo(BigDecimal value) {
            addCriterion("before_amount_1 =", value, "beforeAmount1");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount1NotEqualTo(BigDecimal value) {
            addCriterion("before_amount_1 <>", value, "beforeAmount1");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount1GreaterThan(BigDecimal value) {
            addCriterion("before_amount_1 >", value, "beforeAmount1");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount1GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_amount_1 >=", value, "beforeAmount1");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount1LessThan(BigDecimal value) {
            addCriterion("before_amount_1 <", value, "beforeAmount1");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount1LessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_amount_1 <=", value, "beforeAmount1");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount1In(List<BigDecimal> values) {
            addCriterion("before_amount_1 in", values, "beforeAmount1");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount1NotIn(List<BigDecimal> values) {
            addCriterion("before_amount_1 not in", values, "beforeAmount1");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount1Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_amount_1 between", value1, value2, "beforeAmount1");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount1NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_amount_1 not between", value1, value2, "beforeAmount1");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount2IsNull() {
            addCriterion("before_amount_2 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount2IsNotNull() {
            addCriterion("before_amount_2 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount2EqualTo(BigDecimal value) {
            addCriterion("before_amount_2 =", value, "beforeAmount2");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount2NotEqualTo(BigDecimal value) {
            addCriterion("before_amount_2 <>", value, "beforeAmount2");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount2GreaterThan(BigDecimal value) {
            addCriterion("before_amount_2 >", value, "beforeAmount2");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount2GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_amount_2 >=", value, "beforeAmount2");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount2LessThan(BigDecimal value) {
            addCriterion("before_amount_2 <", value, "beforeAmount2");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount2LessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_amount_2 <=", value, "beforeAmount2");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount2In(List<BigDecimal> values) {
            addCriterion("before_amount_2 in", values, "beforeAmount2");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount2NotIn(List<BigDecimal> values) {
            addCriterion("before_amount_2 not in", values, "beforeAmount2");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount2Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_amount_2 between", value1, value2, "beforeAmount2");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount2NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_amount_2 not between", value1, value2, "beforeAmount2");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount3IsNull() {
            addCriterion("before_amount_3 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount3IsNotNull() {
            addCriterion("before_amount_3 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount3EqualTo(BigDecimal value) {
            addCriterion("before_amount_3 =", value, "beforeAmount3");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount3NotEqualTo(BigDecimal value) {
            addCriterion("before_amount_3 <>", value, "beforeAmount3");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount3GreaterThan(BigDecimal value) {
            addCriterion("before_amount_3 >", value, "beforeAmount3");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount3GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_amount_3 >=", value, "beforeAmount3");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount3LessThan(BigDecimal value) {
            addCriterion("before_amount_3 <", value, "beforeAmount3");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount3LessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_amount_3 <=", value, "beforeAmount3");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount3In(List<BigDecimal> values) {
            addCriterion("before_amount_3 in", values, "beforeAmount3");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount3NotIn(List<BigDecimal> values) {
            addCriterion("before_amount_3 not in", values, "beforeAmount3");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount3Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_amount_3 between", value1, value2, "beforeAmount3");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount3NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_amount_3 not between", value1, value2, "beforeAmount3");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount4IsNull() {
            addCriterion("before_amount_4 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount4IsNotNull() {
            addCriterion("before_amount_4 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount4EqualTo(BigDecimal value) {
            addCriterion("before_amount_4 =", value, "beforeAmount4");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount4NotEqualTo(BigDecimal value) {
            addCriterion("before_amount_4 <>", value, "beforeAmount4");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount4GreaterThan(BigDecimal value) {
            addCriterion("before_amount_4 >", value, "beforeAmount4");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount4GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_amount_4 >=", value, "beforeAmount4");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount4LessThan(BigDecimal value) {
            addCriterion("before_amount_4 <", value, "beforeAmount4");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount4LessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_amount_4 <=", value, "beforeAmount4");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount4In(List<BigDecimal> values) {
            addCriterion("before_amount_4 in", values, "beforeAmount4");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount4NotIn(List<BigDecimal> values) {
            addCriterion("before_amount_4 not in", values, "beforeAmount4");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount4Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_amount_4 between", value1, value2, "beforeAmount4");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount4NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_amount_4 not between", value1, value2, "beforeAmount4");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount5IsNull() {
            addCriterion("before_amount_5 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount5IsNotNull() {
            addCriterion("before_amount_5 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount5EqualTo(BigDecimal value) {
            addCriterion("before_amount_5 =", value, "beforeAmount5");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount5NotEqualTo(BigDecimal value) {
            addCriterion("before_amount_5 <>", value, "beforeAmount5");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount5GreaterThan(BigDecimal value) {
            addCriterion("before_amount_5 >", value, "beforeAmount5");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount5GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_amount_5 >=", value, "beforeAmount5");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount5LessThan(BigDecimal value) {
            addCriterion("before_amount_5 <", value, "beforeAmount5");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount5LessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_amount_5 <=", value, "beforeAmount5");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount5In(List<BigDecimal> values) {
            addCriterion("before_amount_5 in", values, "beforeAmount5");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount5NotIn(List<BigDecimal> values) {
            addCriterion("before_amount_5 not in", values, "beforeAmount5");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount5Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_amount_5 between", value1, value2, "beforeAmount5");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount5NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_amount_5 not between", value1, value2, "beforeAmount5");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount6IsNull() {
            addCriterion("before_amount_6 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount6IsNotNull() {
            addCriterion("before_amount_6 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount6EqualTo(BigDecimal value) {
            addCriterion("before_amount_6 =", value, "beforeAmount6");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount6NotEqualTo(BigDecimal value) {
            addCriterion("before_amount_6 <>", value, "beforeAmount6");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount6GreaterThan(BigDecimal value) {
            addCriterion("before_amount_6 >", value, "beforeAmount6");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount6GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_amount_6 >=", value, "beforeAmount6");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount6LessThan(BigDecimal value) {
            addCriterion("before_amount_6 <", value, "beforeAmount6");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount6LessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_amount_6 <=", value, "beforeAmount6");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount6In(List<BigDecimal> values) {
            addCriterion("before_amount_6 in", values, "beforeAmount6");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount6NotIn(List<BigDecimal> values) {
            addCriterion("before_amount_6 not in", values, "beforeAmount6");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount6Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_amount_6 between", value1, value2, "beforeAmount6");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount6NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_amount_6 not between", value1, value2, "beforeAmount6");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount7IsNull() {
            addCriterion("before_amount_7 is null");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount7IsNotNull() {
            addCriterion("before_amount_7 is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount7EqualTo(BigDecimal value) {
            addCriterion("before_amount_7 =", value, "beforeAmount7");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount7NotEqualTo(BigDecimal value) {
            addCriterion("before_amount_7 <>", value, "beforeAmount7");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount7GreaterThan(BigDecimal value) {
            addCriterion("before_amount_7 >", value, "beforeAmount7");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount7GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_amount_7 >=", value, "beforeAmount7");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount7LessThan(BigDecimal value) {
            addCriterion("before_amount_7 <", value, "beforeAmount7");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount7LessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_amount_7 <=", value, "beforeAmount7");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount7In(List<BigDecimal> values) {
            addCriterion("before_amount_7 in", values, "beforeAmount7");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount7NotIn(List<BigDecimal> values) {
            addCriterion("before_amount_7 not in", values, "beforeAmount7");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount7Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_amount_7 between", value1, value2, "beforeAmount7");
            return (Criteria) this;
        }

        public Criteria andBeforeAmount7NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_amount_7 not between", value1, value2, "beforeAmount7");
            return (Criteria) this;
        }

        public Criteria andLastUseDateIsNull() {
            addCriterion("last_use_date is null");
            return (Criteria) this;
        }

        public Criteria andLastUseDateIsNotNull() {
            addCriterion("last_use_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastUseDateEqualTo(Date value) {
            addCriterion("last_use_date =", value, "lastUseDate");
            return (Criteria) this;
        }

        public Criteria andLastUseDateNotEqualTo(Date value) {
            addCriterion("last_use_date <>", value, "lastUseDate");
            return (Criteria) this;
        }

        public Criteria andLastUseDateGreaterThan(Date value) {
            addCriterion("last_use_date >", value, "lastUseDate");
            return (Criteria) this;
        }

        public Criteria andLastUseDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_use_date >=", value, "lastUseDate");
            return (Criteria) this;
        }

        public Criteria andLastUseDateLessThan(Date value) {
            addCriterion("last_use_date <", value, "lastUseDate");
            return (Criteria) this;
        }

        public Criteria andLastUseDateLessThanOrEqualTo(Date value) {
            addCriterion("last_use_date <=", value, "lastUseDate");
            return (Criteria) this;
        }

        public Criteria andLastUseDateIn(List<Date> values) {
            addCriterion("last_use_date in", values, "lastUseDate");
            return (Criteria) this;
        }

        public Criteria andLastUseDateNotIn(List<Date> values) {
            addCriterion("last_use_date not in", values, "lastUseDate");
            return (Criteria) this;
        }

        public Criteria andLastUseDateBetween(Date value1, Date value2) {
            addCriterion("last_use_date between", value1, value2, "lastUseDate");
            return (Criteria) this;
        }

        public Criteria andLastUseDateNotBetween(Date value1, Date value2) {
            addCriterion("last_use_date not between", value1, value2, "lastUseDate");
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
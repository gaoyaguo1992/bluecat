package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DevTradeGatherInfoModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DevTradeGatherInfoModelExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andHisTradeAmountIsNull() {
            addCriterion("his_trade_amount is null");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountIsNotNull() {
            addCriterion("his_trade_amount is not null");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountEqualTo(BigDecimal value) {
            addCriterion("his_trade_amount =", value, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountNotEqualTo(BigDecimal value) {
            addCriterion("his_trade_amount <>", value, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountGreaterThan(BigDecimal value) {
            addCriterion("his_trade_amount >", value, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("his_trade_amount >=", value, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountLessThan(BigDecimal value) {
            addCriterion("his_trade_amount <", value, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("his_trade_amount <=", value, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountIn(List<BigDecimal> values) {
            addCriterion("his_trade_amount in", values, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountNotIn(List<BigDecimal> values) {
            addCriterion("his_trade_amount not in", values, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("his_trade_amount between", value1, value2, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("his_trade_amount not between", value1, value2, "hisTradeAmount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountIsNull() {
            addCriterion("his_trade_count is null");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountIsNotNull() {
            addCriterion("his_trade_count is not null");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountEqualTo(Integer value) {
            addCriterion("his_trade_count =", value, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountNotEqualTo(Integer value) {
            addCriterion("his_trade_count <>", value, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountGreaterThan(Integer value) {
            addCriterion("his_trade_count >", value, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("his_trade_count >=", value, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountLessThan(Integer value) {
            addCriterion("his_trade_count <", value, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountLessThanOrEqualTo(Integer value) {
            addCriterion("his_trade_count <=", value, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountIn(List<Integer> values) {
            addCriterion("his_trade_count in", values, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountNotIn(List<Integer> values) {
            addCriterion("his_trade_count not in", values, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountBetween(Integer value1, Integer value2) {
            addCriterion("his_trade_count between", value1, value2, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andHisTradeCountNotBetween(Integer value1, Integer value2) {
            addCriterion("his_trade_count not between", value1, value2, "hisTradeCount");
            return (Criteria) this;
        }

        public Criteria andYesTradeAmountIsNull() {
            addCriterion("yes_trade_amount is null");
            return (Criteria) this;
        }

        public Criteria andYesTradeAmountIsNotNull() {
            addCriterion("yes_trade_amount is not null");
            return (Criteria) this;
        }

        public Criteria andYesTradeAmountEqualTo(BigDecimal value) {
            addCriterion("yes_trade_amount =", value, "yesTradeAmount");
            return (Criteria) this;
        }

        public Criteria andYesTradeAmountNotEqualTo(BigDecimal value) {
            addCriterion("yes_trade_amount <>", value, "yesTradeAmount");
            return (Criteria) this;
        }

        public Criteria andYesTradeAmountGreaterThan(BigDecimal value) {
            addCriterion("yes_trade_amount >", value, "yesTradeAmount");
            return (Criteria) this;
        }

        public Criteria andYesTradeAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("yes_trade_amount >=", value, "yesTradeAmount");
            return (Criteria) this;
        }

        public Criteria andYesTradeAmountLessThan(BigDecimal value) {
            addCriterion("yes_trade_amount <", value, "yesTradeAmount");
            return (Criteria) this;
        }

        public Criteria andYesTradeAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("yes_trade_amount <=", value, "yesTradeAmount");
            return (Criteria) this;
        }

        public Criteria andYesTradeAmountIn(List<BigDecimal> values) {
            addCriterion("yes_trade_amount in", values, "yesTradeAmount");
            return (Criteria) this;
        }

        public Criteria andYesTradeAmountNotIn(List<BigDecimal> values) {
            addCriterion("yes_trade_amount not in", values, "yesTradeAmount");
            return (Criteria) this;
        }

        public Criteria andYesTradeAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("yes_trade_amount between", value1, value2, "yesTradeAmount");
            return (Criteria) this;
        }

        public Criteria andYesTradeAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("yes_trade_amount not between", value1, value2, "yesTradeAmount");
            return (Criteria) this;
        }

        public Criteria andYesTradeCountIsNull() {
            addCriterion("yes_trade_count is null");
            return (Criteria) this;
        }

        public Criteria andYesTradeCountIsNotNull() {
            addCriterion("yes_trade_count is not null");
            return (Criteria) this;
        }

        public Criteria andYesTradeCountEqualTo(Integer value) {
            addCriterion("yes_trade_count =", value, "yesTradeCount");
            return (Criteria) this;
        }

        public Criteria andYesTradeCountNotEqualTo(Integer value) {
            addCriterion("yes_trade_count <>", value, "yesTradeCount");
            return (Criteria) this;
        }

        public Criteria andYesTradeCountGreaterThan(Integer value) {
            addCriterion("yes_trade_count >", value, "yesTradeCount");
            return (Criteria) this;
        }

        public Criteria andYesTradeCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("yes_trade_count >=", value, "yesTradeCount");
            return (Criteria) this;
        }

        public Criteria andYesTradeCountLessThan(Integer value) {
            addCriterion("yes_trade_count <", value, "yesTradeCount");
            return (Criteria) this;
        }

        public Criteria andYesTradeCountLessThanOrEqualTo(Integer value) {
            addCriterion("yes_trade_count <=", value, "yesTradeCount");
            return (Criteria) this;
        }

        public Criteria andYesTradeCountIn(List<Integer> values) {
            addCriterion("yes_trade_count in", values, "yesTradeCount");
            return (Criteria) this;
        }

        public Criteria andYesTradeCountNotIn(List<Integer> values) {
            addCriterion("yes_trade_count not in", values, "yesTradeCount");
            return (Criteria) this;
        }

        public Criteria andYesTradeCountBetween(Integer value1, Integer value2) {
            addCriterion("yes_trade_count between", value1, value2, "yesTradeCount");
            return (Criteria) this;
        }

        public Criteria andYesTradeCountNotBetween(Integer value1, Integer value2) {
            addCriterion("yes_trade_count not between", value1, value2, "yesTradeCount");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNull() {
            addCriterion("unit_Price is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNotNull() {
            addCriterion("unit_Price is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceEqualTo(BigDecimal value) {
            addCriterion("unit_Price =", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotEqualTo(BigDecimal value) {
            addCriterion("unit_Price <>", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThan(BigDecimal value) {
            addCriterion("unit_Price >", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unit_Price >=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThan(BigDecimal value) {
            addCriterion("unit_Price <", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unit_Price <=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIn(List<BigDecimal> values) {
            addCriterion("unit_Price in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotIn(List<BigDecimal> values) {
            addCriterion("unit_Price not in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unit_Price between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unit_Price not between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeIsNull() {
            addCriterion("count_time is null");
            return (Criteria) this;
        }

        public Criteria andCountTimeIsNotNull() {
            addCriterion("count_time is not null");
            return (Criteria) this;
        }

        public Criteria andCountTimeEqualTo(Date value) {
            addCriterionForJDBCDate("count_time =", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("count_time <>", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("count_time >", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("count_time >=", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeLessThan(Date value) {
            addCriterionForJDBCDate("count_time <", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("count_time <=", value, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeIn(List<Date> values) {
            addCriterionForJDBCDate("count_time in", values, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("count_time not in", values, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("count_time between", value1, value2, "countTime");
            return (Criteria) this;
        }

        public Criteria andCountTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("count_time not between", value1, value2, "countTime");
            return (Criteria) this;
        }

        public Criteria andIsRepairIsNull() {
            addCriterion("is_repair is null");
            return (Criteria) this;
        }

        public Criteria andIsRepairIsNotNull() {
            addCriterion("is_repair is not null");
            return (Criteria) this;
        }

        public Criteria andIsRepairEqualTo(Byte value) {
            addCriterion("is_repair =", value, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairNotEqualTo(Byte value) {
            addCriterion("is_repair <>", value, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairGreaterThan(Byte value) {
            addCriterion("is_repair >", value, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_repair >=", value, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairLessThan(Byte value) {
            addCriterion("is_repair <", value, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairLessThanOrEqualTo(Byte value) {
            addCriterion("is_repair <=", value, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairIn(List<Byte> values) {
            addCriterion("is_repair in", values, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairNotIn(List<Byte> values) {
            addCriterion("is_repair not in", values, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairBetween(Byte value1, Byte value2) {
            addCriterion("is_repair between", value1, value2, "isRepair");
            return (Criteria) this;
        }

        public Criteria andIsRepairNotBetween(Byte value1, Byte value2) {
            addCriterion("is_repair not between", value1, value2, "isRepair");
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
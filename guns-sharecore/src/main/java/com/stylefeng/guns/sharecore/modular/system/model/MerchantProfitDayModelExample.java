package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MerchantProfitDayModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerchantProfitDayModelExample() {
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

        public Criteria andCheckDateIsNull() {
            addCriterion("check_date is null");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNotNull() {
            addCriterion("check_date is not null");
            return (Criteria) this;
        }

        public Criteria andCheckDateEqualTo(Date value) {
            addCriterionForJDBCDate("check_date =", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("check_date <>", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThan(Date value) {
            addCriterionForJDBCDate("check_date >", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("check_date >=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThan(Date value) {
            addCriterionForJDBCDate("check_date <", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("check_date <=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateIn(List<Date> values) {
            addCriterionForJDBCDate("check_date in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("check_date not in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("check_date between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("check_date not between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckMonthIsNull() {
            addCriterion("check_month is null");
            return (Criteria) this;
        }

        public Criteria andCheckMonthIsNotNull() {
            addCriterion("check_month is not null");
            return (Criteria) this;
        }

        public Criteria andCheckMonthEqualTo(String value) {
            addCriterion("check_month =", value, "checkMonth");
            return (Criteria) this;
        }

        public Criteria andCheckMonthNotEqualTo(String value) {
            addCriterion("check_month <>", value, "checkMonth");
            return (Criteria) this;
        }

        public Criteria andCheckMonthGreaterThan(String value) {
            addCriterion("check_month >", value, "checkMonth");
            return (Criteria) this;
        }

        public Criteria andCheckMonthGreaterThanOrEqualTo(String value) {
            addCriterion("check_month >=", value, "checkMonth");
            return (Criteria) this;
        }

        public Criteria andCheckMonthLessThan(String value) {
            addCriterion("check_month <", value, "checkMonth");
            return (Criteria) this;
        }

        public Criteria andCheckMonthLessThanOrEqualTo(String value) {
            addCriterion("check_month <=", value, "checkMonth");
            return (Criteria) this;
        }

        public Criteria andCheckMonthLike(String value) {
            addCriterion("check_month like", value, "checkMonth");
            return (Criteria) this;
        }

        public Criteria andCheckMonthNotLike(String value) {
            addCriterion("check_month not like", value, "checkMonth");
            return (Criteria) this;
        }

        public Criteria andCheckMonthIn(List<String> values) {
            addCriterion("check_month in", values, "checkMonth");
            return (Criteria) this;
        }

        public Criteria andCheckMonthNotIn(List<String> values) {
            addCriterion("check_month not in", values, "checkMonth");
            return (Criteria) this;
        }

        public Criteria andCheckMonthBetween(String value1, String value2) {
            addCriterion("check_month between", value1, value2, "checkMonth");
            return (Criteria) this;
        }

        public Criteria andCheckMonthNotBetween(String value1, String value2) {
            addCriterion("check_month not between", value1, value2, "checkMonth");
            return (Criteria) this;
        }

        public Criteria andCheckYearIsNull() {
            addCriterion("check_year is null");
            return (Criteria) this;
        }

        public Criteria andCheckYearIsNotNull() {
            addCriterion("check_year is not null");
            return (Criteria) this;
        }

        public Criteria andCheckYearEqualTo(String value) {
            addCriterion("check_year =", value, "checkYear");
            return (Criteria) this;
        }

        public Criteria andCheckYearNotEqualTo(String value) {
            addCriterion("check_year <>", value, "checkYear");
            return (Criteria) this;
        }

        public Criteria andCheckYearGreaterThan(String value) {
            addCriterion("check_year >", value, "checkYear");
            return (Criteria) this;
        }

        public Criteria andCheckYearGreaterThanOrEqualTo(String value) {
            addCriterion("check_year >=", value, "checkYear");
            return (Criteria) this;
        }

        public Criteria andCheckYearLessThan(String value) {
            addCriterion("check_year <", value, "checkYear");
            return (Criteria) this;
        }

        public Criteria andCheckYearLessThanOrEqualTo(String value) {
            addCriterion("check_year <=", value, "checkYear");
            return (Criteria) this;
        }

        public Criteria andCheckYearLike(String value) {
            addCriterion("check_year like", value, "checkYear");
            return (Criteria) this;
        }

        public Criteria andCheckYearNotLike(String value) {
            addCriterion("check_year not like", value, "checkYear");
            return (Criteria) this;
        }

        public Criteria andCheckYearIn(List<String> values) {
            addCriterion("check_year in", values, "checkYear");
            return (Criteria) this;
        }

        public Criteria andCheckYearNotIn(List<String> values) {
            addCriterion("check_year not in", values, "checkYear");
            return (Criteria) this;
        }

        public Criteria andCheckYearBetween(String value1, String value2) {
            addCriterion("check_year between", value1, value2, "checkYear");
            return (Criteria) this;
        }

        public Criteria andCheckYearNotBetween(String value1, String value2) {
            addCriterion("check_year not between", value1, value2, "checkYear");
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

        public Criteria andRatioIdIsNull() {
            addCriterion("ratio_id is null");
            return (Criteria) this;
        }

        public Criteria andRatioIdIsNotNull() {
            addCriterion("ratio_id is not null");
            return (Criteria) this;
        }

        public Criteria andRatioIdEqualTo(Long value) {
            addCriterion("ratio_id =", value, "ratioId");
            return (Criteria) this;
        }

        public Criteria andRatioIdNotEqualTo(Long value) {
            addCriterion("ratio_id <>", value, "ratioId");
            return (Criteria) this;
        }

        public Criteria andRatioIdGreaterThan(Long value) {
            addCriterion("ratio_id >", value, "ratioId");
            return (Criteria) this;
        }

        public Criteria andRatioIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ratio_id >=", value, "ratioId");
            return (Criteria) this;
        }

        public Criteria andRatioIdLessThan(Long value) {
            addCriterion("ratio_id <", value, "ratioId");
            return (Criteria) this;
        }

        public Criteria andRatioIdLessThanOrEqualTo(Long value) {
            addCriterion("ratio_id <=", value, "ratioId");
            return (Criteria) this;
        }

        public Criteria andRatioIdIn(List<Long> values) {
            addCriterion("ratio_id in", values, "ratioId");
            return (Criteria) this;
        }

        public Criteria andRatioIdNotIn(List<Long> values) {
            addCriterion("ratio_id not in", values, "ratioId");
            return (Criteria) this;
        }

        public Criteria andRatioIdBetween(Long value1, Long value2) {
            addCriterion("ratio_id between", value1, value2, "ratioId");
            return (Criteria) this;
        }

        public Criteria andRatioIdNotBetween(Long value1, Long value2) {
            addCriterion("ratio_id not between", value1, value2, "ratioId");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIsNull() {
            addCriterion("merchant_name is null");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIsNotNull() {
            addCriterion("merchant_name is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantNameEqualTo(String value) {
            addCriterion("merchant_name =", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotEqualTo(String value) {
            addCriterion("merchant_name <>", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameGreaterThan(String value) {
            addCriterion("merchant_name >", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_name >=", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLessThan(String value) {
            addCriterion("merchant_name <", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLessThanOrEqualTo(String value) {
            addCriterion("merchant_name <=", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLike(String value) {
            addCriterion("merchant_name like", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotLike(String value) {
            addCriterion("merchant_name not like", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIn(List<String> values) {
            addCriterion("merchant_name in", values, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotIn(List<String> values) {
            addCriterion("merchant_name not in", values, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameBetween(String value1, String value2) {
            addCriterion("merchant_name between", value1, value2, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotBetween(String value1, String value2) {
            addCriterion("merchant_name not between", value1, value2, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIsNull() {
            addCriterion("merchant_type is null");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIsNotNull() {
            addCriterion("merchant_type is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeEqualTo(String value) {
            addCriterion("merchant_type =", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeNotEqualTo(String value) {
            addCriterion("merchant_type <>", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeGreaterThan(String value) {
            addCriterion("merchant_type >", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_type >=", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeLessThan(String value) {
            addCriterion("merchant_type <", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeLessThanOrEqualTo(String value) {
            addCriterion("merchant_type <=", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeLike(String value) {
            addCriterion("merchant_type like", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeNotLike(String value) {
            addCriterion("merchant_type not like", value, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeIn(List<String> values) {
            addCriterion("merchant_type in", values, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeNotIn(List<String> values) {
            addCriterion("merchant_type not in", values, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeBetween(String value1, String value2) {
            addCriterion("merchant_type between", value1, value2, "merchantType");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeNotBetween(String value1, String value2) {
            addCriterion("merchant_type not between", value1, value2, "merchantType");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeIsNull() {
            addCriterion("settle_fee_type is null");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeIsNotNull() {
            addCriterion("settle_fee_type is not null");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeEqualTo(Byte value) {
            addCriterion("settle_fee_type =", value, "settleFeeType");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeNotEqualTo(Byte value) {
            addCriterion("settle_fee_type <>", value, "settleFeeType");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeGreaterThan(Byte value) {
            addCriterion("settle_fee_type >", value, "settleFeeType");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("settle_fee_type >=", value, "settleFeeType");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeLessThan(Byte value) {
            addCriterion("settle_fee_type <", value, "settleFeeType");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeLessThanOrEqualTo(Byte value) {
            addCriterion("settle_fee_type <=", value, "settleFeeType");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeIn(List<Byte> values) {
            addCriterion("settle_fee_type in", values, "settleFeeType");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeNotIn(List<Byte> values) {
            addCriterion("settle_fee_type not in", values, "settleFeeType");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeBetween(Byte value1, Byte value2) {
            addCriterion("settle_fee_type between", value1, value2, "settleFeeType");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("settle_fee_type not between", value1, value2, "settleFeeType");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeCnIsNull() {
            addCriterion("settle_fee_type_cn is null");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeCnIsNotNull() {
            addCriterion("settle_fee_type_cn is not null");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeCnEqualTo(String value) {
            addCriterion("settle_fee_type_cn =", value, "settleFeeTypeCn");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeCnNotEqualTo(String value) {
            addCriterion("settle_fee_type_cn <>", value, "settleFeeTypeCn");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeCnGreaterThan(String value) {
            addCriterion("settle_fee_type_cn >", value, "settleFeeTypeCn");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeCnGreaterThanOrEqualTo(String value) {
            addCriterion("settle_fee_type_cn >=", value, "settleFeeTypeCn");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeCnLessThan(String value) {
            addCriterion("settle_fee_type_cn <", value, "settleFeeTypeCn");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeCnLessThanOrEqualTo(String value) {
            addCriterion("settle_fee_type_cn <=", value, "settleFeeTypeCn");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeCnLike(String value) {
            addCriterion("settle_fee_type_cn like", value, "settleFeeTypeCn");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeCnNotLike(String value) {
            addCriterion("settle_fee_type_cn not like", value, "settleFeeTypeCn");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeCnIn(List<String> values) {
            addCriterion("settle_fee_type_cn in", values, "settleFeeTypeCn");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeCnNotIn(List<String> values) {
            addCriterion("settle_fee_type_cn not in", values, "settleFeeTypeCn");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeCnBetween(String value1, String value2) {
            addCriterion("settle_fee_type_cn between", value1, value2, "settleFeeTypeCn");
            return (Criteria) this;
        }

        public Criteria andSettleFeeTypeCnNotBetween(String value1, String value2) {
            addCriterion("settle_fee_type_cn not between", value1, value2, "settleFeeTypeCn");
            return (Criteria) this;
        }

        public Criteria andSettlementCountIsNull() {
            addCriterion("settlement_count is null");
            return (Criteria) this;
        }

        public Criteria andSettlementCountIsNotNull() {
            addCriterion("settlement_count is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementCountEqualTo(Integer value) {
            addCriterion("settlement_count =", value, "settlementCount");
            return (Criteria) this;
        }

        public Criteria andSettlementCountNotEqualTo(Integer value) {
            addCriterion("settlement_count <>", value, "settlementCount");
            return (Criteria) this;
        }

        public Criteria andSettlementCountGreaterThan(Integer value) {
            addCriterion("settlement_count >", value, "settlementCount");
            return (Criteria) this;
        }

        public Criteria andSettlementCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("settlement_count >=", value, "settlementCount");
            return (Criteria) this;
        }

        public Criteria andSettlementCountLessThan(Integer value) {
            addCriterion("settlement_count <", value, "settlementCount");
            return (Criteria) this;
        }

        public Criteria andSettlementCountLessThanOrEqualTo(Integer value) {
            addCriterion("settlement_count <=", value, "settlementCount");
            return (Criteria) this;
        }

        public Criteria andSettlementCountIn(List<Integer> values) {
            addCriterion("settlement_count in", values, "settlementCount");
            return (Criteria) this;
        }

        public Criteria andSettlementCountNotIn(List<Integer> values) {
            addCriterion("settlement_count not in", values, "settlementCount");
            return (Criteria) this;
        }

        public Criteria andSettlementCountBetween(Integer value1, Integer value2) {
            addCriterion("settlement_count between", value1, value2, "settlementCount");
            return (Criteria) this;
        }

        public Criteria andSettlementCountNotBetween(Integer value1, Integer value2) {
            addCriterion("settlement_count not between", value1, value2, "settlementCount");
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

        public Criteria andDoneAmountIsNull() {
            addCriterion("done_amount is null");
            return (Criteria) this;
        }

        public Criteria andDoneAmountIsNotNull() {
            addCriterion("done_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDoneAmountEqualTo(BigDecimal value) {
            addCriterion("done_amount =", value, "doneAmount");
            return (Criteria) this;
        }

        public Criteria andDoneAmountNotEqualTo(BigDecimal value) {
            addCriterion("done_amount <>", value, "doneAmount");
            return (Criteria) this;
        }

        public Criteria andDoneAmountGreaterThan(BigDecimal value) {
            addCriterion("done_amount >", value, "doneAmount");
            return (Criteria) this;
        }

        public Criteria andDoneAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("done_amount >=", value, "doneAmount");
            return (Criteria) this;
        }

        public Criteria andDoneAmountLessThan(BigDecimal value) {
            addCriterion("done_amount <", value, "doneAmount");
            return (Criteria) this;
        }

        public Criteria andDoneAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("done_amount <=", value, "doneAmount");
            return (Criteria) this;
        }

        public Criteria andDoneAmountIn(List<BigDecimal> values) {
            addCriterion("done_amount in", values, "doneAmount");
            return (Criteria) this;
        }

        public Criteria andDoneAmountNotIn(List<BigDecimal> values) {
            addCriterion("done_amount not in", values, "doneAmount");
            return (Criteria) this;
        }

        public Criteria andDoneAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("done_amount between", value1, value2, "doneAmount");
            return (Criteria) this;
        }

        public Criteria andDoneAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("done_amount not between", value1, value2, "doneAmount");
            return (Criteria) this;
        }

        public Criteria andUnAmountIsNull() {
            addCriterion("un_amount is null");
            return (Criteria) this;
        }

        public Criteria andUnAmountIsNotNull() {
            addCriterion("un_amount is not null");
            return (Criteria) this;
        }

        public Criteria andUnAmountEqualTo(BigDecimal value) {
            addCriterion("un_amount =", value, "unAmount");
            return (Criteria) this;
        }

        public Criteria andUnAmountNotEqualTo(BigDecimal value) {
            addCriterion("un_amount <>", value, "unAmount");
            return (Criteria) this;
        }

        public Criteria andUnAmountGreaterThan(BigDecimal value) {
            addCriterion("un_amount >", value, "unAmount");
            return (Criteria) this;
        }

        public Criteria andUnAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("un_amount >=", value, "unAmount");
            return (Criteria) this;
        }

        public Criteria andUnAmountLessThan(BigDecimal value) {
            addCriterion("un_amount <", value, "unAmount");
            return (Criteria) this;
        }

        public Criteria andUnAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("un_amount <=", value, "unAmount");
            return (Criteria) this;
        }

        public Criteria andUnAmountIn(List<BigDecimal> values) {
            addCriterion("un_amount in", values, "unAmount");
            return (Criteria) this;
        }

        public Criteria andUnAmountNotIn(List<BigDecimal> values) {
            addCriterion("un_amount not in", values, "unAmount");
            return (Criteria) this;
        }

        public Criteria andUnAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("un_amount between", value1, value2, "unAmount");
            return (Criteria) this;
        }

        public Criteria andUnAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("un_amount not between", value1, value2, "unAmount");
            return (Criteria) this;
        }

        public Criteria andRatioTimeIsNull() {
            addCriterion("ratio_time is null");
            return (Criteria) this;
        }

        public Criteria andRatioTimeIsNotNull() {
            addCriterion("ratio_time is not null");
            return (Criteria) this;
        }

        public Criteria andRatioTimeEqualTo(Date value) {
            addCriterion("ratio_time =", value, "ratioTime");
            return (Criteria) this;
        }

        public Criteria andRatioTimeNotEqualTo(Date value) {
            addCriterion("ratio_time <>", value, "ratioTime");
            return (Criteria) this;
        }

        public Criteria andRatioTimeGreaterThan(Date value) {
            addCriterion("ratio_time >", value, "ratioTime");
            return (Criteria) this;
        }

        public Criteria andRatioTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ratio_time >=", value, "ratioTime");
            return (Criteria) this;
        }

        public Criteria andRatioTimeLessThan(Date value) {
            addCriterion("ratio_time <", value, "ratioTime");
            return (Criteria) this;
        }

        public Criteria andRatioTimeLessThanOrEqualTo(Date value) {
            addCriterion("ratio_time <=", value, "ratioTime");
            return (Criteria) this;
        }

        public Criteria andRatioTimeIn(List<Date> values) {
            addCriterion("ratio_time in", values, "ratioTime");
            return (Criteria) this;
        }

        public Criteria andRatioTimeNotIn(List<Date> values) {
            addCriterion("ratio_time not in", values, "ratioTime");
            return (Criteria) this;
        }

        public Criteria andRatioTimeBetween(Date value1, Date value2) {
            addCriterion("ratio_time between", value1, value2, "ratioTime");
            return (Criteria) this;
        }

        public Criteria andRatioTimeNotBetween(Date value1, Date value2) {
            addCriterion("ratio_time not between", value1, value2, "ratioTime");
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

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnIsNull() {
            addCriterion("industry_category_cn is null");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnIsNotNull() {
            addCriterion("industry_category_cn is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnEqualTo(String value) {
            addCriterion("industry_category_cn =", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnNotEqualTo(String value) {
            addCriterion("industry_category_cn <>", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnGreaterThan(String value) {
            addCriterion("industry_category_cn >", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnGreaterThanOrEqualTo(String value) {
            addCriterion("industry_category_cn >=", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnLessThan(String value) {
            addCriterion("industry_category_cn <", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnLessThanOrEqualTo(String value) {
            addCriterion("industry_category_cn <=", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnLike(String value) {
            addCriterion("industry_category_cn like", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnNotLike(String value) {
            addCriterion("industry_category_cn not like", value, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnIn(List<String> values) {
            addCriterion("industry_category_cn in", values, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnNotIn(List<String> values) {
            addCriterion("industry_category_cn not in", values, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnBetween(String value1, String value2) {
            addCriterion("industry_category_cn between", value1, value2, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andIndustryCategoryCnNotBetween(String value1, String value2) {
            addCriterion("industry_category_cn not between", value1, value2, "industryCategoryCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnIsNull() {
            addCriterion("merchant_type_cn is null");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnIsNotNull() {
            addCriterion("merchant_type_cn is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnEqualTo(String value) {
            addCriterion("merchant_type_cn =", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnNotEqualTo(String value) {
            addCriterion("merchant_type_cn <>", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnGreaterThan(String value) {
            addCriterion("merchant_type_cn >", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_type_cn >=", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnLessThan(String value) {
            addCriterion("merchant_type_cn <", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnLessThanOrEqualTo(String value) {
            addCriterion("merchant_type_cn <=", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnLike(String value) {
            addCriterion("merchant_type_cn like", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnNotLike(String value) {
            addCriterion("merchant_type_cn not like", value, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnIn(List<String> values) {
            addCriterion("merchant_type_cn in", values, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnNotIn(List<String> values) {
            addCriterion("merchant_type_cn not in", values, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnBetween(String value1, String value2) {
            addCriterion("merchant_type_cn between", value1, value2, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerchantTypeCnNotBetween(String value1, String value2) {
            addCriterion("merchant_type_cn not between", value1, value2, "merchantTypeCn");
            return (Criteria) this;
        }

        public Criteria andChargerTypeIsNull() {
            addCriterion("charger_type is null");
            return (Criteria) this;
        }

        public Criteria andChargerTypeIsNotNull() {
            addCriterion("charger_type is not null");
            return (Criteria) this;
        }

        public Criteria andChargerTypeEqualTo(Byte value) {
            addCriterion("charger_type =", value, "chargerType");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNotEqualTo(Byte value) {
            addCriterion("charger_type <>", value, "chargerType");
            return (Criteria) this;
        }

        public Criteria andChargerTypeGreaterThan(Byte value) {
            addCriterion("charger_type >", value, "chargerType");
            return (Criteria) this;
        }

        public Criteria andChargerTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("charger_type >=", value, "chargerType");
            return (Criteria) this;
        }

        public Criteria andChargerTypeLessThan(Byte value) {
            addCriterion("charger_type <", value, "chargerType");
            return (Criteria) this;
        }

        public Criteria andChargerTypeLessThanOrEqualTo(Byte value) {
            addCriterion("charger_type <=", value, "chargerType");
            return (Criteria) this;
        }

        public Criteria andChargerTypeIn(List<Byte> values) {
            addCriterion("charger_type in", values, "chargerType");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNotIn(List<Byte> values) {
            addCriterion("charger_type not in", values, "chargerType");
            return (Criteria) this;
        }

        public Criteria andChargerTypeBetween(Byte value1, Byte value2) {
            addCriterion("charger_type between", value1, value2, "chargerType");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("charger_type not between", value1, value2, "chargerType");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNull() {
            addCriterion("batch_no is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(Long value) {
            addCriterion("batch_no =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(Long value) {
            addCriterion("batch_no <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(Long value) {
            addCriterion("batch_no >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(Long value) {
            addCriterion("batch_no >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(Long value) {
            addCriterion("batch_no <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(Long value) {
            addCriterion("batch_no <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<Long> values) {
            addCriterion("batch_no in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<Long> values) {
            addCriterion("batch_no not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(Long value1, Long value2) {
            addCriterion("batch_no between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(Long value1, Long value2) {
            addCriterion("batch_no not between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andIsFreezeIsNull() {
            addCriterion("is_freeze is null");
            return (Criteria) this;
        }

        public Criteria andIsFreezeIsNotNull() {
            addCriterion("is_freeze is not null");
            return (Criteria) this;
        }

        public Criteria andIsFreezeEqualTo(Byte value) {
            addCriterion("is_freeze =", value, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeNotEqualTo(Byte value) {
            addCriterion("is_freeze <>", value, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeGreaterThan(Byte value) {
            addCriterion("is_freeze >", value, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_freeze >=", value, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeLessThan(Byte value) {
            addCriterion("is_freeze <", value, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeLessThanOrEqualTo(Byte value) {
            addCriterion("is_freeze <=", value, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeIn(List<Byte> values) {
            addCriterion("is_freeze in", values, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeNotIn(List<Byte> values) {
            addCriterion("is_freeze not in", values, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeBetween(Byte value1, Byte value2) {
            addCriterion("is_freeze between", value1, value2, "isFreeze");
            return (Criteria) this;
        }

        public Criteria andIsFreezeNotBetween(Byte value1, Byte value2) {
            addCriterion("is_freeze not between", value1, value2, "isFreeze");
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
package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MerWithdrawMetadataModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerWithdrawMetadataModelExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeIsNull() {
            addCriterion("mer_withdraw_type is null");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeIsNotNull() {
            addCriterion("mer_withdraw_type is not null");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeEqualTo(Byte value) {
            addCriterion("mer_withdraw_type =", value, "merWithdrawType");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeNotEqualTo(Byte value) {
            addCriterion("mer_withdraw_type <>", value, "merWithdrawType");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeGreaterThan(Byte value) {
            addCriterion("mer_withdraw_type >", value, "merWithdrawType");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("mer_withdraw_type >=", value, "merWithdrawType");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeLessThan(Byte value) {
            addCriterion("mer_withdraw_type <", value, "merWithdrawType");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeLessThanOrEqualTo(Byte value) {
            addCriterion("mer_withdraw_type <=", value, "merWithdrawType");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeIn(List<Byte> values) {
            addCriterion("mer_withdraw_type in", values, "merWithdrawType");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeNotIn(List<Byte> values) {
            addCriterion("mer_withdraw_type not in", values, "merWithdrawType");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeBetween(Byte value1, Byte value2) {
            addCriterion("mer_withdraw_type between", value1, value2, "merWithdrawType");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("mer_withdraw_type not between", value1, value2, "merWithdrawType");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeCnIsNull() {
            addCriterion("mer_withdraw_type_cn is null");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeCnIsNotNull() {
            addCriterion("mer_withdraw_type_cn is not null");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeCnEqualTo(String value) {
            addCriterion("mer_withdraw_type_cn =", value, "merWithdrawTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeCnNotEqualTo(String value) {
            addCriterion("mer_withdraw_type_cn <>", value, "merWithdrawTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeCnGreaterThan(String value) {
            addCriterion("mer_withdraw_type_cn >", value, "merWithdrawTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeCnGreaterThanOrEqualTo(String value) {
            addCriterion("mer_withdraw_type_cn >=", value, "merWithdrawTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeCnLessThan(String value) {
            addCriterion("mer_withdraw_type_cn <", value, "merWithdrawTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeCnLessThanOrEqualTo(String value) {
            addCriterion("mer_withdraw_type_cn <=", value, "merWithdrawTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeCnLike(String value) {
            addCriterion("mer_withdraw_type_cn like", value, "merWithdrawTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeCnNotLike(String value) {
            addCriterion("mer_withdraw_type_cn not like", value, "merWithdrawTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeCnIn(List<String> values) {
            addCriterion("mer_withdraw_type_cn in", values, "merWithdrawTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeCnNotIn(List<String> values) {
            addCriterion("mer_withdraw_type_cn not in", values, "merWithdrawTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeCnBetween(String value1, String value2) {
            addCriterion("mer_withdraw_type_cn between", value1, value2, "merWithdrawTypeCn");
            return (Criteria) this;
        }

        public Criteria andMerWithdrawTypeCnNotBetween(String value1, String value2) {
            addCriterion("mer_withdraw_type_cn not between", value1, value2, "merWithdrawTypeCn");
            return (Criteria) this;
        }

        public Criteria andMaxWithdrawTimesIsNull() {
            addCriterion("max_withdraw_times is null");
            return (Criteria) this;
        }

        public Criteria andMaxWithdrawTimesIsNotNull() {
            addCriterion("max_withdraw_times is not null");
            return (Criteria) this;
        }

        public Criteria andMaxWithdrawTimesEqualTo(Byte value) {
            addCriterion("max_withdraw_times =", value, "maxWithdrawTimes");
            return (Criteria) this;
        }

        public Criteria andMaxWithdrawTimesNotEqualTo(Byte value) {
            addCriterion("max_withdraw_times <>", value, "maxWithdrawTimes");
            return (Criteria) this;
        }

        public Criteria andMaxWithdrawTimesGreaterThan(Byte value) {
            addCriterion("max_withdraw_times >", value, "maxWithdrawTimes");
            return (Criteria) this;
        }

        public Criteria andMaxWithdrawTimesGreaterThanOrEqualTo(Byte value) {
            addCriterion("max_withdraw_times >=", value, "maxWithdrawTimes");
            return (Criteria) this;
        }

        public Criteria andMaxWithdrawTimesLessThan(Byte value) {
            addCriterion("max_withdraw_times <", value, "maxWithdrawTimes");
            return (Criteria) this;
        }

        public Criteria andMaxWithdrawTimesLessThanOrEqualTo(Byte value) {
            addCriterion("max_withdraw_times <=", value, "maxWithdrawTimes");
            return (Criteria) this;
        }

        public Criteria andMaxWithdrawTimesIn(List<Byte> values) {
            addCriterion("max_withdraw_times in", values, "maxWithdrawTimes");
            return (Criteria) this;
        }

        public Criteria andMaxWithdrawTimesNotIn(List<Byte> values) {
            addCriterion("max_withdraw_times not in", values, "maxWithdrawTimes");
            return (Criteria) this;
        }

        public Criteria andMaxWithdrawTimesBetween(Byte value1, Byte value2) {
            addCriterion("max_withdraw_times between", value1, value2, "maxWithdrawTimes");
            return (Criteria) this;
        }

        public Criteria andMaxWithdrawTimesNotBetween(Byte value1, Byte value2) {
            addCriterion("max_withdraw_times not between", value1, value2, "maxWithdrawTimes");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawStartDayIsNull() {
            addCriterion("can_withdraw_start_day is null");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawStartDayIsNotNull() {
            addCriterion("can_withdraw_start_day is not null");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawStartDayEqualTo(Byte value) {
            addCriterion("can_withdraw_start_day =", value, "canWithdrawStartDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawStartDayNotEqualTo(Byte value) {
            addCriterion("can_withdraw_start_day <>", value, "canWithdrawStartDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawStartDayGreaterThan(Byte value) {
            addCriterion("can_withdraw_start_day >", value, "canWithdrawStartDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawStartDayGreaterThanOrEqualTo(Byte value) {
            addCriterion("can_withdraw_start_day >=", value, "canWithdrawStartDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawStartDayLessThan(Byte value) {
            addCriterion("can_withdraw_start_day <", value, "canWithdrawStartDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawStartDayLessThanOrEqualTo(Byte value) {
            addCriterion("can_withdraw_start_day <=", value, "canWithdrawStartDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawStartDayIn(List<Byte> values) {
            addCriterion("can_withdraw_start_day in", values, "canWithdrawStartDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawStartDayNotIn(List<Byte> values) {
            addCriterion("can_withdraw_start_day not in", values, "canWithdrawStartDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawStartDayBetween(Byte value1, Byte value2) {
            addCriterion("can_withdraw_start_day between", value1, value2, "canWithdrawStartDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawStartDayNotBetween(Byte value1, Byte value2) {
            addCriterion("can_withdraw_start_day not between", value1, value2, "canWithdrawStartDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawEndDayIsNull() {
            addCriterion("can_withdraw_end_day is null");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawEndDayIsNotNull() {
            addCriterion("can_withdraw_end_day is not null");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawEndDayEqualTo(Byte value) {
            addCriterion("can_withdraw_end_day =", value, "canWithdrawEndDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawEndDayNotEqualTo(Byte value) {
            addCriterion("can_withdraw_end_day <>", value, "canWithdrawEndDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawEndDayGreaterThan(Byte value) {
            addCriterion("can_withdraw_end_day >", value, "canWithdrawEndDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawEndDayGreaterThanOrEqualTo(Byte value) {
            addCriterion("can_withdraw_end_day >=", value, "canWithdrawEndDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawEndDayLessThan(Byte value) {
            addCriterion("can_withdraw_end_day <", value, "canWithdrawEndDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawEndDayLessThanOrEqualTo(Byte value) {
            addCriterion("can_withdraw_end_day <=", value, "canWithdrawEndDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawEndDayIn(List<Byte> values) {
            addCriterion("can_withdraw_end_day in", values, "canWithdrawEndDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawEndDayNotIn(List<Byte> values) {
            addCriterion("can_withdraw_end_day not in", values, "canWithdrawEndDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawEndDayBetween(Byte value1, Byte value2) {
            addCriterion("can_withdraw_end_day between", value1, value2, "canWithdrawEndDay");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawEndDayNotBetween(Byte value1, Byte value2) {
            addCriterion("can_withdraw_end_day not between", value1, value2, "canWithdrawEndDay");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNull() {
            addCriterion("is_default is null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNotNull() {
            addCriterion("is_default is not null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultEqualTo(Byte value) {
            addCriterion("is_default =", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotEqualTo(Byte value) {
            addCriterion("is_default <>", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThan(Byte value) {
            addCriterion("is_default >", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_default >=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThan(Byte value) {
            addCriterion("is_default <", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThanOrEqualTo(Byte value) {
            addCriterion("is_default <=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIn(List<Byte> values) {
            addCriterion("is_default in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotIn(List<Byte> values) {
            addCriterion("is_default not in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultBetween(Byte value1, Byte value2) {
            addCriterion("is_default between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotBetween(Byte value1, Byte value2) {
            addCriterion("is_default not between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andPayWayIsNull() {
            addCriterion("pay_way is null");
            return (Criteria) this;
        }

        public Criteria andPayWayIsNotNull() {
            addCriterion("pay_way is not null");
            return (Criteria) this;
        }

        public Criteria andPayWayEqualTo(Byte value) {
            addCriterion("pay_way =", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotEqualTo(Byte value) {
            addCriterion("pay_way <>", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayGreaterThan(Byte value) {
            addCriterion("pay_way >", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayGreaterThanOrEqualTo(Byte value) {
            addCriterion("pay_way >=", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayLessThan(Byte value) {
            addCriterion("pay_way <", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayLessThanOrEqualTo(Byte value) {
            addCriterion("pay_way <=", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayIn(List<Byte> values) {
            addCriterion("pay_way in", values, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotIn(List<Byte> values) {
            addCriterion("pay_way not in", values, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayBetween(Byte value1, Byte value2) {
            addCriterion("pay_way between", value1, value2, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotBetween(Byte value1, Byte value2) {
            addCriterion("pay_way not between", value1, value2, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayCnIsNull() {
            addCriterion("pay_way_cn is null");
            return (Criteria) this;
        }

        public Criteria andPayWayCnIsNotNull() {
            addCriterion("pay_way_cn is not null");
            return (Criteria) this;
        }

        public Criteria andPayWayCnEqualTo(String value) {
            addCriterion("pay_way_cn =", value, "payWayCn");
            return (Criteria) this;
        }

        public Criteria andPayWayCnNotEqualTo(String value) {
            addCriterion("pay_way_cn <>", value, "payWayCn");
            return (Criteria) this;
        }

        public Criteria andPayWayCnGreaterThan(String value) {
            addCriterion("pay_way_cn >", value, "payWayCn");
            return (Criteria) this;
        }

        public Criteria andPayWayCnGreaterThanOrEqualTo(String value) {
            addCriterion("pay_way_cn >=", value, "payWayCn");
            return (Criteria) this;
        }

        public Criteria andPayWayCnLessThan(String value) {
            addCriterion("pay_way_cn <", value, "payWayCn");
            return (Criteria) this;
        }

        public Criteria andPayWayCnLessThanOrEqualTo(String value) {
            addCriterion("pay_way_cn <=", value, "payWayCn");
            return (Criteria) this;
        }

        public Criteria andPayWayCnLike(String value) {
            addCriterion("pay_way_cn like", value, "payWayCn");
            return (Criteria) this;
        }

        public Criteria andPayWayCnNotLike(String value) {
            addCriterion("pay_way_cn not like", value, "payWayCn");
            return (Criteria) this;
        }

        public Criteria andPayWayCnIn(List<String> values) {
            addCriterion("pay_way_cn in", values, "payWayCn");
            return (Criteria) this;
        }

        public Criteria andPayWayCnNotIn(List<String> values) {
            addCriterion("pay_way_cn not in", values, "payWayCn");
            return (Criteria) this;
        }

        public Criteria andPayWayCnBetween(String value1, String value2) {
            addCriterion("pay_way_cn between", value1, value2, "payWayCn");
            return (Criteria) this;
        }

        public Criteria andPayWayCnNotBetween(String value1, String value2) {
            addCriterion("pay_way_cn not between", value1, value2, "payWayCn");
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

        public Criteria andIsRealTimeIsNull() {
            addCriterion("is_real_time is null");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeIsNotNull() {
            addCriterion("is_real_time is not null");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeEqualTo(Byte value) {
            addCriterion("is_real_time =", value, "isRealTime");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeNotEqualTo(Byte value) {
            addCriterion("is_real_time <>", value, "isRealTime");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeGreaterThan(Byte value) {
            addCriterion("is_real_time >", value, "isRealTime");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_real_time >=", value, "isRealTime");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeLessThan(Byte value) {
            addCriterion("is_real_time <", value, "isRealTime");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeLessThanOrEqualTo(Byte value) {
            addCriterion("is_real_time <=", value, "isRealTime");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeIn(List<Byte> values) {
            addCriterion("is_real_time in", values, "isRealTime");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeNotIn(List<Byte> values) {
            addCriterion("is_real_time not in", values, "isRealTime");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeBetween(Byte value1, Byte value2) {
            addCriterion("is_real_time between", value1, value2, "isRealTime");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeNotBetween(Byte value1, Byte value2) {
            addCriterion("is_real_time not between", value1, value2, "isRealTime");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeCnIsNull() {
            addCriterion("is_real_time_cn is null");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeCnIsNotNull() {
            addCriterion("is_real_time_cn is not null");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeCnEqualTo(String value) {
            addCriterion("is_real_time_cn =", value, "isRealTimeCn");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeCnNotEqualTo(String value) {
            addCriterion("is_real_time_cn <>", value, "isRealTimeCn");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeCnGreaterThan(String value) {
            addCriterion("is_real_time_cn >", value, "isRealTimeCn");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeCnGreaterThanOrEqualTo(String value) {
            addCriterion("is_real_time_cn >=", value, "isRealTimeCn");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeCnLessThan(String value) {
            addCriterion("is_real_time_cn <", value, "isRealTimeCn");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeCnLessThanOrEqualTo(String value) {
            addCriterion("is_real_time_cn <=", value, "isRealTimeCn");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeCnLike(String value) {
            addCriterion("is_real_time_cn like", value, "isRealTimeCn");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeCnNotLike(String value) {
            addCriterion("is_real_time_cn not like", value, "isRealTimeCn");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeCnIn(List<String> values) {
            addCriterion("is_real_time_cn in", values, "isRealTimeCn");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeCnNotIn(List<String> values) {
            addCriterion("is_real_time_cn not in", values, "isRealTimeCn");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeCnBetween(String value1, String value2) {
            addCriterion("is_real_time_cn between", value1, value2, "isRealTimeCn");
            return (Criteria) this;
        }

        public Criteria andIsRealTimeCnNotBetween(String value1, String value2) {
            addCriterion("is_real_time_cn not between", value1, value2, "isRealTimeCn");
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
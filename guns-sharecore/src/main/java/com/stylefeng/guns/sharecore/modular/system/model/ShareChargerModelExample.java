package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShareChargerModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShareChargerModelExample() {
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

        public Criteria andChargerTypeIdIsNull() {
            addCriterion("charger_type_id is null");
            return (Criteria) this;
        }

        public Criteria andChargerTypeIdIsNotNull() {
            addCriterion("charger_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andChargerTypeIdEqualTo(Integer value) {
            addCriterion("charger_type_id =", value, "chargerTypeId");
            return (Criteria) this;
        }

        public Criteria andChargerTypeIdNotEqualTo(Integer value) {
            addCriterion("charger_type_id <>", value, "chargerTypeId");
            return (Criteria) this;
        }

        public Criteria andChargerTypeIdGreaterThan(Integer value) {
            addCriterion("charger_type_id >", value, "chargerTypeId");
            return (Criteria) this;
        }

        public Criteria andChargerTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("charger_type_id >=", value, "chargerTypeId");
            return (Criteria) this;
        }

        public Criteria andChargerTypeIdLessThan(Integer value) {
            addCriterion("charger_type_id <", value, "chargerTypeId");
            return (Criteria) this;
        }

        public Criteria andChargerTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("charger_type_id <=", value, "chargerTypeId");
            return (Criteria) this;
        }

        public Criteria andChargerTypeIdIn(List<Integer> values) {
            addCriterion("charger_type_id in", values, "chargerTypeId");
            return (Criteria) this;
        }

        public Criteria andChargerTypeIdNotIn(List<Integer> values) {
            addCriterion("charger_type_id not in", values, "chargerTypeId");
            return (Criteria) this;
        }

        public Criteria andChargerTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("charger_type_id between", value1, value2, "chargerTypeId");
            return (Criteria) this;
        }

        public Criteria andChargerTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("charger_type_id not between", value1, value2, "chargerTypeId");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNameIsNull() {
            addCriterion("charger_type_name is null");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNameIsNotNull() {
            addCriterion("charger_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNameEqualTo(String value) {
            addCriterion("charger_type_name =", value, "chargerTypeName");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNameNotEqualTo(String value) {
            addCriterion("charger_type_name <>", value, "chargerTypeName");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNameGreaterThan(String value) {
            addCriterion("charger_type_name >", value, "chargerTypeName");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("charger_type_name >=", value, "chargerTypeName");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNameLessThan(String value) {
            addCriterion("charger_type_name <", value, "chargerTypeName");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNameLessThanOrEqualTo(String value) {
            addCriterion("charger_type_name <=", value, "chargerTypeName");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNameLike(String value) {
            addCriterion("charger_type_name like", value, "chargerTypeName");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNameNotLike(String value) {
            addCriterion("charger_type_name not like", value, "chargerTypeName");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNameIn(List<String> values) {
            addCriterion("charger_type_name in", values, "chargerTypeName");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNameNotIn(List<String> values) {
            addCriterion("charger_type_name not in", values, "chargerTypeName");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNameBetween(String value1, String value2) {
            addCriterion("charger_type_name between", value1, value2, "chargerTypeName");
            return (Criteria) this;
        }

        public Criteria andChargerTypeNameNotBetween(String value1, String value2) {
            addCriterion("charger_type_name not between", value1, value2, "chargerTypeName");
            return (Criteria) this;
        }

        public Criteria andPwdIndexIsNull() {
            addCriterion("pwd_index is null");
            return (Criteria) this;
        }

        public Criteria andPwdIndexIsNotNull() {
            addCriterion("pwd_index is not null");
            return (Criteria) this;
        }

        public Criteria andPwdIndexEqualTo(Long value) {
            addCriterion("pwd_index =", value, "pwdIndex");
            return (Criteria) this;
        }

        public Criteria andPwdIndexNotEqualTo(Long value) {
            addCriterion("pwd_index <>", value, "pwdIndex");
            return (Criteria) this;
        }

        public Criteria andPwdIndexGreaterThan(Long value) {
            addCriterion("pwd_index >", value, "pwdIndex");
            return (Criteria) this;
        }

        public Criteria andPwdIndexGreaterThanOrEqualTo(Long value) {
            addCriterion("pwd_index >=", value, "pwdIndex");
            return (Criteria) this;
        }

        public Criteria andPwdIndexLessThan(Long value) {
            addCriterion("pwd_index <", value, "pwdIndex");
            return (Criteria) this;
        }

        public Criteria andPwdIndexLessThanOrEqualTo(Long value) {
            addCriterion("pwd_index <=", value, "pwdIndex");
            return (Criteria) this;
        }

        public Criteria andPwdIndexIn(List<Long> values) {
            addCriterion("pwd_index in", values, "pwdIndex");
            return (Criteria) this;
        }

        public Criteria andPwdIndexNotIn(List<Long> values) {
            addCriterion("pwd_index not in", values, "pwdIndex");
            return (Criteria) this;
        }

        public Criteria andPwdIndexBetween(Long value1, Long value2) {
            addCriterion("pwd_index between", value1, value2, "pwdIndex");
            return (Criteria) this;
        }

        public Criteria andPwdIndexNotBetween(Long value1, Long value2) {
            addCriterion("pwd_index not between", value1, value2, "pwdIndex");
            return (Criteria) this;
        }

        public Criteria andPwdsIsNull() {
            addCriterion("pwds is null");
            return (Criteria) this;
        }

        public Criteria andPwdsIsNotNull() {
            addCriterion("pwds is not null");
            return (Criteria) this;
        }

        public Criteria andPwdsEqualTo(String value) {
            addCriterion("pwds =", value, "pwds");
            return (Criteria) this;
        }

        public Criteria andPwdsNotEqualTo(String value) {
            addCriterion("pwds <>", value, "pwds");
            return (Criteria) this;
        }

        public Criteria andPwdsGreaterThan(String value) {
            addCriterion("pwds >", value, "pwds");
            return (Criteria) this;
        }

        public Criteria andPwdsGreaterThanOrEqualTo(String value) {
            addCriterion("pwds >=", value, "pwds");
            return (Criteria) this;
        }

        public Criteria andPwdsLessThan(String value) {
            addCriterion("pwds <", value, "pwds");
            return (Criteria) this;
        }

        public Criteria andPwdsLessThanOrEqualTo(String value) {
            addCriterion("pwds <=", value, "pwds");
            return (Criteria) this;
        }

        public Criteria andPwdsLike(String value) {
            addCriterion("pwds like", value, "pwds");
            return (Criteria) this;
        }

        public Criteria andPwdsNotLike(String value) {
            addCriterion("pwds not like", value, "pwds");
            return (Criteria) this;
        }

        public Criteria andPwdsIn(List<String> values) {
            addCriterion("pwds in", values, "pwds");
            return (Criteria) this;
        }

        public Criteria andPwdsNotIn(List<String> values) {
            addCriterion("pwds not in", values, "pwds");
            return (Criteria) this;
        }

        public Criteria andPwdsBetween(String value1, String value2) {
            addCriterion("pwds between", value1, value2, "pwds");
            return (Criteria) this;
        }

        public Criteria andPwdsNotBetween(String value1, String value2) {
            addCriterion("pwds not between", value1, value2, "pwds");
            return (Criteria) this;
        }

        public Criteria andUseCountTimesYesterdayIsNull() {
            addCriterion("use_count_times_yesterday is null");
            return (Criteria) this;
        }

        public Criteria andUseCountTimesYesterdayIsNotNull() {
            addCriterion("use_count_times_yesterday is not null");
            return (Criteria) this;
        }

        public Criteria andUseCountTimesYesterdayEqualTo(Long value) {
            addCriterion("use_count_times_yesterday =", value, "useCountTimesYesterday");
            return (Criteria) this;
        }

        public Criteria andUseCountTimesYesterdayNotEqualTo(Long value) {
            addCriterion("use_count_times_yesterday <>", value, "useCountTimesYesterday");
            return (Criteria) this;
        }

        public Criteria andUseCountTimesYesterdayGreaterThan(Long value) {
            addCriterion("use_count_times_yesterday >", value, "useCountTimesYesterday");
            return (Criteria) this;
        }

        public Criteria andUseCountTimesYesterdayGreaterThanOrEqualTo(Long value) {
            addCriterion("use_count_times_yesterday >=", value, "useCountTimesYesterday");
            return (Criteria) this;
        }

        public Criteria andUseCountTimesYesterdayLessThan(Long value) {
            addCriterion("use_count_times_yesterday <", value, "useCountTimesYesterday");
            return (Criteria) this;
        }

        public Criteria andUseCountTimesYesterdayLessThanOrEqualTo(Long value) {
            addCriterion("use_count_times_yesterday <=", value, "useCountTimesYesterday");
            return (Criteria) this;
        }

        public Criteria andUseCountTimesYesterdayIn(List<Long> values) {
            addCriterion("use_count_times_yesterday in", values, "useCountTimesYesterday");
            return (Criteria) this;
        }

        public Criteria andUseCountTimesYesterdayNotIn(List<Long> values) {
            addCriterion("use_count_times_yesterday not in", values, "useCountTimesYesterday");
            return (Criteria) this;
        }

        public Criteria andUseCountTimesYesterdayBetween(Long value1, Long value2) {
            addCriterion("use_count_times_yesterday between", value1, value2, "useCountTimesYesterday");
            return (Criteria) this;
        }

        public Criteria andUseCountTimesYesterdayNotBetween(Long value1, Long value2) {
            addCriterion("use_count_times_yesterday not between", value1, value2, "useCountTimesYesterday");
            return (Criteria) this;
        }

        public Criteria andTotalAmountYesterdayIsNull() {
            addCriterion("total_amount_yesterday is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountYesterdayIsNotNull() {
            addCriterion("total_amount_yesterday is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountYesterdayEqualTo(Long value) {
            addCriterion("total_amount_yesterday =", value, "totalAmountYesterday");
            return (Criteria) this;
        }

        public Criteria andTotalAmountYesterdayNotEqualTo(Long value) {
            addCriterion("total_amount_yesterday <>", value, "totalAmountYesterday");
            return (Criteria) this;
        }

        public Criteria andTotalAmountYesterdayGreaterThan(Long value) {
            addCriterion("total_amount_yesterday >", value, "totalAmountYesterday");
            return (Criteria) this;
        }

        public Criteria andTotalAmountYesterdayGreaterThanOrEqualTo(Long value) {
            addCriterion("total_amount_yesterday >=", value, "totalAmountYesterday");
            return (Criteria) this;
        }

        public Criteria andTotalAmountYesterdayLessThan(Long value) {
            addCriterion("total_amount_yesterday <", value, "totalAmountYesterday");
            return (Criteria) this;
        }

        public Criteria andTotalAmountYesterdayLessThanOrEqualTo(Long value) {
            addCriterion("total_amount_yesterday <=", value, "totalAmountYesterday");
            return (Criteria) this;
        }

        public Criteria andTotalAmountYesterdayIn(List<Long> values) {
            addCriterion("total_amount_yesterday in", values, "totalAmountYesterday");
            return (Criteria) this;
        }

        public Criteria andTotalAmountYesterdayNotIn(List<Long> values) {
            addCriterion("total_amount_yesterday not in", values, "totalAmountYesterday");
            return (Criteria) this;
        }

        public Criteria andTotalAmountYesterdayBetween(Long value1, Long value2) {
            addCriterion("total_amount_yesterday between", value1, value2, "totalAmountYesterday");
            return (Criteria) this;
        }

        public Criteria andTotalAmountYesterdayNotBetween(Long value1, Long value2) {
            addCriterion("total_amount_yesterday not between", value1, value2, "totalAmountYesterday");
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

        public Criteria andPwdModeIsNull() {
            addCriterion("pwd_mode is null");
            return (Criteria) this;
        }

        public Criteria andPwdModeIsNotNull() {
            addCriterion("pwd_mode is not null");
            return (Criteria) this;
        }

        public Criteria andPwdModeEqualTo(Long value) {
            addCriterion("pwd_mode =", value, "pwdMode");
            return (Criteria) this;
        }

        public Criteria andPwdModeNotEqualTo(Long value) {
            addCriterion("pwd_mode <>", value, "pwdMode");
            return (Criteria) this;
        }

        public Criteria andPwdModeGreaterThan(Long value) {
            addCriterion("pwd_mode >", value, "pwdMode");
            return (Criteria) this;
        }

        public Criteria andPwdModeGreaterThanOrEqualTo(Long value) {
            addCriterion("pwd_mode >=", value, "pwdMode");
            return (Criteria) this;
        }

        public Criteria andPwdModeLessThan(Long value) {
            addCriterion("pwd_mode <", value, "pwdMode");
            return (Criteria) this;
        }

        public Criteria andPwdModeLessThanOrEqualTo(Long value) {
            addCriterion("pwd_mode <=", value, "pwdMode");
            return (Criteria) this;
        }

        public Criteria andPwdModeIn(List<Long> values) {
            addCriterion("pwd_mode in", values, "pwdMode");
            return (Criteria) this;
        }

        public Criteria andPwdModeNotIn(List<Long> values) {
            addCriterion("pwd_mode not in", values, "pwdMode");
            return (Criteria) this;
        }

        public Criteria andPwdModeBetween(Long value1, Long value2) {
            addCriterion("pwd_mode between", value1, value2, "pwdMode");
            return (Criteria) this;
        }

        public Criteria andPwdModeNotBetween(Long value1, Long value2) {
            addCriterion("pwd_mode not between", value1, value2, "pwdMode");
            return (Criteria) this;
        }

        public Criteria andRefactorIdxIsNull() {
            addCriterion("refactor_idx is null");
            return (Criteria) this;
        }

        public Criteria andRefactorIdxIsNotNull() {
            addCriterion("refactor_idx is not null");
            return (Criteria) this;
        }

        public Criteria andRefactorIdxEqualTo(String value) {
            addCriterion("refactor_idx =", value, "refactorIdx");
            return (Criteria) this;
        }

        public Criteria andRefactorIdxNotEqualTo(String value) {
            addCriterion("refactor_idx <>", value, "refactorIdx");
            return (Criteria) this;
        }

        public Criteria andRefactorIdxGreaterThan(String value) {
            addCriterion("refactor_idx >", value, "refactorIdx");
            return (Criteria) this;
        }

        public Criteria andRefactorIdxGreaterThanOrEqualTo(String value) {
            addCriterion("refactor_idx >=", value, "refactorIdx");
            return (Criteria) this;
        }

        public Criteria andRefactorIdxLessThan(String value) {
            addCriterion("refactor_idx <", value, "refactorIdx");
            return (Criteria) this;
        }

        public Criteria andRefactorIdxLessThanOrEqualTo(String value) {
            addCriterion("refactor_idx <=", value, "refactorIdx");
            return (Criteria) this;
        }

        public Criteria andRefactorIdxLike(String value) {
            addCriterion("refactor_idx like", value, "refactorIdx");
            return (Criteria) this;
        }

        public Criteria andRefactorIdxNotLike(String value) {
            addCriterion("refactor_idx not like", value, "refactorIdx");
            return (Criteria) this;
        }

        public Criteria andRefactorIdxIn(List<String> values) {
            addCriterion("refactor_idx in", values, "refactorIdx");
            return (Criteria) this;
        }

        public Criteria andRefactorIdxNotIn(List<String> values) {
            addCriterion("refactor_idx not in", values, "refactorIdx");
            return (Criteria) this;
        }

        public Criteria andRefactorIdxBetween(String value1, String value2) {
            addCriterion("refactor_idx between", value1, value2, "refactorIdx");
            return (Criteria) this;
        }

        public Criteria andRefactorIdxNotBetween(String value1, String value2) {
            addCriterion("refactor_idx not between", value1, value2, "refactorIdx");
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

        public Criteria andCreateDateTimeIsNull() {
            addCriterion("create_date_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeIsNotNull() {
            addCriterion("create_date_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeEqualTo(Date value) {
            addCriterion("create_date_time =", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeNotEqualTo(Date value) {
            addCriterion("create_date_time <>", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeGreaterThan(Date value) {
            addCriterion("create_date_time >", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date_time >=", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeLessThan(Date value) {
            addCriterion("create_date_time <", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_date_time <=", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeIn(List<Date> values) {
            addCriterion("create_date_time in", values, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeNotIn(List<Date> values) {
            addCriterion("create_date_time not in", values, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeBetween(Date value1, Date value2) {
            addCriterion("create_date_time between", value1, value2, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_date_time not between", value1, value2, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeIsNull() {
            addCriterion("update_date_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeIsNotNull() {
            addCriterion("update_date_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeEqualTo(Date value) {
            addCriterion("update_date_time =", value, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeNotEqualTo(Date value) {
            addCriterion("update_date_time <>", value, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeGreaterThan(Date value) {
            addCriterion("update_date_time >", value, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date_time >=", value, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeLessThan(Date value) {
            addCriterion("update_date_time <", value, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_date_time <=", value, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeIn(List<Date> values) {
            addCriterion("update_date_time in", values, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeNotIn(List<Date> values) {
            addCriterion("update_date_time not in", values, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeBetween(Date value1, Date value2) {
            addCriterion("update_date_time between", value1, value2, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateDateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_date_time not between", value1, value2, "updateDateTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeIsNull() {
            addCriterion("last_use_time is null");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeIsNotNull() {
            addCriterion("last_use_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeEqualTo(Date value) {
            addCriterion("last_use_time =", value, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeNotEqualTo(Date value) {
            addCriterion("last_use_time <>", value, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeGreaterThan(Date value) {
            addCriterion("last_use_time >", value, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_use_time >=", value, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeLessThan(Date value) {
            addCriterion("last_use_time <", value, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_use_time <=", value, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeIn(List<Date> values) {
            addCriterion("last_use_time in", values, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeNotIn(List<Date> values) {
            addCriterion("last_use_time not in", values, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeBetween(Date value1, Date value2) {
            addCriterion("last_use_time between", value1, value2, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_use_time not between", value1, value2, "lastUseTime");
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
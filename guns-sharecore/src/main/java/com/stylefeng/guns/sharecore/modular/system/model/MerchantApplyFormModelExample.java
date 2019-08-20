package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MerchantApplyFormModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerchantApplyFormModelExample() {
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

        public Criteria andPersonNameIsNull() {
            addCriterion("person_name is null");
            return (Criteria) this;
        }

        public Criteria andPersonNameIsNotNull() {
            addCriterion("person_name is not null");
            return (Criteria) this;
        }

        public Criteria andPersonNameEqualTo(String value) {
            addCriterion("person_name =", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotEqualTo(String value) {
            addCriterion("person_name <>", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThan(String value) {
            addCriterion("person_name >", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThanOrEqualTo(String value) {
            addCriterion("person_name >=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThan(String value) {
            addCriterion("person_name <", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThanOrEqualTo(String value) {
            addCriterion("person_name <=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLike(String value) {
            addCriterion("person_name like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotLike(String value) {
            addCriterion("person_name not like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameIn(List<String> values) {
            addCriterion("person_name in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotIn(List<String> values) {
            addCriterion("person_name not in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameBetween(String value1, String value2) {
            addCriterion("person_name between", value1, value2, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotBetween(String value1, String value2) {
            addCriterion("person_name not between", value1, value2, "personName");
            return (Criteria) this;
        }

        public Criteria andTelNoIsNull() {
            addCriterion("tel_no is null");
            return (Criteria) this;
        }

        public Criteria andTelNoIsNotNull() {
            addCriterion("tel_no is not null");
            return (Criteria) this;
        }

        public Criteria andTelNoEqualTo(String value) {
            addCriterion("tel_no =", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoNotEqualTo(String value) {
            addCriterion("tel_no <>", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoGreaterThan(String value) {
            addCriterion("tel_no >", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoGreaterThanOrEqualTo(String value) {
            addCriterion("tel_no >=", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoLessThan(String value) {
            addCriterion("tel_no <", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoLessThanOrEqualTo(String value) {
            addCriterion("tel_no <=", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoLike(String value) {
            addCriterion("tel_no like", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoNotLike(String value) {
            addCriterion("tel_no not like", value, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoIn(List<String> values) {
            addCriterion("tel_no in", values, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoNotIn(List<String> values) {
            addCriterion("tel_no not in", values, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoBetween(String value1, String value2) {
            addCriterion("tel_no between", value1, value2, "telNo");
            return (Criteria) this;
        }

        public Criteria andTelNoNotBetween(String value1, String value2) {
            addCriterion("tel_no not between", value1, value2, "telNo");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andZoneIsNull() {
            addCriterion("zone is null");
            return (Criteria) this;
        }

        public Criteria andZoneIsNotNull() {
            addCriterion("zone is not null");
            return (Criteria) this;
        }

        public Criteria andZoneEqualTo(String value) {
            addCriterion("zone =", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotEqualTo(String value) {
            addCriterion("zone <>", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneGreaterThan(String value) {
            addCriterion("zone >", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneGreaterThanOrEqualTo(String value) {
            addCriterion("zone >=", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneLessThan(String value) {
            addCriterion("zone <", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneLessThanOrEqualTo(String value) {
            addCriterion("zone <=", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneLike(String value) {
            addCriterion("zone like", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotLike(String value) {
            addCriterion("zone not like", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneIn(List<String> values) {
            addCriterion("zone in", values, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotIn(List<String> values) {
            addCriterion("zone not in", values, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneBetween(String value1, String value2) {
            addCriterion("zone between", value1, value2, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotBetween(String value1, String value2) {
            addCriterion("zone not between", value1, value2, "zone");
            return (Criteria) this;
        }

        public Criteria andAgentsZoneIsNull() {
            addCriterion("agents_zone is null");
            return (Criteria) this;
        }

        public Criteria andAgentsZoneIsNotNull() {
            addCriterion("agents_zone is not null");
            return (Criteria) this;
        }

        public Criteria andAgentsZoneEqualTo(String value) {
            addCriterion("agents_zone =", value, "agentsZone");
            return (Criteria) this;
        }

        public Criteria andAgentsZoneNotEqualTo(String value) {
            addCriterion("agents_zone <>", value, "agentsZone");
            return (Criteria) this;
        }

        public Criteria andAgentsZoneGreaterThan(String value) {
            addCriterion("agents_zone >", value, "agentsZone");
            return (Criteria) this;
        }

        public Criteria andAgentsZoneGreaterThanOrEqualTo(String value) {
            addCriterion("agents_zone >=", value, "agentsZone");
            return (Criteria) this;
        }

        public Criteria andAgentsZoneLessThan(String value) {
            addCriterion("agents_zone <", value, "agentsZone");
            return (Criteria) this;
        }

        public Criteria andAgentsZoneLessThanOrEqualTo(String value) {
            addCriterion("agents_zone <=", value, "agentsZone");
            return (Criteria) this;
        }

        public Criteria andAgentsZoneLike(String value) {
            addCriterion("agents_zone like", value, "agentsZone");
            return (Criteria) this;
        }

        public Criteria andAgentsZoneNotLike(String value) {
            addCriterion("agents_zone not like", value, "agentsZone");
            return (Criteria) this;
        }

        public Criteria andAgentsZoneIn(List<String> values) {
            addCriterion("agents_zone in", values, "agentsZone");
            return (Criteria) this;
        }

        public Criteria andAgentsZoneNotIn(List<String> values) {
            addCriterion("agents_zone not in", values, "agentsZone");
            return (Criteria) this;
        }

        public Criteria andAgentsZoneBetween(String value1, String value2) {
            addCriterion("agents_zone between", value1, value2, "agentsZone");
            return (Criteria) this;
        }

        public Criteria andAgentsZoneNotBetween(String value1, String value2) {
            addCriterion("agents_zone not between", value1, value2, "agentsZone");
            return (Criteria) this;
        }

        public Criteria andThrowDevChannelIsNull() {
            addCriterion("throw_dev_channel is null");
            return (Criteria) this;
        }

        public Criteria andThrowDevChannelIsNotNull() {
            addCriterion("throw_dev_channel is not null");
            return (Criteria) this;
        }

        public Criteria andThrowDevChannelEqualTo(String value) {
            addCriterion("throw_dev_channel =", value, "throwDevChannel");
            return (Criteria) this;
        }

        public Criteria andThrowDevChannelNotEqualTo(String value) {
            addCriterion("throw_dev_channel <>", value, "throwDevChannel");
            return (Criteria) this;
        }

        public Criteria andThrowDevChannelGreaterThan(String value) {
            addCriterion("throw_dev_channel >", value, "throwDevChannel");
            return (Criteria) this;
        }

        public Criteria andThrowDevChannelGreaterThanOrEqualTo(String value) {
            addCriterion("throw_dev_channel >=", value, "throwDevChannel");
            return (Criteria) this;
        }

        public Criteria andThrowDevChannelLessThan(String value) {
            addCriterion("throw_dev_channel <", value, "throwDevChannel");
            return (Criteria) this;
        }

        public Criteria andThrowDevChannelLessThanOrEqualTo(String value) {
            addCriterion("throw_dev_channel <=", value, "throwDevChannel");
            return (Criteria) this;
        }

        public Criteria andThrowDevChannelLike(String value) {
            addCriterion("throw_dev_channel like", value, "throwDevChannel");
            return (Criteria) this;
        }

        public Criteria andThrowDevChannelNotLike(String value) {
            addCriterion("throw_dev_channel not like", value, "throwDevChannel");
            return (Criteria) this;
        }

        public Criteria andThrowDevChannelIn(List<String> values) {
            addCriterion("throw_dev_channel in", values, "throwDevChannel");
            return (Criteria) this;
        }

        public Criteria andThrowDevChannelNotIn(List<String> values) {
            addCriterion("throw_dev_channel not in", values, "throwDevChannel");
            return (Criteria) this;
        }

        public Criteria andThrowDevChannelBetween(String value1, String value2) {
            addCriterion("throw_dev_channel between", value1, value2, "throwDevChannel");
            return (Criteria) this;
        }

        public Criteria andThrowDevChannelNotBetween(String value1, String value2) {
            addCriterion("throw_dev_channel not between", value1, value2, "throwDevChannel");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIsNull() {
            addCriterion("apply_type is null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIsNotNull() {
            addCriterion("apply_type is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeEqualTo(Long value) {
            addCriterion("apply_type =", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotEqualTo(Long value) {
            addCriterion("apply_type <>", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeGreaterThan(Long value) {
            addCriterion("apply_type >", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("apply_type >=", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLessThan(Long value) {
            addCriterion("apply_type <", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLessThanOrEqualTo(Long value) {
            addCriterion("apply_type <=", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIn(List<Long> values) {
            addCriterion("apply_type in", values, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotIn(List<Long> values) {
            addCriterion("apply_type not in", values, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeBetween(Long value1, Long value2) {
            addCriterion("apply_type between", value1, value2, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotBetween(Long value1, Long value2) {
            addCriterion("apply_type not between", value1, value2, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeCnIsNull() {
            addCriterion("apply_type_cn is null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeCnIsNotNull() {
            addCriterion("apply_type_cn is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeCnEqualTo(String value) {
            addCriterion("apply_type_cn =", value, "applyTypeCn");
            return (Criteria) this;
        }

        public Criteria andApplyTypeCnNotEqualTo(String value) {
            addCriterion("apply_type_cn <>", value, "applyTypeCn");
            return (Criteria) this;
        }

        public Criteria andApplyTypeCnGreaterThan(String value) {
            addCriterion("apply_type_cn >", value, "applyTypeCn");
            return (Criteria) this;
        }

        public Criteria andApplyTypeCnGreaterThanOrEqualTo(String value) {
            addCriterion("apply_type_cn >=", value, "applyTypeCn");
            return (Criteria) this;
        }

        public Criteria andApplyTypeCnLessThan(String value) {
            addCriterion("apply_type_cn <", value, "applyTypeCn");
            return (Criteria) this;
        }

        public Criteria andApplyTypeCnLessThanOrEqualTo(String value) {
            addCriterion("apply_type_cn <=", value, "applyTypeCn");
            return (Criteria) this;
        }

        public Criteria andApplyTypeCnLike(String value) {
            addCriterion("apply_type_cn like", value, "applyTypeCn");
            return (Criteria) this;
        }

        public Criteria andApplyTypeCnNotLike(String value) {
            addCriterion("apply_type_cn not like", value, "applyTypeCn");
            return (Criteria) this;
        }

        public Criteria andApplyTypeCnIn(List<String> values) {
            addCriterion("apply_type_cn in", values, "applyTypeCn");
            return (Criteria) this;
        }

        public Criteria andApplyTypeCnNotIn(List<String> values) {
            addCriterion("apply_type_cn not in", values, "applyTypeCn");
            return (Criteria) this;
        }

        public Criteria andApplyTypeCnBetween(String value1, String value2) {
            addCriterion("apply_type_cn between", value1, value2, "applyTypeCn");
            return (Criteria) this;
        }

        public Criteria andApplyTypeCnNotBetween(String value1, String value2) {
            addCriterion("apply_type_cn not between", value1, value2, "applyTypeCn");
            return (Criteria) this;
        }

        public Criteria andDoStatusIsNull() {
            addCriterion("do_status is null");
            return (Criteria) this;
        }

        public Criteria andDoStatusIsNotNull() {
            addCriterion("do_status is not null");
            return (Criteria) this;
        }

        public Criteria andDoStatusEqualTo(Long value) {
            addCriterion("do_status =", value, "doStatus");
            return (Criteria) this;
        }

        public Criteria andDoStatusNotEqualTo(Long value) {
            addCriterion("do_status <>", value, "doStatus");
            return (Criteria) this;
        }

        public Criteria andDoStatusGreaterThan(Long value) {
            addCriterion("do_status >", value, "doStatus");
            return (Criteria) this;
        }

        public Criteria andDoStatusGreaterThanOrEqualTo(Long value) {
            addCriterion("do_status >=", value, "doStatus");
            return (Criteria) this;
        }

        public Criteria andDoStatusLessThan(Long value) {
            addCriterion("do_status <", value, "doStatus");
            return (Criteria) this;
        }

        public Criteria andDoStatusLessThanOrEqualTo(Long value) {
            addCriterion("do_status <=", value, "doStatus");
            return (Criteria) this;
        }

        public Criteria andDoStatusIn(List<Long> values) {
            addCriterion("do_status in", values, "doStatus");
            return (Criteria) this;
        }

        public Criteria andDoStatusNotIn(List<Long> values) {
            addCriterion("do_status not in", values, "doStatus");
            return (Criteria) this;
        }

        public Criteria andDoStatusBetween(Long value1, Long value2) {
            addCriterion("do_status between", value1, value2, "doStatus");
            return (Criteria) this;
        }

        public Criteria andDoStatusNotBetween(Long value1, Long value2) {
            addCriterion("do_status not between", value1, value2, "doStatus");
            return (Criteria) this;
        }

        public Criteria andDoStatusCnIsNull() {
            addCriterion("do_status_cn is null");
            return (Criteria) this;
        }

        public Criteria andDoStatusCnIsNotNull() {
            addCriterion("do_status_cn is not null");
            return (Criteria) this;
        }

        public Criteria andDoStatusCnEqualTo(String value) {
            addCriterion("do_status_cn =", value, "doStatusCn");
            return (Criteria) this;
        }

        public Criteria andDoStatusCnNotEqualTo(String value) {
            addCriterion("do_status_cn <>", value, "doStatusCn");
            return (Criteria) this;
        }

        public Criteria andDoStatusCnGreaterThan(String value) {
            addCriterion("do_status_cn >", value, "doStatusCn");
            return (Criteria) this;
        }

        public Criteria andDoStatusCnGreaterThanOrEqualTo(String value) {
            addCriterion("do_status_cn >=", value, "doStatusCn");
            return (Criteria) this;
        }

        public Criteria andDoStatusCnLessThan(String value) {
            addCriterion("do_status_cn <", value, "doStatusCn");
            return (Criteria) this;
        }

        public Criteria andDoStatusCnLessThanOrEqualTo(String value) {
            addCriterion("do_status_cn <=", value, "doStatusCn");
            return (Criteria) this;
        }

        public Criteria andDoStatusCnLike(String value) {
            addCriterion("do_status_cn like", value, "doStatusCn");
            return (Criteria) this;
        }

        public Criteria andDoStatusCnNotLike(String value) {
            addCriterion("do_status_cn not like", value, "doStatusCn");
            return (Criteria) this;
        }

        public Criteria andDoStatusCnIn(List<String> values) {
            addCriterion("do_status_cn in", values, "doStatusCn");
            return (Criteria) this;
        }

        public Criteria andDoStatusCnNotIn(List<String> values) {
            addCriterion("do_status_cn not in", values, "doStatusCn");
            return (Criteria) this;
        }

        public Criteria andDoStatusCnBetween(String value1, String value2) {
            addCriterion("do_status_cn between", value1, value2, "doStatusCn");
            return (Criteria) this;
        }

        public Criteria andDoStatusCnNotBetween(String value1, String value2) {
            addCriterion("do_status_cn not between", value1, value2, "doStatusCn");
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
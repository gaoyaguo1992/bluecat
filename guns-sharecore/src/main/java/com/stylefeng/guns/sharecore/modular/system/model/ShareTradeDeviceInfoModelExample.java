package com.stylefeng.guns.sharecore.modular.system.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShareTradeDeviceInfoModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShareTradeDeviceInfoModelExample() {
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

        public Criteria andTradeIdIsNull() {
            addCriterion("trade_id is null");
            return (Criteria) this;
        }

        public Criteria andTradeIdIsNotNull() {
            addCriterion("trade_id is not null");
            return (Criteria) this;
        }

        public Criteria andTradeIdEqualTo(Long value) {
            addCriterion("trade_id =", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotEqualTo(Long value) {
            addCriterion("trade_id <>", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdGreaterThan(Long value) {
            addCriterion("trade_id >", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("trade_id >=", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdLessThan(Long value) {
            addCriterion("trade_id <", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdLessThanOrEqualTo(Long value) {
            addCriterion("trade_id <=", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdIn(List<Long> values) {
            addCriterion("trade_id in", values, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotIn(List<Long> values) {
            addCriterion("trade_id not in", values, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdBetween(Long value1, Long value2) {
            addCriterion("trade_id between", value1, value2, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotBetween(Long value1, Long value2) {
            addCriterion("trade_id not between", value1, value2, "tradeId");
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

        public Criteria andDeviceTypeIdIsNull() {
            addCriterion("device_type_id is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIdIsNotNull() {
            addCriterion("device_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIdEqualTo(Integer value) {
            addCriterion("device_type_id =", value, "deviceTypeId");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIdNotEqualTo(Integer value) {
            addCriterion("device_type_id <>", value, "deviceTypeId");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIdGreaterThan(Integer value) {
            addCriterion("device_type_id >", value, "deviceTypeId");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("device_type_id >=", value, "deviceTypeId");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIdLessThan(Integer value) {
            addCriterion("device_type_id <", value, "deviceTypeId");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("device_type_id <=", value, "deviceTypeId");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIdIn(List<Integer> values) {
            addCriterion("device_type_id in", values, "deviceTypeId");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIdNotIn(List<Integer> values) {
            addCriterion("device_type_id not in", values, "deviceTypeId");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("device_type_id between", value1, value2, "deviceTypeId");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("device_type_id not between", value1, value2, "deviceTypeId");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameIsNull() {
            addCriterion("device_type_name is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameIsNotNull() {
            addCriterion("device_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameEqualTo(String value) {
            addCriterion("device_type_name =", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameNotEqualTo(String value) {
            addCriterion("device_type_name <>", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameGreaterThan(String value) {
            addCriterion("device_type_name >", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("device_type_name >=", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameLessThan(String value) {
            addCriterion("device_type_name <", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameLessThanOrEqualTo(String value) {
            addCriterion("device_type_name <=", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameLike(String value) {
            addCriterion("device_type_name like", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameNotLike(String value) {
            addCriterion("device_type_name not like", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameIn(List<String> values) {
            addCriterion("device_type_name in", values, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameNotIn(List<String> values) {
            addCriterion("device_type_name not in", values, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameBetween(String value1, String value2) {
            addCriterion("device_type_name between", value1, value2, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameNotBetween(String value1, String value2) {
            addCriterion("device_type_name not between", value1, value2, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceStatusIsNull() {
            addCriterion("device_status is null");
            return (Criteria) this;
        }

        public Criteria andDeviceStatusIsNotNull() {
            addCriterion("device_status is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceStatusEqualTo(Integer value) {
            addCriterion("device_status =", value, "deviceStatus");
            return (Criteria) this;
        }

        public Criteria andDeviceStatusNotEqualTo(Integer value) {
            addCriterion("device_status <>", value, "deviceStatus");
            return (Criteria) this;
        }

        public Criteria andDeviceStatusGreaterThan(Integer value) {
            addCriterion("device_status >", value, "deviceStatus");
            return (Criteria) this;
        }

        public Criteria andDeviceStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("device_status >=", value, "deviceStatus");
            return (Criteria) this;
        }

        public Criteria andDeviceStatusLessThan(Integer value) {
            addCriterion("device_status <", value, "deviceStatus");
            return (Criteria) this;
        }

        public Criteria andDeviceStatusLessThanOrEqualTo(Integer value) {
            addCriterion("device_status <=", value, "deviceStatus");
            return (Criteria) this;
        }

        public Criteria andDeviceStatusIn(List<Integer> values) {
            addCriterion("device_status in", values, "deviceStatus");
            return (Criteria) this;
        }

        public Criteria andDeviceStatusNotIn(List<Integer> values) {
            addCriterion("device_status not in", values, "deviceStatus");
            return (Criteria) this;
        }

        public Criteria andDeviceStatusBetween(Integer value1, Integer value2) {
            addCriterion("device_status between", value1, value2, "deviceStatus");
            return (Criteria) this;
        }

        public Criteria andDeviceStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("device_status not between", value1, value2, "deviceStatus");
            return (Criteria) this;
        }

        public Criteria andOnlineDatetimeIsNull() {
            addCriterion("online_datetime is null");
            return (Criteria) this;
        }

        public Criteria andOnlineDatetimeIsNotNull() {
            addCriterion("online_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineDatetimeEqualTo(Date value) {
            addCriterion("online_datetime =", value, "onlineDatetime");
            return (Criteria) this;
        }

        public Criteria andOnlineDatetimeNotEqualTo(Date value) {
            addCriterion("online_datetime <>", value, "onlineDatetime");
            return (Criteria) this;
        }

        public Criteria andOnlineDatetimeGreaterThan(Date value) {
            addCriterion("online_datetime >", value, "onlineDatetime");
            return (Criteria) this;
        }

        public Criteria andOnlineDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("online_datetime >=", value, "onlineDatetime");
            return (Criteria) this;
        }

        public Criteria andOnlineDatetimeLessThan(Date value) {
            addCriterion("online_datetime <", value, "onlineDatetime");
            return (Criteria) this;
        }

        public Criteria andOnlineDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("online_datetime <=", value, "onlineDatetime");
            return (Criteria) this;
        }

        public Criteria andOnlineDatetimeIn(List<Date> values) {
            addCriterion("online_datetime in", values, "onlineDatetime");
            return (Criteria) this;
        }

        public Criteria andOnlineDatetimeNotIn(List<Date> values) {
            addCriterion("online_datetime not in", values, "onlineDatetime");
            return (Criteria) this;
        }

        public Criteria andOnlineDatetimeBetween(Date value1, Date value2) {
            addCriterion("online_datetime between", value1, value2, "onlineDatetime");
            return (Criteria) this;
        }

        public Criteria andOnlineDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("online_datetime not between", value1, value2, "onlineDatetime");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantCnIsNull() {
            addCriterion("online_merchant_cn is null");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantCnIsNotNull() {
            addCriterion("online_merchant_cn is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantCnEqualTo(String value) {
            addCriterion("online_merchant_cn =", value, "onlineMerchantCn");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantCnNotEqualTo(String value) {
            addCriterion("online_merchant_cn <>", value, "onlineMerchantCn");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantCnGreaterThan(String value) {
            addCriterion("online_merchant_cn >", value, "onlineMerchantCn");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantCnGreaterThanOrEqualTo(String value) {
            addCriterion("online_merchant_cn >=", value, "onlineMerchantCn");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantCnLessThan(String value) {
            addCriterion("online_merchant_cn <", value, "onlineMerchantCn");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantCnLessThanOrEqualTo(String value) {
            addCriterion("online_merchant_cn <=", value, "onlineMerchantCn");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantCnLike(String value) {
            addCriterion("online_merchant_cn like", value, "onlineMerchantCn");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantCnNotLike(String value) {
            addCriterion("online_merchant_cn not like", value, "onlineMerchantCn");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantCnIn(List<String> values) {
            addCriterion("online_merchant_cn in", values, "onlineMerchantCn");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantCnNotIn(List<String> values) {
            addCriterion("online_merchant_cn not in", values, "onlineMerchantCn");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantCnBetween(String value1, String value2) {
            addCriterion("online_merchant_cn between", value1, value2, "onlineMerchantCn");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantCnNotBetween(String value1, String value2) {
            addCriterion("online_merchant_cn not between", value1, value2, "onlineMerchantCn");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantIdIsNull() {
            addCriterion("online_merchant_id is null");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantIdIsNotNull() {
            addCriterion("online_merchant_id is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantIdEqualTo(Long value) {
            addCriterion("online_merchant_id =", value, "onlineMerchantId");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantIdNotEqualTo(Long value) {
            addCriterion("online_merchant_id <>", value, "onlineMerchantId");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantIdGreaterThan(Long value) {
            addCriterion("online_merchant_id >", value, "onlineMerchantId");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantIdGreaterThanOrEqualTo(Long value) {
            addCriterion("online_merchant_id >=", value, "onlineMerchantId");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantIdLessThan(Long value) {
            addCriterion("online_merchant_id <", value, "onlineMerchantId");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantIdLessThanOrEqualTo(Long value) {
            addCriterion("online_merchant_id <=", value, "onlineMerchantId");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantIdIn(List<Long> values) {
            addCriterion("online_merchant_id in", values, "onlineMerchantId");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantIdNotIn(List<Long> values) {
            addCriterion("online_merchant_id not in", values, "onlineMerchantId");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantIdBetween(Long value1, Long value2) {
            addCriterion("online_merchant_id between", value1, value2, "onlineMerchantId");
            return (Criteria) this;
        }

        public Criteria andOnlineMerchantIdNotBetween(Long value1, Long value2) {
            addCriterion("online_merchant_id not between", value1, value2, "onlineMerchantId");
            return (Criteria) this;
        }

        public Criteria andOnlineAddressIsNull() {
            addCriterion("online_address is null");
            return (Criteria) this;
        }

        public Criteria andOnlineAddressIsNotNull() {
            addCriterion("online_address is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineAddressEqualTo(String value) {
            addCriterion("online_address =", value, "onlineAddress");
            return (Criteria) this;
        }

        public Criteria andOnlineAddressNotEqualTo(String value) {
            addCriterion("online_address <>", value, "onlineAddress");
            return (Criteria) this;
        }

        public Criteria andOnlineAddressGreaterThan(String value) {
            addCriterion("online_address >", value, "onlineAddress");
            return (Criteria) this;
        }

        public Criteria andOnlineAddressGreaterThanOrEqualTo(String value) {
            addCriterion("online_address >=", value, "onlineAddress");
            return (Criteria) this;
        }

        public Criteria andOnlineAddressLessThan(String value) {
            addCriterion("online_address <", value, "onlineAddress");
            return (Criteria) this;
        }

        public Criteria andOnlineAddressLessThanOrEqualTo(String value) {
            addCriterion("online_address <=", value, "onlineAddress");
            return (Criteria) this;
        }

        public Criteria andOnlineAddressLike(String value) {
            addCriterion("online_address like", value, "onlineAddress");
            return (Criteria) this;
        }

        public Criteria andOnlineAddressNotLike(String value) {
            addCriterion("online_address not like", value, "onlineAddress");
            return (Criteria) this;
        }

        public Criteria andOnlineAddressIn(List<String> values) {
            addCriterion("online_address in", values, "onlineAddress");
            return (Criteria) this;
        }

        public Criteria andOnlineAddressNotIn(List<String> values) {
            addCriterion("online_address not in", values, "onlineAddress");
            return (Criteria) this;
        }

        public Criteria andOnlineAddressBetween(String value1, String value2) {
            addCriterion("online_address between", value1, value2, "onlineAddress");
            return (Criteria) this;
        }

        public Criteria andOnlineAddressNotBetween(String value1, String value2) {
            addCriterion("online_address not between", value1, value2, "onlineAddress");
            return (Criteria) this;
        }

        public Criteria andOnlineProvinceIsNull() {
            addCriterion("online_province is null");
            return (Criteria) this;
        }

        public Criteria andOnlineProvinceIsNotNull() {
            addCriterion("online_province is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineProvinceEqualTo(String value) {
            addCriterion("online_province =", value, "onlineProvince");
            return (Criteria) this;
        }

        public Criteria andOnlineProvinceNotEqualTo(String value) {
            addCriterion("online_province <>", value, "onlineProvince");
            return (Criteria) this;
        }

        public Criteria andOnlineProvinceGreaterThan(String value) {
            addCriterion("online_province >", value, "onlineProvince");
            return (Criteria) this;
        }

        public Criteria andOnlineProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("online_province >=", value, "onlineProvince");
            return (Criteria) this;
        }

        public Criteria andOnlineProvinceLessThan(String value) {
            addCriterion("online_province <", value, "onlineProvince");
            return (Criteria) this;
        }

        public Criteria andOnlineProvinceLessThanOrEqualTo(String value) {
            addCriterion("online_province <=", value, "onlineProvince");
            return (Criteria) this;
        }

        public Criteria andOnlineProvinceLike(String value) {
            addCriterion("online_province like", value, "onlineProvince");
            return (Criteria) this;
        }

        public Criteria andOnlineProvinceNotLike(String value) {
            addCriterion("online_province not like", value, "onlineProvince");
            return (Criteria) this;
        }

        public Criteria andOnlineProvinceIn(List<String> values) {
            addCriterion("online_province in", values, "onlineProvince");
            return (Criteria) this;
        }

        public Criteria andOnlineProvinceNotIn(List<String> values) {
            addCriterion("online_province not in", values, "onlineProvince");
            return (Criteria) this;
        }

        public Criteria andOnlineProvinceBetween(String value1, String value2) {
            addCriterion("online_province between", value1, value2, "onlineProvince");
            return (Criteria) this;
        }

        public Criteria andOnlineProvinceNotBetween(String value1, String value2) {
            addCriterion("online_province not between", value1, value2, "onlineProvince");
            return (Criteria) this;
        }

        public Criteria andOnlineXCoordinateIsNull() {
            addCriterion("online_x_coordinate is null");
            return (Criteria) this;
        }

        public Criteria andOnlineXCoordinateIsNotNull() {
            addCriterion("online_x_coordinate is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineXCoordinateEqualTo(BigDecimal value) {
            addCriterion("online_x_coordinate =", value, "onlineXCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineXCoordinateNotEqualTo(BigDecimal value) {
            addCriterion("online_x_coordinate <>", value, "onlineXCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineXCoordinateGreaterThan(BigDecimal value) {
            addCriterion("online_x_coordinate >", value, "onlineXCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineXCoordinateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("online_x_coordinate >=", value, "onlineXCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineXCoordinateLessThan(BigDecimal value) {
            addCriterion("online_x_coordinate <", value, "onlineXCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineXCoordinateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("online_x_coordinate <=", value, "onlineXCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineXCoordinateIn(List<BigDecimal> values) {
            addCriterion("online_x_coordinate in", values, "onlineXCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineXCoordinateNotIn(List<BigDecimal> values) {
            addCriterion("online_x_coordinate not in", values, "onlineXCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineXCoordinateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("online_x_coordinate between", value1, value2, "onlineXCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineXCoordinateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("online_x_coordinate not between", value1, value2, "onlineXCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineYCoordinateIsNull() {
            addCriterion("online_y_coordinate is null");
            return (Criteria) this;
        }

        public Criteria andOnlineYCoordinateIsNotNull() {
            addCriterion("online_y_coordinate is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineYCoordinateEqualTo(BigDecimal value) {
            addCriterion("online_y_coordinate =", value, "onlineYCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineYCoordinateNotEqualTo(BigDecimal value) {
            addCriterion("online_y_coordinate <>", value, "onlineYCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineYCoordinateGreaterThan(BigDecimal value) {
            addCriterion("online_y_coordinate >", value, "onlineYCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineYCoordinateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("online_y_coordinate >=", value, "onlineYCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineYCoordinateLessThan(BigDecimal value) {
            addCriterion("online_y_coordinate <", value, "onlineYCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineYCoordinateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("online_y_coordinate <=", value, "onlineYCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineYCoordinateIn(List<BigDecimal> values) {
            addCriterion("online_y_coordinate in", values, "onlineYCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineYCoordinateNotIn(List<BigDecimal> values) {
            addCriterion("online_y_coordinate not in", values, "onlineYCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineYCoordinateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("online_y_coordinate between", value1, value2, "onlineYCoordinate");
            return (Criteria) this;
        }

        public Criteria andOnlineYCoordinateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("online_y_coordinate not between", value1, value2, "onlineYCoordinate");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIdIsNull() {
            addCriterion("fee_type_id is null");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIdIsNotNull() {
            addCriterion("fee_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIdEqualTo(Integer value) {
            addCriterion("fee_type_id =", value, "feeTypeId");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIdNotEqualTo(Integer value) {
            addCriterion("fee_type_id <>", value, "feeTypeId");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIdGreaterThan(Integer value) {
            addCriterion("fee_type_id >", value, "feeTypeId");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fee_type_id >=", value, "feeTypeId");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIdLessThan(Integer value) {
            addCriterion("fee_type_id <", value, "feeTypeId");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("fee_type_id <=", value, "feeTypeId");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIdIn(List<Integer> values) {
            addCriterion("fee_type_id in", values, "feeTypeId");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIdNotIn(List<Integer> values) {
            addCriterion("fee_type_id not in", values, "feeTypeId");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("fee_type_id between", value1, value2, "feeTypeId");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fee_type_id not between", value1, value2, "feeTypeId");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNameIsNull() {
            addCriterion("fee_type_name is null");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNameIsNotNull() {
            addCriterion("fee_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNameEqualTo(String value) {
            addCriterion("fee_type_name =", value, "feeTypeName");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNameNotEqualTo(String value) {
            addCriterion("fee_type_name <>", value, "feeTypeName");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNameGreaterThan(String value) {
            addCriterion("fee_type_name >", value, "feeTypeName");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("fee_type_name >=", value, "feeTypeName");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNameLessThan(String value) {
            addCriterion("fee_type_name <", value, "feeTypeName");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNameLessThanOrEqualTo(String value) {
            addCriterion("fee_type_name <=", value, "feeTypeName");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNameLike(String value) {
            addCriterion("fee_type_name like", value, "feeTypeName");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNameNotLike(String value) {
            addCriterion("fee_type_name not like", value, "feeTypeName");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNameIn(List<String> values) {
            addCriterion("fee_type_name in", values, "feeTypeName");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNameNotIn(List<String> values) {
            addCriterion("fee_type_name not in", values, "feeTypeName");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNameBetween(String value1, String value2) {
            addCriterion("fee_type_name between", value1, value2, "feeTypeName");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNameNotBetween(String value1, String value2) {
            addCriterion("fee_type_name not between", value1, value2, "feeTypeName");
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

        public Criteria andFirstAmountIsNull() {
            addCriterion("first_amount is null");
            return (Criteria) this;
        }

        public Criteria andFirstAmountIsNotNull() {
            addCriterion("first_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFirstAmountEqualTo(BigDecimal value) {
            addCriterion("first_amount =", value, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountNotEqualTo(BigDecimal value) {
            addCriterion("first_amount <>", value, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountGreaterThan(BigDecimal value) {
            addCriterion("first_amount >", value, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("first_amount >=", value, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountLessThan(BigDecimal value) {
            addCriterion("first_amount <", value, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("first_amount <=", value, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountIn(List<BigDecimal> values) {
            addCriterion("first_amount in", values, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountNotIn(List<BigDecimal> values) {
            addCriterion("first_amount not in", values, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("first_amount between", value1, value2, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("first_amount not between", value1, value2, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstMinutesIsNull() {
            addCriterion("first_minutes is null");
            return (Criteria) this;
        }

        public Criteria andFirstMinutesIsNotNull() {
            addCriterion("first_minutes is not null");
            return (Criteria) this;
        }

        public Criteria andFirstMinutesEqualTo(Long value) {
            addCriterion("first_minutes =", value, "firstMinutes");
            return (Criteria) this;
        }

        public Criteria andFirstMinutesNotEqualTo(Long value) {
            addCriterion("first_minutes <>", value, "firstMinutes");
            return (Criteria) this;
        }

        public Criteria andFirstMinutesGreaterThan(Long value) {
            addCriterion("first_minutes >", value, "firstMinutes");
            return (Criteria) this;
        }

        public Criteria andFirstMinutesGreaterThanOrEqualTo(Long value) {
            addCriterion("first_minutes >=", value, "firstMinutes");
            return (Criteria) this;
        }

        public Criteria andFirstMinutesLessThan(Long value) {
            addCriterion("first_minutes <", value, "firstMinutes");
            return (Criteria) this;
        }

        public Criteria andFirstMinutesLessThanOrEqualTo(Long value) {
            addCriterion("first_minutes <=", value, "firstMinutes");
            return (Criteria) this;
        }

        public Criteria andFirstMinutesIn(List<Long> values) {
            addCriterion("first_minutes in", values, "firstMinutes");
            return (Criteria) this;
        }

        public Criteria andFirstMinutesNotIn(List<Long> values) {
            addCriterion("first_minutes not in", values, "firstMinutes");
            return (Criteria) this;
        }

        public Criteria andFirstMinutesBetween(Long value1, Long value2) {
            addCriterion("first_minutes between", value1, value2, "firstMinutes");
            return (Criteria) this;
        }

        public Criteria andFirstMinutesNotBetween(Long value1, Long value2) {
            addCriterion("first_minutes not between", value1, value2, "firstMinutes");
            return (Criteria) this;
        }

        public Criteria andAmountPerHourIsNull() {
            addCriterion("amount_per_hour is null");
            return (Criteria) this;
        }

        public Criteria andAmountPerHourIsNotNull() {
            addCriterion("amount_per_hour is not null");
            return (Criteria) this;
        }

        public Criteria andAmountPerHourEqualTo(BigDecimal value) {
            addCriterion("amount_per_hour =", value, "amountPerHour");
            return (Criteria) this;
        }

        public Criteria andAmountPerHourNotEqualTo(BigDecimal value) {
            addCriterion("amount_per_hour <>", value, "amountPerHour");
            return (Criteria) this;
        }

        public Criteria andAmountPerHourGreaterThan(BigDecimal value) {
            addCriterion("amount_per_hour >", value, "amountPerHour");
            return (Criteria) this;
        }

        public Criteria andAmountPerHourGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_per_hour >=", value, "amountPerHour");
            return (Criteria) this;
        }

        public Criteria andAmountPerHourLessThan(BigDecimal value) {
            addCriterion("amount_per_hour <", value, "amountPerHour");
            return (Criteria) this;
        }

        public Criteria andAmountPerHourLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_per_hour <=", value, "amountPerHour");
            return (Criteria) this;
        }

        public Criteria andAmountPerHourIn(List<BigDecimal> values) {
            addCriterion("amount_per_hour in", values, "amountPerHour");
            return (Criteria) this;
        }

        public Criteria andAmountPerHourNotIn(List<BigDecimal> values) {
            addCriterion("amount_per_hour not in", values, "amountPerHour");
            return (Criteria) this;
        }

        public Criteria andAmountPerHourBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_per_hour between", value1, value2, "amountPerHour");
            return (Criteria) this;
        }

        public Criteria andAmountPerHourNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_per_hour not between", value1, value2, "amountPerHour");
            return (Criteria) this;
        }

        public Criteria andAmountMax24hourIsNull() {
            addCriterion("amount_max_24hour is null");
            return (Criteria) this;
        }

        public Criteria andAmountMax24hourIsNotNull() {
            addCriterion("amount_max_24hour is not null");
            return (Criteria) this;
        }

        public Criteria andAmountMax24hourEqualTo(BigDecimal value) {
            addCriterion("amount_max_24hour =", value, "amountMax24hour");
            return (Criteria) this;
        }

        public Criteria andAmountMax24hourNotEqualTo(BigDecimal value) {
            addCriterion("amount_max_24hour <>", value, "amountMax24hour");
            return (Criteria) this;
        }

        public Criteria andAmountMax24hourGreaterThan(BigDecimal value) {
            addCriterion("amount_max_24hour >", value, "amountMax24hour");
            return (Criteria) this;
        }

        public Criteria andAmountMax24hourGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_max_24hour >=", value, "amountMax24hour");
            return (Criteria) this;
        }

        public Criteria andAmountMax24hourLessThan(BigDecimal value) {
            addCriterion("amount_max_24hour <", value, "amountMax24hour");
            return (Criteria) this;
        }

        public Criteria andAmountMax24hourLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_max_24hour <=", value, "amountMax24hour");
            return (Criteria) this;
        }

        public Criteria andAmountMax24hourIn(List<BigDecimal> values) {
            addCriterion("amount_max_24hour in", values, "amountMax24hour");
            return (Criteria) this;
        }

        public Criteria andAmountMax24hourNotIn(List<BigDecimal> values) {
            addCriterion("amount_max_24hour not in", values, "amountMax24hour");
            return (Criteria) this;
        }

        public Criteria andAmountMax24hourBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_max_24hour between", value1, value2, "amountMax24hour");
            return (Criteria) this;
        }

        public Criteria andAmountMax24hourNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_max_24hour not between", value1, value2, "amountMax24hour");
            return (Criteria) this;
        }

        public Criteria andFee1TypeIdIsNull() {
            addCriterion("fee1_type_id is null");
            return (Criteria) this;
        }

        public Criteria andFee1TypeIdIsNotNull() {
            addCriterion("fee1_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andFee1TypeIdEqualTo(Integer value) {
            addCriterion("fee1_type_id =", value, "fee1TypeId");
            return (Criteria) this;
        }

        public Criteria andFee1TypeIdNotEqualTo(Integer value) {
            addCriterion("fee1_type_id <>", value, "fee1TypeId");
            return (Criteria) this;
        }

        public Criteria andFee1TypeIdGreaterThan(Integer value) {
            addCriterion("fee1_type_id >", value, "fee1TypeId");
            return (Criteria) this;
        }

        public Criteria andFee1TypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fee1_type_id >=", value, "fee1TypeId");
            return (Criteria) this;
        }

        public Criteria andFee1TypeIdLessThan(Integer value) {
            addCriterion("fee1_type_id <", value, "fee1TypeId");
            return (Criteria) this;
        }

        public Criteria andFee1TypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("fee1_type_id <=", value, "fee1TypeId");
            return (Criteria) this;
        }

        public Criteria andFee1TypeIdIn(List<Integer> values) {
            addCriterion("fee1_type_id in", values, "fee1TypeId");
            return (Criteria) this;
        }

        public Criteria andFee1TypeIdNotIn(List<Integer> values) {
            addCriterion("fee1_type_id not in", values, "fee1TypeId");
            return (Criteria) this;
        }

        public Criteria andFee1TypeIdBetween(Integer value1, Integer value2) {
            addCriterion("fee1_type_id between", value1, value2, "fee1TypeId");
            return (Criteria) this;
        }

        public Criteria andFee1TypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fee1_type_id not between", value1, value2, "fee1TypeId");
            return (Criteria) this;
        }

        public Criteria andFee1TypeNameIsNull() {
            addCriterion("fee1_type_name is null");
            return (Criteria) this;
        }

        public Criteria andFee1TypeNameIsNotNull() {
            addCriterion("fee1_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andFee1TypeNameEqualTo(String value) {
            addCriterion("fee1_type_name =", value, "fee1TypeName");
            return (Criteria) this;
        }

        public Criteria andFee1TypeNameNotEqualTo(String value) {
            addCriterion("fee1_type_name <>", value, "fee1TypeName");
            return (Criteria) this;
        }

        public Criteria andFee1TypeNameGreaterThan(String value) {
            addCriterion("fee1_type_name >", value, "fee1TypeName");
            return (Criteria) this;
        }

        public Criteria andFee1TypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("fee1_type_name >=", value, "fee1TypeName");
            return (Criteria) this;
        }

        public Criteria andFee1TypeNameLessThan(String value) {
            addCriterion("fee1_type_name <", value, "fee1TypeName");
            return (Criteria) this;
        }

        public Criteria andFee1TypeNameLessThanOrEqualTo(String value) {
            addCriterion("fee1_type_name <=", value, "fee1TypeName");
            return (Criteria) this;
        }

        public Criteria andFee1TypeNameLike(String value) {
            addCriterion("fee1_type_name like", value, "fee1TypeName");
            return (Criteria) this;
        }

        public Criteria andFee1TypeNameNotLike(String value) {
            addCriterion("fee1_type_name not like", value, "fee1TypeName");
            return (Criteria) this;
        }

        public Criteria andFee1TypeNameIn(List<String> values) {
            addCriterion("fee1_type_name in", values, "fee1TypeName");
            return (Criteria) this;
        }

        public Criteria andFee1TypeNameNotIn(List<String> values) {
            addCriterion("fee1_type_name not in", values, "fee1TypeName");
            return (Criteria) this;
        }

        public Criteria andFee1TypeNameBetween(String value1, String value2) {
            addCriterion("fee1_type_name between", value1, value2, "fee1TypeName");
            return (Criteria) this;
        }

        public Criteria andFee1TypeNameNotBetween(String value1, String value2) {
            addCriterion("fee1_type_name not between", value1, value2, "fee1TypeName");
            return (Criteria) this;
        }

        public Criteria andFee1HourTypeIsNull() {
            addCriterion("fee1_hour_type is null");
            return (Criteria) this;
        }

        public Criteria andFee1HourTypeIsNotNull() {
            addCriterion("fee1_hour_type is not null");
            return (Criteria) this;
        }

        public Criteria andFee1HourTypeEqualTo(Integer value) {
            addCriterion("fee1_hour_type =", value, "fee1HourType");
            return (Criteria) this;
        }

        public Criteria andFee1HourTypeNotEqualTo(Integer value) {
            addCriterion("fee1_hour_type <>", value, "fee1HourType");
            return (Criteria) this;
        }

        public Criteria andFee1HourTypeGreaterThan(Integer value) {
            addCriterion("fee1_hour_type >", value, "fee1HourType");
            return (Criteria) this;
        }

        public Criteria andFee1HourTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("fee1_hour_type >=", value, "fee1HourType");
            return (Criteria) this;
        }

        public Criteria andFee1HourTypeLessThan(Integer value) {
            addCriterion("fee1_hour_type <", value, "fee1HourType");
            return (Criteria) this;
        }

        public Criteria andFee1HourTypeLessThanOrEqualTo(Integer value) {
            addCriterion("fee1_hour_type <=", value, "fee1HourType");
            return (Criteria) this;
        }

        public Criteria andFee1HourTypeIn(List<Integer> values) {
            addCriterion("fee1_hour_type in", values, "fee1HourType");
            return (Criteria) this;
        }

        public Criteria andFee1HourTypeNotIn(List<Integer> values) {
            addCriterion("fee1_hour_type not in", values, "fee1HourType");
            return (Criteria) this;
        }

        public Criteria andFee1HourTypeBetween(Integer value1, Integer value2) {
            addCriterion("fee1_hour_type between", value1, value2, "fee1HourType");
            return (Criteria) this;
        }

        public Criteria andFee1HourTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("fee1_hour_type not between", value1, value2, "fee1HourType");
            return (Criteria) this;
        }

        public Criteria andFee1HourAmountIsNull() {
            addCriterion("fee1_hour_amount is null");
            return (Criteria) this;
        }

        public Criteria andFee1HourAmountIsNotNull() {
            addCriterion("fee1_hour_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFee1HourAmountEqualTo(BigDecimal value) {
            addCriterion("fee1_hour_amount =", value, "fee1HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee1HourAmountNotEqualTo(BigDecimal value) {
            addCriterion("fee1_hour_amount <>", value, "fee1HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee1HourAmountGreaterThan(BigDecimal value) {
            addCriterion("fee1_hour_amount >", value, "fee1HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee1HourAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee1_hour_amount >=", value, "fee1HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee1HourAmountLessThan(BigDecimal value) {
            addCriterion("fee1_hour_amount <", value, "fee1HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee1HourAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee1_hour_amount <=", value, "fee1HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee1HourAmountIn(List<BigDecimal> values) {
            addCriterion("fee1_hour_amount in", values, "fee1HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee1HourAmountNotIn(List<BigDecimal> values) {
            addCriterion("fee1_hour_amount not in", values, "fee1HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee1HourAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee1_hour_amount between", value1, value2, "fee1HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee1HourAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee1_hour_amount not between", value1, value2, "fee1HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee2TypeIdIsNull() {
            addCriterion("fee2_type_id is null");
            return (Criteria) this;
        }

        public Criteria andFee2TypeIdIsNotNull() {
            addCriterion("fee2_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andFee2TypeIdEqualTo(Integer value) {
            addCriterion("fee2_type_id =", value, "fee2TypeId");
            return (Criteria) this;
        }

        public Criteria andFee2TypeIdNotEqualTo(Integer value) {
            addCriterion("fee2_type_id <>", value, "fee2TypeId");
            return (Criteria) this;
        }

        public Criteria andFee2TypeIdGreaterThan(Integer value) {
            addCriterion("fee2_type_id >", value, "fee2TypeId");
            return (Criteria) this;
        }

        public Criteria andFee2TypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fee2_type_id >=", value, "fee2TypeId");
            return (Criteria) this;
        }

        public Criteria andFee2TypeIdLessThan(Integer value) {
            addCriterion("fee2_type_id <", value, "fee2TypeId");
            return (Criteria) this;
        }

        public Criteria andFee2TypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("fee2_type_id <=", value, "fee2TypeId");
            return (Criteria) this;
        }

        public Criteria andFee2TypeIdIn(List<Integer> values) {
            addCriterion("fee2_type_id in", values, "fee2TypeId");
            return (Criteria) this;
        }

        public Criteria andFee2TypeIdNotIn(List<Integer> values) {
            addCriterion("fee2_type_id not in", values, "fee2TypeId");
            return (Criteria) this;
        }

        public Criteria andFee2TypeIdBetween(Integer value1, Integer value2) {
            addCriterion("fee2_type_id between", value1, value2, "fee2TypeId");
            return (Criteria) this;
        }

        public Criteria andFee2TypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fee2_type_id not between", value1, value2, "fee2TypeId");
            return (Criteria) this;
        }

        public Criteria andFee2TypeNameIsNull() {
            addCriterion("fee2_type_name is null");
            return (Criteria) this;
        }

        public Criteria andFee2TypeNameIsNotNull() {
            addCriterion("fee2_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andFee2TypeNameEqualTo(String value) {
            addCriterion("fee2_type_name =", value, "fee2TypeName");
            return (Criteria) this;
        }

        public Criteria andFee2TypeNameNotEqualTo(String value) {
            addCriterion("fee2_type_name <>", value, "fee2TypeName");
            return (Criteria) this;
        }

        public Criteria andFee2TypeNameGreaterThan(String value) {
            addCriterion("fee2_type_name >", value, "fee2TypeName");
            return (Criteria) this;
        }

        public Criteria andFee2TypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("fee2_type_name >=", value, "fee2TypeName");
            return (Criteria) this;
        }

        public Criteria andFee2TypeNameLessThan(String value) {
            addCriterion("fee2_type_name <", value, "fee2TypeName");
            return (Criteria) this;
        }

        public Criteria andFee2TypeNameLessThanOrEqualTo(String value) {
            addCriterion("fee2_type_name <=", value, "fee2TypeName");
            return (Criteria) this;
        }

        public Criteria andFee2TypeNameLike(String value) {
            addCriterion("fee2_type_name like", value, "fee2TypeName");
            return (Criteria) this;
        }

        public Criteria andFee2TypeNameNotLike(String value) {
            addCriterion("fee2_type_name not like", value, "fee2TypeName");
            return (Criteria) this;
        }

        public Criteria andFee2TypeNameIn(List<String> values) {
            addCriterion("fee2_type_name in", values, "fee2TypeName");
            return (Criteria) this;
        }

        public Criteria andFee2TypeNameNotIn(List<String> values) {
            addCriterion("fee2_type_name not in", values, "fee2TypeName");
            return (Criteria) this;
        }

        public Criteria andFee2TypeNameBetween(String value1, String value2) {
            addCriterion("fee2_type_name between", value1, value2, "fee2TypeName");
            return (Criteria) this;
        }

        public Criteria andFee2TypeNameNotBetween(String value1, String value2) {
            addCriterion("fee2_type_name not between", value1, value2, "fee2TypeName");
            return (Criteria) this;
        }

        public Criteria andFee2HourTypeIsNull() {
            addCriterion("fee2_hour_type is null");
            return (Criteria) this;
        }

        public Criteria andFee2HourTypeIsNotNull() {
            addCriterion("fee2_hour_type is not null");
            return (Criteria) this;
        }

        public Criteria andFee2HourTypeEqualTo(Integer value) {
            addCriterion("fee2_hour_type =", value, "fee2HourType");
            return (Criteria) this;
        }

        public Criteria andFee2HourTypeNotEqualTo(Integer value) {
            addCriterion("fee2_hour_type <>", value, "fee2HourType");
            return (Criteria) this;
        }

        public Criteria andFee2HourTypeGreaterThan(Integer value) {
            addCriterion("fee2_hour_type >", value, "fee2HourType");
            return (Criteria) this;
        }

        public Criteria andFee2HourTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("fee2_hour_type >=", value, "fee2HourType");
            return (Criteria) this;
        }

        public Criteria andFee2HourTypeLessThan(Integer value) {
            addCriterion("fee2_hour_type <", value, "fee2HourType");
            return (Criteria) this;
        }

        public Criteria andFee2HourTypeLessThanOrEqualTo(Integer value) {
            addCriterion("fee2_hour_type <=", value, "fee2HourType");
            return (Criteria) this;
        }

        public Criteria andFee2HourTypeIn(List<Integer> values) {
            addCriterion("fee2_hour_type in", values, "fee2HourType");
            return (Criteria) this;
        }

        public Criteria andFee2HourTypeNotIn(List<Integer> values) {
            addCriterion("fee2_hour_type not in", values, "fee2HourType");
            return (Criteria) this;
        }

        public Criteria andFee2HourTypeBetween(Integer value1, Integer value2) {
            addCriterion("fee2_hour_type between", value1, value2, "fee2HourType");
            return (Criteria) this;
        }

        public Criteria andFee2HourTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("fee2_hour_type not between", value1, value2, "fee2HourType");
            return (Criteria) this;
        }

        public Criteria andFee2HourAmountIsNull() {
            addCriterion("fee2_hour_amount is null");
            return (Criteria) this;
        }

        public Criteria andFee2HourAmountIsNotNull() {
            addCriterion("fee2_hour_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFee2HourAmountEqualTo(BigDecimal value) {
            addCriterion("fee2_hour_amount =", value, "fee2HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee2HourAmountNotEqualTo(BigDecimal value) {
            addCriterion("fee2_hour_amount <>", value, "fee2HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee2HourAmountGreaterThan(BigDecimal value) {
            addCriterion("fee2_hour_amount >", value, "fee2HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee2HourAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee2_hour_amount >=", value, "fee2HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee2HourAmountLessThan(BigDecimal value) {
            addCriterion("fee2_hour_amount <", value, "fee2HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee2HourAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee2_hour_amount <=", value, "fee2HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee2HourAmountIn(List<BigDecimal> values) {
            addCriterion("fee2_hour_amount in", values, "fee2HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee2HourAmountNotIn(List<BigDecimal> values) {
            addCriterion("fee2_hour_amount not in", values, "fee2HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee2HourAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee2_hour_amount between", value1, value2, "fee2HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee2HourAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee2_hour_amount not between", value1, value2, "fee2HourAmount");
            return (Criteria) this;
        }

        public Criteria andFee3TypeIdIsNull() {
            addCriterion("fee3_type_id is null");
            return (Criteria) this;
        }

        public Criteria andFee3TypeIdIsNotNull() {
            addCriterion("fee3_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andFee3TypeIdEqualTo(Integer value) {
            addCriterion("fee3_type_id =", value, "fee3TypeId");
            return (Criteria) this;
        }

        public Criteria andFee3TypeIdNotEqualTo(Integer value) {
            addCriterion("fee3_type_id <>", value, "fee3TypeId");
            return (Criteria) this;
        }

        public Criteria andFee3TypeIdGreaterThan(Integer value) {
            addCriterion("fee3_type_id >", value, "fee3TypeId");
            return (Criteria) this;
        }

        public Criteria andFee3TypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fee3_type_id >=", value, "fee3TypeId");
            return (Criteria) this;
        }

        public Criteria andFee3TypeIdLessThan(Integer value) {
            addCriterion("fee3_type_id <", value, "fee3TypeId");
            return (Criteria) this;
        }

        public Criteria andFee3TypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("fee3_type_id <=", value, "fee3TypeId");
            return (Criteria) this;
        }

        public Criteria andFee3TypeIdIn(List<Integer> values) {
            addCriterion("fee3_type_id in", values, "fee3TypeId");
            return (Criteria) this;
        }

        public Criteria andFee3TypeIdNotIn(List<Integer> values) {
            addCriterion("fee3_type_id not in", values, "fee3TypeId");
            return (Criteria) this;
        }

        public Criteria andFee3TypeIdBetween(Integer value1, Integer value2) {
            addCriterion("fee3_type_id between", value1, value2, "fee3TypeId");
            return (Criteria) this;
        }

        public Criteria andFee3TypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fee3_type_id not between", value1, value2, "fee3TypeId");
            return (Criteria) this;
        }

        public Criteria andFee3TypeNameIsNull() {
            addCriterion("fee3_type_name is null");
            return (Criteria) this;
        }

        public Criteria andFee3TypeNameIsNotNull() {
            addCriterion("fee3_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andFee3TypeNameEqualTo(String value) {
            addCriterion("fee3_type_name =", value, "fee3TypeName");
            return (Criteria) this;
        }

        public Criteria andFee3TypeNameNotEqualTo(String value) {
            addCriterion("fee3_type_name <>", value, "fee3TypeName");
            return (Criteria) this;
        }

        public Criteria andFee3TypeNameGreaterThan(String value) {
            addCriterion("fee3_type_name >", value, "fee3TypeName");
            return (Criteria) this;
        }

        public Criteria andFee3TypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("fee3_type_name >=", value, "fee3TypeName");
            return (Criteria) this;
        }

        public Criteria andFee3TypeNameLessThan(String value) {
            addCriterion("fee3_type_name <", value, "fee3TypeName");
            return (Criteria) this;
        }

        public Criteria andFee3TypeNameLessThanOrEqualTo(String value) {
            addCriterion("fee3_type_name <=", value, "fee3TypeName");
            return (Criteria) this;
        }

        public Criteria andFee3TypeNameLike(String value) {
            addCriterion("fee3_type_name like", value, "fee3TypeName");
            return (Criteria) this;
        }

        public Criteria andFee3TypeNameNotLike(String value) {
            addCriterion("fee3_type_name not like", value, "fee3TypeName");
            return (Criteria) this;
        }

        public Criteria andFee3TypeNameIn(List<String> values) {
            addCriterion("fee3_type_name in", values, "fee3TypeName");
            return (Criteria) this;
        }

        public Criteria andFee3TypeNameNotIn(List<String> values) {
            addCriterion("fee3_type_name not in", values, "fee3TypeName");
            return (Criteria) this;
        }

        public Criteria andFee3TypeNameBetween(String value1, String value2) {
            addCriterion("fee3_type_name between", value1, value2, "fee3TypeName");
            return (Criteria) this;
        }

        public Criteria andFee3TypeNameNotBetween(String value1, String value2) {
            addCriterion("fee3_type_name not between", value1, value2, "fee3TypeName");
            return (Criteria) this;
        }

        public Criteria andFee3HourTypeIsNull() {
            addCriterion("fee3_hour_type is null");
            return (Criteria) this;
        }

        public Criteria andFee3HourTypeIsNotNull() {
            addCriterion("fee3_hour_type is not null");
            return (Criteria) this;
        }

        public Criteria andFee3HourTypeEqualTo(Integer value) {
            addCriterion("fee3_hour_type =", value, "fee3HourType");
            return (Criteria) this;
        }

        public Criteria andFee3HourTypeNotEqualTo(Integer value) {
            addCriterion("fee3_hour_type <>", value, "fee3HourType");
            return (Criteria) this;
        }

        public Criteria andFee3HourTypeGreaterThan(Integer value) {
            addCriterion("fee3_hour_type >", value, "fee3HourType");
            return (Criteria) this;
        }

        public Criteria andFee3HourTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("fee3_hour_type >=", value, "fee3HourType");
            return (Criteria) this;
        }

        public Criteria andFee3HourTypeLessThan(Integer value) {
            addCriterion("fee3_hour_type <", value, "fee3HourType");
            return (Criteria) this;
        }

        public Criteria andFee3HourTypeLessThanOrEqualTo(Integer value) {
            addCriterion("fee3_hour_type <=", value, "fee3HourType");
            return (Criteria) this;
        }

        public Criteria andFee3HourTypeIn(List<Integer> values) {
            addCriterion("fee3_hour_type in", values, "fee3HourType");
            return (Criteria) this;
        }

        public Criteria andFee3HourTypeNotIn(List<Integer> values) {
            addCriterion("fee3_hour_type not in", values, "fee3HourType");
            return (Criteria) this;
        }

        public Criteria andFee3HourTypeBetween(Integer value1, Integer value2) {
            addCriterion("fee3_hour_type between", value1, value2, "fee3HourType");
            return (Criteria) this;
        }

        public Criteria andFee3HourTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("fee3_hour_type not between", value1, value2, "fee3HourType");
            return (Criteria) this;
        }

        public Criteria andFee3HourAountIsNull() {
            addCriterion("fee3_hour_aount is null");
            return (Criteria) this;
        }

        public Criteria andFee3HourAountIsNotNull() {
            addCriterion("fee3_hour_aount is not null");
            return (Criteria) this;
        }

        public Criteria andFee3HourAountEqualTo(BigDecimal value) {
            addCriterion("fee3_hour_aount =", value, "fee3HourAount");
            return (Criteria) this;
        }

        public Criteria andFee3HourAountNotEqualTo(BigDecimal value) {
            addCriterion("fee3_hour_aount <>", value, "fee3HourAount");
            return (Criteria) this;
        }

        public Criteria andFee3HourAountGreaterThan(BigDecimal value) {
            addCriterion("fee3_hour_aount >", value, "fee3HourAount");
            return (Criteria) this;
        }

        public Criteria andFee3HourAountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee3_hour_aount >=", value, "fee3HourAount");
            return (Criteria) this;
        }

        public Criteria andFee3HourAountLessThan(BigDecimal value) {
            addCriterion("fee3_hour_aount <", value, "fee3HourAount");
            return (Criteria) this;
        }

        public Criteria andFee3HourAountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee3_hour_aount <=", value, "fee3HourAount");
            return (Criteria) this;
        }

        public Criteria andFee3HourAountIn(List<BigDecimal> values) {
            addCriterion("fee3_hour_aount in", values, "fee3HourAount");
            return (Criteria) this;
        }

        public Criteria andFee3HourAountNotIn(List<BigDecimal> values) {
            addCriterion("fee3_hour_aount not in", values, "fee3HourAount");
            return (Criteria) this;
        }

        public Criteria andFee3HourAountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee3_hour_aount between", value1, value2, "fee3HourAount");
            return (Criteria) this;
        }

        public Criteria andFee3HourAountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee3_hour_aount not between", value1, value2, "fee3HourAount");
            return (Criteria) this;
        }

        public Criteria andPlatformRatoIsNull() {
            addCriterion("platform_rato is null");
            return (Criteria) this;
        }

        public Criteria andPlatformRatoIsNotNull() {
            addCriterion("platform_rato is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformRatoEqualTo(BigDecimal value) {
            addCriterion("platform_rato =", value, "platformRato");
            return (Criteria) this;
        }

        public Criteria andPlatformRatoNotEqualTo(BigDecimal value) {
            addCriterion("platform_rato <>", value, "platformRato");
            return (Criteria) this;
        }

        public Criteria andPlatformRatoGreaterThan(BigDecimal value) {
            addCriterion("platform_rato >", value, "platformRato");
            return (Criteria) this;
        }

        public Criteria andPlatformRatoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_rato >=", value, "platformRato");
            return (Criteria) this;
        }

        public Criteria andPlatformRatoLessThan(BigDecimal value) {
            addCriterion("platform_rato <", value, "platformRato");
            return (Criteria) this;
        }

        public Criteria andPlatformRatoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_rato <=", value, "platformRato");
            return (Criteria) this;
        }

        public Criteria andPlatformRatoIn(List<BigDecimal> values) {
            addCriterion("platform_rato in", values, "platformRato");
            return (Criteria) this;
        }

        public Criteria andPlatformRatoNotIn(List<BigDecimal> values) {
            addCriterion("platform_rato not in", values, "platformRato");
            return (Criteria) this;
        }

        public Criteria andPlatformRatoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_rato between", value1, value2, "platformRato");
            return (Criteria) this;
        }

        public Criteria andPlatformRatoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_rato not between", value1, value2, "platformRato");
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

        public Criteria andAgents1CnIsNull() {
            addCriterion("agents1_cn is null");
            return (Criteria) this;
        }

        public Criteria andAgents1CnIsNotNull() {
            addCriterion("agents1_cn is not null");
            return (Criteria) this;
        }

        public Criteria andAgents1CnEqualTo(String value) {
            addCriterion("agents1_cn =", value, "agents1Cn");
            return (Criteria) this;
        }

        public Criteria andAgents1CnNotEqualTo(String value) {
            addCriterion("agents1_cn <>", value, "agents1Cn");
            return (Criteria) this;
        }

        public Criteria andAgents1CnGreaterThan(String value) {
            addCriterion("agents1_cn >", value, "agents1Cn");
            return (Criteria) this;
        }

        public Criteria andAgents1CnGreaterThanOrEqualTo(String value) {
            addCriterion("agents1_cn >=", value, "agents1Cn");
            return (Criteria) this;
        }

        public Criteria andAgents1CnLessThan(String value) {
            addCriterion("agents1_cn <", value, "agents1Cn");
            return (Criteria) this;
        }

        public Criteria andAgents1CnLessThanOrEqualTo(String value) {
            addCriterion("agents1_cn <=", value, "agents1Cn");
            return (Criteria) this;
        }

        public Criteria andAgents1CnLike(String value) {
            addCriterion("agents1_cn like", value, "agents1Cn");
            return (Criteria) this;
        }

        public Criteria andAgents1CnNotLike(String value) {
            addCriterion("agents1_cn not like", value, "agents1Cn");
            return (Criteria) this;
        }

        public Criteria andAgents1CnIn(List<String> values) {
            addCriterion("agents1_cn in", values, "agents1Cn");
            return (Criteria) this;
        }

        public Criteria andAgents1CnNotIn(List<String> values) {
            addCriterion("agents1_cn not in", values, "agents1Cn");
            return (Criteria) this;
        }

        public Criteria andAgents1CnBetween(String value1, String value2) {
            addCriterion("agents1_cn between", value1, value2, "agents1Cn");
            return (Criteria) this;
        }

        public Criteria andAgents1CnNotBetween(String value1, String value2) {
            addCriterion("agents1_cn not between", value1, value2, "agents1Cn");
            return (Criteria) this;
        }

        public Criteria andAgents1RatoIsNull() {
            addCriterion("agents1_rato is null");
            return (Criteria) this;
        }

        public Criteria andAgents1RatoIsNotNull() {
            addCriterion("agents1_rato is not null");
            return (Criteria) this;
        }

        public Criteria andAgents1RatoEqualTo(BigDecimal value) {
            addCriterion("agents1_rato =", value, "agents1Rato");
            return (Criteria) this;
        }

        public Criteria andAgents1RatoNotEqualTo(BigDecimal value) {
            addCriterion("agents1_rato <>", value, "agents1Rato");
            return (Criteria) this;
        }

        public Criteria andAgents1RatoGreaterThan(BigDecimal value) {
            addCriterion("agents1_rato >", value, "agents1Rato");
            return (Criteria) this;
        }

        public Criteria andAgents1RatoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("agents1_rato >=", value, "agents1Rato");
            return (Criteria) this;
        }

        public Criteria andAgents1RatoLessThan(BigDecimal value) {
            addCriterion("agents1_rato <", value, "agents1Rato");
            return (Criteria) this;
        }

        public Criteria andAgents1RatoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("agents1_rato <=", value, "agents1Rato");
            return (Criteria) this;
        }

        public Criteria andAgents1RatoIn(List<BigDecimal> values) {
            addCriterion("agents1_rato in", values, "agents1Rato");
            return (Criteria) this;
        }

        public Criteria andAgents1RatoNotIn(List<BigDecimal> values) {
            addCriterion("agents1_rato not in", values, "agents1Rato");
            return (Criteria) this;
        }

        public Criteria andAgents1RatoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agents1_rato between", value1, value2, "agents1Rato");
            return (Criteria) this;
        }

        public Criteria andAgents1RatoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agents1_rato not between", value1, value2, "agents1Rato");
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

        public Criteria andAgents2CnIsNull() {
            addCriterion("agents2_cn is null");
            return (Criteria) this;
        }

        public Criteria andAgents2CnIsNotNull() {
            addCriterion("agents2_cn is not null");
            return (Criteria) this;
        }

        public Criteria andAgents2CnEqualTo(String value) {
            addCriterion("agents2_cn =", value, "agents2Cn");
            return (Criteria) this;
        }

        public Criteria andAgents2CnNotEqualTo(String value) {
            addCriterion("agents2_cn <>", value, "agents2Cn");
            return (Criteria) this;
        }

        public Criteria andAgents2CnGreaterThan(String value) {
            addCriterion("agents2_cn >", value, "agents2Cn");
            return (Criteria) this;
        }

        public Criteria andAgents2CnGreaterThanOrEqualTo(String value) {
            addCriterion("agents2_cn >=", value, "agents2Cn");
            return (Criteria) this;
        }

        public Criteria andAgents2CnLessThan(String value) {
            addCriterion("agents2_cn <", value, "agents2Cn");
            return (Criteria) this;
        }

        public Criteria andAgents2CnLessThanOrEqualTo(String value) {
            addCriterion("agents2_cn <=", value, "agents2Cn");
            return (Criteria) this;
        }

        public Criteria andAgents2CnLike(String value) {
            addCriterion("agents2_cn like", value, "agents2Cn");
            return (Criteria) this;
        }

        public Criteria andAgents2CnNotLike(String value) {
            addCriterion("agents2_cn not like", value, "agents2Cn");
            return (Criteria) this;
        }

        public Criteria andAgents2CnIn(List<String> values) {
            addCriterion("agents2_cn in", values, "agents2Cn");
            return (Criteria) this;
        }

        public Criteria andAgents2CnNotIn(List<String> values) {
            addCriterion("agents2_cn not in", values, "agents2Cn");
            return (Criteria) this;
        }

        public Criteria andAgents2CnBetween(String value1, String value2) {
            addCriterion("agents2_cn between", value1, value2, "agents2Cn");
            return (Criteria) this;
        }

        public Criteria andAgents2CnNotBetween(String value1, String value2) {
            addCriterion("agents2_cn not between", value1, value2, "agents2Cn");
            return (Criteria) this;
        }

        public Criteria andAgents2RatoIsNull() {
            addCriterion("agents2_rato is null");
            return (Criteria) this;
        }

        public Criteria andAgents2RatoIsNotNull() {
            addCriterion("agents2_rato is not null");
            return (Criteria) this;
        }

        public Criteria andAgents2RatoEqualTo(BigDecimal value) {
            addCriterion("agents2_rato =", value, "agents2Rato");
            return (Criteria) this;
        }

        public Criteria andAgents2RatoNotEqualTo(BigDecimal value) {
            addCriterion("agents2_rato <>", value, "agents2Rato");
            return (Criteria) this;
        }

        public Criteria andAgents2RatoGreaterThan(BigDecimal value) {
            addCriterion("agents2_rato >", value, "agents2Rato");
            return (Criteria) this;
        }

        public Criteria andAgents2RatoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("agents2_rato >=", value, "agents2Rato");
            return (Criteria) this;
        }

        public Criteria andAgents2RatoLessThan(BigDecimal value) {
            addCriterion("agents2_rato <", value, "agents2Rato");
            return (Criteria) this;
        }

        public Criteria andAgents2RatoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("agents2_rato <=", value, "agents2Rato");
            return (Criteria) this;
        }

        public Criteria andAgents2RatoIn(List<BigDecimal> values) {
            addCriterion("agents2_rato in", values, "agents2Rato");
            return (Criteria) this;
        }

        public Criteria andAgents2RatoNotIn(List<BigDecimal> values) {
            addCriterion("agents2_rato not in", values, "agents2Rato");
            return (Criteria) this;
        }

        public Criteria andAgents2RatoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agents2_rato between", value1, value2, "agents2Rato");
            return (Criteria) this;
        }

        public Criteria andAgents2RatoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agents2_rato not between", value1, value2, "agents2Rato");
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

        public Criteria andAgents3RatoIsNull() {
            addCriterion("agents3_rato is null");
            return (Criteria) this;
        }

        public Criteria andAgents3RatoIsNotNull() {
            addCriterion("agents3_rato is not null");
            return (Criteria) this;
        }

        public Criteria andAgents3RatoEqualTo(BigDecimal value) {
            addCriterion("agents3_rato =", value, "agents3Rato");
            return (Criteria) this;
        }

        public Criteria andAgents3RatoNotEqualTo(BigDecimal value) {
            addCriterion("agents3_rato <>", value, "agents3Rato");
            return (Criteria) this;
        }

        public Criteria andAgents3RatoGreaterThan(BigDecimal value) {
            addCriterion("agents3_rato >", value, "agents3Rato");
            return (Criteria) this;
        }

        public Criteria andAgents3RatoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("agents3_rato >=", value, "agents3Rato");
            return (Criteria) this;
        }

        public Criteria andAgents3RatoLessThan(BigDecimal value) {
            addCriterion("agents3_rato <", value, "agents3Rato");
            return (Criteria) this;
        }

        public Criteria andAgents3RatoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("agents3_rato <=", value, "agents3Rato");
            return (Criteria) this;
        }

        public Criteria andAgents3RatoIn(List<BigDecimal> values) {
            addCriterion("agents3_rato in", values, "agents3Rato");
            return (Criteria) this;
        }

        public Criteria andAgents3RatoNotIn(List<BigDecimal> values) {
            addCriterion("agents3_rato not in", values, "agents3Rato");
            return (Criteria) this;
        }

        public Criteria andAgents3RatoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agents3_rato between", value1, value2, "agents3Rato");
            return (Criteria) this;
        }

        public Criteria andAgents3RatoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agents3_rato not between", value1, value2, "agents3Rato");
            return (Criteria) this;
        }

        public Criteria andAgents3CnIsNull() {
            addCriterion("agents3_cn is null");
            return (Criteria) this;
        }

        public Criteria andAgents3CnIsNotNull() {
            addCriterion("agents3_cn is not null");
            return (Criteria) this;
        }

        public Criteria andAgents3CnEqualTo(String value) {
            addCriterion("agents3_cn =", value, "agents3Cn");
            return (Criteria) this;
        }

        public Criteria andAgents3CnNotEqualTo(String value) {
            addCriterion("agents3_cn <>", value, "agents3Cn");
            return (Criteria) this;
        }

        public Criteria andAgents3CnGreaterThan(String value) {
            addCriterion("agents3_cn >", value, "agents3Cn");
            return (Criteria) this;
        }

        public Criteria andAgents3CnGreaterThanOrEqualTo(String value) {
            addCriterion("agents3_cn >=", value, "agents3Cn");
            return (Criteria) this;
        }

        public Criteria andAgents3CnLessThan(String value) {
            addCriterion("agents3_cn <", value, "agents3Cn");
            return (Criteria) this;
        }

        public Criteria andAgents3CnLessThanOrEqualTo(String value) {
            addCriterion("agents3_cn <=", value, "agents3Cn");
            return (Criteria) this;
        }

        public Criteria andAgents3CnLike(String value) {
            addCriterion("agents3_cn like", value, "agents3Cn");
            return (Criteria) this;
        }

        public Criteria andAgents3CnNotLike(String value) {
            addCriterion("agents3_cn not like", value, "agents3Cn");
            return (Criteria) this;
        }

        public Criteria andAgents3CnIn(List<String> values) {
            addCriterion("agents3_cn in", values, "agents3Cn");
            return (Criteria) this;
        }

        public Criteria andAgents3CnNotIn(List<String> values) {
            addCriterion("agents3_cn not in", values, "agents3Cn");
            return (Criteria) this;
        }

        public Criteria andAgents3CnBetween(String value1, String value2) {
            addCriterion("agents3_cn between", value1, value2, "agents3Cn");
            return (Criteria) this;
        }

        public Criteria andAgents3CnNotBetween(String value1, String value2) {
            addCriterion("agents3_cn not between", value1, value2, "agents3Cn");
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

        public Criteria andShopkeeperCnIsNull() {
            addCriterion("shopkeeper_cn is null");
            return (Criteria) this;
        }

        public Criteria andShopkeeperCnIsNotNull() {
            addCriterion("shopkeeper_cn is not null");
            return (Criteria) this;
        }

        public Criteria andShopkeeperCnEqualTo(String value) {
            addCriterion("shopkeeper_cn =", value, "shopkeeperCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperCnNotEqualTo(String value) {
            addCriterion("shopkeeper_cn <>", value, "shopkeeperCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperCnGreaterThan(String value) {
            addCriterion("shopkeeper_cn >", value, "shopkeeperCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperCnGreaterThanOrEqualTo(String value) {
            addCriterion("shopkeeper_cn >=", value, "shopkeeperCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperCnLessThan(String value) {
            addCriterion("shopkeeper_cn <", value, "shopkeeperCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperCnLessThanOrEqualTo(String value) {
            addCriterion("shopkeeper_cn <=", value, "shopkeeperCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperCnLike(String value) {
            addCriterion("shopkeeper_cn like", value, "shopkeeperCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperCnNotLike(String value) {
            addCriterion("shopkeeper_cn not like", value, "shopkeeperCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperCnIn(List<String> values) {
            addCriterion("shopkeeper_cn in", values, "shopkeeperCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperCnNotIn(List<String> values) {
            addCriterion("shopkeeper_cn not in", values, "shopkeeperCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperCnBetween(String value1, String value2) {
            addCriterion("shopkeeper_cn between", value1, value2, "shopkeeperCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperCnNotBetween(String value1, String value2) {
            addCriterion("shopkeeper_cn not between", value1, value2, "shopkeeperCn");
            return (Criteria) this;
        }

        public Criteria andShopkeeperRatoIsNull() {
            addCriterion("shopkeeper_rato is null");
            return (Criteria) this;
        }

        public Criteria andShopkeeperRatoIsNotNull() {
            addCriterion("shopkeeper_rato is not null");
            return (Criteria) this;
        }

        public Criteria andShopkeeperRatoEqualTo(BigDecimal value) {
            addCriterion("shopkeeper_rato =", value, "shopkeeperRato");
            return (Criteria) this;
        }

        public Criteria andShopkeeperRatoNotEqualTo(BigDecimal value) {
            addCriterion("shopkeeper_rato <>", value, "shopkeeperRato");
            return (Criteria) this;
        }

        public Criteria andShopkeeperRatoGreaterThan(BigDecimal value) {
            addCriterion("shopkeeper_rato >", value, "shopkeeperRato");
            return (Criteria) this;
        }

        public Criteria andShopkeeperRatoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("shopkeeper_rato >=", value, "shopkeeperRato");
            return (Criteria) this;
        }

        public Criteria andShopkeeperRatoLessThan(BigDecimal value) {
            addCriterion("shopkeeper_rato <", value, "shopkeeperRato");
            return (Criteria) this;
        }

        public Criteria andShopkeeperRatoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("shopkeeper_rato <=", value, "shopkeeperRato");
            return (Criteria) this;
        }

        public Criteria andShopkeeperRatoIn(List<BigDecimal> values) {
            addCriterion("shopkeeper_rato in", values, "shopkeeperRato");
            return (Criteria) this;
        }

        public Criteria andShopkeeperRatoNotIn(List<BigDecimal> values) {
            addCriterion("shopkeeper_rato not in", values, "shopkeeperRato");
            return (Criteria) this;
        }

        public Criteria andShopkeeperRatoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shopkeeper_rato between", value1, value2, "shopkeeperRato");
            return (Criteria) this;
        }

        public Criteria andShopkeeperRatoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shopkeeper_rato not between", value1, value2, "shopkeeperRato");
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

        public Criteria andAllianceBusinessCnIsNull() {
            addCriterion("alliance_business_cn is null");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessCnIsNotNull() {
            addCriterion("alliance_business_cn is not null");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessCnEqualTo(String value) {
            addCriterion("alliance_business_cn =", value, "allianceBusinessCn");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessCnNotEqualTo(String value) {
            addCriterion("alliance_business_cn <>", value, "allianceBusinessCn");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessCnGreaterThan(String value) {
            addCriterion("alliance_business_cn >", value, "allianceBusinessCn");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessCnGreaterThanOrEqualTo(String value) {
            addCriterion("alliance_business_cn >=", value, "allianceBusinessCn");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessCnLessThan(String value) {
            addCriterion("alliance_business_cn <", value, "allianceBusinessCn");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessCnLessThanOrEqualTo(String value) {
            addCriterion("alliance_business_cn <=", value, "allianceBusinessCn");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessCnLike(String value) {
            addCriterion("alliance_business_cn like", value, "allianceBusinessCn");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessCnNotLike(String value) {
            addCriterion("alliance_business_cn not like", value, "allianceBusinessCn");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessCnIn(List<String> values) {
            addCriterion("alliance_business_cn in", values, "allianceBusinessCn");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessCnNotIn(List<String> values) {
            addCriterion("alliance_business_cn not in", values, "allianceBusinessCn");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessCnBetween(String value1, String value2) {
            addCriterion("alliance_business_cn between", value1, value2, "allianceBusinessCn");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessCnNotBetween(String value1, String value2) {
            addCriterion("alliance_business_cn not between", value1, value2, "allianceBusinessCn");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessRateIsNull() {
            addCriterion("alliance_business_rate is null");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessRateIsNotNull() {
            addCriterion("alliance_business_rate is not null");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessRateEqualTo(BigDecimal value) {
            addCriterion("alliance_business_rate =", value, "allianceBusinessRate");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessRateNotEqualTo(BigDecimal value) {
            addCriterion("alliance_business_rate <>", value, "allianceBusinessRate");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessRateGreaterThan(BigDecimal value) {
            addCriterion("alliance_business_rate >", value, "allianceBusinessRate");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("alliance_business_rate >=", value, "allianceBusinessRate");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessRateLessThan(BigDecimal value) {
            addCriterion("alliance_business_rate <", value, "allianceBusinessRate");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("alliance_business_rate <=", value, "allianceBusinessRate");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessRateIn(List<BigDecimal> values) {
            addCriterion("alliance_business_rate in", values, "allianceBusinessRate");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessRateNotIn(List<BigDecimal> values) {
            addCriterion("alliance_business_rate not in", values, "allianceBusinessRate");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("alliance_business_rate between", value1, value2, "allianceBusinessRate");
            return (Criteria) this;
        }

        public Criteria andAllianceBusinessRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("alliance_business_rate not between", value1, value2, "allianceBusinessRate");
            return (Criteria) this;
        }

        public Criteria andTradeYfjAmountIsNull() {
            addCriterion("trade_yfj_amount is null");
            return (Criteria) this;
        }

        public Criteria andTradeYfjAmountIsNotNull() {
            addCriterion("trade_yfj_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTradeYfjAmountEqualTo(BigDecimal value) {
            addCriterion("trade_yfj_amount =", value, "tradeYfjAmount");
            return (Criteria) this;
        }

        public Criteria andTradeYfjAmountNotEqualTo(BigDecimal value) {
            addCriterion("trade_yfj_amount <>", value, "tradeYfjAmount");
            return (Criteria) this;
        }

        public Criteria andTradeYfjAmountGreaterThan(BigDecimal value) {
            addCriterion("trade_yfj_amount >", value, "tradeYfjAmount");
            return (Criteria) this;
        }

        public Criteria andTradeYfjAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_yfj_amount >=", value, "tradeYfjAmount");
            return (Criteria) this;
        }

        public Criteria andTradeYfjAmountLessThan(BigDecimal value) {
            addCriterion("trade_yfj_amount <", value, "tradeYfjAmount");
            return (Criteria) this;
        }

        public Criteria andTradeYfjAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_yfj_amount <=", value, "tradeYfjAmount");
            return (Criteria) this;
        }

        public Criteria andTradeYfjAmountIn(List<BigDecimal> values) {
            addCriterion("trade_yfj_amount in", values, "tradeYfjAmount");
            return (Criteria) this;
        }

        public Criteria andTradeYfjAmountNotIn(List<BigDecimal> values) {
            addCriterion("trade_yfj_amount not in", values, "tradeYfjAmount");
            return (Criteria) this;
        }

        public Criteria andTradeYfjAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_yfj_amount between", value1, value2, "tradeYfjAmount");
            return (Criteria) this;
        }

        public Criteria andTradeYfjAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_yfj_amount not between", value1, value2, "tradeYfjAmount");
            return (Criteria) this;
        }

        public Criteria andTradeCustNoIsNull() {
            addCriterion("trade_cust_no is null");
            return (Criteria) this;
        }

        public Criteria andTradeCustNoIsNotNull() {
            addCriterion("trade_cust_no is not null");
            return (Criteria) this;
        }

        public Criteria andTradeCustNoEqualTo(Long value) {
            addCriterion("trade_cust_no =", value, "tradeCustNo");
            return (Criteria) this;
        }

        public Criteria andTradeCustNoNotEqualTo(Long value) {
            addCriterion("trade_cust_no <>", value, "tradeCustNo");
            return (Criteria) this;
        }

        public Criteria andTradeCustNoGreaterThan(Long value) {
            addCriterion("trade_cust_no >", value, "tradeCustNo");
            return (Criteria) this;
        }

        public Criteria andTradeCustNoGreaterThanOrEqualTo(Long value) {
            addCriterion("trade_cust_no >=", value, "tradeCustNo");
            return (Criteria) this;
        }

        public Criteria andTradeCustNoLessThan(Long value) {
            addCriterion("trade_cust_no <", value, "tradeCustNo");
            return (Criteria) this;
        }

        public Criteria andTradeCustNoLessThanOrEqualTo(Long value) {
            addCriterion("trade_cust_no <=", value, "tradeCustNo");
            return (Criteria) this;
        }

        public Criteria andTradeCustNoIn(List<Long> values) {
            addCriterion("trade_cust_no in", values, "tradeCustNo");
            return (Criteria) this;
        }

        public Criteria andTradeCustNoNotIn(List<Long> values) {
            addCriterion("trade_cust_no not in", values, "tradeCustNo");
            return (Criteria) this;
        }

        public Criteria andTradeCustNoBetween(Long value1, Long value2) {
            addCriterion("trade_cust_no between", value1, value2, "tradeCustNo");
            return (Criteria) this;
        }

        public Criteria andTradeCustNoNotBetween(Long value1, Long value2) {
            addCriterion("trade_cust_no not between", value1, value2, "tradeCustNo");
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

        public Criteria andSqrUrlIsNull() {
            addCriterion("SQR_url is null");
            return (Criteria) this;
        }

        public Criteria andSqrUrlIsNotNull() {
            addCriterion("SQR_url is not null");
            return (Criteria) this;
        }

        public Criteria andSqrUrlEqualTo(String value) {
            addCriterion("SQR_url =", value, "sqrUrl");
            return (Criteria) this;
        }

        public Criteria andSqrUrlNotEqualTo(String value) {
            addCriterion("SQR_url <>", value, "sqrUrl");
            return (Criteria) this;
        }

        public Criteria andSqrUrlGreaterThan(String value) {
            addCriterion("SQR_url >", value, "sqrUrl");
            return (Criteria) this;
        }

        public Criteria andSqrUrlGreaterThanOrEqualTo(String value) {
            addCriterion("SQR_url >=", value, "sqrUrl");
            return (Criteria) this;
        }

        public Criteria andSqrUrlLessThan(String value) {
            addCriterion("SQR_url <", value, "sqrUrl");
            return (Criteria) this;
        }

        public Criteria andSqrUrlLessThanOrEqualTo(String value) {
            addCriterion("SQR_url <=", value, "sqrUrl");
            return (Criteria) this;
        }

        public Criteria andSqrUrlLike(String value) {
            addCriterion("SQR_url like", value, "sqrUrl");
            return (Criteria) this;
        }

        public Criteria andSqrUrlNotLike(String value) {
            addCriterion("SQR_url not like", value, "sqrUrl");
            return (Criteria) this;
        }

        public Criteria andSqrUrlIn(List<String> values) {
            addCriterion("SQR_url in", values, "sqrUrl");
            return (Criteria) this;
        }

        public Criteria andSqrUrlNotIn(List<String> values) {
            addCriterion("SQR_url not in", values, "sqrUrl");
            return (Criteria) this;
        }

        public Criteria andSqrUrlBetween(String value1, String value2) {
            addCriterion("SQR_url between", value1, value2, "sqrUrl");
            return (Criteria) this;
        }

        public Criteria andSqrUrlNotBetween(String value1, String value2) {
            addCriterion("SQR_url not between", value1, value2, "sqrUrl");
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

        public Criteria andYfjRebackTypeIsNull() {
            addCriterion("yfj_reback_type is null");
            return (Criteria) this;
        }

        public Criteria andYfjRebackTypeIsNotNull() {
            addCriterion("yfj_reback_type is not null");
            return (Criteria) this;
        }

        public Criteria andYfjRebackTypeEqualTo(Integer value) {
            addCriterion("yfj_reback_type =", value, "yfjRebackType");
            return (Criteria) this;
        }

        public Criteria andYfjRebackTypeNotEqualTo(Integer value) {
            addCriterion("yfj_reback_type <>", value, "yfjRebackType");
            return (Criteria) this;
        }

        public Criteria andYfjRebackTypeGreaterThan(Integer value) {
            addCriterion("yfj_reback_type >", value, "yfjRebackType");
            return (Criteria) this;
        }

        public Criteria andYfjRebackTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("yfj_reback_type >=", value, "yfjRebackType");
            return (Criteria) this;
        }

        public Criteria andYfjRebackTypeLessThan(Integer value) {
            addCriterion("yfj_reback_type <", value, "yfjRebackType");
            return (Criteria) this;
        }

        public Criteria andYfjRebackTypeLessThanOrEqualTo(Integer value) {
            addCriterion("yfj_reback_type <=", value, "yfjRebackType");
            return (Criteria) this;
        }

        public Criteria andYfjRebackTypeIn(List<Integer> values) {
            addCriterion("yfj_reback_type in", values, "yfjRebackType");
            return (Criteria) this;
        }

        public Criteria andYfjRebackTypeNotIn(List<Integer> values) {
            addCriterion("yfj_reback_type not in", values, "yfjRebackType");
            return (Criteria) this;
        }

        public Criteria andYfjRebackTypeBetween(Integer value1, Integer value2) {
            addCriterion("yfj_reback_type between", value1, value2, "yfjRebackType");
            return (Criteria) this;
        }

        public Criteria andYfjRebackTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("yfj_reback_type not between", value1, value2, "yfjRebackType");
            return (Criteria) this;
        }

        public Criteria andTradeFeeTypeIdIsNull() {
            addCriterion("trade_fee_type_id is null");
            return (Criteria) this;
        }

        public Criteria andTradeFeeTypeIdIsNotNull() {
            addCriterion("trade_fee_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTradeFeeTypeIdEqualTo(Integer value) {
            addCriterion("trade_fee_type_id =", value, "tradeFeeTypeId");
            return (Criteria) this;
        }

        public Criteria andTradeFeeTypeIdNotEqualTo(Integer value) {
            addCriterion("trade_fee_type_id <>", value, "tradeFeeTypeId");
            return (Criteria) this;
        }

        public Criteria andTradeFeeTypeIdGreaterThan(Integer value) {
            addCriterion("trade_fee_type_id >", value, "tradeFeeTypeId");
            return (Criteria) this;
        }

        public Criteria andTradeFeeTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("trade_fee_type_id >=", value, "tradeFeeTypeId");
            return (Criteria) this;
        }

        public Criteria andTradeFeeTypeIdLessThan(Integer value) {
            addCriterion("trade_fee_type_id <", value, "tradeFeeTypeId");
            return (Criteria) this;
        }

        public Criteria andTradeFeeTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("trade_fee_type_id <=", value, "tradeFeeTypeId");
            return (Criteria) this;
        }

        public Criteria andTradeFeeTypeIdIn(List<Integer> values) {
            addCriterion("trade_fee_type_id in", values, "tradeFeeTypeId");
            return (Criteria) this;
        }

        public Criteria andTradeFeeTypeIdNotIn(List<Integer> values) {
            addCriterion("trade_fee_type_id not in", values, "tradeFeeTypeId");
            return (Criteria) this;
        }

        public Criteria andTradeFeeTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("trade_fee_type_id between", value1, value2, "tradeFeeTypeId");
            return (Criteria) this;
        }

        public Criteria andTradeFeeTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("trade_fee_type_id not between", value1, value2, "tradeFeeTypeId");
            return (Criteria) this;
        }

        public Criteria andPasswordTypeIsNull() {
            addCriterion("password_type is null");
            return (Criteria) this;
        }

        public Criteria andPasswordTypeIsNotNull() {
            addCriterion("password_type is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordTypeEqualTo(Integer value) {
            addCriterion("password_type =", value, "passwordType");
            return (Criteria) this;
        }

        public Criteria andPasswordTypeNotEqualTo(Integer value) {
            addCriterion("password_type <>", value, "passwordType");
            return (Criteria) this;
        }

        public Criteria andPasswordTypeGreaterThan(Integer value) {
            addCriterion("password_type >", value, "passwordType");
            return (Criteria) this;
        }

        public Criteria andPasswordTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("password_type >=", value, "passwordType");
            return (Criteria) this;
        }

        public Criteria andPasswordTypeLessThan(Integer value) {
            addCriterion("password_type <", value, "passwordType");
            return (Criteria) this;
        }

        public Criteria andPasswordTypeLessThanOrEqualTo(Integer value) {
            addCriterion("password_type <=", value, "passwordType");
            return (Criteria) this;
        }

        public Criteria andPasswordTypeIn(List<Integer> values) {
            addCriterion("password_type in", values, "passwordType");
            return (Criteria) this;
        }

        public Criteria andPasswordTypeNotIn(List<Integer> values) {
            addCriterion("password_type not in", values, "passwordType");
            return (Criteria) this;
        }

        public Criteria andPasswordTypeBetween(Integer value1, Integer value2) {
            addCriterion("password_type between", value1, value2, "passwordType");
            return (Criteria) this;
        }

        public Criteria andPasswordTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("password_type not between", value1, value2, "passwordType");
            return (Criteria) this;
        }

        public Criteria andPasswordMonthIsNull() {
            addCriterion("password_month is null");
            return (Criteria) this;
        }

        public Criteria andPasswordMonthIsNotNull() {
            addCriterion("password_month is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordMonthEqualTo(String value) {
            addCriterion("password_month =", value, "passwordMonth");
            return (Criteria) this;
        }

        public Criteria andPasswordMonthNotEqualTo(String value) {
            addCriterion("password_month <>", value, "passwordMonth");
            return (Criteria) this;
        }

        public Criteria andPasswordMonthGreaterThan(String value) {
            addCriterion("password_month >", value, "passwordMonth");
            return (Criteria) this;
        }

        public Criteria andPasswordMonthGreaterThanOrEqualTo(String value) {
            addCriterion("password_month >=", value, "passwordMonth");
            return (Criteria) this;
        }

        public Criteria andPasswordMonthLessThan(String value) {
            addCriterion("password_month <", value, "passwordMonth");
            return (Criteria) this;
        }

        public Criteria andPasswordMonthLessThanOrEqualTo(String value) {
            addCriterion("password_month <=", value, "passwordMonth");
            return (Criteria) this;
        }

        public Criteria andPasswordMonthLike(String value) {
            addCriterion("password_month like", value, "passwordMonth");
            return (Criteria) this;
        }

        public Criteria andPasswordMonthNotLike(String value) {
            addCriterion("password_month not like", value, "passwordMonth");
            return (Criteria) this;
        }

        public Criteria andPasswordMonthIn(List<String> values) {
            addCriterion("password_month in", values, "passwordMonth");
            return (Criteria) this;
        }

        public Criteria andPasswordMonthNotIn(List<String> values) {
            addCriterion("password_month not in", values, "passwordMonth");
            return (Criteria) this;
        }

        public Criteria andPasswordMonthBetween(String value1, String value2) {
            addCriterion("password_month between", value1, value2, "passwordMonth");
            return (Criteria) this;
        }

        public Criteria andPasswordMonthNotBetween(String value1, String value2) {
            addCriterion("password_month not between", value1, value2, "passwordMonth");
            return (Criteria) this;
        }

        public Criteria andPasswordBatchIsNull() {
            addCriterion("password_batch is null");
            return (Criteria) this;
        }

        public Criteria andPasswordBatchIsNotNull() {
            addCriterion("password_batch is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordBatchEqualTo(String value) {
            addCriterion("password_batch =", value, "passwordBatch");
            return (Criteria) this;
        }

        public Criteria andPasswordBatchNotEqualTo(String value) {
            addCriterion("password_batch <>", value, "passwordBatch");
            return (Criteria) this;
        }

        public Criteria andPasswordBatchGreaterThan(String value) {
            addCriterion("password_batch >", value, "passwordBatch");
            return (Criteria) this;
        }

        public Criteria andPasswordBatchGreaterThanOrEqualTo(String value) {
            addCriterion("password_batch >=", value, "passwordBatch");
            return (Criteria) this;
        }

        public Criteria andPasswordBatchLessThan(String value) {
            addCriterion("password_batch <", value, "passwordBatch");
            return (Criteria) this;
        }

        public Criteria andPasswordBatchLessThanOrEqualTo(String value) {
            addCriterion("password_batch <=", value, "passwordBatch");
            return (Criteria) this;
        }

        public Criteria andPasswordBatchLike(String value) {
            addCriterion("password_batch like", value, "passwordBatch");
            return (Criteria) this;
        }

        public Criteria andPasswordBatchNotLike(String value) {
            addCriterion("password_batch not like", value, "passwordBatch");
            return (Criteria) this;
        }

        public Criteria andPasswordBatchIn(List<String> values) {
            addCriterion("password_batch in", values, "passwordBatch");
            return (Criteria) this;
        }

        public Criteria andPasswordBatchNotIn(List<String> values) {
            addCriterion("password_batch not in", values, "passwordBatch");
            return (Criteria) this;
        }

        public Criteria andPasswordBatchBetween(String value1, String value2) {
            addCriterion("password_batch between", value1, value2, "passwordBatch");
            return (Criteria) this;
        }

        public Criteria andPasswordBatchNotBetween(String value1, String value2) {
            addCriterion("password_batch not between", value1, value2, "passwordBatch");
            return (Criteria) this;
        }

        public Criteria andPasswordKeyIsNull() {
            addCriterion("password_key is null");
            return (Criteria) this;
        }

        public Criteria andPasswordKeyIsNotNull() {
            addCriterion("password_key is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordKeyEqualTo(String value) {
            addCriterion("password_key =", value, "passwordKey");
            return (Criteria) this;
        }

        public Criteria andPasswordKeyNotEqualTo(String value) {
            addCriterion("password_key <>", value, "passwordKey");
            return (Criteria) this;
        }

        public Criteria andPasswordKeyGreaterThan(String value) {
            addCriterion("password_key >", value, "passwordKey");
            return (Criteria) this;
        }

        public Criteria andPasswordKeyGreaterThanOrEqualTo(String value) {
            addCriterion("password_key >=", value, "passwordKey");
            return (Criteria) this;
        }

        public Criteria andPasswordKeyLessThan(String value) {
            addCriterion("password_key <", value, "passwordKey");
            return (Criteria) this;
        }

        public Criteria andPasswordKeyLessThanOrEqualTo(String value) {
            addCriterion("password_key <=", value, "passwordKey");
            return (Criteria) this;
        }

        public Criteria andPasswordKeyLike(String value) {
            addCriterion("password_key like", value, "passwordKey");
            return (Criteria) this;
        }

        public Criteria andPasswordKeyNotLike(String value) {
            addCriterion("password_key not like", value, "passwordKey");
            return (Criteria) this;
        }

        public Criteria andPasswordKeyIn(List<String> values) {
            addCriterion("password_key in", values, "passwordKey");
            return (Criteria) this;
        }

        public Criteria andPasswordKeyNotIn(List<String> values) {
            addCriterion("password_key not in", values, "passwordKey");
            return (Criteria) this;
        }

        public Criteria andPasswordKeyBetween(String value1, String value2) {
            addCriterion("password_key between", value1, value2, "passwordKey");
            return (Criteria) this;
        }

        public Criteria andPasswordKeyNotBetween(String value1, String value2) {
            addCriterion("password_key not between", value1, value2, "passwordKey");
            return (Criteria) this;
        }

        public Criteria andPasswordYearIsNull() {
            addCriterion("password_year is null");
            return (Criteria) this;
        }

        public Criteria andPasswordYearIsNotNull() {
            addCriterion("password_year is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordYearEqualTo(String value) {
            addCriterion("password_year =", value, "passwordYear");
            return (Criteria) this;
        }

        public Criteria andPasswordYearNotEqualTo(String value) {
            addCriterion("password_year <>", value, "passwordYear");
            return (Criteria) this;
        }

        public Criteria andPasswordYearGreaterThan(String value) {
            addCriterion("password_year >", value, "passwordYear");
            return (Criteria) this;
        }

        public Criteria andPasswordYearGreaterThanOrEqualTo(String value) {
            addCriterion("password_year >=", value, "passwordYear");
            return (Criteria) this;
        }

        public Criteria andPasswordYearLessThan(String value) {
            addCriterion("password_year <", value, "passwordYear");
            return (Criteria) this;
        }

        public Criteria andPasswordYearLessThanOrEqualTo(String value) {
            addCriterion("password_year <=", value, "passwordYear");
            return (Criteria) this;
        }

        public Criteria andPasswordYearLike(String value) {
            addCriterion("password_year like", value, "passwordYear");
            return (Criteria) this;
        }

        public Criteria andPasswordYearNotLike(String value) {
            addCriterion("password_year not like", value, "passwordYear");
            return (Criteria) this;
        }

        public Criteria andPasswordYearIn(List<String> values) {
            addCriterion("password_year in", values, "passwordYear");
            return (Criteria) this;
        }

        public Criteria andPasswordYearNotIn(List<String> values) {
            addCriterion("password_year not in", values, "passwordYear");
            return (Criteria) this;
        }

        public Criteria andPasswordYearBetween(String value1, String value2) {
            addCriterion("password_year between", value1, value2, "passwordYear");
            return (Criteria) this;
        }

        public Criteria andPasswordYearNotBetween(String value1, String value2) {
            addCriterion("password_year not between", value1, value2, "passwordYear");
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
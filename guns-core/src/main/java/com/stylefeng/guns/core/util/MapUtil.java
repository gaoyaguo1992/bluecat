package com.stylefeng.guns.core.util;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stylefeng.guns.core.model.AdCodeCommand;
import com.stylefeng.guns.core.model.AllProvinceCityZoneModel;
import com.stylefeng.guns.core.model.MapCommand;
import com.stylefeng.guns.core.model.RegionInfoModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *  地图地址转换相关的工具类
 * 
 */
public class MapUtil {
	private static Logger logger = LoggerFactory.getLogger(MapUtil.class);    // 日志记录
	// key
    private static final String KEY = "XUKBZ-3JRHV-PT7PA-UYR4R-6Z5ZE-RYFZU";//腾讯地图Api使用的key
    
    /**
     * 根据经纬度获取地址相关信息
     * @param lng 经度
     * @param lat 纬度
     * @return
     */
    public static MapCommand getLocation(String lng, String lat) {
        //Map<String, Object> resultMap = new HashMap<String, Object>();
    	MapCommand mapCommand = new MapCommand();
    	if("22.543099".equals(lat) && "114.057868".equals(lng)) {
    		return new MapCommand();
    	}
    	String result = "";
    	try{    	
	        // 参数解释：lng：经度，lat：维度。KEY：腾讯地图key，get_poi：返回状态。1返回，0不返回
	        String urlString = "http://apis.map.qq.com/ws/geocoder/v1/?location=" + lat + "," + lng + "&key=" + KEY + "&get_poi=1";
	    	result = HttpRequestUtils.httpGet(urlString);
	        // 转JSON格式
	        JSONObject jsonObject = JSONObject.fromObject(result).getJSONObject("result");
	        // 获取地址（行政区划信息） 包含有国籍，省份，城市
	        JSONObject formattedAddresses = jsonObject.getJSONObject("formatted_addresses");
	        String detailAddr = null; //详情地址
	        String nation = "" ; //国家 
	        String province = ""; //省  对应国外的一级行政区 如美国的“州”
	        String city = "" ;// 市  对应国外的第二级行政区 如美国的“县”  
	        String zone = "" ; // 区   对应国外的第三级行政区  如美国的“city市”“town乡镇”
	        JSONObject address_component = jsonObject.getJSONObject("address_component");
	        if (formattedAddresses != null && formattedAddresses.size() > 0) {  //如果定位的是在中国范围之内
	        	detailAddr = formattedAddresses.get("recommend").toString();
		        detailAddr = detailAddr.substring(detailAddr.indexOf("区") + 1);
		        mapCommand.setAddr(jsonObject.get("address").toString() + detailAddr);
		        //下面获取中国内的各级行政区
		        nation = address_component.get("nation").toString();
		        province = address_component.get("province").toString();
		        city = address_component.get("city").toString();
		        zone = address_component.get("district").toString();
			}else {
				detailAddr = jsonObject.get("address").toString();
	            mapCommand.setAddr(detailAddr);
	            nation = address_component.get("nation").toString();
	            province  = address_component.get("ad_level_1").toString();
	            city  = address_component.get("ad_level_2").toString();
	            zone = address_component.get("ad_level_3").toString();
			}
	        mapCommand.setNation(nation);
	        mapCommand.setProvince(province);
	        mapCommand.setCity(city);
	        mapCommand.setZone(zone);
	        logger.info("腾讯地图经纬度lng{},lat{},addr:{}",lng,lat,mapCommand.getAddr());
	        if("广东省深圳市福田区福中三路深圳市民中心".equals(mapCommand.getAddr())) {
	        	return new MapCommand();
	        }
    	}catch(Exception e){
    		logger.error("简析腾讯地图经纬度报错:{}",result);
    		return new MapCommand();
    	}
        return mapCommand;
    }
    
    /**
     * 根据经纬度获取地址相关信息,去除市民中心的bug的版本
     * @param lng 经度
     * @param lat 纬度
     * @return
     */
    public static MapCommand getLocation1(String lng, String lat) {
        //Map<String, Object> resultMap = new HashMap<String, Object>();
    	MapCommand mapCommand = new MapCommand();
    	String result = "";
    	try{    	
	        // 参数解释：lng：经度，lat：维度。KEY：腾讯地图key，get_poi：返回状态。1返回，0不返回
	        String urlString = "http://apis.map.qq.com/ws/geocoder/v1/?location=" + lat + "," + lng + "&key=" + KEY + "&get_poi=1";
	    	result = HttpRequestUtils.httpGet(urlString);
	        // 转JSON格式
	        JSONObject jsonObject = JSONObject.fromObject(result).getJSONObject("result");
	        // 获取地址（行政区划信息） 包含有国籍，省份，城市
	        JSONObject formattedAddresses = jsonObject.getJSONObject("formatted_addresses");
	        String detailAddr = null; //详情地址
	        String nation = "" ; //国家 
	        String province = ""; //省  对应国外的一级行政区 如美国的“州”
	        String city = "" ;// 市  对应国外的第二级行政区 如美国的“县”  
	        String zone = "" ; // 区   对应国外的第三级行政区  如美国的“city市”“town乡镇”
	        JSONObject address_component = jsonObject.getJSONObject("address_component");
	        if (formattedAddresses != null && formattedAddresses.size() > 0) {  //如果定位的是在中国范围之内
	        	detailAddr = formattedAddresses.get("recommend").toString();
		        detailAddr = detailAddr.substring(detailAddr.indexOf("区") + 1);
		        mapCommand.setAddr(jsonObject.get("address").toString() + detailAddr);
		        //下面获取中国内的各级行政区
		        nation = address_component.get("nation").toString();
		        province = address_component.get("province").toString();
		        city = address_component.get("city").toString();
		        zone = address_component.get("district").toString();
			}else {
				detailAddr = jsonObject.get("address").toString();
	            mapCommand.setAddr(detailAddr);
	            nation = address_component.get("nation").toString();
	            province  = address_component.get("ad_level_1").toString();
	            city  = address_component.get("ad_level_2").toString();
	            zone = address_component.get("ad_level_3").toString();
			}
	        mapCommand.setNation(nation);
	        mapCommand.setProvince(province);
	        mapCommand.setCity(city);
	        mapCommand.setZone(zone);
	        logger.info("腾讯地图经纬度lng{},lat{},addr:{}",lng,lat,mapCommand.getAddr());
    	}catch(Exception e){
    		logger.error("简析腾讯地图经纬度报错:{}",result);
    		return new MapCommand();
    	}
        return mapCommand;
    }
    
    /**
     * 访问腾讯地图，获取省市区数据
     * @return
     */
    public static AllProvinceCityZoneModel readRegionModelFromTencentMap(){
        String urlString = "https://apis.map.qq.com/ws/district/v1/list?key=" + KEY ;
        String result = "";
        //long start = System.currentTimeMillis();
        result = HttpRequestUtils.httpGet(urlString);
        //System.out.println("访问接口所需时间为："+(System.currentTimeMillis() - start));
        AllProvinceCityZoneModel locationModel = new AllProvinceCityZoneModel();  //创建一个地理行政机构的Model类
        JSONArray allLocation = JSONObject.fromObject(result).getJSONArray("result");

        //遍历一级行政区
        JSONArray provinces = allLocation.getJSONArray(0);
        //System.out.println(provinces.size());
        List<RegionInfoModel> provinceModels = new ArrayList<RegionInfoModel>();
        for(int i = 0 ; i < provinces.size();i++){
            JSONObject province = (JSONObject)provinces.get(i);
            Long provinceId = province.getLong("id");
            String provinceName = province.getString("fullname");
            RegionInfoModel model = new RegionInfoModel();
            model.setRegionId(provinceId);
            model.setName(provinceName);
            provinceModels.add(model);
        }
        locationModel.setProvinces(provinceModels);

        //下面遍历所有的二级行政机构
        JSONArray citys = allLocation.getJSONArray(1);
        //System.out.println(citys.size());
        List<RegionInfoModel> cityModels = new ArrayList<RegionInfoModel>();
        for(int i = 0 ; i < citys.size();i++) {
            JSONObject city = (JSONObject) citys.get(i);
            Long cityId = city.getLong("id");
            String cityName = city.getString("fullname");
            RegionInfoModel model = new RegionInfoModel();
            model.setRegionId(cityId);
            model.setName(cityName);
            cityModels.add(model);
        }
        locationModel.setCitys(cityModels);

        //遍历三级行政区
        List<RegionInfoModel> zoneModels = new ArrayList<RegionInfoModel>();
        JSONArray zones = allLocation.getJSONArray(2);
        //System.out.println(zones.size());
        //System.out.println(zones);
        for(int i = 0 ; i < zones.size();i++){
            JSONObject zone = (JSONObject)zones.get(i);
            Long zoneId = zone.getLong("id");
            String zoneName = zone.getString("fullname");
            RegionInfoModel model = new RegionInfoModel();
            model.setRegionId(zoneId);
            model.setName(zoneName);
            zoneModels.add(model);
        }
        locationModel.setZones(zoneModels);
        return locationModel;

    }
    
    /**
     * 根据省（一级行政区）选取相应的市（二级行政区）
     * @param province
     * @param locationModel
     * @return
     */
    public static List<RegionInfoModel> selectCity(RegionInfoModel province,AllProvinceCityZoneModel locationModel){
        List<RegionInfoModel> cityModels = new ArrayList<RegionInfoModel>();
        Long parentId = province.getRegionId(); //父级的id
        List<RegionInfoModel>  allCitys = locationModel.getCitys();
        //System.out.println(citys);
        for(int i = 0 ; i < allCitys.size();i++){
        	RegionInfoModel city = allCitys.get(i);
            Long cityId = city.getRegionId();
            if(parentId/10000 != cityId/10000){
                continue;
            }else {
                String cityName = city.getName();
                RegionInfoModel model = new RegionInfoModel();
                model.setParentId(parentId);
                model.setRegionId(cityId);
                model.setName(cityName);
                cityModels.add(model);
            }

        }
        return cityModels;
    }
    
    /**
     * 根据市（二级行政区）得县或者区（三级行政区）
     * @param city
     * @param locationModel
     * @return
     */
    public static List<RegionInfoModel> selectTownOrZone(RegionInfoModel city,AllProvinceCityZoneModel locationModel){
        List<RegionInfoModel> zoneModels = new ArrayList<RegionInfoModel>();
        Long parentId = city.getRegionId();  //父级的id
        List<RegionInfoModel> allZones= locationModel.getZones();
        //System.out.println(zones);
        for(int i = 0 ; i < allZones.size();i++){
        	RegionInfoModel zone = allZones.get(i);
            Long zoneId = zone.getRegionId();
            if(parentId/100 != zoneId/100){
                continue;
            }else {
                String zoneName = zone.getName();
                RegionInfoModel model = new RegionInfoModel();
                model.setParentId(parentId);
                model.setRegionId(zoneId);
                model.setName(zoneName);
                zoneModels.add(model);
            }
        }
        return zoneModels;
    }
    
    /**
     * 根据经纬度反解析出省市区的code
     * @param lng
     * @param lat
     * @return
     * @throws Exception
     */
    public static AdCodeCommand getAdCode(String lng, String lat) throws Exception{
    	AdCodeCommand adCodeModel = new AdCodeCommand();
    	String urlString = "http://apis.map.qq.com/ws/geocoder/v1/?location=" + lat + "," + lng + "&key=" + KEY + "&get_poi=1";
	    String result = HttpRequestUtils.httpGet(urlString);
    	try{   
	        JSONObject jsonObject = JSONObject.fromObject(result).getJSONObject("result");
	      //获取详细地址
	        String address1  = jsonObject.getString("address");
	        
	        JSONObject formattedAddresses = jsonObject.getJSONObject("formatted_addresses");
	        
	        if (formattedAddresses == null || formattedAddresses.size() <= 0) {
				throw new Exception("中国以外的地区无法定位获取省市区的code");
				
			}
	        
	        String address2 = formattedAddresses.getString("recommend");
	        //System.out.println(address2);
	        //获取adinfo：
	        //{"nation_code":"156","adcode":"110101","city_code":"156110000","name":"中国,北京市,北京市,东城区","location":{"lat":39.90888,"lng":116.39527},"nation":"中国","province":"北京市","city":"北京市","district":"东城区"}
	        JSONObject adInfo = jsonObject.getJSONObject("ad_info");
	        //System.out.println(adInfo);
	        String nation = adInfo.getString("nation_code");
	        String ad = adInfo.getString("adcode");
	        String city = adInfo.getString("city_code");
	        //获取names：
	        //"name":"中国,北京市,北京市,东城区"
	        String [] names = adInfo.getString("name").split(",");
	        
	        String cityCode = city.substring(nation.length());
	        String provinceCode = null;
	        String chatAtCity = cityCode.substring(2,4);
	        if (!chatAtCity.equals("00")){ //如果是非直辖市
	        	if (names.length > 3) {
	        		provinceCode = cityCode.substring(0,2)+"0000";
		            String zoneCode = ad;
		            adCodeModel.setProvinceCode(provinceCode);
		            adCodeModel.setCityCode(cityCode);
		            adCodeModel.setZoneCode(zoneCode);
		            adCodeModel.setProvinceName(names[1]);
		            adCodeModel.setCityName(names[2]);
		            adCodeModel.setZoneName(names[3]);
		            adCodeModel.setAddr(address1 + address2);
				}else if (names.length == 3) {
					provinceCode = cityCode.substring(0,2)+"0000";
		            String zoneCode = ad;
		            adCodeModel.setProvinceCode(provinceCode);
		            adCodeModel.setCityCode(cityCode);
		            adCodeModel.setZoneCode(Integer.valueOf(Integer.valueOf(cityCode) + 99).toString());
		            adCodeModel.setProvinceName(names[1]);
		            adCodeModel.setCityName(names[2]);
		            adCodeModel.setZoneName(names[2]);
		            adCodeModel.setAddr(address1 + address2);
				} 
	        }else{
	        	if (names.length > 3) {
	        		provinceCode = cityCode.substring(0,2)+"0000";
		            adCodeModel.setProvinceCode(provinceCode);
		            String zoneCode = ad;
		            adCodeModel.setCityCode(zoneCode);
		            adCodeModel.setZoneCode(null);
		            adCodeModel.setProvinceName(names[1]);
		            adCodeModel.setCityName(names[3]);
		            adCodeModel.setZoneName(null);
		            adCodeModel.setAddr(address1 + address2);
				}else if (names.length == 3) {
					provinceCode = cityCode.substring(0,2)+"0000";
		            adCodeModel.setProvinceCode(provinceCode);
		            String zoneCode = ad;
		            adCodeModel.setCityCode(zoneCode);
		            adCodeModel.setZoneCode(Integer.valueOf(Integer.valueOf(zoneCode) + 99).toString());
		            adCodeModel.setProvinceName(names[1]);
		            adCodeModel.setCityName(names[2]);
		            adCodeModel.setZoneName(names[2]);
		            adCodeModel.setAddr(address1 + address2);
				}
	        }
    	}catch (Exception e) {
			logger.info("获取省市区编码错错误！！{}",result);
			throw e;
		}
        return adCodeModel;
    }
}

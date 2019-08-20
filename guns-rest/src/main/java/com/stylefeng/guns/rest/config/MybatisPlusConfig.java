package com.stylefeng.guns.rest.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.stylefeng.guns.core.config.properties.DruidProperties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @Date 2017年8月23日12:51:41
 */
@Configuration
@MapperScan(basePackages = {"com.stylefeng.guns.rest.*.dao", "com.stylefeng.guns.rest.common.persistence.dao"
					,"com.stylefeng.guns.sharecore.*.dao", "com.stylefeng.guns.sharecore.common.persistence.dao"
					,"com.stylefeng.guns.sharecore.modular.system.dao"})
																											   										 
public class MybatisPlusConfig {
	@Autowired
	DruidProperties druidProperties;
    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
    	PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType(DBType.MYSQL.getDb());
        return paginationInterceptor;
    }
    /**
     * druid数据库连接池
     * @return
     */
    @Bean(initMethod="init")
    public DruidDataSource dataSource(){
    	DruidDataSource dataSource = new DruidDataSource();
    	druidProperties.config(dataSource);
    	return dataSource;
    }
}

package com.stylefeng.guns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * SpringBoot方式启动类
 * @author stylefeng
 * @Date 2017/5/21 12:06
 */
@SpringBootApplication
@EnableScheduling
public class GunsApplication {

	private final static Logger logger = LoggerFactory.getLogger(GunsApplication.class);

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(GunsApplication.class, args);
		logger.info("GunsApplication is success!");
	}
	/**
	 * 创建定时任务...
	 * @return
	 */
	@Bean
	public TaskScheduler taskScheduler() {
		logger.info("创建定时任务taskScheduler....");
		try {
			ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
			taskScheduler.setPoolSize(10);
			taskScheduler.setThreadNamePrefix("springboot-task");
			return taskScheduler;
		} catch (Exception e) {
			logger.info("创建定时任务taskScheduler.error...",e);
			// TODO: handle exception
			return null;
		}
	}

}

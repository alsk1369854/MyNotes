package com.example.demo;

import com.example.demo.bean.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	Person person;

	@Autowired
	ApplicationContext ioc;


	Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	void testLogging(){

		// Log Level Height to Low [error > warn > info(default) > debug > trace]
		// 可在 profile 中配置 logging.level.{page}={level} 來指定日誌級別
		logger.trace("Trace Log");
		logger.debug("Debug Log");
		// Spring boot log level Defaults to INFO
		logger.info("Info Log");
		logger.warn("Warn Log");
		logger.error("Error Log");
	}

	@Test
	void testBeanContimt(){
		boolean isContain =  ioc.containsBean("helloService");
		Assertions.assertEquals(isContain, true);
	}

	@Test
	void contextLoads() {
		System.out.println(person);
	}

}

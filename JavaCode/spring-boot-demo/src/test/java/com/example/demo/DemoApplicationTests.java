package com.example.demo;

import com.example.demo.bean.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	Person person;

	@Autowired
	ApplicationContext ioc;

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

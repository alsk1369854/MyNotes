package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @SpringBootApplication 此標籤表示此類為 spring boot 應用
 */
//@ImportResource(locations = {"classpath:beans.xml"}) // 導入傳統的 beans.xml 配置文檔，推薦使用 ./config/MyAppConfig 方法配置

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

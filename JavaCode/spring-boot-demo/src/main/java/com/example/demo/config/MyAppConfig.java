package com.example.demo.config;

import com.example.demo.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 表示為一個配置類
 *
 */
@Configuration
public class MyAppConfig {

    // @Bean 將方法的返回值添加到容器中，組件的默認 id 為方法名
    @Bean
    public HelloService helloService(){
        return new HelloService();
    }
}

package com.example.demo.config;


import com.example.demo.component.LoginHandlerInterceptor;
import com.example.demo.component.MyLocaleResolver;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;

//@EnableWebMvc // 完全掌握 Web Mvc 配置，取消所有 Spring 預設的配置
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /**
     * 直接添加映射
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/mymvcconfig").setViewName("mymvcconfig");
    }

    /**
     * 創建一個 WebMvcConfigurer 他將會在啟動時被執行
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index").setViewName("login");
                registry.addViewController("/main").setViewName("dashboard");
            }

//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                        .excludePathPatterns("/", "/index", "/user/login", "/assets/**", "/webjars/**");
//            }
        };
        return webMvcConfigurer;
    }


    /*
    * 添加使用自定義的地區語言解析器
    * */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

}

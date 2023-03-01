package com.example.demo.config;

import com.example.demo.filter.MyFilter;
import com.example.demo.listener.MyListener;
import com.example.demo.servlet.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MyServerConfig {
    // 註冊 Servlet
    @Bean
    public ServletRegistrationBean myServlet(){
        // 全地址使用 Servlet
        // ServletRegistrationBean<MyServlet> registrationServlet = new ServletRegistrationBean(new MyServlet());

        // 指定地址使用 Servlet
        ServletRegistrationBean<MyServlet> registrationServlet = new ServletRegistrationBean(new MyServlet());
        registrationServlet.setUrlMappings(Arrays.asList("/myservlet"));

        return registrationServlet;
    }

    // 註冊 Filter
    @Bean
    public FilterRegistrationBean<MyFilter> myFilter(){
        // 全地址使用 Filter
        // FilterRegistrationBean<MyFilter> registrationFilter = new FilterRegistrationBean<>(new MyFilter());

        // 指定地址使用 Filter
        FilterRegistrationBean<MyFilter> registrationFilter = new FilterRegistrationBean<>(new MyFilter());
        registrationFilter.setUrlPatterns(Arrays.asList("/myfilter"));

        return registrationFilter;
    }

    // 註冊 Listener
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationListener = new ServletListenerRegistrationBean<>(new MyListener());
        return registrationListener;
    }
}

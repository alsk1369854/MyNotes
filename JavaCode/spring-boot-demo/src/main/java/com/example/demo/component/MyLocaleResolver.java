package com.example.demo.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import java.util.Locale;

// 自定義地區語言解析器
public class MyLocaleResolver implements LocaleResolver {

    // 自定義地區語言解析方法
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(l)) {
            String[] split = l.split("_");
            // Locale(語言, 區域);
            // Locale("en", "US");
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}

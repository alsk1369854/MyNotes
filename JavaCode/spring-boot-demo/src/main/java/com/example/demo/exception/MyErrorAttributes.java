package com.example.demo.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

// 設置 Error 丟出時，附帶的錯誤信息
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        // 取得錯誤訊息數據
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        // 直接添加數據
        errorAttributes.put("company", "ming");

        // 間接添加數據，獲取先前添加好的數據，並添加到錯誤數據集中
        Map<String, Object> other = (Map<String, Object>) webRequest.getAttribute("errors", 0);
        errorAttributes.put("errors", other);

        // 回傳錯誤數據集
        return errorAttributes;
    }
}

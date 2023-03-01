package com.example.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// 異常管理類
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(Exception.class) // 接收全部的 Exception
    // @ExceptionHandler(ServerException.class) // 接收特定的 Exception
    public String handlerException(Exception e, HttpServletRequest request){
        // 日誌紀錄
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.error("My Exception Handler");

        // 在 AbstractErrorController 會取用 jakarta.servlet.error.status_code 來設定異常狀態訊息
        // path: spring-boot-autoconfigure-3.0.1.jar\org\springframework\boot\autoconfigure\web\servlet\error\AbstractErrorController.class
        // Integer statusCode = (Integer)request.getAttribute("jakarta.servlet.error.status_code");

        // 自訂義異常狀態碼
        request.setAttribute("jakarta.servlet.error.status_code", 500);

        // 建立自定義的異常數據集
        Map<String, Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        map.put("exception", e);
        // 將自定義數據集，預先加入至 request 參數當中(會在 MyErrorAttributes 處理時邦定到，異常數據中)
        request.setAttribute("errors", map);

        // 委派給 Spring boot 預設的錯誤處裡類處裡(能夠自動適配請求 header 所需要的數據類型做響應)
        return "forward:/error";
    }
}

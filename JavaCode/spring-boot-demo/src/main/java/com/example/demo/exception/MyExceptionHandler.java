package com.example.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {


    @ExceptionHandler(Exception.class) // 接收全部的 Exception
//    @ExceptionHandler(ServerException.class) // 接收特定的 Exception
    public String handlerException(Exception e, HttpServletRequest request){
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.error("My Exception Handler");

//        在 AbstractErrorController 會取用 jakarta.servlet.error.status_code 來設定異常狀態訊息
//        path: spring-boot-autoconfigure-3.0.1.jar\org\springframework\boot\autoconfigure\web\servlet\error\AbstractErrorController.class
//        Integer statusCode = (Integer)request.getAttribute("jakarta.servlet.error.status_code");

        // 自訂義異常狀態碼
        request.setAttribute("jakarta.servlet.error.status_code", 500);

        Map<String, Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        map.put("exception", e);
        request.setAttribute("errors", map);

        return "forward:/error";
    }
}

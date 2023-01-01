package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


//@ResponseBody
//@Controller
// 上放兩註解二合一
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }

}

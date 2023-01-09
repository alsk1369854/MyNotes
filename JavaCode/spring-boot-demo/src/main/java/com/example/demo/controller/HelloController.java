package com.example.demo.controller;



import com.example.demo.exception.ServerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.RuntimeErrorException;
import java.util.Arrays;
import java.util.Map;


//@ResponseBody
//@RestController //包含 @ResponseBody 與 @Controller
@Controller
public class HelloController {

//    @RequestMapping({"/","/index"})
//    public String index(){
//        return "login";
//    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Map<String, Object> map){
        map.put("hiTag", "<h4>Hi</h4>");
        map.put("usernames", Arrays.asList("han", "jj", "mark"));
        map.put("author", new Object() {
            public String name = "ming";
            public Integer age = 20;
        });
        return "thymeleaf";
    }

    @GetMapping("/500")
    RuntimeErrorException getError(){
        throw new ServerException();
    }
}

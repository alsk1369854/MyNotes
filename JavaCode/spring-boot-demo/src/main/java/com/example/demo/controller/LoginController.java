package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LoginController {

//    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @PostMapping("/user/login")
    public String login(
            @RequestParam(value = "email") String email,
            @RequestParam("password") String password,
            HttpSession session
    ){
        if(!StringUtils.isEmpty(email) && "1234".equals(password) ){
//          登入成功
            session.setAttribute("loginUser", email);
            return "redirect:/main";
        }else{
//          登入失敗
            session.setAttribute("msg", "帳號密碼錯誤!");
            return "login";
        }
    }

}

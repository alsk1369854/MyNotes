package com.example.demo.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登入檢查
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null) {
//            未登入
            request.getSession().setAttribute("msg", "請先登入");
            response.sendRedirect("/index"); // 外部轉發
//            request.getRequestDispatcher("/index").forward(request, response); // 內部轉發
            return false;
        }else{
//            已登入
            return true;
        }
    }
}

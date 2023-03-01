package com.example.demo.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Web 應用啟動...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Web 應用關閉...");
    }
}

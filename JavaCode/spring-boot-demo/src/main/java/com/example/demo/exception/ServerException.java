package com.example.demo.exception;

import javax.management.RuntimeErrorException;

// 自定義異常類
public class ServerException extends RuntimeException {

    public ServerException() {
        super("Server Error Exception");
    }
}

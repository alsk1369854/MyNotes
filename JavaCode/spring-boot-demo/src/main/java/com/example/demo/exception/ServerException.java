package com.example.demo.exception;

import javax.management.RuntimeErrorException;

public class ServerException extends RuntimeException {

    public ServerException() {
        super("Server Error Exception");
    }
}

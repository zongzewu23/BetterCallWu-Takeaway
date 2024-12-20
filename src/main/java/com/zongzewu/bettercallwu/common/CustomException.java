package com.zongzewu.bettercallwu.common;

public class CustomException extends RuntimeException{
    /**
     * Custom exception class
     * @param message
     */
    public CustomException(String message){
        super(message);
    }
}

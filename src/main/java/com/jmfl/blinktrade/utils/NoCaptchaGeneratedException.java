package com.jmfl.blinktrade.utils;

public class NoCaptchaGeneratedException extends Exception{

    @Override
    public String getMessage() {
        return "No captcha is generated";
    }
}

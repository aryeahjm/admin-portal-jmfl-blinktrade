package com.jmfl.blinktrade.model;

public class User {
    private String name;
    private String password;

    private String captcha;

    public User(String name, String password, String captcha) {
        this.name = name;
        this.password = password;
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 
}
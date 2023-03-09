package com.jmfl.blinktrade.utils;

import java.util.Random;

public abstract class Utils {


    public String generateCaptcha(int n)
    {
        //to generate random integers in the range [0-61]
        Random rand = new Random(62);

        // Characters to be included
        String chrs = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        // Generate n characters from above set and
        // add these characters to captcha.
        String captcha = "";
        while (n-->0){
            int index = (int)(Math.random()*62);
            captcha+=chrs.charAt(index);
        }

        return captcha;
    }

    public Boolean validateCaptcha(String to_validate,String captcha_onscreen){
        return to_validate.equals(captcha_onscreen);
    }

}

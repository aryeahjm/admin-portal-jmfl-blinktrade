package com.jmfl.blinktrade.dao;

import com.jmfl.blinktrade.constants.Values;
import com.jmfl.blinktrade.model.Security;
import com.jmfl.blinktrade.utils.NoCaptchaGeneratedException;
import com.jmfl.blinktrade.utils.Utils;
import org.springframework.stereotype.Component;

@Component
public class Login extends Utils implements LoginDao {

    private String captcha = null;


    @Override
    public String generateCaptcha(int n) {
        String _captcha_gen = super.generateCaptcha(n);
        setCaptcha(_captcha_gen);
        return _captcha_gen;
    }

    @Override
    public String generateCaptcha() {
        return generateCaptcha(Values.CAPTCHA_LENGTH);
    }

    @Override
    public String regenerateCaptcha() {
        return generateCaptcha(Values.CAPTCHA_LENGTH);
    }

    @Override
    public Boolean validateCaptcha(String to_validate) throws NoCaptchaGeneratedException{
        return super.validateCaptcha(to_validate,getCaptcha());
    }

    @Override
    public Boolean verifyUser(String username, String password, String captcha) throws NoCaptchaGeneratedException {
        if(validateCaptcha(captcha)){
            // If captcha is validated we will verify other parameters
            verifyUser(username,password);

        }else{
            return false;
        }
        return null;
    }

    private void verifyUser(String username , String password){
        // TODO

    }

    @Override
    public void getUserPreviousSession() {

    }

    @Override
    public void updateUserPreviousSession() {

    }

    @Override
    public String generateExperienceId() {
        return null;
    }

    @Override
    public String getExperienceID() {
        return null;
    }

    @Override
    public String getCaptcha () throws NoCaptchaGeneratedException {
        if(captcha == null || captcha.isEmpty()){
            throw new NoCaptchaGeneratedException();
        }
        return captcha;
    }

    @Override
    public void setCaptcha(String _captcha) {
        this.captcha = _captcha;
    }


}

package com.jmfl.blinktrade.dao;

import com.jmfl.blinktrade.model.Session;
import com.jmfl.blinktrade.utils.NoCaptchaGeneratedException;
import jakarta.servlet.http.HttpSession;

public interface LoginDao {

    // Exposed functionalities
    public String generateCaptcha();
    public String regenerateCaptcha();
    public Boolean validateCaptcha(String to_validate) throws NoCaptchaGeneratedException;
    public Boolean verifyUser(String username , String password , String captcha) throws NoCaptchaGeneratedException;
    public void getUserPreviousSession();
    public void updateUserPreviousSession();



    // Security
    public String generateExperienceId(HttpSession session);
    public String getExperienceID();
    public String getCaptcha() throws NoCaptchaGeneratedException;
    public void setCaptcha(String _captcha);
//    void setSecurityModel(Security securityModel);


}

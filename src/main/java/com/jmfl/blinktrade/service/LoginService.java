package com.jmfl.blinktrade.service;


import com.jmfl.blinktrade.dao.LoginDao;
import com.jmfl.blinktrade.utils.NoCaptchaGeneratedException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    LoginDao loginDao;

    public String generateCaptcha(){
        return loginDao.generateCaptcha();
    }
    public Boolean validateCaptcha(String to_validate) throws NoCaptchaGeneratedException {
        return loginDao.validateCaptcha(to_validate);
    }

    public String getCorrectCaptcha() throws NoCaptchaGeneratedException {
        return loginDao.getCaptcha();
    }

    public String generateThenSaveSessionKey(HttpSession session){
        // generate the session key that could be store and preserved to see if user did log in
        return loginDao.generateExperienceId(session);
    }

    public String getSessionKey(){
        return loginDao.getExperienceID();
    }

}

package com.jmfl.blinktrade.service;


import com.jmfl.blinktrade.dao.Login;
import com.jmfl.blinktrade.dao.LoginDao;
import com.jmfl.blinktrade.model.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    LoginDao loginDao;

    public String testing(){
        return loginDao.generateCaptcha();
    }


}

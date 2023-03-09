package com.jmfl.blinktrade.adminportal;

import autovalue.shaded.com.google.auto.service.AutoService;
import com.jmfl.blinktrade.dao.Login;
import com.jmfl.blinktrade.dao.LoginDao;
import com.jmfl.blinktrade.model.User;
import com.jmfl.blinktrade.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


/*
    username and password is given , with captcha , then based on the entries we will direct the user experience
    // user type could be admin & verifier
    // we will save the user login details in our storage

 */

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome(){
        return "home";
    }

    @Autowired
    LoginService service;


    // We will receive user first name and last name in the model
    @PostMapping("/register")
    public String submitLogin(@ModelAttribute("user") User user){
        System.out.println(service.testing());

        return "success";

    }
}

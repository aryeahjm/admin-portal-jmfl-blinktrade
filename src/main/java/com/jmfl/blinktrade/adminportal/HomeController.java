package com.jmfl.blinktrade.adminportal;

import autovalue.shaded.com.google.auto.service.AutoService;
import com.google.cloud.datastore.*;
import com.jmfl.blinktrade.constants.Values;
import com.jmfl.blinktrade.dao.Login;
import com.jmfl.blinktrade.dao.LoginDao;
import com.jmfl.blinktrade.model.User;
import com.jmfl.blinktrade.service.CloudService;
import com.jmfl.blinktrade.service.LoginService;
import com.jmfl.blinktrade.utils.NoCaptchaGeneratedException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.jmfl.blinktrade.constants.Values.DB_KIND_STR;


/*
    username and password is given , with captcha , then based on the entries we will direct the user experience
    // user type could be admin & verifier
    // we will save the user login details in our storage

 */

@Controller
public class HomeController {


    @Autowired
    LoginService service;

    @Autowired
    CloudService cloudService;


    @GetMapping("/")
    public String getHome(Model model){
        if(errorState){
            errorState = false;
            model.addAttribute("response","Verification failed");
        }
        String s = service.generateCaptcha();
        model.addAttribute("captcha",s);
        return "home";
    }


    @GetMapping("/admin")
    public String getAdmin(HttpSession session){
        if(session.getAttribute(Values.SESSION_KEY_PROP_NAME) == null){
            return "redirect:/";
        }else {
            return "admin";
        }
    }

    @GetMapping("/verifier")
    public String getVerifier(HttpSession session){
        if(session.getAttribute(Values.SESSION_KEY_PROP_NAME) == null){
            return "redirect:/";
        }else {
            return "verifier";
        }
    }


    Boolean errorState = false;
    // We will receive user first name and last name in the model
    @PostMapping("/register")
    public String submitLogin(@ModelAttribute("user")User user , HttpSession session) throws NoCaptchaGeneratedException {
        if(user.getName().trim().isEmpty() || user.getPassword().isEmpty()){
            return redirectToHomeWithError("User Field Empty");
        }
        try {
            if(service.validateCaptcha(user.getCaptcha())){
                // captcha verification success
                String empid = user.getName().trim();
                String pass = user.getPassword().trim();
                Long l = cloudService.getUserType(new User(empid,pass,""),DB_KIND_STR);
                // so now we will direct user based on type
                if(l != null) {
                    if (l == 1) {
                        service.generateThenSaveSessionKey(session);
                        System.out.println("admin");
                        return "redirect:/admin";
                    } else if (l == 0) {
                        service.generateThenSaveSessionKey(session);
                        System.out.println("verifier");
                        return "redirect:/verifier";
                    }
                    else{
                        return redirectToHomeWithError("Invalid user type");
                    }
                }else return redirectToHomeWithError("No User Found");
            }
            else{
                // we will redirect to this page with error message
                return redirectToHomeWithError("Invalid Captcha");
            }
        } catch (NoCaptchaGeneratedException e) {
            System.out.println("error"+e.getMessage());
            return "error";
        }
    }

    private String redirectToHomeWithError(String message){
        System.out.println(message);
        errorState= true;
        return "redirect:/";
    }
}

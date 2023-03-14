package com.jmfl.blinktrade.adminportal;

import com.jmfl.blinktrade.constants.Values;
import com.jmfl.blinktrade.model.User;
import com.jmfl.blinktrade.service.CloudService;
import com.jmfl.blinktrade.service.LoginService;
import com.jmfl.blinktrade.utils.NoCaptchaGeneratedException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.jmfl.blinktrade.constants.Values.DB_KIND_SESSION_STR;
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
    public String getHome(Model model,HttpSession session){
        if(session.getAttribute(Values.STATE_ERROR_PROP)!=null && ((Boolean) session.getAttribute(Values.STATE_ERROR_PROP))){
            session.setAttribute(Values.STATE_ERROR_PROP,false);
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


    // We will receive user first name and last name in the model
    @PostMapping("/register")
    public String submitLogin(@ModelAttribute("user")User user , HttpSession session) throws NoCaptchaGeneratedException {
        if(user.getName().trim().isEmpty() || user.getPassword().trim().isEmpty()){
            return redirectToHomeWithError("User Field Empty",session);
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

                        // Saving user credentials , NOTE: Password not encrypted here
                        session.setAttribute(Values.EMP_ID_PROP_NAME,empid);
                        session.setAttribute(Values.EMP_PASS_PROP_NAME,pass);

                        cloudService.updateSession(user,DB_KIND_SESSION_STR);

                        return "redirect:/admin";
                    } else if (l == 0) {
                        service.generateThenSaveSessionKey(session);
                        System.out.println("verifier");

                        // Saving user credentials , NOTE: Password not encrypted here
                        session.setAttribute(Values.EMP_ID_PROP_NAME,empid);
                        session.setAttribute(Values.EMP_PASS_PROP_NAME,pass);

                        cloudService.updateSession(user,DB_KIND_SESSION_STR);

                        return "redirect:/verifier";
                    }
                    else{
                        return redirectToHomeWithError("Invalid user type",session);
                    }
                }else return redirectToHomeWithError("No User Found",session);
            }
            else{
                // we will redirect to this page with error message
                return redirectToHomeWithError("Invalid Captcha",session);
            }
        } catch (NoCaptchaGeneratedException e) {
            System.out.println("error"+e.getMessage());
            return "error";
        }
    }

    private String redirectToHomeWithError(String message,HttpSession session){
        System.out.println(message);
        session.setAttribute(Values.STATE_ERROR_PROP,true);
        return "redirect:/";
    }
}

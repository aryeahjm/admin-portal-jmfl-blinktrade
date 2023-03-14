package com.jmfl.blinktrade.adminportal;

import com.jmfl.blinktrade.constants.Values;
import com.jmfl.blinktrade.utils.Utils;
import jakarta.servlet.http.HttpSession;
import jdk.jshell.execution.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.NoSuchAlgorithmException;

@Controller
public class TestingController {

    @GetMapping("/test/1")
    public String testHash(Model model) throws NoSuchAlgorithmException {
        String pass = "aryatyagi";
        String encrypt = Utils.encrypt(pass);
        model.addAttribute("test",encrypt);
        System.out.println(encrypt);
        return "test";
    }

}

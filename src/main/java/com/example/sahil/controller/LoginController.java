package com.example.sahil.controller;

import com.example.sahil.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/") //This is optional as this is the only controller so a root URL pattern is not required
public class LoginController {

    public final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    //We can use the newer @GetMethod too, but using @RequestMapping for understanding purposes
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        //The Name of the Login page , the viewResolver will give this value a filePath and the .jsp extension
        return "Login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String greetTheCaller(@RequestParam String first, @RequestParam String last, @RequestParam String password,
                                 ModelMap modelMap) {
        modelMap.put("fName", first);
        modelMap.put("lName", last);
        if (loginService.isEmptyFields(first, last, password)) {
            modelMap.put("errorMessage", "Please fill all fields !");
            return "Login";
        }
        return "Greet";

    }

}
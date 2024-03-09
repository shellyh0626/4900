package com.bcstudents.personnelmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

// Spring Boot allow auto configuration
@RestController
public class PersonnelManagementController {
    // Invoke usage and receive parameters from frontend.
    // http://localhost:8080/hello
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model)
    {
        if(!StringUtils.isEmpty(username) && "09876".equals(password)){
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","Incorrect username or password!");
            return "index";
        }
    }
}

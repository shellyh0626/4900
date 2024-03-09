package com.bcstudents.personnelmanagement.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

// Spring Boot allow auto configuration
@RestController
public class PersonnelManagementController {
    // Invoke usage and receive parameters from frontend.
    // http://localhost:8080/hello
    @RequestMapping("/hello")
    public String hello(@RequestParam("username") String username, @RequestParam("password") String password, Model model,
                        HttpSession session)
    {
        if(!StringUtils.isEmpty(username) && "09876".equals(password)){
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg", "username or password are incorrect");
            return "index";
        }
    }
}



package com.bcstudents.personnelmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Spring Boot allow auto configuration
@RestController
public class PersonnelManagementController {
    // Invoke usage and receive parameters from frontend.
    // http://localhost:8080/hello
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}

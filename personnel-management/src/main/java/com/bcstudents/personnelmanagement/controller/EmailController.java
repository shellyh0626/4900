package com.bcstudents.personnelmanagement.controller;

import com.bcstudents.personnelmanagement.bean.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    @Autowired
    private Email emailService;

    @RequestMapping("/email")
    public String showEmailForm() {
        return "mailSender/mailSender"; // Assuming your HTML file is named mailSender.html
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam String toEmail, @RequestParam String subject, @RequestParam String body, Model model) {
        emailService.sendEmail(toEmail, subject, body);
        model.addAttribute("message", "Email sent successfully");
        return "mailSender/mailSender"; // Redirect back to the email form after sending the email
    }
}
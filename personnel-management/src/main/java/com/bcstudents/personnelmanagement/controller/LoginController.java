package com.bcstudents.personnelmanagement.controller;
import com.bcstudents.personnelmanagement.bean.User;
import com.bcstudents.personnelmanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private IUserService userService;

    @PostMapping(value = "/user/login")
    public String login(User user, Map<String, Object> map, HttpSession session){
//        use username and password to check
        User u = userService.login(user);

        if(u==null) {
//       login fail
            map.put("msg","Wrong PW！");
            return "login";

        } else {
//          login success
            session.setAttribute("loginUser",user.getUsername());
            return "redirect:/main.html";
        }

    }

    @RequestMapping(value = "/user/logout")
    public String logout(){
        return "login";
    }
}

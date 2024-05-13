package com.bcstudents.personnelmanagement.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bcstudents.personnelmanagement.bean.User;
import com.bcstudents.personnelmanagement.config.PaginationConstant;
import com.bcstudents.personnelmanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    IUserService userService;

    // search all
    @GetMapping("/users")
    public String list(Model model,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum) {
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum= PaginationConstant.CURRENT_NUM;
        }
        //set page
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        //search data
        List<User> users = userService.getAllUsers();
        //put result into pageinfo
        PageInfo<User> pageInfo=new PageInfo<>(users);
        //use model pass to pageinfo
        model.addAttribute("pageInfo",pageInfo);
        return "user/user";
    }

    // add
    @GetMapping("/user")
    public String toAddPage(Model model) {
        // add page

        return "user/add";
    }

    // user add
    @PostMapping("/user")
    public String addUser(User user) {
        // save
        userService.addUser(user);
        // back to users page
        return "redirect:/users";
    }

    // back to update page
    @GetMapping("/user/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user",user);

        // bakc to add page
        return "user/add";
    }

    // change
    @PutMapping("/user")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    //delete
    @DeleteMapping("/user/{id}")
    public String deleteUserById(@PathVariable("id") Integer id){
        userService.deleteUserById(id);
        return "redirect:/users";
    }

}

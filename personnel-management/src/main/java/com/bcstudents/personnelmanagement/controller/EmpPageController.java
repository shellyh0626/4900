package com.bcstudents.personnelmanagement.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bcstudents.personnelmanagement.bean.Emp;
import com.bcstudents.personnelmanagement.config.PaginationConstant;
import com.bcstudents.personnelmanagement.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmpPageController {
    @Autowired
    private IEmpService empService;

    @GetMapping("/page")
    public String findAll(Model model,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum) {
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum=PaginationConstant.CURRENT_NUM;
        }
        //set page
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        //search data
        List<Emp> emps = empService.getEmpAndDept();
        //put result into pageinfo
        PageInfo<Emp> pageInfo=new PageInfo<>(emps);
        //use model to transfer
        model.addAttribute("pageInfo",pageInfo);
        return "emp/page";
    }
}

package com.xtu.hrms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtu.hrms.bean.*;
import com.xtu.hrms.config.PaginationConstant;
import com.xtu.hrms.service.IEmpService;
import com.xtu.hrms.service.IEmpsalaryService;
import com.xtu.hrms.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmpsalaryController {
    @Autowired
    IEmpsalaryService empsalaryService;

    @Autowired
    IEmpService empService;

    @Autowired
    ISalaryService salaryService;

    // search all
    @GetMapping("/salarys")
    public String list(Model model,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum) {

        if(ObjectUtils.isEmpty(pageNum)){
            pageNum= PaginationConstant.CURRENT_NUM;
        }
        //set page
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        //search data
        List<Empsalary> empsalarys = empsalaryService.getAll();

        PageInfo<Empsalary> pageInfo=new PageInfo<>(empsalarys);

        //use model to pass
        model.addAttribute("pageInfo",pageInfo);
        return "salary/salary";
    }

    // to add page
    @GetMapping("/salary")
    public String toAddPage(Model model) {
        List<Emp> emps = empService.getAllEmps();
        model.addAttribute("emps",emps);

        List<Salary> salarys = salaryService.getAllSalarys();
        model.addAttribute("salarys",salarys);

        return "salary/add";
    }

    // add
    @PostMapping("/salary")
    public String addEmpSalary(Empsalary empsalary) {
        // save
        empsalaryService.addEmpsalary(empsalary);
        // return to salary page
        return "redirect:/salarys";
    }

    // to change page
    @GetMapping("/salary/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
        Empsalary empsalary = empsalaryService.getEmpsalaryById(id);
        model.addAttribute("empsalary",empsalary);

        List<Emp> emps = empService.getAllEmps();
        model.addAttribute("emps",emps);

        List<Salary> salarys = salaryService.getAllSalarys();
        model.addAttribute("salarys",salarys);

        // return to add page
        return "salary/add";
    }

    // change and submit id
    @PutMapping("/salary")
    public String updateEmpsalary(Empsalary empsalary) {
        empsalaryService.updateEmpsalary(empsalary);
        return "redirect:/salarys";
    }

    //delete
    @DeleteMapping("/salary/{id}")
    public String deleteEmpsalaryById(@PathVariable("id") Integer id){
        empsalaryService.deleteEmpsalaryById(id);
        return "redirect:/salarys";
    }

    @GetMapping("/salaryaccount/{id}")
    public String showSalaryAccount(@PathVariable("id") Integer id, Model model) {
        Salary salary = salaryService.getSalaryById(id);
        model.addAttribute("salary",salary);

        return "salary/salaryaccount";
    }

    @PostMapping("/salsearch")//submit list and display in frontend
    public String queryEmpsalary(@RequestParam String name, Model model,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum){
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        //search data
        List<Empsalary> empsalarys = empsalaryService.query(name);

        PageInfo<Empsalary> pageInfo=new PageInfo<>(empsalarys);

        model.addAttribute("pageInfo",pageInfo);
        return "salary/salary";
    }
}
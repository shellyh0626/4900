package com.bcstudents.personnelmanagement.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bcstudents.personnelmanagement.Appraise;
import com.bcstudents.personnelmanagement.bean.Contract;
import com.bcstudents.personnelmanagement.bean.Emp;
import com.bcstudents.personnelmanagement.bean.Emprp;
import com.bcstudents.personnelmanagement.config.PaginationConstant;
import com.bcstudents.personnelmanagement.service.IAppraiseService;
import com.bcstudents.personnelmanagement.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppraiseController {
    @Autowired
    IAppraiseService appraiseService;

    @Autowired
    IEmpService empService;

    //  search all
    @GetMapping("/appraises")
    public String list(Model model,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum) {
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum= PaginationConstant.CURRENT_NUM;
        }
        // set page
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        //search data
        List<Appraise> appraises = appraiseService.getAll();
        // put the output into pageinfo
        PageInfo<Appraise> pageInfo=new PageInfo<>(appraises);
        // use model to transfer into page
        model.addAttribute("pageInfo",pageInfo);
        return "appraise/appraise";
    }

    // add page
    @GetMapping("/appraise")
    public String toAddPage(Model model) {
        List<Emp> emps = empService.getAllEmps();
        model.addAttribute("emps",emps);

        return "appraise/add";
    }

    // add
    @PostMapping("/appraise")
    public String addAppraise(Appraise appraise) {
        // save
        appraiseService.addAppraise(appraise);
        //  list
        return "redirect:/appraises";
    }

    //  updatepage
    @GetMapping("/appraise/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id,Model model) {
        Appraise appraise = appraiseService.getAppraiseById(id);
        model.addAttribute("appraise",appraise);

        List<Emp> emps = empService.getAllEmps();
        model.addAttribute("emps",emps);

        // back to change page
        return "appraise/add";
    }

    // change id
    @PutMapping("/appraise")
    public String updateAppraise(Appraise appraise) {
        appraiseService.updateAppraise(appraise);
        return "redirect:/appraises";
    }

    // delete
    @DeleteMapping("/appraise/{id}")
    public String deleteAppraiseById(@PathVariable("id") Integer id){
        appraiseService.deleteAppraiseById(id);
        return "redirect:/appraises";
    }

    @PostMapping("/appsearch")//submit list+send back frontend data
    public String queryAppraise(@RequestParam String name, Model model,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum){
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        // search data
        List<Appraise> appraises = appraiseService.query(name);

        PageInfo<Appraise> pageInfo=new PageInfo<>(appraises);

        model.addAttribute("pageInfo",pageInfo);
        return "appraise/appraise";
    }
}

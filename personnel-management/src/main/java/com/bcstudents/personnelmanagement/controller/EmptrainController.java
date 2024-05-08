package com.xtu.hrms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtu.hrms.bean.Emp;
import com.xtu.hrms.bean.Emprp;
import com.xtu.hrms.bean.Emptrain;
import com.xtu.hrms.config.PaginationConstant;
import com.xtu.hrms.service.IEmpService;
import com.xtu.hrms.service.IEmptrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmptrainController {
    @Autowired
    IEmptrainService emptrainService;

    @Autowired
    IEmpService empService;
    // search all
    @GetMapping("/trains")
    public String list(Model model,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum) {

        if(ObjectUtils.isEmpty(pageNum)){
            pageNum= PaginationConstant.CURRENT_NUM;
        }
        //set page
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        //search data
        List<Emptrain> emptrains = emptrainService.getEmptrainAndEmp();

        PageInfo<Emptrain> pageInfo=new PageInfo<>(emptrains);

        //use model to pass
        model.addAttribute("pageInfo",pageInfo);
        return "train/train";
    }


    @GetMapping("/train")
    public String toAddPage(Model model) {
        // add page
        List<Emp> emps = empService.getAllEmps();
        model.addAttribute("emps",emps);

        return "train/add";
    }

    // add
    @PostMapping("/train")
    public String addEmptrain(Emptrain emptrain) {
        // save
        emptrainService.addEmptrain(emptrain);
        // to list page
        return "redirect:/trains";
    }

    // to change page
    @GetMapping("/train/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
        Emptrain emptrain = emptrainService.getEmptrainById(id);
        model.addAttribute("train",emptrain);

        List<Emp> emps = empService.getAllEmps();
        model.addAttribute("emps",emps);

        // back to add page
        return "train/add";
    }

    // update
    @PutMapping("/train")
    public String updateEmptrain(Emptrain emptrain) {
        emptrainService.updateEmptrain(emptrain);
        return "redirect:/trains";
    }

    //delete
    @DeleteMapping("/train/{id}")
    public String deleteEmptrainById(@PathVariable("id") Integer id){
        emptrainService.deleteEmptrainById(id);
        return "redirect:/trains";
    }

    @PostMapping("/trasearch")//submit and display
    public String queryEmptrain(@RequestParam String name, Model model,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum){
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        //search data
        List<Emptrain> emptrains = emptrainService.query(name);

        PageInfo<Emptrain> pageInfo=new PageInfo<>(emptrains);

        model.addAttribute("pageInfo",pageInfo);
        return "train/train";
    }
}
package com.bcstudents.personnelmanagement.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bcstudents.personnelmanagement.bean.Dept;
import com.bcstudents.personnelmanagement.bean.Emp;
import com.bcstudents.personnelmanagement.bean.Nation;
import com.bcstudents.personnelmanagement.bean.Position;
import com.bcstudents.personnelmanagement.config.PaginationConstant;
import com.bcstudents.personnelmanagement.service.IDeptService;
import com.bcstudents.personnelmanagement.service.IEmpService;
import com.bcstudents.personnelmanagement.service.INationService;
import com.bcstudents.personnelmanagement.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmpController {
    @Autowired
    IEmpService empService;

    @Autowired
    IDeptService deptService;

    @Autowired
    INationService nationService;

    @Autowired
    IPositionService positionService;

    // search all worker
    @GetMapping("/emps")
    public String list(Model model,@RequestParam(value="pageNum",defaultValue="1") Integer pageNum) {

        // place in the request place
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum= PaginationConstant.CURRENT_NUM;
        }
        //set page
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        //search data
        List<Emp> emps = empService.getEmpAndDept();
        //place the result into PageInfo
        PageInfo<Emp> pageInfo=new PageInfo<>(emps);
        //use model to pass
        model.addAttribute("pageInfo",pageInfo);
        return "emp/list";
    }

    // add
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        // show page list
        List<Dept> depts = deptService.getAllDepts();
        model.addAttribute("depts",depts);
        // show all nation
        List<Nation> nations = nationService.getAllNations();
        model.addAttribute("nations",nations);
        // show all position
        List<Position> positions = positionService.getAllPositions();
        model.addAttribute("positions",positions);
        return "emp/add";
    }

    // add emp
    @PostMapping("/emp")
    public String addEmp(Emp emp) {
        // save
        empService.addEmp(emp);
        // return to emps page
        return "redirect:/emps";
    }

    // update
    @GetMapping("/emp/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id,Model model) {
        Emp emp = empService.getEmpById(id);
        model.addAttribute("emp",emp);

        // show all dep
        List<Dept> depts = deptService.getAllDepts();
        model.addAttribute("depts",depts);
        // show all nation
        List<Nation> nations = nationService.getAllNations();
        model.addAttribute("nations",nations);
        // show all position
        List<Position> positions = positionService.getAllPositions();
        model.addAttribute("positions",positions);
        // back to add page
        return "emp/add";
    }

    // emps change need id
    @PutMapping("/emp")
    public String updateEmp(Emp emp) {
        empService.updateEmp(emp);
        return "redirect:/emps";
    }

    //emp delete
    @DeleteMapping("/emp/{id}")
    public String deleteEmpById(@PathVariable("id") Integer id){
        empService.deleteEmpById(id);
        return "redirect:/emps";
    }


    // search up emp
    @PostMapping("/search")//submit list
    public String queryEmp(@RequestParam String name, Model model,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum){
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        //search data
        List<Emp> emps=empService.query(name);
        model.addAttribute("emps",emps);//pass to frontend
        PageInfo<Emp> pageInfo=new PageInfo<>(emps);
        //use model transfer
        model.addAttribute("pageInfo",pageInfo);
        return "emp/list";
    }
}
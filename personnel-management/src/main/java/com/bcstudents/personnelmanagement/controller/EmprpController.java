package com.xtu.hrms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtu.hrms.bean.Emp;
import com.xtu.hrms.bean.Emprp;
import com.xtu.hrms.config.PaginationConstant;
import com.xtu.hrms.service.IEmpService;
import com.xtu.hrms.service.IEmprpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmprpController {
    @Autowired
    IEmprpService emprpService;

    @Autowired
    IEmpService empService;
    // search all
    @GetMapping("/emprps")
    public String list(Model model,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum) {

        if(ObjectUtils.isEmpty(pageNum)){
            pageNum= PaginationConstant.CURRENT_NUM;
        }
        //set page
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        //search data
        List<Emprp> emprps = emprpService.getEmprpAndEmp();

        PageInfo<Emprp> pageInfo=new PageInfo<>(emprps);

        //use model to pass
        model.addAttribute("pageInfo",pageInfo);
        return "emprp/emprp";
    }

    // to add page
    @GetMapping("/emprp")
    public String toAddPage(Model model) {
        List<Emp> emps = empService.getAllEmps();
        model.addAttribute("emps",emps);

        return "emprp/add";
    }

    // add
    @PostMapping("/emprp")
    public String addEmprp(Emprp emprp) {
        // save
        emprpService.addEmprp(emprp);
        // list page
        return "redirect:/emprps";
    }

    // change page or update
    @GetMapping("/emprp/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
        Emprp emprp = emprpService.getEmprpById(id);
        model.addAttribute("emprp",emprp);

        List<Emp> emps = empService.getAllEmps();
        model.addAttribute("emps",emps);

        // return to add page
        return "emprp/add";
    }

    // change
    @PutMapping("/emprp")
    public String updateEmprp(Emprp emprp) {
        emprpService.updateEmprp(emprp);
        return "redirect:/emprps";
    }

    //delete
    @DeleteMapping("/emprp/{id}")
    public String deleteEmprpById(@PathVariable("id") Integer id){
        emprpService.deleteEmprpById(id);
        return "redirect:/emprps";
    }

    @PostMapping("/rpsearch")//submit list and display in frontend
    public String queryEmprp(@RequestParam String name, Model model,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum){
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        //search date
        List<Emprp> emprps = emprpService.query(name);

        PageInfo<Emprp> pageInfo=new PageInfo<>(emprps);

        model.addAttribute("pageInfo",pageInfo);
        return "emprp/emprp";
    }
}
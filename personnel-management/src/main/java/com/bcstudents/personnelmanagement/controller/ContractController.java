package com.bcstudents.personnelmanagement.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bcstudents.personnelmanagement.bean.*;
import com.bcstudents.personnelmanagement.PaginationConstant;
import com.bcstudents.personnelmanagement.IContractService;
import com.bcstudents.personnelmanagement.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContractController {
    @Autowired
    IContractService contractService;

    @Autowired
    IEmpService empService;

    //  search list and back to dashboard
    @GetMapping("/contracts")
    public String list(Model model,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum) {
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum= PaginationConstant.CURRENT_NUM;
        }
        //set starpage
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        //search data
        List<Contract> contracts = contractService.getContractAndEmp();
        PageInfo<Contract> pageInfo=new PageInfo<>(contracts);
        //use model to transfer back to page
        model.addAttribute("pageInfo",pageInfo);
        return "contract/contract";
    }

    // add page
    @GetMapping("/contract")
    public String toAddPage(Model model) {
        // ist all emps
        List<Emp> emps = empService.getAllEmps();
        model.addAttribute("emps",emps);
        return "contract/add";
    }

    // contract add
    @PostMapping("/contract")
    public String addContract(Contract contract) {
        // save
        contractService.addContract(contract);
        // contract page
        return "redirect:/contracts";
    }

    //update contract show in frontend
    @GetMapping("/contract/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id,Model model) {
        Contract contract = contractService.getContractById(id);
        model.addAttribute("contract",contract);

        // list all emps
        List<Emp> emps = empService.getAllEmps();
        model.addAttribute("emps",emps);
        // back to add page
        return "contract/add";
    }

    // contract change need id
    @PutMapping("/contract")
    public String updateContract(Contract contract) {
        contractService.updateContract(contract);
        return "redirect:/contracts";
    }

    //delete
    @DeleteMapping("/contract/{id}")
    public String deleteContractById(@PathVariable("id") Integer id){
        contractService.deleteContractById(id);
        return "redirect:/contracts";
    }

    @PostMapping("/consearch")//submit and show in frontend
    public String queryContract(@RequestParam String name, Model model,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum){
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        //search data
        List<Contract> contracts = contractService.query(name);

        PageInfo<Contract> pageInfo=new PageInfo<>(contracts);

        model.addAttribute("pageInfo",pageInfo);
        return "contract/contract";
    }
}

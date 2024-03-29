package com.bcstudents.personnelmanagement.controller;

import com.bcstudents.personnelmanagement.dao.DepartmentDao;
import com.bcstudents.personnelmanagement.dao.EmployeeDao;
import com.bcstudents.personnelmanagement.pojo.Department;
import com.bcstudents.personnelmanagement.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmpController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
        return "list1";
    }
    @GetMapping("/emp")
    public String Addpage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }
@PostMapping("/Emp")
public String addEmp(Employee employee) {
        System.out.println("save=>" + employee);
    employeeDao.save(employee);
    return "redirect:/emps";
}

@GetMapping("“/emp/{id}”")
    public String toUpdateEmp(@PathVariable("id")Integer id, Model model) {
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("”emp”", employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/update";
    }
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
}

package com.bcstudents.personnelmanagement.mapper;

import com.bcstudents.personnelmanagement.bean.Emp;

import java.util.List;


public interface EmpMapper {
    //    Retrieve employee by id
    Emp getEmpById(Integer id);

    Emp getEmpAndDeptById(Integer id);

    //    Retrieve employee by workID
    Emp getEmpByWorkID(Integer workID);

    //    Retrieve all employees
    List<Emp> getAllEmps();

    List<Emp> getEmpAndDept();

    //    Delete employee by id
    int deleteEmpById(Integer id);

    //    Add employee
    int addEmp(Emp emp);

    //    Update employee information
    int updateEmp(Emp emp);

    List<Emp> query(String name);
}

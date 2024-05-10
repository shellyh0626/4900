package com.bcstudents.personnelmanagement.mapper;

import com.bcstudents.personnelmanagement.bean.Salary;

import java.util.List;

public interface SalaryMapper {
    //    Retrieve by id
    Salary getSalaryById(Integer id);

    //    Retrieve all
    List<Salary> getAllSalarys();

    //    Delete by id
    int deleteSalaryById(Integer id);

    //    Add
    int addSalary(Salary salary);

    //    Update
    int updateSalary(Salary salary);
}

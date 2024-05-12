package com.bcstudents.personnelmanagement.service;

import com.bcstudents.personnelmanagement.bean.Salary;

import java.util.List;

public interface ISalaryService {
    Salary getSalaryById(Integer id);

    List<Salary> getAllSalarys();

    int deleteSalaryById(Integer id);

    int addSalary(Salary salary);

    int updateSalary(Salary salary);
}

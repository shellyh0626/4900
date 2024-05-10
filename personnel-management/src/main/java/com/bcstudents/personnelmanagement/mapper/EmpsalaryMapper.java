package com.bcstudents.personnelmanagement.mapper;

import com.bcstudents.personnelmanagement.bean.Empsalary;

import java.util.List;

public interface EmpsalaryMapper {
    //    Retrieve employee salary by id
    Empsalary getEmpsalaryById(Integer id);

    //    Retrieve all employee salaries
    List<Empsalary> getAllEmpsalarys();

    List<Empsalary> getAll();

    //    Delete employee salary by id
    int deleteEmpsalaryById(Integer id);

    //    Add employee salary
    int addEmpsalary(Empsalary empsalary);

    //    Update salary
    int updateEmpsalary(Empsalary empsalary);

    List<Empsalary> query(String name);
}
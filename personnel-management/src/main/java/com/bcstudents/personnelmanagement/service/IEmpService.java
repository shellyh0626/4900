package com.bcstudents.personnelmanagement.service;

import com.bcstudents.personnelmanagement.bean.Emp;

import java.util.List;

public interface IEmpService {
    Emp getEmpById(Integer id);

    Emp getEmpAndDeptById(Integer id);

    Emp getEmpByWorkID(Integer workID);

    List<Emp> getAllEmps();

    List<Emp> getEmpAndDept();

    int deleteEmpById(Integer id);

    int addEmp(Emp emp);

    int updateEmp(Emp emp);

    List<Emp> query(String name);
}

package com.bcstudents.personnelmanagement.mapper;

import com.bcstudents.personnelmanagement.bean.Dept;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

// Specify that this is a Mapper for database operations
@Mapper
@Repository
public interface DeptMapper {
    // Retrieve department by id
    Dept getDeptById(Integer id);

    // Retrieve all departments
    List<Dept> getAllDepts();

    // Delete department by id
    int deleteDeptById(Integer id);

    // Add department
    int addDept(Dept dept);

    // Update department
    int updateDept(Dept dept);
}

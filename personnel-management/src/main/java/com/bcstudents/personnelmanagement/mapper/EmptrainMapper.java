package com.bcstudents.personnelmanagement.mapper;

import com.bcstudents.personnelmanagement.bean.Emptrain;

import java.util.List;

public interface EmptrainMapper {
    Emptrain getEmptrainById(Integer id);

    //    Retrieve all
    List<Emptrain> getAllEmptrains();

    List<Emptrain> getEmptrainAndEmp();

    //    Delete by id
    int deleteEmptrainById(Integer id);

    //    Add
    int addEmptrain(Emptrain emptrain);

    //    Update
    int updateEmptrain(Emptrain emptrain);

    List<Emptrain> query(String name);
}
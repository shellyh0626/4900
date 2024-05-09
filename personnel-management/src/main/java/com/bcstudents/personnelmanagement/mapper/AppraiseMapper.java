package com.bcstudents.personnelmanagement.mapper;

import com.bcstudents.personnelmanagement.Appraise;

import java.util.List;

public interface AppraiseMapper {
    Appraise getAppraiseById(Integer id);

    //    Retrieve all
    List<Appraise> getAllAppraises();

    List<Appraise> getAll();

    //    Delete by id
    int deleteAppraiseById(Integer id);

    //    Add
    int addAppraise(Appraise appraise);

    //    Update
    int updateAppraise(Appraise appraise);

    List<Appraise> query(String name);
}

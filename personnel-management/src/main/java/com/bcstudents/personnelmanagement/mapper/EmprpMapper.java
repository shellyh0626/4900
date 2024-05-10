package com.bcstudents.personnelmanagement.mapper;

import com.bcstudents.personnelmanagement.bean.Emprp;

import java.util.List;

public interface    EmprpMapper {
    Emprp getEmprpById(Integer id);

    //    Retrieve all
    List<Emprp> getAllEmprps();

    List<Emprp> getEmprpAndEmp();

    //    Delete by id
    int deleteEmprpById(Integer id);

    //    Add
    int addEmprp(Emprp emprp);

    //    Update
    int updateEmprp(Emprp emprp);

    List<Emprp> query(String name);
}


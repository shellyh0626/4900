package com.bcstudents.personnelmanagement.service;

import com.bcstudents.personnelmanagement.bean.Emprp;

import java.util.List;

public interface IEmprpService {
    Emprp getEmprpById(Integer id);

    List<Emprp> getAllEmprps();

    List<Emprp> getEmprpAndEmp();

    int deleteEmprpById(Integer id);

    int addEmprp(Emprp emprp);

    int updateEmprp(Emprp emprp);

    List<Emprp> query(String name);
}


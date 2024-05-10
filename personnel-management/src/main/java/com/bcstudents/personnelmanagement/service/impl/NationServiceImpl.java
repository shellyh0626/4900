package com.bcstudents.personnelmanagement.service.impl;

import com.bcstudents.personnelmanagement.Nation;
import com.bcstudents.personnelmanagement.mapper.NationMapper;
import com.bcstudents.personnelmanagement.service.INationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationServiceImpl implements INationService {
    @Autowired
    private NationMapper nationMapper;
    public List<Nation> getAllNations() {
        return nationMapper.getAllNations();
    }
}

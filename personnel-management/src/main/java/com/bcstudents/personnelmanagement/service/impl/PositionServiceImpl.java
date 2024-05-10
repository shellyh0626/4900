package com.bcstudents.personnelmanagement.service.impl;

import com.bcstudents.personnelmanagement.Position;
import com.bcstudents.personnelmanagement.mapper.PositionMapper;
import com.bcstudents.personnelmanagement.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements IPositionService {
    @Autowired
    private PositionMapper positionMapper;

    @Override
    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }
}

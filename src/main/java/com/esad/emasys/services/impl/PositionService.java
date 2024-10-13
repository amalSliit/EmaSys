package com.esad.emasys.services.impl;

import com.esad.emasys.model.Position;
import com.esad.emasys.services.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public List<Position> getAllPositions() {
        return (List<Position>) positionRepository.findAll();
    }
}

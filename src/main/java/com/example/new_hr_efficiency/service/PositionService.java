package com.example.new_hr_efficiency.service;

import com.example.new_hr_efficiency.model.Position;
import com.example.new_hr_efficiency.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public List<Position> getPositions() {
        return positionRepository.findAll();
    }

    public Position savePosition(Position position) {
        return positionRepository.save(position);
    }

    public Position getPosition(Long id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Position not found"));
    }

    public Position updatePosition(Long id, Position position) {
        Position existingPosition = positionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Position not found"));
        existingPosition.setName(position.getName());
        existingPosition.setDescription(position.getDescription());
        existingPosition.setEmployees(position.getEmployees());
        return positionRepository.save(existingPosition);
    }

    public Position deletePosition(Long id) {
        Position existingPosition = positionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Position not found"));
        positionRepository.deleteById(id);
        return existingPosition;
    }

}

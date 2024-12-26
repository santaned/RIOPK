package com.example.new_hr_efficiency.controller;

import com.example.new_hr_efficiency.model.Employee;
import com.example.new_hr_efficiency.model.Position;
import com.example.new_hr_efficiency.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @PostMapping("/position")
    public ResponseEntity<Position> savePosition(@RequestBody Position position) {
        Position savedPosition = positionService.savePosition(position);
        return new ResponseEntity<>(savedPosition, HttpStatus.CREATED);
    }

    @GetMapping("/position/{id}")
    public ResponseEntity<Position> getPosition(@PathVariable Long id) {
        Position position;
        try {
            position = positionService.getPosition(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @GetMapping("/positions")
    public List<Position> getPositions() {
        return positionService.getPositions();
    }

    @PatchMapping("/position/{id}")
    public ResponseEntity<Position> updatePosition(@PathVariable Long id, @RequestBody Position position) {
        Position updatedPosition;
        try {
            updatedPosition = positionService.updatePosition(id, position);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedPosition, HttpStatus.OK);
    }

    @DeleteMapping("/position/{id}")
    public ResponseEntity<Position> deletePosition(@PathVariable Long id) {
        Position deletedPosition;
        try {
            deletedPosition = positionService.deletePosition(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedPosition, HttpStatus.OK);
    }

    @GetMapping("/position/{id}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByPosition(@PathVariable Long id) {
        Position position;
        try {
            position = positionService.getPosition(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(position.getEmployees(), HttpStatus.OK);
    }

}

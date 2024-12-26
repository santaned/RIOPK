package com.example.new_hr_efficiency.controller;

import com.example.new_hr_efficiency.model.Department;
import com.example.new_hr_efficiency.model.Employee;
import com.example.new_hr_efficiency.model.KPI;
import com.example.new_hr_efficiency.model.Position;
import com.example.new_hr_efficiency.service.DepartmentService;
import com.example.new_hr_efficiency.service.EmployeeService;
import com.example.new_hr_efficiency.service.KPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private KPIService kpiService;

    @PostMapping("/department")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        Department department;
        try {
            department = departmentService.getDepartment(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("/departments")
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @PatchMapping("/department/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        Department updatedDepartment;
        try {
            updatedDepartment = departmentService.updateDepartment(id, department);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable Long id) {
        Department deletedDepartment;
        try {
            deletedDepartment = departmentService.deleteDepartment(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedDepartment, HttpStatus.OK);
    }

    @GetMapping("/department/{id}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable Long id) {
        Department department;
        try {
            department = departmentService.getDepartment(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department.getEmployees(), HttpStatus.OK);
    }

    @GetMapping("/department/{id}/kpis")
    public ResponseEntity<List<KPI>> getKPIsByDepartment(@PathVariable Long id) {
        Department department;
        try {
            department = departmentService.getDepartment(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department.getKpis(), HttpStatus.OK);
    }

    @GetMapping("/department/{id}/positions")
    public ResponseEntity<List<Position>> getPositionsByDepartment(@PathVariable Long id) {
        Department department;
        try {
            department = departmentService.getDepartment(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department.getPositions(), HttpStatus.OK);
    }

}

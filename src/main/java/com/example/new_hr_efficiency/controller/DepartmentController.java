package com.example.new_hr_efficiency.controller;

import com.example.new_hr_efficiency.model.Department;
import com.example.new_hr_efficiency.service.DepartmentService;
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

    @PostMapping("/department")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department){
        Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id){
        Department department = departmentService.getDepartment(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("/departments")
    public List<Department> getDepartments(){
        return departmentService.getDepartments();
    }

    @PatchMapping("/department/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department){
        Department updatedDepartment = departmentService.updateDepartment(id, department);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable Long id){
        Department deletedDepartment = departmentService.deleteDepartment(id);
        return new ResponseEntity<>(deletedDepartment, HttpStatus.OK);
    }

}

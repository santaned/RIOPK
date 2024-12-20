package com.example.new_hr_efficiency.controller;

import com.example.new_hr_efficiency.model.Department;
import com.example.new_hr_efficiency.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private Department department;

    @BeforeEach
    public void setUp() {
        department = new Department(1L, "HR", "Human Resources", null, null);
    }

    @Test
    public void testSaveDepartment() {
        when(departmentService.saveDepartment(department)).thenReturn(department);

        ResponseEntity<Department> response = departmentController.saveDepartment(department);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("HR", response.getBody().getName());
    }

    @Test
    public void testGetDepartment() {
        when(departmentService.getDepartment(1L)).thenReturn(department);

        ResponseEntity<Department> response = departmentController.getDepartment(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("HR", response.getBody().getName());
    }

    @Test
    public void testGetDepartments() {
        List<Department> departments = Arrays.asList(department);
        when(departmentService.getDepartments()).thenReturn(departments);

        List<Department> response = departmentController.getDepartments();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("HR", response.get(0).getName());
    }

    @Test
    public void testUpdateDepartment() {
        Department updatedDepartment = new Department(1L, "HR", "Updated Human Resources", null, null);
        when(departmentService.updateDepartment(1L, updatedDepartment)).thenReturn(updatedDepartment);

        ResponseEntity<Department> response = departmentController.updateDepartment(1L, updatedDepartment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Human Resources", response.getBody().getDescription());
    }

    @Test
    public void testDeleteDepartment() {
        when(departmentService.deleteDepartment(1L)).thenReturn(department);

        ResponseEntity<Department> response = departmentController.deleteDepartment(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("HR", response.getBody().getName());
    }
}

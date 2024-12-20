package com.example.new_hr_efficiency.controller;

import com.example.new_hr_efficiency.model.Department;
import com.example.new_hr_efficiency.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class DepartmentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    public void setUp() {
        departmentService.getDepartments().forEach(department -> departmentService.deleteDepartment(department.getId()));

        department = new Department(null, "IT" + System.currentTimeMillis(), "Information Technology", null, null);
        department = departmentService.saveDepartment(department);
    }

    @Test
    public void testSaveDepartment() throws Exception {
        Department newDepartment = new Department(null, "HR" + System.currentTimeMillis(), "Human Resources", null, null);

        mockMvc.perform(post("/api/v1/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"" + newDepartment.getName() + "\", \"description\": \"" + newDepartment.getDescription() + "\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(newDepartment.getName()))
                .andExpect(jsonPath("$.description").value(newDepartment.getDescription()));
    }

    @Test
    public void testGetDepartment() throws Exception {
        mockMvc.perform(get("/api/v1/department/{id}", department.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(department.getName()))
                .andExpect(jsonPath("$.description").value(department.getDescription()));
    }

    @Test
    public void testGetDepartments() throws Exception {
        Department department2 = new Department(null, "Finance" + System.currentTimeMillis(), "Finance Department", null, null);
        departmentService.saveDepartment(department2);

        mockMvc.perform(get("/api/v1/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(department.getName()))
                .andExpect(jsonPath("$[1].name").value(department2.getName()));
    }

    @Test
    public void testUpdateDepartment() throws Exception {
        Department updatedDepartment = new Department(department.getId(), "Updated IT" + System.currentTimeMillis(), "Updated Information Technology", null, null);

        mockMvc.perform(patch("/api/v1/department/{id}", department.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"" + updatedDepartment.getName() + "\", \"description\": \"" + updatedDepartment.getDescription() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updatedDepartment.getName()))
                .andExpect(jsonPath("$.description").value(updatedDepartment.getDescription()));
    }

    @Test
    public void testDeleteDepartment() throws Exception {
        mockMvc.perform(delete("/api/v1/department/{id}", department.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(department.getName()))
                .andExpect(jsonPath("$.description").value(department.getDescription()));
    }
}

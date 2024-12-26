package com.example.new_hr_efficiency.controller;

import com.example.new_hr_efficiency.model.Department;
import com.example.new_hr_efficiency.repository.DepartmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DepartmentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        departmentRepository.save(new Department(null, "IT", "IT Department", null, null, null));
    }

    @AfterEach
    void tearDown() {
        departmentRepository.deleteAll();
    }

    @Test
    void shouldGetDepartmentById() throws Exception {
        Department department = departmentRepository.findAll().get(0);

        mockMvc.perform(get("/api/v1/department/" + department.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("IT"));
    }

    @Test
    void shouldCreateDepartment() throws Exception {
        mockMvc.perform(post("/api/v1/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"HR\",\"description\":\"HR Department\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("HR"));
    }

    @Test
    void shouldUpdateDepartment() throws Exception {
        Department department = departmentRepository.findAll().get(0);

        mockMvc.perform(patch("/api/v1/department/" + department.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated IT\",\"description\":\"Updated IT Department\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated IT"));
    }

    @Test
    void shouldDeleteDepartment() throws Exception {
        Department department = departmentRepository.findAll().get(0);

        mockMvc.perform(delete("/api/v1/department/" + department.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAllDepartments() throws Exception {
        mockMvc.perform(get("/api/v1/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("IT"));
    }
}

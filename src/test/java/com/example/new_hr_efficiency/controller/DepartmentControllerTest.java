package com.example.new_hr_efficiency.controller;

import com.example.new_hr_efficiency.model.Department;
import com.example.new_hr_efficiency.model.Employee;
import com.example.new_hr_efficiency.model.KPI;
import com.example.new_hr_efficiency.model.Position;
import com.example.new_hr_efficiency.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DepartmentControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    void shouldSaveDepartment() throws Exception {
        Department department = new Department(1L, "IT", "IT Department", Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        when(departmentService.saveDepartment(any(Department.class))).thenReturn(department);

        mockMvc.perform(post("/api/v1/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"IT\",\"description\":\"IT Department\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("IT"));
    }

    @Test
    void shouldGetDepartmentById() throws Exception {
        Department department = new Department(1L, "IT", "IT Department", Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        when(departmentService.getDepartment(1L)).thenReturn(department);

        mockMvc.perform(get("/api/v1/department/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("IT"));
    }

    @Test
    void shouldReturnNotFoundForInvalidDepartmentId() throws Exception {
        when(departmentService.getDepartment(1L)).thenThrow(new RuntimeException("Department not found"));

        mockMvc.perform(get("/api/v1/department/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllDepartments() throws Exception {
        Department department = new Department(1L, "IT", "IT Department", Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        when(departmentService.getDepartments()).thenReturn(Collections.singletonList(department));

        mockMvc.perform(get("/api/v1/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("IT"));
    }

    @Test
    void shouldUpdateDepartment() throws Exception {
        Department department = new Department(1L, "Updated IT", "Updated IT Department", Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        when(departmentService.updateDepartment(eq(1L), any(Department.class))).thenReturn(department);

        mockMvc.perform(patch("/api/v1/department/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated IT\",\"description\":\"Updated IT Department\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated IT"));
    }

    @Test
    void shouldDeleteDepartment() throws Exception {
        Department department = new Department(1L, "IT", "IT Department", Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        when(departmentService.deleteDepartment(1L)).thenReturn(department);

        mockMvc.perform(delete("/api/v1/department/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("IT"));
    }

    @Test
    void shouldGetPositionsByDepartmentId() throws Exception {
        Position position = new Position(1L, "Position Name", "Position Description", null);
        Department department = new Department(1L, "IT", "IT Department", Collections.emptyList(), Collections.emptyList(), List.of(position));
        when(departmentService.getDepartment(1L)).thenReturn(department);

        mockMvc.perform(get("/api/v1/department/1/positions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Position Name"));
    }
}

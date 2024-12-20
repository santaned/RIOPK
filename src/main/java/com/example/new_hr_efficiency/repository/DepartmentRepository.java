package com.example.new_hr_efficiency.repository;

import com.example.new_hr_efficiency.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

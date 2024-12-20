package com.example.new_hr_efficiency.repository;

import com.example.new_hr_efficiency.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUsername(String username);
}

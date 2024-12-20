package com.example.new_hr_efficiency.repository;

import com.example.new_hr_efficiency.model.KPI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KPIRepository extends JpaRepository<KPI, Long> {
}

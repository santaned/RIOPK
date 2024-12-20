package com.example.new_hr_efficiency.repository;

import com.example.new_hr_efficiency.model.KPIValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KPIValueRepository extends JpaRepository<KPIValue, Long> {
}

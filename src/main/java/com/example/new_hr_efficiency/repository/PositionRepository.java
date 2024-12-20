package com.example.new_hr_efficiency.repository;

import com.example.new_hr_efficiency.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}

package com.example.new_hr_efficiency.repository;

import com.example.new_hr_efficiency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    User findByActivationCode(String code);
}

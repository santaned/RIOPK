package com.example.new_hr_efficiency.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name of department can not be null")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @NotBlank(message = "Description of department can not be null")
    @Column(name = "description", unique = true, nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "department")
    private List<Employee> employees;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "department")
    private List<KPI> kpis;

}

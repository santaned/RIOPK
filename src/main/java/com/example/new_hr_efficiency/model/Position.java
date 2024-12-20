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
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name of position can not be null")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @NotBlank(message = "Description of position can not be null")
    @Column(name = "description", unique = true, nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "position")
    private List<Employee> employees;

}

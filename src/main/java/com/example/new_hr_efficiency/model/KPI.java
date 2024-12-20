package com.example.new_hr_efficiency.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "kpi")
public class KPI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name of KPI can not be null")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @NotBlank(message = "Description of KPI can not be null")
    @Column(name = "description", unique = true, nullable = false)
    private String description;

    @NotNull(message = "Target value of KPI can not be null")
    @Column(name = "targetValue", nullable = false)
    private Float targetValue;

    @NotBlank(message = "Description of KPI can not be null")
    @Column(name = "measurement_unit", nullable = false)
    private String measurementUnit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "kpi")
    private List<KPIValue> kpiValues;

    public KPI(long l, String kpiName, String kpiDescription, double v, double v1) {
    }
}

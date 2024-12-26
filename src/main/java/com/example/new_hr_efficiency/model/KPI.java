package com.example.new_hr_efficiency.model;

import jakarta.persistence.*;
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

    //    @NotBlank(message = "Name of KPI can not be null")
    @Column(name = "name")
    private String name;

    //    @NotBlank(message = "Description of KPI can not be null")
    @Column(name = "description")
    private String description;

    //    @NotBlank(message = "Description of KPI can not be null")
    @Column(name = "measurement_unit")
    private String measurementUnit;

    //    @NotNull(message = "Target value of KPI can not be null")
    @Column(name = "targetValue")
    private Float targetValue;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "kpi_id")
    private List<KPIValue> kpiValues;

    public KPI(long l, String kpiName, String kpiDescription, double v, double v1) {
    }
}

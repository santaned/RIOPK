package com.example.new_hr_efficiency.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    //    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
//    @NotEmpty(message = "Email cannot be empty")
    @Column(name = "email")
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Roles roles;

    //    @NotBlank(message = "First name can not be null")
//    @Column(name = "first_name", nullable = false)
    private String firstName;

    //    @NotBlank(message = "Last name can not be null")
//    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "last_login_date")
    private Date lastLoginDate;

    @Column(name = "registration_date")
    private Date registrationDate;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "employee_id")
    private List<KPIValue> kpiValues;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}

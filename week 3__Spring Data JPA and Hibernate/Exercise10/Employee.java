package com.example.employeemanagementsystem.model;

import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToOne
    private Department department;

    @Formula("(select count(*) from department where department.id = department_id)")
    private int departmentEmployeeCount;

    // Getters and Setters
    // ...
}

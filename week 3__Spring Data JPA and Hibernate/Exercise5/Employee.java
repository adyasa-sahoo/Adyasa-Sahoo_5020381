package com.example.employeemanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(
        name = "Employee.findByDepartmentNameNamed",
        query = "SELECT e FROM Employee e WHERE e.department.name = :departmentName"
    ),
    @NamedQuery(
        name = "Employee.findByEmailDomainNamed",
        query = "SELECT e FROM Employee e WHERE e.email LIKE %:domain"
    )
})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}

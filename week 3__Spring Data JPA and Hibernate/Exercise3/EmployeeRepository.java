package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find employees by name containing a specific string (case-insensitive)
    List<Employee> findByNameContainingIgnoreCase(String name);

    // Find employees by department name
    List<Employee> findByDepartmentName(String departmentName);

    // Find employees with an email that ends with a specific domain
    List<Employee> findByEmailEndingWith(String domain);
}

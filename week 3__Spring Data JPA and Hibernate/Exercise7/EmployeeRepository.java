package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Method to retrieve a paginated and sorted list of employees
    Page<Employee> findAll(Pageable pageable);
}

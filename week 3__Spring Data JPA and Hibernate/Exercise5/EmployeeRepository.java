package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Execute named query to find employees by department name
    List<Employee> findByDepartmentNameNamed(@Param("departmentName") String departmentName);

    // Execute named query to find employees by email domain
    List<Employee> findByEmailDomainNamed(@Param("domain") String domain);
}

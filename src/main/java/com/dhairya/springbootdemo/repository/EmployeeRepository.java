package com.dhairya.springbootdemo.repository;

import com.dhairya.springbootdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByDepartment(String department);
    List<Employee> findByDepartmentAndName(String department, String name);
}

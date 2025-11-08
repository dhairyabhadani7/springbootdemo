package com.dhairya.springbootdemo.controller;

import com.dhairya.springbootdemo.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping("/employee")
    public Employee getEmployee(){
        return new Employee(101,"Dhairya","IT");
    }

    @PostMapping("/employee")
    public String createEmployee(@RequestBody Employee emp){
        return "Employee created: " + emp.getName() + " (" + emp.getDepartment() + ")";
    }
}

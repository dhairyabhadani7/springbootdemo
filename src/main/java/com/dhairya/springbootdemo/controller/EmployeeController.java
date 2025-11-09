package com.dhairya.springbootdemo.controller;

import com.dhairya.springbootdemo.model.Employee;
import com.dhairya.springbootdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    //CREATE
    @PostMapping
    public Employee createEMployee(@Validated  @RequestBody Employee emp){
        return employeeService.addEmployee(emp);
    }

    //READ ALL
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //READ ONE
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id){
        return  employeeService.getEmployeeById(id);
    }

    //UPDATE
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id,@Validated @RequestBody Employee emp){
        //System.out.println("Received in PUT: id=" + id + ", name=" + emp.getName() + ", dept=" + emp.getDepartment());
        emp.setId(id);
        return employeeService.updateEmployee(id,emp);
    }
    //DELETE

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id){
        boolean deleted = employeeService.deleteEmployee(id);
        return deleted ? "Employee deleted successfully!" : "Employee not found!";
    }


    }


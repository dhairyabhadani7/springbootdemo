package com.dhairya.springbootdemo.controller;

import com.dhairya.springbootdemo.model.Employee;
import com.dhairya.springbootdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<Employee> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return employeeService.getAllEmployees(page, size, sortBy, sortDir);
    }

    //READ ONE
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id){
        return  employeeService.getEmployeeById(id);
    }

    // Search by department
    @GetMapping("/search")
    public List<Employee> findByDepartment(@RequestParam String department) {
        return employeeService.findByDepartment(department);
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
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }


    }


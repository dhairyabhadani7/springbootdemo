package com.dhairya.springbootdemo.service;

import com.dhairya.springbootdemo.exception.ResourceNotFoundException;
import com.dhairya.springbootdemo.model.Employee;
import com.dhairya.springbootdemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;


@Service
public class EmployeeService {
//    private Map<Integer, Employee> employeeMap = new HashMap<>();
//
//    //CREATE
//
//    public Employee addEmpployee(Employee emp){
//        employeeMap.put(emp.getId(),emp);
//        return emp;
//    }
//
//    //READ ALL
//
//    public List<Employee> getAllEmployees(){
//        return new ArrayList<>(employeeMap.values());
//    }
//
//    //READ ONE
//
//    public Employee getEmployee(int id){
//        return employeeMap.get(id);
//    }
//
//    //UPDATE
//    public Employee updateEmployee(int id, Employee updatedEmp){
//        Employee existingEmp = employeeMap.get(id);
//        if(existingEmp!=null){
//            existingEmp.setName(updatedEmp.getName());
//            existingEmp.setDepartment(updatedEmp.getDepartment());
//            return existingEmp;
//        }
//        return null;
//    }
//
//    //DELETE
//
//    public boolean deleteEmployee(int id){
//        return employeeMap.remove(id)!=null;
//    }

    @Autowired
    private EmployeeRepository employeeRepository;

    // CREATE
    public Employee addEmployee(Employee emp) {
        return employeeRepository.save(emp);
    }

    // READ ALL
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // READ ONE
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Employee not found with id: " + id));
    }

    // UPDATE
    public Employee updateEmployee(Integer id, Employee updatedEmp) {
        Employee existing  = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with id: " + id));
        existing.setName(updatedEmp.getName());
        existing.setDepartment(updatedEmp.getDepartment());
            return employeeRepository.save(existing);
        }


    // DELETE
    public boolean deleteEmployee(Integer id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }



}

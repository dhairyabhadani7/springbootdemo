package com.dhairya.springbootdemo.service;

import com.dhairya.springbootdemo.exception.ResourceNotFoundException;
import com.dhairya.springbootdemo.model.Employee;
import com.dhairya.springbootdemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;


@Service
public class EmployeeService {
//    private Map<Integer, Employee> employeeMap = new HashMap<>();
//
//    //CREATE
//
//    public Employee addEmployee(Employee emp){
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
    public Page<Employee> getAllEmployees(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page ,size ,sort);
        return employeeRepository.findAll(pageable);
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
    public void deleteEmployee(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    // Search by department
    public List<Employee> findByDepartment(String dept) {
        return employeeRepository.findByDepartment(dept);
    }
    //Search by Department and Name
    public List<Employee> findByDepartmentAndName(String dept, String name){
        return employeeRepository.findByDepartmentAndName(dept,name);
    }
}

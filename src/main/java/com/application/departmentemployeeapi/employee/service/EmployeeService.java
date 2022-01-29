package com.application.departmentemployeeapi.employee.service;

import com.application.departmentemployeeapi.employee.entity.Employee;
import com.application.departmentemployeeapi.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee>getAllEmployees(String departmentId)
    {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findByDepartmentId(departmentId).forEach(employees::add);
        return employees;
    }
    public Employee getEmployee(String id)
    {
        return employeeRepository.findById(id).orElse(null);
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }
}

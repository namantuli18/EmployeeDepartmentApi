package com.application.departmentemployeeapi.employee.controller;

import com.application.departmentemployeeapi.department.entity.Department;
import com.application.departmentemployeeapi.employee.service.EmployeeService;
import com.application.departmentemployeeapi.employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/departments/{id}/employees")
    public List<Employee> getAllEmployees(@PathVariable  String id)
    {
        return employeeService.getAllEmployees(id);
    }

    @RequestMapping("/departments/{departmentId}/employees/{id}")
    public Employee getEmployee(@PathVariable  String id)
    {
        return employeeService.getEmployee(id);
    }

    @RequestMapping(value = "/departments/{departmentId}/employees",method = RequestMethod.POST)
    public Employee addEmployee(@RequestBody Employee employee, @PathVariable  String departmentId)
    {
        employee.setDepartment(new Department(departmentId,"",""));
         return employeeService.addEmployee(employee);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "departments/{departmentId}/employees/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable  String departmentId)
    {
        employee.setDepartment(new Department(departmentId,"",""));
        return employeeService.updateEmployee(employee);
    }

    @RequestMapping(value = "departments/{departmentId}/employees/{id}",method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable  String id)
    {
        employeeService.deleteEmployee(id);
    }
}

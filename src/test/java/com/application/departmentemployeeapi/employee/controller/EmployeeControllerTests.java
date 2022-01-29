package com.application.departmentemployeeapi.employee.controller;

import com.application.departmentemployeeapi.employee.entity.Employee;
import com.application.departmentemployeeapi.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {EmployeeControllerTests.class})
public class EmployeeControllerTests {
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;
    List<Employee> employees;
    Employee employee;

    @Test
    public void test_getAllDepartments()
    {   List<Employee> myEmployees = new ArrayList<Employee>();
        myEmployees.add(new Employee("T2", "Mohan", "Manager","D1"));
        myEmployees.add(new Employee("T1", "Suresh", "Senior","D1"));
        String id = "D1";
        when(employeeService.getAllEmployees(id)).thenReturn(myEmployees);
        List<Employee> response = employeeController.getAllEmployees(id);
        assertEquals(2,response.size());
    }

    @Test
    public void test_getEmployee()
    {
        Employee employee = new Employee("T2", "Mohan", "Manager","D1");
        String id = "T2";
        when(employeeService.getEmployee(id)).thenReturn(employee);
        Employee employee1 =  employeeController.getEmployee(id);
        assertEquals(id,employee1.getId());
    }

    @Test
    public void test_addEmployee()
    {
        Employee employee = new Employee("T2", "Mohan", "Manager","D1");
        String departmentId = "D1";
        when(employeeService.addEmployee(employee)).thenReturn(employee);
        assertEquals(employee,employeeController.addEmployee(employee,departmentId));
    }

    @Test
    public void test_updateEmployee()
    {
        Employee employee = new Employee("T2", "Mohan", "Manager","D1");
        String id = "T2";
        when(employeeService.getEmployee(id)).thenReturn(employee);
        when(employeeService.updateEmployee(employee)).thenReturn(employee);

        assertEquals(employee,employeeController.updateEmployee(employee,id));

    }

}

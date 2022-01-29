package com.application.departmentemployeeapi.employee.service;

import com.application.departmentemployeeapi.department.service.DepartmentServiceTests;
import com.application.departmentemployeeapi.employee.entity.Employee;
import com.application.departmentemployeeapi.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes= DepartmentServiceTests.class)

public class EmployeeServiceTests {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void test_getAllEmployees() {
        List<Employee> myEmployee = new ArrayList<Employee>();
        myEmployee.add(new Employee("T1", "Aman", "Senior","D1"));
        myEmployee.add(new Employee("T2", "Harris", "Intern","D1"));

        String departmentId = "D1";
        when(employeeRepository.findByDepartmentId(departmentId)).thenReturn(myEmployee);
        assertEquals(2, employeeService.getAllEmployees("D1").size());
    }

    @Test
    public void test_addEmployee() {
        Employee employee = new Employee("T1", "Aman", "Senior","D1");
        when(employeeRepository.save(employee)).thenReturn(employee);
        assertEquals(employee, employeeService.addEmployee(employee));
    }

    @Test
    public void test_updateEmployee() {
        Employee employee = new Employee("T1", "Aman", "Senior","D1");
        when(employeeRepository.save(employee)).thenReturn(employee);
        assertEquals(employee, employeeService.updateEmployee(employee));
    }

    @Test
    public void test_deleteEmployee() {
        String id = "T1";
        employeeService.deleteEmployee(id);
        verify(employeeRepository, times(1)).deleteById(id);
    }
}

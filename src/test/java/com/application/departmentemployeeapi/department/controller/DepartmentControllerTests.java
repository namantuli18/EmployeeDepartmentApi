package com.application.departmentemployeeapi.department.controller;

import com.application.departmentemployeeapi.department.entity.Department;
import com.application.departmentemployeeapi.department.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DepartmentControllerTests.class})
public class DepartmentControllerTests {

    @Mock
    DepartmentService departmentService;

    @InjectMocks
    DepartmentController departmentController;
    List<Department> myDepartments;
    Department department;

    @Test
    public void test_getAllDepartments()
    {   List<Department> myDepartments = new ArrayList<Department>();
        myDepartments.add(new Department("D1", "Money", "Finance"));
        myDepartments.add(new Department("D2", "Travel", "Tech"));
        when(departmentService.getAllDepartments()).thenReturn(myDepartments);
        ResponseEntity<List<Department>> response = departmentController.getAllDepartments();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(2,response.getBody().size());

    }

    @Test
    public void test_getDepartment()
    {
        Department department = new Department("D1", "Money", "Finance");
        String id = "D1";
        when(departmentService.getDepartment(id)).thenReturn(department);
        ResponseEntity<Department> response =  departmentController.getDepartment(id);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(id,response.getBody().getId());

    }

    @Test
    public void test_addDepartment()
    {
        Department department = new Department("D1", "Money", "Finance");
        when(departmentService.addDepartment(department)).thenReturn(department);
        ResponseEntity<Department> response =  departmentController.addDepartment(department);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(department,response.getBody());

    }
    @Test
    public void test_updateDepartment()
    {
        Department department = new Department("D1", "Money", "Finance");
        String id = "D1";
        String name = "Money";
        String category = "Finance";
        when(departmentService.getDepartment(id)).thenReturn(department);
        when(departmentService.updateDepartment(department,id)).thenReturn(department);
        ResponseEntity<Department> response =  departmentController.updateDepartment(department,id);

        assertEquals(HttpStatus.OK,response.getStatusCode());

    }

    @Test
    public void test_deleteDepartment()
    {
        Department department = new Department("D1", "Money", "Finance");
        String id = "D1";

        when(departmentService.getDepartment(id)).thenReturn(department);
        ResponseEntity<Void> response =  departmentController.deleteDepartment(id);

        assertEquals(HttpStatus.GONE,response.getStatusCode());

    }

}

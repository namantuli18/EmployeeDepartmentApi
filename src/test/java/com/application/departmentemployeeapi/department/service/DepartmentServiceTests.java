package com.application.departmentemployeeapi.department.service;

import com.application.departmentemployeeapi.department.entity.Department;
import com.application.departmentemployeeapi.department.repository.DepartmentRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes= DepartmentServiceTests.class)
@AutoConfigureMockMvc

public class DepartmentServiceTests {


    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    DepartmentService departmentService;

    @Test
    public void contextLoads() {
    }

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private MockMvc mockMvc;

    @Before("")
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }


    @Test
    public void test_getAllDepartments() {
        List<Department> myDepartments = new ArrayList<Department>();
        myDepartments.add(new Department("D1", "Money", "Finance"));
        myDepartments.add(new Department("D2", "Travel", "Tech"));
        when(departmentRepository.findAll()).thenReturn(myDepartments);

        assertEquals(2, departmentService.getAllDepartments().size());
    }

    @Test
    public void test_getDepartment() {
        Department department = new Department("D1", "Money", "Finance");
        String id = "D1";
        when(departmentRepository.findById(id)).thenReturn(Optional.of(department));
        assertEquals(id, departmentService.getDepartment("D1").getId());
    }

    @Test
    public void test_addDepartment() {
        Department department = new Department("D3", "R&D", "Research");
        when(departmentRepository.save(department)).thenReturn(department);
        assertEquals(department, departmentService.addDepartment(department));
    }

    @Test
    public void test_updateDepartment() {
        Department department = new Department("D3", "R&D", "Research");
        when(departmentRepository.save(department)).thenReturn(department);
        assertEquals(department, departmentService.updateDepartment(department, "D3"));
    }

    @Test
    public void test_deleteDepartment() {
        String id = "D3";
        departmentService.deleteDepartment(id);
        verify(departmentRepository, times(1)).deleteById(id);
    }

    @Test
    public void whenUploadFileSuccess() throws Exception {
//        try {
//            mockMvc.perform(
//                            MockMvcRequestBuilders
//                                    .multipart("/upload")
//                                    .file(
//                                            new MockMultipartFile("file", "test.csv", ",multipart/form-data", "id,name,department".getBytes())
//                                    )
//                    ).andExpect(MockMvcResultMatchers.status().isOk());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

}

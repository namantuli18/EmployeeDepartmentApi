package com.application.departmentemployeeapi.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/departments")
    public List<Department> getAllDepartments()
    {
        return departmentService.getAllDepartments();
    }

    @RequestMapping("/departments/{id}")
    public Department getDepartment(@PathVariable  String id)
    {
        return departmentService.getDepartment(id);
    }
    @RequestMapping(value = "/departments",method = RequestMethod.POST)
    public void addDepartment(@RequestBody Department department)
    {
        departmentService.addDepartment(department);
    }
    @RequestMapping(method = RequestMethod.PUT,value = "departments/{id}")
    public void updateDepartment(@RequestBody Department department,@PathVariable String id)
    {
        departmentService.updateDepartment(department,id);
    }

    @RequestMapping(value = "/departments/{id}",method = RequestMethod.DELETE)
    public void deleteDepartment(@PathVariable  String id)
    {
        departmentService.deleteDepartment(id);
    }
}

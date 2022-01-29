package com.application.departmentemployeeapi.department.repository;

import com.application.departmentemployeeapi.department.entity.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<Department,String> {
    Department getById(String id);

}

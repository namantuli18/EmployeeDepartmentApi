package com.application.departmentemployeeapi.employee;

import com.application.departmentemployeeapi.department.Department;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
    @Id
    private String id;
    private String name;
    private String grade;
    @ManyToOne
    private Department department;




    public Employee() {

    }

    public Employee(String id, String name, String grade,String departmentId) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.department = new Department(departmentId,"","");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

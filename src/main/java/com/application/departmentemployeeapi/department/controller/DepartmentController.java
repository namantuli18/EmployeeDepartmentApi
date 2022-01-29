package com.application.departmentemployeeapi.department.controller;

import com.application.departmentemployeeapi.department.service.DepartmentService;
import com.application.departmentemployeeapi.department.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.logging.Logger;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@RestController
public class DepartmentController {
    private final static Logger logger = Logger.getLogger(String.valueOf(DepartmentController.class));

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        if (departments.isEmpty()) {
            logger.info("No department exists");
            return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
        }
        logger.info("Found " + departments.size() + " Departments");
        logger.info(String.valueOf(departments));
        logger.info(Arrays.toString(departments.toArray()));
        return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);

    }

    @RequestMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable String id) {
        Department department = departmentService.getDepartment(id);
        if (department == null) {
            logger.info("Department with id " + id + " does not exist");
            return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
        }
        logger.info("Found Department:: " + department);
        return new ResponseEntity<Department>(department, HttpStatus.OK);
    }

    @RequestMapping(value = "/departments", method = RequestMethod.POST)
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
        logger.info("Added:: " + department);
        return new ResponseEntity<Department>(department, HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "departments/{id}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department, @PathVariable String id) {
        Department newDepartment = departmentService.getDepartment(id);
        if (newDepartment == null) {
            logger.info("Department with id " + newDepartment.getId() + " does not exist");
            return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
        } else {
            departmentService.addDepartment(department);
            return new ResponseEntity<Department>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteDepartment(@PathVariable String id) {
        Department department = departmentService.getDepartment(id);
        if (department == null) {
            logger.info("Department with id " + id + " does not exist");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        } else {
            departmentService.deleteDepartment(id);
            logger.info("Department with id " + id + " deleted");
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/upload")
    public void uploadFile(
            @RequestParam(value = "file") MultipartFile files) throws Exception {

            departmentService.saveDepartments(files.getInputStream());

    }

    @RequestMapping (method = RequestMethod.GET,value = "/getAllFile")
    public @ResponseBody
    CompletableFuture<ResponseEntity> getAllUpload() {
        return departmentService.getAllUpload().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetDeptFailure);
    }

    private static Function<Throwable, ResponseEntity<? extends List<Department>>> handleGetDeptFailure = throwable -> {
        LOGGER.error("Failed to read records: "+ throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };



}


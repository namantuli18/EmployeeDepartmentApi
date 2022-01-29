package com.application.departmentemployeeapi.department.service;

import com.application.departmentemployeeapi.department.repository.DepartmentRepository;
import com.application.departmentemployeeapi.department.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    public List<Department>getAllDepartments()
    {
        List<Department> departments = new ArrayList<>();
        departmentRepository.findAll().forEach(departments::add);
        return departments;
    }

    @CachePut(value="departments")
    public Department getDepartment(String id)
    {
        return departmentRepository.findById(id).orElse(null);
    }
    @Transactional
    public Department addDepartment(Department department) {

        departmentRepository.save(department);
//        throw new DataIntegrityViolationException("Throwing exception for demoing Rollback!!!");

        return department;

    }

    public Department updateDepartment(Department department, String id) {
        departmentRepository.save(department);
        return department;
    }

    public void deleteDepartment(String id) {
        departmentRepository.deleteById(id);
    }

    public void uploadFile(MultipartFile file) throws IOException {
        file.transferTo(new File( System.getProperty("user.dir")+file.getOriginalFilename()));

    }


    private List<Department> parseCSVFile(final InputStream inputStream) throws Exception {
        final List<Department> departments=new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line=br.readLine()) != null) {
                    final String[] data=line.split(",");
                    final Department department=new Department();
                    department.setId(data[0]);
                    department.setName(data[1]);
                    department.setCategory(data[2]);
                    departments.add(department);
                }
                return departments;
            }
        } catch(final IOException e) {
            LOGGER.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }

    @Async
    public CompletableFuture<List<Department>> saveDepartments(final InputStream inputStream) throws Exception {
        final long start = System.currentTimeMillis();

        List<Department> departments = parseCSVFile(inputStream);


        LOGGER.info("Saving a list of departments of size "+ departments.size() + " records");

        departments = (List<Department>) departmentRepository.saveAll(departments);

        LOGGER.info("Elapsed time: "+(System.currentTimeMillis() - start));
        return CompletableFuture.completedFuture(departments);
    }

    @Async
    public CompletableFuture<List<Department>> getAllUpload() {

        LOGGER.info("Request to get a list of departments");

        final List<Department> departments = (List<Department>) departmentRepository.findAll();
        return CompletableFuture.completedFuture(departments);
    }

}

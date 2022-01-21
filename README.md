# EmployeeDepartmentApi

This project explains CRUD (**C**reate, **R**ead, **U**pdate, **D**elete) operations using spring boot, Apache Derby and MySql Databases.
In this app we are using Spring Data JPA for built-in methods to do CRUD operations using `@CrudRepositoryClass`.     

![Table Relationship](https://github.com/namantuli18/EmployeeDepartmentApi/blob/master/img/data%20design.png)


## Dependencies 
- Spring Web
- Spring Data JPA 
- MS SQL Server Driver / Apache Derby

## Tools
- Eclipse or IntelliJ IDEA (or any preferred IDE) with embedded Maven
- Maven (version >= 3.6.0)
- Postman (or any RESTful API testing tool)

###  Build and Run application
_GOTO >_ **~/absolute-path-to-directory**  
and run the below command in terminal
> **```mvn spring-boot:run```** it will run application as spring boot application

or
> **```mvn clean install```** it will build application and create **jar** file under target directory 

Run jar file from below path with given command
> **```java -jar ~/absolute-path-to-directory/target/spring-boot-h2-crud-0.0.1-SNAPSHOT.jar```**

Or
> run main method from `DepartmentEmployeeApiDataApplication.java` as spring boot application.  

### To use My SQL Database as backend

Create a MySQL database with the name department and add the credentials to /resources/application.properties

```
#=============================================================================
#                                    DATA SOURCE
# =============================================================================
# Connection url for the database "department"
spring.datasource.url = jdbc:mysql://localhost:3306/democrud?useSSL=false
# Username and password
spring.datasource.username=root
spring.datasource.password=****************
spring.datasource.testWhileIdle = true
spring.datasource.validationQuery = SELECT 1
# ==========================================================================
#                                  JPA / HIBERNATE
# ==========================================================================
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update
```

### API Endpoints

- #### Department CRUD Operations
    > **GET Mapping** 
    
    ```curl --location --request GET 'localhost:8080/departments' \```  - Get all Departments
    
    > **GET Mapping**
    
    ```curl --location --request GET 'localhost:8080/departments/T2' \``` - Get Department by ID
       
    > **POST Mapping**  
    
    ```curl --location --request POST 'localhost:8080/departments' \ --data-raw '{ "id": "D1", "name": "Money", "category": "Finance"}'``` - Add new Department
    
    > **PUT Mapping**
    
    ```curl --location --request PUT 'localhost:8080/departments/D2' \ --data-raw '{ "id": "D1", "name": "Money", "category": "Finance"}'``` - Update Department by ID
    
    > **DELETE Mapping**
    
   ``` curl --location --request DELETE 'localhost:8080/departments/D2' \``` - Delete Department by Id
   
 -  #### Employee CRUD Operations
    > **GET Mapping** 
    
    ```curl --location --request GET 'localhost:8080/departments/D1/employees' \ ```  - Get all Employees for a Department
    
    > **GET Mapping**
    
    ```curl --location --request GET 'localhost:8080/departments/D1/employees/T2' \``` - Get Employees for a Department by ID
       
    > **POST Mapping**  
    
    ```curl --location --request POST 'localhost:8080/departments/D1/employees' \ --data-raw '{"id": "T1","name": "Sudhakar","grade": "Junior"}'``` - Add new Employee to a Department
    
    > **PUT Mapping**
    
    ```curl --location --request PUT 'localhost:8080/departments/D1/employees/T3' \--data-raw '{"id": "T1","name": "Sudhakar","grade": "Junior"}'``` - Update Employee for a Department by EmployeeID
    
    > **DELETE Mapping**
    
   ``` curl --location --request DELETE 'localhost:8080/departments/D1/employees/T1'  \``` - Delete an Employee from a Department by Id
   
### Postman And Command Line Screenshots

![Postman2](https://github.com/namantuli18/EmployeeDepartmentApi/blob/master/img/s1.png)
![CLI](https://github.com/namantuli18/EmployeeDepartmentApi/blob/master/img/s4.png)



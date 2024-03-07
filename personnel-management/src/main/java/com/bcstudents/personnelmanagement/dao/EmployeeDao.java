package com.bcstudents.personnelmanagement.dao;

import com.bcstudents.personnelmanagement.pojo.Department;
import com.bcstudents.personnelmanagement.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository // Used for database operations in a Spring application.
// Employee data access object.
// This connects with Employee.java file under pojo package.
public class EmployeeDao {
    // Simulate data in the database
    private static Map<Integer, Employee> employees = null;
    // Department that employee belongs to
    @Autowired // Data from DepartmentDao can now transfer here
    private DepartmentDao departmentDao;
    static {
        employees = new HashMap<Integer, Employee>(); //create a employee data map
        employees.put(1001, new Employee(1001, "Morris", "morris@gmail.com", 0, new Department(101, "Human Resources Team")));
        employees.put(1002, new Employee(1002, "Huang", "huang@gmail.com", 1, new Department(102, "Marketing Team")));
        employees.put(1003, new Employee(1003, "Smith", "smith@gmail.com", 0, new Department(103, "Research Team")));
        employees.put(1004, new Employee(1004, "Chen", "chen@gmail.com", 1, new Department(104, "Technology Team")));
        employees.put(1005, new Employee(1005, "Akiyama", "akiyama@gmail.com", 0, new Department(105, "Investment Banking Team")));
    }
}

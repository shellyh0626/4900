package com.bcstudents.personnelmanagement.dao;

import com.bcstudents.personnelmanagement.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository // Used for database operations in a Spring application.
// Department data access object.
// This connects with Department.java file under pojo package.
public class DepartmentDao {
    // Simulate data in the database
    private static Map<Integer, Department> departments = null;
    static {
        departments = new HashMap<Integer, Department>(); //create a department data map
        departments.put(101, new Department(101, "Human Resources Team"));
        departments.put(102, new Department(102, "Marketing Team"));
        departments.put(103, new Department(103, "Research Team"));
        departments.put(104, new Department(104, "Technology Team"));
        departments.put(105, new Department(105, "Investment Banking Team"));
    }

    //Collect all department info
    public Collection<Department> getDepartments() {
        return departments.values();
    }

    // Get department name by id
    public Department getDepartmentById(Integer id) {
        return departments.get(id);
    }
}


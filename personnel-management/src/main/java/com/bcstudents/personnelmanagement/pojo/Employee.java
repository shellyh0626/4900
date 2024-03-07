package com.bcstudents.personnelmanagement.pojo;

import java.util.Date;

public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;  //0: female  1: male
    private Department department;
    private Date birth;
}

package com.bcstudents.personnelmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.bcstudents.personnelmanagement.mapper")
@SpringBootApplication

// The entrance point for our Spring Boot project
public class PersonnelManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonnelManagementApplication.class, args);
	}

}

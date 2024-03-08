package com.bcstudents.personnelmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// The entrance point for our Spring Boot project
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class PersonnelManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonnelManagementApplication.class, args);
	}

}

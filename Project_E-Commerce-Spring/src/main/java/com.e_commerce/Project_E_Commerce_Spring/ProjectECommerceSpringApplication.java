package com.e_commerce.Project_E_Commerce_Spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ProjectECommerceSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectECommerceSpringApplication.class, args);
	}

}

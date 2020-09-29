package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.demo.controller","com.example.demo.bean","com.example.demo.metadata"})
public class MyfirstProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyfirstProjectApplication.class, args);
		
		 

	}

}

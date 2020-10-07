package com.example.demo.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.User;
import com.example.demo.bean.UserDao;
import com.example.demo.exceptions.ResponseWrapper;

@RestController
@RequestMapping("/api")
public class maincontroller {

	@Autowired
	UserDao userdao;

	private static final Logger log = LogManager.getLogger(maincontroller.class);

	
	@GetMapping("/ping")
	public String testconnection() {
		return "pong";
	}

	@PostMapping("/user")
	public ResponseWrapper RegisterUser(@Valid @RequestBody User user) throws Exception {
		
		log.info("Inside RegisterUser");
		int rowcount = userdao.CreateUser(user);
		ResponseWrapper responsewrapper = new ResponseWrapper();

		if (rowcount == 1) {
			System.out.println("User details");
			System.out.println(user.getFirstName());
			System.out.println(user.getLastName());
			System.out.println(user.getEmail());
			System.out.println(user.getPassword());
			responsewrapper.setMsg_success("User Created");
			return responsewrapper;
		}
		else
		{
		
			//responsewrapper.setMsg_failure("Duplicate User");
			//return responsewrapper;
			
			throw new Exception("Duplicate User");
		}
	}

	@GetMapping("/validateuser")
	public ResponseWrapper ValidateUser( @RequestBody User user) {
		ResponseWrapper responsewrapper = new ResponseWrapper();
		int rowcount;
		System.out.println("User details for Validation>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getEmail());
		rowcount = userdao.ExistingUser(user);
		if (rowcount == 1) {
			responsewrapper.setMsg_success("LOGGED IN");
			return responsewrapper;
		}
		else 
		{
			responsewrapper.setMsg_failure("Please Register");
			return responsewrapper;
		}
	}

}

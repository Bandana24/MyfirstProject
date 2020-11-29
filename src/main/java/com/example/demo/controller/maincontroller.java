package com.example.demo.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

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
@CrossOrigin
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
		System.out.println("Inside RegisterUser ");
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

	@PostMapping("/validateuser")
	public ResponseWrapper ValidateUser( @RequestBody User user) {
		ResponseWrapper responsewrapper = new ResponseWrapper();
		int rowcount;
		System.out.println("User details for Validation>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getEmail());
		rowcount = userdao.ValidateUser(user);
		System.out.println(rowcount);
		if (rowcount == 1) {
			responsewrapper.setMsg_success("LOGGED IN");
			return responsewrapper;
		}
		else 
		{
			responsewrapper.setMsg_failure("Please Sign Up");
			return responsewrapper;
		}
	}
	@GetMapping("/fetchusers")
	public ResponseWrapper FetchUsers()
	{
		ResponseWrapper responsewrapper = new ResponseWrapper();
		List<User> UserList = new ArrayList<User>();
		UserList = userdao.Fetchuser();
	
		responsewrapper.setRet_object(UserList);
		responsewrapper.setMsg_success("Successfully fetched");
		return responsewrapper;
	}
	
	@PostMapping("/searchusers")
	public ResponseWrapper SearchUsers(@RequestBody User user)
	{
		ResponseWrapper responsewrapper = new ResponseWrapper();
		List<User> UserList = new ArrayList<User>();
		UserList = userdao.SearchUsers(user);
		 System.out.println("After the search function"); 
		Boolean Userlistflag = UserList.isEmpty(); 
        if (Userlistflag == true) 
        {
            System.out.println("The List is empty"); 
        }
        else
        {
            System.out.println("The List is not empty"); 
        }
		responsewrapper.setRet_object(UserList);
		responsewrapper.setMsg_success("Successfully fetched");
		 System.out.println(UserList); 
		return responsewrapper;
	}
	
	@PostMapping("/deleteuser")
	public ResponseWrapper Deleteuser(@RequestBody User user)
	{
		ResponseWrapper responsewrapper = new ResponseWrapper();
		int rowcount;
		if(user.getID() == null)
		{
			System.out.println("User id for Deletion" + user.getID());
			responsewrapper.setMsg_failure("User Id is not specified for Deletion");
		}
		else
		{
		rowcount = userdao.Deleteuser(user);
		responsewrapper.setRet_object(user);
		responsewrapper.setMsg_success("Successfully Deleted");
		}
		return responsewrapper;
	}

	
	@PostMapping("/modifyuser")
	public ResponseWrapper modifyuser(@RequestBody User user)
	{
		ResponseWrapper responsewrapper = new ResponseWrapper();
		System.out.println("Inside the call ModifyUser");
		int rowcount;
		if(user.getID() == null)
		{
			System.out.println("User id for Modify" + user.getFirstName());
			System.out.println("User id for Modify" + user.getLastName());
			System.out.println("User id for Modify" + user.getID());
			responsewrapper.setMsg_failure("User Id is not specified for Modification");
		}
		else
		{
			System.out.println("Inside Modify User");
		rowcount = userdao.Modifyuser(user);
		if (rowcount==0) 
		{
			responsewrapper.setMsg_failure("Modification failed");
		
		}
		else
		{
		responsewrapper.setRet_object(user);
		responsewrapper.setMsg_success("Successfully Modified");
		}
		
	}
		return responsewrapper;
	}
}



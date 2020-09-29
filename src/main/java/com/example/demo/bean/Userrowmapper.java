package com.example.demo.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Userrowmapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		User user = new User();
		user.ID= rs.getLong("Id");
		user.FirstName= rs.getString("FirstName");
		user.LastName = rs.getString("lastName");
		user.Email= rs.getString("email");
		return user;
	}
}


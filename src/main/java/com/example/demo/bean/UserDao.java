package com.example.demo.bean;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.controller.maincontroller;
import com.example.demo.metadata.Sequence;

@Repository
public class UserDao {

	@Autowired
	Sequence seq;

	@Autowired
	NamedParameterJdbcTemplate template;
	private static final Logger log = LogManager.getLogger(UserDao.class);

	public NamedParameterJdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(NamedParameterJdbcTemplate template) {
		this.template = template;
	}

	public int CreateUser(User user) {
		String sql;
		String seq_type;
		seq_type = "MY_USER_SEQ";
		Long user_id;

		// Duplicacy check
		System.out.println("Duplicacy check inside UserDao Class");
		int rowcount;
		rowcount = ExistingUser(user);

		if (rowcount == 0)// User doesnt exists, hence create User
		{

			user_id = seq.fetchseqencenumber(seq_type);
			sql = "Insert into User values (:id,:firstname,:lastname,:email,:password)";

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user_id);
			map.put("firstname", user.getFirstName());
			map.put("lastname", user.getLastName());
			map.put("email", user.getEmail());
			map.put("password", user.getPassword());

			return template.execute(sql, map, new PreparedStatementCallback<Integer>() {
				@Override
				public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					return ps.executeUpdate(); // returning 1, meaning user has been created
				}
			});
		} else {
			// log and error and return an error
			return 0; // User already exists
		}

	}

	public int ExistingUser(User user)
	{
	
		Map<String,Object> map = new HashMap<String,Object>();
		
		System.out.println("Inside function ExistingUser in UserDao Class");
		System.out.println("User details");
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getEmail());
		
		
		map.put("firstname", user.getFirstName());
		map.put("lastname", user.getLastName());
		map.put("email", user.getEmail());
		
		String sql;
		int rowcount=0;
		sql= "SELECT count(*) FROM USER WHERE FIRSTNAME=:firstname and LASTNAME= :lastname and EMAIL =:email ";
		try
		{
		 rowcount = template.queryForObject(sql, map, Integer.class);
		
		}
		 catch (Exception e) {
		 if (log.isDebugEnabled())
		 {
		       log.debug(e);
		  }
		
		 }
		 return rowcount;
	}
}
	
	
	

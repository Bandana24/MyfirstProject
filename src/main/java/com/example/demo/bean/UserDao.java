package com.example.demo.bean;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
	public List<User> Fetchuser()
	{
		System.out.println("Inside Fetchuser>>>>>>>>>>>>>>>>>Bandana");

        String sql;
        sql = "Select Id, FirstName, LastName, Email from USER" ;
        Userrowmapper userrowmap = new Userrowmapper();
        List<User> UserList = new ArrayList<User>();
    	try
		{
    		UserList = template.query(sql,userrowmap);
    		
		
		}
		 catch (Exception e) {
		 if (log.isDebugEnabled())
		 {
		       log.debug(e);
		  }
		
		 }
    	return UserList;
		
		
	}
	
	public List<User> SearchUsers(User user)
	{
	
		Map<String,Object> map = new HashMap<String,Object>();
		 Userrowmapper userrowmap = new Userrowmapper();
		  List<User> UserList = new ArrayList<User>();
		
		System.out.println("Inside function SearchUsers in UserDao Class");
		System.out.println("User details");
		System.out.println(user.getID());
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getEmail());
		

		map.put("id", user.getID());
		map.put("firstname", user.getFirstName());
		map.put("lastname", user.getLastName());
		map.put("email", user.getEmail());
		String mainsql,sql;
		mainsql= null;
		int flag=0;
		sql="SELECT * FROM USER ";
		 
		if(user.getID() != null)
		{
			if(flag == 0)
			{
				mainsql= sql.concat(" where ID=:id  ");
				sql=mainsql;
				flag=1;
			}
		}
		if(user.getFirstName() != null)
		{
			if(flag == 0)
			{
				mainsql = sql.concat(" where FIRSTNAME=:firstname");
				sql=mainsql;
				flag=1;
			}
			else
			{
				mainsql =sql.concat(" and FIRSTNAME=:firstname");
				sql=mainsql;
				flag=1;
			}
			
		}
		if(user.getLastName() != null)
		{
			if(flag == 0)
			{
				mainsql = sql.concat(" where LastName=:lastname");
				sql=mainsql;
				flag=1;
			}
			else
			{
				mainsql =sql.concat("  and LastName=:lastname");
				sql=mainsql;
			}
			
		}
		if(user.getEmail() != null)
		{
			if(flag == 0)
			{
				mainsql = sql.concat(" where Email=:email");
				sql=mainsql;
				flag=1;
			}
			else
			{
				mainsql =sql.concat("  and  Email=:email");
				sql=mainsql;
			}
			
		}
		if(mainsql== null)
		{
			mainsql=sql;
		}
		
	
		System.out.println(mainsql);
	

		//sql= "SELECT * FROM USER WHERE FIRSTNAME=:firstname and LASTNAME= :lastname and EMAIL =:email AND ID= :Id";
		
		try
		{
			UserList = template.query(mainsql,map,userrowmap);
		
		}
		 catch (Exception e) {
		 if (log.isDebugEnabled())
		 {
		       log.debug(e);
		  }
		
		 }
		 return UserList;
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
	
	
	public int ValidateUser(User user)
	{
	
		Map<String,Object> map = new HashMap<String,Object>();
		
		System.out.println("Inside function ExistingUser in UserDao Class");
		System.out.println("User details");
		
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		
		
		
		map.put("email", user.getEmail());
		map.put("password",user.getPassword());
		
		String sql;
		int rowcount=0;
		sql= "SELECT count(*) FROM USER WHERE  EMAIL =:email and Password=:password ";
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
	
	
	public int Deleteuser(User user)
	{
		Map<String, Object> map= new HashMap<String, Object>();
		
		System.out.println("Inside function Deleteuser in UserDao Class");
		System.out.println("User details");
		System.out.println(user.getID());
		
		map.put("userid", user.getID());

		String sql;
		int rowcount=0;
		sql = "Delete from User where ID= :userid";
		
		try
		{
		 rowcount = template.update(sql, map);
		
		}
		 catch (Exception e) {
		 if (log.isDebugEnabled())
		 {
		       log.debug(e);
		  }
		
		 }
		 return rowcount;
		
	}
	
	public int Modifyuser(User user)
	{
		Map<String, Object> map= new HashMap<String, Object>();
		Userrowmapper userrowmap = new Userrowmapper();
		String sql;
		User user_return;
		int rowcount=0;
			
		System.out.println("Inside function Modifyuser in UserDao Class");
		System.out.println("User details");
		System.out.println(user.getID());
		
		map.put("userid", user.getID());
		
		//fetch the record from the database
		
		sql = "Select * from USER where Id= :userid " ;
	
		 user_return = template.queryForObject(sql, map, userrowmap);
		 System.out.println(user_return.getID());
		 
		 //Check if any of the fields in the input is null
		
		 if(user.getFirstName() == null)
		 {
			 user.setFirstName(user_return.getFirstName());
		 }
		 if(user.getLastName()== null)
		 {
			 user.setLastName(user_return.getLastName());
		 }
		 if(user.getEmail() == null)
		 {
			 user.setEmail(user_return.getEmail());
		 }
		 //map the inputs to be updated in the database
		 map.put("Id", user.getID());
		 map.put("firstname", user.getFirstName());
		 map.put("lastname", user.getLastName());
		 map.put("email", user.getEmail());
		 
		 System.out.println(user.getID());
		 System.out.println(user.getFirstName());
		 System.out.println(user.getLastName());
		 System.out.println(user.getEmail());
		 
	    sql = "UPDATE USER set FIRSTNAME = :firstname, LASTNAME=:lastname, EMAIL=:email where ID= :Id ";
		try
		{
		 rowcount = template.update(sql, map);
		 System.out.println("ROWCOUNT" +  rowcount);
		
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

	
	
	

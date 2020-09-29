package com.example.demo.metadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class Sequence {
	
	Long Sequence_number;
	@Autowired
	JdbcTemplate jdbctemplate;

	public JdbcTemplate getJdbctemplate() {
		return jdbctemplate;
	}

	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}
	
	public Long getSequence_number() {
		return Sequence_number;
	}

	public void setSequence_number(Long sequence_number) {
		Sequence_number = sequence_number;
	}
    public Long fetchseqencenumber(String seq_type)
    {
    	String sql;
    	sql= "Select " +  seq_type + ".NEXTVAL from DUAL";
    	Long seq_num;
    	seq_num= jdbctemplate.queryForObject( sql, Long.class );
    	return seq_num;
    	
    }

}

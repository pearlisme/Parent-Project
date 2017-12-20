
package com.cognizant.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cognizant.model.RelationalObject;

@Repository("relationalObjectDao")
public class RelationalObjectDaoImpl implements RelationalObjectDao {

	// Autowired the instance of jdbcTemplate
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<RelationalObject> getParents() {

		List<RelationalObject> relationalObjectList = null;

			relationalObjectList = jdbcTemplate.query("select * from parent_child_mtrx",
					new BeanPropertyRowMapper<RelationalObject>(RelationalObject.class));

			return relationalObjectList;

	}

}

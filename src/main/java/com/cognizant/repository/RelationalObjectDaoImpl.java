
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
	public List<RelationalObject> getParentsByInMemory() {

		List<RelationalObject> relationalObjectList = null;

			relationalObjectList = jdbcTemplate.query("select * from parent_child_mtrx",
					new BeanPropertyRowMapper<RelationalObject>(RelationalObject.class));

			return relationalObjectList;

	}

	
	@Override
	public RelationalObject getParentByQuery(int childId) {
		
		RelationalObject relationalObject = null;
		String sql="SELECT (SELECT parent_id FROM mirror m1 WHERE m1.child_id = ?) AS \"IMMEDIATE PARENT\" , parent_id AS \"ULTIMATE PARENT\" FROM mirror WHERE CONNECT_BY_ISLEAF =1\n" + 
				"CONNECT BY PRIOR parent_id = child_id\n" + 
				"START WITH child_id =?";
		
		relationalObject = jdbcTemplate.queryForObject(sql, new Object[] {childId} ,new BeanPropertyRowMapper<RelationalObject>(RelationalObject.class));
		
		return relationalObject;
	}

}

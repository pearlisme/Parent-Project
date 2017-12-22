package com.cognizant.repository;

import java.util.List;

import com.cognizant.model.RelationalObject;

public interface RelationalObjectDao {

	public List<RelationalObject> getParentsByInMemory();
	
	public RelationalObject getParentByQuery(int childId);

}

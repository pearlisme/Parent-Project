package com.cognizant.service;

public interface RelationalObjectService {

	public void getParentsByInMemory(int childId);
	
	public void getParentByQuery(int childId);
}

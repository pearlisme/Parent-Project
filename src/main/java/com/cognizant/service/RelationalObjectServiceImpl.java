package com.cognizant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cognizant.model.RelationalObject;
import com.cognizant.repository.RelationalObjectDao;

@Service("relationalObjectService")
public class RelationalObjectServiceImpl implements RelationalObjectService {

	@Autowired
	private RelationalObjectDao relationObjectDao;

	@Override
	public void getParentById(int childId) {

		int immediateParent = 0;
		int ultimateParent = 0;
		try {
			List<RelationalObject> relationalObjectList = relationObjectDao.getParents();

			// Block to find the immediate parent with child id
			if (!relationalObjectList.isEmpty() && relationalObjectList != null) {
				for (RelationalObject ro : relationalObjectList) {
					if (ro.getChildId() == childId) {
						immediateParent = ro.getParentId();
						relationalObjectList.remove(ro.getChildId());
						System.out.println("IMMEDIATE PARENT : " + immediateParent);
						break;
					}
				}

				// Method call to find the ultimate parent for the given child id
				ultimateParent = findUltimateParent(relationalObjectList, childId);
				System.out.println("ULTIMATE_PARENT : " + ultimateParent);

			}
		} catch (EmptyResultDataAccessException e) {
			throw new RuntimeException("Empty relationalObjectList found!");
		} 
	}

	private int findUltimateParent(List<RelationalObject> relationalObjectList, int innerChildId) {
		int relationalParent = 0;
		for (RelationalObject relationalObject : relationalObjectList) {

			if (relationalObject.getChildId() == innerChildId) {
				relationalParent = relationalObject.getParentId();

				// Removing the child object from the List to optimise the result list
				relationalObjectList.remove(relationalObject);
				// Recursive call to find the Utimate Parent by return current innerChild
				return findUltimateParent(relationalObjectList, relationalParent);
			}
		}
		// Returning the Child Id as parent if no parent mapped with the child id
		return innerChildId;
	}
}

package com.cognizant.model;

import org.springframework.stereotype.Component;

@Component
public class RelationalObject {

	private int id;
	private int childId;
	private int parentId;

	public int getChildId() {
		return childId;
	}

	public int getId() {
		return id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setChildId(int childId) {
		this.childId = childId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}

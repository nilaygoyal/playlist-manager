package com.hs.mixtape.datamodel;

/**
 * 
 * Change is the datamodel for each user
 * 
 * <pre>
 *	{
 *		"id" : #user_id,
 *		"name" : 'user's name'
 *	}
 * </pre>
 * 
 */
public class User {
	private String id;
	private String name;
	public User() {
		super();
	}
	public User(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

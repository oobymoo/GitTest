package com.asjy.wtb.model;

public class User {
	private int id;
	private String username;
	private String password;
	private String addTime;
	private String usercname;
	
	public String getUsercname() {
		return usercname;
	}
	public void setUsercname(String usercname) {
		this.usercname = usercname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
	
}

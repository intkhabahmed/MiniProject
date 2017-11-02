package com.cg.bean;

public class User {
	private int userId;
	private String username;
	private String password;
	private String email;
	private long mobileNo;
	private String role;
	
	public User(){
		System.out.println("Empty constructor is called");
	}
	
	public User(String username, String password, String email, long mobileNo,
			String role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobileNo = mobileNo;
		this.role = role;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
}

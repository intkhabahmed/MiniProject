package com.cg.bean;

public class User {
	private String loginId;
	private String password;
	public User(String loginId, String password) {
		super();
		this.loginId = loginId;
		this.password = password;
	}
	public User(){
		System.out.println("Empty constructor is called");
	}
	
	public boolean login(String username, String password){
		if(this.loginId.equals(username) && this.password.equals(password)){
			return true;
		}
		else{
			return false;
		}
	}
	
}

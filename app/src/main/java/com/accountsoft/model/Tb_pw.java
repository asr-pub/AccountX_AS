package com.accountsoft.model;

public class Tb_pw {
	
	private String username;
	
	private String password;
	
	public Tb_pw(){
		super();
		
	}
	
	public Tb_pw(String password ,String username){
		
		super();
		this.password =password;
		this.username=username;
	}
	
	
	public String getpassword() {
		return password;

	}
	public String getusername() {
		return username;

	}
	
	
	public void setpassword(String password){
		this.password=password;
	}

	public void setusername(String username){
		this.username=username;
	}
}

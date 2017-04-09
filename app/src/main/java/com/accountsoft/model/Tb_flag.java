package com.accountsoft.model;

public class Tb_flag {
	private int _id;
	private String flag;
	
	
	public Tb_flag(){
		super();
	}
	
	public Tb_flag(int id, String flag){
		
		
		super();
		this._id=id;
		this.flag=flag;
		
		
	}
	
	public int getid(){
		return _id;
	}
	
	public String getflag(){
		return flag;
		
	}
	
	public void setid(int id){
		this._id=id;
	}
	
	public void setflag(String flag){
		this.flag=flag;
	}

	
	

}

package com.accountsoft.scalss;

public class Picture {
	
	private String title;
	private int imageID;
	
public Picture(){
	super();
}

public Picture(String title, int imageid){
	super();
	this.title =title;
	this.imageID=imageid;
	
}

public String getTitle(){
	return title;
}

public void setTitle( String title){
	
	this.title=title;
	
}
public int getimageid(){
	return this.imageID;
}
public void setimageId(int imageid){
	this.imageID = imageid;
}
}

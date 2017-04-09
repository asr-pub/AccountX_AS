package com.accountsoft.dao;

import com.accountsoft.model.Tb_pw;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class pwDAO {
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	

	
	public pwDAO(Context context){
		helper=new DBOpenHelper(context);
		
	}
	
	public void add(String username,String tb_password) {
		db=helper.getWritableDatabase();
		db.execSQL("insert into tb_password (username,password) values(?,?)",new String[]{username,tb_password
			
		});
		
		
	}
	
	public Boolean findpassword( String password) {
		db=helper.getWritableDatabase();
		Cursor cursor =db.rawQuery("select * from tb_password where password=?", new String[]{password});
		if(cursor.moveToNext()){
			return  true;
		}
			
		return false;
		
		
	}
	
	public void update(Tb_pw tb_password){
		
		db=helper.getWritableDatabase();
		db.execSQL("update  tb_password set password =? username=?",new String[]{tb_password.getpassword(),tb_password.getusername()
				
		});
	}
	
	public long getCount() {
		db=helper.getWritableDatabase();
		Cursor cursor =db.rawQuery("select count(password)from tb_password", null);
		if(cursor.moveToNext()){
			return  cursor.getLong(0);
		}
			
		return 0;
	}
	
	
	

}

















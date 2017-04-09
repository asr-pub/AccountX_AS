package com.accountsoft.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.accountsoft.model.Tb_inaccount;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class InaccountDAO {
	
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	
	
	public InaccountDAO(Context context){
		helper=new DBOpenHelper(context);
		
	}
	
	public void add(Tb_inaccount tb_inaccount) {
		db=helper.getWritableDatabase();
		db.execSQL("insert into tb_inaccount (_id,money,time,type,handler,mark) values(?,?,?,?,?,?)", new
        Object[]{tb_inaccount.getid(),tb_inaccount.getmoney(),tb_inaccount.gettime(),
		 tb_inaccount.gettype(),tb_inaccount.gethandler(),tb_inaccount.getmark()		
		});
		
		
	}
	
	public void updata(Tb_inaccount tb_inaccount) {
		db=helper.getWritableDatabase();
		db.execSQL("update tb_inaccount set money=?,time=?,type=?,handler=?,mark=? where _id=?",new Object[]{
				 tb_inaccount.getmoney(),tb_inaccount.gettime(),
				 tb_inaccount.gettype(),tb_inaccount.gethandler(),tb_inaccount.getmark(),tb_inaccount.getid()
		});
		
	}
	
	
	public Tb_inaccount find(int id) {
		db=helper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select _id,money,time,type,handler,mark from  tb_inaccount where _id =?", 
				new String[]{String.valueOf(id)});
		
		if (cursor.moveToNext()) {
			
			return new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),
					   cursor.getDouble(cursor.getColumnIndex("money")),
					   cursor.getString(cursor.getColumnIndex("time")),
					   cursor.getString(cursor.getColumnIndex("type")),
					   cursor.getString(cursor.getColumnIndex("handler")),
					   cursor.getString(cursor.getColumnIndex("mark")));
			
			
		}
		
		return null;
		
	}
	
	public Map<Integer, Double> findwithdate(String date){
		db=helper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select _id,money,time,type,handler,mark from  tb_inaccount where time =?", 
				new String[]{date});
		Map<Integer, Double> inMap =new HashMap<Integer, Double>();
		
		int n =0;
		
if (!cursor.moveToNext()) {
			
			inMap.put(0, 0.0);
			
			return inMap;
			
		}
         cursor.moveToPrevious();

		while(cursor.moveToNext()) {
			
			
			inMap.put( n,cursor.getDouble(cursor.getColumnIndex("money")) );
			
			n++;
		
		}
		
		
		return inMap;
		
	}
	
	
	
	
	public void detele(Integer... ids) {
		
		if(ids.length>0){
			
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < ids.length; i++) {
				
				sb.append('?').append(',');
				
				
			}
			sb.deleteCharAt(sb.length()-1);
			db=helper.getWritableDatabase();
			db.execSQL("delete form tb_inaccount where _id in)"+"(",ids);
		}
		
		
	}
	
	
	public List<Tb_inaccount> getScrollData(int start, int count) {
		
		List<Tb_inaccount>tb_inaccounts=new ArrayList<Tb_inaccount>();
		db=helper.getWritableDatabase();
		Cursor cursor =db.rawQuery("select * from tb_inaccount limit ?,?", new String[]{
		String.valueOf(start),String.valueOf(count)});
		while (cursor.moveToNext()) {
			
			tb_inaccounts.add(new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),
					   cursor.getDouble(cursor.getColumnIndex("money")),
					   cursor.getString(cursor.getColumnIndex("time")),
					   cursor.getString(cursor.getColumnIndex("type")),
					   cursor.getString(cursor.getColumnIndex("handler")),
					   cursor.getString(cursor.getColumnIndex("mark"))));
			
		}
		
		return tb_inaccounts;
		
		
	}
	
	
	public long getCount() {
		db=helper.getWritableDatabase();
		Cursor cursor =db.rawQuery("select count(_id)from tb_inaccount", null);
		if(cursor.moveToNext()){
			return  cursor.getLong(0);
		}
			
		return 0;
	}
	
	
	public int  getMaxid() {
		db=helper.getWritableDatabase();
		Cursor cursor =db.rawQuery("select max(_id)from tb_inaccount", null);
		if(cursor.moveToLast()){
			return  cursor.getInt(0);
		}
			
		return 0;
	}
	
	
	
	

}



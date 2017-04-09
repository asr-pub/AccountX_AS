package com.accountsoft.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.accountsoft.model.Tb_outaccount;

public class outaccountDAO {
	
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	
	
	public outaccountDAO(Context context){
		helper=new DBOpenHelper(context);
		
	}
	
	public void add(Tb_outaccount tb_outaccount) {
		db=helper.getWritableDatabase();
		db.execSQL("insert into tb_outaccount (_id,money,time,type,address,mark) values(?,?,?,?,?,?)", new
        Object[]{tb_outaccount.getid(),tb_outaccount.getmoney(),tb_outaccount.gettime(),
		 tb_outaccount.gettype(),tb_outaccount.getaddress(),tb_outaccount.getmark()		
		});
		
		
	}
	
	public void updata(Tb_outaccount tb_outaccount) {
		db=helper.getWritableDatabase();
		db.execSQL("update tb_outaccount set money=?,time=?,type=?,address=?,mark=? where _id=?",new Object[]{
				 tb_outaccount.getmoney(),tb_outaccount.gettime(),
				 tb_outaccount.gettype(),tb_outaccount.getaddress(),tb_outaccount.getmark(),tb_outaccount.getid()
		});
		
	}
	
	
	public Tb_outaccount find(int id) {
		db=helper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select _id,money,time,type,address,mark from tb_outaccount where _id =?", 
				new String[]{String.valueOf(id)});
		
		if (cursor.moveToNext()) {
			
			return new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")),
					   cursor.getDouble(cursor.getColumnIndex("money")),
					   cursor.getString(cursor.getColumnIndex("time")),
					   cursor.getString(cursor.getColumnIndex("type")),
					   cursor.getString(cursor.getColumnIndex("address")),
					   cursor.getString(cursor.getColumnIndex("mark")));
			
			
		}
		
		return null;
		
	}
	
	public Map<Integer, Double> findwithdate(String date) {
		db=helper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select _id,money,time,type,address,mark from tb_outaccount where time =?", 
				new String[]{date});
		
	Map<Integer, Double> outMap =new HashMap<Integer, Double>();
		
		int n =0;
		
if (!cursor.moveToNext()) {
			
	outMap.put(0, 0.0);
			
			return outMap;
			
		}
         cursor.moveToPrevious();

		while(cursor.moveToNext()) {
			
			
			outMap.put( n,cursor.getDouble(cursor.getColumnIndex("money")) );
			
			n++;
		
		}
		
		
		return outMap;
		
	}
	
	
	
	
	public void detele(Integer... ids) {
		
		if(ids.length>0){
			
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < ids.length; i++) {
				
				sb.append('?').append(',');
				
				
			}
			sb.deleteCharAt(sb.length()-1);
			db=helper.getWritableDatabase();
			db.execSQL("delete form tb_outaccount where _id in)"+"(",ids);
		}
		
		
	}
	
	
	public List<Tb_outaccount> getScrollData(int start, int count) {
		
		List<Tb_outaccount>tb_outaccounts=new ArrayList<Tb_outaccount>();
		db=helper.getWritableDatabase();
		Cursor cursor =db.rawQuery("select *from tb_outaccount limit ?,?", new String[]{
		String.valueOf(start),String.valueOf(count)});
		while (cursor.moveToNext()) {
			
			tb_outaccounts.add(new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")),
					   cursor.getDouble(cursor.getColumnIndex("money")),
					   cursor.getString(cursor.getColumnIndex("time")),
					   cursor.getString(cursor.getColumnIndex("type")),
					   cursor.getString(cursor.getColumnIndex("address")),
					   cursor.getString(cursor.getColumnIndex("mark"))));
			
		}
		
		return tb_outaccounts;
		
		
	}
	
	
	public long getCount() {
		db=helper.getWritableDatabase();
		Cursor cursor =db.rawQuery("select count(_id)from tb_outaccount", null);
		if(cursor.moveToNext()){
			return  cursor.getLong(0);
		}
			
		return 0;
	}
	
	
	public int  getMaxid() {
		db=helper.getWritableDatabase();
		Cursor cursor =db.rawQuery("select max(_id)from tb_outaccount", null);
		if(cursor.moveToLast()){
			return  cursor.getInt(0);
		}
			
		return 0;
	}
	
	
	
	

}
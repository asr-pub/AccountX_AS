package com.accountsoft.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.accountsoft.R;
import com.accountsoft.dao.InaccountDAO;
import com.accountsoft.model.Tb_inaccount;

public class Inaccountinfo extends Activity{
	
	public static final String FLAG ="id";
	ListView lvinfo;
	String strType="";
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.inaccountinfo);
		
		lvinfo=(ListView)findViewById(R.id.lvinaccountinfo);
		
		Showinfo(R.id.btnininfo);
		
		lvinfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String strinfo=String.valueOf(((TextView)view).getText());
				String strid=strinfo.substring(0,strinfo.indexOf('|'));
				Intent intent =new Intent(Inaccountinfo.this,InfoManage.class);
				intent.putExtra(FLAG, new String[]{strid,strType});
				startActivity(intent);
				
				
			}
		});
	}
	
	private void Showinfo(int intType){
		String[]  strinfos=null;
		ArrayAdapter<String>arrayAdapter=null;
		strType="btnininfo";
		InaccountDAO inaccountinfo=new InaccountDAO(Inaccountinfo.this);
		List<Tb_inaccount> listinfos=inaccountinfo.getScrollData(0,(int)inaccountinfo.getCount());
		strinfos=new String[listinfos.size()];
		int m=0;
		for (Tb_inaccount tb_inaccount:listinfos) {
			strinfos[m]=tb_inaccount.getid()+"|"+tb_inaccount.gettype()+" "+String.valueOf(tb_inaccount.getmoney())+" "+tb_inaccount.gettime();
			m++;
			
			
		}
		arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,strinfos);
		lvinfo.setAdapter(arrayAdapter);
		
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();// 实现基类中的方法
		Showinfo(R.id.btnininfo);// 显示收入信息
	}
	

}

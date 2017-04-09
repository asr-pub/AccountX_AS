package com.accountsoft.activity;

import com.accountsoft.R;
import com.accountsoft.dao.pwDAO;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class regist extends Activity{
	
	public EditText txtregist;
	public EditText txt_username;
	public Button btn_regist;
	public EditText txt_buget;
	public Button btnbuget;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.regist);
		
		
		txtregist=(EditText)findViewById(R.id.txt_password);
		btn_regist=(Button)findViewById(R.id.btn_regist);
		txt_username=(EditText)findViewById(R.id.txt_username);
		txt_buget=(EditText)findViewById(R.id.txt_buget);
		btnbuget=(Button)findViewById(R.id.btnbuget);
		
	
	   
	   
		btn_regist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				
				
				if (!txtregist.getText().toString().isEmpty()&&!txt_username.getText().toString().isEmpty()) {
					
					pwDAO pwdao =new pwDAO(regist.this);
					pwdao.add(txt_username.getText().toString(),txtregist.getText().toString());
					
					Toast.makeText(regist.this, "设置成功", Toast.LENGTH_SHORT).show();
					
					
				}
					else {
						Toast.makeText(regist.this, "请输入正确密码", Toast.LENGTH_SHORT).show();
					}
				
			}
		});
		
		
		btnbuget.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if(null!=txt_buget.getText().toString()){
					
					SharedPreferences settings = getSharedPreferences("user", 0); //获取一个 SharedPreferences 对象  
				      
				       settings.edit().putFloat("buget",Float.parseFloat(txt_buget.getText().toString())).commit();
				
				
				}
			}
		});
		
		
	}
	
	

}

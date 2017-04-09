package com.accountsoft.activity;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.accountsoft.R;
import com.accountsoft.dao.pwDAO;



@SuppressLint("NewApi")
public class Login extends Activity {
	
	
	public EditText txtlogin;
	public ImageButton btnlogin;
	public ImageButton btnregist;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		txtlogin=(EditText)findViewById(R.id.txtlogin);
		btnlogin=(ImageButton)findViewById(R.id.btnlogin);
		btnregist=(ImageButton)findViewById(R.id.btnclose);
		
		
		btnlogin.setOnClickListener(new OnClickListener() {
		
			
			
			@Override
			public void onClick(View v) {
			Intent intent =new Intent(Login.this,MainActivity.class);
			pwDAO pwdao =new pwDAO(Login.this);
			
			pwdao.add("123", "123");
			
			
			//(pwdao.getCount()==0|pwdao.getpassword().isEmpty())&&txtlogin.getText().toString().isEmpty()
			if (pwdao.getCount()!=0&&pwdao.findpassword(txtlogin.getText().toString())) {
				
				startActivity(intent);
			}
				else {
					Toast.makeText(Login.this, "请输入正确密码. 默认密码和用户名都是123", Toast.LENGTH_SHORT).show();
				}
			}
		
		});
		
		btnregist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent =new Intent(Login.this,regist.class);
				startActivity(intent);
				
			}
		});
		
		

	}

}

package com.accountsoft.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.accountsoft.R;
import com.accountsoft.dao.flagDAO;
import com.accountsoft.model.Tb_flag;

public class Accountflag extends Activity{
EditText txtFlag;
Button btnflageSaveButton,btnflageCancelButton;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.accountflag);
	
	txtFlag=(EditText)findViewById(R.id.txtFlag);
	btnflageCancelButton=(Button)findViewById(R.id.btnflagCancel);
	btnflageSaveButton=(Button)findViewById(R.id.btnflagSave);
	
	//==========================================================
	
	btnflageSaveButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String strFlag=txtFlag.getText().toString();
			if(!strFlag.isEmpty()){
				flagDAO fDao=new flagDAO(Accountflag.this);
				Tb_flag tb_flag =new Tb_flag(fDao.getMaxid()+1, strFlag);
				fDao.add(tb_flag);
				Toast.makeText(Accountflag.this, "新增标签添加成功", Toast.LENGTH_SHORT).show();
				
				
			}
			else {
				Toast.makeText(Accountflag.this, "请输入便签", Toast.LENGTH_SHORT).show();
				
			}
			
		}
		
	});
	
	
	btnflageCancelButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		txtFlag.setText("");	
		}
	});
}


}

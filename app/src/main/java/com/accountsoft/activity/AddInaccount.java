package com.accountsoft.activity;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.accountsoft.R;
import com.accountsoft.dao.InaccountDAO;
import com.accountsoft.model.Tb_inaccount;

public class AddInaccount extends Activity{
	
	protected static final int DATE_DILOG_ID=0;
	public EditText txtinMoney,txtinTime,txtinHandler,txtinMark;
	public Spinner spintype;
	public Button btnsaveButton,btncancelButton ;
	private int mYear,mMonth,mDay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.addinaccount);
		
		txtinHandler=(EditText)findViewById(R.id.txtinhandler);
		txtinMark=(EditText)findViewById(R.id.txtinmark);
		txtinMoney=(EditText)findViewById(R.id.txtinMoney);
		txtinTime=(EditText)findViewById(R.id.txtintime);
		spintype=(Spinner)findViewById(R.id.spintype);
		btncancelButton=(Button)findViewById(R.id.btnincancel);
		btnsaveButton=(Button)findViewById(R.id.btninsave);
	
		//=====================================================
		
		txtinTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DILOG_ID);
			}
		});
		final Calendar c=Calendar.getInstance();
		mYear=c.get(Calendar.YEAR);
		mMonth=c.get(Calendar.MONTH);
		mDay=c.get(Calendar.DAY_OF_MONTH);
		updateDisplay();
		
		
		//=====================================
		
		btnsaveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			String strinMoney=txtinMoney.getText().toString();
			if (!strinMoney.isEmpty()) {
          InaccountDAO inaccountDAO =new InaccountDAO(AddInaccount.this);
          Tb_inaccount tb_inaccount =new Tb_inaccount(inaccountDAO.getMaxid()+1, Double.parseDouble(strinMoney), txtinTime.getText().toString(), spintype.getSelectedItem().toString(),txtinHandler.getText().toString(), txtinMark.getText().toString());
          
          inaccountDAO.add(tb_inaccount);
          Toast.makeText(AddInaccount.this, "添加成功", Toast.LENGTH_SHORT).show();

			}
			else {
				Toast.makeText(AddInaccount.this, "请输入收入金额", Toast.LENGTH_SHORT).show();
				
			}
			}
		});
		
		btncancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				txtinHandler.setText("");
				txtinMark.setText("");
				txtinMoney.setText("");
				txtinMoney.setHint("0.00");
				txtinTime.setText("");
				txtinTime.setHint("2011-01-01");
				spintype.setSelection(0);
				
				
			}
		});
		
	}
	
	
	
	private void updateDisplay(){
		txtinTime.setText(new StringBuilder().append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
	}
	
	@Override
	protected Dialog onCreateDialog(int id){
		switch (id) {
		case DATE_DILOG_ID:
			
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
			
			
		}
		return null;
	}
	
	private DatePickerDialog.OnDateSetListener mDateSetListener =new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear=year;
			mMonth=monthOfYear;
			mDay=dayOfMonth;
			updateDisplay();
			
		}
	};

}

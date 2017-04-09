package com.accountsoft.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.accountsoft.R;
import com.accountsoft.dao.InaccountDAO;
import com.accountsoft.dao.outaccountDAO;
import com.accountsoft.model.Tb_inaccount;
import com.accountsoft.model.Tb_outaccount;

public class InfoManage extends Activity{
	
	protected static final int DATE_DILOG_ID=0;
	TextView tvtitle,textView;
	EditText txtmoney,txttime,txtHa,txtmark;
	Spinner sptype;
	Button btnEdit,btnDel;
	String[] strinfos;
	String strid,strType;
	protected int myear,mmonth,mday;
	outaccountDAO outaccountDAO=new outaccountDAO(InfoManage.this);
	InaccountDAO inaccountDAO=new InaccountDAO(InfoManage.this);
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.infomanage);
		
		tvtitle=(TextView)findViewById(R.id.inouttitle);
		textView =(TextView)findViewById(R.id.tvInOut);
		txtmoney=(EditText)findViewById(R.id.txtInOutMoney);
		txttime=(EditText)findViewById(R.id.txtInOutTime);
		txtHa=(EditText)findViewById(R.id.txtInOut);
		txtmark=(EditText)findViewById(R.id.txtInOutMark);
		btnDel=(Button)findViewById(R.id.btnInOutDelete);
		btnEdit=(Button)findViewById(R.id.btnInOutEdit);
		sptype=(Spinner)findViewById(R.id.spInOutType);
		
		//=====================================================
		
		Intent intent =getIntent();
		Bundle bundle =intent.getExtras();
		strinfos =bundle.getStringArray(Showinfo.FLAG);
		strid=strinfos[0];
		strType=strinfos[1];
		if(strType.equals("btnoutinfo")){
			tvtitle.setText("支出管理");
			
			textView.setText("地点");
			Tb_outaccount tb_outaccount =outaccountDAO.find(Integer.parseInt(strid));
			txtmoney.setText(String.valueOf(tb_outaccount.getmoney()));
			txttime.setText(tb_outaccount.gettime());
			sptype.setPrompt(tb_outaccount.gettype());
			txtHa.setText(tb_outaccount.getaddress());
			txtmark.setText(tb_outaccount.getmark());
			
			
		}else {
tvtitle.setText("收入管理");
			
			textView.setText("付款方");
			Tb_inaccount tb_inaccount = inaccountDAO.find(Integer.parseInt(strid));
			txtmoney.setText(String.valueOf(tb_inaccount.getmoney()));
			txttime.setText(tb_inaccount.gettime());
			sptype.setPrompt(tb_inaccount.gettype());
			txtHa.setText(tb_inaccount.gethandler());
			txtmark.setText(tb_inaccount.getmark());
		}
		
		//======================================
		
		btnEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			if (strType.equals("btnoutinfo")) {
				Tb_outaccount tb_outaccount =new Tb_outaccount();
				tb_outaccount.setid(Integer.parseInt(strid));
				tb_outaccount.setmoney(Double.parseDouble(txtmoney.getText().toString()));
				tb_outaccount.settime(txttime.getText().toString());
				tb_outaccount.settype(sptype.getSelectedItem().toString());
				tb_outaccount.setaddress(txtHa.getText().toString());
				tb_outaccount.setmark(txtmark.getText().toString());
				
				outaccountDAO.updata(tb_outaccount);
				
				
				
				
				
			}else if (strType.equals("btnininfo")) {
				Tb_inaccount tb_inaccount =new Tb_inaccount();
				tb_inaccount.setid(Integer.parseInt(strid));
				tb_inaccount.setmoney(Double.parseDouble(txtmoney.getText().toString()));
				tb_inaccount.settime(txttime.getText().toString());
				tb_inaccount.settype(sptype.getSelectedItem().toString());
				tb_inaccount.sethandler(txtHa.getText().toString());
				tb_inaccount.setmark(txtmark.getText().toString());
				
				inaccountDAO.updata(tb_inaccount);
				
			}
			
			Toast.makeText(InfoManage.this, "数据修改成功", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		
		
		
		//==============================================================
		
		btnDel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			if(strType.equals("btnoutinfo")){
				outaccountDAO.detele(Integer.parseInt(strid));
				
				
			}
			else if(strType.equals("btnininfo")){
				
				inaccountDAO.detele(Integer.parseInt(strid));
				
				
			}
			Toast.makeText(InfoManage.this, "删除数据成功", Toast.LENGTH_SHORT).show();
			
			}
		});
		
	}
	
	

}



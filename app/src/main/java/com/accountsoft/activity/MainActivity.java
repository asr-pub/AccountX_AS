package com.accountsoft.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.accountsoft.MyChart2;
import com.accountsoft.R;
import com.accountsoft.Adapter.pictureAdapter;


public class MainActivity extends Activity {
	
	public GridView gvinfo;
	public String[] title;
	public int[] images; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		title=new String[]{"新增支出","新增收入" ,"我的支出","我的收入", "数据管理", "便签", "月收支统计图"	,"预算支出图","密码设置","退出"
		};
		
		images=new int[]{
			R.drawable.xzzc1,R.drawable.xzsl1,R.drawable.wdzc1
			,R.drawable.wdsr1,R.drawable.sjgl1,R.drawable.bq1,	
			R.drawable.tu,R.drawable.tu,R.drawable.shezhi,R.drawable.fh1
		};
		
		
		gvinfo=(GridView)findViewById(R.id.gvinfo);
		pictureAdapter adapter=new pictureAdapter(title, images, this);
		gvinfo.setAdapter(adapter);
		gvinfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			Intent intent =null;
			switch (position) {
			case 0:
				
				intent =new Intent(MainActivity.this,AddOutaccount.class);
				startActivity(intent);
				break;
            case 1:
            	intent =new Intent(MainActivity.this,AddInaccount.class);
				startActivity(intent);
				
				break;
				
            case 2:
            	intent =new Intent(MainActivity.this,Outaccountinfo.class);
				startActivity(intent);
	            break;
             case 3:
            	 intent =new Intent(MainActivity.this,Inaccountinfo.class);
 				startActivity(intent);
	            break;
             case 4:
            	 intent =new Intent(MainActivity.this,Showinfo.class);
 				startActivity(intent);
	             break;
             case 5:
            	 intent =new Intent(MainActivity.this,Accountflag.class);
  				startActivity(intent);
	             break;
	             
            
	             
             case 6:
            	 intent =new Intent(MainActivity.this,myChart.class);
    				startActivity(intent);
     	             break;
            case 7:
            
            	intent =new Intent(MainActivity.this,MyChart2.class);
				startActivity(intent);
 	             break;
			
            case 8:
                
            	intent =new Intent(MainActivity.this,regist.class);
				startActivity(intent);
 	             break;
 	             
            case 9:
           	 finish();
			}
			
				
			}
		});
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}









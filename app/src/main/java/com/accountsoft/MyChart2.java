package com.accountsoft;

import java.util.ArrayList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;

import com.accountsoft.dao.outaccountDAO;
import com.accountsoft.model.Tb_outaccount;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.Legend.LegendPosition;

public class MyChart2 extends Activity {
	
	private PieChart pChart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_my_chart2);
		pChart=(PieChart)findViewById(R.id.chart2);
		
		

	        Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

	        pChart.setValueTypeface(tf);
	        pChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf"));

	        pChart.setHoleRadius(60f);

	        pChart.setDescription("");

	        pChart.setDrawYValues(true);
	        pChart.setDrawCenterText(true);

	        pChart.setDrawHoleEnabled(true);

	        pChart.setRotationAngle(0);

	        // draws the corresponding description value into the slice
	        pChart.setDrawXValues(true);

	        // enable rotation of the chart by touch
	        pChart.setRotationEnabled(true);

	        // display percentage values
	        pChart.setUsePercentValues(true);
	        // pChart.setUnit(" €");
	        // pChart.setDrawUnitsInChart(true);

	        // add a selection listener
	      
	       pChart.setTouchEnabled(true);
	       
	       pChart.animateX(1800);

	        pChart.setCenterText("预算|支出");

	        setData();

	        pChart.animateXY(1500, 1500);
	        // pChart.spin(2000, 0, 360);

	        Legend l = pChart.getLegend();
	        l.setPosition(LegendPosition.RIGHT_OF_CHART);
	        l.setXEntrySpace(7f);
	        l.setYEntrySpace(5f);
	        
	        pChart.invalidate();
		
	}

	private void setData() {
		
		
		
		ArrayList<String> xVals = new ArrayList<String>();
		
		
		ArrayList<Entry> yVals1 = new ArrayList<Entry>();
		
		
		 SharedPreferences settings = getSharedPreferences("user", 0); //获取一个 SharedPreferences 对象  
	      //取出保存的NAME，取出改字段名的值，不存在则创建默认为空  
		 
		 float string=0;
	        settings.getFloat("buget", string); //取出保存的 NAME  
	        
		
		
	float sum =0;
	ArrayList< Double> bugetsArrayList=new ArrayList<Double>();
	
	
		
		float buget =1500;
		if(string>0){
			buget=string;
		}
		bugetsArrayList=this.dayoutaccount();
		for(int i=0;i<bugetsArrayList.size();i++){
			double j=bugetsArrayList.get(i);
			sum+=j;
		}

            yVals1.add(new Entry(((buget-sum)/buget),1));
            yVals1.add(new Entry(1-(buget-sum)/buget,2));


       
            
            xVals.add("预算");
            xVals.add("支出");
		PieDataSet set1 = new PieDataSet(yVals1, "Election Results");
		ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        
        colors.add(ColorTemplate.getHoloBlue());

        set1.setColors(colors);
        PieData data = new PieData(xVals, set1);
        pChart.setData(data);

        // undo all highlights
        pChart.highlightValues(null);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_chart2, menu);
		return true;
	}
	
	
public ArrayList<Double> dayoutaccount() {
		
		outaccountDAO outDAO = new outaccountDAO(MyChart2.this);
		Tb_outaccount tb_outaccount = new Tb_outaccount();
		

		Time time = new Time();
		int day = time.getDays();
		int month = time.getmonth();
		int year = time.getyear();

		ArrayList<Double> out = new ArrayList<Double>();

		for (int n=1; n <= day; n++) {
			String date = "" + year + "-" + month + "-" + n;
			
			double temp =0.0;
			
			for (int i = 0; i < outDAO.findwithdate(date).size(); i++) {
				
				 double intemp=outDAO.findwithdate(date).get(i);
				 
				 temp =temp+intemp;
				 
				
			}

			out.add(temp);

			

		}

		return out;
	}
	

}

package com.accountsoft.activity;



import java.util.ArrayList;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import com.accountsoft.R;
import com.accountsoft.Time;
import com.accountsoft.dao.InaccountDAO;
import com.accountsoft.dao.outaccountDAO;
import com.accountsoft.model.Tb_inaccount;
import com.accountsoft.model.Tb_outaccount;
import com.github.mikephil.charting.charts.BarLineChartBase.BorderPosition;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.Legend.LegendForm;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.XLabels.XLabelPosition;
import com.github.mikephil.charting.utils.YLabels;

public class myChart extends Activity {
	
	private LineChart mChart;
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mChart = (LineChart) findViewById(R.id.chart1);
        
      

        // 设置在Y轴上是否是从0开始显示
        mChart.setStartAtZero(true);
        //是否在Y轴显示数据，就是曲线上的数据
        mChart.setDrawYValues(true);
        //设置网格
        mChart.setDrawBorder(true);
        mChart.setBorderPositions(new BorderPosition[] {
            BorderPosition.BOTTOM});
     	//在chart上的右下角加描述
        mChart.setDescription("本月收入支出图");
     	//设置Y轴上的单位
     	mChart.setUnit("元"); 
	    //设置透明度
	    mChart.setAlpha(0.8f);
	    //设置网格底下的那条线的颜色
	    mChart.setBorderColor(Color.rgb(100, 216, 214));
	    //设置Y轴前后倒置
        mChart.setInvertYAxisEnabled(false);
        //设置高亮显示
        mChart.setHighlightEnabled(true);
        //设置是否可以触摸，如为false，则不能拖动，缩放等
        mChart.setTouchEnabled(true);
        //设置是否可以拖拽，缩放
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        //设置是否能扩大扩小
        mChart.setPinchZoom(true);
        // 设置背景颜色
        // mChart.setBackgroundColor(Color.GRAY);
        //设置点击chart图对应的数据弹出标注
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        // define an offset to change the original position of the marker||定义一个偏移量来改变标记的原始位置
        // (optional)
        mv.setOffsets(-mv.getMeasuredWidth() / 2, -mv.getMeasuredHeight());
        // set the marker to the chart
        mChart.setMarkerView(mv);
        // enable/disable highlight indicators (the lines that indicate the
        // highlighted Entry) 启用/禁用突出指标
        mChart.setHighlightIndicatorEnabled(false);
        //设置字体格式，如正楷
        Typeface tf = Typeface.createFromAsset(getAssets(),
				"OpenSans-Regular.ttf");
		mChart.setValueTypeface(tf);

		XLabels xl = mChart.getXLabels();
//		xl.setAvoidFirstLastClipping(true);
//		xl.setAdjustXLabels(true);
		xl.setPosition(XLabelPosition.BOTTOM); // 设置X轴的数据在底部显示
		xl.setTypeface(tf); // 设置字体
		xl.setTextSize(10f); // 设置字体大小
		xl.setSpaceBetweenLabels(4); // 设置数据之间的间距

		YLabels yl = mChart.getYLabels();
		
		
		
		// yl.setPosition(YLabelPosition.LEFT_INSIDE); // set the position
		yl.setTypeface(tf); // 设置字体
		yl.setTextSize(10f); // s设置字体大小
		yl.setLabelCount(2); // 设置Y轴最多显示的数据个数
		
        // 加载数据
        setData();
        //从X轴进入的动画
		mChart.animateX(4000);
		mChart.animateY(3000);   //从Y轴进入的动画
		mChart.animateXY(3000, 3000);    //从XY轴一起进入的动画
		
		//设置最小的缩放
		 mChart.setScaleMinima(0.5f, 1f);
		//设置视口
		// mChart.centerViewPort(10, 50);

        // get the legend (only possible after setting data)||获得图例
        Legend l = mChart.getLegend();
        l.setForm(LegendForm.LINE);  //设置图最下面显示的类型
		l.setTypeface(tf);  
		l.setTextSize(15);
		l.setTextColor(Color.rgb(104, 241, 175));
		l.setXEntrySpace(30);
		l.setFormSize(10f); // set the size of the legend forms/shapes

        // 刷新图表
        mChart.invalidate();
    }

    private void setData() {
    	
    	
    	
    	Time daystTime= new Time();
    	String[] aa = new String[daystTime.getDays()];
   // 	Daysaccount daysaccount =new Daysaccount();
    	Double[] inStrings= new Double[this.dayinaccount().size()];
    	
    	Double[] outStrings=new Double[this.dayoutaccount().size()];
    	
    	inStrings=this.dayinaccount().toArray(inStrings);
    	outStrings=this.dayoutaccount().toArray(outStrings);
    	
    	
    	
    	for (int i = 0; i < daystTime.getDays(); i++) {
    		
    		aa[i]=""+(i+1);
			
		}
    	
    	
//    	String[] bb = {"122.00","234.34","85.67","117.90","500.33","113.33","120.78"};
//    	String[] cc = {"100.00","100.00","100.00","100.00","100.00","100.00","100.00"};

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < aa.length; i++) {
            xVals.add(aa[i]);
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < inStrings.length; i++) {
            yVals.add(new Entry((Float.parseFloat(inStrings[i].toString())), i));
       }
        
        ArrayList<Entry> y1Vals = new ArrayList<Entry>();

        for (int i = 0; i < outStrings.length; i++) {
            y1Vals.add(new Entry(Float.parseFloat(outStrings[i].toString()), i));
        }
        

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "收入");
        
        set1.setDrawCubic(true);  //设置曲线为圆滑的线
		set1.setCubicIntensity(0.2f);
		set1.setDrawFilled(false);  //设置包括的范围区域填充颜色
		set1.setDrawCircles(true);  //设置有圆点
		set1.setLineWidth(2f);    //设置线的宽度
		set1.setCircleSize(5f);   //设置小圆的大小
		set1.setHighLightColor(Color.rgb(244, 117, 117));
		set1.setColor(Color.rgb(104, 241, 175));    //设置曲线的颜色
		
		
		  LineDataSet set2 = new LineDataSet(y1Vals, "支出 ");
	        
	        set2.setDrawCubic(true);  //设置曲线为圆滑的线
			set2.setCubicIntensity(0.2f);
			set2.setDrawFilled(false);  //设置包括的范围区域填充颜色
			set2.setDrawCircles(true);  //设置有圆点
			set2.setLineWidth(2f);    //设置线的宽度
			set2.setCircleSize(5f);   //设置小圆的大小
			set2.setHighLightColor(Color.rgb(244, 117, 117));
			set2.setColor(Color.rgb(255, 0, 0));    //设置曲线的颜色

        // create a data object with the datasets
			
	ArrayList<LineDataSet> linesets =new ArrayList<LineDataSet>();
	
	linesets.add(set1);
	linesets.add(set2);
	
	LineData data = new LineData(xVals, linesets);
        // set data
        mChart.setData(data);
       
    }
    
    public ArrayList<Double> dayinaccount() {
		InaccountDAO inaccountDAO = new InaccountDAO(myChart.this);
		Tb_inaccount tb_inaccount = new Tb_inaccount();

		ArrayList<Double> in = new ArrayList<Double>();
		
		
	

		Time time = new Time();
		int day = time.getDays();
		int month = time.getmonth();
		int year = time.getyear();
		
		
		
		//================================================

		for (int n=1; n <= day; n++) {
			String date = "" + year + "-" + month + "-" + n;
			
			double temp =0.0;
			
			for (int i = 0; i < inaccountDAO.findwithdate(date).size(); i++) {
				
				 double intemp=inaccountDAO.findwithdate(date).get(i);
				 
				 temp =temp+intemp;
				 
				
			}

			in.add(temp);

			

		}

		return in;

	}
    
public ArrayList<Double> dayoutaccount() {
		
		outaccountDAO outDAO = new outaccountDAO(myChart.this);
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

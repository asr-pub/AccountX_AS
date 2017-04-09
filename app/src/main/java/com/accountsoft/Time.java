package com.accountsoft;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time  {

	



	public String getWeekAndDay() {
		Calendar calendar = Calendar.getInstance();
		int week = calendar.get(Calendar.WEEK_OF_MONTH);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		if (day == 1) {
			day = 7;
			week = week - 1;
		} else {
			day = day - 1;

		}

		return "今天是本月的第" + week + "周" + ",星期" + day;

	}

	public int getDays() { 
	        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	        String date = sDateFormat.format(new java.util.Date()); 
	       
	        String dates[] = date.split("-"); 
	      
	   
	        int month = Integer.valueOf(dates[1]); 
	        
	        int day =0;
	        
	        if (month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
	        	
	        	day=31;
				
			}else if (month==4||month==6||month==9||month==11) {
				day=30;
			}
	        else {
	        	
	        	  if ((Integer.parseInt(dates[0])%4==0 && Integer.parseInt(dates[0])%100!=0) || Integer.parseInt(dates[0])%400==0) {
				   day=28;
						
			      }
	        	  else {
					day=29;
				}
	        }

	       return day;
	     
	}
	
	public int getmonth() { 
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        String date = sDateFormat.format(new java.util.Date()); 
       
        String dates[] = date.split("-"); 
      
   
        int month = Integer.valueOf(dates[1]);
        
        return month;
        
	}
	
	public int getyear() { 
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        String date = sDateFormat.format(new java.util.Date()); 
       
        String dates[] = date.split("-"); 
      
   
        int year = Integer.valueOf(dates[0]);
        
        return year;
        
	}



}
